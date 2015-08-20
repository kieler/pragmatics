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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'lost'", "'found'", "'async'", "'create'", "'response'", "'sync'", "'sequenceDiagram'", "'lifeline'", "'as'", "'to'", "'sourceNote'", "'targetNote'", "'note'", "'destroy'", "'fragment'", "'{'", "'}'", "'label'", "'refinement'", "'lifelines'", "','", "'sourceStartBlock'", "'sourceEndBlock'", "'targetStartBlock'", "'targetEndBlock'", "'startBlock'", "'endBlock'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
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
    public static final int RULE_ID=5;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:97:1: ruleLifeline : ( ( rule__Lifeline__Group__0 ) ) ;
    public final void ruleLifeline() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:101:2: ( ( ( rule__Lifeline__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__Lifeline__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__Lifeline__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:103:1: ( rule__Lifeline__Group__0 )
            {
             before(grammarAccess.getLifelineAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:1: ( rule__Lifeline__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:2: rule__Lifeline__Group__0
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__0_in_ruleLifeline154);
            rule__Lifeline__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getGroup()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleOneLifelineNote"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:200:1: entryRuleOneLifelineNote : ruleOneLifelineNote EOF ;
    public final void entryRuleOneLifelineNote() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:201:1: ( ruleOneLifelineNote EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:202:1: ruleOneLifelineNote EOF
            {
             before(grammarAccess.getOneLifelineNoteRule()); 
            pushFollow(FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote361);
            ruleOneLifelineNote();

            state._fsp--;

             after(grammarAccess.getOneLifelineNoteRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineNote368); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:209:1: ruleOneLifelineNote : ( ( rule__OneLifelineNote__Group__0 ) ) ;
    public final void ruleOneLifelineNote() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:213:2: ( ( ( rule__OneLifelineNote__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:214:1: ( ( rule__OneLifelineNote__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:214:1: ( ( rule__OneLifelineNote__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:215:1: ( rule__OneLifelineNote__Group__0 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:216:1: ( rule__OneLifelineNote__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:216:2: rule__OneLifelineNote__Group__0
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__0_in_ruleOneLifelineNote394);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:228:1: entryRuleDestroyLifelineEvent : ruleDestroyLifelineEvent EOF ;
    public final void entryRuleDestroyLifelineEvent() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:229:1: ( ruleDestroyLifelineEvent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:230:1: ruleDestroyLifelineEvent EOF
            {
             before(grammarAccess.getDestroyLifelineEventRule()); 
            pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent421);
            ruleDestroyLifelineEvent();

            state._fsp--;

             after(grammarAccess.getDestroyLifelineEventRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroyLifelineEvent428); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:237:1: ruleDestroyLifelineEvent : ( ( rule__DestroyLifelineEvent__Group__0 ) ) ;
    public final void ruleDestroyLifelineEvent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:241:2: ( ( ( rule__DestroyLifelineEvent__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:242:1: ( ( rule__DestroyLifelineEvent__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:242:1: ( ( rule__DestroyLifelineEvent__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:243:1: ( rule__DestroyLifelineEvent__Group__0 )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:244:1: ( rule__DestroyLifelineEvent__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:244:2: rule__DestroyLifelineEvent__Group__0
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__0_in_ruleDestroyLifelineEvent454);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:256:1: entryRuleFragment : ruleFragment EOF ;
    public final void entryRuleFragment() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:257:1: ( ruleFragment EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:258:1: ruleFragment EOF
            {
             before(grammarAccess.getFragmentRule()); 
            pushFollow(FOLLOW_ruleFragment_in_entryRuleFragment481);
            ruleFragment();

            state._fsp--;

             after(grammarAccess.getFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragment488); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:265:1: ruleFragment : ( ( rule__Fragment__Group__0 ) ) ;
    public final void ruleFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:269:2: ( ( ( rule__Fragment__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:270:1: ( ( rule__Fragment__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:270:1: ( ( rule__Fragment__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:271:1: ( rule__Fragment__Group__0 )
            {
             before(grammarAccess.getFragmentAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:272:1: ( rule__Fragment__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:272:2: rule__Fragment__Group__0
            {
            pushFollow(FOLLOW_rule__Fragment__Group__0_in_ruleFragment514);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:284:1: entryRuleSection : ruleSection EOF ;
    public final void entryRuleSection() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:285:1: ( ruleSection EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:286:1: ruleSection EOF
            {
             before(grammarAccess.getSectionRule()); 
            pushFollow(FOLLOW_ruleSection_in_entryRuleSection541);
            ruleSection();

            state._fsp--;

             after(grammarAccess.getSectionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSection548); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:293:1: ruleSection : ( ( rule__Section__Group__0 ) ) ;
    public final void ruleSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:297:2: ( ( ( rule__Section__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__Section__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__Section__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:299:1: ( rule__Section__Group__0 )
            {
             before(grammarAccess.getSectionAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:1: ( rule__Section__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:2: rule__Section__Group__0
            {
            pushFollow(FOLLOW_rule__Section__Group__0_in_ruleSection574);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:312:1: entryRuleRefinement : ruleRefinement EOF ;
    public final void entryRuleRefinement() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:313:1: ( ruleRefinement EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:314:1: ruleRefinement EOF
            {
             before(grammarAccess.getRefinementRule()); 
            pushFollow(FOLLOW_ruleRefinement_in_entryRuleRefinement601);
            ruleRefinement();

            state._fsp--;

             after(grammarAccess.getRefinementRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRefinement608); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:321:1: ruleRefinement : ( ( rule__Refinement__Group__0 ) ) ;
    public final void ruleRefinement() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:325:2: ( ( ( rule__Refinement__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:326:1: ( ( rule__Refinement__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:326:1: ( ( rule__Refinement__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:327:1: ( rule__Refinement__Group__0 )
            {
             before(grammarAccess.getRefinementAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:328:1: ( rule__Refinement__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:328:2: rule__Refinement__Group__0
            {
            pushFollow(FOLLOW_rule__Refinement__Group__0_in_ruleRefinement634);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:341:1: ruleMessageType : ( ( rule__MessageType__Alternatives ) ) ;
    public final void ruleMessageType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:345:1: ( ( ( rule__MessageType__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:346:1: ( ( rule__MessageType__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:346:1: ( ( rule__MessageType__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:347:1: ( rule__MessageType__Alternatives )
            {
             before(grammarAccess.getMessageTypeAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:348:1: ( rule__MessageType__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:348:2: rule__MessageType__Alternatives
            {
            pushFollow(FOLLOW_rule__MessageType__Alternatives_in_ruleMessageType671);
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


    // $ANTLR start "rule__Interaction__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:359:1: rule__Interaction__Alternatives : ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineNote ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) );
    public final void rule__Interaction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:363:1: ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineNote ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) )
            int alt1=6;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:364:1: ( ruleTwoLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:364:1: ( ruleTwoLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:365:1: ruleTwoLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives706);
                    ruleTwoLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:370:6: ( ruleOneLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:370:6: ( ruleOneLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:371:1: ruleOneLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives723);
                    ruleOneLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:376:6: ( ruleFragment )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:376:6: ( ruleFragment )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:377:1: ruleFragment
                    {
                     before(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFragment_in_rule__Interaction__Alternatives740);
                    ruleFragment();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:382:6: ( ruleOneLifelineNote )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:382:6: ( ruleOneLifelineNote )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:383:1: ruleOneLifelineNote
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives757);
                    ruleOneLifelineNote();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:388:6: ( ruleDestroyLifelineEvent )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:388:6: ( ruleDestroyLifelineEvent )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:389:1: ruleDestroyLifelineEvent
                    {
                     before(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_rule__Interaction__Alternatives774);
                    ruleDestroyLifelineEvent();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:394:6: ( ruleRefinement )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:394:6: ( ruleRefinement )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:395:1: ruleRefinement
                    {
                     before(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives791);
                    ruleRefinement();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_5()); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:405:1: rule__TwoLifelineMessage__Alternatives_5 : ( ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__Group_5_1__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:409:1: ( ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__Group_5_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==33) ) {
                alt2=1;
            }
            else if ( (LA2_0==34) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:410:1: ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:410:1: ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:411:1: ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockAssignment_5_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:412:1: ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:412:2: rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_5823);
                    rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockAssignment_5_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:416:6: ( ( rule__TwoLifelineMessage__Group_5_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:416:6: ( ( rule__TwoLifelineMessage__Group_5_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:417:1: ( rule__TwoLifelineMessage__Group_5_1__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_5_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:418:1: ( rule__TwoLifelineMessage__Group_5_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:418:2: rule__TwoLifelineMessage__Group_5_1__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__0_in_rule__TwoLifelineMessage__Alternatives_5841);
                    rule__TwoLifelineMessage__Group_5_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getGroup_5_1()); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:427:1: rule__TwoLifelineMessage__Alternatives_6 : ( ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__Group_6_1__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:431:1: ( ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__Group_6_1__0 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==35) ) {
                alt3=1;
            }
            else if ( (LA3_0==36) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:432:1: ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:432:1: ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:433:1: ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockAssignment_6_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:434:1: ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:434:2: rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_6874);
                    rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockAssignment_6_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:438:6: ( ( rule__TwoLifelineMessage__Group_6_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:438:6: ( ( rule__TwoLifelineMessage__Group_6_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:439:1: ( rule__TwoLifelineMessage__Group_6_1__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_6_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:440:1: ( rule__TwoLifelineMessage__Group_6_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:440:2: rule__TwoLifelineMessage__Group_6_1__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__0_in_rule__TwoLifelineMessage__Alternatives_6892);
                    rule__TwoLifelineMessage__Group_6_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getGroup_6_1()); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:449:1: rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 : ( ( 'lost' ) | ( 'found' ) );
    public final void rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:453:1: ( ( 'lost' ) | ( 'found' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            else if ( (LA4_0==13) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:454:1: ( 'lost' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:454:1: ( 'lost' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:455:1: 'lost'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundLostKeyword_2_0_0()); 
                    match(input,12,FOLLOW_12_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0926); 
                     after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundLostKeyword_2_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:462:6: ( 'found' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:462:6: ( 'found' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:463:1: 'found'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundFoundKeyword_2_0_1()); 
                    match(input,13,FOLLOW_13_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0946); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:475:1: rule__OneLifelineMessage__Alternatives_4 : ( ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) ) | ( ( rule__OneLifelineMessage__Group_4_1__0 ) ) );
    public final void rule__OneLifelineMessage__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:479:1: ( ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) ) | ( ( rule__OneLifelineMessage__Group_4_1__0 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==37) ) {
                alt5=1;
            }
            else if ( (LA5_0==38) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:480:1: ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:480:1: ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:481:1: ( rule__OneLifelineMessage__StartBlockAssignment_4_0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getStartBlockAssignment_4_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:482:1: ( rule__OneLifelineMessage__StartBlockAssignment_4_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:482:2: rule__OneLifelineMessage__StartBlockAssignment_4_0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__StartBlockAssignment_4_0_in_rule__OneLifelineMessage__Alternatives_4980);
                    rule__OneLifelineMessage__StartBlockAssignment_4_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getStartBlockAssignment_4_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:486:6: ( ( rule__OneLifelineMessage__Group_4_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:486:6: ( ( rule__OneLifelineMessage__Group_4_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:487:1: ( rule__OneLifelineMessage__Group_4_1__0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getGroup_4_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:488:1: ( rule__OneLifelineMessage__Group_4_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:488:2: rule__OneLifelineMessage__Group_4_1__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__0_in_rule__OneLifelineMessage__Alternatives_4998);
                    rule__OneLifelineMessage__Group_4_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getGroup_4_1()); 

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


    // $ANTLR start "rule__MessageType__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:497:1: rule__MessageType__Alternatives : ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) );
    public final void rule__MessageType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:501:1: ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt6=1;
                }
                break;
            case 15:
                {
                alt6=2;
                }
                break;
            case 16:
                {
                alt6=3;
                }
                break;
            case 17:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:502:1: ( ( 'async' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:502:1: ( ( 'async' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:503:1: ( 'async' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:504:1: ( 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:504:3: 'async'
                    {
                    match(input,14,FOLLOW_14_in_rule__MessageType__Alternatives1032); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:509:6: ( ( 'create' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:509:6: ( ( 'create' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:510:1: ( 'create' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:511:1: ( 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:511:3: 'create'
                    {
                    match(input,15,FOLLOW_15_in_rule__MessageType__Alternatives1053); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:516:6: ( ( 'response' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:516:6: ( ( 'response' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:517:1: ( 'response' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:518:1: ( 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:518:3: 'response'
                    {
                    match(input,16,FOLLOW_16_in_rule__MessageType__Alternatives1074); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:523:6: ( ( 'sync' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:523:6: ( ( 'sync' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:524:1: ( 'sync' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getSyncEnumLiteralDeclaration_3()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:525:1: ( 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:525:3: 'sync'
                    {
                    match(input,17,FOLLOW_17_in_rule__MessageType__Alternatives1095); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:537:1: rule__SequenceDiagram__Group__0 : rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 ;
    public final void rule__SequenceDiagram__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:541:1: ( rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:542:2: rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01128);
            rule__SequenceDiagram__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01131);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:549:1: rule__SequenceDiagram__Group__0__Impl : ( () ) ;
    public final void rule__SequenceDiagram__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:553:1: ( ( () ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:554:1: ( () )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:554:1: ( () )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:555:1: ()
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramAction_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:556:1: ()
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:558:1: 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:568:1: rule__SequenceDiagram__Group__1 : rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 ;
    public final void rule__SequenceDiagram__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:572:1: ( rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:573:2: rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11189);
            rule__SequenceDiagram__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11192);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:580:1: rule__SequenceDiagram__Group__1__Impl : ( 'sequenceDiagram' ) ;
    public final void rule__SequenceDiagram__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:584:1: ( ( 'sequenceDiagram' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:585:1: ( 'sequenceDiagram' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:585:1: ( 'sequenceDiagram' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:586:1: 'sequenceDiagram'
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramKeyword_1()); 
            match(input,18,FOLLOW_18_in_rule__SequenceDiagram__Group__1__Impl1220); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:599:1: rule__SequenceDiagram__Group__2 : rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 ;
    public final void rule__SequenceDiagram__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:603:1: ( rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:604:2: rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21251);
            rule__SequenceDiagram__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21254);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:611:1: rule__SequenceDiagram__Group__2__Impl : ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) ;
    public final void rule__SequenceDiagram__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:615:1: ( ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:616:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:616:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:617:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:618:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:618:2: rule__SequenceDiagram__DiagramNameAssignment_2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1281);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:628:1: rule__SequenceDiagram__Group__3 : rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 ;
    public final void rule__SequenceDiagram__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:632:1: ( rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:633:2: rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31311);
            rule__SequenceDiagram__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31314);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:640:1: rule__SequenceDiagram__Group__3__Impl : ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* ) ;
    public final void rule__SequenceDiagram__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:644:1: ( ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:645:1: ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:645:1: ( ( rule__SequenceDiagram__LifelinesAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:646:1: ( rule__SequenceDiagram__LifelinesAssignment_3 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:647:1: ( rule__SequenceDiagram__LifelinesAssignment_3 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:647:2: rule__SequenceDiagram__LifelinesAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__LifelinesAssignment_3_in_rule__SequenceDiagram__Group__3__Impl1341);
            	    rule__SequenceDiagram__LifelinesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:657:1: rule__SequenceDiagram__Group__4 : rule__SequenceDiagram__Group__4__Impl ;
    public final void rule__SequenceDiagram__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:661:1: ( rule__SequenceDiagram__Group__4__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:662:2: rule__SequenceDiagram__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41372);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:668:1: rule__SequenceDiagram__Group__4__Impl : ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* ) ;
    public final void rule__SequenceDiagram__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:672:1: ( ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:673:1: ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:673:1: ( ( rule__SequenceDiagram__InteractionsAssignment_4 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:674:1: ( rule__SequenceDiagram__InteractionsAssignment_4 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:675:1: ( rule__SequenceDiagram__InteractionsAssignment_4 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID||LA8_0==26||LA8_0==30) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:675:2: rule__SequenceDiagram__InteractionsAssignment_4
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__InteractionsAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1399);
            	    rule__SequenceDiagram__InteractionsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
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


    // $ANTLR start "rule__Lifeline__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:695:1: rule__Lifeline__Group__0 : rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1 ;
    public final void rule__Lifeline__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:699:1: ( rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:700:2: rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__0__Impl_in_rule__Lifeline__Group__01440);
            rule__Lifeline__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__1_in_rule__Lifeline__Group__01443);
            rule__Lifeline__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__0"


    // $ANTLR start "rule__Lifeline__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:707:1: rule__Lifeline__Group__0__Impl : ( 'lifeline' ) ;
    public final void rule__Lifeline__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:711:1: ( ( 'lifeline' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:712:1: ( 'lifeline' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:712:1: ( 'lifeline' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:713:1: 'lifeline'
            {
             before(grammarAccess.getLifelineAccess().getLifelineKeyword_0()); 
            match(input,19,FOLLOW_19_in_rule__Lifeline__Group__0__Impl1471); 
             after(grammarAccess.getLifelineAccess().getLifelineKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__0__Impl"


    // $ANTLR start "rule__Lifeline__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:726:1: rule__Lifeline__Group__1 : rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2 ;
    public final void rule__Lifeline__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:730:1: ( rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:731:2: rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__1__Impl_in_rule__Lifeline__Group__11502);
            rule__Lifeline__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__2_in_rule__Lifeline__Group__11505);
            rule__Lifeline__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__1"


    // $ANTLR start "rule__Lifeline__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:738:1: rule__Lifeline__Group__1__Impl : ( ( rule__Lifeline__CaptionAssignment_1 ) ) ;
    public final void rule__Lifeline__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:742:1: ( ( ( rule__Lifeline__CaptionAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:743:1: ( ( rule__Lifeline__CaptionAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:743:1: ( ( rule__Lifeline__CaptionAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:744:1: ( rule__Lifeline__CaptionAssignment_1 )
            {
             before(grammarAccess.getLifelineAccess().getCaptionAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:745:1: ( rule__Lifeline__CaptionAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:745:2: rule__Lifeline__CaptionAssignment_1
            {
            pushFollow(FOLLOW_rule__Lifeline__CaptionAssignment_1_in_rule__Lifeline__Group__1__Impl1532);
            rule__Lifeline__CaptionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getCaptionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__1__Impl"


    // $ANTLR start "rule__Lifeline__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:755:1: rule__Lifeline__Group__2 : rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3 ;
    public final void rule__Lifeline__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:759:1: ( rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:760:2: rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__2__Impl_in_rule__Lifeline__Group__21562);
            rule__Lifeline__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__3_in_rule__Lifeline__Group__21565);
            rule__Lifeline__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__2"


    // $ANTLR start "rule__Lifeline__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:767:1: rule__Lifeline__Group__2__Impl : ( 'as' ) ;
    public final void rule__Lifeline__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:771:1: ( ( 'as' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:772:1: ( 'as' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:772:1: ( 'as' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:773:1: 'as'
            {
             before(grammarAccess.getLifelineAccess().getAsKeyword_2()); 
            match(input,20,FOLLOW_20_in_rule__Lifeline__Group__2__Impl1593); 
             after(grammarAccess.getLifelineAccess().getAsKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__2__Impl"


    // $ANTLR start "rule__Lifeline__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:786:1: rule__Lifeline__Group__3 : rule__Lifeline__Group__3__Impl ;
    public final void rule__Lifeline__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:790:1: ( rule__Lifeline__Group__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:791:2: rule__Lifeline__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__3__Impl_in_rule__Lifeline__Group__31624);
            rule__Lifeline__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__3"


    // $ANTLR start "rule__Lifeline__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:797:1: rule__Lifeline__Group__3__Impl : ( ( rule__Lifeline__NameAssignment_3 ) ) ;
    public final void rule__Lifeline__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:801:1: ( ( ( rule__Lifeline__NameAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:802:1: ( ( rule__Lifeline__NameAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:802:1: ( ( rule__Lifeline__NameAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:803:1: ( rule__Lifeline__NameAssignment_3 )
            {
             before(grammarAccess.getLifelineAccess().getNameAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:804:1: ( rule__Lifeline__NameAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:804:2: rule__Lifeline__NameAssignment_3
            {
            pushFollow(FOLLOW_rule__Lifeline__NameAssignment_3_in_rule__Lifeline__Group__3__Impl1651);
            rule__Lifeline__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getLifelineAccess().getNameAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__Group__3__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:822:1: rule__TwoLifelineMessage__Group__0 : rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 ;
    public final void rule__TwoLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:826:1: ( rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:827:2: rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__01689);
            rule__TwoLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__01692);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:834:1: rule__TwoLifelineMessage__Group__0__Impl : ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:838:1: ( ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:839:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:839:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:840:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:841:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:841:2: rule__TwoLifelineMessage__SourceLifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl1719);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:851:1: rule__TwoLifelineMessage__Group__1 : rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 ;
    public final void rule__TwoLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:855:1: ( rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:856:2: rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__11749);
            rule__TwoLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__11752);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:863:1: rule__TwoLifelineMessage__Group__1__Impl : ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:867:1: ( ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:868:1: ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:868:1: ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:869:1: ( rule__TwoLifelineMessage__MessageTypeAssignment_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:870:1: ( rule__TwoLifelineMessage__MessageTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:870:2: rule__TwoLifelineMessage__MessageTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__MessageTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl1779);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:880:1: rule__TwoLifelineMessage__Group__2 : rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 ;
    public final void rule__TwoLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:884:1: ( rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:885:2: rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__21809);
            rule__TwoLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__21812);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:892:1: rule__TwoLifelineMessage__Group__2__Impl : ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) ) ;
    public final void rule__TwoLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:896:1: ( ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:897:1: ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:897:1: ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:898:1: ( rule__TwoLifelineMessage__MessageAssignment_2 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:899:1: ( rule__TwoLifelineMessage__MessageAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:899:2: rule__TwoLifelineMessage__MessageAssignment_2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__MessageAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl1839);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:909:1: rule__TwoLifelineMessage__Group__3 : rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 ;
    public final void rule__TwoLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:913:1: ( rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:914:2: rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__31869);
            rule__TwoLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__31872);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:921:1: rule__TwoLifelineMessage__Group__3__Impl : ( 'to' ) ;
    public final void rule__TwoLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:925:1: ( ( 'to' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:926:1: ( 'to' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:926:1: ( 'to' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:927:1: 'to'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3()); 
            match(input,21,FOLLOW_21_in_rule__TwoLifelineMessage__Group__3__Impl1900); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:940:1: rule__TwoLifelineMessage__Group__4 : rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 ;
    public final void rule__TwoLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:944:1: ( rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:945:2: rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__41931);
            rule__TwoLifelineMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__41934);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:952:1: rule__TwoLifelineMessage__Group__4__Impl : ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) ;
    public final void rule__TwoLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:956:1: ( ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:957:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:957:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:958:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:959:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:959:2: rule__TwoLifelineMessage__TargetLifelineAssignment_4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl1961);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:969:1: rule__TwoLifelineMessage__Group__5 : rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 ;
    public final void rule__TwoLifelineMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:973:1: ( rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:974:2: rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__51991);
            rule__TwoLifelineMessage__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__51994);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:981:1: rule__TwoLifelineMessage__Group__5__Impl : ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) ;
    public final void rule__TwoLifelineMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:985:1: ( ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:986:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:986:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:987:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:988:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=33 && LA9_0<=34)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:988:2: rule__TwoLifelineMessage__Alternatives_5
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2021);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:998:1: rule__TwoLifelineMessage__Group__6 : rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 ;
    public final void rule__TwoLifelineMessage__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1002:1: ( rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1003:2: rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__62052);
            rule__TwoLifelineMessage__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__62055);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1010:1: rule__TwoLifelineMessage__Group__6__Impl : ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) ;
    public final void rule__TwoLifelineMessage__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1014:1: ( ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1015:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1015:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1016:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_6()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1017:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=35 && LA10_0<=36)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1017:2: rule__TwoLifelineMessage__Alternatives_6
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl2082);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1027:1: rule__TwoLifelineMessage__Group__7 : rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 ;
    public final void rule__TwoLifelineMessage__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1031:1: ( rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1032:2: rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__72113);
            rule__TwoLifelineMessage__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__72116);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1039:1: rule__TwoLifelineMessage__Group__7__Impl : ( ( rule__TwoLifelineMessage__Group_7__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1043:1: ( ( ( rule__TwoLifelineMessage__Group_7__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1044:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1044:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1045:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1046:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==22) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1046:2: rule__TwoLifelineMessage__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl2143);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1056:1: rule__TwoLifelineMessage__Group__8 : rule__TwoLifelineMessage__Group__8__Impl ;
    public final void rule__TwoLifelineMessage__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1060:1: ( rule__TwoLifelineMessage__Group__8__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1061:2: rule__TwoLifelineMessage__Group__8__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__82174);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1067:1: rule__TwoLifelineMessage__Group__8__Impl : ( ( rule__TwoLifelineMessage__Group_8__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1071:1: ( ( ( rule__TwoLifelineMessage__Group_8__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1072:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1072:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1073:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_8()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1074:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==23) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1074:2: rule__TwoLifelineMessage__Group_8__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl2201);
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


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1102:1: rule__TwoLifelineMessage__Group_5_1__0 : rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1 ;
    public final void rule__TwoLifelineMessage__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1106:1: ( rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1107:2: rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__0__Impl_in_rule__TwoLifelineMessage__Group_5_1__02250);
            rule__TwoLifelineMessage__Group_5_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__1_in_rule__TwoLifelineMessage__Group_5_1__02253);
            rule__TwoLifelineMessage__Group_5_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_1__0"


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1114:1: rule__TwoLifelineMessage__Group_5_1__0__Impl : ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1118:1: ( ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1119:1: ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1119:1: ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1120:1: ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockAssignment_5_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1121:1: ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1121:2: rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0_in_rule__TwoLifelineMessage__Group_5_1__0__Impl2280);
            rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockAssignment_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_1__0__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1131:1: rule__TwoLifelineMessage__Group_5_1__1 : rule__TwoLifelineMessage__Group_5_1__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1135:1: ( rule__TwoLifelineMessage__Group_5_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1136:2: rule__TwoLifelineMessage__Group_5_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__1__Impl_in_rule__TwoLifelineMessage__Group_5_1__12310);
            rule__TwoLifelineMessage__Group_5_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_1__1"


    // $ANTLR start "rule__TwoLifelineMessage__Group_5_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1142:1: rule__TwoLifelineMessage__Group_5_1__1__Impl : ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1146:1: ( ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1147:1: ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1147:1: ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1148:1: ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockCountAssignment_5_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1149:1: ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_INT_GREATER_ZERO) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1149:2: rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1_in_rule__TwoLifelineMessage__Group_5_1__1__Impl2337);
                    rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockCountAssignment_5_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_5_1__1__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1163:1: rule__TwoLifelineMessage__Group_6_1__0 : rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1 ;
    public final void rule__TwoLifelineMessage__Group_6_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1167:1: ( rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1168:2: rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__0__Impl_in_rule__TwoLifelineMessage__Group_6_1__02372);
            rule__TwoLifelineMessage__Group_6_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__1_in_rule__TwoLifelineMessage__Group_6_1__02375);
            rule__TwoLifelineMessage__Group_6_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_1__0"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1175:1: rule__TwoLifelineMessage__Group_6_1__0__Impl : ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_6_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1179:1: ( ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1180:1: ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1180:1: ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1181:1: ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockAssignment_6_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1182:1: ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1182:2: rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0_in_rule__TwoLifelineMessage__Group_6_1__0__Impl2402);
            rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockAssignment_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_1__0__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1192:1: rule__TwoLifelineMessage__Group_6_1__1 : rule__TwoLifelineMessage__Group_6_1__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_6_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1196:1: ( rule__TwoLifelineMessage__Group_6_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1197:2: rule__TwoLifelineMessage__Group_6_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__1__Impl_in_rule__TwoLifelineMessage__Group_6_1__12432);
            rule__TwoLifelineMessage__Group_6_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_1__1"


    // $ANTLR start "rule__TwoLifelineMessage__Group_6_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1203:1: rule__TwoLifelineMessage__Group_6_1__1__Impl : ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_6_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1207:1: ( ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1208:1: ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1208:1: ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1209:1: ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockCountAssignment_6_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1210:1: ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_INT_GREATER_ZERO) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1210:2: rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1_in_rule__TwoLifelineMessage__Group_6_1__1__Impl2459);
                    rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockCountAssignment_6_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__Group_6_1__1__Impl"


    // $ANTLR start "rule__TwoLifelineMessage__Group_7__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1224:1: rule__TwoLifelineMessage__Group_7__0 : rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 ;
    public final void rule__TwoLifelineMessage__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1228:1: ( rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1229:2: rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__02494);
            rule__TwoLifelineMessage__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__02497);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1236:1: rule__TwoLifelineMessage__Group_7__0__Impl : ( 'sourceNote' ) ;
    public final void rule__TwoLifelineMessage__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1240:1: ( ( 'sourceNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1241:1: ( 'sourceNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1241:1: ( 'sourceNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1242:1: 'sourceNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0()); 
            match(input,22,FOLLOW_22_in_rule__TwoLifelineMessage__Group_7__0__Impl2525); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1255:1: rule__TwoLifelineMessage__Group_7__1 : rule__TwoLifelineMessage__Group_7__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1259:1: ( rule__TwoLifelineMessage__Group_7__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1260:2: rule__TwoLifelineMessage__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__12556);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1266:1: rule__TwoLifelineMessage__Group_7__1__Impl : ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1270:1: ( ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1271:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1271:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1272:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteAssignment_7_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1273:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1273:2: rule__TwoLifelineMessage__SourceNoteAssignment_7_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl2583);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1287:1: rule__TwoLifelineMessage__Group_8__0 : rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 ;
    public final void rule__TwoLifelineMessage__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1291:1: ( rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1292:2: rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__02617);
            rule__TwoLifelineMessage__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__02620);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1299:1: rule__TwoLifelineMessage__Group_8__0__Impl : ( 'targetNote' ) ;
    public final void rule__TwoLifelineMessage__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1303:1: ( ( 'targetNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1304:1: ( 'targetNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1304:1: ( 'targetNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1305:1: 'targetNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0()); 
            match(input,23,FOLLOW_23_in_rule__TwoLifelineMessage__Group_8__0__Impl2648); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1318:1: rule__TwoLifelineMessage__Group_8__1 : rule__TwoLifelineMessage__Group_8__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1322:1: ( rule__TwoLifelineMessage__Group_8__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1323:2: rule__TwoLifelineMessage__Group_8__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__12679);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1329:1: rule__TwoLifelineMessage__Group_8__1__Impl : ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1333:1: ( ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1334:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1334:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1335:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteAssignment_8_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1336:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1336:2: rule__TwoLifelineMessage__TargetNoteAssignment_8_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl2706);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1350:1: rule__OneLifelineMessage__Group__0 : rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 ;
    public final void rule__OneLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1354:1: ( rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1355:2: rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__02740);
            rule__OneLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__02743);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1362:1: rule__OneLifelineMessage__Group__0__Impl : ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1366:1: ( ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1367:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1367:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1368:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1369:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1369:2: rule__OneLifelineMessage__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl2770);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1379:1: rule__OneLifelineMessage__Group__1 : rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 ;
    public final void rule__OneLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1383:1: ( rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1384:2: rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__12800);
            rule__OneLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__12803);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1391:1: rule__OneLifelineMessage__Group__1__Impl : ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) ) ;
    public final void rule__OneLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1395:1: ( ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1396:1: ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1396:1: ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1397:1: ( rule__OneLifelineMessage__MessageTypeAssignment_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1398:1: ( rule__OneLifelineMessage__MessageTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1398:2: rule__OneLifelineMessage__MessageTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl2830);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1408:1: rule__OneLifelineMessage__Group__2 : rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 ;
    public final void rule__OneLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1412:1: ( rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1413:2: rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__22860);
            rule__OneLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__22863);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1420:1: rule__OneLifelineMessage__Group__2__Impl : ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) ) ;
    public final void rule__OneLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1424:1: ( ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1425:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1425:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1426:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1427:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1427:2: rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2_in_rule__OneLifelineMessage__Group__2__Impl2890);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1437:1: rule__OneLifelineMessage__Group__3 : rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 ;
    public final void rule__OneLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1441:1: ( rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1442:2: rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__32920);
            rule__OneLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__32923);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1449:1: rule__OneLifelineMessage__Group__3__Impl : ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) ) ;
    public final void rule__OneLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1453:1: ( ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1454:1: ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1454:1: ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1455:1: ( rule__OneLifelineMessage__CaptionAssignment_3 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getCaptionAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1456:1: ( rule__OneLifelineMessage__CaptionAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1456:2: rule__OneLifelineMessage__CaptionAssignment_3
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__CaptionAssignment_3_in_rule__OneLifelineMessage__Group__3__Impl2950);
            rule__OneLifelineMessage__CaptionAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getCaptionAssignment_3()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1466:1: rule__OneLifelineMessage__Group__4 : rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5 ;
    public final void rule__OneLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1470:1: ( rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1471:2: rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__42980);
            rule__OneLifelineMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__5_in_rule__OneLifelineMessage__Group__42983);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1478:1: rule__OneLifelineMessage__Group__4__Impl : ( ( rule__OneLifelineMessage__Alternatives_4 )? ) ;
    public final void rule__OneLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1482:1: ( ( ( rule__OneLifelineMessage__Alternatives_4 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1483:1: ( ( rule__OneLifelineMessage__Alternatives_4 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1483:1: ( ( rule__OneLifelineMessage__Alternatives_4 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1484:1: ( rule__OneLifelineMessage__Alternatives_4 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getAlternatives_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1485:1: ( rule__OneLifelineMessage__Alternatives_4 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=37 && LA15_0<=38)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1485:2: rule__OneLifelineMessage__Alternatives_4
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Alternatives_4_in_rule__OneLifelineMessage__Group__4__Impl3010);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1495:1: rule__OneLifelineMessage__Group__5 : rule__OneLifelineMessage__Group__5__Impl ;
    public final void rule__OneLifelineMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1499:1: ( rule__OneLifelineMessage__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1500:2: rule__OneLifelineMessage__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__5__Impl_in_rule__OneLifelineMessage__Group__53041);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1506:1: rule__OneLifelineMessage__Group__5__Impl : ( ( rule__OneLifelineMessage__Group_5__0 )? ) ;
    public final void rule__OneLifelineMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1510:1: ( ( ( rule__OneLifelineMessage__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1511:1: ( ( rule__OneLifelineMessage__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1511:1: ( ( rule__OneLifelineMessage__Group_5__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1512:1: ( rule__OneLifelineMessage__Group_5__0 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1513:1: ( rule__OneLifelineMessage__Group_5__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==24) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1513:2: rule__OneLifelineMessage__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__0_in_rule__OneLifelineMessage__Group__5__Impl3068);
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


    // $ANTLR start "rule__OneLifelineMessage__Group_4_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1535:1: rule__OneLifelineMessage__Group_4_1__0 : rule__OneLifelineMessage__Group_4_1__0__Impl rule__OneLifelineMessage__Group_4_1__1 ;
    public final void rule__OneLifelineMessage__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1539:1: ( rule__OneLifelineMessage__Group_4_1__0__Impl rule__OneLifelineMessage__Group_4_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1540:2: rule__OneLifelineMessage__Group_4_1__0__Impl rule__OneLifelineMessage__Group_4_1__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__0__Impl_in_rule__OneLifelineMessage__Group_4_1__03111);
            rule__OneLifelineMessage__Group_4_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__1_in_rule__OneLifelineMessage__Group_4_1__03114);
            rule__OneLifelineMessage__Group_4_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_1__0"


    // $ANTLR start "rule__OneLifelineMessage__Group_4_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1547:1: rule__OneLifelineMessage__Group_4_1__0__Impl : ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) ) ;
    public final void rule__OneLifelineMessage__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1551:1: ( ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1552:1: ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1552:1: ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1553:1: ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockAssignment_4_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1554:1: ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1554:2: rule__OneLifelineMessage__EndBlockAssignment_4_1_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__EndBlockAssignment_4_1_0_in_rule__OneLifelineMessage__Group_4_1__0__Impl3141);
            rule__OneLifelineMessage__EndBlockAssignment_4_1_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockAssignment_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_1__0__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_4_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1564:1: rule__OneLifelineMessage__Group_4_1__1 : rule__OneLifelineMessage__Group_4_1__1__Impl ;
    public final void rule__OneLifelineMessage__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1568:1: ( rule__OneLifelineMessage__Group_4_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1569:2: rule__OneLifelineMessage__Group_4_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__1__Impl_in_rule__OneLifelineMessage__Group_4_1__13171);
            rule__OneLifelineMessage__Group_4_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_1__1"


    // $ANTLR start "rule__OneLifelineMessage__Group_4_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1575:1: rule__OneLifelineMessage__Group_4_1__1__Impl : ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? ) ;
    public final void rule__OneLifelineMessage__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1579:1: ( ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1580:1: ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1580:1: ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1581:1: ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountAssignment_4_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1582:1: ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_INT_GREATER_ZERO) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1582:2: rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1_in_rule__OneLifelineMessage__Group_4_1__1__Impl3198);
                    rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountAssignment_4_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4_1__1__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_5__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1596:1: rule__OneLifelineMessage__Group_5__0 : rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1 ;
    public final void rule__OneLifelineMessage__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1600:1: ( rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1601:2: rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__0__Impl_in_rule__OneLifelineMessage__Group_5__03233);
            rule__OneLifelineMessage__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__1_in_rule__OneLifelineMessage__Group_5__03236);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1608:1: rule__OneLifelineMessage__Group_5__0__Impl : ( 'note' ) ;
    public final void rule__OneLifelineMessage__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1612:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1613:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1613:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1614:1: 'note'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0()); 
            match(input,24,FOLLOW_24_in_rule__OneLifelineMessage__Group_5__0__Impl3264); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1627:1: rule__OneLifelineMessage__Group_5__1 : rule__OneLifelineMessage__Group_5__1__Impl ;
    public final void rule__OneLifelineMessage__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1631:1: ( rule__OneLifelineMessage__Group_5__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1632:2: rule__OneLifelineMessage__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__1__Impl_in_rule__OneLifelineMessage__Group_5__13295);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1638:1: rule__OneLifelineMessage__Group_5__1__Impl : ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) ) ;
    public final void rule__OneLifelineMessage__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1642:1: ( ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1643:1: ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1643:1: ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1644:1: ( rule__OneLifelineMessage__NoteAssignment_5_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteAssignment_5_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1645:1: ( rule__OneLifelineMessage__NoteAssignment_5_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1645:2: rule__OneLifelineMessage__NoteAssignment_5_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__NoteAssignment_5_1_in_rule__OneLifelineMessage__Group_5__1__Impl3322);
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


    // $ANTLR start "rule__OneLifelineNote__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1659:1: rule__OneLifelineNote__Group__0 : rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 ;
    public final void rule__OneLifelineNote__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1663:1: ( rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1664:2: rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__03356);
            rule__OneLifelineNote__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__03359);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1671:1: rule__OneLifelineNote__Group__0__Impl : ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineNote__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1675:1: ( ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1676:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1676:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1677:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1678:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1678:2: rule__OneLifelineNote__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl3386);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1688:1: rule__OneLifelineNote__Group__1 : rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 ;
    public final void rule__OneLifelineNote__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1692:1: ( rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1693:2: rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__13416);
            rule__OneLifelineNote__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__13419);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1700:1: rule__OneLifelineNote__Group__1__Impl : ( 'note' ) ;
    public final void rule__OneLifelineNote__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1704:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1705:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1705:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1706:1: 'note'
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1()); 
            match(input,24,FOLLOW_24_in_rule__OneLifelineNote__Group__1__Impl3447); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1719:1: rule__OneLifelineNote__Group__2 : rule__OneLifelineNote__Group__2__Impl ;
    public final void rule__OneLifelineNote__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1723:1: ( rule__OneLifelineNote__Group__2__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1724:2: rule__OneLifelineNote__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__23478);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1730:1: rule__OneLifelineNote__Group__2__Impl : ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) ;
    public final void rule__OneLifelineNote__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1734:1: ( ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1735:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1735:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1736:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1737:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1737:2: rule__OneLifelineNote__NoteAssignment_2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl3505);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1753:1: rule__DestroyLifelineEvent__Group__0 : rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1 ;
    public final void rule__DestroyLifelineEvent__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1757:1: ( rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1758:2: rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__0__Impl_in_rule__DestroyLifelineEvent__Group__03541);
            rule__DestroyLifelineEvent__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__1_in_rule__DestroyLifelineEvent__Group__03544);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1765:1: rule__DestroyLifelineEvent__Group__0__Impl : ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) ) ;
    public final void rule__DestroyLifelineEvent__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1769:1: ( ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1770:1: ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1770:1: ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1771:1: ( rule__DestroyLifelineEvent__LifelineAssignment_0 )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1772:1: ( rule__DestroyLifelineEvent__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1772:2: rule__DestroyLifelineEvent__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__LifelineAssignment_0_in_rule__DestroyLifelineEvent__Group__0__Impl3571);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1782:1: rule__DestroyLifelineEvent__Group__1 : rule__DestroyLifelineEvent__Group__1__Impl ;
    public final void rule__DestroyLifelineEvent__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1786:1: ( rule__DestroyLifelineEvent__Group__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1787:2: rule__DestroyLifelineEvent__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__1__Impl_in_rule__DestroyLifelineEvent__Group__13601);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1793:1: rule__DestroyLifelineEvent__Group__1__Impl : ( 'destroy' ) ;
    public final void rule__DestroyLifelineEvent__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1797:1: ( ( 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1798:1: ( 'destroy' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1798:1: ( 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1799:1: 'destroy'
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getDestroyKeyword_1()); 
            match(input,25,FOLLOW_25_in_rule__DestroyLifelineEvent__Group__1__Impl3629); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1816:1: rule__Fragment__Group__0 : rule__Fragment__Group__0__Impl rule__Fragment__Group__1 ;
    public final void rule__Fragment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1820:1: ( rule__Fragment__Group__0__Impl rule__Fragment__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1821:2: rule__Fragment__Group__0__Impl rule__Fragment__Group__1
            {
            pushFollow(FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__03664);
            rule__Fragment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__03667);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1828:1: rule__Fragment__Group__0__Impl : ( 'fragment' ) ;
    public final void rule__Fragment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1832:1: ( ( 'fragment' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1833:1: ( 'fragment' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1833:1: ( 'fragment' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1834:1: 'fragment'
            {
             before(grammarAccess.getFragmentAccess().getFragmentKeyword_0()); 
            match(input,26,FOLLOW_26_in_rule__Fragment__Group__0__Impl3695); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1847:1: rule__Fragment__Group__1 : rule__Fragment__Group__1__Impl rule__Fragment__Group__2 ;
    public final void rule__Fragment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1851:1: ( rule__Fragment__Group__1__Impl rule__Fragment__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1852:2: rule__Fragment__Group__1__Impl rule__Fragment__Group__2
            {
            pushFollow(FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__13726);
            rule__Fragment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__13729);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1859:1: rule__Fragment__Group__1__Impl : ( ( rule__Fragment__NameAssignment_1 ) ) ;
    public final void rule__Fragment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1863:1: ( ( ( rule__Fragment__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1864:1: ( ( rule__Fragment__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1864:1: ( ( rule__Fragment__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1865:1: ( rule__Fragment__NameAssignment_1 )
            {
             before(grammarAccess.getFragmentAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1866:1: ( rule__Fragment__NameAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1866:2: rule__Fragment__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl3756);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1876:1: rule__Fragment__Group__2 : rule__Fragment__Group__2__Impl rule__Fragment__Group__3 ;
    public final void rule__Fragment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1880:1: ( rule__Fragment__Group__2__Impl rule__Fragment__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1881:2: rule__Fragment__Group__2__Impl rule__Fragment__Group__3
            {
            pushFollow(FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__23786);
            rule__Fragment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__23789);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1888:1: rule__Fragment__Group__2__Impl : ( ( rule__Fragment__SectionsAssignment_2 ) ) ;
    public final void rule__Fragment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1892:1: ( ( ( rule__Fragment__SectionsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1893:1: ( ( rule__Fragment__SectionsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1893:1: ( ( rule__Fragment__SectionsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1894:1: ( rule__Fragment__SectionsAssignment_2 )
            {
             before(grammarAccess.getFragmentAccess().getSectionsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1895:1: ( rule__Fragment__SectionsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1895:2: rule__Fragment__SectionsAssignment_2
            {
            pushFollow(FOLLOW_rule__Fragment__SectionsAssignment_2_in_rule__Fragment__Group__2__Impl3816);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1905:1: rule__Fragment__Group__3 : rule__Fragment__Group__3__Impl ;
    public final void rule__Fragment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1909:1: ( rule__Fragment__Group__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1910:2: rule__Fragment__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__33846);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1916:1: rule__Fragment__Group__3__Impl : ( ( rule__Fragment__SectionsAssignment_3 )* ) ;
    public final void rule__Fragment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1920:1: ( ( ( rule__Fragment__SectionsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1921:1: ( ( rule__Fragment__SectionsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1921:1: ( ( rule__Fragment__SectionsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1922:1: ( rule__Fragment__SectionsAssignment_3 )*
            {
             before(grammarAccess.getFragmentAccess().getSectionsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1923:1: ( rule__Fragment__SectionsAssignment_3 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==27) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1923:2: rule__Fragment__SectionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Fragment__SectionsAssignment_3_in_rule__Fragment__Group__3__Impl3873);
            	    rule__Fragment__SectionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1941:1: rule__Section__Group__0 : rule__Section__Group__0__Impl rule__Section__Group__1 ;
    public final void rule__Section__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1945:1: ( rule__Section__Group__0__Impl rule__Section__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1946:2: rule__Section__Group__0__Impl rule__Section__Group__1
            {
            pushFollow(FOLLOW_rule__Section__Group__0__Impl_in_rule__Section__Group__03912);
            rule__Section__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__1_in_rule__Section__Group__03915);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1953:1: rule__Section__Group__0__Impl : ( '{' ) ;
    public final void rule__Section__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1957:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1958:1: ( '{' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1958:1: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1959:1: '{'
            {
             before(grammarAccess.getSectionAccess().getLeftCurlyBracketKeyword_0()); 
            match(input,27,FOLLOW_27_in_rule__Section__Group__0__Impl3943); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1972:1: rule__Section__Group__1 : rule__Section__Group__1__Impl rule__Section__Group__2 ;
    public final void rule__Section__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1976:1: ( rule__Section__Group__1__Impl rule__Section__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1977:2: rule__Section__Group__1__Impl rule__Section__Group__2
            {
            pushFollow(FOLLOW_rule__Section__Group__1__Impl_in_rule__Section__Group__13974);
            rule__Section__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__2_in_rule__Section__Group__13977);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1984:1: rule__Section__Group__1__Impl : ( ( rule__Section__Group_1__0 )? ) ;
    public final void rule__Section__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1988:1: ( ( ( rule__Section__Group_1__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1989:1: ( ( rule__Section__Group_1__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1989:1: ( ( rule__Section__Group_1__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1990:1: ( rule__Section__Group_1__0 )?
            {
             before(grammarAccess.getSectionAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1991:1: ( rule__Section__Group_1__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==29) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1991:2: rule__Section__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Section__Group_1__0_in_rule__Section__Group__1__Impl4004);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2001:1: rule__Section__Group__2 : rule__Section__Group__2__Impl rule__Section__Group__3 ;
    public final void rule__Section__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2005:1: ( rule__Section__Group__2__Impl rule__Section__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2006:2: rule__Section__Group__2__Impl rule__Section__Group__3
            {
            pushFollow(FOLLOW_rule__Section__Group__2__Impl_in_rule__Section__Group__24035);
            rule__Section__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__3_in_rule__Section__Group__24038);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2013:1: rule__Section__Group__2__Impl : ( ( rule__Section__InteractionsAssignment_2 ) ) ;
    public final void rule__Section__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2017:1: ( ( ( rule__Section__InteractionsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2018:1: ( ( rule__Section__InteractionsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2018:1: ( ( rule__Section__InteractionsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2019:1: ( rule__Section__InteractionsAssignment_2 )
            {
             before(grammarAccess.getSectionAccess().getInteractionsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2020:1: ( rule__Section__InteractionsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2020:2: rule__Section__InteractionsAssignment_2
            {
            pushFollow(FOLLOW_rule__Section__InteractionsAssignment_2_in_rule__Section__Group__2__Impl4065);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2030:1: rule__Section__Group__3 : rule__Section__Group__3__Impl rule__Section__Group__4 ;
    public final void rule__Section__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2034:1: ( rule__Section__Group__3__Impl rule__Section__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2035:2: rule__Section__Group__3__Impl rule__Section__Group__4
            {
            pushFollow(FOLLOW_rule__Section__Group__3__Impl_in_rule__Section__Group__34095);
            rule__Section__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__4_in_rule__Section__Group__34098);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2042:1: rule__Section__Group__3__Impl : ( ( rule__Section__InteractionsAssignment_3 )* ) ;
    public final void rule__Section__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2046:1: ( ( ( rule__Section__InteractionsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2047:1: ( ( rule__Section__InteractionsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2047:1: ( ( rule__Section__InteractionsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2048:1: ( rule__Section__InteractionsAssignment_3 )*
            {
             before(grammarAccess.getSectionAccess().getInteractionsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2049:1: ( rule__Section__InteractionsAssignment_3 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID||LA20_0==26||LA20_0==30) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2049:2: rule__Section__InteractionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Section__InteractionsAssignment_3_in_rule__Section__Group__3__Impl4125);
            	    rule__Section__InteractionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2059:1: rule__Section__Group__4 : rule__Section__Group__4__Impl ;
    public final void rule__Section__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2063:1: ( rule__Section__Group__4__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2064:2: rule__Section__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__Section__Group__4__Impl_in_rule__Section__Group__44156);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2070:1: rule__Section__Group__4__Impl : ( '}' ) ;
    public final void rule__Section__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2074:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2075:1: ( '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2075:1: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2076:1: '}'
            {
             before(grammarAccess.getSectionAccess().getRightCurlyBracketKeyword_4()); 
            match(input,28,FOLLOW_28_in_rule__Section__Group__4__Impl4184); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2099:1: rule__Section__Group_1__0 : rule__Section__Group_1__0__Impl rule__Section__Group_1__1 ;
    public final void rule__Section__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2103:1: ( rule__Section__Group_1__0__Impl rule__Section__Group_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2104:2: rule__Section__Group_1__0__Impl rule__Section__Group_1__1
            {
            pushFollow(FOLLOW_rule__Section__Group_1__0__Impl_in_rule__Section__Group_1__04225);
            rule__Section__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group_1__1_in_rule__Section__Group_1__04228);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2111:1: rule__Section__Group_1__0__Impl : ( 'label' ) ;
    public final void rule__Section__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2115:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2116:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2116:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2117:1: 'label'
            {
             before(grammarAccess.getSectionAccess().getLabelKeyword_1_0()); 
            match(input,29,FOLLOW_29_in_rule__Section__Group_1__0__Impl4256); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2130:1: rule__Section__Group_1__1 : rule__Section__Group_1__1__Impl ;
    public final void rule__Section__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2134:1: ( rule__Section__Group_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2135:2: rule__Section__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Section__Group_1__1__Impl_in_rule__Section__Group_1__14287);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2141:1: rule__Section__Group_1__1__Impl : ( ( rule__Section__LabelAssignment_1_1 ) ) ;
    public final void rule__Section__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2145:1: ( ( ( rule__Section__LabelAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2146:1: ( ( rule__Section__LabelAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2146:1: ( ( rule__Section__LabelAssignment_1_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2147:1: ( rule__Section__LabelAssignment_1_1 )
            {
             before(grammarAccess.getSectionAccess().getLabelAssignment_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2148:1: ( rule__Section__LabelAssignment_1_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2148:2: rule__Section__LabelAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Section__LabelAssignment_1_1_in_rule__Section__Group_1__1__Impl4314);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2162:1: rule__Refinement__Group__0 : rule__Refinement__Group__0__Impl rule__Refinement__Group__1 ;
    public final void rule__Refinement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2166:1: ( rule__Refinement__Group__0__Impl rule__Refinement__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2167:2: rule__Refinement__Group__0__Impl rule__Refinement__Group__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__04348);
            rule__Refinement__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__04351);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2174:1: rule__Refinement__Group__0__Impl : ( 'refinement' ) ;
    public final void rule__Refinement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2178:1: ( ( 'refinement' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2179:1: ( 'refinement' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2179:1: ( 'refinement' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2180:1: 'refinement'
            {
             before(grammarAccess.getRefinementAccess().getRefinementKeyword_0()); 
            match(input,30,FOLLOW_30_in_rule__Refinement__Group__0__Impl4379); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2193:1: rule__Refinement__Group__1 : rule__Refinement__Group__1__Impl rule__Refinement__Group__2 ;
    public final void rule__Refinement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2197:1: ( rule__Refinement__Group__1__Impl rule__Refinement__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2198:2: rule__Refinement__Group__1__Impl rule__Refinement__Group__2
            {
            pushFollow(FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__14410);
            rule__Refinement__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__14413);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2205:1: rule__Refinement__Group__1__Impl : ( 'label' ) ;
    public final void rule__Refinement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2209:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2210:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2210:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2211:1: 'label'
            {
             before(grammarAccess.getRefinementAccess().getLabelKeyword_1()); 
            match(input,29,FOLLOW_29_in_rule__Refinement__Group__1__Impl4441); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2224:1: rule__Refinement__Group__2 : rule__Refinement__Group__2__Impl rule__Refinement__Group__3 ;
    public final void rule__Refinement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2228:1: ( rule__Refinement__Group__2__Impl rule__Refinement__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2229:2: rule__Refinement__Group__2__Impl rule__Refinement__Group__3
            {
            pushFollow(FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__24472);
            rule__Refinement__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__24475);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2236:1: rule__Refinement__Group__2__Impl : ( ( rule__Refinement__LabelAssignment_2 ) ) ;
    public final void rule__Refinement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2240:1: ( ( ( rule__Refinement__LabelAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2241:1: ( ( rule__Refinement__LabelAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2241:1: ( ( rule__Refinement__LabelAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2242:1: ( rule__Refinement__LabelAssignment_2 )
            {
             before(grammarAccess.getRefinementAccess().getLabelAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2243:1: ( rule__Refinement__LabelAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2243:2: rule__Refinement__LabelAssignment_2
            {
            pushFollow(FOLLOW_rule__Refinement__LabelAssignment_2_in_rule__Refinement__Group__2__Impl4502);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2253:1: rule__Refinement__Group__3 : rule__Refinement__Group__3__Impl rule__Refinement__Group__4 ;
    public final void rule__Refinement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2257:1: ( rule__Refinement__Group__3__Impl rule__Refinement__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2258:2: rule__Refinement__Group__3__Impl rule__Refinement__Group__4
            {
            pushFollow(FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__34532);
            rule__Refinement__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__34535);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2265:1: rule__Refinement__Group__3__Impl : ( 'lifelines' ) ;
    public final void rule__Refinement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2269:1: ( ( 'lifelines' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2270:1: ( 'lifelines' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2270:1: ( 'lifelines' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2271:1: 'lifelines'
            {
             before(grammarAccess.getRefinementAccess().getLifelinesKeyword_3()); 
            match(input,31,FOLLOW_31_in_rule__Refinement__Group__3__Impl4563); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2284:1: rule__Refinement__Group__4 : rule__Refinement__Group__4__Impl rule__Refinement__Group__5 ;
    public final void rule__Refinement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2288:1: ( rule__Refinement__Group__4__Impl rule__Refinement__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2289:2: rule__Refinement__Group__4__Impl rule__Refinement__Group__5
            {
            pushFollow(FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__44594);
            rule__Refinement__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__44597);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2296:1: rule__Refinement__Group__4__Impl : ( ( rule__Refinement__LifelinesAssignment_4 ) ) ;
    public final void rule__Refinement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2300:1: ( ( ( rule__Refinement__LifelinesAssignment_4 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2301:1: ( ( rule__Refinement__LifelinesAssignment_4 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2301:1: ( ( rule__Refinement__LifelinesAssignment_4 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2302:1: ( rule__Refinement__LifelinesAssignment_4 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2303:1: ( rule__Refinement__LifelinesAssignment_4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2303:2: rule__Refinement__LifelinesAssignment_4
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_4_in_rule__Refinement__Group__4__Impl4624);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2313:1: rule__Refinement__Group__5 : rule__Refinement__Group__5__Impl ;
    public final void rule__Refinement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2317:1: ( rule__Refinement__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2318:2: rule__Refinement__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__54654);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2324:1: rule__Refinement__Group__5__Impl : ( ( rule__Refinement__Group_5__0 )* ) ;
    public final void rule__Refinement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2328:1: ( ( ( rule__Refinement__Group_5__0 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2329:1: ( ( rule__Refinement__Group_5__0 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2329:1: ( ( rule__Refinement__Group_5__0 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2330:1: ( rule__Refinement__Group_5__0 )*
            {
             before(grammarAccess.getRefinementAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2331:1: ( rule__Refinement__Group_5__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==32) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2331:2: rule__Refinement__Group_5__0
            	    {
            	    pushFollow(FOLLOW_rule__Refinement__Group_5__0_in_rule__Refinement__Group__5__Impl4681);
            	    rule__Refinement__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2353:1: rule__Refinement__Group_5__0 : rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1 ;
    public final void rule__Refinement__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2357:1: ( rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2358:2: rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group_5__0__Impl_in_rule__Refinement__Group_5__04724);
            rule__Refinement__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group_5__1_in_rule__Refinement__Group_5__04727);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2365:1: rule__Refinement__Group_5__0__Impl : ( ',' ) ;
    public final void rule__Refinement__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2369:1: ( ( ',' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2370:1: ( ',' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2370:1: ( ',' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2371:1: ','
            {
             before(grammarAccess.getRefinementAccess().getCommaKeyword_5_0()); 
            match(input,32,FOLLOW_32_in_rule__Refinement__Group_5__0__Impl4755); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2384:1: rule__Refinement__Group_5__1 : rule__Refinement__Group_5__1__Impl ;
    public final void rule__Refinement__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2388:1: ( rule__Refinement__Group_5__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2389:2: rule__Refinement__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group_5__1__Impl_in_rule__Refinement__Group_5__14786);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2395:1: rule__Refinement__Group_5__1__Impl : ( ( rule__Refinement__LifelinesAssignment_5_1 ) ) ;
    public final void rule__Refinement__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2399:1: ( ( ( rule__Refinement__LifelinesAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2400:1: ( ( rule__Refinement__LifelinesAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2400:1: ( ( rule__Refinement__LifelinesAssignment_5_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2401:1: ( rule__Refinement__LifelinesAssignment_5_1 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_5_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2402:1: ( rule__Refinement__LifelinesAssignment_5_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2402:2: rule__Refinement__LifelinesAssignment_5_1
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_5_1_in_rule__Refinement__Group_5__1__Impl4813);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2417:1: rule__SequenceDiagram__DiagramNameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__SequenceDiagram__DiagramNameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2421:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2422:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2422:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2423:1: RULE_STRING
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_24852); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2432:1: rule__SequenceDiagram__LifelinesAssignment_3 : ( ruleLifeline ) ;
    public final void rule__SequenceDiagram__LifelinesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2436:1: ( ( ruleLifeline ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2437:1: ( ruleLifeline )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2437:1: ( ruleLifeline )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2438:1: ruleLifeline
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_34883);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2447:1: rule__SequenceDiagram__InteractionsAssignment_4 : ( ruleInteraction ) ;
    public final void rule__SequenceDiagram__InteractionsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2451:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2452:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2452:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2453:1: ruleInteraction
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_44914);
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


    // $ANTLR start "rule__Lifeline__CaptionAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2462:1: rule__Lifeline__CaptionAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Lifeline__CaptionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2466:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2467:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2467:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2468:1: RULE_STRING
            {
             before(grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_14945); 
             after(grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__CaptionAssignment_1"


    // $ANTLR start "rule__Lifeline__NameAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2477:1: rule__Lifeline__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__Lifeline__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2481:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2482:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2482:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2483:1: RULE_ID
            {
             before(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_34976); 
             after(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Lifeline__NameAssignment_3"


    // $ANTLR start "rule__TwoLifelineMessage__SourceLifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2492:1: rule__TwoLifelineMessage__SourceLifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__SourceLifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2496:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2497:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2497:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2498:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2499:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2500:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_05011); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2511:1: rule__TwoLifelineMessage__MessageTypeAssignment_1 : ( ruleMessageType ) ;
    public final void rule__TwoLifelineMessage__MessageTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2515:1: ( ( ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2516:1: ( ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2516:1: ( ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2517:1: ruleMessageType
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleMessageType_in_rule__TwoLifelineMessage__MessageTypeAssignment_15046);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2526:1: rule__TwoLifelineMessage__MessageAssignment_2 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__MessageAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2530:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2531:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2531:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2532:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__MessageAssignment_25077); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2541:1: rule__TwoLifelineMessage__TargetLifelineAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__TargetLifelineAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2545:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2546:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2546:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2547:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2548:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2549:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_45112); 
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


    // $ANTLR start "rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2560:1: rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 : ( ( 'sourceStartBlock' ) ) ;
    public final void rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2564:1: ( ( ( 'sourceStartBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2565:1: ( ( 'sourceStartBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2565:1: ( ( 'sourceStartBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2566:1: ( 'sourceStartBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockSourceStartBlockKeyword_5_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2567:1: ( 'sourceStartBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2568:1: 'sourceStartBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockSourceStartBlockKeyword_5_0_0()); 
            match(input,33,FOLLOW_33_in_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_05152); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockSourceStartBlockKeyword_5_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockSourceStartBlockKeyword_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0"


    // $ANTLR start "rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2583:1: rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 : ( ( 'sourceEndBlock' ) ) ;
    public final void rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2587:1: ( ( ( 'sourceEndBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2588:1: ( ( 'sourceEndBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2588:1: ( ( 'sourceEndBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2589:1: ( 'sourceEndBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockSourceEndBlockKeyword_5_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2590:1: ( 'sourceEndBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2591:1: 'sourceEndBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockSourceEndBlockKeyword_5_1_0_0()); 
            match(input,34,FOLLOW_34_in_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_05196); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockSourceEndBlockKeyword_5_1_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockSourceEndBlockKeyword_5_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0"


    // $ANTLR start "rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2606:1: rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2610:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2611:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2611:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2612:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_15235); 
             after(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1"


    // $ANTLR start "rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2621:1: rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 : ( ( 'targetStartBlock' ) ) ;
    public final void rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2625:1: ( ( ( 'targetStartBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2626:1: ( ( 'targetStartBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2626:1: ( ( 'targetStartBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2627:1: ( 'targetStartBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockTargetStartBlockKeyword_6_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2628:1: ( 'targetStartBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2629:1: 'targetStartBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockTargetStartBlockKeyword_6_0_0()); 
            match(input,35,FOLLOW_35_in_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_05271); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockTargetStartBlockKeyword_6_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockTargetStartBlockKeyword_6_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0"


    // $ANTLR start "rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2644:1: rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 : ( ( 'targetEndBlock' ) ) ;
    public final void rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2648:1: ( ( ( 'targetEndBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2649:1: ( ( 'targetEndBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2649:1: ( ( 'targetEndBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2650:1: ( 'targetEndBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockTargetEndBlockKeyword_6_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2651:1: ( 'targetEndBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2652:1: 'targetEndBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockTargetEndBlockKeyword_6_1_0_0()); 
            match(input,36,FOLLOW_36_in_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_05315); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockTargetEndBlockKeyword_6_1_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockTargetEndBlockKeyword_6_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0"


    // $ANTLR start "rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2667:1: rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2671:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2672:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2672:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2673:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_15354); 
             after(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1"


    // $ANTLR start "rule__TwoLifelineMessage__SourceNoteAssignment_7_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2682:1: rule__TwoLifelineMessage__SourceNoteAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__SourceNoteAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2686:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2687:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2687:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2688:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_15385); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2697:1: rule__TwoLifelineMessage__TargetNoteAssignment_8_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__TargetNoteAssignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2701:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2702:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2702:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2703:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_15416); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2712:1: rule__OneLifelineMessage__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineMessage__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2716:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2717:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2717:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2718:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2719:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2720:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_05451); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2731:1: rule__OneLifelineMessage__MessageTypeAssignment_1 : ( ruleMessageType ) ;
    public final void rule__OneLifelineMessage__MessageTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2735:1: ( ( ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2736:1: ( ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2736:1: ( ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2737:1: ruleMessageType
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleMessageType_in_rule__OneLifelineMessage__MessageTypeAssignment_15486);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2746:1: rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2 : ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) ) ;
    public final void rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2750:1: ( ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2751:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2751:1: ( ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2752:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundAlternatives_2_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2753:1: ( rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2753:2: rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_25517);
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


    // $ANTLR start "rule__OneLifelineMessage__CaptionAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2762:1: rule__OneLifelineMessage__CaptionAssignment_3 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__CaptionAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2766:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2767:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2767:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2768:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__CaptionAssignment_35550); 
             after(grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__CaptionAssignment_3"


    // $ANTLR start "rule__OneLifelineMessage__StartBlockAssignment_4_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2777:1: rule__OneLifelineMessage__StartBlockAssignment_4_0 : ( ( 'startBlock' ) ) ;
    public final void rule__OneLifelineMessage__StartBlockAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2781:1: ( ( ( 'startBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2782:1: ( ( 'startBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2782:1: ( ( 'startBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2783:1: ( 'startBlock' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2784:1: ( 'startBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2785:1: 'startBlock'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0()); 
            match(input,37,FOLLOW_37_in_rule__OneLifelineMessage__StartBlockAssignment_4_05586); 
             after(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__StartBlockAssignment_4_0"


    // $ANTLR start "rule__OneLifelineMessage__EndBlockAssignment_4_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2800:1: rule__OneLifelineMessage__EndBlockAssignment_4_1_0 : ( ( 'endBlock' ) ) ;
    public final void rule__OneLifelineMessage__EndBlockAssignment_4_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2804:1: ( ( ( 'endBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2805:1: ( ( 'endBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2805:1: ( ( 'endBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2806:1: ( 'endBlock' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2807:1: ( 'endBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2808:1: 'endBlock'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0()); 
            match(input,38,FOLLOW_38_in_rule__OneLifelineMessage__EndBlockAssignment_4_1_05630); 
             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__EndBlockAssignment_4_1_0"


    // $ANTLR start "rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2823:1: rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2827:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2828:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2828:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2829:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_4_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_15669); 
             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_4_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1"


    // $ANTLR start "rule__OneLifelineMessage__NoteAssignment_5_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2838:1: rule__OneLifelineMessage__NoteAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__NoteAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2842:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2843:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2843:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2844:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_5_15700); 
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


    // $ANTLR start "rule__OneLifelineNote__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2853:1: rule__OneLifelineNote__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineNote__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2857:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2858:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2858:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2859:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2860:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2861:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_05735); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2872:1: rule__OneLifelineNote__NoteAssignment_2 : ( RULE_STRING ) ;
    public final void rule__OneLifelineNote__NoteAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2876:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2877:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2877:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2878:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_25770); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2887:1: rule__DestroyLifelineEvent__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__DestroyLifelineEvent__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2891:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2892:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2892:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2893:1: ( RULE_ID )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2894:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2895:1: RULE_ID
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DestroyLifelineEvent__LifelineAssignment_05805); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2906:1: rule__Fragment__NameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Fragment__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2910:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2911:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2911:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2912:1: RULE_STRING
            {
             before(grammarAccess.getFragmentAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_15840); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2921:1: rule__Fragment__SectionsAssignment_2 : ( ruleSection ) ;
    public final void rule__Fragment__SectionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2925:1: ( ( ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2926:1: ( ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2926:1: ( ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2927:1: ruleSection
            {
             before(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_25871);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2936:1: rule__Fragment__SectionsAssignment_3 : ( ruleSection ) ;
    public final void rule__Fragment__SectionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2940:1: ( ( ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2941:1: ( ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2941:1: ( ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2942:1: ruleSection
            {
             before(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_35902);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2951:1: rule__Section__LabelAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__Section__LabelAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2955:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2956:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2956:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2957:1: RULE_STRING
            {
             before(grammarAccess.getSectionAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Section__LabelAssignment_1_15933); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2966:1: rule__Section__InteractionsAssignment_2 : ( ruleInteraction ) ;
    public final void rule__Section__InteractionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2970:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2971:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2971:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2972:1: ruleInteraction
            {
             before(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_25964);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2981:1: rule__Section__InteractionsAssignment_3 : ( ruleInteraction ) ;
    public final void rule__Section__InteractionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2985:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2986:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2986:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2987:1: ruleInteraction
            {
             before(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_35995);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2996:1: rule__Refinement__LabelAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Refinement__LabelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3000:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3001:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3001:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3002:1: RULE_STRING
            {
             before(grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_26026); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3011:1: rule__Refinement__LifelinesAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3015:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3016:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3016:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3017:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3018:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3019:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_46061); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3030:1: rule__Refinement__LifelinesAssignment_5_1 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3034:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3035:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3035:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3036:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_5_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3037:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3038:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_5_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_5_16100); 
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


    protected DFA1 dfa1 = new DFA1(this);
    static final String DFA1_eotS =
        "\14\uffff";
    static final String DFA1_eofS =
        "\14\uffff";
    static final String DFA1_minS =
        "\1\5\1\16\2\uffff\4\4\4\uffff";
    static final String DFA1_maxS =
        "\1\36\1\31\2\uffff\4\15\4\uffff";
    static final String DFA1_acceptS =
        "\2\uffff\1\3\1\6\4\uffff\1\5\1\4\1\2\1\1";
    static final String DFA1_specialS =
        "\14\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\1\24\uffff\1\2\3\uffff\1\3",
            "\1\4\1\5\1\6\1\7\6\uffff\1\11\1\10",
            "",
            "",
            "\1\13\7\uffff\2\12",
            "\1\13\7\uffff\2\12",
            "\1\13\7\uffff\2\12",
            "\1\13\7\uffff\2\12",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "359:1: rule__Interaction__Alternatives : ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineNote ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0_in_ruleSequenceDiagram94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_entryRuleLifeline121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLifeline128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__0_in_ruleLifeline154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_entryRuleInteraction181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInteraction188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Interaction__Alternatives_in_ruleInteraction214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTwoLifelineMessage248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__0_in_ruleTwoLifelineMessage274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineMessage308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__0_in_ruleOneLifelineMessage334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineNote368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__0_in_ruleOneLifelineNote394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroyLifelineEvent428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__0_in_ruleDestroyLifelineEvent454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0_in_ruleFragment514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_entryRuleSection541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSection548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__0_in_ruleSection574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0_in_ruleRefinement634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MessageType__Alternatives_in_ruleMessageType671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_rule__Interaction__Alternatives740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_rule__Interaction__Alternatives774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_5823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__0_in_rule__TwoLifelineMessage__Alternatives_5841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_6874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__0_in_rule__TwoLifelineMessage__Alternatives_6892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__StartBlockAssignment_4_0_in_rule__OneLifelineMessage__Alternatives_4980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__0_in_rule__OneLifelineMessage__Alternatives_4998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__MessageType__Alternatives1032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__MessageType__Alternatives1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__MessageType__Alternatives1074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__MessageType__Alternatives1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01128 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11189 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__SequenceDiagram__Group__1__Impl1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21251 = new BitSet(new long[]{0x0000000044080020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31311 = new BitSet(new long[]{0x0000000044080020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__LifelinesAssignment_3_in_rule__SequenceDiagram__Group__3__Impl1341 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__InteractionsAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1399 = new BitSet(new long[]{0x0000000044000022L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__0__Impl_in_rule__Lifeline__Group__01440 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__1_in_rule__Lifeline__Group__01443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Lifeline__Group__0__Impl1471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__1__Impl_in_rule__Lifeline__Group__11502 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__2_in_rule__Lifeline__Group__11505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__CaptionAssignment_1_in_rule__Lifeline__Group__1__Impl1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__2__Impl_in_rule__Lifeline__Group__21562 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__3_in_rule__Lifeline__Group__21565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Lifeline__Group__2__Impl1593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__3__Impl_in_rule__Lifeline__Group__31624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__NameAssignment_3_in_rule__Lifeline__Group__3__Impl1651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__01689 = new BitSet(new long[]{0x000000000003C000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__01692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl1719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__11749 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__11752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__MessageTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl1779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__21809 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__21812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__MessageAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl1839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__31869 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__31872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__TwoLifelineMessage__Group__3__Impl1900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__41931 = new BitSet(new long[]{0x0000001E00C00000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__41934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl1961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__51991 = new BitSet(new long[]{0x0000001E00C00000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__51994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__62052 = new BitSet(new long[]{0x0000001E00C00000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__62055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__72113 = new BitSet(new long[]{0x0000001E00C00000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__72116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl2143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__82174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl2201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__0__Impl_in_rule__TwoLifelineMessage__Group_5_1__02250 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__1_in_rule__TwoLifelineMessage__Group_5_1__02253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0_in_rule__TwoLifelineMessage__Group_5_1__0__Impl2280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__1__Impl_in_rule__TwoLifelineMessage__Group_5_1__12310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1_in_rule__TwoLifelineMessage__Group_5_1__1__Impl2337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__0__Impl_in_rule__TwoLifelineMessage__Group_6_1__02372 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__1_in_rule__TwoLifelineMessage__Group_6_1__02375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0_in_rule__TwoLifelineMessage__Group_6_1__0__Impl2402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__1__Impl_in_rule__TwoLifelineMessage__Group_6_1__12432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1_in_rule__TwoLifelineMessage__Group_6_1__1__Impl2459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__02494 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__02497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__TwoLifelineMessage__Group_7__0__Impl2525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__12556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl2583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__02617 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__02620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__TwoLifelineMessage__Group_8__0__Impl2648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__12679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl2706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__02740 = new BitSet(new long[]{0x000000000003C000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__02743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__12800 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__12803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl2830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__22860 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__22863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_2_in_rule__OneLifelineMessage__Group__2__Impl2890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__32920 = new BitSet(new long[]{0x0000006001000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__32923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__CaptionAssignment_3_in_rule__OneLifelineMessage__Group__3__Impl2950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__42980 = new BitSet(new long[]{0x0000006001000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__5_in_rule__OneLifelineMessage__Group__42983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Alternatives_4_in_rule__OneLifelineMessage__Group__4__Impl3010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__5__Impl_in_rule__OneLifelineMessage__Group__53041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__0_in_rule__OneLifelineMessage__Group__5__Impl3068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__0__Impl_in_rule__OneLifelineMessage__Group_4_1__03111 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__1_in_rule__OneLifelineMessage__Group_4_1__03114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndBlockAssignment_4_1_0_in_rule__OneLifelineMessage__Group_4_1__0__Impl3141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__1__Impl_in_rule__OneLifelineMessage__Group_4_1__13171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1_in_rule__OneLifelineMessage__Group_4_1__1__Impl3198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__0__Impl_in_rule__OneLifelineMessage__Group_5__03233 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__1_in_rule__OneLifelineMessage__Group_5__03236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__OneLifelineMessage__Group_5__0__Impl3264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__1__Impl_in_rule__OneLifelineMessage__Group_5__13295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__NoteAssignment_5_1_in_rule__OneLifelineMessage__Group_5__1__Impl3322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__03356 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__03359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl3386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__13416 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__13419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__OneLifelineNote__Group__1__Impl3447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__23478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl3505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__0__Impl_in_rule__DestroyLifelineEvent__Group__03541 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__1_in_rule__DestroyLifelineEvent__Group__03544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__LifelineAssignment_0_in_rule__DestroyLifelineEvent__Group__0__Impl3571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__1__Impl_in_rule__DestroyLifelineEvent__Group__13601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__DestroyLifelineEvent__Group__1__Impl3629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__03664 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__03667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Fragment__Group__0__Impl3695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__13726 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__13729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl3756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__23786 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__23789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__SectionsAssignment_2_in_rule__Fragment__Group__2__Impl3816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__33846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__SectionsAssignment_3_in_rule__Fragment__Group__3__Impl3873 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_rule__Section__Group__0__Impl_in_rule__Section__Group__03912 = new BitSet(new long[]{0x0000000064000020L});
    public static final BitSet FOLLOW_rule__Section__Group__1_in_rule__Section__Group__03915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__Section__Group__0__Impl3943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__1__Impl_in_rule__Section__Group__13974 = new BitSet(new long[]{0x0000000064000020L});
    public static final BitSet FOLLOW_rule__Section__Group__2_in_rule__Section__Group__13977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__0_in_rule__Section__Group__1__Impl4004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__2__Impl_in_rule__Section__Group__24035 = new BitSet(new long[]{0x0000000054000020L});
    public static final BitSet FOLLOW_rule__Section__Group__3_in_rule__Section__Group__24038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__InteractionsAssignment_2_in_rule__Section__Group__2__Impl4065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__3__Impl_in_rule__Section__Group__34095 = new BitSet(new long[]{0x0000000054000020L});
    public static final BitSet FOLLOW_rule__Section__Group__4_in_rule__Section__Group__34098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__InteractionsAssignment_3_in_rule__Section__Group__3__Impl4125 = new BitSet(new long[]{0x0000000044000022L});
    public static final BitSet FOLLOW_rule__Section__Group__4__Impl_in_rule__Section__Group__44156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__Section__Group__4__Impl4184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__0__Impl_in_rule__Section__Group_1__04225 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Section__Group_1__1_in_rule__Section__Group_1__04228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Section__Group_1__0__Impl4256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__1__Impl_in_rule__Section__Group_1__14287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__LabelAssignment_1_1_in_rule__Section__Group_1__1__Impl4314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__04348 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__04351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__Refinement__Group__0__Impl4379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__14410 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__14413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__Refinement__Group__1__Impl4441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__24472 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__24475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LabelAssignment_2_in_rule__Refinement__Group__2__Impl4502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__34532 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__34535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Refinement__Group__3__Impl4563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__44594 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__44597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_4_in_rule__Refinement__Group__4__Impl4624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__54654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__0_in_rule__Refinement__Group__5__Impl4681 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__0__Impl_in_rule__Refinement__Group_5__04724 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__1_in_rule__Refinement__Group_5__04727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__Refinement__Group_5__0__Impl4755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__1__Impl_in_rule__Refinement__Group_5__14786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_5_1_in_rule__Refinement__Group_5__1__Impl4813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_24852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_34883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_44914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_14945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_34976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_05011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMessageType_in_rule__TwoLifelineMessage__MessageTypeAssignment_15046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__MessageAssignment_25077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_45112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_05152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_05196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_15235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_05271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_05315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_15354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_15385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_15416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_05451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMessageType_in_rule__OneLifelineMessage__MessageTypeAssignment_15486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeLostAndFoundAlternatives_2_0_in_rule__OneLifelineMessage__MessageTypeLostAndFoundAssignment_25517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__CaptionAssignment_35550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__OneLifelineMessage__StartBlockAssignment_4_05586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__OneLifelineMessage__EndBlockAssignment_4_1_05630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_15669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_5_15700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_05735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_25770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DestroyLifelineEvent__LifelineAssignment_05805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_15840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_25871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_35902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Section__LabelAssignment_1_15933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_25964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_35995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_26026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_46061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_5_16100 = new BitSet(new long[]{0x0000000000000002L});

}