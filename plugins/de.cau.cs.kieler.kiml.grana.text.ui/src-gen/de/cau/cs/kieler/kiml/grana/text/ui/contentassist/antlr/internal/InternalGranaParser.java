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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BOOLEAN", "RULE_STRING", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'", "'layoutBeforeAnalysis'", "'measureExecutionTime'"
    };
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_ID=8;
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


    // $ANTLR start "entryRuleAnalysis"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:228:1: entryRuleAnalysis : ruleAnalysis EOF ;
    public final void entryRuleAnalysis() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:229:1: ( ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:230:1: ruleAnalysis EOF
            {
             before(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis421);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getAnalysisRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis428); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:237:1: ruleAnalysis : ( ( rule__Analysis__NameAssignment ) ) ;
    public final void ruleAnalysis() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:241:2: ( ( ( rule__Analysis__NameAssignment ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__Analysis__NameAssignment ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__Analysis__NameAssignment ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:243:1: ( rule__Analysis__NameAssignment )
            {
             before(grammarAccess.getAnalysisAccess().getNameAssignment()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:1: ( rule__Analysis__NameAssignment )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:2: rule__Analysis__NameAssignment
            {
            pushFollow(FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis454);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:256:1: entryRuleKIdentifier : ruleKIdentifier EOF ;
    public final void entryRuleKIdentifier() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:257:1: ( ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:258:1: ruleKIdentifier EOF
            {
             before(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier481);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKIdentifierRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier488); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:265:1: ruleKIdentifier : ( ( rule__KIdentifier__Group__0 ) ) ;
    public final void ruleKIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:269:2: ( ( ( rule__KIdentifier__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__KIdentifier__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__KIdentifier__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:271:1: ( rule__KIdentifier__Group__0 )
            {
             before(grammarAccess.getKIdentifierAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:1: ( rule__KIdentifier__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:2: rule__KIdentifier__Group__0
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier514);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:284:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:285:1: ( rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:286:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry541);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry548); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:293:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:297:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__PersistentEntry__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:299:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:1: ( rule__PersistentEntry__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry574);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:312:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:313:1: ( ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:314:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID601);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID608); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:321:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:325:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:326:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:326:1: ( ( rule__QualifiedID__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:327:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:328:1: ( rule__QualifiedID__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:328:2: rule__QualifiedID__Group__0
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID634);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:340:1: entryRulePropertyValue : rulePropertyValue EOF ;
    public final void entryRulePropertyValue() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:341:1: ( rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:342:1: rulePropertyValue EOF
            {
             before(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue661);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPropertyValueRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue668); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:349:1: rulePropertyValue : ( ( rule__PropertyValue__Alternatives ) ) ;
    public final void rulePropertyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:353:2: ( ( ( rule__PropertyValue__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:354:1: ( ( rule__PropertyValue__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:354:1: ( ( rule__PropertyValue__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:355:1: ( rule__PropertyValue__Alternatives )
            {
             before(grammarAccess.getPropertyValueAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:356:1: ( rule__PropertyValue__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:356:2: rule__PropertyValue__Alternatives
            {
            pushFollow(FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue694);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:368:1: entryRuleFloat : ruleFloat EOF ;
    public final void entryRuleFloat() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:369:1: ( ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:370:1: ruleFloat EOF
            {
             before(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat721);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat728); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:377:1: ruleFloat : ( ( rule__Float__Alternatives ) ) ;
    public final void ruleFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:381:2: ( ( ( rule__Float__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:382:1: ( ( rule__Float__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:382:1: ( ( rule__Float__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:383:1: ( rule__Float__Alternatives )
            {
             before(grammarAccess.getFloatAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:384:1: ( rule__Float__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:384:2: rule__Float__Alternatives
            {
            pushFollow(FOLLOW_rule__Float__Alternatives_in_ruleFloat754);
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


    // $ANTLR start "rule__Resource__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:396:1: rule__Resource__Alternatives : ( ( ruleResourceReference ) | ( ruleLocalResource ) );
    public final void rule__Resource__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:400:1: ( ( ruleResourceReference ) | ( ruleLocalResource ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==18) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_STRING) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:401:1: ( ruleResourceReference )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:401:1: ( ruleResourceReference )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:402:1: ruleResourceReference
                    {
                     before(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleResourceReference_in_rule__Resource__Alternatives790);
                    ruleResourceReference();

                    state._fsp--;

                     after(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:407:6: ( ruleLocalResource )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:407:6: ( ruleLocalResource )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:408:1: ruleLocalResource
                    {
                     before(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLocalResource_in_rule__Resource__Alternatives807);
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


    // $ANTLR start "rule__PropertyValue__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:418:1: rule__PropertyValue__Alternatives : ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) );
    public final void rule__PropertyValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:422:1: ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) )
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:423:1: ( RULE_BOOLEAN )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:423:1: ( RULE_BOOLEAN )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:424:1: RULE_BOOLEAN
                    {
                     before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                    match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives839); 
                     after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:429:6: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:429:6: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:430:1: RULE_STRING
                    {
                     before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives856); 
                     after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:435:6: ( ruleFloat )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:435:6: ( ruleFloat )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:436:1: ruleFloat
                    {
                     before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives873);
                    ruleFloat();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:441:6: ( ruleQualifiedID )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:441:6: ( ruleQualifiedID )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:442:1: ruleQualifiedID
                    {
                     before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives890);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:452:1: rule__Float__Alternatives : ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) );
    public final void rule__Float__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:456:1: ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) )
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
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:457:1: ( RULE_TFLOAT )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:457:1: ( RULE_TFLOAT )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:458:1: RULE_TFLOAT
                    {
                     before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                    match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives922); 
                     after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:463:6: ( RULE_NATURAL )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:463:6: ( RULE_NATURAL )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:464:1: RULE_NATURAL
                    {
                     before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                    match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives939); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:476:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:480:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:481:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
            {
            pushFollow(FOLLOW_rule__Grana__Group__0__Impl_in_rule__Grana__Group__0969);
            rule__Grana__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group__1_in_rule__Grana__Group__0972);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:488:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:492:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:493:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:493:1: ( ( rule__Grana__Group_0__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:494:1: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:495:1: ( rule__Grana__Group_0__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:495:2: rule__Grana__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Grana__Group_0__0_in_rule__Grana__Group__0__Impl999);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:505:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:509:1: ( rule__Grana__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:510:2: rule__Grana__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group__1__Impl_in_rule__Grana__Group__11030);
            rule__Grana__Group__1__Impl();

            state._fsp--;


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:516:1: rule__Grana__Group__1__Impl : ( ( ( rule__Grana__JobsAssignment_1 ) ) ( ( rule__Grana__JobsAssignment_1 )* ) ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:520:1: ( ( ( ( rule__Grana__JobsAssignment_1 ) ) ( ( rule__Grana__JobsAssignment_1 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:521:1: ( ( ( rule__Grana__JobsAssignment_1 ) ) ( ( rule__Grana__JobsAssignment_1 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:521:1: ( ( ( rule__Grana__JobsAssignment_1 ) ) ( ( rule__Grana__JobsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:522:1: ( ( rule__Grana__JobsAssignment_1 ) ) ( ( rule__Grana__JobsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:522:1: ( ( rule__Grana__JobsAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:523:1: ( rule__Grana__JobsAssignment_1 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:524:1: ( rule__Grana__JobsAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:524:2: rule__Grana__JobsAssignment_1
            {
            pushFollow(FOLLOW_rule__Grana__JobsAssignment_1_in_rule__Grana__Group__1__Impl1059);
            rule__Grana__JobsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_1()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:527:1: ( ( rule__Grana__JobsAssignment_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:528:1: ( rule__Grana__JobsAssignment_1 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:529:1: ( rule__Grana__JobsAssignment_1 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==13) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:529:2: rule__Grana__JobsAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__Grana__JobsAssignment_1_in_rule__Grana__Group__1__Impl1071);
            	    rule__Grana__JobsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getGranaAccess().getJobsAssignment_1()); 

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
    // $ANTLR end "rule__Grana__Group__1__Impl"


    // $ANTLR start "rule__Grana__Group_0__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:544:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:548:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:549:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
            {
            pushFollow(FOLLOW_rule__Grana__Group_0__0__Impl_in_rule__Grana__Group_0__01108);
            rule__Grana__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group_0__1_in_rule__Grana__Group_0__01111);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:556:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:560:1: ( ( 'globalResources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:561:1: ( 'globalResources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:561:1: ( 'globalResources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:562:1: 'globalResources'
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0()); 
            match(input,12,FOLLOW_12_in_rule__Grana__Group_0__0__Impl1139); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:575:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:579:1: ( rule__Grana__Group_0__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:580:2: rule__Grana__Group_0__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group_0__1__Impl_in_rule__Grana__Group_0__11170);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:586:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:590:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:591:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:591:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:592:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:593:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_ID) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:593:2: rule__Grana__GlobalResourcesAssignment_0_1
            	    {
            	    pushFollow(FOLLOW_rule__Grana__GlobalResourcesAssignment_0_1_in_rule__Grana__Group_0__1__Impl1197);
            	    rule__Grana__GlobalResourcesAssignment_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
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


    // $ANTLR start "rule__Job__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:607:1: rule__Job__Group__0 : rule__Job__Group__0__Impl rule__Job__Group__1 ;
    public final void rule__Job__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:611:1: ( rule__Job__Group__0__Impl rule__Job__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:612:2: rule__Job__Group__0__Impl rule__Job__Group__1
            {
            pushFollow(FOLLOW_rule__Job__Group__0__Impl_in_rule__Job__Group__01232);
            rule__Job__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__1_in_rule__Job__Group__01235);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:619:1: rule__Job__Group__0__Impl : ( () ) ;
    public final void rule__Job__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:623:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:624:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:624:1: ( () )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:625:1: ()
            {
             before(grammarAccess.getJobAccess().getJobAction_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:626:1: ()
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:628:1: 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:638:1: rule__Job__Group__1 : rule__Job__Group__1__Impl rule__Job__Group__2 ;
    public final void rule__Job__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:642:1: ( rule__Job__Group__1__Impl rule__Job__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:643:2: rule__Job__Group__1__Impl rule__Job__Group__2
            {
            pushFollow(FOLLOW_rule__Job__Group__1__Impl_in_rule__Job__Group__11293);
            rule__Job__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__2_in_rule__Job__Group__11296);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:650:1: rule__Job__Group__1__Impl : ( 'job' ) ;
    public final void rule__Job__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:654:1: ( ( 'job' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:655:1: ( 'job' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:655:1: ( 'job' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:656:1: 'job'
            {
             before(grammarAccess.getJobAccess().getJobKeyword_1()); 
            match(input,13,FOLLOW_13_in_rule__Job__Group__1__Impl1324); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:669:1: rule__Job__Group__2 : rule__Job__Group__2__Impl rule__Job__Group__3 ;
    public final void rule__Job__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:673:1: ( rule__Job__Group__2__Impl rule__Job__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:674:2: rule__Job__Group__2__Impl rule__Job__Group__3
            {
            pushFollow(FOLLOW_rule__Job__Group__2__Impl_in_rule__Job__Group__21355);
            rule__Job__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__3_in_rule__Job__Group__21358);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:681:1: rule__Job__Group__2__Impl : ( ( rule__Job__NameAssignment_2 )? ) ;
    public final void rule__Job__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:685:1: ( ( ( rule__Job__NameAssignment_2 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:686:1: ( ( rule__Job__NameAssignment_2 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:686:1: ( ( rule__Job__NameAssignment_2 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:687:1: ( rule__Job__NameAssignment_2 )?
            {
             before(grammarAccess.getJobAccess().getNameAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:688:1: ( rule__Job__NameAssignment_2 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:688:2: rule__Job__NameAssignment_2
                    {
                    pushFollow(FOLLOW_rule__Job__NameAssignment_2_in_rule__Job__Group__2__Impl1385);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:698:1: rule__Job__Group__3 : rule__Job__Group__3__Impl rule__Job__Group__4 ;
    public final void rule__Job__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:702:1: ( rule__Job__Group__3__Impl rule__Job__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:703:2: rule__Job__Group__3__Impl rule__Job__Group__4
            {
            pushFollow(FOLLOW_rule__Job__Group__3__Impl_in_rule__Job__Group__31416);
            rule__Job__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__4_in_rule__Job__Group__31419);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:710:1: rule__Job__Group__3__Impl : ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? ) ;
    public final void rule__Job__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:714:1: ( ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:715:1: ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:715:1: ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:716:1: ( rule__Job__LayoutBeforeAnalysisAssignment_3 )?
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:717:1: ( rule__Job__LayoutBeforeAnalysisAssignment_3 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==24) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:717:2: rule__Job__LayoutBeforeAnalysisAssignment_3
                    {
                    pushFollow(FOLLOW_rule__Job__LayoutBeforeAnalysisAssignment_3_in_rule__Job__Group__3__Impl1446);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:727:1: rule__Job__Group__4 : rule__Job__Group__4__Impl rule__Job__Group__5 ;
    public final void rule__Job__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:731:1: ( rule__Job__Group__4__Impl rule__Job__Group__5 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:732:2: rule__Job__Group__4__Impl rule__Job__Group__5
            {
            pushFollow(FOLLOW_rule__Job__Group__4__Impl_in_rule__Job__Group__41477);
            rule__Job__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__5_in_rule__Job__Group__41480);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:739:1: rule__Job__Group__4__Impl : ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? ) ;
    public final void rule__Job__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:743:1: ( ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:744:1: ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:744:1: ( ( rule__Job__MeasureExecutionTimeAssignment_4 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:745:1: ( rule__Job__MeasureExecutionTimeAssignment_4 )?
            {
             before(grammarAccess.getJobAccess().getMeasureExecutionTimeAssignment_4()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:746:1: ( rule__Job__MeasureExecutionTimeAssignment_4 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==25) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:746:2: rule__Job__MeasureExecutionTimeAssignment_4
                    {
                    pushFollow(FOLLOW_rule__Job__MeasureExecutionTimeAssignment_4_in_rule__Job__Group__4__Impl1507);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:756:1: rule__Job__Group__5 : rule__Job__Group__5__Impl rule__Job__Group__6 ;
    public final void rule__Job__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:760:1: ( rule__Job__Group__5__Impl rule__Job__Group__6 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:761:2: rule__Job__Group__5__Impl rule__Job__Group__6
            {
            pushFollow(FOLLOW_rule__Job__Group__5__Impl_in_rule__Job__Group__51538);
            rule__Job__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__6_in_rule__Job__Group__51541);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:768:1: rule__Job__Group__5__Impl : ( 'resources' ) ;
    public final void rule__Job__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:772:1: ( ( 'resources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:773:1: ( 'resources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:773:1: ( 'resources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:774:1: 'resources'
            {
             before(grammarAccess.getJobAccess().getResourcesKeyword_5()); 
            match(input,14,FOLLOW_14_in_rule__Job__Group__5__Impl1569); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:787:1: rule__Job__Group__6 : rule__Job__Group__6__Impl rule__Job__Group__7 ;
    public final void rule__Job__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:791:1: ( rule__Job__Group__6__Impl rule__Job__Group__7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:792:2: rule__Job__Group__6__Impl rule__Job__Group__7
            {
            pushFollow(FOLLOW_rule__Job__Group__6__Impl_in_rule__Job__Group__61600);
            rule__Job__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__7_in_rule__Job__Group__61603);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:799:1: rule__Job__Group__6__Impl : ( ( rule__Job__ResourcesAssignment_6 )* ) ;
    public final void rule__Job__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:803:1: ( ( ( rule__Job__ResourcesAssignment_6 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:804:1: ( ( rule__Job__ResourcesAssignment_6 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:804:1: ( ( rule__Job__ResourcesAssignment_6 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:805:1: ( rule__Job__ResourcesAssignment_6 )*
            {
             before(grammarAccess.getJobAccess().getResourcesAssignment_6()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:806:1: ( rule__Job__ResourcesAssignment_6 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_STRING||LA10_0==18) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:806:2: rule__Job__ResourcesAssignment_6
            	    {
            	    pushFollow(FOLLOW_rule__Job__ResourcesAssignment_6_in_rule__Job__Group__6__Impl1630);
            	    rule__Job__ResourcesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:816:1: rule__Job__Group__7 : rule__Job__Group__7__Impl rule__Job__Group__8 ;
    public final void rule__Job__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:820:1: ( rule__Job__Group__7__Impl rule__Job__Group__8 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:821:2: rule__Job__Group__7__Impl rule__Job__Group__8
            {
            pushFollow(FOLLOW_rule__Job__Group__7__Impl_in_rule__Job__Group__71661);
            rule__Job__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__8_in_rule__Job__Group__71664);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:828:1: rule__Job__Group__7__Impl : ( 'layoutoptions' ) ;
    public final void rule__Job__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:832:1: ( ( 'layoutoptions' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:833:1: ( 'layoutoptions' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:833:1: ( 'layoutoptions' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:834:1: 'layoutoptions'
            {
             before(grammarAccess.getJobAccess().getLayoutoptionsKeyword_7()); 
            match(input,15,FOLLOW_15_in_rule__Job__Group__7__Impl1692); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:847:1: rule__Job__Group__8 : rule__Job__Group__8__Impl rule__Job__Group__9 ;
    public final void rule__Job__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:851:1: ( rule__Job__Group__8__Impl rule__Job__Group__9 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:852:2: rule__Job__Group__8__Impl rule__Job__Group__9
            {
            pushFollow(FOLLOW_rule__Job__Group__8__Impl_in_rule__Job__Group__81723);
            rule__Job__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__9_in_rule__Job__Group__81726);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:859:1: rule__Job__Group__8__Impl : ( ( rule__Job__LayoutOptionsAssignment_8 ) ) ;
    public final void rule__Job__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:863:1: ( ( ( rule__Job__LayoutOptionsAssignment_8 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:864:1: ( ( rule__Job__LayoutOptionsAssignment_8 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:864:1: ( ( rule__Job__LayoutOptionsAssignment_8 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:865:1: ( rule__Job__LayoutOptionsAssignment_8 )
            {
             before(grammarAccess.getJobAccess().getLayoutOptionsAssignment_8()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:866:1: ( rule__Job__LayoutOptionsAssignment_8 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:866:2: rule__Job__LayoutOptionsAssignment_8
            {
            pushFollow(FOLLOW_rule__Job__LayoutOptionsAssignment_8_in_rule__Job__Group__8__Impl1753);
            rule__Job__LayoutOptionsAssignment_8();

            state._fsp--;


            }

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:876:1: rule__Job__Group__9 : rule__Job__Group__9__Impl rule__Job__Group__10 ;
    public final void rule__Job__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:880:1: ( rule__Job__Group__9__Impl rule__Job__Group__10 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:881:2: rule__Job__Group__9__Impl rule__Job__Group__10
            {
            pushFollow(FOLLOW_rule__Job__Group__9__Impl_in_rule__Job__Group__91783);
            rule__Job__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__10_in_rule__Job__Group__91786);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:888:1: rule__Job__Group__9__Impl : ( 'analyses' ) ;
    public final void rule__Job__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:892:1: ( ( 'analyses' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:893:1: ( 'analyses' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:893:1: ( 'analyses' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:894:1: 'analyses'
            {
             before(grammarAccess.getJobAccess().getAnalysesKeyword_9()); 
            match(input,16,FOLLOW_16_in_rule__Job__Group__9__Impl1814); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:907:1: rule__Job__Group__10 : rule__Job__Group__10__Impl rule__Job__Group__11 ;
    public final void rule__Job__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:911:1: ( rule__Job__Group__10__Impl rule__Job__Group__11 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:912:2: rule__Job__Group__10__Impl rule__Job__Group__11
            {
            pushFollow(FOLLOW_rule__Job__Group__10__Impl_in_rule__Job__Group__101845);
            rule__Job__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__11_in_rule__Job__Group__101848);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:919:1: rule__Job__Group__10__Impl : ( ( rule__Job__AnalysesAssignment_10 )* ) ;
    public final void rule__Job__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:923:1: ( ( ( rule__Job__AnalysesAssignment_10 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:924:1: ( ( rule__Job__AnalysesAssignment_10 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:924:1: ( ( rule__Job__AnalysesAssignment_10 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:925:1: ( rule__Job__AnalysesAssignment_10 )*
            {
             before(grammarAccess.getJobAccess().getAnalysesAssignment_10()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:926:1: ( rule__Job__AnalysesAssignment_10 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:926:2: rule__Job__AnalysesAssignment_10
            	    {
            	    pushFollow(FOLLOW_rule__Job__AnalysesAssignment_10_in_rule__Job__Group__10__Impl1875);
            	    rule__Job__AnalysesAssignment_10();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:936:1: rule__Job__Group__11 : rule__Job__Group__11__Impl rule__Job__Group__12 ;
    public final void rule__Job__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:940:1: ( rule__Job__Group__11__Impl rule__Job__Group__12 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:941:2: rule__Job__Group__11__Impl rule__Job__Group__12
            {
            pushFollow(FOLLOW_rule__Job__Group__11__Impl_in_rule__Job__Group__111906);
            rule__Job__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__12_in_rule__Job__Group__111909);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:948:1: rule__Job__Group__11__Impl : ( 'output' ) ;
    public final void rule__Job__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:952:1: ( ( 'output' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:953:1: ( 'output' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:953:1: ( 'output' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:954:1: 'output'
            {
             before(grammarAccess.getJobAccess().getOutputKeyword_11()); 
            match(input,17,FOLLOW_17_in_rule__Job__Group__11__Impl1937); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:967:1: rule__Job__Group__12 : rule__Job__Group__12__Impl ;
    public final void rule__Job__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:971:1: ( rule__Job__Group__12__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:972:2: rule__Job__Group__12__Impl
            {
            pushFollow(FOLLOW_rule__Job__Group__12__Impl_in_rule__Job__Group__121968);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:978:1: rule__Job__Group__12__Impl : ( ( rule__Job__OutputAssignment_12 ) ) ;
    public final void rule__Job__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:982:1: ( ( ( rule__Job__OutputAssignment_12 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:983:1: ( ( rule__Job__OutputAssignment_12 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:983:1: ( ( rule__Job__OutputAssignment_12 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:984:1: ( rule__Job__OutputAssignment_12 )
            {
             before(grammarAccess.getJobAccess().getOutputAssignment_12()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:985:1: ( rule__Job__OutputAssignment_12 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:985:2: rule__Job__OutputAssignment_12
            {
            pushFollow(FOLLOW_rule__Job__OutputAssignment_12_in_rule__Job__Group__12__Impl1995);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1021:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1025:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1026:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__0__Impl_in_rule__ResourceReference__Group__02051);
            rule__ResourceReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ResourceReference__Group__1_in_rule__ResourceReference__Group__02054);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1033:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1037:1: ( ( 'ref' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1038:1: ( 'ref' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1038:1: ( 'ref' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1039:1: 'ref'
            {
             before(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 
            match(input,18,FOLLOW_18_in_rule__ResourceReference__Group__0__Impl2082); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1052:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1056:1: ( rule__ResourceReference__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1057:2: rule__ResourceReference__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__1__Impl_in_rule__ResourceReference__Group__12113);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1063:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1067:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1068:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1068:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1069:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1069:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1070:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1071:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1071:2: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2142);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1074:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1075:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1076:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_ID) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1076:2: rule__ResourceReference__ResourceRefsAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2154);
            	    rule__ResourceReference__ResourceRefsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1091:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1095:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1096:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__0__Impl_in_rule__GlobalResourceRef__Group__02191);
            rule__GlobalResourceRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__1_in_rule__GlobalResourceRef__Group__02194);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1103:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1107:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1108:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1108:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1109:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1110:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1110:2: rule__GlobalResourceRef__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__NameAssignment_0_in_rule__GlobalResourceRef__Group__0__Impl2221);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1120:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1124:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1125:2: rule__GlobalResourceRef__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__1__Impl_in_rule__GlobalResourceRef__Group__12251);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1131:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1135:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1136:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1136:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1137:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1138:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1138:2: rule__GlobalResourceRef__ResourcesAssignment_1
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__ResourcesAssignment_1_in_rule__GlobalResourceRef__Group__1__Impl2278);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1152:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1156:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1157:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__0__Impl_in_rule__LocalResource__Group__02312);
            rule__LocalResource__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalResource__Group__1_in_rule__LocalResource__Group__02315);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1164:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1168:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1169:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1169:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1170:1: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1171:1: ( rule__LocalResource__PathAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1171:2: rule__LocalResource__PathAssignment_0
            {
            pushFollow(FOLLOW_rule__LocalResource__PathAssignment_0_in_rule__LocalResource__Group__0__Impl2342);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1181:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1185:1: ( rule__LocalResource__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1186:2: rule__LocalResource__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__1__Impl_in_rule__LocalResource__Group__12372);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1192:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1196:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1197:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1197:1: ( ( rule__LocalResource__Group_1__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1198:1: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1199:1: ( rule__LocalResource__Group_1__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1199:2: rule__LocalResource__Group_1__0
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__0_in_rule__LocalResource__Group__1__Impl2399);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1213:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1217:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1218:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__0__Impl_in_rule__LocalResource__Group_1__02433);
            rule__LocalResource__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalResource__Group_1__1_in_rule__LocalResource__Group_1__02436);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1225:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1229:1: ( ( 'filter' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1230:1: ( 'filter' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1230:1: ( 'filter' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1231:1: 'filter'
            {
             before(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 
            match(input,19,FOLLOW_19_in_rule__LocalResource__Group_1__0__Impl2464); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1244:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1248:1: ( rule__LocalResource__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1249:2: rule__LocalResource__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__1__Impl_in_rule__LocalResource__Group_1__12495);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1255:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1259:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1260:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1260:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1261:1: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1262:1: ( rule__LocalResource__FilterAssignment_1_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1262:2: rule__LocalResource__FilterAssignment_1_1
            {
            pushFollow(FOLLOW_rule__LocalResource__FilterAssignment_1_1_in_rule__LocalResource__Group_1__1__Impl2522);
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


    // $ANTLR start "rule__KIdentifier__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1276:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1280:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1281:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__02556);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__02559);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1288:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1292:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1293:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1293:1: ( () )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1294:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1295:1: ()
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1297:1: 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1307:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1311:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1312:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__12617);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__12620);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1319:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1323:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1324:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1324:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1325:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1326:1: ( rule__KIdentifier__IdAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1326:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl2647);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1336:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1340:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1341:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__22677);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__22680);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1348:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1352:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1353:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1353:1: ( '{' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1354:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,20,FOLLOW_20_in_rule__KIdentifier__Group__2__Impl2708); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1367:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1371:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1372:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__32739);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__32742);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1379:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1383:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1384:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1384:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1385:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1386:1: ( rule__KIdentifier__Group_3__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1386:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl2769);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1396:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1400:1: ( rule__KIdentifier__Group__4__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1401:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__42800);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1407:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1411:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1412:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1412:1: ( '}' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1413:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,21,FOLLOW_21_in_rule__KIdentifier__Group__4__Impl2828); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1436:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1440:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1441:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__02869);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__02872);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1448:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1452:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1453:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1453:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1454:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1455:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1455:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl2899);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1465:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1469:1: ( rule__KIdentifier__Group_3__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1470:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__12929);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1476:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1480:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1481:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1481:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1482:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1483:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1483:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl2956);
            	    rule__KIdentifier__PersistentEntriesAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1497:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1501:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1502:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__02991);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__02994);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1509:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1513:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1514:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1514:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1515:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1516:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1516:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl3021);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1526:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1530:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1531:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__13051);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__13054);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1538:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1542:1: ( ( ':' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1543:1: ( ':' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1543:1: ( ':' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1544:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,22,FOLLOW_22_in_rule__PersistentEntry__Group__1__Impl3082); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1557:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1561:1: ( rule__PersistentEntry__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1562:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__23113);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1568:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1572:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1573:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1573:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1574:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1575:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1575:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl3140);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1591:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1595:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1596:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__03176);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__03179);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1603:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1607:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1608:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1608:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1609:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl3206); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1620:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1624:1: ( rule__QualifiedID__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1625:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__13235);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1631:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1635:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1636:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1636:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1637:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1638:1: ( rule__QualifiedID__Group_1__0 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==23) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1638:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl3262);
            	    rule__QualifiedID__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1652:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1656:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1657:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__03297);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__03300);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1664:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1668:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1669:1: ( '.' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1669:1: ( '.' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1670:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,23,FOLLOW_23_in_rule__QualifiedID__Group_1__0__Impl3328); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1683:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1687:1: ( rule__QualifiedID__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1688:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__13359);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1694:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1698:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1699:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1699:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1700:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl3386); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1716:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1720:1: ( ( ruleGlobalResourceRef ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1721:1: ( ruleGlobalResourceRef )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1721:1: ( ruleGlobalResourceRef )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1722:1: ruleGlobalResourceRef
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesGlobalResourceRefParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_rule__Grana__GlobalResourcesAssignment_0_13424);
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


    // $ANTLR start "rule__Grana__JobsAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1731:1: rule__Grana__JobsAssignment_1 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1735:1: ( ( ruleJob ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1736:1: ( ruleJob )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1736:1: ( ruleJob )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1737:1: ruleJob
            {
             before(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleJob_in_rule__Grana__JobsAssignment_13455);
            ruleJob();

            state._fsp--;

             after(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Grana__JobsAssignment_1"


    // $ANTLR start "rule__Job__NameAssignment_2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1746:1: rule__Job__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Job__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1750:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1751:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1751:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1752:1: RULE_ID
            {
             before(grammarAccess.getJobAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Job__NameAssignment_23486); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1761:1: rule__Job__LayoutBeforeAnalysisAssignment_3 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__Job__LayoutBeforeAnalysisAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1765:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1766:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1766:1: ( ( 'layoutBeforeAnalysis' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1767:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1768:1: ( 'layoutBeforeAnalysis' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1769:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 
            match(input,24,FOLLOW_24_in_rule__Job__LayoutBeforeAnalysisAssignment_33522); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1784:1: rule__Job__MeasureExecutionTimeAssignment_4 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__Job__MeasureExecutionTimeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1788:1: ( ( ( 'measureExecutionTime' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1789:1: ( ( 'measureExecutionTime' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1789:1: ( ( 'measureExecutionTime' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1790:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1791:1: ( 'measureExecutionTime' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1792:1: 'measureExecutionTime'
            {
             before(grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0()); 
            match(input,25,FOLLOW_25_in_rule__Job__MeasureExecutionTimeAssignment_43566); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1807:1: rule__Job__ResourcesAssignment_6 : ( ruleResource ) ;
    public final void rule__Job__ResourcesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1811:1: ( ( ruleResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1812:1: ( ruleResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1812:1: ( ruleResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1813:1: ruleResource
            {
             before(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_6_0()); 
            pushFollow(FOLLOW_ruleResource_in_rule__Job__ResourcesAssignment_63605);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1822:1: rule__Job__LayoutOptionsAssignment_8 : ( ruleKIdentifier ) ;
    public final void rule__Job__LayoutOptionsAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1826:1: ( ( ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1827:1: ( ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1827:1: ( ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1828:1: ruleKIdentifier
            {
             before(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_8_0()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_rule__Job__LayoutOptionsAssignment_83636);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1837:1: rule__Job__AnalysesAssignment_10 : ( ruleAnalysis ) ;
    public final void rule__Job__AnalysesAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1841:1: ( ( ruleAnalysis ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1842:1: ( ruleAnalysis )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1842:1: ( ruleAnalysis )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1843:1: ruleAnalysis
            {
             before(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_10_0()); 
            pushFollow(FOLLOW_ruleAnalysis_in_rule__Job__AnalysesAssignment_103667);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1852:1: rule__Job__OutputAssignment_12 : ( RULE_STRING ) ;
    public final void rule__Job__OutputAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1856:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1857:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1857:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1858:1: RULE_STRING
            {
             before(grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_12_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Job__OutputAssignment_123698); 
             after(grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_12_0()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1867:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1871:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1872:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1872:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1873:1: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1874:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1875:1: RULE_ID
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ResourceReference__ResourceRefsAssignment_13733); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1886:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1890:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1891:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1891:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1892:1: RULE_ID
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__GlobalResourceRef__NameAssignment_03768); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1901:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1905:1: ( ( ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1906:1: ( ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1906:1: ( ruleLocalResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1907:1: ruleLocalResource
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleLocalResource_in_rule__GlobalResourceRef__ResourcesAssignment_13799);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1916:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1920:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1921:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1921:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1922:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalResource__PathAssignment_03830); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1931:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1935:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1936:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1936:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1937:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalResource__FilterAssignment_1_13861); 
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


    // $ANTLR start "rule__Analysis__NameAssignment"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1946:1: rule__Analysis__NameAssignment : ( ruleQualifiedID ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1950:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1951:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1951:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1952:1: ruleQualifiedID
            {
             before(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment3892);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1961:1: rule__KIdentifier__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1965:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1966:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1966:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1967:1: RULE_ID
            {
             before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_13923); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1976:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1980:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1981:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1981:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1982:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_03954);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1991:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1995:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1996:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1996:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1997:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_13985);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2006:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2010:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2011:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2011:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2012:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_04016);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2021:1: rule__PersistentEntry__ValueAssignment_2 : ( rulePropertyValue ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2025:1: ( ( rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2026:1: ( rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2026:1: ( rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2027:1: rulePropertyValue
            {
             before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_24047);
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
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Float__Alternatives_in_ruleFloat754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_rule__Resource__Alternatives790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_rule__Resource__Alternatives807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__0__Impl_in_rule__Grana__Group__0969 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Grana__Group__1_in_rule__Grana__Group__0972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__0_in_rule__Grana__Group__0__Impl999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__1__Impl_in_rule__Grana__Group__11030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__JobsAssignment_1_in_rule__Grana__Group__1__Impl1059 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_rule__Grana__JobsAssignment_1_in_rule__Grana__Group__1__Impl1071 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__0__Impl_in_rule__Grana__Group_0__01108 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__1_in_rule__Grana__Group_0__01111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Grana__Group_0__0__Impl1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__1__Impl_in_rule__Grana__Group_0__11170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__GlobalResourcesAssignment_0_1_in_rule__Grana__Group_0__1__Impl1197 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Job__Group__0__Impl_in_rule__Job__Group__01232 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__Job__Group__1_in_rule__Job__Group__01235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__1__Impl_in_rule__Job__Group__11293 = new BitSet(new long[]{0x0000000003004100L});
    public static final BitSet FOLLOW_rule__Job__Group__2_in_rule__Job__Group__11296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Job__Group__1__Impl1324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__2__Impl_in_rule__Job__Group__21355 = new BitSet(new long[]{0x0000000003004100L});
    public static final BitSet FOLLOW_rule__Job__Group__3_in_rule__Job__Group__21358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__NameAssignment_2_in_rule__Job__Group__2__Impl1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__3__Impl_in_rule__Job__Group__31416 = new BitSet(new long[]{0x0000000003004100L});
    public static final BitSet FOLLOW_rule__Job__Group__4_in_rule__Job__Group__31419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__LayoutBeforeAnalysisAssignment_3_in_rule__Job__Group__3__Impl1446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__4__Impl_in_rule__Job__Group__41477 = new BitSet(new long[]{0x0000000003004100L});
    public static final BitSet FOLLOW_rule__Job__Group__5_in_rule__Job__Group__41480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__MeasureExecutionTimeAssignment_4_in_rule__Job__Group__4__Impl1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__5__Impl_in_rule__Job__Group__51538 = new BitSet(new long[]{0x0000000000048020L});
    public static final BitSet FOLLOW_rule__Job__Group__6_in_rule__Job__Group__51541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Job__Group__5__Impl1569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__6__Impl_in_rule__Job__Group__61600 = new BitSet(new long[]{0x0000000000048020L});
    public static final BitSet FOLLOW_rule__Job__Group__7_in_rule__Job__Group__61603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__ResourcesAssignment_6_in_rule__Job__Group__6__Impl1630 = new BitSet(new long[]{0x0000000000040022L});
    public static final BitSet FOLLOW_rule__Job__Group__7__Impl_in_rule__Job__Group__71661 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Job__Group__8_in_rule__Job__Group__71664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Job__Group__7__Impl1692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__8__Impl_in_rule__Job__Group__81723 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__Job__Group__9_in_rule__Job__Group__81726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__LayoutOptionsAssignment_8_in_rule__Job__Group__8__Impl1753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__9__Impl_in_rule__Job__Group__91783 = new BitSet(new long[]{0x0000000000020100L});
    public static final BitSet FOLLOW_rule__Job__Group__10_in_rule__Job__Group__91786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Job__Group__9__Impl1814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__10__Impl_in_rule__Job__Group__101845 = new BitSet(new long[]{0x0000000000020100L});
    public static final BitSet FOLLOW_rule__Job__Group__11_in_rule__Job__Group__101848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__AnalysesAssignment_10_in_rule__Job__Group__10__Impl1875 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Job__Group__11__Impl_in_rule__Job__Group__111906 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Job__Group__12_in_rule__Job__Group__111909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Job__Group__11__Impl1937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__12__Impl_in_rule__Job__Group__121968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__OutputAssignment_12_in_rule__Job__Group__12__Impl1995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__0__Impl_in_rule__ResourceReference__Group__02051 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__1_in_rule__ResourceReference__Group__02054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__ResourceReference__Group__0__Impl2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__1__Impl_in_rule__ResourceReference__Group__12113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2142 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl2154 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__0__Impl_in_rule__GlobalResourceRef__Group__02191 = new BitSet(new long[]{0x0000000000040020L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__1_in_rule__GlobalResourceRef__Group__02194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__NameAssignment_0_in_rule__GlobalResourceRef__Group__0__Impl2221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__1__Impl_in_rule__GlobalResourceRef__Group__12251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__ResourcesAssignment_1_in_rule__GlobalResourceRef__Group__1__Impl2278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__0__Impl_in_rule__LocalResource__Group__02312 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__1_in_rule__LocalResource__Group__02315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__PathAssignment_0_in_rule__LocalResource__Group__0__Impl2342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__1__Impl_in_rule__LocalResource__Group__12372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__0_in_rule__LocalResource__Group__1__Impl2399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__0__Impl_in_rule__LocalResource__Group_1__02433 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__1_in_rule__LocalResource__Group_1__02436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__LocalResource__Group_1__0__Impl2464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__1__Impl_in_rule__LocalResource__Group_1__12495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__FilterAssignment_1_1_in_rule__LocalResource__Group_1__1__Impl2522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__02556 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__02559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__12617 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__12620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl2647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__22677 = new BitSet(new long[]{0x0000000000200100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__22680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__KIdentifier__Group__2__Impl2708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__32739 = new BitSet(new long[]{0x0000000000200100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__32742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl2769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__42800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__KIdentifier__Group__4__Impl2828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__02869 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__02872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl2899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__12929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl2956 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__02991 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__02994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl3021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__13051 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__13054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__PersistentEntry__Group__1__Impl3082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__23113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl3140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__03176 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__03179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl3206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__13235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl3262 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__03297 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__03300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__QualifiedID__Group_1__0__Impl3328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__13359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl3386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_rule__Grana__GlobalResourcesAssignment_0_13424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJob_in_rule__Grana__JobsAssignment_13455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Job__NameAssignment_23486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Job__LayoutBeforeAnalysisAssignment_33522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Job__MeasureExecutionTimeAssignment_43566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_rule__Job__ResourcesAssignment_63605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_rule__Job__LayoutOptionsAssignment_83636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_rule__Job__AnalysesAssignment_103667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Job__OutputAssignment_123698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ResourceReference__ResourceRefsAssignment_13733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__GlobalResourceRef__NameAssignment_03768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_rule__GlobalResourceRef__ResourcesAssignment_13799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalResource__PathAssignment_03830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalResource__FilterAssignment_1_13861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment3892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_13923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_03954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_13985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_04016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_24047 = new BitSet(new long[]{0x0000000000000002L});

}