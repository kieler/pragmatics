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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_SIGNED_INT", "RULE_FLOAT", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'true'", "'false'", "'csv'", "'json'", "'globalResources'", "'globalOutputs'", "'execute'", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'comparejob'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'rangeanalyses'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'ref'", "'filter'", "'{'", "'}'", "'node'", "'label'", "':'", "'port'", "'layout'", "'['", "']'", "'position'", "'size'", "'edge'", "'->'", "'incoming'", "'outgoing'", "'start'", "'end'", "'bends'", "'|'", "'section'", "'.'", "'parallel'", "'all'", "'layoutBeforeAnalysis'", "'measureExecutionTime'"
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
    public static final int RULE_ID=6;
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
    public static final int T__20=20;
    public static final int T__21=21;
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
    public static final int RULE_SIGNED_INT=4;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int RULE_FLOAT=5;
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


    // $ANTLR start "entryRuleResource"
    // InternalGrana.g:340:1: entryRuleResource : ruleResource EOF ;
    public final void entryRuleResource() throws RecognitionException {
        try {
            // InternalGrana.g:341:1: ( ruleResource EOF )
            // InternalGrana.g:342:1: ruleResource EOF
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
    // InternalGrana.g:349:1: ruleResource : ( ( rule__Resource__Alternatives ) ) ;
    public final void ruleResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:353:2: ( ( ( rule__Resource__Alternatives ) ) )
            // InternalGrana.g:354:1: ( ( rule__Resource__Alternatives ) )
            {
            // InternalGrana.g:354:1: ( ( rule__Resource__Alternatives ) )
            // InternalGrana.g:355:1: ( rule__Resource__Alternatives )
            {
             before(grammarAccess.getResourceAccess().getAlternatives()); 
            // InternalGrana.g:356:1: ( rule__Resource__Alternatives )
            // InternalGrana.g:356:2: rule__Resource__Alternatives
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
    // InternalGrana.g:368:1: entryRuleResourceReference : ruleResourceReference EOF ;
    public final void entryRuleResourceReference() throws RecognitionException {
        try {
            // InternalGrana.g:369:1: ( ruleResourceReference EOF )
            // InternalGrana.g:370:1: ruleResourceReference EOF
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
    // InternalGrana.g:377:1: ruleResourceReference : ( ( rule__ResourceReference__Group__0 ) ) ;
    public final void ruleResourceReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:381:2: ( ( ( rule__ResourceReference__Group__0 ) ) )
            // InternalGrana.g:382:1: ( ( rule__ResourceReference__Group__0 ) )
            {
            // InternalGrana.g:382:1: ( ( rule__ResourceReference__Group__0 ) )
            // InternalGrana.g:383:1: ( rule__ResourceReference__Group__0 )
            {
             before(grammarAccess.getResourceReferenceAccess().getGroup()); 
            // InternalGrana.g:384:1: ( rule__ResourceReference__Group__0 )
            // InternalGrana.g:384:2: rule__ResourceReference__Group__0
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
    // InternalGrana.g:396:1: entryRuleGlobalResourceRef : ruleGlobalResourceRef EOF ;
    public final void entryRuleGlobalResourceRef() throws RecognitionException {
        try {
            // InternalGrana.g:397:1: ( ruleGlobalResourceRef EOF )
            // InternalGrana.g:398:1: ruleGlobalResourceRef EOF
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
    // InternalGrana.g:405:1: ruleGlobalResourceRef : ( ( rule__GlobalResourceRef__Group__0 ) ) ;
    public final void ruleGlobalResourceRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:409:2: ( ( ( rule__GlobalResourceRef__Group__0 ) ) )
            // InternalGrana.g:410:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            {
            // InternalGrana.g:410:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            // InternalGrana.g:411:1: ( rule__GlobalResourceRef__Group__0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getGroup()); 
            // InternalGrana.g:412:1: ( rule__GlobalResourceRef__Group__0 )
            // InternalGrana.g:412:2: rule__GlobalResourceRef__Group__0
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
    // InternalGrana.g:424:1: entryRuleLocalResource : ruleLocalResource EOF ;
    public final void entryRuleLocalResource() throws RecognitionException {
        try {
            // InternalGrana.g:425:1: ( ruleLocalResource EOF )
            // InternalGrana.g:426:1: ruleLocalResource EOF
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
    // InternalGrana.g:433:1: ruleLocalResource : ( ( rule__LocalResource__Group__0 ) ) ;
    public final void ruleLocalResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:437:2: ( ( ( rule__LocalResource__Group__0 ) ) )
            // InternalGrana.g:438:1: ( ( rule__LocalResource__Group__0 ) )
            {
            // InternalGrana.g:438:1: ( ( rule__LocalResource__Group__0 ) )
            // InternalGrana.g:439:1: ( rule__LocalResource__Group__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup()); 
            // InternalGrana.g:440:1: ( rule__LocalResource__Group__0 )
            // InternalGrana.g:440:2: rule__LocalResource__Group__0
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
    // InternalGrana.g:452:1: entryRuleOutput : ruleOutput EOF ;
    public final void entryRuleOutput() throws RecognitionException {
        try {
            // InternalGrana.g:453:1: ( ruleOutput EOF )
            // InternalGrana.g:454:1: ruleOutput EOF
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
    // InternalGrana.g:461:1: ruleOutput : ( ( rule__Output__Alternatives ) ) ;
    public final void ruleOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:465:2: ( ( ( rule__Output__Alternatives ) ) )
            // InternalGrana.g:466:1: ( ( rule__Output__Alternatives ) )
            {
            // InternalGrana.g:466:1: ( ( rule__Output__Alternatives ) )
            // InternalGrana.g:467:1: ( rule__Output__Alternatives )
            {
             before(grammarAccess.getOutputAccess().getAlternatives()); 
            // InternalGrana.g:468:1: ( rule__Output__Alternatives )
            // InternalGrana.g:468:2: rule__Output__Alternatives
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
    // InternalGrana.g:480:1: entryRuleGlobalOutputRef : ruleGlobalOutputRef EOF ;
    public final void entryRuleGlobalOutputRef() throws RecognitionException {
        try {
            // InternalGrana.g:481:1: ( ruleGlobalOutputRef EOF )
            // InternalGrana.g:482:1: ruleGlobalOutputRef EOF
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
    // InternalGrana.g:489:1: ruleGlobalOutputRef : ( ( rule__GlobalOutputRef__Group__0 ) ) ;
    public final void ruleGlobalOutputRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:493:2: ( ( ( rule__GlobalOutputRef__Group__0 ) ) )
            // InternalGrana.g:494:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            {
            // InternalGrana.g:494:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            // InternalGrana.g:495:1: ( rule__GlobalOutputRef__Group__0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getGroup()); 
            // InternalGrana.g:496:1: ( rule__GlobalOutputRef__Group__0 )
            // InternalGrana.g:496:2: rule__GlobalOutputRef__Group__0
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
    // InternalGrana.g:508:1: entryRuleOutputReference : ruleOutputReference EOF ;
    public final void entryRuleOutputReference() throws RecognitionException {
        try {
            // InternalGrana.g:509:1: ( ruleOutputReference EOF )
            // InternalGrana.g:510:1: ruleOutputReference EOF
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
    // InternalGrana.g:517:1: ruleOutputReference : ( ( rule__OutputReference__Group__0 ) ) ;
    public final void ruleOutputReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:521:2: ( ( ( rule__OutputReference__Group__0 ) ) )
            // InternalGrana.g:522:1: ( ( rule__OutputReference__Group__0 ) )
            {
            // InternalGrana.g:522:1: ( ( rule__OutputReference__Group__0 ) )
            // InternalGrana.g:523:1: ( rule__OutputReference__Group__0 )
            {
             before(grammarAccess.getOutputReferenceAccess().getGroup()); 
            // InternalGrana.g:524:1: ( rule__OutputReference__Group__0 )
            // InternalGrana.g:524:2: rule__OutputReference__Group__0
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
    // InternalGrana.g:536:1: entryRuleLocalOutput : ruleLocalOutput EOF ;
    public final void entryRuleLocalOutput() throws RecognitionException {
        try {
            // InternalGrana.g:537:1: ( ruleLocalOutput EOF )
            // InternalGrana.g:538:1: ruleLocalOutput EOF
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
    // InternalGrana.g:545:1: ruleLocalOutput : ( ( rule__LocalOutput__PathAssignment ) ) ;
    public final void ruleLocalOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:549:2: ( ( ( rule__LocalOutput__PathAssignment ) ) )
            // InternalGrana.g:550:1: ( ( rule__LocalOutput__PathAssignment ) )
            {
            // InternalGrana.g:550:1: ( ( rule__LocalOutput__PathAssignment ) )
            // InternalGrana.g:551:1: ( rule__LocalOutput__PathAssignment )
            {
             before(grammarAccess.getLocalOutputAccess().getPathAssignment()); 
            // InternalGrana.g:552:1: ( rule__LocalOutput__PathAssignment )
            // InternalGrana.g:552:2: rule__LocalOutput__PathAssignment
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
    // InternalGrana.g:564:1: entryRuleAnalysis : ruleAnalysis EOF ;
    public final void entryRuleAnalysis() throws RecognitionException {
        try {
            // InternalGrana.g:565:1: ( ruleAnalysis EOF )
            // InternalGrana.g:566:1: ruleAnalysis EOF
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
    // InternalGrana.g:573:1: ruleAnalysis : ( ( rule__Analysis__NameAssignment ) ) ;
    public final void ruleAnalysis() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:577:2: ( ( ( rule__Analysis__NameAssignment ) ) )
            // InternalGrana.g:578:1: ( ( rule__Analysis__NameAssignment ) )
            {
            // InternalGrana.g:578:1: ( ( rule__Analysis__NameAssignment ) )
            // InternalGrana.g:579:1: ( rule__Analysis__NameAssignment )
            {
             before(grammarAccess.getAnalysisAccess().getNameAssignment()); 
            // InternalGrana.g:580:1: ( rule__Analysis__NameAssignment )
            // InternalGrana.g:580:2: rule__Analysis__NameAssignment
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
    // InternalGrana.g:592:1: entryRuleLayoutConfig : ruleLayoutConfig EOF ;
    public final void entryRuleLayoutConfig() throws RecognitionException {
        try {
            // InternalGrana.g:593:1: ( ruleLayoutConfig EOF )
            // InternalGrana.g:594:1: ruleLayoutConfig EOF
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
    // InternalGrana.g:601:1: ruleLayoutConfig : ( ( rule__LayoutConfig__Group__0 ) ) ;
    public final void ruleLayoutConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:605:2: ( ( ( rule__LayoutConfig__Group__0 ) ) )
            // InternalGrana.g:606:1: ( ( rule__LayoutConfig__Group__0 ) )
            {
            // InternalGrana.g:606:1: ( ( rule__LayoutConfig__Group__0 ) )
            // InternalGrana.g:607:1: ( rule__LayoutConfig__Group__0 )
            {
             before(grammarAccess.getLayoutConfigAccess().getGroup()); 
            // InternalGrana.g:608:1: ( rule__LayoutConfig__Group__0 )
            // InternalGrana.g:608:2: rule__LayoutConfig__Group__0
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
    // InternalGrana.g:622:1: entryRuleElkNode : ruleElkNode EOF ;
    public final void entryRuleElkNode() throws RecognitionException {
        try {
            // InternalGrana.g:623:1: ( ruleElkNode EOF )
            // InternalGrana.g:624:1: ruleElkNode EOF
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
    // InternalGrana.g:631:1: ruleElkNode : ( ( rule__ElkNode__Group__0 ) ) ;
    public final void ruleElkNode() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:635:2: ( ( ( rule__ElkNode__Group__0 ) ) )
            // InternalGrana.g:636:1: ( ( rule__ElkNode__Group__0 ) )
            {
            // InternalGrana.g:636:1: ( ( rule__ElkNode__Group__0 ) )
            // InternalGrana.g:637:1: ( rule__ElkNode__Group__0 )
            {
             before(grammarAccess.getElkNodeAccess().getGroup()); 
            // InternalGrana.g:638:1: ( rule__ElkNode__Group__0 )
            // InternalGrana.g:638:2: rule__ElkNode__Group__0
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
    // InternalGrana.g:650:1: entryRuleElkLabel : ruleElkLabel EOF ;
    public final void entryRuleElkLabel() throws RecognitionException {
        try {
            // InternalGrana.g:651:1: ( ruleElkLabel EOF )
            // InternalGrana.g:652:1: ruleElkLabel EOF
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
    // InternalGrana.g:659:1: ruleElkLabel : ( ( rule__ElkLabel__Group__0 ) ) ;
    public final void ruleElkLabel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:663:2: ( ( ( rule__ElkLabel__Group__0 ) ) )
            // InternalGrana.g:664:1: ( ( rule__ElkLabel__Group__0 ) )
            {
            // InternalGrana.g:664:1: ( ( rule__ElkLabel__Group__0 ) )
            // InternalGrana.g:665:1: ( rule__ElkLabel__Group__0 )
            {
             before(grammarAccess.getElkLabelAccess().getGroup()); 
            // InternalGrana.g:666:1: ( rule__ElkLabel__Group__0 )
            // InternalGrana.g:666:2: rule__ElkLabel__Group__0
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
    // InternalGrana.g:678:1: entryRuleElkPort : ruleElkPort EOF ;
    public final void entryRuleElkPort() throws RecognitionException {
        try {
            // InternalGrana.g:679:1: ( ruleElkPort EOF )
            // InternalGrana.g:680:1: ruleElkPort EOF
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
    // InternalGrana.g:687:1: ruleElkPort : ( ( rule__ElkPort__Group__0 ) ) ;
    public final void ruleElkPort() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:691:2: ( ( ( rule__ElkPort__Group__0 ) ) )
            // InternalGrana.g:692:1: ( ( rule__ElkPort__Group__0 ) )
            {
            // InternalGrana.g:692:1: ( ( rule__ElkPort__Group__0 ) )
            // InternalGrana.g:693:1: ( rule__ElkPort__Group__0 )
            {
             before(grammarAccess.getElkPortAccess().getGroup()); 
            // InternalGrana.g:694:1: ( rule__ElkPort__Group__0 )
            // InternalGrana.g:694:2: rule__ElkPort__Group__0
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
    // InternalGrana.g:707:1: ruleShapeLayout : ( ( rule__ShapeLayout__Group__0 ) ) ;
    public final void ruleShapeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:711:2: ( ( ( rule__ShapeLayout__Group__0 ) ) )
            // InternalGrana.g:712:1: ( ( rule__ShapeLayout__Group__0 ) )
            {
            // InternalGrana.g:712:1: ( ( rule__ShapeLayout__Group__0 ) )
            // InternalGrana.g:713:1: ( rule__ShapeLayout__Group__0 )
            {
             before(grammarAccess.getShapeLayoutAccess().getGroup()); 
            // InternalGrana.g:714:1: ( rule__ShapeLayout__Group__0 )
            // InternalGrana.g:714:2: rule__ShapeLayout__Group__0
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
    // InternalGrana.g:726:1: entryRuleElkEdge : ruleElkEdge EOF ;
    public final void entryRuleElkEdge() throws RecognitionException {
        try {
            // InternalGrana.g:727:1: ( ruleElkEdge EOF )
            // InternalGrana.g:728:1: ruleElkEdge EOF
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
    // InternalGrana.g:735:1: ruleElkEdge : ( ( rule__ElkEdge__Group__0 ) ) ;
    public final void ruleElkEdge() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:739:2: ( ( ( rule__ElkEdge__Group__0 ) ) )
            // InternalGrana.g:740:1: ( ( rule__ElkEdge__Group__0 ) )
            {
            // InternalGrana.g:740:1: ( ( rule__ElkEdge__Group__0 ) )
            // InternalGrana.g:741:1: ( rule__ElkEdge__Group__0 )
            {
             before(grammarAccess.getElkEdgeAccess().getGroup()); 
            // InternalGrana.g:742:1: ( rule__ElkEdge__Group__0 )
            // InternalGrana.g:742:2: rule__ElkEdge__Group__0
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
    // InternalGrana.g:755:1: ruleEdgeLayout : ( ( rule__EdgeLayout__Group__0 ) ) ;
    public final void ruleEdgeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:759:2: ( ( ( rule__EdgeLayout__Group__0 ) ) )
            // InternalGrana.g:760:1: ( ( rule__EdgeLayout__Group__0 ) )
            {
            // InternalGrana.g:760:1: ( ( rule__EdgeLayout__Group__0 ) )
            // InternalGrana.g:761:1: ( rule__EdgeLayout__Group__0 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getGroup()); 
            // InternalGrana.g:762:1: ( rule__EdgeLayout__Group__0 )
            // InternalGrana.g:762:2: rule__EdgeLayout__Group__0
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
    // InternalGrana.g:774:1: entryRuleElkSingleEdgeSection : ruleElkSingleEdgeSection EOF ;
    public final void entryRuleElkSingleEdgeSection() throws RecognitionException {
        try {
            // InternalGrana.g:775:1: ( ruleElkSingleEdgeSection EOF )
            // InternalGrana.g:776:1: ruleElkSingleEdgeSection EOF
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
    // InternalGrana.g:783:1: ruleElkSingleEdgeSection : ( ( rule__ElkSingleEdgeSection__Group__0 ) ) ;
    public final void ruleElkSingleEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:787:2: ( ( ( rule__ElkSingleEdgeSection__Group__0 ) ) )
            // InternalGrana.g:788:1: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            {
            // InternalGrana.g:788:1: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            // InternalGrana.g:789:1: ( rule__ElkSingleEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup()); 
            // InternalGrana.g:790:1: ( rule__ElkSingleEdgeSection__Group__0 )
            // InternalGrana.g:790:2: rule__ElkSingleEdgeSection__Group__0
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
    // InternalGrana.g:802:1: entryRuleElkEdgeSection : ruleElkEdgeSection EOF ;
    public final void entryRuleElkEdgeSection() throws RecognitionException {
        try {
            // InternalGrana.g:803:1: ( ruleElkEdgeSection EOF )
            // InternalGrana.g:804:1: ruleElkEdgeSection EOF
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
    // InternalGrana.g:811:1: ruleElkEdgeSection : ( ( rule__ElkEdgeSection__Group__0 ) ) ;
    public final void ruleElkEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:815:2: ( ( ( rule__ElkEdgeSection__Group__0 ) ) )
            // InternalGrana.g:816:1: ( ( rule__ElkEdgeSection__Group__0 ) )
            {
            // InternalGrana.g:816:1: ( ( rule__ElkEdgeSection__Group__0 ) )
            // InternalGrana.g:817:1: ( rule__ElkEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup()); 
            // InternalGrana.g:818:1: ( rule__ElkEdgeSection__Group__0 )
            // InternalGrana.g:818:2: rule__ElkEdgeSection__Group__0
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
    // InternalGrana.g:830:1: entryRuleElkBendPoint : ruleElkBendPoint EOF ;
    public final void entryRuleElkBendPoint() throws RecognitionException {
        try {
            // InternalGrana.g:831:1: ( ruleElkBendPoint EOF )
            // InternalGrana.g:832:1: ruleElkBendPoint EOF
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
    // InternalGrana.g:839:1: ruleElkBendPoint : ( ( rule__ElkBendPoint__Group__0 ) ) ;
    public final void ruleElkBendPoint() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:843:2: ( ( ( rule__ElkBendPoint__Group__0 ) ) )
            // InternalGrana.g:844:1: ( ( rule__ElkBendPoint__Group__0 ) )
            {
            // InternalGrana.g:844:1: ( ( rule__ElkBendPoint__Group__0 ) )
            // InternalGrana.g:845:1: ( rule__ElkBendPoint__Group__0 )
            {
             before(grammarAccess.getElkBendPointAccess().getGroup()); 
            // InternalGrana.g:846:1: ( rule__ElkBendPoint__Group__0 )
            // InternalGrana.g:846:2: rule__ElkBendPoint__Group__0
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


    // $ANTLR start "entryRuleProperty"
    // InternalGrana.g:858:1: entryRuleProperty : ruleProperty EOF ;
    public final void entryRuleProperty() throws RecognitionException {
        try {
            // InternalGrana.g:859:1: ( ruleProperty EOF )
            // InternalGrana.g:860:1: ruleProperty EOF
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
    // InternalGrana.g:867:1: ruleProperty : ( ( rule__Property__Group__0 ) ) ;
    public final void ruleProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:871:2: ( ( ( rule__Property__Group__0 ) ) )
            // InternalGrana.g:872:1: ( ( rule__Property__Group__0 ) )
            {
            // InternalGrana.g:872:1: ( ( rule__Property__Group__0 ) )
            // InternalGrana.g:873:1: ( rule__Property__Group__0 )
            {
             before(grammarAccess.getPropertyAccess().getGroup()); 
            // InternalGrana.g:874:1: ( rule__Property__Group__0 )
            // InternalGrana.g:874:2: rule__Property__Group__0
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


    // $ANTLR start "entryRuleBoolean"
    // InternalGrana.g:914:1: entryRuleBoolean : ruleBoolean EOF ;
    public final void entryRuleBoolean() throws RecognitionException {
        try {
            // InternalGrana.g:915:1: ( ruleBoolean EOF )
            // InternalGrana.g:916:1: ruleBoolean EOF
            {
             before(grammarAccess.getBooleanRule()); 
            pushFollow(FOLLOW_1);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getBooleanRule()); 
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
    // $ANTLR end "entryRuleBoolean"


    // $ANTLR start "ruleBoolean"
    // InternalGrana.g:923:1: ruleBoolean : ( ( rule__Boolean__Alternatives ) ) ;
    public final void ruleBoolean() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:927:2: ( ( ( rule__Boolean__Alternatives ) ) )
            // InternalGrana.g:928:1: ( ( rule__Boolean__Alternatives ) )
            {
            // InternalGrana.g:928:1: ( ( rule__Boolean__Alternatives ) )
            // InternalGrana.g:929:1: ( rule__Boolean__Alternatives )
            {
             before(grammarAccess.getBooleanAccess().getAlternatives()); 
            // InternalGrana.g:930:1: ( rule__Boolean__Alternatives )
            // InternalGrana.g:930:2: rule__Boolean__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Boolean__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBooleanAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolean"


    // $ANTLR start "entryRuleNumber"
    // InternalGrana.g:942:1: entryRuleNumber : ruleNumber EOF ;
    public final void entryRuleNumber() throws RecognitionException {
        try {
            // InternalGrana.g:943:1: ( ruleNumber EOF )
            // InternalGrana.g:944:1: ruleNumber EOF
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
    // InternalGrana.g:951:1: ruleNumber : ( ( rule__Number__Alternatives ) ) ;
    public final void ruleNumber() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:955:2: ( ( ( rule__Number__Alternatives ) ) )
            // InternalGrana.g:956:1: ( ( rule__Number__Alternatives ) )
            {
            // InternalGrana.g:956:1: ( ( rule__Number__Alternatives ) )
            // InternalGrana.g:957:1: ( rule__Number__Alternatives )
            {
             before(grammarAccess.getNumberAccess().getAlternatives()); 
            // InternalGrana.g:958:1: ( rule__Number__Alternatives )
            // InternalGrana.g:958:2: rule__Number__Alternatives
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


    // $ANTLR start "ruleOutputType"
    // InternalGrana.g:1007:1: ruleOutputType : ( ( rule__OutputType__Alternatives ) ) ;
    public final void ruleOutputType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1011:1: ( ( ( rule__OutputType__Alternatives ) ) )
            // InternalGrana.g:1012:1: ( ( rule__OutputType__Alternatives ) )
            {
            // InternalGrana.g:1012:1: ( ( rule__OutputType__Alternatives ) )
            // InternalGrana.g:1013:1: ( rule__OutputType__Alternatives )
            {
             before(grammarAccess.getOutputTypeAccess().getAlternatives()); 
            // InternalGrana.g:1014:1: ( rule__OutputType__Alternatives )
            // InternalGrana.g:1014:2: rule__OutputType__Alternatives
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
    // InternalGrana.g:1025:1: rule__Grana__Alternatives_2_2 : ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) );
    public final void rule__Grana__Alternatives_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1029:1: ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==60) ) {
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
                    // InternalGrana.g:1030:1: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    {
                    // InternalGrana.g:1030:1: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    // InternalGrana.g:1031:1: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_2_0()); 
                    // InternalGrana.g:1032:1: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    // InternalGrana.g:1032:2: rule__Grana__ExecuteAllAssignment_2_2_0
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
                    // InternalGrana.g:1036:6: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    {
                    // InternalGrana.g:1036:6: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    // InternalGrana.g:1037:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    {
                    // InternalGrana.g:1037:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) )
                    // InternalGrana.g:1038:1: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:1039:1: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    // InternalGrana.g:1039:2: rule__Grana__ExecuteAssignment_2_2_1
                    {
                    pushFollow(FOLLOW_3);
                    rule__Grana__ExecuteAssignment_2_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 

                    }

                    // InternalGrana.g:1042:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    // InternalGrana.g:1043:1: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:1044:1: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalGrana.g:1044:2: rule__Grana__ExecuteAssignment_2_2_1
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
    // InternalGrana.g:1054:1: rule__Job__Alternatives : ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) );
    public final void rule__Job__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1058:1: ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) )
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
                    // InternalGrana.g:1059:1: ( ruleRegularJob )
                    {
                    // InternalGrana.g:1059:1: ( ruleRegularJob )
                    // InternalGrana.g:1060:1: ruleRegularJob
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
                    // InternalGrana.g:1065:6: ( ruleRangeJob )
                    {
                    // InternalGrana.g:1065:6: ( ruleRangeJob )
                    // InternalGrana.g:1066:1: ruleRangeJob
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
                    // InternalGrana.g:1071:6: ( ruleCompareJob )
                    {
                    // InternalGrana.g:1071:6: ( ruleCompareJob )
                    // InternalGrana.g:1072:1: ruleCompareJob
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
    // InternalGrana.g:1082:1: rule__RangeJob__Alternatives_12 : ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) );
    public final void rule__RangeJob__Alternatives_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1086:1: ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) )
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
                    // InternalGrana.g:1087:1: ( ( rule__RangeJob__Group_12_0__0 ) )
                    {
                    // InternalGrana.g:1087:1: ( ( rule__RangeJob__Group_12_0__0 ) )
                    // InternalGrana.g:1088:1: ( rule__RangeJob__Group_12_0__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_0()); 
                    // InternalGrana.g:1089:1: ( rule__RangeJob__Group_12_0__0 )
                    // InternalGrana.g:1089:2: rule__RangeJob__Group_12_0__0
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
                    // InternalGrana.g:1093:6: ( ( rule__RangeJob__Group_12_1__0 ) )
                    {
                    // InternalGrana.g:1093:6: ( ( rule__RangeJob__Group_12_1__0 ) )
                    // InternalGrana.g:1094:1: ( rule__RangeJob__Group_12_1__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_1()); 
                    // InternalGrana.g:1095:1: ( rule__RangeJob__Group_12_1__0 )
                    // InternalGrana.g:1095:2: rule__RangeJob__Group_12_1__0
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
    // InternalGrana.g:1104:1: rule__Range__Alternatives : ( ( ruleFloatRange ) | ( ruleIntRange ) );
    public final void rule__Range__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1108:1: ( ( ruleFloatRange ) | ( ruleIntRange ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==31) ) {
                alt5=1;
            }
            else if ( ((LA5_0>=33 && LA5_0<=34)) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalGrana.g:1109:1: ( ruleFloatRange )
                    {
                    // InternalGrana.g:1109:1: ( ruleFloatRange )
                    // InternalGrana.g:1110:1: ruleFloatRange
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
                    // InternalGrana.g:1115:6: ( ruleIntRange )
                    {
                    // InternalGrana.g:1115:6: ( ruleIntRange )
                    // InternalGrana.g:1116:1: ruleIntRange
                    {
                     before(grammarAccess.getRangeAccess().getIntRangeParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1126:1: rule__IntRange__Alternatives : ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) );
    public final void rule__IntRange__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1130:1: ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) )
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
                    // InternalGrana.g:1131:1: ( ruleIntRangeRange )
                    {
                    // InternalGrana.g:1131:1: ( ruleIntRangeRange )
                    // InternalGrana.g:1132:1: ruleIntRangeRange
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
                    // InternalGrana.g:1137:6: ( ruleIntRangeValues )
                    {
                    // InternalGrana.g:1137:6: ( ruleIntRangeValues )
                    // InternalGrana.g:1138:1: ruleIntRangeValues
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
    // InternalGrana.g:1148:1: rule__Resource__Alternatives : ( ( ruleResourceReference ) | ( ruleLocalResource ) );
    public final void rule__Resource__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1152:1: ( ( ruleResourceReference ) | ( ruleLocalResource ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==36) ) {
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
                    // InternalGrana.g:1153:1: ( ruleResourceReference )
                    {
                    // InternalGrana.g:1153:1: ( ruleResourceReference )
                    // InternalGrana.g:1154:1: ruleResourceReference
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
                    // InternalGrana.g:1159:6: ( ruleLocalResource )
                    {
                    // InternalGrana.g:1159:6: ( ruleLocalResource )
                    // InternalGrana.g:1160:1: ruleLocalResource
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
    // InternalGrana.g:1170:1: rule__Output__Alternatives : ( ( ruleOutputReference ) | ( ruleLocalOutput ) );
    public final void rule__Output__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1174:1: ( ( ruleOutputReference ) | ( ruleLocalOutput ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==36) ) {
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
                    // InternalGrana.g:1175:1: ( ruleOutputReference )
                    {
                    // InternalGrana.g:1175:1: ( ruleOutputReference )
                    // InternalGrana.g:1176:1: ruleOutputReference
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
                    // InternalGrana.g:1181:6: ( ruleLocalOutput )
                    {
                    // InternalGrana.g:1181:6: ( ruleLocalOutput )
                    // InternalGrana.g:1182:1: ruleLocalOutput
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
    // InternalGrana.g:1193:1: rule__ElkNode__Alternatives_2_3 : ( ( ( rule__ElkNode__ChildrenAssignment_2_3_0 ) ) | ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_1 ) ) | ( ( rule__ElkNode__PortsAssignment_2_3_2 ) ) | ( ( rule__ElkNode__LabelsAssignment_2_3_3 ) ) );
    public final void rule__ElkNode__Alternatives_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1197:1: ( ( ( rule__ElkNode__ChildrenAssignment_2_3_0 ) ) | ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_1 ) ) | ( ( rule__ElkNode__PortsAssignment_2_3_2 ) ) | ( ( rule__ElkNode__LabelsAssignment_2_3_3 ) ) )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt9=1;
                }
                break;
            case 49:
                {
                alt9=2;
                }
                break;
            case 43:
                {
                alt9=3;
                }
                break;
            case 41:
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
                    // InternalGrana.g:1198:1: ( ( rule__ElkNode__ChildrenAssignment_2_3_0 ) )
                    {
                    // InternalGrana.g:1198:1: ( ( rule__ElkNode__ChildrenAssignment_2_3_0 ) )
                    // InternalGrana.g:1199:1: ( rule__ElkNode__ChildrenAssignment_2_3_0 )
                    {
                     before(grammarAccess.getElkNodeAccess().getChildrenAssignment_2_3_0()); 
                    // InternalGrana.g:1200:1: ( rule__ElkNode__ChildrenAssignment_2_3_0 )
                    // InternalGrana.g:1200:2: rule__ElkNode__ChildrenAssignment_2_3_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__ChildrenAssignment_2_3_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getChildrenAssignment_2_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1204:6: ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_1 ) )
                    {
                    // InternalGrana.g:1204:6: ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_1 ) )
                    // InternalGrana.g:1205:1: ( rule__ElkNode__ContainedEdgesAssignment_2_3_1 )
                    {
                     before(grammarAccess.getElkNodeAccess().getContainedEdgesAssignment_2_3_1()); 
                    // InternalGrana.g:1206:1: ( rule__ElkNode__ContainedEdgesAssignment_2_3_1 )
                    // InternalGrana.g:1206:2: rule__ElkNode__ContainedEdgesAssignment_2_3_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__ContainedEdgesAssignment_2_3_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getContainedEdgesAssignment_2_3_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:1210:6: ( ( rule__ElkNode__PortsAssignment_2_3_2 ) )
                    {
                    // InternalGrana.g:1210:6: ( ( rule__ElkNode__PortsAssignment_2_3_2 ) )
                    // InternalGrana.g:1211:1: ( rule__ElkNode__PortsAssignment_2_3_2 )
                    {
                     before(grammarAccess.getElkNodeAccess().getPortsAssignment_2_3_2()); 
                    // InternalGrana.g:1212:1: ( rule__ElkNode__PortsAssignment_2_3_2 )
                    // InternalGrana.g:1212:2: rule__ElkNode__PortsAssignment_2_3_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__PortsAssignment_2_3_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getPortsAssignment_2_3_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:1216:6: ( ( rule__ElkNode__LabelsAssignment_2_3_3 ) )
                    {
                    // InternalGrana.g:1216:6: ( ( rule__ElkNode__LabelsAssignment_2_3_3 ) )
                    // InternalGrana.g:1217:1: ( rule__ElkNode__LabelsAssignment_2_3_3 )
                    {
                     before(grammarAccess.getElkNodeAccess().getLabelsAssignment_2_3_3()); 
                    // InternalGrana.g:1218:1: ( rule__ElkNode__LabelsAssignment_2_3_3 )
                    // InternalGrana.g:1218:2: rule__ElkNode__LabelsAssignment_2_3_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__LabelsAssignment_2_3_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getLabelsAssignment_2_3_3()); 

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
    // InternalGrana.g:1227:1: rule__EdgeLayout__Alternatives_2 : ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) );
    public final void rule__EdgeLayout__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1231:1: ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==EOF||LA11_0==46||(LA11_0>=51 && LA11_0<=55)) ) {
                alt11=1;
            }
            else if ( (LA11_0==57) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalGrana.g:1232:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    {
                    // InternalGrana.g:1232:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    // InternalGrana.g:1233:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0()); 
                    // InternalGrana.g:1234:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    // InternalGrana.g:1234:2: rule__EdgeLayout__SectionsAssignment_2_0
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
                    // InternalGrana.g:1238:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    {
                    // InternalGrana.g:1238:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    // InternalGrana.g:1239:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    {
                    // InternalGrana.g:1239:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) )
                    // InternalGrana.g:1240:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalGrana.g:1241:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    // InternalGrana.g:1241:2: rule__EdgeLayout__SectionsAssignment_2_1
                    {
                    pushFollow(FOLLOW_4);
                    rule__EdgeLayout__SectionsAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 

                    }

                    // InternalGrana.g:1244:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    // InternalGrana.g:1245:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalGrana.g:1246:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==57) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalGrana.g:1246:2: rule__EdgeLayout__SectionsAssignment_2_1
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


    // $ANTLR start "rule__Property__Alternatives_2"
    // InternalGrana.g:1256:1: rule__Property__Alternatives_2 : ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( ( rule__Property__ValueAssignment_2_4 ) ) );
    public final void rule__Property__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1260:1: ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( ( rule__Property__ValueAssignment_2_4 ) ) )
            int alt12=5;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt12=1;
                }
                break;
            case RULE_ID:
                {
                alt12=2;
                }
                break;
            case 13:
            case 14:
                {
                alt12=3;
                }
                break;
            case RULE_SIGNED_INT:
                {
                alt12=4;
                }
                break;
            case RULE_FLOAT:
                {
                alt12=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // InternalGrana.g:1261:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    {
                    // InternalGrana.g:1261:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    // InternalGrana.g:1262:1: ( rule__Property__ValueAssignment_2_0 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_0()); 
                    // InternalGrana.g:1263:1: ( rule__Property__ValueAssignment_2_0 )
                    // InternalGrana.g:1263:2: rule__Property__ValueAssignment_2_0
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
                    // InternalGrana.g:1267:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    {
                    // InternalGrana.g:1267:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    // InternalGrana.g:1268:1: ( rule__Property__ValueAssignment_2_1 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_1()); 
                    // InternalGrana.g:1269:1: ( rule__Property__ValueAssignment_2_1 )
                    // InternalGrana.g:1269:2: rule__Property__ValueAssignment_2_1
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
                    // InternalGrana.g:1273:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    {
                    // InternalGrana.g:1273:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    // InternalGrana.g:1274:1: ( rule__Property__ValueAssignment_2_2 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_2()); 
                    // InternalGrana.g:1275:1: ( rule__Property__ValueAssignment_2_2 )
                    // InternalGrana.g:1275:2: rule__Property__ValueAssignment_2_2
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
                    // InternalGrana.g:1279:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    {
                    // InternalGrana.g:1279:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    // InternalGrana.g:1280:1: ( rule__Property__ValueAssignment_2_3 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 
                    // InternalGrana.g:1281:1: ( rule__Property__ValueAssignment_2_3 )
                    // InternalGrana.g:1281:2: rule__Property__ValueAssignment_2_3
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
                    // InternalGrana.g:1285:6: ( ( rule__Property__ValueAssignment_2_4 ) )
                    {
                    // InternalGrana.g:1285:6: ( ( rule__Property__ValueAssignment_2_4 ) )
                    // InternalGrana.g:1286:1: ( rule__Property__ValueAssignment_2_4 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_4()); 
                    // InternalGrana.g:1287:1: ( rule__Property__ValueAssignment_2_4 )
                    // InternalGrana.g:1287:2: rule__Property__ValueAssignment_2_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Property__ValueAssignment_2_4();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_4()); 

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


    // $ANTLR start "rule__Boolean__Alternatives"
    // InternalGrana.g:1296:1: rule__Boolean__Alternatives : ( ( 'true' ) | ( 'false' ) );
    public final void rule__Boolean__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1300:1: ( ( 'true' ) | ( 'false' ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==13) ) {
                alt13=1;
            }
            else if ( (LA13_0==14) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalGrana.g:1301:1: ( 'true' )
                    {
                    // InternalGrana.g:1301:1: ( 'true' )
                    // InternalGrana.g:1302:1: 'true'
                    {
                     before(grammarAccess.getBooleanAccess().getTrueKeyword_0()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getBooleanAccess().getTrueKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1309:6: ( 'false' )
                    {
                    // InternalGrana.g:1309:6: ( 'false' )
                    // InternalGrana.g:1310:1: 'false'
                    {
                     before(grammarAccess.getBooleanAccess().getFalseKeyword_1()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getBooleanAccess().getFalseKeyword_1()); 

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
    // $ANTLR end "rule__Boolean__Alternatives"


    // $ANTLR start "rule__Number__Alternatives"
    // InternalGrana.g:1322:1: rule__Number__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__Number__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1326:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
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
                    // InternalGrana.g:1327:1: ( RULE_SIGNED_INT )
                    {
                    // InternalGrana.g:1327:1: ( RULE_SIGNED_INT )
                    // InternalGrana.g:1328:1: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FOLLOW_2); 
                     after(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1333:6: ( RULE_FLOAT )
                    {
                    // InternalGrana.g:1333:6: ( RULE_FLOAT )
                    // InternalGrana.g:1334:1: RULE_FLOAT
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


    // $ANTLR start "rule__OutputType__Alternatives"
    // InternalGrana.g:1344:1: rule__OutputType__Alternatives : ( ( ( 'csv' ) ) | ( ( 'json' ) ) );
    public final void rule__OutputType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1348:1: ( ( ( 'csv' ) ) | ( ( 'json' ) ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==15) ) {
                alt15=1;
            }
            else if ( (LA15_0==16) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // InternalGrana.g:1349:1: ( ( 'csv' ) )
                    {
                    // InternalGrana.g:1349:1: ( ( 'csv' ) )
                    // InternalGrana.g:1350:1: ( 'csv' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 
                    // InternalGrana.g:1351:1: ( 'csv' )
                    // InternalGrana.g:1351:3: 'csv'
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1356:6: ( ( 'json' ) )
                    {
                    // InternalGrana.g:1356:6: ( ( 'json' ) )
                    // InternalGrana.g:1357:1: ( 'json' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1()); 
                    // InternalGrana.g:1358:1: ( 'json' )
                    // InternalGrana.g:1358:3: 'json'
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
    // InternalGrana.g:1370:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1374:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // InternalGrana.g:1375:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
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
    // InternalGrana.g:1382:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1386:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // InternalGrana.g:1387:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // InternalGrana.g:1387:1: ( ( rule__Grana__Group_0__0 )? )
            // InternalGrana.g:1388:1: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // InternalGrana.g:1389:1: ( rule__Grana__Group_0__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==17) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalGrana.g:1389:2: rule__Grana__Group_0__0
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
    // InternalGrana.g:1399:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl rule__Grana__Group__2 ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1403:1: ( rule__Grana__Group__1__Impl rule__Grana__Group__2 )
            // InternalGrana.g:1404:2: rule__Grana__Group__1__Impl rule__Grana__Group__2
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
    // InternalGrana.g:1411:1: rule__Grana__Group__1__Impl : ( ( rule__Grana__Group_1__0 )? ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1415:1: ( ( ( rule__Grana__Group_1__0 )? ) )
            // InternalGrana.g:1416:1: ( ( rule__Grana__Group_1__0 )? )
            {
            // InternalGrana.g:1416:1: ( ( rule__Grana__Group_1__0 )? )
            // InternalGrana.g:1417:1: ( rule__Grana__Group_1__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_1()); 
            // InternalGrana.g:1418:1: ( rule__Grana__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalGrana.g:1418:2: rule__Grana__Group_1__0
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
    // InternalGrana.g:1428:1: rule__Grana__Group__2 : rule__Grana__Group__2__Impl rule__Grana__Group__3 ;
    public final void rule__Grana__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1432:1: ( rule__Grana__Group__2__Impl rule__Grana__Group__3 )
            // InternalGrana.g:1433:2: rule__Grana__Group__2__Impl rule__Grana__Group__3
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
    // InternalGrana.g:1440:1: rule__Grana__Group__2__Impl : ( ( rule__Grana__Group_2__0 ) ) ;
    public final void rule__Grana__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1444:1: ( ( ( rule__Grana__Group_2__0 ) ) )
            // InternalGrana.g:1445:1: ( ( rule__Grana__Group_2__0 ) )
            {
            // InternalGrana.g:1445:1: ( ( rule__Grana__Group_2__0 ) )
            // InternalGrana.g:1446:1: ( rule__Grana__Group_2__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup_2()); 
            // InternalGrana.g:1447:1: ( rule__Grana__Group_2__0 )
            // InternalGrana.g:1447:2: rule__Grana__Group_2__0
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
    // InternalGrana.g:1457:1: rule__Grana__Group__3 : rule__Grana__Group__3__Impl ;
    public final void rule__Grana__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1461:1: ( rule__Grana__Group__3__Impl )
            // InternalGrana.g:1462:2: rule__Grana__Group__3__Impl
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
    // InternalGrana.g:1468:1: rule__Grana__Group__3__Impl : ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) ;
    public final void rule__Grana__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1472:1: ( ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) )
            // InternalGrana.g:1473:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            {
            // InternalGrana.g:1473:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            // InternalGrana.g:1474:1: ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* )
            {
            // InternalGrana.g:1474:1: ( ( rule__Grana__JobsAssignment_3 ) )
            // InternalGrana.g:1475:1: ( rule__Grana__JobsAssignment_3 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1476:1: ( rule__Grana__JobsAssignment_3 )
            // InternalGrana.g:1476:2: rule__Grana__JobsAssignment_3
            {
            pushFollow(FOLLOW_7);
            rule__Grana__JobsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }

            // InternalGrana.g:1479:1: ( ( rule__Grana__JobsAssignment_3 )* )
            // InternalGrana.g:1480:1: ( rule__Grana__JobsAssignment_3 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1481:1: ( rule__Grana__JobsAssignment_3 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==20||(LA18_0>=25 && LA18_0<=26)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalGrana.g:1481:2: rule__Grana__JobsAssignment_3
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Grana__JobsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
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
    // InternalGrana.g:1500:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1504:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // InternalGrana.g:1505:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
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
    // InternalGrana.g:1512:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1516:1: ( ( 'globalResources' ) )
            // InternalGrana.g:1517:1: ( 'globalResources' )
            {
            // InternalGrana.g:1517:1: ( 'globalResources' )
            // InternalGrana.g:1518:1: 'globalResources'
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
    // InternalGrana.g:1531:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1535:1: ( rule__Grana__Group_0__1__Impl )
            // InternalGrana.g:1536:2: rule__Grana__Group_0__1__Impl
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
    // InternalGrana.g:1542:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1546:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // InternalGrana.g:1547:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // InternalGrana.g:1547:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // InternalGrana.g:1548:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // InternalGrana.g:1549:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalGrana.g:1549:2: rule__Grana__GlobalResourcesAssignment_0_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Grana__GlobalResourcesAssignment_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // InternalGrana.g:1563:1: rule__Grana__Group_1__0 : rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 ;
    public final void rule__Grana__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1567:1: ( rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 )
            // InternalGrana.g:1568:2: rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1
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
    // InternalGrana.g:1575:1: rule__Grana__Group_1__0__Impl : ( 'globalOutputs' ) ;
    public final void rule__Grana__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1579:1: ( ( 'globalOutputs' ) )
            // InternalGrana.g:1580:1: ( 'globalOutputs' )
            {
            // InternalGrana.g:1580:1: ( 'globalOutputs' )
            // InternalGrana.g:1581:1: 'globalOutputs'
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
    // InternalGrana.g:1594:1: rule__Grana__Group_1__1 : rule__Grana__Group_1__1__Impl ;
    public final void rule__Grana__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1598:1: ( rule__Grana__Group_1__1__Impl )
            // InternalGrana.g:1599:2: rule__Grana__Group_1__1__Impl
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
    // InternalGrana.g:1605:1: rule__Grana__Group_1__1__Impl : ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) ;
    public final void rule__Grana__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1609:1: ( ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) )
            // InternalGrana.g:1610:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            {
            // InternalGrana.g:1610:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            // InternalGrana.g:1611:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 
            // InternalGrana.g:1612:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalGrana.g:1612:2: rule__Grana__GloobalOutputsAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Grana__GloobalOutputsAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // InternalGrana.g:1626:1: rule__Grana__Group_2__0 : rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 ;
    public final void rule__Grana__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1630:1: ( rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 )
            // InternalGrana.g:1631:2: rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1
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
    // InternalGrana.g:1638:1: rule__Grana__Group_2__0__Impl : ( 'execute' ) ;
    public final void rule__Grana__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1642:1: ( ( 'execute' ) )
            // InternalGrana.g:1643:1: ( 'execute' )
            {
            // InternalGrana.g:1643:1: ( 'execute' )
            // InternalGrana.g:1644:1: 'execute'
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
    // InternalGrana.g:1657:1: rule__Grana__Group_2__1 : rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 ;
    public final void rule__Grana__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1661:1: ( rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 )
            // InternalGrana.g:1662:2: rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2
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
    // InternalGrana.g:1669:1: rule__Grana__Group_2__1__Impl : ( ( rule__Grana__ParallelAssignment_2_1 )? ) ;
    public final void rule__Grana__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1673:1: ( ( ( rule__Grana__ParallelAssignment_2_1 )? ) )
            // InternalGrana.g:1674:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            {
            // InternalGrana.g:1674:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            // InternalGrana.g:1675:1: ( rule__Grana__ParallelAssignment_2_1 )?
            {
             before(grammarAccess.getGranaAccess().getParallelAssignment_2_1()); 
            // InternalGrana.g:1676:1: ( rule__Grana__ParallelAssignment_2_1 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==59) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalGrana.g:1676:2: rule__Grana__ParallelAssignment_2_1
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
    // InternalGrana.g:1686:1: rule__Grana__Group_2__2 : rule__Grana__Group_2__2__Impl ;
    public final void rule__Grana__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1690:1: ( rule__Grana__Group_2__2__Impl )
            // InternalGrana.g:1691:2: rule__Grana__Group_2__2__Impl
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
    // InternalGrana.g:1697:1: rule__Grana__Group_2__2__Impl : ( ( rule__Grana__Alternatives_2_2 ) ) ;
    public final void rule__Grana__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1701:1: ( ( ( rule__Grana__Alternatives_2_2 ) ) )
            // InternalGrana.g:1702:1: ( ( rule__Grana__Alternatives_2_2 ) )
            {
            // InternalGrana.g:1702:1: ( ( rule__Grana__Alternatives_2_2 ) )
            // InternalGrana.g:1703:1: ( rule__Grana__Alternatives_2_2 )
            {
             before(grammarAccess.getGranaAccess().getAlternatives_2_2()); 
            // InternalGrana.g:1704:1: ( rule__Grana__Alternatives_2_2 )
            // InternalGrana.g:1704:2: rule__Grana__Alternatives_2_2
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
    // InternalGrana.g:1720:1: rule__RegularJob__Group__0 : rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 ;
    public final void rule__RegularJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1724:1: ( rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 )
            // InternalGrana.g:1725:2: rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1
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
    // InternalGrana.g:1732:1: rule__RegularJob__Group__0__Impl : ( 'job' ) ;
    public final void rule__RegularJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1736:1: ( ( 'job' ) )
            // InternalGrana.g:1737:1: ( 'job' )
            {
            // InternalGrana.g:1737:1: ( 'job' )
            // InternalGrana.g:1738:1: 'job'
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
    // InternalGrana.g:1751:1: rule__RegularJob__Group__1 : rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 ;
    public final void rule__RegularJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1755:1: ( rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 )
            // InternalGrana.g:1756:2: rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2
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
    // InternalGrana.g:1763:1: rule__RegularJob__Group__1__Impl : ( ( rule__RegularJob__NameAssignment_1 ) ) ;
    public final void rule__RegularJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1767:1: ( ( ( rule__RegularJob__NameAssignment_1 ) ) )
            // InternalGrana.g:1768:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:1768:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            // InternalGrana.g:1769:1: ( rule__RegularJob__NameAssignment_1 )
            {
             before(grammarAccess.getRegularJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:1770:1: ( rule__RegularJob__NameAssignment_1 )
            // InternalGrana.g:1770:2: rule__RegularJob__NameAssignment_1
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
    // InternalGrana.g:1780:1: rule__RegularJob__Group__2 : rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 ;
    public final void rule__RegularJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1784:1: ( rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 )
            // InternalGrana.g:1785:2: rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3
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
    // InternalGrana.g:1792:1: rule__RegularJob__Group__2__Impl : ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) ;
    public final void rule__RegularJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1796:1: ( ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) )
            // InternalGrana.g:1797:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            {
            // InternalGrana.g:1797:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            // InternalGrana.g:1798:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2()); 
            // InternalGrana.g:1799:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==61) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalGrana.g:1799:2: rule__RegularJob__LayoutBeforeAnalysisAssignment_2
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
    // InternalGrana.g:1809:1: rule__RegularJob__Group__3 : rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 ;
    public final void rule__RegularJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1813:1: ( rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 )
            // InternalGrana.g:1814:2: rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4
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
    // InternalGrana.g:1821:1: rule__RegularJob__Group__3__Impl : ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) ;
    public final void rule__RegularJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1825:1: ( ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) )
            // InternalGrana.g:1826:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            {
            // InternalGrana.g:1826:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            // InternalGrana.g:1827:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3()); 
            // InternalGrana.g:1828:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==62) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalGrana.g:1828:2: rule__RegularJob__MeasureExecutionTimeAssignment_3
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
    // InternalGrana.g:1838:1: rule__RegularJob__Group__4 : rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 ;
    public final void rule__RegularJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1842:1: ( rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 )
            // InternalGrana.g:1843:2: rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5
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
    // InternalGrana.g:1850:1: rule__RegularJob__Group__4__Impl : ( 'resources' ) ;
    public final void rule__RegularJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1854:1: ( ( 'resources' ) )
            // InternalGrana.g:1855:1: ( 'resources' )
            {
            // InternalGrana.g:1855:1: ( 'resources' )
            // InternalGrana.g:1856:1: 'resources'
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
    // InternalGrana.g:1869:1: rule__RegularJob__Group__5 : rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 ;
    public final void rule__RegularJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1873:1: ( rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 )
            // InternalGrana.g:1874:2: rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6
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
    // InternalGrana.g:1881:1: rule__RegularJob__Group__5__Impl : ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) ;
    public final void rule__RegularJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1885:1: ( ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) )
            // InternalGrana.g:1886:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            {
            // InternalGrana.g:1886:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            // InternalGrana.g:1887:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            {
            // InternalGrana.g:1887:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) )
            // InternalGrana.g:1888:1: ( rule__RegularJob__ResourcesAssignment_5 )
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:1889:1: ( rule__RegularJob__ResourcesAssignment_5 )
            // InternalGrana.g:1889:2: rule__RegularJob__ResourcesAssignment_5
            {
            pushFollow(FOLLOW_13);
            rule__RegularJob__ResourcesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 

            }

            // InternalGrana.g:1892:1: ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            // InternalGrana.g:1893:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:1894:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_STRING||LA24_0==36) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalGrana.g:1894:2: rule__RegularJob__ResourcesAssignment_5
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__RegularJob__ResourcesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // InternalGrana.g:1905:1: rule__RegularJob__Group__6 : rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 ;
    public final void rule__RegularJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1909:1: ( rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 )
            // InternalGrana.g:1910:2: rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7
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
    // InternalGrana.g:1917:1: rule__RegularJob__Group__6__Impl : ( 'layoutoptions' ) ;
    public final void rule__RegularJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1921:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:1922:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:1922:1: ( 'layoutoptions' )
            // InternalGrana.g:1923:1: 'layoutoptions'
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
    // InternalGrana.g:1936:1: rule__RegularJob__Group__7 : rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 ;
    public final void rule__RegularJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1940:1: ( rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 )
            // InternalGrana.g:1941:2: rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8
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
    // InternalGrana.g:1948:1: rule__RegularJob__Group__7__Impl : ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) ;
    public final void rule__RegularJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1952:1: ( ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) )
            // InternalGrana.g:1953:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            {
            // InternalGrana.g:1953:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            // InternalGrana.g:1954:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            {
            // InternalGrana.g:1954:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) )
            // InternalGrana.g:1955:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:1956:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            // InternalGrana.g:1956:2: rule__RegularJob__LayoutOptionsAssignment_7
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__LayoutOptionsAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 

            }

            // InternalGrana.g:1959:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            // InternalGrana.g:1960:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:1961:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_ID) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalGrana.g:1961:2: rule__RegularJob__LayoutOptionsAssignment_7
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RegularJob__LayoutOptionsAssignment_7();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // InternalGrana.g:1972:1: rule__RegularJob__Group__8 : rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 ;
    public final void rule__RegularJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1976:1: ( rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 )
            // InternalGrana.g:1977:2: rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9
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
    // InternalGrana.g:1984:1: rule__RegularJob__Group__8__Impl : ( 'analyses' ) ;
    public final void rule__RegularJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1988:1: ( ( 'analyses' ) )
            // InternalGrana.g:1989:1: ( 'analyses' )
            {
            // InternalGrana.g:1989:1: ( 'analyses' )
            // InternalGrana.g:1990:1: 'analyses'
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
    // InternalGrana.g:2003:1: rule__RegularJob__Group__9 : rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 ;
    public final void rule__RegularJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2007:1: ( rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 )
            // InternalGrana.g:2008:2: rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10
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
    // InternalGrana.g:2015:1: rule__RegularJob__Group__9__Impl : ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) ;
    public final void rule__RegularJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2019:1: ( ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) )
            // InternalGrana.g:2020:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            {
            // InternalGrana.g:2020:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            // InternalGrana.g:2021:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            {
            // InternalGrana.g:2021:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) )
            // InternalGrana.g:2022:1: ( rule__RegularJob__AnalysesAssignment_9 )
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:2023:1: ( rule__RegularJob__AnalysesAssignment_9 )
            // InternalGrana.g:2023:2: rule__RegularJob__AnalysesAssignment_9
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__AnalysesAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 

            }

            // InternalGrana.g:2026:1: ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            // InternalGrana.g:2027:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:2028:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalGrana.g:2028:2: rule__RegularJob__AnalysesAssignment_9
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RegularJob__AnalysesAssignment_9();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
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
    // InternalGrana.g:2039:1: rule__RegularJob__Group__10 : rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 ;
    public final void rule__RegularJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2043:1: ( rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 )
            // InternalGrana.g:2044:2: rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11
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
    // InternalGrana.g:2051:1: rule__RegularJob__Group__10__Impl : ( 'output' ) ;
    public final void rule__RegularJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2055:1: ( ( 'output' ) )
            // InternalGrana.g:2056:1: ( 'output' )
            {
            // InternalGrana.g:2056:1: ( 'output' )
            // InternalGrana.g:2057:1: 'output'
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
    // InternalGrana.g:2070:1: rule__RegularJob__Group__11 : rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 ;
    public final void rule__RegularJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2074:1: ( rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 )
            // InternalGrana.g:2075:2: rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12
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
    // InternalGrana.g:2082:1: rule__RegularJob__Group__11__Impl : ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) ;
    public final void rule__RegularJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2086:1: ( ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) )
            // InternalGrana.g:2087:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            {
            // InternalGrana.g:2087:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            // InternalGrana.g:2088:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            {
             before(grammarAccess.getRegularJobAccess().getOutputTypeAssignment_11()); 
            // InternalGrana.g:2089:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=15 && LA27_0<=16)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalGrana.g:2089:2: rule__RegularJob__OutputTypeAssignment_11
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
    // InternalGrana.g:2099:1: rule__RegularJob__Group__12 : rule__RegularJob__Group__12__Impl ;
    public final void rule__RegularJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2103:1: ( rule__RegularJob__Group__12__Impl )
            // InternalGrana.g:2104:2: rule__RegularJob__Group__12__Impl
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
    // InternalGrana.g:2110:1: rule__RegularJob__Group__12__Impl : ( ( rule__RegularJob__OutputAssignment_12 ) ) ;
    public final void rule__RegularJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2114:1: ( ( ( rule__RegularJob__OutputAssignment_12 ) ) )
            // InternalGrana.g:2115:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            {
            // InternalGrana.g:2115:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            // InternalGrana.g:2116:1: ( rule__RegularJob__OutputAssignment_12 )
            {
             before(grammarAccess.getRegularJobAccess().getOutputAssignment_12()); 
            // InternalGrana.g:2117:1: ( rule__RegularJob__OutputAssignment_12 )
            // InternalGrana.g:2117:2: rule__RegularJob__OutputAssignment_12
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
    // InternalGrana.g:2153:1: rule__CompareJob__Group__0 : rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 ;
    public final void rule__CompareJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2157:1: ( rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 )
            // InternalGrana.g:2158:2: rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1
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
    // InternalGrana.g:2165:1: rule__CompareJob__Group__0__Impl : ( 'comparejob' ) ;
    public final void rule__CompareJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2169:1: ( ( 'comparejob' ) )
            // InternalGrana.g:2170:1: ( 'comparejob' )
            {
            // InternalGrana.g:2170:1: ( 'comparejob' )
            // InternalGrana.g:2171:1: 'comparejob'
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
    // InternalGrana.g:2184:1: rule__CompareJob__Group__1 : rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 ;
    public final void rule__CompareJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2188:1: ( rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 )
            // InternalGrana.g:2189:2: rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2
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
    // InternalGrana.g:2196:1: rule__CompareJob__Group__1__Impl : ( ( rule__CompareJob__NameAssignment_1 ) ) ;
    public final void rule__CompareJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2200:1: ( ( ( rule__CompareJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2201:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2201:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            // InternalGrana.g:2202:1: ( rule__CompareJob__NameAssignment_1 )
            {
             before(grammarAccess.getCompareJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2203:1: ( rule__CompareJob__NameAssignment_1 )
            // InternalGrana.g:2203:2: rule__CompareJob__NameAssignment_1
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
    // InternalGrana.g:2213:1: rule__CompareJob__Group__2 : rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 ;
    public final void rule__CompareJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2217:1: ( rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 )
            // InternalGrana.g:2218:2: rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3
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
    // InternalGrana.g:2225:1: rule__CompareJob__Group__2__Impl : ( 'resources' ) ;
    public final void rule__CompareJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2229:1: ( ( 'resources' ) )
            // InternalGrana.g:2230:1: ( 'resources' )
            {
            // InternalGrana.g:2230:1: ( 'resources' )
            // InternalGrana.g:2231:1: 'resources'
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
    // InternalGrana.g:2244:1: rule__CompareJob__Group__3 : rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 ;
    public final void rule__CompareJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2248:1: ( rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 )
            // InternalGrana.g:2249:2: rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4
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
    // InternalGrana.g:2256:1: rule__CompareJob__Group__3__Impl : ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) ;
    public final void rule__CompareJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2260:1: ( ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) )
            // InternalGrana.g:2261:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            {
            // InternalGrana.g:2261:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            // InternalGrana.g:2262:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            {
            // InternalGrana.g:2262:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) )
            // InternalGrana.g:2263:1: ( rule__CompareJob__ResourcesAssignment_3 )
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2264:1: ( rule__CompareJob__ResourcesAssignment_3 )
            // InternalGrana.g:2264:2: rule__CompareJob__ResourcesAssignment_3
            {
            pushFollow(FOLLOW_13);
            rule__CompareJob__ResourcesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 

            }

            // InternalGrana.g:2267:1: ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            // InternalGrana.g:2268:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2269:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_STRING||LA28_0==36) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalGrana.g:2269:2: rule__CompareJob__ResourcesAssignment_3
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__CompareJob__ResourcesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
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
    // InternalGrana.g:2280:1: rule__CompareJob__Group__4 : rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 ;
    public final void rule__CompareJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2284:1: ( rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 )
            // InternalGrana.g:2285:2: rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5
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
    // InternalGrana.g:2292:1: rule__CompareJob__Group__4__Impl : ( 'layoutoptions' ) ;
    public final void rule__CompareJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2296:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2297:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2297:1: ( 'layoutoptions' )
            // InternalGrana.g:2298:1: 'layoutoptions'
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
    // InternalGrana.g:2311:1: rule__CompareJob__Group__5 : rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 ;
    public final void rule__CompareJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2315:1: ( rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 )
            // InternalGrana.g:2316:2: rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6
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
    // InternalGrana.g:2323:1: rule__CompareJob__Group__5__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) ;
    public final void rule__CompareJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2327:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) )
            // InternalGrana.g:2328:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            {
            // InternalGrana.g:2328:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            // InternalGrana.g:2329:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_5()); 
            // InternalGrana.g:2330:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            // InternalGrana.g:2330:2: rule__CompareJob__LayoutOptionsAssignment_5
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
    // InternalGrana.g:2340:1: rule__CompareJob__Group__6 : rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 ;
    public final void rule__CompareJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2344:1: ( rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 )
            // InternalGrana.g:2345:2: rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7
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
    // InternalGrana.g:2352:1: rule__CompareJob__Group__6__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) ;
    public final void rule__CompareJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2356:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) )
            // InternalGrana.g:2357:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            {
            // InternalGrana.g:2357:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2358:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2359:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2359:2: rule__CompareJob__LayoutOptionsAssignment_6
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
    // InternalGrana.g:2369:1: rule__CompareJob__Group__7 : rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 ;
    public final void rule__CompareJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2373:1: ( rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 )
            // InternalGrana.g:2374:2: rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8
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
    // InternalGrana.g:2381:1: rule__CompareJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__CompareJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2385:1: ( ( 'analyses' ) )
            // InternalGrana.g:2386:1: ( 'analyses' )
            {
            // InternalGrana.g:2386:1: ( 'analyses' )
            // InternalGrana.g:2387:1: 'analyses'
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
    // InternalGrana.g:2400:1: rule__CompareJob__Group__8 : rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 ;
    public final void rule__CompareJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2404:1: ( rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 )
            // InternalGrana.g:2405:2: rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9
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
    // InternalGrana.g:2412:1: rule__CompareJob__Group__8__Impl : ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__CompareJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2416:1: ( ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2417:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2417:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2418:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2418:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2419:1: ( rule__CompareJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2420:1: ( rule__CompareJob__AnalysesAssignment_8 )
            // InternalGrana.g:2420:2: rule__CompareJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__CompareJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2423:1: ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2424:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2425:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_ID) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalGrana.g:2425:2: rule__CompareJob__AnalysesAssignment_8
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__CompareJob__AnalysesAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
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
    // InternalGrana.g:2436:1: rule__CompareJob__Group__9 : rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 ;
    public final void rule__CompareJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2440:1: ( rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 )
            // InternalGrana.g:2441:2: rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10
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
    // InternalGrana.g:2448:1: rule__CompareJob__Group__9__Impl : ( 'output' ) ;
    public final void rule__CompareJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2452:1: ( ( 'output' ) )
            // InternalGrana.g:2453:1: ( 'output' )
            {
            // InternalGrana.g:2453:1: ( 'output' )
            // InternalGrana.g:2454:1: 'output'
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
    // InternalGrana.g:2467:1: rule__CompareJob__Group__10 : rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 ;
    public final void rule__CompareJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2471:1: ( rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 )
            // InternalGrana.g:2472:2: rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11
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
    // InternalGrana.g:2479:1: rule__CompareJob__Group__10__Impl : ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) ;
    public final void rule__CompareJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2483:1: ( ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) )
            // InternalGrana.g:2484:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            {
            // InternalGrana.g:2484:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            // InternalGrana.g:2485:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            {
             before(grammarAccess.getCompareJobAccess().getOutputTypeAssignment_10()); 
            // InternalGrana.g:2486:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=15 && LA30_0<=16)) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalGrana.g:2486:2: rule__CompareJob__OutputTypeAssignment_10
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
    // InternalGrana.g:2496:1: rule__CompareJob__Group__11 : rule__CompareJob__Group__11__Impl ;
    public final void rule__CompareJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2500:1: ( rule__CompareJob__Group__11__Impl )
            // InternalGrana.g:2501:2: rule__CompareJob__Group__11__Impl
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
    // InternalGrana.g:2507:1: rule__CompareJob__Group__11__Impl : ( ( rule__CompareJob__OutputAssignment_11 ) ) ;
    public final void rule__CompareJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2511:1: ( ( ( rule__CompareJob__OutputAssignment_11 ) ) )
            // InternalGrana.g:2512:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            {
            // InternalGrana.g:2512:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            // InternalGrana.g:2513:1: ( rule__CompareJob__OutputAssignment_11 )
            {
             before(grammarAccess.getCompareJobAccess().getOutputAssignment_11()); 
            // InternalGrana.g:2514:1: ( rule__CompareJob__OutputAssignment_11 )
            // InternalGrana.g:2514:2: rule__CompareJob__OutputAssignment_11
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
    // InternalGrana.g:2548:1: rule__RangeJob__Group__0 : rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 ;
    public final void rule__RangeJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2552:1: ( rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 )
            // InternalGrana.g:2553:2: rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1
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
    // InternalGrana.g:2560:1: rule__RangeJob__Group__0__Impl : ( 'rangejob' ) ;
    public final void rule__RangeJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2564:1: ( ( 'rangejob' ) )
            // InternalGrana.g:2565:1: ( 'rangejob' )
            {
            // InternalGrana.g:2565:1: ( 'rangejob' )
            // InternalGrana.g:2566:1: 'rangejob'
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
    // InternalGrana.g:2579:1: rule__RangeJob__Group__1 : rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 ;
    public final void rule__RangeJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2583:1: ( rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 )
            // InternalGrana.g:2584:2: rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2
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
    // InternalGrana.g:2591:1: rule__RangeJob__Group__1__Impl : ( ( rule__RangeJob__NameAssignment_1 ) ) ;
    public final void rule__RangeJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2595:1: ( ( ( rule__RangeJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2596:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2596:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            // InternalGrana.g:2597:1: ( rule__RangeJob__NameAssignment_1 )
            {
             before(grammarAccess.getRangeJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2598:1: ( rule__RangeJob__NameAssignment_1 )
            // InternalGrana.g:2598:2: rule__RangeJob__NameAssignment_1
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
    // InternalGrana.g:2608:1: rule__RangeJob__Group__2 : rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 ;
    public final void rule__RangeJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2612:1: ( rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 )
            // InternalGrana.g:2613:2: rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3
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
    // InternalGrana.g:2620:1: rule__RangeJob__Group__2__Impl : ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) ;
    public final void rule__RangeJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2624:1: ( ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) )
            // InternalGrana.g:2625:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            {
            // InternalGrana.g:2625:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            // InternalGrana.g:2626:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeAssignment_2()); 
            // InternalGrana.g:2627:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==62) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalGrana.g:2627:2: rule__RangeJob__MeasureExecutionTimeAssignment_2
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
    // InternalGrana.g:2637:1: rule__RangeJob__Group__3 : rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 ;
    public final void rule__RangeJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2641:1: ( rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 )
            // InternalGrana.g:2642:2: rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4
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
    // InternalGrana.g:2649:1: rule__RangeJob__Group__3__Impl : ( 'resources' ) ;
    public final void rule__RangeJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2653:1: ( ( 'resources' ) )
            // InternalGrana.g:2654:1: ( 'resources' )
            {
            // InternalGrana.g:2654:1: ( 'resources' )
            // InternalGrana.g:2655:1: 'resources'
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
    // InternalGrana.g:2668:1: rule__RangeJob__Group__4 : rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 ;
    public final void rule__RangeJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2672:1: ( rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 )
            // InternalGrana.g:2673:2: rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5
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
    // InternalGrana.g:2680:1: rule__RangeJob__Group__4__Impl : ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) ;
    public final void rule__RangeJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2684:1: ( ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) )
            // InternalGrana.g:2685:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            {
            // InternalGrana.g:2685:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            // InternalGrana.g:2686:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            {
            // InternalGrana.g:2686:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) )
            // InternalGrana.g:2687:1: ( rule__RangeJob__ResourcesAssignment_4 )
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2688:1: ( rule__RangeJob__ResourcesAssignment_4 )
            // InternalGrana.g:2688:2: rule__RangeJob__ResourcesAssignment_4
            {
            pushFollow(FOLLOW_13);
            rule__RangeJob__ResourcesAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 

            }

            // InternalGrana.g:2691:1: ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            // InternalGrana.g:2692:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2693:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_STRING||LA32_0==36) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalGrana.g:2693:2: rule__RangeJob__ResourcesAssignment_4
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__RangeJob__ResourcesAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
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
    // InternalGrana.g:2704:1: rule__RangeJob__Group__5 : rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 ;
    public final void rule__RangeJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2708:1: ( rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 )
            // InternalGrana.g:2709:2: rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6
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
    // InternalGrana.g:2716:1: rule__RangeJob__Group__5__Impl : ( 'layoutoptions' ) ;
    public final void rule__RangeJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2720:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2721:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2721:1: ( 'layoutoptions' )
            // InternalGrana.g:2722:1: 'layoutoptions'
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
    // InternalGrana.g:2735:1: rule__RangeJob__Group__6 : rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 ;
    public final void rule__RangeJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2739:1: ( rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 )
            // InternalGrana.g:2740:2: rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7
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
    // InternalGrana.g:2747:1: rule__RangeJob__Group__6__Impl : ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) ;
    public final void rule__RangeJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2751:1: ( ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) )
            // InternalGrana.g:2752:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            {
            // InternalGrana.g:2752:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            // InternalGrana.g:2753:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            {
            // InternalGrana.g:2753:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2754:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2755:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2755:2: rule__RangeJob__LayoutOptionsAssignment_6
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__LayoutOptionsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 

            }

            // InternalGrana.g:2758:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            // InternalGrana.g:2759:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2760:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalGrana.g:2760:2: rule__RangeJob__LayoutOptionsAssignment_6
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__LayoutOptionsAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
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
    // InternalGrana.g:2771:1: rule__RangeJob__Group__7 : rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 ;
    public final void rule__RangeJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2775:1: ( rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 )
            // InternalGrana.g:2776:2: rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8
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
    // InternalGrana.g:2783:1: rule__RangeJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__RangeJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2787:1: ( ( 'analyses' ) )
            // InternalGrana.g:2788:1: ( 'analyses' )
            {
            // InternalGrana.g:2788:1: ( 'analyses' )
            // InternalGrana.g:2789:1: 'analyses'
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
    // InternalGrana.g:2802:1: rule__RangeJob__Group__8 : rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 ;
    public final void rule__RangeJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2806:1: ( rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 )
            // InternalGrana.g:2807:2: rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9
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
    // InternalGrana.g:2814:1: rule__RangeJob__Group__8__Impl : ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__RangeJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2818:1: ( ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2819:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2819:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2820:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2820:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2821:1: ( rule__RangeJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2822:1: ( rule__RangeJob__AnalysesAssignment_8 )
            // InternalGrana.g:2822:2: rule__RangeJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2825:1: ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2826:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2827:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalGrana.g:2827:2: rule__RangeJob__AnalysesAssignment_8
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__AnalysesAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
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
    // InternalGrana.g:2838:1: rule__RangeJob__Group__9 : rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 ;
    public final void rule__RangeJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2842:1: ( rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 )
            // InternalGrana.g:2843:2: rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10
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
    // InternalGrana.g:2850:1: rule__RangeJob__Group__9__Impl : ( 'rangeoption' ) ;
    public final void rule__RangeJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2854:1: ( ( 'rangeoption' ) )
            // InternalGrana.g:2855:1: ( 'rangeoption' )
            {
            // InternalGrana.g:2855:1: ( 'rangeoption' )
            // InternalGrana.g:2856:1: 'rangeoption'
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
    // InternalGrana.g:2869:1: rule__RangeJob__Group__10 : rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 ;
    public final void rule__RangeJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2873:1: ( rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 )
            // InternalGrana.g:2874:2: rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11
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
    // InternalGrana.g:2881:1: rule__RangeJob__Group__10__Impl : ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) ;
    public final void rule__RangeJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2885:1: ( ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) )
            // InternalGrana.g:2886:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            {
            // InternalGrana.g:2886:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            // InternalGrana.g:2887:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_10()); 
            // InternalGrana.g:2888:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            // InternalGrana.g:2888:2: rule__RangeJob__RangeOptionAssignment_10
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
    // InternalGrana.g:2898:1: rule__RangeJob__Group__11 : rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 ;
    public final void rule__RangeJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2902:1: ( rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 )
            // InternalGrana.g:2903:2: rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12
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
    // InternalGrana.g:2910:1: rule__RangeJob__Group__11__Impl : ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) ;
    public final void rule__RangeJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2914:1: ( ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) )
            // InternalGrana.g:2915:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            {
            // InternalGrana.g:2915:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            // InternalGrana.g:2916:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_11()); 
            // InternalGrana.g:2917:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            // InternalGrana.g:2917:2: rule__RangeJob__RangeValuesAssignment_11
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
    // InternalGrana.g:2927:1: rule__RangeJob__Group__12 : rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 ;
    public final void rule__RangeJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2931:1: ( rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 )
            // InternalGrana.g:2932:2: rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13
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
    // InternalGrana.g:2939:1: rule__RangeJob__Group__12__Impl : ( ( rule__RangeJob__Alternatives_12 ) ) ;
    public final void rule__RangeJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2943:1: ( ( ( rule__RangeJob__Alternatives_12 ) ) )
            // InternalGrana.g:2944:1: ( ( rule__RangeJob__Alternatives_12 ) )
            {
            // InternalGrana.g:2944:1: ( ( rule__RangeJob__Alternatives_12 ) )
            // InternalGrana.g:2945:1: ( rule__RangeJob__Alternatives_12 )
            {
             before(grammarAccess.getRangeJobAccess().getAlternatives_12()); 
            // InternalGrana.g:2946:1: ( rule__RangeJob__Alternatives_12 )
            // InternalGrana.g:2946:2: rule__RangeJob__Alternatives_12
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
    // InternalGrana.g:2956:1: rule__RangeJob__Group__13 : rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 ;
    public final void rule__RangeJob__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2960:1: ( rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 )
            // InternalGrana.g:2961:2: rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14
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
    // InternalGrana.g:2968:1: rule__RangeJob__Group__13__Impl : ( 'output' ) ;
    public final void rule__RangeJob__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2972:1: ( ( 'output' ) )
            // InternalGrana.g:2973:1: ( 'output' )
            {
            // InternalGrana.g:2973:1: ( 'output' )
            // InternalGrana.g:2974:1: 'output'
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
    // InternalGrana.g:2987:1: rule__RangeJob__Group__14 : rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 ;
    public final void rule__RangeJob__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2991:1: ( rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 )
            // InternalGrana.g:2992:2: rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15
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
    // InternalGrana.g:2999:1: rule__RangeJob__Group__14__Impl : ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) ;
    public final void rule__RangeJob__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3003:1: ( ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) )
            // InternalGrana.g:3004:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            {
            // InternalGrana.g:3004:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            // InternalGrana.g:3005:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            {
             before(grammarAccess.getRangeJobAccess().getOutputTypeAssignment_14()); 
            // InternalGrana.g:3006:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=15 && LA35_0<=16)) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalGrana.g:3006:2: rule__RangeJob__OutputTypeAssignment_14
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
    // InternalGrana.g:3016:1: rule__RangeJob__Group__15 : rule__RangeJob__Group__15__Impl ;
    public final void rule__RangeJob__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3020:1: ( rule__RangeJob__Group__15__Impl )
            // InternalGrana.g:3021:2: rule__RangeJob__Group__15__Impl
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
    // InternalGrana.g:3027:1: rule__RangeJob__Group__15__Impl : ( ( rule__RangeJob__OutputAssignment_15 ) ) ;
    public final void rule__RangeJob__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3031:1: ( ( ( rule__RangeJob__OutputAssignment_15 ) ) )
            // InternalGrana.g:3032:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            {
            // InternalGrana.g:3032:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            // InternalGrana.g:3033:1: ( rule__RangeJob__OutputAssignment_15 )
            {
             before(grammarAccess.getRangeJobAccess().getOutputAssignment_15()); 
            // InternalGrana.g:3034:1: ( rule__RangeJob__OutputAssignment_15 )
            // InternalGrana.g:3034:2: rule__RangeJob__OutputAssignment_15
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
    // InternalGrana.g:3076:1: rule__RangeJob__Group_12_0__0 : rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 ;
    public final void rule__RangeJob__Group_12_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3080:1: ( rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 )
            // InternalGrana.g:3081:2: rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1
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
    // InternalGrana.g:3088:1: rule__RangeJob__Group_12_0__0__Impl : ( 'rangeanalysis' ) ;
    public final void rule__RangeJob__Group_12_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3092:1: ( ( 'rangeanalysis' ) )
            // InternalGrana.g:3093:1: ( 'rangeanalysis' )
            {
            // InternalGrana.g:3093:1: ( 'rangeanalysis' )
            // InternalGrana.g:3094:1: 'rangeanalysis'
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
    // InternalGrana.g:3107:1: rule__RangeJob__Group_12_0__1 : rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 ;
    public final void rule__RangeJob__Group_12_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3111:1: ( rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 )
            // InternalGrana.g:3112:2: rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2
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
    // InternalGrana.g:3119:1: rule__RangeJob__Group_12_0__1__Impl : ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) ;
    public final void rule__RangeJob__Group_12_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3123:1: ( ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) )
            // InternalGrana.g:3124:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            {
            // InternalGrana.g:3124:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            // InternalGrana.g:3125:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12_0_1()); 
            // InternalGrana.g:3126:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            // InternalGrana.g:3126:2: rule__RangeJob__RangeAnalysisAssignment_12_0_1
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
    // InternalGrana.g:3136:1: rule__RangeJob__Group_12_0__2 : rule__RangeJob__Group_12_0__2__Impl ;
    public final void rule__RangeJob__Group_12_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3140:1: ( rule__RangeJob__Group_12_0__2__Impl )
            // InternalGrana.g:3141:2: rule__RangeJob__Group_12_0__2__Impl
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
    // InternalGrana.g:3147:1: rule__RangeJob__Group_12_0__2__Impl : ( ( rule__RangeJob__Group_12_0_2__0 )? ) ;
    public final void rule__RangeJob__Group_12_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3151:1: ( ( ( rule__RangeJob__Group_12_0_2__0 )? ) )
            // InternalGrana.g:3152:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            {
            // InternalGrana.g:3152:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            // InternalGrana.g:3153:1: ( rule__RangeJob__Group_12_0_2__0 )?
            {
             before(grammarAccess.getRangeJobAccess().getGroup_12_0_2()); 
            // InternalGrana.g:3154:1: ( rule__RangeJob__Group_12_0_2__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==29) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalGrana.g:3154:2: rule__RangeJob__Group_12_0_2__0
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
    // InternalGrana.g:3170:1: rule__RangeJob__Group_12_0_2__0 : rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 ;
    public final void rule__RangeJob__Group_12_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3174:1: ( rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 )
            // InternalGrana.g:3175:2: rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1
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
    // InternalGrana.g:3182:1: rule__RangeJob__Group_12_0_2__0__Impl : ( 'component' ) ;
    public final void rule__RangeJob__Group_12_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3186:1: ( ( 'component' ) )
            // InternalGrana.g:3187:1: ( 'component' )
            {
            // InternalGrana.g:3187:1: ( 'component' )
            // InternalGrana.g:3188:1: 'component'
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
    // InternalGrana.g:3201:1: rule__RangeJob__Group_12_0_2__1 : rule__RangeJob__Group_12_0_2__1__Impl ;
    public final void rule__RangeJob__Group_12_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3205:1: ( rule__RangeJob__Group_12_0_2__1__Impl )
            // InternalGrana.g:3206:2: rule__RangeJob__Group_12_0_2__1__Impl
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
    // InternalGrana.g:3212:1: rule__RangeJob__Group_12_0_2__1__Impl : ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) ;
    public final void rule__RangeJob__Group_12_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3216:1: ( ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) )
            // InternalGrana.g:3217:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            {
            // InternalGrana.g:3217:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            // InternalGrana.g:3218:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_12_0_2_1()); 
            // InternalGrana.g:3219:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            // InternalGrana.g:3219:2: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1
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
    // InternalGrana.g:3233:1: rule__RangeJob__Group_12_1__0 : rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 ;
    public final void rule__RangeJob__Group_12_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3237:1: ( rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 )
            // InternalGrana.g:3238:2: rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1
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
    // InternalGrana.g:3245:1: rule__RangeJob__Group_12_1__0__Impl : ( 'rangeanalyses' ) ;
    public final void rule__RangeJob__Group_12_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3249:1: ( ( 'rangeanalyses' ) )
            // InternalGrana.g:3250:1: ( 'rangeanalyses' )
            {
            // InternalGrana.g:3250:1: ( 'rangeanalyses' )
            // InternalGrana.g:3251:1: 'rangeanalyses'
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
    // InternalGrana.g:3264:1: rule__RangeJob__Group_12_1__1 : rule__RangeJob__Group_12_1__1__Impl ;
    public final void rule__RangeJob__Group_12_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3268:1: ( rule__RangeJob__Group_12_1__1__Impl )
            // InternalGrana.g:3269:2: rule__RangeJob__Group_12_1__1__Impl
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
    // InternalGrana.g:3275:1: rule__RangeJob__Group_12_1__1__Impl : ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) ;
    public final void rule__RangeJob__Group_12_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3279:1: ( ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) )
            // InternalGrana.g:3280:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            {
            // InternalGrana.g:3280:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            // InternalGrana.g:3281:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            {
            // InternalGrana.g:3281:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) )
            // InternalGrana.g:3282:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3283:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            // InternalGrana.g:3283:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 

            }

            // InternalGrana.g:3286:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            // InternalGrana.g:3287:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3288:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==RULE_ID) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalGrana.g:3288:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop37;
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
    // InternalGrana.g:3303:1: rule__FloatRange__Group__0 : rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 ;
    public final void rule__FloatRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3307:1: ( rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 )
            // InternalGrana.g:3308:2: rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1
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
    // InternalGrana.g:3315:1: rule__FloatRange__Group__0__Impl : ( 'floatvalues' ) ;
    public final void rule__FloatRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3319:1: ( ( 'floatvalues' ) )
            // InternalGrana.g:3320:1: ( 'floatvalues' )
            {
            // InternalGrana.g:3320:1: ( 'floatvalues' )
            // InternalGrana.g:3321:1: 'floatvalues'
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
    // InternalGrana.g:3334:1: rule__FloatRange__Group__1 : rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 ;
    public final void rule__FloatRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3338:1: ( rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 )
            // InternalGrana.g:3339:2: rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2
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
    // InternalGrana.g:3346:1: rule__FloatRange__Group__1__Impl : ( ( rule__FloatRange__ValuesAssignment_1 ) ) ;
    public final void rule__FloatRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3350:1: ( ( ( rule__FloatRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3351:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3351:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            // InternalGrana.g:3352:1: ( rule__FloatRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3353:1: ( rule__FloatRange__ValuesAssignment_1 )
            // InternalGrana.g:3353:2: rule__FloatRange__ValuesAssignment_1
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
    // InternalGrana.g:3363:1: rule__FloatRange__Group__2 : rule__FloatRange__Group__2__Impl ;
    public final void rule__FloatRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3367:1: ( rule__FloatRange__Group__2__Impl )
            // InternalGrana.g:3368:2: rule__FloatRange__Group__2__Impl
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
    // InternalGrana.g:3374:1: rule__FloatRange__Group__2__Impl : ( ( rule__FloatRange__Group_2__0 )* ) ;
    public final void rule__FloatRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3378:1: ( ( ( rule__FloatRange__Group_2__0 )* ) )
            // InternalGrana.g:3379:1: ( ( rule__FloatRange__Group_2__0 )* )
            {
            // InternalGrana.g:3379:1: ( ( rule__FloatRange__Group_2__0 )* )
            // InternalGrana.g:3380:1: ( rule__FloatRange__Group_2__0 )*
            {
             before(grammarAccess.getFloatRangeAccess().getGroup_2()); 
            // InternalGrana.g:3381:1: ( rule__FloatRange__Group_2__0 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==32) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalGrana.g:3381:2: rule__FloatRange__Group_2__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__FloatRange__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
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
    // InternalGrana.g:3397:1: rule__FloatRange__Group_2__0 : rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 ;
    public final void rule__FloatRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3401:1: ( rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 )
            // InternalGrana.g:3402:2: rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1
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
    // InternalGrana.g:3409:1: rule__FloatRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__FloatRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3413:1: ( ( ',' ) )
            // InternalGrana.g:3414:1: ( ',' )
            {
            // InternalGrana.g:3414:1: ( ',' )
            // InternalGrana.g:3415:1: ','
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
    // InternalGrana.g:3428:1: rule__FloatRange__Group_2__1 : rule__FloatRange__Group_2__1__Impl ;
    public final void rule__FloatRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3432:1: ( rule__FloatRange__Group_2__1__Impl )
            // InternalGrana.g:3433:2: rule__FloatRange__Group_2__1__Impl
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
    // InternalGrana.g:3439:1: rule__FloatRange__Group_2__1__Impl : ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__FloatRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3443:1: ( ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3444:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3444:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3445:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3446:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            // InternalGrana.g:3446:2: rule__FloatRange__ValuesAssignment_2_1
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
    // InternalGrana.g:3460:1: rule__IntRangeValues__Group__0 : rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 ;
    public final void rule__IntRangeValues__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3464:1: ( rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 )
            // InternalGrana.g:3465:2: rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1
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
    // InternalGrana.g:3472:1: rule__IntRangeValues__Group__0__Impl : ( 'intvalues' ) ;
    public final void rule__IntRangeValues__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3476:1: ( ( 'intvalues' ) )
            // InternalGrana.g:3477:1: ( 'intvalues' )
            {
            // InternalGrana.g:3477:1: ( 'intvalues' )
            // InternalGrana.g:3478:1: 'intvalues'
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
    // InternalGrana.g:3491:1: rule__IntRangeValues__Group__1 : rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 ;
    public final void rule__IntRangeValues__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3495:1: ( rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 )
            // InternalGrana.g:3496:2: rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2
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
    // InternalGrana.g:3503:1: rule__IntRangeValues__Group__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) ;
    public final void rule__IntRangeValues__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3507:1: ( ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3508:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3508:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            // InternalGrana.g:3509:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3510:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            // InternalGrana.g:3510:2: rule__IntRangeValues__ValuesAssignment_1
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
    // InternalGrana.g:3520:1: rule__IntRangeValues__Group__2 : rule__IntRangeValues__Group__2__Impl ;
    public final void rule__IntRangeValues__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3524:1: ( rule__IntRangeValues__Group__2__Impl )
            // InternalGrana.g:3525:2: rule__IntRangeValues__Group__2__Impl
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
    // InternalGrana.g:3531:1: rule__IntRangeValues__Group__2__Impl : ( ( rule__IntRangeValues__Group_2__0 )* ) ;
    public final void rule__IntRangeValues__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3535:1: ( ( ( rule__IntRangeValues__Group_2__0 )* ) )
            // InternalGrana.g:3536:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            {
            // InternalGrana.g:3536:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            // InternalGrana.g:3537:1: ( rule__IntRangeValues__Group_2__0 )*
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup_2()); 
            // InternalGrana.g:3538:1: ( rule__IntRangeValues__Group_2__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==32) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalGrana.g:3538:2: rule__IntRangeValues__Group_2__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__IntRangeValues__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
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
    // InternalGrana.g:3554:1: rule__IntRangeValues__Group_2__0 : rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 ;
    public final void rule__IntRangeValues__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3558:1: ( rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 )
            // InternalGrana.g:3559:2: rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1
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
    // InternalGrana.g:3566:1: rule__IntRangeValues__Group_2__0__Impl : ( ',' ) ;
    public final void rule__IntRangeValues__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3570:1: ( ( ',' ) )
            // InternalGrana.g:3571:1: ( ',' )
            {
            // InternalGrana.g:3571:1: ( ',' )
            // InternalGrana.g:3572:1: ','
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
    // InternalGrana.g:3585:1: rule__IntRangeValues__Group_2__1 : rule__IntRangeValues__Group_2__1__Impl ;
    public final void rule__IntRangeValues__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3589:1: ( rule__IntRangeValues__Group_2__1__Impl )
            // InternalGrana.g:3590:2: rule__IntRangeValues__Group_2__1__Impl
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
    // InternalGrana.g:3596:1: rule__IntRangeValues__Group_2__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) ;
    public final void rule__IntRangeValues__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3600:1: ( ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3601:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3601:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3602:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3603:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            // InternalGrana.g:3603:2: rule__IntRangeValues__ValuesAssignment_2_1
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
    // InternalGrana.g:3617:1: rule__IntRangeRange__Group__0 : rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 ;
    public final void rule__IntRangeRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3621:1: ( rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 )
            // InternalGrana.g:3622:2: rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1
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
    // InternalGrana.g:3629:1: rule__IntRangeRange__Group__0__Impl : ( 'intrange' ) ;
    public final void rule__IntRangeRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3633:1: ( ( 'intrange' ) )
            // InternalGrana.g:3634:1: ( 'intrange' )
            {
            // InternalGrana.g:3634:1: ( 'intrange' )
            // InternalGrana.g:3635:1: 'intrange'
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
    // InternalGrana.g:3648:1: rule__IntRangeRange__Group__1 : rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 ;
    public final void rule__IntRangeRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3652:1: ( rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 )
            // InternalGrana.g:3653:2: rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2
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
    // InternalGrana.g:3660:1: rule__IntRangeRange__Group__1__Impl : ( ( rule__IntRangeRange__StartAssignment_1 ) ) ;
    public final void rule__IntRangeRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3664:1: ( ( ( rule__IntRangeRange__StartAssignment_1 ) ) )
            // InternalGrana.g:3665:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            {
            // InternalGrana.g:3665:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            // InternalGrana.g:3666:1: ( rule__IntRangeRange__StartAssignment_1 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1()); 
            // InternalGrana.g:3667:1: ( rule__IntRangeRange__StartAssignment_1 )
            // InternalGrana.g:3667:2: rule__IntRangeRange__StartAssignment_1
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
    // InternalGrana.g:3677:1: rule__IntRangeRange__Group__2 : rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 ;
    public final void rule__IntRangeRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3681:1: ( rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 )
            // InternalGrana.g:3682:2: rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3
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
    // InternalGrana.g:3689:1: rule__IntRangeRange__Group__2__Impl : ( 'to' ) ;
    public final void rule__IntRangeRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3693:1: ( ( 'to' ) )
            // InternalGrana.g:3694:1: ( 'to' )
            {
            // InternalGrana.g:3694:1: ( 'to' )
            // InternalGrana.g:3695:1: 'to'
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
    // InternalGrana.g:3708:1: rule__IntRangeRange__Group__3 : rule__IntRangeRange__Group__3__Impl ;
    public final void rule__IntRangeRange__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3712:1: ( rule__IntRangeRange__Group__3__Impl )
            // InternalGrana.g:3713:2: rule__IntRangeRange__Group__3__Impl
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
    // InternalGrana.g:3719:1: rule__IntRangeRange__Group__3__Impl : ( ( rule__IntRangeRange__EndAssignment_3 ) ) ;
    public final void rule__IntRangeRange__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3723:1: ( ( ( rule__IntRangeRange__EndAssignment_3 ) ) )
            // InternalGrana.g:3724:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            {
            // InternalGrana.g:3724:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            // InternalGrana.g:3725:1: ( rule__IntRangeRange__EndAssignment_3 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3()); 
            // InternalGrana.g:3726:1: ( rule__IntRangeRange__EndAssignment_3 )
            // InternalGrana.g:3726:2: rule__IntRangeRange__EndAssignment_3
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


    // $ANTLR start "rule__ResourceReference__Group__0"
    // InternalGrana.g:3744:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3748:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // InternalGrana.g:3749:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
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
    // InternalGrana.g:3756:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3760:1: ( ( 'ref' ) )
            // InternalGrana.g:3761:1: ( 'ref' )
            {
            // InternalGrana.g:3761:1: ( 'ref' )
            // InternalGrana.g:3762:1: 'ref'
            {
             before(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 
            match(input,36,FOLLOW_2); 
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
    // InternalGrana.g:3775:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3779:1: ( rule__ResourceReference__Group__1__Impl )
            // InternalGrana.g:3780:2: rule__ResourceReference__Group__1__Impl
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
    // InternalGrana.g:3786:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3790:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // InternalGrana.g:3791:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // InternalGrana.g:3791:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // InternalGrana.g:3792:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // InternalGrana.g:3792:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // InternalGrana.g:3793:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:3794:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // InternalGrana.g:3794:2: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_3);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // InternalGrana.g:3797:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // InternalGrana.g:3798:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:3799:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_ID) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalGrana.g:3799:2: rule__ResourceReference__ResourceRefsAssignment_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ResourceReference__ResourceRefsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop40;
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
    // InternalGrana.g:3814:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3818:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // InternalGrana.g:3819:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
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
    // InternalGrana.g:3826:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3830:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // InternalGrana.g:3831:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:3831:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // InternalGrana.g:3832:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:3833:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            // InternalGrana.g:3833:2: rule__GlobalResourceRef__NameAssignment_0
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
    // InternalGrana.g:3843:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3847:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // InternalGrana.g:3848:2: rule__GlobalResourceRef__Group__1__Impl
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
    // InternalGrana.g:3854:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3858:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // InternalGrana.g:3859:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // InternalGrana.g:3859:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // InternalGrana.g:3860:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // InternalGrana.g:3861:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // InternalGrana.g:3861:2: rule__GlobalResourceRef__ResourcesAssignment_1
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
    // InternalGrana.g:3875:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3879:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // InternalGrana.g:3880:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
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
    // InternalGrana.g:3887:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3891:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // InternalGrana.g:3892:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // InternalGrana.g:3892:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // InternalGrana.g:3893:1: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // InternalGrana.g:3894:1: ( rule__LocalResource__PathAssignment_0 )
            // InternalGrana.g:3894:2: rule__LocalResource__PathAssignment_0
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
    // InternalGrana.g:3904:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3908:1: ( rule__LocalResource__Group__1__Impl )
            // InternalGrana.g:3909:2: rule__LocalResource__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3915:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3919:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // InternalGrana.g:3920:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // InternalGrana.g:3920:1: ( ( rule__LocalResource__Group_1__0 ) )
            // InternalGrana.g:3921:1: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // InternalGrana.g:3922:1: ( rule__LocalResource__Group_1__0 )
            // InternalGrana.g:3922:2: rule__LocalResource__Group_1__0
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


    // $ANTLR start "rule__LocalResource__Group_1__0"
    // InternalGrana.g:3936:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3940:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // InternalGrana.g:3941:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
            {
            pushFollow(FOLLOW_29);
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
    // InternalGrana.g:3948:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3952:1: ( ( 'filter' ) )
            // InternalGrana.g:3953:1: ( 'filter' )
            {
            // InternalGrana.g:3953:1: ( 'filter' )
            // InternalGrana.g:3954:1: 'filter'
            {
             before(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 
            match(input,37,FOLLOW_2); 
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
    // InternalGrana.g:3967:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3971:1: ( rule__LocalResource__Group_1__1__Impl )
            // InternalGrana.g:3972:2: rule__LocalResource__Group_1__1__Impl
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
    // InternalGrana.g:3978:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3982:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // InternalGrana.g:3983:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // InternalGrana.g:3983:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // InternalGrana.g:3984:1: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // InternalGrana.g:3985:1: ( rule__LocalResource__FilterAssignment_1_1 )
            // InternalGrana.g:3985:2: rule__LocalResource__FilterAssignment_1_1
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
    // InternalGrana.g:3999:1: rule__GlobalOutputRef__Group__0 : rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 ;
    public final void rule__GlobalOutputRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4003:1: ( rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 )
            // InternalGrana.g:4004:2: rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1
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
    // InternalGrana.g:4011:1: rule__GlobalOutputRef__Group__0__Impl : ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalOutputRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4015:1: ( ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) )
            // InternalGrana.g:4016:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:4016:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            // InternalGrana.g:4017:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:4018:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            // InternalGrana.g:4018:2: rule__GlobalOutputRef__NameAssignment_0
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
    // InternalGrana.g:4028:1: rule__GlobalOutputRef__Group__1 : rule__GlobalOutputRef__Group__1__Impl ;
    public final void rule__GlobalOutputRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4032:1: ( rule__GlobalOutputRef__Group__1__Impl )
            // InternalGrana.g:4033:2: rule__GlobalOutputRef__Group__1__Impl
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
    // InternalGrana.g:4039:1: rule__GlobalOutputRef__Group__1__Impl : ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) ;
    public final void rule__GlobalOutputRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4043:1: ( ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) )
            // InternalGrana.g:4044:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            {
            // InternalGrana.g:4044:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            // InternalGrana.g:4045:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 
            // InternalGrana.g:4046:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            // InternalGrana.g:4046:2: rule__GlobalOutputRef__OutputAssignment_1
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
    // InternalGrana.g:4060:1: rule__OutputReference__Group__0 : rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 ;
    public final void rule__OutputReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4064:1: ( rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 )
            // InternalGrana.g:4065:2: rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1
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
    // InternalGrana.g:4072:1: rule__OutputReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__OutputReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4076:1: ( ( 'ref' ) )
            // InternalGrana.g:4077:1: ( 'ref' )
            {
            // InternalGrana.g:4077:1: ( 'ref' )
            // InternalGrana.g:4078:1: 'ref'
            {
             before(grammarAccess.getOutputReferenceAccess().getRefKeyword_0()); 
            match(input,36,FOLLOW_2); 
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
    // InternalGrana.g:4091:1: rule__OutputReference__Group__1 : rule__OutputReference__Group__1__Impl ;
    public final void rule__OutputReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4095:1: ( rule__OutputReference__Group__1__Impl )
            // InternalGrana.g:4096:2: rule__OutputReference__Group__1__Impl
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
    // InternalGrana.g:4102:1: rule__OutputReference__Group__1__Impl : ( ( rule__OutputReference__OutputRefAssignment_1 ) ) ;
    public final void rule__OutputReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4106:1: ( ( ( rule__OutputReference__OutputRefAssignment_1 ) ) )
            // InternalGrana.g:4107:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            {
            // InternalGrana.g:4107:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            // InternalGrana.g:4108:1: ( rule__OutputReference__OutputRefAssignment_1 )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 
            // InternalGrana.g:4109:1: ( rule__OutputReference__OutputRefAssignment_1 )
            // InternalGrana.g:4109:2: rule__OutputReference__OutputRefAssignment_1
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
    // InternalGrana.g:4123:1: rule__LayoutConfig__Group__0 : rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 ;
    public final void rule__LayoutConfig__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4127:1: ( rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 )
            // InternalGrana.g:4128:2: rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1
            {
            pushFollow(FOLLOW_30);
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
    // InternalGrana.g:4135:1: rule__LayoutConfig__Group__0__Impl : ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) ;
    public final void rule__LayoutConfig__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4139:1: ( ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) )
            // InternalGrana.g:4140:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            {
            // InternalGrana.g:4140:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            // InternalGrana.g:4141:1: ( rule__LayoutConfig__IdentifierAssignment_0 )
            {
             before(grammarAccess.getLayoutConfigAccess().getIdentifierAssignment_0()); 
            // InternalGrana.g:4142:1: ( rule__LayoutConfig__IdentifierAssignment_0 )
            // InternalGrana.g:4142:2: rule__LayoutConfig__IdentifierAssignment_0
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
    // InternalGrana.g:4152:1: rule__LayoutConfig__Group__1 : rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 ;
    public final void rule__LayoutConfig__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4156:1: ( rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 )
            // InternalGrana.g:4157:2: rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2
            {
            pushFollow(FOLLOW_31);
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
    // InternalGrana.g:4164:1: rule__LayoutConfig__Group__1__Impl : ( '{' ) ;
    public final void rule__LayoutConfig__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4168:1: ( ( '{' ) )
            // InternalGrana.g:4169:1: ( '{' )
            {
            // InternalGrana.g:4169:1: ( '{' )
            // InternalGrana.g:4170:1: '{'
            {
             before(grammarAccess.getLayoutConfigAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4183:1: rule__LayoutConfig__Group__2 : rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 ;
    public final void rule__LayoutConfig__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4187:1: ( rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 )
            // InternalGrana.g:4188:2: rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3
            {
            pushFollow(FOLLOW_31);
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
    // InternalGrana.g:4195:1: rule__LayoutConfig__Group__2__Impl : ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) ;
    public final void rule__LayoutConfig__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4199:1: ( ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) )
            // InternalGrana.g:4200:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            {
            // InternalGrana.g:4200:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            // InternalGrana.g:4201:1: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getLayoutConfigAccess().getPropertiesAssignment_2()); 
            // InternalGrana.g:4202:1: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==RULE_ID) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalGrana.g:4202:2: rule__LayoutConfig__PropertiesAssignment_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__LayoutConfig__PropertiesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop41;
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
    // InternalGrana.g:4212:1: rule__LayoutConfig__Group__3 : rule__LayoutConfig__Group__3__Impl ;
    public final void rule__LayoutConfig__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4216:1: ( rule__LayoutConfig__Group__3__Impl )
            // InternalGrana.g:4217:2: rule__LayoutConfig__Group__3__Impl
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
    // InternalGrana.g:4223:1: rule__LayoutConfig__Group__3__Impl : ( '}' ) ;
    public final void rule__LayoutConfig__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4227:1: ( ( '}' ) )
            // InternalGrana.g:4228:1: ( '}' )
            {
            // InternalGrana.g:4228:1: ( '}' )
            // InternalGrana.g:4229:1: '}'
            {
             before(grammarAccess.getLayoutConfigAccess().getRightCurlyBracketKeyword_3()); 
            match(input,39,FOLLOW_2); 
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
    // InternalGrana.g:4252:1: rule__ElkNode__Group__0 : rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 ;
    public final void rule__ElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4256:1: ( rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 )
            // InternalGrana.g:4257:2: rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1
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
    // InternalGrana.g:4264:1: rule__ElkNode__Group__0__Impl : ( 'node' ) ;
    public final void rule__ElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4268:1: ( ( 'node' ) )
            // InternalGrana.g:4269:1: ( 'node' )
            {
            // InternalGrana.g:4269:1: ( 'node' )
            // InternalGrana.g:4270:1: 'node'
            {
             before(grammarAccess.getElkNodeAccess().getNodeKeyword_0()); 
            match(input,40,FOLLOW_2); 
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
    // InternalGrana.g:4283:1: rule__ElkNode__Group__1 : rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 ;
    public final void rule__ElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4287:1: ( rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 )
            // InternalGrana.g:4288:2: rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2
            {
            pushFollow(FOLLOW_30);
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
    // InternalGrana.g:4295:1: rule__ElkNode__Group__1__Impl : ( ( rule__ElkNode__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4299:1: ( ( ( rule__ElkNode__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:4300:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:4300:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            // InternalGrana.g:4301:1: ( rule__ElkNode__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:4302:1: ( rule__ElkNode__IdentifierAssignment_1 )
            // InternalGrana.g:4302:2: rule__ElkNode__IdentifierAssignment_1
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
    // InternalGrana.g:4312:1: rule__ElkNode__Group__2 : rule__ElkNode__Group__2__Impl ;
    public final void rule__ElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4316:1: ( rule__ElkNode__Group__2__Impl )
            // InternalGrana.g:4317:2: rule__ElkNode__Group__2__Impl
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
    // InternalGrana.g:4323:1: rule__ElkNode__Group__2__Impl : ( ( rule__ElkNode__Group_2__0 )? ) ;
    public final void rule__ElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4327:1: ( ( ( rule__ElkNode__Group_2__0 )? ) )
            // InternalGrana.g:4328:1: ( ( rule__ElkNode__Group_2__0 )? )
            {
            // InternalGrana.g:4328:1: ( ( rule__ElkNode__Group_2__0 )? )
            // InternalGrana.g:4329:1: ( rule__ElkNode__Group_2__0 )?
            {
             before(grammarAccess.getElkNodeAccess().getGroup_2()); 
            // InternalGrana.g:4330:1: ( rule__ElkNode__Group_2__0 )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==38) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalGrana.g:4330:2: rule__ElkNode__Group_2__0
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
    // InternalGrana.g:4346:1: rule__ElkNode__Group_2__0 : rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 ;
    public final void rule__ElkNode__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4350:1: ( rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 )
            // InternalGrana.g:4351:2: rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4358:1: rule__ElkNode__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkNode__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4362:1: ( ( '{' ) )
            // InternalGrana.g:4363:1: ( '{' )
            {
            // InternalGrana.g:4363:1: ( '{' )
            // InternalGrana.g:4364:1: '{'
            {
             before(grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_2_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4377:1: rule__ElkNode__Group_2__1 : rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 ;
    public final void rule__ElkNode__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4381:1: ( rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 )
            // InternalGrana.g:4382:2: rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4389:1: rule__ElkNode__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkNode__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4393:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4394:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4394:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4395:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkNodeAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:4396:1: ( ruleShapeLayout )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==44) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalGrana.g:4396:3: ruleShapeLayout
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
    // InternalGrana.g:4406:1: rule__ElkNode__Group_2__2 : rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 ;
    public final void rule__ElkNode__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4410:1: ( rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 )
            // InternalGrana.g:4411:2: rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4418:1: rule__ElkNode__Group_2__2__Impl : ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkNode__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4422:1: ( ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:4423:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:4423:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:4424:1: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:4425:1: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==RULE_ID) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalGrana.g:4425:2: rule__ElkNode__PropertiesAssignment_2_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkNode__PropertiesAssignment_2_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop44;
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
    // InternalGrana.g:4435:1: rule__ElkNode__Group_2__3 : rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 ;
    public final void rule__ElkNode__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4439:1: ( rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 )
            // InternalGrana.g:4440:2: rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4447:1: rule__ElkNode__Group_2__3__Impl : ( ( rule__ElkNode__Alternatives_2_3 )* ) ;
    public final void rule__ElkNode__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4451:1: ( ( ( rule__ElkNode__Alternatives_2_3 )* ) )
            // InternalGrana.g:4452:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            {
            // InternalGrana.g:4452:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            // InternalGrana.g:4453:1: ( rule__ElkNode__Alternatives_2_3 )*
            {
             before(grammarAccess.getElkNodeAccess().getAlternatives_2_3()); 
            // InternalGrana.g:4454:1: ( rule__ElkNode__Alternatives_2_3 )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( ((LA45_0>=40 && LA45_0<=41)||LA45_0==43||LA45_0==49) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalGrana.g:4454:2: rule__ElkNode__Alternatives_2_3
            	    {
            	    pushFollow(FOLLOW_33);
            	    rule__ElkNode__Alternatives_2_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop45;
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
    // InternalGrana.g:4464:1: rule__ElkNode__Group_2__4 : rule__ElkNode__Group_2__4__Impl ;
    public final void rule__ElkNode__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4468:1: ( rule__ElkNode__Group_2__4__Impl )
            // InternalGrana.g:4469:2: rule__ElkNode__Group_2__4__Impl
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
    // InternalGrana.g:4475:1: rule__ElkNode__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkNode__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4479:1: ( ( '}' ) )
            // InternalGrana.g:4480:1: ( '}' )
            {
            // InternalGrana.g:4480:1: ( '}' )
            // InternalGrana.g:4481:1: '}'
            {
             before(grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_2_4()); 
            match(input,39,FOLLOW_2); 
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
    // InternalGrana.g:4504:1: rule__ElkLabel__Group__0 : rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 ;
    public final void rule__ElkLabel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4508:1: ( rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 )
            // InternalGrana.g:4509:2: rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1
            {
            pushFollow(FOLLOW_34);
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
    // InternalGrana.g:4516:1: rule__ElkLabel__Group__0__Impl : ( 'label' ) ;
    public final void rule__ElkLabel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4520:1: ( ( 'label' ) )
            // InternalGrana.g:4521:1: ( 'label' )
            {
            // InternalGrana.g:4521:1: ( 'label' )
            // InternalGrana.g:4522:1: 'label'
            {
             before(grammarAccess.getElkLabelAccess().getLabelKeyword_0()); 
            match(input,41,FOLLOW_2); 
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
    // InternalGrana.g:4535:1: rule__ElkLabel__Group__1 : rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 ;
    public final void rule__ElkLabel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4539:1: ( rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 )
            // InternalGrana.g:4540:2: rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2
            {
            pushFollow(FOLLOW_34);
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
    // InternalGrana.g:4547:1: rule__ElkLabel__Group__1__Impl : ( ( rule__ElkLabel__Group_1__0 )? ) ;
    public final void rule__ElkLabel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4551:1: ( ( ( rule__ElkLabel__Group_1__0 )? ) )
            // InternalGrana.g:4552:1: ( ( rule__ElkLabel__Group_1__0 )? )
            {
            // InternalGrana.g:4552:1: ( ( rule__ElkLabel__Group_1__0 )? )
            // InternalGrana.g:4553:1: ( rule__ElkLabel__Group_1__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_1()); 
            // InternalGrana.g:4554:1: ( rule__ElkLabel__Group_1__0 )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==RULE_ID) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalGrana.g:4554:2: rule__ElkLabel__Group_1__0
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
    // InternalGrana.g:4564:1: rule__ElkLabel__Group__2 : rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 ;
    public final void rule__ElkLabel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4568:1: ( rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 )
            // InternalGrana.g:4569:2: rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3
            {
            pushFollow(FOLLOW_30);
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
    // InternalGrana.g:4576:1: rule__ElkLabel__Group__2__Impl : ( ( rule__ElkLabel__TextAssignment_2 ) ) ;
    public final void rule__ElkLabel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4580:1: ( ( ( rule__ElkLabel__TextAssignment_2 ) ) )
            // InternalGrana.g:4581:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            {
            // InternalGrana.g:4581:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            // InternalGrana.g:4582:1: ( rule__ElkLabel__TextAssignment_2 )
            {
             before(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 
            // InternalGrana.g:4583:1: ( rule__ElkLabel__TextAssignment_2 )
            // InternalGrana.g:4583:2: rule__ElkLabel__TextAssignment_2
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
    // InternalGrana.g:4593:1: rule__ElkLabel__Group__3 : rule__ElkLabel__Group__3__Impl ;
    public final void rule__ElkLabel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4597:1: ( rule__ElkLabel__Group__3__Impl )
            // InternalGrana.g:4598:2: rule__ElkLabel__Group__3__Impl
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
    // InternalGrana.g:4604:1: rule__ElkLabel__Group__3__Impl : ( ( rule__ElkLabel__Group_3__0 )? ) ;
    public final void rule__ElkLabel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4608:1: ( ( ( rule__ElkLabel__Group_3__0 )? ) )
            // InternalGrana.g:4609:1: ( ( rule__ElkLabel__Group_3__0 )? )
            {
            // InternalGrana.g:4609:1: ( ( rule__ElkLabel__Group_3__0 )? )
            // InternalGrana.g:4610:1: ( rule__ElkLabel__Group_3__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_3()); 
            // InternalGrana.g:4611:1: ( rule__ElkLabel__Group_3__0 )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==38) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalGrana.g:4611:2: rule__ElkLabel__Group_3__0
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
    // InternalGrana.g:4629:1: rule__ElkLabel__Group_1__0 : rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 ;
    public final void rule__ElkLabel__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4633:1: ( rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 )
            // InternalGrana.g:4634:2: rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalGrana.g:4641:1: rule__ElkLabel__Group_1__0__Impl : ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkLabel__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4645:1: ( ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:4646:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:4646:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:4647:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:4648:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            // InternalGrana.g:4648:2: rule__ElkLabel__IdentifierAssignment_1_0
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
    // InternalGrana.g:4658:1: rule__ElkLabel__Group_1__1 : rule__ElkLabel__Group_1__1__Impl ;
    public final void rule__ElkLabel__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4662:1: ( rule__ElkLabel__Group_1__1__Impl )
            // InternalGrana.g:4663:2: rule__ElkLabel__Group_1__1__Impl
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
    // InternalGrana.g:4669:1: rule__ElkLabel__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkLabel__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4673:1: ( ( ':' ) )
            // InternalGrana.g:4674:1: ( ':' )
            {
            // InternalGrana.g:4674:1: ( ':' )
            // InternalGrana.g:4675:1: ':'
            {
             before(grammarAccess.getElkLabelAccess().getColonKeyword_1_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:4692:1: rule__ElkLabel__Group_3__0 : rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 ;
    public final void rule__ElkLabel__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4696:1: ( rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 )
            // InternalGrana.g:4697:2: rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4704:1: rule__ElkLabel__Group_3__0__Impl : ( '{' ) ;
    public final void rule__ElkLabel__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4708:1: ( ( '{' ) )
            // InternalGrana.g:4709:1: ( '{' )
            {
            // InternalGrana.g:4709:1: ( '{' )
            // InternalGrana.g:4710:1: '{'
            {
             before(grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4723:1: rule__ElkLabel__Group_3__1 : rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 ;
    public final void rule__ElkLabel__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4727:1: ( rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 )
            // InternalGrana.g:4728:2: rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4735:1: rule__ElkLabel__Group_3__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkLabel__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4739:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4740:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4740:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4741:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 
            // InternalGrana.g:4742:1: ( ruleShapeLayout )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==44) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // InternalGrana.g:4742:3: ruleShapeLayout
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
    // InternalGrana.g:4752:1: rule__ElkLabel__Group_3__2 : rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 ;
    public final void rule__ElkLabel__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4756:1: ( rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 )
            // InternalGrana.g:4757:2: rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4764:1: rule__ElkLabel__Group_3__2__Impl : ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) ;
    public final void rule__ElkLabel__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4768:1: ( ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) )
            // InternalGrana.g:4769:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            {
            // InternalGrana.g:4769:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            // InternalGrana.g:4770:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 
            // InternalGrana.g:4771:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==RULE_ID) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // InternalGrana.g:4771:2: rule__ElkLabel__PropertiesAssignment_3_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkLabel__PropertiesAssignment_3_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop49;
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
    // InternalGrana.g:4781:1: rule__ElkLabel__Group_3__3 : rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 ;
    public final void rule__ElkLabel__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4785:1: ( rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 )
            // InternalGrana.g:4786:2: rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4793:1: rule__ElkLabel__Group_3__3__Impl : ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) ;
    public final void rule__ElkLabel__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4797:1: ( ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) )
            // InternalGrana.g:4798:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            {
            // InternalGrana.g:4798:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            // InternalGrana.g:4799:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            {
             before(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 
            // InternalGrana.g:4800:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==41) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // InternalGrana.g:4800:2: rule__ElkLabel__LabelsAssignment_3_3
            	    {
            	    pushFollow(FOLLOW_33);
            	    rule__ElkLabel__LabelsAssignment_3_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop50;
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
    // InternalGrana.g:4810:1: rule__ElkLabel__Group_3__4 : rule__ElkLabel__Group_3__4__Impl ;
    public final void rule__ElkLabel__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4814:1: ( rule__ElkLabel__Group_3__4__Impl )
            // InternalGrana.g:4815:2: rule__ElkLabel__Group_3__4__Impl
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
    // InternalGrana.g:4821:1: rule__ElkLabel__Group_3__4__Impl : ( '}' ) ;
    public final void rule__ElkLabel__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4825:1: ( ( '}' ) )
            // InternalGrana.g:4826:1: ( '}' )
            {
            // InternalGrana.g:4826:1: ( '}' )
            // InternalGrana.g:4827:1: '}'
            {
             before(grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4()); 
            match(input,39,FOLLOW_2); 
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
    // InternalGrana.g:4850:1: rule__ElkPort__Group__0 : rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 ;
    public final void rule__ElkPort__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4854:1: ( rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 )
            // InternalGrana.g:4855:2: rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1
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
    // InternalGrana.g:4862:1: rule__ElkPort__Group__0__Impl : ( 'port' ) ;
    public final void rule__ElkPort__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4866:1: ( ( 'port' ) )
            // InternalGrana.g:4867:1: ( 'port' )
            {
            // InternalGrana.g:4867:1: ( 'port' )
            // InternalGrana.g:4868:1: 'port'
            {
             before(grammarAccess.getElkPortAccess().getPortKeyword_0()); 
            match(input,43,FOLLOW_2); 
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
    // InternalGrana.g:4881:1: rule__ElkPort__Group__1 : rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 ;
    public final void rule__ElkPort__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4885:1: ( rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 )
            // InternalGrana.g:4886:2: rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2
            {
            pushFollow(FOLLOW_30);
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
    // InternalGrana.g:4893:1: rule__ElkPort__Group__1__Impl : ( ( rule__ElkPort__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkPort__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4897:1: ( ( ( rule__ElkPort__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:4898:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:4898:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            // InternalGrana.g:4899:1: ( rule__ElkPort__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkPortAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:4900:1: ( rule__ElkPort__IdentifierAssignment_1 )
            // InternalGrana.g:4900:2: rule__ElkPort__IdentifierAssignment_1
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
    // InternalGrana.g:4910:1: rule__ElkPort__Group__2 : rule__ElkPort__Group__2__Impl ;
    public final void rule__ElkPort__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4914:1: ( rule__ElkPort__Group__2__Impl )
            // InternalGrana.g:4915:2: rule__ElkPort__Group__2__Impl
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
    // InternalGrana.g:4921:1: rule__ElkPort__Group__2__Impl : ( ( rule__ElkPort__Group_2__0 )? ) ;
    public final void rule__ElkPort__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4925:1: ( ( ( rule__ElkPort__Group_2__0 )? ) )
            // InternalGrana.g:4926:1: ( ( rule__ElkPort__Group_2__0 )? )
            {
            // InternalGrana.g:4926:1: ( ( rule__ElkPort__Group_2__0 )? )
            // InternalGrana.g:4927:1: ( rule__ElkPort__Group_2__0 )?
            {
             before(grammarAccess.getElkPortAccess().getGroup_2()); 
            // InternalGrana.g:4928:1: ( rule__ElkPort__Group_2__0 )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==38) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalGrana.g:4928:2: rule__ElkPort__Group_2__0
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
    // InternalGrana.g:4944:1: rule__ElkPort__Group_2__0 : rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 ;
    public final void rule__ElkPort__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4948:1: ( rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 )
            // InternalGrana.g:4949:2: rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4956:1: rule__ElkPort__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkPort__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4960:1: ( ( '{' ) )
            // InternalGrana.g:4961:1: ( '{' )
            {
            // InternalGrana.g:4961:1: ( '{' )
            // InternalGrana.g:4962:1: '{'
            {
             before(grammarAccess.getElkPortAccess().getLeftCurlyBracketKeyword_2_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4975:1: rule__ElkPort__Group_2__1 : rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 ;
    public final void rule__ElkPort__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4979:1: ( rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 )
            // InternalGrana.g:4980:2: rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:4987:1: rule__ElkPort__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkPort__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4991:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4992:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4992:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4993:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkPortAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:4994:1: ( ruleShapeLayout )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==44) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalGrana.g:4994:3: ruleShapeLayout
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
    // InternalGrana.g:5004:1: rule__ElkPort__Group_2__2 : rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 ;
    public final void rule__ElkPort__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5008:1: ( rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 )
            // InternalGrana.g:5009:2: rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:5016:1: rule__ElkPort__Group_2__2__Impl : ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkPort__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5020:1: ( ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:5021:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:5021:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:5022:1: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkPortAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:5023:1: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==RULE_ID) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // InternalGrana.g:5023:2: rule__ElkPort__PropertiesAssignment_2_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkPort__PropertiesAssignment_2_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop53;
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
    // InternalGrana.g:5033:1: rule__ElkPort__Group_2__3 : rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 ;
    public final void rule__ElkPort__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5037:1: ( rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 )
            // InternalGrana.g:5038:2: rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:5045:1: rule__ElkPort__Group_2__3__Impl : ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) ;
    public final void rule__ElkPort__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5049:1: ( ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) )
            // InternalGrana.g:5050:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            {
            // InternalGrana.g:5050:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            // InternalGrana.g:5051:1: ( rule__ElkPort__LabelsAssignment_2_3 )*
            {
             before(grammarAccess.getElkPortAccess().getLabelsAssignment_2_3()); 
            // InternalGrana.g:5052:1: ( rule__ElkPort__LabelsAssignment_2_3 )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==41) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // InternalGrana.g:5052:2: rule__ElkPort__LabelsAssignment_2_3
            	    {
            	    pushFollow(FOLLOW_33);
            	    rule__ElkPort__LabelsAssignment_2_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop54;
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
    // InternalGrana.g:5062:1: rule__ElkPort__Group_2__4 : rule__ElkPort__Group_2__4__Impl ;
    public final void rule__ElkPort__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5066:1: ( rule__ElkPort__Group_2__4__Impl )
            // InternalGrana.g:5067:2: rule__ElkPort__Group_2__4__Impl
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
    // InternalGrana.g:5073:1: rule__ElkPort__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkPort__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5077:1: ( ( '}' ) )
            // InternalGrana.g:5078:1: ( '}' )
            {
            // InternalGrana.g:5078:1: ( '}' )
            // InternalGrana.g:5079:1: '}'
            {
             before(grammarAccess.getElkPortAccess().getRightCurlyBracketKeyword_2_4()); 
            match(input,39,FOLLOW_2); 
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
    // InternalGrana.g:5102:1: rule__ShapeLayout__Group__0 : rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 ;
    public final void rule__ShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5106:1: ( rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 )
            // InternalGrana.g:5107:2: rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1
            {
            pushFollow(FOLLOW_36);
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
    // InternalGrana.g:5114:1: rule__ShapeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__ShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5118:1: ( ( 'layout' ) )
            // InternalGrana.g:5119:1: ( 'layout' )
            {
            // InternalGrana.g:5119:1: ( 'layout' )
            // InternalGrana.g:5120:1: 'layout'
            {
             before(grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:5133:1: rule__ShapeLayout__Group__1 : rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 ;
    public final void rule__ShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5137:1: ( rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 )
            // InternalGrana.g:5138:2: rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2
            {
            pushFollow(FOLLOW_37);
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
    // InternalGrana.g:5145:1: rule__ShapeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__ShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5149:1: ( ( '[' ) )
            // InternalGrana.g:5150:1: ( '[' )
            {
            // InternalGrana.g:5150:1: ( '[' )
            // InternalGrana.g:5151:1: '['
            {
             before(grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,45,FOLLOW_2); 
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
    // InternalGrana.g:5164:1: rule__ShapeLayout__Group__2 : rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 ;
    public final void rule__ShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5168:1: ( rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 )
            // InternalGrana.g:5169:2: rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3
            {
            pushFollow(FOLLOW_38);
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
    // InternalGrana.g:5176:1: rule__ShapeLayout__Group__2__Impl : ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) ;
    public final void rule__ShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5180:1: ( ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) )
            // InternalGrana.g:5181:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            {
            // InternalGrana.g:5181:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            // InternalGrana.g:5182:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 
            // InternalGrana.g:5183:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            // InternalGrana.g:5183:2: rule__ShapeLayout__UnorderedGroup_2
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
    // InternalGrana.g:5193:1: rule__ShapeLayout__Group__3 : rule__ShapeLayout__Group__3__Impl ;
    public final void rule__ShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5197:1: ( rule__ShapeLayout__Group__3__Impl )
            // InternalGrana.g:5198:2: rule__ShapeLayout__Group__3__Impl
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
    // InternalGrana.g:5204:1: rule__ShapeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__ShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5208:1: ( ( ']' ) )
            // InternalGrana.g:5209:1: ( ']' )
            {
            // InternalGrana.g:5209:1: ( ']' )
            // InternalGrana.g:5210:1: ']'
            {
             before(grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,46,FOLLOW_2); 
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
    // InternalGrana.g:5231:1: rule__ShapeLayout__Group_2_0__0 : rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 ;
    public final void rule__ShapeLayout__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5235:1: ( rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 )
            // InternalGrana.g:5236:2: rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalGrana.g:5243:1: rule__ShapeLayout__Group_2_0__0__Impl : ( 'position' ) ;
    public final void rule__ShapeLayout__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5247:1: ( ( 'position' ) )
            // InternalGrana.g:5248:1: ( 'position' )
            {
            // InternalGrana.g:5248:1: ( 'position' )
            // InternalGrana.g:5249:1: 'position'
            {
             before(grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0()); 
            match(input,47,FOLLOW_2); 
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
    // InternalGrana.g:5262:1: rule__ShapeLayout__Group_2_0__1 : rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 ;
    public final void rule__ShapeLayout__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5266:1: ( rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 )
            // InternalGrana.g:5267:2: rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2
            {
            pushFollow(FOLLOW_39);
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
    // InternalGrana.g:5274:1: rule__ShapeLayout__Group_2_0__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5278:1: ( ( ':' ) )
            // InternalGrana.g:5279:1: ( ':' )
            {
            // InternalGrana.g:5279:1: ( ':' )
            // InternalGrana.g:5280:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:5293:1: rule__ShapeLayout__Group_2_0__2 : rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 ;
    public final void rule__ShapeLayout__Group_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5297:1: ( rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 )
            // InternalGrana.g:5298:2: rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3
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
    // InternalGrana.g:5305:1: rule__ShapeLayout__Group_2_0__2__Impl : ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5309:1: ( ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) )
            // InternalGrana.g:5310:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            {
            // InternalGrana.g:5310:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            // InternalGrana.g:5311:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 
            // InternalGrana.g:5312:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            // InternalGrana.g:5312:2: rule__ShapeLayout__XAssignment_2_0_2
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
    // InternalGrana.g:5322:1: rule__ShapeLayout__Group_2_0__3 : rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 ;
    public final void rule__ShapeLayout__Group_2_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5326:1: ( rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 )
            // InternalGrana.g:5327:2: rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4
            {
            pushFollow(FOLLOW_39);
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
    // InternalGrana.g:5334:1: rule__ShapeLayout__Group_2_0__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5338:1: ( ( ',' ) )
            // InternalGrana.g:5339:1: ( ',' )
            {
            // InternalGrana.g:5339:1: ( ',' )
            // InternalGrana.g:5340:1: ','
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
    // InternalGrana.g:5353:1: rule__ShapeLayout__Group_2_0__4 : rule__ShapeLayout__Group_2_0__4__Impl ;
    public final void rule__ShapeLayout__Group_2_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5357:1: ( rule__ShapeLayout__Group_2_0__4__Impl )
            // InternalGrana.g:5358:2: rule__ShapeLayout__Group_2_0__4__Impl
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
    // InternalGrana.g:5364:1: rule__ShapeLayout__Group_2_0__4__Impl : ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5368:1: ( ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) )
            // InternalGrana.g:5369:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            {
            // InternalGrana.g:5369:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            // InternalGrana.g:5370:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 
            // InternalGrana.g:5371:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            // InternalGrana.g:5371:2: rule__ShapeLayout__YAssignment_2_0_4
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
    // InternalGrana.g:5391:1: rule__ShapeLayout__Group_2_1__0 : rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 ;
    public final void rule__ShapeLayout__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5395:1: ( rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 )
            // InternalGrana.g:5396:2: rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalGrana.g:5403:1: rule__ShapeLayout__Group_2_1__0__Impl : ( 'size' ) ;
    public final void rule__ShapeLayout__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5407:1: ( ( 'size' ) )
            // InternalGrana.g:5408:1: ( 'size' )
            {
            // InternalGrana.g:5408:1: ( 'size' )
            // InternalGrana.g:5409:1: 'size'
            {
             before(grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0()); 
            match(input,48,FOLLOW_2); 
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
    // InternalGrana.g:5422:1: rule__ShapeLayout__Group_2_1__1 : rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 ;
    public final void rule__ShapeLayout__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5426:1: ( rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 )
            // InternalGrana.g:5427:2: rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2
            {
            pushFollow(FOLLOW_39);
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
    // InternalGrana.g:5434:1: rule__ShapeLayout__Group_2_1__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5438:1: ( ( ':' ) )
            // InternalGrana.g:5439:1: ( ':' )
            {
            // InternalGrana.g:5439:1: ( ':' )
            // InternalGrana.g:5440:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:5453:1: rule__ShapeLayout__Group_2_1__2 : rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 ;
    public final void rule__ShapeLayout__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5457:1: ( rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 )
            // InternalGrana.g:5458:2: rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3
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
    // InternalGrana.g:5465:1: rule__ShapeLayout__Group_2_1__2__Impl : ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5469:1: ( ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) )
            // InternalGrana.g:5470:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            {
            // InternalGrana.g:5470:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            // InternalGrana.g:5471:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 
            // InternalGrana.g:5472:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            // InternalGrana.g:5472:2: rule__ShapeLayout__WidthAssignment_2_1_2
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
    // InternalGrana.g:5482:1: rule__ShapeLayout__Group_2_1__3 : rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 ;
    public final void rule__ShapeLayout__Group_2_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5486:1: ( rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 )
            // InternalGrana.g:5487:2: rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4
            {
            pushFollow(FOLLOW_39);
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
    // InternalGrana.g:5494:1: rule__ShapeLayout__Group_2_1__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5498:1: ( ( ',' ) )
            // InternalGrana.g:5499:1: ( ',' )
            {
            // InternalGrana.g:5499:1: ( ',' )
            // InternalGrana.g:5500:1: ','
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
    // InternalGrana.g:5513:1: rule__ShapeLayout__Group_2_1__4 : rule__ShapeLayout__Group_2_1__4__Impl ;
    public final void rule__ShapeLayout__Group_2_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5517:1: ( rule__ShapeLayout__Group_2_1__4__Impl )
            // InternalGrana.g:5518:2: rule__ShapeLayout__Group_2_1__4__Impl
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
    // InternalGrana.g:5524:1: rule__ShapeLayout__Group_2_1__4__Impl : ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5528:1: ( ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) )
            // InternalGrana.g:5529:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            {
            // InternalGrana.g:5529:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            // InternalGrana.g:5530:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 
            // InternalGrana.g:5531:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            // InternalGrana.g:5531:2: rule__ShapeLayout__HeightAssignment_2_1_4
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
    // InternalGrana.g:5551:1: rule__ElkEdge__Group__0 : rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 ;
    public final void rule__ElkEdge__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5555:1: ( rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 )
            // InternalGrana.g:5556:2: rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1
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
    // InternalGrana.g:5563:1: rule__ElkEdge__Group__0__Impl : ( 'edge' ) ;
    public final void rule__ElkEdge__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5567:1: ( ( 'edge' ) )
            // InternalGrana.g:5568:1: ( 'edge' )
            {
            // InternalGrana.g:5568:1: ( 'edge' )
            // InternalGrana.g:5569:1: 'edge'
            {
             before(grammarAccess.getElkEdgeAccess().getEdgeKeyword_0()); 
            match(input,49,FOLLOW_2); 
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
    // InternalGrana.g:5582:1: rule__ElkEdge__Group__1 : rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 ;
    public final void rule__ElkEdge__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5586:1: ( rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 )
            // InternalGrana.g:5587:2: rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2
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
    // InternalGrana.g:5594:1: rule__ElkEdge__Group__1__Impl : ( ( rule__ElkEdge__Group_1__0 )? ) ;
    public final void rule__ElkEdge__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5598:1: ( ( ( rule__ElkEdge__Group_1__0 )? ) )
            // InternalGrana.g:5599:1: ( ( rule__ElkEdge__Group_1__0 )? )
            {
            // InternalGrana.g:5599:1: ( ( rule__ElkEdge__Group_1__0 )? )
            // InternalGrana.g:5600:1: ( rule__ElkEdge__Group_1__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_1()); 
            // InternalGrana.g:5601:1: ( rule__ElkEdge__Group_1__0 )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==RULE_ID) ) {
                int LA55_1 = input.LA(2);

                if ( (LA55_1==42) ) {
                    alt55=1;
                }
            }
            switch (alt55) {
                case 1 :
                    // InternalGrana.g:5601:2: rule__ElkEdge__Group_1__0
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
    // InternalGrana.g:5611:1: rule__ElkEdge__Group__2 : rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 ;
    public final void rule__ElkEdge__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5615:1: ( rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 )
            // InternalGrana.g:5616:2: rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3
            {
            pushFollow(FOLLOW_40);
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
    // InternalGrana.g:5623:1: rule__ElkEdge__Group__2__Impl : ( ( rule__ElkEdge__SourcesAssignment_2 ) ) ;
    public final void rule__ElkEdge__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5627:1: ( ( ( rule__ElkEdge__SourcesAssignment_2 ) ) )
            // InternalGrana.g:5628:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            {
            // InternalGrana.g:5628:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            // InternalGrana.g:5629:1: ( rule__ElkEdge__SourcesAssignment_2 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_2()); 
            // InternalGrana.g:5630:1: ( rule__ElkEdge__SourcesAssignment_2 )
            // InternalGrana.g:5630:2: rule__ElkEdge__SourcesAssignment_2
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
    // InternalGrana.g:5640:1: rule__ElkEdge__Group__3 : rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 ;
    public final void rule__ElkEdge__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5644:1: ( rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 )
            // InternalGrana.g:5645:2: rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4
            {
            pushFollow(FOLLOW_40);
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
    // InternalGrana.g:5652:1: rule__ElkEdge__Group__3__Impl : ( ( rule__ElkEdge__Group_3__0 )* ) ;
    public final void rule__ElkEdge__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5656:1: ( ( ( rule__ElkEdge__Group_3__0 )* ) )
            // InternalGrana.g:5657:1: ( ( rule__ElkEdge__Group_3__0 )* )
            {
            // InternalGrana.g:5657:1: ( ( rule__ElkEdge__Group_3__0 )* )
            // InternalGrana.g:5658:1: ( rule__ElkEdge__Group_3__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_3()); 
            // InternalGrana.g:5659:1: ( rule__ElkEdge__Group_3__0 )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==32) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // InternalGrana.g:5659:2: rule__ElkEdge__Group_3__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__ElkEdge__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop56;
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
    // InternalGrana.g:5669:1: rule__ElkEdge__Group__4 : rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 ;
    public final void rule__ElkEdge__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5673:1: ( rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 )
            // InternalGrana.g:5674:2: rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5
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
    // InternalGrana.g:5681:1: rule__ElkEdge__Group__4__Impl : ( '->' ) ;
    public final void rule__ElkEdge__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5685:1: ( ( '->' ) )
            // InternalGrana.g:5686:1: ( '->' )
            {
            // InternalGrana.g:5686:1: ( '->' )
            // InternalGrana.g:5687:1: '->'
            {
             before(grammarAccess.getElkEdgeAccess().getHyphenMinusGreaterThanSignKeyword_4()); 
            match(input,50,FOLLOW_2); 
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
    // InternalGrana.g:5700:1: rule__ElkEdge__Group__5 : rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 ;
    public final void rule__ElkEdge__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5704:1: ( rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 )
            // InternalGrana.g:5705:2: rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6
            {
            pushFollow(FOLLOW_41);
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
    // InternalGrana.g:5712:1: rule__ElkEdge__Group__5__Impl : ( ( rule__ElkEdge__TargetsAssignment_5 ) ) ;
    public final void rule__ElkEdge__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5716:1: ( ( ( rule__ElkEdge__TargetsAssignment_5 ) ) )
            // InternalGrana.g:5717:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            {
            // InternalGrana.g:5717:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            // InternalGrana.g:5718:1: ( rule__ElkEdge__TargetsAssignment_5 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_5()); 
            // InternalGrana.g:5719:1: ( rule__ElkEdge__TargetsAssignment_5 )
            // InternalGrana.g:5719:2: rule__ElkEdge__TargetsAssignment_5
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
    // InternalGrana.g:5729:1: rule__ElkEdge__Group__6 : rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 ;
    public final void rule__ElkEdge__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5733:1: ( rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 )
            // InternalGrana.g:5734:2: rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7
            {
            pushFollow(FOLLOW_41);
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
    // InternalGrana.g:5741:1: rule__ElkEdge__Group__6__Impl : ( ( rule__ElkEdge__Group_6__0 )* ) ;
    public final void rule__ElkEdge__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5745:1: ( ( ( rule__ElkEdge__Group_6__0 )* ) )
            // InternalGrana.g:5746:1: ( ( rule__ElkEdge__Group_6__0 )* )
            {
            // InternalGrana.g:5746:1: ( ( rule__ElkEdge__Group_6__0 )* )
            // InternalGrana.g:5747:1: ( rule__ElkEdge__Group_6__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_6()); 
            // InternalGrana.g:5748:1: ( rule__ElkEdge__Group_6__0 )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==32) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalGrana.g:5748:2: rule__ElkEdge__Group_6__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__ElkEdge__Group_6__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop57;
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
    // InternalGrana.g:5758:1: rule__ElkEdge__Group__7 : rule__ElkEdge__Group__7__Impl ;
    public final void rule__ElkEdge__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5762:1: ( rule__ElkEdge__Group__7__Impl )
            // InternalGrana.g:5763:2: rule__ElkEdge__Group__7__Impl
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
    // InternalGrana.g:5769:1: rule__ElkEdge__Group__7__Impl : ( ( rule__ElkEdge__Group_7__0 )? ) ;
    public final void rule__ElkEdge__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5773:1: ( ( ( rule__ElkEdge__Group_7__0 )? ) )
            // InternalGrana.g:5774:1: ( ( rule__ElkEdge__Group_7__0 )? )
            {
            // InternalGrana.g:5774:1: ( ( rule__ElkEdge__Group_7__0 )? )
            // InternalGrana.g:5775:1: ( rule__ElkEdge__Group_7__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_7()); 
            // InternalGrana.g:5776:1: ( rule__ElkEdge__Group_7__0 )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==38) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalGrana.g:5776:2: rule__ElkEdge__Group_7__0
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
    // InternalGrana.g:5802:1: rule__ElkEdge__Group_1__0 : rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 ;
    public final void rule__ElkEdge__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5806:1: ( rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 )
            // InternalGrana.g:5807:2: rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalGrana.g:5814:1: rule__ElkEdge__Group_1__0__Impl : ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkEdge__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5818:1: ( ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:5819:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:5819:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:5820:1: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkEdgeAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:5821:1: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            // InternalGrana.g:5821:2: rule__ElkEdge__IdentifierAssignment_1_0
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
    // InternalGrana.g:5831:1: rule__ElkEdge__Group_1__1 : rule__ElkEdge__Group_1__1__Impl ;
    public final void rule__ElkEdge__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5835:1: ( rule__ElkEdge__Group_1__1__Impl )
            // InternalGrana.g:5836:2: rule__ElkEdge__Group_1__1__Impl
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
    // InternalGrana.g:5842:1: rule__ElkEdge__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdge__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5846:1: ( ( ':' ) )
            // InternalGrana.g:5847:1: ( ':' )
            {
            // InternalGrana.g:5847:1: ( ':' )
            // InternalGrana.g:5848:1: ':'
            {
             before(grammarAccess.getElkEdgeAccess().getColonKeyword_1_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:5865:1: rule__ElkEdge__Group_3__0 : rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 ;
    public final void rule__ElkEdge__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5869:1: ( rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 )
            // InternalGrana.g:5870:2: rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1
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
    // InternalGrana.g:5877:1: rule__ElkEdge__Group_3__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5881:1: ( ( ',' ) )
            // InternalGrana.g:5882:1: ( ',' )
            {
            // InternalGrana.g:5882:1: ( ',' )
            // InternalGrana.g:5883:1: ','
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
    // InternalGrana.g:5896:1: rule__ElkEdge__Group_3__1 : rule__ElkEdge__Group_3__1__Impl ;
    public final void rule__ElkEdge__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5900:1: ( rule__ElkEdge__Group_3__1__Impl )
            // InternalGrana.g:5901:2: rule__ElkEdge__Group_3__1__Impl
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
    // InternalGrana.g:5907:1: rule__ElkEdge__Group_3__1__Impl : ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) ;
    public final void rule__ElkEdge__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5911:1: ( ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) )
            // InternalGrana.g:5912:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            {
            // InternalGrana.g:5912:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            // InternalGrana.g:5913:1: ( rule__ElkEdge__SourcesAssignment_3_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_3_1()); 
            // InternalGrana.g:5914:1: ( rule__ElkEdge__SourcesAssignment_3_1 )
            // InternalGrana.g:5914:2: rule__ElkEdge__SourcesAssignment_3_1
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
    // InternalGrana.g:5928:1: rule__ElkEdge__Group_6__0 : rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 ;
    public final void rule__ElkEdge__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5932:1: ( rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 )
            // InternalGrana.g:5933:2: rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1
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
    // InternalGrana.g:5940:1: rule__ElkEdge__Group_6__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5944:1: ( ( ',' ) )
            // InternalGrana.g:5945:1: ( ',' )
            {
            // InternalGrana.g:5945:1: ( ',' )
            // InternalGrana.g:5946:1: ','
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
    // InternalGrana.g:5959:1: rule__ElkEdge__Group_6__1 : rule__ElkEdge__Group_6__1__Impl ;
    public final void rule__ElkEdge__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5963:1: ( rule__ElkEdge__Group_6__1__Impl )
            // InternalGrana.g:5964:2: rule__ElkEdge__Group_6__1__Impl
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
    // InternalGrana.g:5970:1: rule__ElkEdge__Group_6__1__Impl : ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) ;
    public final void rule__ElkEdge__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5974:1: ( ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) )
            // InternalGrana.g:5975:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            {
            // InternalGrana.g:5975:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            // InternalGrana.g:5976:1: ( rule__ElkEdge__TargetsAssignment_6_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_6_1()); 
            // InternalGrana.g:5977:1: ( rule__ElkEdge__TargetsAssignment_6_1 )
            // InternalGrana.g:5977:2: rule__ElkEdge__TargetsAssignment_6_1
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
    // InternalGrana.g:5991:1: rule__ElkEdge__Group_7__0 : rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 ;
    public final void rule__ElkEdge__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5995:1: ( rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 )
            // InternalGrana.g:5996:2: rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:6003:1: rule__ElkEdge__Group_7__0__Impl : ( '{' ) ;
    public final void rule__ElkEdge__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6007:1: ( ( '{' ) )
            // InternalGrana.g:6008:1: ( '{' )
            {
            // InternalGrana.g:6008:1: ( '{' )
            // InternalGrana.g:6009:1: '{'
            {
             before(grammarAccess.getElkEdgeAccess().getLeftCurlyBracketKeyword_7_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:6022:1: rule__ElkEdge__Group_7__1 : rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 ;
    public final void rule__ElkEdge__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6026:1: ( rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 )
            // InternalGrana.g:6027:2: rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:6034:1: rule__ElkEdge__Group_7__1__Impl : ( ( ruleEdgeLayout )? ) ;
    public final void rule__ElkEdge__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6038:1: ( ( ( ruleEdgeLayout )? ) )
            // InternalGrana.g:6039:1: ( ( ruleEdgeLayout )? )
            {
            // InternalGrana.g:6039:1: ( ( ruleEdgeLayout )? )
            // InternalGrana.g:6040:1: ( ruleEdgeLayout )?
            {
             before(grammarAccess.getElkEdgeAccess().getEdgeLayoutParserRuleCall_7_1()); 
            // InternalGrana.g:6041:1: ( ruleEdgeLayout )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==44) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalGrana.g:6041:3: ruleEdgeLayout
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
    // InternalGrana.g:6051:1: rule__ElkEdge__Group_7__2 : rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 ;
    public final void rule__ElkEdge__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6055:1: ( rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 )
            // InternalGrana.g:6056:2: rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:6063:1: rule__ElkEdge__Group_7__2__Impl : ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) ;
    public final void rule__ElkEdge__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6067:1: ( ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) )
            // InternalGrana.g:6068:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            {
            // InternalGrana.g:6068:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            // InternalGrana.g:6069:1: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            {
             before(grammarAccess.getElkEdgeAccess().getPropertiesAssignment_7_2()); 
            // InternalGrana.g:6070:1: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==RULE_ID) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // InternalGrana.g:6070:2: rule__ElkEdge__PropertiesAssignment_7_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkEdge__PropertiesAssignment_7_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop60;
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
    // InternalGrana.g:6080:1: rule__ElkEdge__Group_7__3 : rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 ;
    public final void rule__ElkEdge__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6084:1: ( rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 )
            // InternalGrana.g:6085:2: rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4
            {
            pushFollow(FOLLOW_32);
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
    // InternalGrana.g:6092:1: rule__ElkEdge__Group_7__3__Impl : ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) ;
    public final void rule__ElkEdge__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6096:1: ( ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) )
            // InternalGrana.g:6097:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            {
            // InternalGrana.g:6097:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            // InternalGrana.g:6098:1: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            {
             before(grammarAccess.getElkEdgeAccess().getLabelsAssignment_7_3()); 
            // InternalGrana.g:6099:1: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==41) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // InternalGrana.g:6099:2: rule__ElkEdge__LabelsAssignment_7_3
            	    {
            	    pushFollow(FOLLOW_33);
            	    rule__ElkEdge__LabelsAssignment_7_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop61;
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
    // InternalGrana.g:6109:1: rule__ElkEdge__Group_7__4 : rule__ElkEdge__Group_7__4__Impl ;
    public final void rule__ElkEdge__Group_7__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6113:1: ( rule__ElkEdge__Group_7__4__Impl )
            // InternalGrana.g:6114:2: rule__ElkEdge__Group_7__4__Impl
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
    // InternalGrana.g:6120:1: rule__ElkEdge__Group_7__4__Impl : ( '}' ) ;
    public final void rule__ElkEdge__Group_7__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6124:1: ( ( '}' ) )
            // InternalGrana.g:6125:1: ( '}' )
            {
            // InternalGrana.g:6125:1: ( '}' )
            // InternalGrana.g:6126:1: '}'
            {
             before(grammarAccess.getElkEdgeAccess().getRightCurlyBracketKeyword_7_4()); 
            match(input,39,FOLLOW_2); 
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
    // InternalGrana.g:6149:1: rule__EdgeLayout__Group__0 : rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 ;
    public final void rule__EdgeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6153:1: ( rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 )
            // InternalGrana.g:6154:2: rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1
            {
            pushFollow(FOLLOW_36);
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
    // InternalGrana.g:6161:1: rule__EdgeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__EdgeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6165:1: ( ( 'layout' ) )
            // InternalGrana.g:6166:1: ( 'layout' )
            {
            // InternalGrana.g:6166:1: ( 'layout' )
            // InternalGrana.g:6167:1: 'layout'
            {
             before(grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:6180:1: rule__EdgeLayout__Group__1 : rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 ;
    public final void rule__EdgeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6184:1: ( rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 )
            // InternalGrana.g:6185:2: rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2
            {
            pushFollow(FOLLOW_42);
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
    // InternalGrana.g:6192:1: rule__EdgeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__EdgeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6196:1: ( ( '[' ) )
            // InternalGrana.g:6197:1: ( '[' )
            {
            // InternalGrana.g:6197:1: ( '[' )
            // InternalGrana.g:6198:1: '['
            {
             before(grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,45,FOLLOW_2); 
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
    // InternalGrana.g:6211:1: rule__EdgeLayout__Group__2 : rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 ;
    public final void rule__EdgeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6215:1: ( rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 )
            // InternalGrana.g:6216:2: rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3
            {
            pushFollow(FOLLOW_38);
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
    // InternalGrana.g:6223:1: rule__EdgeLayout__Group__2__Impl : ( ( rule__EdgeLayout__Alternatives_2 ) ) ;
    public final void rule__EdgeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6227:1: ( ( ( rule__EdgeLayout__Alternatives_2 ) ) )
            // InternalGrana.g:6228:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            {
            // InternalGrana.g:6228:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            // InternalGrana.g:6229:1: ( rule__EdgeLayout__Alternatives_2 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 
            // InternalGrana.g:6230:1: ( rule__EdgeLayout__Alternatives_2 )
            // InternalGrana.g:6230:2: rule__EdgeLayout__Alternatives_2
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
    // InternalGrana.g:6240:1: rule__EdgeLayout__Group__3 : rule__EdgeLayout__Group__3__Impl ;
    public final void rule__EdgeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6244:1: ( rule__EdgeLayout__Group__3__Impl )
            // InternalGrana.g:6245:2: rule__EdgeLayout__Group__3__Impl
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
    // InternalGrana.g:6251:1: rule__EdgeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__EdgeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6255:1: ( ( ']' ) )
            // InternalGrana.g:6256:1: ( ']' )
            {
            // InternalGrana.g:6256:1: ( ']' )
            // InternalGrana.g:6257:1: ']'
            {
             before(grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,46,FOLLOW_2); 
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
    // InternalGrana.g:6278:1: rule__ElkSingleEdgeSection__Group__0 : rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 ;
    public final void rule__ElkSingleEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6282:1: ( rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 )
            // InternalGrana.g:6283:2: rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1
            {
            pushFollow(FOLLOW_43);
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
    // InternalGrana.g:6290:1: rule__ElkSingleEdgeSection__Group__0__Impl : ( () ) ;
    public final void rule__ElkSingleEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6294:1: ( ( () ) )
            // InternalGrana.g:6295:1: ( () )
            {
            // InternalGrana.g:6295:1: ( () )
            // InternalGrana.g:6296:1: ()
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 
            // InternalGrana.g:6297:1: ()
            // InternalGrana.g:6299:1: 
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
    // InternalGrana.g:6309:1: rule__ElkSingleEdgeSection__Group__1 : rule__ElkSingleEdgeSection__Group__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6313:1: ( rule__ElkSingleEdgeSection__Group__1__Impl )
            // InternalGrana.g:6314:2: rule__ElkSingleEdgeSection__Group__1__Impl
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
    // InternalGrana.g:6320:1: rule__ElkSingleEdgeSection__Group__1__Impl : ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6324:1: ( ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) ) )
            // InternalGrana.g:6325:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) )
            {
            // InternalGrana.g:6325:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) )
            // InternalGrana.g:6326:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1()); 
            // InternalGrana.g:6327:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1 )
            // InternalGrana.g:6327:2: rule__ElkSingleEdgeSection__UnorderedGroup_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1()); 

            }


            }

        }
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


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__0"
    // InternalGrana.g:6341:1: rule__ElkSingleEdgeSection__Group_1_0__0 : rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6345:1: ( rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1 )
            // InternalGrana.g:6346:2: rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkSingleEdgeSection__Group_1_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__0__Impl"
    // InternalGrana.g:6353:1: rule__ElkSingleEdgeSection__Group_1_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6357:1: ( ( 'incoming' ) )
            // InternalGrana.g:6358:1: ( 'incoming' )
            {
            // InternalGrana.g:6358:1: ( 'incoming' )
            // InternalGrana.g:6359:1: 'incoming'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__1"
    // InternalGrana.g:6372:1: rule__ElkSingleEdgeSection__Group_1_0__1 : rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6376:1: ( rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2 )
            // InternalGrana.g:6377:2: rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2
            {
            pushFollow(FOLLOW_8);
            rule__ElkSingleEdgeSection__Group_1_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__1__Impl"
    // InternalGrana.g:6384:1: rule__ElkSingleEdgeSection__Group_1_0__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6388:1: ( ( ':' ) )
            // InternalGrana.g:6389:1: ( ':' )
            {
            // InternalGrana.g:6389:1: ( ':' )
            // InternalGrana.g:6390:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__2"
    // InternalGrana.g:6403:1: rule__ElkSingleEdgeSection__Group_1_0__2 : rule__ElkSingleEdgeSection__Group_1_0__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6407:1: ( rule__ElkSingleEdgeSection__Group_1_0__2__Impl )
            // InternalGrana.g:6408:2: rule__ElkSingleEdgeSection__Group_1_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__2__Impl"
    // InternalGrana.g:6414:1: rule__ElkSingleEdgeSection__Group_1_0__2__Impl : ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6418:1: ( ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) ) )
            // InternalGrana.g:6419:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) )
            {
            // InternalGrana.g:6419:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) )
            // InternalGrana.g:6420:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_2()); 
            // InternalGrana.g:6421:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 )
            // InternalGrana.g:6421:2: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__0"
    // InternalGrana.g:6437:1: rule__ElkSingleEdgeSection__Group_1_1__0 : rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6441:1: ( rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 )
            // InternalGrana.g:6442:2: rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalGrana.g:6449:1: rule__ElkSingleEdgeSection__Group_1_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6453:1: ( ( 'outgoing' ) )
            // InternalGrana.g:6454:1: ( 'outgoing' )
            {
            // InternalGrana.g:6454:1: ( 'outgoing' )
            // InternalGrana.g:6455:1: 'outgoing'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_1_0()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_1_0()); 

            }


            }

        }
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
    // InternalGrana.g:6468:1: rule__ElkSingleEdgeSection__Group_1_1__1 : rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6472:1: ( rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 )
            // InternalGrana.g:6473:2: rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2
            {
            pushFollow(FOLLOW_8);
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
    // InternalGrana.g:6480:1: rule__ElkSingleEdgeSection__Group_1_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6484:1: ( ( ':' ) )
            // InternalGrana.g:6485:1: ( ':' )
            {
            // InternalGrana.g:6485:1: ( ':' )
            // InternalGrana.g:6486:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:6499:1: rule__ElkSingleEdgeSection__Group_1_1__2 : rule__ElkSingleEdgeSection__Group_1_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6503:1: ( rule__ElkSingleEdgeSection__Group_1_1__2__Impl )
            // InternalGrana.g:6504:2: rule__ElkSingleEdgeSection__Group_1_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__2__Impl();

            state._fsp--;


            }

        }
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
    // InternalGrana.g:6510:1: rule__ElkSingleEdgeSection__Group_1_1__2__Impl : ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6514:1: ( ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) ) )
            // InternalGrana.g:6515:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) )
            {
            // InternalGrana.g:6515:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) )
            // InternalGrana.g:6516:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_1_2()); 
            // InternalGrana.g:6517:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 )
            // InternalGrana.g:6517:2: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_1_2()); 

            }


            }

        }
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


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__0"
    // InternalGrana.g:6533:1: rule__ElkSingleEdgeSection__Group_1_2__0 : rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6537:1: ( rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1 )
            // InternalGrana.g:6538:2: rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkSingleEdgeSection__Group_1_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__0__Impl"
    // InternalGrana.g:6545:1: rule__ElkSingleEdgeSection__Group_1_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6549:1: ( ( 'start' ) )
            // InternalGrana.g:6550:1: ( 'start' )
            {
            // InternalGrana.g:6550:1: ( 'start' )
            // InternalGrana.g:6551:1: 'start'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_2_0()); 
            match(input,53,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__1"
    // InternalGrana.g:6564:1: rule__ElkSingleEdgeSection__Group_1_2__1 : rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6568:1: ( rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2 )
            // InternalGrana.g:6569:2: rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2
            {
            pushFollow(FOLLOW_39);
            rule__ElkSingleEdgeSection__Group_1_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__1__Impl"
    // InternalGrana.g:6576:1: rule__ElkSingleEdgeSection__Group_1_2__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6580:1: ( ( ':' ) )
            // InternalGrana.g:6581:1: ( ':' )
            {
            // InternalGrana.g:6581:1: ( ':' )
            // InternalGrana.g:6582:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_2_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__2"
    // InternalGrana.g:6595:1: rule__ElkSingleEdgeSection__Group_1_2__2 : rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6599:1: ( rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3 )
            // InternalGrana.g:6600:2: rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkSingleEdgeSection__Group_1_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__2__Impl"
    // InternalGrana.g:6607:1: rule__ElkSingleEdgeSection__Group_1_2__2__Impl : ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6611:1: ( ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) ) )
            // InternalGrana.g:6612:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) )
            {
            // InternalGrana.g:6612:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) )
            // InternalGrana.g:6613:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_2_2()); 
            // InternalGrana.g:6614:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 )
            // InternalGrana.g:6614:2: rule__ElkSingleEdgeSection__StartXAssignment_1_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__StartXAssignment_1_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__3"
    // InternalGrana.g:6624:1: rule__ElkSingleEdgeSection__Group_1_2__3 : rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6628:1: ( rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4 )
            // InternalGrana.g:6629:2: rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4
            {
            pushFollow(FOLLOW_39);
            rule__ElkSingleEdgeSection__Group_1_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__3__Impl"
    // InternalGrana.g:6636:1: rule__ElkSingleEdgeSection__Group_1_2__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6640:1: ( ( ',' ) )
            // InternalGrana.g:6641:1: ( ',' )
            {
            // InternalGrana.g:6641:1: ( ',' )
            // InternalGrana.g:6642:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_2_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__4"
    // InternalGrana.g:6655:1: rule__ElkSingleEdgeSection__Group_1_2__4 : rule__ElkSingleEdgeSection__Group_1_2__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6659:1: ( rule__ElkSingleEdgeSection__Group_1_2__4__Impl )
            // InternalGrana.g:6660:2: rule__ElkSingleEdgeSection__Group_1_2__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__4__Impl"
    // InternalGrana.g:6666:1: rule__ElkSingleEdgeSection__Group_1_2__4__Impl : ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6670:1: ( ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) ) )
            // InternalGrana.g:6671:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) )
            {
            // InternalGrana.g:6671:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) )
            // InternalGrana.g:6672:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_2_4()); 
            // InternalGrana.g:6673:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 )
            // InternalGrana.g:6673:2: rule__ElkSingleEdgeSection__StartYAssignment_1_2_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__StartYAssignment_1_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_2_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__0"
    // InternalGrana.g:6693:1: rule__ElkSingleEdgeSection__Group_1_3__0 : rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6697:1: ( rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1 )
            // InternalGrana.g:6698:2: rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkSingleEdgeSection__Group_1_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__0__Impl"
    // InternalGrana.g:6705:1: rule__ElkSingleEdgeSection__Group_1_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6709:1: ( ( 'end' ) )
            // InternalGrana.g:6710:1: ( 'end' )
            {
            // InternalGrana.g:6710:1: ( 'end' )
            // InternalGrana.g:6711:1: 'end'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_3_0()); 
            match(input,54,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__1"
    // InternalGrana.g:6724:1: rule__ElkSingleEdgeSection__Group_1_3__1 : rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6728:1: ( rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2 )
            // InternalGrana.g:6729:2: rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2
            {
            pushFollow(FOLLOW_39);
            rule__ElkSingleEdgeSection__Group_1_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__1__Impl"
    // InternalGrana.g:6736:1: rule__ElkSingleEdgeSection__Group_1_3__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6740:1: ( ( ':' ) )
            // InternalGrana.g:6741:1: ( ':' )
            {
            // InternalGrana.g:6741:1: ( ':' )
            // InternalGrana.g:6742:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_3_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__2"
    // InternalGrana.g:6755:1: rule__ElkSingleEdgeSection__Group_1_3__2 : rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6759:1: ( rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3 )
            // InternalGrana.g:6760:2: rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkSingleEdgeSection__Group_1_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__2__Impl"
    // InternalGrana.g:6767:1: rule__ElkSingleEdgeSection__Group_1_3__2__Impl : ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6771:1: ( ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) ) )
            // InternalGrana.g:6772:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) )
            {
            // InternalGrana.g:6772:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) )
            // InternalGrana.g:6773:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_3_2()); 
            // InternalGrana.g:6774:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 )
            // InternalGrana.g:6774:2: rule__ElkSingleEdgeSection__EndXAssignment_1_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__EndXAssignment_1_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__3"
    // InternalGrana.g:6784:1: rule__ElkSingleEdgeSection__Group_1_3__3 : rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6788:1: ( rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4 )
            // InternalGrana.g:6789:2: rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4
            {
            pushFollow(FOLLOW_39);
            rule__ElkSingleEdgeSection__Group_1_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__3__Impl"
    // InternalGrana.g:6796:1: rule__ElkSingleEdgeSection__Group_1_3__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6800:1: ( ( ',' ) )
            // InternalGrana.g:6801:1: ( ',' )
            {
            // InternalGrana.g:6801:1: ( ',' )
            // InternalGrana.g:6802:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_3_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__4"
    // InternalGrana.g:6815:1: rule__ElkSingleEdgeSection__Group_1_3__4 : rule__ElkSingleEdgeSection__Group_1_3__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6819:1: ( rule__ElkSingleEdgeSection__Group_1_3__4__Impl )
            // InternalGrana.g:6820:2: rule__ElkSingleEdgeSection__Group_1_3__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__4__Impl"
    // InternalGrana.g:6826:1: rule__ElkSingleEdgeSection__Group_1_3__4__Impl : ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6830:1: ( ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) ) )
            // InternalGrana.g:6831:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) )
            {
            // InternalGrana.g:6831:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) )
            // InternalGrana.g:6832:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_3_4()); 
            // InternalGrana.g:6833:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 )
            // InternalGrana.g:6833:2: rule__ElkSingleEdgeSection__EndYAssignment_1_3_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__EndYAssignment_1_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__0"
    // InternalGrana.g:6853:1: rule__ElkSingleEdgeSection__Group_1_4__0 : rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6857:1: ( rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1 )
            // InternalGrana.g:6858:2: rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkSingleEdgeSection__Group_1_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__0__Impl"
    // InternalGrana.g:6865:1: rule__ElkSingleEdgeSection__Group_1_4__0__Impl : ( 'bends' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6869:1: ( ( 'bends' ) )
            // InternalGrana.g:6870:1: ( 'bends' )
            {
            // InternalGrana.g:6870:1: ( 'bends' )
            // InternalGrana.g:6871:1: 'bends'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_4_0()); 
            match(input,55,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__1"
    // InternalGrana.g:6884:1: rule__ElkSingleEdgeSection__Group_1_4__1 : rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6888:1: ( rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2 )
            // InternalGrana.g:6889:2: rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2
            {
            pushFollow(FOLLOW_39);
            rule__ElkSingleEdgeSection__Group_1_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__1__Impl"
    // InternalGrana.g:6896:1: rule__ElkSingleEdgeSection__Group_1_4__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6900:1: ( ( ':' ) )
            // InternalGrana.g:6901:1: ( ':' )
            {
            // InternalGrana.g:6901:1: ( ':' )
            // InternalGrana.g:6902:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_4_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__2"
    // InternalGrana.g:6915:1: rule__ElkSingleEdgeSection__Group_1_4__2 : rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6919:1: ( rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3 )
            // InternalGrana.g:6920:2: rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3
            {
            pushFollow(FOLLOW_44);
            rule__ElkSingleEdgeSection__Group_1_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__2__Impl"
    // InternalGrana.g:6927:1: rule__ElkSingleEdgeSection__Group_1_4__2__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6931:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) ) )
            // InternalGrana.g:6932:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) )
            {
            // InternalGrana.g:6932:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) )
            // InternalGrana.g:6933:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_2()); 
            // InternalGrana.g:6934:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 )
            // InternalGrana.g:6934:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__3"
    // InternalGrana.g:6944:1: rule__ElkSingleEdgeSection__Group_1_4__3 : rule__ElkSingleEdgeSection__Group_1_4__3__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6948:1: ( rule__ElkSingleEdgeSection__Group_1_4__3__Impl )
            // InternalGrana.g:6949:2: rule__ElkSingleEdgeSection__Group_1_4__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__3__Impl"
    // InternalGrana.g:6955:1: rule__ElkSingleEdgeSection__Group_1_4__3__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6959:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* ) )
            // InternalGrana.g:6960:1: ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* )
            {
            // InternalGrana.g:6960:1: ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* )
            // InternalGrana.g:6961:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4_3()); 
            // InternalGrana.g:6962:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==56) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // InternalGrana.g:6962:2: rule__ElkSingleEdgeSection__Group_1_4_3__0
            	    {
            	    pushFollow(FOLLOW_45);
            	    rule__ElkSingleEdgeSection__Group_1_4_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__0"
    // InternalGrana.g:6980:1: rule__ElkSingleEdgeSection__Group_1_4_3__0 : rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6984:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1 )
            // InternalGrana.g:6985:2: rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1
            {
            pushFollow(FOLLOW_39);
            rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl"
    // InternalGrana.g:6992:1: rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl : ( '|' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6996:1: ( ( '|' ) )
            // InternalGrana.g:6997:1: ( '|' )
            {
            // InternalGrana.g:6997:1: ( '|' )
            // InternalGrana.g:6998:1: '|'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_4_3_0()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__1"
    // InternalGrana.g:7011:1: rule__ElkSingleEdgeSection__Group_1_4_3__1 : rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7015:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl )
            // InternalGrana.g:7016:2: rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl"
    // InternalGrana.g:7022:1: rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7026:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) ) )
            // InternalGrana.g:7027:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) )
            {
            // InternalGrana.g:7027:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) )
            // InternalGrana.g:7028:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_3_1()); 
            // InternalGrana.g:7029:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 )
            // InternalGrana.g:7029:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__0"
    // InternalGrana.g:7043:1: rule__ElkEdgeSection__Group__0 : rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 ;
    public final void rule__ElkEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7047:1: ( rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 )
            // InternalGrana.g:7048:2: rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1
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
    // InternalGrana.g:7055:1: rule__ElkEdgeSection__Group__0__Impl : ( 'section' ) ;
    public final void rule__ElkEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7059:1: ( ( 'section' ) )
            // InternalGrana.g:7060:1: ( 'section' )
            {
            // InternalGrana.g:7060:1: ( 'section' )
            // InternalGrana.g:7061:1: 'section'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0()); 
            match(input,57,FOLLOW_2); 
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
    // InternalGrana.g:7074:1: rule__ElkEdgeSection__Group__1 : rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 ;
    public final void rule__ElkEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7078:1: ( rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 )
            // InternalGrana.g:7079:2: rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2
            {
            pushFollow(FOLLOW_46);
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
    // InternalGrana.g:7086:1: rule__ElkEdgeSection__Group__1__Impl : ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7090:1: ( ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:7091:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:7091:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            // InternalGrana.g:7092:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:7093:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            // InternalGrana.g:7093:2: rule__ElkEdgeSection__IdentifierAssignment_1
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
    // InternalGrana.g:7103:1: rule__ElkEdgeSection__Group__2 : rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 ;
    public final void rule__ElkEdgeSection__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7107:1: ( rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 )
            // InternalGrana.g:7108:2: rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3
            {
            pushFollow(FOLLOW_46);
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
    // InternalGrana.g:7115:1: rule__ElkEdgeSection__Group__2__Impl : ( ( rule__ElkEdgeSection__Group_2__0 )? ) ;
    public final void rule__ElkEdgeSection__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7119:1: ( ( ( rule__ElkEdgeSection__Group_2__0 )? ) )
            // InternalGrana.g:7120:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            {
            // InternalGrana.g:7120:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            // InternalGrana.g:7121:1: ( rule__ElkEdgeSection__Group_2__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 
            // InternalGrana.g:7122:1: ( rule__ElkEdgeSection__Group_2__0 )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==50) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalGrana.g:7122:2: rule__ElkEdgeSection__Group_2__0
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
    // InternalGrana.g:7132:1: rule__ElkEdgeSection__Group__3 : rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 ;
    public final void rule__ElkEdgeSection__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7136:1: ( rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 )
            // InternalGrana.g:7137:2: rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4
            {
            pushFollow(FOLLOW_43);
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
    // InternalGrana.g:7144:1: rule__ElkEdgeSection__Group__3__Impl : ( '[' ) ;
    public final void rule__ElkEdgeSection__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7148:1: ( ( '[' ) )
            // InternalGrana.g:7149:1: ( '[' )
            {
            // InternalGrana.g:7149:1: ( '[' )
            // InternalGrana.g:7150:1: '['
            {
             before(grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3()); 
            match(input,45,FOLLOW_2); 
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
    // InternalGrana.g:7163:1: rule__ElkEdgeSection__Group__4 : rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 ;
    public final void rule__ElkEdgeSection__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7167:1: ( rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 )
            // InternalGrana.g:7168:2: rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5
            {
            pushFollow(FOLLOW_38);
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
    // InternalGrana.g:7175:1: rule__ElkEdgeSection__Group__4__Impl : ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) ) ;
    public final void rule__ElkEdgeSection__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7179:1: ( ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) ) )
            // InternalGrana.g:7180:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) )
            {
            // InternalGrana.g:7180:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) )
            // InternalGrana.g:7181:1: ( rule__ElkEdgeSection__UnorderedGroup_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4()); 
            // InternalGrana.g:7182:1: ( rule__ElkEdgeSection__UnorderedGroup_4 )
            // InternalGrana.g:7182:2: rule__ElkEdgeSection__UnorderedGroup_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4()); 

            }


            }

        }
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
    // InternalGrana.g:7192:1: rule__ElkEdgeSection__Group__5 : rule__ElkEdgeSection__Group__5__Impl ;
    public final void rule__ElkEdgeSection__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7196:1: ( rule__ElkEdgeSection__Group__5__Impl )
            // InternalGrana.g:7197:2: rule__ElkEdgeSection__Group__5__Impl
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
    // InternalGrana.g:7203:1: rule__ElkEdgeSection__Group__5__Impl : ( ']' ) ;
    public final void rule__ElkEdgeSection__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7207:1: ( ( ']' ) )
            // InternalGrana.g:7208:1: ( ']' )
            {
            // InternalGrana.g:7208:1: ( ']' )
            // InternalGrana.g:7209:1: ']'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5()); 
            match(input,46,FOLLOW_2); 
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
    // InternalGrana.g:7234:1: rule__ElkEdgeSection__Group_2__0 : rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 ;
    public final void rule__ElkEdgeSection__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7238:1: ( rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 )
            // InternalGrana.g:7239:2: rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1
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
    // InternalGrana.g:7246:1: rule__ElkEdgeSection__Group_2__0__Impl : ( '->' ) ;
    public final void rule__ElkEdgeSection__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7250:1: ( ( '->' ) )
            // InternalGrana.g:7251:1: ( '->' )
            {
            // InternalGrana.g:7251:1: ( '->' )
            // InternalGrana.g:7252:1: '->'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 
            match(input,50,FOLLOW_2); 
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
    // InternalGrana.g:7265:1: rule__ElkEdgeSection__Group_2__1 : rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 ;
    public final void rule__ElkEdgeSection__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7269:1: ( rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 )
            // InternalGrana.g:7270:2: rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2
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
    // InternalGrana.g:7277:1: rule__ElkEdgeSection__Group_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7281:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) )
            // InternalGrana.g:7282:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            {
            // InternalGrana.g:7282:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            // InternalGrana.g:7283:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 
            // InternalGrana.g:7284:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            // InternalGrana.g:7284:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1
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
    // InternalGrana.g:7294:1: rule__ElkEdgeSection__Group_2__2 : rule__ElkEdgeSection__Group_2__2__Impl ;
    public final void rule__ElkEdgeSection__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7298:1: ( rule__ElkEdgeSection__Group_2__2__Impl )
            // InternalGrana.g:7299:2: rule__ElkEdgeSection__Group_2__2__Impl
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
    // InternalGrana.g:7305:1: rule__ElkEdgeSection__Group_2__2__Impl : ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7309:1: ( ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) )
            // InternalGrana.g:7310:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            {
            // InternalGrana.g:7310:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            // InternalGrana.g:7311:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 
            // InternalGrana.g:7312:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==32) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalGrana.g:7312:2: rule__ElkEdgeSection__Group_2_2__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__ElkEdgeSection__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop64;
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
    // InternalGrana.g:7328:1: rule__ElkEdgeSection__Group_2_2__0 : rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 ;
    public final void rule__ElkEdgeSection__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7332:1: ( rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 )
            // InternalGrana.g:7333:2: rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1
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
    // InternalGrana.g:7340:1: rule__ElkEdgeSection__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7344:1: ( ( ',' ) )
            // InternalGrana.g:7345:1: ( ',' )
            {
            // InternalGrana.g:7345:1: ( ',' )
            // InternalGrana.g:7346:1: ','
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
    // InternalGrana.g:7359:1: rule__ElkEdgeSection__Group_2_2__1 : rule__ElkEdgeSection__Group_2_2__1__Impl ;
    public final void rule__ElkEdgeSection__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7363:1: ( rule__ElkEdgeSection__Group_2_2__1__Impl )
            // InternalGrana.g:7364:2: rule__ElkEdgeSection__Group_2_2__1__Impl
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
    // InternalGrana.g:7370:1: rule__ElkEdgeSection__Group_2_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7374:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) )
            // InternalGrana.g:7375:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            {
            // InternalGrana.g:7375:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            // InternalGrana.g:7376:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 
            // InternalGrana.g:7377:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            // InternalGrana.g:7377:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1
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


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__0"
    // InternalGrana.g:7391:1: rule__ElkEdgeSection__Group_4_0__0 : rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1 ;
    public final void rule__ElkEdgeSection__Group_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7395:1: ( rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1 )
            // InternalGrana.g:7396:2: rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkEdgeSection__Group_4_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__0__Impl"
    // InternalGrana.g:7403:1: rule__ElkEdgeSection__Group_4_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkEdgeSection__Group_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7407:1: ( ( 'incoming' ) )
            // InternalGrana.g:7408:1: ( 'incoming' )
            {
            // InternalGrana.g:7408:1: ( 'incoming' )
            // InternalGrana.g:7409:1: 'incoming'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__1"
    // InternalGrana.g:7422:1: rule__ElkEdgeSection__Group_4_0__1 : rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2 ;
    public final void rule__ElkEdgeSection__Group_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7426:1: ( rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2 )
            // InternalGrana.g:7427:2: rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdgeSection__Group_4_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__1__Impl"
    // InternalGrana.g:7434:1: rule__ElkEdgeSection__Group_4_0__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7438:1: ( ( ':' ) )
            // InternalGrana.g:7439:1: ( ':' )
            {
            // InternalGrana.g:7439:1: ( ':' )
            // InternalGrana.g:7440:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__2"
    // InternalGrana.g:7453:1: rule__ElkEdgeSection__Group_4_0__2 : rule__ElkEdgeSection__Group_4_0__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7457:1: ( rule__ElkEdgeSection__Group_4_0__2__Impl )
            // InternalGrana.g:7458:2: rule__ElkEdgeSection__Group_4_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__2__Impl"
    // InternalGrana.g:7464:1: rule__ElkEdgeSection__Group_4_0__2__Impl : ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7468:1: ( ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) ) )
            // InternalGrana.g:7469:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) )
            {
            // InternalGrana.g:7469:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) )
            // InternalGrana.g:7470:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_2()); 
            // InternalGrana.g:7471:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 )
            // InternalGrana.g:7471:2: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__0"
    // InternalGrana.g:7487:1: rule__ElkEdgeSection__Group_4_1__0 : rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7491:1: ( rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 )
            // InternalGrana.g:7492:2: rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalGrana.g:7499:1: rule__ElkEdgeSection__Group_4_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7503:1: ( ( 'outgoing' ) )
            // InternalGrana.g:7504:1: ( 'outgoing' )
            {
            // InternalGrana.g:7504:1: ( 'outgoing' )
            // InternalGrana.g:7505:1: 'outgoing'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_1_0()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_1_0()); 

            }


            }

        }
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
    // InternalGrana.g:7518:1: rule__ElkEdgeSection__Group_4_1__1 : rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7522:1: ( rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 )
            // InternalGrana.g:7523:2: rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2
            {
            pushFollow(FOLLOW_8);
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
    // InternalGrana.g:7530:1: rule__ElkEdgeSection__Group_4_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7534:1: ( ( ':' ) )
            // InternalGrana.g:7535:1: ( ':' )
            {
            // InternalGrana.g:7535:1: ( ':' )
            // InternalGrana.g:7536:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:7549:1: rule__ElkEdgeSection__Group_4_1__2 : rule__ElkEdgeSection__Group_4_1__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7553:1: ( rule__ElkEdgeSection__Group_4_1__2__Impl )
            // InternalGrana.g:7554:2: rule__ElkEdgeSection__Group_4_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__2__Impl();

            state._fsp--;


            }

        }
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
    // InternalGrana.g:7560:1: rule__ElkEdgeSection__Group_4_1__2__Impl : ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7564:1: ( ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) ) )
            // InternalGrana.g:7565:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) )
            {
            // InternalGrana.g:7565:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) )
            // InternalGrana.g:7566:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_1_2()); 
            // InternalGrana.g:7567:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 )
            // InternalGrana.g:7567:2: rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_1_2()); 

            }


            }

        }
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


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__0"
    // InternalGrana.g:7583:1: rule__ElkEdgeSection__Group_4_2__0 : rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1 ;
    public final void rule__ElkEdgeSection__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7587:1: ( rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1 )
            // InternalGrana.g:7588:2: rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkEdgeSection__Group_4_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__0__Impl"
    // InternalGrana.g:7595:1: rule__ElkEdgeSection__Group_4_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7599:1: ( ( 'start' ) )
            // InternalGrana.g:7600:1: ( 'start' )
            {
            // InternalGrana.g:7600:1: ( 'start' )
            // InternalGrana.g:7601:1: 'start'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_2_0()); 
            match(input,53,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__1"
    // InternalGrana.g:7614:1: rule__ElkEdgeSection__Group_4_2__1 : rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2 ;
    public final void rule__ElkEdgeSection__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7618:1: ( rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2 )
            // InternalGrana.g:7619:2: rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2
            {
            pushFollow(FOLLOW_39);
            rule__ElkEdgeSection__Group_4_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__1__Impl"
    // InternalGrana.g:7626:1: rule__ElkEdgeSection__Group_4_2__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7630:1: ( ( ':' ) )
            // InternalGrana.g:7631:1: ( ':' )
            {
            // InternalGrana.g:7631:1: ( ':' )
            // InternalGrana.g:7632:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_2_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__2"
    // InternalGrana.g:7645:1: rule__ElkEdgeSection__Group_4_2__2 : rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3 ;
    public final void rule__ElkEdgeSection__Group_4_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7649:1: ( rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3 )
            // InternalGrana.g:7650:2: rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkEdgeSection__Group_4_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__2__Impl"
    // InternalGrana.g:7657:1: rule__ElkEdgeSection__Group_4_2__2__Impl : ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7661:1: ( ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) ) )
            // InternalGrana.g:7662:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) )
            {
            // InternalGrana.g:7662:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) )
            // InternalGrana.g:7663:1: ( rule__ElkEdgeSection__StartXAssignment_4_2_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_2_2()); 
            // InternalGrana.g:7664:1: ( rule__ElkEdgeSection__StartXAssignment_4_2_2 )
            // InternalGrana.g:7664:2: rule__ElkEdgeSection__StartXAssignment_4_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__StartXAssignment_4_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__3"
    // InternalGrana.g:7674:1: rule__ElkEdgeSection__Group_4_2__3 : rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4 ;
    public final void rule__ElkEdgeSection__Group_4_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7678:1: ( rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4 )
            // InternalGrana.g:7679:2: rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4
            {
            pushFollow(FOLLOW_39);
            rule__ElkEdgeSection__Group_4_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__3__Impl"
    // InternalGrana.g:7686:1: rule__ElkEdgeSection__Group_4_2__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7690:1: ( ( ',' ) )
            // InternalGrana.g:7691:1: ( ',' )
            {
            // InternalGrana.g:7691:1: ( ',' )
            // InternalGrana.g:7692:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_2_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__4"
    // InternalGrana.g:7705:1: rule__ElkEdgeSection__Group_4_2__4 : rule__ElkEdgeSection__Group_4_2__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7709:1: ( rule__ElkEdgeSection__Group_4_2__4__Impl )
            // InternalGrana.g:7710:2: rule__ElkEdgeSection__Group_4_2__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__4__Impl"
    // InternalGrana.g:7716:1: rule__ElkEdgeSection__Group_4_2__4__Impl : ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7720:1: ( ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) ) )
            // InternalGrana.g:7721:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) )
            {
            // InternalGrana.g:7721:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) )
            // InternalGrana.g:7722:1: ( rule__ElkEdgeSection__StartYAssignment_4_2_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_2_4()); 
            // InternalGrana.g:7723:1: ( rule__ElkEdgeSection__StartYAssignment_4_2_4 )
            // InternalGrana.g:7723:2: rule__ElkEdgeSection__StartYAssignment_4_2_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__StartYAssignment_4_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_2_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__0"
    // InternalGrana.g:7743:1: rule__ElkEdgeSection__Group_4_3__0 : rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7747:1: ( rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1 )
            // InternalGrana.g:7748:2: rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkEdgeSection__Group_4_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__0__Impl"
    // InternalGrana.g:7755:1: rule__ElkEdgeSection__Group_4_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7759:1: ( ( 'end' ) )
            // InternalGrana.g:7760:1: ( 'end' )
            {
            // InternalGrana.g:7760:1: ( 'end' )
            // InternalGrana.g:7761:1: 'end'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_3_0()); 
            match(input,54,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__1"
    // InternalGrana.g:7774:1: rule__ElkEdgeSection__Group_4_3__1 : rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2 ;
    public final void rule__ElkEdgeSection__Group_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7778:1: ( rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2 )
            // InternalGrana.g:7779:2: rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2
            {
            pushFollow(FOLLOW_39);
            rule__ElkEdgeSection__Group_4_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__1__Impl"
    // InternalGrana.g:7786:1: rule__ElkEdgeSection__Group_4_3__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7790:1: ( ( ':' ) )
            // InternalGrana.g:7791:1: ( ':' )
            {
            // InternalGrana.g:7791:1: ( ':' )
            // InternalGrana.g:7792:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_3_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__2"
    // InternalGrana.g:7805:1: rule__ElkEdgeSection__Group_4_3__2 : rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3 ;
    public final void rule__ElkEdgeSection__Group_4_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7809:1: ( rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3 )
            // InternalGrana.g:7810:2: rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkEdgeSection__Group_4_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__2__Impl"
    // InternalGrana.g:7817:1: rule__ElkEdgeSection__Group_4_3__2__Impl : ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7821:1: ( ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) ) )
            // InternalGrana.g:7822:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) )
            {
            // InternalGrana.g:7822:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) )
            // InternalGrana.g:7823:1: ( rule__ElkEdgeSection__EndXAssignment_4_3_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_3_2()); 
            // InternalGrana.g:7824:1: ( rule__ElkEdgeSection__EndXAssignment_4_3_2 )
            // InternalGrana.g:7824:2: rule__ElkEdgeSection__EndXAssignment_4_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__EndXAssignment_4_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__3"
    // InternalGrana.g:7834:1: rule__ElkEdgeSection__Group_4_3__3 : rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4 ;
    public final void rule__ElkEdgeSection__Group_4_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7838:1: ( rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4 )
            // InternalGrana.g:7839:2: rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4
            {
            pushFollow(FOLLOW_39);
            rule__ElkEdgeSection__Group_4_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__3__Impl"
    // InternalGrana.g:7846:1: rule__ElkEdgeSection__Group_4_3__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7850:1: ( ( ',' ) )
            // InternalGrana.g:7851:1: ( ',' )
            {
            // InternalGrana.g:7851:1: ( ',' )
            // InternalGrana.g:7852:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_3_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__4"
    // InternalGrana.g:7865:1: rule__ElkEdgeSection__Group_4_3__4 : rule__ElkEdgeSection__Group_4_3__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7869:1: ( rule__ElkEdgeSection__Group_4_3__4__Impl )
            // InternalGrana.g:7870:2: rule__ElkEdgeSection__Group_4_3__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__4__Impl"
    // InternalGrana.g:7876:1: rule__ElkEdgeSection__Group_4_3__4__Impl : ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7880:1: ( ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) ) )
            // InternalGrana.g:7881:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) )
            {
            // InternalGrana.g:7881:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) )
            // InternalGrana.g:7882:1: ( rule__ElkEdgeSection__EndYAssignment_4_3_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_3_4()); 
            // InternalGrana.g:7883:1: ( rule__ElkEdgeSection__EndYAssignment_4_3_4 )
            // InternalGrana.g:7883:2: rule__ElkEdgeSection__EndYAssignment_4_3_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__EndYAssignment_4_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__0"
    // InternalGrana.g:7903:1: rule__ElkEdgeSection__Group_4_4__0 : rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1 ;
    public final void rule__ElkEdgeSection__Group_4_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7907:1: ( rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1 )
            // InternalGrana.g:7908:2: rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkEdgeSection__Group_4_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__0__Impl"
    // InternalGrana.g:7915:1: rule__ElkEdgeSection__Group_4_4__0__Impl : ( 'bends' ) ;
    public final void rule__ElkEdgeSection__Group_4_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7919:1: ( ( 'bends' ) )
            // InternalGrana.g:7920:1: ( 'bends' )
            {
            // InternalGrana.g:7920:1: ( 'bends' )
            // InternalGrana.g:7921:1: 'bends'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_4_0()); 
            match(input,55,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__1"
    // InternalGrana.g:7934:1: rule__ElkEdgeSection__Group_4_4__1 : rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2 ;
    public final void rule__ElkEdgeSection__Group_4_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7938:1: ( rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2 )
            // InternalGrana.g:7939:2: rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2
            {
            pushFollow(FOLLOW_39);
            rule__ElkEdgeSection__Group_4_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__1__Impl"
    // InternalGrana.g:7946:1: rule__ElkEdgeSection__Group_4_4__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7950:1: ( ( ':' ) )
            // InternalGrana.g:7951:1: ( ':' )
            {
            // InternalGrana.g:7951:1: ( ':' )
            // InternalGrana.g:7952:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_4_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__2"
    // InternalGrana.g:7965:1: rule__ElkEdgeSection__Group_4_4__2 : rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3 ;
    public final void rule__ElkEdgeSection__Group_4_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7969:1: ( rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3 )
            // InternalGrana.g:7970:2: rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3
            {
            pushFollow(FOLLOW_44);
            rule__ElkEdgeSection__Group_4_4__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__2__Impl"
    // InternalGrana.g:7977:1: rule__ElkEdgeSection__Group_4_4__2__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7981:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) ) )
            // InternalGrana.g:7982:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) )
            {
            // InternalGrana.g:7982:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) )
            // InternalGrana.g:7983:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_2()); 
            // InternalGrana.g:7984:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 )
            // InternalGrana.g:7984:2: rule__ElkEdgeSection__BendPointsAssignment_4_4_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_4_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__3"
    // InternalGrana.g:7994:1: rule__ElkEdgeSection__Group_4_4__3 : rule__ElkEdgeSection__Group_4_4__3__Impl ;
    public final void rule__ElkEdgeSection__Group_4_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7998:1: ( rule__ElkEdgeSection__Group_4_4__3__Impl )
            // InternalGrana.g:7999:2: rule__ElkEdgeSection__Group_4_4__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__3__Impl"
    // InternalGrana.g:8005:1: rule__ElkEdgeSection__Group_4_4__3__Impl : ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_4_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8009:1: ( ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* ) )
            // InternalGrana.g:8010:1: ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* )
            {
            // InternalGrana.g:8010:1: ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* )
            // InternalGrana.g:8011:1: ( rule__ElkEdgeSection__Group_4_4_3__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4_3()); 
            // InternalGrana.g:8012:1: ( rule__ElkEdgeSection__Group_4_4_3__0 )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==56) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // InternalGrana.g:8012:2: rule__ElkEdgeSection__Group_4_4_3__0
            	    {
            	    pushFollow(FOLLOW_45);
            	    rule__ElkEdgeSection__Group_4_4_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__0"
    // InternalGrana.g:8030:1: rule__ElkEdgeSection__Group_4_4_3__0 : rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8034:1: ( rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1 )
            // InternalGrana.g:8035:2: rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1
            {
            pushFollow(FOLLOW_39);
            rule__ElkEdgeSection__Group_4_4_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__0__Impl"
    // InternalGrana.g:8042:1: rule__ElkEdgeSection__Group_4_4_3__0__Impl : ( '|' ) ;
    public final void rule__ElkEdgeSection__Group_4_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8046:1: ( ( '|' ) )
            // InternalGrana.g:8047:1: ( '|' )
            {
            // InternalGrana.g:8047:1: ( '|' )
            // InternalGrana.g:8048:1: '|'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_4_3_0()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__1"
    // InternalGrana.g:8061:1: rule__ElkEdgeSection__Group_4_4_3__1 : rule__ElkEdgeSection__Group_4_4_3__1__Impl ;
    public final void rule__ElkEdgeSection__Group_4_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8065:1: ( rule__ElkEdgeSection__Group_4_4_3__1__Impl )
            // InternalGrana.g:8066:2: rule__ElkEdgeSection__Group_4_4_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__1__Impl"
    // InternalGrana.g:8072:1: rule__ElkEdgeSection__Group_4_4_3__1__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8076:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) ) )
            // InternalGrana.g:8077:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) )
            {
            // InternalGrana.g:8077:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) )
            // InternalGrana.g:8078:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_3_1()); 
            // InternalGrana.g:8079:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 )
            // InternalGrana.g:8079:2: rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__1__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__0"
    // InternalGrana.g:8093:1: rule__ElkBendPoint__Group__0 : rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 ;
    public final void rule__ElkBendPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8097:1: ( rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 )
            // InternalGrana.g:8098:2: rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1
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
    // InternalGrana.g:8105:1: rule__ElkBendPoint__Group__0__Impl : ( ( rule__ElkBendPoint__XAssignment_0 ) ) ;
    public final void rule__ElkBendPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8109:1: ( ( ( rule__ElkBendPoint__XAssignment_0 ) ) )
            // InternalGrana.g:8110:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            {
            // InternalGrana.g:8110:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            // InternalGrana.g:8111:1: ( rule__ElkBendPoint__XAssignment_0 )
            {
             before(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 
            // InternalGrana.g:8112:1: ( rule__ElkBendPoint__XAssignment_0 )
            // InternalGrana.g:8112:2: rule__ElkBendPoint__XAssignment_0
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
    // InternalGrana.g:8122:1: rule__ElkBendPoint__Group__1 : rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 ;
    public final void rule__ElkBendPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8126:1: ( rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 )
            // InternalGrana.g:8127:2: rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2
            {
            pushFollow(FOLLOW_39);
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
    // InternalGrana.g:8134:1: rule__ElkBendPoint__Group__1__Impl : ( ',' ) ;
    public final void rule__ElkBendPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8138:1: ( ( ',' ) )
            // InternalGrana.g:8139:1: ( ',' )
            {
            // InternalGrana.g:8139:1: ( ',' )
            // InternalGrana.g:8140:1: ','
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
    // InternalGrana.g:8153:1: rule__ElkBendPoint__Group__2 : rule__ElkBendPoint__Group__2__Impl ;
    public final void rule__ElkBendPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8157:1: ( rule__ElkBendPoint__Group__2__Impl )
            // InternalGrana.g:8158:2: rule__ElkBendPoint__Group__2__Impl
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
    // InternalGrana.g:8164:1: rule__ElkBendPoint__Group__2__Impl : ( ( rule__ElkBendPoint__YAssignment_2 ) ) ;
    public final void rule__ElkBendPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8168:1: ( ( ( rule__ElkBendPoint__YAssignment_2 ) ) )
            // InternalGrana.g:8169:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            {
            // InternalGrana.g:8169:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            // InternalGrana.g:8170:1: ( rule__ElkBendPoint__YAssignment_2 )
            {
             before(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 
            // InternalGrana.g:8171:1: ( rule__ElkBendPoint__YAssignment_2 )
            // InternalGrana.g:8171:2: rule__ElkBendPoint__YAssignment_2
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


    // $ANTLR start "rule__Property__Group__0"
    // InternalGrana.g:8187:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8191:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalGrana.g:8192:2: rule__Property__Group__0__Impl rule__Property__Group__1
            {
            pushFollow(FOLLOW_35);
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
    // InternalGrana.g:8199:1: rule__Property__Group__0__Impl : ( ( rule__Property__KeyAssignment_0 ) ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8203:1: ( ( ( rule__Property__KeyAssignment_0 ) ) )
            // InternalGrana.g:8204:1: ( ( rule__Property__KeyAssignment_0 ) )
            {
            // InternalGrana.g:8204:1: ( ( rule__Property__KeyAssignment_0 ) )
            // InternalGrana.g:8205:1: ( rule__Property__KeyAssignment_0 )
            {
             before(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 
            // InternalGrana.g:8206:1: ( rule__Property__KeyAssignment_0 )
            // InternalGrana.g:8206:2: rule__Property__KeyAssignment_0
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
    // InternalGrana.g:8216:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8220:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalGrana.g:8221:2: rule__Property__Group__1__Impl rule__Property__Group__2
            {
            pushFollow(FOLLOW_47);
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
    // InternalGrana.g:8228:1: rule__Property__Group__1__Impl : ( ':' ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8232:1: ( ( ':' ) )
            // InternalGrana.g:8233:1: ( ':' )
            {
            // InternalGrana.g:8233:1: ( ':' )
            // InternalGrana.g:8234:1: ':'
            {
             before(grammarAccess.getPropertyAccess().getColonKeyword_1()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:8247:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8251:1: ( rule__Property__Group__2__Impl )
            // InternalGrana.g:8252:2: rule__Property__Group__2__Impl
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
    // InternalGrana.g:8258:1: rule__Property__Group__2__Impl : ( ( rule__Property__Alternatives_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8262:1: ( ( ( rule__Property__Alternatives_2 ) ) )
            // InternalGrana.g:8263:1: ( ( rule__Property__Alternatives_2 ) )
            {
            // InternalGrana.g:8263:1: ( ( rule__Property__Alternatives_2 ) )
            // InternalGrana.g:8264:1: ( rule__Property__Alternatives_2 )
            {
             before(grammarAccess.getPropertyAccess().getAlternatives_2()); 
            // InternalGrana.g:8265:1: ( rule__Property__Alternatives_2 )
            // InternalGrana.g:8265:2: rule__Property__Alternatives_2
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


    // $ANTLR start "rule__QualifiedId__Group__0"
    // InternalGrana.g:8281:1: rule__QualifiedId__Group__0 : rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 ;
    public final void rule__QualifiedId__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8285:1: ( rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 )
            // InternalGrana.g:8286:2: rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1
            {
            pushFollow(FOLLOW_48);
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
    // InternalGrana.g:8293:1: rule__QualifiedId__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8297:1: ( ( RULE_ID ) )
            // InternalGrana.g:8298:1: ( RULE_ID )
            {
            // InternalGrana.g:8298:1: ( RULE_ID )
            // InternalGrana.g:8299:1: RULE_ID
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
    // InternalGrana.g:8310:1: rule__QualifiedId__Group__1 : rule__QualifiedId__Group__1__Impl ;
    public final void rule__QualifiedId__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8314:1: ( rule__QualifiedId__Group__1__Impl )
            // InternalGrana.g:8315:2: rule__QualifiedId__Group__1__Impl
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
    // InternalGrana.g:8321:1: rule__QualifiedId__Group__1__Impl : ( ( rule__QualifiedId__Group_1__0 )* ) ;
    public final void rule__QualifiedId__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8325:1: ( ( ( rule__QualifiedId__Group_1__0 )* ) )
            // InternalGrana.g:8326:1: ( ( rule__QualifiedId__Group_1__0 )* )
            {
            // InternalGrana.g:8326:1: ( ( rule__QualifiedId__Group_1__0 )* )
            // InternalGrana.g:8327:1: ( rule__QualifiedId__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup_1()); 
            // InternalGrana.g:8328:1: ( rule__QualifiedId__Group_1__0 )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==58) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalGrana.g:8328:2: rule__QualifiedId__Group_1__0
            	    {
            	    pushFollow(FOLLOW_49);
            	    rule__QualifiedId__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop66;
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
    // InternalGrana.g:8342:1: rule__QualifiedId__Group_1__0 : rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 ;
    public final void rule__QualifiedId__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8346:1: ( rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 )
            // InternalGrana.g:8347:2: rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1
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
    // InternalGrana.g:8354:1: rule__QualifiedId__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedId__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8358:1: ( ( '.' ) )
            // InternalGrana.g:8359:1: ( '.' )
            {
            // InternalGrana.g:8359:1: ( '.' )
            // InternalGrana.g:8360:1: '.'
            {
             before(grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0()); 
            match(input,58,FOLLOW_2); 
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
    // InternalGrana.g:8373:1: rule__QualifiedId__Group_1__1 : rule__QualifiedId__Group_1__1__Impl ;
    public final void rule__QualifiedId__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8377:1: ( rule__QualifiedId__Group_1__1__Impl )
            // InternalGrana.g:8378:2: rule__QualifiedId__Group_1__1__Impl
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
    // InternalGrana.g:8384:1: rule__QualifiedId__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8388:1: ( ( RULE_ID ) )
            // InternalGrana.g:8389:1: ( RULE_ID )
            {
            // InternalGrana.g:8389:1: ( RULE_ID )
            // InternalGrana.g:8390:1: RULE_ID
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


    // $ANTLR start "rule__PropertyKey__Group__0"
    // InternalGrana.g:8405:1: rule__PropertyKey__Group__0 : rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 ;
    public final void rule__PropertyKey__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8409:1: ( rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 )
            // InternalGrana.g:8410:2: rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1
            {
            pushFollow(FOLLOW_48);
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
    // InternalGrana.g:8417:1: rule__PropertyKey__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8421:1: ( ( RULE_ID ) )
            // InternalGrana.g:8422:1: ( RULE_ID )
            {
            // InternalGrana.g:8422:1: ( RULE_ID )
            // InternalGrana.g:8423:1: RULE_ID
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
    // InternalGrana.g:8434:1: rule__PropertyKey__Group__1 : rule__PropertyKey__Group__1__Impl ;
    public final void rule__PropertyKey__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8438:1: ( rule__PropertyKey__Group__1__Impl )
            // InternalGrana.g:8439:2: rule__PropertyKey__Group__1__Impl
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
    // InternalGrana.g:8445:1: rule__PropertyKey__Group__1__Impl : ( ( rule__PropertyKey__Group_1__0 )* ) ;
    public final void rule__PropertyKey__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8449:1: ( ( ( rule__PropertyKey__Group_1__0 )* ) )
            // InternalGrana.g:8450:1: ( ( rule__PropertyKey__Group_1__0 )* )
            {
            // InternalGrana.g:8450:1: ( ( rule__PropertyKey__Group_1__0 )* )
            // InternalGrana.g:8451:1: ( rule__PropertyKey__Group_1__0 )*
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup_1()); 
            // InternalGrana.g:8452:1: ( rule__PropertyKey__Group_1__0 )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==58) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // InternalGrana.g:8452:2: rule__PropertyKey__Group_1__0
            	    {
            	    pushFollow(FOLLOW_49);
            	    rule__PropertyKey__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop67;
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
    // InternalGrana.g:8466:1: rule__PropertyKey__Group_1__0 : rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 ;
    public final void rule__PropertyKey__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8470:1: ( rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 )
            // InternalGrana.g:8471:2: rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1
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
    // InternalGrana.g:8478:1: rule__PropertyKey__Group_1__0__Impl : ( '.' ) ;
    public final void rule__PropertyKey__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8482:1: ( ( '.' ) )
            // InternalGrana.g:8483:1: ( '.' )
            {
            // InternalGrana.g:8483:1: ( '.' )
            // InternalGrana.g:8484:1: '.'
            {
             before(grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0()); 
            match(input,58,FOLLOW_2); 
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
    // InternalGrana.g:8497:1: rule__PropertyKey__Group_1__1 : rule__PropertyKey__Group_1__1__Impl ;
    public final void rule__PropertyKey__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8501:1: ( rule__PropertyKey__Group_1__1__Impl )
            // InternalGrana.g:8502:2: rule__PropertyKey__Group_1__1__Impl
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
    // InternalGrana.g:8508:1: rule__PropertyKey__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8512:1: ( ( RULE_ID ) )
            // InternalGrana.g:8513:1: ( RULE_ID )
            {
            // InternalGrana.g:8513:1: ( RULE_ID )
            // InternalGrana.g:8514:1: RULE_ID
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
    // InternalGrana.g:8530:1: rule__ShapeLayout__UnorderedGroup_2 : ( rule__ShapeLayout__UnorderedGroup_2__0 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            
        try {
            // InternalGrana.g:8535:1: ( ( rule__ShapeLayout__UnorderedGroup_2__0 )? )
            // InternalGrana.g:8536:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            {
            // InternalGrana.g:8536:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( LA68_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt68=1;
            }
            else if ( LA68_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalGrana.g:8536:2: rule__ShapeLayout__UnorderedGroup_2__0
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
    // InternalGrana.g:8546:1: rule__ShapeLayout__UnorderedGroup_2__Impl : ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) ;
    public final void rule__ShapeLayout__UnorderedGroup_2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:8551:1: ( ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) )
            // InternalGrana.g:8552:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            {
            // InternalGrana.g:8552:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( LA69_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt69=1;
            }
            else if ( LA69_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt69=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // InternalGrana.g:8554:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    {
                    // InternalGrana.g:8554:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    // InternalGrana.g:8555:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
                    }
                    // InternalGrana.g:8555:108: ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    // InternalGrana.g:8556:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8562:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    // InternalGrana.g:8564:7: ( rule__ShapeLayout__Group_2_0__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 
                    // InternalGrana.g:8565:7: ( rule__ShapeLayout__Group_2_0__0 )
                    // InternalGrana.g:8565:8: rule__ShapeLayout__Group_2_0__0
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
                    // InternalGrana.g:8571:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    {
                    // InternalGrana.g:8571:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    // InternalGrana.g:8572:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
                    }
                    // InternalGrana.g:8572:108: ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    // InternalGrana.g:8573:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8579:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    // InternalGrana.g:8581:7: ( rule__ShapeLayout__Group_2_1__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 
                    // InternalGrana.g:8582:7: ( rule__ShapeLayout__Group_2_1__0 )
                    // InternalGrana.g:8582:8: rule__ShapeLayout__Group_2_1__0
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
    // InternalGrana.g:8597:1: rule__ShapeLayout__UnorderedGroup_2__0 : rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8601:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? )
            // InternalGrana.g:8602:2: rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            {
            pushFollow(FOLLOW_50);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;

            // InternalGrana.g:8603:2: ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( LA70_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt70=1;
            }
            else if ( LA70_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalGrana.g:8603:2: rule__ShapeLayout__UnorderedGroup_2__1
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
    // InternalGrana.g:8610:1: rule__ShapeLayout__UnorderedGroup_2__1 : rule__ShapeLayout__UnorderedGroup_2__Impl ;
    public final void rule__ShapeLayout__UnorderedGroup_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8614:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl )
            // InternalGrana.g:8615:2: rule__ShapeLayout__UnorderedGroup_2__Impl
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


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1"
    // InternalGrana.g:8626:1: rule__ElkSingleEdgeSection__UnorderedGroup_1 : ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1());
            
        try {
            // InternalGrana.g:8631:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )? )
            // InternalGrana.g:8632:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )?
            {
            // InternalGrana.g:8632:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( LA71_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt71=1;
            }
            else if ( LA71_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt71=1;
            }
            else if ( LA71_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt71=1;
            }
            else if ( LA71_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt71=1;
            }
            else if ( LA71_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // InternalGrana.g:8632:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__0();

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

            	getUnorderedGroupHelper().leave(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl"
    // InternalGrana.g:8642:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl : ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) ) ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:8647:1: ( ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) ) )
            // InternalGrana.g:8648:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) )
            {
            // InternalGrana.g:8648:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) )
            int alt72=5;
            int LA72_0 = input.LA(1);

            if ( LA72_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt72=1;
            }
            else if ( LA72_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt72=2;
            }
            else if ( LA72_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt72=3;
            }
            else if ( LA72_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt72=4;
            }
            else if ( LA72_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt72=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // InternalGrana.g:8650:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) )
                    {
                    // InternalGrana.g:8650:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) )
                    // InternalGrana.g:8651:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0)");
                    }
                    // InternalGrana.g:8651:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) )
                    // InternalGrana.g:8652:6: ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8658:6: ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) )
                    // InternalGrana.g:8660:7: ( rule__ElkSingleEdgeSection__Group_1_0__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0()); 
                    // InternalGrana.g:8661:7: ( rule__ElkSingleEdgeSection__Group_1_0__0 )
                    // InternalGrana.g:8661:8: rule__ElkSingleEdgeSection__Group_1_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:8667:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) )
                    {
                    // InternalGrana.g:8667:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) )
                    // InternalGrana.g:8668:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1)");
                    }
                    // InternalGrana.g:8668:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) )
                    // InternalGrana.g:8669:6: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8675:6: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) )
                    // InternalGrana.g:8677:7: ( rule__ElkSingleEdgeSection__Group_1_1__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 
                    // InternalGrana.g:8678:7: ( rule__ElkSingleEdgeSection__Group_1_1__0 )
                    // InternalGrana.g:8678:8: rule__ElkSingleEdgeSection__Group_1_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:8684:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) )
                    {
                    // InternalGrana.g:8684:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) )
                    // InternalGrana.g:8685:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2)");
                    }
                    // InternalGrana.g:8685:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) )
                    // InternalGrana.g:8686:6: ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8692:6: ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) )
                    // InternalGrana.g:8694:7: ( rule__ElkSingleEdgeSection__Group_1_2__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_2()); 
                    // InternalGrana.g:8695:7: ( rule__ElkSingleEdgeSection__Group_1_2__0 )
                    // InternalGrana.g:8695:8: rule__ElkSingleEdgeSection__Group_1_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:8701:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) )
                    {
                    // InternalGrana.g:8701:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) )
                    // InternalGrana.g:8702:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3)");
                    }
                    // InternalGrana.g:8702:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) )
                    // InternalGrana.g:8703:6: ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8709:6: ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) )
                    // InternalGrana.g:8711:7: ( rule__ElkSingleEdgeSection__Group_1_3__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_3()); 
                    // InternalGrana.g:8712:7: ( rule__ElkSingleEdgeSection__Group_1_3__0 )
                    // InternalGrana.g:8712:8: rule__ElkSingleEdgeSection__Group_1_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalGrana.g:8718:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) )
                    {
                    // InternalGrana.g:8718:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) )
                    // InternalGrana.g:8719:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4)");
                    }
                    // InternalGrana.g:8719:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) )
                    // InternalGrana.g:8720:6: ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8726:6: ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) )
                    // InternalGrana.g:8728:7: ( rule__ElkSingleEdgeSection__Group_1_4__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4()); 
                    // InternalGrana.g:8729:7: ( rule__ElkSingleEdgeSection__Group_1_4__0 )
                    // InternalGrana.g:8729:8: rule__ElkSingleEdgeSection__Group_1_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4()); 

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
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__0"
    // InternalGrana.g:8744:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__0 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8748:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )? )
            // InternalGrana.g:8749:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalGrana.g:8750:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( LA73_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt73=1;
            }
            else if ( LA73_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt73=1;
            }
            else if ( LA73_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt73=1;
            }
            else if ( LA73_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt73=1;
            }
            else if ( LA73_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // InternalGrana.g:8750:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__1"
    // InternalGrana.g:8757:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__1 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8761:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )? )
            // InternalGrana.g:8762:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalGrana.g:8763:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( LA74_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt74=1;
            }
            else if ( LA74_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt74=1;
            }
            else if ( LA74_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt74=1;
            }
            else if ( LA74_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt74=1;
            }
            else if ( LA74_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalGrana.g:8763:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__2"
    // InternalGrana.g:8770:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__2 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8774:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )? )
            // InternalGrana.g:8775:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalGrana.g:8776:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( LA75_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt75=1;
            }
            else if ( LA75_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt75=1;
            }
            else if ( LA75_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt75=1;
            }
            else if ( LA75_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt75=1;
            }
            else if ( LA75_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalGrana.g:8776:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__3();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__3"
    // InternalGrana.g:8783:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__3 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8787:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )? )
            // InternalGrana.g:8788:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalGrana.g:8789:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( LA76_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt76=1;
            }
            else if ( LA76_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt76=1;
            }
            else if ( LA76_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt76=1;
            }
            else if ( LA76_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt76=1;
            }
            else if ( LA76_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalGrana.g:8789:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__4();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__4"
    // InternalGrana.g:8796:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__4 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8800:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl )
            // InternalGrana.g:8801:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__4"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4"
    // InternalGrana.g:8818:1: rule__ElkEdgeSection__UnorderedGroup_4 : ( rule__ElkEdgeSection__UnorderedGroup_4__0 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4());
            
        try {
            // InternalGrana.g:8823:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4__0 )? )
            // InternalGrana.g:8824:2: ( rule__ElkEdgeSection__UnorderedGroup_4__0 )?
            {
            // InternalGrana.g:8824:2: ( rule__ElkEdgeSection__UnorderedGroup_4__0 )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( LA77_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt77=1;
            }
            else if ( LA77_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt77=1;
            }
            else if ( LA77_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt77=1;
            }
            else if ( LA77_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt77=1;
            }
            else if ( LA77_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // InternalGrana.g:8824:2: rule__ElkEdgeSection__UnorderedGroup_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__0();

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

            	getUnorderedGroupHelper().leave(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__Impl"
    // InternalGrana.g:8834:1: rule__ElkEdgeSection__UnorderedGroup_4__Impl : ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) ) ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:8839:1: ( ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) ) )
            // InternalGrana.g:8840:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) )
            {
            // InternalGrana.g:8840:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) )
            int alt78=5;
            int LA78_0 = input.LA(1);

            if ( LA78_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt78=1;
            }
            else if ( LA78_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt78=2;
            }
            else if ( LA78_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt78=3;
            }
            else if ( LA78_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt78=4;
            }
            else if ( LA78_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt78=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }
            switch (alt78) {
                case 1 :
                    // InternalGrana.g:8842:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) )
                    {
                    // InternalGrana.g:8842:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) )
                    // InternalGrana.g:8843:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0)");
                    }
                    // InternalGrana.g:8843:111: ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) )
                    // InternalGrana.g:8844:6: ( ( rule__ElkEdgeSection__Group_4_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8850:6: ( ( rule__ElkEdgeSection__Group_4_0__0 ) )
                    // InternalGrana.g:8852:7: ( rule__ElkEdgeSection__Group_4_0__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0()); 
                    // InternalGrana.g:8853:7: ( rule__ElkEdgeSection__Group_4_0__0 )
                    // InternalGrana.g:8853:8: rule__ElkEdgeSection__Group_4_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:8859:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) )
                    {
                    // InternalGrana.g:8859:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) )
                    // InternalGrana.g:8860:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1)");
                    }
                    // InternalGrana.g:8860:111: ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) )
                    // InternalGrana.g:8861:6: ( ( rule__ElkEdgeSection__Group_4_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8867:6: ( ( rule__ElkEdgeSection__Group_4_1__0 ) )
                    // InternalGrana.g:8869:7: ( rule__ElkEdgeSection__Group_4_1__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 
                    // InternalGrana.g:8870:7: ( rule__ElkEdgeSection__Group_4_1__0 )
                    // InternalGrana.g:8870:8: rule__ElkEdgeSection__Group_4_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:8876:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) )
                    {
                    // InternalGrana.g:8876:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) )
                    // InternalGrana.g:8877:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2)");
                    }
                    // InternalGrana.g:8877:111: ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) )
                    // InternalGrana.g:8878:6: ( ( rule__ElkEdgeSection__Group_4_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8884:6: ( ( rule__ElkEdgeSection__Group_4_2__0 ) )
                    // InternalGrana.g:8886:7: ( rule__ElkEdgeSection__Group_4_2__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_2()); 
                    // InternalGrana.g:8887:7: ( rule__ElkEdgeSection__Group_4_2__0 )
                    // InternalGrana.g:8887:8: rule__ElkEdgeSection__Group_4_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:8893:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) )
                    {
                    // InternalGrana.g:8893:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) )
                    // InternalGrana.g:8894:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3)");
                    }
                    // InternalGrana.g:8894:111: ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) )
                    // InternalGrana.g:8895:6: ( ( rule__ElkEdgeSection__Group_4_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8901:6: ( ( rule__ElkEdgeSection__Group_4_3__0 ) )
                    // InternalGrana.g:8903:7: ( rule__ElkEdgeSection__Group_4_3__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_3()); 
                    // InternalGrana.g:8904:7: ( rule__ElkEdgeSection__Group_4_3__0 )
                    // InternalGrana.g:8904:8: rule__ElkEdgeSection__Group_4_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalGrana.g:8910:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) )
                    {
                    // InternalGrana.g:8910:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) )
                    // InternalGrana.g:8911:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4)");
                    }
                    // InternalGrana.g:8911:111: ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) )
                    // InternalGrana.g:8912:6: ( ( rule__ElkEdgeSection__Group_4_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:8918:6: ( ( rule__ElkEdgeSection__Group_4_4__0 ) )
                    // InternalGrana.g:8920:7: ( rule__ElkEdgeSection__Group_4_4__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4()); 
                    // InternalGrana.g:8921:7: ( rule__ElkEdgeSection__Group_4_4__0 )
                    // InternalGrana.g:8921:8: rule__ElkEdgeSection__Group_4_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4()); 

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
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__0"
    // InternalGrana.g:8936:1: rule__ElkEdgeSection__UnorderedGroup_4__0 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8940:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )? )
            // InternalGrana.g:8941:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalGrana.g:8942:2: ( rule__ElkEdgeSection__UnorderedGroup_4__1 )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( LA79_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt79=1;
            }
            else if ( LA79_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt79=1;
            }
            else if ( LA79_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt79=1;
            }
            else if ( LA79_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt79=1;
            }
            else if ( LA79_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // InternalGrana.g:8942:2: rule__ElkEdgeSection__UnorderedGroup_4__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__1();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__0"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__1"
    // InternalGrana.g:8949:1: rule__ElkEdgeSection__UnorderedGroup_4__1 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8953:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )? )
            // InternalGrana.g:8954:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalGrana.g:8955:2: ( rule__ElkEdgeSection__UnorderedGroup_4__2 )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( LA80_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt80=1;
            }
            else if ( LA80_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt80=1;
            }
            else if ( LA80_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt80=1;
            }
            else if ( LA80_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt80=1;
            }
            else if ( LA80_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // InternalGrana.g:8955:2: rule__ElkEdgeSection__UnorderedGroup_4__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__2();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__1"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__2"
    // InternalGrana.g:8962:1: rule__ElkEdgeSection__UnorderedGroup_4__2 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8966:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )? )
            // InternalGrana.g:8967:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalGrana.g:8968:2: ( rule__ElkEdgeSection__UnorderedGroup_4__3 )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( LA81_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt81=1;
            }
            else if ( LA81_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt81=1;
            }
            else if ( LA81_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt81=1;
            }
            else if ( LA81_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt81=1;
            }
            else if ( LA81_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // InternalGrana.g:8968:2: rule__ElkEdgeSection__UnorderedGroup_4__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__3();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__2"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__3"
    // InternalGrana.g:8975:1: rule__ElkEdgeSection__UnorderedGroup_4__3 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8979:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )? )
            // InternalGrana.g:8980:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )?
            {
            pushFollow(FOLLOW_51);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalGrana.g:8981:2: ( rule__ElkEdgeSection__UnorderedGroup_4__4 )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( LA82_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt82=1;
            }
            else if ( LA82_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt82=1;
            }
            else if ( LA82_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt82=1;
            }
            else if ( LA82_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt82=1;
            }
            else if ( LA82_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalGrana.g:8981:2: rule__ElkEdgeSection__UnorderedGroup_4__4
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__4();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__3"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__4"
    // InternalGrana.g:8988:1: rule__ElkEdgeSection__UnorderedGroup_4__4 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8992:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl )
            // InternalGrana.g:8993:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__4"


    // $ANTLR start "rule__Grana__GlobalResourcesAssignment_0_1"
    // InternalGrana.g:9010:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9014:1: ( ( ruleGlobalResourceRef ) )
            // InternalGrana.g:9015:1: ( ruleGlobalResourceRef )
            {
            // InternalGrana.g:9015:1: ( ruleGlobalResourceRef )
            // InternalGrana.g:9016:1: ruleGlobalResourceRef
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
    // InternalGrana.g:9025:1: rule__Grana__GloobalOutputsAssignment_1_1 : ( ruleGlobalOutputRef ) ;
    public final void rule__Grana__GloobalOutputsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9029:1: ( ( ruleGlobalOutputRef ) )
            // InternalGrana.g:9030:1: ( ruleGlobalOutputRef )
            {
            // InternalGrana.g:9030:1: ( ruleGlobalOutputRef )
            // InternalGrana.g:9031:1: ruleGlobalOutputRef
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
    // InternalGrana.g:9040:1: rule__Grana__ParallelAssignment_2_1 : ( ( 'parallel' ) ) ;
    public final void rule__Grana__ParallelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9044:1: ( ( ( 'parallel' ) ) )
            // InternalGrana.g:9045:1: ( ( 'parallel' ) )
            {
            // InternalGrana.g:9045:1: ( ( 'parallel' ) )
            // InternalGrana.g:9046:1: ( 'parallel' )
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            // InternalGrana.g:9047:1: ( 'parallel' )
            // InternalGrana.g:9048:1: 'parallel'
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            match(input,59,FOLLOW_2); 
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
    // InternalGrana.g:9063:1: rule__Grana__ExecuteAllAssignment_2_2_0 : ( ( 'all' ) ) ;
    public final void rule__Grana__ExecuteAllAssignment_2_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9067:1: ( ( ( 'all' ) ) )
            // InternalGrana.g:9068:1: ( ( 'all' ) )
            {
            // InternalGrana.g:9068:1: ( ( 'all' ) )
            // InternalGrana.g:9069:1: ( 'all' )
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            // InternalGrana.g:9070:1: ( 'all' )
            // InternalGrana.g:9071:1: 'all'
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            match(input,60,FOLLOW_2); 
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
    // InternalGrana.g:9086:1: rule__Grana__ExecuteAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__Grana__ExecuteAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9090:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:9091:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:9091:1: ( ( RULE_ID ) )
            // InternalGrana.g:9092:1: ( RULE_ID )
            {
             before(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_2_1_0()); 
            // InternalGrana.g:9093:1: ( RULE_ID )
            // InternalGrana.g:9094:1: RULE_ID
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
    // InternalGrana.g:9105:1: rule__Grana__JobsAssignment_3 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9109:1: ( ( ruleJob ) )
            // InternalGrana.g:9110:1: ( ruleJob )
            {
            // InternalGrana.g:9110:1: ( ruleJob )
            // InternalGrana.g:9111:1: ruleJob
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
    // InternalGrana.g:9120:1: rule__RegularJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RegularJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9124:1: ( ( RULE_ID ) )
            // InternalGrana.g:9125:1: ( RULE_ID )
            {
            // InternalGrana.g:9125:1: ( RULE_ID )
            // InternalGrana.g:9126:1: RULE_ID
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
    // InternalGrana.g:9135:1: rule__RegularJob__LayoutBeforeAnalysisAssignment_2 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__RegularJob__LayoutBeforeAnalysisAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9139:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // InternalGrana.g:9140:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // InternalGrana.g:9140:1: ( ( 'layoutBeforeAnalysis' ) )
            // InternalGrana.g:9141:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            // InternalGrana.g:9142:1: ( 'layoutBeforeAnalysis' )
            // InternalGrana.g:9143:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            match(input,61,FOLLOW_2); 
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
    // InternalGrana.g:9158:1: rule__RegularJob__MeasureExecutionTimeAssignment_3 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RegularJob__MeasureExecutionTimeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9162:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:9163:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:9163:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:9164:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            // InternalGrana.g:9165:1: ( 'measureExecutionTime' )
            // InternalGrana.g:9166:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            match(input,62,FOLLOW_2); 
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
    // InternalGrana.g:9181:1: rule__RegularJob__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__RegularJob__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9185:1: ( ( ruleResource ) )
            // InternalGrana.g:9186:1: ( ruleResource )
            {
            // InternalGrana.g:9186:1: ( ruleResource )
            // InternalGrana.g:9187:1: ruleResource
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
    // InternalGrana.g:9196:1: rule__RegularJob__LayoutOptionsAssignment_7 : ( ruleLayoutConfig ) ;
    public final void rule__RegularJob__LayoutOptionsAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9200:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9201:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9201:1: ( ruleLayoutConfig )
            // InternalGrana.g:9202:1: ruleLayoutConfig
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
    // InternalGrana.g:9211:1: rule__RegularJob__AnalysesAssignment_9 : ( ruleAnalysis ) ;
    public final void rule__RegularJob__AnalysesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9215:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9216:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9216:1: ( ruleAnalysis )
            // InternalGrana.g:9217:1: ruleAnalysis
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
    // InternalGrana.g:9226:1: rule__RegularJob__OutputTypeAssignment_11 : ( ruleOutputType ) ;
    public final void rule__RegularJob__OutputTypeAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9230:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9231:1: ( ruleOutputType )
            {
            // InternalGrana.g:9231:1: ( ruleOutputType )
            // InternalGrana.g:9232:1: ruleOutputType
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
    // InternalGrana.g:9241:1: rule__RegularJob__OutputAssignment_12 : ( ruleOutput ) ;
    public final void rule__RegularJob__OutputAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9245:1: ( ( ruleOutput ) )
            // InternalGrana.g:9246:1: ( ruleOutput )
            {
            // InternalGrana.g:9246:1: ( ruleOutput )
            // InternalGrana.g:9247:1: ruleOutput
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
    // InternalGrana.g:9256:1: rule__CompareJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__CompareJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9260:1: ( ( RULE_ID ) )
            // InternalGrana.g:9261:1: ( RULE_ID )
            {
            // InternalGrana.g:9261:1: ( RULE_ID )
            // InternalGrana.g:9262:1: RULE_ID
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
    // InternalGrana.g:9271:1: rule__CompareJob__ResourcesAssignment_3 : ( ruleResource ) ;
    public final void rule__CompareJob__ResourcesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9275:1: ( ( ruleResource ) )
            // InternalGrana.g:9276:1: ( ruleResource )
            {
            // InternalGrana.g:9276:1: ( ruleResource )
            // InternalGrana.g:9277:1: ruleResource
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
    // InternalGrana.g:9286:1: rule__CompareJob__LayoutOptionsAssignment_5 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9290:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9291:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9291:1: ( ruleLayoutConfig )
            // InternalGrana.g:9292:1: ruleLayoutConfig
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
    // InternalGrana.g:9301:1: rule__CompareJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9305:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9306:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9306:1: ( ruleLayoutConfig )
            // InternalGrana.g:9307:1: ruleLayoutConfig
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
    // InternalGrana.g:9316:1: rule__CompareJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__CompareJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9320:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9321:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9321:1: ( ruleAnalysis )
            // InternalGrana.g:9322:1: ruleAnalysis
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
    // InternalGrana.g:9331:1: rule__CompareJob__OutputTypeAssignment_10 : ( ruleOutputType ) ;
    public final void rule__CompareJob__OutputTypeAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9335:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9336:1: ( ruleOutputType )
            {
            // InternalGrana.g:9336:1: ( ruleOutputType )
            // InternalGrana.g:9337:1: ruleOutputType
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
    // InternalGrana.g:9346:1: rule__CompareJob__OutputAssignment_11 : ( ruleOutput ) ;
    public final void rule__CompareJob__OutputAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9350:1: ( ( ruleOutput ) )
            // InternalGrana.g:9351:1: ( ruleOutput )
            {
            // InternalGrana.g:9351:1: ( ruleOutput )
            // InternalGrana.g:9352:1: ruleOutput
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
    // InternalGrana.g:9361:1: rule__RangeJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RangeJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9365:1: ( ( RULE_ID ) )
            // InternalGrana.g:9366:1: ( RULE_ID )
            {
            // InternalGrana.g:9366:1: ( RULE_ID )
            // InternalGrana.g:9367:1: RULE_ID
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
    // InternalGrana.g:9376:1: rule__RangeJob__MeasureExecutionTimeAssignment_2 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RangeJob__MeasureExecutionTimeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9380:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:9381:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:9381:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:9382:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            // InternalGrana.g:9383:1: ( 'measureExecutionTime' )
            // InternalGrana.g:9384:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            match(input,62,FOLLOW_2); 
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
    // InternalGrana.g:9399:1: rule__RangeJob__ResourcesAssignment_4 : ( ruleResource ) ;
    public final void rule__RangeJob__ResourcesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9403:1: ( ( ruleResource ) )
            // InternalGrana.g:9404:1: ( ruleResource )
            {
            // InternalGrana.g:9404:1: ( ruleResource )
            // InternalGrana.g:9405:1: ruleResource
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
    // InternalGrana.g:9414:1: rule__RangeJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__RangeJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9418:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9419:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9419:1: ( ruleLayoutConfig )
            // InternalGrana.g:9420:1: ruleLayoutConfig
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
    // InternalGrana.g:9429:1: rule__RangeJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9433:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9434:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9434:1: ( ruleAnalysis )
            // InternalGrana.g:9435:1: ruleAnalysis
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
    // InternalGrana.g:9444:1: rule__RangeJob__RangeOptionAssignment_10 : ( ruleQualifiedId ) ;
    public final void rule__RangeJob__RangeOptionAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9448:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9449:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:9449:1: ( ruleQualifiedId )
            // InternalGrana.g:9450:1: ruleQualifiedId
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
    // InternalGrana.g:9459:1: rule__RangeJob__RangeValuesAssignment_11 : ( ruleRange ) ;
    public final void rule__RangeJob__RangeValuesAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9463:1: ( ( ruleRange ) )
            // InternalGrana.g:9464:1: ( ruleRange )
            {
            // InternalGrana.g:9464:1: ( ruleRange )
            // InternalGrana.g:9465:1: ruleRange
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
    // InternalGrana.g:9474:1: rule__RangeJob__RangeAnalysisAssignment_12_0_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysisAssignment_12_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9478:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9479:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9479:1: ( ruleAnalysis )
            // InternalGrana.g:9480:1: ruleAnalysis
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
    // InternalGrana.g:9489:1: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9493:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:9494:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:9494:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:9495:1: RULE_SIGNED_INT
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
    // InternalGrana.g:9504:1: rule__RangeJob__RangeAnalysesAssignment_12_1_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysesAssignment_12_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9508:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9509:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9509:1: ( ruleAnalysis )
            // InternalGrana.g:9510:1: ruleAnalysis
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
    // InternalGrana.g:9519:1: rule__RangeJob__OutputTypeAssignment_14 : ( ruleOutputType ) ;
    public final void rule__RangeJob__OutputTypeAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9523:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9524:1: ( ruleOutputType )
            {
            // InternalGrana.g:9524:1: ( ruleOutputType )
            // InternalGrana.g:9525:1: ruleOutputType
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
    // InternalGrana.g:9534:1: rule__RangeJob__OutputAssignment_15 : ( ruleOutput ) ;
    public final void rule__RangeJob__OutputAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9538:1: ( ( ruleOutput ) )
            // InternalGrana.g:9539:1: ( ruleOutput )
            {
            // InternalGrana.g:9539:1: ( ruleOutput )
            // InternalGrana.g:9540:1: ruleOutput
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
    // InternalGrana.g:9549:1: rule__FloatRange__ValuesAssignment_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9553:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:9554:1: ( RULE_FLOAT )
            {
            // InternalGrana.g:9554:1: ( RULE_FLOAT )
            // InternalGrana.g:9555:1: RULE_FLOAT
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
    // InternalGrana.g:9564:1: rule__FloatRange__ValuesAssignment_2_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9568:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:9569:1: ( RULE_FLOAT )
            {
            // InternalGrana.g:9569:1: ( RULE_FLOAT )
            // InternalGrana.g:9570:1: RULE_FLOAT
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
    // InternalGrana.g:9579:1: rule__IntRangeValues__ValuesAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9583:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:9584:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:9584:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:9585:1: RULE_SIGNED_INT
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
    // InternalGrana.g:9594:1: rule__IntRangeValues__ValuesAssignment_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9598:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:9599:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:9599:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:9600:1: RULE_SIGNED_INT
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
    // InternalGrana.g:9609:1: rule__IntRangeRange__StartAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__StartAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9613:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:9614:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:9614:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:9615:1: RULE_SIGNED_INT
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
    // InternalGrana.g:9624:1: rule__IntRangeRange__EndAssignment_3 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__EndAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9628:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:9629:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:9629:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:9630:1: RULE_SIGNED_INT
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


    // $ANTLR start "rule__ResourceReference__ResourceRefsAssignment_1"
    // InternalGrana.g:9639:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9643:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:9644:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:9644:1: ( ( RULE_ID ) )
            // InternalGrana.g:9645:1: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // InternalGrana.g:9646:1: ( RULE_ID )
            // InternalGrana.g:9647:1: RULE_ID
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
    // InternalGrana.g:9658:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9662:1: ( ( RULE_ID ) )
            // InternalGrana.g:9663:1: ( RULE_ID )
            {
            // InternalGrana.g:9663:1: ( RULE_ID )
            // InternalGrana.g:9664:1: RULE_ID
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
    // InternalGrana.g:9673:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9677:1: ( ( ruleLocalResource ) )
            // InternalGrana.g:9678:1: ( ruleLocalResource )
            {
            // InternalGrana.g:9678:1: ( ruleLocalResource )
            // InternalGrana.g:9679:1: ruleLocalResource
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
    // InternalGrana.g:9688:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9692:1: ( ( RULE_STRING ) )
            // InternalGrana.g:9693:1: ( RULE_STRING )
            {
            // InternalGrana.g:9693:1: ( RULE_STRING )
            // InternalGrana.g:9694:1: RULE_STRING
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
    // InternalGrana.g:9703:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9707:1: ( ( RULE_STRING ) )
            // InternalGrana.g:9708:1: ( RULE_STRING )
            {
            // InternalGrana.g:9708:1: ( RULE_STRING )
            // InternalGrana.g:9709:1: RULE_STRING
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


    // $ANTLR start "rule__GlobalOutputRef__NameAssignment_0"
    // InternalGrana.g:9718:1: rule__GlobalOutputRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalOutputRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9722:1: ( ( RULE_ID ) )
            // InternalGrana.g:9723:1: ( RULE_ID )
            {
            // InternalGrana.g:9723:1: ( RULE_ID )
            // InternalGrana.g:9724:1: RULE_ID
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
    // InternalGrana.g:9733:1: rule__GlobalOutputRef__OutputAssignment_1 : ( ruleLocalOutput ) ;
    public final void rule__GlobalOutputRef__OutputAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9737:1: ( ( ruleLocalOutput ) )
            // InternalGrana.g:9738:1: ( ruleLocalOutput )
            {
            // InternalGrana.g:9738:1: ( ruleLocalOutput )
            // InternalGrana.g:9739:1: ruleLocalOutput
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
    // InternalGrana.g:9748:1: rule__OutputReference__OutputRefAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__OutputReference__OutputRefAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9752:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:9753:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:9753:1: ( ( RULE_ID ) )
            // InternalGrana.g:9754:1: ( RULE_ID )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            // InternalGrana.g:9755:1: ( RULE_ID )
            // InternalGrana.g:9756:1: RULE_ID
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
    // InternalGrana.g:9767:1: rule__LocalOutput__PathAssignment : ( RULE_STRING ) ;
    public final void rule__LocalOutput__PathAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9771:1: ( ( RULE_STRING ) )
            // InternalGrana.g:9772:1: ( RULE_STRING )
            {
            // InternalGrana.g:9772:1: ( RULE_STRING )
            // InternalGrana.g:9773:1: RULE_STRING
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
    // InternalGrana.g:9782:1: rule__Analysis__NameAssignment : ( ruleQualifiedId ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9786:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9787:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:9787:1: ( ruleQualifiedId )
            // InternalGrana.g:9788:1: ruleQualifiedId
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
    // InternalGrana.g:9797:1: rule__LayoutConfig__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__LayoutConfig__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9801:1: ( ( RULE_ID ) )
            // InternalGrana.g:9802:1: ( RULE_ID )
            {
            // InternalGrana.g:9802:1: ( RULE_ID )
            // InternalGrana.g:9803:1: RULE_ID
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
    // InternalGrana.g:9812:1: rule__LayoutConfig__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__LayoutConfig__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9816:1: ( ( ruleProperty ) )
            // InternalGrana.g:9817:1: ( ruleProperty )
            {
            // InternalGrana.g:9817:1: ( ruleProperty )
            // InternalGrana.g:9818:1: ruleProperty
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
    // InternalGrana.g:9833:1: rule__ElkNode__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkNode__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9837:1: ( ( RULE_ID ) )
            // InternalGrana.g:9838:1: ( RULE_ID )
            {
            // InternalGrana.g:9838:1: ( RULE_ID )
            // InternalGrana.g:9839:1: RULE_ID
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
    // InternalGrana.g:9848:1: rule__ElkNode__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkNode__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9852:1: ( ( ruleProperty ) )
            // InternalGrana.g:9853:1: ( ruleProperty )
            {
            // InternalGrana.g:9853:1: ( ruleProperty )
            // InternalGrana.g:9854:1: ruleProperty
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


    // $ANTLR start "rule__ElkNode__ChildrenAssignment_2_3_0"
    // InternalGrana.g:9863:1: rule__ElkNode__ChildrenAssignment_2_3_0 : ( ruleElkNode ) ;
    public final void rule__ElkNode__ChildrenAssignment_2_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9867:1: ( ( ruleElkNode ) )
            // InternalGrana.g:9868:1: ( ruleElkNode )
            {
            // InternalGrana.g:9868:1: ( ruleElkNode )
            // InternalGrana.g:9869:1: ruleElkNode
            {
             before(grammarAccess.getElkNodeAccess().getChildrenElkNodeParserRuleCall_2_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleElkNode();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getChildrenElkNodeParserRuleCall_2_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__ChildrenAssignment_2_3_0"


    // $ANTLR start "rule__ElkNode__ContainedEdgesAssignment_2_3_1"
    // InternalGrana.g:9878:1: rule__ElkNode__ContainedEdgesAssignment_2_3_1 : ( ruleElkEdge ) ;
    public final void rule__ElkNode__ContainedEdgesAssignment_2_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9882:1: ( ( ruleElkEdge ) )
            // InternalGrana.g:9883:1: ( ruleElkEdge )
            {
            // InternalGrana.g:9883:1: ( ruleElkEdge )
            // InternalGrana.g:9884:1: ruleElkEdge
            {
             before(grammarAccess.getElkNodeAccess().getContainedEdgesElkEdgeParserRuleCall_2_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleElkEdge();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getContainedEdgesElkEdgeParserRuleCall_2_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__ContainedEdgesAssignment_2_3_1"


    // $ANTLR start "rule__ElkNode__PortsAssignment_2_3_2"
    // InternalGrana.g:9893:1: rule__ElkNode__PortsAssignment_2_3_2 : ( ruleElkPort ) ;
    public final void rule__ElkNode__PortsAssignment_2_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9897:1: ( ( ruleElkPort ) )
            // InternalGrana.g:9898:1: ( ruleElkPort )
            {
            // InternalGrana.g:9898:1: ( ruleElkPort )
            // InternalGrana.g:9899:1: ruleElkPort
            {
             before(grammarAccess.getElkNodeAccess().getPortsElkPortParserRuleCall_2_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleElkPort();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getPortsElkPortParserRuleCall_2_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__PortsAssignment_2_3_2"


    // $ANTLR start "rule__ElkNode__LabelsAssignment_2_3_3"
    // InternalGrana.g:9908:1: rule__ElkNode__LabelsAssignment_2_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkNode__LabelsAssignment_2_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9912:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:9913:1: ( ruleElkLabel )
            {
            // InternalGrana.g:9913:1: ( ruleElkLabel )
            // InternalGrana.g:9914:1: ruleElkLabel
            {
             before(grammarAccess.getElkNodeAccess().getLabelsElkLabelParserRuleCall_2_3_3_0()); 
            pushFollow(FOLLOW_2);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getLabelsElkLabelParserRuleCall_2_3_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__LabelsAssignment_2_3_3"


    // $ANTLR start "rule__ElkLabel__IdentifierAssignment_1_0"
    // InternalGrana.g:9923:1: rule__ElkLabel__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkLabel__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9927:1: ( ( RULE_ID ) )
            // InternalGrana.g:9928:1: ( RULE_ID )
            {
            // InternalGrana.g:9928:1: ( RULE_ID )
            // InternalGrana.g:9929:1: RULE_ID
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
    // InternalGrana.g:9938:1: rule__ElkLabel__TextAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ElkLabel__TextAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9942:1: ( ( RULE_STRING ) )
            // InternalGrana.g:9943:1: ( RULE_STRING )
            {
            // InternalGrana.g:9943:1: ( RULE_STRING )
            // InternalGrana.g:9944:1: RULE_STRING
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
    // InternalGrana.g:9953:1: rule__ElkLabel__PropertiesAssignment_3_2 : ( ruleProperty ) ;
    public final void rule__ElkLabel__PropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9957:1: ( ( ruleProperty ) )
            // InternalGrana.g:9958:1: ( ruleProperty )
            {
            // InternalGrana.g:9958:1: ( ruleProperty )
            // InternalGrana.g:9959:1: ruleProperty
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
    // InternalGrana.g:9968:1: rule__ElkLabel__LabelsAssignment_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkLabel__LabelsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9972:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:9973:1: ( ruleElkLabel )
            {
            // InternalGrana.g:9973:1: ( ruleElkLabel )
            // InternalGrana.g:9974:1: ruleElkLabel
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
    // InternalGrana.g:9983:1: rule__ElkPort__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkPort__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9987:1: ( ( RULE_ID ) )
            // InternalGrana.g:9988:1: ( RULE_ID )
            {
            // InternalGrana.g:9988:1: ( RULE_ID )
            // InternalGrana.g:9989:1: RULE_ID
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
    // InternalGrana.g:9998:1: rule__ElkPort__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkPort__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10002:1: ( ( ruleProperty ) )
            // InternalGrana.g:10003:1: ( ruleProperty )
            {
            // InternalGrana.g:10003:1: ( ruleProperty )
            // InternalGrana.g:10004:1: ruleProperty
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
    // InternalGrana.g:10013:1: rule__ElkPort__LabelsAssignment_2_3 : ( ruleElkLabel ) ;
    public final void rule__ElkPort__LabelsAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10017:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10018:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10018:1: ( ruleElkLabel )
            // InternalGrana.g:10019:1: ruleElkLabel
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
    // InternalGrana.g:10028:1: rule__ShapeLayout__XAssignment_2_0_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__XAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10032:1: ( ( ruleNumber ) )
            // InternalGrana.g:10033:1: ( ruleNumber )
            {
            // InternalGrana.g:10033:1: ( ruleNumber )
            // InternalGrana.g:10034:1: ruleNumber
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
    // InternalGrana.g:10043:1: rule__ShapeLayout__YAssignment_2_0_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__YAssignment_2_0_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10047:1: ( ( ruleNumber ) )
            // InternalGrana.g:10048:1: ( ruleNumber )
            {
            // InternalGrana.g:10048:1: ( ruleNumber )
            // InternalGrana.g:10049:1: ruleNumber
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
    // InternalGrana.g:10058:1: rule__ShapeLayout__WidthAssignment_2_1_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__WidthAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10062:1: ( ( ruleNumber ) )
            // InternalGrana.g:10063:1: ( ruleNumber )
            {
            // InternalGrana.g:10063:1: ( ruleNumber )
            // InternalGrana.g:10064:1: ruleNumber
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
    // InternalGrana.g:10073:1: rule__ShapeLayout__HeightAssignment_2_1_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__HeightAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10077:1: ( ( ruleNumber ) )
            // InternalGrana.g:10078:1: ( ruleNumber )
            {
            // InternalGrana.g:10078:1: ( ruleNumber )
            // InternalGrana.g:10079:1: ruleNumber
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
    // InternalGrana.g:10088:1: rule__ElkEdge__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkEdge__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10092:1: ( ( RULE_ID ) )
            // InternalGrana.g:10093:1: ( RULE_ID )
            {
            // InternalGrana.g:10093:1: ( RULE_ID )
            // InternalGrana.g:10094:1: RULE_ID
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
    // InternalGrana.g:10103:1: rule__ElkEdge__SourcesAssignment_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10107:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10108:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10108:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10109:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_2_0()); 
            // InternalGrana.g:10110:1: ( ruleQualifiedId )
            // InternalGrana.g:10111:1: ruleQualifiedId
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
    // InternalGrana.g:10122:1: rule__ElkEdge__SourcesAssignment_3_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10126:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10127:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10127:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10128:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_3_1_0()); 
            // InternalGrana.g:10129:1: ( ruleQualifiedId )
            // InternalGrana.g:10130:1: ruleQualifiedId
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
    // InternalGrana.g:10141:1: rule__ElkEdge__TargetsAssignment_5 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10145:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10146:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10146:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10147:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_5_0()); 
            // InternalGrana.g:10148:1: ( ruleQualifiedId )
            // InternalGrana.g:10149:1: ruleQualifiedId
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
    // InternalGrana.g:10160:1: rule__ElkEdge__TargetsAssignment_6_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10164:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10165:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10165:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10166:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_6_1_0()); 
            // InternalGrana.g:10167:1: ( ruleQualifiedId )
            // InternalGrana.g:10168:1: ruleQualifiedId
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
    // InternalGrana.g:10179:1: rule__ElkEdge__PropertiesAssignment_7_2 : ( ruleProperty ) ;
    public final void rule__ElkEdge__PropertiesAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10183:1: ( ( ruleProperty ) )
            // InternalGrana.g:10184:1: ( ruleProperty )
            {
            // InternalGrana.g:10184:1: ( ruleProperty )
            // InternalGrana.g:10185:1: ruleProperty
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
    // InternalGrana.g:10194:1: rule__ElkEdge__LabelsAssignment_7_3 : ( ruleElkLabel ) ;
    public final void rule__ElkEdge__LabelsAssignment_7_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10198:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10199:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10199:1: ( ruleElkLabel )
            // InternalGrana.g:10200:1: ruleElkLabel
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
    // InternalGrana.g:10209:1: rule__EdgeLayout__SectionsAssignment_2_0 : ( ruleElkSingleEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10213:1: ( ( ruleElkSingleEdgeSection ) )
            // InternalGrana.g:10214:1: ( ruleElkSingleEdgeSection )
            {
            // InternalGrana.g:10214:1: ( ruleElkSingleEdgeSection )
            // InternalGrana.g:10215:1: ruleElkSingleEdgeSection
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
    // InternalGrana.g:10224:1: rule__EdgeLayout__SectionsAssignment_2_1 : ( ruleElkEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10228:1: ( ( ruleElkEdgeSection ) )
            // InternalGrana.g:10229:1: ( ruleElkEdgeSection )
            {
            // InternalGrana.g:10229:1: ( ruleElkEdgeSection )
            // InternalGrana.g:10230:1: ruleElkEdgeSection
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


    // $ANTLR start "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2"
    // InternalGrana.g:10239:1: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10243:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10244:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10244:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10245:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_2_0()); 
            // InternalGrana.g:10246:1: ( ruleQualifiedId )
            // InternalGrana.g:10247:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2"
    // InternalGrana.g:10258:1: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10262:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10263:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10263:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10264:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_1_2_0()); 
            // InternalGrana.g:10265:1: ( ruleQualifiedId )
            // InternalGrana.g:10266:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_1_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_1_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartXAssignment_1_2_2"
    // InternalGrana.g:10277:1: rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartXAssignment_1_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10281:1: ( ( ruleNumber ) )
            // InternalGrana.g:10282:1: ( ruleNumber )
            {
            // InternalGrana.g:10282:1: ( ruleNumber )
            // InternalGrana.g:10283:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__StartXAssignment_1_2_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartYAssignment_1_2_4"
    // InternalGrana.g:10292:1: rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartYAssignment_1_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10296:1: ( ( ruleNumber ) )
            // InternalGrana.g:10297:1: ( ruleNumber )
            {
            // InternalGrana.g:10297:1: ( ruleNumber )
            // InternalGrana.g:10298:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_2_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__StartYAssignment_1_2_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndXAssignment_1_3_2"
    // InternalGrana.g:10307:1: rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndXAssignment_1_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10311:1: ( ( ruleNumber ) )
            // InternalGrana.g:10312:1: ( ruleNumber )
            {
            // InternalGrana.g:10312:1: ( ruleNumber )
            // InternalGrana.g:10313:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__EndXAssignment_1_3_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndYAssignment_1_3_4"
    // InternalGrana.g:10322:1: rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndYAssignment_1_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10326:1: ( ( ruleNumber ) )
            // InternalGrana.g:10327:1: ( ruleNumber )
            {
            // InternalGrana.g:10327:1: ( ruleNumber )
            // InternalGrana.g:10328:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_3_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__EndYAssignment_1_3_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2"
    // InternalGrana.g:10337:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10341:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10342:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10342:1: ( ruleElkBendPoint )
            // InternalGrana.g:10343:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_2_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1"
    // InternalGrana.g:10352:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10356:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10357:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10357:1: ( ruleElkBendPoint )
            // InternalGrana.g:10358:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1"


    // $ANTLR start "rule__ElkEdgeSection__IdentifierAssignment_1"
    // InternalGrana.g:10367:1: rule__ElkEdgeSection__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkEdgeSection__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10371:1: ( ( RULE_ID ) )
            // InternalGrana.g:10372:1: ( RULE_ID )
            {
            // InternalGrana.g:10372:1: ( RULE_ID )
            // InternalGrana.g:10373:1: RULE_ID
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
    // InternalGrana.g:10382:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10386:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10387:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10387:1: ( ( RULE_ID ) )
            // InternalGrana.g:10388:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
            // InternalGrana.g:10389:1: ( RULE_ID )
            // InternalGrana.g:10390:1: RULE_ID
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
    // InternalGrana.g:10401:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10405:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10406:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10406:1: ( ( RULE_ID ) )
            // InternalGrana.g:10407:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
            // InternalGrana.g:10408:1: ( RULE_ID )
            // InternalGrana.g:10409:1: RULE_ID
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


    // $ANTLR start "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2"
    // InternalGrana.g:10420:1: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10424:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10425:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10425:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10426:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_2_0()); 
            // InternalGrana.g:10427:1: ( ruleQualifiedId )
            // InternalGrana.g:10428:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2"
    // InternalGrana.g:10439:1: rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10443:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10444:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10444:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10445:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_1_2_0()); 
            // InternalGrana.g:10446:1: ( ruleQualifiedId )
            // InternalGrana.g:10447:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_1_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_1_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2"


    // $ANTLR start "rule__ElkEdgeSection__StartXAssignment_4_2_2"
    // InternalGrana.g:10458:1: rule__ElkEdgeSection__StartXAssignment_4_2_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartXAssignment_4_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10462:1: ( ( ruleNumber ) )
            // InternalGrana.g:10463:1: ( ruleNumber )
            {
            // InternalGrana.g:10463:1: ( ruleNumber )
            // InternalGrana.g:10464:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__StartXAssignment_4_2_2"


    // $ANTLR start "rule__ElkEdgeSection__StartYAssignment_4_2_4"
    // InternalGrana.g:10473:1: rule__ElkEdgeSection__StartYAssignment_4_2_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartYAssignment_4_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10477:1: ( ( ruleNumber ) )
            // InternalGrana.g:10478:1: ( ruleNumber )
            {
            // InternalGrana.g:10478:1: ( ruleNumber )
            // InternalGrana.g:10479:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_2_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__StartYAssignment_4_2_4"


    // $ANTLR start "rule__ElkEdgeSection__EndXAssignment_4_3_2"
    // InternalGrana.g:10488:1: rule__ElkEdgeSection__EndXAssignment_4_3_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndXAssignment_4_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10492:1: ( ( ruleNumber ) )
            // InternalGrana.g:10493:1: ( ruleNumber )
            {
            // InternalGrana.g:10493:1: ( ruleNumber )
            // InternalGrana.g:10494:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__EndXAssignment_4_3_2"


    // $ANTLR start "rule__ElkEdgeSection__EndYAssignment_4_3_4"
    // InternalGrana.g:10503:1: rule__ElkEdgeSection__EndYAssignment_4_3_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndYAssignment_4_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10507:1: ( ( ruleNumber ) )
            // InternalGrana.g:10508:1: ( ruleNumber )
            {
            // InternalGrana.g:10508:1: ( ruleNumber )
            // InternalGrana.g:10509:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_3_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__EndYAssignment_4_3_4"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_4_2"
    // InternalGrana.g:10518:1: rule__ElkEdgeSection__BendPointsAssignment_4_4_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10522:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10523:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10523:1: ( ruleElkBendPoint )
            // InternalGrana.g:10524:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_2_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_4_2"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1"
    // InternalGrana.g:10533:1: rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10537:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10538:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10538:1: ( ruleElkBendPoint )
            // InternalGrana.g:10539:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1"


    // $ANTLR start "rule__ElkBendPoint__XAssignment_0"
    // InternalGrana.g:10548:1: rule__ElkBendPoint__XAssignment_0 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10552:1: ( ( ruleNumber ) )
            // InternalGrana.g:10553:1: ( ruleNumber )
            {
            // InternalGrana.g:10553:1: ( ruleNumber )
            // InternalGrana.g:10554:1: ruleNumber
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
    // InternalGrana.g:10563:1: rule__ElkBendPoint__YAssignment_2 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10567:1: ( ( ruleNumber ) )
            // InternalGrana.g:10568:1: ( ruleNumber )
            {
            // InternalGrana.g:10568:1: ( ruleNumber )
            // InternalGrana.g:10569:1: ruleNumber
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
    // InternalGrana.g:10578:1: rule__Property__KeyAssignment_0 : ( rulePropertyKey ) ;
    public final void rule__Property__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10582:1: ( ( rulePropertyKey ) )
            // InternalGrana.g:10583:1: ( rulePropertyKey )
            {
            // InternalGrana.g:10583:1: ( rulePropertyKey )
            // InternalGrana.g:10584:1: rulePropertyKey
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
    // InternalGrana.g:10593:1: rule__Property__ValueAssignment_2_0 : ( RULE_STRING ) ;
    public final void rule__Property__ValueAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10597:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10598:1: ( RULE_STRING )
            {
            // InternalGrana.g:10598:1: ( RULE_STRING )
            // InternalGrana.g:10599:1: RULE_STRING
            {
             before(grammarAccess.getPropertyAccess().getValueSTRINGTerminalRuleCall_2_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getValueSTRINGTerminalRuleCall_2_0_0()); 

            }


            }

        }
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
    // InternalGrana.g:10608:1: rule__Property__ValueAssignment_2_1 : ( ruleQualifiedId ) ;
    public final void rule__Property__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10612:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10613:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:10613:1: ( ruleQualifiedId )
            // InternalGrana.g:10614:1: ruleQualifiedId
            {
             before(grammarAccess.getPropertyAccess().getValueQualifiedIdParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueQualifiedIdParserRuleCall_2_1_0()); 

            }


            }

        }
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
    // InternalGrana.g:10623:1: rule__Property__ValueAssignment_2_2 : ( ruleBoolean ) ;
    public final void rule__Property__ValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10627:1: ( ( ruleBoolean ) )
            // InternalGrana.g:10628:1: ( ruleBoolean )
            {
            // InternalGrana.g:10628:1: ( ruleBoolean )
            // InternalGrana.g:10629:1: ruleBoolean
            {
             before(grammarAccess.getPropertyAccess().getValueBooleanParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueBooleanParserRuleCall_2_2_0()); 

            }


            }

        }
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
    // InternalGrana.g:10638:1: rule__Property__ValueAssignment_2_3 : ( RULE_SIGNED_INT ) ;
    public final void rule__Property__ValueAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10642:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10643:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10643:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10644:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getPropertyAccess().getValueSIGNED_INTTerminalRuleCall_2_3_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getValueSIGNED_INTTerminalRuleCall_2_3_0()); 

            }


            }

        }
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


    // $ANTLR start "rule__Property__ValueAssignment_2_4"
    // InternalGrana.g:10653:1: rule__Property__ValueAssignment_2_4 : ( RULE_FLOAT ) ;
    public final void rule__Property__ValueAssignment_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10657:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:10658:1: ( RULE_FLOAT )
            {
            // InternalGrana.g:10658:1: ( RULE_FLOAT )
            // InternalGrana.g:10659:1: RULE_FLOAT
            {
             before(grammarAccess.getPropertyAccess().getValueFLOATTerminalRuleCall_2_4_0()); 
            match(input,RULE_FLOAT,FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getValueFLOATTerminalRuleCall_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__ValueAssignment_2_4"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000006100000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000006100002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x1800000000000040L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x6000000000200000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000001000000080L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000001000000082L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000001000018080L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x4000000000200000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000680000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000008000000040L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x00021B8000000040L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x00020B0000000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0004000100000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000004100000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x02F8000000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x00F8000000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0004200000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x00000000000060F0L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0001800000000002L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x00F8000000000002L});

}
