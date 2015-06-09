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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BOOLEAN", "RULE_STRING", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'globalOutputs'", "'execute'", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'", "'all'", "'layoutBeforeAnalysis'", "'measureExecutionTime'"
    };
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_ID=8;
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
            pushFollow(FOLLOW_rule__Job__Group__0_in_ruleJob154);
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
            pushFollow(FOLLOW_ruleResource_in_entryRuleResource181);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getResourceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResource188); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:125:1: ruleResource : ( ( rule__Resource__Alternatives ) ) ;
    public final void ruleResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:129:2: ( ( ( rule__Resource__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:130:1: ( ( rule__Resource__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:130:1: ( ( rule__Resource__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:131:1: ( rule__Resource__Alternatives )
            {
             before(grammarAccess.getResourceAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:132:1: ( rule__Resource__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:132:2: rule__Resource__Alternatives
            {
            pushFollow(FOLLOW_rule__Resource__Alternatives_in_ruleResource214);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:144:1: entryRuleResourceReference : ruleResourceReference EOF ;
    public final void entryRuleResourceReference() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:145:1: ( ruleResourceReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:146:1: ruleResourceReference EOF
            {
             before(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_ruleResourceReference_in_entryRuleResourceReference241);
            ruleResourceReference();

            state._fsp--;

             after(grammarAccess.getResourceReferenceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResourceReference248); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:153:1: ruleResourceReference : ( ( rule__ResourceReference__Group__0 ) ) ;
    public final void ruleResourceReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:157:2: ( ( ( rule__ResourceReference__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:158:1: ( ( rule__ResourceReference__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:158:1: ( ( rule__ResourceReference__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:159:1: ( rule__ResourceReference__Group__0 )
            {
             before(grammarAccess.getResourceReferenceAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:160:1: ( rule__ResourceReference__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:160:2: rule__ResourceReference__Group__0
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__0_in_ruleResourceReference274);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:172:1: entryRuleGlobalResourceRef : ruleGlobalResourceRef EOF ;
    public final void entryRuleGlobalResourceRef() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:173:1: ( ruleGlobalResourceRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:174:1: ruleGlobalResourceRef EOF
            {
             before(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef301);
            ruleGlobalResourceRef();

            state._fsp--;

             after(grammarAccess.getGlobalResourceRefRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalResourceRef308); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:181:1: ruleGlobalResourceRef : ( ( rule__GlobalResourceRef__Group__0 ) ) ;
    public final void ruleGlobalResourceRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:185:2: ( ( ( rule__GlobalResourceRef__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:186:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:186:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:187:1: ( rule__GlobalResourceRef__Group__0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:188:1: ( rule__GlobalResourceRef__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:188:2: rule__GlobalResourceRef__Group__0
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__0_in_ruleGlobalResourceRef334);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:200:1: entryRuleLocalResource : ruleLocalResource EOF ;
    public final void entryRuleLocalResource() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:201:1: ( ruleLocalResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:202:1: ruleLocalResource EOF
            {
             before(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_ruleLocalResource_in_entryRuleLocalResource361);
            ruleLocalResource();

            state._fsp--;

             after(grammarAccess.getLocalResourceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalResource368); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:209:1: ruleLocalResource : ( ( rule__LocalResource__Group__0 ) ) ;
    public final void ruleLocalResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:213:2: ( ( ( rule__LocalResource__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:214:1: ( ( rule__LocalResource__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:214:1: ( ( rule__LocalResource__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:215:1: ( rule__LocalResource__Group__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:216:1: ( rule__LocalResource__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:216:2: rule__LocalResource__Group__0
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__0_in_ruleLocalResource394);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:228:1: entryRuleOutput : ruleOutput EOF ;
    public final void entryRuleOutput() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:229:1: ( ruleOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:230:1: ruleOutput EOF
            {
             before(grammarAccess.getOutputRule()); 
            pushFollow(FOLLOW_ruleOutput_in_entryRuleOutput421);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getOutputRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutput428); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:237:1: ruleOutput : ( ( rule__Output__Alternatives ) ) ;
    public final void ruleOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:241:2: ( ( ( rule__Output__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__Output__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__Output__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:243:1: ( rule__Output__Alternatives )
            {
             before(grammarAccess.getOutputAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:1: ( rule__Output__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:2: rule__Output__Alternatives
            {
            pushFollow(FOLLOW_rule__Output__Alternatives_in_ruleOutput454);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:256:1: entryRuleGlobalOutputRef : ruleGlobalOutputRef EOF ;
    public final void entryRuleGlobalOutputRef() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:257:1: ( ruleGlobalOutputRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:258:1: ruleGlobalOutputRef EOF
            {
             before(grammarAccess.getGlobalOutputRefRule()); 
            pushFollow(FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef481);
            ruleGlobalOutputRef();

            state._fsp--;

             after(grammarAccess.getGlobalOutputRefRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalOutputRef488); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:265:1: ruleGlobalOutputRef : ( ( rule__GlobalOutputRef__Group__0 ) ) ;
    public final void ruleGlobalOutputRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:269:2: ( ( ( rule__GlobalOutputRef__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:271:1: ( rule__GlobalOutputRef__Group__0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:1: ( rule__GlobalOutputRef__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:2: rule__GlobalOutputRef__Group__0
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__0_in_ruleGlobalOutputRef514);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:284:1: entryRuleOutputReference : ruleOutputReference EOF ;
    public final void entryRuleOutputReference() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:285:1: ( ruleOutputReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:286:1: ruleOutputReference EOF
            {
             before(grammarAccess.getOutputReferenceRule()); 
            pushFollow(FOLLOW_ruleOutputReference_in_entryRuleOutputReference541);
            ruleOutputReference();

            state._fsp--;

             after(grammarAccess.getOutputReferenceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutputReference548); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:293:1: ruleOutputReference : ( ( rule__OutputReference__Group__0 ) ) ;
    public final void ruleOutputReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:297:2: ( ( ( rule__OutputReference__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__OutputReference__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__OutputReference__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:299:1: ( rule__OutputReference__Group__0 )
            {
             before(grammarAccess.getOutputReferenceAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:1: ( rule__OutputReference__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:2: rule__OutputReference__Group__0
            {
            pushFollow(FOLLOW_rule__OutputReference__Group__0_in_ruleOutputReference574);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:312:1: entryRuleLocalOutput : ruleLocalOutput EOF ;
    public final void entryRuleLocalOutput() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:313:1: ( ruleLocalOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:314:1: ruleLocalOutput EOF
            {
             before(grammarAccess.getLocalOutputRule()); 
            pushFollow(FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput601);
            ruleLocalOutput();

            state._fsp--;

             after(grammarAccess.getLocalOutputRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalOutput608); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:321:1: ruleLocalOutput : ( ( rule__LocalOutput__PathAssignment ) ) ;
    public final void ruleLocalOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:325:2: ( ( ( rule__LocalOutput__PathAssignment ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:326:1: ( ( rule__LocalOutput__PathAssignment ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:326:1: ( ( rule__LocalOutput__PathAssignment ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:327:1: ( rule__LocalOutput__PathAssignment )
            {
             before(grammarAccess.getLocalOutputAccess().getPathAssignment()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:328:1: ( rule__LocalOutput__PathAssignment )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:328:2: rule__LocalOutput__PathAssignment
            {
            pushFollow(FOLLOW_rule__LocalOutput__PathAssignment_in_ruleLocalOutput634);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:340:1: entryRuleAnalysis : ruleAnalysis EOF ;
    public final void entryRuleAnalysis() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:341:1: ( ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:342:1: ruleAnalysis EOF
            {
             before(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis661);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getAnalysisRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis668); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:349:1: ruleAnalysis : ( ( rule__Analysis__NameAssignment ) ) ;
    public final void ruleAnalysis() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:353:2: ( ( ( rule__Analysis__NameAssignment ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:354:1: ( ( rule__Analysis__NameAssignment ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:354:1: ( ( rule__Analysis__NameAssignment ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:355:1: ( rule__Analysis__NameAssignment )
            {
             before(grammarAccess.getAnalysisAccess().getNameAssignment()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:356:1: ( rule__Analysis__NameAssignment )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:356:2: rule__Analysis__NameAssignment
            {
            pushFollow(FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis694);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:368:1: entryRuleKIdentifier : ruleKIdentifier EOF ;
    public final void entryRuleKIdentifier() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:369:1: ( ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:370:1: ruleKIdentifier EOF
            {
             before(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier721);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKIdentifierRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier728); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:377:1: ruleKIdentifier : ( ( rule__KIdentifier__Group__0 ) ) ;
    public final void ruleKIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:381:2: ( ( ( rule__KIdentifier__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:382:1: ( ( rule__KIdentifier__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:382:1: ( ( rule__KIdentifier__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:383:1: ( rule__KIdentifier__Group__0 )
            {
             before(grammarAccess.getKIdentifierAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:384:1: ( rule__KIdentifier__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:384:2: rule__KIdentifier__Group__0
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier754);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:396:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:397:1: ( rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:398:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry781);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry788); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:405:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:409:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:410:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:410:1: ( ( rule__PersistentEntry__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:411:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:412:1: ( rule__PersistentEntry__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:412:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry814);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:424:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:425:1: ( ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:426:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID841);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID848); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:433:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:437:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:438:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:438:1: ( ( rule__QualifiedID__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:439:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:440:1: ( rule__QualifiedID__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:440:2: rule__QualifiedID__Group__0
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID874);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:452:1: entryRulePropertyValue : rulePropertyValue EOF ;
    public final void entryRulePropertyValue() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:453:1: ( rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:454:1: rulePropertyValue EOF
            {
             before(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue901);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPropertyValueRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue908); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:461:1: rulePropertyValue : ( ( rule__PropertyValue__Alternatives ) ) ;
    public final void rulePropertyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:465:2: ( ( ( rule__PropertyValue__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:466:1: ( ( rule__PropertyValue__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:466:1: ( ( rule__PropertyValue__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:467:1: ( rule__PropertyValue__Alternatives )
            {
             before(grammarAccess.getPropertyValueAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:468:1: ( rule__PropertyValue__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:468:2: rule__PropertyValue__Alternatives
            {
            pushFollow(FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue934);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:480:1: entryRuleFloat : ruleFloat EOF ;
    public final void entryRuleFloat() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:481:1: ( ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:482:1: ruleFloat EOF
            {
             before(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat961);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat968); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:489:1: ruleFloat : ( ( rule__Float__Alternatives ) ) ;
    public final void ruleFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:493:2: ( ( ( rule__Float__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:494:1: ( ( rule__Float__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:494:1: ( ( rule__Float__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:495:1: ( rule__Float__Alternatives )
            {
             before(grammarAccess.getFloatAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:496:1: ( rule__Float__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:496:2: rule__Float__Alternatives
            {
            pushFollow(FOLLOW_rule__Float__Alternatives_in_ruleFloat994);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:508:1: rule__Grana__Alternatives_2_1 : ( ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) ) );
    public final void rule__Grana__Alternatives_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:512:1: ( ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==26) ) {
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:513:1: ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:513:1: ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:514:1: ( rule__Grana__ExecuteAllAssignment_2_1_0 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_1_0()); 
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:515:1: ( rule__Grana__ExecuteAllAssignment_2_1_0 )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:515:2: rule__Grana__ExecuteAllAssignment_2_1_0
                    {
                    pushFollow(FOLLOW_rule__Grana__ExecuteAllAssignment_2_1_0_in_rule__Grana__Alternatives_2_11030);
                    rule__Grana__ExecuteAllAssignment_2_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:519:6: ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:519:6: ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:520:1: ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:520:1: ( ( rule__Grana__ExecuteAssignment_2_1_1 ) )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:521:1: ( rule__Grana__ExecuteAssignment_2_1_1 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_1_1()); 
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:522:1: ( rule__Grana__ExecuteAssignment_2_1_1 )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:522:2: rule__Grana__ExecuteAssignment_2_1_1
                    {
                    pushFollow(FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11050);
                    rule__Grana__ExecuteAssignment_2_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_1_1()); 

                    }

                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:525:1: ( ( rule__Grana__ExecuteAssignment_2_1_1 )* )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:526:1: ( rule__Grana__ExecuteAssignment_2_1_1 )*
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_1_1()); 
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:527:1: ( rule__Grana__ExecuteAssignment_2_1_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:527:2: rule__Grana__ExecuteAssignment_2_1_1
                    	    {
                    	    pushFollow(FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11062);
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


    // $ANTLR start "rule__Resource__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:537:1: rule__Resource__Alternatives : ( ( ruleResourceReference ) | ( ruleLocalResource ) );
    public final void rule__Resource__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:541:1: ( ( ruleResourceReference ) | ( ruleLocalResource ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==20) ) {
                alt3=1;
            }
            else if ( (LA3_0==RULE_STRING) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:542:1: ( ruleResourceReference )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:542:1: ( ruleResourceReference )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:543:1: ruleResourceReference
                    {
                     before(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleResourceReference_in_rule__Resource__Alternatives1098);
                    ruleResourceReference();

                    state._fsp--;

                     after(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:548:6: ( ruleLocalResource )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:548:6: ( ruleLocalResource )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:549:1: ruleLocalResource
                    {
                     before(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLocalResource_in_rule__Resource__Alternatives1115);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:559:1: rule__Output__Alternatives : ( ( ruleOutputReference ) | ( ruleLocalOutput ) );
    public final void rule__Output__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:563:1: ( ( ruleOutputReference ) | ( ruleLocalOutput ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==20) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_STRING) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:564:1: ( ruleOutputReference )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:564:1: ( ruleOutputReference )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:565:1: ruleOutputReference
                    {
                     before(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleOutputReference_in_rule__Output__Alternatives1147);
                    ruleOutputReference();

                    state._fsp--;

                     after(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:570:6: ( ruleLocalOutput )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:570:6: ( ruleLocalOutput )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:571:1: ruleLocalOutput
                    {
                     before(grammarAccess.getOutputAccess().getLocalOutputParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLocalOutput_in_rule__Output__Alternatives1164);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:581:1: rule__PropertyValue__Alternatives : ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) );
    public final void rule__PropertyValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:585:1: ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) )
            int alt5=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt5=1;
                }
                break;
            case RULE_STRING:
                {
                alt5=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt5=3;
                }
                break;
            case RULE_ID:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:586:1: ( RULE_BOOLEAN )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:586:1: ( RULE_BOOLEAN )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:587:1: RULE_BOOLEAN
                    {
                     before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                    match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives1196); 
                     after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:592:6: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:592:6: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:593:1: RULE_STRING
                    {
                     before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives1213); 
                     after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:598:6: ( ruleFloat )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:598:6: ( ruleFloat )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:599:1: ruleFloat
                    {
                     before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives1230);
                    ruleFloat();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:604:6: ( ruleQualifiedID )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:604:6: ( ruleQualifiedID )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:605:1: ruleQualifiedID
                    {
                     before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives1247);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:615:1: rule__Float__Alternatives : ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) );
    public final void rule__Float__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:619:1: ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_TFLOAT) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_NATURAL) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:620:1: ( RULE_TFLOAT )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:620:1: ( RULE_TFLOAT )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:621:1: RULE_TFLOAT
                    {
                     before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                    match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives1279); 
                     after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:626:6: ( RULE_NATURAL )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:626:6: ( RULE_NATURAL )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:627:1: RULE_NATURAL
                    {
                     before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                    match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives1296); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:639:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:643:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:644:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
            {
            pushFollow(FOLLOW_rule__Grana__Group__0__Impl_in_rule__Grana__Group__01326);
            rule__Grana__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group__1_in_rule__Grana__Group__01329);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:651:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:655:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:656:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:656:1: ( ( rule__Grana__Group_0__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:657:1: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:658:1: ( rule__Grana__Group_0__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==12) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:658:2: rule__Grana__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Grana__Group_0__0_in_rule__Grana__Group__0__Impl1356);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:668:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl rule__Grana__Group__2 ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:672:1: ( rule__Grana__Group__1__Impl rule__Grana__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:673:2: rule__Grana__Group__1__Impl rule__Grana__Group__2
            {
            pushFollow(FOLLOW_rule__Grana__Group__1__Impl_in_rule__Grana__Group__11387);
            rule__Grana__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group__2_in_rule__Grana__Group__11390);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:680:1: rule__Grana__Group__1__Impl : ( ( rule__Grana__Group_1__0 )? ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:684:1: ( ( ( rule__Grana__Group_1__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:685:1: ( ( rule__Grana__Group_1__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:685:1: ( ( rule__Grana__Group_1__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:686:1: ( rule__Grana__Group_1__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:687:1: ( rule__Grana__Group_1__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==13) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:687:2: rule__Grana__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Grana__Group_1__0_in_rule__Grana__Group__1__Impl1417);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:697:1: rule__Grana__Group__2 : rule__Grana__Group__2__Impl rule__Grana__Group__3 ;
    public final void rule__Grana__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:701:1: ( rule__Grana__Group__2__Impl rule__Grana__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:702:2: rule__Grana__Group__2__Impl rule__Grana__Group__3
            {
            pushFollow(FOLLOW_rule__Grana__Group__2__Impl_in_rule__Grana__Group__21448);
            rule__Grana__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group__3_in_rule__Grana__Group__21451);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:709:1: rule__Grana__Group__2__Impl : ( ( rule__Grana__Group_2__0 ) ) ;
    public final void rule__Grana__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:713:1: ( ( ( rule__Grana__Group_2__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:714:1: ( ( rule__Grana__Group_2__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:714:1: ( ( rule__Grana__Group_2__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:715:1: ( rule__Grana__Group_2__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:716:1: ( rule__Grana__Group_2__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:716:2: rule__Grana__Group_2__0
            {
            pushFollow(FOLLOW_rule__Grana__Group_2__0_in_rule__Grana__Group__2__Impl1478);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:726:1: rule__Grana__Group__3 : rule__Grana__Group__3__Impl ;
    public final void rule__Grana__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:730:1: ( rule__Grana__Group__3__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:731:2: rule__Grana__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group__3__Impl_in_rule__Grana__Group__31508);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:737:1: rule__Grana__Group__3__Impl : ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) ;
    public final void rule__Grana__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:741:1: ( ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:742:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:742:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:743:1: ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:743:1: ( ( rule__Grana__JobsAssignment_3 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:744:1: ( rule__Grana__JobsAssignment_3 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:745:1: ( rule__Grana__JobsAssignment_3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:745:2: rule__Grana__JobsAssignment_3
            {
            pushFollow(FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl1537);
            rule__Grana__JobsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:748:1: ( ( rule__Grana__JobsAssignment_3 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:749:1: ( rule__Grana__JobsAssignment_3 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:750:1: ( rule__Grana__JobsAssignment_3 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==15) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:750:2: rule__Grana__JobsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl1549);
            	    rule__Grana__JobsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:769:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:773:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:774:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
            {
            pushFollow(FOLLOW_rule__Grana__Group_0__0__Impl_in_rule__Grana__Group_0__01590);
            rule__Grana__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group_0__1_in_rule__Grana__Group_0__01593);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:781:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:785:1: ( ( 'globalResources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:786:1: ( 'globalResources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:786:1: ( 'globalResources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:787:1: 'globalResources'
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0()); 
            match(input,12,FOLLOW_12_in_rule__Grana__Group_0__0__Impl1621); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:800:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:804:1: ( rule__Grana__Group_0__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:805:2: rule__Grana__Group_0__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group_0__1__Impl_in_rule__Grana__Group_0__11652);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:811:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:815:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:816:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:816:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:817:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:818:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_ID) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:818:2: rule__Grana__GlobalResourcesAssignment_0_1
            	    {
            	    pushFollow(FOLLOW_rule__Grana__GlobalResourcesAssignment_0_1_in_rule__Grana__Group_0__1__Impl1679);
            	    rule__Grana__GlobalResourcesAssignment_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:832:1: rule__Grana__Group_1__0 : rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 ;
    public final void rule__Grana__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:836:1: ( rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:837:2: rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1
            {
            pushFollow(FOLLOW_rule__Grana__Group_1__0__Impl_in_rule__Grana__Group_1__01714);
            rule__Grana__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group_1__1_in_rule__Grana__Group_1__01717);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:844:1: rule__Grana__Group_1__0__Impl : ( 'globalOutputs' ) ;
    public final void rule__Grana__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:848:1: ( ( 'globalOutputs' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:849:1: ( 'globalOutputs' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:849:1: ( 'globalOutputs' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:850:1: 'globalOutputs'
            {
             before(grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0()); 
            match(input,13,FOLLOW_13_in_rule__Grana__Group_1__0__Impl1745); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:863:1: rule__Grana__Group_1__1 : rule__Grana__Group_1__1__Impl ;
    public final void rule__Grana__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:867:1: ( rule__Grana__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:868:2: rule__Grana__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group_1__1__Impl_in_rule__Grana__Group_1__11776);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:874:1: rule__Grana__Group_1__1__Impl : ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) ;
    public final void rule__Grana__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:878:1: ( ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:879:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:879:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:880:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:881:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:881:2: rule__Grana__GloobalOutputsAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_rule__Grana__GloobalOutputsAssignment_1_1_in_rule__Grana__Group_1__1__Impl1803);
            	    rule__Grana__GloobalOutputsAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:895:1: rule__Grana__Group_2__0 : rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 ;
    public final void rule__Grana__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:899:1: ( rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:900:2: rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1
            {
            pushFollow(FOLLOW_rule__Grana__Group_2__0__Impl_in_rule__Grana__Group_2__01838);
            rule__Grana__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group_2__1_in_rule__Grana__Group_2__01841);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:907:1: rule__Grana__Group_2__0__Impl : ( 'execute' ) ;
    public final void rule__Grana__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:911:1: ( ( 'execute' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:912:1: ( 'execute' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:912:1: ( 'execute' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:913:1: 'execute'
            {
             before(grammarAccess.getGranaAccess().getExecuteKeyword_2_0()); 
            match(input,14,FOLLOW_14_in_rule__Grana__Group_2__0__Impl1869); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:926:1: rule__Grana__Group_2__1 : rule__Grana__Group_2__1__Impl ;
    public final void rule__Grana__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:930:1: ( rule__Grana__Group_2__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:931:2: rule__Grana__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group_2__1__Impl_in_rule__Grana__Group_2__11900);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:937:1: rule__Grana__Group_2__1__Impl : ( ( rule__Grana__Alternatives_2_1 ) ) ;
    public final void rule__Grana__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:941:1: ( ( ( rule__Grana__Alternatives_2_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:942:1: ( ( rule__Grana__Alternatives_2_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:942:1: ( ( rule__Grana__Alternatives_2_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:943:1: ( rule__Grana__Alternatives_2_1 )
            {
             before(grammarAccess.getGranaAccess().getAlternatives_2_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:944:1: ( rule__Grana__Alternatives_2_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:944:2: rule__Grana__Alternatives_2_1
            {
            pushFollow(FOLLOW_rule__Grana__Alternatives_2_1_in_rule__Grana__Group_2__1__Impl1927);
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


    // $ANTLR start "rule__Job__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:958:1: rule__Job__Group__0 : rule__Job__Group__0__Impl rule__Job__Group__1 ;
    public final void rule__Job__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:962:1: ( rule__Job__Group__0__Impl rule__Job__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:963:2: rule__Job__Group__0__Impl rule__Job__Group__1
            {
            pushFollow(FOLLOW_rule__Job__Group__0__Impl_in_rule__Job__Group__01961);
            rule__Job__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__1_in_rule__Job__Group__01964);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:970:1: rule__Job__Group__0__Impl : ( () ) ;
    public final void rule__Job__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:974:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:975:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:975:1: ( () )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:976:1: ()
            {
             before(grammarAccess.getJobAccess().getJobAction_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:977:1: ()
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:979:1: 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:989:1: rule__Job__Group__1 : rule__Job__Group__1__Impl rule__Job__Group__2 ;
    public final void rule__Job__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:993:1: ( rule__Job__Group__1__Impl rule__Job__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:994:2: rule__Job__Group__1__Impl rule__Job__Group__2
            {
            pushFollow(FOLLOW_rule__Job__Group__1__Impl_in_rule__Job__Group__12022);
            rule__Job__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__2_in_rule__Job__Group__12025);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1001:1: rule__Job__Group__1__Impl : ( 'job' ) ;
    public final void rule__Job__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1005:1: ( ( 'job' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1006:1: ( 'job' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1006:1: ( 'job' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1007:1: 'job'
            {
             before(grammarAccess.getJobAccess().getJobKeyword_1()); 
            match(input,15,FOLLOW_15_in_rule__Job__Group__1__Impl2053); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1020:1: rule__Job__Group__2 : rule__Job__Group__2__Impl rule__Job__Group__3 ;
    public final void rule__Job__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1024:1: ( rule__Job__Group__2__Impl rule__Job__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1025:2: rule__Job__Group__2__Impl rule__Job__Group__3
            {
            pushFollow(FOLLOW_rule__Job__Group__2__Impl_in_rule__Job__Group__22084);
            rule__Job__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__3_in_rule__Job__Group__22087);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1032:1: rule__Job__Group__2__Impl : ( ( rule__Job__NameAssignment_2 ) ) ;
    public final void rule__Job__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1036:1: ( ( ( rule__Job__NameAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1037:1: ( ( rule__Job__NameAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1037:1: ( ( rule__Job__NameAssignment_2 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1038:1: ( rule__Job__NameAssignment_2 )
            {
             before(grammarAccess.getJobAccess().getNameAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1039:1: ( rule__Job__NameAssignment_2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1039:2: rule__Job__NameAssignment_2
            {
            pushFollow(FOLLOW_rule__Job__NameAssignment_2_in_rule__Job__Group__2__Impl2114);
            rule__Job__NameAssignment_2();

            state._fsp--;


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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1049:1: rule__Job__Group__3 : rule__Job__Group__3__Impl rule__Job__Group__4 ;
    public final void rule__Job__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1053:1: ( rule__Job__Group__3__Impl rule__Job__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1054:2: rule__Job__Group__3__Impl rule__Job__Group__4
            {
            pushFollow(FOLLOW_rule__Job__Group__3__Impl_in_rule__Job__Group__32144);
            rule__Job__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__4_in_rule__Job__Group__32147);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1061:1: rule__Job__Group__3__Impl : ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? ) ;
    public final void rule__Job__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1065:1: ( ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1066:1: ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1066:1: ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1067:1: ( rule__Job__LayoutBeforeAnalysisAssignment_3 )?
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1068:1: ( rule__Job__LayoutBeforeAnalysisAssignment_3 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==27) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1068:2: rule__Job__LayoutBeforeAnalysisAssignment_3
                    {
                    pushFollow(FOLLOW_rule__Job__LayoutBeforeAnalysisAssignment_3_in_rule__Job__Group__3__Impl2174);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1078:1: rule__Job__Group__4 : rule__Job__Group__4__Impl rule__Job__Group__5 ;
    public final void rule__Job__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1082:1: ( rule__Job__Group__4__Impl rule__Job__Group__5 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1083:2: rule__Job__Group__4__Impl rule__Job__Group__5
            {
            pushFollow(FOLLOW_rule__Job__Group__4__Impl_in_rule__Job__Group__42205);
            rule__Job__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__5_in_rule__Job__Group__42208);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1090:1: rule__Job__Group__4__Impl : ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? ) ;
    public final void rule__Job__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1094:1: ( ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1095:1: ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1095:1: ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1096:1: ( rule__Job__MeasureExecutionTimeAssignment_4 )?
            {
             before(grammarAccess.getJobAccess().getMeasureExecutionTimeAssignment_4()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1097:1: ( rule__Job__MeasureExecutionTimeAssignment_4 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==28) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1097:2: rule__Job__MeasureExecutionTimeAssignment_4
                    {
                    pushFollow(FOLLOW_rule__Job__MeasureExecutionTimeAssignment_4_in_rule__Job__Group__4__Impl2235);
                    rule__Job__MeasureExecutionTimeAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getJobAccess().getMeasureExecutionTimeAssignment_4()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1107:1: rule__Job__Group__5 : rule__Job__Group__5__Impl rule__Job__Group__6 ;
    public final void rule__Job__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1111:1: ( rule__Job__Group__5__Impl rule__Job__Group__6 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1112:2: rule__Job__Group__5__Impl rule__Job__Group__6
            {
            pushFollow(FOLLOW_rule__Job__Group__5__Impl_in_rule__Job__Group__52266);
            rule__Job__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__6_in_rule__Job__Group__52269);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1119:1: rule__Job__Group__5__Impl : ( 'resources' ) ;
    public final void rule__Job__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1123:1: ( ( 'resources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1124:1: ( 'resources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1124:1: ( 'resources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1125:1: 'resources'
            {
             before(grammarAccess.getJobAccess().getResourcesKeyword_5()); 
            match(input,16,FOLLOW_16_in_rule__Job__Group__5__Impl2297); 
             after(grammarAccess.getJobAccess().getResourcesKeyword_5()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1138:1: rule__Job__Group__6 : rule__Job__Group__6__Impl rule__Job__Group__7 ;
    public final void rule__Job__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1142:1: ( rule__Job__Group__6__Impl rule__Job__Group__7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1143:2: rule__Job__Group__6__Impl rule__Job__Group__7
            {
            pushFollow(FOLLOW_rule__Job__Group__6__Impl_in_rule__Job__Group__62328);
            rule__Job__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__7_in_rule__Job__Group__62331);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1150:1: rule__Job__Group__6__Impl : ( ( rule__Job__ResourcesAssignment_6 )* ) ;
    public final void rule__Job__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1154:1: ( ( ( rule__Job__ResourcesAssignment_6 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1155:1: ( ( rule__Job__ResourcesAssignment_6 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1155:1: ( ( rule__Job__ResourcesAssignment_6 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1156:1: ( rule__Job__ResourcesAssignment_6 )*
            {
             before(grammarAccess.getJobAccess().getResourcesAssignment_6()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1157:1: ( rule__Job__ResourcesAssignment_6 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_STRING||LA14_0==20) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1157:2: rule__Job__ResourcesAssignment_6
            	    {
            	    pushFollow(FOLLOW_rule__Job__ResourcesAssignment_6_in_rule__Job__Group__6__Impl2358);
            	    rule__Job__ResourcesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getJobAccess().getResourcesAssignment_6()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1167:1: rule__Job__Group__7 : rule__Job__Group__7__Impl rule__Job__Group__8 ;
    public final void rule__Job__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1171:1: ( rule__Job__Group__7__Impl rule__Job__Group__8 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1172:2: rule__Job__Group__7__Impl rule__Job__Group__8
            {
            pushFollow(FOLLOW_rule__Job__Group__7__Impl_in_rule__Job__Group__72389);
            rule__Job__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__8_in_rule__Job__Group__72392);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1179:1: rule__Job__Group__7__Impl : ( 'layoutoptions' ) ;
    public final void rule__Job__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1183:1: ( ( 'layoutoptions' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1184:1: ( 'layoutoptions' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1184:1: ( 'layoutoptions' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1185:1: 'layoutoptions'
            {
             before(grammarAccess.getJobAccess().getLayoutoptionsKeyword_7()); 
            match(input,17,FOLLOW_17_in_rule__Job__Group__7__Impl2420); 
             after(grammarAccess.getJobAccess().getLayoutoptionsKeyword_7()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1198:1: rule__Job__Group__8 : rule__Job__Group__8__Impl rule__Job__Group__9 ;
    public final void rule__Job__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1202:1: ( rule__Job__Group__8__Impl rule__Job__Group__9 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1203:2: rule__Job__Group__8__Impl rule__Job__Group__9
            {
            pushFollow(FOLLOW_rule__Job__Group__8__Impl_in_rule__Job__Group__82451);
            rule__Job__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__9_in_rule__Job__Group__82454);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1210:1: rule__Job__Group__8__Impl : ( ( rule__Job__LayoutOptionsAssignment_8 )* ) ;
    public final void rule__Job__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1214:1: ( ( ( rule__Job__LayoutOptionsAssignment_8 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1215:1: ( ( rule__Job__LayoutOptionsAssignment_8 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1215:1: ( ( rule__Job__LayoutOptionsAssignment_8 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1216:1: ( rule__Job__LayoutOptionsAssignment_8 )*
            {
             before(grammarAccess.getJobAccess().getLayoutOptionsAssignment_8()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1217:1: ( rule__Job__LayoutOptionsAssignment_8 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_ID) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1217:2: rule__Job__LayoutOptionsAssignment_8
            	    {
            	    pushFollow(FOLLOW_rule__Job__LayoutOptionsAssignment_8_in_rule__Job__Group__8__Impl2481);
            	    rule__Job__LayoutOptionsAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getJobAccess().getLayoutOptionsAssignment_8()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1227:1: rule__Job__Group__9 : rule__Job__Group__9__Impl rule__Job__Group__10 ;
    public final void rule__Job__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1231:1: ( rule__Job__Group__9__Impl rule__Job__Group__10 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1232:2: rule__Job__Group__9__Impl rule__Job__Group__10
            {
            pushFollow(FOLLOW_rule__Job__Group__9__Impl_in_rule__Job__Group__92512);
            rule__Job__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__10_in_rule__Job__Group__92515);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1239:1: rule__Job__Group__9__Impl : ( 'analyses' ) ;
    public final void rule__Job__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1243:1: ( ( 'analyses' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1244:1: ( 'analyses' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1244:1: ( 'analyses' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1245:1: 'analyses'
            {
             before(grammarAccess.getJobAccess().getAnalysesKeyword_9()); 
            match(input,18,FOLLOW_18_in_rule__Job__Group__9__Impl2543); 
             after(grammarAccess.getJobAccess().getAnalysesKeyword_9()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1258:1: rule__Job__Group__10 : rule__Job__Group__10__Impl rule__Job__Group__11 ;
    public final void rule__Job__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1262:1: ( rule__Job__Group__10__Impl rule__Job__Group__11 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1263:2: rule__Job__Group__10__Impl rule__Job__Group__11
            {
            pushFollow(FOLLOW_rule__Job__Group__10__Impl_in_rule__Job__Group__102574);
            rule__Job__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__11_in_rule__Job__Group__102577);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1270:1: rule__Job__Group__10__Impl : ( ( rule__Job__AnalysesAssignment_10 )* ) ;
    public final void rule__Job__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1274:1: ( ( ( rule__Job__AnalysesAssignment_10 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1275:1: ( ( rule__Job__AnalysesAssignment_10 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1275:1: ( ( rule__Job__AnalysesAssignment_10 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1276:1: ( rule__Job__AnalysesAssignment_10 )*
            {
             before(grammarAccess.getJobAccess().getAnalysesAssignment_10()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1277:1: ( rule__Job__AnalysesAssignment_10 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1277:2: rule__Job__AnalysesAssignment_10
            	    {
            	    pushFollow(FOLLOW_rule__Job__AnalysesAssignment_10_in_rule__Job__Group__10__Impl2604);
            	    rule__Job__AnalysesAssignment_10();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getJobAccess().getAnalysesAssignment_10()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1287:1: rule__Job__Group__11 : rule__Job__Group__11__Impl rule__Job__Group__12 ;
    public final void rule__Job__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1291:1: ( rule__Job__Group__11__Impl rule__Job__Group__12 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1292:2: rule__Job__Group__11__Impl rule__Job__Group__12
            {
            pushFollow(FOLLOW_rule__Job__Group__11__Impl_in_rule__Job__Group__112635);
            rule__Job__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__12_in_rule__Job__Group__112638);
            rule__Job__Group__12();

            state._fsp--;


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1299:1: rule__Job__Group__11__Impl : ( 'output' ) ;
    public final void rule__Job__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1303:1: ( ( 'output' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1304:1: ( 'output' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1304:1: ( 'output' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1305:1: 'output'
            {
             before(grammarAccess.getJobAccess().getOutputKeyword_11()); 
            match(input,19,FOLLOW_19_in_rule__Job__Group__11__Impl2666); 
             after(grammarAccess.getJobAccess().getOutputKeyword_11()); 

            }


            }

        }
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


    // $ANTLR start "rule__Job__Group__12"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1318:1: rule__Job__Group__12 : rule__Job__Group__12__Impl ;
    public final void rule__Job__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1322:1: ( rule__Job__Group__12__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1323:2: rule__Job__Group__12__Impl
            {
            pushFollow(FOLLOW_rule__Job__Group__12__Impl_in_rule__Job__Group__122697);
            rule__Job__Group__12__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__12"


    // $ANTLR start "rule__Job__Group__12__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1329:1: rule__Job__Group__12__Impl : ( ( rule__Job__OutputAssignment_12 ) ) ;
    public final void rule__Job__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1333:1: ( ( ( rule__Job__OutputAssignment_12 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1334:1: ( ( rule__Job__OutputAssignment_12 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1334:1: ( ( rule__Job__OutputAssignment_12 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1335:1: ( rule__Job__OutputAssignment_12 )
            {
             before(grammarAccess.getJobAccess().getOutputAssignment_12()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1336:1: ( rule__Job__OutputAssignment_12 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1336:2: rule__Job__OutputAssignment_12
            {
            pushFollow(FOLLOW_rule__Job__OutputAssignment_12_in_rule__Job__Group__12__Impl2724);
            rule__Job__OutputAssignment_12();

            state._fsp--;


            }

             after(grammarAccess.getJobAccess().getOutputAssignment_12()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__12__Impl"


    // $ANTLR start "rule__ResourceReference__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1372:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1376:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1377:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__0__Impl_in_rule__ResourceReference__Group__02780);
            rule__ResourceReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ResourceReference__Group__1_in_rule__ResourceReference__Group__02783);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1384:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1388:1: ( ( 'ref' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1389:1: ( 'ref' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1389:1: ( 'ref' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1390:1: 'ref'
            {
             before(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 
            match(input,20,FOLLOW_20_in_rule__ResourceReference__Group__0__Impl2811); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1403:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1407:1: ( rule__ResourceReference__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1408:2: rule__ResourceReference__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__1__Impl_in_rule__ResourceReference__Group__12842);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1414:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1418:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1419:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1419:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1420:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1420:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1421:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1422:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1422:2: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2871);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1425:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1426:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1427:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_ID) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1427:2: rule__ResourceReference__ResourceRefsAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2883);
            	    rule__ResourceReference__ResourceRefsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1442:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1446:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1447:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__0__Impl_in_rule__GlobalResourceRef__Group__02920);
            rule__GlobalResourceRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__1_in_rule__GlobalResourceRef__Group__02923);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1454:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1458:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1459:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1459:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1460:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1461:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1461:2: rule__GlobalResourceRef__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__NameAssignment_0_in_rule__GlobalResourceRef__Group__0__Impl2950);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1471:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1475:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1476:2: rule__GlobalResourceRef__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__1__Impl_in_rule__GlobalResourceRef__Group__12980);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1482:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1486:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1487:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1487:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1488:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1489:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1489:2: rule__GlobalResourceRef__ResourcesAssignment_1
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__ResourcesAssignment_1_in_rule__GlobalResourceRef__Group__1__Impl3007);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1503:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1507:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1508:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__0__Impl_in_rule__LocalResource__Group__03041);
            rule__LocalResource__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalResource__Group__1_in_rule__LocalResource__Group__03044);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1515:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1519:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1520:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1520:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1521:1: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1522:1: ( rule__LocalResource__PathAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1522:2: rule__LocalResource__PathAssignment_0
            {
            pushFollow(FOLLOW_rule__LocalResource__PathAssignment_0_in_rule__LocalResource__Group__0__Impl3071);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1532:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1536:1: ( rule__LocalResource__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1537:2: rule__LocalResource__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__1__Impl_in_rule__LocalResource__Group__13101);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1543:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1547:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1548:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1548:1: ( ( rule__LocalResource__Group_1__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1549:1: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1550:1: ( rule__LocalResource__Group_1__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1550:2: rule__LocalResource__Group_1__0
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__0_in_rule__LocalResource__Group__1__Impl3128);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1564:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1568:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1569:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__0__Impl_in_rule__LocalResource__Group_1__03162);
            rule__LocalResource__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalResource__Group_1__1_in_rule__LocalResource__Group_1__03165);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1576:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1580:1: ( ( 'filter' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1581:1: ( 'filter' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1581:1: ( 'filter' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1582:1: 'filter'
            {
             before(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 
            match(input,21,FOLLOW_21_in_rule__LocalResource__Group_1__0__Impl3193); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1595:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1599:1: ( rule__LocalResource__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1600:2: rule__LocalResource__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__1__Impl_in_rule__LocalResource__Group_1__13224);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1606:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1610:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1611:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1611:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1612:1: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1613:1: ( rule__LocalResource__FilterAssignment_1_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1613:2: rule__LocalResource__FilterAssignment_1_1
            {
            pushFollow(FOLLOW_rule__LocalResource__FilterAssignment_1_1_in_rule__LocalResource__Group_1__1__Impl3251);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1627:1: rule__GlobalOutputRef__Group__0 : rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 ;
    public final void rule__GlobalOutputRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1631:1: ( rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1632:2: rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__0__Impl_in_rule__GlobalOutputRef__Group__03285);
            rule__GlobalOutputRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__1_in_rule__GlobalOutputRef__Group__03288);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1639:1: rule__GlobalOutputRef__Group__0__Impl : ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalOutputRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1643:1: ( ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1644:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1644:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1645:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1646:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1646:2: rule__GlobalOutputRef__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__NameAssignment_0_in_rule__GlobalOutputRef__Group__0__Impl3315);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1656:1: rule__GlobalOutputRef__Group__1 : rule__GlobalOutputRef__Group__1__Impl ;
    public final void rule__GlobalOutputRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1660:1: ( rule__GlobalOutputRef__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1661:2: rule__GlobalOutputRef__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__1__Impl_in_rule__GlobalOutputRef__Group__13345);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1667:1: rule__GlobalOutputRef__Group__1__Impl : ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) ;
    public final void rule__GlobalOutputRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1671:1: ( ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1672:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1672:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1673:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1674:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1674:2: rule__GlobalOutputRef__OutputAssignment_1
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__OutputAssignment_1_in_rule__GlobalOutputRef__Group__1__Impl3372);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1688:1: rule__OutputReference__Group__0 : rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 ;
    public final void rule__OutputReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1692:1: ( rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1693:2: rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1
            {
            pushFollow(FOLLOW_rule__OutputReference__Group__0__Impl_in_rule__OutputReference__Group__03406);
            rule__OutputReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OutputReference__Group__1_in_rule__OutputReference__Group__03409);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1700:1: rule__OutputReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__OutputReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1704:1: ( ( 'ref' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1705:1: ( 'ref' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1705:1: ( 'ref' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1706:1: 'ref'
            {
             before(grammarAccess.getOutputReferenceAccess().getRefKeyword_0()); 
            match(input,20,FOLLOW_20_in_rule__OutputReference__Group__0__Impl3437); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1719:1: rule__OutputReference__Group__1 : rule__OutputReference__Group__1__Impl ;
    public final void rule__OutputReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1723:1: ( rule__OutputReference__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1724:2: rule__OutputReference__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OutputReference__Group__1__Impl_in_rule__OutputReference__Group__13468);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1730:1: rule__OutputReference__Group__1__Impl : ( ( rule__OutputReference__OutputRefAssignment_1 ) ) ;
    public final void rule__OutputReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1734:1: ( ( ( rule__OutputReference__OutputRefAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1735:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1735:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1736:1: ( rule__OutputReference__OutputRefAssignment_1 )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1737:1: ( rule__OutputReference__OutputRefAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1737:2: rule__OutputReference__OutputRefAssignment_1
            {
            pushFollow(FOLLOW_rule__OutputReference__OutputRefAssignment_1_in_rule__OutputReference__Group__1__Impl3495);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1751:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1755:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1756:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__03529);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__03532);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1763:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1767:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1768:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1768:1: ( () )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1769:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1770:1: ()
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1772:1: 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1782:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1786:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1787:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__13590);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__13593);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1794:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1798:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1799:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1799:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1800:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1801:1: ( rule__KIdentifier__IdAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1801:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl3620);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1811:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1815:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1816:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__23650);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__23653);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1823:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1827:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1828:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1828:1: ( '{' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1829:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,22,FOLLOW_22_in_rule__KIdentifier__Group__2__Impl3681); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1842:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1846:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1847:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__33712);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__33715);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1854:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1858:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1859:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1859:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1860:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1861:1: ( rule__KIdentifier__Group_3__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1861:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl3742);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1871:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1875:1: ( rule__KIdentifier__Group__4__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1876:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__43773);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1882:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1886:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1887:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1887:1: ( '}' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1888:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,23,FOLLOW_23_in_rule__KIdentifier__Group__4__Impl3801); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1911:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1915:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1916:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__03842);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__03845);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1923:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1927:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1928:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1928:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1929:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1930:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1930:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl3872);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1940:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1944:1: ( rule__KIdentifier__Group_3__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1945:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__13902);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1951:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1955:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1956:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1956:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1957:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1958:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1958:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl3929);
            	    rule__KIdentifier__PersistentEntriesAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1972:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1976:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1977:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__03964);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__03967);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1984:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1988:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1989:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1989:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1990:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1991:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1991:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl3994);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2001:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2005:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2006:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__14024);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__14027);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2013:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2017:1: ( ( ':' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2018:1: ( ':' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2018:1: ( ':' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2019:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,24,FOLLOW_24_in_rule__PersistentEntry__Group__1__Impl4055); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2032:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2036:1: ( rule__PersistentEntry__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2037:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__24086);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2043:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2047:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2048:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2048:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2049:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2050:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2050:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl4113);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2066:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2070:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2071:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__04149);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__04152);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2078:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2082:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2083:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2083:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2084:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl4179); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2095:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2099:1: ( rule__QualifiedID__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2100:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__14208);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2106:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2110:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2111:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2111:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2112:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2113:1: ( rule__QualifiedID__Group_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==25) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2113:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl4235);
            	    rule__QualifiedID__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2127:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2131:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2132:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__04270);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__04273);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2139:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2143:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2144:1: ( '.' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2144:1: ( '.' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2145:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,25,FOLLOW_25_in_rule__QualifiedID__Group_1__0__Impl4301); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2158:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2162:1: ( rule__QualifiedID__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2163:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__14332);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2169:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2173:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2174:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2174:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2175:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl4359); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2191:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2195:1: ( ( ruleGlobalResourceRef ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2196:1: ( ruleGlobalResourceRef )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2196:1: ( ruleGlobalResourceRef )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2197:1: ruleGlobalResourceRef
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesGlobalResourceRefParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_rule__Grana__GlobalResourcesAssignment_0_14397);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2206:1: rule__Grana__GloobalOutputsAssignment_1_1 : ( ruleGlobalOutputRef ) ;
    public final void rule__Grana__GloobalOutputsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2210:1: ( ( ruleGlobalOutputRef ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2211:1: ( ruleGlobalOutputRef )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2211:1: ( ruleGlobalOutputRef )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2212:1: ruleGlobalOutputRef
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsGlobalOutputRefParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleGlobalOutputRef_in_rule__Grana__GloobalOutputsAssignment_1_14428);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2221:1: rule__Grana__ExecuteAllAssignment_2_1_0 : ( ( 'all' ) ) ;
    public final void rule__Grana__ExecuteAllAssignment_2_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2225:1: ( ( ( 'all' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2226:1: ( ( 'all' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2226:1: ( ( 'all' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2227:1: ( 'all' )
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_1_0_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2228:1: ( 'all' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2229:1: 'all'
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_1_0_0()); 
            match(input,26,FOLLOW_26_in_rule__Grana__ExecuteAllAssignment_2_1_04464); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2244:1: rule__Grana__ExecuteAssignment_2_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__Grana__ExecuteAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2248:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2249:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2249:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2250:1: ( RULE_ID )
            {
             before(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_1_1_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2251:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2252:1: RULE_ID
            {
             before(grammarAccess.getGranaAccess().getExecuteJobIDTerminalRuleCall_2_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Grana__ExecuteAssignment_2_1_14507); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2263:1: rule__Grana__JobsAssignment_3 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2267:1: ( ( ruleJob ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2268:1: ( ruleJob )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2268:1: ( ruleJob )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2269:1: ruleJob
            {
             before(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleJob_in_rule__Grana__JobsAssignment_34542);
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


    // $ANTLR start "rule__Job__NameAssignment_2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2278:1: rule__Job__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Job__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2282:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2283:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2283:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2284:1: RULE_ID
            {
             before(grammarAccess.getJobAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Job__NameAssignment_24573); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2293:1: rule__Job__LayoutBeforeAnalysisAssignment_3 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__Job__LayoutBeforeAnalysisAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2297:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2298:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2298:1: ( ( 'layoutBeforeAnalysis' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2299:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2300:1: ( 'layoutBeforeAnalysis' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2301:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 
            match(input,27,FOLLOW_27_in_rule__Job__LayoutBeforeAnalysisAssignment_34609); 
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


    // $ANTLR start "rule__Job__MeasureExecutionTimeAssignment_4"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2316:1: rule__Job__MeasureExecutionTimeAssignment_4 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__Job__MeasureExecutionTimeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2320:1: ( ( ( 'measureExecutionTime' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2321:1: ( ( 'measureExecutionTime' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2321:1: ( ( 'measureExecutionTime' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2322:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2323:1: ( 'measureExecutionTime' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2324:1: 'measureExecutionTime'
            {
             before(grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0()); 
            match(input,28,FOLLOW_28_in_rule__Job__MeasureExecutionTimeAssignment_44653); 
             after(grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0()); 

            }

             after(grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__MeasureExecutionTimeAssignment_4"


    // $ANTLR start "rule__Job__ResourcesAssignment_6"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2339:1: rule__Job__ResourcesAssignment_6 : ( ruleResource ) ;
    public final void rule__Job__ResourcesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2343:1: ( ( ruleResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2344:1: ( ruleResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2344:1: ( ruleResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2345:1: ruleResource
            {
             before(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_6_0()); 
            pushFollow(FOLLOW_ruleResource_in_rule__Job__ResourcesAssignment_64692);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__ResourcesAssignment_6"


    // $ANTLR start "rule__Job__LayoutOptionsAssignment_8"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2354:1: rule__Job__LayoutOptionsAssignment_8 : ( ruleKIdentifier ) ;
    public final void rule__Job__LayoutOptionsAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2358:1: ( ( ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2359:1: ( ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2359:1: ( ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2360:1: ruleKIdentifier
            {
             before(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_8_0()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_rule__Job__LayoutOptionsAssignment_84723);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__LayoutOptionsAssignment_8"


    // $ANTLR start "rule__Job__AnalysesAssignment_10"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2369:1: rule__Job__AnalysesAssignment_10 : ( ruleAnalysis ) ;
    public final void rule__Job__AnalysesAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2373:1: ( ( ruleAnalysis ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2374:1: ( ruleAnalysis )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2374:1: ( ruleAnalysis )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2375:1: ruleAnalysis
            {
             before(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_10_0()); 
            pushFollow(FOLLOW_ruleAnalysis_in_rule__Job__AnalysesAssignment_104754);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_10_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__AnalysesAssignment_10"


    // $ANTLR start "rule__Job__OutputAssignment_12"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2384:1: rule__Job__OutputAssignment_12 : ( ruleOutput ) ;
    public final void rule__Job__OutputAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2388:1: ( ( ruleOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2389:1: ( ruleOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2389:1: ( ruleOutput )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2390:1: ruleOutput
            {
             before(grammarAccess.getJobAccess().getOutputOutputParserRuleCall_12_0()); 
            pushFollow(FOLLOW_ruleOutput_in_rule__Job__OutputAssignment_124785);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getJobAccess().getOutputOutputParserRuleCall_12_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__OutputAssignment_12"


    // $ANTLR start "rule__ResourceReference__ResourceRefsAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2399:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2403:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2404:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2404:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2405:1: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2406:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2407:1: RULE_ID
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ResourceReference__ResourceRefsAssignment_14820); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2418:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2422:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2423:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2423:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2424:1: RULE_ID
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__GlobalResourceRef__NameAssignment_04855); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2433:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2437:1: ( ( ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2438:1: ( ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2438:1: ( ruleLocalResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2439:1: ruleLocalResource
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleLocalResource_in_rule__GlobalResourceRef__ResourcesAssignment_14886);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2448:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2452:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2453:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2453:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2454:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalResource__PathAssignment_04917); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2463:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2467:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2468:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2468:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2469:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalResource__FilterAssignment_1_14948); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2478:1: rule__GlobalOutputRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalOutputRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2482:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2483:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2483:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2484:1: RULE_ID
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__GlobalOutputRef__NameAssignment_04979); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2493:1: rule__GlobalOutputRef__OutputAssignment_1 : ( ruleLocalOutput ) ;
    public final void rule__GlobalOutputRef__OutputAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2497:1: ( ( ruleLocalOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2498:1: ( ruleLocalOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2498:1: ( ruleLocalOutput )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2499:1: ruleLocalOutput
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputLocalOutputParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleLocalOutput_in_rule__GlobalOutputRef__OutputAssignment_15010);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2508:1: rule__OutputReference__OutputRefAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__OutputReference__OutputRefAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2512:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2513:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2513:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2514:1: ( RULE_ID )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2515:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2516:1: RULE_ID
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OutputReference__OutputRefAssignment_15045); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2527:1: rule__LocalOutput__PathAssignment : ( RULE_STRING ) ;
    public final void rule__LocalOutput__PathAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2531:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2532:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2532:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2533:1: RULE_STRING
            {
             before(grammarAccess.getLocalOutputAccess().getPathSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalOutput__PathAssignment5080); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2542:1: rule__Analysis__NameAssignment : ( ruleQualifiedID ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2546:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2547:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2547:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2548:1: ruleQualifiedID
            {
             before(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment5111);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2557:1: rule__KIdentifier__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2561:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2562:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2562:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2563:1: RULE_ID
            {
             before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_15142); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2572:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2576:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2577:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2577:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2578:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_05173);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2587:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2591:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2592:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2592:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2593:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_15204);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2602:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2606:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2607:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2607:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2608:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_05235);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2617:1: rule__PersistentEntry__ValueAssignment_2 : ( rulePropertyValue ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2621:1: ( ( rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2622:1: ( rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2622:1: ( rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2623:1: rulePropertyValue
            {
             before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_25266);
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
    public static final BitSet FOLLOW_rule__Job__Group__0_in_ruleJob154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_entryRuleResource181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResource188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Alternatives_in_ruleResource214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_entryRuleResourceReference241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResourceReference248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__0_in_ruleResourceReference274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalResourceRef308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__0_in_ruleGlobalResourceRef334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_entryRuleLocalResource361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalResource368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__0_in_ruleLocalResource394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutput_in_entryRuleOutput421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutput428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Output__Alternatives_in_ruleOutput454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalOutputRef488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__0_in_ruleGlobalOutputRef514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_entryRuleOutputReference541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutputReference548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__0_in_ruleOutputReference574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalOutput608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalOutput__PathAssignment_in_ruleLocalOutput634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry781 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat961 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Float__Alternatives_in_ruleFloat994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__ExecuteAllAssignment_2_1_0_in_rule__Grana__Alternatives_2_11030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11050 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11062 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ruleResourceReference_in_rule__Resource__Alternatives1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_rule__Resource__Alternatives1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_rule__Output__Alternatives1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_rule__Output__Alternatives1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives1196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives1230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives1279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__0__Impl_in_rule__Grana__Group__01326 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_rule__Grana__Group__1_in_rule__Grana__Group__01329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__0_in_rule__Grana__Group__0__Impl1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__1__Impl_in_rule__Grana__Group__11387 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_rule__Grana__Group__2_in_rule__Grana__Group__11390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__0_in_rule__Grana__Group__1__Impl1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__2__Impl_in_rule__Grana__Group__21448 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Grana__Group__3_in_rule__Grana__Group__21451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__0_in_rule__Grana__Group__2__Impl1478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__3__Impl_in_rule__Grana__Group__31508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl1537 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl1549 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__0__Impl_in_rule__Grana__Group_0__01590 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__1_in_rule__Grana__Group_0__01593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Grana__Group_0__0__Impl1621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__1__Impl_in_rule__Grana__Group_0__11652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__GlobalResourcesAssignment_0_1_in_rule__Grana__Group_0__1__Impl1679 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__0__Impl_in_rule__Grana__Group_1__01714 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__1_in_rule__Grana__Group_1__01717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Grana__Group_1__0__Impl1745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__1__Impl_in_rule__Grana__Group_1__11776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__GloobalOutputsAssignment_1_1_in_rule__Grana__Group_1__1__Impl1803 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__0__Impl_in_rule__Grana__Group_2__01838 = new BitSet(new long[]{0x0000000004000100L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__1_in_rule__Grana__Group_2__01841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Grana__Group_2__0__Impl1869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__1__Impl_in_rule__Grana__Group_2__11900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Alternatives_2_1_in_rule__Grana__Group_2__1__Impl1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__0__Impl_in_rule__Job__Group__01961 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Job__Group__1_in_rule__Job__Group__01964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__1__Impl_in_rule__Job__Group__12022 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Job__Group__2_in_rule__Job__Group__12025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Job__Group__1__Impl2053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__2__Impl_in_rule__Job__Group__22084 = new BitSet(new long[]{0x0000000018010000L});
    public static final BitSet FOLLOW_rule__Job__Group__3_in_rule__Job__Group__22087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__NameAssignment_2_in_rule__Job__Group__2__Impl2114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__3__Impl_in_rule__Job__Group__32144 = new BitSet(new long[]{0x0000000018010000L});
    public static final BitSet FOLLOW_rule__Job__Group__4_in_rule__Job__Group__32147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__LayoutBeforeAnalysisAssignment_3_in_rule__Job__Group__3__Impl2174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__4__Impl_in_rule__Job__Group__42205 = new BitSet(new long[]{0x0000000018010000L});
    public static final BitSet FOLLOW_rule__Job__Group__5_in_rule__Job__Group__42208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__MeasureExecutionTimeAssignment_4_in_rule__Job__Group__4__Impl2235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__5__Impl_in_rule__Job__Group__52266 = new BitSet(new long[]{0x0000000000120020L});
    public static final BitSet FOLLOW_rule__Job__Group__6_in_rule__Job__Group__52269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Job__Group__5__Impl2297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__6__Impl_in_rule__Job__Group__62328 = new BitSet(new long[]{0x0000000000120020L});
    public static final BitSet FOLLOW_rule__Job__Group__7_in_rule__Job__Group__62331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__ResourcesAssignment_6_in_rule__Job__Group__6__Impl2358 = new BitSet(new long[]{0x0000000000100022L});
    public static final BitSet FOLLOW_rule__Job__Group__7__Impl_in_rule__Job__Group__72389 = new BitSet(new long[]{0x0000000000040100L});
    public static final BitSet FOLLOW_rule__Job__Group__8_in_rule__Job__Group__72392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Job__Group__7__Impl2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__8__Impl_in_rule__Job__Group__82451 = new BitSet(new long[]{0x0000000000040100L});
    public static final BitSet FOLLOW_rule__Job__Group__9_in_rule__Job__Group__82454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__LayoutOptionsAssignment_8_in_rule__Job__Group__8__Impl2481 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Job__Group__9__Impl_in_rule__Job__Group__92512 = new BitSet(new long[]{0x0000000000080100L});
    public static final BitSet FOLLOW_rule__Job__Group__10_in_rule__Job__Group__92515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Job__Group__9__Impl2543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__10__Impl_in_rule__Job__Group__102574 = new BitSet(new long[]{0x0000000000080100L});
    public static final BitSet FOLLOW_rule__Job__Group__11_in_rule__Job__Group__102577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__AnalysesAssignment_10_in_rule__Job__Group__10__Impl2604 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Job__Group__11__Impl_in_rule__Job__Group__112635 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_rule__Job__Group__12_in_rule__Job__Group__112638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Job__Group__11__Impl2666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__12__Impl_in_rule__Job__Group__122697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__OutputAssignment_12_in_rule__Job__Group__12__Impl2724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__0__Impl_in_rule__ResourceReference__Group__02780 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__1_in_rule__ResourceReference__Group__02783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__ResourceReference__Group__0__Impl2811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__1__Impl_in_rule__ResourceReference__Group__12842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2871 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2883 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__0__Impl_in_rule__GlobalResourceRef__Group__02920 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__1_in_rule__GlobalResourceRef__Group__02923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__NameAssignment_0_in_rule__GlobalResourceRef__Group__0__Impl2950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__1__Impl_in_rule__GlobalResourceRef__Group__12980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__ResourcesAssignment_1_in_rule__GlobalResourceRef__Group__1__Impl3007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__0__Impl_in_rule__LocalResource__Group__03041 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__1_in_rule__LocalResource__Group__03044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__PathAssignment_0_in_rule__LocalResource__Group__0__Impl3071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__1__Impl_in_rule__LocalResource__Group__13101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__0_in_rule__LocalResource__Group__1__Impl3128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__0__Impl_in_rule__LocalResource__Group_1__03162 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__1_in_rule__LocalResource__Group_1__03165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__LocalResource__Group_1__0__Impl3193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__1__Impl_in_rule__LocalResource__Group_1__13224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__FilterAssignment_1_1_in_rule__LocalResource__Group_1__1__Impl3251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__0__Impl_in_rule__GlobalOutputRef__Group__03285 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__1_in_rule__GlobalOutputRef__Group__03288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__NameAssignment_0_in_rule__GlobalOutputRef__Group__0__Impl3315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__1__Impl_in_rule__GlobalOutputRef__Group__13345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__OutputAssignment_1_in_rule__GlobalOutputRef__Group__1__Impl3372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__0__Impl_in_rule__OutputReference__Group__03406 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__1_in_rule__OutputReference__Group__03409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__OutputReference__Group__0__Impl3437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__1__Impl_in_rule__OutputReference__Group__13468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__OutputRefAssignment_1_in_rule__OutputReference__Group__1__Impl3495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__03529 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__03532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__13590 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__13593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl3620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__23650 = new BitSet(new long[]{0x0000000000800100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__23653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__KIdentifier__Group__2__Impl3681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__33712 = new BitSet(new long[]{0x0000000000800100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__33715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl3742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__43773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__KIdentifier__Group__4__Impl3801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__03842 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__03845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__13902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl3929 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__03964 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__03967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl3994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__14024 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__14027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__PersistentEntry__Group__1__Impl4055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__24086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl4113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__04149 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__04152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl4179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__14208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl4235 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__04270 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__04273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__QualifiedID__Group_1__0__Impl4301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__14332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl4359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_rule__Grana__GlobalResourcesAssignment_0_14397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalOutputRef_in_rule__Grana__GloobalOutputsAssignment_1_14428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Grana__ExecuteAllAssignment_2_1_04464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Grana__ExecuteAssignment_2_1_14507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJob_in_rule__Grana__JobsAssignment_34542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Job__NameAssignment_24573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Job__LayoutBeforeAnalysisAssignment_34609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Job__MeasureExecutionTimeAssignment_44653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_rule__Job__ResourcesAssignment_64692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_rule__Job__LayoutOptionsAssignment_84723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_rule__Job__AnalysesAssignment_104754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutput_in_rule__Job__OutputAssignment_124785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ResourceReference__ResourceRefsAssignment_14820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__GlobalResourceRef__NameAssignment_04855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_rule__GlobalResourceRef__ResourcesAssignment_14886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalResource__PathAssignment_04917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalResource__FilterAssignment_1_14948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__GlobalOutputRef__NameAssignment_04979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_rule__GlobalOutputRef__OutputAssignment_15010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OutputReference__OutputRefAssignment_15045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalOutput__PathAssignment5080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment5111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_15142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_05173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_15204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_05235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_25266 = new BitSet(new long[]{0x0000000000000002L});

}