package de.cau.cs.kieler.grana.text.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
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
    // InternalGrana.g:53:1: entryRuleGrana : ruleGrana EOF ;
    public final void entryRuleGrana() throws RecognitionException {
        try {
            // InternalGrana.g:54:1: ( ruleGrana EOF )
            // InternalGrana.g:55:1: ruleGrana EOF
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
    // InternalGrana.g:62:1: ruleGrana : ( ( rule__Grana__Group__0 ) ) ;
    public final void ruleGrana() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:66:2: ( ( ( rule__Grana__Group__0 ) ) )
            // InternalGrana.g:67:2: ( ( rule__Grana__Group__0 ) )
            {
            // InternalGrana.g:67:2: ( ( rule__Grana__Group__0 ) )
            // InternalGrana.g:68:3: ( rule__Grana__Group__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup()); 
            // InternalGrana.g:69:3: ( rule__Grana__Group__0 )
            // InternalGrana.g:69:4: rule__Grana__Group__0
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
    // InternalGrana.g:78:1: entryRuleJob : ruleJob EOF ;
    public final void entryRuleJob() throws RecognitionException {
        try {
            // InternalGrana.g:79:1: ( ruleJob EOF )
            // InternalGrana.g:80:1: ruleJob EOF
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
    // InternalGrana.g:87:1: ruleJob : ( ( rule__Job__Alternatives ) ) ;
    public final void ruleJob() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:91:2: ( ( ( rule__Job__Alternatives ) ) )
            // InternalGrana.g:92:2: ( ( rule__Job__Alternatives ) )
            {
            // InternalGrana.g:92:2: ( ( rule__Job__Alternatives ) )
            // InternalGrana.g:93:3: ( rule__Job__Alternatives )
            {
             before(grammarAccess.getJobAccess().getAlternatives()); 
            // InternalGrana.g:94:3: ( rule__Job__Alternatives )
            // InternalGrana.g:94:4: rule__Job__Alternatives
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
    // InternalGrana.g:103:1: entryRuleRegularJob : ruleRegularJob EOF ;
    public final void entryRuleRegularJob() throws RecognitionException {
        try {
            // InternalGrana.g:104:1: ( ruleRegularJob EOF )
            // InternalGrana.g:105:1: ruleRegularJob EOF
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
    // InternalGrana.g:112:1: ruleRegularJob : ( ( rule__RegularJob__Group__0 ) ) ;
    public final void ruleRegularJob() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:116:2: ( ( ( rule__RegularJob__Group__0 ) ) )
            // InternalGrana.g:117:2: ( ( rule__RegularJob__Group__0 ) )
            {
            // InternalGrana.g:117:2: ( ( rule__RegularJob__Group__0 ) )
            // InternalGrana.g:118:3: ( rule__RegularJob__Group__0 )
            {
             before(grammarAccess.getRegularJobAccess().getGroup()); 
            // InternalGrana.g:119:3: ( rule__RegularJob__Group__0 )
            // InternalGrana.g:119:4: rule__RegularJob__Group__0
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
    // InternalGrana.g:128:1: entryRuleCompareJob : ruleCompareJob EOF ;
    public final void entryRuleCompareJob() throws RecognitionException {
        try {
            // InternalGrana.g:129:1: ( ruleCompareJob EOF )
            // InternalGrana.g:130:1: ruleCompareJob EOF
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
    // InternalGrana.g:137:1: ruleCompareJob : ( ( rule__CompareJob__Group__0 ) ) ;
    public final void ruleCompareJob() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:141:2: ( ( ( rule__CompareJob__Group__0 ) ) )
            // InternalGrana.g:142:2: ( ( rule__CompareJob__Group__0 ) )
            {
            // InternalGrana.g:142:2: ( ( rule__CompareJob__Group__0 ) )
            // InternalGrana.g:143:3: ( rule__CompareJob__Group__0 )
            {
             before(grammarAccess.getCompareJobAccess().getGroup()); 
            // InternalGrana.g:144:3: ( rule__CompareJob__Group__0 )
            // InternalGrana.g:144:4: rule__CompareJob__Group__0
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
    // InternalGrana.g:153:1: entryRuleRangeJob : ruleRangeJob EOF ;
    public final void entryRuleRangeJob() throws RecognitionException {
        try {
            // InternalGrana.g:154:1: ( ruleRangeJob EOF )
            // InternalGrana.g:155:1: ruleRangeJob EOF
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
    // InternalGrana.g:162:1: ruleRangeJob : ( ( rule__RangeJob__Group__0 ) ) ;
    public final void ruleRangeJob() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:166:2: ( ( ( rule__RangeJob__Group__0 ) ) )
            // InternalGrana.g:167:2: ( ( rule__RangeJob__Group__0 ) )
            {
            // InternalGrana.g:167:2: ( ( rule__RangeJob__Group__0 ) )
            // InternalGrana.g:168:3: ( rule__RangeJob__Group__0 )
            {
             before(grammarAccess.getRangeJobAccess().getGroup()); 
            // InternalGrana.g:169:3: ( rule__RangeJob__Group__0 )
            // InternalGrana.g:169:4: rule__RangeJob__Group__0
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
    // InternalGrana.g:178:1: entryRuleRange : ruleRange EOF ;
    public final void entryRuleRange() throws RecognitionException {
        try {
            // InternalGrana.g:179:1: ( ruleRange EOF )
            // InternalGrana.g:180:1: ruleRange EOF
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
    // InternalGrana.g:187:1: ruleRange : ( ( rule__Range__Alternatives ) ) ;
    public final void ruleRange() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:191:2: ( ( ( rule__Range__Alternatives ) ) )
            // InternalGrana.g:192:2: ( ( rule__Range__Alternatives ) )
            {
            // InternalGrana.g:192:2: ( ( rule__Range__Alternatives ) )
            // InternalGrana.g:193:3: ( rule__Range__Alternatives )
            {
             before(grammarAccess.getRangeAccess().getAlternatives()); 
            // InternalGrana.g:194:3: ( rule__Range__Alternatives )
            // InternalGrana.g:194:4: rule__Range__Alternatives
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
    // InternalGrana.g:203:1: entryRuleFloatRange : ruleFloatRange EOF ;
    public final void entryRuleFloatRange() throws RecognitionException {
        try {
            // InternalGrana.g:204:1: ( ruleFloatRange EOF )
            // InternalGrana.g:205:1: ruleFloatRange EOF
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
    // InternalGrana.g:212:1: ruleFloatRange : ( ( rule__FloatRange__Group__0 ) ) ;
    public final void ruleFloatRange() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:216:2: ( ( ( rule__FloatRange__Group__0 ) ) )
            // InternalGrana.g:217:2: ( ( rule__FloatRange__Group__0 ) )
            {
            // InternalGrana.g:217:2: ( ( rule__FloatRange__Group__0 ) )
            // InternalGrana.g:218:3: ( rule__FloatRange__Group__0 )
            {
             before(grammarAccess.getFloatRangeAccess().getGroup()); 
            // InternalGrana.g:219:3: ( rule__FloatRange__Group__0 )
            // InternalGrana.g:219:4: rule__FloatRange__Group__0
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
    // InternalGrana.g:228:1: entryRuleIntRange : ruleIntRange EOF ;
    public final void entryRuleIntRange() throws RecognitionException {
        try {
            // InternalGrana.g:229:1: ( ruleIntRange EOF )
            // InternalGrana.g:230:1: ruleIntRange EOF
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
    // InternalGrana.g:237:1: ruleIntRange : ( ( rule__IntRange__Alternatives ) ) ;
    public final void ruleIntRange() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:241:2: ( ( ( rule__IntRange__Alternatives ) ) )
            // InternalGrana.g:242:2: ( ( rule__IntRange__Alternatives ) )
            {
            // InternalGrana.g:242:2: ( ( rule__IntRange__Alternatives ) )
            // InternalGrana.g:243:3: ( rule__IntRange__Alternatives )
            {
             before(grammarAccess.getIntRangeAccess().getAlternatives()); 
            // InternalGrana.g:244:3: ( rule__IntRange__Alternatives )
            // InternalGrana.g:244:4: rule__IntRange__Alternatives
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
    // InternalGrana.g:253:1: entryRuleIntRangeValues : ruleIntRangeValues EOF ;
    public final void entryRuleIntRangeValues() throws RecognitionException {
        try {
            // InternalGrana.g:254:1: ( ruleIntRangeValues EOF )
            // InternalGrana.g:255:1: ruleIntRangeValues EOF
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
    // InternalGrana.g:262:1: ruleIntRangeValues : ( ( rule__IntRangeValues__Group__0 ) ) ;
    public final void ruleIntRangeValues() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:266:2: ( ( ( rule__IntRangeValues__Group__0 ) ) )
            // InternalGrana.g:267:2: ( ( rule__IntRangeValues__Group__0 ) )
            {
            // InternalGrana.g:267:2: ( ( rule__IntRangeValues__Group__0 ) )
            // InternalGrana.g:268:3: ( rule__IntRangeValues__Group__0 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup()); 
            // InternalGrana.g:269:3: ( rule__IntRangeValues__Group__0 )
            // InternalGrana.g:269:4: rule__IntRangeValues__Group__0
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
    // InternalGrana.g:278:1: entryRuleIntRangeRange : ruleIntRangeRange EOF ;
    public final void entryRuleIntRangeRange() throws RecognitionException {
        try {
            // InternalGrana.g:279:1: ( ruleIntRangeRange EOF )
            // InternalGrana.g:280:1: ruleIntRangeRange EOF
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
    // InternalGrana.g:287:1: ruleIntRangeRange : ( ( rule__IntRangeRange__Group__0 ) ) ;
    public final void ruleIntRangeRange() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:291:2: ( ( ( rule__IntRangeRange__Group__0 ) ) )
            // InternalGrana.g:292:2: ( ( rule__IntRangeRange__Group__0 ) )
            {
            // InternalGrana.g:292:2: ( ( rule__IntRangeRange__Group__0 ) )
            // InternalGrana.g:293:3: ( rule__IntRangeRange__Group__0 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getGroup()); 
            // InternalGrana.g:294:3: ( rule__IntRangeRange__Group__0 )
            // InternalGrana.g:294:4: rule__IntRangeRange__Group__0
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
    // InternalGrana.g:303:1: entryRuleEnumRange : ruleEnumRange EOF ;
    public final void entryRuleEnumRange() throws RecognitionException {
        try {
            // InternalGrana.g:304:1: ( ruleEnumRange EOF )
            // InternalGrana.g:305:1: ruleEnumRange EOF
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
    // InternalGrana.g:312:1: ruleEnumRange : ( ( rule__EnumRange__Group__0 ) ) ;
    public final void ruleEnumRange() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:316:2: ( ( ( rule__EnumRange__Group__0 ) ) )
            // InternalGrana.g:317:2: ( ( rule__EnumRange__Group__0 ) )
            {
            // InternalGrana.g:317:2: ( ( rule__EnumRange__Group__0 ) )
            // InternalGrana.g:318:3: ( rule__EnumRange__Group__0 )
            {
             before(grammarAccess.getEnumRangeAccess().getGroup()); 
            // InternalGrana.g:319:3: ( rule__EnumRange__Group__0 )
            // InternalGrana.g:319:4: rule__EnumRange__Group__0
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
    // InternalGrana.g:328:1: entryRuleResource : ruleResource EOF ;
    public final void entryRuleResource() throws RecognitionException {
        try {
            // InternalGrana.g:329:1: ( ruleResource EOF )
            // InternalGrana.g:330:1: ruleResource EOF
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
    // InternalGrana.g:337:1: ruleResource : ( ( rule__Resource__Alternatives ) ) ;
    public final void ruleResource() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:341:2: ( ( ( rule__Resource__Alternatives ) ) )
            // InternalGrana.g:342:2: ( ( rule__Resource__Alternatives ) )
            {
            // InternalGrana.g:342:2: ( ( rule__Resource__Alternatives ) )
            // InternalGrana.g:343:3: ( rule__Resource__Alternatives )
            {
             before(grammarAccess.getResourceAccess().getAlternatives()); 
            // InternalGrana.g:344:3: ( rule__Resource__Alternatives )
            // InternalGrana.g:344:4: rule__Resource__Alternatives
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
    // InternalGrana.g:353:1: entryRuleResourceReference : ruleResourceReference EOF ;
    public final void entryRuleResourceReference() throws RecognitionException {
        try {
            // InternalGrana.g:354:1: ( ruleResourceReference EOF )
            // InternalGrana.g:355:1: ruleResourceReference EOF
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
    // InternalGrana.g:362:1: ruleResourceReference : ( ( rule__ResourceReference__Group__0 ) ) ;
    public final void ruleResourceReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:366:2: ( ( ( rule__ResourceReference__Group__0 ) ) )
            // InternalGrana.g:367:2: ( ( rule__ResourceReference__Group__0 ) )
            {
            // InternalGrana.g:367:2: ( ( rule__ResourceReference__Group__0 ) )
            // InternalGrana.g:368:3: ( rule__ResourceReference__Group__0 )
            {
             before(grammarAccess.getResourceReferenceAccess().getGroup()); 
            // InternalGrana.g:369:3: ( rule__ResourceReference__Group__0 )
            // InternalGrana.g:369:4: rule__ResourceReference__Group__0
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
    // InternalGrana.g:378:1: entryRuleGlobalResourceRef : ruleGlobalResourceRef EOF ;
    public final void entryRuleGlobalResourceRef() throws RecognitionException {
        try {
            // InternalGrana.g:379:1: ( ruleGlobalResourceRef EOF )
            // InternalGrana.g:380:1: ruleGlobalResourceRef EOF
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
    // InternalGrana.g:387:1: ruleGlobalResourceRef : ( ( rule__GlobalResourceRef__Group__0 ) ) ;
    public final void ruleGlobalResourceRef() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:391:2: ( ( ( rule__GlobalResourceRef__Group__0 ) ) )
            // InternalGrana.g:392:2: ( ( rule__GlobalResourceRef__Group__0 ) )
            {
            // InternalGrana.g:392:2: ( ( rule__GlobalResourceRef__Group__0 ) )
            // InternalGrana.g:393:3: ( rule__GlobalResourceRef__Group__0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getGroup()); 
            // InternalGrana.g:394:3: ( rule__GlobalResourceRef__Group__0 )
            // InternalGrana.g:394:4: rule__GlobalResourceRef__Group__0
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
    // InternalGrana.g:403:1: entryRuleLocalResource : ruleLocalResource EOF ;
    public final void entryRuleLocalResource() throws RecognitionException {
        try {
            // InternalGrana.g:404:1: ( ruleLocalResource EOF )
            // InternalGrana.g:405:1: ruleLocalResource EOF
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
    // InternalGrana.g:412:1: ruleLocalResource : ( ( rule__LocalResource__Group__0 ) ) ;
    public final void ruleLocalResource() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:416:2: ( ( ( rule__LocalResource__Group__0 ) ) )
            // InternalGrana.g:417:2: ( ( rule__LocalResource__Group__0 ) )
            {
            // InternalGrana.g:417:2: ( ( rule__LocalResource__Group__0 ) )
            // InternalGrana.g:418:3: ( rule__LocalResource__Group__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup()); 
            // InternalGrana.g:419:3: ( rule__LocalResource__Group__0 )
            // InternalGrana.g:419:4: rule__LocalResource__Group__0
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
    // InternalGrana.g:428:1: entryRuleOutput : ruleOutput EOF ;
    public final void entryRuleOutput() throws RecognitionException {
        try {
            // InternalGrana.g:429:1: ( ruleOutput EOF )
            // InternalGrana.g:430:1: ruleOutput EOF
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
    // InternalGrana.g:437:1: ruleOutput : ( ( rule__Output__Alternatives ) ) ;
    public final void ruleOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:441:2: ( ( ( rule__Output__Alternatives ) ) )
            // InternalGrana.g:442:2: ( ( rule__Output__Alternatives ) )
            {
            // InternalGrana.g:442:2: ( ( rule__Output__Alternatives ) )
            // InternalGrana.g:443:3: ( rule__Output__Alternatives )
            {
             before(grammarAccess.getOutputAccess().getAlternatives()); 
            // InternalGrana.g:444:3: ( rule__Output__Alternatives )
            // InternalGrana.g:444:4: rule__Output__Alternatives
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
    // InternalGrana.g:453:1: entryRuleGlobalOutputRef : ruleGlobalOutputRef EOF ;
    public final void entryRuleGlobalOutputRef() throws RecognitionException {
        try {
            // InternalGrana.g:454:1: ( ruleGlobalOutputRef EOF )
            // InternalGrana.g:455:1: ruleGlobalOutputRef EOF
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
    // InternalGrana.g:462:1: ruleGlobalOutputRef : ( ( rule__GlobalOutputRef__Group__0 ) ) ;
    public final void ruleGlobalOutputRef() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:466:2: ( ( ( rule__GlobalOutputRef__Group__0 ) ) )
            // InternalGrana.g:467:2: ( ( rule__GlobalOutputRef__Group__0 ) )
            {
            // InternalGrana.g:467:2: ( ( rule__GlobalOutputRef__Group__0 ) )
            // InternalGrana.g:468:3: ( rule__GlobalOutputRef__Group__0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getGroup()); 
            // InternalGrana.g:469:3: ( rule__GlobalOutputRef__Group__0 )
            // InternalGrana.g:469:4: rule__GlobalOutputRef__Group__0
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
    // InternalGrana.g:478:1: entryRuleOutputReference : ruleOutputReference EOF ;
    public final void entryRuleOutputReference() throws RecognitionException {
        try {
            // InternalGrana.g:479:1: ( ruleOutputReference EOF )
            // InternalGrana.g:480:1: ruleOutputReference EOF
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
    // InternalGrana.g:487:1: ruleOutputReference : ( ( rule__OutputReference__Group__0 ) ) ;
    public final void ruleOutputReference() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:491:2: ( ( ( rule__OutputReference__Group__0 ) ) )
            // InternalGrana.g:492:2: ( ( rule__OutputReference__Group__0 ) )
            {
            // InternalGrana.g:492:2: ( ( rule__OutputReference__Group__0 ) )
            // InternalGrana.g:493:3: ( rule__OutputReference__Group__0 )
            {
             before(grammarAccess.getOutputReferenceAccess().getGroup()); 
            // InternalGrana.g:494:3: ( rule__OutputReference__Group__0 )
            // InternalGrana.g:494:4: rule__OutputReference__Group__0
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
    // InternalGrana.g:503:1: entryRuleLocalOutput : ruleLocalOutput EOF ;
    public final void entryRuleLocalOutput() throws RecognitionException {
        try {
            // InternalGrana.g:504:1: ( ruleLocalOutput EOF )
            // InternalGrana.g:505:1: ruleLocalOutput EOF
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
    // InternalGrana.g:512:1: ruleLocalOutput : ( ( rule__LocalOutput__PathAssignment ) ) ;
    public final void ruleLocalOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:516:2: ( ( ( rule__LocalOutput__PathAssignment ) ) )
            // InternalGrana.g:517:2: ( ( rule__LocalOutput__PathAssignment ) )
            {
            // InternalGrana.g:517:2: ( ( rule__LocalOutput__PathAssignment ) )
            // InternalGrana.g:518:3: ( rule__LocalOutput__PathAssignment )
            {
             before(grammarAccess.getLocalOutputAccess().getPathAssignment()); 
            // InternalGrana.g:519:3: ( rule__LocalOutput__PathAssignment )
            // InternalGrana.g:519:4: rule__LocalOutput__PathAssignment
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
    // InternalGrana.g:528:1: entryRuleAnalysis : ruleAnalysis EOF ;
    public final void entryRuleAnalysis() throws RecognitionException {
        try {
            // InternalGrana.g:529:1: ( ruleAnalysis EOF )
            // InternalGrana.g:530:1: ruleAnalysis EOF
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
    // InternalGrana.g:537:1: ruleAnalysis : ( ( rule__Analysis__NameAssignment ) ) ;
    public final void ruleAnalysis() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:541:2: ( ( ( rule__Analysis__NameAssignment ) ) )
            // InternalGrana.g:542:2: ( ( rule__Analysis__NameAssignment ) )
            {
            // InternalGrana.g:542:2: ( ( rule__Analysis__NameAssignment ) )
            // InternalGrana.g:543:3: ( rule__Analysis__NameAssignment )
            {
             before(grammarAccess.getAnalysisAccess().getNameAssignment()); 
            // InternalGrana.g:544:3: ( rule__Analysis__NameAssignment )
            // InternalGrana.g:544:4: rule__Analysis__NameAssignment
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
    // InternalGrana.g:553:1: entryRuleLayoutConfig : ruleLayoutConfig EOF ;
    public final void entryRuleLayoutConfig() throws RecognitionException {
        try {
            // InternalGrana.g:554:1: ( ruleLayoutConfig EOF )
            // InternalGrana.g:555:1: ruleLayoutConfig EOF
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
    // InternalGrana.g:562:1: ruleLayoutConfig : ( ( rule__LayoutConfig__Group__0 ) ) ;
    public final void ruleLayoutConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:566:2: ( ( ( rule__LayoutConfig__Group__0 ) ) )
            // InternalGrana.g:567:2: ( ( rule__LayoutConfig__Group__0 ) )
            {
            // InternalGrana.g:567:2: ( ( rule__LayoutConfig__Group__0 ) )
            // InternalGrana.g:568:3: ( rule__LayoutConfig__Group__0 )
            {
             before(grammarAccess.getLayoutConfigAccess().getGroup()); 
            // InternalGrana.g:569:3: ( rule__LayoutConfig__Group__0 )
            // InternalGrana.g:569:4: rule__LayoutConfig__Group__0
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
    // InternalGrana.g:578:1: entryRuleElkNode : ruleElkNode EOF ;
    public final void entryRuleElkNode() throws RecognitionException {
        try {
            // InternalGrana.g:579:1: ( ruleElkNode EOF )
            // InternalGrana.g:580:1: ruleElkNode EOF
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
    // InternalGrana.g:587:1: ruleElkNode : ( ( rule__ElkNode__Group__0 ) ) ;
    public final void ruleElkNode() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:591:2: ( ( ( rule__ElkNode__Group__0 ) ) )
            // InternalGrana.g:592:2: ( ( rule__ElkNode__Group__0 ) )
            {
            // InternalGrana.g:592:2: ( ( rule__ElkNode__Group__0 ) )
            // InternalGrana.g:593:3: ( rule__ElkNode__Group__0 )
            {
             before(grammarAccess.getElkNodeAccess().getGroup()); 
            // InternalGrana.g:594:3: ( rule__ElkNode__Group__0 )
            // InternalGrana.g:594:4: rule__ElkNode__Group__0
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
    // InternalGrana.g:603:1: entryRuleElkLabel : ruleElkLabel EOF ;
    public final void entryRuleElkLabel() throws RecognitionException {
        try {
            // InternalGrana.g:604:1: ( ruleElkLabel EOF )
            // InternalGrana.g:605:1: ruleElkLabel EOF
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
    // InternalGrana.g:612:1: ruleElkLabel : ( ( rule__ElkLabel__Group__0 ) ) ;
    public final void ruleElkLabel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:616:2: ( ( ( rule__ElkLabel__Group__0 ) ) )
            // InternalGrana.g:617:2: ( ( rule__ElkLabel__Group__0 ) )
            {
            // InternalGrana.g:617:2: ( ( rule__ElkLabel__Group__0 ) )
            // InternalGrana.g:618:3: ( rule__ElkLabel__Group__0 )
            {
             before(grammarAccess.getElkLabelAccess().getGroup()); 
            // InternalGrana.g:619:3: ( rule__ElkLabel__Group__0 )
            // InternalGrana.g:619:4: rule__ElkLabel__Group__0
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
    // InternalGrana.g:628:1: entryRuleElkPort : ruleElkPort EOF ;
    public final void entryRuleElkPort() throws RecognitionException {
        try {
            // InternalGrana.g:629:1: ( ruleElkPort EOF )
            // InternalGrana.g:630:1: ruleElkPort EOF
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
    // InternalGrana.g:637:1: ruleElkPort : ( ( rule__ElkPort__Group__0 ) ) ;
    public final void ruleElkPort() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:641:2: ( ( ( rule__ElkPort__Group__0 ) ) )
            // InternalGrana.g:642:2: ( ( rule__ElkPort__Group__0 ) )
            {
            // InternalGrana.g:642:2: ( ( rule__ElkPort__Group__0 ) )
            // InternalGrana.g:643:3: ( rule__ElkPort__Group__0 )
            {
             before(grammarAccess.getElkPortAccess().getGroup()); 
            // InternalGrana.g:644:3: ( rule__ElkPort__Group__0 )
            // InternalGrana.g:644:4: rule__ElkPort__Group__0
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
    // InternalGrana.g:654:1: ruleShapeLayout : ( ( rule__ShapeLayout__Group__0 ) ) ;
    public final void ruleShapeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:658:2: ( ( ( rule__ShapeLayout__Group__0 ) ) )
            // InternalGrana.g:659:2: ( ( rule__ShapeLayout__Group__0 ) )
            {
            // InternalGrana.g:659:2: ( ( rule__ShapeLayout__Group__0 ) )
            // InternalGrana.g:660:3: ( rule__ShapeLayout__Group__0 )
            {
             before(grammarAccess.getShapeLayoutAccess().getGroup()); 
            // InternalGrana.g:661:3: ( rule__ShapeLayout__Group__0 )
            // InternalGrana.g:661:4: rule__ShapeLayout__Group__0
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
    // InternalGrana.g:670:1: entryRuleElkEdge : ruleElkEdge EOF ;
    public final void entryRuleElkEdge() throws RecognitionException {
        try {
            // InternalGrana.g:671:1: ( ruleElkEdge EOF )
            // InternalGrana.g:672:1: ruleElkEdge EOF
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
    // InternalGrana.g:679:1: ruleElkEdge : ( ( rule__ElkEdge__Group__0 ) ) ;
    public final void ruleElkEdge() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:683:2: ( ( ( rule__ElkEdge__Group__0 ) ) )
            // InternalGrana.g:684:2: ( ( rule__ElkEdge__Group__0 ) )
            {
            // InternalGrana.g:684:2: ( ( rule__ElkEdge__Group__0 ) )
            // InternalGrana.g:685:3: ( rule__ElkEdge__Group__0 )
            {
             before(grammarAccess.getElkEdgeAccess().getGroup()); 
            // InternalGrana.g:686:3: ( rule__ElkEdge__Group__0 )
            // InternalGrana.g:686:4: rule__ElkEdge__Group__0
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
    // InternalGrana.g:696:1: ruleEdgeLayout : ( ( rule__EdgeLayout__Group__0 ) ) ;
    public final void ruleEdgeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:700:2: ( ( ( rule__EdgeLayout__Group__0 ) ) )
            // InternalGrana.g:701:2: ( ( rule__EdgeLayout__Group__0 ) )
            {
            // InternalGrana.g:701:2: ( ( rule__EdgeLayout__Group__0 ) )
            // InternalGrana.g:702:3: ( rule__EdgeLayout__Group__0 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getGroup()); 
            // InternalGrana.g:703:3: ( rule__EdgeLayout__Group__0 )
            // InternalGrana.g:703:4: rule__EdgeLayout__Group__0
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
    // InternalGrana.g:712:1: entryRuleElkSingleEdgeSection : ruleElkSingleEdgeSection EOF ;
    public final void entryRuleElkSingleEdgeSection() throws RecognitionException {
        try {
            // InternalGrana.g:713:1: ( ruleElkSingleEdgeSection EOF )
            // InternalGrana.g:714:1: ruleElkSingleEdgeSection EOF
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
    // InternalGrana.g:721:1: ruleElkSingleEdgeSection : ( ( rule__ElkSingleEdgeSection__Group__0 ) ) ;
    public final void ruleElkSingleEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:725:2: ( ( ( rule__ElkSingleEdgeSection__Group__0 ) ) )
            // InternalGrana.g:726:2: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            {
            // InternalGrana.g:726:2: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            // InternalGrana.g:727:3: ( rule__ElkSingleEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup()); 
            // InternalGrana.g:728:3: ( rule__ElkSingleEdgeSection__Group__0 )
            // InternalGrana.g:728:4: rule__ElkSingleEdgeSection__Group__0
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
    // InternalGrana.g:737:1: entryRuleElkEdgeSection : ruleElkEdgeSection EOF ;
    public final void entryRuleElkEdgeSection() throws RecognitionException {
        try {
            // InternalGrana.g:738:1: ( ruleElkEdgeSection EOF )
            // InternalGrana.g:739:1: ruleElkEdgeSection EOF
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
    // InternalGrana.g:746:1: ruleElkEdgeSection : ( ( rule__ElkEdgeSection__Group__0 ) ) ;
    public final void ruleElkEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:750:2: ( ( ( rule__ElkEdgeSection__Group__0 ) ) )
            // InternalGrana.g:751:2: ( ( rule__ElkEdgeSection__Group__0 ) )
            {
            // InternalGrana.g:751:2: ( ( rule__ElkEdgeSection__Group__0 ) )
            // InternalGrana.g:752:3: ( rule__ElkEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup()); 
            // InternalGrana.g:753:3: ( rule__ElkEdgeSection__Group__0 )
            // InternalGrana.g:753:4: rule__ElkEdgeSection__Group__0
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
    // InternalGrana.g:762:1: entryRuleElkBendPoint : ruleElkBendPoint EOF ;
    public final void entryRuleElkBendPoint() throws RecognitionException {
        try {
            // InternalGrana.g:763:1: ( ruleElkBendPoint EOF )
            // InternalGrana.g:764:1: ruleElkBendPoint EOF
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
    // InternalGrana.g:771:1: ruleElkBendPoint : ( ( rule__ElkBendPoint__Group__0 ) ) ;
    public final void ruleElkBendPoint() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:775:2: ( ( ( rule__ElkBendPoint__Group__0 ) ) )
            // InternalGrana.g:776:2: ( ( rule__ElkBendPoint__Group__0 ) )
            {
            // InternalGrana.g:776:2: ( ( rule__ElkBendPoint__Group__0 ) )
            // InternalGrana.g:777:3: ( rule__ElkBendPoint__Group__0 )
            {
             before(grammarAccess.getElkBendPointAccess().getGroup()); 
            // InternalGrana.g:778:3: ( rule__ElkBendPoint__Group__0 )
            // InternalGrana.g:778:4: rule__ElkBendPoint__Group__0
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
    // InternalGrana.g:787:1: entryRuleQualifiedId : ruleQualifiedId EOF ;
    public final void entryRuleQualifiedId() throws RecognitionException {
        try {
            // InternalGrana.g:788:1: ( ruleQualifiedId EOF )
            // InternalGrana.g:789:1: ruleQualifiedId EOF
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
    // InternalGrana.g:796:1: ruleQualifiedId : ( ( rule__QualifiedId__Group__0 ) ) ;
    public final void ruleQualifiedId() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:800:2: ( ( ( rule__QualifiedId__Group__0 ) ) )
            // InternalGrana.g:801:2: ( ( rule__QualifiedId__Group__0 ) )
            {
            // InternalGrana.g:801:2: ( ( rule__QualifiedId__Group__0 ) )
            // InternalGrana.g:802:3: ( rule__QualifiedId__Group__0 )
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup()); 
            // InternalGrana.g:803:3: ( rule__QualifiedId__Group__0 )
            // InternalGrana.g:803:4: rule__QualifiedId__Group__0
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
    // InternalGrana.g:812:1: entryRuleNumber : ruleNumber EOF ;
    public final void entryRuleNumber() throws RecognitionException {
        try {
            // InternalGrana.g:813:1: ( ruleNumber EOF )
            // InternalGrana.g:814:1: ruleNumber EOF
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
    // InternalGrana.g:821:1: ruleNumber : ( ( rule__Number__Alternatives ) ) ;
    public final void ruleNumber() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:825:2: ( ( ( rule__Number__Alternatives ) ) )
            // InternalGrana.g:826:2: ( ( rule__Number__Alternatives ) )
            {
            // InternalGrana.g:826:2: ( ( rule__Number__Alternatives ) )
            // InternalGrana.g:827:3: ( rule__Number__Alternatives )
            {
             before(grammarAccess.getNumberAccess().getAlternatives()); 
            // InternalGrana.g:828:3: ( rule__Number__Alternatives )
            // InternalGrana.g:828:4: rule__Number__Alternatives
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
    // InternalGrana.g:837:1: entryRuleProperty : ruleProperty EOF ;
    public final void entryRuleProperty() throws RecognitionException {
        try {
            // InternalGrana.g:838:1: ( ruleProperty EOF )
            // InternalGrana.g:839:1: ruleProperty EOF
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
    // InternalGrana.g:846:1: ruleProperty : ( ( rule__Property__Group__0 ) ) ;
    public final void ruleProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:850:2: ( ( ( rule__Property__Group__0 ) ) )
            // InternalGrana.g:851:2: ( ( rule__Property__Group__0 ) )
            {
            // InternalGrana.g:851:2: ( ( rule__Property__Group__0 ) )
            // InternalGrana.g:852:3: ( rule__Property__Group__0 )
            {
             before(grammarAccess.getPropertyAccess().getGroup()); 
            // InternalGrana.g:853:3: ( rule__Property__Group__0 )
            // InternalGrana.g:853:4: rule__Property__Group__0
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
    // InternalGrana.g:862:1: entryRulePropertyKey : rulePropertyKey EOF ;
    public final void entryRulePropertyKey() throws RecognitionException {
         
        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalGrana.g:866:1: ( rulePropertyKey EOF )
            // InternalGrana.g:867:1: rulePropertyKey EOF
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
    // InternalGrana.g:877:1: rulePropertyKey : ( ( rule__PropertyKey__Group__0 ) ) ;
    public final void rulePropertyKey() throws RecognitionException {

        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:882:2: ( ( ( rule__PropertyKey__Group__0 ) ) )
            // InternalGrana.g:883:2: ( ( rule__PropertyKey__Group__0 ) )
            {
            // InternalGrana.g:883:2: ( ( rule__PropertyKey__Group__0 ) )
            // InternalGrana.g:884:3: ( rule__PropertyKey__Group__0 )
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup()); 
            // InternalGrana.g:885:3: ( rule__PropertyKey__Group__0 )
            // InternalGrana.g:885:4: rule__PropertyKey__Group__0
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
    // InternalGrana.g:895:1: entryRuleStringValue : ruleStringValue EOF ;
    public final void entryRuleStringValue() throws RecognitionException {
        try {
            // InternalGrana.g:896:1: ( ruleStringValue EOF )
            // InternalGrana.g:897:1: ruleStringValue EOF
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
    // InternalGrana.g:904:1: ruleStringValue : ( RULE_STRING ) ;
    public final void ruleStringValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:908:2: ( ( RULE_STRING ) )
            // InternalGrana.g:909:2: ( RULE_STRING )
            {
            // InternalGrana.g:909:2: ( RULE_STRING )
            // InternalGrana.g:910:3: RULE_STRING
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
    // InternalGrana.g:920:1: entryRuleQualifiedIdValue : ruleQualifiedIdValue EOF ;
    public final void entryRuleQualifiedIdValue() throws RecognitionException {
        try {
            // InternalGrana.g:921:1: ( ruleQualifiedIdValue EOF )
            // InternalGrana.g:922:1: ruleQualifiedIdValue EOF
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
    // InternalGrana.g:929:1: ruleQualifiedIdValue : ( ruleQualifiedId ) ;
    public final void ruleQualifiedIdValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:933:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:934:2: ( ruleQualifiedId )
            {
            // InternalGrana.g:934:2: ( ruleQualifiedId )
            // InternalGrana.g:935:3: ruleQualifiedId
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
    // InternalGrana.g:945:1: entryRuleNumberValue : ruleNumberValue EOF ;
    public final void entryRuleNumberValue() throws RecognitionException {
        try {
            // InternalGrana.g:946:1: ( ruleNumberValue EOF )
            // InternalGrana.g:947:1: ruleNumberValue EOF
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
    // InternalGrana.g:954:1: ruleNumberValue : ( ( rule__NumberValue__Alternatives ) ) ;
    public final void ruleNumberValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:958:2: ( ( ( rule__NumberValue__Alternatives ) ) )
            // InternalGrana.g:959:2: ( ( rule__NumberValue__Alternatives ) )
            {
            // InternalGrana.g:959:2: ( ( rule__NumberValue__Alternatives ) )
            // InternalGrana.g:960:3: ( rule__NumberValue__Alternatives )
            {
             before(grammarAccess.getNumberValueAccess().getAlternatives()); 
            // InternalGrana.g:961:3: ( rule__NumberValue__Alternatives )
            // InternalGrana.g:961:4: rule__NumberValue__Alternatives
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
    // InternalGrana.g:970:1: entryRuleBooleanValue : ruleBooleanValue EOF ;
    public final void entryRuleBooleanValue() throws RecognitionException {
        try {
            // InternalGrana.g:971:1: ( ruleBooleanValue EOF )
            // InternalGrana.g:972:1: ruleBooleanValue EOF
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
    // InternalGrana.g:979:1: ruleBooleanValue : ( ( rule__BooleanValue__Alternatives ) ) ;
    public final void ruleBooleanValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:983:2: ( ( ( rule__BooleanValue__Alternatives ) ) )
            // InternalGrana.g:984:2: ( ( rule__BooleanValue__Alternatives ) )
            {
            // InternalGrana.g:984:2: ( ( rule__BooleanValue__Alternatives ) )
            // InternalGrana.g:985:3: ( rule__BooleanValue__Alternatives )
            {
             before(grammarAccess.getBooleanValueAccess().getAlternatives()); 
            // InternalGrana.g:986:3: ( rule__BooleanValue__Alternatives )
            // InternalGrana.g:986:4: rule__BooleanValue__Alternatives
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
    // InternalGrana.g:995:1: ruleOutputType : ( ( rule__OutputType__Alternatives ) ) ;
    public final void ruleOutputType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:999:1: ( ( ( rule__OutputType__Alternatives ) ) )
            // InternalGrana.g:1000:2: ( ( rule__OutputType__Alternatives ) )
            {
            // InternalGrana.g:1000:2: ( ( rule__OutputType__Alternatives ) )
            // InternalGrana.g:1001:3: ( rule__OutputType__Alternatives )
            {
             before(grammarAccess.getOutputTypeAccess().getAlternatives()); 
            // InternalGrana.g:1002:3: ( rule__OutputType__Alternatives )
            // InternalGrana.g:1002:4: rule__OutputType__Alternatives
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
    // InternalGrana.g:1010:1: rule__Grana__Alternatives_2_2 : ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) );
    public final void rule__Grana__Alternatives_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1014:1: ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) )
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
                    // InternalGrana.g:1015:2: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    {
                    // InternalGrana.g:1015:2: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    // InternalGrana.g:1016:3: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_2_0()); 
                    // InternalGrana.g:1017:3: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    // InternalGrana.g:1017:4: rule__Grana__ExecuteAllAssignment_2_2_0
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
                    // InternalGrana.g:1021:2: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    {
                    // InternalGrana.g:1021:2: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    // InternalGrana.g:1022:3: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    {
                    // InternalGrana.g:1022:3: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) )
                    // InternalGrana.g:1023:4: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:1024:4: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    // InternalGrana.g:1024:5: rule__Grana__ExecuteAssignment_2_2_1
                    {
                    pushFollow(FOLLOW_3);
                    rule__Grana__ExecuteAssignment_2_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 

                    }

                    // InternalGrana.g:1027:3: ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    // InternalGrana.g:1028:4: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:1029:4: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalGrana.g:1029:5: rule__Grana__ExecuteAssignment_2_2_1
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
    // InternalGrana.g:1038:1: rule__Job__Alternatives : ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) );
    public final void rule__Job__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1042:1: ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) )
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
                    // InternalGrana.g:1043:2: ( ruleRegularJob )
                    {
                    // InternalGrana.g:1043:2: ( ruleRegularJob )
                    // InternalGrana.g:1044:3: ruleRegularJob
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
                    // InternalGrana.g:1049:2: ( ruleRangeJob )
                    {
                    // InternalGrana.g:1049:2: ( ruleRangeJob )
                    // InternalGrana.g:1050:3: ruleRangeJob
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
                    // InternalGrana.g:1055:2: ( ruleCompareJob )
                    {
                    // InternalGrana.g:1055:2: ( ruleCompareJob )
                    // InternalGrana.g:1056:3: ruleCompareJob
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
    // InternalGrana.g:1065:1: rule__RangeJob__Alternatives_12 : ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) );
    public final void rule__RangeJob__Alternatives_12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1069:1: ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) )
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
                    // InternalGrana.g:1070:2: ( ( rule__RangeJob__Group_12_0__0 ) )
                    {
                    // InternalGrana.g:1070:2: ( ( rule__RangeJob__Group_12_0__0 ) )
                    // InternalGrana.g:1071:3: ( rule__RangeJob__Group_12_0__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_0()); 
                    // InternalGrana.g:1072:3: ( rule__RangeJob__Group_12_0__0 )
                    // InternalGrana.g:1072:4: rule__RangeJob__Group_12_0__0
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
                    // InternalGrana.g:1076:2: ( ( rule__RangeJob__Group_12_1__0 ) )
                    {
                    // InternalGrana.g:1076:2: ( ( rule__RangeJob__Group_12_1__0 ) )
                    // InternalGrana.g:1077:3: ( rule__RangeJob__Group_12_1__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_1()); 
                    // InternalGrana.g:1078:3: ( rule__RangeJob__Group_12_1__0 )
                    // InternalGrana.g:1078:4: rule__RangeJob__Group_12_1__0
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
    // InternalGrana.g:1086:1: rule__Range__Alternatives : ( ( ruleFloatRange ) | ( ruleIntRange ) | ( ruleEnumRange ) );
    public final void rule__Range__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1090:1: ( ( ruleFloatRange ) | ( ruleIntRange ) | ( ruleEnumRange ) )
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
                    // InternalGrana.g:1091:2: ( ruleFloatRange )
                    {
                    // InternalGrana.g:1091:2: ( ruleFloatRange )
                    // InternalGrana.g:1092:3: ruleFloatRange
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
                    // InternalGrana.g:1097:2: ( ruleIntRange )
                    {
                    // InternalGrana.g:1097:2: ( ruleIntRange )
                    // InternalGrana.g:1098:3: ruleIntRange
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
                    // InternalGrana.g:1103:2: ( ruleEnumRange )
                    {
                    // InternalGrana.g:1103:2: ( ruleEnumRange )
                    // InternalGrana.g:1104:3: ruleEnumRange
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
    // InternalGrana.g:1113:1: rule__IntRange__Alternatives : ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) );
    public final void rule__IntRange__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1117:1: ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) )
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
                    // InternalGrana.g:1118:2: ( ruleIntRangeRange )
                    {
                    // InternalGrana.g:1118:2: ( ruleIntRangeRange )
                    // InternalGrana.g:1119:3: ruleIntRangeRange
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
                    // InternalGrana.g:1124:2: ( ruleIntRangeValues )
                    {
                    // InternalGrana.g:1124:2: ( ruleIntRangeValues )
                    // InternalGrana.g:1125:3: ruleIntRangeValues
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
    // InternalGrana.g:1134:1: rule__Resource__Alternatives : ( ( ruleResourceReference ) | ( ruleLocalResource ) );
    public final void rule__Resource__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1138:1: ( ( ruleResourceReference ) | ( ruleLocalResource ) )
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
                    // InternalGrana.g:1139:2: ( ruleResourceReference )
                    {
                    // InternalGrana.g:1139:2: ( ruleResourceReference )
                    // InternalGrana.g:1140:3: ruleResourceReference
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
                    // InternalGrana.g:1145:2: ( ruleLocalResource )
                    {
                    // InternalGrana.g:1145:2: ( ruleLocalResource )
                    // InternalGrana.g:1146:3: ruleLocalResource
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
    // InternalGrana.g:1155:1: rule__Output__Alternatives : ( ( ruleOutputReference ) | ( ruleLocalOutput ) );
    public final void rule__Output__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1159:1: ( ( ruleOutputReference ) | ( ruleLocalOutput ) )
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
                    // InternalGrana.g:1160:2: ( ruleOutputReference )
                    {
                    // InternalGrana.g:1160:2: ( ruleOutputReference )
                    // InternalGrana.g:1161:3: ruleOutputReference
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
                    // InternalGrana.g:1166:2: ( ruleLocalOutput )
                    {
                    // InternalGrana.g:1166:2: ( ruleLocalOutput )
                    // InternalGrana.g:1167:3: ruleLocalOutput
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
    // InternalGrana.g:1176:1: rule__ElkNode__Alternatives_2_3 : ( ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) ) | ( ( rule__ElkNode__PortsAssignment_2_3_1 ) ) | ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) ) | ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) ) );
    public final void rule__ElkNode__Alternatives_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1180:1: ( ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) ) | ( ( rule__ElkNode__PortsAssignment_2_3_1 ) ) | ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) ) | ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) ) )
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
                    // InternalGrana.g:1181:2: ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) )
                    {
                    // InternalGrana.g:1181:2: ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) )
                    // InternalGrana.g:1182:3: ( rule__ElkNode__LabelsAssignment_2_3_0 )
                    {
                     before(grammarAccess.getElkNodeAccess().getLabelsAssignment_2_3_0()); 
                    // InternalGrana.g:1183:3: ( rule__ElkNode__LabelsAssignment_2_3_0 )
                    // InternalGrana.g:1183:4: rule__ElkNode__LabelsAssignment_2_3_0
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
                    // InternalGrana.g:1187:2: ( ( rule__ElkNode__PortsAssignment_2_3_1 ) )
                    {
                    // InternalGrana.g:1187:2: ( ( rule__ElkNode__PortsAssignment_2_3_1 ) )
                    // InternalGrana.g:1188:3: ( rule__ElkNode__PortsAssignment_2_3_1 )
                    {
                     before(grammarAccess.getElkNodeAccess().getPortsAssignment_2_3_1()); 
                    // InternalGrana.g:1189:3: ( rule__ElkNode__PortsAssignment_2_3_1 )
                    // InternalGrana.g:1189:4: rule__ElkNode__PortsAssignment_2_3_1
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
                    // InternalGrana.g:1193:2: ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) )
                    {
                    // InternalGrana.g:1193:2: ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) )
                    // InternalGrana.g:1194:3: ( rule__ElkNode__ChildrenAssignment_2_3_2 )
                    {
                     before(grammarAccess.getElkNodeAccess().getChildrenAssignment_2_3_2()); 
                    // InternalGrana.g:1195:3: ( rule__ElkNode__ChildrenAssignment_2_3_2 )
                    // InternalGrana.g:1195:4: rule__ElkNode__ChildrenAssignment_2_3_2
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
                    // InternalGrana.g:1199:2: ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) )
                    {
                    // InternalGrana.g:1199:2: ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) )
                    // InternalGrana.g:1200:3: ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 )
                    {
                     before(grammarAccess.getElkNodeAccess().getContainedEdgesAssignment_2_3_3()); 
                    // InternalGrana.g:1201:3: ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 )
                    // InternalGrana.g:1201:4: rule__ElkNode__ContainedEdgesAssignment_2_3_3
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
    // InternalGrana.g:1209:1: rule__EdgeLayout__Alternatives_2 : ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) );
    public final void rule__EdgeLayout__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1213:1: ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) )
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
                    // InternalGrana.g:1214:2: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    {
                    // InternalGrana.g:1214:2: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    // InternalGrana.g:1215:3: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0()); 
                    // InternalGrana.g:1216:3: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    // InternalGrana.g:1216:4: rule__EdgeLayout__SectionsAssignment_2_0
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
                    // InternalGrana.g:1220:2: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    {
                    // InternalGrana.g:1220:2: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    // InternalGrana.g:1221:3: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    {
                    // InternalGrana.g:1221:3: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) )
                    // InternalGrana.g:1222:4: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalGrana.g:1223:4: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    // InternalGrana.g:1223:5: rule__EdgeLayout__SectionsAssignment_2_1
                    {
                    pushFollow(FOLLOW_4);
                    rule__EdgeLayout__SectionsAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 

                    }

                    // InternalGrana.g:1226:3: ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    // InternalGrana.g:1227:4: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalGrana.g:1228:4: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==59) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalGrana.g:1228:5: rule__EdgeLayout__SectionsAssignment_2_1
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
    // InternalGrana.g:1237:1: rule__Number__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__Number__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1241:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
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
                    // InternalGrana.g:1242:2: ( RULE_SIGNED_INT )
                    {
                    // InternalGrana.g:1242:2: ( RULE_SIGNED_INT )
                    // InternalGrana.g:1243:3: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FOLLOW_2); 
                     after(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1248:2: ( RULE_FLOAT )
                    {
                    // InternalGrana.g:1248:2: ( RULE_FLOAT )
                    // InternalGrana.g:1249:3: RULE_FLOAT
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
    // InternalGrana.g:1258:1: rule__Property__Alternatives_2 : ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( 'null' ) );
    public final void rule__Property__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1262:1: ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( 'null' ) )
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
                    // InternalGrana.g:1263:2: ( ( rule__Property__ValueAssignment_2_0 ) )
                    {
                    // InternalGrana.g:1263:2: ( ( rule__Property__ValueAssignment_2_0 ) )
                    // InternalGrana.g:1264:3: ( rule__Property__ValueAssignment_2_0 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_0()); 
                    // InternalGrana.g:1265:3: ( rule__Property__ValueAssignment_2_0 )
                    // InternalGrana.g:1265:4: rule__Property__ValueAssignment_2_0
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
                    // InternalGrana.g:1269:2: ( ( rule__Property__ValueAssignment_2_1 ) )
                    {
                    // InternalGrana.g:1269:2: ( ( rule__Property__ValueAssignment_2_1 ) )
                    // InternalGrana.g:1270:3: ( rule__Property__ValueAssignment_2_1 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_1()); 
                    // InternalGrana.g:1271:3: ( rule__Property__ValueAssignment_2_1 )
                    // InternalGrana.g:1271:4: rule__Property__ValueAssignment_2_1
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
                    // InternalGrana.g:1275:2: ( ( rule__Property__ValueAssignment_2_2 ) )
                    {
                    // InternalGrana.g:1275:2: ( ( rule__Property__ValueAssignment_2_2 ) )
                    // InternalGrana.g:1276:3: ( rule__Property__ValueAssignment_2_2 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_2()); 
                    // InternalGrana.g:1277:3: ( rule__Property__ValueAssignment_2_2 )
                    // InternalGrana.g:1277:4: rule__Property__ValueAssignment_2_2
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
                    // InternalGrana.g:1281:2: ( ( rule__Property__ValueAssignment_2_3 ) )
                    {
                    // InternalGrana.g:1281:2: ( ( rule__Property__ValueAssignment_2_3 ) )
                    // InternalGrana.g:1282:3: ( rule__Property__ValueAssignment_2_3 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 
                    // InternalGrana.g:1283:3: ( rule__Property__ValueAssignment_2_3 )
                    // InternalGrana.g:1283:4: rule__Property__ValueAssignment_2_3
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
                    // InternalGrana.g:1287:2: ( 'null' )
                    {
                    // InternalGrana.g:1287:2: ( 'null' )
                    // InternalGrana.g:1288:3: 'null'
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
    // InternalGrana.g:1297:1: rule__NumberValue__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__NumberValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1301:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
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
                    // InternalGrana.g:1302:2: ( RULE_SIGNED_INT )
                    {
                    // InternalGrana.g:1302:2: ( RULE_SIGNED_INT )
                    // InternalGrana.g:1303:3: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FOLLOW_2); 
                     after(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1308:2: ( RULE_FLOAT )
                    {
                    // InternalGrana.g:1308:2: ( RULE_FLOAT )
                    // InternalGrana.g:1309:3: RULE_FLOAT
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
    // InternalGrana.g:1318:1: rule__BooleanValue__Alternatives : ( ( 'true' ) | ( 'false' ) );
    public final void rule__BooleanValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1322:1: ( ( 'true' ) | ( 'false' ) )
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
                    // InternalGrana.g:1323:2: ( 'true' )
                    {
                    // InternalGrana.g:1323:2: ( 'true' )
                    // InternalGrana.g:1324:3: 'true'
                    {
                     before(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1329:2: ( 'false' )
                    {
                    // InternalGrana.g:1329:2: ( 'false' )
                    // InternalGrana.g:1330:3: 'false'
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
    // InternalGrana.g:1339:1: rule__OutputType__Alternatives : ( ( ( 'csv' ) ) | ( ( 'json' ) ) );
    public final void rule__OutputType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1343:1: ( ( ( 'csv' ) ) | ( ( 'json' ) ) )
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
                    // InternalGrana.g:1344:2: ( ( 'csv' ) )
                    {
                    // InternalGrana.g:1344:2: ( ( 'csv' ) )
                    // InternalGrana.g:1345:3: ( 'csv' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 
                    // InternalGrana.g:1346:3: ( 'csv' )
                    // InternalGrana.g:1346:4: 'csv'
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1350:2: ( ( 'json' ) )
                    {
                    // InternalGrana.g:1350:2: ( ( 'json' ) )
                    // InternalGrana.g:1351:3: ( 'json' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1()); 
                    // InternalGrana.g:1352:3: ( 'json' )
                    // InternalGrana.g:1352:4: 'json'
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
    // InternalGrana.g:1360:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1364:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // InternalGrana.g:1365:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
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
    // InternalGrana.g:1372:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1376:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // InternalGrana.g:1377:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // InternalGrana.g:1377:1: ( ( rule__Grana__Group_0__0 )? )
            // InternalGrana.g:1378:2: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // InternalGrana.g:1379:2: ( rule__Grana__Group_0__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalGrana.g:1379:3: rule__Grana__Group_0__0
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
    // InternalGrana.g:1387:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl rule__Grana__Group__2 ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1391:1: ( rule__Grana__Group__1__Impl rule__Grana__Group__2 )
            // InternalGrana.g:1392:2: rule__Grana__Group__1__Impl rule__Grana__Group__2
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
    // InternalGrana.g:1399:1: rule__Grana__Group__1__Impl : ( ( rule__Grana__Group_1__0 )? ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1403:1: ( ( ( rule__Grana__Group_1__0 )? ) )
            // InternalGrana.g:1404:1: ( ( rule__Grana__Group_1__0 )? )
            {
            // InternalGrana.g:1404:1: ( ( rule__Grana__Group_1__0 )? )
            // InternalGrana.g:1405:2: ( rule__Grana__Group_1__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_1()); 
            // InternalGrana.g:1406:2: ( rule__Grana__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==19) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalGrana.g:1406:3: rule__Grana__Group_1__0
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
    // InternalGrana.g:1414:1: rule__Grana__Group__2 : rule__Grana__Group__2__Impl rule__Grana__Group__3 ;
    public final void rule__Grana__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1418:1: ( rule__Grana__Group__2__Impl rule__Grana__Group__3 )
            // InternalGrana.g:1419:2: rule__Grana__Group__2__Impl rule__Grana__Group__3
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
    // InternalGrana.g:1426:1: rule__Grana__Group__2__Impl : ( ( rule__Grana__Group_2__0 ) ) ;
    public final void rule__Grana__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1430:1: ( ( ( rule__Grana__Group_2__0 ) ) )
            // InternalGrana.g:1431:1: ( ( rule__Grana__Group_2__0 ) )
            {
            // InternalGrana.g:1431:1: ( ( rule__Grana__Group_2__0 ) )
            // InternalGrana.g:1432:2: ( rule__Grana__Group_2__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup_2()); 
            // InternalGrana.g:1433:2: ( rule__Grana__Group_2__0 )
            // InternalGrana.g:1433:3: rule__Grana__Group_2__0
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
    // InternalGrana.g:1441:1: rule__Grana__Group__3 : rule__Grana__Group__3__Impl ;
    public final void rule__Grana__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1445:1: ( rule__Grana__Group__3__Impl )
            // InternalGrana.g:1446:2: rule__Grana__Group__3__Impl
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
    // InternalGrana.g:1452:1: rule__Grana__Group__3__Impl : ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) ;
    public final void rule__Grana__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1456:1: ( ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) )
            // InternalGrana.g:1457:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            {
            // InternalGrana.g:1457:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            // InternalGrana.g:1458:2: ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* )
            {
            // InternalGrana.g:1458:2: ( ( rule__Grana__JobsAssignment_3 ) )
            // InternalGrana.g:1459:3: ( rule__Grana__JobsAssignment_3 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1460:3: ( rule__Grana__JobsAssignment_3 )
            // InternalGrana.g:1460:4: rule__Grana__JobsAssignment_3
            {
            pushFollow(FOLLOW_7);
            rule__Grana__JobsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }

            // InternalGrana.g:1463:2: ( ( rule__Grana__JobsAssignment_3 )* )
            // InternalGrana.g:1464:3: ( rule__Grana__JobsAssignment_3 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1465:3: ( rule__Grana__JobsAssignment_3 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==21||(LA19_0>=26 && LA19_0<=27)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalGrana.g:1465:4: rule__Grana__JobsAssignment_3
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
    // InternalGrana.g:1475:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1479:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // InternalGrana.g:1480:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
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
    // InternalGrana.g:1487:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1491:1: ( ( 'globalResources' ) )
            // InternalGrana.g:1492:1: ( 'globalResources' )
            {
            // InternalGrana.g:1492:1: ( 'globalResources' )
            // InternalGrana.g:1493:2: 'globalResources'
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
    // InternalGrana.g:1502:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1506:1: ( rule__Grana__Group_0__1__Impl )
            // InternalGrana.g:1507:2: rule__Grana__Group_0__1__Impl
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
    // InternalGrana.g:1513:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1517:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // InternalGrana.g:1518:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // InternalGrana.g:1518:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // InternalGrana.g:1519:2: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // InternalGrana.g:1520:2: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalGrana.g:1520:3: rule__Grana__GlobalResourcesAssignment_0_1
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
    // InternalGrana.g:1529:1: rule__Grana__Group_1__0 : rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 ;
    public final void rule__Grana__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1533:1: ( rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 )
            // InternalGrana.g:1534:2: rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1
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
    // InternalGrana.g:1541:1: rule__Grana__Group_1__0__Impl : ( 'globalOutputs' ) ;
    public final void rule__Grana__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1545:1: ( ( 'globalOutputs' ) )
            // InternalGrana.g:1546:1: ( 'globalOutputs' )
            {
            // InternalGrana.g:1546:1: ( 'globalOutputs' )
            // InternalGrana.g:1547:2: 'globalOutputs'
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
    // InternalGrana.g:1556:1: rule__Grana__Group_1__1 : rule__Grana__Group_1__1__Impl ;
    public final void rule__Grana__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1560:1: ( rule__Grana__Group_1__1__Impl )
            // InternalGrana.g:1561:2: rule__Grana__Group_1__1__Impl
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
    // InternalGrana.g:1567:1: rule__Grana__Group_1__1__Impl : ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) ;
    public final void rule__Grana__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1571:1: ( ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) )
            // InternalGrana.g:1572:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            {
            // InternalGrana.g:1572:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            // InternalGrana.g:1573:2: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 
            // InternalGrana.g:1574:2: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalGrana.g:1574:3: rule__Grana__GloobalOutputsAssignment_1_1
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
    // InternalGrana.g:1583:1: rule__Grana__Group_2__0 : rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 ;
    public final void rule__Grana__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1587:1: ( rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 )
            // InternalGrana.g:1588:2: rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1
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
    // InternalGrana.g:1595:1: rule__Grana__Group_2__0__Impl : ( 'execute' ) ;
    public final void rule__Grana__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1599:1: ( ( 'execute' ) )
            // InternalGrana.g:1600:1: ( 'execute' )
            {
            // InternalGrana.g:1600:1: ( 'execute' )
            // InternalGrana.g:1601:2: 'execute'
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
    // InternalGrana.g:1610:1: rule__Grana__Group_2__1 : rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 ;
    public final void rule__Grana__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1614:1: ( rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 )
            // InternalGrana.g:1615:2: rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2
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
    // InternalGrana.g:1622:1: rule__Grana__Group_2__1__Impl : ( ( rule__Grana__ParallelAssignment_2_1 )? ) ;
    public final void rule__Grana__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1626:1: ( ( ( rule__Grana__ParallelAssignment_2_1 )? ) )
            // InternalGrana.g:1627:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            {
            // InternalGrana.g:1627:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            // InternalGrana.g:1628:2: ( rule__Grana__ParallelAssignment_2_1 )?
            {
             before(grammarAccess.getGranaAccess().getParallelAssignment_2_1()); 
            // InternalGrana.g:1629:2: ( rule__Grana__ParallelAssignment_2_1 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==61) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalGrana.g:1629:3: rule__Grana__ParallelAssignment_2_1
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
    // InternalGrana.g:1637:1: rule__Grana__Group_2__2 : rule__Grana__Group_2__2__Impl ;
    public final void rule__Grana__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1641:1: ( rule__Grana__Group_2__2__Impl )
            // InternalGrana.g:1642:2: rule__Grana__Group_2__2__Impl
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
    // InternalGrana.g:1648:1: rule__Grana__Group_2__2__Impl : ( ( rule__Grana__Alternatives_2_2 ) ) ;
    public final void rule__Grana__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1652:1: ( ( ( rule__Grana__Alternatives_2_2 ) ) )
            // InternalGrana.g:1653:1: ( ( rule__Grana__Alternatives_2_2 ) )
            {
            // InternalGrana.g:1653:1: ( ( rule__Grana__Alternatives_2_2 ) )
            // InternalGrana.g:1654:2: ( rule__Grana__Alternatives_2_2 )
            {
             before(grammarAccess.getGranaAccess().getAlternatives_2_2()); 
            // InternalGrana.g:1655:2: ( rule__Grana__Alternatives_2_2 )
            // InternalGrana.g:1655:3: rule__Grana__Alternatives_2_2
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
    // InternalGrana.g:1664:1: rule__RegularJob__Group__0 : rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 ;
    public final void rule__RegularJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1668:1: ( rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 )
            // InternalGrana.g:1669:2: rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1
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
    // InternalGrana.g:1676:1: rule__RegularJob__Group__0__Impl : ( 'job' ) ;
    public final void rule__RegularJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1680:1: ( ( 'job' ) )
            // InternalGrana.g:1681:1: ( 'job' )
            {
            // InternalGrana.g:1681:1: ( 'job' )
            // InternalGrana.g:1682:2: 'job'
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
    // InternalGrana.g:1691:1: rule__RegularJob__Group__1 : rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 ;
    public final void rule__RegularJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1695:1: ( rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 )
            // InternalGrana.g:1696:2: rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2
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
    // InternalGrana.g:1703:1: rule__RegularJob__Group__1__Impl : ( ( rule__RegularJob__NameAssignment_1 ) ) ;
    public final void rule__RegularJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1707:1: ( ( ( rule__RegularJob__NameAssignment_1 ) ) )
            // InternalGrana.g:1708:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:1708:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            // InternalGrana.g:1709:2: ( rule__RegularJob__NameAssignment_1 )
            {
             before(grammarAccess.getRegularJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:1710:2: ( rule__RegularJob__NameAssignment_1 )
            // InternalGrana.g:1710:3: rule__RegularJob__NameAssignment_1
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
    // InternalGrana.g:1718:1: rule__RegularJob__Group__2 : rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 ;
    public final void rule__RegularJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1722:1: ( rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 )
            // InternalGrana.g:1723:2: rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3
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
    // InternalGrana.g:1730:1: rule__RegularJob__Group__2__Impl : ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) ;
    public final void rule__RegularJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1734:1: ( ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) )
            // InternalGrana.g:1735:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            {
            // InternalGrana.g:1735:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            // InternalGrana.g:1736:2: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2()); 
            // InternalGrana.g:1737:2: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==63) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalGrana.g:1737:3: rule__RegularJob__LayoutBeforeAnalysisAssignment_2
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
    // InternalGrana.g:1745:1: rule__RegularJob__Group__3 : rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 ;
    public final void rule__RegularJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1749:1: ( rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 )
            // InternalGrana.g:1750:2: rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4
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
    // InternalGrana.g:1757:1: rule__RegularJob__Group__3__Impl : ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) ;
    public final void rule__RegularJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1761:1: ( ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) )
            // InternalGrana.g:1762:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            {
            // InternalGrana.g:1762:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            // InternalGrana.g:1763:2: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3()); 
            // InternalGrana.g:1764:2: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==64) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalGrana.g:1764:3: rule__RegularJob__MeasureExecutionTimeAssignment_3
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
    // InternalGrana.g:1772:1: rule__RegularJob__Group__4 : rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 ;
    public final void rule__RegularJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1776:1: ( rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 )
            // InternalGrana.g:1777:2: rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5
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
    // InternalGrana.g:1784:1: rule__RegularJob__Group__4__Impl : ( 'resources' ) ;
    public final void rule__RegularJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1788:1: ( ( 'resources' ) )
            // InternalGrana.g:1789:1: ( 'resources' )
            {
            // InternalGrana.g:1789:1: ( 'resources' )
            // InternalGrana.g:1790:2: 'resources'
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
    // InternalGrana.g:1799:1: rule__RegularJob__Group__5 : rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 ;
    public final void rule__RegularJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1803:1: ( rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 )
            // InternalGrana.g:1804:2: rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6
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
    // InternalGrana.g:1811:1: rule__RegularJob__Group__5__Impl : ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) ;
    public final void rule__RegularJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1815:1: ( ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) )
            // InternalGrana.g:1816:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            {
            // InternalGrana.g:1816:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            // InternalGrana.g:1817:2: ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            {
            // InternalGrana.g:1817:2: ( ( rule__RegularJob__ResourcesAssignment_5 ) )
            // InternalGrana.g:1818:3: ( rule__RegularJob__ResourcesAssignment_5 )
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:1819:3: ( rule__RegularJob__ResourcesAssignment_5 )
            // InternalGrana.g:1819:4: rule__RegularJob__ResourcesAssignment_5
            {
            pushFollow(FOLLOW_13);
            rule__RegularJob__ResourcesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 

            }

            // InternalGrana.g:1822:2: ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            // InternalGrana.g:1823:3: ( rule__RegularJob__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:1824:3: ( rule__RegularJob__ResourcesAssignment_5 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_STRING||LA25_0==38) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalGrana.g:1824:4: rule__RegularJob__ResourcesAssignment_5
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
    // InternalGrana.g:1833:1: rule__RegularJob__Group__6 : rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 ;
    public final void rule__RegularJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1837:1: ( rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 )
            // InternalGrana.g:1838:2: rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7
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
    // InternalGrana.g:1845:1: rule__RegularJob__Group__6__Impl : ( 'layoutoptions' ) ;
    public final void rule__RegularJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1849:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:1850:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:1850:1: ( 'layoutoptions' )
            // InternalGrana.g:1851:2: 'layoutoptions'
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
    // InternalGrana.g:1860:1: rule__RegularJob__Group__7 : rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 ;
    public final void rule__RegularJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1864:1: ( rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 )
            // InternalGrana.g:1865:2: rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8
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
    // InternalGrana.g:1872:1: rule__RegularJob__Group__7__Impl : ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) ;
    public final void rule__RegularJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1876:1: ( ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) )
            // InternalGrana.g:1877:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            {
            // InternalGrana.g:1877:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            // InternalGrana.g:1878:2: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            {
            // InternalGrana.g:1878:2: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) )
            // InternalGrana.g:1879:3: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:1880:3: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            // InternalGrana.g:1880:4: rule__RegularJob__LayoutOptionsAssignment_7
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__LayoutOptionsAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 

            }

            // InternalGrana.g:1883:2: ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            // InternalGrana.g:1884:3: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:1885:3: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalGrana.g:1885:4: rule__RegularJob__LayoutOptionsAssignment_7
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
    // InternalGrana.g:1894:1: rule__RegularJob__Group__8 : rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 ;
    public final void rule__RegularJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1898:1: ( rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 )
            // InternalGrana.g:1899:2: rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9
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
    // InternalGrana.g:1906:1: rule__RegularJob__Group__8__Impl : ( 'analyses' ) ;
    public final void rule__RegularJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1910:1: ( ( 'analyses' ) )
            // InternalGrana.g:1911:1: ( 'analyses' )
            {
            // InternalGrana.g:1911:1: ( 'analyses' )
            // InternalGrana.g:1912:2: 'analyses'
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
    // InternalGrana.g:1921:1: rule__RegularJob__Group__9 : rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 ;
    public final void rule__RegularJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1925:1: ( rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 )
            // InternalGrana.g:1926:2: rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10
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
    // InternalGrana.g:1933:1: rule__RegularJob__Group__9__Impl : ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) ;
    public final void rule__RegularJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1937:1: ( ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) )
            // InternalGrana.g:1938:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            {
            // InternalGrana.g:1938:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            // InternalGrana.g:1939:2: ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            {
            // InternalGrana.g:1939:2: ( ( rule__RegularJob__AnalysesAssignment_9 ) )
            // InternalGrana.g:1940:3: ( rule__RegularJob__AnalysesAssignment_9 )
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:1941:3: ( rule__RegularJob__AnalysesAssignment_9 )
            // InternalGrana.g:1941:4: rule__RegularJob__AnalysesAssignment_9
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__AnalysesAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 

            }

            // InternalGrana.g:1944:2: ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            // InternalGrana.g:1945:3: ( rule__RegularJob__AnalysesAssignment_9 )*
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:1946:3: ( rule__RegularJob__AnalysesAssignment_9 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalGrana.g:1946:4: rule__RegularJob__AnalysesAssignment_9
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
    // InternalGrana.g:1955:1: rule__RegularJob__Group__10 : rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 ;
    public final void rule__RegularJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1959:1: ( rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 )
            // InternalGrana.g:1960:2: rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11
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
    // InternalGrana.g:1967:1: rule__RegularJob__Group__10__Impl : ( 'output' ) ;
    public final void rule__RegularJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1971:1: ( ( 'output' ) )
            // InternalGrana.g:1972:1: ( 'output' )
            {
            // InternalGrana.g:1972:1: ( 'output' )
            // InternalGrana.g:1973:2: 'output'
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
    // InternalGrana.g:1982:1: rule__RegularJob__Group__11 : rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 ;
    public final void rule__RegularJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1986:1: ( rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 )
            // InternalGrana.g:1987:2: rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12
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
    // InternalGrana.g:1994:1: rule__RegularJob__Group__11__Impl : ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) ;
    public final void rule__RegularJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:1998:1: ( ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) )
            // InternalGrana.g:1999:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            {
            // InternalGrana.g:1999:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            // InternalGrana.g:2000:2: ( rule__RegularJob__OutputTypeAssignment_11 )?
            {
             before(grammarAccess.getRegularJobAccess().getOutputTypeAssignment_11()); 
            // InternalGrana.g:2001:2: ( rule__RegularJob__OutputTypeAssignment_11 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=16 && LA28_0<=17)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalGrana.g:2001:3: rule__RegularJob__OutputTypeAssignment_11
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
    // InternalGrana.g:2009:1: rule__RegularJob__Group__12 : rule__RegularJob__Group__12__Impl ;
    public final void rule__RegularJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2013:1: ( rule__RegularJob__Group__12__Impl )
            // InternalGrana.g:2014:2: rule__RegularJob__Group__12__Impl
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
    // InternalGrana.g:2020:1: rule__RegularJob__Group__12__Impl : ( ( rule__RegularJob__OutputAssignment_12 ) ) ;
    public final void rule__RegularJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2024:1: ( ( ( rule__RegularJob__OutputAssignment_12 ) ) )
            // InternalGrana.g:2025:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            {
            // InternalGrana.g:2025:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            // InternalGrana.g:2026:2: ( rule__RegularJob__OutputAssignment_12 )
            {
             before(grammarAccess.getRegularJobAccess().getOutputAssignment_12()); 
            // InternalGrana.g:2027:2: ( rule__RegularJob__OutputAssignment_12 )
            // InternalGrana.g:2027:3: rule__RegularJob__OutputAssignment_12
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
    // InternalGrana.g:2036:1: rule__CompareJob__Group__0 : rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 ;
    public final void rule__CompareJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2040:1: ( rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 )
            // InternalGrana.g:2041:2: rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1
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
    // InternalGrana.g:2048:1: rule__CompareJob__Group__0__Impl : ( 'comparejob' ) ;
    public final void rule__CompareJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2052:1: ( ( 'comparejob' ) )
            // InternalGrana.g:2053:1: ( 'comparejob' )
            {
            // InternalGrana.g:2053:1: ( 'comparejob' )
            // InternalGrana.g:2054:2: 'comparejob'
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
    // InternalGrana.g:2063:1: rule__CompareJob__Group__1 : rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 ;
    public final void rule__CompareJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2067:1: ( rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 )
            // InternalGrana.g:2068:2: rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2
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
    // InternalGrana.g:2075:1: rule__CompareJob__Group__1__Impl : ( ( rule__CompareJob__NameAssignment_1 ) ) ;
    public final void rule__CompareJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2079:1: ( ( ( rule__CompareJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2080:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2080:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            // InternalGrana.g:2081:2: ( rule__CompareJob__NameAssignment_1 )
            {
             before(grammarAccess.getCompareJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2082:2: ( rule__CompareJob__NameAssignment_1 )
            // InternalGrana.g:2082:3: rule__CompareJob__NameAssignment_1
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
    // InternalGrana.g:2090:1: rule__CompareJob__Group__2 : rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 ;
    public final void rule__CompareJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2094:1: ( rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 )
            // InternalGrana.g:2095:2: rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3
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
    // InternalGrana.g:2102:1: rule__CompareJob__Group__2__Impl : ( 'resources' ) ;
    public final void rule__CompareJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2106:1: ( ( 'resources' ) )
            // InternalGrana.g:2107:1: ( 'resources' )
            {
            // InternalGrana.g:2107:1: ( 'resources' )
            // InternalGrana.g:2108:2: 'resources'
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
    // InternalGrana.g:2117:1: rule__CompareJob__Group__3 : rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 ;
    public final void rule__CompareJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2121:1: ( rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 )
            // InternalGrana.g:2122:2: rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4
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
    // InternalGrana.g:2129:1: rule__CompareJob__Group__3__Impl : ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) ;
    public final void rule__CompareJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2133:1: ( ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) )
            // InternalGrana.g:2134:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            {
            // InternalGrana.g:2134:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            // InternalGrana.g:2135:2: ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            {
            // InternalGrana.g:2135:2: ( ( rule__CompareJob__ResourcesAssignment_3 ) )
            // InternalGrana.g:2136:3: ( rule__CompareJob__ResourcesAssignment_3 )
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2137:3: ( rule__CompareJob__ResourcesAssignment_3 )
            // InternalGrana.g:2137:4: rule__CompareJob__ResourcesAssignment_3
            {
            pushFollow(FOLLOW_13);
            rule__CompareJob__ResourcesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 

            }

            // InternalGrana.g:2140:2: ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            // InternalGrana.g:2141:3: ( rule__CompareJob__ResourcesAssignment_3 )*
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2142:3: ( rule__CompareJob__ResourcesAssignment_3 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_STRING||LA29_0==38) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalGrana.g:2142:4: rule__CompareJob__ResourcesAssignment_3
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
    // InternalGrana.g:2151:1: rule__CompareJob__Group__4 : rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 ;
    public final void rule__CompareJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2155:1: ( rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 )
            // InternalGrana.g:2156:2: rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5
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
    // InternalGrana.g:2163:1: rule__CompareJob__Group__4__Impl : ( 'layoutoptions' ) ;
    public final void rule__CompareJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2167:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2168:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2168:1: ( 'layoutoptions' )
            // InternalGrana.g:2169:2: 'layoutoptions'
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
    // InternalGrana.g:2178:1: rule__CompareJob__Group__5 : rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 ;
    public final void rule__CompareJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2182:1: ( rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 )
            // InternalGrana.g:2183:2: rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6
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
    // InternalGrana.g:2190:1: rule__CompareJob__Group__5__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) ;
    public final void rule__CompareJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2194:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) )
            // InternalGrana.g:2195:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            {
            // InternalGrana.g:2195:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            // InternalGrana.g:2196:2: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_5()); 
            // InternalGrana.g:2197:2: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            // InternalGrana.g:2197:3: rule__CompareJob__LayoutOptionsAssignment_5
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
    // InternalGrana.g:2205:1: rule__CompareJob__Group__6 : rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 ;
    public final void rule__CompareJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2209:1: ( rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 )
            // InternalGrana.g:2210:2: rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7
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
    // InternalGrana.g:2217:1: rule__CompareJob__Group__6__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) ;
    public final void rule__CompareJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2221:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) )
            // InternalGrana.g:2222:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            {
            // InternalGrana.g:2222:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2223:2: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2224:2: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2224:3: rule__CompareJob__LayoutOptionsAssignment_6
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
    // InternalGrana.g:2232:1: rule__CompareJob__Group__7 : rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 ;
    public final void rule__CompareJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2236:1: ( rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 )
            // InternalGrana.g:2237:2: rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8
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
    // InternalGrana.g:2244:1: rule__CompareJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__CompareJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2248:1: ( ( 'analyses' ) )
            // InternalGrana.g:2249:1: ( 'analyses' )
            {
            // InternalGrana.g:2249:1: ( 'analyses' )
            // InternalGrana.g:2250:2: 'analyses'
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
    // InternalGrana.g:2259:1: rule__CompareJob__Group__8 : rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 ;
    public final void rule__CompareJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2263:1: ( rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 )
            // InternalGrana.g:2264:2: rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9
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
    // InternalGrana.g:2271:1: rule__CompareJob__Group__8__Impl : ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__CompareJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2275:1: ( ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2276:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2276:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2277:2: ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2277:2: ( ( rule__CompareJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2278:3: ( rule__CompareJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2279:3: ( rule__CompareJob__AnalysesAssignment_8 )
            // InternalGrana.g:2279:4: rule__CompareJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__CompareJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2282:2: ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2283:3: ( rule__CompareJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2284:3: ( rule__CompareJob__AnalysesAssignment_8 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalGrana.g:2284:4: rule__CompareJob__AnalysesAssignment_8
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
    // InternalGrana.g:2293:1: rule__CompareJob__Group__9 : rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 ;
    public final void rule__CompareJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2297:1: ( rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 )
            // InternalGrana.g:2298:2: rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10
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
    // InternalGrana.g:2305:1: rule__CompareJob__Group__9__Impl : ( 'output' ) ;
    public final void rule__CompareJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2309:1: ( ( 'output' ) )
            // InternalGrana.g:2310:1: ( 'output' )
            {
            // InternalGrana.g:2310:1: ( 'output' )
            // InternalGrana.g:2311:2: 'output'
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
    // InternalGrana.g:2320:1: rule__CompareJob__Group__10 : rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 ;
    public final void rule__CompareJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2324:1: ( rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 )
            // InternalGrana.g:2325:2: rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11
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
    // InternalGrana.g:2332:1: rule__CompareJob__Group__10__Impl : ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) ;
    public final void rule__CompareJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2336:1: ( ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) )
            // InternalGrana.g:2337:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            {
            // InternalGrana.g:2337:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            // InternalGrana.g:2338:2: ( rule__CompareJob__OutputTypeAssignment_10 )?
            {
             before(grammarAccess.getCompareJobAccess().getOutputTypeAssignment_10()); 
            // InternalGrana.g:2339:2: ( rule__CompareJob__OutputTypeAssignment_10 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=16 && LA31_0<=17)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalGrana.g:2339:3: rule__CompareJob__OutputTypeAssignment_10
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
    // InternalGrana.g:2347:1: rule__CompareJob__Group__11 : rule__CompareJob__Group__11__Impl ;
    public final void rule__CompareJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2351:1: ( rule__CompareJob__Group__11__Impl )
            // InternalGrana.g:2352:2: rule__CompareJob__Group__11__Impl
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
    // InternalGrana.g:2358:1: rule__CompareJob__Group__11__Impl : ( ( rule__CompareJob__OutputAssignment_11 ) ) ;
    public final void rule__CompareJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2362:1: ( ( ( rule__CompareJob__OutputAssignment_11 ) ) )
            // InternalGrana.g:2363:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            {
            // InternalGrana.g:2363:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            // InternalGrana.g:2364:2: ( rule__CompareJob__OutputAssignment_11 )
            {
             before(grammarAccess.getCompareJobAccess().getOutputAssignment_11()); 
            // InternalGrana.g:2365:2: ( rule__CompareJob__OutputAssignment_11 )
            // InternalGrana.g:2365:3: rule__CompareJob__OutputAssignment_11
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
    // InternalGrana.g:2374:1: rule__RangeJob__Group__0 : rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 ;
    public final void rule__RangeJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2378:1: ( rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 )
            // InternalGrana.g:2379:2: rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1
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
    // InternalGrana.g:2386:1: rule__RangeJob__Group__0__Impl : ( 'rangejob' ) ;
    public final void rule__RangeJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2390:1: ( ( 'rangejob' ) )
            // InternalGrana.g:2391:1: ( 'rangejob' )
            {
            // InternalGrana.g:2391:1: ( 'rangejob' )
            // InternalGrana.g:2392:2: 'rangejob'
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
    // InternalGrana.g:2401:1: rule__RangeJob__Group__1 : rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 ;
    public final void rule__RangeJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2405:1: ( rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 )
            // InternalGrana.g:2406:2: rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2
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
    // InternalGrana.g:2413:1: rule__RangeJob__Group__1__Impl : ( ( rule__RangeJob__NameAssignment_1 ) ) ;
    public final void rule__RangeJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2417:1: ( ( ( rule__RangeJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2418:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2418:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            // InternalGrana.g:2419:2: ( rule__RangeJob__NameAssignment_1 )
            {
             before(grammarAccess.getRangeJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2420:2: ( rule__RangeJob__NameAssignment_1 )
            // InternalGrana.g:2420:3: rule__RangeJob__NameAssignment_1
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
    // InternalGrana.g:2428:1: rule__RangeJob__Group__2 : rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 ;
    public final void rule__RangeJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2432:1: ( rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 )
            // InternalGrana.g:2433:2: rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3
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
    // InternalGrana.g:2440:1: rule__RangeJob__Group__2__Impl : ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) ;
    public final void rule__RangeJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2444:1: ( ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) )
            // InternalGrana.g:2445:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            {
            // InternalGrana.g:2445:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            // InternalGrana.g:2446:2: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeAssignment_2()); 
            // InternalGrana.g:2447:2: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==64) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalGrana.g:2447:3: rule__RangeJob__MeasureExecutionTimeAssignment_2
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
    // InternalGrana.g:2455:1: rule__RangeJob__Group__3 : rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 ;
    public final void rule__RangeJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2459:1: ( rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 )
            // InternalGrana.g:2460:2: rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4
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
    // InternalGrana.g:2467:1: rule__RangeJob__Group__3__Impl : ( 'resources' ) ;
    public final void rule__RangeJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2471:1: ( ( 'resources' ) )
            // InternalGrana.g:2472:1: ( 'resources' )
            {
            // InternalGrana.g:2472:1: ( 'resources' )
            // InternalGrana.g:2473:2: 'resources'
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
    // InternalGrana.g:2482:1: rule__RangeJob__Group__4 : rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 ;
    public final void rule__RangeJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2486:1: ( rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 )
            // InternalGrana.g:2487:2: rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5
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
    // InternalGrana.g:2494:1: rule__RangeJob__Group__4__Impl : ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) ;
    public final void rule__RangeJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2498:1: ( ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) )
            // InternalGrana.g:2499:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            {
            // InternalGrana.g:2499:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            // InternalGrana.g:2500:2: ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            {
            // InternalGrana.g:2500:2: ( ( rule__RangeJob__ResourcesAssignment_4 ) )
            // InternalGrana.g:2501:3: ( rule__RangeJob__ResourcesAssignment_4 )
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2502:3: ( rule__RangeJob__ResourcesAssignment_4 )
            // InternalGrana.g:2502:4: rule__RangeJob__ResourcesAssignment_4
            {
            pushFollow(FOLLOW_13);
            rule__RangeJob__ResourcesAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 

            }

            // InternalGrana.g:2505:2: ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            // InternalGrana.g:2506:3: ( rule__RangeJob__ResourcesAssignment_4 )*
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2507:3: ( rule__RangeJob__ResourcesAssignment_4 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_STRING||LA33_0==38) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalGrana.g:2507:4: rule__RangeJob__ResourcesAssignment_4
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
    // InternalGrana.g:2516:1: rule__RangeJob__Group__5 : rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 ;
    public final void rule__RangeJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2520:1: ( rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 )
            // InternalGrana.g:2521:2: rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6
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
    // InternalGrana.g:2528:1: rule__RangeJob__Group__5__Impl : ( 'layoutoptions' ) ;
    public final void rule__RangeJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2532:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2533:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2533:1: ( 'layoutoptions' )
            // InternalGrana.g:2534:2: 'layoutoptions'
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
    // InternalGrana.g:2543:1: rule__RangeJob__Group__6 : rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 ;
    public final void rule__RangeJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2547:1: ( rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 )
            // InternalGrana.g:2548:2: rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7
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
    // InternalGrana.g:2555:1: rule__RangeJob__Group__6__Impl : ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) ;
    public final void rule__RangeJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2559:1: ( ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) )
            // InternalGrana.g:2560:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            {
            // InternalGrana.g:2560:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            // InternalGrana.g:2561:2: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            {
            // InternalGrana.g:2561:2: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2562:3: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2563:3: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2563:4: rule__RangeJob__LayoutOptionsAssignment_6
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__LayoutOptionsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 

            }

            // InternalGrana.g:2566:2: ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            // InternalGrana.g:2567:3: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2568:3: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalGrana.g:2568:4: rule__RangeJob__LayoutOptionsAssignment_6
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
    // InternalGrana.g:2577:1: rule__RangeJob__Group__7 : rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 ;
    public final void rule__RangeJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2581:1: ( rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 )
            // InternalGrana.g:2582:2: rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8
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
    // InternalGrana.g:2589:1: rule__RangeJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__RangeJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2593:1: ( ( 'analyses' ) )
            // InternalGrana.g:2594:1: ( 'analyses' )
            {
            // InternalGrana.g:2594:1: ( 'analyses' )
            // InternalGrana.g:2595:2: 'analyses'
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
    // InternalGrana.g:2604:1: rule__RangeJob__Group__8 : rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 ;
    public final void rule__RangeJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2608:1: ( rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 )
            // InternalGrana.g:2609:2: rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9
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
    // InternalGrana.g:2616:1: rule__RangeJob__Group__8__Impl : ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__RangeJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2620:1: ( ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2621:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2621:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2622:2: ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2622:2: ( ( rule__RangeJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2623:3: ( rule__RangeJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2624:3: ( rule__RangeJob__AnalysesAssignment_8 )
            // InternalGrana.g:2624:4: rule__RangeJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2627:2: ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2628:3: ( rule__RangeJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2629:3: ( rule__RangeJob__AnalysesAssignment_8 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalGrana.g:2629:4: rule__RangeJob__AnalysesAssignment_8
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
    // InternalGrana.g:2638:1: rule__RangeJob__Group__9 : rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 ;
    public final void rule__RangeJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2642:1: ( rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 )
            // InternalGrana.g:2643:2: rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10
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
    // InternalGrana.g:2650:1: rule__RangeJob__Group__9__Impl : ( 'rangeoption' ) ;
    public final void rule__RangeJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2654:1: ( ( 'rangeoption' ) )
            // InternalGrana.g:2655:1: ( 'rangeoption' )
            {
            // InternalGrana.g:2655:1: ( 'rangeoption' )
            // InternalGrana.g:2656:2: 'rangeoption'
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
    // InternalGrana.g:2665:1: rule__RangeJob__Group__10 : rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 ;
    public final void rule__RangeJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2669:1: ( rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 )
            // InternalGrana.g:2670:2: rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11
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
    // InternalGrana.g:2677:1: rule__RangeJob__Group__10__Impl : ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) ;
    public final void rule__RangeJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2681:1: ( ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) )
            // InternalGrana.g:2682:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            {
            // InternalGrana.g:2682:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            // InternalGrana.g:2683:2: ( rule__RangeJob__RangeOptionAssignment_10 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_10()); 
            // InternalGrana.g:2684:2: ( rule__RangeJob__RangeOptionAssignment_10 )
            // InternalGrana.g:2684:3: rule__RangeJob__RangeOptionAssignment_10
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
    // InternalGrana.g:2692:1: rule__RangeJob__Group__11 : rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 ;
    public final void rule__RangeJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2696:1: ( rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 )
            // InternalGrana.g:2697:2: rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12
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
    // InternalGrana.g:2704:1: rule__RangeJob__Group__11__Impl : ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) ;
    public final void rule__RangeJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2708:1: ( ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) )
            // InternalGrana.g:2709:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            {
            // InternalGrana.g:2709:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            // InternalGrana.g:2710:2: ( rule__RangeJob__RangeValuesAssignment_11 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_11()); 
            // InternalGrana.g:2711:2: ( rule__RangeJob__RangeValuesAssignment_11 )
            // InternalGrana.g:2711:3: rule__RangeJob__RangeValuesAssignment_11
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
    // InternalGrana.g:2719:1: rule__RangeJob__Group__12 : rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 ;
    public final void rule__RangeJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2723:1: ( rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 )
            // InternalGrana.g:2724:2: rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13
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
    // InternalGrana.g:2731:1: rule__RangeJob__Group__12__Impl : ( ( rule__RangeJob__Alternatives_12 ) ) ;
    public final void rule__RangeJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2735:1: ( ( ( rule__RangeJob__Alternatives_12 ) ) )
            // InternalGrana.g:2736:1: ( ( rule__RangeJob__Alternatives_12 ) )
            {
            // InternalGrana.g:2736:1: ( ( rule__RangeJob__Alternatives_12 ) )
            // InternalGrana.g:2737:2: ( rule__RangeJob__Alternatives_12 )
            {
             before(grammarAccess.getRangeJobAccess().getAlternatives_12()); 
            // InternalGrana.g:2738:2: ( rule__RangeJob__Alternatives_12 )
            // InternalGrana.g:2738:3: rule__RangeJob__Alternatives_12
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
    // InternalGrana.g:2746:1: rule__RangeJob__Group__13 : rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 ;
    public final void rule__RangeJob__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2750:1: ( rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 )
            // InternalGrana.g:2751:2: rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14
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
    // InternalGrana.g:2758:1: rule__RangeJob__Group__13__Impl : ( 'output' ) ;
    public final void rule__RangeJob__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2762:1: ( ( 'output' ) )
            // InternalGrana.g:2763:1: ( 'output' )
            {
            // InternalGrana.g:2763:1: ( 'output' )
            // InternalGrana.g:2764:2: 'output'
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
    // InternalGrana.g:2773:1: rule__RangeJob__Group__14 : rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 ;
    public final void rule__RangeJob__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2777:1: ( rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 )
            // InternalGrana.g:2778:2: rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15
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
    // InternalGrana.g:2785:1: rule__RangeJob__Group__14__Impl : ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) ;
    public final void rule__RangeJob__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2789:1: ( ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) )
            // InternalGrana.g:2790:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            {
            // InternalGrana.g:2790:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            // InternalGrana.g:2791:2: ( rule__RangeJob__OutputTypeAssignment_14 )?
            {
             before(grammarAccess.getRangeJobAccess().getOutputTypeAssignment_14()); 
            // InternalGrana.g:2792:2: ( rule__RangeJob__OutputTypeAssignment_14 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=16 && LA36_0<=17)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalGrana.g:2792:3: rule__RangeJob__OutputTypeAssignment_14
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
    // InternalGrana.g:2800:1: rule__RangeJob__Group__15 : rule__RangeJob__Group__15__Impl ;
    public final void rule__RangeJob__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2804:1: ( rule__RangeJob__Group__15__Impl )
            // InternalGrana.g:2805:2: rule__RangeJob__Group__15__Impl
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
    // InternalGrana.g:2811:1: rule__RangeJob__Group__15__Impl : ( ( rule__RangeJob__OutputAssignment_15 ) ) ;
    public final void rule__RangeJob__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2815:1: ( ( ( rule__RangeJob__OutputAssignment_15 ) ) )
            // InternalGrana.g:2816:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            {
            // InternalGrana.g:2816:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            // InternalGrana.g:2817:2: ( rule__RangeJob__OutputAssignment_15 )
            {
             before(grammarAccess.getRangeJobAccess().getOutputAssignment_15()); 
            // InternalGrana.g:2818:2: ( rule__RangeJob__OutputAssignment_15 )
            // InternalGrana.g:2818:3: rule__RangeJob__OutputAssignment_15
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
    // InternalGrana.g:2827:1: rule__RangeJob__Group_12_0__0 : rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 ;
    public final void rule__RangeJob__Group_12_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2831:1: ( rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 )
            // InternalGrana.g:2832:2: rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1
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
    // InternalGrana.g:2839:1: rule__RangeJob__Group_12_0__0__Impl : ( 'rangeanalysis' ) ;
    public final void rule__RangeJob__Group_12_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2843:1: ( ( 'rangeanalysis' ) )
            // InternalGrana.g:2844:1: ( 'rangeanalysis' )
            {
            // InternalGrana.g:2844:1: ( 'rangeanalysis' )
            // InternalGrana.g:2845:2: 'rangeanalysis'
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
    // InternalGrana.g:2854:1: rule__RangeJob__Group_12_0__1 : rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 ;
    public final void rule__RangeJob__Group_12_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2858:1: ( rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 )
            // InternalGrana.g:2859:2: rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2
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
    // InternalGrana.g:2866:1: rule__RangeJob__Group_12_0__1__Impl : ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) ;
    public final void rule__RangeJob__Group_12_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2870:1: ( ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) )
            // InternalGrana.g:2871:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            {
            // InternalGrana.g:2871:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            // InternalGrana.g:2872:2: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12_0_1()); 
            // InternalGrana.g:2873:2: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            // InternalGrana.g:2873:3: rule__RangeJob__RangeAnalysisAssignment_12_0_1
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
    // InternalGrana.g:2881:1: rule__RangeJob__Group_12_0__2 : rule__RangeJob__Group_12_0__2__Impl ;
    public final void rule__RangeJob__Group_12_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2885:1: ( rule__RangeJob__Group_12_0__2__Impl )
            // InternalGrana.g:2886:2: rule__RangeJob__Group_12_0__2__Impl
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
    // InternalGrana.g:2892:1: rule__RangeJob__Group_12_0__2__Impl : ( ( rule__RangeJob__Group_12_0_2__0 )? ) ;
    public final void rule__RangeJob__Group_12_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2896:1: ( ( ( rule__RangeJob__Group_12_0_2__0 )? ) )
            // InternalGrana.g:2897:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            {
            // InternalGrana.g:2897:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            // InternalGrana.g:2898:2: ( rule__RangeJob__Group_12_0_2__0 )?
            {
             before(grammarAccess.getRangeJobAccess().getGroup_12_0_2()); 
            // InternalGrana.g:2899:2: ( rule__RangeJob__Group_12_0_2__0 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==30) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalGrana.g:2899:3: rule__RangeJob__Group_12_0_2__0
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
    // InternalGrana.g:2908:1: rule__RangeJob__Group_12_0_2__0 : rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 ;
    public final void rule__RangeJob__Group_12_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2912:1: ( rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 )
            // InternalGrana.g:2913:2: rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1
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
    // InternalGrana.g:2920:1: rule__RangeJob__Group_12_0_2__0__Impl : ( 'component' ) ;
    public final void rule__RangeJob__Group_12_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2924:1: ( ( 'component' ) )
            // InternalGrana.g:2925:1: ( 'component' )
            {
            // InternalGrana.g:2925:1: ( 'component' )
            // InternalGrana.g:2926:2: 'component'
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
    // InternalGrana.g:2935:1: rule__RangeJob__Group_12_0_2__1 : rule__RangeJob__Group_12_0_2__1__Impl ;
    public final void rule__RangeJob__Group_12_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2939:1: ( rule__RangeJob__Group_12_0_2__1__Impl )
            // InternalGrana.g:2940:2: rule__RangeJob__Group_12_0_2__1__Impl
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
    // InternalGrana.g:2946:1: rule__RangeJob__Group_12_0_2__1__Impl : ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) ;
    public final void rule__RangeJob__Group_12_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2950:1: ( ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) )
            // InternalGrana.g:2951:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            {
            // InternalGrana.g:2951:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            // InternalGrana.g:2952:2: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_12_0_2_1()); 
            // InternalGrana.g:2953:2: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            // InternalGrana.g:2953:3: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1
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
    // InternalGrana.g:2962:1: rule__RangeJob__Group_12_1__0 : rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 ;
    public final void rule__RangeJob__Group_12_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2966:1: ( rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 )
            // InternalGrana.g:2967:2: rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1
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
    // InternalGrana.g:2974:1: rule__RangeJob__Group_12_1__0__Impl : ( 'rangeanalyses' ) ;
    public final void rule__RangeJob__Group_12_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2978:1: ( ( 'rangeanalyses' ) )
            // InternalGrana.g:2979:1: ( 'rangeanalyses' )
            {
            // InternalGrana.g:2979:1: ( 'rangeanalyses' )
            // InternalGrana.g:2980:2: 'rangeanalyses'
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
    // InternalGrana.g:2989:1: rule__RangeJob__Group_12_1__1 : rule__RangeJob__Group_12_1__1__Impl ;
    public final void rule__RangeJob__Group_12_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:2993:1: ( rule__RangeJob__Group_12_1__1__Impl )
            // InternalGrana.g:2994:2: rule__RangeJob__Group_12_1__1__Impl
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
    // InternalGrana.g:3000:1: rule__RangeJob__Group_12_1__1__Impl : ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) ;
    public final void rule__RangeJob__Group_12_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3004:1: ( ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) )
            // InternalGrana.g:3005:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            {
            // InternalGrana.g:3005:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            // InternalGrana.g:3006:2: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            {
            // InternalGrana.g:3006:2: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) )
            // InternalGrana.g:3007:3: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3008:3: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            // InternalGrana.g:3008:4: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 

            }

            // InternalGrana.g:3011:2: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            // InternalGrana.g:3012:3: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3013:3: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==RULE_ID) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalGrana.g:3013:4: rule__RangeJob__RangeAnalysesAssignment_12_1_1
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
    // InternalGrana.g:3023:1: rule__FloatRange__Group__0 : rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 ;
    public final void rule__FloatRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3027:1: ( rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 )
            // InternalGrana.g:3028:2: rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1
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
    // InternalGrana.g:3035:1: rule__FloatRange__Group__0__Impl : ( 'floatvalues' ) ;
    public final void rule__FloatRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3039:1: ( ( 'floatvalues' ) )
            // InternalGrana.g:3040:1: ( 'floatvalues' )
            {
            // InternalGrana.g:3040:1: ( 'floatvalues' )
            // InternalGrana.g:3041:2: 'floatvalues'
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
    // InternalGrana.g:3050:1: rule__FloatRange__Group__1 : rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 ;
    public final void rule__FloatRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3054:1: ( rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 )
            // InternalGrana.g:3055:2: rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2
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
    // InternalGrana.g:3062:1: rule__FloatRange__Group__1__Impl : ( ( rule__FloatRange__ValuesAssignment_1 ) ) ;
    public final void rule__FloatRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3066:1: ( ( ( rule__FloatRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3067:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3067:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            // InternalGrana.g:3068:2: ( rule__FloatRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3069:2: ( rule__FloatRange__ValuesAssignment_1 )
            // InternalGrana.g:3069:3: rule__FloatRange__ValuesAssignment_1
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
    // InternalGrana.g:3077:1: rule__FloatRange__Group__2 : rule__FloatRange__Group__2__Impl ;
    public final void rule__FloatRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3081:1: ( rule__FloatRange__Group__2__Impl )
            // InternalGrana.g:3082:2: rule__FloatRange__Group__2__Impl
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
    // InternalGrana.g:3088:1: rule__FloatRange__Group__2__Impl : ( ( rule__FloatRange__Group_2__0 )* ) ;
    public final void rule__FloatRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3092:1: ( ( ( rule__FloatRange__Group_2__0 )* ) )
            // InternalGrana.g:3093:1: ( ( rule__FloatRange__Group_2__0 )* )
            {
            // InternalGrana.g:3093:1: ( ( rule__FloatRange__Group_2__0 )* )
            // InternalGrana.g:3094:2: ( rule__FloatRange__Group_2__0 )*
            {
             before(grammarAccess.getFloatRangeAccess().getGroup_2()); 
            // InternalGrana.g:3095:2: ( rule__FloatRange__Group_2__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==33) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalGrana.g:3095:3: rule__FloatRange__Group_2__0
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
    // InternalGrana.g:3104:1: rule__FloatRange__Group_2__0 : rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 ;
    public final void rule__FloatRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3108:1: ( rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 )
            // InternalGrana.g:3109:2: rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1
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
    // InternalGrana.g:3116:1: rule__FloatRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__FloatRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3120:1: ( ( ',' ) )
            // InternalGrana.g:3121:1: ( ',' )
            {
            // InternalGrana.g:3121:1: ( ',' )
            // InternalGrana.g:3122:2: ','
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
    // InternalGrana.g:3131:1: rule__FloatRange__Group_2__1 : rule__FloatRange__Group_2__1__Impl ;
    public final void rule__FloatRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3135:1: ( rule__FloatRange__Group_2__1__Impl )
            // InternalGrana.g:3136:2: rule__FloatRange__Group_2__1__Impl
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
    // InternalGrana.g:3142:1: rule__FloatRange__Group_2__1__Impl : ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__FloatRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3146:1: ( ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3147:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3147:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3148:2: ( rule__FloatRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3149:2: ( rule__FloatRange__ValuesAssignment_2_1 )
            // InternalGrana.g:3149:3: rule__FloatRange__ValuesAssignment_2_1
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
    // InternalGrana.g:3158:1: rule__IntRangeValues__Group__0 : rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 ;
    public final void rule__IntRangeValues__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3162:1: ( rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 )
            // InternalGrana.g:3163:2: rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1
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
    // InternalGrana.g:3170:1: rule__IntRangeValues__Group__0__Impl : ( 'intvalues' ) ;
    public final void rule__IntRangeValues__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3174:1: ( ( 'intvalues' ) )
            // InternalGrana.g:3175:1: ( 'intvalues' )
            {
            // InternalGrana.g:3175:1: ( 'intvalues' )
            // InternalGrana.g:3176:2: 'intvalues'
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
    // InternalGrana.g:3185:1: rule__IntRangeValues__Group__1 : rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 ;
    public final void rule__IntRangeValues__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3189:1: ( rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 )
            // InternalGrana.g:3190:2: rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2
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
    // InternalGrana.g:3197:1: rule__IntRangeValues__Group__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) ;
    public final void rule__IntRangeValues__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3201:1: ( ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3202:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3202:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            // InternalGrana.g:3203:2: ( rule__IntRangeValues__ValuesAssignment_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3204:2: ( rule__IntRangeValues__ValuesAssignment_1 )
            // InternalGrana.g:3204:3: rule__IntRangeValues__ValuesAssignment_1
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
    // InternalGrana.g:3212:1: rule__IntRangeValues__Group__2 : rule__IntRangeValues__Group__2__Impl ;
    public final void rule__IntRangeValues__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3216:1: ( rule__IntRangeValues__Group__2__Impl )
            // InternalGrana.g:3217:2: rule__IntRangeValues__Group__2__Impl
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
    // InternalGrana.g:3223:1: rule__IntRangeValues__Group__2__Impl : ( ( rule__IntRangeValues__Group_2__0 )* ) ;
    public final void rule__IntRangeValues__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3227:1: ( ( ( rule__IntRangeValues__Group_2__0 )* ) )
            // InternalGrana.g:3228:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            {
            // InternalGrana.g:3228:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            // InternalGrana.g:3229:2: ( rule__IntRangeValues__Group_2__0 )*
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup_2()); 
            // InternalGrana.g:3230:2: ( rule__IntRangeValues__Group_2__0 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==33) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalGrana.g:3230:3: rule__IntRangeValues__Group_2__0
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
    // InternalGrana.g:3239:1: rule__IntRangeValues__Group_2__0 : rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 ;
    public final void rule__IntRangeValues__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3243:1: ( rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 )
            // InternalGrana.g:3244:2: rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1
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
    // InternalGrana.g:3251:1: rule__IntRangeValues__Group_2__0__Impl : ( ',' ) ;
    public final void rule__IntRangeValues__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3255:1: ( ( ',' ) )
            // InternalGrana.g:3256:1: ( ',' )
            {
            // InternalGrana.g:3256:1: ( ',' )
            // InternalGrana.g:3257:2: ','
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
    // InternalGrana.g:3266:1: rule__IntRangeValues__Group_2__1 : rule__IntRangeValues__Group_2__1__Impl ;
    public final void rule__IntRangeValues__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3270:1: ( rule__IntRangeValues__Group_2__1__Impl )
            // InternalGrana.g:3271:2: rule__IntRangeValues__Group_2__1__Impl
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
    // InternalGrana.g:3277:1: rule__IntRangeValues__Group_2__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) ;
    public final void rule__IntRangeValues__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3281:1: ( ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3282:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3282:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3283:2: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3284:2: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            // InternalGrana.g:3284:3: rule__IntRangeValues__ValuesAssignment_2_1
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
    // InternalGrana.g:3293:1: rule__IntRangeRange__Group__0 : rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 ;
    public final void rule__IntRangeRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3297:1: ( rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 )
            // InternalGrana.g:3298:2: rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1
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
    // InternalGrana.g:3305:1: rule__IntRangeRange__Group__0__Impl : ( 'intrange' ) ;
    public final void rule__IntRangeRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3309:1: ( ( 'intrange' ) )
            // InternalGrana.g:3310:1: ( 'intrange' )
            {
            // InternalGrana.g:3310:1: ( 'intrange' )
            // InternalGrana.g:3311:2: 'intrange'
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
    // InternalGrana.g:3320:1: rule__IntRangeRange__Group__1 : rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 ;
    public final void rule__IntRangeRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3324:1: ( rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 )
            // InternalGrana.g:3325:2: rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2
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
    // InternalGrana.g:3332:1: rule__IntRangeRange__Group__1__Impl : ( ( rule__IntRangeRange__StartAssignment_1 ) ) ;
    public final void rule__IntRangeRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3336:1: ( ( ( rule__IntRangeRange__StartAssignment_1 ) ) )
            // InternalGrana.g:3337:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            {
            // InternalGrana.g:3337:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            // InternalGrana.g:3338:2: ( rule__IntRangeRange__StartAssignment_1 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1()); 
            // InternalGrana.g:3339:2: ( rule__IntRangeRange__StartAssignment_1 )
            // InternalGrana.g:3339:3: rule__IntRangeRange__StartAssignment_1
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
    // InternalGrana.g:3347:1: rule__IntRangeRange__Group__2 : rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 ;
    public final void rule__IntRangeRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3351:1: ( rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 )
            // InternalGrana.g:3352:2: rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3
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
    // InternalGrana.g:3359:1: rule__IntRangeRange__Group__2__Impl : ( 'to' ) ;
    public final void rule__IntRangeRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3363:1: ( ( 'to' ) )
            // InternalGrana.g:3364:1: ( 'to' )
            {
            // InternalGrana.g:3364:1: ( 'to' )
            // InternalGrana.g:3365:2: 'to'
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
    // InternalGrana.g:3374:1: rule__IntRangeRange__Group__3 : rule__IntRangeRange__Group__3__Impl ;
    public final void rule__IntRangeRange__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3378:1: ( rule__IntRangeRange__Group__3__Impl )
            // InternalGrana.g:3379:2: rule__IntRangeRange__Group__3__Impl
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
    // InternalGrana.g:3385:1: rule__IntRangeRange__Group__3__Impl : ( ( rule__IntRangeRange__EndAssignment_3 ) ) ;
    public final void rule__IntRangeRange__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3389:1: ( ( ( rule__IntRangeRange__EndAssignment_3 ) ) )
            // InternalGrana.g:3390:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            {
            // InternalGrana.g:3390:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            // InternalGrana.g:3391:2: ( rule__IntRangeRange__EndAssignment_3 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3()); 
            // InternalGrana.g:3392:2: ( rule__IntRangeRange__EndAssignment_3 )
            // InternalGrana.g:3392:3: rule__IntRangeRange__EndAssignment_3
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
    // InternalGrana.g:3401:1: rule__EnumRange__Group__0 : rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1 ;
    public final void rule__EnumRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3405:1: ( rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1 )
            // InternalGrana.g:3406:2: rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1
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
    // InternalGrana.g:3413:1: rule__EnumRange__Group__0__Impl : ( 'enumvalues' ) ;
    public final void rule__EnumRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3417:1: ( ( 'enumvalues' ) )
            // InternalGrana.g:3418:1: ( 'enumvalues' )
            {
            // InternalGrana.g:3418:1: ( 'enumvalues' )
            // InternalGrana.g:3419:2: 'enumvalues'
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
    // InternalGrana.g:3428:1: rule__EnumRange__Group__1 : rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2 ;
    public final void rule__EnumRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3432:1: ( rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2 )
            // InternalGrana.g:3433:2: rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2
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
    // InternalGrana.g:3440:1: rule__EnumRange__Group__1__Impl : ( ( rule__EnumRange__ValuesAssignment_1 ) ) ;
    public final void rule__EnumRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3444:1: ( ( ( rule__EnumRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3445:1: ( ( rule__EnumRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3445:1: ( ( rule__EnumRange__ValuesAssignment_1 ) )
            // InternalGrana.g:3446:2: ( rule__EnumRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getEnumRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3447:2: ( rule__EnumRange__ValuesAssignment_1 )
            // InternalGrana.g:3447:3: rule__EnumRange__ValuesAssignment_1
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
    // InternalGrana.g:3455:1: rule__EnumRange__Group__2 : rule__EnumRange__Group__2__Impl ;
    public final void rule__EnumRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3459:1: ( rule__EnumRange__Group__2__Impl )
            // InternalGrana.g:3460:2: rule__EnumRange__Group__2__Impl
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
    // InternalGrana.g:3466:1: rule__EnumRange__Group__2__Impl : ( ( rule__EnumRange__Group_2__0 )* ) ;
    public final void rule__EnumRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3470:1: ( ( ( rule__EnumRange__Group_2__0 )* ) )
            // InternalGrana.g:3471:1: ( ( rule__EnumRange__Group_2__0 )* )
            {
            // InternalGrana.g:3471:1: ( ( rule__EnumRange__Group_2__0 )* )
            // InternalGrana.g:3472:2: ( rule__EnumRange__Group_2__0 )*
            {
             before(grammarAccess.getEnumRangeAccess().getGroup_2()); 
            // InternalGrana.g:3473:2: ( rule__EnumRange__Group_2__0 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==33) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalGrana.g:3473:3: rule__EnumRange__Group_2__0
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
    // InternalGrana.g:3482:1: rule__EnumRange__Group_2__0 : rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1 ;
    public final void rule__EnumRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3486:1: ( rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1 )
            // InternalGrana.g:3487:2: rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1
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
    // InternalGrana.g:3494:1: rule__EnumRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__EnumRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3498:1: ( ( ',' ) )
            // InternalGrana.g:3499:1: ( ',' )
            {
            // InternalGrana.g:3499:1: ( ',' )
            // InternalGrana.g:3500:2: ','
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
    // InternalGrana.g:3509:1: rule__EnumRange__Group_2__1 : rule__EnumRange__Group_2__1__Impl ;
    public final void rule__EnumRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3513:1: ( rule__EnumRange__Group_2__1__Impl )
            // InternalGrana.g:3514:2: rule__EnumRange__Group_2__1__Impl
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
    // InternalGrana.g:3520:1: rule__EnumRange__Group_2__1__Impl : ( ( rule__EnumRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__EnumRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3524:1: ( ( ( rule__EnumRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3525:1: ( ( rule__EnumRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3525:1: ( ( rule__EnumRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3526:2: ( rule__EnumRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getEnumRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3527:2: ( rule__EnumRange__ValuesAssignment_2_1 )
            // InternalGrana.g:3527:3: rule__EnumRange__ValuesAssignment_2_1
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
    // InternalGrana.g:3536:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3540:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // InternalGrana.g:3541:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
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
    // InternalGrana.g:3548:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3552:1: ( ( 'ref' ) )
            // InternalGrana.g:3553:1: ( 'ref' )
            {
            // InternalGrana.g:3553:1: ( 'ref' )
            // InternalGrana.g:3554:2: 'ref'
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
    // InternalGrana.g:3563:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3567:1: ( rule__ResourceReference__Group__1__Impl )
            // InternalGrana.g:3568:2: rule__ResourceReference__Group__1__Impl
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
    // InternalGrana.g:3574:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3578:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // InternalGrana.g:3579:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // InternalGrana.g:3579:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // InternalGrana.g:3580:2: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // InternalGrana.g:3580:2: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // InternalGrana.g:3581:3: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:3582:3: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // InternalGrana.g:3582:4: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_3);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // InternalGrana.g:3585:2: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // InternalGrana.g:3586:3: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:3587:3: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==RULE_ID) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalGrana.g:3587:4: rule__ResourceReference__ResourceRefsAssignment_1
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
    // InternalGrana.g:3597:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3601:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // InternalGrana.g:3602:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
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
    // InternalGrana.g:3609:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3613:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // InternalGrana.g:3614:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:3614:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // InternalGrana.g:3615:2: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:3616:2: ( rule__GlobalResourceRef__NameAssignment_0 )
            // InternalGrana.g:3616:3: rule__GlobalResourceRef__NameAssignment_0
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
    // InternalGrana.g:3624:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3628:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // InternalGrana.g:3629:2: rule__GlobalResourceRef__Group__1__Impl
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
    // InternalGrana.g:3635:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3639:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // InternalGrana.g:3640:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // InternalGrana.g:3640:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // InternalGrana.g:3641:2: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // InternalGrana.g:3642:2: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // InternalGrana.g:3642:3: rule__GlobalResourceRef__ResourcesAssignment_1
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
    // InternalGrana.g:3651:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3655:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // InternalGrana.g:3656:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
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
    // InternalGrana.g:3663:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3667:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // InternalGrana.g:3668:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // InternalGrana.g:3668:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // InternalGrana.g:3669:2: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // InternalGrana.g:3670:2: ( rule__LocalResource__PathAssignment_0 )
            // InternalGrana.g:3670:3: rule__LocalResource__PathAssignment_0
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
    // InternalGrana.g:3678:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2 ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3682:1: ( rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2 )
            // InternalGrana.g:3683:2: rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2
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
    // InternalGrana.g:3690:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3694:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // InternalGrana.g:3695:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // InternalGrana.g:3695:1: ( ( rule__LocalResource__Group_1__0 ) )
            // InternalGrana.g:3696:2: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // InternalGrana.g:3697:2: ( rule__LocalResource__Group_1__0 )
            // InternalGrana.g:3697:3: rule__LocalResource__Group_1__0
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
    // InternalGrana.g:3705:1: rule__LocalResource__Group__2 : rule__LocalResource__Group__2__Impl ;
    public final void rule__LocalResource__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3709:1: ( rule__LocalResource__Group__2__Impl )
            // InternalGrana.g:3710:2: rule__LocalResource__Group__2__Impl
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
    // InternalGrana.g:3716:1: rule__LocalResource__Group__2__Impl : ( ( rule__LocalResource__RecurseAssignment_2 )? ) ;
    public final void rule__LocalResource__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3720:1: ( ( ( rule__LocalResource__RecurseAssignment_2 )? ) )
            // InternalGrana.g:3721:1: ( ( rule__LocalResource__RecurseAssignment_2 )? )
            {
            // InternalGrana.g:3721:1: ( ( rule__LocalResource__RecurseAssignment_2 )? )
            // InternalGrana.g:3722:2: ( rule__LocalResource__RecurseAssignment_2 )?
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseAssignment_2()); 
            // InternalGrana.g:3723:2: ( rule__LocalResource__RecurseAssignment_2 )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==65) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalGrana.g:3723:3: rule__LocalResource__RecurseAssignment_2
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
    // InternalGrana.g:3732:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3736:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // InternalGrana.g:3737:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
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
    // InternalGrana.g:3744:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3748:1: ( ( 'filter' ) )
            // InternalGrana.g:3749:1: ( 'filter' )
            {
            // InternalGrana.g:3749:1: ( 'filter' )
            // InternalGrana.g:3750:2: 'filter'
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
    // InternalGrana.g:3759:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3763:1: ( rule__LocalResource__Group_1__1__Impl )
            // InternalGrana.g:3764:2: rule__LocalResource__Group_1__1__Impl
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
    // InternalGrana.g:3770:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3774:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // InternalGrana.g:3775:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // InternalGrana.g:3775:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // InternalGrana.g:3776:2: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // InternalGrana.g:3777:2: ( rule__LocalResource__FilterAssignment_1_1 )
            // InternalGrana.g:3777:3: rule__LocalResource__FilterAssignment_1_1
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
    // InternalGrana.g:3786:1: rule__GlobalOutputRef__Group__0 : rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 ;
    public final void rule__GlobalOutputRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3790:1: ( rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 )
            // InternalGrana.g:3791:2: rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1
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
    // InternalGrana.g:3798:1: rule__GlobalOutputRef__Group__0__Impl : ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalOutputRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3802:1: ( ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) )
            // InternalGrana.g:3803:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:3803:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            // InternalGrana.g:3804:2: ( rule__GlobalOutputRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:3805:2: ( rule__GlobalOutputRef__NameAssignment_0 )
            // InternalGrana.g:3805:3: rule__GlobalOutputRef__NameAssignment_0
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
    // InternalGrana.g:3813:1: rule__GlobalOutputRef__Group__1 : rule__GlobalOutputRef__Group__1__Impl ;
    public final void rule__GlobalOutputRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3817:1: ( rule__GlobalOutputRef__Group__1__Impl )
            // InternalGrana.g:3818:2: rule__GlobalOutputRef__Group__1__Impl
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
    // InternalGrana.g:3824:1: rule__GlobalOutputRef__Group__1__Impl : ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) ;
    public final void rule__GlobalOutputRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3828:1: ( ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) )
            // InternalGrana.g:3829:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            {
            // InternalGrana.g:3829:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            // InternalGrana.g:3830:2: ( rule__GlobalOutputRef__OutputAssignment_1 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 
            // InternalGrana.g:3831:2: ( rule__GlobalOutputRef__OutputAssignment_1 )
            // InternalGrana.g:3831:3: rule__GlobalOutputRef__OutputAssignment_1
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
    // InternalGrana.g:3840:1: rule__OutputReference__Group__0 : rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 ;
    public final void rule__OutputReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3844:1: ( rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 )
            // InternalGrana.g:3845:2: rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1
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
    // InternalGrana.g:3852:1: rule__OutputReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__OutputReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3856:1: ( ( 'ref' ) )
            // InternalGrana.g:3857:1: ( 'ref' )
            {
            // InternalGrana.g:3857:1: ( 'ref' )
            // InternalGrana.g:3858:2: 'ref'
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
    // InternalGrana.g:3867:1: rule__OutputReference__Group__1 : rule__OutputReference__Group__1__Impl ;
    public final void rule__OutputReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3871:1: ( rule__OutputReference__Group__1__Impl )
            // InternalGrana.g:3872:2: rule__OutputReference__Group__1__Impl
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
    // InternalGrana.g:3878:1: rule__OutputReference__Group__1__Impl : ( ( rule__OutputReference__OutputRefAssignment_1 ) ) ;
    public final void rule__OutputReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3882:1: ( ( ( rule__OutputReference__OutputRefAssignment_1 ) ) )
            // InternalGrana.g:3883:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            {
            // InternalGrana.g:3883:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            // InternalGrana.g:3884:2: ( rule__OutputReference__OutputRefAssignment_1 )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 
            // InternalGrana.g:3885:2: ( rule__OutputReference__OutputRefAssignment_1 )
            // InternalGrana.g:3885:3: rule__OutputReference__OutputRefAssignment_1
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
    // InternalGrana.g:3894:1: rule__LayoutConfig__Group__0 : rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 ;
    public final void rule__LayoutConfig__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3898:1: ( rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 )
            // InternalGrana.g:3899:2: rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1
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
    // InternalGrana.g:3906:1: rule__LayoutConfig__Group__0__Impl : ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) ;
    public final void rule__LayoutConfig__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3910:1: ( ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) )
            // InternalGrana.g:3911:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            {
            // InternalGrana.g:3911:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            // InternalGrana.g:3912:2: ( rule__LayoutConfig__IdentifierAssignment_0 )
            {
             before(grammarAccess.getLayoutConfigAccess().getIdentifierAssignment_0()); 
            // InternalGrana.g:3913:2: ( rule__LayoutConfig__IdentifierAssignment_0 )
            // InternalGrana.g:3913:3: rule__LayoutConfig__IdentifierAssignment_0
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
    // InternalGrana.g:3921:1: rule__LayoutConfig__Group__1 : rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 ;
    public final void rule__LayoutConfig__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3925:1: ( rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 )
            // InternalGrana.g:3926:2: rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2
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
    // InternalGrana.g:3933:1: rule__LayoutConfig__Group__1__Impl : ( '{' ) ;
    public final void rule__LayoutConfig__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3937:1: ( ( '{' ) )
            // InternalGrana.g:3938:1: ( '{' )
            {
            // InternalGrana.g:3938:1: ( '{' )
            // InternalGrana.g:3939:2: '{'
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
    // InternalGrana.g:3948:1: rule__LayoutConfig__Group__2 : rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 ;
    public final void rule__LayoutConfig__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3952:1: ( rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 )
            // InternalGrana.g:3953:2: rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3
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
    // InternalGrana.g:3960:1: rule__LayoutConfig__Group__2__Impl : ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) ;
    public final void rule__LayoutConfig__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3964:1: ( ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) )
            // InternalGrana.g:3965:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            {
            // InternalGrana.g:3965:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            // InternalGrana.g:3966:2: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getLayoutConfigAccess().getPropertiesAssignment_2()); 
            // InternalGrana.g:3967:2: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==RULE_ID) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalGrana.g:3967:3: rule__LayoutConfig__PropertiesAssignment_2
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
    // InternalGrana.g:3975:1: rule__LayoutConfig__Group__3 : rule__LayoutConfig__Group__3__Impl ;
    public final void rule__LayoutConfig__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3979:1: ( rule__LayoutConfig__Group__3__Impl )
            // InternalGrana.g:3980:2: rule__LayoutConfig__Group__3__Impl
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
    // InternalGrana.g:3986:1: rule__LayoutConfig__Group__3__Impl : ( '}' ) ;
    public final void rule__LayoutConfig__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:3990:1: ( ( '}' ) )
            // InternalGrana.g:3991:1: ( '}' )
            {
            // InternalGrana.g:3991:1: ( '}' )
            // InternalGrana.g:3992:2: '}'
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
    // InternalGrana.g:4002:1: rule__ElkNode__Group__0 : rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 ;
    public final void rule__ElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4006:1: ( rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 )
            // InternalGrana.g:4007:2: rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1
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
    // InternalGrana.g:4014:1: rule__ElkNode__Group__0__Impl : ( 'node' ) ;
    public final void rule__ElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4018:1: ( ( 'node' ) )
            // InternalGrana.g:4019:1: ( 'node' )
            {
            // InternalGrana.g:4019:1: ( 'node' )
            // InternalGrana.g:4020:2: 'node'
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
    // InternalGrana.g:4029:1: rule__ElkNode__Group__1 : rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 ;
    public final void rule__ElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4033:1: ( rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 )
            // InternalGrana.g:4034:2: rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2
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
    // InternalGrana.g:4041:1: rule__ElkNode__Group__1__Impl : ( ( rule__ElkNode__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4045:1: ( ( ( rule__ElkNode__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:4046:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:4046:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            // InternalGrana.g:4047:2: ( rule__ElkNode__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:4048:2: ( rule__ElkNode__IdentifierAssignment_1 )
            // InternalGrana.g:4048:3: rule__ElkNode__IdentifierAssignment_1
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
    // InternalGrana.g:4056:1: rule__ElkNode__Group__2 : rule__ElkNode__Group__2__Impl ;
    public final void rule__ElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4060:1: ( rule__ElkNode__Group__2__Impl )
            // InternalGrana.g:4061:2: rule__ElkNode__Group__2__Impl
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
    // InternalGrana.g:4067:1: rule__ElkNode__Group__2__Impl : ( ( rule__ElkNode__Group_2__0 )? ) ;
    public final void rule__ElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4071:1: ( ( ( rule__ElkNode__Group_2__0 )? ) )
            // InternalGrana.g:4072:1: ( ( rule__ElkNode__Group_2__0 )? )
            {
            // InternalGrana.g:4072:1: ( ( rule__ElkNode__Group_2__0 )? )
            // InternalGrana.g:4073:2: ( rule__ElkNode__Group_2__0 )?
            {
             before(grammarAccess.getElkNodeAccess().getGroup_2()); 
            // InternalGrana.g:4074:2: ( rule__ElkNode__Group_2__0 )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==40) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalGrana.g:4074:3: rule__ElkNode__Group_2__0
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
    // InternalGrana.g:4083:1: rule__ElkNode__Group_2__0 : rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 ;
    public final void rule__ElkNode__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4087:1: ( rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 )
            // InternalGrana.g:4088:2: rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1
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
    // InternalGrana.g:4095:1: rule__ElkNode__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkNode__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4099:1: ( ( '{' ) )
            // InternalGrana.g:4100:1: ( '{' )
            {
            // InternalGrana.g:4100:1: ( '{' )
            // InternalGrana.g:4101:2: '{'
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
    // InternalGrana.g:4110:1: rule__ElkNode__Group_2__1 : rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 ;
    public final void rule__ElkNode__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4114:1: ( rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 )
            // InternalGrana.g:4115:2: rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2
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
    // InternalGrana.g:4122:1: rule__ElkNode__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkNode__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4126:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4127:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4127:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4128:2: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkNodeAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:4129:2: ( ruleShapeLayout )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==46) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalGrana.g:4129:3: ruleShapeLayout
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
    // InternalGrana.g:4137:1: rule__ElkNode__Group_2__2 : rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 ;
    public final void rule__ElkNode__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4141:1: ( rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 )
            // InternalGrana.g:4142:2: rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3
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
    // InternalGrana.g:4149:1: rule__ElkNode__Group_2__2__Impl : ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkNode__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4153:1: ( ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:4154:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:4154:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:4155:2: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:4156:2: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==RULE_ID) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalGrana.g:4156:3: rule__ElkNode__PropertiesAssignment_2_2
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
    // InternalGrana.g:4164:1: rule__ElkNode__Group_2__3 : rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 ;
    public final void rule__ElkNode__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4168:1: ( rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 )
            // InternalGrana.g:4169:2: rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4
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
    // InternalGrana.g:4176:1: rule__ElkNode__Group_2__3__Impl : ( ( rule__ElkNode__Alternatives_2_3 )* ) ;
    public final void rule__ElkNode__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4180:1: ( ( ( rule__ElkNode__Alternatives_2_3 )* ) )
            // InternalGrana.g:4181:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            {
            // InternalGrana.g:4181:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            // InternalGrana.g:4182:2: ( rule__ElkNode__Alternatives_2_3 )*
            {
             before(grammarAccess.getElkNodeAccess().getAlternatives_2_3()); 
            // InternalGrana.g:4183:2: ( rule__ElkNode__Alternatives_2_3 )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=42 && LA48_0<=43)||LA48_0==45||LA48_0==51) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalGrana.g:4183:3: rule__ElkNode__Alternatives_2_3
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
    // InternalGrana.g:4191:1: rule__ElkNode__Group_2__4 : rule__ElkNode__Group_2__4__Impl ;
    public final void rule__ElkNode__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4195:1: ( rule__ElkNode__Group_2__4__Impl )
            // InternalGrana.g:4196:2: rule__ElkNode__Group_2__4__Impl
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
    // InternalGrana.g:4202:1: rule__ElkNode__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkNode__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4206:1: ( ( '}' ) )
            // InternalGrana.g:4207:1: ( '}' )
            {
            // InternalGrana.g:4207:1: ( '}' )
            // InternalGrana.g:4208:2: '}'
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
    // InternalGrana.g:4218:1: rule__ElkLabel__Group__0 : rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 ;
    public final void rule__ElkLabel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4222:1: ( rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 )
            // InternalGrana.g:4223:2: rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1
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
    // InternalGrana.g:4230:1: rule__ElkLabel__Group__0__Impl : ( 'label' ) ;
    public final void rule__ElkLabel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4234:1: ( ( 'label' ) )
            // InternalGrana.g:4235:1: ( 'label' )
            {
            // InternalGrana.g:4235:1: ( 'label' )
            // InternalGrana.g:4236:2: 'label'
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
    // InternalGrana.g:4245:1: rule__ElkLabel__Group__1 : rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 ;
    public final void rule__ElkLabel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4249:1: ( rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 )
            // InternalGrana.g:4250:2: rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2
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
    // InternalGrana.g:4257:1: rule__ElkLabel__Group__1__Impl : ( ( rule__ElkLabel__Group_1__0 )? ) ;
    public final void rule__ElkLabel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4261:1: ( ( ( rule__ElkLabel__Group_1__0 )? ) )
            // InternalGrana.g:4262:1: ( ( rule__ElkLabel__Group_1__0 )? )
            {
            // InternalGrana.g:4262:1: ( ( rule__ElkLabel__Group_1__0 )? )
            // InternalGrana.g:4263:2: ( rule__ElkLabel__Group_1__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_1()); 
            // InternalGrana.g:4264:2: ( rule__ElkLabel__Group_1__0 )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==RULE_ID) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalGrana.g:4264:3: rule__ElkLabel__Group_1__0
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
    // InternalGrana.g:4272:1: rule__ElkLabel__Group__2 : rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 ;
    public final void rule__ElkLabel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4276:1: ( rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 )
            // InternalGrana.g:4277:2: rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3
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
    // InternalGrana.g:4284:1: rule__ElkLabel__Group__2__Impl : ( ( rule__ElkLabel__TextAssignment_2 ) ) ;
    public final void rule__ElkLabel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4288:1: ( ( ( rule__ElkLabel__TextAssignment_2 ) ) )
            // InternalGrana.g:4289:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            {
            // InternalGrana.g:4289:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            // InternalGrana.g:4290:2: ( rule__ElkLabel__TextAssignment_2 )
            {
             before(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 
            // InternalGrana.g:4291:2: ( rule__ElkLabel__TextAssignment_2 )
            // InternalGrana.g:4291:3: rule__ElkLabel__TextAssignment_2
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
    // InternalGrana.g:4299:1: rule__ElkLabel__Group__3 : rule__ElkLabel__Group__3__Impl ;
    public final void rule__ElkLabel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4303:1: ( rule__ElkLabel__Group__3__Impl )
            // InternalGrana.g:4304:2: rule__ElkLabel__Group__3__Impl
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
    // InternalGrana.g:4310:1: rule__ElkLabel__Group__3__Impl : ( ( rule__ElkLabel__Group_3__0 )? ) ;
    public final void rule__ElkLabel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4314:1: ( ( ( rule__ElkLabel__Group_3__0 )? ) )
            // InternalGrana.g:4315:1: ( ( rule__ElkLabel__Group_3__0 )? )
            {
            // InternalGrana.g:4315:1: ( ( rule__ElkLabel__Group_3__0 )? )
            // InternalGrana.g:4316:2: ( rule__ElkLabel__Group_3__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_3()); 
            // InternalGrana.g:4317:2: ( rule__ElkLabel__Group_3__0 )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==40) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalGrana.g:4317:3: rule__ElkLabel__Group_3__0
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
    // InternalGrana.g:4326:1: rule__ElkLabel__Group_1__0 : rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 ;
    public final void rule__ElkLabel__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4330:1: ( rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 )
            // InternalGrana.g:4331:2: rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1
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
    // InternalGrana.g:4338:1: rule__ElkLabel__Group_1__0__Impl : ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkLabel__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4342:1: ( ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:4343:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:4343:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:4344:2: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:4345:2: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            // InternalGrana.g:4345:3: rule__ElkLabel__IdentifierAssignment_1_0
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
    // InternalGrana.g:4353:1: rule__ElkLabel__Group_1__1 : rule__ElkLabel__Group_1__1__Impl ;
    public final void rule__ElkLabel__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4357:1: ( rule__ElkLabel__Group_1__1__Impl )
            // InternalGrana.g:4358:2: rule__ElkLabel__Group_1__1__Impl
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
    // InternalGrana.g:4364:1: rule__ElkLabel__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkLabel__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4368:1: ( ( ':' ) )
            // InternalGrana.g:4369:1: ( ':' )
            {
            // InternalGrana.g:4369:1: ( ':' )
            // InternalGrana.g:4370:2: ':'
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
    // InternalGrana.g:4380:1: rule__ElkLabel__Group_3__0 : rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 ;
    public final void rule__ElkLabel__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4384:1: ( rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 )
            // InternalGrana.g:4385:2: rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1
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
    // InternalGrana.g:4392:1: rule__ElkLabel__Group_3__0__Impl : ( '{' ) ;
    public final void rule__ElkLabel__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4396:1: ( ( '{' ) )
            // InternalGrana.g:4397:1: ( '{' )
            {
            // InternalGrana.g:4397:1: ( '{' )
            // InternalGrana.g:4398:2: '{'
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
    // InternalGrana.g:4407:1: rule__ElkLabel__Group_3__1 : rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 ;
    public final void rule__ElkLabel__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4411:1: ( rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 )
            // InternalGrana.g:4412:2: rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2
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
    // InternalGrana.g:4419:1: rule__ElkLabel__Group_3__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkLabel__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4423:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4424:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4424:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4425:2: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 
            // InternalGrana.g:4426:2: ( ruleShapeLayout )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==46) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalGrana.g:4426:3: ruleShapeLayout
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
    // InternalGrana.g:4434:1: rule__ElkLabel__Group_3__2 : rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 ;
    public final void rule__ElkLabel__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4438:1: ( rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 )
            // InternalGrana.g:4439:2: rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3
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
    // InternalGrana.g:4446:1: rule__ElkLabel__Group_3__2__Impl : ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) ;
    public final void rule__ElkLabel__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4450:1: ( ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) )
            // InternalGrana.g:4451:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            {
            // InternalGrana.g:4451:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            // InternalGrana.g:4452:2: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 
            // InternalGrana.g:4453:2: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==RULE_ID) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // InternalGrana.g:4453:3: rule__ElkLabel__PropertiesAssignment_3_2
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
    // InternalGrana.g:4461:1: rule__ElkLabel__Group_3__3 : rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 ;
    public final void rule__ElkLabel__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4465:1: ( rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 )
            // InternalGrana.g:4466:2: rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4
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
    // InternalGrana.g:4473:1: rule__ElkLabel__Group_3__3__Impl : ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) ;
    public final void rule__ElkLabel__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4477:1: ( ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) )
            // InternalGrana.g:4478:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            {
            // InternalGrana.g:4478:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            // InternalGrana.g:4479:2: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            {
             before(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 
            // InternalGrana.g:4480:2: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==43) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // InternalGrana.g:4480:3: rule__ElkLabel__LabelsAssignment_3_3
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
    // InternalGrana.g:4488:1: rule__ElkLabel__Group_3__4 : rule__ElkLabel__Group_3__4__Impl ;
    public final void rule__ElkLabel__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4492:1: ( rule__ElkLabel__Group_3__4__Impl )
            // InternalGrana.g:4493:2: rule__ElkLabel__Group_3__4__Impl
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
    // InternalGrana.g:4499:1: rule__ElkLabel__Group_3__4__Impl : ( '}' ) ;
    public final void rule__ElkLabel__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4503:1: ( ( '}' ) )
            // InternalGrana.g:4504:1: ( '}' )
            {
            // InternalGrana.g:4504:1: ( '}' )
            // InternalGrana.g:4505:2: '}'
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
    // InternalGrana.g:4515:1: rule__ElkPort__Group__0 : rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 ;
    public final void rule__ElkPort__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4519:1: ( rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 )
            // InternalGrana.g:4520:2: rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1
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
    // InternalGrana.g:4527:1: rule__ElkPort__Group__0__Impl : ( 'port' ) ;
    public final void rule__ElkPort__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4531:1: ( ( 'port' ) )
            // InternalGrana.g:4532:1: ( 'port' )
            {
            // InternalGrana.g:4532:1: ( 'port' )
            // InternalGrana.g:4533:2: 'port'
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
    // InternalGrana.g:4542:1: rule__ElkPort__Group__1 : rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 ;
    public final void rule__ElkPort__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4546:1: ( rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 )
            // InternalGrana.g:4547:2: rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2
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
    // InternalGrana.g:4554:1: rule__ElkPort__Group__1__Impl : ( ( rule__ElkPort__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkPort__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4558:1: ( ( ( rule__ElkPort__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:4559:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:4559:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            // InternalGrana.g:4560:2: ( rule__ElkPort__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkPortAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:4561:2: ( rule__ElkPort__IdentifierAssignment_1 )
            // InternalGrana.g:4561:3: rule__ElkPort__IdentifierAssignment_1
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
    // InternalGrana.g:4569:1: rule__ElkPort__Group__2 : rule__ElkPort__Group__2__Impl ;
    public final void rule__ElkPort__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4573:1: ( rule__ElkPort__Group__2__Impl )
            // InternalGrana.g:4574:2: rule__ElkPort__Group__2__Impl
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
    // InternalGrana.g:4580:1: rule__ElkPort__Group__2__Impl : ( ( rule__ElkPort__Group_2__0 )? ) ;
    public final void rule__ElkPort__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4584:1: ( ( ( rule__ElkPort__Group_2__0 )? ) )
            // InternalGrana.g:4585:1: ( ( rule__ElkPort__Group_2__0 )? )
            {
            // InternalGrana.g:4585:1: ( ( rule__ElkPort__Group_2__0 )? )
            // InternalGrana.g:4586:2: ( rule__ElkPort__Group_2__0 )?
            {
             before(grammarAccess.getElkPortAccess().getGroup_2()); 
            // InternalGrana.g:4587:2: ( rule__ElkPort__Group_2__0 )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==40) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalGrana.g:4587:3: rule__ElkPort__Group_2__0
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
    // InternalGrana.g:4596:1: rule__ElkPort__Group_2__0 : rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 ;
    public final void rule__ElkPort__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4600:1: ( rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 )
            // InternalGrana.g:4601:2: rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1
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
    // InternalGrana.g:4608:1: rule__ElkPort__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkPort__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4612:1: ( ( '{' ) )
            // InternalGrana.g:4613:1: ( '{' )
            {
            // InternalGrana.g:4613:1: ( '{' )
            // InternalGrana.g:4614:2: '{'
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
    // InternalGrana.g:4623:1: rule__ElkPort__Group_2__1 : rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 ;
    public final void rule__ElkPort__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4627:1: ( rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 )
            // InternalGrana.g:4628:2: rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2
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
    // InternalGrana.g:4635:1: rule__ElkPort__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkPort__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4639:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4640:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4640:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4641:2: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkPortAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:4642:2: ( ruleShapeLayout )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==46) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalGrana.g:4642:3: ruleShapeLayout
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
    // InternalGrana.g:4650:1: rule__ElkPort__Group_2__2 : rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 ;
    public final void rule__ElkPort__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4654:1: ( rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 )
            // InternalGrana.g:4655:2: rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3
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
    // InternalGrana.g:4662:1: rule__ElkPort__Group_2__2__Impl : ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkPort__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4666:1: ( ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:4667:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:4667:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:4668:2: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkPortAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:4669:2: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==RULE_ID) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // InternalGrana.g:4669:3: rule__ElkPort__PropertiesAssignment_2_2
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
    // InternalGrana.g:4677:1: rule__ElkPort__Group_2__3 : rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 ;
    public final void rule__ElkPort__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4681:1: ( rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 )
            // InternalGrana.g:4682:2: rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4
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
    // InternalGrana.g:4689:1: rule__ElkPort__Group_2__3__Impl : ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) ;
    public final void rule__ElkPort__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4693:1: ( ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) )
            // InternalGrana.g:4694:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            {
            // InternalGrana.g:4694:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            // InternalGrana.g:4695:2: ( rule__ElkPort__LabelsAssignment_2_3 )*
            {
             before(grammarAccess.getElkPortAccess().getLabelsAssignment_2_3()); 
            // InternalGrana.g:4696:2: ( rule__ElkPort__LabelsAssignment_2_3 )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==43) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalGrana.g:4696:3: rule__ElkPort__LabelsAssignment_2_3
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
    // InternalGrana.g:4704:1: rule__ElkPort__Group_2__4 : rule__ElkPort__Group_2__4__Impl ;
    public final void rule__ElkPort__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4708:1: ( rule__ElkPort__Group_2__4__Impl )
            // InternalGrana.g:4709:2: rule__ElkPort__Group_2__4__Impl
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
    // InternalGrana.g:4715:1: rule__ElkPort__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkPort__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4719:1: ( ( '}' ) )
            // InternalGrana.g:4720:1: ( '}' )
            {
            // InternalGrana.g:4720:1: ( '}' )
            // InternalGrana.g:4721:2: '}'
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
    // InternalGrana.g:4731:1: rule__ShapeLayout__Group__0 : rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 ;
    public final void rule__ShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4735:1: ( rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 )
            // InternalGrana.g:4736:2: rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1
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
    // InternalGrana.g:4743:1: rule__ShapeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__ShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4747:1: ( ( 'layout' ) )
            // InternalGrana.g:4748:1: ( 'layout' )
            {
            // InternalGrana.g:4748:1: ( 'layout' )
            // InternalGrana.g:4749:2: 'layout'
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
    // InternalGrana.g:4758:1: rule__ShapeLayout__Group__1 : rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 ;
    public final void rule__ShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4762:1: ( rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 )
            // InternalGrana.g:4763:2: rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2
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
    // InternalGrana.g:4770:1: rule__ShapeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__ShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4774:1: ( ( '[' ) )
            // InternalGrana.g:4775:1: ( '[' )
            {
            // InternalGrana.g:4775:1: ( '[' )
            // InternalGrana.g:4776:2: '['
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
    // InternalGrana.g:4785:1: rule__ShapeLayout__Group__2 : rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 ;
    public final void rule__ShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4789:1: ( rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 )
            // InternalGrana.g:4790:2: rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3
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
    // InternalGrana.g:4797:1: rule__ShapeLayout__Group__2__Impl : ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) ;
    public final void rule__ShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4801:1: ( ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) )
            // InternalGrana.g:4802:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            {
            // InternalGrana.g:4802:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            // InternalGrana.g:4803:2: ( rule__ShapeLayout__UnorderedGroup_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 
            // InternalGrana.g:4804:2: ( rule__ShapeLayout__UnorderedGroup_2 )
            // InternalGrana.g:4804:3: rule__ShapeLayout__UnorderedGroup_2
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
    // InternalGrana.g:4812:1: rule__ShapeLayout__Group__3 : rule__ShapeLayout__Group__3__Impl ;
    public final void rule__ShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4816:1: ( rule__ShapeLayout__Group__3__Impl )
            // InternalGrana.g:4817:2: rule__ShapeLayout__Group__3__Impl
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
    // InternalGrana.g:4823:1: rule__ShapeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__ShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4827:1: ( ( ']' ) )
            // InternalGrana.g:4828:1: ( ']' )
            {
            // InternalGrana.g:4828:1: ( ']' )
            // InternalGrana.g:4829:2: ']'
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
    // InternalGrana.g:4839:1: rule__ShapeLayout__Group_2_0__0 : rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 ;
    public final void rule__ShapeLayout__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4843:1: ( rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 )
            // InternalGrana.g:4844:2: rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1
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
    // InternalGrana.g:4851:1: rule__ShapeLayout__Group_2_0__0__Impl : ( 'position' ) ;
    public final void rule__ShapeLayout__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4855:1: ( ( 'position' ) )
            // InternalGrana.g:4856:1: ( 'position' )
            {
            // InternalGrana.g:4856:1: ( 'position' )
            // InternalGrana.g:4857:2: 'position'
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
    // InternalGrana.g:4866:1: rule__ShapeLayout__Group_2_0__1 : rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 ;
    public final void rule__ShapeLayout__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4870:1: ( rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 )
            // InternalGrana.g:4871:2: rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2
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
    // InternalGrana.g:4878:1: rule__ShapeLayout__Group_2_0__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4882:1: ( ( ':' ) )
            // InternalGrana.g:4883:1: ( ':' )
            {
            // InternalGrana.g:4883:1: ( ':' )
            // InternalGrana.g:4884:2: ':'
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
    // InternalGrana.g:4893:1: rule__ShapeLayout__Group_2_0__2 : rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 ;
    public final void rule__ShapeLayout__Group_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4897:1: ( rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 )
            // InternalGrana.g:4898:2: rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3
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
    // InternalGrana.g:4905:1: rule__ShapeLayout__Group_2_0__2__Impl : ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4909:1: ( ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) )
            // InternalGrana.g:4910:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            {
            // InternalGrana.g:4910:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            // InternalGrana.g:4911:2: ( rule__ShapeLayout__XAssignment_2_0_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 
            // InternalGrana.g:4912:2: ( rule__ShapeLayout__XAssignment_2_0_2 )
            // InternalGrana.g:4912:3: rule__ShapeLayout__XAssignment_2_0_2
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
    // InternalGrana.g:4920:1: rule__ShapeLayout__Group_2_0__3 : rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 ;
    public final void rule__ShapeLayout__Group_2_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4924:1: ( rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 )
            // InternalGrana.g:4925:2: rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4
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
    // InternalGrana.g:4932:1: rule__ShapeLayout__Group_2_0__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4936:1: ( ( ',' ) )
            // InternalGrana.g:4937:1: ( ',' )
            {
            // InternalGrana.g:4937:1: ( ',' )
            // InternalGrana.g:4938:2: ','
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
    // InternalGrana.g:4947:1: rule__ShapeLayout__Group_2_0__4 : rule__ShapeLayout__Group_2_0__4__Impl ;
    public final void rule__ShapeLayout__Group_2_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4951:1: ( rule__ShapeLayout__Group_2_0__4__Impl )
            // InternalGrana.g:4952:2: rule__ShapeLayout__Group_2_0__4__Impl
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
    // InternalGrana.g:4958:1: rule__ShapeLayout__Group_2_0__4__Impl : ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4962:1: ( ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) )
            // InternalGrana.g:4963:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            {
            // InternalGrana.g:4963:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            // InternalGrana.g:4964:2: ( rule__ShapeLayout__YAssignment_2_0_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 
            // InternalGrana.g:4965:2: ( rule__ShapeLayout__YAssignment_2_0_4 )
            // InternalGrana.g:4965:3: rule__ShapeLayout__YAssignment_2_0_4
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
    // InternalGrana.g:4974:1: rule__ShapeLayout__Group_2_1__0 : rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 ;
    public final void rule__ShapeLayout__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4978:1: ( rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 )
            // InternalGrana.g:4979:2: rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1
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
    // InternalGrana.g:4986:1: rule__ShapeLayout__Group_2_1__0__Impl : ( 'size' ) ;
    public final void rule__ShapeLayout__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:4990:1: ( ( 'size' ) )
            // InternalGrana.g:4991:1: ( 'size' )
            {
            // InternalGrana.g:4991:1: ( 'size' )
            // InternalGrana.g:4992:2: 'size'
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
    // InternalGrana.g:5001:1: rule__ShapeLayout__Group_2_1__1 : rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 ;
    public final void rule__ShapeLayout__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5005:1: ( rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 )
            // InternalGrana.g:5006:2: rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2
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
    // InternalGrana.g:5013:1: rule__ShapeLayout__Group_2_1__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5017:1: ( ( ':' ) )
            // InternalGrana.g:5018:1: ( ':' )
            {
            // InternalGrana.g:5018:1: ( ':' )
            // InternalGrana.g:5019:2: ':'
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
    // InternalGrana.g:5028:1: rule__ShapeLayout__Group_2_1__2 : rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 ;
    public final void rule__ShapeLayout__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5032:1: ( rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 )
            // InternalGrana.g:5033:2: rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3
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
    // InternalGrana.g:5040:1: rule__ShapeLayout__Group_2_1__2__Impl : ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5044:1: ( ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) )
            // InternalGrana.g:5045:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            {
            // InternalGrana.g:5045:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            // InternalGrana.g:5046:2: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 
            // InternalGrana.g:5047:2: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            // InternalGrana.g:5047:3: rule__ShapeLayout__WidthAssignment_2_1_2
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
    // InternalGrana.g:5055:1: rule__ShapeLayout__Group_2_1__3 : rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 ;
    public final void rule__ShapeLayout__Group_2_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5059:1: ( rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 )
            // InternalGrana.g:5060:2: rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4
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
    // InternalGrana.g:5067:1: rule__ShapeLayout__Group_2_1__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5071:1: ( ( ',' ) )
            // InternalGrana.g:5072:1: ( ',' )
            {
            // InternalGrana.g:5072:1: ( ',' )
            // InternalGrana.g:5073:2: ','
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
    // InternalGrana.g:5082:1: rule__ShapeLayout__Group_2_1__4 : rule__ShapeLayout__Group_2_1__4__Impl ;
    public final void rule__ShapeLayout__Group_2_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5086:1: ( rule__ShapeLayout__Group_2_1__4__Impl )
            // InternalGrana.g:5087:2: rule__ShapeLayout__Group_2_1__4__Impl
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
    // InternalGrana.g:5093:1: rule__ShapeLayout__Group_2_1__4__Impl : ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5097:1: ( ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) )
            // InternalGrana.g:5098:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            {
            // InternalGrana.g:5098:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            // InternalGrana.g:5099:2: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 
            // InternalGrana.g:5100:2: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            // InternalGrana.g:5100:3: rule__ShapeLayout__HeightAssignment_2_1_4
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
    // InternalGrana.g:5109:1: rule__ElkEdge__Group__0 : rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 ;
    public final void rule__ElkEdge__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5113:1: ( rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 )
            // InternalGrana.g:5114:2: rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1
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
    // InternalGrana.g:5121:1: rule__ElkEdge__Group__0__Impl : ( 'edge' ) ;
    public final void rule__ElkEdge__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5125:1: ( ( 'edge' ) )
            // InternalGrana.g:5126:1: ( 'edge' )
            {
            // InternalGrana.g:5126:1: ( 'edge' )
            // InternalGrana.g:5127:2: 'edge'
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
    // InternalGrana.g:5136:1: rule__ElkEdge__Group__1 : rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 ;
    public final void rule__ElkEdge__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5140:1: ( rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 )
            // InternalGrana.g:5141:2: rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2
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
    // InternalGrana.g:5148:1: rule__ElkEdge__Group__1__Impl : ( ( rule__ElkEdge__Group_1__0 )? ) ;
    public final void rule__ElkEdge__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5152:1: ( ( ( rule__ElkEdge__Group_1__0 )? ) )
            // InternalGrana.g:5153:1: ( ( rule__ElkEdge__Group_1__0 )? )
            {
            // InternalGrana.g:5153:1: ( ( rule__ElkEdge__Group_1__0 )? )
            // InternalGrana.g:5154:2: ( rule__ElkEdge__Group_1__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_1()); 
            // InternalGrana.g:5155:2: ( rule__ElkEdge__Group_1__0 )?
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
                    // InternalGrana.g:5155:3: rule__ElkEdge__Group_1__0
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
    // InternalGrana.g:5163:1: rule__ElkEdge__Group__2 : rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 ;
    public final void rule__ElkEdge__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5167:1: ( rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 )
            // InternalGrana.g:5168:2: rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3
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
    // InternalGrana.g:5175:1: rule__ElkEdge__Group__2__Impl : ( ( rule__ElkEdge__SourcesAssignment_2 ) ) ;
    public final void rule__ElkEdge__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5179:1: ( ( ( rule__ElkEdge__SourcesAssignment_2 ) ) )
            // InternalGrana.g:5180:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            {
            // InternalGrana.g:5180:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            // InternalGrana.g:5181:2: ( rule__ElkEdge__SourcesAssignment_2 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_2()); 
            // InternalGrana.g:5182:2: ( rule__ElkEdge__SourcesAssignment_2 )
            // InternalGrana.g:5182:3: rule__ElkEdge__SourcesAssignment_2
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
    // InternalGrana.g:5190:1: rule__ElkEdge__Group__3 : rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 ;
    public final void rule__ElkEdge__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5194:1: ( rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 )
            // InternalGrana.g:5195:2: rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4
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
    // InternalGrana.g:5202:1: rule__ElkEdge__Group__3__Impl : ( ( rule__ElkEdge__Group_3__0 )* ) ;
    public final void rule__ElkEdge__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5206:1: ( ( ( rule__ElkEdge__Group_3__0 )* ) )
            // InternalGrana.g:5207:1: ( ( rule__ElkEdge__Group_3__0 )* )
            {
            // InternalGrana.g:5207:1: ( ( rule__ElkEdge__Group_3__0 )* )
            // InternalGrana.g:5208:2: ( rule__ElkEdge__Group_3__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_3()); 
            // InternalGrana.g:5209:2: ( rule__ElkEdge__Group_3__0 )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==33) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // InternalGrana.g:5209:3: rule__ElkEdge__Group_3__0
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
    // InternalGrana.g:5217:1: rule__ElkEdge__Group__4 : rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 ;
    public final void rule__ElkEdge__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5221:1: ( rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 )
            // InternalGrana.g:5222:2: rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5
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
    // InternalGrana.g:5229:1: rule__ElkEdge__Group__4__Impl : ( '->' ) ;
    public final void rule__ElkEdge__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5233:1: ( ( '->' ) )
            // InternalGrana.g:5234:1: ( '->' )
            {
            // InternalGrana.g:5234:1: ( '->' )
            // InternalGrana.g:5235:2: '->'
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
    // InternalGrana.g:5244:1: rule__ElkEdge__Group__5 : rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 ;
    public final void rule__ElkEdge__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5248:1: ( rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 )
            // InternalGrana.g:5249:2: rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6
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
    // InternalGrana.g:5256:1: rule__ElkEdge__Group__5__Impl : ( ( rule__ElkEdge__TargetsAssignment_5 ) ) ;
    public final void rule__ElkEdge__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5260:1: ( ( ( rule__ElkEdge__TargetsAssignment_5 ) ) )
            // InternalGrana.g:5261:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            {
            // InternalGrana.g:5261:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            // InternalGrana.g:5262:2: ( rule__ElkEdge__TargetsAssignment_5 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_5()); 
            // InternalGrana.g:5263:2: ( rule__ElkEdge__TargetsAssignment_5 )
            // InternalGrana.g:5263:3: rule__ElkEdge__TargetsAssignment_5
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
    // InternalGrana.g:5271:1: rule__ElkEdge__Group__6 : rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 ;
    public final void rule__ElkEdge__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5275:1: ( rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 )
            // InternalGrana.g:5276:2: rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7
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
    // InternalGrana.g:5283:1: rule__ElkEdge__Group__6__Impl : ( ( rule__ElkEdge__Group_6__0 )* ) ;
    public final void rule__ElkEdge__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5287:1: ( ( ( rule__ElkEdge__Group_6__0 )* ) )
            // InternalGrana.g:5288:1: ( ( rule__ElkEdge__Group_6__0 )* )
            {
            // InternalGrana.g:5288:1: ( ( rule__ElkEdge__Group_6__0 )* )
            // InternalGrana.g:5289:2: ( rule__ElkEdge__Group_6__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_6()); 
            // InternalGrana.g:5290:2: ( rule__ElkEdge__Group_6__0 )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==33) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // InternalGrana.g:5290:3: rule__ElkEdge__Group_6__0
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
    // InternalGrana.g:5298:1: rule__ElkEdge__Group__7 : rule__ElkEdge__Group__7__Impl ;
    public final void rule__ElkEdge__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5302:1: ( rule__ElkEdge__Group__7__Impl )
            // InternalGrana.g:5303:2: rule__ElkEdge__Group__7__Impl
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
    // InternalGrana.g:5309:1: rule__ElkEdge__Group__7__Impl : ( ( rule__ElkEdge__Group_7__0 )? ) ;
    public final void rule__ElkEdge__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5313:1: ( ( ( rule__ElkEdge__Group_7__0 )? ) )
            // InternalGrana.g:5314:1: ( ( rule__ElkEdge__Group_7__0 )? )
            {
            // InternalGrana.g:5314:1: ( ( rule__ElkEdge__Group_7__0 )? )
            // InternalGrana.g:5315:2: ( rule__ElkEdge__Group_7__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_7()); 
            // InternalGrana.g:5316:2: ( rule__ElkEdge__Group_7__0 )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==40) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalGrana.g:5316:3: rule__ElkEdge__Group_7__0
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
    // InternalGrana.g:5325:1: rule__ElkEdge__Group_1__0 : rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 ;
    public final void rule__ElkEdge__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5329:1: ( rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 )
            // InternalGrana.g:5330:2: rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1
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
    // InternalGrana.g:5337:1: rule__ElkEdge__Group_1__0__Impl : ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkEdge__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5341:1: ( ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:5342:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:5342:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:5343:2: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkEdgeAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:5344:2: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            // InternalGrana.g:5344:3: rule__ElkEdge__IdentifierAssignment_1_0
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
    // InternalGrana.g:5352:1: rule__ElkEdge__Group_1__1 : rule__ElkEdge__Group_1__1__Impl ;
    public final void rule__ElkEdge__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5356:1: ( rule__ElkEdge__Group_1__1__Impl )
            // InternalGrana.g:5357:2: rule__ElkEdge__Group_1__1__Impl
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
    // InternalGrana.g:5363:1: rule__ElkEdge__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdge__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5367:1: ( ( ':' ) )
            // InternalGrana.g:5368:1: ( ':' )
            {
            // InternalGrana.g:5368:1: ( ':' )
            // InternalGrana.g:5369:2: ':'
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
    // InternalGrana.g:5379:1: rule__ElkEdge__Group_3__0 : rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 ;
    public final void rule__ElkEdge__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5383:1: ( rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 )
            // InternalGrana.g:5384:2: rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1
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
    // InternalGrana.g:5391:1: rule__ElkEdge__Group_3__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5395:1: ( ( ',' ) )
            // InternalGrana.g:5396:1: ( ',' )
            {
            // InternalGrana.g:5396:1: ( ',' )
            // InternalGrana.g:5397:2: ','
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
    // InternalGrana.g:5406:1: rule__ElkEdge__Group_3__1 : rule__ElkEdge__Group_3__1__Impl ;
    public final void rule__ElkEdge__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5410:1: ( rule__ElkEdge__Group_3__1__Impl )
            // InternalGrana.g:5411:2: rule__ElkEdge__Group_3__1__Impl
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
    // InternalGrana.g:5417:1: rule__ElkEdge__Group_3__1__Impl : ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) ;
    public final void rule__ElkEdge__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5421:1: ( ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) )
            // InternalGrana.g:5422:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            {
            // InternalGrana.g:5422:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            // InternalGrana.g:5423:2: ( rule__ElkEdge__SourcesAssignment_3_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_3_1()); 
            // InternalGrana.g:5424:2: ( rule__ElkEdge__SourcesAssignment_3_1 )
            // InternalGrana.g:5424:3: rule__ElkEdge__SourcesAssignment_3_1
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
    // InternalGrana.g:5433:1: rule__ElkEdge__Group_6__0 : rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 ;
    public final void rule__ElkEdge__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5437:1: ( rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 )
            // InternalGrana.g:5438:2: rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1
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
    // InternalGrana.g:5445:1: rule__ElkEdge__Group_6__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5449:1: ( ( ',' ) )
            // InternalGrana.g:5450:1: ( ',' )
            {
            // InternalGrana.g:5450:1: ( ',' )
            // InternalGrana.g:5451:2: ','
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
    // InternalGrana.g:5460:1: rule__ElkEdge__Group_6__1 : rule__ElkEdge__Group_6__1__Impl ;
    public final void rule__ElkEdge__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5464:1: ( rule__ElkEdge__Group_6__1__Impl )
            // InternalGrana.g:5465:2: rule__ElkEdge__Group_6__1__Impl
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
    // InternalGrana.g:5471:1: rule__ElkEdge__Group_6__1__Impl : ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) ;
    public final void rule__ElkEdge__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5475:1: ( ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) )
            // InternalGrana.g:5476:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            {
            // InternalGrana.g:5476:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            // InternalGrana.g:5477:2: ( rule__ElkEdge__TargetsAssignment_6_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_6_1()); 
            // InternalGrana.g:5478:2: ( rule__ElkEdge__TargetsAssignment_6_1 )
            // InternalGrana.g:5478:3: rule__ElkEdge__TargetsAssignment_6_1
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
    // InternalGrana.g:5487:1: rule__ElkEdge__Group_7__0 : rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 ;
    public final void rule__ElkEdge__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5491:1: ( rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 )
            // InternalGrana.g:5492:2: rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1
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
    // InternalGrana.g:5499:1: rule__ElkEdge__Group_7__0__Impl : ( '{' ) ;
    public final void rule__ElkEdge__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5503:1: ( ( '{' ) )
            // InternalGrana.g:5504:1: ( '{' )
            {
            // InternalGrana.g:5504:1: ( '{' )
            // InternalGrana.g:5505:2: '{'
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
    // InternalGrana.g:5514:1: rule__ElkEdge__Group_7__1 : rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 ;
    public final void rule__ElkEdge__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5518:1: ( rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 )
            // InternalGrana.g:5519:2: rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2
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
    // InternalGrana.g:5526:1: rule__ElkEdge__Group_7__1__Impl : ( ( ruleEdgeLayout )? ) ;
    public final void rule__ElkEdge__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5530:1: ( ( ( ruleEdgeLayout )? ) )
            // InternalGrana.g:5531:1: ( ( ruleEdgeLayout )? )
            {
            // InternalGrana.g:5531:1: ( ( ruleEdgeLayout )? )
            // InternalGrana.g:5532:2: ( ruleEdgeLayout )?
            {
             before(grammarAccess.getElkEdgeAccess().getEdgeLayoutParserRuleCall_7_1()); 
            // InternalGrana.g:5533:2: ( ruleEdgeLayout )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==46) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalGrana.g:5533:3: ruleEdgeLayout
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
    // InternalGrana.g:5541:1: rule__ElkEdge__Group_7__2 : rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 ;
    public final void rule__ElkEdge__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5545:1: ( rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 )
            // InternalGrana.g:5546:2: rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3
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
    // InternalGrana.g:5553:1: rule__ElkEdge__Group_7__2__Impl : ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) ;
    public final void rule__ElkEdge__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5557:1: ( ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) )
            // InternalGrana.g:5558:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            {
            // InternalGrana.g:5558:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            // InternalGrana.g:5559:2: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            {
             before(grammarAccess.getElkEdgeAccess().getPropertiesAssignment_7_2()); 
            // InternalGrana.g:5560:2: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==RULE_ID) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // InternalGrana.g:5560:3: rule__ElkEdge__PropertiesAssignment_7_2
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
    // InternalGrana.g:5568:1: rule__ElkEdge__Group_7__3 : rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 ;
    public final void rule__ElkEdge__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5572:1: ( rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 )
            // InternalGrana.g:5573:2: rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4
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
    // InternalGrana.g:5580:1: rule__ElkEdge__Group_7__3__Impl : ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) ;
    public final void rule__ElkEdge__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5584:1: ( ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) )
            // InternalGrana.g:5585:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            {
            // InternalGrana.g:5585:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            // InternalGrana.g:5586:2: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            {
             before(grammarAccess.getElkEdgeAccess().getLabelsAssignment_7_3()); 
            // InternalGrana.g:5587:2: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==43) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalGrana.g:5587:3: rule__ElkEdge__LabelsAssignment_7_3
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
    // InternalGrana.g:5595:1: rule__ElkEdge__Group_7__4 : rule__ElkEdge__Group_7__4__Impl ;
    public final void rule__ElkEdge__Group_7__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5599:1: ( rule__ElkEdge__Group_7__4__Impl )
            // InternalGrana.g:5600:2: rule__ElkEdge__Group_7__4__Impl
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
    // InternalGrana.g:5606:1: rule__ElkEdge__Group_7__4__Impl : ( '}' ) ;
    public final void rule__ElkEdge__Group_7__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5610:1: ( ( '}' ) )
            // InternalGrana.g:5611:1: ( '}' )
            {
            // InternalGrana.g:5611:1: ( '}' )
            // InternalGrana.g:5612:2: '}'
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
    // InternalGrana.g:5622:1: rule__EdgeLayout__Group__0 : rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 ;
    public final void rule__EdgeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5626:1: ( rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 )
            // InternalGrana.g:5627:2: rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1
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
    // InternalGrana.g:5634:1: rule__EdgeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__EdgeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5638:1: ( ( 'layout' ) )
            // InternalGrana.g:5639:1: ( 'layout' )
            {
            // InternalGrana.g:5639:1: ( 'layout' )
            // InternalGrana.g:5640:2: 'layout'
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
    // InternalGrana.g:5649:1: rule__EdgeLayout__Group__1 : rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 ;
    public final void rule__EdgeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5653:1: ( rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 )
            // InternalGrana.g:5654:2: rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2
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
    // InternalGrana.g:5661:1: rule__EdgeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__EdgeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5665:1: ( ( '[' ) )
            // InternalGrana.g:5666:1: ( '[' )
            {
            // InternalGrana.g:5666:1: ( '[' )
            // InternalGrana.g:5667:2: '['
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
    // InternalGrana.g:5676:1: rule__EdgeLayout__Group__2 : rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 ;
    public final void rule__EdgeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5680:1: ( rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 )
            // InternalGrana.g:5681:2: rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3
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
    // InternalGrana.g:5688:1: rule__EdgeLayout__Group__2__Impl : ( ( rule__EdgeLayout__Alternatives_2 ) ) ;
    public final void rule__EdgeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5692:1: ( ( ( rule__EdgeLayout__Alternatives_2 ) ) )
            // InternalGrana.g:5693:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            {
            // InternalGrana.g:5693:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            // InternalGrana.g:5694:2: ( rule__EdgeLayout__Alternatives_2 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 
            // InternalGrana.g:5695:2: ( rule__EdgeLayout__Alternatives_2 )
            // InternalGrana.g:5695:3: rule__EdgeLayout__Alternatives_2
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
    // InternalGrana.g:5703:1: rule__EdgeLayout__Group__3 : rule__EdgeLayout__Group__3__Impl ;
    public final void rule__EdgeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5707:1: ( rule__EdgeLayout__Group__3__Impl )
            // InternalGrana.g:5708:2: rule__EdgeLayout__Group__3__Impl
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
    // InternalGrana.g:5714:1: rule__EdgeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__EdgeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5718:1: ( ( ']' ) )
            // InternalGrana.g:5719:1: ( ']' )
            {
            // InternalGrana.g:5719:1: ( ']' )
            // InternalGrana.g:5720:2: ']'
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
    // InternalGrana.g:5730:1: rule__ElkSingleEdgeSection__Group__0 : rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 ;
    public final void rule__ElkSingleEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5734:1: ( rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 )
            // InternalGrana.g:5735:2: rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1
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
    // InternalGrana.g:5742:1: rule__ElkSingleEdgeSection__Group__0__Impl : ( () ) ;
    public final void rule__ElkSingleEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5746:1: ( ( () ) )
            // InternalGrana.g:5747:1: ( () )
            {
            // InternalGrana.g:5747:1: ( () )
            // InternalGrana.g:5748:2: ()
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 
            // InternalGrana.g:5749:2: ()
            // InternalGrana.g:5749:3: 
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
    // InternalGrana.g:5757:1: rule__ElkSingleEdgeSection__Group__1 : rule__ElkSingleEdgeSection__Group__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5761:1: ( rule__ElkSingleEdgeSection__Group__1__Impl )
            // InternalGrana.g:5762:2: rule__ElkSingleEdgeSection__Group__1__Impl
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
    // InternalGrana.g:5768:1: rule__ElkSingleEdgeSection__Group__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5772:1: ( ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) )
            // InternalGrana.g:5773:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            {
            // InternalGrana.g:5773:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            // InternalGrana.g:5774:2: ( rule__ElkSingleEdgeSection__Group_1__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1()); 
            // InternalGrana.g:5775:2: ( rule__ElkSingleEdgeSection__Group_1__0 )
            // InternalGrana.g:5775:3: rule__ElkSingleEdgeSection__Group_1__0
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
    // InternalGrana.g:5784:1: rule__ElkSingleEdgeSection__Group_1__0 : rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5788:1: ( rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 )
            // InternalGrana.g:5789:2: rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1
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
    // InternalGrana.g:5796:1: rule__ElkSingleEdgeSection__Group_1__0__Impl : ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5800:1: ( ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) )
            // InternalGrana.g:5801:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            {
            // InternalGrana.g:5801:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            // InternalGrana.g:5802:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0()); 
            // InternalGrana.g:5803:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            // InternalGrana.g:5803:3: rule__ElkSingleEdgeSection__UnorderedGroup_1_0
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
    // InternalGrana.g:5811:1: rule__ElkSingleEdgeSection__Group_1__1 : rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5815:1: ( rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 )
            // InternalGrana.g:5816:2: rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2
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
    // InternalGrana.g:5823:1: rule__ElkSingleEdgeSection__Group_1__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5827:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) )
            // InternalGrana.g:5828:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            {
            // InternalGrana.g:5828:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            // InternalGrana.g:5829:2: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 
            // InternalGrana.g:5830:2: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==57) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalGrana.g:5830:3: rule__ElkSingleEdgeSection__Group_1_1__0
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
    // InternalGrana.g:5838:1: rule__ElkSingleEdgeSection__Group_1__2 : rule__ElkSingleEdgeSection__Group_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5842:1: ( rule__ElkSingleEdgeSection__Group_1__2__Impl )
            // InternalGrana.g:5843:2: rule__ElkSingleEdgeSection__Group_1__2__Impl
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
    // InternalGrana.g:5849:1: rule__ElkSingleEdgeSection__Group_1__2__Impl : ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5853:1: ( ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) )
            // InternalGrana.g:5854:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            {
            // InternalGrana.g:5854:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            // InternalGrana.g:5855:2: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesAssignment_1_2()); 
            // InternalGrana.g:5856:2: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==RULE_ID) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalGrana.g:5856:3: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2
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
    // InternalGrana.g:5865:1: rule__ElkSingleEdgeSection__Group_1_0_0__0 : rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5869:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 )
            // InternalGrana.g:5870:2: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1
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
    // InternalGrana.g:5877:1: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5881:1: ( ( 'incoming' ) )
            // InternalGrana.g:5882:1: ( 'incoming' )
            {
            // InternalGrana.g:5882:1: ( 'incoming' )
            // InternalGrana.g:5883:2: 'incoming'
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
    // InternalGrana.g:5892:1: rule__ElkSingleEdgeSection__Group_1_0_0__1 : rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5896:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 )
            // InternalGrana.g:5897:2: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2
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
    // InternalGrana.g:5904:1: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5908:1: ( ( ':' ) )
            // InternalGrana.g:5909:1: ( ':' )
            {
            // InternalGrana.g:5909:1: ( ':' )
            // InternalGrana.g:5910:2: ':'
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
    // InternalGrana.g:5919:1: rule__ElkSingleEdgeSection__Group_1_0_0__2 : rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5923:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl )
            // InternalGrana.g:5924:2: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl
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
    // InternalGrana.g:5930:1: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl : ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5934:1: ( ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) )
            // InternalGrana.g:5935:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            {
            // InternalGrana.g:5935:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            // InternalGrana.g:5936:2: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_0_2()); 
            // InternalGrana.g:5937:2: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            // InternalGrana.g:5937:3: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2
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
    // InternalGrana.g:5946:1: rule__ElkSingleEdgeSection__Group_1_0_1__0 : rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5950:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 )
            // InternalGrana.g:5951:2: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1
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
    // InternalGrana.g:5958:1: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5962:1: ( ( 'outgoing' ) )
            // InternalGrana.g:5963:1: ( 'outgoing' )
            {
            // InternalGrana.g:5963:1: ( 'outgoing' )
            // InternalGrana.g:5964:2: 'outgoing'
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
    // InternalGrana.g:5973:1: rule__ElkSingleEdgeSection__Group_1_0_1__1 : rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5977:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 )
            // InternalGrana.g:5978:2: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2
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
    // InternalGrana.g:5985:1: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:5989:1: ( ( ':' ) )
            // InternalGrana.g:5990:1: ( ':' )
            {
            // InternalGrana.g:5990:1: ( ':' )
            // InternalGrana.g:5991:2: ':'
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
    // InternalGrana.g:6000:1: rule__ElkSingleEdgeSection__Group_1_0_1__2 : rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6004:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl )
            // InternalGrana.g:6005:2: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl
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
    // InternalGrana.g:6011:1: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl : ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6015:1: ( ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) )
            // InternalGrana.g:6016:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            {
            // InternalGrana.g:6016:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            // InternalGrana.g:6017:2: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_0_1_2()); 
            // InternalGrana.g:6018:2: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            // InternalGrana.g:6018:3: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2
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
    // InternalGrana.g:6027:1: rule__ElkSingleEdgeSection__Group_1_0_2__0 : rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6031:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 )
            // InternalGrana.g:6032:2: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1
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
    // InternalGrana.g:6039:1: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6043:1: ( ( 'start' ) )
            // InternalGrana.g:6044:1: ( 'start' )
            {
            // InternalGrana.g:6044:1: ( 'start' )
            // InternalGrana.g:6045:2: 'start'
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
    // InternalGrana.g:6054:1: rule__ElkSingleEdgeSection__Group_1_0_2__1 : rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6058:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 )
            // InternalGrana.g:6059:2: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2
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
    // InternalGrana.g:6066:1: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6070:1: ( ( ':' ) )
            // InternalGrana.g:6071:1: ( ':' )
            {
            // InternalGrana.g:6071:1: ( ':' )
            // InternalGrana.g:6072:2: ':'
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
    // InternalGrana.g:6081:1: rule__ElkSingleEdgeSection__Group_1_0_2__2 : rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6085:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 )
            // InternalGrana.g:6086:2: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3
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
    // InternalGrana.g:6093:1: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl : ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6097:1: ( ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) )
            // InternalGrana.g:6098:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            {
            // InternalGrana.g:6098:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            // InternalGrana.g:6099:2: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_0_2_2()); 
            // InternalGrana.g:6100:2: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            // InternalGrana.g:6100:3: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2
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
    // InternalGrana.g:6108:1: rule__ElkSingleEdgeSection__Group_1_0_2__3 : rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6112:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 )
            // InternalGrana.g:6113:2: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4
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
    // InternalGrana.g:6120:1: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6124:1: ( ( ',' ) )
            // InternalGrana.g:6125:1: ( ',' )
            {
            // InternalGrana.g:6125:1: ( ',' )
            // InternalGrana.g:6126:2: ','
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
    // InternalGrana.g:6135:1: rule__ElkSingleEdgeSection__Group_1_0_2__4 : rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6139:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl )
            // InternalGrana.g:6140:2: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl
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
    // InternalGrana.g:6146:1: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl : ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6150:1: ( ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) )
            // InternalGrana.g:6151:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            {
            // InternalGrana.g:6151:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            // InternalGrana.g:6152:2: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_0_2_4()); 
            // InternalGrana.g:6153:2: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            // InternalGrana.g:6153:3: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4
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
    // InternalGrana.g:6162:1: rule__ElkSingleEdgeSection__Group_1_0_3__0 : rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6166:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 )
            // InternalGrana.g:6167:2: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1
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
    // InternalGrana.g:6174:1: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6178:1: ( ( 'end' ) )
            // InternalGrana.g:6179:1: ( 'end' )
            {
            // InternalGrana.g:6179:1: ( 'end' )
            // InternalGrana.g:6180:2: 'end'
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
    // InternalGrana.g:6189:1: rule__ElkSingleEdgeSection__Group_1_0_3__1 : rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6193:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 )
            // InternalGrana.g:6194:2: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2
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
    // InternalGrana.g:6201:1: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6205:1: ( ( ':' ) )
            // InternalGrana.g:6206:1: ( ':' )
            {
            // InternalGrana.g:6206:1: ( ':' )
            // InternalGrana.g:6207:2: ':'
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
    // InternalGrana.g:6216:1: rule__ElkSingleEdgeSection__Group_1_0_3__2 : rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6220:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 )
            // InternalGrana.g:6221:2: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3
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
    // InternalGrana.g:6228:1: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl : ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6232:1: ( ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) )
            // InternalGrana.g:6233:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            {
            // InternalGrana.g:6233:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            // InternalGrana.g:6234:2: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_0_3_2()); 
            // InternalGrana.g:6235:2: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            // InternalGrana.g:6235:3: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2
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
    // InternalGrana.g:6243:1: rule__ElkSingleEdgeSection__Group_1_0_3__3 : rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6247:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 )
            // InternalGrana.g:6248:2: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4
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
    // InternalGrana.g:6255:1: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6259:1: ( ( ',' ) )
            // InternalGrana.g:6260:1: ( ',' )
            {
            // InternalGrana.g:6260:1: ( ',' )
            // InternalGrana.g:6261:2: ','
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
    // InternalGrana.g:6270:1: rule__ElkSingleEdgeSection__Group_1_0_3__4 : rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6274:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl )
            // InternalGrana.g:6275:2: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl
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
    // InternalGrana.g:6281:1: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl : ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6285:1: ( ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) )
            // InternalGrana.g:6286:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            {
            // InternalGrana.g:6286:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            // InternalGrana.g:6287:2: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_0_3_4()); 
            // InternalGrana.g:6288:2: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            // InternalGrana.g:6288:3: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4
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
    // InternalGrana.g:6297:1: rule__ElkSingleEdgeSection__Group_1_1__0 : rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6301:1: ( rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 )
            // InternalGrana.g:6302:2: rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1
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
    // InternalGrana.g:6309:1: rule__ElkSingleEdgeSection__Group_1_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6313:1: ( ( 'bends' ) )
            // InternalGrana.g:6314:1: ( 'bends' )
            {
            // InternalGrana.g:6314:1: ( 'bends' )
            // InternalGrana.g:6315:2: 'bends'
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
    // InternalGrana.g:6324:1: rule__ElkSingleEdgeSection__Group_1_1__1 : rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6328:1: ( rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 )
            // InternalGrana.g:6329:2: rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2
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
    // InternalGrana.g:6336:1: rule__ElkSingleEdgeSection__Group_1_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6340:1: ( ( ':' ) )
            // InternalGrana.g:6341:1: ( ':' )
            {
            // InternalGrana.g:6341:1: ( ':' )
            // InternalGrana.g:6342:2: ':'
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
    // InternalGrana.g:6351:1: rule__ElkSingleEdgeSection__Group_1_1__2 : rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6355:1: ( rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 )
            // InternalGrana.g:6356:2: rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3
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
    // InternalGrana.g:6363:1: rule__ElkSingleEdgeSection__Group_1_1__2__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6367:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) )
            // InternalGrana.g:6368:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            {
            // InternalGrana.g:6368:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            // InternalGrana.g:6369:2: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_2()); 
            // InternalGrana.g:6370:2: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            // InternalGrana.g:6370:3: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2
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
    // InternalGrana.g:6378:1: rule__ElkSingleEdgeSection__Group_1_1__3 : rule__ElkSingleEdgeSection__Group_1_1__3__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6382:1: ( rule__ElkSingleEdgeSection__Group_1_1__3__Impl )
            // InternalGrana.g:6383:2: rule__ElkSingleEdgeSection__Group_1_1__3__Impl
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
    // InternalGrana.g:6389:1: rule__ElkSingleEdgeSection__Group_1_1__3__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6393:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) )
            // InternalGrana.g:6394:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            {
            // InternalGrana.g:6394:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            // InternalGrana.g:6395:2: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1_3()); 
            // InternalGrana.g:6396:2: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==58) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // InternalGrana.g:6396:3: rule__ElkSingleEdgeSection__Group_1_1_3__0
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
    // InternalGrana.g:6405:1: rule__ElkSingleEdgeSection__Group_1_1_3__0 : rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6409:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 )
            // InternalGrana.g:6410:2: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1
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
    // InternalGrana.g:6417:1: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6421:1: ( ( '|' ) )
            // InternalGrana.g:6422:1: ( '|' )
            {
            // InternalGrana.g:6422:1: ( '|' )
            // InternalGrana.g:6423:2: '|'
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
    // InternalGrana.g:6432:1: rule__ElkSingleEdgeSection__Group_1_1_3__1 : rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6436:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl )
            // InternalGrana.g:6437:2: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl
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
    // InternalGrana.g:6443:1: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6447:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) )
            // InternalGrana.g:6448:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            {
            // InternalGrana.g:6448:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            // InternalGrana.g:6449:2: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_3_1()); 
            // InternalGrana.g:6450:2: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            // InternalGrana.g:6450:3: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1
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
    // InternalGrana.g:6459:1: rule__ElkEdgeSection__Group__0 : rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 ;
    public final void rule__ElkEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6463:1: ( rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 )
            // InternalGrana.g:6464:2: rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1
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
    // InternalGrana.g:6471:1: rule__ElkEdgeSection__Group__0__Impl : ( 'section' ) ;
    public final void rule__ElkEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6475:1: ( ( 'section' ) )
            // InternalGrana.g:6476:1: ( 'section' )
            {
            // InternalGrana.g:6476:1: ( 'section' )
            // InternalGrana.g:6477:2: 'section'
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
    // InternalGrana.g:6486:1: rule__ElkEdgeSection__Group__1 : rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 ;
    public final void rule__ElkEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6490:1: ( rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 )
            // InternalGrana.g:6491:2: rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2
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
    // InternalGrana.g:6498:1: rule__ElkEdgeSection__Group__1__Impl : ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6502:1: ( ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:6503:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:6503:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            // InternalGrana.g:6504:2: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:6505:2: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            // InternalGrana.g:6505:3: rule__ElkEdgeSection__IdentifierAssignment_1
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
    // InternalGrana.g:6513:1: rule__ElkEdgeSection__Group__2 : rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 ;
    public final void rule__ElkEdgeSection__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6517:1: ( rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 )
            // InternalGrana.g:6518:2: rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3
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
    // InternalGrana.g:6525:1: rule__ElkEdgeSection__Group__2__Impl : ( ( rule__ElkEdgeSection__Group_2__0 )? ) ;
    public final void rule__ElkEdgeSection__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6529:1: ( ( ( rule__ElkEdgeSection__Group_2__0 )? ) )
            // InternalGrana.g:6530:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            {
            // InternalGrana.g:6530:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            // InternalGrana.g:6531:2: ( rule__ElkEdgeSection__Group_2__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 
            // InternalGrana.g:6532:2: ( rule__ElkEdgeSection__Group_2__0 )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==52) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalGrana.g:6532:3: rule__ElkEdgeSection__Group_2__0
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
    // InternalGrana.g:6540:1: rule__ElkEdgeSection__Group__3 : rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 ;
    public final void rule__ElkEdgeSection__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6544:1: ( rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 )
            // InternalGrana.g:6545:2: rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4
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
    // InternalGrana.g:6552:1: rule__ElkEdgeSection__Group__3__Impl : ( '[' ) ;
    public final void rule__ElkEdgeSection__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6556:1: ( ( '[' ) )
            // InternalGrana.g:6557:1: ( '[' )
            {
            // InternalGrana.g:6557:1: ( '[' )
            // InternalGrana.g:6558:2: '['
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
    // InternalGrana.g:6567:1: rule__ElkEdgeSection__Group__4 : rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 ;
    public final void rule__ElkEdgeSection__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6571:1: ( rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 )
            // InternalGrana.g:6572:2: rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5
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
    // InternalGrana.g:6579:1: rule__ElkEdgeSection__Group__4__Impl : ( ( rule__ElkEdgeSection__Group_4__0 ) ) ;
    public final void rule__ElkEdgeSection__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6583:1: ( ( ( rule__ElkEdgeSection__Group_4__0 ) ) )
            // InternalGrana.g:6584:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            {
            // InternalGrana.g:6584:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            // InternalGrana.g:6585:2: ( rule__ElkEdgeSection__Group_4__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4()); 
            // InternalGrana.g:6586:2: ( rule__ElkEdgeSection__Group_4__0 )
            // InternalGrana.g:6586:3: rule__ElkEdgeSection__Group_4__0
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
    // InternalGrana.g:6594:1: rule__ElkEdgeSection__Group__5 : rule__ElkEdgeSection__Group__5__Impl ;
    public final void rule__ElkEdgeSection__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6598:1: ( rule__ElkEdgeSection__Group__5__Impl )
            // InternalGrana.g:6599:2: rule__ElkEdgeSection__Group__5__Impl
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
    // InternalGrana.g:6605:1: rule__ElkEdgeSection__Group__5__Impl : ( ']' ) ;
    public final void rule__ElkEdgeSection__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6609:1: ( ( ']' ) )
            // InternalGrana.g:6610:1: ( ']' )
            {
            // InternalGrana.g:6610:1: ( ']' )
            // InternalGrana.g:6611:2: ']'
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
    // InternalGrana.g:6621:1: rule__ElkEdgeSection__Group_2__0 : rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 ;
    public final void rule__ElkEdgeSection__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6625:1: ( rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 )
            // InternalGrana.g:6626:2: rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1
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
    // InternalGrana.g:6633:1: rule__ElkEdgeSection__Group_2__0__Impl : ( '->' ) ;
    public final void rule__ElkEdgeSection__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6637:1: ( ( '->' ) )
            // InternalGrana.g:6638:1: ( '->' )
            {
            // InternalGrana.g:6638:1: ( '->' )
            // InternalGrana.g:6639:2: '->'
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
    // InternalGrana.g:6648:1: rule__ElkEdgeSection__Group_2__1 : rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 ;
    public final void rule__ElkEdgeSection__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6652:1: ( rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 )
            // InternalGrana.g:6653:2: rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2
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
    // InternalGrana.g:6660:1: rule__ElkEdgeSection__Group_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6664:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) )
            // InternalGrana.g:6665:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            {
            // InternalGrana.g:6665:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            // InternalGrana.g:6666:2: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 
            // InternalGrana.g:6667:2: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            // InternalGrana.g:6667:3: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1
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
    // InternalGrana.g:6675:1: rule__ElkEdgeSection__Group_2__2 : rule__ElkEdgeSection__Group_2__2__Impl ;
    public final void rule__ElkEdgeSection__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6679:1: ( rule__ElkEdgeSection__Group_2__2__Impl )
            // InternalGrana.g:6680:2: rule__ElkEdgeSection__Group_2__2__Impl
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
    // InternalGrana.g:6686:1: rule__ElkEdgeSection__Group_2__2__Impl : ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6690:1: ( ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) )
            // InternalGrana.g:6691:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            {
            // InternalGrana.g:6691:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            // InternalGrana.g:6692:2: ( rule__ElkEdgeSection__Group_2_2__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 
            // InternalGrana.g:6693:2: ( rule__ElkEdgeSection__Group_2_2__0 )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==33) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalGrana.g:6693:3: rule__ElkEdgeSection__Group_2_2__0
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
    // InternalGrana.g:6702:1: rule__ElkEdgeSection__Group_2_2__0 : rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 ;
    public final void rule__ElkEdgeSection__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6706:1: ( rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 )
            // InternalGrana.g:6707:2: rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1
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
    // InternalGrana.g:6714:1: rule__ElkEdgeSection__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6718:1: ( ( ',' ) )
            // InternalGrana.g:6719:1: ( ',' )
            {
            // InternalGrana.g:6719:1: ( ',' )
            // InternalGrana.g:6720:2: ','
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
    // InternalGrana.g:6729:1: rule__ElkEdgeSection__Group_2_2__1 : rule__ElkEdgeSection__Group_2_2__1__Impl ;
    public final void rule__ElkEdgeSection__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6733:1: ( rule__ElkEdgeSection__Group_2_2__1__Impl )
            // InternalGrana.g:6734:2: rule__ElkEdgeSection__Group_2_2__1__Impl
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
    // InternalGrana.g:6740:1: rule__ElkEdgeSection__Group_2_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6744:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) )
            // InternalGrana.g:6745:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            {
            // InternalGrana.g:6745:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            // InternalGrana.g:6746:2: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 
            // InternalGrana.g:6747:2: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            // InternalGrana.g:6747:3: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1
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
    // InternalGrana.g:6756:1: rule__ElkEdgeSection__Group_4__0 : rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 ;
    public final void rule__ElkEdgeSection__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6760:1: ( rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 )
            // InternalGrana.g:6761:2: rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1
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
    // InternalGrana.g:6768:1: rule__ElkEdgeSection__Group_4__0__Impl : ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) ;
    public final void rule__ElkEdgeSection__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6772:1: ( ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) )
            // InternalGrana.g:6773:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            {
            // InternalGrana.g:6773:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            // InternalGrana.g:6774:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0()); 
            // InternalGrana.g:6775:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            // InternalGrana.g:6775:3: rule__ElkEdgeSection__UnorderedGroup_4_0
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
    // InternalGrana.g:6783:1: rule__ElkEdgeSection__Group_4__1 : rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 ;
    public final void rule__ElkEdgeSection__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6787:1: ( rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 )
            // InternalGrana.g:6788:2: rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2
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
    // InternalGrana.g:6795:1: rule__ElkEdgeSection__Group_4__1__Impl : ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) ;
    public final void rule__ElkEdgeSection__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6799:1: ( ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) )
            // InternalGrana.g:6800:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            {
            // InternalGrana.g:6800:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            // InternalGrana.g:6801:2: ( rule__ElkEdgeSection__Group_4_1__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 
            // InternalGrana.g:6802:2: ( rule__ElkEdgeSection__Group_4_1__0 )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==57) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalGrana.g:6802:3: rule__ElkEdgeSection__Group_4_1__0
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
    // InternalGrana.g:6810:1: rule__ElkEdgeSection__Group_4__2 : rule__ElkEdgeSection__Group_4__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6814:1: ( rule__ElkEdgeSection__Group_4__2__Impl )
            // InternalGrana.g:6815:2: rule__ElkEdgeSection__Group_4__2__Impl
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
    // InternalGrana.g:6821:1: rule__ElkEdgeSection__Group_4__2__Impl : ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) ;
    public final void rule__ElkEdgeSection__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6825:1: ( ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) )
            // InternalGrana.g:6826:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            {
            // InternalGrana.g:6826:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            // InternalGrana.g:6827:2: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getPropertiesAssignment_4_2()); 
            // InternalGrana.g:6828:2: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==RULE_ID) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // InternalGrana.g:6828:3: rule__ElkEdgeSection__PropertiesAssignment_4_2
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
    // InternalGrana.g:6837:1: rule__ElkEdgeSection__Group_4_0_0__0 : rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6841:1: ( rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 )
            // InternalGrana.g:6842:2: rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1
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
    // InternalGrana.g:6849:1: rule__ElkEdgeSection__Group_4_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6853:1: ( ( 'incoming' ) )
            // InternalGrana.g:6854:1: ( 'incoming' )
            {
            // InternalGrana.g:6854:1: ( 'incoming' )
            // InternalGrana.g:6855:2: 'incoming'
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
    // InternalGrana.g:6864:1: rule__ElkEdgeSection__Group_4_0_0__1 : rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6868:1: ( rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 )
            // InternalGrana.g:6869:2: rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2
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
    // InternalGrana.g:6876:1: rule__ElkEdgeSection__Group_4_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6880:1: ( ( ':' ) )
            // InternalGrana.g:6881:1: ( ':' )
            {
            // InternalGrana.g:6881:1: ( ':' )
            // InternalGrana.g:6882:2: ':'
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
    // InternalGrana.g:6891:1: rule__ElkEdgeSection__Group_4_0_0__2 : rule__ElkEdgeSection__Group_4_0_0__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6895:1: ( rule__ElkEdgeSection__Group_4_0_0__2__Impl )
            // InternalGrana.g:6896:2: rule__ElkEdgeSection__Group_4_0_0__2__Impl
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
    // InternalGrana.g:6902:1: rule__ElkEdgeSection__Group_4_0_0__2__Impl : ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6906:1: ( ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) )
            // InternalGrana.g:6907:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            {
            // InternalGrana.g:6907:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            // InternalGrana.g:6908:2: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_0_2()); 
            // InternalGrana.g:6909:2: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            // InternalGrana.g:6909:3: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2
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
    // InternalGrana.g:6918:1: rule__ElkEdgeSection__Group_4_0_1__0 : rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6922:1: ( rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 )
            // InternalGrana.g:6923:2: rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1
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
    // InternalGrana.g:6930:1: rule__ElkEdgeSection__Group_4_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6934:1: ( ( 'outgoing' ) )
            // InternalGrana.g:6935:1: ( 'outgoing' )
            {
            // InternalGrana.g:6935:1: ( 'outgoing' )
            // InternalGrana.g:6936:2: 'outgoing'
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
    // InternalGrana.g:6945:1: rule__ElkEdgeSection__Group_4_0_1__1 : rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6949:1: ( rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 )
            // InternalGrana.g:6950:2: rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2
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
    // InternalGrana.g:6957:1: rule__ElkEdgeSection__Group_4_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6961:1: ( ( ':' ) )
            // InternalGrana.g:6962:1: ( ':' )
            {
            // InternalGrana.g:6962:1: ( ':' )
            // InternalGrana.g:6963:2: ':'
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
    // InternalGrana.g:6972:1: rule__ElkEdgeSection__Group_4_0_1__2 : rule__ElkEdgeSection__Group_4_0_1__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6976:1: ( rule__ElkEdgeSection__Group_4_0_1__2__Impl )
            // InternalGrana.g:6977:2: rule__ElkEdgeSection__Group_4_0_1__2__Impl
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
    // InternalGrana.g:6983:1: rule__ElkEdgeSection__Group_4_0_1__2__Impl : ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:6987:1: ( ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) )
            // InternalGrana.g:6988:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            {
            // InternalGrana.g:6988:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            // InternalGrana.g:6989:2: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_0_1_2()); 
            // InternalGrana.g:6990:2: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            // InternalGrana.g:6990:3: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2
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
    // InternalGrana.g:6999:1: rule__ElkEdgeSection__Group_4_0_2__0 : rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7003:1: ( rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 )
            // InternalGrana.g:7004:2: rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1
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
    // InternalGrana.g:7011:1: rule__ElkEdgeSection__Group_4_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7015:1: ( ( 'start' ) )
            // InternalGrana.g:7016:1: ( 'start' )
            {
            // InternalGrana.g:7016:1: ( 'start' )
            // InternalGrana.g:7017:2: 'start'
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
    // InternalGrana.g:7026:1: rule__ElkEdgeSection__Group_4_0_2__1 : rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7030:1: ( rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 )
            // InternalGrana.g:7031:2: rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2
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
    // InternalGrana.g:7038:1: rule__ElkEdgeSection__Group_4_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7042:1: ( ( ':' ) )
            // InternalGrana.g:7043:1: ( ':' )
            {
            // InternalGrana.g:7043:1: ( ':' )
            // InternalGrana.g:7044:2: ':'
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
    // InternalGrana.g:7053:1: rule__ElkEdgeSection__Group_4_0_2__2 : rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7057:1: ( rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 )
            // InternalGrana.g:7058:2: rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3
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
    // InternalGrana.g:7065:1: rule__ElkEdgeSection__Group_4_0_2__2__Impl : ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7069:1: ( ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) )
            // InternalGrana.g:7070:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            {
            // InternalGrana.g:7070:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            // InternalGrana.g:7071:2: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_0_2_2()); 
            // InternalGrana.g:7072:2: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            // InternalGrana.g:7072:3: rule__ElkEdgeSection__StartXAssignment_4_0_2_2
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
    // InternalGrana.g:7080:1: rule__ElkEdgeSection__Group_4_0_2__3 : rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7084:1: ( rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 )
            // InternalGrana.g:7085:2: rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4
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
    // InternalGrana.g:7092:1: rule__ElkEdgeSection__Group_4_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7096:1: ( ( ',' ) )
            // InternalGrana.g:7097:1: ( ',' )
            {
            // InternalGrana.g:7097:1: ( ',' )
            // InternalGrana.g:7098:2: ','
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
    // InternalGrana.g:7107:1: rule__ElkEdgeSection__Group_4_0_2__4 : rule__ElkEdgeSection__Group_4_0_2__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7111:1: ( rule__ElkEdgeSection__Group_4_0_2__4__Impl )
            // InternalGrana.g:7112:2: rule__ElkEdgeSection__Group_4_0_2__4__Impl
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
    // InternalGrana.g:7118:1: rule__ElkEdgeSection__Group_4_0_2__4__Impl : ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7122:1: ( ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) )
            // InternalGrana.g:7123:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            {
            // InternalGrana.g:7123:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            // InternalGrana.g:7124:2: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_0_2_4()); 
            // InternalGrana.g:7125:2: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            // InternalGrana.g:7125:3: rule__ElkEdgeSection__StartYAssignment_4_0_2_4
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
    // InternalGrana.g:7134:1: rule__ElkEdgeSection__Group_4_0_3__0 : rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7138:1: ( rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 )
            // InternalGrana.g:7139:2: rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1
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
    // InternalGrana.g:7146:1: rule__ElkEdgeSection__Group_4_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7150:1: ( ( 'end' ) )
            // InternalGrana.g:7151:1: ( 'end' )
            {
            // InternalGrana.g:7151:1: ( 'end' )
            // InternalGrana.g:7152:2: 'end'
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
    // InternalGrana.g:7161:1: rule__ElkEdgeSection__Group_4_0_3__1 : rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7165:1: ( rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 )
            // InternalGrana.g:7166:2: rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2
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
    // InternalGrana.g:7173:1: rule__ElkEdgeSection__Group_4_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7177:1: ( ( ':' ) )
            // InternalGrana.g:7178:1: ( ':' )
            {
            // InternalGrana.g:7178:1: ( ':' )
            // InternalGrana.g:7179:2: ':'
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
    // InternalGrana.g:7188:1: rule__ElkEdgeSection__Group_4_0_3__2 : rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7192:1: ( rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 )
            // InternalGrana.g:7193:2: rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3
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
    // InternalGrana.g:7200:1: rule__ElkEdgeSection__Group_4_0_3__2__Impl : ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7204:1: ( ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) )
            // InternalGrana.g:7205:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            {
            // InternalGrana.g:7205:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            // InternalGrana.g:7206:2: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_0_3_2()); 
            // InternalGrana.g:7207:2: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            // InternalGrana.g:7207:3: rule__ElkEdgeSection__EndXAssignment_4_0_3_2
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
    // InternalGrana.g:7215:1: rule__ElkEdgeSection__Group_4_0_3__3 : rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7219:1: ( rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 )
            // InternalGrana.g:7220:2: rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4
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
    // InternalGrana.g:7227:1: rule__ElkEdgeSection__Group_4_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7231:1: ( ( ',' ) )
            // InternalGrana.g:7232:1: ( ',' )
            {
            // InternalGrana.g:7232:1: ( ',' )
            // InternalGrana.g:7233:2: ','
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
    // InternalGrana.g:7242:1: rule__ElkEdgeSection__Group_4_0_3__4 : rule__ElkEdgeSection__Group_4_0_3__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7246:1: ( rule__ElkEdgeSection__Group_4_0_3__4__Impl )
            // InternalGrana.g:7247:2: rule__ElkEdgeSection__Group_4_0_3__4__Impl
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
    // InternalGrana.g:7253:1: rule__ElkEdgeSection__Group_4_0_3__4__Impl : ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7257:1: ( ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) )
            // InternalGrana.g:7258:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            {
            // InternalGrana.g:7258:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            // InternalGrana.g:7259:2: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_0_3_4()); 
            // InternalGrana.g:7260:2: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            // InternalGrana.g:7260:3: rule__ElkEdgeSection__EndYAssignment_4_0_3_4
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
    // InternalGrana.g:7269:1: rule__ElkEdgeSection__Group_4_1__0 : rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7273:1: ( rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 )
            // InternalGrana.g:7274:2: rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1
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
    // InternalGrana.g:7281:1: rule__ElkEdgeSection__Group_4_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7285:1: ( ( 'bends' ) )
            // InternalGrana.g:7286:1: ( 'bends' )
            {
            // InternalGrana.g:7286:1: ( 'bends' )
            // InternalGrana.g:7287:2: 'bends'
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
    // InternalGrana.g:7296:1: rule__ElkEdgeSection__Group_4_1__1 : rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7300:1: ( rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 )
            // InternalGrana.g:7301:2: rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2
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
    // InternalGrana.g:7308:1: rule__ElkEdgeSection__Group_4_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7312:1: ( ( ':' ) )
            // InternalGrana.g:7313:1: ( ':' )
            {
            // InternalGrana.g:7313:1: ( ':' )
            // InternalGrana.g:7314:2: ':'
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
    // InternalGrana.g:7323:1: rule__ElkEdgeSection__Group_4_1__2 : rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 ;
    public final void rule__ElkEdgeSection__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7327:1: ( rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 )
            // InternalGrana.g:7328:2: rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3
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
    // InternalGrana.g:7335:1: rule__ElkEdgeSection__Group_4_1__2__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7339:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) )
            // InternalGrana.g:7340:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            {
            // InternalGrana.g:7340:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            // InternalGrana.g:7341:2: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_2()); 
            // InternalGrana.g:7342:2: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            // InternalGrana.g:7342:3: rule__ElkEdgeSection__BendPointsAssignment_4_1_2
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
    // InternalGrana.g:7350:1: rule__ElkEdgeSection__Group_4_1__3 : rule__ElkEdgeSection__Group_4_1__3__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7354:1: ( rule__ElkEdgeSection__Group_4_1__3__Impl )
            // InternalGrana.g:7355:2: rule__ElkEdgeSection__Group_4_1__3__Impl
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
    // InternalGrana.g:7361:1: rule__ElkEdgeSection__Group_4_1__3__Impl : ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_4_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7365:1: ( ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) )
            // InternalGrana.g:7366:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            {
            // InternalGrana.g:7366:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            // InternalGrana.g:7367:2: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1_3()); 
            // InternalGrana.g:7368:2: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==58) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // InternalGrana.g:7368:3: rule__ElkEdgeSection__Group_4_1_3__0
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
    // InternalGrana.g:7377:1: rule__ElkEdgeSection__Group_4_1_3__0 : rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7381:1: ( rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 )
            // InternalGrana.g:7382:2: rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1
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
    // InternalGrana.g:7389:1: rule__ElkEdgeSection__Group_4_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7393:1: ( ( '|' ) )
            // InternalGrana.g:7394:1: ( '|' )
            {
            // InternalGrana.g:7394:1: ( '|' )
            // InternalGrana.g:7395:2: '|'
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
    // InternalGrana.g:7404:1: rule__ElkEdgeSection__Group_4_1_3__1 : rule__ElkEdgeSection__Group_4_1_3__1__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7408:1: ( rule__ElkEdgeSection__Group_4_1_3__1__Impl )
            // InternalGrana.g:7409:2: rule__ElkEdgeSection__Group_4_1_3__1__Impl
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
    // InternalGrana.g:7415:1: rule__ElkEdgeSection__Group_4_1_3__1__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7419:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) )
            // InternalGrana.g:7420:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            {
            // InternalGrana.g:7420:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            // InternalGrana.g:7421:2: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_3_1()); 
            // InternalGrana.g:7422:2: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            // InternalGrana.g:7422:3: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1
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
    // InternalGrana.g:7431:1: rule__ElkBendPoint__Group__0 : rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 ;
    public final void rule__ElkBendPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7435:1: ( rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 )
            // InternalGrana.g:7436:2: rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1
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
    // InternalGrana.g:7443:1: rule__ElkBendPoint__Group__0__Impl : ( ( rule__ElkBendPoint__XAssignment_0 ) ) ;
    public final void rule__ElkBendPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7447:1: ( ( ( rule__ElkBendPoint__XAssignment_0 ) ) )
            // InternalGrana.g:7448:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            {
            // InternalGrana.g:7448:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            // InternalGrana.g:7449:2: ( rule__ElkBendPoint__XAssignment_0 )
            {
             before(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 
            // InternalGrana.g:7450:2: ( rule__ElkBendPoint__XAssignment_0 )
            // InternalGrana.g:7450:3: rule__ElkBendPoint__XAssignment_0
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
    // InternalGrana.g:7458:1: rule__ElkBendPoint__Group__1 : rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 ;
    public final void rule__ElkBendPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7462:1: ( rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 )
            // InternalGrana.g:7463:2: rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2
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
    // InternalGrana.g:7470:1: rule__ElkBendPoint__Group__1__Impl : ( ',' ) ;
    public final void rule__ElkBendPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7474:1: ( ( ',' ) )
            // InternalGrana.g:7475:1: ( ',' )
            {
            // InternalGrana.g:7475:1: ( ',' )
            // InternalGrana.g:7476:2: ','
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
    // InternalGrana.g:7485:1: rule__ElkBendPoint__Group__2 : rule__ElkBendPoint__Group__2__Impl ;
    public final void rule__ElkBendPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7489:1: ( rule__ElkBendPoint__Group__2__Impl )
            // InternalGrana.g:7490:2: rule__ElkBendPoint__Group__2__Impl
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
    // InternalGrana.g:7496:1: rule__ElkBendPoint__Group__2__Impl : ( ( rule__ElkBendPoint__YAssignment_2 ) ) ;
    public final void rule__ElkBendPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7500:1: ( ( ( rule__ElkBendPoint__YAssignment_2 ) ) )
            // InternalGrana.g:7501:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            {
            // InternalGrana.g:7501:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            // InternalGrana.g:7502:2: ( rule__ElkBendPoint__YAssignment_2 )
            {
             before(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 
            // InternalGrana.g:7503:2: ( rule__ElkBendPoint__YAssignment_2 )
            // InternalGrana.g:7503:3: rule__ElkBendPoint__YAssignment_2
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
    // InternalGrana.g:7512:1: rule__QualifiedId__Group__0 : rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 ;
    public final void rule__QualifiedId__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7516:1: ( rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 )
            // InternalGrana.g:7517:2: rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1
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
    // InternalGrana.g:7524:1: rule__QualifiedId__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7528:1: ( ( RULE_ID ) )
            // InternalGrana.g:7529:1: ( RULE_ID )
            {
            // InternalGrana.g:7529:1: ( RULE_ID )
            // InternalGrana.g:7530:2: RULE_ID
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
    // InternalGrana.g:7539:1: rule__QualifiedId__Group__1 : rule__QualifiedId__Group__1__Impl ;
    public final void rule__QualifiedId__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7543:1: ( rule__QualifiedId__Group__1__Impl )
            // InternalGrana.g:7544:2: rule__QualifiedId__Group__1__Impl
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
    // InternalGrana.g:7550:1: rule__QualifiedId__Group__1__Impl : ( ( rule__QualifiedId__Group_1__0 )* ) ;
    public final void rule__QualifiedId__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7554:1: ( ( ( rule__QualifiedId__Group_1__0 )* ) )
            // InternalGrana.g:7555:1: ( ( rule__QualifiedId__Group_1__0 )* )
            {
            // InternalGrana.g:7555:1: ( ( rule__QualifiedId__Group_1__0 )* )
            // InternalGrana.g:7556:2: ( rule__QualifiedId__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup_1()); 
            // InternalGrana.g:7557:2: ( rule__QualifiedId__Group_1__0 )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==60) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // InternalGrana.g:7557:3: rule__QualifiedId__Group_1__0
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
    // InternalGrana.g:7566:1: rule__QualifiedId__Group_1__0 : rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 ;
    public final void rule__QualifiedId__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7570:1: ( rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 )
            // InternalGrana.g:7571:2: rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1
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
    // InternalGrana.g:7578:1: rule__QualifiedId__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedId__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7582:1: ( ( '.' ) )
            // InternalGrana.g:7583:1: ( '.' )
            {
            // InternalGrana.g:7583:1: ( '.' )
            // InternalGrana.g:7584:2: '.'
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
    // InternalGrana.g:7593:1: rule__QualifiedId__Group_1__1 : rule__QualifiedId__Group_1__1__Impl ;
    public final void rule__QualifiedId__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7597:1: ( rule__QualifiedId__Group_1__1__Impl )
            // InternalGrana.g:7598:2: rule__QualifiedId__Group_1__1__Impl
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
    // InternalGrana.g:7604:1: rule__QualifiedId__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7608:1: ( ( RULE_ID ) )
            // InternalGrana.g:7609:1: ( RULE_ID )
            {
            // InternalGrana.g:7609:1: ( RULE_ID )
            // InternalGrana.g:7610:2: RULE_ID
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
    // InternalGrana.g:7620:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7624:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalGrana.g:7625:2: rule__Property__Group__0__Impl rule__Property__Group__1
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
    // InternalGrana.g:7632:1: rule__Property__Group__0__Impl : ( ( rule__Property__KeyAssignment_0 ) ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7636:1: ( ( ( rule__Property__KeyAssignment_0 ) ) )
            // InternalGrana.g:7637:1: ( ( rule__Property__KeyAssignment_0 ) )
            {
            // InternalGrana.g:7637:1: ( ( rule__Property__KeyAssignment_0 ) )
            // InternalGrana.g:7638:2: ( rule__Property__KeyAssignment_0 )
            {
             before(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 
            // InternalGrana.g:7639:2: ( rule__Property__KeyAssignment_0 )
            // InternalGrana.g:7639:3: rule__Property__KeyAssignment_0
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
    // InternalGrana.g:7647:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7651:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalGrana.g:7652:2: rule__Property__Group__1__Impl rule__Property__Group__2
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
    // InternalGrana.g:7659:1: rule__Property__Group__1__Impl : ( ':' ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7663:1: ( ( ':' ) )
            // InternalGrana.g:7664:1: ( ':' )
            {
            // InternalGrana.g:7664:1: ( ':' )
            // InternalGrana.g:7665:2: ':'
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
    // InternalGrana.g:7674:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7678:1: ( rule__Property__Group__2__Impl )
            // InternalGrana.g:7679:2: rule__Property__Group__2__Impl
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
    // InternalGrana.g:7685:1: rule__Property__Group__2__Impl : ( ( rule__Property__Alternatives_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7689:1: ( ( ( rule__Property__Alternatives_2 ) ) )
            // InternalGrana.g:7690:1: ( ( rule__Property__Alternatives_2 ) )
            {
            // InternalGrana.g:7690:1: ( ( rule__Property__Alternatives_2 ) )
            // InternalGrana.g:7691:2: ( rule__Property__Alternatives_2 )
            {
             before(grammarAccess.getPropertyAccess().getAlternatives_2()); 
            // InternalGrana.g:7692:2: ( rule__Property__Alternatives_2 )
            // InternalGrana.g:7692:3: rule__Property__Alternatives_2
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
    // InternalGrana.g:7701:1: rule__PropertyKey__Group__0 : rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 ;
    public final void rule__PropertyKey__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7705:1: ( rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 )
            // InternalGrana.g:7706:2: rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1
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
    // InternalGrana.g:7713:1: rule__PropertyKey__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7717:1: ( ( RULE_ID ) )
            // InternalGrana.g:7718:1: ( RULE_ID )
            {
            // InternalGrana.g:7718:1: ( RULE_ID )
            // InternalGrana.g:7719:2: RULE_ID
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
    // InternalGrana.g:7728:1: rule__PropertyKey__Group__1 : rule__PropertyKey__Group__1__Impl ;
    public final void rule__PropertyKey__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7732:1: ( rule__PropertyKey__Group__1__Impl )
            // InternalGrana.g:7733:2: rule__PropertyKey__Group__1__Impl
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
    // InternalGrana.g:7739:1: rule__PropertyKey__Group__1__Impl : ( ( rule__PropertyKey__Group_1__0 )* ) ;
    public final void rule__PropertyKey__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7743:1: ( ( ( rule__PropertyKey__Group_1__0 )* ) )
            // InternalGrana.g:7744:1: ( ( rule__PropertyKey__Group_1__0 )* )
            {
            // InternalGrana.g:7744:1: ( ( rule__PropertyKey__Group_1__0 )* )
            // InternalGrana.g:7745:2: ( rule__PropertyKey__Group_1__0 )*
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup_1()); 
            // InternalGrana.g:7746:2: ( rule__PropertyKey__Group_1__0 )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==60) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // InternalGrana.g:7746:3: rule__PropertyKey__Group_1__0
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
    // InternalGrana.g:7755:1: rule__PropertyKey__Group_1__0 : rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 ;
    public final void rule__PropertyKey__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7759:1: ( rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 )
            // InternalGrana.g:7760:2: rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1
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
    // InternalGrana.g:7767:1: rule__PropertyKey__Group_1__0__Impl : ( '.' ) ;
    public final void rule__PropertyKey__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7771:1: ( ( '.' ) )
            // InternalGrana.g:7772:1: ( '.' )
            {
            // InternalGrana.g:7772:1: ( '.' )
            // InternalGrana.g:7773:2: '.'
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
    // InternalGrana.g:7782:1: rule__PropertyKey__Group_1__1 : rule__PropertyKey__Group_1__1__Impl ;
    public final void rule__PropertyKey__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7786:1: ( rule__PropertyKey__Group_1__1__Impl )
            // InternalGrana.g:7787:2: rule__PropertyKey__Group_1__1__Impl
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
    // InternalGrana.g:7793:1: rule__PropertyKey__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7797:1: ( ( RULE_ID ) )
            // InternalGrana.g:7798:1: ( RULE_ID )
            {
            // InternalGrana.g:7798:1: ( RULE_ID )
            // InternalGrana.g:7799:2: RULE_ID
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
    // InternalGrana.g:7809:1: rule__ShapeLayout__UnorderedGroup_2 : ( rule__ShapeLayout__UnorderedGroup_2__0 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
        	
        try {
            // InternalGrana.g:7814:1: ( ( rule__ShapeLayout__UnorderedGroup_2__0 )? )
            // InternalGrana.g:7815:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            {
            // InternalGrana.g:7815:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
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
                    // InternalGrana.g:7815:2: rule__ShapeLayout__UnorderedGroup_2__0
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
    // InternalGrana.g:7823:1: rule__ShapeLayout__UnorderedGroup_2__Impl : ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) ;
    public final void rule__ShapeLayout__UnorderedGroup_2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalGrana.g:7828:1: ( ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) )
            // InternalGrana.g:7829:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            {
            // InternalGrana.g:7829:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
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
                    // InternalGrana.g:7830:3: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    {
                    // InternalGrana.g:7830:3: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    // InternalGrana.g:7831:4: {...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
                    }
                    // InternalGrana.g:7831:107: ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    // InternalGrana.g:7832:5: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:7838:5: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    // InternalGrana.g:7839:6: ( rule__ShapeLayout__Group_2_0__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 
                    // InternalGrana.g:7840:6: ( rule__ShapeLayout__Group_2_0__0 )
                    // InternalGrana.g:7840:7: rule__ShapeLayout__Group_2_0__0
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
                    // InternalGrana.g:7845:3: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    {
                    // InternalGrana.g:7845:3: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    // InternalGrana.g:7846:4: {...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
                    }
                    // InternalGrana.g:7846:107: ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    // InternalGrana.g:7847:5: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:7853:5: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    // InternalGrana.g:7854:6: ( rule__ShapeLayout__Group_2_1__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 
                    // InternalGrana.g:7855:6: ( rule__ShapeLayout__Group_2_1__0 )
                    // InternalGrana.g:7855:7: rule__ShapeLayout__Group_2_1__0
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
    // InternalGrana.g:7868:1: rule__ShapeLayout__UnorderedGroup_2__0 : rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7872:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? )
            // InternalGrana.g:7873:2: rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            {
            pushFollow(FOLLOW_54);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;

            // InternalGrana.g:7874:2: ( rule__ShapeLayout__UnorderedGroup_2__1 )?
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
                    // InternalGrana.g:7874:2: rule__ShapeLayout__UnorderedGroup_2__1
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
    // InternalGrana.g:7880:1: rule__ShapeLayout__UnorderedGroup_2__1 : rule__ShapeLayout__UnorderedGroup_2__Impl ;
    public final void rule__ShapeLayout__UnorderedGroup_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7884:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl )
            // InternalGrana.g:7885:2: rule__ShapeLayout__UnorderedGroup_2__Impl
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
    // InternalGrana.g:7892:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0 : ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
        	
        try {
            // InternalGrana.g:7897:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? )
            // InternalGrana.g:7898:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
            {
            // InternalGrana.g:7898:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
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
                    // InternalGrana.g:7898:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0
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
    // InternalGrana.g:7906:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl : ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalGrana.g:7911:1: ( ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) )
            // InternalGrana.g:7912:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
            {
            // InternalGrana.g:7912:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
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
                    // InternalGrana.g:7913:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    {
                    // InternalGrana.g:7913:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    // InternalGrana.g:7914:4: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0)");
                    }
                    // InternalGrana.g:7914:118: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    // InternalGrana.g:7915:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:7921:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    // InternalGrana.g:7922:6: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_0()); 
                    // InternalGrana.g:7923:6: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    // InternalGrana.g:7923:7: rule__ElkSingleEdgeSection__Group_1_0_0__0
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
                    // InternalGrana.g:7928:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    {
                    // InternalGrana.g:7928:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    // InternalGrana.g:7929:4: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1)");
                    }
                    // InternalGrana.g:7929:118: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    // InternalGrana.g:7930:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:7936:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    // InternalGrana.g:7937:6: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_1()); 
                    // InternalGrana.g:7938:6: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    // InternalGrana.g:7938:7: rule__ElkSingleEdgeSection__Group_1_0_1__0
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
                    // InternalGrana.g:7943:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    {
                    // InternalGrana.g:7943:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    // InternalGrana.g:7944:4: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2)");
                    }
                    // InternalGrana.g:7944:118: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    // InternalGrana.g:7945:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:7951:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    // InternalGrana.g:7952:6: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_2()); 
                    // InternalGrana.g:7953:6: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    // InternalGrana.g:7953:7: rule__ElkSingleEdgeSection__Group_1_0_2__0
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
                    // InternalGrana.g:7958:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    {
                    // InternalGrana.g:7958:3: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    // InternalGrana.g:7959:4: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3)");
                    }
                    // InternalGrana.g:7959:118: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    // InternalGrana.g:7960:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:7966:5: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    // InternalGrana.g:7967:6: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_3()); 
                    // InternalGrana.g:7968:6: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    // InternalGrana.g:7968:7: rule__ElkSingleEdgeSection__Group_1_0_3__0
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
    // InternalGrana.g:7981:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7985:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? )
            // InternalGrana.g:7986:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:7987:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
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
                    // InternalGrana.g:7987:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1
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
    // InternalGrana.g:7993:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:7997:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? )
            // InternalGrana.g:7998:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:7999:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
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
                    // InternalGrana.g:7999:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2
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
    // InternalGrana.g:8005:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8009:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? )
            // InternalGrana.g:8010:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:8011:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
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
                    // InternalGrana.g:8011:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3
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
    // InternalGrana.g:8017:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8021:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl )
            // InternalGrana.g:8022:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl
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
    // InternalGrana.g:8029:1: rule__ElkEdgeSection__UnorderedGroup_4_0 : ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
        	
        try {
            // InternalGrana.g:8034:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? )
            // InternalGrana.g:8035:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
            {
            // InternalGrana.g:8035:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
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
                    // InternalGrana.g:8035:2: rule__ElkEdgeSection__UnorderedGroup_4_0__0
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
    // InternalGrana.g:8043:1: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl : ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
        	
        try {
            // InternalGrana.g:8048:1: ( ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) )
            // InternalGrana.g:8049:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
            {
            // InternalGrana.g:8049:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
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
                    // InternalGrana.g:8050:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    {
                    // InternalGrana.g:8050:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    // InternalGrana.g:8051:4: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0)");
                    }
                    // InternalGrana.g:8051:112: ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    // InternalGrana.g:8052:5: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:8058:5: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    // InternalGrana.g:8059:6: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_0()); 
                    // InternalGrana.g:8060:6: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    // InternalGrana.g:8060:7: rule__ElkEdgeSection__Group_4_0_0__0
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
                    // InternalGrana.g:8065:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    {
                    // InternalGrana.g:8065:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    // InternalGrana.g:8066:4: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1)");
                    }
                    // InternalGrana.g:8066:112: ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    // InternalGrana.g:8067:5: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:8073:5: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    // InternalGrana.g:8074:6: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_1()); 
                    // InternalGrana.g:8075:6: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    // InternalGrana.g:8075:7: rule__ElkEdgeSection__Group_4_0_1__0
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
                    // InternalGrana.g:8080:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    {
                    // InternalGrana.g:8080:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    // InternalGrana.g:8081:4: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2)");
                    }
                    // InternalGrana.g:8081:112: ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    // InternalGrana.g:8082:5: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:8088:5: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    // InternalGrana.g:8089:6: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_2()); 
                    // InternalGrana.g:8090:6: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    // InternalGrana.g:8090:7: rule__ElkEdgeSection__Group_4_0_2__0
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
                    // InternalGrana.g:8095:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    {
                    // InternalGrana.g:8095:3: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    // InternalGrana.g:8096:4: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3)");
                    }
                    // InternalGrana.g:8096:112: ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    // InternalGrana.g:8097:5: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    {

                    					getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3);
                    				

                    					selected = true;
                    				
                    // InternalGrana.g:8103:5: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    // InternalGrana.g:8104:6: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_3()); 
                    // InternalGrana.g:8105:6: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    // InternalGrana.g:8105:7: rule__ElkEdgeSection__Group_4_0_3__0
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
    // InternalGrana.g:8118:1: rule__ElkEdgeSection__UnorderedGroup_4_0__0 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8122:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? )
            // InternalGrana.g:8123:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:8124:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
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
                    // InternalGrana.g:8124:2: rule__ElkEdgeSection__UnorderedGroup_4_0__1
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
    // InternalGrana.g:8130:1: rule__ElkEdgeSection__UnorderedGroup_4_0__1 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8134:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? )
            // InternalGrana.g:8135:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:8136:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
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
                    // InternalGrana.g:8136:2: rule__ElkEdgeSection__UnorderedGroup_4_0__2
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
    // InternalGrana.g:8142:1: rule__ElkEdgeSection__UnorderedGroup_4_0__2 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8146:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? )
            // InternalGrana.g:8147:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:8148:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
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
                    // InternalGrana.g:8148:2: rule__ElkEdgeSection__UnorderedGroup_4_0__3
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
    // InternalGrana.g:8154:1: rule__ElkEdgeSection__UnorderedGroup_4_0__3 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8158:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl )
            // InternalGrana.g:8159:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl
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
    // InternalGrana.g:8166:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8170:1: ( ( ruleGlobalResourceRef ) )
            // InternalGrana.g:8171:2: ( ruleGlobalResourceRef )
            {
            // InternalGrana.g:8171:2: ( ruleGlobalResourceRef )
            // InternalGrana.g:8172:3: ruleGlobalResourceRef
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
    // InternalGrana.g:8181:1: rule__Grana__GloobalOutputsAssignment_1_1 : ( ruleGlobalOutputRef ) ;
    public final void rule__Grana__GloobalOutputsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8185:1: ( ( ruleGlobalOutputRef ) )
            // InternalGrana.g:8186:2: ( ruleGlobalOutputRef )
            {
            // InternalGrana.g:8186:2: ( ruleGlobalOutputRef )
            // InternalGrana.g:8187:3: ruleGlobalOutputRef
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
    // InternalGrana.g:8196:1: rule__Grana__ParallelAssignment_2_1 : ( ( 'parallel' ) ) ;
    public final void rule__Grana__ParallelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8200:1: ( ( ( 'parallel' ) ) )
            // InternalGrana.g:8201:2: ( ( 'parallel' ) )
            {
            // InternalGrana.g:8201:2: ( ( 'parallel' ) )
            // InternalGrana.g:8202:3: ( 'parallel' )
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            // InternalGrana.g:8203:3: ( 'parallel' )
            // InternalGrana.g:8204:4: 'parallel'
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
    // InternalGrana.g:8215:1: rule__Grana__ExecuteAllAssignment_2_2_0 : ( ( 'all' ) ) ;
    public final void rule__Grana__ExecuteAllAssignment_2_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8219:1: ( ( ( 'all' ) ) )
            // InternalGrana.g:8220:2: ( ( 'all' ) )
            {
            // InternalGrana.g:8220:2: ( ( 'all' ) )
            // InternalGrana.g:8221:3: ( 'all' )
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            // InternalGrana.g:8222:3: ( 'all' )
            // InternalGrana.g:8223:4: 'all'
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
    // InternalGrana.g:8234:1: rule__Grana__ExecuteAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__Grana__ExecuteAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8238:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:8239:2: ( ( RULE_ID ) )
            {
            // InternalGrana.g:8239:2: ( ( RULE_ID ) )
            // InternalGrana.g:8240:3: ( RULE_ID )
            {
             before(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_2_1_0()); 
            // InternalGrana.g:8241:3: ( RULE_ID )
            // InternalGrana.g:8242:4: RULE_ID
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
    // InternalGrana.g:8253:1: rule__Grana__JobsAssignment_3 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8257:1: ( ( ruleJob ) )
            // InternalGrana.g:8258:2: ( ruleJob )
            {
            // InternalGrana.g:8258:2: ( ruleJob )
            // InternalGrana.g:8259:3: ruleJob
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
    // InternalGrana.g:8268:1: rule__RegularJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RegularJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8272:1: ( ( RULE_ID ) )
            // InternalGrana.g:8273:2: ( RULE_ID )
            {
            // InternalGrana.g:8273:2: ( RULE_ID )
            // InternalGrana.g:8274:3: RULE_ID
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
    // InternalGrana.g:8283:1: rule__RegularJob__LayoutBeforeAnalysisAssignment_2 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__RegularJob__LayoutBeforeAnalysisAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8287:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // InternalGrana.g:8288:2: ( ( 'layoutBeforeAnalysis' ) )
            {
            // InternalGrana.g:8288:2: ( ( 'layoutBeforeAnalysis' ) )
            // InternalGrana.g:8289:3: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            // InternalGrana.g:8290:3: ( 'layoutBeforeAnalysis' )
            // InternalGrana.g:8291:4: 'layoutBeforeAnalysis'
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
    // InternalGrana.g:8302:1: rule__RegularJob__MeasureExecutionTimeAssignment_3 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RegularJob__MeasureExecutionTimeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8306:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:8307:2: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:8307:2: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:8308:3: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            // InternalGrana.g:8309:3: ( 'measureExecutionTime' )
            // InternalGrana.g:8310:4: 'measureExecutionTime'
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
    // InternalGrana.g:8321:1: rule__RegularJob__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__RegularJob__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8325:1: ( ( ruleResource ) )
            // InternalGrana.g:8326:2: ( ruleResource )
            {
            // InternalGrana.g:8326:2: ( ruleResource )
            // InternalGrana.g:8327:3: ruleResource
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
    // InternalGrana.g:8336:1: rule__RegularJob__LayoutOptionsAssignment_7 : ( ruleLayoutConfig ) ;
    public final void rule__RegularJob__LayoutOptionsAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8340:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:8341:2: ( ruleLayoutConfig )
            {
            // InternalGrana.g:8341:2: ( ruleLayoutConfig )
            // InternalGrana.g:8342:3: ruleLayoutConfig
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
    // InternalGrana.g:8351:1: rule__RegularJob__AnalysesAssignment_9 : ( ruleAnalysis ) ;
    public final void rule__RegularJob__AnalysesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8355:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:8356:2: ( ruleAnalysis )
            {
            // InternalGrana.g:8356:2: ( ruleAnalysis )
            // InternalGrana.g:8357:3: ruleAnalysis
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
    // InternalGrana.g:8366:1: rule__RegularJob__OutputTypeAssignment_11 : ( ruleOutputType ) ;
    public final void rule__RegularJob__OutputTypeAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8370:1: ( ( ruleOutputType ) )
            // InternalGrana.g:8371:2: ( ruleOutputType )
            {
            // InternalGrana.g:8371:2: ( ruleOutputType )
            // InternalGrana.g:8372:3: ruleOutputType
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
    // InternalGrana.g:8381:1: rule__RegularJob__OutputAssignment_12 : ( ruleOutput ) ;
    public final void rule__RegularJob__OutputAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8385:1: ( ( ruleOutput ) )
            // InternalGrana.g:8386:2: ( ruleOutput )
            {
            // InternalGrana.g:8386:2: ( ruleOutput )
            // InternalGrana.g:8387:3: ruleOutput
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
    // InternalGrana.g:8396:1: rule__CompareJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__CompareJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8400:1: ( ( RULE_ID ) )
            // InternalGrana.g:8401:2: ( RULE_ID )
            {
            // InternalGrana.g:8401:2: ( RULE_ID )
            // InternalGrana.g:8402:3: RULE_ID
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
    // InternalGrana.g:8411:1: rule__CompareJob__ResourcesAssignment_3 : ( ruleResource ) ;
    public final void rule__CompareJob__ResourcesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8415:1: ( ( ruleResource ) )
            // InternalGrana.g:8416:2: ( ruleResource )
            {
            // InternalGrana.g:8416:2: ( ruleResource )
            // InternalGrana.g:8417:3: ruleResource
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
    // InternalGrana.g:8426:1: rule__CompareJob__LayoutOptionsAssignment_5 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8430:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:8431:2: ( ruleLayoutConfig )
            {
            // InternalGrana.g:8431:2: ( ruleLayoutConfig )
            // InternalGrana.g:8432:3: ruleLayoutConfig
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
    // InternalGrana.g:8441:1: rule__CompareJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8445:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:8446:2: ( ruleLayoutConfig )
            {
            // InternalGrana.g:8446:2: ( ruleLayoutConfig )
            // InternalGrana.g:8447:3: ruleLayoutConfig
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
    // InternalGrana.g:8456:1: rule__CompareJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__CompareJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8460:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:8461:2: ( ruleAnalysis )
            {
            // InternalGrana.g:8461:2: ( ruleAnalysis )
            // InternalGrana.g:8462:3: ruleAnalysis
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
    // InternalGrana.g:8471:1: rule__CompareJob__OutputTypeAssignment_10 : ( ruleOutputType ) ;
    public final void rule__CompareJob__OutputTypeAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8475:1: ( ( ruleOutputType ) )
            // InternalGrana.g:8476:2: ( ruleOutputType )
            {
            // InternalGrana.g:8476:2: ( ruleOutputType )
            // InternalGrana.g:8477:3: ruleOutputType
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
    // InternalGrana.g:8486:1: rule__CompareJob__OutputAssignment_11 : ( ruleOutput ) ;
    public final void rule__CompareJob__OutputAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8490:1: ( ( ruleOutput ) )
            // InternalGrana.g:8491:2: ( ruleOutput )
            {
            // InternalGrana.g:8491:2: ( ruleOutput )
            // InternalGrana.g:8492:3: ruleOutput
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
    // InternalGrana.g:8501:1: rule__RangeJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RangeJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8505:1: ( ( RULE_ID ) )
            // InternalGrana.g:8506:2: ( RULE_ID )
            {
            // InternalGrana.g:8506:2: ( RULE_ID )
            // InternalGrana.g:8507:3: RULE_ID
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
    // InternalGrana.g:8516:1: rule__RangeJob__MeasureExecutionTimeAssignment_2 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RangeJob__MeasureExecutionTimeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8520:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:8521:2: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:8521:2: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:8522:3: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            // InternalGrana.g:8523:3: ( 'measureExecutionTime' )
            // InternalGrana.g:8524:4: 'measureExecutionTime'
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
    // InternalGrana.g:8535:1: rule__RangeJob__ResourcesAssignment_4 : ( ruleResource ) ;
    public final void rule__RangeJob__ResourcesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8539:1: ( ( ruleResource ) )
            // InternalGrana.g:8540:2: ( ruleResource )
            {
            // InternalGrana.g:8540:2: ( ruleResource )
            // InternalGrana.g:8541:3: ruleResource
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
    // InternalGrana.g:8550:1: rule__RangeJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__RangeJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8554:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:8555:2: ( ruleLayoutConfig )
            {
            // InternalGrana.g:8555:2: ( ruleLayoutConfig )
            // InternalGrana.g:8556:3: ruleLayoutConfig
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
    // InternalGrana.g:8565:1: rule__RangeJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8569:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:8570:2: ( ruleAnalysis )
            {
            // InternalGrana.g:8570:2: ( ruleAnalysis )
            // InternalGrana.g:8571:3: ruleAnalysis
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
    // InternalGrana.g:8580:1: rule__RangeJob__RangeOptionAssignment_10 : ( ruleQualifiedId ) ;
    public final void rule__RangeJob__RangeOptionAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8584:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:8585:2: ( ruleQualifiedId )
            {
            // InternalGrana.g:8585:2: ( ruleQualifiedId )
            // InternalGrana.g:8586:3: ruleQualifiedId
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
    // InternalGrana.g:8595:1: rule__RangeJob__RangeValuesAssignment_11 : ( ruleRange ) ;
    public final void rule__RangeJob__RangeValuesAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8599:1: ( ( ruleRange ) )
            // InternalGrana.g:8600:2: ( ruleRange )
            {
            // InternalGrana.g:8600:2: ( ruleRange )
            // InternalGrana.g:8601:3: ruleRange
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
    // InternalGrana.g:8610:1: rule__RangeJob__RangeAnalysisAssignment_12_0_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysisAssignment_12_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8614:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:8615:2: ( ruleAnalysis )
            {
            // InternalGrana.g:8615:2: ( ruleAnalysis )
            // InternalGrana.g:8616:3: ruleAnalysis
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
    // InternalGrana.g:8625:1: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8629:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:8630:2: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:8630:2: ( RULE_SIGNED_INT )
            // InternalGrana.g:8631:3: RULE_SIGNED_INT
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
    // InternalGrana.g:8640:1: rule__RangeJob__RangeAnalysesAssignment_12_1_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysesAssignment_12_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8644:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:8645:2: ( ruleAnalysis )
            {
            // InternalGrana.g:8645:2: ( ruleAnalysis )
            // InternalGrana.g:8646:3: ruleAnalysis
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
    // InternalGrana.g:8655:1: rule__RangeJob__OutputTypeAssignment_14 : ( ruleOutputType ) ;
    public final void rule__RangeJob__OutputTypeAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8659:1: ( ( ruleOutputType ) )
            // InternalGrana.g:8660:2: ( ruleOutputType )
            {
            // InternalGrana.g:8660:2: ( ruleOutputType )
            // InternalGrana.g:8661:3: ruleOutputType
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
    // InternalGrana.g:8670:1: rule__RangeJob__OutputAssignment_15 : ( ruleOutput ) ;
    public final void rule__RangeJob__OutputAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8674:1: ( ( ruleOutput ) )
            // InternalGrana.g:8675:2: ( ruleOutput )
            {
            // InternalGrana.g:8675:2: ( ruleOutput )
            // InternalGrana.g:8676:3: ruleOutput
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
    // InternalGrana.g:8685:1: rule__FloatRange__ValuesAssignment_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8689:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:8690:2: ( RULE_FLOAT )
            {
            // InternalGrana.g:8690:2: ( RULE_FLOAT )
            // InternalGrana.g:8691:3: RULE_FLOAT
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
    // InternalGrana.g:8700:1: rule__FloatRange__ValuesAssignment_2_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8704:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:8705:2: ( RULE_FLOAT )
            {
            // InternalGrana.g:8705:2: ( RULE_FLOAT )
            // InternalGrana.g:8706:3: RULE_FLOAT
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
    // InternalGrana.g:8715:1: rule__IntRangeValues__ValuesAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8719:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:8720:2: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:8720:2: ( RULE_SIGNED_INT )
            // InternalGrana.g:8721:3: RULE_SIGNED_INT
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
    // InternalGrana.g:8730:1: rule__IntRangeValues__ValuesAssignment_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8734:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:8735:2: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:8735:2: ( RULE_SIGNED_INT )
            // InternalGrana.g:8736:3: RULE_SIGNED_INT
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
    // InternalGrana.g:8745:1: rule__IntRangeRange__StartAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__StartAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8749:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:8750:2: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:8750:2: ( RULE_SIGNED_INT )
            // InternalGrana.g:8751:3: RULE_SIGNED_INT
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
    // InternalGrana.g:8760:1: rule__IntRangeRange__EndAssignment_3 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__EndAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8764:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:8765:2: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:8765:2: ( RULE_SIGNED_INT )
            // InternalGrana.g:8766:3: RULE_SIGNED_INT
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
    // InternalGrana.g:8775:1: rule__EnumRange__ValuesAssignment_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__EnumRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8779:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:8780:2: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:8780:2: ( ruleQualifiedIdValue )
            // InternalGrana.g:8781:3: ruleQualifiedIdValue
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
    // InternalGrana.g:8790:1: rule__EnumRange__ValuesAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__EnumRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8794:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:8795:2: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:8795:2: ( ruleQualifiedIdValue )
            // InternalGrana.g:8796:3: ruleQualifiedIdValue
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
    // InternalGrana.g:8805:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8809:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:8810:2: ( ( RULE_ID ) )
            {
            // InternalGrana.g:8810:2: ( ( RULE_ID ) )
            // InternalGrana.g:8811:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // InternalGrana.g:8812:3: ( RULE_ID )
            // InternalGrana.g:8813:4: RULE_ID
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
    // InternalGrana.g:8824:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8828:1: ( ( RULE_ID ) )
            // InternalGrana.g:8829:2: ( RULE_ID )
            {
            // InternalGrana.g:8829:2: ( RULE_ID )
            // InternalGrana.g:8830:3: RULE_ID
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
    // InternalGrana.g:8839:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8843:1: ( ( ruleLocalResource ) )
            // InternalGrana.g:8844:2: ( ruleLocalResource )
            {
            // InternalGrana.g:8844:2: ( ruleLocalResource )
            // InternalGrana.g:8845:3: ruleLocalResource
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
    // InternalGrana.g:8854:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8858:1: ( ( RULE_STRING ) )
            // InternalGrana.g:8859:2: ( RULE_STRING )
            {
            // InternalGrana.g:8859:2: ( RULE_STRING )
            // InternalGrana.g:8860:3: RULE_STRING
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
    // InternalGrana.g:8869:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8873:1: ( ( RULE_STRING ) )
            // InternalGrana.g:8874:2: ( RULE_STRING )
            {
            // InternalGrana.g:8874:2: ( RULE_STRING )
            // InternalGrana.g:8875:3: RULE_STRING
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
    // InternalGrana.g:8884:1: rule__LocalResource__RecurseAssignment_2 : ( ( 'recurse' ) ) ;
    public final void rule__LocalResource__RecurseAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8888:1: ( ( ( 'recurse' ) ) )
            // InternalGrana.g:8889:2: ( ( 'recurse' ) )
            {
            // InternalGrana.g:8889:2: ( ( 'recurse' ) )
            // InternalGrana.g:8890:3: ( 'recurse' )
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0()); 
            // InternalGrana.g:8891:3: ( 'recurse' )
            // InternalGrana.g:8892:4: 'recurse'
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
    // InternalGrana.g:8903:1: rule__GlobalOutputRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalOutputRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8907:1: ( ( RULE_ID ) )
            // InternalGrana.g:8908:2: ( RULE_ID )
            {
            // InternalGrana.g:8908:2: ( RULE_ID )
            // InternalGrana.g:8909:3: RULE_ID
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
    // InternalGrana.g:8918:1: rule__GlobalOutputRef__OutputAssignment_1 : ( ruleLocalOutput ) ;
    public final void rule__GlobalOutputRef__OutputAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8922:1: ( ( ruleLocalOutput ) )
            // InternalGrana.g:8923:2: ( ruleLocalOutput )
            {
            // InternalGrana.g:8923:2: ( ruleLocalOutput )
            // InternalGrana.g:8924:3: ruleLocalOutput
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
    // InternalGrana.g:8933:1: rule__OutputReference__OutputRefAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__OutputReference__OutputRefAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8937:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:8938:2: ( ( RULE_ID ) )
            {
            // InternalGrana.g:8938:2: ( ( RULE_ID ) )
            // InternalGrana.g:8939:3: ( RULE_ID )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            // InternalGrana.g:8940:3: ( RULE_ID )
            // InternalGrana.g:8941:4: RULE_ID
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
    // InternalGrana.g:8952:1: rule__LocalOutput__PathAssignment : ( RULE_STRING ) ;
    public final void rule__LocalOutput__PathAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8956:1: ( ( RULE_STRING ) )
            // InternalGrana.g:8957:2: ( RULE_STRING )
            {
            // InternalGrana.g:8957:2: ( RULE_STRING )
            // InternalGrana.g:8958:3: RULE_STRING
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
    // InternalGrana.g:8967:1: rule__Analysis__NameAssignment : ( ruleQualifiedId ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8971:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:8972:2: ( ruleQualifiedId )
            {
            // InternalGrana.g:8972:2: ( ruleQualifiedId )
            // InternalGrana.g:8973:3: ruleQualifiedId
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
    // InternalGrana.g:8982:1: rule__LayoutConfig__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__LayoutConfig__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:8986:1: ( ( RULE_ID ) )
            // InternalGrana.g:8987:2: ( RULE_ID )
            {
            // InternalGrana.g:8987:2: ( RULE_ID )
            // InternalGrana.g:8988:3: RULE_ID
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
    // InternalGrana.g:8997:1: rule__LayoutConfig__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__LayoutConfig__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9001:1: ( ( ruleProperty ) )
            // InternalGrana.g:9002:2: ( ruleProperty )
            {
            // InternalGrana.g:9002:2: ( ruleProperty )
            // InternalGrana.g:9003:3: ruleProperty
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
    // InternalGrana.g:9012:1: rule__ElkNode__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkNode__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9016:1: ( ( RULE_ID ) )
            // InternalGrana.g:9017:2: ( RULE_ID )
            {
            // InternalGrana.g:9017:2: ( RULE_ID )
            // InternalGrana.g:9018:3: RULE_ID
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
    // InternalGrana.g:9027:1: rule__ElkNode__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkNode__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9031:1: ( ( ruleProperty ) )
            // InternalGrana.g:9032:2: ( ruleProperty )
            {
            // InternalGrana.g:9032:2: ( ruleProperty )
            // InternalGrana.g:9033:3: ruleProperty
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
    // InternalGrana.g:9042:1: rule__ElkNode__LabelsAssignment_2_3_0 : ( ruleElkLabel ) ;
    public final void rule__ElkNode__LabelsAssignment_2_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9046:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:9047:2: ( ruleElkLabel )
            {
            // InternalGrana.g:9047:2: ( ruleElkLabel )
            // InternalGrana.g:9048:3: ruleElkLabel
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
    // InternalGrana.g:9057:1: rule__ElkNode__PortsAssignment_2_3_1 : ( ruleElkPort ) ;
    public final void rule__ElkNode__PortsAssignment_2_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9061:1: ( ( ruleElkPort ) )
            // InternalGrana.g:9062:2: ( ruleElkPort )
            {
            // InternalGrana.g:9062:2: ( ruleElkPort )
            // InternalGrana.g:9063:3: ruleElkPort
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
    // InternalGrana.g:9072:1: rule__ElkNode__ChildrenAssignment_2_3_2 : ( ruleElkNode ) ;
    public final void rule__ElkNode__ChildrenAssignment_2_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9076:1: ( ( ruleElkNode ) )
            // InternalGrana.g:9077:2: ( ruleElkNode )
            {
            // InternalGrana.g:9077:2: ( ruleElkNode )
            // InternalGrana.g:9078:3: ruleElkNode
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
    // InternalGrana.g:9087:1: rule__ElkNode__ContainedEdgesAssignment_2_3_3 : ( ruleElkEdge ) ;
    public final void rule__ElkNode__ContainedEdgesAssignment_2_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9091:1: ( ( ruleElkEdge ) )
            // InternalGrana.g:9092:2: ( ruleElkEdge )
            {
            // InternalGrana.g:9092:2: ( ruleElkEdge )
            // InternalGrana.g:9093:3: ruleElkEdge
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
    // InternalGrana.g:9102:1: rule__ElkLabel__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkLabel__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9106:1: ( ( RULE_ID ) )
            // InternalGrana.g:9107:2: ( RULE_ID )
            {
            // InternalGrana.g:9107:2: ( RULE_ID )
            // InternalGrana.g:9108:3: RULE_ID
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
    // InternalGrana.g:9117:1: rule__ElkLabel__TextAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ElkLabel__TextAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9121:1: ( ( RULE_STRING ) )
            // InternalGrana.g:9122:2: ( RULE_STRING )
            {
            // InternalGrana.g:9122:2: ( RULE_STRING )
            // InternalGrana.g:9123:3: RULE_STRING
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
    // InternalGrana.g:9132:1: rule__ElkLabel__PropertiesAssignment_3_2 : ( ruleProperty ) ;
    public final void rule__ElkLabel__PropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9136:1: ( ( ruleProperty ) )
            // InternalGrana.g:9137:2: ( ruleProperty )
            {
            // InternalGrana.g:9137:2: ( ruleProperty )
            // InternalGrana.g:9138:3: ruleProperty
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
    // InternalGrana.g:9147:1: rule__ElkLabel__LabelsAssignment_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkLabel__LabelsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9151:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:9152:2: ( ruleElkLabel )
            {
            // InternalGrana.g:9152:2: ( ruleElkLabel )
            // InternalGrana.g:9153:3: ruleElkLabel
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
    // InternalGrana.g:9162:1: rule__ElkPort__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkPort__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9166:1: ( ( RULE_ID ) )
            // InternalGrana.g:9167:2: ( RULE_ID )
            {
            // InternalGrana.g:9167:2: ( RULE_ID )
            // InternalGrana.g:9168:3: RULE_ID
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
    // InternalGrana.g:9177:1: rule__ElkPort__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkPort__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9181:1: ( ( ruleProperty ) )
            // InternalGrana.g:9182:2: ( ruleProperty )
            {
            // InternalGrana.g:9182:2: ( ruleProperty )
            // InternalGrana.g:9183:3: ruleProperty
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
    // InternalGrana.g:9192:1: rule__ElkPort__LabelsAssignment_2_3 : ( ruleElkLabel ) ;
    public final void rule__ElkPort__LabelsAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9196:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:9197:2: ( ruleElkLabel )
            {
            // InternalGrana.g:9197:2: ( ruleElkLabel )
            // InternalGrana.g:9198:3: ruleElkLabel
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
    // InternalGrana.g:9207:1: rule__ShapeLayout__XAssignment_2_0_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__XAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9211:1: ( ( ruleNumber ) )
            // InternalGrana.g:9212:2: ( ruleNumber )
            {
            // InternalGrana.g:9212:2: ( ruleNumber )
            // InternalGrana.g:9213:3: ruleNumber
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
    // InternalGrana.g:9222:1: rule__ShapeLayout__YAssignment_2_0_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__YAssignment_2_0_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9226:1: ( ( ruleNumber ) )
            // InternalGrana.g:9227:2: ( ruleNumber )
            {
            // InternalGrana.g:9227:2: ( ruleNumber )
            // InternalGrana.g:9228:3: ruleNumber
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
    // InternalGrana.g:9237:1: rule__ShapeLayout__WidthAssignment_2_1_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__WidthAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9241:1: ( ( ruleNumber ) )
            // InternalGrana.g:9242:2: ( ruleNumber )
            {
            // InternalGrana.g:9242:2: ( ruleNumber )
            // InternalGrana.g:9243:3: ruleNumber
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
    // InternalGrana.g:9252:1: rule__ShapeLayout__HeightAssignment_2_1_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__HeightAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9256:1: ( ( ruleNumber ) )
            // InternalGrana.g:9257:2: ( ruleNumber )
            {
            // InternalGrana.g:9257:2: ( ruleNumber )
            // InternalGrana.g:9258:3: ruleNumber
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
    // InternalGrana.g:9267:1: rule__ElkEdge__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkEdge__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9271:1: ( ( RULE_ID ) )
            // InternalGrana.g:9272:2: ( RULE_ID )
            {
            // InternalGrana.g:9272:2: ( RULE_ID )
            // InternalGrana.g:9273:3: RULE_ID
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
    // InternalGrana.g:9282:1: rule__ElkEdge__SourcesAssignment_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9286:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9287:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9287:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9288:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_2_0()); 
            // InternalGrana.g:9289:3: ( ruleQualifiedId )
            // InternalGrana.g:9290:4: ruleQualifiedId
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
    // InternalGrana.g:9301:1: rule__ElkEdge__SourcesAssignment_3_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9305:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9306:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9306:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9307:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_3_1_0()); 
            // InternalGrana.g:9308:3: ( ruleQualifiedId )
            // InternalGrana.g:9309:4: ruleQualifiedId
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
    // InternalGrana.g:9320:1: rule__ElkEdge__TargetsAssignment_5 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9324:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9325:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9325:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9326:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_5_0()); 
            // InternalGrana.g:9327:3: ( ruleQualifiedId )
            // InternalGrana.g:9328:4: ruleQualifiedId
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
    // InternalGrana.g:9339:1: rule__ElkEdge__TargetsAssignment_6_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9343:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9344:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9344:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9345:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_6_1_0()); 
            // InternalGrana.g:9346:3: ( ruleQualifiedId )
            // InternalGrana.g:9347:4: ruleQualifiedId
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
    // InternalGrana.g:9358:1: rule__ElkEdge__PropertiesAssignment_7_2 : ( ruleProperty ) ;
    public final void rule__ElkEdge__PropertiesAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9362:1: ( ( ruleProperty ) )
            // InternalGrana.g:9363:2: ( ruleProperty )
            {
            // InternalGrana.g:9363:2: ( ruleProperty )
            // InternalGrana.g:9364:3: ruleProperty
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
    // InternalGrana.g:9373:1: rule__ElkEdge__LabelsAssignment_7_3 : ( ruleElkLabel ) ;
    public final void rule__ElkEdge__LabelsAssignment_7_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9377:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:9378:2: ( ruleElkLabel )
            {
            // InternalGrana.g:9378:2: ( ruleElkLabel )
            // InternalGrana.g:9379:3: ruleElkLabel
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
    // InternalGrana.g:9388:1: rule__EdgeLayout__SectionsAssignment_2_0 : ( ruleElkSingleEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9392:1: ( ( ruleElkSingleEdgeSection ) )
            // InternalGrana.g:9393:2: ( ruleElkSingleEdgeSection )
            {
            // InternalGrana.g:9393:2: ( ruleElkSingleEdgeSection )
            // InternalGrana.g:9394:3: ruleElkSingleEdgeSection
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
    // InternalGrana.g:9403:1: rule__EdgeLayout__SectionsAssignment_2_1 : ( ruleElkEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9407:1: ( ( ruleElkEdgeSection ) )
            // InternalGrana.g:9408:2: ( ruleElkEdgeSection )
            {
            // InternalGrana.g:9408:2: ( ruleElkEdgeSection )
            // InternalGrana.g:9409:3: ruleElkEdgeSection
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
    // InternalGrana.g:9418:1: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9422:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9423:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9423:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9424:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0()); 
            // InternalGrana.g:9425:3: ( ruleQualifiedId )
            // InternalGrana.g:9426:4: ruleQualifiedId
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
    // InternalGrana.g:9437:1: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9441:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9442:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9442:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9443:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0()); 
            // InternalGrana.g:9444:3: ( ruleQualifiedId )
            // InternalGrana.g:9445:4: ruleQualifiedId
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
    // InternalGrana.g:9456:1: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9460:1: ( ( ruleNumber ) )
            // InternalGrana.g:9461:2: ( ruleNumber )
            {
            // InternalGrana.g:9461:2: ( ruleNumber )
            // InternalGrana.g:9462:3: ruleNumber
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
    // InternalGrana.g:9471:1: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9475:1: ( ( ruleNumber ) )
            // InternalGrana.g:9476:2: ( ruleNumber )
            {
            // InternalGrana.g:9476:2: ( ruleNumber )
            // InternalGrana.g:9477:3: ruleNumber
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
    // InternalGrana.g:9486:1: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9490:1: ( ( ruleNumber ) )
            // InternalGrana.g:9491:2: ( ruleNumber )
            {
            // InternalGrana.g:9491:2: ( ruleNumber )
            // InternalGrana.g:9492:3: ruleNumber
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
    // InternalGrana.g:9501:1: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9505:1: ( ( ruleNumber ) )
            // InternalGrana.g:9506:2: ( ruleNumber )
            {
            // InternalGrana.g:9506:2: ( ruleNumber )
            // InternalGrana.g:9507:3: ruleNumber
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
    // InternalGrana.g:9516:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9520:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:9521:2: ( ruleElkBendPoint )
            {
            // InternalGrana.g:9521:2: ( ruleElkBendPoint )
            // InternalGrana.g:9522:3: ruleElkBendPoint
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
    // InternalGrana.g:9531:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9535:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:9536:2: ( ruleElkBendPoint )
            {
            // InternalGrana.g:9536:2: ( ruleElkBendPoint )
            // InternalGrana.g:9537:3: ruleElkBendPoint
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
    // InternalGrana.g:9546:1: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 : ( ruleProperty ) ;
    public final void rule__ElkSingleEdgeSection__PropertiesAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9550:1: ( ( ruleProperty ) )
            // InternalGrana.g:9551:2: ( ruleProperty )
            {
            // InternalGrana.g:9551:2: ( ruleProperty )
            // InternalGrana.g:9552:3: ruleProperty
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
    // InternalGrana.g:9561:1: rule__ElkEdgeSection__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkEdgeSection__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9565:1: ( ( RULE_ID ) )
            // InternalGrana.g:9566:2: ( RULE_ID )
            {
            // InternalGrana.g:9566:2: ( RULE_ID )
            // InternalGrana.g:9567:3: RULE_ID
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
    // InternalGrana.g:9576:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9580:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:9581:2: ( ( RULE_ID ) )
            {
            // InternalGrana.g:9581:2: ( ( RULE_ID ) )
            // InternalGrana.g:9582:3: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
            // InternalGrana.g:9583:3: ( RULE_ID )
            // InternalGrana.g:9584:4: RULE_ID
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
    // InternalGrana.g:9595:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9599:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:9600:2: ( ( RULE_ID ) )
            {
            // InternalGrana.g:9600:2: ( ( RULE_ID ) )
            // InternalGrana.g:9601:3: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
            // InternalGrana.g:9602:3: ( RULE_ID )
            // InternalGrana.g:9603:4: RULE_ID
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
    // InternalGrana.g:9614:1: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9618:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9619:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9619:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9620:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0()); 
            // InternalGrana.g:9621:3: ( ruleQualifiedId )
            // InternalGrana.g:9622:4: ruleQualifiedId
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
    // InternalGrana.g:9633:1: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9637:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:9638:2: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:9638:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9639:3: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0()); 
            // InternalGrana.g:9640:3: ( ruleQualifiedId )
            // InternalGrana.g:9641:4: ruleQualifiedId
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
    // InternalGrana.g:9652:1: rule__ElkEdgeSection__StartXAssignment_4_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartXAssignment_4_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9656:1: ( ( ruleNumber ) )
            // InternalGrana.g:9657:2: ( ruleNumber )
            {
            // InternalGrana.g:9657:2: ( ruleNumber )
            // InternalGrana.g:9658:3: ruleNumber
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
    // InternalGrana.g:9667:1: rule__ElkEdgeSection__StartYAssignment_4_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartYAssignment_4_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9671:1: ( ( ruleNumber ) )
            // InternalGrana.g:9672:2: ( ruleNumber )
            {
            // InternalGrana.g:9672:2: ( ruleNumber )
            // InternalGrana.g:9673:3: ruleNumber
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
    // InternalGrana.g:9682:1: rule__ElkEdgeSection__EndXAssignment_4_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndXAssignment_4_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9686:1: ( ( ruleNumber ) )
            // InternalGrana.g:9687:2: ( ruleNumber )
            {
            // InternalGrana.g:9687:2: ( ruleNumber )
            // InternalGrana.g:9688:3: ruleNumber
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
    // InternalGrana.g:9697:1: rule__ElkEdgeSection__EndYAssignment_4_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndYAssignment_4_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9701:1: ( ( ruleNumber ) )
            // InternalGrana.g:9702:2: ( ruleNumber )
            {
            // InternalGrana.g:9702:2: ( ruleNumber )
            // InternalGrana.g:9703:3: ruleNumber
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
    // InternalGrana.g:9712:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9716:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:9717:2: ( ruleElkBendPoint )
            {
            // InternalGrana.g:9717:2: ( ruleElkBendPoint )
            // InternalGrana.g:9718:3: ruleElkBendPoint
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
    // InternalGrana.g:9727:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9731:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:9732:2: ( ruleElkBendPoint )
            {
            // InternalGrana.g:9732:2: ( ruleElkBendPoint )
            // InternalGrana.g:9733:3: ruleElkBendPoint
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
    // InternalGrana.g:9742:1: rule__ElkEdgeSection__PropertiesAssignment_4_2 : ( ruleProperty ) ;
    public final void rule__ElkEdgeSection__PropertiesAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9746:1: ( ( ruleProperty ) )
            // InternalGrana.g:9747:2: ( ruleProperty )
            {
            // InternalGrana.g:9747:2: ( ruleProperty )
            // InternalGrana.g:9748:3: ruleProperty
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
    // InternalGrana.g:9757:1: rule__ElkBendPoint__XAssignment_0 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9761:1: ( ( ruleNumber ) )
            // InternalGrana.g:9762:2: ( ruleNumber )
            {
            // InternalGrana.g:9762:2: ( ruleNumber )
            // InternalGrana.g:9763:3: ruleNumber
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
    // InternalGrana.g:9772:1: rule__ElkBendPoint__YAssignment_2 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9776:1: ( ( ruleNumber ) )
            // InternalGrana.g:9777:2: ( ruleNumber )
            {
            // InternalGrana.g:9777:2: ( ruleNumber )
            // InternalGrana.g:9778:3: ruleNumber
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
    // InternalGrana.g:9787:1: rule__Property__KeyAssignment_0 : ( rulePropertyKey ) ;
    public final void rule__Property__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9791:1: ( ( rulePropertyKey ) )
            // InternalGrana.g:9792:2: ( rulePropertyKey )
            {
            // InternalGrana.g:9792:2: ( rulePropertyKey )
            // InternalGrana.g:9793:3: rulePropertyKey
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
    // InternalGrana.g:9802:1: rule__Property__ValueAssignment_2_0 : ( ruleStringValue ) ;
    public final void rule__Property__ValueAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9806:1: ( ( ruleStringValue ) )
            // InternalGrana.g:9807:2: ( ruleStringValue )
            {
            // InternalGrana.g:9807:2: ( ruleStringValue )
            // InternalGrana.g:9808:3: ruleStringValue
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
    // InternalGrana.g:9817:1: rule__Property__ValueAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__Property__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9821:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:9822:2: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:9822:2: ( ruleQualifiedIdValue )
            // InternalGrana.g:9823:3: ruleQualifiedIdValue
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
    // InternalGrana.g:9832:1: rule__Property__ValueAssignment_2_2 : ( ruleNumberValue ) ;
    public final void rule__Property__ValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9836:1: ( ( ruleNumberValue ) )
            // InternalGrana.g:9837:2: ( ruleNumberValue )
            {
            // InternalGrana.g:9837:2: ( ruleNumberValue )
            // InternalGrana.g:9838:3: ruleNumberValue
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
    // InternalGrana.g:9847:1: rule__Property__ValueAssignment_2_3 : ( ruleBooleanValue ) ;
    public final void rule__Property__ValueAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalGrana.g:9851:1: ( ( ruleBooleanValue ) )
            // InternalGrana.g:9852:2: ( ruleBooleanValue )
            {
            // InternalGrana.g:9852:2: ( ruleBooleanValue )
            // InternalGrana.g:9853:3: ruleBooleanValue
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
