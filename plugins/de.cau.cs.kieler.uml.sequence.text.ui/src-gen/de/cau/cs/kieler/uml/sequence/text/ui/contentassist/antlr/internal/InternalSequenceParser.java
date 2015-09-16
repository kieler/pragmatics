package de.cau.cs.kieler.uml.sequence.text.ui.contentassist.antlr.internal; 

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
import de.cau.cs.kieler.uml.sequence.text.services.SequenceGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSequenceParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'lost'", "'found'", "'sync'", "'async'", "'response'", "'create'", "'sequenceDiagram'", "'lifeline'", "'as'", "'usecase'", "'to'", "'sourceNote'", "'targetNote'", "'note'", "'self'", "'destroy'", "'fragment'", "'{'", "'}'", "'label'", "'refinement'", "'lifelines'", "','", "'sourceStartEndExec'", "'sourceStartExec'", "'sourceEndExec'", "'targetStartEndExec'", "'targetStartExec'", "'targetEndExec'", "'startEndExec'", "'startExec'", "'endExec'"
    };
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
    public static final int T__29=29;
    public static final int RULE_INT_GREATER_ZERO=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
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
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalSequenceParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSequenceParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSequenceParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g"; }


     
     	private SequenceGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(SequenceGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleSequenceDiagram"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:60:1: entryRuleSequenceDiagram : ruleSequenceDiagram EOF ;
    public final void entryRuleSequenceDiagram() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:61:1: ( ruleSequenceDiagram EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:62:1: ruleSequenceDiagram EOF
            {
             before(grammarAccess.getSequenceDiagramRule()); 
            pushFollow(FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram61);
            ruleSequenceDiagram();

            state._fsp--;

             after(grammarAccess.getSequenceDiagramRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceDiagram68); 

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
    // $ANTLR end "entryRuleSequenceDiagram"


    // $ANTLR start "ruleSequenceDiagram"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:69:1: ruleSequenceDiagram : ( ( rule__SequenceDiagram__Group__0 ) ) ;
    public final void ruleSequenceDiagram() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:73:2: ( ( ( rule__SequenceDiagram__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:74:1: ( ( rule__SequenceDiagram__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:74:1: ( ( rule__SequenceDiagram__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:75:1: ( rule__SequenceDiagram__Group__0 )
            {
             before(grammarAccess.getSequenceDiagramAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:76:1: ( rule__SequenceDiagram__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:76:2: rule__SequenceDiagram__Group__0
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__0_in_ruleSequenceDiagram94);
            rule__SequenceDiagram__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSequenceDiagramAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSequenceDiagram"


    // $ANTLR start "entryRuleLifeline"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:88:1: entryRuleLifeline : ruleLifeline EOF ;
    public final void entryRuleLifeline() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:89:1: ( ruleLifeline EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:90:1: ruleLifeline EOF
            {
             before(grammarAccess.getLifelineRule()); 
            pushFollow(FOLLOW_ruleLifeline_in_entryRuleLifeline121);
            ruleLifeline();

            state._fsp--;

             after(grammarAccess.getLifelineRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLifeline128); 

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
    // $ANTLR end "entryRuleLifeline"


    // $ANTLR start "ruleLifeline"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:97:1: ruleLifeline : ( ( rule__Lifeline__Alternatives ) ) ;
    public final void ruleLifeline() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:101:2: ( ( ( rule__Lifeline__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__Lifeline__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__Lifeline__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:103:1: ( rule__Lifeline__Alternatives )
            {
             before(grammarAccess.getLifelineAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:1: ( rule__Lifeline__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:2: rule__Lifeline__Alternatives
            {
            pushFollow(FOLLOW_rule__Lifeline__Alternatives_in_ruleLifeline154);
            rule__Lifeline__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLifeline"


    // $ANTLR start "entryRuleInteraction"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:116:1: entryRuleInteraction : ruleInteraction EOF ;
    public final void entryRuleInteraction() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:117:1: ( ruleInteraction EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:118:1: ruleInteraction EOF
            {
             before(grammarAccess.getInteractionRule()); 
            pushFollow(FOLLOW_ruleInteraction_in_entryRuleInteraction181);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getInteractionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInteraction188); 

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
    // $ANTLR end "entryRuleInteraction"


    // $ANTLR start "ruleInteraction"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:125:1: ruleInteraction : ( ( rule__Interaction__Alternatives ) ) ;
    public final void ruleInteraction() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:129:2: ( ( ( rule__Interaction__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:130:1: ( ( rule__Interaction__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:130:1: ( ( rule__Interaction__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:131:1: ( rule__Interaction__Alternatives )
            {
             before(grammarAccess.getInteractionAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:132:1: ( rule__Interaction__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:132:2: rule__Interaction__Alternatives
            {
            pushFollow(FOLLOW_rule__Interaction__Alternatives_in_ruleInteraction214);
            rule__Interaction__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getInteractionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInteraction"


    // $ANTLR start "entryRuleTwoLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:144:1: entryRuleTwoLifelineMessage : ruleTwoLifelineMessage EOF ;
    public final void entryRuleTwoLifelineMessage() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:145:1: ( ruleTwoLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:146:1: ruleTwoLifelineMessage EOF
            {
             before(grammarAccess.getTwoLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage241);
            ruleTwoLifelineMessage();

            state._fsp--;

             after(grammarAccess.getTwoLifelineMessageRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTwoLifelineMessage248); 

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
    // $ANTLR end "entryRuleTwoLifelineMessage"


    // $ANTLR start "ruleTwoLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:153:1: ruleTwoLifelineMessage : ( ( rule__TwoLifelineMessage__Group__0 ) ) ;
    public final void ruleTwoLifelineMessage() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:157:2: ( ( ( rule__TwoLifelineMessage__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:158:1: ( ( rule__TwoLifelineMessage__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:158:1: ( ( rule__TwoLifelineMessage__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:159:1: ( rule__TwoLifelineMessage__Group__0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:160:1: ( rule__TwoLifelineMessage__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:160:2: rule__TwoLifelineMessage__Group__0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__0_in_ruleTwoLifelineMessage274);
            rule__TwoLifelineMessage__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTwoLifelineMessage"


    // $ANTLR start "entryRuleOneLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:172:1: entryRuleOneLifelineMessage : ruleOneLifelineMessage EOF ;
    public final void entryRuleOneLifelineMessage() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:173:1: ( ruleOneLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:174:1: ruleOneLifelineMessage EOF
            {
             before(grammarAccess.getOneLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage301);
            ruleOneLifelineMessage();

            state._fsp--;

             after(grammarAccess.getOneLifelineMessageRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineMessage308); 

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
    // $ANTLR end "entryRuleOneLifelineMessage"


    // $ANTLR start "ruleOneLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:181:1: ruleOneLifelineMessage : ( ( rule__OneLifelineMessage__Group__0 ) ) ;
    public final void ruleOneLifelineMessage() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:185:2: ( ( ( rule__OneLifelineMessage__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:186:1: ( ( rule__OneLifelineMessage__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:186:1: ( ( rule__OneLifelineMessage__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:187:1: ( rule__OneLifelineMessage__Group__0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:188:1: ( rule__OneLifelineMessage__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:188:2: rule__OneLifelineMessage__Group__0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__0_in_ruleOneLifelineMessage334);
            rule__OneLifelineMessage__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOneLifelineMessage"


    // $ANTLR start "entryRuleSelfMessage"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:200:1: entryRuleSelfMessage : ruleSelfMessage EOF ;
    public final void entryRuleSelfMessage() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:201:1: ( ruleSelfMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:202:1: ruleSelfMessage EOF
            {
             before(grammarAccess.getSelfMessageRule()); 
            pushFollow(FOLLOW_ruleSelfMessage_in_entryRuleSelfMessage361);
            ruleSelfMessage();

            state._fsp--;

             after(grammarAccess.getSelfMessageRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelfMessage368); 

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
    // $ANTLR end "entryRuleSelfMessage"


    // $ANTLR start "ruleSelfMessage"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:209:1: ruleSelfMessage : ( ( rule__SelfMessage__Group__0 ) ) ;
    public final void ruleSelfMessage() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:213:2: ( ( ( rule__SelfMessage__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:214:1: ( ( rule__SelfMessage__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:214:1: ( ( rule__SelfMessage__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:215:1: ( rule__SelfMessage__Group__0 )
            {
             before(grammarAccess.getSelfMessageAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:216:1: ( rule__SelfMessage__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:216:2: rule__SelfMessage__Group__0
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group__0_in_ruleSelfMessage394);
            rule__SelfMessage__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSelfMessageAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSelfMessage"


    // $ANTLR start "entryRuleOneLifelineNote"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:228:1: entryRuleOneLifelineNote : ruleOneLifelineNote EOF ;
    public final void entryRuleOneLifelineNote() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:229:1: ( ruleOneLifelineNote EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:230:1: ruleOneLifelineNote EOF
            {
             before(grammarAccess.getOneLifelineNoteRule()); 
            pushFollow(FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote421);
            ruleOneLifelineNote();

            state._fsp--;

             after(grammarAccess.getOneLifelineNoteRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineNote428); 

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
    // $ANTLR end "entryRuleOneLifelineNote"


    // $ANTLR start "ruleOneLifelineNote"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:237:1: ruleOneLifelineNote : ( ( rule__OneLifelineNote__Group__0 ) ) ;
    public final void ruleOneLifelineNote() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:241:2: ( ( ( rule__OneLifelineNote__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:242:1: ( ( rule__OneLifelineNote__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:242:1: ( ( rule__OneLifelineNote__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:243:1: ( rule__OneLifelineNote__Group__0 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:244:1: ( rule__OneLifelineNote__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:244:2: rule__OneLifelineNote__Group__0
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__0_in_ruleOneLifelineNote454);
            rule__OneLifelineNote__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineNoteAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOneLifelineNote"


    // $ANTLR start "entryRuleDestroyLifelineEvent"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:256:1: entryRuleDestroyLifelineEvent : ruleDestroyLifelineEvent EOF ;
    public final void entryRuleDestroyLifelineEvent() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:257:1: ( ruleDestroyLifelineEvent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:258:1: ruleDestroyLifelineEvent EOF
            {
             before(grammarAccess.getDestroyLifelineEventRule()); 
            pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent481);
            ruleDestroyLifelineEvent();

            state._fsp--;

             after(grammarAccess.getDestroyLifelineEventRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroyLifelineEvent488); 

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
    // $ANTLR end "entryRuleDestroyLifelineEvent"


    // $ANTLR start "ruleDestroyLifelineEvent"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:265:1: ruleDestroyLifelineEvent : ( ( rule__DestroyLifelineEvent__Group__0 ) ) ;
    public final void ruleDestroyLifelineEvent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:269:2: ( ( ( rule__DestroyLifelineEvent__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:270:1: ( ( rule__DestroyLifelineEvent__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:270:1: ( ( rule__DestroyLifelineEvent__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:271:1: ( rule__DestroyLifelineEvent__Group__0 )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:272:1: ( rule__DestroyLifelineEvent__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:272:2: rule__DestroyLifelineEvent__Group__0
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__0_in_ruleDestroyLifelineEvent514);
            rule__DestroyLifelineEvent__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDestroyLifelineEventAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDestroyLifelineEvent"


    // $ANTLR start "entryRuleFragment"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:284:1: entryRuleFragment : ruleFragment EOF ;
    public final void entryRuleFragment() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:285:1: ( ruleFragment EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:286:1: ruleFragment EOF
            {
             before(grammarAccess.getFragmentRule()); 
            pushFollow(FOLLOW_ruleFragment_in_entryRuleFragment541);
            ruleFragment();

            state._fsp--;

             after(grammarAccess.getFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragment548); 

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
    // $ANTLR end "entryRuleFragment"


    // $ANTLR start "ruleFragment"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:293:1: ruleFragment : ( ( rule__Fragment__Group__0 ) ) ;
    public final void ruleFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:297:2: ( ( ( rule__Fragment__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__Fragment__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__Fragment__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:299:1: ( rule__Fragment__Group__0 )
            {
             before(grammarAccess.getFragmentAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:1: ( rule__Fragment__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:2: rule__Fragment__Group__0
            {
            pushFollow(FOLLOW_rule__Fragment__Group__0_in_ruleFragment574);
            rule__Fragment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFragmentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFragment"


    // $ANTLR start "entryRuleSection"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:312:1: entryRuleSection : ruleSection EOF ;
    public final void entryRuleSection() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:313:1: ( ruleSection EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:314:1: ruleSection EOF
            {
             before(grammarAccess.getSectionRule()); 
            pushFollow(FOLLOW_ruleSection_in_entryRuleSection601);
            ruleSection();

            state._fsp--;

             after(grammarAccess.getSectionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSection608); 

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
    // $ANTLR end "entryRuleSection"


    // $ANTLR start "ruleSection"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:321:1: ruleSection : ( ( rule__Section__Group__0 ) ) ;
    public final void ruleSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:325:2: ( ( ( rule__Section__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:326:1: ( ( rule__Section__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:326:1: ( ( rule__Section__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:327:1: ( rule__Section__Group__0 )
            {
             before(grammarAccess.getSectionAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:328:1: ( rule__Section__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:328:2: rule__Section__Group__0
            {
            pushFollow(FOLLOW_rule__Section__Group__0_in_ruleSection634);
            rule__Section__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSectionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSection"


    // $ANTLR start "entryRuleRefinement"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:340:1: entryRuleRefinement : ruleRefinement EOF ;
    public final void entryRuleRefinement() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:341:1: ( ruleRefinement EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:342:1: ruleRefinement EOF
            {
             before(grammarAccess.getRefinementRule()); 
            pushFollow(FOLLOW_ruleRefinement_in_entryRuleRefinement661);
            ruleRefinement();

            state._fsp--;

             after(grammarAccess.getRefinementRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRefinement668); 

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
    // $ANTLR end "entryRuleRefinement"


    // $ANTLR start "ruleRefinement"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:349:1: ruleRefinement : ( ( rule__Refinement__Group__0 ) ) ;
    public final void ruleRefinement() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:353:2: ( ( ( rule__Refinement__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:354:1: ( ( rule__Refinement__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:354:1: ( ( rule__Refinement__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:355:1: ( rule__Refinement__Group__0 )
            {
             before(grammarAccess.getRefinementAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:356:1: ( rule__Refinement__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:356:2: rule__Refinement__Group__0
            {
            pushFollow(FOLLOW_rule__Refinement__Group__0_in_ruleRefinement694);
            rule__Refinement__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRefinementAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRefinement"


    // $ANTLR start "ruleMessageType"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:369:1: ruleMessageType : ( ( rule__MessageType__Alternatives ) ) ;
    public final void ruleMessageType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:373:1: ( ( ( rule__MessageType__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:374:1: ( ( rule__MessageType__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:374:1: ( ( rule__MessageType__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:375:1: ( rule__MessageType__Alternatives )
            {
             before(grammarAccess.getMessageTypeAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:376:1: ( rule__MessageType__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:376:2: rule__MessageType__Alternatives
            {
            pushFollow(FOLLOW_rule__MessageType__Alternatives_in_ruleMessageType731);
            rule__MessageType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getMessageTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMessageType"


    // $ANTLR start "rule__Lifeline__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:387:1: rule__Lifeline__Alternatives : ( ( ( rule__Lifeline__Group_0__0 ) ) | ( ( rule__Lifeline__Group_1__0 ) ) );
    public final void rule__Lifeline__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:391:1: ( ( ( rule__Lifeline__Group_0__0 ) ) | ( ( rule__Lifeline__Group_1__0 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==19) ) {
                alt1=1;
            }
            else if ( (LA1_0==21) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:392:1: ( ( rule__Lifeline__Group_0__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:392:1: ( ( rule__Lifeline__Group_0__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:393:1: ( rule__Lifeline__Group_0__0 )
                    {
                     before(grammarAccess.getLifelineAccess().getGroup_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:394:1: ( rule__Lifeline__Group_0__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:394:2: rule__Lifeline__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Lifeline__Group_0__0_in_rule__Lifeline__Alternatives766);
                    rule__Lifeline__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getLifelineAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:398:6: ( ( rule__Lifeline__Group_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:398:6: ( ( rule__Lifeline__Group_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:399:1: ( rule__Lifeline__Group_1__0 )
                    {
                     before(grammarAccess.getLifelineAccess().getGroup_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:400:1: ( rule__Lifeline__Group_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:400:2: rule__Lifeline__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Lifeline__Group_1__0_in_rule__Lifeline__Alternatives784);
                    rule__Lifeline__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getLifelineAccess().getGroup_1()); 

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
    // $ANTLR end "rule__Lifeline__Alternatives"


    // $ANTLR start "rule__Interaction__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:409:1: rule__Interaction__Alternatives : ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineNote ) | ( ruleSelfMessage ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) );
    public final void rule__Interaction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:413:1: ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineNote ) | ( ruleSelfMessage ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) )
            int alt2=7;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:414:1: ( ruleTwoLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:414:1: ( ruleTwoLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:415:1: ruleTwoLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives817);
                    ruleTwoLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:420:6: ( ruleOneLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:420:6: ( ruleOneLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:421:1: ruleOneLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives834);
                    ruleOneLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:426:6: ( ruleFragment )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:426:6: ( ruleFragment )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:427:1: ruleFragment
                    {
                     before(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFragment_in_rule__Interaction__Alternatives851);
                    ruleFragment();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:432:6: ( ruleOneLifelineNote )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:432:6: ( ruleOneLifelineNote )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:433:1: ruleOneLifelineNote
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives868);
                    ruleOneLifelineNote();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:438:6: ( ruleSelfMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:438:6: ( ruleSelfMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:439:1: ruleSelfMessage
                    {
                     before(grammarAccess.getInteractionAccess().getSelfMessageParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleSelfMessage_in_rule__Interaction__Alternatives885);
                    ruleSelfMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getSelfMessageParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:444:6: ( ruleDestroyLifelineEvent )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:444:6: ( ruleDestroyLifelineEvent )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:445:1: ruleDestroyLifelineEvent
                    {
                     before(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_rule__Interaction__Alternatives902);
                    ruleDestroyLifelineEvent();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:450:6: ( ruleRefinement )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:450:6: ( ruleRefinement )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:451:1: ruleRefinement
                    {
                     before(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 
                    pushFollow(FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives919);
                    ruleRefinement();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 

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
    // $ANTLR end "rule__Interaction__Alternatives"


    // $ANTLR start "rule__TwoLifelineMessage__Alternatives_5"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:461:1: rule__TwoLifelineMessage__Alternatives_5 : ( ( ( rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__SourceStartExecAssignment_5_1 ) ) | ( ( rule__TwoLifelineMessage__Group_5_2__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:465:1: ( ( ( rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__SourceStartExecAssignment_5_1 ) ) | ( ( rule__TwoLifelineMessage__Group_5_2__0 ) ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt3=1;
                }
                break;
            case 36:
                {
                alt3=2;
                }
                break;
            case 37:
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
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:466:1: ( ( rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:466:1: ( ( rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:467:1: ( rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartEndExecAssignment_5_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:468:1: ( rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:468:2: rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_5951);
                    rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartEndExecAssignment_5_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:472:6: ( ( rule__TwoLifelineMessage__SourceStartExecAssignment_5_1 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:472:6: ( ( rule__TwoLifelineMessage__SourceStartExecAssignment_5_1 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:473:1: ( rule__TwoLifelineMessage__SourceStartExecAssignment_5_1 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartExecAssignment_5_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:474:1: ( rule__TwoLifelineMessage__SourceStartExecAssignment_5_1 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:474:2: rule__TwoLifelineMessage__SourceStartExecAssignment_5_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceStartExecAssignment_5_1_in_rule__TwoLifelineMessage__Alternatives_5969);
                    rule__TwoLifelineMessage__SourceStartExecAssignment_5_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartExecAssignment_5_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:478:6: ( ( rule__TwoLifelineMessage__Group_5_2__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:478:6: ( ( rule__TwoLifelineMessage__Group_5_2__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:479:1: ( rule__TwoLifelineMessage__Group_5_2__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_5_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:480:1: ( rule__TwoLifelineMessage__Group_5_2__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:480:2: rule__TwoLifelineMessage__Group_5_2__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_2__0_in_rule__TwoLifelineMessage__Alternatives_5987);
                    rule__TwoLifelineMessage__Group_5_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getGroup_5_2()); 

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
    // $ANTLR end "rule__TwoLifelineMessage__Alternatives_5"


    // $ANTLR start "rule__TwoLifelineMessage__Alternatives_6"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:489:1: rule__TwoLifelineMessage__Alternatives_6 : ( ( ( rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__TargetStartExecAssignment_6_1 ) ) | ( ( rule__TwoLifelineMessage__Group_6_2__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:493:1: ( ( ( rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__TargetStartExecAssignment_6_1 ) ) | ( ( rule__TwoLifelineMessage__Group_6_2__0 ) ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt4=1;
                }
                break;
            case 39:
                {
                alt4=2;
                }
                break;
            case 40:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:494:1: ( ( rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:494:1: ( ( rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:495:1: ( rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartEndExecAssignment_6_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:496:1: ( rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:496:2: rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_61020);
                    rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartEndExecAssignment_6_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:500:6: ( ( rule__TwoLifelineMessage__TargetStartExecAssignment_6_1 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:500:6: ( ( rule__TwoLifelineMessage__TargetStartExecAssignment_6_1 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:501:1: ( rule__TwoLifelineMessage__TargetStartExecAssignment_6_1 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartExecAssignment_6_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:502:1: ( rule__TwoLifelineMessage__TargetStartExecAssignment_6_1 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:502:2: rule__TwoLifelineMessage__TargetStartExecAssignment_6_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetStartExecAssignment_6_1_in_rule__TwoLifelineMessage__Alternatives_61038);
                    rule__TwoLifelineMessage__TargetStartExecAssignment_6_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartExecAssignment_6_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:506:6: ( ( rule__TwoLifelineMessage__Group_6_2__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:506:6: ( ( rule__TwoLifelineMessage__Group_6_2__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:507:1: ( rule__TwoLifelineMessage__Group_6_2__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_6_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:508:1: ( rule__TwoLifelineMessage__Group_6_2__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:508:2: rule__TwoLifelineMessage__Group_6_2__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_2__0_in_rule__TwoLifelineMessage__Alternatives_61056);
                    rule__TwoLifelineMessage__Group_6_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getGroup_6_2()); 

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
    // $ANTLR end "rule__TwoLifelineMessage__Alternatives_6"


    // $ANTLR start "rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:517:1: rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 : ( ( 'lost' ) | ( 'found' ) );
    public final void rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:521:1: ( ( 'lost' ) | ( 'found' ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==12) ) {
                alt5=1;
            }
            else if ( (LA5_0==13) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:522:1: ( 'lost' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:522:1: ( 'lost' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:523:1: 'lost'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundLostKeyword_2_0_0()); 
                    match(input,12,FOLLOW_12_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_01090); 
                     after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundLostKeyword_2_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:530:6: ( 'found' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:530:6: ( 'found' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:531:1: 'found'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundFoundKeyword_2_0_1()); 
                    match(input,13,FOLLOW_13_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_01110); 
                     after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundFoundKeyword_2_0_1()); 

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
    // $ANTLR end "rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0"


    // $ANTLR start "rule__OneLifelineMessage__Alternatives_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:543:1: rule__OneLifelineMessage__Alternatives_4 : ( ( ( rule__OneLifelineMessage__StartEndExecAssignment_4_0 ) ) | ( ( rule__OneLifelineMessage__StartExecAssignment_4_1 ) ) | ( ( rule__OneLifelineMessage__Group_4_2__0 ) ) );
    public final void rule__OneLifelineMessage__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:547:1: ( ( ( rule__OneLifelineMessage__StartEndExecAssignment_4_0 ) ) | ( ( rule__OneLifelineMessage__StartExecAssignment_4_1 ) ) | ( ( rule__OneLifelineMessage__Group_4_2__0 ) ) )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt6=1;
                }
                break;
            case 42:
                {
                alt6=2;
                }
                break;
            case 43:
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:548:1: ( ( rule__OneLifelineMessage__StartEndExecAssignment_4_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:548:1: ( ( rule__OneLifelineMessage__StartEndExecAssignment_4_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:549:1: ( rule__OneLifelineMessage__StartEndExecAssignment_4_0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getStartEndExecAssignment_4_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:550:1: ( rule__OneLifelineMessage__StartEndExecAssignment_4_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:550:2: rule__OneLifelineMessage__StartEndExecAssignment_4_0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__StartEndExecAssignment_4_0_in_rule__OneLifelineMessage__Alternatives_41144);
                    rule__OneLifelineMessage__StartEndExecAssignment_4_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getStartEndExecAssignment_4_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:554:6: ( ( rule__OneLifelineMessage__StartExecAssignment_4_1 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:554:6: ( ( rule__OneLifelineMessage__StartExecAssignment_4_1 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:555:1: ( rule__OneLifelineMessage__StartExecAssignment_4_1 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getStartExecAssignment_4_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:556:1: ( rule__OneLifelineMessage__StartExecAssignment_4_1 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:556:2: rule__OneLifelineMessage__StartExecAssignment_4_1
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__StartExecAssignment_4_1_in_rule__OneLifelineMessage__Alternatives_41162);
                    rule__OneLifelineMessage__StartExecAssignment_4_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getStartExecAssignment_4_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:560:6: ( ( rule__OneLifelineMessage__Group_4_2__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:560:6: ( ( rule__OneLifelineMessage__Group_4_2__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:561:1: ( rule__OneLifelineMessage__Group_4_2__0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getGroup_4_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:562:1: ( rule__OneLifelineMessage__Group_4_2__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:562:2: rule__OneLifelineMessage__Group_4_2__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_2__0_in_rule__OneLifelineMessage__Alternatives_41180);
                    rule__OneLifelineMessage__Group_4_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getGroup_4_2()); 

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
    // $ANTLR end "rule__OneLifelineMessage__Alternatives_4"


    // $ANTLR start "rule__SelfMessage__MessageTypeAlternatives_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:571:1: rule__SelfMessage__MessageTypeAlternatives_1_0 : ( ( 'sync' ) | ( 'async' ) | ( 'response' ) );
    public final void rule__SelfMessage__MessageTypeAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:575:1: ( ( 'sync' ) | ( 'async' ) | ( 'response' ) )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt7=1;
                }
                break;
            case 15:
                {
                alt7=2;
                }
                break;
            case 16:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:576:1: ( 'sync' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:576:1: ( 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:577:1: 'sync'
                    {
                     before(grammarAccess.getSelfMessageAccess().getMessageTypeSyncKeyword_1_0_0()); 
                    match(input,14,FOLLOW_14_in_rule__SelfMessage__MessageTypeAlternatives_1_01214); 
                     after(grammarAccess.getSelfMessageAccess().getMessageTypeSyncKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:584:6: ( 'async' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:584:6: ( 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:585:1: 'async'
                    {
                     before(grammarAccess.getSelfMessageAccess().getMessageTypeAsyncKeyword_1_0_1()); 
                    match(input,15,FOLLOW_15_in_rule__SelfMessage__MessageTypeAlternatives_1_01234); 
                     after(grammarAccess.getSelfMessageAccess().getMessageTypeAsyncKeyword_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:592:6: ( 'response' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:592:6: ( 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:593:1: 'response'
                    {
                     before(grammarAccess.getSelfMessageAccess().getMessageTypeResponseKeyword_1_0_2()); 
                    match(input,16,FOLLOW_16_in_rule__SelfMessage__MessageTypeAlternatives_1_01254); 
                     after(grammarAccess.getSelfMessageAccess().getMessageTypeResponseKeyword_1_0_2()); 

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
    // $ANTLR end "rule__SelfMessage__MessageTypeAlternatives_1_0"


    // $ANTLR start "rule__SelfMessage__Alternatives_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:605:1: rule__SelfMessage__Alternatives_4 : ( ( ( rule__SelfMessage__StartEndExecAssignment_4_0 ) ) | ( ( rule__SelfMessage__StartExecAssignment_4_1 ) ) | ( ( rule__SelfMessage__Group_4_2__0 ) ) );
    public final void rule__SelfMessage__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:609:1: ( ( ( rule__SelfMessage__StartEndExecAssignment_4_0 ) ) | ( ( rule__SelfMessage__StartExecAssignment_4_1 ) ) | ( ( rule__SelfMessage__Group_4_2__0 ) ) )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt8=1;
                }
                break;
            case 42:
                {
                alt8=2;
                }
                break;
            case 43:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:610:1: ( ( rule__SelfMessage__StartEndExecAssignment_4_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:610:1: ( ( rule__SelfMessage__StartEndExecAssignment_4_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:611:1: ( rule__SelfMessage__StartEndExecAssignment_4_0 )
                    {
                     before(grammarAccess.getSelfMessageAccess().getStartEndExecAssignment_4_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:612:1: ( rule__SelfMessage__StartEndExecAssignment_4_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:612:2: rule__SelfMessage__StartEndExecAssignment_4_0
                    {
                    pushFollow(FOLLOW_rule__SelfMessage__StartEndExecAssignment_4_0_in_rule__SelfMessage__Alternatives_41288);
                    rule__SelfMessage__StartEndExecAssignment_4_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getSelfMessageAccess().getStartEndExecAssignment_4_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:616:6: ( ( rule__SelfMessage__StartExecAssignment_4_1 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:616:6: ( ( rule__SelfMessage__StartExecAssignment_4_1 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:617:1: ( rule__SelfMessage__StartExecAssignment_4_1 )
                    {
                     before(grammarAccess.getSelfMessageAccess().getStartExecAssignment_4_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:618:1: ( rule__SelfMessage__StartExecAssignment_4_1 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:618:2: rule__SelfMessage__StartExecAssignment_4_1
                    {
                    pushFollow(FOLLOW_rule__SelfMessage__StartExecAssignment_4_1_in_rule__SelfMessage__Alternatives_41306);
                    rule__SelfMessage__StartExecAssignment_4_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getSelfMessageAccess().getStartExecAssignment_4_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:622:6: ( ( rule__SelfMessage__Group_4_2__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:622:6: ( ( rule__SelfMessage__Group_4_2__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:623:1: ( rule__SelfMessage__Group_4_2__0 )
                    {
                     before(grammarAccess.getSelfMessageAccess().getGroup_4_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:624:1: ( rule__SelfMessage__Group_4_2__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:624:2: rule__SelfMessage__Group_4_2__0
                    {
                    pushFollow(FOLLOW_rule__SelfMessage__Group_4_2__0_in_rule__SelfMessage__Alternatives_41324);
                    rule__SelfMessage__Group_4_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getSelfMessageAccess().getGroup_4_2()); 

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
    // $ANTLR end "rule__SelfMessage__Alternatives_4"


    // $ANTLR start "rule__MessageType__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:633:1: rule__MessageType__Alternatives : ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) );
    public final void rule__MessageType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:637:1: ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt9=1;
                }
                break;
            case 17:
                {
                alt9=2;
                }
                break;
            case 16:
                {
                alt9=3;
                }
                break;
            case 14:
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
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:638:1: ( ( 'async' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:638:1: ( ( 'async' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:639:1: ( 'async' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:640:1: ( 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:640:3: 'async'
                    {
                    match(input,15,FOLLOW_15_in_rule__MessageType__Alternatives1358); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:645:6: ( ( 'create' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:645:6: ( ( 'create' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:646:1: ( 'create' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:647:1: ( 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:647:3: 'create'
                    {
                    match(input,17,FOLLOW_17_in_rule__MessageType__Alternatives1379); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:652:6: ( ( 'response' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:652:6: ( ( 'response' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:653:1: ( 'response' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:654:1: ( 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:654:3: 'response'
                    {
                    match(input,16,FOLLOW_16_in_rule__MessageType__Alternatives1400); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:659:6: ( ( 'sync' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:659:6: ( ( 'sync' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:660:1: ( 'sync' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getSyncEnumLiteralDeclaration_3()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:661:1: ( 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:661:3: 'sync'
                    {
                    match(input,14,FOLLOW_14_in_rule__MessageType__Alternatives1421); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getSyncEnumLiteralDeclaration_3()); 

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
    // $ANTLR end "rule__MessageType__Alternatives"


    // $ANTLR start "rule__SequenceDiagram__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:673:1: rule__SequenceDiagram__Group__0 : rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 ;
    public final void rule__SequenceDiagram__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:677:1: ( rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:678:2: rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01454);
            rule__SequenceDiagram__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01457);
            rule__SequenceDiagram__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__0"


    // $ANTLR start "rule__SequenceDiagram__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:685:1: rule__SequenceDiagram__Group__0__Impl : ( () ) ;
    public final void rule__SequenceDiagram__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:689:1: ( ( () ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:690:1: ( () )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:690:1: ( () )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:691:1: ()
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramAction_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:692:1: ()
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:694:1: 
            {
            }

             after(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__0__Impl"


    // $ANTLR start "rule__SequenceDiagram__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:704:1: rule__SequenceDiagram__Group__1 : rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 ;
    public final void rule__SequenceDiagram__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:708:1: ( rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:709:2: rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11515);
            rule__SequenceDiagram__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11518);
            rule__SequenceDiagram__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__1"


    // $ANTLR start "rule__SequenceDiagram__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:716:1: rule__SequenceDiagram__Group__1__Impl : ( 'sequenceDiagram' ) ;
    public final void rule__SequenceDiagram__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:720:1: ( ( 'sequenceDiagram' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:721:1: ( 'sequenceDiagram' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:721:1: ( 'sequenceDiagram' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:722:1: 'sequenceDiagram'
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramKeyword_1()); 
            match(input,18,FOLLOW_18_in_rule__SequenceDiagram__Group__1__Impl1546); 
             after(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__1__Impl"


    // $ANTLR start "rule__SequenceDiagram__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:735:1: rule__SequenceDiagram__Group__2 : rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 ;
    public final void rule__SequenceDiagram__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:739:1: ( rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:740:2: rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21577);
            rule__SequenceDiagram__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21580);
            rule__SequenceDiagram__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__2"


    // $ANTLR start "rule__SequenceDiagram__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:747:1: rule__SequenceDiagram__Group__2__Impl : ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) ;
    public final void rule__SequenceDiagram__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:751:1: ( ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:752:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:752:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:753:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:754:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:754:2: rule__SequenceDiagram__DiagramNameAssignment_2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1607);
            rule__SequenceDiagram__DiagramNameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSequenceDiagramAccess().getDiagramNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__2__Impl"


    // $ANTLR start "rule__SequenceDiagram__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:764:1: rule__SequenceDiagram__Group__3 : rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 ;
    public final void rule__SequenceDiagram__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:768:1: ( rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:769:2: rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31637);
            rule__SequenceDiagram__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31640);
            rule__SequenceDiagram__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__3"


    // $ANTLR start "rule__SequenceDiagram__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:776:1: rule__SequenceDiagram__Group__3__Impl : ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* ) ;
    public final void rule__SequenceDiagram__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:780:1: ( ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:781:1: ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:781:1: ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:782:1: ( rule__SequenceDiagram__LifelinesAssignment_3 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:783:1: ( rule__SequenceDiagram__LifelinesAssignment_3 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==19||LA10_0==21) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:783:2: rule__SequenceDiagram__LifelinesAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__LifelinesAssignment_3_in_rule__SequenceDiagram__Group__3__Impl1667);
            	    rule__SequenceDiagram__LifelinesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getSequenceDiagramAccess().getLifelinesAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__3__Impl"


    // $ANTLR start "rule__SequenceDiagram__Group__4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:793:1: rule__SequenceDiagram__Group__4 : rule__SequenceDiagram__Group__4__Impl ;
    public final void rule__SequenceDiagram__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:797:1: ( rule__SequenceDiagram__Group__4__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:798:2: rule__SequenceDiagram__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41698);
            rule__SequenceDiagram__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__4"


    // $ANTLR start "rule__SequenceDiagram__Group__4__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:804:1: rule__SequenceDiagram__Group__4__Impl : ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* ) ;
    public final void rule__SequenceDiagram__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:808:1: ( ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:809:1: ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:809:1: ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:810:1: ( rule__SequenceDiagram__InteractionsAssignment_4 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:811:1: ( rule__SequenceDiagram__InteractionsAssignment_4 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID||LA11_0==28||LA11_0==32) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:811:2: rule__SequenceDiagram__InteractionsAssignment_4
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__InteractionsAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1725);
            	    rule__SequenceDiagram__InteractionsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getSequenceDiagramAccess().getInteractionsAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__4__Impl"


    // $ANTLR start "rule__Lifeline__Group_0__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:831:1: rule__Lifeline__Group_0__0 : rule__Lifeline__Group_0__0__Impl rule__Lifeline__Group_0__1 ;
    public final void rule__Lifeline__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:835:1: ( rule__Lifeline__Group_0__0__Impl rule__Lifeline__Group_0__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:836:2: rule__Lifeline__Group_0__0__Impl rule__Lifeline__Group_0__1
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_0__0__Impl_in_rule__Lifeline__Group_0__01766);
            rule__Lifeline__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group_0__1_in_rule__Lifeline__Group_0__01769);
            rule__Lifeline__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__0"


    // $ANTLR start "rule__Lifeline__Group_0__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:843:1: rule__Lifeline__Group_0__0__Impl : ( 'lifeline' ) ;
    public final void rule__Lifeline__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:847:1: ( ( 'lifeline' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:848:1: ( 'lifeline' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:848:1: ( 'lifeline' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:849:1: 'lifeline'
            {
             before(grammarAccess.getLifelineAccess().getLifelineKeyword_0_0()); 
            match(input,19,FOLLOW_19_in_rule__Lifeline__Group_0__0__Impl1797); 
             after(grammarAccess.getLifelineAccess().getLifelineKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__0__Impl"


    // $ANTLR start "rule__Lifeline__Group_0__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:862:1: rule__Lifeline__Group_0__1 : rule__Lifeline__Group_0__1__Impl rule__Lifeline__Group_0__2 ;
    public final void rule__Lifeline__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:866:1: ( rule__Lifeline__Group_0__1__Impl rule__Lifeline__Group_0__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:867:2: rule__Lifeline__Group_0__1__Impl rule__Lifeline__Group_0__2
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_0__1__Impl_in_rule__Lifeline__Group_0__11828);
            rule__Lifeline__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group_0__2_in_rule__Lifeline__Group_0__11831);
            rule__Lifeline__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__1"


    // $ANTLR start "rule__Lifeline__Group_0__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:874:1: rule__Lifeline__Group_0__1__Impl : ( ( rule__Lifeline__CaptionAssignment_0_1 ) ) ;
    public final void rule__Lifeline__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:878:1: ( ( ( rule__Lifeline__CaptionAssignment_0_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:879:1: ( ( rule__Lifeline__CaptionAssignment_0_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:879:1: ( ( rule__Lifeline__CaptionAssignment_0_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:880:1: ( rule__Lifeline__CaptionAssignment_0_1 )
            {
             before(grammarAccess.getLifelineAccess().getCaptionAssignment_0_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:881:1: ( rule__Lifeline__CaptionAssignment_0_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:881:2: rule__Lifeline__CaptionAssignment_0_1
            {
            pushFollow(FOLLOW_rule__Lifeline__CaptionAssignment_0_1_in_rule__Lifeline__Group_0__1__Impl1858);
            rule__Lifeline__CaptionAssignment_0_1();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getCaptionAssignment_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__1__Impl"


    // $ANTLR start "rule__Lifeline__Group_0__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:891:1: rule__Lifeline__Group_0__2 : rule__Lifeline__Group_0__2__Impl rule__Lifeline__Group_0__3 ;
    public final void rule__Lifeline__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:895:1: ( rule__Lifeline__Group_0__2__Impl rule__Lifeline__Group_0__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:896:2: rule__Lifeline__Group_0__2__Impl rule__Lifeline__Group_0__3
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_0__2__Impl_in_rule__Lifeline__Group_0__21888);
            rule__Lifeline__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group_0__3_in_rule__Lifeline__Group_0__21891);
            rule__Lifeline__Group_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__2"


    // $ANTLR start "rule__Lifeline__Group_0__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:903:1: rule__Lifeline__Group_0__2__Impl : ( 'as' ) ;
    public final void rule__Lifeline__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:907:1: ( ( 'as' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:908:1: ( 'as' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:908:1: ( 'as' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:909:1: 'as'
            {
             before(grammarAccess.getLifelineAccess().getAsKeyword_0_2()); 
            match(input,20,FOLLOW_20_in_rule__Lifeline__Group_0__2__Impl1919); 
             after(grammarAccess.getLifelineAccess().getAsKeyword_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__2__Impl"


    // $ANTLR start "rule__Lifeline__Group_0__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:922:1: rule__Lifeline__Group_0__3 : rule__Lifeline__Group_0__3__Impl ;
    public final void rule__Lifeline__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:926:1: ( rule__Lifeline__Group_0__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:927:2: rule__Lifeline__Group_0__3__Impl
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_0__3__Impl_in_rule__Lifeline__Group_0__31950);
            rule__Lifeline__Group_0__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__3"


    // $ANTLR start "rule__Lifeline__Group_0__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:933:1: rule__Lifeline__Group_0__3__Impl : ( ( rule__Lifeline__NameAssignment_0_3 ) ) ;
    public final void rule__Lifeline__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:937:1: ( ( ( rule__Lifeline__NameAssignment_0_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:938:1: ( ( rule__Lifeline__NameAssignment_0_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:938:1: ( ( rule__Lifeline__NameAssignment_0_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:939:1: ( rule__Lifeline__NameAssignment_0_3 )
            {
             before(grammarAccess.getLifelineAccess().getNameAssignment_0_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:940:1: ( rule__Lifeline__NameAssignment_0_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:940:2: rule__Lifeline__NameAssignment_0_3
            {
            pushFollow(FOLLOW_rule__Lifeline__NameAssignment_0_3_in_rule__Lifeline__Group_0__3__Impl1977);
            rule__Lifeline__NameAssignment_0_3();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getNameAssignment_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_0__3__Impl"


    // $ANTLR start "rule__Lifeline__Group_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:958:1: rule__Lifeline__Group_1__0 : rule__Lifeline__Group_1__0__Impl rule__Lifeline__Group_1__1 ;
    public final void rule__Lifeline__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:962:1: ( rule__Lifeline__Group_1__0__Impl rule__Lifeline__Group_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:963:2: rule__Lifeline__Group_1__0__Impl rule__Lifeline__Group_1__1
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_1__0__Impl_in_rule__Lifeline__Group_1__02015);
            rule__Lifeline__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group_1__1_in_rule__Lifeline__Group_1__02018);
            rule__Lifeline__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__0"


    // $ANTLR start "rule__Lifeline__Group_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:970:1: rule__Lifeline__Group_1__0__Impl : ( 'usecase' ) ;
    public final void rule__Lifeline__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:974:1: ( ( 'usecase' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:975:1: ( 'usecase' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:975:1: ( 'usecase' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:976:1: 'usecase'
            {
             before(grammarAccess.getLifelineAccess().getUsecaseKeyword_1_0()); 
            match(input,21,FOLLOW_21_in_rule__Lifeline__Group_1__0__Impl2046); 
             after(grammarAccess.getLifelineAccess().getUsecaseKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__0__Impl"


    // $ANTLR start "rule__Lifeline__Group_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:989:1: rule__Lifeline__Group_1__1 : rule__Lifeline__Group_1__1__Impl rule__Lifeline__Group_1__2 ;
    public final void rule__Lifeline__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:993:1: ( rule__Lifeline__Group_1__1__Impl rule__Lifeline__Group_1__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:994:2: rule__Lifeline__Group_1__1__Impl rule__Lifeline__Group_1__2
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_1__1__Impl_in_rule__Lifeline__Group_1__12077);
            rule__Lifeline__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group_1__2_in_rule__Lifeline__Group_1__12080);
            rule__Lifeline__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__1"


    // $ANTLR start "rule__Lifeline__Group_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1001:1: rule__Lifeline__Group_1__1__Impl : ( ( rule__Lifeline__UsecaseCaptionAssignment_1_1 ) ) ;
    public final void rule__Lifeline__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1005:1: ( ( ( rule__Lifeline__UsecaseCaptionAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1006:1: ( ( rule__Lifeline__UsecaseCaptionAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1006:1: ( ( rule__Lifeline__UsecaseCaptionAssignment_1_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1007:1: ( rule__Lifeline__UsecaseCaptionAssignment_1_1 )
            {
             before(grammarAccess.getLifelineAccess().getUsecaseCaptionAssignment_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1008:1: ( rule__Lifeline__UsecaseCaptionAssignment_1_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1008:2: rule__Lifeline__UsecaseCaptionAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Lifeline__UsecaseCaptionAssignment_1_1_in_rule__Lifeline__Group_1__1__Impl2107);
            rule__Lifeline__UsecaseCaptionAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getUsecaseCaptionAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__1__Impl"


    // $ANTLR start "rule__Lifeline__Group_1__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1018:1: rule__Lifeline__Group_1__2 : rule__Lifeline__Group_1__2__Impl rule__Lifeline__Group_1__3 ;
    public final void rule__Lifeline__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1022:1: ( rule__Lifeline__Group_1__2__Impl rule__Lifeline__Group_1__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1023:2: rule__Lifeline__Group_1__2__Impl rule__Lifeline__Group_1__3
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_1__2__Impl_in_rule__Lifeline__Group_1__22137);
            rule__Lifeline__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group_1__3_in_rule__Lifeline__Group_1__22140);
            rule__Lifeline__Group_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__2"


    // $ANTLR start "rule__Lifeline__Group_1__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1030:1: rule__Lifeline__Group_1__2__Impl : ( 'as' ) ;
    public final void rule__Lifeline__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1034:1: ( ( 'as' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1035:1: ( 'as' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1035:1: ( 'as' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1036:1: 'as'
            {
             before(grammarAccess.getLifelineAccess().getAsKeyword_1_2()); 
            match(input,20,FOLLOW_20_in_rule__Lifeline__Group_1__2__Impl2168); 
             after(grammarAccess.getLifelineAccess().getAsKeyword_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__2__Impl"


    // $ANTLR start "rule__Lifeline__Group_1__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1049:1: rule__Lifeline__Group_1__3 : rule__Lifeline__Group_1__3__Impl ;
    public final void rule__Lifeline__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1053:1: ( rule__Lifeline__Group_1__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1054:2: rule__Lifeline__Group_1__3__Impl
            {
            pushFollow(FOLLOW_rule__Lifeline__Group_1__3__Impl_in_rule__Lifeline__Group_1__32199);
            rule__Lifeline__Group_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__3"


    // $ANTLR start "rule__Lifeline__Group_1__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1060:1: rule__Lifeline__Group_1__3__Impl : ( ( rule__Lifeline__NameAssignment_1_3 ) ) ;
    public final void rule__Lifeline__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1064:1: ( ( ( rule__Lifeline__NameAssignment_1_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1065:1: ( ( rule__Lifeline__NameAssignment_1_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1065:1: ( ( rule__Lifeline__NameAssignment_1_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1066:1: ( rule__Lifeline__NameAssignment_1_3 )
            {
             before(grammarAccess.getLifelineAccess().getNameAssignment_1_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1067:1: ( rule__Lifeline__NameAssignment_1_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1067:2: rule__Lifeline__NameAssignment_1_3
            {
            pushFollow(FOLLOW_rule__Lifeline__NameAssignment_1_3_in_rule__Lifeline__Group_1__3__Impl2226);
            rule__Lifeline__NameAssignment_1_3();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getNameAssignment_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group_1__3__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1085:1: rule__TwoLifelineMessage__Group__0 : rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 ;
    public final void rule__TwoLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1089:1: ( rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1090:2: rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__02264);
            rule__TwoLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__02267);
            rule__TwoLifelineMessage__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__0"


    // $ANTLR start "rule__TwoLifelineMessage__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1097:1: rule__TwoLifelineMessage__Group__0__Impl : ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1101:1: ( ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1102:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1102:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1103:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1104:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1104:2: rule__TwoLifelineMessage__SourceLifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl2294);
            rule__TwoLifelineMessage__SourceLifelineAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__0__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1114:1: rule__TwoLifelineMessage__Group__1 : rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 ;
    public final void rule__TwoLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1118:1: ( rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1119:2: rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__12324);
            rule__TwoLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__12327);
            rule__TwoLifelineMessage__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__1"


    // $ANTLR start "rule__TwoLifelineMessage__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1126:1: rule__TwoLifelineMessage__Group__1__Impl : ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1130:1: ( ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1131:1: ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1131:1: ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1132:1: ( rule__TwoLifelineMessage__MessageTypeAssignment_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1133:1: ( rule__TwoLifelineMessage__MessageTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1133:2: rule__TwoLifelineMessage__MessageTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__MessageTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl2354);
            rule__TwoLifelineMessage__MessageTypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__1__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1143:1: rule__TwoLifelineMessage__Group__2 : rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 ;
    public final void rule__TwoLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1147:1: ( rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1148:2: rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__22384);
            rule__TwoLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__22387);
            rule__TwoLifelineMessage__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__2"


    // $ANTLR start "rule__TwoLifelineMessage__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1155:1: rule__TwoLifelineMessage__Group__2__Impl : ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) ) ;
    public final void rule__TwoLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1159:1: ( ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1160:1: ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1160:1: ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1161:1: ( rule__TwoLifelineMessage__MessageAssignment_2 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1162:1: ( rule__TwoLifelineMessage__MessageAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1162:2: rule__TwoLifelineMessage__MessageAssignment_2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__MessageAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl2414);
            rule__TwoLifelineMessage__MessageAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getMessageAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__2__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1172:1: rule__TwoLifelineMessage__Group__3 : rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 ;
    public final void rule__TwoLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1176:1: ( rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1177:2: rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__32444);
            rule__TwoLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__32447);
            rule__TwoLifelineMessage__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__3"


    // $ANTLR start "rule__TwoLifelineMessage__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1184:1: rule__TwoLifelineMessage__Group__3__Impl : ( 'to' ) ;
    public final void rule__TwoLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1188:1: ( ( 'to' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1189:1: ( 'to' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1189:1: ( 'to' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1190:1: 'to'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3()); 
            match(input,22,FOLLOW_22_in_rule__TwoLifelineMessage__Group__3__Impl2475); 
             after(grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__3__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1203:1: rule__TwoLifelineMessage__Group__4 : rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 ;
    public final void rule__TwoLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1207:1: ( rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1208:2: rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__42506);
            rule__TwoLifelineMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__42509);
            rule__TwoLifelineMessage__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__4"


    // $ANTLR start "rule__TwoLifelineMessage__Group__4__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1215:1: rule__TwoLifelineMessage__Group__4__Impl : ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) ;
    public final void rule__TwoLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1219:1: ( ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1220:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1220:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1221:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1222:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1222:2: rule__TwoLifelineMessage__TargetLifelineAssignment_4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl2536);
            rule__TwoLifelineMessage__TargetLifelineAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__4__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__5"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1232:1: rule__TwoLifelineMessage__Group__5 : rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 ;
    public final void rule__TwoLifelineMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1236:1: ( rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1237:2: rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__52566);
            rule__TwoLifelineMessage__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__52569);
            rule__TwoLifelineMessage__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__5"


    // $ANTLR start "rule__TwoLifelineMessage__Group__5__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1244:1: rule__TwoLifelineMessage__Group__5__Impl : ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) ;
    public final void rule__TwoLifelineMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1248:1: ( ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1249:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1249:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1250:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1251:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=35 && LA12_0<=37)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1251:2: rule__TwoLifelineMessage__Alternatives_5
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2596);
                    rule__TwoLifelineMessage__Alternatives_5();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__5__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__6"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1261:1: rule__TwoLifelineMessage__Group__6 : rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 ;
    public final void rule__TwoLifelineMessage__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1265:1: ( rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1266:2: rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__62627);
            rule__TwoLifelineMessage__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__62630);
            rule__TwoLifelineMessage__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__6"


    // $ANTLR start "rule__TwoLifelineMessage__Group__6__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1273:1: rule__TwoLifelineMessage__Group__6__Impl : ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) ;
    public final void rule__TwoLifelineMessage__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1277:1: ( ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1278:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1278:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1279:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_6()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1280:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=38 && LA13_0<=40)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1280:2: rule__TwoLifelineMessage__Alternatives_6
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl2657);
                    rule__TwoLifelineMessage__Alternatives_6();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__6__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__7"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1290:1: rule__TwoLifelineMessage__Group__7 : rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 ;
    public final void rule__TwoLifelineMessage__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1294:1: ( rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1295:2: rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__72688);
            rule__TwoLifelineMessage__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__72691);
            rule__TwoLifelineMessage__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__7"


    // $ANTLR start "rule__TwoLifelineMessage__Group__7__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1302:1: rule__TwoLifelineMessage__Group__7__Impl : ( ( rule__TwoLifelineMessage__Group_7__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1306:1: ( ( ( rule__TwoLifelineMessage__Group_7__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1307:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1307:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1308:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1309:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==23) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1309:2: rule__TwoLifelineMessage__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl2718);
                    rule__TwoLifelineMessage__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__7__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__8"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1319:1: rule__TwoLifelineMessage__Group__8 : rule__TwoLifelineMessage__Group__8__Impl ;
    public final void rule__TwoLifelineMessage__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1323:1: ( rule__TwoLifelineMessage__Group__8__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1324:2: rule__TwoLifelineMessage__Group__8__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__82749);
            rule__TwoLifelineMessage__Group__8__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__8"


    // $ANTLR start "rule__TwoLifelineMessage__Group__8__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1330:1: rule__TwoLifelineMessage__Group__8__Impl : ( ( rule__TwoLifelineMessage__Group_8__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1334:1: ( ( ( rule__TwoLifelineMessage__Group_8__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1335:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1335:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1336:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_8()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1337:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==24) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1337:2: rule__TwoLifelineMessage__Group_8__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl2776);
                    rule__TwoLifelineMessage__Group_8__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getGroup_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group__8__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_2__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1365:1: rule__TwoLifelineMessage__Group_5_2__0 : rule__TwoLifelineMessage__Group_5_2__0__Impl rule__TwoLifelineMessage__Group_5_2__1 ;
    public final void rule__TwoLifelineMessage__Group_5_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1369:1: ( rule__TwoLifelineMessage__Group_5_2__0__Impl rule__TwoLifelineMessage__Group_5_2__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1370:2: rule__TwoLifelineMessage__Group_5_2__0__Impl rule__TwoLifelineMessage__Group_5_2__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_2__0__Impl_in_rule__TwoLifelineMessage__Group_5_2__02825);
            rule__TwoLifelineMessage__Group_5_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_2__1_in_rule__TwoLifelineMessage__Group_5_2__02828);
            rule__TwoLifelineMessage__Group_5_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_2__0"


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_2__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1377:1: rule__TwoLifelineMessage__Group_5_2__0__Impl : ( ( rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_5_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1381:1: ( ( ( rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1382:1: ( ( rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1382:1: ( ( rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1383:1: ( rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecAssignment_5_2_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1384:1: ( rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1384:2: rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0_in_rule__TwoLifelineMessage__Group_5_2__0__Impl2855);
            rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecAssignment_5_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_2__0__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_2__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1394:1: rule__TwoLifelineMessage__Group_5_2__1 : rule__TwoLifelineMessage__Group_5_2__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_5_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1398:1: ( rule__TwoLifelineMessage__Group_5_2__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1399:2: rule__TwoLifelineMessage__Group_5_2__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_2__1__Impl_in_rule__TwoLifelineMessage__Group_5_2__12885);
            rule__TwoLifelineMessage__Group_5_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_2__1"


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_2__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1405:1: rule__TwoLifelineMessage__Group_5_2__1__Impl : ( ( rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_5_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1409:1: ( ( ( rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1410:1: ( ( rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1410:1: ( ( rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1411:1: ( rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecCountAssignment_5_2_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1412:1: ( rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT_GREATER_ZERO) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1412:2: rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1_in_rule__TwoLifelineMessage__Group_5_2__1__Impl2912);
                    rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecCountAssignment_5_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_2__1__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_2__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1426:1: rule__TwoLifelineMessage__Group_6_2__0 : rule__TwoLifelineMessage__Group_6_2__0__Impl rule__TwoLifelineMessage__Group_6_2__1 ;
    public final void rule__TwoLifelineMessage__Group_6_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1430:1: ( rule__TwoLifelineMessage__Group_6_2__0__Impl rule__TwoLifelineMessage__Group_6_2__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1431:2: rule__TwoLifelineMessage__Group_6_2__0__Impl rule__TwoLifelineMessage__Group_6_2__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_2__0__Impl_in_rule__TwoLifelineMessage__Group_6_2__02947);
            rule__TwoLifelineMessage__Group_6_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_2__1_in_rule__TwoLifelineMessage__Group_6_2__02950);
            rule__TwoLifelineMessage__Group_6_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_2__0"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_2__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1438:1: rule__TwoLifelineMessage__Group_6_2__0__Impl : ( ( rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_6_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1442:1: ( ( ( rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1443:1: ( ( rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1443:1: ( ( rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1444:1: ( rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecAssignment_6_2_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1445:1: ( rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1445:2: rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0_in_rule__TwoLifelineMessage__Group_6_2__0__Impl2977);
            rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecAssignment_6_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_2__0__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_2__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1455:1: rule__TwoLifelineMessage__Group_6_2__1 : rule__TwoLifelineMessage__Group_6_2__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_6_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1459:1: ( rule__TwoLifelineMessage__Group_6_2__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1460:2: rule__TwoLifelineMessage__Group_6_2__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_2__1__Impl_in_rule__TwoLifelineMessage__Group_6_2__13007);
            rule__TwoLifelineMessage__Group_6_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_2__1"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_2__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1466:1: rule__TwoLifelineMessage__Group_6_2__1__Impl : ( ( rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_6_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1470:1: ( ( ( rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1471:1: ( ( rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1471:1: ( ( rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1472:1: ( rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecCountAssignment_6_2_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1473:1: ( rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_INT_GREATER_ZERO) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1473:2: rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1_in_rule__TwoLifelineMessage__Group_6_2__1__Impl3034);
                    rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecCountAssignment_6_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_2__1__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_7__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1487:1: rule__TwoLifelineMessage__Group_7__0 : rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 ;
    public final void rule__TwoLifelineMessage__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1491:1: ( rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1492:2: rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__03069);
            rule__TwoLifelineMessage__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__03072);
            rule__TwoLifelineMessage__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_7__0"


    // $ANTLR start "rule__TwoLifelineMessage__Group_7__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1499:1: rule__TwoLifelineMessage__Group_7__0__Impl : ( 'sourceNote' ) ;
    public final void rule__TwoLifelineMessage__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1503:1: ( ( 'sourceNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1504:1: ( 'sourceNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1504:1: ( 'sourceNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1505:1: 'sourceNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0()); 
            match(input,23,FOLLOW_23_in_rule__TwoLifelineMessage__Group_7__0__Impl3100); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_7__0__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_7__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1518:1: rule__TwoLifelineMessage__Group_7__1 : rule__TwoLifelineMessage__Group_7__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1522:1: ( rule__TwoLifelineMessage__Group_7__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1523:2: rule__TwoLifelineMessage__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__13131);
            rule__TwoLifelineMessage__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_7__1"


    // $ANTLR start "rule__TwoLifelineMessage__Group_7__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1529:1: rule__TwoLifelineMessage__Group_7__1__Impl : ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1533:1: ( ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1534:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1534:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1535:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteAssignment_7_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1536:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1536:2: rule__TwoLifelineMessage__SourceNoteAssignment_7_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl3158);
            rule__TwoLifelineMessage__SourceNoteAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteAssignment_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_7__1__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_8__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1550:1: rule__TwoLifelineMessage__Group_8__0 : rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 ;
    public final void rule__TwoLifelineMessage__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1554:1: ( rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1555:2: rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__03192);
            rule__TwoLifelineMessage__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__03195);
            rule__TwoLifelineMessage__Group_8__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_8__0"


    // $ANTLR start "rule__TwoLifelineMessage__Group_8__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1562:1: rule__TwoLifelineMessage__Group_8__0__Impl : ( 'targetNote' ) ;
    public final void rule__TwoLifelineMessage__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1566:1: ( ( 'targetNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1567:1: ( 'targetNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1567:1: ( 'targetNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1568:1: 'targetNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0()); 
            match(input,24,FOLLOW_24_in_rule__TwoLifelineMessage__Group_8__0__Impl3223); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_8__0__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_8__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1581:1: rule__TwoLifelineMessage__Group_8__1 : rule__TwoLifelineMessage__Group_8__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1585:1: ( rule__TwoLifelineMessage__Group_8__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1586:2: rule__TwoLifelineMessage__Group_8__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__13254);
            rule__TwoLifelineMessage__Group_8__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_8__1"


    // $ANTLR start "rule__TwoLifelineMessage__Group_8__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1592:1: rule__TwoLifelineMessage__Group_8__1__Impl : ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1596:1: ( ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1597:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1597:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1598:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteAssignment_8_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1599:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1599:2: rule__TwoLifelineMessage__TargetNoteAssignment_8_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl3281);
            rule__TwoLifelineMessage__TargetNoteAssignment_8_1();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteAssignment_8_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_8__1__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1613:1: rule__OneLifelineMessage__Group__0 : rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 ;
    public final void rule__OneLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1617:1: ( rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1618:2: rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__03315);
            rule__OneLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__03318);
            rule__OneLifelineMessage__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__0"


    // $ANTLR start "rule__OneLifelineMessage__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1625:1: rule__OneLifelineMessage__Group__0__Impl : ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1629:1: ( ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1630:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1630:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1631:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1632:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1632:2: rule__OneLifelineMessage__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl3345);
            rule__OneLifelineMessage__LifelineAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getLifelineAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__0__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1642:1: rule__OneLifelineMessage__Group__1 : rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 ;
    public final void rule__OneLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1646:1: ( rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1647:2: rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__13375);
            rule__OneLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__13378);
            rule__OneLifelineMessage__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__1"


    // $ANTLR start "rule__OneLifelineMessage__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1654:1: rule__OneLifelineMessage__Group__1__Impl : ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) ) ;
    public final void rule__OneLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1658:1: ( ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1659:1: ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1659:1: ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1660:1: ( rule__OneLifelineMessage__MessageTypeAssignment_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1661:1: ( rule__OneLifelineMessage__MessageTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1661:2: rule__OneLifelineMessage__MessageTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl3405);
            rule__OneLifelineMessage__MessageTypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__1__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1671:1: rule__OneLifelineMessage__Group__2 : rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 ;
    public final void rule__OneLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1675:1: ( rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1676:2: rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__23435);
            rule__OneLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__23438);
            rule__OneLifelineMessage__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__2"


    // $ANTLR start "rule__OneLifelineMessage__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1683:1: rule__OneLifelineMessage__Group__2__Impl : ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) ) ;
    public final void rule__OneLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1687:1: ( ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1688:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1688:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1689:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1690:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1690:2: rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2_in_rule__OneLifelineMessage__Group__2__Impl3465);
            rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__2__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1700:1: rule__OneLifelineMessage__Group__3 : rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 ;
    public final void rule__OneLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1704:1: ( rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1705:2: rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__33495);
            rule__OneLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__33498);
            rule__OneLifelineMessage__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__3"


    // $ANTLR start "rule__OneLifelineMessage__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1712:1: rule__OneLifelineMessage__Group__3__Impl : ( ( rule__OneLifelineMessage__MessageAssignment_3 ) ) ;
    public final void rule__OneLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1716:1: ( ( ( rule__OneLifelineMessage__MessageAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1717:1: ( ( rule__OneLifelineMessage__MessageAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1717:1: ( ( rule__OneLifelineMessage__MessageAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1718:1: ( rule__OneLifelineMessage__MessageAssignment_3 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1719:1: ( rule__OneLifelineMessage__MessageAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1719:2: rule__OneLifelineMessage__MessageAssignment_3
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageAssignment_3_in_rule__OneLifelineMessage__Group__3__Impl3525);
            rule__OneLifelineMessage__MessageAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getMessageAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__3__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group__4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1729:1: rule__OneLifelineMessage__Group__4 : rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5 ;
    public final void rule__OneLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1733:1: ( rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1734:2: rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__43555);
            rule__OneLifelineMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__5_in_rule__OneLifelineMessage__Group__43558);
            rule__OneLifelineMessage__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__4"


    // $ANTLR start "rule__OneLifelineMessage__Group__4__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1741:1: rule__OneLifelineMessage__Group__4__Impl : ( ( rule__OneLifelineMessage__Alternatives_4 )? ) ;
    public final void rule__OneLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1745:1: ( ( ( rule__OneLifelineMessage__Alternatives_4 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1746:1: ( ( rule__OneLifelineMessage__Alternatives_4 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1746:1: ( ( rule__OneLifelineMessage__Alternatives_4 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1747:1: ( rule__OneLifelineMessage__Alternatives_4 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getAlternatives_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1748:1: ( rule__OneLifelineMessage__Alternatives_4 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=41 && LA18_0<=43)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1748:2: rule__OneLifelineMessage__Alternatives_4
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Alternatives_4_in_rule__OneLifelineMessage__Group__4__Impl3585);
                    rule__OneLifelineMessage__Alternatives_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineMessageAccess().getAlternatives_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__4__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group__5"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1758:1: rule__OneLifelineMessage__Group__5 : rule__OneLifelineMessage__Group__5__Impl ;
    public final void rule__OneLifelineMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1762:1: ( rule__OneLifelineMessage__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1763:2: rule__OneLifelineMessage__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__5__Impl_in_rule__OneLifelineMessage__Group__53616);
            rule__OneLifelineMessage__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__5"


    // $ANTLR start "rule__OneLifelineMessage__Group__5__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1769:1: rule__OneLifelineMessage__Group__5__Impl : ( ( rule__OneLifelineMessage__Group_5__0 )? ) ;
    public final void rule__OneLifelineMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1773:1: ( ( ( rule__OneLifelineMessage__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1774:1: ( ( rule__OneLifelineMessage__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1774:1: ( ( rule__OneLifelineMessage__Group_5__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1775:1: ( rule__OneLifelineMessage__Group_5__0 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1776:1: ( rule__OneLifelineMessage__Group_5__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==25) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1776:2: rule__OneLifelineMessage__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__0_in_rule__OneLifelineMessage__Group__5__Impl3643);
                    rule__OneLifelineMessage__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineMessageAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group__5__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_4_2__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1798:1: rule__OneLifelineMessage__Group_4_2__0 : rule__OneLifelineMessage__Group_4_2__0__Impl rule__OneLifelineMessage__Group_4_2__1 ;
    public final void rule__OneLifelineMessage__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1802:1: ( rule__OneLifelineMessage__Group_4_2__0__Impl rule__OneLifelineMessage__Group_4_2__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1803:2: rule__OneLifelineMessage__Group_4_2__0__Impl rule__OneLifelineMessage__Group_4_2__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_2__0__Impl_in_rule__OneLifelineMessage__Group_4_2__03686);
            rule__OneLifelineMessage__Group_4_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_2__1_in_rule__OneLifelineMessage__Group_4_2__03689);
            rule__OneLifelineMessage__Group_4_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_2__0"


    // $ANTLR start "rule__OneLifelineMessage__Group_4_2__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1810:1: rule__OneLifelineMessage__Group_4_2__0__Impl : ( ( rule__OneLifelineMessage__EndExecAssignment_4_2_0 ) ) ;
    public final void rule__OneLifelineMessage__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1814:1: ( ( ( rule__OneLifelineMessage__EndExecAssignment_4_2_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1815:1: ( ( rule__OneLifelineMessage__EndExecAssignment_4_2_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1815:1: ( ( rule__OneLifelineMessage__EndExecAssignment_4_2_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1816:1: ( rule__OneLifelineMessage__EndExecAssignment_4_2_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndExecAssignment_4_2_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1817:1: ( rule__OneLifelineMessage__EndExecAssignment_4_2_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1817:2: rule__OneLifelineMessage__EndExecAssignment_4_2_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__EndExecAssignment_4_2_0_in_rule__OneLifelineMessage__Group_4_2__0__Impl3716);
            rule__OneLifelineMessage__EndExecAssignment_4_2_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndExecAssignment_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_2__0__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_4_2__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1827:1: rule__OneLifelineMessage__Group_4_2__1 : rule__OneLifelineMessage__Group_4_2__1__Impl ;
    public final void rule__OneLifelineMessage__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1831:1: ( rule__OneLifelineMessage__Group_4_2__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1832:2: rule__OneLifelineMessage__Group_4_2__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_2__1__Impl_in_rule__OneLifelineMessage__Group_4_2__13746);
            rule__OneLifelineMessage__Group_4_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_2__1"


    // $ANTLR start "rule__OneLifelineMessage__Group_4_2__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1838:1: rule__OneLifelineMessage__Group_4_2__1__Impl : ( ( rule__OneLifelineMessage__EndExecCountAssignment_4_2_1 )? ) ;
    public final void rule__OneLifelineMessage__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1842:1: ( ( ( rule__OneLifelineMessage__EndExecCountAssignment_4_2_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1843:1: ( ( rule__OneLifelineMessage__EndExecCountAssignment_4_2_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1843:1: ( ( rule__OneLifelineMessage__EndExecCountAssignment_4_2_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1844:1: ( rule__OneLifelineMessage__EndExecCountAssignment_4_2_1 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndExecCountAssignment_4_2_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1845:1: ( rule__OneLifelineMessage__EndExecCountAssignment_4_2_1 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_INT_GREATER_ZERO) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1845:2: rule__OneLifelineMessage__EndExecCountAssignment_4_2_1
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__EndExecCountAssignment_4_2_1_in_rule__OneLifelineMessage__Group_4_2__1__Impl3773);
                    rule__OneLifelineMessage__EndExecCountAssignment_4_2_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndExecCountAssignment_4_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_2__1__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_5__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1859:1: rule__OneLifelineMessage__Group_5__0 : rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1 ;
    public final void rule__OneLifelineMessage__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1863:1: ( rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1864:2: rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__0__Impl_in_rule__OneLifelineMessage__Group_5__03808);
            rule__OneLifelineMessage__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__1_in_rule__OneLifelineMessage__Group_5__03811);
            rule__OneLifelineMessage__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_5__0"


    // $ANTLR start "rule__OneLifelineMessage__Group_5__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1871:1: rule__OneLifelineMessage__Group_5__0__Impl : ( 'note' ) ;
    public final void rule__OneLifelineMessage__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1875:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1876:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1876:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1877:1: 'note'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0()); 
            match(input,25,FOLLOW_25_in_rule__OneLifelineMessage__Group_5__0__Impl3839); 
             after(grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_5__0__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_5__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1890:1: rule__OneLifelineMessage__Group_5__1 : rule__OneLifelineMessage__Group_5__1__Impl ;
    public final void rule__OneLifelineMessage__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1894:1: ( rule__OneLifelineMessage__Group_5__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1895:2: rule__OneLifelineMessage__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__1__Impl_in_rule__OneLifelineMessage__Group_5__13870);
            rule__OneLifelineMessage__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_5__1"


    // $ANTLR start "rule__OneLifelineMessage__Group_5__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1901:1: rule__OneLifelineMessage__Group_5__1__Impl : ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) ) ;
    public final void rule__OneLifelineMessage__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1905:1: ( ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1906:1: ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1906:1: ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1907:1: ( rule__OneLifelineMessage__NoteAssignment_5_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteAssignment_5_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1908:1: ( rule__OneLifelineMessage__NoteAssignment_5_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1908:2: rule__OneLifelineMessage__NoteAssignment_5_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__NoteAssignment_5_1_in_rule__OneLifelineMessage__Group_5__1__Impl3897);
            rule__OneLifelineMessage__NoteAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getNoteAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_5__1__Impl"


    // $ANTLR start "rule__SelfMessage__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1922:1: rule__SelfMessage__Group__0 : rule__SelfMessage__Group__0__Impl rule__SelfMessage__Group__1 ;
    public final void rule__SelfMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1926:1: ( rule__SelfMessage__Group__0__Impl rule__SelfMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1927:2: rule__SelfMessage__Group__0__Impl rule__SelfMessage__Group__1
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group__0__Impl_in_rule__SelfMessage__Group__03931);
            rule__SelfMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SelfMessage__Group__1_in_rule__SelfMessage__Group__03934);
            rule__SelfMessage__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__0"


    // $ANTLR start "rule__SelfMessage__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1934:1: rule__SelfMessage__Group__0__Impl : ( ( rule__SelfMessage__LifelineAssignment_0 ) ) ;
    public final void rule__SelfMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1938:1: ( ( ( rule__SelfMessage__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1939:1: ( ( rule__SelfMessage__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1939:1: ( ( rule__SelfMessage__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1940:1: ( rule__SelfMessage__LifelineAssignment_0 )
            {
             before(grammarAccess.getSelfMessageAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1941:1: ( rule__SelfMessage__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1941:2: rule__SelfMessage__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__SelfMessage__LifelineAssignment_0_in_rule__SelfMessage__Group__0__Impl3961);
            rule__SelfMessage__LifelineAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getSelfMessageAccess().getLifelineAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__0__Impl"


    // $ANTLR start "rule__SelfMessage__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1951:1: rule__SelfMessage__Group__1 : rule__SelfMessage__Group__1__Impl rule__SelfMessage__Group__2 ;
    public final void rule__SelfMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1955:1: ( rule__SelfMessage__Group__1__Impl rule__SelfMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1956:2: rule__SelfMessage__Group__1__Impl rule__SelfMessage__Group__2
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group__1__Impl_in_rule__SelfMessage__Group__13991);
            rule__SelfMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SelfMessage__Group__2_in_rule__SelfMessage__Group__13994);
            rule__SelfMessage__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__1"


    // $ANTLR start "rule__SelfMessage__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1963:1: rule__SelfMessage__Group__1__Impl : ( ( rule__SelfMessage__MessageTypeAssignment_1 ) ) ;
    public final void rule__SelfMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1967:1: ( ( ( rule__SelfMessage__MessageTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1968:1: ( ( rule__SelfMessage__MessageTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1968:1: ( ( rule__SelfMessage__MessageTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1969:1: ( rule__SelfMessage__MessageTypeAssignment_1 )
            {
             before(grammarAccess.getSelfMessageAccess().getMessageTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1970:1: ( rule__SelfMessage__MessageTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1970:2: rule__SelfMessage__MessageTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__SelfMessage__MessageTypeAssignment_1_in_rule__SelfMessage__Group__1__Impl4021);
            rule__SelfMessage__MessageTypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getSelfMessageAccess().getMessageTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__1__Impl"


    // $ANTLR start "rule__SelfMessage__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1980:1: rule__SelfMessage__Group__2 : rule__SelfMessage__Group__2__Impl rule__SelfMessage__Group__3 ;
    public final void rule__SelfMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1984:1: ( rule__SelfMessage__Group__2__Impl rule__SelfMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1985:2: rule__SelfMessage__Group__2__Impl rule__SelfMessage__Group__3
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group__2__Impl_in_rule__SelfMessage__Group__24051);
            rule__SelfMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SelfMessage__Group__3_in_rule__SelfMessage__Group__24054);
            rule__SelfMessage__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__2"


    // $ANTLR start "rule__SelfMessage__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1992:1: rule__SelfMessage__Group__2__Impl : ( 'self' ) ;
    public final void rule__SelfMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1996:1: ( ( 'self' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1997:1: ( 'self' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1997:1: ( 'self' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1998:1: 'self'
            {
             before(grammarAccess.getSelfMessageAccess().getSelfKeyword_2()); 
            match(input,26,FOLLOW_26_in_rule__SelfMessage__Group__2__Impl4082); 
             after(grammarAccess.getSelfMessageAccess().getSelfKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__2__Impl"


    // $ANTLR start "rule__SelfMessage__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2011:1: rule__SelfMessage__Group__3 : rule__SelfMessage__Group__3__Impl rule__SelfMessage__Group__4 ;
    public final void rule__SelfMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2015:1: ( rule__SelfMessage__Group__3__Impl rule__SelfMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2016:2: rule__SelfMessage__Group__3__Impl rule__SelfMessage__Group__4
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group__3__Impl_in_rule__SelfMessage__Group__34113);
            rule__SelfMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SelfMessage__Group__4_in_rule__SelfMessage__Group__34116);
            rule__SelfMessage__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__3"


    // $ANTLR start "rule__SelfMessage__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2023:1: rule__SelfMessage__Group__3__Impl : ( ( rule__SelfMessage__MessageAssignment_3 ) ) ;
    public final void rule__SelfMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2027:1: ( ( ( rule__SelfMessage__MessageAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2028:1: ( ( rule__SelfMessage__MessageAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2028:1: ( ( rule__SelfMessage__MessageAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2029:1: ( rule__SelfMessage__MessageAssignment_3 )
            {
             before(grammarAccess.getSelfMessageAccess().getMessageAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2030:1: ( rule__SelfMessage__MessageAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2030:2: rule__SelfMessage__MessageAssignment_3
            {
            pushFollow(FOLLOW_rule__SelfMessage__MessageAssignment_3_in_rule__SelfMessage__Group__3__Impl4143);
            rule__SelfMessage__MessageAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getSelfMessageAccess().getMessageAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__3__Impl"


    // $ANTLR start "rule__SelfMessage__Group__4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2040:1: rule__SelfMessage__Group__4 : rule__SelfMessage__Group__4__Impl rule__SelfMessage__Group__5 ;
    public final void rule__SelfMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2044:1: ( rule__SelfMessage__Group__4__Impl rule__SelfMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2045:2: rule__SelfMessage__Group__4__Impl rule__SelfMessage__Group__5
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group__4__Impl_in_rule__SelfMessage__Group__44173);
            rule__SelfMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SelfMessage__Group__5_in_rule__SelfMessage__Group__44176);
            rule__SelfMessage__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__4"


    // $ANTLR start "rule__SelfMessage__Group__4__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2052:1: rule__SelfMessage__Group__4__Impl : ( ( rule__SelfMessage__Alternatives_4 )? ) ;
    public final void rule__SelfMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2056:1: ( ( ( rule__SelfMessage__Alternatives_4 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2057:1: ( ( rule__SelfMessage__Alternatives_4 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2057:1: ( ( rule__SelfMessage__Alternatives_4 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2058:1: ( rule__SelfMessage__Alternatives_4 )?
            {
             before(grammarAccess.getSelfMessageAccess().getAlternatives_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2059:1: ( rule__SelfMessage__Alternatives_4 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=41 && LA21_0<=43)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2059:2: rule__SelfMessage__Alternatives_4
                    {
                    pushFollow(FOLLOW_rule__SelfMessage__Alternatives_4_in_rule__SelfMessage__Group__4__Impl4203);
                    rule__SelfMessage__Alternatives_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSelfMessageAccess().getAlternatives_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__4__Impl"


    // $ANTLR start "rule__SelfMessage__Group__5"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2069:1: rule__SelfMessage__Group__5 : rule__SelfMessage__Group__5__Impl ;
    public final void rule__SelfMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2073:1: ( rule__SelfMessage__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2074:2: rule__SelfMessage__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group__5__Impl_in_rule__SelfMessage__Group__54234);
            rule__SelfMessage__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__5"


    // $ANTLR start "rule__SelfMessage__Group__5__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2080:1: rule__SelfMessage__Group__5__Impl : ( ( rule__SelfMessage__Group_5__0 )? ) ;
    public final void rule__SelfMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2084:1: ( ( ( rule__SelfMessage__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2085:1: ( ( rule__SelfMessage__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2085:1: ( ( rule__SelfMessage__Group_5__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2086:1: ( rule__SelfMessage__Group_5__0 )?
            {
             before(grammarAccess.getSelfMessageAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2087:1: ( rule__SelfMessage__Group_5__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==25) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2087:2: rule__SelfMessage__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__SelfMessage__Group_5__0_in_rule__SelfMessage__Group__5__Impl4261);
                    rule__SelfMessage__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSelfMessageAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group__5__Impl"


    // $ANTLR start "rule__SelfMessage__Group_4_2__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2109:1: rule__SelfMessage__Group_4_2__0 : rule__SelfMessage__Group_4_2__0__Impl rule__SelfMessage__Group_4_2__1 ;
    public final void rule__SelfMessage__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2113:1: ( rule__SelfMessage__Group_4_2__0__Impl rule__SelfMessage__Group_4_2__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2114:2: rule__SelfMessage__Group_4_2__0__Impl rule__SelfMessage__Group_4_2__1
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group_4_2__0__Impl_in_rule__SelfMessage__Group_4_2__04304);
            rule__SelfMessage__Group_4_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SelfMessage__Group_4_2__1_in_rule__SelfMessage__Group_4_2__04307);
            rule__SelfMessage__Group_4_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_4_2__0"


    // $ANTLR start "rule__SelfMessage__Group_4_2__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2121:1: rule__SelfMessage__Group_4_2__0__Impl : ( ( rule__SelfMessage__EndExecAssignment_4_2_0 ) ) ;
    public final void rule__SelfMessage__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2125:1: ( ( ( rule__SelfMessage__EndExecAssignment_4_2_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2126:1: ( ( rule__SelfMessage__EndExecAssignment_4_2_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2126:1: ( ( rule__SelfMessage__EndExecAssignment_4_2_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2127:1: ( rule__SelfMessage__EndExecAssignment_4_2_0 )
            {
             before(grammarAccess.getSelfMessageAccess().getEndExecAssignment_4_2_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2128:1: ( rule__SelfMessage__EndExecAssignment_4_2_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2128:2: rule__SelfMessage__EndExecAssignment_4_2_0
            {
            pushFollow(FOLLOW_rule__SelfMessage__EndExecAssignment_4_2_0_in_rule__SelfMessage__Group_4_2__0__Impl4334);
            rule__SelfMessage__EndExecAssignment_4_2_0();

            state._fsp--;


            }

             after(grammarAccess.getSelfMessageAccess().getEndExecAssignment_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_4_2__0__Impl"


    // $ANTLR start "rule__SelfMessage__Group_4_2__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2138:1: rule__SelfMessage__Group_4_2__1 : rule__SelfMessage__Group_4_2__1__Impl ;
    public final void rule__SelfMessage__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2142:1: ( rule__SelfMessage__Group_4_2__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2143:2: rule__SelfMessage__Group_4_2__1__Impl
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group_4_2__1__Impl_in_rule__SelfMessage__Group_4_2__14364);
            rule__SelfMessage__Group_4_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_4_2__1"


    // $ANTLR start "rule__SelfMessage__Group_4_2__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2149:1: rule__SelfMessage__Group_4_2__1__Impl : ( ( rule__SelfMessage__EndExecCountAssignment_4_2_1 )? ) ;
    public final void rule__SelfMessage__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2153:1: ( ( ( rule__SelfMessage__EndExecCountAssignment_4_2_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2154:1: ( ( rule__SelfMessage__EndExecCountAssignment_4_2_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2154:1: ( ( rule__SelfMessage__EndExecCountAssignment_4_2_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2155:1: ( rule__SelfMessage__EndExecCountAssignment_4_2_1 )?
            {
             before(grammarAccess.getSelfMessageAccess().getEndExecCountAssignment_4_2_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2156:1: ( rule__SelfMessage__EndExecCountAssignment_4_2_1 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_INT_GREATER_ZERO) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2156:2: rule__SelfMessage__EndExecCountAssignment_4_2_1
                    {
                    pushFollow(FOLLOW_rule__SelfMessage__EndExecCountAssignment_4_2_1_in_rule__SelfMessage__Group_4_2__1__Impl4391);
                    rule__SelfMessage__EndExecCountAssignment_4_2_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSelfMessageAccess().getEndExecCountAssignment_4_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_4_2__1__Impl"


    // $ANTLR start "rule__SelfMessage__Group_5__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2170:1: rule__SelfMessage__Group_5__0 : rule__SelfMessage__Group_5__0__Impl rule__SelfMessage__Group_5__1 ;
    public final void rule__SelfMessage__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2174:1: ( rule__SelfMessage__Group_5__0__Impl rule__SelfMessage__Group_5__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2175:2: rule__SelfMessage__Group_5__0__Impl rule__SelfMessage__Group_5__1
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group_5__0__Impl_in_rule__SelfMessage__Group_5__04426);
            rule__SelfMessage__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SelfMessage__Group_5__1_in_rule__SelfMessage__Group_5__04429);
            rule__SelfMessage__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_5__0"


    // $ANTLR start "rule__SelfMessage__Group_5__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2182:1: rule__SelfMessage__Group_5__0__Impl : ( 'note' ) ;
    public final void rule__SelfMessage__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2186:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2187:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2187:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2188:1: 'note'
            {
             before(grammarAccess.getSelfMessageAccess().getNoteKeyword_5_0()); 
            match(input,25,FOLLOW_25_in_rule__SelfMessage__Group_5__0__Impl4457); 
             after(grammarAccess.getSelfMessageAccess().getNoteKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_5__0__Impl"


    // $ANTLR start "rule__SelfMessage__Group_5__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2201:1: rule__SelfMessage__Group_5__1 : rule__SelfMessage__Group_5__1__Impl ;
    public final void rule__SelfMessage__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2205:1: ( rule__SelfMessage__Group_5__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2206:2: rule__SelfMessage__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__SelfMessage__Group_5__1__Impl_in_rule__SelfMessage__Group_5__14488);
            rule__SelfMessage__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_5__1"


    // $ANTLR start "rule__SelfMessage__Group_5__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2212:1: rule__SelfMessage__Group_5__1__Impl : ( ( rule__SelfMessage__NoteAssignment_5_1 ) ) ;
    public final void rule__SelfMessage__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2216:1: ( ( ( rule__SelfMessage__NoteAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2217:1: ( ( rule__SelfMessage__NoteAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2217:1: ( ( rule__SelfMessage__NoteAssignment_5_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2218:1: ( rule__SelfMessage__NoteAssignment_5_1 )
            {
             before(grammarAccess.getSelfMessageAccess().getNoteAssignment_5_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2219:1: ( rule__SelfMessage__NoteAssignment_5_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2219:2: rule__SelfMessage__NoteAssignment_5_1
            {
            pushFollow(FOLLOW_rule__SelfMessage__NoteAssignment_5_1_in_rule__SelfMessage__Group_5__1__Impl4515);
            rule__SelfMessage__NoteAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getSelfMessageAccess().getNoteAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__Group_5__1__Impl"


    // $ANTLR start "rule__OneLifelineNote__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2233:1: rule__OneLifelineNote__Group__0 : rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 ;
    public final void rule__OneLifelineNote__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2237:1: ( rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2238:2: rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__04549);
            rule__OneLifelineNote__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__04552);
            rule__OneLifelineNote__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__Group__0"


    // $ANTLR start "rule__OneLifelineNote__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2245:1: rule__OneLifelineNote__Group__0__Impl : ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineNote__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2249:1: ( ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2250:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2250:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2251:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2252:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2252:2: rule__OneLifelineNote__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl4579);
            rule__OneLifelineNote__LifelineAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineNoteAccess().getLifelineAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__Group__0__Impl"


    // $ANTLR start "rule__OneLifelineNote__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2262:1: rule__OneLifelineNote__Group__1 : rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 ;
    public final void rule__OneLifelineNote__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2266:1: ( rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2267:2: rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__14609);
            rule__OneLifelineNote__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__14612);
            rule__OneLifelineNote__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__Group__1"


    // $ANTLR start "rule__OneLifelineNote__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2274:1: rule__OneLifelineNote__Group__1__Impl : ( 'note' ) ;
    public final void rule__OneLifelineNote__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2278:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2279:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2279:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2280:1: 'note'
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1()); 
            match(input,25,FOLLOW_25_in_rule__OneLifelineNote__Group__1__Impl4640); 
             after(grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__Group__1__Impl"


    // $ANTLR start "rule__OneLifelineNote__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2293:1: rule__OneLifelineNote__Group__2 : rule__OneLifelineNote__Group__2__Impl ;
    public final void rule__OneLifelineNote__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2297:1: ( rule__OneLifelineNote__Group__2__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2298:2: rule__OneLifelineNote__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__24671);
            rule__OneLifelineNote__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__Group__2"


    // $ANTLR start "rule__OneLifelineNote__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2304:1: rule__OneLifelineNote__Group__2__Impl : ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) ;
    public final void rule__OneLifelineNote__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2308:1: ( ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2309:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2309:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2310:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2311:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2311:2: rule__OneLifelineNote__NoteAssignment_2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl4698);
            rule__OneLifelineNote__NoteAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineNoteAccess().getNoteAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__Group__2__Impl"


    // $ANTLR start "rule__DestroyLifelineEvent__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2327:1: rule__DestroyLifelineEvent__Group__0 : rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1 ;
    public final void rule__DestroyLifelineEvent__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2331:1: ( rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2332:2: rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__0__Impl_in_rule__DestroyLifelineEvent__Group__04734);
            rule__DestroyLifelineEvent__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__1_in_rule__DestroyLifelineEvent__Group__04737);
            rule__DestroyLifelineEvent__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DestroyLifelineEvent__Group__0"


    // $ANTLR start "rule__DestroyLifelineEvent__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2339:1: rule__DestroyLifelineEvent__Group__0__Impl : ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) ) ;
    public final void rule__DestroyLifelineEvent__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2343:1: ( ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2344:1: ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2344:1: ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2345:1: ( rule__DestroyLifelineEvent__LifelineAssignment_0 )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2346:1: ( rule__DestroyLifelineEvent__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2346:2: rule__DestroyLifelineEvent__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__LifelineAssignment_0_in_rule__DestroyLifelineEvent__Group__0__Impl4764);
            rule__DestroyLifelineEvent__LifelineAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDestroyLifelineEventAccess().getLifelineAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DestroyLifelineEvent__Group__0__Impl"


    // $ANTLR start "rule__DestroyLifelineEvent__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2356:1: rule__DestroyLifelineEvent__Group__1 : rule__DestroyLifelineEvent__Group__1__Impl ;
    public final void rule__DestroyLifelineEvent__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2360:1: ( rule__DestroyLifelineEvent__Group__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2361:2: rule__DestroyLifelineEvent__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__1__Impl_in_rule__DestroyLifelineEvent__Group__14794);
            rule__DestroyLifelineEvent__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DestroyLifelineEvent__Group__1"


    // $ANTLR start "rule__DestroyLifelineEvent__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2367:1: rule__DestroyLifelineEvent__Group__1__Impl : ( 'destroy' ) ;
    public final void rule__DestroyLifelineEvent__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2371:1: ( ( 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2372:1: ( 'destroy' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2372:1: ( 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2373:1: 'destroy'
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getDestroyKeyword_1()); 
            match(input,27,FOLLOW_27_in_rule__DestroyLifelineEvent__Group__1__Impl4822); 
             after(grammarAccess.getDestroyLifelineEventAccess().getDestroyKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DestroyLifelineEvent__Group__1__Impl"


    // $ANTLR start "rule__Fragment__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2390:1: rule__Fragment__Group__0 : rule__Fragment__Group__0__Impl rule__Fragment__Group__1 ;
    public final void rule__Fragment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2394:1: ( rule__Fragment__Group__0__Impl rule__Fragment__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2395:2: rule__Fragment__Group__0__Impl rule__Fragment__Group__1
            {
            pushFollow(FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__04857);
            rule__Fragment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__04860);
            rule__Fragment__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__0"


    // $ANTLR start "rule__Fragment__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2402:1: rule__Fragment__Group__0__Impl : ( 'fragment' ) ;
    public final void rule__Fragment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2406:1: ( ( 'fragment' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2407:1: ( 'fragment' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2407:1: ( 'fragment' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2408:1: 'fragment'
            {
             before(grammarAccess.getFragmentAccess().getFragmentKeyword_0()); 
            match(input,28,FOLLOW_28_in_rule__Fragment__Group__0__Impl4888); 
             after(grammarAccess.getFragmentAccess().getFragmentKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__0__Impl"


    // $ANTLR start "rule__Fragment__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2421:1: rule__Fragment__Group__1 : rule__Fragment__Group__1__Impl rule__Fragment__Group__2 ;
    public final void rule__Fragment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2425:1: ( rule__Fragment__Group__1__Impl rule__Fragment__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2426:2: rule__Fragment__Group__1__Impl rule__Fragment__Group__2
            {
            pushFollow(FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__14919);
            rule__Fragment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__14922);
            rule__Fragment__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__1"


    // $ANTLR start "rule__Fragment__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2433:1: rule__Fragment__Group__1__Impl : ( ( rule__Fragment__NameAssignment_1 ) ) ;
    public final void rule__Fragment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2437:1: ( ( ( rule__Fragment__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2438:1: ( ( rule__Fragment__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2438:1: ( ( rule__Fragment__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2439:1: ( rule__Fragment__NameAssignment_1 )
            {
             before(grammarAccess.getFragmentAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2440:1: ( rule__Fragment__NameAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2440:2: rule__Fragment__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl4949);
            rule__Fragment__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFragmentAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__1__Impl"


    // $ANTLR start "rule__Fragment__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2450:1: rule__Fragment__Group__2 : rule__Fragment__Group__2__Impl rule__Fragment__Group__3 ;
    public final void rule__Fragment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2454:1: ( rule__Fragment__Group__2__Impl rule__Fragment__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2455:2: rule__Fragment__Group__2__Impl rule__Fragment__Group__3
            {
            pushFollow(FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__24979);
            rule__Fragment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__24982);
            rule__Fragment__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__2"


    // $ANTLR start "rule__Fragment__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2462:1: rule__Fragment__Group__2__Impl : ( ( rule__Fragment__SectionsAssignment_2 ) ) ;
    public final void rule__Fragment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2466:1: ( ( ( rule__Fragment__SectionsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2467:1: ( ( rule__Fragment__SectionsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2467:1: ( ( rule__Fragment__SectionsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2468:1: ( rule__Fragment__SectionsAssignment_2 )
            {
             before(grammarAccess.getFragmentAccess().getSectionsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2469:1: ( rule__Fragment__SectionsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2469:2: rule__Fragment__SectionsAssignment_2
            {
            pushFollow(FOLLOW_rule__Fragment__SectionsAssignment_2_in_rule__Fragment__Group__2__Impl5009);
            rule__Fragment__SectionsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFragmentAccess().getSectionsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__2__Impl"


    // $ANTLR start "rule__Fragment__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2479:1: rule__Fragment__Group__3 : rule__Fragment__Group__3__Impl ;
    public final void rule__Fragment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2483:1: ( rule__Fragment__Group__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2484:2: rule__Fragment__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__35039);
            rule__Fragment__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__3"


    // $ANTLR start "rule__Fragment__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2490:1: rule__Fragment__Group__3__Impl : ( ( rule__Fragment__SectionsAssignment_3 )* ) ;
    public final void rule__Fragment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2494:1: ( ( ( rule__Fragment__SectionsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2495:1: ( ( rule__Fragment__SectionsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2495:1: ( ( rule__Fragment__SectionsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2496:1: ( rule__Fragment__SectionsAssignment_3 )*
            {
             before(grammarAccess.getFragmentAccess().getSectionsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2497:1: ( rule__Fragment__SectionsAssignment_3 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==29) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2497:2: rule__Fragment__SectionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Fragment__SectionsAssignment_3_in_rule__Fragment__Group__3__Impl5066);
            	    rule__Fragment__SectionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getFragmentAccess().getSectionsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__Group__3__Impl"


    // $ANTLR start "rule__Section__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2515:1: rule__Section__Group__0 : rule__Section__Group__0__Impl rule__Section__Group__1 ;
    public final void rule__Section__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2519:1: ( rule__Section__Group__0__Impl rule__Section__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2520:2: rule__Section__Group__0__Impl rule__Section__Group__1
            {
            pushFollow(FOLLOW_rule__Section__Group__0__Impl_in_rule__Section__Group__05105);
            rule__Section__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__1_in_rule__Section__Group__05108);
            rule__Section__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__0"


    // $ANTLR start "rule__Section__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2527:1: rule__Section__Group__0__Impl : ( '{' ) ;
    public final void rule__Section__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2531:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2532:1: ( '{' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2532:1: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2533:1: '{'
            {
             before(grammarAccess.getSectionAccess().getLeftCurlyBracketKeyword_0()); 
            match(input,29,FOLLOW_29_in_rule__Section__Group__0__Impl5136); 
             after(grammarAccess.getSectionAccess().getLeftCurlyBracketKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__0__Impl"


    // $ANTLR start "rule__Section__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2546:1: rule__Section__Group__1 : rule__Section__Group__1__Impl rule__Section__Group__2 ;
    public final void rule__Section__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2550:1: ( rule__Section__Group__1__Impl rule__Section__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2551:2: rule__Section__Group__1__Impl rule__Section__Group__2
            {
            pushFollow(FOLLOW_rule__Section__Group__1__Impl_in_rule__Section__Group__15167);
            rule__Section__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__2_in_rule__Section__Group__15170);
            rule__Section__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__1"


    // $ANTLR start "rule__Section__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2558:1: rule__Section__Group__1__Impl : ( ( rule__Section__Group_1__0 )? ) ;
    public final void rule__Section__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2562:1: ( ( ( rule__Section__Group_1__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2563:1: ( ( rule__Section__Group_1__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2563:1: ( ( rule__Section__Group_1__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2564:1: ( rule__Section__Group_1__0 )?
            {
             before(grammarAccess.getSectionAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2565:1: ( rule__Section__Group_1__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==31) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2565:2: rule__Section__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Section__Group_1__0_in_rule__Section__Group__1__Impl5197);
                    rule__Section__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSectionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__1__Impl"


    // $ANTLR start "rule__Section__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2575:1: rule__Section__Group__2 : rule__Section__Group__2__Impl rule__Section__Group__3 ;
    public final void rule__Section__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2579:1: ( rule__Section__Group__2__Impl rule__Section__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2580:2: rule__Section__Group__2__Impl rule__Section__Group__3
            {
            pushFollow(FOLLOW_rule__Section__Group__2__Impl_in_rule__Section__Group__25228);
            rule__Section__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__3_in_rule__Section__Group__25231);
            rule__Section__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__2"


    // $ANTLR start "rule__Section__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2587:1: rule__Section__Group__2__Impl : ( ( rule__Section__InteractionsAssignment_2 ) ) ;
    public final void rule__Section__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2591:1: ( ( ( rule__Section__InteractionsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2592:1: ( ( rule__Section__InteractionsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2592:1: ( ( rule__Section__InteractionsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2593:1: ( rule__Section__InteractionsAssignment_2 )
            {
             before(grammarAccess.getSectionAccess().getInteractionsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2594:1: ( rule__Section__InteractionsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2594:2: rule__Section__InteractionsAssignment_2
            {
            pushFollow(FOLLOW_rule__Section__InteractionsAssignment_2_in_rule__Section__Group__2__Impl5258);
            rule__Section__InteractionsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSectionAccess().getInteractionsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__2__Impl"


    // $ANTLR start "rule__Section__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2604:1: rule__Section__Group__3 : rule__Section__Group__3__Impl rule__Section__Group__4 ;
    public final void rule__Section__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2608:1: ( rule__Section__Group__3__Impl rule__Section__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2609:2: rule__Section__Group__3__Impl rule__Section__Group__4
            {
            pushFollow(FOLLOW_rule__Section__Group__3__Impl_in_rule__Section__Group__35288);
            rule__Section__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__4_in_rule__Section__Group__35291);
            rule__Section__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__3"


    // $ANTLR start "rule__Section__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2616:1: rule__Section__Group__3__Impl : ( ( rule__Section__InteractionsAssignment_3 )* ) ;
    public final void rule__Section__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2620:1: ( ( ( rule__Section__InteractionsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2621:1: ( ( rule__Section__InteractionsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2621:1: ( ( rule__Section__InteractionsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2622:1: ( rule__Section__InteractionsAssignment_3 )*
            {
             before(grammarAccess.getSectionAccess().getInteractionsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2623:1: ( rule__Section__InteractionsAssignment_3 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID||LA26_0==28||LA26_0==32) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2623:2: rule__Section__InteractionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Section__InteractionsAssignment_3_in_rule__Section__Group__3__Impl5318);
            	    rule__Section__InteractionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getSectionAccess().getInteractionsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__3__Impl"


    // $ANTLR start "rule__Section__Group__4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2633:1: rule__Section__Group__4 : rule__Section__Group__4__Impl ;
    public final void rule__Section__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2637:1: ( rule__Section__Group__4__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2638:2: rule__Section__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__Section__Group__4__Impl_in_rule__Section__Group__45349);
            rule__Section__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__4"


    // $ANTLR start "rule__Section__Group__4__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2644:1: rule__Section__Group__4__Impl : ( '}' ) ;
    public final void rule__Section__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2648:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2649:1: ( '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2649:1: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2650:1: '}'
            {
             before(grammarAccess.getSectionAccess().getRightCurlyBracketKeyword_4()); 
            match(input,30,FOLLOW_30_in_rule__Section__Group__4__Impl5377); 
             after(grammarAccess.getSectionAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group__4__Impl"


    // $ANTLR start "rule__Section__Group_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2673:1: rule__Section__Group_1__0 : rule__Section__Group_1__0__Impl rule__Section__Group_1__1 ;
    public final void rule__Section__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2677:1: ( rule__Section__Group_1__0__Impl rule__Section__Group_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2678:2: rule__Section__Group_1__0__Impl rule__Section__Group_1__1
            {
            pushFollow(FOLLOW_rule__Section__Group_1__0__Impl_in_rule__Section__Group_1__05418);
            rule__Section__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group_1__1_in_rule__Section__Group_1__05421);
            rule__Section__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group_1__0"


    // $ANTLR start "rule__Section__Group_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2685:1: rule__Section__Group_1__0__Impl : ( 'label' ) ;
    public final void rule__Section__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2689:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2690:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2690:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2691:1: 'label'
            {
             before(grammarAccess.getSectionAccess().getLabelKeyword_1_0()); 
            match(input,31,FOLLOW_31_in_rule__Section__Group_1__0__Impl5449); 
             after(grammarAccess.getSectionAccess().getLabelKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group_1__0__Impl"


    // $ANTLR start "rule__Section__Group_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2704:1: rule__Section__Group_1__1 : rule__Section__Group_1__1__Impl ;
    public final void rule__Section__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2708:1: ( rule__Section__Group_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2709:2: rule__Section__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Section__Group_1__1__Impl_in_rule__Section__Group_1__15480);
            rule__Section__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group_1__1"


    // $ANTLR start "rule__Section__Group_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2715:1: rule__Section__Group_1__1__Impl : ( ( rule__Section__LabelAssignment_1_1 ) ) ;
    public final void rule__Section__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2719:1: ( ( ( rule__Section__LabelAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2720:1: ( ( rule__Section__LabelAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2720:1: ( ( rule__Section__LabelAssignment_1_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2721:1: ( rule__Section__LabelAssignment_1_1 )
            {
             before(grammarAccess.getSectionAccess().getLabelAssignment_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2722:1: ( rule__Section__LabelAssignment_1_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2722:2: rule__Section__LabelAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Section__LabelAssignment_1_1_in_rule__Section__Group_1__1__Impl5507);
            rule__Section__LabelAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getSectionAccess().getLabelAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__Group_1__1__Impl"


    // $ANTLR start "rule__Refinement__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2736:1: rule__Refinement__Group__0 : rule__Refinement__Group__0__Impl rule__Refinement__Group__1 ;
    public final void rule__Refinement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2740:1: ( rule__Refinement__Group__0__Impl rule__Refinement__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2741:2: rule__Refinement__Group__0__Impl rule__Refinement__Group__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__05541);
            rule__Refinement__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__05544);
            rule__Refinement__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__0"


    // $ANTLR start "rule__Refinement__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2748:1: rule__Refinement__Group__0__Impl : ( 'refinement' ) ;
    public final void rule__Refinement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2752:1: ( ( 'refinement' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2753:1: ( 'refinement' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2753:1: ( 'refinement' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2754:1: 'refinement'
            {
             before(grammarAccess.getRefinementAccess().getRefinementKeyword_0()); 
            match(input,32,FOLLOW_32_in_rule__Refinement__Group__0__Impl5572); 
             after(grammarAccess.getRefinementAccess().getRefinementKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__0__Impl"


    // $ANTLR start "rule__Refinement__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2767:1: rule__Refinement__Group__1 : rule__Refinement__Group__1__Impl rule__Refinement__Group__2 ;
    public final void rule__Refinement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2771:1: ( rule__Refinement__Group__1__Impl rule__Refinement__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2772:2: rule__Refinement__Group__1__Impl rule__Refinement__Group__2
            {
            pushFollow(FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__15603);
            rule__Refinement__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__15606);
            rule__Refinement__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__1"


    // $ANTLR start "rule__Refinement__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2779:1: rule__Refinement__Group__1__Impl : ( 'label' ) ;
    public final void rule__Refinement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2783:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2784:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2784:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2785:1: 'label'
            {
             before(grammarAccess.getRefinementAccess().getLabelKeyword_1()); 
            match(input,31,FOLLOW_31_in_rule__Refinement__Group__1__Impl5634); 
             after(grammarAccess.getRefinementAccess().getLabelKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__1__Impl"


    // $ANTLR start "rule__Refinement__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2798:1: rule__Refinement__Group__2 : rule__Refinement__Group__2__Impl rule__Refinement__Group__3 ;
    public final void rule__Refinement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2802:1: ( rule__Refinement__Group__2__Impl rule__Refinement__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2803:2: rule__Refinement__Group__2__Impl rule__Refinement__Group__3
            {
            pushFollow(FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__25665);
            rule__Refinement__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__25668);
            rule__Refinement__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__2"


    // $ANTLR start "rule__Refinement__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2810:1: rule__Refinement__Group__2__Impl : ( ( rule__Refinement__LabelAssignment_2 ) ) ;
    public final void rule__Refinement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2814:1: ( ( ( rule__Refinement__LabelAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2815:1: ( ( rule__Refinement__LabelAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2815:1: ( ( rule__Refinement__LabelAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2816:1: ( rule__Refinement__LabelAssignment_2 )
            {
             before(grammarAccess.getRefinementAccess().getLabelAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2817:1: ( rule__Refinement__LabelAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2817:2: rule__Refinement__LabelAssignment_2
            {
            pushFollow(FOLLOW_rule__Refinement__LabelAssignment_2_in_rule__Refinement__Group__2__Impl5695);
            rule__Refinement__LabelAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getRefinementAccess().getLabelAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__2__Impl"


    // $ANTLR start "rule__Refinement__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2827:1: rule__Refinement__Group__3 : rule__Refinement__Group__3__Impl rule__Refinement__Group__4 ;
    public final void rule__Refinement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2831:1: ( rule__Refinement__Group__3__Impl rule__Refinement__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2832:2: rule__Refinement__Group__3__Impl rule__Refinement__Group__4
            {
            pushFollow(FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__35725);
            rule__Refinement__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__35728);
            rule__Refinement__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__3"


    // $ANTLR start "rule__Refinement__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2839:1: rule__Refinement__Group__3__Impl : ( 'lifelines' ) ;
    public final void rule__Refinement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2843:1: ( ( 'lifelines' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2844:1: ( 'lifelines' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2844:1: ( 'lifelines' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2845:1: 'lifelines'
            {
             before(grammarAccess.getRefinementAccess().getLifelinesKeyword_3()); 
            match(input,33,FOLLOW_33_in_rule__Refinement__Group__3__Impl5756); 
             after(grammarAccess.getRefinementAccess().getLifelinesKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__3__Impl"


    // $ANTLR start "rule__Refinement__Group__4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2858:1: rule__Refinement__Group__4 : rule__Refinement__Group__4__Impl rule__Refinement__Group__5 ;
    public final void rule__Refinement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2862:1: ( rule__Refinement__Group__4__Impl rule__Refinement__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2863:2: rule__Refinement__Group__4__Impl rule__Refinement__Group__5
            {
            pushFollow(FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__45787);
            rule__Refinement__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__45790);
            rule__Refinement__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__4"


    // $ANTLR start "rule__Refinement__Group__4__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2870:1: rule__Refinement__Group__4__Impl : ( ( rule__Refinement__LifelinesAssignment_4 ) ) ;
    public final void rule__Refinement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2874:1: ( ( ( rule__Refinement__LifelinesAssignment_4 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2875:1: ( ( rule__Refinement__LifelinesAssignment_4 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2875:1: ( ( rule__Refinement__LifelinesAssignment_4 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2876:1: ( rule__Refinement__LifelinesAssignment_4 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2877:1: ( rule__Refinement__LifelinesAssignment_4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2877:2: rule__Refinement__LifelinesAssignment_4
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_4_in_rule__Refinement__Group__4__Impl5817);
            rule__Refinement__LifelinesAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getRefinementAccess().getLifelinesAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__4__Impl"


    // $ANTLR start "rule__Refinement__Group__5"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2887:1: rule__Refinement__Group__5 : rule__Refinement__Group__5__Impl ;
    public final void rule__Refinement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2891:1: ( rule__Refinement__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2892:2: rule__Refinement__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__55847);
            rule__Refinement__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__5"


    // $ANTLR start "rule__Refinement__Group__5__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2898:1: rule__Refinement__Group__5__Impl : ( ( rule__Refinement__Group_5__0 )* ) ;
    public final void rule__Refinement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2902:1: ( ( ( rule__Refinement__Group_5__0 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2903:1: ( ( rule__Refinement__Group_5__0 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2903:1: ( ( rule__Refinement__Group_5__0 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2904:1: ( rule__Refinement__Group_5__0 )*
            {
             before(grammarAccess.getRefinementAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2905:1: ( rule__Refinement__Group_5__0 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==34) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2905:2: rule__Refinement__Group_5__0
            	    {
            	    pushFollow(FOLLOW_rule__Refinement__Group_5__0_in_rule__Refinement__Group__5__Impl5874);
            	    rule__Refinement__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getRefinementAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__5__Impl"


    // $ANTLR start "rule__Refinement__Group_5__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2927:1: rule__Refinement__Group_5__0 : rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1 ;
    public final void rule__Refinement__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2931:1: ( rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2932:2: rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group_5__0__Impl_in_rule__Refinement__Group_5__05917);
            rule__Refinement__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group_5__1_in_rule__Refinement__Group_5__05920);
            rule__Refinement__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_5__0"


    // $ANTLR start "rule__Refinement__Group_5__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2939:1: rule__Refinement__Group_5__0__Impl : ( ',' ) ;
    public final void rule__Refinement__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2943:1: ( ( ',' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2944:1: ( ',' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2944:1: ( ',' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2945:1: ','
            {
             before(grammarAccess.getRefinementAccess().getCommaKeyword_5_0()); 
            match(input,34,FOLLOW_34_in_rule__Refinement__Group_5__0__Impl5948); 
             after(grammarAccess.getRefinementAccess().getCommaKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_5__0__Impl"


    // $ANTLR start "rule__Refinement__Group_5__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2958:1: rule__Refinement__Group_5__1 : rule__Refinement__Group_5__1__Impl ;
    public final void rule__Refinement__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2962:1: ( rule__Refinement__Group_5__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2963:2: rule__Refinement__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group_5__1__Impl_in_rule__Refinement__Group_5__15979);
            rule__Refinement__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_5__1"


    // $ANTLR start "rule__Refinement__Group_5__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2969:1: rule__Refinement__Group_5__1__Impl : ( ( rule__Refinement__LifelinesAssignment_5_1 ) ) ;
    public final void rule__Refinement__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2973:1: ( ( ( rule__Refinement__LifelinesAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2974:1: ( ( rule__Refinement__LifelinesAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2974:1: ( ( rule__Refinement__LifelinesAssignment_5_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2975:1: ( rule__Refinement__LifelinesAssignment_5_1 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_5_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2976:1: ( rule__Refinement__LifelinesAssignment_5_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2976:2: rule__Refinement__LifelinesAssignment_5_1
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_5_1_in_rule__Refinement__Group_5__1__Impl6006);
            rule__Refinement__LifelinesAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getRefinementAccess().getLifelinesAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_5__1__Impl"


    // $ANTLR start "rule__SequenceDiagram__DiagramNameAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2991:1: rule__SequenceDiagram__DiagramNameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__SequenceDiagram__DiagramNameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2995:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2996:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2996:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2997:1: RULE_STRING
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_26045); 
             after(grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__DiagramNameAssignment_2"


    // $ANTLR start "rule__SequenceDiagram__LifelinesAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3006:1: rule__SequenceDiagram__LifelinesAssignment_3 : ( ruleLifeline ) ;
    public final void rule__SequenceDiagram__LifelinesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3010:1: ( ( ruleLifeline ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3011:1: ( ruleLifeline )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3011:1: ( ruleLifeline )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3012:1: ruleLifeline
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_36076);
            ruleLifeline();

            state._fsp--;

             after(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__LifelinesAssignment_3"


    // $ANTLR start "rule__SequenceDiagram__InteractionsAssignment_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3021:1: rule__SequenceDiagram__InteractionsAssignment_4 : ( ruleInteraction ) ;
    public final void rule__SequenceDiagram__InteractionsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3025:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3026:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3026:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3027:1: ruleInteraction
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_46107);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__InteractionsAssignment_4"


    // $ANTLR start "rule__Lifeline__CaptionAssignment_0_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3036:1: rule__Lifeline__CaptionAssignment_0_1 : ( RULE_STRING ) ;
    public final void rule__Lifeline__CaptionAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3040:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3041:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3041:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3042:1: RULE_STRING
            {
             before(grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_0_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_0_16138); 
             after(grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__CaptionAssignment_0_1"


    // $ANTLR start "rule__Lifeline__NameAssignment_0_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3051:1: rule__Lifeline__NameAssignment_0_3 : ( RULE_ID ) ;
    public final void rule__Lifeline__NameAssignment_0_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3055:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3056:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3056:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3057:1: RULE_ID
            {
             before(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_0_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_0_36169); 
             after(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__NameAssignment_0_3"


    // $ANTLR start "rule__Lifeline__UsecaseCaptionAssignment_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3066:1: rule__Lifeline__UsecaseCaptionAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__Lifeline__UsecaseCaptionAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3070:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3071:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3071:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3072:1: RULE_STRING
            {
             before(grammarAccess.getLifelineAccess().getUsecaseCaptionSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Lifeline__UsecaseCaptionAssignment_1_16200); 
             after(grammarAccess.getLifelineAccess().getUsecaseCaptionSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__UsecaseCaptionAssignment_1_1"


    // $ANTLR start "rule__Lifeline__NameAssignment_1_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3081:1: rule__Lifeline__NameAssignment_1_3 : ( RULE_ID ) ;
    public final void rule__Lifeline__NameAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3085:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3086:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3086:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3087:1: RULE_ID
            {
             before(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_1_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_1_36231); 
             after(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__NameAssignment_1_3"


    // $ANTLR start "rule__TwoLifelineMessage__SourceLifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3096:1: rule__TwoLifelineMessage__SourceLifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__SourceLifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3100:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3101:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3101:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3102:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3103:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3104:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_06266); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceLifelineAssignment_0"


    // $ANTLR start "rule__TwoLifelineMessage__MessageTypeAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3115:1: rule__TwoLifelineMessage__MessageTypeAssignment_1 : ( ruleMessageType ) ;
    public final void rule__TwoLifelineMessage__MessageTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3119:1: ( ( ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3120:1: ( ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3120:1: ( ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3121:1: ruleMessageType
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleMessageType_in_rule__TwoLifelineMessage__MessageTypeAssignment_16301);
            ruleMessageType();

            state._fsp--;

             after(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__MessageTypeAssignment_1"


    // $ANTLR start "rule__TwoLifelineMessage__MessageAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3130:1: rule__TwoLifelineMessage__MessageAssignment_2 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__MessageAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3134:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3135:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3135:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3136:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__MessageAssignment_26332); 
             after(grammarAccess.getTwoLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__MessageAssignment_2"


    // $ANTLR start "rule__TwoLifelineMessage__TargetLifelineAssignment_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3145:1: rule__TwoLifelineMessage__TargetLifelineAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__TargetLifelineAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3149:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3150:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3150:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3151:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3152:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3153:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_46367); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineIDTerminalRuleCall_4_0_1()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetLifelineAssignment_4"


    // $ANTLR start "rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3164:1: rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0 : ( ( 'sourceStartEndExec' ) ) ;
    public final void rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3168:1: ( ( ( 'sourceStartEndExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3169:1: ( ( 'sourceStartEndExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3169:1: ( ( 'sourceStartEndExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3170:1: ( 'sourceStartEndExec' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartEndExecSourceStartEndExecKeyword_5_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3171:1: ( 'sourceStartEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3172:1: 'sourceStartEndExec'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartEndExecSourceStartEndExecKeyword_5_0_0()); 
            match(input,35,FOLLOW_35_in_rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_06407); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartEndExecSourceStartEndExecKeyword_5_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartEndExecSourceStartEndExecKeyword_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0"


    // $ANTLR start "rule__TwoLifelineMessage__SourceStartExecAssignment_5_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3187:1: rule__TwoLifelineMessage__SourceStartExecAssignment_5_1 : ( ( 'sourceStartExec' ) ) ;
    public final void rule__TwoLifelineMessage__SourceStartExecAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3191:1: ( ( ( 'sourceStartExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3192:1: ( ( 'sourceStartExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3192:1: ( ( 'sourceStartExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3193:1: ( 'sourceStartExec' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartExecSourceStartExecKeyword_5_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3194:1: ( 'sourceStartExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3195:1: 'sourceStartExec'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartExecSourceStartExecKeyword_5_1_0()); 
            match(input,36,FOLLOW_36_in_rule__TwoLifelineMessage__SourceStartExecAssignment_5_16451); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartExecSourceStartExecKeyword_5_1_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartExecSourceStartExecKeyword_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceStartExecAssignment_5_1"


    // $ANTLR start "rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3210:1: rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0 : ( ( 'sourceEndExec' ) ) ;
    public final void rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3214:1: ( ( ( 'sourceEndExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3215:1: ( ( 'sourceEndExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3215:1: ( ( 'sourceEndExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3216:1: ( 'sourceEndExec' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecSourceEndExecKeyword_5_2_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3217:1: ( 'sourceEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3218:1: 'sourceEndExec'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecSourceEndExecKeyword_5_2_0_0()); 
            match(input,37,FOLLOW_37_in_rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_06495); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecSourceEndExecKeyword_5_2_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecSourceEndExecKeyword_5_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0"


    // $ANTLR start "rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3233:1: rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3237:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3238:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3238:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3239:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecCountINT_GREATER_ZEROTerminalRuleCall_5_2_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_16534); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecCountINT_GREATER_ZEROTerminalRuleCall_5_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1"


    // $ANTLR start "rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3248:1: rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0 : ( ( 'targetStartEndExec' ) ) ;
    public final void rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3252:1: ( ( ( 'targetStartEndExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3253:1: ( ( 'targetStartEndExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3253:1: ( ( 'targetStartEndExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3254:1: ( 'targetStartEndExec' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartEndExecTargetStartEndExecKeyword_6_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3255:1: ( 'targetStartEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3256:1: 'targetStartEndExec'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartEndExecTargetStartEndExecKeyword_6_0_0()); 
            match(input,38,FOLLOW_38_in_rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_06570); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartEndExecTargetStartEndExecKeyword_6_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartEndExecTargetStartEndExecKeyword_6_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0"


    // $ANTLR start "rule__TwoLifelineMessage__TargetStartExecAssignment_6_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3271:1: rule__TwoLifelineMessage__TargetStartExecAssignment_6_1 : ( ( 'targetStartExec' ) ) ;
    public final void rule__TwoLifelineMessage__TargetStartExecAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3275:1: ( ( ( 'targetStartExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3276:1: ( ( 'targetStartExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3276:1: ( ( 'targetStartExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3277:1: ( 'targetStartExec' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartExecTargetStartExecKeyword_6_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3278:1: ( 'targetStartExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3279:1: 'targetStartExec'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartExecTargetStartExecKeyword_6_1_0()); 
            match(input,39,FOLLOW_39_in_rule__TwoLifelineMessage__TargetStartExecAssignment_6_16614); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartExecTargetStartExecKeyword_6_1_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartExecTargetStartExecKeyword_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetStartExecAssignment_6_1"


    // $ANTLR start "rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3294:1: rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0 : ( ( 'targetEndExec' ) ) ;
    public final void rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3298:1: ( ( ( 'targetEndExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3299:1: ( ( 'targetEndExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3299:1: ( ( 'targetEndExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3300:1: ( 'targetEndExec' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecTargetEndExecKeyword_6_2_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3301:1: ( 'targetEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3302:1: 'targetEndExec'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecTargetEndExecKeyword_6_2_0_0()); 
            match(input,40,FOLLOW_40_in_rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_06658); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecTargetEndExecKeyword_6_2_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecTargetEndExecKeyword_6_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0"


    // $ANTLR start "rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3317:1: rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3321:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3322:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3322:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3323:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecCountINT_GREATER_ZEROTerminalRuleCall_6_2_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_16697); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecCountINT_GREATER_ZEROTerminalRuleCall_6_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1"


    // $ANTLR start "rule__TwoLifelineMessage__SourceNoteAssignment_7_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3332:1: rule__TwoLifelineMessage__SourceNoteAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__SourceNoteAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3336:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3337:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3337:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3338:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_16728); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceNoteAssignment_7_1"


    // $ANTLR start "rule__TwoLifelineMessage__TargetNoteAssignment_8_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3347:1: rule__TwoLifelineMessage__TargetNoteAssignment_8_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__TargetNoteAssignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3351:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3352:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3352:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3353:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_16759); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetNoteAssignment_8_1"


    // $ANTLR start "rule__OneLifelineMessage__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3362:1: rule__OneLifelineMessage__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineMessage__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3366:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3367:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3367:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3368:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3369:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3370:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_06794); 
             after(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__LifelineAssignment_0"


    // $ANTLR start "rule__OneLifelineMessage__MessageTypeAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3381:1: rule__OneLifelineMessage__MessageTypeAssignment_1 : ( ruleMessageType ) ;
    public final void rule__OneLifelineMessage__MessageTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3385:1: ( ( ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3386:1: ( ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3386:1: ( ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3387:1: ruleMessageType
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleMessageType_in_rule__OneLifelineMessage__MessageTypeAssignment_16829);
            ruleMessageType();

            state._fsp--;

             after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__MessageTypeAssignment_1"


    // $ANTLR start "rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3396:1: rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 : ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) ) ;
    public final void rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3400:1: ( ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3401:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3401:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3402:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundAlternatives_2_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3403:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3403:2: rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_26860);
            rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundAlternatives_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2"


    // $ANTLR start "rule__OneLifelineMessage__MessageAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3412:1: rule__OneLifelineMessage__MessageAssignment_3 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__MessageAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3416:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3417:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3417:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3418:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__MessageAssignment_36893); 
             after(grammarAccess.getOneLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__MessageAssignment_3"


    // $ANTLR start "rule__OneLifelineMessage__StartEndExecAssignment_4_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3427:1: rule__OneLifelineMessage__StartEndExecAssignment_4_0 : ( ( 'startEndExec' ) ) ;
    public final void rule__OneLifelineMessage__StartEndExecAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3431:1: ( ( ( 'startEndExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3432:1: ( ( 'startEndExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3432:1: ( ( 'startEndExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3433:1: ( 'startEndExec' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3434:1: ( 'startEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3435:1: 'startEndExec'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 
            match(input,41,FOLLOW_41_in_rule__OneLifelineMessage__StartEndExecAssignment_4_06929); 
             after(grammarAccess.getOneLifelineMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__StartEndExecAssignment_4_0"


    // $ANTLR start "rule__OneLifelineMessage__StartExecAssignment_4_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3450:1: rule__OneLifelineMessage__StartExecAssignment_4_1 : ( ( 'startExec' ) ) ;
    public final void rule__OneLifelineMessage__StartExecAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3454:1: ( ( ( 'startExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3455:1: ( ( 'startExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3455:1: ( ( 'startExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3456:1: ( 'startExec' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartExecStartExecKeyword_4_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3457:1: ( 'startExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3458:1: 'startExec'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartExecStartExecKeyword_4_1_0()); 
            match(input,42,FOLLOW_42_in_rule__OneLifelineMessage__StartExecAssignment_4_16973); 
             after(grammarAccess.getOneLifelineMessageAccess().getStartExecStartExecKeyword_4_1_0()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getStartExecStartExecKeyword_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__StartExecAssignment_4_1"


    // $ANTLR start "rule__OneLifelineMessage__EndExecAssignment_4_2_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3473:1: rule__OneLifelineMessage__EndExecAssignment_4_2_0 : ( ( 'endExec' ) ) ;
    public final void rule__OneLifelineMessage__EndExecAssignment_4_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3477:1: ( ( ( 'endExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3478:1: ( ( 'endExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3478:1: ( ( 'endExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3479:1: ( 'endExec' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3480:1: ( 'endExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3481:1: 'endExec'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 
            match(input,43,FOLLOW_43_in_rule__OneLifelineMessage__EndExecAssignment_4_2_07017); 
             after(grammarAccess.getOneLifelineMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__EndExecAssignment_4_2_0"


    // $ANTLR start "rule__OneLifelineMessage__EndExecCountAssignment_4_2_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3496:1: rule__OneLifelineMessage__EndExecCountAssignment_4_2_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__OneLifelineMessage__EndExecCountAssignment_4_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3500:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3501:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3501:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3502:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndExecCountINT_GREATER_ZEROTerminalRuleCall_4_2_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndExecCountAssignment_4_2_17056); 
             after(grammarAccess.getOneLifelineMessageAccess().getEndExecCountINT_GREATER_ZEROTerminalRuleCall_4_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__EndExecCountAssignment_4_2_1"


    // $ANTLR start "rule__OneLifelineMessage__NoteAssignment_5_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3511:1: rule__OneLifelineMessage__NoteAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__NoteAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3515:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3516:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3516:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3517:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_5_17087); 
             after(grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__NoteAssignment_5_1"


    // $ANTLR start "rule__SelfMessage__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3526:1: rule__SelfMessage__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__SelfMessage__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3530:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3531:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3531:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3532:1: ( RULE_ID )
            {
             before(grammarAccess.getSelfMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3533:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3534:1: RULE_ID
            {
             before(grammarAccess.getSelfMessageAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__SelfMessage__LifelineAssignment_07122); 
             after(grammarAccess.getSelfMessageAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getSelfMessageAccess().getLifelineLifelineCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__LifelineAssignment_0"


    // $ANTLR start "rule__SelfMessage__MessageTypeAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3545:1: rule__SelfMessage__MessageTypeAssignment_1 : ( ( rule__SelfMessage__MessageTypeAlternatives_1_0 ) ) ;
    public final void rule__SelfMessage__MessageTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3549:1: ( ( ( rule__SelfMessage__MessageTypeAlternatives_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3550:1: ( ( rule__SelfMessage__MessageTypeAlternatives_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3550:1: ( ( rule__SelfMessage__MessageTypeAlternatives_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3551:1: ( rule__SelfMessage__MessageTypeAlternatives_1_0 )
            {
             before(grammarAccess.getSelfMessageAccess().getMessageTypeAlternatives_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3552:1: ( rule__SelfMessage__MessageTypeAlternatives_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3552:2: rule__SelfMessage__MessageTypeAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__SelfMessage__MessageTypeAlternatives_1_0_in_rule__SelfMessage__MessageTypeAssignment_17157);
            rule__SelfMessage__MessageTypeAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getSelfMessageAccess().getMessageTypeAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__MessageTypeAssignment_1"


    // $ANTLR start "rule__SelfMessage__MessageAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3561:1: rule__SelfMessage__MessageAssignment_3 : ( RULE_STRING ) ;
    public final void rule__SelfMessage__MessageAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3565:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3566:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3566:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3567:1: RULE_STRING
            {
             before(grammarAccess.getSelfMessageAccess().getMessageSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SelfMessage__MessageAssignment_37190); 
             after(grammarAccess.getSelfMessageAccess().getMessageSTRINGTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__MessageAssignment_3"


    // $ANTLR start "rule__SelfMessage__StartEndExecAssignment_4_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3576:1: rule__SelfMessage__StartEndExecAssignment_4_0 : ( ( 'startEndExec' ) ) ;
    public final void rule__SelfMessage__StartEndExecAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3580:1: ( ( ( 'startEndExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3581:1: ( ( 'startEndExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3581:1: ( ( 'startEndExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3582:1: ( 'startEndExec' )
            {
             before(grammarAccess.getSelfMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3583:1: ( 'startEndExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3584:1: 'startEndExec'
            {
             before(grammarAccess.getSelfMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 
            match(input,41,FOLLOW_41_in_rule__SelfMessage__StartEndExecAssignment_4_07226); 
             after(grammarAccess.getSelfMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 

            }

             after(grammarAccess.getSelfMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__StartEndExecAssignment_4_0"


    // $ANTLR start "rule__SelfMessage__StartExecAssignment_4_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3599:1: rule__SelfMessage__StartExecAssignment_4_1 : ( ( 'startExec' ) ) ;
    public final void rule__SelfMessage__StartExecAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3603:1: ( ( ( 'startExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3604:1: ( ( 'startExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3604:1: ( ( 'startExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3605:1: ( 'startExec' )
            {
             before(grammarAccess.getSelfMessageAccess().getStartExecStartExecKeyword_4_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3606:1: ( 'startExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3607:1: 'startExec'
            {
             before(grammarAccess.getSelfMessageAccess().getStartExecStartExecKeyword_4_1_0()); 
            match(input,42,FOLLOW_42_in_rule__SelfMessage__StartExecAssignment_4_17270); 
             after(grammarAccess.getSelfMessageAccess().getStartExecStartExecKeyword_4_1_0()); 

            }

             after(grammarAccess.getSelfMessageAccess().getStartExecStartExecKeyword_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__StartExecAssignment_4_1"


    // $ANTLR start "rule__SelfMessage__EndExecAssignment_4_2_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3622:1: rule__SelfMessage__EndExecAssignment_4_2_0 : ( ( 'endExec' ) ) ;
    public final void rule__SelfMessage__EndExecAssignment_4_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3626:1: ( ( ( 'endExec' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3627:1: ( ( 'endExec' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3627:1: ( ( 'endExec' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3628:1: ( 'endExec' )
            {
             before(grammarAccess.getSelfMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3629:1: ( 'endExec' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3630:1: 'endExec'
            {
             before(grammarAccess.getSelfMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 
            match(input,43,FOLLOW_43_in_rule__SelfMessage__EndExecAssignment_4_2_07314); 
             after(grammarAccess.getSelfMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 

            }

             after(grammarAccess.getSelfMessageAccess().getEndExecEndExecKeyword_4_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__EndExecAssignment_4_2_0"


    // $ANTLR start "rule__SelfMessage__EndExecCountAssignment_4_2_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3645:1: rule__SelfMessage__EndExecCountAssignment_4_2_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__SelfMessage__EndExecCountAssignment_4_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3649:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3650:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3650:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3651:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getSelfMessageAccess().getEndExecCountINT_GREATER_ZEROTerminalRuleCall_4_2_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__SelfMessage__EndExecCountAssignment_4_2_17353); 
             after(grammarAccess.getSelfMessageAccess().getEndExecCountINT_GREATER_ZEROTerminalRuleCall_4_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__EndExecCountAssignment_4_2_1"


    // $ANTLR start "rule__SelfMessage__NoteAssignment_5_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3660:1: rule__SelfMessage__NoteAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__SelfMessage__NoteAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3664:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3665:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3665:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3666:1: RULE_STRING
            {
             before(grammarAccess.getSelfMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SelfMessage__NoteAssignment_5_17384); 
             after(grammarAccess.getSelfMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SelfMessage__NoteAssignment_5_1"


    // $ANTLR start "rule__OneLifelineNote__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3675:1: rule__OneLifelineNote__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineNote__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3679:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3680:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3680:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3681:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3682:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3683:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_07419); 
             after(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__LifelineAssignment_0"


    // $ANTLR start "rule__OneLifelineNote__NoteAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3694:1: rule__OneLifelineNote__NoteAssignment_2 : ( RULE_STRING ) ;
    public final void rule__OneLifelineNote__NoteAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3698:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3699:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3699:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3700:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_27454); 
             after(grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineNote__NoteAssignment_2"


    // $ANTLR start "rule__DestroyLifelineEvent__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3709:1: rule__DestroyLifelineEvent__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__DestroyLifelineEvent__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3713:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3714:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3714:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3715:1: ( RULE_ID )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3716:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3717:1: RULE_ID
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DestroyLifelineEvent__LifelineAssignment_07489); 
             after(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DestroyLifelineEvent__LifelineAssignment_0"


    // $ANTLR start "rule__Fragment__NameAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3728:1: rule__Fragment__NameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Fragment__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3732:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3733:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3733:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3734:1: RULE_STRING
            {
             before(grammarAccess.getFragmentAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_17524); 
             after(grammarAccess.getFragmentAccess().getNameSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__NameAssignment_1"


    // $ANTLR start "rule__Fragment__SectionsAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3743:1: rule__Fragment__SectionsAssignment_2 : ( ruleSection ) ;
    public final void rule__Fragment__SectionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3747:1: ( ( ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3748:1: ( ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3748:1: ( ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3749:1: ruleSection
            {
             before(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_27555);
            ruleSection();

            state._fsp--;

             after(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__SectionsAssignment_2"


    // $ANTLR start "rule__Fragment__SectionsAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3758:1: rule__Fragment__SectionsAssignment_3 : ( ruleSection ) ;
    public final void rule__Fragment__SectionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3762:1: ( ( ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3763:1: ( ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3763:1: ( ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3764:1: ruleSection
            {
             before(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_37586);
            ruleSection();

            state._fsp--;

             after(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__SectionsAssignment_3"


    // $ANTLR start "rule__Section__LabelAssignment_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3773:1: rule__Section__LabelAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__Section__LabelAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3777:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3778:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3778:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3779:1: RULE_STRING
            {
             before(grammarAccess.getSectionAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Section__LabelAssignment_1_17617); 
             after(grammarAccess.getSectionAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__LabelAssignment_1_1"


    // $ANTLR start "rule__Section__InteractionsAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3788:1: rule__Section__InteractionsAssignment_2 : ( ruleInteraction ) ;
    public final void rule__Section__InteractionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3792:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3793:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3793:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3794:1: ruleInteraction
            {
             before(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_27648);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__InteractionsAssignment_2"


    // $ANTLR start "rule__Section__InteractionsAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3803:1: rule__Section__InteractionsAssignment_3 : ( ruleInteraction ) ;
    public final void rule__Section__InteractionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3807:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3808:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3808:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3809:1: ruleInteraction
            {
             before(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_37679);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Section__InteractionsAssignment_3"


    // $ANTLR start "rule__Refinement__LabelAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3818:1: rule__Refinement__LabelAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Refinement__LabelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3822:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3823:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3823:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3824:1: RULE_STRING
            {
             before(grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_27710); 
             after(grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__LabelAssignment_2"


    // $ANTLR start "rule__Refinement__LifelinesAssignment_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3833:1: rule__Refinement__LifelinesAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3837:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3838:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3838:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3839:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3840:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3841:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_47745); 
             after(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_4_0_1()); 

            }

             after(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__LifelinesAssignment_4"


    // $ANTLR start "rule__Refinement__LifelinesAssignment_5_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3852:1: rule__Refinement__LifelinesAssignment_5_1 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3856:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3857:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3857:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3858:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_5_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3859:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3860:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_5_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_5_17784); 
             after(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_5_1_0_1()); 

            }

             after(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__LifelinesAssignment_5_1"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\15\uffff";
    static final String DFA2_eofS =
        "\15\uffff";
    static final String DFA2_minS =
        "\1\5\1\16\2\uffff\4\4\5\uffff";
    static final String DFA2_maxS =
        "\1\40\1\33\2\uffff\1\32\1\15\2\32\5\uffff";
    static final String DFA2_acceptS =
        "\2\uffff\1\3\1\7\4\uffff\1\4\1\6\1\1\1\5\1\2";
    static final String DFA2_specialS =
        "\15\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\26\uffff\1\2\3\uffff\1\3",
            "\1\7\1\4\1\6\1\5\7\uffff\1\10\1\uffff\1\11",
            "",
            "",
            "\1\12\7\uffff\2\14\14\uffff\1\13",
            "\1\12\7\uffff\2\14",
            "\1\12\7\uffff\2\14\14\uffff\1\13",
            "\1\12\7\uffff\2\14\14\uffff\1\13",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "409:1: rule__Interaction__Alternatives : ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineNote ) | ( ruleSelfMessage ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0_in_ruleSequenceDiagram94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_entryRuleLifeline121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLifeline128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Alternatives_in_ruleLifeline154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_entryRuleInteraction181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInteraction188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Interaction__Alternatives_in_ruleInteraction214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTwoLifelineMessage248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__0_in_ruleTwoLifelineMessage274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineMessage308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__0_in_ruleOneLifelineMessage334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelfMessage_in_entryRuleSelfMessage361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelfMessage368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__0_in_ruleSelfMessage394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineNote428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__0_in_ruleOneLifelineNote454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroyLifelineEvent488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__0_in_ruleDestroyLifelineEvent514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0_in_ruleFragment574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_entryRuleSection601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSection608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__0_in_ruleSection634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0_in_ruleRefinement694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MessageType__Alternatives_in_ruleMessageType731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__0_in_rule__Lifeline__Alternatives766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__0_in_rule__Lifeline__Alternatives784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_rule__Interaction__Alternatives851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelfMessage_in_rule__Interaction__Alternatives885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_rule__Interaction__Alternatives902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_5951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceStartExecAssignment_5_1_in_rule__TwoLifelineMessage__Alternatives_5969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_2__0_in_rule__TwoLifelineMessage__Alternatives_5987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_61020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetStartExecAssignment_6_1_in_rule__TwoLifelineMessage__Alternatives_61038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_2__0_in_rule__TwoLifelineMessage__Alternatives_61056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_01090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_01110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__StartEndExecAssignment_4_0_in_rule__OneLifelineMessage__Alternatives_41144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__StartExecAssignment_4_1_in_rule__OneLifelineMessage__Alternatives_41162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_2__0_in_rule__OneLifelineMessage__Alternatives_41180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__SelfMessage__MessageTypeAlternatives_1_01214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__SelfMessage__MessageTypeAlternatives_1_01234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__SelfMessage__MessageTypeAlternatives_1_01254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__StartEndExecAssignment_4_0_in_rule__SelfMessage__Alternatives_41288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__StartExecAssignment_4_1_in_rule__SelfMessage__Alternatives_41306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_4_2__0_in_rule__SelfMessage__Alternatives_41324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__MessageType__Alternatives1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__MessageType__Alternatives1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__MessageType__Alternatives1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__MessageType__Alternatives1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01454 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11515 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__SequenceDiagram__Group__1__Impl1546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21577 = new BitSet(new long[]{0x0000000110280020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31637 = new BitSet(new long[]{0x0000000110280020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__LifelinesAssignment_3_in_rule__SequenceDiagram__Group__3__Impl1667 = new BitSet(new long[]{0x0000000000280002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__InteractionsAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1725 = new BitSet(new long[]{0x0000000110000022L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__0__Impl_in_rule__Lifeline__Group_0__01766 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__1_in_rule__Lifeline__Group_0__01769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Lifeline__Group_0__0__Impl1797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__1__Impl_in_rule__Lifeline__Group_0__11828 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__2_in_rule__Lifeline__Group_0__11831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__CaptionAssignment_0_1_in_rule__Lifeline__Group_0__1__Impl1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__2__Impl_in_rule__Lifeline__Group_0__21888 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__3_in_rule__Lifeline__Group_0__21891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Lifeline__Group_0__2__Impl1919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_0__3__Impl_in_rule__Lifeline__Group_0__31950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__NameAssignment_0_3_in_rule__Lifeline__Group_0__3__Impl1977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__0__Impl_in_rule__Lifeline__Group_1__02015 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__1_in_rule__Lifeline__Group_1__02018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Lifeline__Group_1__0__Impl2046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__1__Impl_in_rule__Lifeline__Group_1__12077 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__2_in_rule__Lifeline__Group_1__12080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__UsecaseCaptionAssignment_1_1_in_rule__Lifeline__Group_1__1__Impl2107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__2__Impl_in_rule__Lifeline__Group_1__22137 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__3_in_rule__Lifeline__Group_1__22140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Lifeline__Group_1__2__Impl2168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group_1__3__Impl_in_rule__Lifeline__Group_1__32199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__NameAssignment_1_3_in_rule__Lifeline__Group_1__3__Impl2226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__02264 = new BitSet(new long[]{0x000000000003C000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__02267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl2294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__12324 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__12327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__MessageTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl2354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__22384 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__22387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__MessageAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl2414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__32444 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__32447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__TwoLifelineMessage__Group__3__Impl2475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__42506 = new BitSet(new long[]{0x000001F801800000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__42509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl2536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__52566 = new BitSet(new long[]{0x000001F801800000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__52569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__62627 = new BitSet(new long[]{0x000001F801800000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__62630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl2657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__72688 = new BitSet(new long[]{0x000001F801800000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__72691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl2718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__82749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl2776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_2__0__Impl_in_rule__TwoLifelineMessage__Group_5_2__02825 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_2__1_in_rule__TwoLifelineMessage__Group_5_2__02828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_0_in_rule__TwoLifelineMessage__Group_5_2__0__Impl2855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_2__1__Impl_in_rule__TwoLifelineMessage__Group_5_2__12885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_1_in_rule__TwoLifelineMessage__Group_5_2__1__Impl2912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_2__0__Impl_in_rule__TwoLifelineMessage__Group_6_2__02947 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_2__1_in_rule__TwoLifelineMessage__Group_6_2__02950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_0_in_rule__TwoLifelineMessage__Group_6_2__0__Impl2977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_2__1__Impl_in_rule__TwoLifelineMessage__Group_6_2__13007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_1_in_rule__TwoLifelineMessage__Group_6_2__1__Impl3034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__03069 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__03072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__TwoLifelineMessage__Group_7__0__Impl3100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__13131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl3158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__03192 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__03195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__TwoLifelineMessage__Group_8__0__Impl3223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__13254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl3281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__03315 = new BitSet(new long[]{0x000000000003C000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__03318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl3345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__13375 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__13378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl3405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__23435 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__23438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2_in_rule__OneLifelineMessage__Group__2__Impl3465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__33495 = new BitSet(new long[]{0x00000E0002000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__33498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageAssignment_3_in_rule__OneLifelineMessage__Group__3__Impl3525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__43555 = new BitSet(new long[]{0x00000E0002000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__5_in_rule__OneLifelineMessage__Group__43558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Alternatives_4_in_rule__OneLifelineMessage__Group__4__Impl3585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__5__Impl_in_rule__OneLifelineMessage__Group__53616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__0_in_rule__OneLifelineMessage__Group__5__Impl3643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_2__0__Impl_in_rule__OneLifelineMessage__Group_4_2__03686 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_2__1_in_rule__OneLifelineMessage__Group_4_2__03689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndExecAssignment_4_2_0_in_rule__OneLifelineMessage__Group_4_2__0__Impl3716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_2__1__Impl_in_rule__OneLifelineMessage__Group_4_2__13746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndExecCountAssignment_4_2_1_in_rule__OneLifelineMessage__Group_4_2__1__Impl3773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__0__Impl_in_rule__OneLifelineMessage__Group_5__03808 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__1_in_rule__OneLifelineMessage__Group_5__03811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__OneLifelineMessage__Group_5__0__Impl3839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__1__Impl_in_rule__OneLifelineMessage__Group_5__13870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__NoteAssignment_5_1_in_rule__OneLifelineMessage__Group_5__1__Impl3897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__0__Impl_in_rule__SelfMessage__Group__03931 = new BitSet(new long[]{0x000000000001C000L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__1_in_rule__SelfMessage__Group__03934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__LifelineAssignment_0_in_rule__SelfMessage__Group__0__Impl3961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__1__Impl_in_rule__SelfMessage__Group__13991 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__2_in_rule__SelfMessage__Group__13994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__MessageTypeAssignment_1_in_rule__SelfMessage__Group__1__Impl4021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__2__Impl_in_rule__SelfMessage__Group__24051 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__3_in_rule__SelfMessage__Group__24054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__SelfMessage__Group__2__Impl4082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__3__Impl_in_rule__SelfMessage__Group__34113 = new BitSet(new long[]{0x00000E0002000000L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__4_in_rule__SelfMessage__Group__34116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__MessageAssignment_3_in_rule__SelfMessage__Group__3__Impl4143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__4__Impl_in_rule__SelfMessage__Group__44173 = new BitSet(new long[]{0x00000E0002000000L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__5_in_rule__SelfMessage__Group__44176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Alternatives_4_in_rule__SelfMessage__Group__4__Impl4203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group__5__Impl_in_rule__SelfMessage__Group__54234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_5__0_in_rule__SelfMessage__Group__5__Impl4261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_4_2__0__Impl_in_rule__SelfMessage__Group_4_2__04304 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_4_2__1_in_rule__SelfMessage__Group_4_2__04307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__EndExecAssignment_4_2_0_in_rule__SelfMessage__Group_4_2__0__Impl4334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_4_2__1__Impl_in_rule__SelfMessage__Group_4_2__14364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__EndExecCountAssignment_4_2_1_in_rule__SelfMessage__Group_4_2__1__Impl4391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_5__0__Impl_in_rule__SelfMessage__Group_5__04426 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_5__1_in_rule__SelfMessage__Group_5__04429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__SelfMessage__Group_5__0__Impl4457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__Group_5__1__Impl_in_rule__SelfMessage__Group_5__14488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__NoteAssignment_5_1_in_rule__SelfMessage__Group_5__1__Impl4515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__04549 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__04552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl4579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__14609 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__14612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__OneLifelineNote__Group__1__Impl4640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__24671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl4698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__0__Impl_in_rule__DestroyLifelineEvent__Group__04734 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__1_in_rule__DestroyLifelineEvent__Group__04737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__LifelineAssignment_0_in_rule__DestroyLifelineEvent__Group__0__Impl4764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__1__Impl_in_rule__DestroyLifelineEvent__Group__14794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__DestroyLifelineEvent__Group__1__Impl4822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__04857 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__04860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Fragment__Group__0__Impl4888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__14919 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__14922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl4949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__24979 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__24982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__SectionsAssignment_2_in_rule__Fragment__Group__2__Impl5009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__35039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__SectionsAssignment_3_in_rule__Fragment__Group__3__Impl5066 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_rule__Section__Group__0__Impl_in_rule__Section__Group__05105 = new BitSet(new long[]{0x0000000190000020L});
    public static final BitSet FOLLOW_rule__Section__Group__1_in_rule__Section__Group__05108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Section__Group__0__Impl5136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__1__Impl_in_rule__Section__Group__15167 = new BitSet(new long[]{0x0000000190000020L});
    public static final BitSet FOLLOW_rule__Section__Group__2_in_rule__Section__Group__15170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__0_in_rule__Section__Group__1__Impl5197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__2__Impl_in_rule__Section__Group__25228 = new BitSet(new long[]{0x0000000150000020L});
    public static final BitSet FOLLOW_rule__Section__Group__3_in_rule__Section__Group__25231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__InteractionsAssignment_2_in_rule__Section__Group__2__Impl5258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__3__Impl_in_rule__Section__Group__35288 = new BitSet(new long[]{0x0000000150000020L});
    public static final BitSet FOLLOW_rule__Section__Group__4_in_rule__Section__Group__35291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__InteractionsAssignment_3_in_rule__Section__Group__3__Impl5318 = new BitSet(new long[]{0x0000000110000022L});
    public static final BitSet FOLLOW_rule__Section__Group__4__Impl_in_rule__Section__Group__45349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Section__Group__4__Impl5377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__0__Impl_in_rule__Section__Group_1__05418 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Section__Group_1__1_in_rule__Section__Group_1__05421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Section__Group_1__0__Impl5449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__1__Impl_in_rule__Section__Group_1__15480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__LabelAssignment_1_1_in_rule__Section__Group_1__1__Impl5507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__05541 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__05544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__Refinement__Group__0__Impl5572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__15603 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__15606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Refinement__Group__1__Impl5634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__25665 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__25668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LabelAssignment_2_in_rule__Refinement__Group__2__Impl5695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__35725 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__35728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__Refinement__Group__3__Impl5756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__45787 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__45790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_4_in_rule__Refinement__Group__4__Impl5817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__55847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__0_in_rule__Refinement__Group__5__Impl5874 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__0__Impl_in_rule__Refinement__Group_5__05917 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__1_in_rule__Refinement__Group_5__05920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__Refinement__Group_5__0__Impl5948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__1__Impl_in_rule__Refinement__Group_5__15979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_5_1_in_rule__Refinement__Group_5__1__Impl6006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_26045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_36076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_46107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_0_16138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_0_36169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Lifeline__UsecaseCaptionAssignment_1_16200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_1_36231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_06266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMessageType_in_rule__TwoLifelineMessage__MessageTypeAssignment_16301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__MessageAssignment_26332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_46367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__TwoLifelineMessage__SourceStartEndExecAssignment_5_06407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__TwoLifelineMessage__SourceStartExecAssignment_5_16451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__TwoLifelineMessage__SourceEndExecAssignment_5_2_06495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__SourceEndExecCountAssignment_5_2_16534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__TwoLifelineMessage__TargetStartEndExecAssignment_6_06570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__TwoLifelineMessage__TargetStartExecAssignment_6_16614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rule__TwoLifelineMessage__TargetEndExecAssignment_6_2_06658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__TargetEndExecCountAssignment_6_2_16697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_16728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_16759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_06794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMessageType_in_rule__OneLifelineMessage__MessageTypeAssignment_16829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_26860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__MessageAssignment_36893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__OneLifelineMessage__StartEndExecAssignment_4_06929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rule__OneLifelineMessage__StartExecAssignment_4_16973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rule__OneLifelineMessage__EndExecAssignment_4_2_07017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndExecCountAssignment_4_2_17056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_5_17087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__SelfMessage__LifelineAssignment_07122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SelfMessage__MessageTypeAlternatives_1_0_in_rule__SelfMessage__MessageTypeAssignment_17157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SelfMessage__MessageAssignment_37190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__SelfMessage__StartEndExecAssignment_4_07226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rule__SelfMessage__StartExecAssignment_4_17270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rule__SelfMessage__EndExecAssignment_4_2_07314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__SelfMessage__EndExecCountAssignment_4_2_17353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SelfMessage__NoteAssignment_5_17384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_07419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_27454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DestroyLifelineEvent__LifelineAssignment_07489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_17524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_27555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_37586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Section__LabelAssignment_1_17617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_27648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_37679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_27710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_47745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_5_17784 = new BitSet(new long[]{0x0000000000000002L});

}