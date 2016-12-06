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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BOOLEAN", "RULE_STRING", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'csv'", "'json'", "'globalResources'", "'globalOutputs'", "'execute'", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'comparejob'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'rangeanalyses'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'", "'parallel'", "'all'", "'layoutBeforeAnalysis'", "'measureExecutionTime'"
    };
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_STRING=5;
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
    public static final int RULE_TFLOAT=6;
    public static final int RULE_ID=8;
    public static final int RULE_WS=11;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_NATURAL=7;
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


    // $ANTLR start "entryRuleKIdentifier"
    // InternalGrana.g:592:1: entryRuleKIdentifier : ruleKIdentifier EOF ;
    public final void entryRuleKIdentifier() throws RecognitionException {
        try {
            // InternalGrana.g:593:1: ( ruleKIdentifier EOF )
            // InternalGrana.g:594:1: ruleKIdentifier EOF
            {
             before(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_1);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKIdentifierRule()); 
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
    // $ANTLR end "entryRuleKIdentifier"


    // $ANTLR start "ruleKIdentifier"
    // InternalGrana.g:601:1: ruleKIdentifier : ( ( rule__KIdentifier__Group__0 ) ) ;
    public final void ruleKIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:605:2: ( ( ( rule__KIdentifier__Group__0 ) ) )
            // InternalGrana.g:606:1: ( ( rule__KIdentifier__Group__0 ) )
            {
            // InternalGrana.g:606:1: ( ( rule__KIdentifier__Group__0 ) )
            // InternalGrana.g:607:1: ( rule__KIdentifier__Group__0 )
            {
             before(grammarAccess.getKIdentifierAccess().getGroup()); 
            // InternalGrana.g:608:1: ( rule__KIdentifier__Group__0 )
            // InternalGrana.g:608:2: rule__KIdentifier__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:620:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // InternalGrana.g:621:1: ( rulePersistentEntry EOF )
            // InternalGrana.g:622:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_1);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
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
    // $ANTLR end "entryRulePersistentEntry"


    // $ANTLR start "rulePersistentEntry"
    // InternalGrana.g:629:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:633:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // InternalGrana.g:634:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // InternalGrana.g:634:1: ( ( rule__PersistentEntry__Group__0 ) )
            // InternalGrana.g:635:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // InternalGrana.g:636:1: ( rule__PersistentEntry__Group__0 )
            // InternalGrana.g:636:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:648:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // InternalGrana.g:649:1: ( ruleQualifiedID EOF )
            // InternalGrana.g:650:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
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
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // InternalGrana.g:657:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:661:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // InternalGrana.g:662:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // InternalGrana.g:662:1: ( ( rule__QualifiedID__Group__0 ) )
            // InternalGrana.g:663:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // InternalGrana.g:664:1: ( rule__QualifiedID__Group__0 )
            // InternalGrana.g:664:2: rule__QualifiedID__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:676:1: entryRulePropertyValue : rulePropertyValue EOF ;
    public final void entryRulePropertyValue() throws RecognitionException {
        try {
            // InternalGrana.g:677:1: ( rulePropertyValue EOF )
            // InternalGrana.g:678:1: rulePropertyValue EOF
            {
             before(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_1);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPropertyValueRule()); 
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
    // $ANTLR end "entryRulePropertyValue"


    // $ANTLR start "rulePropertyValue"
    // InternalGrana.g:685:1: rulePropertyValue : ( ( rule__PropertyValue__Alternatives ) ) ;
    public final void rulePropertyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:689:2: ( ( ( rule__PropertyValue__Alternatives ) ) )
            // InternalGrana.g:690:1: ( ( rule__PropertyValue__Alternatives ) )
            {
            // InternalGrana.g:690:1: ( ( rule__PropertyValue__Alternatives ) )
            // InternalGrana.g:691:1: ( rule__PropertyValue__Alternatives )
            {
             before(grammarAccess.getPropertyValueAccess().getAlternatives()); 
            // InternalGrana.g:692:1: ( rule__PropertyValue__Alternatives )
            // InternalGrana.g:692:2: rule__PropertyValue__Alternatives
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:704:1: entryRuleFloat : ruleFloat EOF ;
    public final void entryRuleFloat() throws RecognitionException {
        try {
            // InternalGrana.g:705:1: ( ruleFloat EOF )
            // InternalGrana.g:706:1: ruleFloat EOF
            {
             before(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_1);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRule()); 
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
    // $ANTLR end "entryRuleFloat"


    // $ANTLR start "ruleFloat"
    // InternalGrana.g:713:1: ruleFloat : ( ( rule__Float__Alternatives ) ) ;
    public final void ruleFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:717:2: ( ( ( rule__Float__Alternatives ) ) )
            // InternalGrana.g:718:1: ( ( rule__Float__Alternatives ) )
            {
            // InternalGrana.g:718:1: ( ( rule__Float__Alternatives ) )
            // InternalGrana.g:719:1: ( rule__Float__Alternatives )
            {
             before(grammarAccess.getFloatAccess().getAlternatives()); 
            // InternalGrana.g:720:1: ( rule__Float__Alternatives )
            // InternalGrana.g:720:2: rule__Float__Alternatives
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "ruleOutputType"
    // InternalGrana.g:733:1: ruleOutputType : ( ( rule__OutputType__Alternatives ) ) ;
    public final void ruleOutputType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:737:1: ( ( ( rule__OutputType__Alternatives ) ) )
            // InternalGrana.g:738:1: ( ( rule__OutputType__Alternatives ) )
            {
            // InternalGrana.g:738:1: ( ( rule__OutputType__Alternatives ) )
            // InternalGrana.g:739:1: ( rule__OutputType__Alternatives )
            {
             before(grammarAccess.getOutputTypeAccess().getAlternatives()); 
            // InternalGrana.g:740:1: ( rule__OutputType__Alternatives )
            // InternalGrana.g:740:2: rule__OutputType__Alternatives
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
    // InternalGrana.g:751:1: rule__Grana__Alternatives_2_2 : ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) );
    public final void rule__Grana__Alternatives_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:755:1: ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==40) ) {
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
                    // InternalGrana.g:756:1: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    {
                    // InternalGrana.g:756:1: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    // InternalGrana.g:757:1: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_2_0()); 
                    // InternalGrana.g:758:1: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    // InternalGrana.g:758:2: rule__Grana__ExecuteAllAssignment_2_2_0
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
                    // InternalGrana.g:762:6: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    {
                    // InternalGrana.g:762:6: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    // InternalGrana.g:763:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    {
                    // InternalGrana.g:763:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) )
                    // InternalGrana.g:764:1: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:765:1: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    // InternalGrana.g:765:2: rule__Grana__ExecuteAssignment_2_2_1
                    {
                    pushFollow(FOLLOW_3);
                    rule__Grana__ExecuteAssignment_2_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 

                    }

                    // InternalGrana.g:768:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    // InternalGrana.g:769:1: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:770:1: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalGrana.g:770:2: rule__Grana__ExecuteAssignment_2_2_1
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
    // InternalGrana.g:780:1: rule__Job__Alternatives : ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) );
    public final void rule__Job__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:784:1: ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt3=1;
                }
                break;
            case 23:
                {
                alt3=2;
                }
                break;
            case 22:
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
                    // InternalGrana.g:785:1: ( ruleRegularJob )
                    {
                    // InternalGrana.g:785:1: ( ruleRegularJob )
                    // InternalGrana.g:786:1: ruleRegularJob
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
                    // InternalGrana.g:791:6: ( ruleRangeJob )
                    {
                    // InternalGrana.g:791:6: ( ruleRangeJob )
                    // InternalGrana.g:792:1: ruleRangeJob
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
                    // InternalGrana.g:797:6: ( ruleCompareJob )
                    {
                    // InternalGrana.g:797:6: ( ruleCompareJob )
                    // InternalGrana.g:798:1: ruleCompareJob
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
    // InternalGrana.g:808:1: rule__RangeJob__Alternatives_12 : ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) );
    public final void rule__RangeJob__Alternatives_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:812:1: ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==25) ) {
                alt4=1;
            }
            else if ( (LA4_0==27) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalGrana.g:813:1: ( ( rule__RangeJob__Group_12_0__0 ) )
                    {
                    // InternalGrana.g:813:1: ( ( rule__RangeJob__Group_12_0__0 ) )
                    // InternalGrana.g:814:1: ( rule__RangeJob__Group_12_0__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_0()); 
                    // InternalGrana.g:815:1: ( rule__RangeJob__Group_12_0__0 )
                    // InternalGrana.g:815:2: rule__RangeJob__Group_12_0__0
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
                    // InternalGrana.g:819:6: ( ( rule__RangeJob__Group_12_1__0 ) )
                    {
                    // InternalGrana.g:819:6: ( ( rule__RangeJob__Group_12_1__0 ) )
                    // InternalGrana.g:820:1: ( rule__RangeJob__Group_12_1__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_1()); 
                    // InternalGrana.g:821:1: ( rule__RangeJob__Group_12_1__0 )
                    // InternalGrana.g:821:2: rule__RangeJob__Group_12_1__0
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
    // InternalGrana.g:830:1: rule__Range__Alternatives : ( ( ruleFloatRange ) | ( ruleIntRange ) );
    public final void rule__Range__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:834:1: ( ( ruleFloatRange ) | ( ruleIntRange ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==28) ) {
                alt5=1;
            }
            else if ( ((LA5_0>=30 && LA5_0<=31)) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalGrana.g:835:1: ( ruleFloatRange )
                    {
                    // InternalGrana.g:835:1: ( ruleFloatRange )
                    // InternalGrana.g:836:1: ruleFloatRange
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
                    // InternalGrana.g:841:6: ( ruleIntRange )
                    {
                    // InternalGrana.g:841:6: ( ruleIntRange )
                    // InternalGrana.g:842:1: ruleIntRange
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
    // InternalGrana.g:852:1: rule__IntRange__Alternatives : ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) );
    public final void rule__IntRange__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:856:1: ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==31) ) {
                alt6=1;
            }
            else if ( (LA6_0==30) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalGrana.g:857:1: ( ruleIntRangeRange )
                    {
                    // InternalGrana.g:857:1: ( ruleIntRangeRange )
                    // InternalGrana.g:858:1: ruleIntRangeRange
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
                    // InternalGrana.g:863:6: ( ruleIntRangeValues )
                    {
                    // InternalGrana.g:863:6: ( ruleIntRangeValues )
                    // InternalGrana.g:864:1: ruleIntRangeValues
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
    // InternalGrana.g:874:1: rule__Resource__Alternatives : ( ( ruleResourceReference ) | ( ruleLocalResource ) );
    public final void rule__Resource__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:878:1: ( ( ruleResourceReference ) | ( ruleLocalResource ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==33) ) {
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
                    // InternalGrana.g:879:1: ( ruleResourceReference )
                    {
                    // InternalGrana.g:879:1: ( ruleResourceReference )
                    // InternalGrana.g:880:1: ruleResourceReference
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
                    // InternalGrana.g:885:6: ( ruleLocalResource )
                    {
                    // InternalGrana.g:885:6: ( ruleLocalResource )
                    // InternalGrana.g:886:1: ruleLocalResource
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
    // InternalGrana.g:896:1: rule__Output__Alternatives : ( ( ruleOutputReference ) | ( ruleLocalOutput ) );
    public final void rule__Output__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:900:1: ( ( ruleOutputReference ) | ( ruleLocalOutput ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==33) ) {
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
                    // InternalGrana.g:901:1: ( ruleOutputReference )
                    {
                    // InternalGrana.g:901:1: ( ruleOutputReference )
                    // InternalGrana.g:902:1: ruleOutputReference
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
                    // InternalGrana.g:907:6: ( ruleLocalOutput )
                    {
                    // InternalGrana.g:907:6: ( ruleLocalOutput )
                    // InternalGrana.g:908:1: ruleLocalOutput
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


    // $ANTLR start "rule__PropertyValue__Alternatives"
    // InternalGrana.g:918:1: rule__PropertyValue__Alternatives : ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) );
    public final void rule__PropertyValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:922:1: ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) )
            int alt9=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt9=1;
                }
                break;
            case RULE_STRING:
                {
                alt9=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt9=3;
                }
                break;
            case RULE_ID:
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
                    // InternalGrana.g:923:1: ( RULE_BOOLEAN )
                    {
                    // InternalGrana.g:923:1: ( RULE_BOOLEAN )
                    // InternalGrana.g:924:1: RULE_BOOLEAN
                    {
                     before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                    match(input,RULE_BOOLEAN,FOLLOW_2); 
                     after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:929:6: ( RULE_STRING )
                    {
                    // InternalGrana.g:929:6: ( RULE_STRING )
                    // InternalGrana.g:930:1: RULE_STRING
                    {
                     before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:935:6: ( ruleFloat )
                    {
                    // InternalGrana.g:935:6: ( ruleFloat )
                    // InternalGrana.g:936:1: ruleFloat
                    {
                     before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleFloat();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:941:6: ( ruleQualifiedID )
                    {
                    // InternalGrana.g:941:6: ( ruleQualifiedID )
                    // InternalGrana.g:942:1: ruleQualifiedID
                    {
                     before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:952:1: rule__Float__Alternatives : ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) );
    public final void rule__Float__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:956:1: ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_TFLOAT) ) {
                alt10=1;
            }
            else if ( (LA10_0==RULE_NATURAL) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalGrana.g:957:1: ( RULE_TFLOAT )
                    {
                    // InternalGrana.g:957:1: ( RULE_TFLOAT )
                    // InternalGrana.g:958:1: RULE_TFLOAT
                    {
                     before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                    match(input,RULE_TFLOAT,FOLLOW_2); 
                     after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:963:6: ( RULE_NATURAL )
                    {
                    // InternalGrana.g:963:6: ( RULE_NATURAL )
                    // InternalGrana.g:964:1: RULE_NATURAL
                    {
                     before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                    match(input,RULE_NATURAL,FOLLOW_2); 
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


    // $ANTLR start "rule__OutputType__Alternatives"
    // InternalGrana.g:974:1: rule__OutputType__Alternatives : ( ( ( 'csv' ) ) | ( ( 'json' ) ) );
    public final void rule__OutputType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:978:1: ( ( ( 'csv' ) ) | ( ( 'json' ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==12) ) {
                alt11=1;
            }
            else if ( (LA11_0==13) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalGrana.g:979:1: ( ( 'csv' ) )
                    {
                    // InternalGrana.g:979:1: ( ( 'csv' ) )
                    // InternalGrana.g:980:1: ( 'csv' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 
                    // InternalGrana.g:981:1: ( 'csv' )
                    // InternalGrana.g:981:3: 'csv'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:986:6: ( ( 'json' ) )
                    {
                    // InternalGrana.g:986:6: ( ( 'json' ) )
                    // InternalGrana.g:987:1: ( 'json' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1()); 
                    // InternalGrana.g:988:1: ( 'json' )
                    // InternalGrana.g:988:3: 'json'
                    {
                    match(input,13,FOLLOW_2); 

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
    // InternalGrana.g:1000:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1004:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // InternalGrana.g:1005:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
            {
            pushFollow(FOLLOW_4);
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
    // InternalGrana.g:1012:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1016:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // InternalGrana.g:1017:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // InternalGrana.g:1017:1: ( ( rule__Grana__Group_0__0 )? )
            // InternalGrana.g:1018:1: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // InternalGrana.g:1019:1: ( rule__Grana__Group_0__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==14) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalGrana.g:1019:2: rule__Grana__Group_0__0
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
    // InternalGrana.g:1029:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl rule__Grana__Group__2 ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1033:1: ( rule__Grana__Group__1__Impl rule__Grana__Group__2 )
            // InternalGrana.g:1034:2: rule__Grana__Group__1__Impl rule__Grana__Group__2
            {
            pushFollow(FOLLOW_4);
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
    // InternalGrana.g:1041:1: rule__Grana__Group__1__Impl : ( ( rule__Grana__Group_1__0 )? ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1045:1: ( ( ( rule__Grana__Group_1__0 )? ) )
            // InternalGrana.g:1046:1: ( ( rule__Grana__Group_1__0 )? )
            {
            // InternalGrana.g:1046:1: ( ( rule__Grana__Group_1__0 )? )
            // InternalGrana.g:1047:1: ( rule__Grana__Group_1__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_1()); 
            // InternalGrana.g:1048:1: ( rule__Grana__Group_1__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==15) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalGrana.g:1048:2: rule__Grana__Group_1__0
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
    // InternalGrana.g:1058:1: rule__Grana__Group__2 : rule__Grana__Group__2__Impl rule__Grana__Group__3 ;
    public final void rule__Grana__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1062:1: ( rule__Grana__Group__2__Impl rule__Grana__Group__3 )
            // InternalGrana.g:1063:2: rule__Grana__Group__2__Impl rule__Grana__Group__3
            {
            pushFollow(FOLLOW_5);
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
    // InternalGrana.g:1070:1: rule__Grana__Group__2__Impl : ( ( rule__Grana__Group_2__0 ) ) ;
    public final void rule__Grana__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1074:1: ( ( ( rule__Grana__Group_2__0 ) ) )
            // InternalGrana.g:1075:1: ( ( rule__Grana__Group_2__0 ) )
            {
            // InternalGrana.g:1075:1: ( ( rule__Grana__Group_2__0 ) )
            // InternalGrana.g:1076:1: ( rule__Grana__Group_2__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup_2()); 
            // InternalGrana.g:1077:1: ( rule__Grana__Group_2__0 )
            // InternalGrana.g:1077:2: rule__Grana__Group_2__0
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
    // InternalGrana.g:1087:1: rule__Grana__Group__3 : rule__Grana__Group__3__Impl ;
    public final void rule__Grana__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1091:1: ( rule__Grana__Group__3__Impl )
            // InternalGrana.g:1092:2: rule__Grana__Group__3__Impl
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
    // InternalGrana.g:1098:1: rule__Grana__Group__3__Impl : ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) ;
    public final void rule__Grana__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1102:1: ( ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) )
            // InternalGrana.g:1103:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            {
            // InternalGrana.g:1103:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            // InternalGrana.g:1104:1: ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* )
            {
            // InternalGrana.g:1104:1: ( ( rule__Grana__JobsAssignment_3 ) )
            // InternalGrana.g:1105:1: ( rule__Grana__JobsAssignment_3 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1106:1: ( rule__Grana__JobsAssignment_3 )
            // InternalGrana.g:1106:2: rule__Grana__JobsAssignment_3
            {
            pushFollow(FOLLOW_6);
            rule__Grana__JobsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }

            // InternalGrana.g:1109:1: ( ( rule__Grana__JobsAssignment_3 )* )
            // InternalGrana.g:1110:1: ( rule__Grana__JobsAssignment_3 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1111:1: ( rule__Grana__JobsAssignment_3 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==17||(LA14_0>=22 && LA14_0<=23)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalGrana.g:1111:2: rule__Grana__JobsAssignment_3
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Grana__JobsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
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
    // InternalGrana.g:1130:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1134:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // InternalGrana.g:1135:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1142:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1146:1: ( ( 'globalResources' ) )
            // InternalGrana.g:1147:1: ( 'globalResources' )
            {
            // InternalGrana.g:1147:1: ( 'globalResources' )
            // InternalGrana.g:1148:1: 'globalResources'
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0()); 
            match(input,14,FOLLOW_2); 
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
    // InternalGrana.g:1161:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1165:1: ( rule__Grana__Group_0__1__Impl )
            // InternalGrana.g:1166:2: rule__Grana__Group_0__1__Impl
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
    // InternalGrana.g:1172:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1176:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // InternalGrana.g:1177:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // InternalGrana.g:1177:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // InternalGrana.g:1178:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // InternalGrana.g:1179:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_ID) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalGrana.g:1179:2: rule__Grana__GlobalResourcesAssignment_0_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Grana__GlobalResourcesAssignment_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
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
    // InternalGrana.g:1193:1: rule__Grana__Group_1__0 : rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 ;
    public final void rule__Grana__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1197:1: ( rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 )
            // InternalGrana.g:1198:2: rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1205:1: rule__Grana__Group_1__0__Impl : ( 'globalOutputs' ) ;
    public final void rule__Grana__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1209:1: ( ( 'globalOutputs' ) )
            // InternalGrana.g:1210:1: ( 'globalOutputs' )
            {
            // InternalGrana.g:1210:1: ( 'globalOutputs' )
            // InternalGrana.g:1211:1: 'globalOutputs'
            {
             before(grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0()); 
            match(input,15,FOLLOW_2); 
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
    // InternalGrana.g:1224:1: rule__Grana__Group_1__1 : rule__Grana__Group_1__1__Impl ;
    public final void rule__Grana__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1228:1: ( rule__Grana__Group_1__1__Impl )
            // InternalGrana.g:1229:2: rule__Grana__Group_1__1__Impl
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
    // InternalGrana.g:1235:1: rule__Grana__Group_1__1__Impl : ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) ;
    public final void rule__Grana__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1239:1: ( ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) )
            // InternalGrana.g:1240:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            {
            // InternalGrana.g:1240:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            // InternalGrana.g:1241:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 
            // InternalGrana.g:1242:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalGrana.g:1242:2: rule__Grana__GloobalOutputsAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Grana__GloobalOutputsAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
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
    // InternalGrana.g:1256:1: rule__Grana__Group_2__0 : rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 ;
    public final void rule__Grana__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1260:1: ( rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 )
            // InternalGrana.g:1261:2: rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1
            {
            pushFollow(FOLLOW_8);
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
    // InternalGrana.g:1268:1: rule__Grana__Group_2__0__Impl : ( 'execute' ) ;
    public final void rule__Grana__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1272:1: ( ( 'execute' ) )
            // InternalGrana.g:1273:1: ( 'execute' )
            {
            // InternalGrana.g:1273:1: ( 'execute' )
            // InternalGrana.g:1274:1: 'execute'
            {
             before(grammarAccess.getGranaAccess().getExecuteKeyword_2_0()); 
            match(input,16,FOLLOW_2); 
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
    // InternalGrana.g:1287:1: rule__Grana__Group_2__1 : rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 ;
    public final void rule__Grana__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1291:1: ( rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 )
            // InternalGrana.g:1292:2: rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2
            {
            pushFollow(FOLLOW_8);
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
    // InternalGrana.g:1299:1: rule__Grana__Group_2__1__Impl : ( ( rule__Grana__ParallelAssignment_2_1 )? ) ;
    public final void rule__Grana__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1303:1: ( ( ( rule__Grana__ParallelAssignment_2_1 )? ) )
            // InternalGrana.g:1304:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            {
            // InternalGrana.g:1304:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            // InternalGrana.g:1305:1: ( rule__Grana__ParallelAssignment_2_1 )?
            {
             before(grammarAccess.getGranaAccess().getParallelAssignment_2_1()); 
            // InternalGrana.g:1306:1: ( rule__Grana__ParallelAssignment_2_1 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==39) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalGrana.g:1306:2: rule__Grana__ParallelAssignment_2_1
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
    // InternalGrana.g:1316:1: rule__Grana__Group_2__2 : rule__Grana__Group_2__2__Impl ;
    public final void rule__Grana__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1320:1: ( rule__Grana__Group_2__2__Impl )
            // InternalGrana.g:1321:2: rule__Grana__Group_2__2__Impl
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
    // InternalGrana.g:1327:1: rule__Grana__Group_2__2__Impl : ( ( rule__Grana__Alternatives_2_2 ) ) ;
    public final void rule__Grana__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1331:1: ( ( ( rule__Grana__Alternatives_2_2 ) ) )
            // InternalGrana.g:1332:1: ( ( rule__Grana__Alternatives_2_2 ) )
            {
            // InternalGrana.g:1332:1: ( ( rule__Grana__Alternatives_2_2 ) )
            // InternalGrana.g:1333:1: ( rule__Grana__Alternatives_2_2 )
            {
             before(grammarAccess.getGranaAccess().getAlternatives_2_2()); 
            // InternalGrana.g:1334:1: ( rule__Grana__Alternatives_2_2 )
            // InternalGrana.g:1334:2: rule__Grana__Alternatives_2_2
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
    // InternalGrana.g:1350:1: rule__RegularJob__Group__0 : rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 ;
    public final void rule__RegularJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1354:1: ( rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 )
            // InternalGrana.g:1355:2: rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1362:1: rule__RegularJob__Group__0__Impl : ( 'job' ) ;
    public final void rule__RegularJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1366:1: ( ( 'job' ) )
            // InternalGrana.g:1367:1: ( 'job' )
            {
            // InternalGrana.g:1367:1: ( 'job' )
            // InternalGrana.g:1368:1: 'job'
            {
             before(grammarAccess.getRegularJobAccess().getJobKeyword_0()); 
            match(input,17,FOLLOW_2); 
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
    // InternalGrana.g:1381:1: rule__RegularJob__Group__1 : rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 ;
    public final void rule__RegularJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1385:1: ( rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 )
            // InternalGrana.g:1386:2: rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2
            {
            pushFollow(FOLLOW_9);
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
    // InternalGrana.g:1393:1: rule__RegularJob__Group__1__Impl : ( ( rule__RegularJob__NameAssignment_1 ) ) ;
    public final void rule__RegularJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1397:1: ( ( ( rule__RegularJob__NameAssignment_1 ) ) )
            // InternalGrana.g:1398:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:1398:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            // InternalGrana.g:1399:1: ( rule__RegularJob__NameAssignment_1 )
            {
             before(grammarAccess.getRegularJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:1400:1: ( rule__RegularJob__NameAssignment_1 )
            // InternalGrana.g:1400:2: rule__RegularJob__NameAssignment_1
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
    // InternalGrana.g:1410:1: rule__RegularJob__Group__2 : rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 ;
    public final void rule__RegularJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1414:1: ( rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 )
            // InternalGrana.g:1415:2: rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3
            {
            pushFollow(FOLLOW_9);
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
    // InternalGrana.g:1422:1: rule__RegularJob__Group__2__Impl : ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) ;
    public final void rule__RegularJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1426:1: ( ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) )
            // InternalGrana.g:1427:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            {
            // InternalGrana.g:1427:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            // InternalGrana.g:1428:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2()); 
            // InternalGrana.g:1429:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==41) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalGrana.g:1429:2: rule__RegularJob__LayoutBeforeAnalysisAssignment_2
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
    // InternalGrana.g:1439:1: rule__RegularJob__Group__3 : rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 ;
    public final void rule__RegularJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1443:1: ( rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 )
            // InternalGrana.g:1444:2: rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4
            {
            pushFollow(FOLLOW_9);
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
    // InternalGrana.g:1451:1: rule__RegularJob__Group__3__Impl : ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) ;
    public final void rule__RegularJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1455:1: ( ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) )
            // InternalGrana.g:1456:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            {
            // InternalGrana.g:1456:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            // InternalGrana.g:1457:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3()); 
            // InternalGrana.g:1458:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==42) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalGrana.g:1458:2: rule__RegularJob__MeasureExecutionTimeAssignment_3
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
    // InternalGrana.g:1468:1: rule__RegularJob__Group__4 : rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 ;
    public final void rule__RegularJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1472:1: ( rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 )
            // InternalGrana.g:1473:2: rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5
            {
            pushFollow(FOLLOW_10);
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
    // InternalGrana.g:1480:1: rule__RegularJob__Group__4__Impl : ( 'resources' ) ;
    public final void rule__RegularJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1484:1: ( ( 'resources' ) )
            // InternalGrana.g:1485:1: ( 'resources' )
            {
            // InternalGrana.g:1485:1: ( 'resources' )
            // InternalGrana.g:1486:1: 'resources'
            {
             before(grammarAccess.getRegularJobAccess().getResourcesKeyword_4()); 
            match(input,18,FOLLOW_2); 
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
    // InternalGrana.g:1499:1: rule__RegularJob__Group__5 : rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 ;
    public final void rule__RegularJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1503:1: ( rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 )
            // InternalGrana.g:1504:2: rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6
            {
            pushFollow(FOLLOW_11);
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
    // InternalGrana.g:1511:1: rule__RegularJob__Group__5__Impl : ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) ;
    public final void rule__RegularJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1515:1: ( ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) )
            // InternalGrana.g:1516:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            {
            // InternalGrana.g:1516:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            // InternalGrana.g:1517:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            {
            // InternalGrana.g:1517:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) )
            // InternalGrana.g:1518:1: ( rule__RegularJob__ResourcesAssignment_5 )
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:1519:1: ( rule__RegularJob__ResourcesAssignment_5 )
            // InternalGrana.g:1519:2: rule__RegularJob__ResourcesAssignment_5
            {
            pushFollow(FOLLOW_12);
            rule__RegularJob__ResourcesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 

            }

            // InternalGrana.g:1522:1: ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            // InternalGrana.g:1523:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:1524:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_STRING||LA20_0==33) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalGrana.g:1524:2: rule__RegularJob__ResourcesAssignment_5
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__RegularJob__ResourcesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // InternalGrana.g:1535:1: rule__RegularJob__Group__6 : rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 ;
    public final void rule__RegularJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1539:1: ( rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 )
            // InternalGrana.g:1540:2: rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1547:1: rule__RegularJob__Group__6__Impl : ( 'layoutoptions' ) ;
    public final void rule__RegularJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1551:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:1552:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:1552:1: ( 'layoutoptions' )
            // InternalGrana.g:1553:1: 'layoutoptions'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6()); 
            match(input,19,FOLLOW_2); 
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
    // InternalGrana.g:1566:1: rule__RegularJob__Group__7 : rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 ;
    public final void rule__RegularJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1570:1: ( rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 )
            // InternalGrana.g:1571:2: rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8
            {
            pushFollow(FOLLOW_13);
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
    // InternalGrana.g:1578:1: rule__RegularJob__Group__7__Impl : ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) ;
    public final void rule__RegularJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1582:1: ( ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) )
            // InternalGrana.g:1583:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            {
            // InternalGrana.g:1583:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            // InternalGrana.g:1584:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            {
            // InternalGrana.g:1584:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) )
            // InternalGrana.g:1585:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:1586:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            // InternalGrana.g:1586:2: rule__RegularJob__LayoutOptionsAssignment_7
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__LayoutOptionsAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 

            }

            // InternalGrana.g:1589:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            // InternalGrana.g:1590:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:1591:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalGrana.g:1591:2: rule__RegularJob__LayoutOptionsAssignment_7
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RegularJob__LayoutOptionsAssignment_7();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // InternalGrana.g:1602:1: rule__RegularJob__Group__8 : rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 ;
    public final void rule__RegularJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1606:1: ( rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 )
            // InternalGrana.g:1607:2: rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1614:1: rule__RegularJob__Group__8__Impl : ( 'analyses' ) ;
    public final void rule__RegularJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1618:1: ( ( 'analyses' ) )
            // InternalGrana.g:1619:1: ( 'analyses' )
            {
            // InternalGrana.g:1619:1: ( 'analyses' )
            // InternalGrana.g:1620:1: 'analyses'
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesKeyword_8()); 
            match(input,20,FOLLOW_2); 
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
    // InternalGrana.g:1633:1: rule__RegularJob__Group__9 : rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 ;
    public final void rule__RegularJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1637:1: ( rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 )
            // InternalGrana.g:1638:2: rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10
            {
            pushFollow(FOLLOW_14);
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
    // InternalGrana.g:1645:1: rule__RegularJob__Group__9__Impl : ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) ;
    public final void rule__RegularJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1649:1: ( ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) )
            // InternalGrana.g:1650:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            {
            // InternalGrana.g:1650:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            // InternalGrana.g:1651:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            {
            // InternalGrana.g:1651:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) )
            // InternalGrana.g:1652:1: ( rule__RegularJob__AnalysesAssignment_9 )
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:1653:1: ( rule__RegularJob__AnalysesAssignment_9 )
            // InternalGrana.g:1653:2: rule__RegularJob__AnalysesAssignment_9
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__AnalysesAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 

            }

            // InternalGrana.g:1656:1: ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            // InternalGrana.g:1657:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:1658:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalGrana.g:1658:2: rule__RegularJob__AnalysesAssignment_9
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RegularJob__AnalysesAssignment_9();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
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
    // InternalGrana.g:1669:1: rule__RegularJob__Group__10 : rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 ;
    public final void rule__RegularJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1673:1: ( rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 )
            // InternalGrana.g:1674:2: rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11
            {
            pushFollow(FOLLOW_15);
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
    // InternalGrana.g:1681:1: rule__RegularJob__Group__10__Impl : ( 'output' ) ;
    public final void rule__RegularJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1685:1: ( ( 'output' ) )
            // InternalGrana.g:1686:1: ( 'output' )
            {
            // InternalGrana.g:1686:1: ( 'output' )
            // InternalGrana.g:1687:1: 'output'
            {
             before(grammarAccess.getRegularJobAccess().getOutputKeyword_10()); 
            match(input,21,FOLLOW_2); 
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
    // InternalGrana.g:1700:1: rule__RegularJob__Group__11 : rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 ;
    public final void rule__RegularJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1704:1: ( rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 )
            // InternalGrana.g:1705:2: rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12
            {
            pushFollow(FOLLOW_15);
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
    // InternalGrana.g:1712:1: rule__RegularJob__Group__11__Impl : ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) ;
    public final void rule__RegularJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1716:1: ( ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) )
            // InternalGrana.g:1717:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            {
            // InternalGrana.g:1717:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            // InternalGrana.g:1718:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            {
             before(grammarAccess.getRegularJobAccess().getOutputTypeAssignment_11()); 
            // InternalGrana.g:1719:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=12 && LA23_0<=13)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalGrana.g:1719:2: rule__RegularJob__OutputTypeAssignment_11
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
    // InternalGrana.g:1729:1: rule__RegularJob__Group__12 : rule__RegularJob__Group__12__Impl ;
    public final void rule__RegularJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1733:1: ( rule__RegularJob__Group__12__Impl )
            // InternalGrana.g:1734:2: rule__RegularJob__Group__12__Impl
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
    // InternalGrana.g:1740:1: rule__RegularJob__Group__12__Impl : ( ( rule__RegularJob__OutputAssignment_12 ) ) ;
    public final void rule__RegularJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1744:1: ( ( ( rule__RegularJob__OutputAssignment_12 ) ) )
            // InternalGrana.g:1745:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            {
            // InternalGrana.g:1745:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            // InternalGrana.g:1746:1: ( rule__RegularJob__OutputAssignment_12 )
            {
             before(grammarAccess.getRegularJobAccess().getOutputAssignment_12()); 
            // InternalGrana.g:1747:1: ( rule__RegularJob__OutputAssignment_12 )
            // InternalGrana.g:1747:2: rule__RegularJob__OutputAssignment_12
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
    // InternalGrana.g:1783:1: rule__CompareJob__Group__0 : rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 ;
    public final void rule__CompareJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1787:1: ( rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 )
            // InternalGrana.g:1788:2: rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1795:1: rule__CompareJob__Group__0__Impl : ( 'comparejob' ) ;
    public final void rule__CompareJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1799:1: ( ( 'comparejob' ) )
            // InternalGrana.g:1800:1: ( 'comparejob' )
            {
            // InternalGrana.g:1800:1: ( 'comparejob' )
            // InternalGrana.g:1801:1: 'comparejob'
            {
             before(grammarAccess.getCompareJobAccess().getComparejobKeyword_0()); 
            match(input,22,FOLLOW_2); 
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
    // InternalGrana.g:1814:1: rule__CompareJob__Group__1 : rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 ;
    public final void rule__CompareJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1818:1: ( rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 )
            // InternalGrana.g:1819:2: rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2
            {
            pushFollow(FOLLOW_16);
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
    // InternalGrana.g:1826:1: rule__CompareJob__Group__1__Impl : ( ( rule__CompareJob__NameAssignment_1 ) ) ;
    public final void rule__CompareJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1830:1: ( ( ( rule__CompareJob__NameAssignment_1 ) ) )
            // InternalGrana.g:1831:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:1831:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            // InternalGrana.g:1832:1: ( rule__CompareJob__NameAssignment_1 )
            {
             before(grammarAccess.getCompareJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:1833:1: ( rule__CompareJob__NameAssignment_1 )
            // InternalGrana.g:1833:2: rule__CompareJob__NameAssignment_1
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
    // InternalGrana.g:1843:1: rule__CompareJob__Group__2 : rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 ;
    public final void rule__CompareJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1847:1: ( rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 )
            // InternalGrana.g:1848:2: rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3
            {
            pushFollow(FOLLOW_10);
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
    // InternalGrana.g:1855:1: rule__CompareJob__Group__2__Impl : ( 'resources' ) ;
    public final void rule__CompareJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1859:1: ( ( 'resources' ) )
            // InternalGrana.g:1860:1: ( 'resources' )
            {
            // InternalGrana.g:1860:1: ( 'resources' )
            // InternalGrana.g:1861:1: 'resources'
            {
             before(grammarAccess.getCompareJobAccess().getResourcesKeyword_2()); 
            match(input,18,FOLLOW_2); 
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
    // InternalGrana.g:1874:1: rule__CompareJob__Group__3 : rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 ;
    public final void rule__CompareJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1878:1: ( rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 )
            // InternalGrana.g:1879:2: rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4
            {
            pushFollow(FOLLOW_11);
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
    // InternalGrana.g:1886:1: rule__CompareJob__Group__3__Impl : ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) ;
    public final void rule__CompareJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1890:1: ( ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) )
            // InternalGrana.g:1891:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            {
            // InternalGrana.g:1891:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            // InternalGrana.g:1892:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            {
            // InternalGrana.g:1892:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) )
            // InternalGrana.g:1893:1: ( rule__CompareJob__ResourcesAssignment_3 )
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:1894:1: ( rule__CompareJob__ResourcesAssignment_3 )
            // InternalGrana.g:1894:2: rule__CompareJob__ResourcesAssignment_3
            {
            pushFollow(FOLLOW_12);
            rule__CompareJob__ResourcesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 

            }

            // InternalGrana.g:1897:1: ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            // InternalGrana.g:1898:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:1899:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_STRING||LA24_0==33) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalGrana.g:1899:2: rule__CompareJob__ResourcesAssignment_3
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__CompareJob__ResourcesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // InternalGrana.g:1910:1: rule__CompareJob__Group__4 : rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 ;
    public final void rule__CompareJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1914:1: ( rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 )
            // InternalGrana.g:1915:2: rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1922:1: rule__CompareJob__Group__4__Impl : ( 'layoutoptions' ) ;
    public final void rule__CompareJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1926:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:1927:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:1927:1: ( 'layoutoptions' )
            // InternalGrana.g:1928:1: 'layoutoptions'
            {
             before(grammarAccess.getCompareJobAccess().getLayoutoptionsKeyword_4()); 
            match(input,19,FOLLOW_2); 
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
    // InternalGrana.g:1941:1: rule__CompareJob__Group__5 : rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 ;
    public final void rule__CompareJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1945:1: ( rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 )
            // InternalGrana.g:1946:2: rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:1953:1: rule__CompareJob__Group__5__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) ;
    public final void rule__CompareJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1957:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) )
            // InternalGrana.g:1958:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            {
            // InternalGrana.g:1958:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            // InternalGrana.g:1959:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_5()); 
            // InternalGrana.g:1960:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            // InternalGrana.g:1960:2: rule__CompareJob__LayoutOptionsAssignment_5
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
    // InternalGrana.g:1970:1: rule__CompareJob__Group__6 : rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 ;
    public final void rule__CompareJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1974:1: ( rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 )
            // InternalGrana.g:1975:2: rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7
            {
            pushFollow(FOLLOW_13);
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
    // InternalGrana.g:1982:1: rule__CompareJob__Group__6__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) ;
    public final void rule__CompareJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1986:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) )
            // InternalGrana.g:1987:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            {
            // InternalGrana.g:1987:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:1988:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:1989:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:1989:2: rule__CompareJob__LayoutOptionsAssignment_6
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
    // InternalGrana.g:1999:1: rule__CompareJob__Group__7 : rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 ;
    public final void rule__CompareJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2003:1: ( rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 )
            // InternalGrana.g:2004:2: rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:2011:1: rule__CompareJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__CompareJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2015:1: ( ( 'analyses' ) )
            // InternalGrana.g:2016:1: ( 'analyses' )
            {
            // InternalGrana.g:2016:1: ( 'analyses' )
            // InternalGrana.g:2017:1: 'analyses'
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesKeyword_7()); 
            match(input,20,FOLLOW_2); 
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
    // InternalGrana.g:2030:1: rule__CompareJob__Group__8 : rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 ;
    public final void rule__CompareJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2034:1: ( rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 )
            // InternalGrana.g:2035:2: rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9
            {
            pushFollow(FOLLOW_14);
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
    // InternalGrana.g:2042:1: rule__CompareJob__Group__8__Impl : ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__CompareJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2046:1: ( ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2047:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2047:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2048:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2048:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2049:1: ( rule__CompareJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2050:1: ( rule__CompareJob__AnalysesAssignment_8 )
            // InternalGrana.g:2050:2: rule__CompareJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__CompareJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2053:1: ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2054:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2055:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_ID) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalGrana.g:2055:2: rule__CompareJob__AnalysesAssignment_8
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__CompareJob__AnalysesAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // InternalGrana.g:2066:1: rule__CompareJob__Group__9 : rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 ;
    public final void rule__CompareJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2070:1: ( rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 )
            // InternalGrana.g:2071:2: rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10
            {
            pushFollow(FOLLOW_15);
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
    // InternalGrana.g:2078:1: rule__CompareJob__Group__9__Impl : ( 'output' ) ;
    public final void rule__CompareJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2082:1: ( ( 'output' ) )
            // InternalGrana.g:2083:1: ( 'output' )
            {
            // InternalGrana.g:2083:1: ( 'output' )
            // InternalGrana.g:2084:1: 'output'
            {
             before(grammarAccess.getCompareJobAccess().getOutputKeyword_9()); 
            match(input,21,FOLLOW_2); 
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
    // InternalGrana.g:2097:1: rule__CompareJob__Group__10 : rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 ;
    public final void rule__CompareJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2101:1: ( rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 )
            // InternalGrana.g:2102:2: rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11
            {
            pushFollow(FOLLOW_15);
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
    // InternalGrana.g:2109:1: rule__CompareJob__Group__10__Impl : ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) ;
    public final void rule__CompareJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2113:1: ( ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) )
            // InternalGrana.g:2114:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            {
            // InternalGrana.g:2114:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            // InternalGrana.g:2115:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            {
             before(grammarAccess.getCompareJobAccess().getOutputTypeAssignment_10()); 
            // InternalGrana.g:2116:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=12 && LA26_0<=13)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalGrana.g:2116:2: rule__CompareJob__OutputTypeAssignment_10
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
    // InternalGrana.g:2126:1: rule__CompareJob__Group__11 : rule__CompareJob__Group__11__Impl ;
    public final void rule__CompareJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2130:1: ( rule__CompareJob__Group__11__Impl )
            // InternalGrana.g:2131:2: rule__CompareJob__Group__11__Impl
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
    // InternalGrana.g:2137:1: rule__CompareJob__Group__11__Impl : ( ( rule__CompareJob__OutputAssignment_11 ) ) ;
    public final void rule__CompareJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2141:1: ( ( ( rule__CompareJob__OutputAssignment_11 ) ) )
            // InternalGrana.g:2142:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            {
            // InternalGrana.g:2142:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            // InternalGrana.g:2143:1: ( rule__CompareJob__OutputAssignment_11 )
            {
             before(grammarAccess.getCompareJobAccess().getOutputAssignment_11()); 
            // InternalGrana.g:2144:1: ( rule__CompareJob__OutputAssignment_11 )
            // InternalGrana.g:2144:2: rule__CompareJob__OutputAssignment_11
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
    // InternalGrana.g:2178:1: rule__RangeJob__Group__0 : rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 ;
    public final void rule__RangeJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2182:1: ( rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 )
            // InternalGrana.g:2183:2: rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:2190:1: rule__RangeJob__Group__0__Impl : ( 'rangejob' ) ;
    public final void rule__RangeJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2194:1: ( ( 'rangejob' ) )
            // InternalGrana.g:2195:1: ( 'rangejob' )
            {
            // InternalGrana.g:2195:1: ( 'rangejob' )
            // InternalGrana.g:2196:1: 'rangejob'
            {
             before(grammarAccess.getRangeJobAccess().getRangejobKeyword_0()); 
            match(input,23,FOLLOW_2); 
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
    // InternalGrana.g:2209:1: rule__RangeJob__Group__1 : rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 ;
    public final void rule__RangeJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2213:1: ( rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 )
            // InternalGrana.g:2214:2: rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2
            {
            pushFollow(FOLLOW_17);
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
    // InternalGrana.g:2221:1: rule__RangeJob__Group__1__Impl : ( ( rule__RangeJob__NameAssignment_1 ) ) ;
    public final void rule__RangeJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2225:1: ( ( ( rule__RangeJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2226:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2226:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            // InternalGrana.g:2227:1: ( rule__RangeJob__NameAssignment_1 )
            {
             before(grammarAccess.getRangeJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2228:1: ( rule__RangeJob__NameAssignment_1 )
            // InternalGrana.g:2228:2: rule__RangeJob__NameAssignment_1
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
    // InternalGrana.g:2238:1: rule__RangeJob__Group__2 : rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 ;
    public final void rule__RangeJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2242:1: ( rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 )
            // InternalGrana.g:2243:2: rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3
            {
            pushFollow(FOLLOW_17);
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
    // InternalGrana.g:2250:1: rule__RangeJob__Group__2__Impl : ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) ;
    public final void rule__RangeJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2254:1: ( ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) )
            // InternalGrana.g:2255:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            {
            // InternalGrana.g:2255:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            // InternalGrana.g:2256:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeAssignment_2()); 
            // InternalGrana.g:2257:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==42) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalGrana.g:2257:2: rule__RangeJob__MeasureExecutionTimeAssignment_2
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
    // InternalGrana.g:2267:1: rule__RangeJob__Group__3 : rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 ;
    public final void rule__RangeJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2271:1: ( rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 )
            // InternalGrana.g:2272:2: rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4
            {
            pushFollow(FOLLOW_10);
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
    // InternalGrana.g:2279:1: rule__RangeJob__Group__3__Impl : ( 'resources' ) ;
    public final void rule__RangeJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2283:1: ( ( 'resources' ) )
            // InternalGrana.g:2284:1: ( 'resources' )
            {
            // InternalGrana.g:2284:1: ( 'resources' )
            // InternalGrana.g:2285:1: 'resources'
            {
             before(grammarAccess.getRangeJobAccess().getResourcesKeyword_3()); 
            match(input,18,FOLLOW_2); 
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
    // InternalGrana.g:2298:1: rule__RangeJob__Group__4 : rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 ;
    public final void rule__RangeJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2302:1: ( rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 )
            // InternalGrana.g:2303:2: rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5
            {
            pushFollow(FOLLOW_11);
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
    // InternalGrana.g:2310:1: rule__RangeJob__Group__4__Impl : ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) ;
    public final void rule__RangeJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2314:1: ( ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) )
            // InternalGrana.g:2315:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            {
            // InternalGrana.g:2315:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            // InternalGrana.g:2316:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            {
            // InternalGrana.g:2316:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) )
            // InternalGrana.g:2317:1: ( rule__RangeJob__ResourcesAssignment_4 )
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2318:1: ( rule__RangeJob__ResourcesAssignment_4 )
            // InternalGrana.g:2318:2: rule__RangeJob__ResourcesAssignment_4
            {
            pushFollow(FOLLOW_12);
            rule__RangeJob__ResourcesAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 

            }

            // InternalGrana.g:2321:1: ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            // InternalGrana.g:2322:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2323:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_STRING||LA28_0==33) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalGrana.g:2323:2: rule__RangeJob__ResourcesAssignment_4
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__RangeJob__ResourcesAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
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
    // InternalGrana.g:2334:1: rule__RangeJob__Group__5 : rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 ;
    public final void rule__RangeJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2338:1: ( rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 )
            // InternalGrana.g:2339:2: rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:2346:1: rule__RangeJob__Group__5__Impl : ( 'layoutoptions' ) ;
    public final void rule__RangeJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2350:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2351:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2351:1: ( 'layoutoptions' )
            // InternalGrana.g:2352:1: 'layoutoptions'
            {
             before(grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_5()); 
            match(input,19,FOLLOW_2); 
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
    // InternalGrana.g:2365:1: rule__RangeJob__Group__6 : rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 ;
    public final void rule__RangeJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2369:1: ( rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 )
            // InternalGrana.g:2370:2: rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7
            {
            pushFollow(FOLLOW_13);
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
    // InternalGrana.g:2377:1: rule__RangeJob__Group__6__Impl : ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) ;
    public final void rule__RangeJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2381:1: ( ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) )
            // InternalGrana.g:2382:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            {
            // InternalGrana.g:2382:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            // InternalGrana.g:2383:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            {
            // InternalGrana.g:2383:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2384:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2385:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2385:2: rule__RangeJob__LayoutOptionsAssignment_6
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__LayoutOptionsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 

            }

            // InternalGrana.g:2388:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            // InternalGrana.g:2389:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2390:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_ID) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalGrana.g:2390:2: rule__RangeJob__LayoutOptionsAssignment_6
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__LayoutOptionsAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
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
    // InternalGrana.g:2401:1: rule__RangeJob__Group__7 : rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 ;
    public final void rule__RangeJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2405:1: ( rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 )
            // InternalGrana.g:2406:2: rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:2413:1: rule__RangeJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__RangeJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2417:1: ( ( 'analyses' ) )
            // InternalGrana.g:2418:1: ( 'analyses' )
            {
            // InternalGrana.g:2418:1: ( 'analyses' )
            // InternalGrana.g:2419:1: 'analyses'
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesKeyword_7()); 
            match(input,20,FOLLOW_2); 
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
    // InternalGrana.g:2432:1: rule__RangeJob__Group__8 : rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 ;
    public final void rule__RangeJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2436:1: ( rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 )
            // InternalGrana.g:2437:2: rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9
            {
            pushFollow(FOLLOW_18);
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
    // InternalGrana.g:2444:1: rule__RangeJob__Group__8__Impl : ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__RangeJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2448:1: ( ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2449:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2449:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2450:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2450:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2451:1: ( rule__RangeJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2452:1: ( rule__RangeJob__AnalysesAssignment_8 )
            // InternalGrana.g:2452:2: rule__RangeJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2455:1: ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2456:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2457:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalGrana.g:2457:2: rule__RangeJob__AnalysesAssignment_8
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__AnalysesAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
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
    // InternalGrana.g:2468:1: rule__RangeJob__Group__9 : rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 ;
    public final void rule__RangeJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2472:1: ( rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 )
            // InternalGrana.g:2473:2: rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:2480:1: rule__RangeJob__Group__9__Impl : ( 'rangeoption' ) ;
    public final void rule__RangeJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2484:1: ( ( 'rangeoption' ) )
            // InternalGrana.g:2485:1: ( 'rangeoption' )
            {
            // InternalGrana.g:2485:1: ( 'rangeoption' )
            // InternalGrana.g:2486:1: 'rangeoption'
            {
             before(grammarAccess.getRangeJobAccess().getRangeoptionKeyword_9()); 
            match(input,24,FOLLOW_2); 
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
    // InternalGrana.g:2499:1: rule__RangeJob__Group__10 : rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 ;
    public final void rule__RangeJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2503:1: ( rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 )
            // InternalGrana.g:2504:2: rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11
            {
            pushFollow(FOLLOW_19);
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
    // InternalGrana.g:2511:1: rule__RangeJob__Group__10__Impl : ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) ;
    public final void rule__RangeJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2515:1: ( ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) )
            // InternalGrana.g:2516:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            {
            // InternalGrana.g:2516:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            // InternalGrana.g:2517:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_10()); 
            // InternalGrana.g:2518:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            // InternalGrana.g:2518:2: rule__RangeJob__RangeOptionAssignment_10
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
    // InternalGrana.g:2528:1: rule__RangeJob__Group__11 : rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 ;
    public final void rule__RangeJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2532:1: ( rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 )
            // InternalGrana.g:2533:2: rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12
            {
            pushFollow(FOLLOW_20);
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
    // InternalGrana.g:2540:1: rule__RangeJob__Group__11__Impl : ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) ;
    public final void rule__RangeJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2544:1: ( ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) )
            // InternalGrana.g:2545:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            {
            // InternalGrana.g:2545:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            // InternalGrana.g:2546:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_11()); 
            // InternalGrana.g:2547:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            // InternalGrana.g:2547:2: rule__RangeJob__RangeValuesAssignment_11
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
    // InternalGrana.g:2557:1: rule__RangeJob__Group__12 : rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 ;
    public final void rule__RangeJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2561:1: ( rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 )
            // InternalGrana.g:2562:2: rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13
            {
            pushFollow(FOLLOW_14);
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
    // InternalGrana.g:2569:1: rule__RangeJob__Group__12__Impl : ( ( rule__RangeJob__Alternatives_12 ) ) ;
    public final void rule__RangeJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2573:1: ( ( ( rule__RangeJob__Alternatives_12 ) ) )
            // InternalGrana.g:2574:1: ( ( rule__RangeJob__Alternatives_12 ) )
            {
            // InternalGrana.g:2574:1: ( ( rule__RangeJob__Alternatives_12 ) )
            // InternalGrana.g:2575:1: ( rule__RangeJob__Alternatives_12 )
            {
             before(grammarAccess.getRangeJobAccess().getAlternatives_12()); 
            // InternalGrana.g:2576:1: ( rule__RangeJob__Alternatives_12 )
            // InternalGrana.g:2576:2: rule__RangeJob__Alternatives_12
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
    // InternalGrana.g:2586:1: rule__RangeJob__Group__13 : rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 ;
    public final void rule__RangeJob__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2590:1: ( rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 )
            // InternalGrana.g:2591:2: rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14
            {
            pushFollow(FOLLOW_15);
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
    // InternalGrana.g:2598:1: rule__RangeJob__Group__13__Impl : ( 'output' ) ;
    public final void rule__RangeJob__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2602:1: ( ( 'output' ) )
            // InternalGrana.g:2603:1: ( 'output' )
            {
            // InternalGrana.g:2603:1: ( 'output' )
            // InternalGrana.g:2604:1: 'output'
            {
             before(grammarAccess.getRangeJobAccess().getOutputKeyword_13()); 
            match(input,21,FOLLOW_2); 
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
    // InternalGrana.g:2617:1: rule__RangeJob__Group__14 : rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 ;
    public final void rule__RangeJob__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2621:1: ( rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 )
            // InternalGrana.g:2622:2: rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15
            {
            pushFollow(FOLLOW_15);
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
    // InternalGrana.g:2629:1: rule__RangeJob__Group__14__Impl : ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) ;
    public final void rule__RangeJob__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2633:1: ( ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) )
            // InternalGrana.g:2634:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            {
            // InternalGrana.g:2634:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            // InternalGrana.g:2635:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            {
             before(grammarAccess.getRangeJobAccess().getOutputTypeAssignment_14()); 
            // InternalGrana.g:2636:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=12 && LA31_0<=13)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalGrana.g:2636:2: rule__RangeJob__OutputTypeAssignment_14
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
    // InternalGrana.g:2646:1: rule__RangeJob__Group__15 : rule__RangeJob__Group__15__Impl ;
    public final void rule__RangeJob__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2650:1: ( rule__RangeJob__Group__15__Impl )
            // InternalGrana.g:2651:2: rule__RangeJob__Group__15__Impl
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
    // InternalGrana.g:2657:1: rule__RangeJob__Group__15__Impl : ( ( rule__RangeJob__OutputAssignment_15 ) ) ;
    public final void rule__RangeJob__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2661:1: ( ( ( rule__RangeJob__OutputAssignment_15 ) ) )
            // InternalGrana.g:2662:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            {
            // InternalGrana.g:2662:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            // InternalGrana.g:2663:1: ( rule__RangeJob__OutputAssignment_15 )
            {
             before(grammarAccess.getRangeJobAccess().getOutputAssignment_15()); 
            // InternalGrana.g:2664:1: ( rule__RangeJob__OutputAssignment_15 )
            // InternalGrana.g:2664:2: rule__RangeJob__OutputAssignment_15
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
    // InternalGrana.g:2706:1: rule__RangeJob__Group_12_0__0 : rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 ;
    public final void rule__RangeJob__Group_12_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2710:1: ( rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 )
            // InternalGrana.g:2711:2: rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:2718:1: rule__RangeJob__Group_12_0__0__Impl : ( 'rangeanalysis' ) ;
    public final void rule__RangeJob__Group_12_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2722:1: ( ( 'rangeanalysis' ) )
            // InternalGrana.g:2723:1: ( 'rangeanalysis' )
            {
            // InternalGrana.g:2723:1: ( 'rangeanalysis' )
            // InternalGrana.g:2724:1: 'rangeanalysis'
            {
             before(grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_12_0_0()); 
            match(input,25,FOLLOW_2); 
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
    // InternalGrana.g:2737:1: rule__RangeJob__Group_12_0__1 : rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 ;
    public final void rule__RangeJob__Group_12_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2741:1: ( rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 )
            // InternalGrana.g:2742:2: rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2
            {
            pushFollow(FOLLOW_21);
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
    // InternalGrana.g:2749:1: rule__RangeJob__Group_12_0__1__Impl : ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) ;
    public final void rule__RangeJob__Group_12_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2753:1: ( ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) )
            // InternalGrana.g:2754:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            {
            // InternalGrana.g:2754:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            // InternalGrana.g:2755:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12_0_1()); 
            // InternalGrana.g:2756:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            // InternalGrana.g:2756:2: rule__RangeJob__RangeAnalysisAssignment_12_0_1
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
    // InternalGrana.g:2766:1: rule__RangeJob__Group_12_0__2 : rule__RangeJob__Group_12_0__2__Impl ;
    public final void rule__RangeJob__Group_12_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2770:1: ( rule__RangeJob__Group_12_0__2__Impl )
            // InternalGrana.g:2771:2: rule__RangeJob__Group_12_0__2__Impl
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
    // InternalGrana.g:2777:1: rule__RangeJob__Group_12_0__2__Impl : ( ( rule__RangeJob__Group_12_0_2__0 )? ) ;
    public final void rule__RangeJob__Group_12_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2781:1: ( ( ( rule__RangeJob__Group_12_0_2__0 )? ) )
            // InternalGrana.g:2782:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            {
            // InternalGrana.g:2782:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            // InternalGrana.g:2783:1: ( rule__RangeJob__Group_12_0_2__0 )?
            {
             before(grammarAccess.getRangeJobAccess().getGroup_12_0_2()); 
            // InternalGrana.g:2784:1: ( rule__RangeJob__Group_12_0_2__0 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==26) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalGrana.g:2784:2: rule__RangeJob__Group_12_0_2__0
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
    // InternalGrana.g:2800:1: rule__RangeJob__Group_12_0_2__0 : rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 ;
    public final void rule__RangeJob__Group_12_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2804:1: ( rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 )
            // InternalGrana.g:2805:2: rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1
            {
            pushFollow(FOLLOW_22);
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
    // InternalGrana.g:2812:1: rule__RangeJob__Group_12_0_2__0__Impl : ( 'component' ) ;
    public final void rule__RangeJob__Group_12_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2816:1: ( ( 'component' ) )
            // InternalGrana.g:2817:1: ( 'component' )
            {
            // InternalGrana.g:2817:1: ( 'component' )
            // InternalGrana.g:2818:1: 'component'
            {
             before(grammarAccess.getRangeJobAccess().getComponentKeyword_12_0_2_0()); 
            match(input,26,FOLLOW_2); 
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
    // InternalGrana.g:2831:1: rule__RangeJob__Group_12_0_2__1 : rule__RangeJob__Group_12_0_2__1__Impl ;
    public final void rule__RangeJob__Group_12_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2835:1: ( rule__RangeJob__Group_12_0_2__1__Impl )
            // InternalGrana.g:2836:2: rule__RangeJob__Group_12_0_2__1__Impl
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
    // InternalGrana.g:2842:1: rule__RangeJob__Group_12_0_2__1__Impl : ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) ;
    public final void rule__RangeJob__Group_12_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2846:1: ( ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) )
            // InternalGrana.g:2847:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            {
            // InternalGrana.g:2847:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            // InternalGrana.g:2848:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_12_0_2_1()); 
            // InternalGrana.g:2849:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            // InternalGrana.g:2849:2: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1
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
    // InternalGrana.g:2863:1: rule__RangeJob__Group_12_1__0 : rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 ;
    public final void rule__RangeJob__Group_12_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2867:1: ( rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 )
            // InternalGrana.g:2868:2: rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:2875:1: rule__RangeJob__Group_12_1__0__Impl : ( 'rangeanalyses' ) ;
    public final void rule__RangeJob__Group_12_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2879:1: ( ( 'rangeanalyses' ) )
            // InternalGrana.g:2880:1: ( 'rangeanalyses' )
            {
            // InternalGrana.g:2880:1: ( 'rangeanalyses' )
            // InternalGrana.g:2881:1: 'rangeanalyses'
            {
             before(grammarAccess.getRangeJobAccess().getRangeanalysesKeyword_12_1_0()); 
            match(input,27,FOLLOW_2); 
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
    // InternalGrana.g:2894:1: rule__RangeJob__Group_12_1__1 : rule__RangeJob__Group_12_1__1__Impl ;
    public final void rule__RangeJob__Group_12_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2898:1: ( rule__RangeJob__Group_12_1__1__Impl )
            // InternalGrana.g:2899:2: rule__RangeJob__Group_12_1__1__Impl
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
    // InternalGrana.g:2905:1: rule__RangeJob__Group_12_1__1__Impl : ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) ;
    public final void rule__RangeJob__Group_12_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2909:1: ( ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) )
            // InternalGrana.g:2910:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            {
            // InternalGrana.g:2910:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            // InternalGrana.g:2911:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            {
            // InternalGrana.g:2911:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) )
            // InternalGrana.g:2912:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:2913:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            // InternalGrana.g:2913:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 

            }

            // InternalGrana.g:2916:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            // InternalGrana.g:2917:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:2918:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalGrana.g:2918:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
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
    // InternalGrana.g:2933:1: rule__FloatRange__Group__0 : rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 ;
    public final void rule__FloatRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2937:1: ( rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 )
            // InternalGrana.g:2938:2: rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1
            {
            pushFollow(FOLLOW_23);
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
    // InternalGrana.g:2945:1: rule__FloatRange__Group__0__Impl : ( 'floatvalues' ) ;
    public final void rule__FloatRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2949:1: ( ( 'floatvalues' ) )
            // InternalGrana.g:2950:1: ( 'floatvalues' )
            {
            // InternalGrana.g:2950:1: ( 'floatvalues' )
            // InternalGrana.g:2951:1: 'floatvalues'
            {
             before(grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0()); 
            match(input,28,FOLLOW_2); 
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
    // InternalGrana.g:2964:1: rule__FloatRange__Group__1 : rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 ;
    public final void rule__FloatRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2968:1: ( rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 )
            // InternalGrana.g:2969:2: rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2
            {
            pushFollow(FOLLOW_24);
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
    // InternalGrana.g:2976:1: rule__FloatRange__Group__1__Impl : ( ( rule__FloatRange__ValuesAssignment_1 ) ) ;
    public final void rule__FloatRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2980:1: ( ( ( rule__FloatRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:2981:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:2981:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            // InternalGrana.g:2982:1: ( rule__FloatRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:2983:1: ( rule__FloatRange__ValuesAssignment_1 )
            // InternalGrana.g:2983:2: rule__FloatRange__ValuesAssignment_1
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
    // InternalGrana.g:2993:1: rule__FloatRange__Group__2 : rule__FloatRange__Group__2__Impl ;
    public final void rule__FloatRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2997:1: ( rule__FloatRange__Group__2__Impl )
            // InternalGrana.g:2998:2: rule__FloatRange__Group__2__Impl
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
    // InternalGrana.g:3004:1: rule__FloatRange__Group__2__Impl : ( ( rule__FloatRange__Group_2__0 )* ) ;
    public final void rule__FloatRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3008:1: ( ( ( rule__FloatRange__Group_2__0 )* ) )
            // InternalGrana.g:3009:1: ( ( rule__FloatRange__Group_2__0 )* )
            {
            // InternalGrana.g:3009:1: ( ( rule__FloatRange__Group_2__0 )* )
            // InternalGrana.g:3010:1: ( rule__FloatRange__Group_2__0 )*
            {
             before(grammarAccess.getFloatRangeAccess().getGroup_2()); 
            // InternalGrana.g:3011:1: ( rule__FloatRange__Group_2__0 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==29) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalGrana.g:3011:2: rule__FloatRange__Group_2__0
            	    {
            	    pushFollow(FOLLOW_25);
            	    rule__FloatRange__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
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
    // InternalGrana.g:3027:1: rule__FloatRange__Group_2__0 : rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 ;
    public final void rule__FloatRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3031:1: ( rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 )
            // InternalGrana.g:3032:2: rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1
            {
            pushFollow(FOLLOW_23);
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
    // InternalGrana.g:3039:1: rule__FloatRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__FloatRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3043:1: ( ( ',' ) )
            // InternalGrana.g:3044:1: ( ',' )
            {
            // InternalGrana.g:3044:1: ( ',' )
            // InternalGrana.g:3045:1: ','
            {
             before(grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0()); 
            match(input,29,FOLLOW_2); 
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
    // InternalGrana.g:3058:1: rule__FloatRange__Group_2__1 : rule__FloatRange__Group_2__1__Impl ;
    public final void rule__FloatRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3062:1: ( rule__FloatRange__Group_2__1__Impl )
            // InternalGrana.g:3063:2: rule__FloatRange__Group_2__1__Impl
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
    // InternalGrana.g:3069:1: rule__FloatRange__Group_2__1__Impl : ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__FloatRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3073:1: ( ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3074:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3074:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3075:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3076:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            // InternalGrana.g:3076:2: rule__FloatRange__ValuesAssignment_2_1
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
    // InternalGrana.g:3090:1: rule__IntRangeValues__Group__0 : rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 ;
    public final void rule__IntRangeValues__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3094:1: ( rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 )
            // InternalGrana.g:3095:2: rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1
            {
            pushFollow(FOLLOW_22);
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
    // InternalGrana.g:3102:1: rule__IntRangeValues__Group__0__Impl : ( 'intvalues' ) ;
    public final void rule__IntRangeValues__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3106:1: ( ( 'intvalues' ) )
            // InternalGrana.g:3107:1: ( 'intvalues' )
            {
            // InternalGrana.g:3107:1: ( 'intvalues' )
            // InternalGrana.g:3108:1: 'intvalues'
            {
             before(grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0()); 
            match(input,30,FOLLOW_2); 
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
    // InternalGrana.g:3121:1: rule__IntRangeValues__Group__1 : rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 ;
    public final void rule__IntRangeValues__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3125:1: ( rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 )
            // InternalGrana.g:3126:2: rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2
            {
            pushFollow(FOLLOW_24);
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
    // InternalGrana.g:3133:1: rule__IntRangeValues__Group__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) ;
    public final void rule__IntRangeValues__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3137:1: ( ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3138:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3138:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            // InternalGrana.g:3139:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3140:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            // InternalGrana.g:3140:2: rule__IntRangeValues__ValuesAssignment_1
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
    // InternalGrana.g:3150:1: rule__IntRangeValues__Group__2 : rule__IntRangeValues__Group__2__Impl ;
    public final void rule__IntRangeValues__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3154:1: ( rule__IntRangeValues__Group__2__Impl )
            // InternalGrana.g:3155:2: rule__IntRangeValues__Group__2__Impl
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
    // InternalGrana.g:3161:1: rule__IntRangeValues__Group__2__Impl : ( ( rule__IntRangeValues__Group_2__0 )* ) ;
    public final void rule__IntRangeValues__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3165:1: ( ( ( rule__IntRangeValues__Group_2__0 )* ) )
            // InternalGrana.g:3166:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            {
            // InternalGrana.g:3166:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            // InternalGrana.g:3167:1: ( rule__IntRangeValues__Group_2__0 )*
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup_2()); 
            // InternalGrana.g:3168:1: ( rule__IntRangeValues__Group_2__0 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==29) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalGrana.g:3168:2: rule__IntRangeValues__Group_2__0
            	    {
            	    pushFollow(FOLLOW_25);
            	    rule__IntRangeValues__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
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
    // InternalGrana.g:3184:1: rule__IntRangeValues__Group_2__0 : rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 ;
    public final void rule__IntRangeValues__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3188:1: ( rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 )
            // InternalGrana.g:3189:2: rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1
            {
            pushFollow(FOLLOW_22);
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
    // InternalGrana.g:3196:1: rule__IntRangeValues__Group_2__0__Impl : ( ',' ) ;
    public final void rule__IntRangeValues__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3200:1: ( ( ',' ) )
            // InternalGrana.g:3201:1: ( ',' )
            {
            // InternalGrana.g:3201:1: ( ',' )
            // InternalGrana.g:3202:1: ','
            {
             before(grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0()); 
            match(input,29,FOLLOW_2); 
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
    // InternalGrana.g:3215:1: rule__IntRangeValues__Group_2__1 : rule__IntRangeValues__Group_2__1__Impl ;
    public final void rule__IntRangeValues__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3219:1: ( rule__IntRangeValues__Group_2__1__Impl )
            // InternalGrana.g:3220:2: rule__IntRangeValues__Group_2__1__Impl
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
    // InternalGrana.g:3226:1: rule__IntRangeValues__Group_2__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) ;
    public final void rule__IntRangeValues__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3230:1: ( ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3231:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3231:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3232:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3233:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            // InternalGrana.g:3233:2: rule__IntRangeValues__ValuesAssignment_2_1
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
    // InternalGrana.g:3247:1: rule__IntRangeRange__Group__0 : rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 ;
    public final void rule__IntRangeRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3251:1: ( rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 )
            // InternalGrana.g:3252:2: rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1
            {
            pushFollow(FOLLOW_22);
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
    // InternalGrana.g:3259:1: rule__IntRangeRange__Group__0__Impl : ( 'intrange' ) ;
    public final void rule__IntRangeRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3263:1: ( ( 'intrange' ) )
            // InternalGrana.g:3264:1: ( 'intrange' )
            {
            // InternalGrana.g:3264:1: ( 'intrange' )
            // InternalGrana.g:3265:1: 'intrange'
            {
             before(grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0()); 
            match(input,31,FOLLOW_2); 
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
    // InternalGrana.g:3278:1: rule__IntRangeRange__Group__1 : rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 ;
    public final void rule__IntRangeRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3282:1: ( rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 )
            // InternalGrana.g:3283:2: rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2
            {
            pushFollow(FOLLOW_26);
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
    // InternalGrana.g:3290:1: rule__IntRangeRange__Group__1__Impl : ( ( rule__IntRangeRange__StartAssignment_1 ) ) ;
    public final void rule__IntRangeRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3294:1: ( ( ( rule__IntRangeRange__StartAssignment_1 ) ) )
            // InternalGrana.g:3295:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            {
            // InternalGrana.g:3295:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            // InternalGrana.g:3296:1: ( rule__IntRangeRange__StartAssignment_1 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1()); 
            // InternalGrana.g:3297:1: ( rule__IntRangeRange__StartAssignment_1 )
            // InternalGrana.g:3297:2: rule__IntRangeRange__StartAssignment_1
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
    // InternalGrana.g:3307:1: rule__IntRangeRange__Group__2 : rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 ;
    public final void rule__IntRangeRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3311:1: ( rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 )
            // InternalGrana.g:3312:2: rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3
            {
            pushFollow(FOLLOW_22);
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
    // InternalGrana.g:3319:1: rule__IntRangeRange__Group__2__Impl : ( 'to' ) ;
    public final void rule__IntRangeRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3323:1: ( ( 'to' ) )
            // InternalGrana.g:3324:1: ( 'to' )
            {
            // InternalGrana.g:3324:1: ( 'to' )
            // InternalGrana.g:3325:1: 'to'
            {
             before(grammarAccess.getIntRangeRangeAccess().getToKeyword_2()); 
            match(input,32,FOLLOW_2); 
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
    // InternalGrana.g:3338:1: rule__IntRangeRange__Group__3 : rule__IntRangeRange__Group__3__Impl ;
    public final void rule__IntRangeRange__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3342:1: ( rule__IntRangeRange__Group__3__Impl )
            // InternalGrana.g:3343:2: rule__IntRangeRange__Group__3__Impl
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
    // InternalGrana.g:3349:1: rule__IntRangeRange__Group__3__Impl : ( ( rule__IntRangeRange__EndAssignment_3 ) ) ;
    public final void rule__IntRangeRange__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3353:1: ( ( ( rule__IntRangeRange__EndAssignment_3 ) ) )
            // InternalGrana.g:3354:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            {
            // InternalGrana.g:3354:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            // InternalGrana.g:3355:1: ( rule__IntRangeRange__EndAssignment_3 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3()); 
            // InternalGrana.g:3356:1: ( rule__IntRangeRange__EndAssignment_3 )
            // InternalGrana.g:3356:2: rule__IntRangeRange__EndAssignment_3
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
    // InternalGrana.g:3374:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3378:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // InternalGrana.g:3379:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:3386:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3390:1: ( ( 'ref' ) )
            // InternalGrana.g:3391:1: ( 'ref' )
            {
            // InternalGrana.g:3391:1: ( 'ref' )
            // InternalGrana.g:3392:1: 'ref'
            {
             before(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:3405:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3409:1: ( rule__ResourceReference__Group__1__Impl )
            // InternalGrana.g:3410:2: rule__ResourceReference__Group__1__Impl
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
    // InternalGrana.g:3416:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3420:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // InternalGrana.g:3421:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // InternalGrana.g:3421:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // InternalGrana.g:3422:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // InternalGrana.g:3422:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // InternalGrana.g:3423:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:3424:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // InternalGrana.g:3424:2: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_3);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // InternalGrana.g:3427:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // InternalGrana.g:3428:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:3429:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalGrana.g:3429:2: rule__ResourceReference__ResourceRefsAssignment_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ResourceReference__ResourceRefsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop36;
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
    // InternalGrana.g:3444:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3448:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // InternalGrana.g:3449:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
            {
            pushFollow(FOLLOW_10);
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
    // InternalGrana.g:3456:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3460:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // InternalGrana.g:3461:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:3461:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // InternalGrana.g:3462:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:3463:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            // InternalGrana.g:3463:2: rule__GlobalResourceRef__NameAssignment_0
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
    // InternalGrana.g:3473:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3477:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // InternalGrana.g:3478:2: rule__GlobalResourceRef__Group__1__Impl
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
    // InternalGrana.g:3484:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3488:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // InternalGrana.g:3489:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // InternalGrana.g:3489:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // InternalGrana.g:3490:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // InternalGrana.g:3491:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // InternalGrana.g:3491:2: rule__GlobalResourceRef__ResourcesAssignment_1
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
    // InternalGrana.g:3505:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3509:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // InternalGrana.g:3510:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
            {
            pushFollow(FOLLOW_27);
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
    // InternalGrana.g:3517:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3521:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // InternalGrana.g:3522:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // InternalGrana.g:3522:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // InternalGrana.g:3523:1: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // InternalGrana.g:3524:1: ( rule__LocalResource__PathAssignment_0 )
            // InternalGrana.g:3524:2: rule__LocalResource__PathAssignment_0
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
    // InternalGrana.g:3534:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3538:1: ( rule__LocalResource__Group__1__Impl )
            // InternalGrana.g:3539:2: rule__LocalResource__Group__1__Impl
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
    // InternalGrana.g:3545:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3549:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // InternalGrana.g:3550:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // InternalGrana.g:3550:1: ( ( rule__LocalResource__Group_1__0 ) )
            // InternalGrana.g:3551:1: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // InternalGrana.g:3552:1: ( rule__LocalResource__Group_1__0 )
            // InternalGrana.g:3552:2: rule__LocalResource__Group_1__0
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
    // InternalGrana.g:3566:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3570:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // InternalGrana.g:3571:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
            {
            pushFollow(FOLLOW_28);
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
    // InternalGrana.g:3578:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3582:1: ( ( 'filter' ) )
            // InternalGrana.g:3583:1: ( 'filter' )
            {
            // InternalGrana.g:3583:1: ( 'filter' )
            // InternalGrana.g:3584:1: 'filter'
            {
             before(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 
            match(input,34,FOLLOW_2); 
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
    // InternalGrana.g:3597:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3601:1: ( rule__LocalResource__Group_1__1__Impl )
            // InternalGrana.g:3602:2: rule__LocalResource__Group_1__1__Impl
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
    // InternalGrana.g:3608:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3612:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // InternalGrana.g:3613:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // InternalGrana.g:3613:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // InternalGrana.g:3614:1: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // InternalGrana.g:3615:1: ( rule__LocalResource__FilterAssignment_1_1 )
            // InternalGrana.g:3615:2: rule__LocalResource__FilterAssignment_1_1
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
    // InternalGrana.g:3629:1: rule__GlobalOutputRef__Group__0 : rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 ;
    public final void rule__GlobalOutputRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3633:1: ( rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 )
            // InternalGrana.g:3634:2: rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1
            {
            pushFollow(FOLLOW_15);
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
    // InternalGrana.g:3641:1: rule__GlobalOutputRef__Group__0__Impl : ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalOutputRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3645:1: ( ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) )
            // InternalGrana.g:3646:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:3646:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            // InternalGrana.g:3647:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:3648:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            // InternalGrana.g:3648:2: rule__GlobalOutputRef__NameAssignment_0
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
    // InternalGrana.g:3658:1: rule__GlobalOutputRef__Group__1 : rule__GlobalOutputRef__Group__1__Impl ;
    public final void rule__GlobalOutputRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3662:1: ( rule__GlobalOutputRef__Group__1__Impl )
            // InternalGrana.g:3663:2: rule__GlobalOutputRef__Group__1__Impl
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
    // InternalGrana.g:3669:1: rule__GlobalOutputRef__Group__1__Impl : ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) ;
    public final void rule__GlobalOutputRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3673:1: ( ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) )
            // InternalGrana.g:3674:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            {
            // InternalGrana.g:3674:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            // InternalGrana.g:3675:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 
            // InternalGrana.g:3676:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            // InternalGrana.g:3676:2: rule__GlobalOutputRef__OutputAssignment_1
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
    // InternalGrana.g:3690:1: rule__OutputReference__Group__0 : rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 ;
    public final void rule__OutputReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3694:1: ( rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 )
            // InternalGrana.g:3695:2: rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalGrana.g:3702:1: rule__OutputReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__OutputReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3706:1: ( ( 'ref' ) )
            // InternalGrana.g:3707:1: ( 'ref' )
            {
            // InternalGrana.g:3707:1: ( 'ref' )
            // InternalGrana.g:3708:1: 'ref'
            {
             before(grammarAccess.getOutputReferenceAccess().getRefKeyword_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:3721:1: rule__OutputReference__Group__1 : rule__OutputReference__Group__1__Impl ;
    public final void rule__OutputReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3725:1: ( rule__OutputReference__Group__1__Impl )
            // InternalGrana.g:3726:2: rule__OutputReference__Group__1__Impl
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
    // InternalGrana.g:3732:1: rule__OutputReference__Group__1__Impl : ( ( rule__OutputReference__OutputRefAssignment_1 ) ) ;
    public final void rule__OutputReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3736:1: ( ( ( rule__OutputReference__OutputRefAssignment_1 ) ) )
            // InternalGrana.g:3737:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            {
            // InternalGrana.g:3737:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            // InternalGrana.g:3738:1: ( rule__OutputReference__OutputRefAssignment_1 )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 
            // InternalGrana.g:3739:1: ( rule__OutputReference__OutputRefAssignment_1 )
            // InternalGrana.g:3739:2: rule__OutputReference__OutputRefAssignment_1
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


    // $ANTLR start "rule__KIdentifier__Group__0"
    // InternalGrana.g:3753:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3757:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // InternalGrana.g:3758:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3765:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3769:1: ( ( () ) )
            // InternalGrana.g:3770:1: ( () )
            {
            // InternalGrana.g:3770:1: ( () )
            // InternalGrana.g:3771:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // InternalGrana.g:3772:1: ()
            // InternalGrana.g:3774:1: 
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
    // InternalGrana.g:3784:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3788:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // InternalGrana.g:3789:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FOLLOW_29);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3796:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3800:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // InternalGrana.g:3801:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // InternalGrana.g:3801:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // InternalGrana.g:3802:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // InternalGrana.g:3803:1: ( rule__KIdentifier__IdAssignment_1 )
            // InternalGrana.g:3803:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3813:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3817:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // InternalGrana.g:3818:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FOLLOW_30);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3825:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3829:1: ( ( '{' ) )
            // InternalGrana.g:3830:1: ( '{' )
            {
            // InternalGrana.g:3830:1: ( '{' )
            // InternalGrana.g:3831:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,35,FOLLOW_2); 
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
    // InternalGrana.g:3844:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3848:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // InternalGrana.g:3849:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FOLLOW_30);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3856:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3860:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // InternalGrana.g:3861:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // InternalGrana.g:3861:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // InternalGrana.g:3862:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // InternalGrana.g:3863:1: ( rule__KIdentifier__Group_3__0 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==RULE_ID) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalGrana.g:3863:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3873:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3877:1: ( rule__KIdentifier__Group__4__Impl )
            // InternalGrana.g:3878:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3884:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3888:1: ( ( '}' ) )
            // InternalGrana.g:3889:1: ( '}' )
            {
            // InternalGrana.g:3889:1: ( '}' )
            // InternalGrana.g:3890:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,36,FOLLOW_2); 
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
    // InternalGrana.g:3913:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3917:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // InternalGrana.g:3918:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FOLLOW_7);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3925:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3929:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // InternalGrana.g:3930:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // InternalGrana.g:3930:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // InternalGrana.g:3931:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // InternalGrana.g:3932:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // InternalGrana.g:3932:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3942:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3946:1: ( rule__KIdentifier__Group_3__1__Impl )
            // InternalGrana.g:3947:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3953:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3957:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // InternalGrana.g:3958:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // InternalGrana.g:3958:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // InternalGrana.g:3959:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // InternalGrana.g:3960:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==RULE_ID) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalGrana.g:3960:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__KIdentifier__PersistentEntriesAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
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
    // InternalGrana.g:3974:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3978:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // InternalGrana.g:3979:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FOLLOW_31);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3986:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3990:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // InternalGrana.g:3991:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // InternalGrana.g:3991:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // InternalGrana.g:3992:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // InternalGrana.g:3993:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // InternalGrana.g:3993:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4003:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4007:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // InternalGrana.g:4008:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FOLLOW_32);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4015:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4019:1: ( ( ':' ) )
            // InternalGrana.g:4020:1: ( ':' )
            {
            // InternalGrana.g:4020:1: ( ':' )
            // InternalGrana.g:4021:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,37,FOLLOW_2); 
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
    // InternalGrana.g:4034:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4038:1: ( rule__PersistentEntry__Group__2__Impl )
            // InternalGrana.g:4039:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4045:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4049:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // InternalGrana.g:4050:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // InternalGrana.g:4050:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // InternalGrana.g:4051:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // InternalGrana.g:4052:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // InternalGrana.g:4052:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4068:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4072:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // InternalGrana.g:4073:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FOLLOW_33);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4080:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4084:1: ( ( RULE_ID ) )
            // InternalGrana.g:4085:1: ( RULE_ID )
            {
            // InternalGrana.g:4085:1: ( RULE_ID )
            // InternalGrana.g:4086:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:4097:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4101:1: ( rule__QualifiedID__Group__1__Impl )
            // InternalGrana.g:4102:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4108:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4112:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // InternalGrana.g:4113:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // InternalGrana.g:4113:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // InternalGrana.g:4114:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // InternalGrana.g:4115:1: ( rule__QualifiedID__Group_1__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==38) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalGrana.g:4115:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FOLLOW_34);
            	    rule__QualifiedID__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
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
    // InternalGrana.g:4129:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4133:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // InternalGrana.g:4134:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FOLLOW_7);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4141:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4145:1: ( ( '.' ) )
            // InternalGrana.g:4146:1: ( '.' )
            {
            // InternalGrana.g:4146:1: ( '.' )
            // InternalGrana.g:4147:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4160:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4164:1: ( rule__QualifiedID__Group_1__1__Impl )
            // InternalGrana.g:4165:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4171:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4175:1: ( ( RULE_ID ) )
            // InternalGrana.g:4176:1: ( RULE_ID )
            {
            // InternalGrana.g:4176:1: ( RULE_ID )
            // InternalGrana.g:4177:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:4193:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4197:1: ( ( ruleGlobalResourceRef ) )
            // InternalGrana.g:4198:1: ( ruleGlobalResourceRef )
            {
            // InternalGrana.g:4198:1: ( ruleGlobalResourceRef )
            // InternalGrana.g:4199:1: ruleGlobalResourceRef
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
    // InternalGrana.g:4208:1: rule__Grana__GloobalOutputsAssignment_1_1 : ( ruleGlobalOutputRef ) ;
    public final void rule__Grana__GloobalOutputsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4212:1: ( ( ruleGlobalOutputRef ) )
            // InternalGrana.g:4213:1: ( ruleGlobalOutputRef )
            {
            // InternalGrana.g:4213:1: ( ruleGlobalOutputRef )
            // InternalGrana.g:4214:1: ruleGlobalOutputRef
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
    // InternalGrana.g:4223:1: rule__Grana__ParallelAssignment_2_1 : ( ( 'parallel' ) ) ;
    public final void rule__Grana__ParallelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4227:1: ( ( ( 'parallel' ) ) )
            // InternalGrana.g:4228:1: ( ( 'parallel' ) )
            {
            // InternalGrana.g:4228:1: ( ( 'parallel' ) )
            // InternalGrana.g:4229:1: ( 'parallel' )
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            // InternalGrana.g:4230:1: ( 'parallel' )
            // InternalGrana.g:4231:1: 'parallel'
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            match(input,39,FOLLOW_2); 
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
    // InternalGrana.g:4246:1: rule__Grana__ExecuteAllAssignment_2_2_0 : ( ( 'all' ) ) ;
    public final void rule__Grana__ExecuteAllAssignment_2_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4250:1: ( ( ( 'all' ) ) )
            // InternalGrana.g:4251:1: ( ( 'all' ) )
            {
            // InternalGrana.g:4251:1: ( ( 'all' ) )
            // InternalGrana.g:4252:1: ( 'all' )
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            // InternalGrana.g:4253:1: ( 'all' )
            // InternalGrana.g:4254:1: 'all'
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            match(input,40,FOLLOW_2); 
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
    // InternalGrana.g:4269:1: rule__Grana__ExecuteAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__Grana__ExecuteAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4273:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:4274:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:4274:1: ( ( RULE_ID ) )
            // InternalGrana.g:4275:1: ( RULE_ID )
            {
             before(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_2_1_0()); 
            // InternalGrana.g:4276:1: ( RULE_ID )
            // InternalGrana.g:4277:1: RULE_ID
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
    // InternalGrana.g:4288:1: rule__Grana__JobsAssignment_3 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4292:1: ( ( ruleJob ) )
            // InternalGrana.g:4293:1: ( ruleJob )
            {
            // InternalGrana.g:4293:1: ( ruleJob )
            // InternalGrana.g:4294:1: ruleJob
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
    // InternalGrana.g:4303:1: rule__RegularJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RegularJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4307:1: ( ( RULE_ID ) )
            // InternalGrana.g:4308:1: ( RULE_ID )
            {
            // InternalGrana.g:4308:1: ( RULE_ID )
            // InternalGrana.g:4309:1: RULE_ID
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
    // InternalGrana.g:4318:1: rule__RegularJob__LayoutBeforeAnalysisAssignment_2 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__RegularJob__LayoutBeforeAnalysisAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4322:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // InternalGrana.g:4323:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // InternalGrana.g:4323:1: ( ( 'layoutBeforeAnalysis' ) )
            // InternalGrana.g:4324:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            // InternalGrana.g:4325:1: ( 'layoutBeforeAnalysis' )
            // InternalGrana.g:4326:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            match(input,41,FOLLOW_2); 
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
    // InternalGrana.g:4341:1: rule__RegularJob__MeasureExecutionTimeAssignment_3 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RegularJob__MeasureExecutionTimeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4345:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:4346:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:4346:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:4347:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            // InternalGrana.g:4348:1: ( 'measureExecutionTime' )
            // InternalGrana.g:4349:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:4364:1: rule__RegularJob__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__RegularJob__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4368:1: ( ( ruleResource ) )
            // InternalGrana.g:4369:1: ( ruleResource )
            {
            // InternalGrana.g:4369:1: ( ruleResource )
            // InternalGrana.g:4370:1: ruleResource
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
    // InternalGrana.g:4379:1: rule__RegularJob__LayoutOptionsAssignment_7 : ( ruleKIdentifier ) ;
    public final void rule__RegularJob__LayoutOptionsAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4383:1: ( ( ruleKIdentifier ) )
            // InternalGrana.g:4384:1: ( ruleKIdentifier )
            {
            // InternalGrana.g:4384:1: ( ruleKIdentifier )
            // InternalGrana.g:4385:1: ruleKIdentifier
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4394:1: rule__RegularJob__AnalysesAssignment_9 : ( ruleAnalysis ) ;
    public final void rule__RegularJob__AnalysesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4398:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:4399:1: ( ruleAnalysis )
            {
            // InternalGrana.g:4399:1: ( ruleAnalysis )
            // InternalGrana.g:4400:1: ruleAnalysis
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
    // InternalGrana.g:4409:1: rule__RegularJob__OutputTypeAssignment_11 : ( ruleOutputType ) ;
    public final void rule__RegularJob__OutputTypeAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4413:1: ( ( ruleOutputType ) )
            // InternalGrana.g:4414:1: ( ruleOutputType )
            {
            // InternalGrana.g:4414:1: ( ruleOutputType )
            // InternalGrana.g:4415:1: ruleOutputType
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
    // InternalGrana.g:4424:1: rule__RegularJob__OutputAssignment_12 : ( ruleOutput ) ;
    public final void rule__RegularJob__OutputAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4428:1: ( ( ruleOutput ) )
            // InternalGrana.g:4429:1: ( ruleOutput )
            {
            // InternalGrana.g:4429:1: ( ruleOutput )
            // InternalGrana.g:4430:1: ruleOutput
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
    // InternalGrana.g:4439:1: rule__CompareJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__CompareJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4443:1: ( ( RULE_ID ) )
            // InternalGrana.g:4444:1: ( RULE_ID )
            {
            // InternalGrana.g:4444:1: ( RULE_ID )
            // InternalGrana.g:4445:1: RULE_ID
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
    // InternalGrana.g:4454:1: rule__CompareJob__ResourcesAssignment_3 : ( ruleResource ) ;
    public final void rule__CompareJob__ResourcesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4458:1: ( ( ruleResource ) )
            // InternalGrana.g:4459:1: ( ruleResource )
            {
            // InternalGrana.g:4459:1: ( ruleResource )
            // InternalGrana.g:4460:1: ruleResource
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
    // InternalGrana.g:4469:1: rule__CompareJob__LayoutOptionsAssignment_5 : ( ruleKIdentifier ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4473:1: ( ( ruleKIdentifier ) )
            // InternalGrana.g:4474:1: ( ruleKIdentifier )
            {
            // InternalGrana.g:4474:1: ( ruleKIdentifier )
            // InternalGrana.g:4475:1: ruleKIdentifier
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsKIdentifierParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getLayoutOptionsKIdentifierParserRuleCall_5_0()); 

            }


            }

        }
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
    // InternalGrana.g:4484:1: rule__CompareJob__LayoutOptionsAssignment_6 : ( ruleKIdentifier ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4488:1: ( ( ruleKIdentifier ) )
            // InternalGrana.g:4489:1: ( ruleKIdentifier )
            {
            // InternalGrana.g:4489:1: ( ruleKIdentifier )
            // InternalGrana.g:4490:1: ruleKIdentifier
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsKIdentifierParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getLayoutOptionsKIdentifierParserRuleCall_6_0()); 

            }


            }

        }
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
    // InternalGrana.g:4499:1: rule__CompareJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__CompareJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4503:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:4504:1: ( ruleAnalysis )
            {
            // InternalGrana.g:4504:1: ( ruleAnalysis )
            // InternalGrana.g:4505:1: ruleAnalysis
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
    // InternalGrana.g:4514:1: rule__CompareJob__OutputTypeAssignment_10 : ( ruleOutputType ) ;
    public final void rule__CompareJob__OutputTypeAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4518:1: ( ( ruleOutputType ) )
            // InternalGrana.g:4519:1: ( ruleOutputType )
            {
            // InternalGrana.g:4519:1: ( ruleOutputType )
            // InternalGrana.g:4520:1: ruleOutputType
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
    // InternalGrana.g:4529:1: rule__CompareJob__OutputAssignment_11 : ( ruleOutput ) ;
    public final void rule__CompareJob__OutputAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4533:1: ( ( ruleOutput ) )
            // InternalGrana.g:4534:1: ( ruleOutput )
            {
            // InternalGrana.g:4534:1: ( ruleOutput )
            // InternalGrana.g:4535:1: ruleOutput
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
    // InternalGrana.g:4544:1: rule__RangeJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RangeJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4548:1: ( ( RULE_ID ) )
            // InternalGrana.g:4549:1: ( RULE_ID )
            {
            // InternalGrana.g:4549:1: ( RULE_ID )
            // InternalGrana.g:4550:1: RULE_ID
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
    // InternalGrana.g:4559:1: rule__RangeJob__MeasureExecutionTimeAssignment_2 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RangeJob__MeasureExecutionTimeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4563:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:4564:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:4564:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:4565:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            // InternalGrana.g:4566:1: ( 'measureExecutionTime' )
            // InternalGrana.g:4567:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:4582:1: rule__RangeJob__ResourcesAssignment_4 : ( ruleResource ) ;
    public final void rule__RangeJob__ResourcesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4586:1: ( ( ruleResource ) )
            // InternalGrana.g:4587:1: ( ruleResource )
            {
            // InternalGrana.g:4587:1: ( ruleResource )
            // InternalGrana.g:4588:1: ruleResource
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
    // InternalGrana.g:4597:1: rule__RangeJob__LayoutOptionsAssignment_6 : ( ruleKIdentifier ) ;
    public final void rule__RangeJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4601:1: ( ( ruleKIdentifier ) )
            // InternalGrana.g:4602:1: ( ruleKIdentifier )
            {
            // InternalGrana.g:4602:1: ( ruleKIdentifier )
            // InternalGrana.g:4603:1: ruleKIdentifier
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsKIdentifierParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsKIdentifierParserRuleCall_6_0()); 

            }


            }

        }
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
    // InternalGrana.g:4612:1: rule__RangeJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4616:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:4617:1: ( ruleAnalysis )
            {
            // InternalGrana.g:4617:1: ( ruleAnalysis )
            // InternalGrana.g:4618:1: ruleAnalysis
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
    // InternalGrana.g:4627:1: rule__RangeJob__RangeOptionAssignment_10 : ( ruleQualifiedID ) ;
    public final void rule__RangeJob__RangeOptionAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4631:1: ( ( ruleQualifiedID ) )
            // InternalGrana.g:4632:1: ( ruleQualifiedID )
            {
            // InternalGrana.g:4632:1: ( ruleQualifiedID )
            // InternalGrana.g:4633:1: ruleQualifiedID
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIDParserRuleCall_10_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIDParserRuleCall_10_0()); 

            }


            }

        }
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
    // InternalGrana.g:4642:1: rule__RangeJob__RangeValuesAssignment_11 : ( ruleRange ) ;
    public final void rule__RangeJob__RangeValuesAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4646:1: ( ( ruleRange ) )
            // InternalGrana.g:4647:1: ( ruleRange )
            {
            // InternalGrana.g:4647:1: ( ruleRange )
            // InternalGrana.g:4648:1: ruleRange
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
    // InternalGrana.g:4657:1: rule__RangeJob__RangeAnalysisAssignment_12_0_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysisAssignment_12_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4661:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:4662:1: ( ruleAnalysis )
            {
            // InternalGrana.g:4662:1: ( ruleAnalysis )
            // InternalGrana.g:4663:1: ruleAnalysis
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
    // InternalGrana.g:4672:1: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 : ( RULE_NATURAL ) ;
    public final void rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4676:1: ( ( RULE_NATURAL ) )
            // InternalGrana.g:4677:1: ( RULE_NATURAL )
            {
            // InternalGrana.g:4677:1: ( RULE_NATURAL )
            // InternalGrana.g:4678:1: RULE_NATURAL
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentNATURALTerminalRuleCall_12_0_2_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentNATURALTerminalRuleCall_12_0_2_1_0()); 

            }


            }

        }
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
    // InternalGrana.g:4687:1: rule__RangeJob__RangeAnalysesAssignment_12_1_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysesAssignment_12_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4691:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:4692:1: ( ruleAnalysis )
            {
            // InternalGrana.g:4692:1: ( ruleAnalysis )
            // InternalGrana.g:4693:1: ruleAnalysis
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
    // InternalGrana.g:4702:1: rule__RangeJob__OutputTypeAssignment_14 : ( ruleOutputType ) ;
    public final void rule__RangeJob__OutputTypeAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4706:1: ( ( ruleOutputType ) )
            // InternalGrana.g:4707:1: ( ruleOutputType )
            {
            // InternalGrana.g:4707:1: ( ruleOutputType )
            // InternalGrana.g:4708:1: ruleOutputType
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
    // InternalGrana.g:4717:1: rule__RangeJob__OutputAssignment_15 : ( ruleOutput ) ;
    public final void rule__RangeJob__OutputAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4721:1: ( ( ruleOutput ) )
            // InternalGrana.g:4722:1: ( ruleOutput )
            {
            // InternalGrana.g:4722:1: ( ruleOutput )
            // InternalGrana.g:4723:1: ruleOutput
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
    // InternalGrana.g:4732:1: rule__FloatRange__ValuesAssignment_1 : ( ruleFloat ) ;
    public final void rule__FloatRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4736:1: ( ( ruleFloat ) )
            // InternalGrana.g:4737:1: ( ruleFloat )
            {
            // InternalGrana.g:4737:1: ( ruleFloat )
            // InternalGrana.g:4738:1: ruleFloat
            {
             before(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4747:1: rule__FloatRange__ValuesAssignment_2_1 : ( ruleFloat ) ;
    public final void rule__FloatRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4751:1: ( ( ruleFloat ) )
            // InternalGrana.g:4752:1: ( ruleFloat )
            {
            // InternalGrana.g:4752:1: ( ruleFloat )
            // InternalGrana.g:4753:1: ruleFloat
            {
             before(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4762:1: rule__IntRangeValues__ValuesAssignment_1 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeValues__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4766:1: ( ( RULE_NATURAL ) )
            // InternalGrana.g:4767:1: ( RULE_NATURAL )
            {
            // InternalGrana.g:4767:1: ( RULE_NATURAL )
            // InternalGrana.g:4768:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_2); 
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
    // InternalGrana.g:4777:1: rule__IntRangeValues__ValuesAssignment_2_1 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeValues__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4781:1: ( ( RULE_NATURAL ) )
            // InternalGrana.g:4782:1: ( RULE_NATURAL )
            {
            // InternalGrana.g:4782:1: ( RULE_NATURAL )
            // InternalGrana.g:4783:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_2_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_2); 
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
    // InternalGrana.g:4792:1: rule__IntRangeRange__StartAssignment_1 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeRange__StartAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4796:1: ( ( RULE_NATURAL ) )
            // InternalGrana.g:4797:1: ( RULE_NATURAL )
            {
            // InternalGrana.g:4797:1: ( RULE_NATURAL )
            // InternalGrana.g:4798:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartNATURALTerminalRuleCall_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_2); 
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
    // InternalGrana.g:4807:1: rule__IntRangeRange__EndAssignment_3 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeRange__EndAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4811:1: ( ( RULE_NATURAL ) )
            // InternalGrana.g:4812:1: ( RULE_NATURAL )
            {
            // InternalGrana.g:4812:1: ( RULE_NATURAL )
            // InternalGrana.g:4813:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndNATURALTerminalRuleCall_3_0()); 
            match(input,RULE_NATURAL,FOLLOW_2); 
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
    // InternalGrana.g:4822:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4826:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:4827:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:4827:1: ( ( RULE_ID ) )
            // InternalGrana.g:4828:1: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // InternalGrana.g:4829:1: ( RULE_ID )
            // InternalGrana.g:4830:1: RULE_ID
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
    // InternalGrana.g:4841:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4845:1: ( ( RULE_ID ) )
            // InternalGrana.g:4846:1: ( RULE_ID )
            {
            // InternalGrana.g:4846:1: ( RULE_ID )
            // InternalGrana.g:4847:1: RULE_ID
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
    // InternalGrana.g:4856:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4860:1: ( ( ruleLocalResource ) )
            // InternalGrana.g:4861:1: ( ruleLocalResource )
            {
            // InternalGrana.g:4861:1: ( ruleLocalResource )
            // InternalGrana.g:4862:1: ruleLocalResource
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
    // InternalGrana.g:4871:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4875:1: ( ( RULE_STRING ) )
            // InternalGrana.g:4876:1: ( RULE_STRING )
            {
            // InternalGrana.g:4876:1: ( RULE_STRING )
            // InternalGrana.g:4877:1: RULE_STRING
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
    // InternalGrana.g:4886:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4890:1: ( ( RULE_STRING ) )
            // InternalGrana.g:4891:1: ( RULE_STRING )
            {
            // InternalGrana.g:4891:1: ( RULE_STRING )
            // InternalGrana.g:4892:1: RULE_STRING
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
    // InternalGrana.g:4901:1: rule__GlobalOutputRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalOutputRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4905:1: ( ( RULE_ID ) )
            // InternalGrana.g:4906:1: ( RULE_ID )
            {
            // InternalGrana.g:4906:1: ( RULE_ID )
            // InternalGrana.g:4907:1: RULE_ID
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
    // InternalGrana.g:4916:1: rule__GlobalOutputRef__OutputAssignment_1 : ( ruleLocalOutput ) ;
    public final void rule__GlobalOutputRef__OutputAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4920:1: ( ( ruleLocalOutput ) )
            // InternalGrana.g:4921:1: ( ruleLocalOutput )
            {
            // InternalGrana.g:4921:1: ( ruleLocalOutput )
            // InternalGrana.g:4922:1: ruleLocalOutput
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
    // InternalGrana.g:4931:1: rule__OutputReference__OutputRefAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__OutputReference__OutputRefAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4935:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:4936:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:4936:1: ( ( RULE_ID ) )
            // InternalGrana.g:4937:1: ( RULE_ID )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            // InternalGrana.g:4938:1: ( RULE_ID )
            // InternalGrana.g:4939:1: RULE_ID
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
    // InternalGrana.g:4950:1: rule__LocalOutput__PathAssignment : ( RULE_STRING ) ;
    public final void rule__LocalOutput__PathAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4954:1: ( ( RULE_STRING ) )
            // InternalGrana.g:4955:1: ( RULE_STRING )
            {
            // InternalGrana.g:4955:1: ( RULE_STRING )
            // InternalGrana.g:4956:1: RULE_STRING
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
    // InternalGrana.g:4965:1: rule__Analysis__NameAssignment : ( ruleQualifiedID ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4969:1: ( ( ruleQualifiedID ) )
            // InternalGrana.g:4970:1: ( ruleQualifiedID )
            {
            // InternalGrana.g:4970:1: ( ruleQualifiedID )
            // InternalGrana.g:4971:1: ruleQualifiedID
            {
             before(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4980:1: rule__KIdentifier__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4984:1: ( ( RULE_ID ) )
            // InternalGrana.g:4985:1: ( RULE_ID )
            {
            // InternalGrana.g:4985:1: ( RULE_ID )
            // InternalGrana.g:4986:1: RULE_ID
            {
             before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:4995:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4999:1: ( ( rulePersistentEntry ) )
            // InternalGrana.g:5000:1: ( rulePersistentEntry )
            {
            // InternalGrana.g:5000:1: ( rulePersistentEntry )
            // InternalGrana.g:5001:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:5010:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5014:1: ( ( rulePersistentEntry ) )
            // InternalGrana.g:5015:1: ( rulePersistentEntry )
            {
            // InternalGrana.g:5015:1: ( rulePersistentEntry )
            // InternalGrana.g:5016:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:5025:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5029:1: ( ( ruleQualifiedID ) )
            // InternalGrana.g:5030:1: ( ruleQualifiedID )
            {
            // InternalGrana.g:5030:1: ( ruleQualifiedID )
            // InternalGrana.g:5031:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:5040:1: rule__PersistentEntry__ValueAssignment_2 : ( rulePropertyValue ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5044:1: ( ( rulePropertyValue ) )
            // InternalGrana.g:5045:1: ( rulePropertyValue )
            {
            // InternalGrana.g:5045:1: ( rulePropertyValue )
            // InternalGrana.g:5046:1: rulePropertyValue
            {
             before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
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


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000C20000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000C20002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000018000000100L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000060000040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000200000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000200000022L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000200003020L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000040000040000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00000000D0000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000001000000100L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000004000000002L});

}