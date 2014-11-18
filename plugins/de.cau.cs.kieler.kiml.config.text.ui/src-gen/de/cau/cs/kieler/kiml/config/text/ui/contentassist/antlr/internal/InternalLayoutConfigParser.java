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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BOOLEAN", "RULE_STRING", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_ID=8;
    public static final int RULE_STRING=5;
    public static final int T__15=15;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_NATURAL=7;
    public static final int RULE_WS=11;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;
    public static final int RULE_TFLOAT=6;

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


    // $ANTLR start "entryRulePropertyValue"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:144:1: entryRulePropertyValue : rulePropertyValue EOF ;
    public final void entryRulePropertyValue() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:145:1: ( rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:146:1: rulePropertyValue EOF
            {
             before(grammarAccess.getPropertyValueRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePropertyValue_in_entryRulePropertyValue241);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPropertyValueRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePropertyValue248); 

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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:153:1: rulePropertyValue : ( ( rule__PropertyValue__Alternatives ) ) ;
    public final void rulePropertyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:157:2: ( ( ( rule__PropertyValue__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:158:1: ( ( rule__PropertyValue__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:158:1: ( ( rule__PropertyValue__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:159:1: ( rule__PropertyValue__Alternatives )
            {
             before(grammarAccess.getPropertyValueAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:160:1: ( rule__PropertyValue__Alternatives )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:160:2: rule__PropertyValue__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue274);
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


    // $ANTLR start "entryRuleFloat"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:200:1: entryRuleFloat : ruleFloat EOF ;
    public final void entryRuleFloat() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:201:1: ( ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:202:1: ruleFloat EOF
            {
             before(grammarAccess.getFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleFloat_in_entryRuleFloat361);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleFloat368); 

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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:209:1: ruleFloat : ( ( rule__Float__Alternatives ) ) ;
    public final void ruleFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:213:2: ( ( ( rule__Float__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:214:1: ( ( rule__Float__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:214:1: ( ( rule__Float__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:215:1: ( rule__Float__Alternatives )
            {
             before(grammarAccess.getFloatAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:216:1: ( rule__Float__Alternatives )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:216:2: rule__Float__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__Float__Alternatives_in_ruleFloat394);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:228:1: rule__PropertyValue__Alternatives : ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) );
    public final void rule__PropertyValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:232:1: ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) )
            int alt1=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt1=1;
                }
                break;
            case RULE_STRING:
                {
                alt1=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt1=3;
                }
                break;
            case RULE_ID:
                {
                alt1=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:233:1: ( RULE_BOOLEAN )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:233:1: ( RULE_BOOLEAN )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:234:1: RULE_BOOLEAN
                    {
                     before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                    match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives430); 
                     after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:239:6: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:239:6: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:240:1: RULE_STRING
                    {
                     before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives447); 
                     after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:245:6: ( ruleFloat )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:245:6: ( ruleFloat )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:246:1: ruleFloat
                    {
                     before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                    pushFollow(FollowSets000.FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives464);
                    ruleFloat();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:251:6: ( ruleQualifiedID )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:251:6: ( ruleQualifiedID )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:252:1: ruleQualifiedID
                    {
                     before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                    pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives481);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:262:1: rule__Float__Alternatives : ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) );
    public final void rule__Float__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:266:1: ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_TFLOAT) ) {
                alt2=1;
            }
            else if ( (LA2_0==RULE_NATURAL) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:267:1: ( RULE_TFLOAT )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:267:1: ( RULE_TFLOAT )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:268:1: RULE_TFLOAT
                    {
                     before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                    match(input,RULE_TFLOAT,FollowSets000.FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives513); 
                     after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:273:6: ( RULE_NATURAL )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:273:6: ( RULE_NATURAL )
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:274:1: RULE_NATURAL
                    {
                     before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                    match(input,RULE_NATURAL,FollowSets000.FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives530); 
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


    // $ANTLR start "rule__KGraphElement__Group__0"
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:286:1: rule__KGraphElement__Group__0 : rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1 ;
    public final void rule__KGraphElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:290:1: ( rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:291:2: rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__Group__0__Impl_in_rule__KGraphElement__Group__0560);
            rule__KGraphElement__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__Group__1_in_rule__KGraphElement__Group__0563);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:298:1: rule__KGraphElement__Group__0__Impl : ( () ) ;
    public final void rule__KGraphElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:302:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:303:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:303:1: ( () )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:304:1: ()
            {
             before(grammarAccess.getKGraphElementAccess().getKNodeAction_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:305:1: ()
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:307:1: 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:317:1: rule__KGraphElement__Group__1 : rule__KGraphElement__Group__1__Impl ;
    public final void rule__KGraphElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:321:1: ( rule__KGraphElement__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:322:2: rule__KGraphElement__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__Group__1__Impl_in_rule__KGraphElement__Group__1621);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:328:1: rule__KGraphElement__Group__1__Impl : ( ( rule__KGraphElement__DataAssignment_1 )* ) ;
    public final void rule__KGraphElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:332:1: ( ( ( rule__KGraphElement__DataAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:333:1: ( ( rule__KGraphElement__DataAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:333:1: ( ( rule__KGraphElement__DataAssignment_1 )* )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:334:1: ( rule__KGraphElement__DataAssignment_1 )*
            {
             before(grammarAccess.getKGraphElementAccess().getDataAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:335:1: ( rule__KGraphElement__DataAssignment_1 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:335:2: rule__KGraphElement__DataAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KGraphElement__DataAssignment_1_in_rule__KGraphElement__Group__1__Impl648);
            	    rule__KGraphElement__DataAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:349:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:353:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:354:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__0683);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__0686);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:361:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:365:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:366:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:366:1: ( () )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:367:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:368:1: ()
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:370:1: 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:380:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:384:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:385:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__1744);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__1747);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:392:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:396:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:397:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:397:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:398:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:399:1: ( rule__KIdentifier__IdAssignment_1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:399:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl774);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:409:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:413:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:414:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__2804);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__2807);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:421:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:425:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:426:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:426:1: ( '{' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:427:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,12,FollowSets000.FOLLOW_12_in_rule__KIdentifier__Group__2__Impl835); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:440:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:444:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:445:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__3866);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__3869);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:452:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:456:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:457:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:457:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:458:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:459:1: ( rule__KIdentifier__Group_3__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:459:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl896);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:469:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:473:1: ( rule__KIdentifier__Group__4__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:474:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__4927);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:480:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:484:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:485:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:485:1: ( '}' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:486:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,13,FollowSets000.FOLLOW_13_in_rule__KIdentifier__Group__4__Impl955); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:509:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:513:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:514:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__0996);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__0999);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:521:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:525:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:526:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:526:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:527:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:528:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:528:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl1026);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:538:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:542:1: ( rule__KIdentifier__Group_3__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:543:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__11056);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:549:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:553:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:554:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:554:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:555:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:556:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ID) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:556:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl1083);
            	    rule__KIdentifier__PersistentEntriesAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:570:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:574:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:575:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__01118);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__01121);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:582:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:586:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:587:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:587:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:588:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:589:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:589:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl1148);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:599:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:603:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:604:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__11178);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__11181);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:611:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:615:1: ( ( ':' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:616:1: ( ':' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:616:1: ( ':' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:617:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__PersistentEntry__Group__1__Impl1209); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:630:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:634:1: ( rule__PersistentEntry__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:635:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__21240);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:641:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:645:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:646:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:646:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:647:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:648:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:648:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl1267);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:664:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:668:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:669:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__01303);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__01306);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:676:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:680:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:681:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:681:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:682:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl1333); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:693:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:697:1: ( rule__QualifiedID__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:698:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__11362);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:704:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:708:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:709:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:709:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:710:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:711:1: ( rule__QualifiedID__Group_1__0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==15) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:711:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl1389);
            	    rule__QualifiedID__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:725:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:729:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:730:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__01424);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__01427);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:737:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:741:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:742:1: ( '.' )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:742:1: ( '.' )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:743:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__QualifiedID__Group_1__0__Impl1455); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:756:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:760:1: ( rule__QualifiedID__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:761:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__11486);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:767:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:771:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:772:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:772:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:773:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl1513); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:789:1: rule__KGraphElement__DataAssignment_1 : ( ruleKIdentifier ) ;
    public final void rule__KGraphElement__DataAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:793:1: ( ( ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:794:1: ( ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:794:1: ( ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:795:1: ruleKIdentifier
            {
             before(grammarAccess.getKGraphElementAccess().getDataKIdentifierParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleKIdentifier_in_rule__KGraphElement__DataAssignment_11551);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:804:1: rule__KIdentifier__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:808:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:809:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:809:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:810:1: RULE_ID
            {
             before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_11582); 
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:819:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:823:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:824:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:824:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:825:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_01613);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:834:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:838:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:839:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:839:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:840:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_11644);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:849:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:853:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:854:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:854:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:855:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_01675);
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
    // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:864:1: rule__PersistentEntry__ValueAssignment_2 : ( rulePropertyValue ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:868:1: ( ( rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:869:1: ( rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:869:1: ( rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.config.text.ui/src-gen/de/cau/cs/kieler/kiml/config/text/ui/contentassist/antlr/internal/InternalLayoutConfig.g:870:1: rulePropertyValue
            {
             before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_21706);
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
        public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue241 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue248 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue274 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID301 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID308 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID334 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat361 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleFloat368 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Float__Alternatives_in_ruleFloat394 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives430 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives447 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives464 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives481 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives513 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives530 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KGraphElement__Group__0__Impl_in_rule__KGraphElement__Group__0560 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_rule__KGraphElement__Group__1_in_rule__KGraphElement__Group__0563 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KGraphElement__Group__1__Impl_in_rule__KGraphElement__Group__1621 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KGraphElement__DataAssignment_1_in_rule__KGraphElement__Group__1__Impl648 = new BitSet(new long[]{0x0000000000000102L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__0683 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__0686 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__1744 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__1747 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl774 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__2804 = new BitSet(new long[]{0x0000000000002100L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__2807 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_12_in_rule__KIdentifier__Group__2__Impl835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__3866 = new BitSet(new long[]{0x0000000000002100L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__3869 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl896 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__4927 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_13_in_rule__KIdentifier__Group__4__Impl955 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__0996 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__0999 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl1026 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__11056 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl1083 = new BitSet(new long[]{0x0000000000000102L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__01118 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__01121 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl1148 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__11178 = new BitSet(new long[]{0x00000000000001F0L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__11181 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__PersistentEntry__Group__1__Impl1209 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__21240 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl1267 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__01303 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__01306 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl1333 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__11362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl1389 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__01424 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__01427 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__QualifiedID__Group_1__0__Impl1455 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__11486 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl1513 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKIdentifier_in_rule__KGraphElement__DataAssignment_11551 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_11582 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_01613 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_11644 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_01675 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_21706 = new BitSet(new long[]{0x0000000000000002L});
    }


}