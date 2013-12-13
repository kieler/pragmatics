package de.cau.cs.kieler.kiml.config.text.ui.contentassist.antlr.internal; 

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
import de.cau.cs.kieler.kiml.config.text.services.LayoutConfigGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalLayoutConfigParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

    // delegates
    // delegators


        public InternalLayoutConfigParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalLayoutConfigParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalLayoutConfigParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g"; }


     
     	private LayoutConfigGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(LayoutConfigGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleKGraphElement"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:60:1: entryRuleKGraphElement : ruleKGraphElement EOF ;
    public final void entryRuleKGraphElement() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:61:1: ( ruleKGraphElement EOF )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:62:1: ruleKGraphElement EOF
            {
             before(grammarAccess.getKGraphElementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGraphElement_in_entryRuleKGraphElement61);
            ruleKGraphElement();

            state._fsp--;

             after(grammarAccess.getKGraphElementRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGraphElement68); 

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
    // $ANTLR end "entryRuleKGraphElement"


    // $ANTLR start "ruleKGraphElement"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:69:1: ruleKGraphElement : ( ( rule__KGraphElement__Group__0 ) ) ;
    public final void ruleKGraphElement() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:73:2: ( ( ( rule__KGraphElement__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:74:1: ( ( rule__KGraphElement__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:74:1: ( ( rule__KGraphElement__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:75:1: ( rule__KGraphElement__Group__0 )
            {
             before(grammarAccess.getKGraphElementAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:76:1: ( rule__KGraphElement__Group__0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:76:2: rule__KGraphElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__Group__0_in_ruleKGraphElement94);
            rule__KGraphElement__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKGraphElementAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKGraphElement"


    // $ANTLR start "entryRuleKIdentifier"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:88:1: entryRuleKIdentifier : ruleKIdentifier EOF ;
    public final void entryRuleKIdentifier() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:89:1: ( ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:90:1: ruleKIdentifier EOF
            {
             before(grammarAccess.getKIdentifierRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier121);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKIdentifierRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKIdentifier128); 

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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:97:1: ruleKIdentifier : ( ( rule__KIdentifier__Group__0 ) ) ;
    public final void ruleKIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:101:2: ( ( ( rule__KIdentifier__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:102:1: ( ( rule__KIdentifier__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:102:1: ( ( rule__KIdentifier__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:103:1: ( rule__KIdentifier__Group__0 )
            {
             before(grammarAccess.getKIdentifierAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:104:1: ( rule__KIdentifier__Group__0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:104:2: rule__KIdentifier__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier154);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:116:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:117:1: ( rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:118:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry181);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry188); 

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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:125:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:129:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:130:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:130:1: ( ( rule__PersistentEntry__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:131:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:132:1: ( rule__PersistentEntry__Group__0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:132:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry214);
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


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:144:1: entryRuleEString : ruleEString EOF ;
    public final void entryRuleEString() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:145:1: ( ruleEString EOF )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:146:1: ruleEString EOF
            {
             before(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString241);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getEStringRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString248); 

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
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:153:1: ruleEString : ( ( rule__EString__Alternatives ) ) ;
    public final void ruleEString() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:157:2: ( ( ( rule__EString__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:158:1: ( ( rule__EString__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:158:1: ( ( rule__EString__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:159:1: ( rule__EString__Alternatives )
            {
             before(grammarAccess.getEStringAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:160:1: ( rule__EString__Alternatives )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:160:2: rule__EString__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__EString__Alternatives_in_ruleEString274);
            rule__EString__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getEStringAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleQualifiedID"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:172:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:173:1: ( ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:174:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID301);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleQualifiedID308); 

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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:181:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:185:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:186:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:186:1: ( ( rule__QualifiedID__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:187:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:188:1: ( rule__QualifiedID__Group__0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:188:2: rule__QualifiedID__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID334);
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


    // $ANTLR start "rule__EString__Alternatives"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:200:1: rule__EString__Alternatives : ( ( RULE_STRING ) | ( RULE_ID ) );
    public final void rule__EString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:204:1: ( ( RULE_STRING ) | ( RULE_ID ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_STRING) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_ID) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:205:1: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:205:1: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:206:1: RULE_STRING
                    {
                     before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__EString__Alternatives370); 
                     after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:211:6: ( RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:211:6: ( RULE_ID )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:212:1: RULE_ID
                    {
                     before(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__EString__Alternatives387); 
                     after(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 

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
    // $ANTLR end "rule__EString__Alternatives"


    // $ANTLR start "rule__KGraphElement__Group__0"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:224:1: rule__KGraphElement__Group__0 : rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1 ;
    public final void rule__KGraphElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:228:1: ( rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:229:2: rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__Group__0__Impl_in_rule__KGraphElement__Group__0417);
            rule__KGraphElement__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__Group__1_in_rule__KGraphElement__Group__0420);
            rule__KGraphElement__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KGraphElement__Group__0"


    // $ANTLR start "rule__KGraphElement__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:236:1: rule__KGraphElement__Group__0__Impl : ( () ) ;
    public final void rule__KGraphElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:240:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:241:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:241:1: ( () )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:242:1: ()
            {
             before(grammarAccess.getKGraphElementAccess().getKNodeAction_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:243:1: ()
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:245:1: 
            {
            }

             after(grammarAccess.getKGraphElementAccess().getKNodeAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KGraphElement__Group__0__Impl"


    // $ANTLR start "rule__KGraphElement__Group__1"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:255:1: rule__KGraphElement__Group__1 : rule__KGraphElement__Group__1__Impl ;
    public final void rule__KGraphElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:259:1: ( rule__KGraphElement__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:260:2: rule__KGraphElement__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__Group__1__Impl_in_rule__KGraphElement__Group__1478);
            rule__KGraphElement__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KGraphElement__Group__1"


    // $ANTLR start "rule__KGraphElement__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:266:1: rule__KGraphElement__Group__1__Impl : ( ( rule__KGraphElement__DataAssignment_1 )* ) ;
    public final void rule__KGraphElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:270:1: ( ( ( rule__KGraphElement__DataAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:271:1: ( ( rule__KGraphElement__DataAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:271:1: ( ( rule__KGraphElement__DataAssignment_1 )* )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:272:1: ( rule__KGraphElement__DataAssignment_1 )*
            {
             before(grammarAccess.getKGraphElementAccess().getDataAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:273:1: ( rule__KGraphElement__DataAssignment_1 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=RULE_STRING && LA2_0<=RULE_ID)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:273:2: rule__KGraphElement__DataAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__DataAssignment_1_in_rule__KGraphElement__Group__1__Impl505);
            	    rule__KGraphElement__DataAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getKGraphElementAccess().getDataAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KGraphElement__Group__1__Impl"


    // $ANTLR start "rule__KIdentifier__Group__0"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:287:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:291:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:292:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__0540);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__0543);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:299:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:303:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:304:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:304:1: ( () )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:305:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:306:1: ()
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:308:1: 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:318:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:322:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:323:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__1601);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__1604);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:330:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:334:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:335:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:335:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:336:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:337:1: ( rule__KIdentifier__IdAssignment_1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:337:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl631);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:347:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:351:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:352:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__2661);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__2664);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:359:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:363:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:364:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:364:1: ( '{' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:365:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,11,FollowSets000.FOLLOW_11_in_rule__KIdentifier__Group__2__Impl692); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:378:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:382:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:383:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__3723);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__3726);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:390:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:394:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:395:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:395:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:396:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:397:1: ( rule__KIdentifier__Group_3__0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:397:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl753);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:407:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:411:1: ( rule__KIdentifier__Group__4__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:412:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__4784);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:418:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:422:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:423:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:423:1: ( '}' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:424:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,12,FollowSets000.FOLLOW_12_in_rule__KIdentifier__Group__4__Impl812); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:447:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:451:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:452:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__0853);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__0856);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:459:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:463:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:464:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:464:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:465:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:466:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:466:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl883);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:476:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:480:1: ( rule__KIdentifier__Group_3__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:481:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__1913);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:487:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:491:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:492:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:492:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:493:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:494:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_ID) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:494:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl940);
            	    rule__KIdentifier__PersistentEntriesAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:508:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:512:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:513:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__0975);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__0978);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:520:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:524:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:525:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:525:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:526:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:527:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:527:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl1005);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:537:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:541:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:542:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__11035);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__11038);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:549:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:553:1: ( ( ':' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:554:1: ( ':' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:554:1: ( ':' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:555:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,13,FollowSets000.FOLLOW_13_in_rule__PersistentEntry__Group__1__Impl1066); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:568:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:572:1: ( rule__PersistentEntry__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:573:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__21097);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:579:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:583:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:584:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:584:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:585:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:586:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:586:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl1124);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:602:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:606:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:607:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__01160);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__01163);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:614:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:618:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:619:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:619:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:620:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl1190); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:631:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:635:1: ( rule__QualifiedID__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:636:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__11219);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:642:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:646:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:647:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:647:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:648:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:649:1: ( rule__QualifiedID__Group_1__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==14) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:649:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl1246);
            	    rule__QualifiedID__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:663:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:667:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:668:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__01281);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__01284);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:675:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:679:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:680:1: ( '.' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:680:1: ( '.' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:681:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__QualifiedID__Group_1__0__Impl1312); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:694:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:698:1: ( rule__QualifiedID__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:699:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__11343);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:705:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:709:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:710:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:710:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:711:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl1370); 
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


    // $ANTLR start "rule__KGraphElement__DataAssignment_1"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:727:1: rule__KGraphElement__DataAssignment_1 : ( ruleKIdentifier ) ;
    public final void rule__KGraphElement__DataAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:731:1: ( ( ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:732:1: ( ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:732:1: ( ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:733:1: ruleKIdentifier
            {
             before(grammarAccess.getKGraphElementAccess().getDataKIdentifierParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleKIdentifier_in_rule__KGraphElement__DataAssignment_11408);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKGraphElementAccess().getDataKIdentifierParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KGraphElement__DataAssignment_1"


    // $ANTLR start "rule__KIdentifier__IdAssignment_1"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:742:1: rule__KIdentifier__IdAssignment_1 : ( ruleEString ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:746:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:747:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:747:1: ( ruleEString )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:748:1: ruleEString
            {
             before(grammarAccess.getKIdentifierAccess().getIdEStringParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rule__KIdentifier__IdAssignment_11439);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getKIdentifierAccess().getIdEStringParserRuleCall_1_0()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:757:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:761:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:762:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:762:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:763:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_01470);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:772:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:776:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:777:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:777:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:778:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_11501);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:787:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:791:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:792:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:792:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:793:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_01532);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:802:1: rule__PersistentEntry__ValueAssignment_2 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:806:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:807:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:807:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:808:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getValueQualifiedIDParserRuleCall_2_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__ValueAssignment_21563);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getPersistentEntryAccess().getValueQualifiedIDParserRuleCall_2_0()); 

            }


            }

        }
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


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKGraphElement_in_entryRuleKGraphElement61 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGraphElement68 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KGraphElement__Group__0_in_ruleKGraphElement94 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier121 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier128 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier154 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry181 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry188 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry214 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString241 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString248 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EString__Alternatives_in_ruleEString274 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID301 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID308 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID334 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__EString__Alternatives370 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__EString__Alternatives387 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KGraphElement__Group__0__Impl_in_rule__KGraphElement__Group__0417 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__KGraphElement__Group__1_in_rule__KGraphElement__Group__0420 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KGraphElement__Group__1__Impl_in_rule__KGraphElement__Group__1478 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KGraphElement__DataAssignment_1_in_rule__KGraphElement__Group__1__Impl505 = new BitSet(new long[]{0x0000000000000032L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__0540 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__0543 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__1601 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__1604 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl631 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__2661 = new BitSet(new long[]{0x0000000000001020L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__2664 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_rule__KIdentifier__Group__2__Impl692 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__3723 = new BitSet(new long[]{0x0000000000001020L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__3726 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl753 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__4784 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_12_in_rule__KIdentifier__Group__4__Impl812 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__0853 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__0856 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl883 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__1913 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl940 = new BitSet(new long[]{0x0000000000000022L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__0975 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__0978 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl1005 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__11035 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__11038 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_13_in_rule__PersistentEntry__Group__1__Impl1066 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__21097 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl1124 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__01160 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__01163 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl1190 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__11219 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl1246 = new BitSet(new long[]{0x0000000000004002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__01281 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__01284 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__QualifiedID__Group_1__0__Impl1312 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__11343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl1370 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKIdentifier_in_rule__KGraphElement__DataAssignment_11408 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rule__KIdentifier__IdAssignment_11439 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_01470 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_11501 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_01532 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__ValueAssignment_21563 = new BitSet(new long[]{0x0000000000000002L});
    }


}