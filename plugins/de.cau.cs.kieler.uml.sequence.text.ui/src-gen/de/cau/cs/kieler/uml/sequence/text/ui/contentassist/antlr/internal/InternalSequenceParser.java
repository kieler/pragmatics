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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'lost'", "'found'", "'async'", "'create'", "'response'", "'sync'", "'sequenceDiagram'", "'{'", "'}'", "'Char'", "'Boolean'", "'Integer'", "'Float'", "'lifeline'", "'as'", "'to'", "'sourceNote'", "'targetNote'", "'note'", "'fragment'", "'label'", "'refinement'", "'lifelines'", "','", "'sourceStartBlock'", "'sourceEndBlock'", "'targetStartBlock'", "'targetEndBlock'", "'startBlock'", "'endBlock'", "'destroy'"
    };
    public static final int T__42=42;
    public static final int RULE_ID=5;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_SL_COMMENT=9;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=8;
    public static final int RULE_INT_GREATER_ZERO=6;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int RULE_STRING=4;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__37=37;
    public static final int T__12=12;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_INT=7;
    public static final int RULE_WS=10;

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


    // $ANTLR start "entryRuleLocalVariable"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:88:1: entryRuleLocalVariable : ruleLocalVariable EOF ;
    public final void entryRuleLocalVariable() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:89:1: ( ruleLocalVariable EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:90:1: ruleLocalVariable EOF
            {
             before(grammarAccess.getLocalVariableRule()); 
            pushFollow(FOLLOW_ruleLocalVariable_in_entryRuleLocalVariable121);
            ruleLocalVariable();

            state._fsp--;

             after(grammarAccess.getLocalVariableRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalVariable128); 

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
    // $ANTLR end "entryRuleLocalVariable"


    // $ANTLR start "ruleLocalVariable"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:97:1: ruleLocalVariable : ( ( rule__LocalVariable__Alternatives ) ) ;
    public final void ruleLocalVariable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:101:2: ( ( ( rule__LocalVariable__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__LocalVariable__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__LocalVariable__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:103:1: ( rule__LocalVariable__Alternatives )
            {
             before(grammarAccess.getLocalVariableAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:1: ( rule__LocalVariable__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:2: rule__LocalVariable__Alternatives
            {
            pushFollow(FOLLOW_rule__LocalVariable__Alternatives_in_ruleLocalVariable154);
            rule__LocalVariable__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLocalVariable"


    // $ANTLR start "entryRuleLifeline"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:116:1: entryRuleLifeline : ruleLifeline EOF ;
    public final void entryRuleLifeline() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:117:1: ( ruleLifeline EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:118:1: ruleLifeline EOF
            {
             before(grammarAccess.getLifelineRule()); 
            pushFollow(FOLLOW_ruleLifeline_in_entryRuleLifeline181);
            ruleLifeline();

            state._fsp--;

             after(grammarAccess.getLifelineRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLifeline188); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:125:1: ruleLifeline : ( ( rule__Lifeline__Group__0 ) ) ;
    public final void ruleLifeline() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:129:2: ( ( ( rule__Lifeline__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:130:1: ( ( rule__Lifeline__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:130:1: ( ( rule__Lifeline__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:131:1: ( rule__Lifeline__Group__0 )
            {
             before(grammarAccess.getLifelineAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:132:1: ( rule__Lifeline__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:132:2: rule__Lifeline__Group__0
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__0_in_ruleLifeline214);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:144:1: entryRuleInteraction : ruleInteraction EOF ;
    public final void entryRuleInteraction() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:145:1: ( ruleInteraction EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:146:1: ruleInteraction EOF
            {
             before(grammarAccess.getInteractionRule()); 
            pushFollow(FOLLOW_ruleInteraction_in_entryRuleInteraction241);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getInteractionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInteraction248); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:153:1: ruleInteraction : ( ( rule__Interaction__Alternatives ) ) ;
    public final void ruleInteraction() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:157:2: ( ( ( rule__Interaction__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:158:1: ( ( rule__Interaction__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:158:1: ( ( rule__Interaction__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:159:1: ( rule__Interaction__Alternatives )
            {
             before(grammarAccess.getInteractionAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:160:1: ( rule__Interaction__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:160:2: rule__Interaction__Alternatives
            {
            pushFollow(FOLLOW_rule__Interaction__Alternatives_in_ruleInteraction274);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:172:1: entryRuleTwoLifelineMessage : ruleTwoLifelineMessage EOF ;
    public final void entryRuleTwoLifelineMessage() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:173:1: ( ruleTwoLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:174:1: ruleTwoLifelineMessage EOF
            {
             before(grammarAccess.getTwoLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage301);
            ruleTwoLifelineMessage();

            state._fsp--;

             after(grammarAccess.getTwoLifelineMessageRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTwoLifelineMessage308); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:181:1: ruleTwoLifelineMessage : ( ( rule__TwoLifelineMessage__Group__0 ) ) ;
    public final void ruleTwoLifelineMessage() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:185:2: ( ( ( rule__TwoLifelineMessage__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:186:1: ( ( rule__TwoLifelineMessage__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:186:1: ( ( rule__TwoLifelineMessage__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:187:1: ( rule__TwoLifelineMessage__Group__0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:188:1: ( rule__TwoLifelineMessage__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:188:2: rule__TwoLifelineMessage__Group__0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__0_in_ruleTwoLifelineMessage334);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:200:1: entryRuleOneLifelineMessage : ruleOneLifelineMessage EOF ;
    public final void entryRuleOneLifelineMessage() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:201:1: ( ruleOneLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:202:1: ruleOneLifelineMessage EOF
            {
             before(grammarAccess.getOneLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage361);
            ruleOneLifelineMessage();

            state._fsp--;

             after(grammarAccess.getOneLifelineMessageRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineMessage368); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:209:1: ruleOneLifelineMessage : ( ( rule__OneLifelineMessage__Group__0 ) ) ;
    public final void ruleOneLifelineMessage() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:213:2: ( ( ( rule__OneLifelineMessage__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:214:1: ( ( rule__OneLifelineMessage__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:214:1: ( ( rule__OneLifelineMessage__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:215:1: ( rule__OneLifelineMessage__Group__0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:216:1: ( rule__OneLifelineMessage__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:216:2: rule__OneLifelineMessage__Group__0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__0_in_ruleOneLifelineMessage394);
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


    // $ANTLR start "entryRuleOneLifelineEndBlock"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:228:1: entryRuleOneLifelineEndBlock : ruleOneLifelineEndBlock EOF ;
    public final void entryRuleOneLifelineEndBlock() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:229:1: ( ruleOneLifelineEndBlock EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:230:1: ruleOneLifelineEndBlock EOF
            {
             before(grammarAccess.getOneLifelineEndBlockRule()); 
            pushFollow(FOLLOW_ruleOneLifelineEndBlock_in_entryRuleOneLifelineEndBlock421);
            ruleOneLifelineEndBlock();

            state._fsp--;

             after(grammarAccess.getOneLifelineEndBlockRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineEndBlock428); 

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
    // $ANTLR end "entryRuleOneLifelineEndBlock"


    // $ANTLR start "ruleOneLifelineEndBlock"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:237:1: ruleOneLifelineEndBlock : ( ( rule__OneLifelineEndBlock__Group__0 ) ) ;
    public final void ruleOneLifelineEndBlock() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:241:2: ( ( ( rule__OneLifelineEndBlock__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:242:1: ( ( rule__OneLifelineEndBlock__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:242:1: ( ( rule__OneLifelineEndBlock__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:243:1: ( rule__OneLifelineEndBlock__Group__0 )
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:244:1: ( rule__OneLifelineEndBlock__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:244:2: rule__OneLifelineEndBlock__Group__0
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__0_in_ruleOneLifelineEndBlock454);
            rule__OneLifelineEndBlock__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineEndBlockAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOneLifelineEndBlock"


    // $ANTLR start "entryRuleOneLifelineNote"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:256:1: entryRuleOneLifelineNote : ruleOneLifelineNote EOF ;
    public final void entryRuleOneLifelineNote() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:257:1: ( ruleOneLifelineNote EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:258:1: ruleOneLifelineNote EOF
            {
             before(grammarAccess.getOneLifelineNoteRule()); 
            pushFollow(FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote481);
            ruleOneLifelineNote();

            state._fsp--;

             after(grammarAccess.getOneLifelineNoteRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineNote488); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:265:1: ruleOneLifelineNote : ( ( rule__OneLifelineNote__Group__0 ) ) ;
    public final void ruleOneLifelineNote() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:269:2: ( ( ( rule__OneLifelineNote__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:270:1: ( ( rule__OneLifelineNote__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:270:1: ( ( rule__OneLifelineNote__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:271:1: ( rule__OneLifelineNote__Group__0 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:272:1: ( rule__OneLifelineNote__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:272:2: rule__OneLifelineNote__Group__0
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__0_in_ruleOneLifelineNote514);
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


    // $ANTLR start "entryRuleDestroy"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:284:1: entryRuleDestroy : ruleDestroy EOF ;
    public final void entryRuleDestroy() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:285:1: ( ruleDestroy EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:286:1: ruleDestroy EOF
            {
             before(grammarAccess.getDestroyRule()); 
            pushFollow(FOLLOW_ruleDestroy_in_entryRuleDestroy541);
            ruleDestroy();

            state._fsp--;

             after(grammarAccess.getDestroyRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroy548); 

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
    // $ANTLR end "entryRuleDestroy"


    // $ANTLR start "ruleDestroy"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:293:1: ruleDestroy : ( ( rule__Destroy__Group__0 ) ) ;
    public final void ruleDestroy() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:297:2: ( ( ( rule__Destroy__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__Destroy__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__Destroy__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:299:1: ( rule__Destroy__Group__0 )
            {
             before(grammarAccess.getDestroyAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:1: ( rule__Destroy__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:2: rule__Destroy__Group__0
            {
            pushFollow(FOLLOW_rule__Destroy__Group__0_in_ruleDestroy574);
            rule__Destroy__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDestroyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDestroy"


    // $ANTLR start "entryRuleFragment"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:312:1: entryRuleFragment : ruleFragment EOF ;
    public final void entryRuleFragment() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:313:1: ( ruleFragment EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:314:1: ruleFragment EOF
            {
             before(grammarAccess.getFragmentRule()); 
            pushFollow(FOLLOW_ruleFragment_in_entryRuleFragment601);
            ruleFragment();

            state._fsp--;

             after(grammarAccess.getFragmentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragment608); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:321:1: ruleFragment : ( ( rule__Fragment__Group__0 ) ) ;
    public final void ruleFragment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:325:2: ( ( ( rule__Fragment__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:326:1: ( ( rule__Fragment__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:326:1: ( ( rule__Fragment__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:327:1: ( rule__Fragment__Group__0 )
            {
             before(grammarAccess.getFragmentAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:328:1: ( rule__Fragment__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:328:2: rule__Fragment__Group__0
            {
            pushFollow(FOLLOW_rule__Fragment__Group__0_in_ruleFragment634);
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


    // $ANTLR start "entryRuleFragmentContent"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:340:1: entryRuleFragmentContent : ruleFragmentContent EOF ;
    public final void entryRuleFragmentContent() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:341:1: ( ruleFragmentContent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:342:1: ruleFragmentContent EOF
            {
             before(grammarAccess.getFragmentContentRule()); 
            pushFollow(FOLLOW_ruleFragmentContent_in_entryRuleFragmentContent661);
            ruleFragmentContent();

            state._fsp--;

             after(grammarAccess.getFragmentContentRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragmentContent668); 

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
    // $ANTLR end "entryRuleFragmentContent"


    // $ANTLR start "ruleFragmentContent"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:349:1: ruleFragmentContent : ( ( rule__FragmentContent__Group__0 ) ) ;
    public final void ruleFragmentContent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:353:2: ( ( ( rule__FragmentContent__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:354:1: ( ( rule__FragmentContent__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:354:1: ( ( rule__FragmentContent__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:355:1: ( rule__FragmentContent__Group__0 )
            {
             before(grammarAccess.getFragmentContentAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:356:1: ( rule__FragmentContent__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:356:2: rule__FragmentContent__Group__0
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group__0_in_ruleFragmentContent694);
            rule__FragmentContent__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFragmentContentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFragmentContent"


    // $ANTLR start "entryRuleRefinement"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:368:1: entryRuleRefinement : ruleRefinement EOF ;
    public final void entryRuleRefinement() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:369:1: ( ruleRefinement EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:370:1: ruleRefinement EOF
            {
             before(grammarAccess.getRefinementRule()); 
            pushFollow(FOLLOW_ruleRefinement_in_entryRuleRefinement721);
            ruleRefinement();

            state._fsp--;

             after(grammarAccess.getRefinementRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRefinement728); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:377:1: ruleRefinement : ( ( rule__Refinement__Group__0 ) ) ;
    public final void ruleRefinement() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:381:2: ( ( ( rule__Refinement__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:382:1: ( ( rule__Refinement__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:382:1: ( ( rule__Refinement__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:383:1: ( rule__Refinement__Group__0 )
            {
             before(grammarAccess.getRefinementAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:384:1: ( rule__Refinement__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:384:2: rule__Refinement__Group__0
            {
            pushFollow(FOLLOW_rule__Refinement__Group__0_in_ruleRefinement754);
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


    // $ANTLR start "ruleTransitionType"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:397:1: ruleTransitionType : ( ( rule__TransitionType__Alternatives ) ) ;
    public final void ruleTransitionType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:401:1: ( ( ( rule__TransitionType__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:402:1: ( ( rule__TransitionType__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:402:1: ( ( rule__TransitionType__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:403:1: ( rule__TransitionType__Alternatives )
            {
             before(grammarAccess.getTransitionTypeAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:404:1: ( rule__TransitionType__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:404:2: rule__TransitionType__Alternatives
            {
            pushFollow(FOLLOW_rule__TransitionType__Alternatives_in_ruleTransitionType791);
            rule__TransitionType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getTransitionTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTransitionType"


    // $ANTLR start "rule__LocalVariable__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:415:1: rule__LocalVariable__Alternatives : ( ( ( rule__LocalVariable__Group_0__0 ) ) | ( ( rule__LocalVariable__Group_1__0 ) ) | ( ( rule__LocalVariable__Group_2__0 ) ) | ( ( rule__LocalVariable__Group_3__0 ) ) );
    public final void rule__LocalVariable__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:419:1: ( ( ( rule__LocalVariable__Group_0__0 ) ) | ( ( rule__LocalVariable__Group_1__0 ) ) | ( ( rule__LocalVariable__Group_2__0 ) ) | ( ( rule__LocalVariable__Group_3__0 ) ) )
            int alt1=4;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt1=1;
                }
                break;
            case 22:
                {
                alt1=2;
                }
                break;
            case 23:
                {
                alt1=3;
                }
                break;
            case 24:
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
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:420:1: ( ( rule__LocalVariable__Group_0__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:420:1: ( ( rule__LocalVariable__Group_0__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:421:1: ( rule__LocalVariable__Group_0__0 )
                    {
                     before(grammarAccess.getLocalVariableAccess().getGroup_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:422:1: ( rule__LocalVariable__Group_0__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:422:2: rule__LocalVariable__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__LocalVariable__Group_0__0_in_rule__LocalVariable__Alternatives826);
                    rule__LocalVariable__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getLocalVariableAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:426:6: ( ( rule__LocalVariable__Group_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:426:6: ( ( rule__LocalVariable__Group_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:427:1: ( rule__LocalVariable__Group_1__0 )
                    {
                     before(grammarAccess.getLocalVariableAccess().getGroup_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:428:1: ( rule__LocalVariable__Group_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:428:2: rule__LocalVariable__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__LocalVariable__Group_1__0_in_rule__LocalVariable__Alternatives844);
                    rule__LocalVariable__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getLocalVariableAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:432:6: ( ( rule__LocalVariable__Group_2__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:432:6: ( ( rule__LocalVariable__Group_2__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:433:1: ( rule__LocalVariable__Group_2__0 )
                    {
                     before(grammarAccess.getLocalVariableAccess().getGroup_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:434:1: ( rule__LocalVariable__Group_2__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:434:2: rule__LocalVariable__Group_2__0
                    {
                    pushFollow(FOLLOW_rule__LocalVariable__Group_2__0_in_rule__LocalVariable__Alternatives862);
                    rule__LocalVariable__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getLocalVariableAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:438:6: ( ( rule__LocalVariable__Group_3__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:438:6: ( ( rule__LocalVariable__Group_3__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:439:1: ( rule__LocalVariable__Group_3__0 )
                    {
                     before(grammarAccess.getLocalVariableAccess().getGroup_3()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:440:1: ( rule__LocalVariable__Group_3__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:440:2: rule__LocalVariable__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__LocalVariable__Group_3__0_in_rule__LocalVariable__Alternatives880);
                    rule__LocalVariable__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getLocalVariableAccess().getGroup_3()); 

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
    // $ANTLR end "rule__LocalVariable__Alternatives"


    // $ANTLR start "rule__Interaction__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:449:1: rule__Interaction__Alternatives : ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineEndBlock ) | ( ruleOneLifelineNote ) | ( ruleDestroy ) | ( ruleRefinement ) );
    public final void rule__Interaction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:453:1: ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineEndBlock ) | ( ruleOneLifelineNote ) | ( ruleDestroy ) | ( ruleRefinement ) )
            int alt2=7;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:454:1: ( ruleTwoLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:454:1: ( ruleTwoLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:455:1: ruleTwoLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives913);
                    ruleTwoLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:460:6: ( ruleOneLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:460:6: ( ruleOneLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:461:1: ruleOneLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives930);
                    ruleOneLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:466:6: ( ruleFragment )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:466:6: ( ruleFragment )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:467:1: ruleFragment
                    {
                     before(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFragment_in_rule__Interaction__Alternatives947);
                    ruleFragment();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:472:6: ( ruleOneLifelineEndBlock )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:472:6: ( ruleOneLifelineEndBlock )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:473:1: ruleOneLifelineEndBlock
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineEndBlockParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleOneLifelineEndBlock_in_rule__Interaction__Alternatives964);
                    ruleOneLifelineEndBlock();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineEndBlockParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:478:6: ( ruleOneLifelineNote )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:478:6: ( ruleOneLifelineNote )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:479:1: ruleOneLifelineNote
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives981);
                    ruleOneLifelineNote();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:484:6: ( ruleDestroy )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:484:6: ( ruleDestroy )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:485:1: ruleDestroy
                    {
                     before(grammarAccess.getInteractionAccess().getDestroyParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleDestroy_in_rule__Interaction__Alternatives998);
                    ruleDestroy();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getDestroyParserRuleCall_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:490:6: ( ruleRefinement )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:490:6: ( ruleRefinement )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:491:1: ruleRefinement
                    {
                     before(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 
                    pushFollow(FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives1015);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:501:1: rule__TwoLifelineMessage__Alternatives_5 : ( ( ( rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__Group_5_1__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:505:1: ( ( ( rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__Group_5_1__0 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==36) ) {
                alt3=1;
            }
            else if ( (LA3_0==37) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:506:1: ( ( rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:506:1: ( ( rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:507:1: ( rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftAssignment_5_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:508:1: ( rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:508:2: rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_51047);
                    rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftAssignment_5_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:512:6: ( ( rule__TwoLifelineMessage__Group_5_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:512:6: ( ( rule__TwoLifelineMessage__Group_5_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:513:1: ( rule__TwoLifelineMessage__Group_5_1__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_5_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:514:1: ( rule__TwoLifelineMessage__Group_5_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:514:2: rule__TwoLifelineMessage__Group_5_1__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__0_in_rule__TwoLifelineMessage__Alternatives_51065);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:523:1: rule__TwoLifelineMessage__Alternatives_6 : ( ( ( rule__TwoLifelineMessage__StartBlockRightAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__Group_6_1__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:527:1: ( ( ( rule__TwoLifelineMessage__StartBlockRightAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__Group_6_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==38) ) {
                alt4=1;
            }
            else if ( (LA4_0==39) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:528:1: ( ( rule__TwoLifelineMessage__StartBlockRightAssignment_6_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:528:1: ( ( rule__TwoLifelineMessage__StartBlockRightAssignment_6_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:529:1: ( rule__TwoLifelineMessage__StartBlockRightAssignment_6_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightAssignment_6_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:530:1: ( rule__TwoLifelineMessage__StartBlockRightAssignment_6_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:530:2: rule__TwoLifelineMessage__StartBlockRightAssignment_6_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__StartBlockRightAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_61098);
                    rule__TwoLifelineMessage__StartBlockRightAssignment_6_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightAssignment_6_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:534:6: ( ( rule__TwoLifelineMessage__Group_6_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:534:6: ( ( rule__TwoLifelineMessage__Group_6_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:535:1: ( rule__TwoLifelineMessage__Group_6_1__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_6_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:536:1: ( rule__TwoLifelineMessage__Group_6_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:536:2: rule__TwoLifelineMessage__Group_6_1__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__0_in_rule__TwoLifelineMessage__Alternatives_61116);
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


    // $ANTLR start "rule__OneLifelineMessage__Alternatives_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:545:1: rule__OneLifelineMessage__Alternatives_2 : ( ( 'lost' ) | ( 'found' ) );
    public final void rule__OneLifelineMessage__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:549:1: ( ( 'lost' ) | ( 'found' ) )
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
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:550:1: ( 'lost' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:550:1: ( 'lost' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:551:1: 'lost'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getLostKeyword_2_0()); 
                    match(input,12,FOLLOW_12_in_rule__OneLifelineMessage__Alternatives_21150); 
                     after(grammarAccess.getOneLifelineMessageAccess().getLostKeyword_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:558:6: ( 'found' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:558:6: ( 'found' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:559:1: 'found'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getFoundKeyword_2_1()); 
                    match(input,13,FOLLOW_13_in_rule__OneLifelineMessage__Alternatives_21170); 
                     after(grammarAccess.getOneLifelineMessageAccess().getFoundKeyword_2_1()); 

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
    // $ANTLR end "rule__OneLifelineMessage__Alternatives_2"


    // $ANTLR start "rule__OneLifelineMessage__Alternatives_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:571:1: rule__OneLifelineMessage__Alternatives_4 : ( ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) ) | ( ( rule__OneLifelineMessage__Group_4_1__0 ) ) );
    public final void rule__OneLifelineMessage__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:575:1: ( ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) ) | ( ( rule__OneLifelineMessage__Group_4_1__0 ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==40) ) {
                alt6=1;
            }
            else if ( (LA6_0==41) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:576:1: ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:576:1: ( ( rule__OneLifelineMessage__StartBlockAssignment_4_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:577:1: ( rule__OneLifelineMessage__StartBlockAssignment_4_0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getStartBlockAssignment_4_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:578:1: ( rule__OneLifelineMessage__StartBlockAssignment_4_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:578:2: rule__OneLifelineMessage__StartBlockAssignment_4_0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__StartBlockAssignment_4_0_in_rule__OneLifelineMessage__Alternatives_41204);
                    rule__OneLifelineMessage__StartBlockAssignment_4_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getStartBlockAssignment_4_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:582:6: ( ( rule__OneLifelineMessage__Group_4_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:582:6: ( ( rule__OneLifelineMessage__Group_4_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:583:1: ( rule__OneLifelineMessage__Group_4_1__0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getGroup_4_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:584:1: ( rule__OneLifelineMessage__Group_4_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:584:2: rule__OneLifelineMessage__Group_4_1__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__0_in_rule__OneLifelineMessage__Alternatives_41222);
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


    // $ANTLR start "rule__TransitionType__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:593:1: rule__TransitionType__Alternatives : ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) );
    public final void rule__TransitionType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:597:1: ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) )
            int alt7=4;
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
            case 17:
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:598:1: ( ( 'async' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:598:1: ( ( 'async' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:599:1: ( 'async' )
                    {
                     before(grammarAccess.getTransitionTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:600:1: ( 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:600:3: 'async'
                    {
                    match(input,14,FOLLOW_14_in_rule__TransitionType__Alternatives1256); 

                    }

                     after(grammarAccess.getTransitionTypeAccess().getAsyncEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:605:6: ( ( 'create' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:605:6: ( ( 'create' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:606:1: ( 'create' )
                    {
                     before(grammarAccess.getTransitionTypeAccess().getCreateEnumLiteralDeclaration_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:607:1: ( 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:607:3: 'create'
                    {
                    match(input,15,FOLLOW_15_in_rule__TransitionType__Alternatives1277); 

                    }

                     after(grammarAccess.getTransitionTypeAccess().getCreateEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:612:6: ( ( 'response' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:612:6: ( ( 'response' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:613:1: ( 'response' )
                    {
                     before(grammarAccess.getTransitionTypeAccess().getResponseEnumLiteralDeclaration_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:614:1: ( 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:614:3: 'response'
                    {
                    match(input,16,FOLLOW_16_in_rule__TransitionType__Alternatives1298); 

                    }

                     after(grammarAccess.getTransitionTypeAccess().getResponseEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:619:6: ( ( 'sync' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:619:6: ( ( 'sync' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:620:1: ( 'sync' )
                    {
                     before(grammarAccess.getTransitionTypeAccess().getSyncEnumLiteralDeclaration_3()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:621:1: ( 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:621:3: 'sync'
                    {
                    match(input,17,FOLLOW_17_in_rule__TransitionType__Alternatives1319); 

                    }

                     after(grammarAccess.getTransitionTypeAccess().getSyncEnumLiteralDeclaration_3()); 

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
    // $ANTLR end "rule__TransitionType__Alternatives"


    // $ANTLR start "rule__SequenceDiagram__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:633:1: rule__SequenceDiagram__Group__0 : rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 ;
    public final void rule__SequenceDiagram__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:637:1: ( rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:638:2: rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01352);
            rule__SequenceDiagram__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01355);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:645:1: rule__SequenceDiagram__Group__0__Impl : ( () ) ;
    public final void rule__SequenceDiagram__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:649:1: ( ( () ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:650:1: ( () )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:650:1: ( () )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:651:1: ()
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramAction_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:652:1: ()
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:654:1: 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:664:1: rule__SequenceDiagram__Group__1 : rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 ;
    public final void rule__SequenceDiagram__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:668:1: ( rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:669:2: rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11413);
            rule__SequenceDiagram__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11416);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:676:1: rule__SequenceDiagram__Group__1__Impl : ( 'sequenceDiagram' ) ;
    public final void rule__SequenceDiagram__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:680:1: ( ( 'sequenceDiagram' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:681:1: ( 'sequenceDiagram' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:681:1: ( 'sequenceDiagram' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:682:1: 'sequenceDiagram'
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramKeyword_1()); 
            match(input,18,FOLLOW_18_in_rule__SequenceDiagram__Group__1__Impl1444); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:695:1: rule__SequenceDiagram__Group__2 : rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 ;
    public final void rule__SequenceDiagram__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:699:1: ( rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:700:2: rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21475);
            rule__SequenceDiagram__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21478);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:707:1: rule__SequenceDiagram__Group__2__Impl : ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) ;
    public final void rule__SequenceDiagram__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:711:1: ( ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:712:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:712:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:713:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:714:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:714:2: rule__SequenceDiagram__DiagramNameAssignment_2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1505);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:724:1: rule__SequenceDiagram__Group__3 : rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 ;
    public final void rule__SequenceDiagram__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:728:1: ( rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:729:2: rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31535);
            rule__SequenceDiagram__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31538);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:736:1: rule__SequenceDiagram__Group__3__Impl : ( ( rule__SequenceDiagram__Group_3__0 )? ) ;
    public final void rule__SequenceDiagram__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:740:1: ( ( ( rule__SequenceDiagram__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:741:1: ( ( rule__SequenceDiagram__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:741:1: ( ( rule__SequenceDiagram__Group_3__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:742:1: ( rule__SequenceDiagram__Group_3__0 )?
            {
             before(grammarAccess.getSequenceDiagramAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:743:1: ( rule__SequenceDiagram__Group_3__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:743:2: rule__SequenceDiagram__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__0_in_rule__SequenceDiagram__Group__3__Impl1565);
                    rule__SequenceDiagram__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSequenceDiagramAccess().getGroup_3()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:753:1: rule__SequenceDiagram__Group__4 : rule__SequenceDiagram__Group__4__Impl rule__SequenceDiagram__Group__5 ;
    public final void rule__SequenceDiagram__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:757:1: ( rule__SequenceDiagram__Group__4__Impl rule__SequenceDiagram__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:758:2: rule__SequenceDiagram__Group__4__Impl rule__SequenceDiagram__Group__5
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41596);
            rule__SequenceDiagram__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__5_in_rule__SequenceDiagram__Group__41599);
            rule__SequenceDiagram__Group__5();

            state._fsp--;


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:765:1: rule__SequenceDiagram__Group__4__Impl : ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* ) ;
    public final void rule__SequenceDiagram__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:769:1: ( ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:770:1: ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:770:1: ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:771:1: ( rule__SequenceDiagram__LifelinesAssignment_4 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:772:1: ( rule__SequenceDiagram__LifelinesAssignment_4 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==25) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:772:2: rule__SequenceDiagram__LifelinesAssignment_4
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__LifelinesAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1626);
            	    rule__SequenceDiagram__LifelinesAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getSequenceDiagramAccess().getLifelinesAssignment_4()); 

            }


            }

        }
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


    // $ANTLR start "rule__SequenceDiagram__Group__5"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:782:1: rule__SequenceDiagram__Group__5 : rule__SequenceDiagram__Group__5__Impl ;
    public final void rule__SequenceDiagram__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:786:1: ( rule__SequenceDiagram__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:787:2: rule__SequenceDiagram__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__5__Impl_in_rule__SequenceDiagram__Group__51657);
            rule__SequenceDiagram__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__5"


    // $ANTLR start "rule__SequenceDiagram__Group__5__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:793:1: rule__SequenceDiagram__Group__5__Impl : ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* ) ;
    public final void rule__SequenceDiagram__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:797:1: ( ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:798:1: ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:798:1: ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:799:1: ( rule__SequenceDiagram__InteractionsAssignment_5 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsAssignment_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:800:1: ( rule__SequenceDiagram__InteractionsAssignment_5 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_ID||LA10_0==31||LA10_0==33) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:800:2: rule__SequenceDiagram__InteractionsAssignment_5
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__InteractionsAssignment_5_in_rule__SequenceDiagram__Group__5__Impl1684);
            	    rule__SequenceDiagram__InteractionsAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getSequenceDiagramAccess().getInteractionsAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group__5__Impl"


    // $ANTLR start "rule__SequenceDiagram__Group_3__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:822:1: rule__SequenceDiagram__Group_3__0 : rule__SequenceDiagram__Group_3__0__Impl rule__SequenceDiagram__Group_3__1 ;
    public final void rule__SequenceDiagram__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:826:1: ( rule__SequenceDiagram__Group_3__0__Impl rule__SequenceDiagram__Group_3__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:827:2: rule__SequenceDiagram__Group_3__0__Impl rule__SequenceDiagram__Group_3__1
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__0__Impl_in_rule__SequenceDiagram__Group_3__01727);
            rule__SequenceDiagram__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__1_in_rule__SequenceDiagram__Group_3__01730);
            rule__SequenceDiagram__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__0"


    // $ANTLR start "rule__SequenceDiagram__Group_3__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:834:1: rule__SequenceDiagram__Group_3__0__Impl : ( '{' ) ;
    public final void rule__SequenceDiagram__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:838:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:839:1: ( '{' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:839:1: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:840:1: '{'
            {
             before(grammarAccess.getSequenceDiagramAccess().getLeftCurlyBracketKeyword_3_0()); 
            match(input,19,FOLLOW_19_in_rule__SequenceDiagram__Group_3__0__Impl1758); 
             after(grammarAccess.getSequenceDiagramAccess().getLeftCurlyBracketKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__0__Impl"


    // $ANTLR start "rule__SequenceDiagram__Group_3__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:853:1: rule__SequenceDiagram__Group_3__1 : rule__SequenceDiagram__Group_3__1__Impl rule__SequenceDiagram__Group_3__2 ;
    public final void rule__SequenceDiagram__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:857:1: ( rule__SequenceDiagram__Group_3__1__Impl rule__SequenceDiagram__Group_3__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:858:2: rule__SequenceDiagram__Group_3__1__Impl rule__SequenceDiagram__Group_3__2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__1__Impl_in_rule__SequenceDiagram__Group_3__11789);
            rule__SequenceDiagram__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__2_in_rule__SequenceDiagram__Group_3__11792);
            rule__SequenceDiagram__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__1"


    // $ANTLR start "rule__SequenceDiagram__Group_3__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:865:1: rule__SequenceDiagram__Group_3__1__Impl : ( ( rule__SequenceDiagram__LocalsAssignment_3_1 )* ) ;
    public final void rule__SequenceDiagram__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:869:1: ( ( ( rule__SequenceDiagram__LocalsAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:870:1: ( ( rule__SequenceDiagram__LocalsAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:870:1: ( ( rule__SequenceDiagram__LocalsAssignment_3_1 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:871:1: ( rule__SequenceDiagram__LocalsAssignment_3_1 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getLocalsAssignment_3_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:872:1: ( rule__SequenceDiagram__LocalsAssignment_3_1 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=21 && LA11_0<=24)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:872:2: rule__SequenceDiagram__LocalsAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__LocalsAssignment_3_1_in_rule__SequenceDiagram__Group_3__1__Impl1819);
            	    rule__SequenceDiagram__LocalsAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getSequenceDiagramAccess().getLocalsAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__1__Impl"


    // $ANTLR start "rule__SequenceDiagram__Group_3__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:882:1: rule__SequenceDiagram__Group_3__2 : rule__SequenceDiagram__Group_3__2__Impl ;
    public final void rule__SequenceDiagram__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:886:1: ( rule__SequenceDiagram__Group_3__2__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:887:2: rule__SequenceDiagram__Group_3__2__Impl
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__2__Impl_in_rule__SequenceDiagram__Group_3__21850);
            rule__SequenceDiagram__Group_3__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__2"


    // $ANTLR start "rule__SequenceDiagram__Group_3__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:893:1: rule__SequenceDiagram__Group_3__2__Impl : ( '}' ) ;
    public final void rule__SequenceDiagram__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:897:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:898:1: ( '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:898:1: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:899:1: '}'
            {
             before(grammarAccess.getSequenceDiagramAccess().getRightCurlyBracketKeyword_3_2()); 
            match(input,20,FOLLOW_20_in_rule__SequenceDiagram__Group_3__2__Impl1878); 
             after(grammarAccess.getSequenceDiagramAccess().getRightCurlyBracketKeyword_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__2__Impl"


    // $ANTLR start "rule__LocalVariable__Group_0__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:918:1: rule__LocalVariable__Group_0__0 : rule__LocalVariable__Group_0__0__Impl rule__LocalVariable__Group_0__1 ;
    public final void rule__LocalVariable__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:922:1: ( rule__LocalVariable__Group_0__0__Impl rule__LocalVariable__Group_0__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:923:2: rule__LocalVariable__Group_0__0__Impl rule__LocalVariable__Group_0__1
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_0__0__Impl_in_rule__LocalVariable__Group_0__01915);
            rule__LocalVariable__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalVariable__Group_0__1_in_rule__LocalVariable__Group_0__01918);
            rule__LocalVariable__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_0__0"


    // $ANTLR start "rule__LocalVariable__Group_0__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:930:1: rule__LocalVariable__Group_0__0__Impl : ( 'Char' ) ;
    public final void rule__LocalVariable__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:934:1: ( ( 'Char' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:935:1: ( 'Char' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:935:1: ( 'Char' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:936:1: 'Char'
            {
             before(grammarAccess.getLocalVariableAccess().getCharKeyword_0_0()); 
            match(input,21,FOLLOW_21_in_rule__LocalVariable__Group_0__0__Impl1946); 
             after(grammarAccess.getLocalVariableAccess().getCharKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_0__0__Impl"


    // $ANTLR start "rule__LocalVariable__Group_0__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:949:1: rule__LocalVariable__Group_0__1 : rule__LocalVariable__Group_0__1__Impl ;
    public final void rule__LocalVariable__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:953:1: ( rule__LocalVariable__Group_0__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:954:2: rule__LocalVariable__Group_0__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_0__1__Impl_in_rule__LocalVariable__Group_0__11977);
            rule__LocalVariable__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_0__1"


    // $ANTLR start "rule__LocalVariable__Group_0__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:960:1: rule__LocalVariable__Group_0__1__Impl : ( ( rule__LocalVariable__NameAssignment_0_1 ) ) ;
    public final void rule__LocalVariable__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:964:1: ( ( ( rule__LocalVariable__NameAssignment_0_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:965:1: ( ( rule__LocalVariable__NameAssignment_0_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:965:1: ( ( rule__LocalVariable__NameAssignment_0_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:966:1: ( rule__LocalVariable__NameAssignment_0_1 )
            {
             before(grammarAccess.getLocalVariableAccess().getNameAssignment_0_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:967:1: ( rule__LocalVariable__NameAssignment_0_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:967:2: rule__LocalVariable__NameAssignment_0_1
            {
            pushFollow(FOLLOW_rule__LocalVariable__NameAssignment_0_1_in_rule__LocalVariable__Group_0__1__Impl2004);
            rule__LocalVariable__NameAssignment_0_1();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getNameAssignment_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_0__1__Impl"


    // $ANTLR start "rule__LocalVariable__Group_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:981:1: rule__LocalVariable__Group_1__0 : rule__LocalVariable__Group_1__0__Impl rule__LocalVariable__Group_1__1 ;
    public final void rule__LocalVariable__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:985:1: ( rule__LocalVariable__Group_1__0__Impl rule__LocalVariable__Group_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:986:2: rule__LocalVariable__Group_1__0__Impl rule__LocalVariable__Group_1__1
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_1__0__Impl_in_rule__LocalVariable__Group_1__02038);
            rule__LocalVariable__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalVariable__Group_1__1_in_rule__LocalVariable__Group_1__02041);
            rule__LocalVariable__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_1__0"


    // $ANTLR start "rule__LocalVariable__Group_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:993:1: rule__LocalVariable__Group_1__0__Impl : ( 'Boolean' ) ;
    public final void rule__LocalVariable__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:997:1: ( ( 'Boolean' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:998:1: ( 'Boolean' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:998:1: ( 'Boolean' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:999:1: 'Boolean'
            {
             before(grammarAccess.getLocalVariableAccess().getBooleanKeyword_1_0()); 
            match(input,22,FOLLOW_22_in_rule__LocalVariable__Group_1__0__Impl2069); 
             after(grammarAccess.getLocalVariableAccess().getBooleanKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_1__0__Impl"


    // $ANTLR start "rule__LocalVariable__Group_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1012:1: rule__LocalVariable__Group_1__1 : rule__LocalVariable__Group_1__1__Impl ;
    public final void rule__LocalVariable__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1016:1: ( rule__LocalVariable__Group_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1017:2: rule__LocalVariable__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_1__1__Impl_in_rule__LocalVariable__Group_1__12100);
            rule__LocalVariable__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_1__1"


    // $ANTLR start "rule__LocalVariable__Group_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1023:1: rule__LocalVariable__Group_1__1__Impl : ( ( rule__LocalVariable__NameAssignment_1_1 ) ) ;
    public final void rule__LocalVariable__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1027:1: ( ( ( rule__LocalVariable__NameAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1028:1: ( ( rule__LocalVariable__NameAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1028:1: ( ( rule__LocalVariable__NameAssignment_1_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1029:1: ( rule__LocalVariable__NameAssignment_1_1 )
            {
             before(grammarAccess.getLocalVariableAccess().getNameAssignment_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1030:1: ( rule__LocalVariable__NameAssignment_1_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1030:2: rule__LocalVariable__NameAssignment_1_1
            {
            pushFollow(FOLLOW_rule__LocalVariable__NameAssignment_1_1_in_rule__LocalVariable__Group_1__1__Impl2127);
            rule__LocalVariable__NameAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getNameAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_1__1__Impl"


    // $ANTLR start "rule__LocalVariable__Group_2__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1044:1: rule__LocalVariable__Group_2__0 : rule__LocalVariable__Group_2__0__Impl rule__LocalVariable__Group_2__1 ;
    public final void rule__LocalVariable__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1048:1: ( rule__LocalVariable__Group_2__0__Impl rule__LocalVariable__Group_2__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1049:2: rule__LocalVariable__Group_2__0__Impl rule__LocalVariable__Group_2__1
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_2__0__Impl_in_rule__LocalVariable__Group_2__02161);
            rule__LocalVariable__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalVariable__Group_2__1_in_rule__LocalVariable__Group_2__02164);
            rule__LocalVariable__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_2__0"


    // $ANTLR start "rule__LocalVariable__Group_2__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1056:1: rule__LocalVariable__Group_2__0__Impl : ( 'Integer' ) ;
    public final void rule__LocalVariable__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1060:1: ( ( 'Integer' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1061:1: ( 'Integer' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1061:1: ( 'Integer' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1062:1: 'Integer'
            {
             before(grammarAccess.getLocalVariableAccess().getIntegerKeyword_2_0()); 
            match(input,23,FOLLOW_23_in_rule__LocalVariable__Group_2__0__Impl2192); 
             after(grammarAccess.getLocalVariableAccess().getIntegerKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_2__0__Impl"


    // $ANTLR start "rule__LocalVariable__Group_2__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1075:1: rule__LocalVariable__Group_2__1 : rule__LocalVariable__Group_2__1__Impl ;
    public final void rule__LocalVariable__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1079:1: ( rule__LocalVariable__Group_2__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1080:2: rule__LocalVariable__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_2__1__Impl_in_rule__LocalVariable__Group_2__12223);
            rule__LocalVariable__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_2__1"


    // $ANTLR start "rule__LocalVariable__Group_2__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1086:1: rule__LocalVariable__Group_2__1__Impl : ( ( rule__LocalVariable__NameAssignment_2_1 ) ) ;
    public final void rule__LocalVariable__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1090:1: ( ( ( rule__LocalVariable__NameAssignment_2_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1091:1: ( ( rule__LocalVariable__NameAssignment_2_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1091:1: ( ( rule__LocalVariable__NameAssignment_2_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1092:1: ( rule__LocalVariable__NameAssignment_2_1 )
            {
             before(grammarAccess.getLocalVariableAccess().getNameAssignment_2_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1093:1: ( rule__LocalVariable__NameAssignment_2_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1093:2: rule__LocalVariable__NameAssignment_2_1
            {
            pushFollow(FOLLOW_rule__LocalVariable__NameAssignment_2_1_in_rule__LocalVariable__Group_2__1__Impl2250);
            rule__LocalVariable__NameAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getNameAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_2__1__Impl"


    // $ANTLR start "rule__LocalVariable__Group_3__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1107:1: rule__LocalVariable__Group_3__0 : rule__LocalVariable__Group_3__0__Impl rule__LocalVariable__Group_3__1 ;
    public final void rule__LocalVariable__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1111:1: ( rule__LocalVariable__Group_3__0__Impl rule__LocalVariable__Group_3__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1112:2: rule__LocalVariable__Group_3__0__Impl rule__LocalVariable__Group_3__1
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_3__0__Impl_in_rule__LocalVariable__Group_3__02284);
            rule__LocalVariable__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalVariable__Group_3__1_in_rule__LocalVariable__Group_3__02287);
            rule__LocalVariable__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_3__0"


    // $ANTLR start "rule__LocalVariable__Group_3__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1119:1: rule__LocalVariable__Group_3__0__Impl : ( 'Float' ) ;
    public final void rule__LocalVariable__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1123:1: ( ( 'Float' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1124:1: ( 'Float' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1124:1: ( 'Float' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1125:1: 'Float'
            {
             before(grammarAccess.getLocalVariableAccess().getFloatKeyword_3_0()); 
            match(input,24,FOLLOW_24_in_rule__LocalVariable__Group_3__0__Impl2315); 
             after(grammarAccess.getLocalVariableAccess().getFloatKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_3__0__Impl"


    // $ANTLR start "rule__LocalVariable__Group_3__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1138:1: rule__LocalVariable__Group_3__1 : rule__LocalVariable__Group_3__1__Impl ;
    public final void rule__LocalVariable__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1142:1: ( rule__LocalVariable__Group_3__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1143:2: rule__LocalVariable__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group_3__1__Impl_in_rule__LocalVariable__Group_3__12346);
            rule__LocalVariable__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_3__1"


    // $ANTLR start "rule__LocalVariable__Group_3__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1149:1: rule__LocalVariable__Group_3__1__Impl : ( ( rule__LocalVariable__NameAssignment_3_1 ) ) ;
    public final void rule__LocalVariable__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1153:1: ( ( ( rule__LocalVariable__NameAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1154:1: ( ( rule__LocalVariable__NameAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1154:1: ( ( rule__LocalVariable__NameAssignment_3_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1155:1: ( rule__LocalVariable__NameAssignment_3_1 )
            {
             before(grammarAccess.getLocalVariableAccess().getNameAssignment_3_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1156:1: ( rule__LocalVariable__NameAssignment_3_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1156:2: rule__LocalVariable__NameAssignment_3_1
            {
            pushFollow(FOLLOW_rule__LocalVariable__NameAssignment_3_1_in_rule__LocalVariable__Group_3__1__Impl2373);
            rule__LocalVariable__NameAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getNameAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group_3__1__Impl"


    // $ANTLR start "rule__Lifeline__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1170:1: rule__Lifeline__Group__0 : rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1 ;
    public final void rule__Lifeline__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1174:1: ( rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1175:2: rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__0__Impl_in_rule__Lifeline__Group__02407);
            rule__Lifeline__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__1_in_rule__Lifeline__Group__02410);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1182:1: rule__Lifeline__Group__0__Impl : ( 'lifeline' ) ;
    public final void rule__Lifeline__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1186:1: ( ( 'lifeline' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1187:1: ( 'lifeline' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1187:1: ( 'lifeline' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1188:1: 'lifeline'
            {
             before(grammarAccess.getLifelineAccess().getLifelineKeyword_0()); 
            match(input,25,FOLLOW_25_in_rule__Lifeline__Group__0__Impl2438); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1201:1: rule__Lifeline__Group__1 : rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2 ;
    public final void rule__Lifeline__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1205:1: ( rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1206:2: rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__1__Impl_in_rule__Lifeline__Group__12469);
            rule__Lifeline__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__2_in_rule__Lifeline__Group__12472);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1213:1: rule__Lifeline__Group__1__Impl : ( ( rule__Lifeline__CaptionAssignment_1 ) ) ;
    public final void rule__Lifeline__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1217:1: ( ( ( rule__Lifeline__CaptionAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1218:1: ( ( rule__Lifeline__CaptionAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1218:1: ( ( rule__Lifeline__CaptionAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1219:1: ( rule__Lifeline__CaptionAssignment_1 )
            {
             before(grammarAccess.getLifelineAccess().getCaptionAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1220:1: ( rule__Lifeline__CaptionAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1220:2: rule__Lifeline__CaptionAssignment_1
            {
            pushFollow(FOLLOW_rule__Lifeline__CaptionAssignment_1_in_rule__Lifeline__Group__1__Impl2499);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1230:1: rule__Lifeline__Group__2 : rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3 ;
    public final void rule__Lifeline__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1234:1: ( rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1235:2: rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__2__Impl_in_rule__Lifeline__Group__22529);
            rule__Lifeline__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__3_in_rule__Lifeline__Group__22532);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1242:1: rule__Lifeline__Group__2__Impl : ( 'as' ) ;
    public final void rule__Lifeline__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1246:1: ( ( 'as' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1247:1: ( 'as' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1247:1: ( 'as' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1248:1: 'as'
            {
             before(grammarAccess.getLifelineAccess().getAsKeyword_2()); 
            match(input,26,FOLLOW_26_in_rule__Lifeline__Group__2__Impl2560); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1261:1: rule__Lifeline__Group__3 : rule__Lifeline__Group__3__Impl ;
    public final void rule__Lifeline__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1265:1: ( rule__Lifeline__Group__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1266:2: rule__Lifeline__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__3__Impl_in_rule__Lifeline__Group__32591);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1272:1: rule__Lifeline__Group__3__Impl : ( ( rule__Lifeline__NameAssignment_3 ) ) ;
    public final void rule__Lifeline__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1276:1: ( ( ( rule__Lifeline__NameAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1277:1: ( ( rule__Lifeline__NameAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1277:1: ( ( rule__Lifeline__NameAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1278:1: ( rule__Lifeline__NameAssignment_3 )
            {
             before(grammarAccess.getLifelineAccess().getNameAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1279:1: ( rule__Lifeline__NameAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1279:2: rule__Lifeline__NameAssignment_3
            {
            pushFollow(FOLLOW_rule__Lifeline__NameAssignment_3_in_rule__Lifeline__Group__3__Impl2618);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1297:1: rule__TwoLifelineMessage__Group__0 : rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 ;
    public final void rule__TwoLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1301:1: ( rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1302:2: rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__02656);
            rule__TwoLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__02659);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1309:1: rule__TwoLifelineMessage__Group__0__Impl : ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1313:1: ( ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1314:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1314:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1315:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1316:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1316:2: rule__TwoLifelineMessage__SourceLifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl2686);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1326:1: rule__TwoLifelineMessage__Group__1 : rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 ;
    public final void rule__TwoLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1330:1: ( rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1331:2: rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__12716);
            rule__TwoLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__12719);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1338:1: rule__TwoLifelineMessage__Group__1__Impl : ( ( rule__TwoLifelineMessage__TransitionTypeAssignment_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1342:1: ( ( ( rule__TwoLifelineMessage__TransitionTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1343:1: ( ( rule__TwoLifelineMessage__TransitionTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1343:1: ( ( rule__TwoLifelineMessage__TransitionTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1344:1: ( rule__TwoLifelineMessage__TransitionTypeAssignment_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTransitionTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1345:1: ( rule__TwoLifelineMessage__TransitionTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1345:2: rule__TwoLifelineMessage__TransitionTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TransitionTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl2746);
            rule__TwoLifelineMessage__TransitionTypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getTransitionTypeAssignment_1()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1355:1: rule__TwoLifelineMessage__Group__2 : rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 ;
    public final void rule__TwoLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1359:1: ( rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1360:2: rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__22776);
            rule__TwoLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__22779);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1367:1: rule__TwoLifelineMessage__Group__2__Impl : ( ( rule__TwoLifelineMessage__CaptionAssignment_2 ) ) ;
    public final void rule__TwoLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1371:1: ( ( ( rule__TwoLifelineMessage__CaptionAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1372:1: ( ( rule__TwoLifelineMessage__CaptionAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1372:1: ( ( rule__TwoLifelineMessage__CaptionAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1373:1: ( rule__TwoLifelineMessage__CaptionAssignment_2 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getCaptionAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1374:1: ( rule__TwoLifelineMessage__CaptionAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1374:2: rule__TwoLifelineMessage__CaptionAssignment_2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__CaptionAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl2806);
            rule__TwoLifelineMessage__CaptionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getCaptionAssignment_2()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1384:1: rule__TwoLifelineMessage__Group__3 : rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 ;
    public final void rule__TwoLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1388:1: ( rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1389:2: rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__32836);
            rule__TwoLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__32839);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1396:1: rule__TwoLifelineMessage__Group__3__Impl : ( 'to' ) ;
    public final void rule__TwoLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1400:1: ( ( 'to' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1401:1: ( 'to' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1401:1: ( 'to' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1402:1: 'to'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3()); 
            match(input,27,FOLLOW_27_in_rule__TwoLifelineMessage__Group__3__Impl2867); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1415:1: rule__TwoLifelineMessage__Group__4 : rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 ;
    public final void rule__TwoLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1419:1: ( rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1420:2: rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__42898);
            rule__TwoLifelineMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__42901);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1427:1: rule__TwoLifelineMessage__Group__4__Impl : ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) ;
    public final void rule__TwoLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1431:1: ( ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1432:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1432:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1433:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1434:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1434:2: rule__TwoLifelineMessage__TargetLifelineAssignment_4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl2928);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1444:1: rule__TwoLifelineMessage__Group__5 : rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 ;
    public final void rule__TwoLifelineMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1448:1: ( rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1449:2: rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__52958);
            rule__TwoLifelineMessage__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__52961);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1456:1: rule__TwoLifelineMessage__Group__5__Impl : ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) ;
    public final void rule__TwoLifelineMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1460:1: ( ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1461:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1461:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1462:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1463:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=36 && LA12_0<=37)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1463:2: rule__TwoLifelineMessage__Alternatives_5
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2988);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1473:1: rule__TwoLifelineMessage__Group__6 : rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 ;
    public final void rule__TwoLifelineMessage__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1477:1: ( rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1478:2: rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__63019);
            rule__TwoLifelineMessage__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__63022);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1485:1: rule__TwoLifelineMessage__Group__6__Impl : ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) ;
    public final void rule__TwoLifelineMessage__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1489:1: ( ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1490:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1490:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1491:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_6()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1492:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=38 && LA13_0<=39)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1492:2: rule__TwoLifelineMessage__Alternatives_6
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl3049);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1502:1: rule__TwoLifelineMessage__Group__7 : rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 ;
    public final void rule__TwoLifelineMessage__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1506:1: ( rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1507:2: rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__73080);
            rule__TwoLifelineMessage__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__73083);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1514:1: rule__TwoLifelineMessage__Group__7__Impl : ( ( rule__TwoLifelineMessage__Group_7__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1518:1: ( ( ( rule__TwoLifelineMessage__Group_7__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1519:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1519:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1520:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1521:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==28) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1521:2: rule__TwoLifelineMessage__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl3110);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1531:1: rule__TwoLifelineMessage__Group__8 : rule__TwoLifelineMessage__Group__8__Impl ;
    public final void rule__TwoLifelineMessage__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1535:1: ( rule__TwoLifelineMessage__Group__8__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1536:2: rule__TwoLifelineMessage__Group__8__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__83141);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1542:1: rule__TwoLifelineMessage__Group__8__Impl : ( ( rule__TwoLifelineMessage__Group_8__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1546:1: ( ( ( rule__TwoLifelineMessage__Group_8__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1547:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1547:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1548:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_8()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1549:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==29) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1549:2: rule__TwoLifelineMessage__Group_8__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl3168);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1577:1: rule__TwoLifelineMessage__Group_5_1__0 : rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1 ;
    public final void rule__TwoLifelineMessage__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1581:1: ( rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1582:2: rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__0__Impl_in_rule__TwoLifelineMessage__Group_5_1__03217);
            rule__TwoLifelineMessage__Group_5_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__1_in_rule__TwoLifelineMessage__Group_5_1__03220);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1589:1: rule__TwoLifelineMessage__Group_5_1__0__Impl : ( ( rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1593:1: ( ( ( rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1594:1: ( ( rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1594:1: ( ( rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1595:1: ( rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftAssignment_5_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1596:1: ( rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1596:2: rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0_in_rule__TwoLifelineMessage__Group_5_1__0__Impl3247);
            rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftAssignment_5_1_0()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1606:1: rule__TwoLifelineMessage__Group_5_1__1 : rule__TwoLifelineMessage__Group_5_1__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1610:1: ( rule__TwoLifelineMessage__Group_5_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1611:2: rule__TwoLifelineMessage__Group_5_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__1__Impl_in_rule__TwoLifelineMessage__Group_5_1__13277);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1617:1: rule__TwoLifelineMessage__Group_5_1__1__Impl : ( ( rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1621:1: ( ( ( rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1622:1: ( ( rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1622:1: ( ( rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1623:1: ( rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftCountAssignment_5_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1624:1: ( rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT_GREATER_ZERO) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1624:2: rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1_in_rule__TwoLifelineMessage__Group_5_1__1__Impl3304);
                    rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftCountAssignment_5_1_1()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1638:1: rule__TwoLifelineMessage__Group_6_1__0 : rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1 ;
    public final void rule__TwoLifelineMessage__Group_6_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1642:1: ( rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1643:2: rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__0__Impl_in_rule__TwoLifelineMessage__Group_6_1__03339);
            rule__TwoLifelineMessage__Group_6_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__1_in_rule__TwoLifelineMessage__Group_6_1__03342);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1650:1: rule__TwoLifelineMessage__Group_6_1__0__Impl : ( ( rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_6_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1654:1: ( ( ( rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1655:1: ( ( rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1655:1: ( ( rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1656:1: ( rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightAssignment_6_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1657:1: ( rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1657:2: rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0_in_rule__TwoLifelineMessage__Group_6_1__0__Impl3369);
            rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0();

            state._fsp--;


            }

             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightAssignment_6_1_0()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1667:1: rule__TwoLifelineMessage__Group_6_1__1 : rule__TwoLifelineMessage__Group_6_1__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_6_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1671:1: ( rule__TwoLifelineMessage__Group_6_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1672:2: rule__TwoLifelineMessage__Group_6_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__1__Impl_in_rule__TwoLifelineMessage__Group_6_1__13399);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1678:1: rule__TwoLifelineMessage__Group_6_1__1__Impl : ( ( rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_6_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1682:1: ( ( ( rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1683:1: ( ( rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1683:1: ( ( rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1684:1: ( rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightCountAssignment_6_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1685:1: ( rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_INT_GREATER_ZERO) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1685:2: rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1_in_rule__TwoLifelineMessage__Group_6_1__1__Impl3426);
                    rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightCountAssignment_6_1_1()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1699:1: rule__TwoLifelineMessage__Group_7__0 : rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 ;
    public final void rule__TwoLifelineMessage__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1703:1: ( rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1704:2: rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__03461);
            rule__TwoLifelineMessage__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__03464);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1711:1: rule__TwoLifelineMessage__Group_7__0__Impl : ( 'sourceNote' ) ;
    public final void rule__TwoLifelineMessage__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1715:1: ( ( 'sourceNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1716:1: ( 'sourceNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1716:1: ( 'sourceNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1717:1: 'sourceNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0()); 
            match(input,28,FOLLOW_28_in_rule__TwoLifelineMessage__Group_7__0__Impl3492); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1730:1: rule__TwoLifelineMessage__Group_7__1 : rule__TwoLifelineMessage__Group_7__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1734:1: ( rule__TwoLifelineMessage__Group_7__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1735:2: rule__TwoLifelineMessage__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__13523);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1741:1: rule__TwoLifelineMessage__Group_7__1__Impl : ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1745:1: ( ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1746:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1746:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1747:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteAssignment_7_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1748:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1748:2: rule__TwoLifelineMessage__SourceNoteAssignment_7_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl3550);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1762:1: rule__TwoLifelineMessage__Group_8__0 : rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 ;
    public final void rule__TwoLifelineMessage__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1766:1: ( rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1767:2: rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__03584);
            rule__TwoLifelineMessage__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__03587);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1774:1: rule__TwoLifelineMessage__Group_8__0__Impl : ( 'targetNote' ) ;
    public final void rule__TwoLifelineMessage__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1778:1: ( ( 'targetNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1779:1: ( 'targetNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1779:1: ( 'targetNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1780:1: 'targetNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0()); 
            match(input,29,FOLLOW_29_in_rule__TwoLifelineMessage__Group_8__0__Impl3615); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1793:1: rule__TwoLifelineMessage__Group_8__1 : rule__TwoLifelineMessage__Group_8__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1797:1: ( rule__TwoLifelineMessage__Group_8__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1798:2: rule__TwoLifelineMessage__Group_8__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__13646);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1804:1: rule__TwoLifelineMessage__Group_8__1__Impl : ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1808:1: ( ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1809:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1809:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1810:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteAssignment_8_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1811:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1811:2: rule__TwoLifelineMessage__TargetNoteAssignment_8_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl3673);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1825:1: rule__OneLifelineMessage__Group__0 : rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 ;
    public final void rule__OneLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1829:1: ( rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1830:2: rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__03707);
            rule__OneLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__03710);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1837:1: rule__OneLifelineMessage__Group__0__Impl : ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1841:1: ( ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1842:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1842:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1843:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1844:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1844:2: rule__OneLifelineMessage__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl3737);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1854:1: rule__OneLifelineMessage__Group__1 : rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 ;
    public final void rule__OneLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1858:1: ( rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1859:2: rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__13767);
            rule__OneLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__13770);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1866:1: rule__OneLifelineMessage__Group__1__Impl : ( ( rule__OneLifelineMessage__TransitionTypeAssignment_1 ) ) ;
    public final void rule__OneLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1870:1: ( ( ( rule__OneLifelineMessage__TransitionTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1871:1: ( ( rule__OneLifelineMessage__TransitionTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1871:1: ( ( rule__OneLifelineMessage__TransitionTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1872:1: ( rule__OneLifelineMessage__TransitionTypeAssignment_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getTransitionTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1873:1: ( rule__OneLifelineMessage__TransitionTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1873:2: rule__OneLifelineMessage__TransitionTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__TransitionTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl3797);
            rule__OneLifelineMessage__TransitionTypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getTransitionTypeAssignment_1()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1883:1: rule__OneLifelineMessage__Group__2 : rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 ;
    public final void rule__OneLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1887:1: ( rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1888:2: rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__23827);
            rule__OneLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__23830);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1895:1: rule__OneLifelineMessage__Group__2__Impl : ( ( rule__OneLifelineMessage__Alternatives_2 ) ) ;
    public final void rule__OneLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1899:1: ( ( ( rule__OneLifelineMessage__Alternatives_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1900:1: ( ( rule__OneLifelineMessage__Alternatives_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1900:1: ( ( rule__OneLifelineMessage__Alternatives_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1901:1: ( rule__OneLifelineMessage__Alternatives_2 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getAlternatives_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1902:1: ( rule__OneLifelineMessage__Alternatives_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1902:2: rule__OneLifelineMessage__Alternatives_2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Alternatives_2_in_rule__OneLifelineMessage__Group__2__Impl3857);
            rule__OneLifelineMessage__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getAlternatives_2()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1912:1: rule__OneLifelineMessage__Group__3 : rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 ;
    public final void rule__OneLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1916:1: ( rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1917:2: rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__33887);
            rule__OneLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__33890);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1924:1: rule__OneLifelineMessage__Group__3__Impl : ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) ) ;
    public final void rule__OneLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1928:1: ( ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1929:1: ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1929:1: ( ( rule__OneLifelineMessage__CaptionAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1930:1: ( rule__OneLifelineMessage__CaptionAssignment_3 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getCaptionAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1931:1: ( rule__OneLifelineMessage__CaptionAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1931:2: rule__OneLifelineMessage__CaptionAssignment_3
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__CaptionAssignment_3_in_rule__OneLifelineMessage__Group__3__Impl3917);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1941:1: rule__OneLifelineMessage__Group__4 : rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5 ;
    public final void rule__OneLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1945:1: ( rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1946:2: rule__OneLifelineMessage__Group__4__Impl rule__OneLifelineMessage__Group__5
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__43947);
            rule__OneLifelineMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__5_in_rule__OneLifelineMessage__Group__43950);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1953:1: rule__OneLifelineMessage__Group__4__Impl : ( ( rule__OneLifelineMessage__Alternatives_4 )? ) ;
    public final void rule__OneLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1957:1: ( ( ( rule__OneLifelineMessage__Alternatives_4 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1958:1: ( ( rule__OneLifelineMessage__Alternatives_4 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1958:1: ( ( rule__OneLifelineMessage__Alternatives_4 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1959:1: ( rule__OneLifelineMessage__Alternatives_4 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getAlternatives_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1960:1: ( rule__OneLifelineMessage__Alternatives_4 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=40 && LA18_0<=41)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1960:2: rule__OneLifelineMessage__Alternatives_4
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Alternatives_4_in_rule__OneLifelineMessage__Group__4__Impl3977);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1970:1: rule__OneLifelineMessage__Group__5 : rule__OneLifelineMessage__Group__5__Impl ;
    public final void rule__OneLifelineMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1974:1: ( rule__OneLifelineMessage__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1975:2: rule__OneLifelineMessage__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__5__Impl_in_rule__OneLifelineMessage__Group__54008);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1981:1: rule__OneLifelineMessage__Group__5__Impl : ( ( rule__OneLifelineMessage__Group_5__0 )? ) ;
    public final void rule__OneLifelineMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1985:1: ( ( ( rule__OneLifelineMessage__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1986:1: ( ( rule__OneLifelineMessage__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1986:1: ( ( rule__OneLifelineMessage__Group_5__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1987:1: ( rule__OneLifelineMessage__Group_5__0 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1988:1: ( rule__OneLifelineMessage__Group_5__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==30) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1988:2: rule__OneLifelineMessage__Group_5__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__0_in_rule__OneLifelineMessage__Group__5__Impl4035);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2010:1: rule__OneLifelineMessage__Group_4_1__0 : rule__OneLifelineMessage__Group_4_1__0__Impl rule__OneLifelineMessage__Group_4_1__1 ;
    public final void rule__OneLifelineMessage__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2014:1: ( rule__OneLifelineMessage__Group_4_1__0__Impl rule__OneLifelineMessage__Group_4_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2015:2: rule__OneLifelineMessage__Group_4_1__0__Impl rule__OneLifelineMessage__Group_4_1__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__0__Impl_in_rule__OneLifelineMessage__Group_4_1__04078);
            rule__OneLifelineMessage__Group_4_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__1_in_rule__OneLifelineMessage__Group_4_1__04081);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2022:1: rule__OneLifelineMessage__Group_4_1__0__Impl : ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) ) ;
    public final void rule__OneLifelineMessage__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2026:1: ( ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2027:1: ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2027:1: ( ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2028:1: ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockAssignment_4_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2029:1: ( rule__OneLifelineMessage__EndBlockAssignment_4_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2029:2: rule__OneLifelineMessage__EndBlockAssignment_4_1_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__EndBlockAssignment_4_1_0_in_rule__OneLifelineMessage__Group_4_1__0__Impl4108);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2039:1: rule__OneLifelineMessage__Group_4_1__1 : rule__OneLifelineMessage__Group_4_1__1__Impl ;
    public final void rule__OneLifelineMessage__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2043:1: ( rule__OneLifelineMessage__Group_4_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2044:2: rule__OneLifelineMessage__Group_4_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4_1__1__Impl_in_rule__OneLifelineMessage__Group_4_1__14138);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2050:1: rule__OneLifelineMessage__Group_4_1__1__Impl : ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? ) ;
    public final void rule__OneLifelineMessage__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2054:1: ( ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2055:1: ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2055:1: ( ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2056:1: ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountAssignment_4_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2057:1: ( rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_INT_GREATER_ZERO) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2057:2: rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1_in_rule__OneLifelineMessage__Group_4_1__1__Impl4165);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2071:1: rule__OneLifelineMessage__Group_5__0 : rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1 ;
    public final void rule__OneLifelineMessage__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2075:1: ( rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2076:2: rule__OneLifelineMessage__Group_5__0__Impl rule__OneLifelineMessage__Group_5__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__0__Impl_in_rule__OneLifelineMessage__Group_5__04200);
            rule__OneLifelineMessage__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__1_in_rule__OneLifelineMessage__Group_5__04203);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2083:1: rule__OneLifelineMessage__Group_5__0__Impl : ( 'note' ) ;
    public final void rule__OneLifelineMessage__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2087:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2088:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2088:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2089:1: 'note'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0()); 
            match(input,30,FOLLOW_30_in_rule__OneLifelineMessage__Group_5__0__Impl4231); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2102:1: rule__OneLifelineMessage__Group_5__1 : rule__OneLifelineMessage__Group_5__1__Impl ;
    public final void rule__OneLifelineMessage__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2106:1: ( rule__OneLifelineMessage__Group_5__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2107:2: rule__OneLifelineMessage__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_5__1__Impl_in_rule__OneLifelineMessage__Group_5__14262);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2113:1: rule__OneLifelineMessage__Group_5__1__Impl : ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) ) ;
    public final void rule__OneLifelineMessage__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2117:1: ( ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2118:1: ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2118:1: ( ( rule__OneLifelineMessage__NoteAssignment_5_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2119:1: ( rule__OneLifelineMessage__NoteAssignment_5_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteAssignment_5_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2120:1: ( rule__OneLifelineMessage__NoteAssignment_5_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2120:2: rule__OneLifelineMessage__NoteAssignment_5_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__NoteAssignment_5_1_in_rule__OneLifelineMessage__Group_5__1__Impl4289);
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


    // $ANTLR start "rule__OneLifelineEndBlock__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2134:1: rule__OneLifelineEndBlock__Group__0 : rule__OneLifelineEndBlock__Group__0__Impl rule__OneLifelineEndBlock__Group__1 ;
    public final void rule__OneLifelineEndBlock__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2138:1: ( rule__OneLifelineEndBlock__Group__0__Impl rule__OneLifelineEndBlock__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2139:2: rule__OneLifelineEndBlock__Group__0__Impl rule__OneLifelineEndBlock__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__0__Impl_in_rule__OneLifelineEndBlock__Group__04323);
            rule__OneLifelineEndBlock__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__1_in_rule__OneLifelineEndBlock__Group__04326);
            rule__OneLifelineEndBlock__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__Group__0"


    // $ANTLR start "rule__OneLifelineEndBlock__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2146:1: rule__OneLifelineEndBlock__Group__0__Impl : ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineEndBlock__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2150:1: ( ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2151:1: ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2151:1: ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2152:1: ( rule__OneLifelineEndBlock__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2153:1: ( rule__OneLifelineEndBlock__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2153:2: rule__OneLifelineEndBlock__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__LifelineAssignment_0_in_rule__OneLifelineEndBlock__Group__0__Impl4353);
            rule__OneLifelineEndBlock__LifelineAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineEndBlockAccess().getLifelineAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__Group__0__Impl"


    // $ANTLR start "rule__OneLifelineEndBlock__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2163:1: rule__OneLifelineEndBlock__Group__1 : rule__OneLifelineEndBlock__Group__1__Impl rule__OneLifelineEndBlock__Group__2 ;
    public final void rule__OneLifelineEndBlock__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2167:1: ( rule__OneLifelineEndBlock__Group__1__Impl rule__OneLifelineEndBlock__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2168:2: rule__OneLifelineEndBlock__Group__1__Impl rule__OneLifelineEndBlock__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__1__Impl_in_rule__OneLifelineEndBlock__Group__14383);
            rule__OneLifelineEndBlock__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__2_in_rule__OneLifelineEndBlock__Group__14386);
            rule__OneLifelineEndBlock__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__Group__1"


    // $ANTLR start "rule__OneLifelineEndBlock__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2175:1: rule__OneLifelineEndBlock__Group__1__Impl : ( ( rule__OneLifelineEndBlock__EndBlockAssignment_1 ) ) ;
    public final void rule__OneLifelineEndBlock__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2179:1: ( ( ( rule__OneLifelineEndBlock__EndBlockAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2180:1: ( ( rule__OneLifelineEndBlock__EndBlockAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2180:1: ( ( rule__OneLifelineEndBlock__EndBlockAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2181:1: ( rule__OneLifelineEndBlock__EndBlockAssignment_1 )
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2182:1: ( rule__OneLifelineEndBlock__EndBlockAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2182:2: rule__OneLifelineEndBlock__EndBlockAssignment_1
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__EndBlockAssignment_1_in_rule__OneLifelineEndBlock__Group__1__Impl4413);
            rule__OneLifelineEndBlock__EndBlockAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__Group__1__Impl"


    // $ANTLR start "rule__OneLifelineEndBlock__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2192:1: rule__OneLifelineEndBlock__Group__2 : rule__OneLifelineEndBlock__Group__2__Impl ;
    public final void rule__OneLifelineEndBlock__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2196:1: ( rule__OneLifelineEndBlock__Group__2__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2197:2: rule__OneLifelineEndBlock__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__2__Impl_in_rule__OneLifelineEndBlock__Group__24443);
            rule__OneLifelineEndBlock__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__Group__2"


    // $ANTLR start "rule__OneLifelineEndBlock__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2203:1: rule__OneLifelineEndBlock__Group__2__Impl : ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? ) ;
    public final void rule__OneLifelineEndBlock__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2207:1: ( ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2208:1: ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2208:1: ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2209:1: ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )?
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2210:1: ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==RULE_INT_GREATER_ZERO) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2210:2: rule__OneLifelineEndBlock__EndBlockCountAssignment_2
                    {
                    pushFollow(FOLLOW_rule__OneLifelineEndBlock__EndBlockCountAssignment_2_in_rule__OneLifelineEndBlock__Group__2__Impl4470);
                    rule__OneLifelineEndBlock__EndBlockCountAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__Group__2__Impl"


    // $ANTLR start "rule__OneLifelineNote__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2226:1: rule__OneLifelineNote__Group__0 : rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 ;
    public final void rule__OneLifelineNote__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2230:1: ( rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2231:2: rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__04507);
            rule__OneLifelineNote__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__04510);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2238:1: rule__OneLifelineNote__Group__0__Impl : ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineNote__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2242:1: ( ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2243:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2243:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2244:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2245:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2245:2: rule__OneLifelineNote__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl4537);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2255:1: rule__OneLifelineNote__Group__1 : rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 ;
    public final void rule__OneLifelineNote__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2259:1: ( rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2260:2: rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__14567);
            rule__OneLifelineNote__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__14570);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2267:1: rule__OneLifelineNote__Group__1__Impl : ( 'note' ) ;
    public final void rule__OneLifelineNote__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2271:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2272:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2272:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2273:1: 'note'
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1()); 
            match(input,30,FOLLOW_30_in_rule__OneLifelineNote__Group__1__Impl4598); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2286:1: rule__OneLifelineNote__Group__2 : rule__OneLifelineNote__Group__2__Impl ;
    public final void rule__OneLifelineNote__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2290:1: ( rule__OneLifelineNote__Group__2__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2291:2: rule__OneLifelineNote__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__24629);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2297:1: rule__OneLifelineNote__Group__2__Impl : ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) ;
    public final void rule__OneLifelineNote__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2301:1: ( ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2302:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2302:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2303:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2304:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2304:2: rule__OneLifelineNote__NoteAssignment_2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl4656);
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


    // $ANTLR start "rule__Destroy__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2320:1: rule__Destroy__Group__0 : rule__Destroy__Group__0__Impl rule__Destroy__Group__1 ;
    public final void rule__Destroy__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2324:1: ( rule__Destroy__Group__0__Impl rule__Destroy__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2325:2: rule__Destroy__Group__0__Impl rule__Destroy__Group__1
            {
            pushFollow(FOLLOW_rule__Destroy__Group__0__Impl_in_rule__Destroy__Group__04692);
            rule__Destroy__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Destroy__Group__1_in_rule__Destroy__Group__04695);
            rule__Destroy__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Destroy__Group__0"


    // $ANTLR start "rule__Destroy__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2332:1: rule__Destroy__Group__0__Impl : ( ( rule__Destroy__LifelineAssignment_0 ) ) ;
    public final void rule__Destroy__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2336:1: ( ( ( rule__Destroy__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2337:1: ( ( rule__Destroy__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2337:1: ( ( rule__Destroy__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2338:1: ( rule__Destroy__LifelineAssignment_0 )
            {
             before(grammarAccess.getDestroyAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2339:1: ( rule__Destroy__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2339:2: rule__Destroy__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__Destroy__LifelineAssignment_0_in_rule__Destroy__Group__0__Impl4722);
            rule__Destroy__LifelineAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDestroyAccess().getLifelineAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Destroy__Group__0__Impl"


    // $ANTLR start "rule__Destroy__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2349:1: rule__Destroy__Group__1 : rule__Destroy__Group__1__Impl ;
    public final void rule__Destroy__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2353:1: ( rule__Destroy__Group__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2354:2: rule__Destroy__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Destroy__Group__1__Impl_in_rule__Destroy__Group__14752);
            rule__Destroy__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Destroy__Group__1"


    // $ANTLR start "rule__Destroy__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2360:1: rule__Destroy__Group__1__Impl : ( ( rule__Destroy__DestroyAssignment_1 ) ) ;
    public final void rule__Destroy__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2364:1: ( ( ( rule__Destroy__DestroyAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2365:1: ( ( rule__Destroy__DestroyAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2365:1: ( ( rule__Destroy__DestroyAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2366:1: ( rule__Destroy__DestroyAssignment_1 )
            {
             before(grammarAccess.getDestroyAccess().getDestroyAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2367:1: ( rule__Destroy__DestroyAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2367:2: rule__Destroy__DestroyAssignment_1
            {
            pushFollow(FOLLOW_rule__Destroy__DestroyAssignment_1_in_rule__Destroy__Group__1__Impl4779);
            rule__Destroy__DestroyAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getDestroyAccess().getDestroyAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Destroy__Group__1__Impl"


    // $ANTLR start "rule__Fragment__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2381:1: rule__Fragment__Group__0 : rule__Fragment__Group__0__Impl rule__Fragment__Group__1 ;
    public final void rule__Fragment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2385:1: ( rule__Fragment__Group__0__Impl rule__Fragment__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2386:2: rule__Fragment__Group__0__Impl rule__Fragment__Group__1
            {
            pushFollow(FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__04813);
            rule__Fragment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__04816);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2393:1: rule__Fragment__Group__0__Impl : ( 'fragment' ) ;
    public final void rule__Fragment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2397:1: ( ( 'fragment' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2398:1: ( 'fragment' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2398:1: ( 'fragment' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2399:1: 'fragment'
            {
             before(grammarAccess.getFragmentAccess().getFragmentKeyword_0()); 
            match(input,31,FOLLOW_31_in_rule__Fragment__Group__0__Impl4844); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2412:1: rule__Fragment__Group__1 : rule__Fragment__Group__1__Impl rule__Fragment__Group__2 ;
    public final void rule__Fragment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2416:1: ( rule__Fragment__Group__1__Impl rule__Fragment__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2417:2: rule__Fragment__Group__1__Impl rule__Fragment__Group__2
            {
            pushFollow(FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__14875);
            rule__Fragment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__14878);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2424:1: rule__Fragment__Group__1__Impl : ( ( rule__Fragment__NameAssignment_1 ) ) ;
    public final void rule__Fragment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2428:1: ( ( ( rule__Fragment__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2429:1: ( ( rule__Fragment__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2429:1: ( ( rule__Fragment__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2430:1: ( rule__Fragment__NameAssignment_1 )
            {
             before(grammarAccess.getFragmentAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2431:1: ( rule__Fragment__NameAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2431:2: rule__Fragment__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl4905);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2441:1: rule__Fragment__Group__2 : rule__Fragment__Group__2__Impl rule__Fragment__Group__3 ;
    public final void rule__Fragment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2445:1: ( rule__Fragment__Group__2__Impl rule__Fragment__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2446:2: rule__Fragment__Group__2__Impl rule__Fragment__Group__3
            {
            pushFollow(FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__24935);
            rule__Fragment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__24938);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2453:1: rule__Fragment__Group__2__Impl : ( ( rule__Fragment__FragmentContentsAssignment_2 ) ) ;
    public final void rule__Fragment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2457:1: ( ( ( rule__Fragment__FragmentContentsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2458:1: ( ( rule__Fragment__FragmentContentsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2458:1: ( ( rule__Fragment__FragmentContentsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2459:1: ( rule__Fragment__FragmentContentsAssignment_2 )
            {
             before(grammarAccess.getFragmentAccess().getFragmentContentsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2460:1: ( rule__Fragment__FragmentContentsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2460:2: rule__Fragment__FragmentContentsAssignment_2
            {
            pushFollow(FOLLOW_rule__Fragment__FragmentContentsAssignment_2_in_rule__Fragment__Group__2__Impl4965);
            rule__Fragment__FragmentContentsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFragmentAccess().getFragmentContentsAssignment_2()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2470:1: rule__Fragment__Group__3 : rule__Fragment__Group__3__Impl ;
    public final void rule__Fragment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2474:1: ( rule__Fragment__Group__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2475:2: rule__Fragment__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__34995);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2481:1: rule__Fragment__Group__3__Impl : ( ( rule__Fragment__FragmentContentsAssignment_3 )* ) ;
    public final void rule__Fragment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2485:1: ( ( ( rule__Fragment__FragmentContentsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2486:1: ( ( rule__Fragment__FragmentContentsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2486:1: ( ( rule__Fragment__FragmentContentsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2487:1: ( rule__Fragment__FragmentContentsAssignment_3 )*
            {
             before(grammarAccess.getFragmentAccess().getFragmentContentsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2488:1: ( rule__Fragment__FragmentContentsAssignment_3 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==19) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2488:2: rule__Fragment__FragmentContentsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Fragment__FragmentContentsAssignment_3_in_rule__Fragment__Group__3__Impl5022);
            	    rule__Fragment__FragmentContentsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getFragmentAccess().getFragmentContentsAssignment_3()); 

            }


            }

        }
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


    // $ANTLR start "rule__FragmentContent__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2506:1: rule__FragmentContent__Group__0 : rule__FragmentContent__Group__0__Impl rule__FragmentContent__Group__1 ;
    public final void rule__FragmentContent__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2510:1: ( rule__FragmentContent__Group__0__Impl rule__FragmentContent__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2511:2: rule__FragmentContent__Group__0__Impl rule__FragmentContent__Group__1
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group__0__Impl_in_rule__FragmentContent__Group__05061);
            rule__FragmentContent__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FragmentContent__Group__1_in_rule__FragmentContent__Group__05064);
            rule__FragmentContent__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__0"


    // $ANTLR start "rule__FragmentContent__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2518:1: rule__FragmentContent__Group__0__Impl : ( '{' ) ;
    public final void rule__FragmentContent__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2522:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2523:1: ( '{' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2523:1: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2524:1: '{'
            {
             before(grammarAccess.getFragmentContentAccess().getLeftCurlyBracketKeyword_0()); 
            match(input,19,FOLLOW_19_in_rule__FragmentContent__Group__0__Impl5092); 
             after(grammarAccess.getFragmentContentAccess().getLeftCurlyBracketKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__0__Impl"


    // $ANTLR start "rule__FragmentContent__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2537:1: rule__FragmentContent__Group__1 : rule__FragmentContent__Group__1__Impl rule__FragmentContent__Group__2 ;
    public final void rule__FragmentContent__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2541:1: ( rule__FragmentContent__Group__1__Impl rule__FragmentContent__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2542:2: rule__FragmentContent__Group__1__Impl rule__FragmentContent__Group__2
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group__1__Impl_in_rule__FragmentContent__Group__15123);
            rule__FragmentContent__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FragmentContent__Group__2_in_rule__FragmentContent__Group__15126);
            rule__FragmentContent__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__1"


    // $ANTLR start "rule__FragmentContent__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2549:1: rule__FragmentContent__Group__1__Impl : ( ( rule__FragmentContent__Group_1__0 )? ) ;
    public final void rule__FragmentContent__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2553:1: ( ( ( rule__FragmentContent__Group_1__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2554:1: ( ( rule__FragmentContent__Group_1__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2554:1: ( ( rule__FragmentContent__Group_1__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2555:1: ( rule__FragmentContent__Group_1__0 )?
            {
             before(grammarAccess.getFragmentContentAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2556:1: ( rule__FragmentContent__Group_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==32) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2556:2: rule__FragmentContent__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__FragmentContent__Group_1__0_in_rule__FragmentContent__Group__1__Impl5153);
                    rule__FragmentContent__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFragmentContentAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__1__Impl"


    // $ANTLR start "rule__FragmentContent__Group__2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2566:1: rule__FragmentContent__Group__2 : rule__FragmentContent__Group__2__Impl rule__FragmentContent__Group__3 ;
    public final void rule__FragmentContent__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2570:1: ( rule__FragmentContent__Group__2__Impl rule__FragmentContent__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2571:2: rule__FragmentContent__Group__2__Impl rule__FragmentContent__Group__3
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group__2__Impl_in_rule__FragmentContent__Group__25184);
            rule__FragmentContent__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FragmentContent__Group__3_in_rule__FragmentContent__Group__25187);
            rule__FragmentContent__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__2"


    // $ANTLR start "rule__FragmentContent__Group__2__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2578:1: rule__FragmentContent__Group__2__Impl : ( ( rule__FragmentContent__InteractionsAssignment_2 ) ) ;
    public final void rule__FragmentContent__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2582:1: ( ( ( rule__FragmentContent__InteractionsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2583:1: ( ( rule__FragmentContent__InteractionsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2583:1: ( ( rule__FragmentContent__InteractionsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2584:1: ( rule__FragmentContent__InteractionsAssignment_2 )
            {
             before(grammarAccess.getFragmentContentAccess().getInteractionsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2585:1: ( rule__FragmentContent__InteractionsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2585:2: rule__FragmentContent__InteractionsAssignment_2
            {
            pushFollow(FOLLOW_rule__FragmentContent__InteractionsAssignment_2_in_rule__FragmentContent__Group__2__Impl5214);
            rule__FragmentContent__InteractionsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFragmentContentAccess().getInteractionsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__2__Impl"


    // $ANTLR start "rule__FragmentContent__Group__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2595:1: rule__FragmentContent__Group__3 : rule__FragmentContent__Group__3__Impl rule__FragmentContent__Group__4 ;
    public final void rule__FragmentContent__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2599:1: ( rule__FragmentContent__Group__3__Impl rule__FragmentContent__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2600:2: rule__FragmentContent__Group__3__Impl rule__FragmentContent__Group__4
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group__3__Impl_in_rule__FragmentContent__Group__35244);
            rule__FragmentContent__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FragmentContent__Group__4_in_rule__FragmentContent__Group__35247);
            rule__FragmentContent__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__3"


    // $ANTLR start "rule__FragmentContent__Group__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2607:1: rule__FragmentContent__Group__3__Impl : ( ( rule__FragmentContent__InteractionsAssignment_3 )* ) ;
    public final void rule__FragmentContent__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2611:1: ( ( ( rule__FragmentContent__InteractionsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2612:1: ( ( rule__FragmentContent__InteractionsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2612:1: ( ( rule__FragmentContent__InteractionsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2613:1: ( rule__FragmentContent__InteractionsAssignment_3 )*
            {
             before(grammarAccess.getFragmentContentAccess().getInteractionsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2614:1: ( rule__FragmentContent__InteractionsAssignment_3 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID||LA24_0==31||LA24_0==33) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2614:2: rule__FragmentContent__InteractionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__FragmentContent__InteractionsAssignment_3_in_rule__FragmentContent__Group__3__Impl5274);
            	    rule__FragmentContent__InteractionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getFragmentContentAccess().getInteractionsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__3__Impl"


    // $ANTLR start "rule__FragmentContent__Group__4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2624:1: rule__FragmentContent__Group__4 : rule__FragmentContent__Group__4__Impl ;
    public final void rule__FragmentContent__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2628:1: ( rule__FragmentContent__Group__4__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2629:2: rule__FragmentContent__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group__4__Impl_in_rule__FragmentContent__Group__45305);
            rule__FragmentContent__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__4"


    // $ANTLR start "rule__FragmentContent__Group__4__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2635:1: rule__FragmentContent__Group__4__Impl : ( '}' ) ;
    public final void rule__FragmentContent__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2639:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2640:1: ( '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2640:1: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2641:1: '}'
            {
             before(grammarAccess.getFragmentContentAccess().getRightCurlyBracketKeyword_4()); 
            match(input,20,FOLLOW_20_in_rule__FragmentContent__Group__4__Impl5333); 
             after(grammarAccess.getFragmentContentAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group__4__Impl"


    // $ANTLR start "rule__FragmentContent__Group_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2664:1: rule__FragmentContent__Group_1__0 : rule__FragmentContent__Group_1__0__Impl rule__FragmentContent__Group_1__1 ;
    public final void rule__FragmentContent__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2668:1: ( rule__FragmentContent__Group_1__0__Impl rule__FragmentContent__Group_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2669:2: rule__FragmentContent__Group_1__0__Impl rule__FragmentContent__Group_1__1
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group_1__0__Impl_in_rule__FragmentContent__Group_1__05374);
            rule__FragmentContent__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FragmentContent__Group_1__1_in_rule__FragmentContent__Group_1__05377);
            rule__FragmentContent__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group_1__0"


    // $ANTLR start "rule__FragmentContent__Group_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2676:1: rule__FragmentContent__Group_1__0__Impl : ( 'label' ) ;
    public final void rule__FragmentContent__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2680:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2681:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2681:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2682:1: 'label'
            {
             before(grammarAccess.getFragmentContentAccess().getLabelKeyword_1_0()); 
            match(input,32,FOLLOW_32_in_rule__FragmentContent__Group_1__0__Impl5405); 
             after(grammarAccess.getFragmentContentAccess().getLabelKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group_1__0__Impl"


    // $ANTLR start "rule__FragmentContent__Group_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2695:1: rule__FragmentContent__Group_1__1 : rule__FragmentContent__Group_1__1__Impl ;
    public final void rule__FragmentContent__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2699:1: ( rule__FragmentContent__Group_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2700:2: rule__FragmentContent__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__FragmentContent__Group_1__1__Impl_in_rule__FragmentContent__Group_1__15436);
            rule__FragmentContent__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group_1__1"


    // $ANTLR start "rule__FragmentContent__Group_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2706:1: rule__FragmentContent__Group_1__1__Impl : ( ( rule__FragmentContent__LabelAssignment_1_1 ) ) ;
    public final void rule__FragmentContent__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2710:1: ( ( ( rule__FragmentContent__LabelAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2711:1: ( ( rule__FragmentContent__LabelAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2711:1: ( ( rule__FragmentContent__LabelAssignment_1_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2712:1: ( rule__FragmentContent__LabelAssignment_1_1 )
            {
             before(grammarAccess.getFragmentContentAccess().getLabelAssignment_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2713:1: ( rule__FragmentContent__LabelAssignment_1_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2713:2: rule__FragmentContent__LabelAssignment_1_1
            {
            pushFollow(FOLLOW_rule__FragmentContent__LabelAssignment_1_1_in_rule__FragmentContent__Group_1__1__Impl5463);
            rule__FragmentContent__LabelAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getFragmentContentAccess().getLabelAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__Group_1__1__Impl"


    // $ANTLR start "rule__Refinement__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2727:1: rule__Refinement__Group__0 : rule__Refinement__Group__0__Impl rule__Refinement__Group__1 ;
    public final void rule__Refinement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2731:1: ( rule__Refinement__Group__0__Impl rule__Refinement__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2732:2: rule__Refinement__Group__0__Impl rule__Refinement__Group__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__05497);
            rule__Refinement__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__05500);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2739:1: rule__Refinement__Group__0__Impl : ( 'refinement' ) ;
    public final void rule__Refinement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2743:1: ( ( 'refinement' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2744:1: ( 'refinement' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2744:1: ( 'refinement' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2745:1: 'refinement'
            {
             before(grammarAccess.getRefinementAccess().getRefinementKeyword_0()); 
            match(input,33,FOLLOW_33_in_rule__Refinement__Group__0__Impl5528); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2758:1: rule__Refinement__Group__1 : rule__Refinement__Group__1__Impl rule__Refinement__Group__2 ;
    public final void rule__Refinement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2762:1: ( rule__Refinement__Group__1__Impl rule__Refinement__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2763:2: rule__Refinement__Group__1__Impl rule__Refinement__Group__2
            {
            pushFollow(FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__15559);
            rule__Refinement__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__15562);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2770:1: rule__Refinement__Group__1__Impl : ( '{' ) ;
    public final void rule__Refinement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2774:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2775:1: ( '{' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2775:1: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2776:1: '{'
            {
             before(grammarAccess.getRefinementAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,19,FOLLOW_19_in_rule__Refinement__Group__1__Impl5590); 
             after(grammarAccess.getRefinementAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2789:1: rule__Refinement__Group__2 : rule__Refinement__Group__2__Impl rule__Refinement__Group__3 ;
    public final void rule__Refinement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2793:1: ( rule__Refinement__Group__2__Impl rule__Refinement__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2794:2: rule__Refinement__Group__2__Impl rule__Refinement__Group__3
            {
            pushFollow(FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__25621);
            rule__Refinement__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__25624);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2801:1: rule__Refinement__Group__2__Impl : ( 'lifelines' ) ;
    public final void rule__Refinement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2805:1: ( ( 'lifelines' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2806:1: ( 'lifelines' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2806:1: ( 'lifelines' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2807:1: 'lifelines'
            {
             before(grammarAccess.getRefinementAccess().getLifelinesKeyword_2()); 
            match(input,34,FOLLOW_34_in_rule__Refinement__Group__2__Impl5652); 
             after(grammarAccess.getRefinementAccess().getLifelinesKeyword_2()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2820:1: rule__Refinement__Group__3 : rule__Refinement__Group__3__Impl rule__Refinement__Group__4 ;
    public final void rule__Refinement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2824:1: ( rule__Refinement__Group__3__Impl rule__Refinement__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2825:2: rule__Refinement__Group__3__Impl rule__Refinement__Group__4
            {
            pushFollow(FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__35683);
            rule__Refinement__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__35686);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2832:1: rule__Refinement__Group__3__Impl : ( ( rule__Refinement__LifelinesAssignment_3 ) ) ;
    public final void rule__Refinement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2836:1: ( ( ( rule__Refinement__LifelinesAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2837:1: ( ( rule__Refinement__LifelinesAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2837:1: ( ( rule__Refinement__LifelinesAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2838:1: ( rule__Refinement__LifelinesAssignment_3 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2839:1: ( rule__Refinement__LifelinesAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2839:2: rule__Refinement__LifelinesAssignment_3
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_3_in_rule__Refinement__Group__3__Impl5713);
            rule__Refinement__LifelinesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getRefinementAccess().getLifelinesAssignment_3()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2849:1: rule__Refinement__Group__4 : rule__Refinement__Group__4__Impl rule__Refinement__Group__5 ;
    public final void rule__Refinement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2853:1: ( rule__Refinement__Group__4__Impl rule__Refinement__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2854:2: rule__Refinement__Group__4__Impl rule__Refinement__Group__5
            {
            pushFollow(FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__45743);
            rule__Refinement__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__45746);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2861:1: rule__Refinement__Group__4__Impl : ( ( rule__Refinement__Group_4__0 )* ) ;
    public final void rule__Refinement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2865:1: ( ( ( rule__Refinement__Group_4__0 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2866:1: ( ( rule__Refinement__Group_4__0 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2866:1: ( ( rule__Refinement__Group_4__0 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2867:1: ( rule__Refinement__Group_4__0 )*
            {
             before(grammarAccess.getRefinementAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2868:1: ( rule__Refinement__Group_4__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==35) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2868:2: rule__Refinement__Group_4__0
            	    {
            	    pushFollow(FOLLOW_rule__Refinement__Group_4__0_in_rule__Refinement__Group__4__Impl5773);
            	    rule__Refinement__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

             after(grammarAccess.getRefinementAccess().getGroup_4()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2878:1: rule__Refinement__Group__5 : rule__Refinement__Group__5__Impl rule__Refinement__Group__6 ;
    public final void rule__Refinement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2882:1: ( rule__Refinement__Group__5__Impl rule__Refinement__Group__6 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2883:2: rule__Refinement__Group__5__Impl rule__Refinement__Group__6
            {
            pushFollow(FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__55804);
            rule__Refinement__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__6_in_rule__Refinement__Group__55807);
            rule__Refinement__Group__6();

            state._fsp--;


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2890:1: rule__Refinement__Group__5__Impl : ( 'label' ) ;
    public final void rule__Refinement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2894:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2895:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2895:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2896:1: 'label'
            {
             before(grammarAccess.getRefinementAccess().getLabelKeyword_5()); 
            match(input,32,FOLLOW_32_in_rule__Refinement__Group__5__Impl5835); 
             after(grammarAccess.getRefinementAccess().getLabelKeyword_5()); 

            }


            }

        }
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


    // $ANTLR start "rule__Refinement__Group__6"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2909:1: rule__Refinement__Group__6 : rule__Refinement__Group__6__Impl rule__Refinement__Group__7 ;
    public final void rule__Refinement__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2913:1: ( rule__Refinement__Group__6__Impl rule__Refinement__Group__7 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2914:2: rule__Refinement__Group__6__Impl rule__Refinement__Group__7
            {
            pushFollow(FOLLOW_rule__Refinement__Group__6__Impl_in_rule__Refinement__Group__65866);
            rule__Refinement__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__7_in_rule__Refinement__Group__65869);
            rule__Refinement__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__6"


    // $ANTLR start "rule__Refinement__Group__6__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2921:1: rule__Refinement__Group__6__Impl : ( ( rule__Refinement__LabelAssignment_6 ) ) ;
    public final void rule__Refinement__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2925:1: ( ( ( rule__Refinement__LabelAssignment_6 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2926:1: ( ( rule__Refinement__LabelAssignment_6 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2926:1: ( ( rule__Refinement__LabelAssignment_6 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2927:1: ( rule__Refinement__LabelAssignment_6 )
            {
             before(grammarAccess.getRefinementAccess().getLabelAssignment_6()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2928:1: ( rule__Refinement__LabelAssignment_6 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2928:2: rule__Refinement__LabelAssignment_6
            {
            pushFollow(FOLLOW_rule__Refinement__LabelAssignment_6_in_rule__Refinement__Group__6__Impl5896);
            rule__Refinement__LabelAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getRefinementAccess().getLabelAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__6__Impl"


    // $ANTLR start "rule__Refinement__Group__7"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2938:1: rule__Refinement__Group__7 : rule__Refinement__Group__7__Impl ;
    public final void rule__Refinement__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2942:1: ( rule__Refinement__Group__7__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2943:2: rule__Refinement__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group__7__Impl_in_rule__Refinement__Group__75926);
            rule__Refinement__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__7"


    // $ANTLR start "rule__Refinement__Group__7__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2949:1: rule__Refinement__Group__7__Impl : ( '}' ) ;
    public final void rule__Refinement__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2953:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2954:1: ( '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2954:1: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2955:1: '}'
            {
             before(grammarAccess.getRefinementAccess().getRightCurlyBracketKeyword_7()); 
            match(input,20,FOLLOW_20_in_rule__Refinement__Group__7__Impl5954); 
             after(grammarAccess.getRefinementAccess().getRightCurlyBracketKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group__7__Impl"


    // $ANTLR start "rule__Refinement__Group_4__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2984:1: rule__Refinement__Group_4__0 : rule__Refinement__Group_4__0__Impl rule__Refinement__Group_4__1 ;
    public final void rule__Refinement__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2988:1: ( rule__Refinement__Group_4__0__Impl rule__Refinement__Group_4__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2989:2: rule__Refinement__Group_4__0__Impl rule__Refinement__Group_4__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group_4__0__Impl_in_rule__Refinement__Group_4__06001);
            rule__Refinement__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group_4__1_in_rule__Refinement__Group_4__06004);
            rule__Refinement__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_4__0"


    // $ANTLR start "rule__Refinement__Group_4__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2996:1: rule__Refinement__Group_4__0__Impl : ( ',' ) ;
    public final void rule__Refinement__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3000:1: ( ( ',' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3001:1: ( ',' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3001:1: ( ',' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3002:1: ','
            {
             before(grammarAccess.getRefinementAccess().getCommaKeyword_4_0()); 
            match(input,35,FOLLOW_35_in_rule__Refinement__Group_4__0__Impl6032); 
             after(grammarAccess.getRefinementAccess().getCommaKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_4__0__Impl"


    // $ANTLR start "rule__Refinement__Group_4__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3015:1: rule__Refinement__Group_4__1 : rule__Refinement__Group_4__1__Impl ;
    public final void rule__Refinement__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3019:1: ( rule__Refinement__Group_4__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3020:2: rule__Refinement__Group_4__1__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group_4__1__Impl_in_rule__Refinement__Group_4__16063);
            rule__Refinement__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_4__1"


    // $ANTLR start "rule__Refinement__Group_4__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3026:1: rule__Refinement__Group_4__1__Impl : ( ( rule__Refinement__LifelinesAssignment_4_1 ) ) ;
    public final void rule__Refinement__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3030:1: ( ( ( rule__Refinement__LifelinesAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3031:1: ( ( rule__Refinement__LifelinesAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3031:1: ( ( rule__Refinement__LifelinesAssignment_4_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3032:1: ( rule__Refinement__LifelinesAssignment_4_1 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_4_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3033:1: ( rule__Refinement__LifelinesAssignment_4_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3033:2: rule__Refinement__LifelinesAssignment_4_1
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_4_1_in_rule__Refinement__Group_4__1__Impl6090);
            rule__Refinement__LifelinesAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getRefinementAccess().getLifelinesAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__Group_4__1__Impl"


    // $ANTLR start "rule__SequenceDiagram__DiagramNameAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3048:1: rule__SequenceDiagram__DiagramNameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__SequenceDiagram__DiagramNameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3052:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3053:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3053:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3054:1: RULE_STRING
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_26129); 
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


    // $ANTLR start "rule__SequenceDiagram__LocalsAssignment_3_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3063:1: rule__SequenceDiagram__LocalsAssignment_3_1 : ( ruleLocalVariable ) ;
    public final void rule__SequenceDiagram__LocalsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3067:1: ( ( ruleLocalVariable ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3068:1: ( ruleLocalVariable )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3068:1: ( ruleLocalVariable )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3069:1: ruleLocalVariable
            {
             before(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleLocalVariable_in_rule__SequenceDiagram__LocalsAssignment_3_16160);
            ruleLocalVariable();

            state._fsp--;

             after(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__LocalsAssignment_3_1"


    // $ANTLR start "rule__SequenceDiagram__LifelinesAssignment_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3078:1: rule__SequenceDiagram__LifelinesAssignment_4 : ( ruleLifeline ) ;
    public final void rule__SequenceDiagram__LifelinesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3082:1: ( ( ruleLifeline ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3083:1: ( ruleLifeline )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3083:1: ( ruleLifeline )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3084:1: ruleLifeline
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_46191);
            ruleLifeline();

            state._fsp--;

             after(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__LifelinesAssignment_4"


    // $ANTLR start "rule__SequenceDiagram__InteractionsAssignment_5"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3093:1: rule__SequenceDiagram__InteractionsAssignment_5 : ( ruleInteraction ) ;
    public final void rule__SequenceDiagram__InteractionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3097:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3098:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3098:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3099:1: ruleInteraction
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_56222);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__InteractionsAssignment_5"


    // $ANTLR start "rule__LocalVariable__NameAssignment_0_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3108:1: rule__LocalVariable__NameAssignment_0_1 : ( RULE_ID ) ;
    public final void rule__LocalVariable__NameAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3112:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3113:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3113:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3114:1: RULE_ID
            {
             before(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_0_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_0_16253); 
             after(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__NameAssignment_0_1"


    // $ANTLR start "rule__LocalVariable__NameAssignment_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3123:1: rule__LocalVariable__NameAssignment_1_1 : ( RULE_ID ) ;
    public final void rule__LocalVariable__NameAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3127:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3128:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3128:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3129:1: RULE_ID
            {
             before(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_1_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_1_16284); 
             after(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__NameAssignment_1_1"


    // $ANTLR start "rule__LocalVariable__NameAssignment_2_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3138:1: rule__LocalVariable__NameAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__LocalVariable__NameAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3142:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3143:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3143:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3144:1: RULE_ID
            {
             before(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_2_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_2_16315); 
             after(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__NameAssignment_2_1"


    // $ANTLR start "rule__LocalVariable__NameAssignment_3_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3153:1: rule__LocalVariable__NameAssignment_3_1 : ( RULE_ID ) ;
    public final void rule__LocalVariable__NameAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3157:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3158:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3158:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3159:1: RULE_ID
            {
             before(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_3_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_3_16346); 
             after(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__NameAssignment_3_1"


    // $ANTLR start "rule__Lifeline__CaptionAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3168:1: rule__Lifeline__CaptionAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Lifeline__CaptionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3172:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3173:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3173:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3174:1: RULE_STRING
            {
             before(grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_16377); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3183:1: rule__Lifeline__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__Lifeline__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3187:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3188:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3188:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3189:1: RULE_ID
            {
             before(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_36408); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3198:1: rule__TwoLifelineMessage__SourceLifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__SourceLifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3202:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3203:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3203:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3204:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3205:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3206:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_06443); 
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


    // $ANTLR start "rule__TwoLifelineMessage__TransitionTypeAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3217:1: rule__TwoLifelineMessage__TransitionTypeAssignment_1 : ( ruleTransitionType ) ;
    public final void rule__TwoLifelineMessage__TransitionTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3221:1: ( ( ruleTransitionType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3222:1: ( ruleTransitionType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3222:1: ( ruleTransitionType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3223:1: ruleTransitionType
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTransitionType_in_rule__TwoLifelineMessage__TransitionTypeAssignment_16478);
            ruleTransitionType();

            state._fsp--;

             after(grammarAccess.getTwoLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__TransitionTypeAssignment_1"


    // $ANTLR start "rule__TwoLifelineMessage__CaptionAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3232:1: rule__TwoLifelineMessage__CaptionAssignment_2 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__CaptionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3236:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3237:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3237:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3238:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__CaptionAssignment_26509); 
             after(grammarAccess.getTwoLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__CaptionAssignment_2"


    // $ANTLR start "rule__TwoLifelineMessage__TargetLifelineAssignment_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3247:1: rule__TwoLifelineMessage__TargetLifelineAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__TargetLifelineAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3251:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3252:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3252:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3253:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3254:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3255:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_46544); 
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


    // $ANTLR start "rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3266:1: rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0 : ( ( 'sourceStartBlock' ) ) ;
    public final void rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3270:1: ( ( ( 'sourceStartBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3271:1: ( ( 'sourceStartBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3271:1: ( ( 'sourceStartBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3272:1: ( 'sourceStartBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftSourceStartBlockKeyword_5_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3273:1: ( 'sourceStartBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3274:1: 'sourceStartBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftSourceStartBlockKeyword_5_0_0()); 
            match(input,36,FOLLOW_36_in_rule__TwoLifelineMessage__StartBlockLeftAssignment_5_06584); 
             after(grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftSourceStartBlockKeyword_5_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftSourceStartBlockKeyword_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0"


    // $ANTLR start "rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3289:1: rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0 : ( ( 'sourceEndBlock' ) ) ;
    public final void rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3293:1: ( ( ( 'sourceEndBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3294:1: ( ( 'sourceEndBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3294:1: ( ( 'sourceEndBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3295:1: ( 'sourceEndBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftSourceEndBlockKeyword_5_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3296:1: ( 'sourceEndBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3297:1: 'sourceEndBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftSourceEndBlockKeyword_5_1_0_0()); 
            match(input,37,FOLLOW_37_in_rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_06628); 
             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftSourceEndBlockKeyword_5_1_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftSourceEndBlockKeyword_5_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0"


    // $ANTLR start "rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3312:1: rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3316:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3317:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3317:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3318:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_16667); 
             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1"


    // $ANTLR start "rule__TwoLifelineMessage__StartBlockRightAssignment_6_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3327:1: rule__TwoLifelineMessage__StartBlockRightAssignment_6_0 : ( ( 'targetStartBlock' ) ) ;
    public final void rule__TwoLifelineMessage__StartBlockRightAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3331:1: ( ( ( 'targetStartBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3332:1: ( ( 'targetStartBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3332:1: ( ( 'targetStartBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3333:1: ( 'targetStartBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightTargetStartBlockKeyword_6_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3334:1: ( 'targetStartBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3335:1: 'targetStartBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightTargetStartBlockKeyword_6_0_0()); 
            match(input,38,FOLLOW_38_in_rule__TwoLifelineMessage__StartBlockRightAssignment_6_06703); 
             after(grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightTargetStartBlockKeyword_6_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightTargetStartBlockKeyword_6_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__StartBlockRightAssignment_6_0"


    // $ANTLR start "rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3350:1: rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0 : ( ( 'targetEndBlock' ) ) ;
    public final void rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3354:1: ( ( ( 'targetEndBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3355:1: ( ( 'targetEndBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3355:1: ( ( 'targetEndBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3356:1: ( 'targetEndBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightTargetEndBlockKeyword_6_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3357:1: ( 'targetEndBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3358:1: 'targetEndBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightTargetEndBlockKeyword_6_1_0_0()); 
            match(input,39,FOLLOW_39_in_rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_06747); 
             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightTargetEndBlockKeyword_6_1_0_0()); 

            }

             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightTargetEndBlockKeyword_6_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0"


    // $ANTLR start "rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3373:1: rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3377:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3378:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3378:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3379:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_16786); 
             after(grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1"


    // $ANTLR start "rule__TwoLifelineMessage__SourceNoteAssignment_7_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3388:1: rule__TwoLifelineMessage__SourceNoteAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__SourceNoteAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3392:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3393:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3393:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3394:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_16817); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3403:1: rule__TwoLifelineMessage__TargetNoteAssignment_8_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__TargetNoteAssignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3407:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3408:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3408:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3409:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_16848); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3418:1: rule__OneLifelineMessage__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineMessage__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3422:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3423:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3423:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3424:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3425:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3426:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_06883); 
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


    // $ANTLR start "rule__OneLifelineMessage__TransitionTypeAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3437:1: rule__OneLifelineMessage__TransitionTypeAssignment_1 : ( ruleTransitionType ) ;
    public final void rule__OneLifelineMessage__TransitionTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3441:1: ( ( ruleTransitionType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3442:1: ( ruleTransitionType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3442:1: ( ruleTransitionType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3443:1: ruleTransitionType
            {
             before(grammarAccess.getOneLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTransitionType_in_rule__OneLifelineMessage__TransitionTypeAssignment_16918);
            ruleTransitionType();

            state._fsp--;

             after(grammarAccess.getOneLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__TransitionTypeAssignment_1"


    // $ANTLR start "rule__OneLifelineMessage__CaptionAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3452:1: rule__OneLifelineMessage__CaptionAssignment_3 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__CaptionAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3456:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3457:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3457:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3458:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__CaptionAssignment_36949); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3467:1: rule__OneLifelineMessage__StartBlockAssignment_4_0 : ( ( 'startBlock' ) ) ;
    public final void rule__OneLifelineMessage__StartBlockAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3471:1: ( ( ( 'startBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3472:1: ( ( 'startBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3472:1: ( ( 'startBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3473:1: ( 'startBlock' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3474:1: ( 'startBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3475:1: 'startBlock'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0()); 
            match(input,40,FOLLOW_40_in_rule__OneLifelineMessage__StartBlockAssignment_4_06985); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3490:1: rule__OneLifelineMessage__EndBlockAssignment_4_1_0 : ( ( 'endBlock' ) ) ;
    public final void rule__OneLifelineMessage__EndBlockAssignment_4_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3494:1: ( ( ( 'endBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3495:1: ( ( 'endBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3495:1: ( ( 'endBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3496:1: ( 'endBlock' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3497:1: ( 'endBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3498:1: 'endBlock'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0()); 
            match(input,41,FOLLOW_41_in_rule__OneLifelineMessage__EndBlockAssignment_4_1_07029); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3513:1: rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3517:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3518:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3518:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3519:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_4_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_17068); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3528:1: rule__OneLifelineMessage__NoteAssignment_5_1 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__NoteAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3532:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3533:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3533:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3534:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_5_17099); 
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


    // $ANTLR start "rule__OneLifelineEndBlock__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3543:1: rule__OneLifelineEndBlock__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineEndBlock__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3547:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3548:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3548:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3549:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3550:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3551:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineEndBlock__LifelineAssignment_07134); 
             after(grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__LifelineAssignment_0"


    // $ANTLR start "rule__OneLifelineEndBlock__EndBlockAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3562:1: rule__OneLifelineEndBlock__EndBlockAssignment_1 : ( ( 'endBlock' ) ) ;
    public final void rule__OneLifelineEndBlock__EndBlockAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3566:1: ( ( ( 'endBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3567:1: ( ( 'endBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3567:1: ( ( 'endBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3568:1: ( 'endBlock' )
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockEndBlockKeyword_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3569:1: ( 'endBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3570:1: 'endBlock'
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockEndBlockKeyword_1_0()); 
            match(input,41,FOLLOW_41_in_rule__OneLifelineEndBlock__EndBlockAssignment_17174); 
             after(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockEndBlockKeyword_1_0()); 

            }

             after(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockEndBlockKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__EndBlockAssignment_1"


    // $ANTLR start "rule__OneLifelineEndBlock__EndBlockCountAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3585:1: rule__OneLifelineEndBlock__EndBlockCountAssignment_2 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__OneLifelineEndBlock__EndBlockCountAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3589:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3590:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3590:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3591:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_2_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineEndBlock__EndBlockCountAssignment_27213); 
             after(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineEndBlock__EndBlockCountAssignment_2"


    // $ANTLR start "rule__OneLifelineNote__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3600:1: rule__OneLifelineNote__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineNote__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3604:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3605:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3605:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3606:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3607:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3608:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_07248); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3619:1: rule__OneLifelineNote__NoteAssignment_2 : ( RULE_STRING ) ;
    public final void rule__OneLifelineNote__NoteAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3623:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3624:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3624:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3625:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_27283); 
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


    // $ANTLR start "rule__Destroy__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3634:1: rule__Destroy__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__Destroy__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3638:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3639:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3639:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3640:1: ( RULE_ID )
            {
             before(grammarAccess.getDestroyAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3641:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3642:1: RULE_ID
            {
             before(grammarAccess.getDestroyAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Destroy__LifelineAssignment_07318); 
             after(grammarAccess.getDestroyAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getDestroyAccess().getLifelineLifelineCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Destroy__LifelineAssignment_0"


    // $ANTLR start "rule__Destroy__DestroyAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3653:1: rule__Destroy__DestroyAssignment_1 : ( ( 'destroy' ) ) ;
    public final void rule__Destroy__DestroyAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3657:1: ( ( ( 'destroy' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3658:1: ( ( 'destroy' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3658:1: ( ( 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3659:1: ( 'destroy' )
            {
             before(grammarAccess.getDestroyAccess().getDestroyDestroyKeyword_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3660:1: ( 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3661:1: 'destroy'
            {
             before(grammarAccess.getDestroyAccess().getDestroyDestroyKeyword_1_0()); 
            match(input,42,FOLLOW_42_in_rule__Destroy__DestroyAssignment_17358); 
             after(grammarAccess.getDestroyAccess().getDestroyDestroyKeyword_1_0()); 

            }

             after(grammarAccess.getDestroyAccess().getDestroyDestroyKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Destroy__DestroyAssignment_1"


    // $ANTLR start "rule__Fragment__NameAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3676:1: rule__Fragment__NameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Fragment__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3680:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3681:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3681:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3682:1: RULE_STRING
            {
             before(grammarAccess.getFragmentAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_17397); 
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


    // $ANTLR start "rule__Fragment__FragmentContentsAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3691:1: rule__Fragment__FragmentContentsAssignment_2 : ( ruleFragmentContent ) ;
    public final void rule__Fragment__FragmentContentsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3695:1: ( ( ruleFragmentContent ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3696:1: ( ruleFragmentContent )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3696:1: ( ruleFragmentContent )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3697:1: ruleFragmentContent
            {
             before(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleFragmentContent_in_rule__Fragment__FragmentContentsAssignment_27428);
            ruleFragmentContent();

            state._fsp--;

             after(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__FragmentContentsAssignment_2"


    // $ANTLR start "rule__Fragment__FragmentContentsAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3706:1: rule__Fragment__FragmentContentsAssignment_3 : ( ruleFragmentContent ) ;
    public final void rule__Fragment__FragmentContentsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3710:1: ( ( ruleFragmentContent ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3711:1: ( ruleFragmentContent )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3711:1: ( ruleFragmentContent )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3712:1: ruleFragmentContent
            {
             before(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleFragmentContent_in_rule__Fragment__FragmentContentsAssignment_37459);
            ruleFragmentContent();

            state._fsp--;

             after(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Fragment__FragmentContentsAssignment_3"


    // $ANTLR start "rule__FragmentContent__LabelAssignment_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3721:1: rule__FragmentContent__LabelAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__FragmentContent__LabelAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3725:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3726:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3726:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3727:1: RULE_STRING
            {
             before(grammarAccess.getFragmentContentAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FragmentContent__LabelAssignment_1_17490); 
             after(grammarAccess.getFragmentContentAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__LabelAssignment_1_1"


    // $ANTLR start "rule__FragmentContent__InteractionsAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3736:1: rule__FragmentContent__InteractionsAssignment_2 : ( ruleInteraction ) ;
    public final void rule__FragmentContent__InteractionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3740:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3741:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3741:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3742:1: ruleInteraction
            {
             before(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__FragmentContent__InteractionsAssignment_27521);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__InteractionsAssignment_2"


    // $ANTLR start "rule__FragmentContent__InteractionsAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3751:1: rule__FragmentContent__InteractionsAssignment_3 : ( ruleInteraction ) ;
    public final void rule__FragmentContent__InteractionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3755:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3756:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3756:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3757:1: ruleInteraction
            {
             before(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__FragmentContent__InteractionsAssignment_37552);
            ruleInteraction();

            state._fsp--;

             after(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FragmentContent__InteractionsAssignment_3"


    // $ANTLR start "rule__Refinement__LifelinesAssignment_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3766:1: rule__Refinement__LifelinesAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3770:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3771:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3771:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3772:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_3_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3773:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3774:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_37587); 
             after(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__LifelinesAssignment_3"


    // $ANTLR start "rule__Refinement__LifelinesAssignment_4_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3785:1: rule__Refinement__LifelinesAssignment_4_1 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3789:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3790:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3790:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3791:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3792:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3793:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_4_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_4_17626); 
             after(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_4_1_0_1()); 

            }

             after(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__LifelinesAssignment_4_1"


    // $ANTLR start "rule__Refinement__LabelAssignment_6"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3804:1: rule__Refinement__LabelAssignment_6 : ( RULE_STRING ) ;
    public final void rule__Refinement__LabelAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3808:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3809:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3809:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3810:1: RULE_STRING
            {
             before(grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_6_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_67661); 
             after(grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Refinement__LabelAssignment_6"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\15\uffff";
    static final String DFA2_eofS =
        "\15\uffff";
    static final String DFA2_minS =
        "\1\5\1\16\2\uffff\4\4\5\uffff";
    static final String DFA2_maxS =
        "\1\41\1\52\2\uffff\4\15\5\uffff";
    static final String DFA2_acceptS =
        "\2\uffff\1\3\1\7\4\uffff\1\4\1\6\1\5\1\2\1\1";
    static final String DFA2_specialS =
        "\15\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\31\uffff\1\2\1\uffff\1\3",
            "\1\4\1\5\1\6\1\7\14\uffff\1\12\12\uffff\1\10\1\11",
            "",
            "",
            "\1\14\7\uffff\2\13",
            "\1\14\7\uffff\2\13",
            "\1\14\7\uffff\2\13",
            "\1\14\7\uffff\2\13",
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
            return "449:1: rule__Interaction__Alternatives : ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineEndBlock ) | ( ruleOneLifelineNote ) | ( ruleDestroy ) | ( ruleRefinement ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0_in_ruleSequenceDiagram94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_entryRuleLocalVariable121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalVariable128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Alternatives_in_ruleLocalVariable154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_entryRuleLifeline181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLifeline188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__0_in_ruleLifeline214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_entryRuleInteraction241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInteraction248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Interaction__Alternatives_in_ruleInteraction274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTwoLifelineMessage308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__0_in_ruleTwoLifelineMessage334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineMessage368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__0_in_ruleOneLifelineMessage394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineEndBlock_in_entryRuleOneLifelineEndBlock421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineEndBlock428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__0_in_ruleOneLifelineEndBlock454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineNote488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__0_in_ruleOneLifelineNote514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroy_in_entryRuleDestroy541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroy548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Destroy__Group__0_in_ruleDestroy574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0_in_ruleFragment634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragmentContent_in_entryRuleFragmentContent661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragmentContent668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__0_in_ruleFragmentContent694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0_in_ruleRefinement754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TransitionType__Alternatives_in_ruleTransitionType791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_0__0_in_rule__LocalVariable__Alternatives826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_1__0_in_rule__LocalVariable__Alternatives844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_2__0_in_rule__LocalVariable__Alternatives862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_3__0_in_rule__LocalVariable__Alternatives880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_rule__Interaction__Alternatives947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineEndBlock_in_rule__Interaction__Alternatives964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroy_in_rule__Interaction__Alternatives998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__StartBlockLeftAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_51047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__0_in_rule__TwoLifelineMessage__Alternatives_51065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__StartBlockRightAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_61098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__0_in_rule__TwoLifelineMessage__Alternatives_61116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__OneLifelineMessage__Alternatives_21150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__OneLifelineMessage__Alternatives_21170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__StartBlockAssignment_4_0_in_rule__OneLifelineMessage__Alternatives_41204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__0_in_rule__OneLifelineMessage__Alternatives_41222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__TransitionType__Alternatives1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__TransitionType__Alternatives1277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__TransitionType__Alternatives1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__TransitionType__Alternatives1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01352 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11413 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__SequenceDiagram__Group__1__Impl1444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21475 = new BitSet(new long[]{0x0000000282080020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31535 = new BitSet(new long[]{0x0000000282080020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__0_in_rule__SequenceDiagram__Group__3__Impl1565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41596 = new BitSet(new long[]{0x0000000282080020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__5_in_rule__SequenceDiagram__Group__41599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__LifelinesAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1626 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__5__Impl_in_rule__SequenceDiagram__Group__51657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__InteractionsAssignment_5_in_rule__SequenceDiagram__Group__5__Impl1684 = new BitSet(new long[]{0x0000000280000022L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__0__Impl_in_rule__SequenceDiagram__Group_3__01727 = new BitSet(new long[]{0x0000000001F00000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__1_in_rule__SequenceDiagram__Group_3__01730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__SequenceDiagram__Group_3__0__Impl1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__1__Impl_in_rule__SequenceDiagram__Group_3__11789 = new BitSet(new long[]{0x0000000001F00000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__2_in_rule__SequenceDiagram__Group_3__11792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__LocalsAssignment_3_1_in_rule__SequenceDiagram__Group_3__1__Impl1819 = new BitSet(new long[]{0x0000000001E00002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__2__Impl_in_rule__SequenceDiagram__Group_3__21850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__SequenceDiagram__Group_3__2__Impl1878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_0__0__Impl_in_rule__LocalVariable__Group_0__01915 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_0__1_in_rule__LocalVariable__Group_0__01918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__LocalVariable__Group_0__0__Impl1946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_0__1__Impl_in_rule__LocalVariable__Group_0__11977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__NameAssignment_0_1_in_rule__LocalVariable__Group_0__1__Impl2004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_1__0__Impl_in_rule__LocalVariable__Group_1__02038 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_1__1_in_rule__LocalVariable__Group_1__02041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__LocalVariable__Group_1__0__Impl2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_1__1__Impl_in_rule__LocalVariable__Group_1__12100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__NameAssignment_1_1_in_rule__LocalVariable__Group_1__1__Impl2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_2__0__Impl_in_rule__LocalVariable__Group_2__02161 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_2__1_in_rule__LocalVariable__Group_2__02164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__LocalVariable__Group_2__0__Impl2192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_2__1__Impl_in_rule__LocalVariable__Group_2__12223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__NameAssignment_2_1_in_rule__LocalVariable__Group_2__1__Impl2250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_3__0__Impl_in_rule__LocalVariable__Group_3__02284 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_3__1_in_rule__LocalVariable__Group_3__02287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__LocalVariable__Group_3__0__Impl2315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group_3__1__Impl_in_rule__LocalVariable__Group_3__12346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__NameAssignment_3_1_in_rule__LocalVariable__Group_3__1__Impl2373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__0__Impl_in_rule__Lifeline__Group__02407 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__1_in_rule__Lifeline__Group__02410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Lifeline__Group__0__Impl2438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__1__Impl_in_rule__Lifeline__Group__12469 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__2_in_rule__Lifeline__Group__12472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__CaptionAssignment_1_in_rule__Lifeline__Group__1__Impl2499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__2__Impl_in_rule__Lifeline__Group__22529 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__3_in_rule__Lifeline__Group__22532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Lifeline__Group__2__Impl2560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__3__Impl_in_rule__Lifeline__Group__32591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__NameAssignment_3_in_rule__Lifeline__Group__3__Impl2618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__02656 = new BitSet(new long[]{0x000000000003C000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__02659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl2686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__12716 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__12719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TransitionTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl2746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__22776 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__22779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__CaptionAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl2806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__32836 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__32839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__TwoLifelineMessage__Group__3__Impl2867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__42898 = new BitSet(new long[]{0x000000F030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__42901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl2928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__52958 = new BitSet(new long[]{0x000000F030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__52961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__63019 = new BitSet(new long[]{0x000000F030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__63022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl3049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__73080 = new BitSet(new long[]{0x000000F030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__73083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl3110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__83141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl3168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__0__Impl_in_rule__TwoLifelineMessage__Group_5_1__03217 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__1_in_rule__TwoLifelineMessage__Group_5_1__03220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_0_in_rule__TwoLifelineMessage__Group_5_1__0__Impl3247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__1__Impl_in_rule__TwoLifelineMessage__Group_5_1__13277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_1_in_rule__TwoLifelineMessage__Group_5_1__1__Impl3304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__0__Impl_in_rule__TwoLifelineMessage__Group_6_1__03339 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__1_in_rule__TwoLifelineMessage__Group_6_1__03342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_0_in_rule__TwoLifelineMessage__Group_6_1__0__Impl3369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__1__Impl_in_rule__TwoLifelineMessage__Group_6_1__13399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_1_in_rule__TwoLifelineMessage__Group_6_1__1__Impl3426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__03461 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__03464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__TwoLifelineMessage__Group_7__0__Impl3492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__13523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__03584 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__03587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__TwoLifelineMessage__Group_8__0__Impl3615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__13646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl3673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__03707 = new BitSet(new long[]{0x000000000003C000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__03710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl3737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__13767 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__13770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__TransitionTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl3797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__23827 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__23830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Alternatives_2_in_rule__OneLifelineMessage__Group__2__Impl3857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__33887 = new BitSet(new long[]{0x0000030040000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__33890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__CaptionAssignment_3_in_rule__OneLifelineMessage__Group__3__Impl3917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__43947 = new BitSet(new long[]{0x0000030040000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__5_in_rule__OneLifelineMessage__Group__43950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Alternatives_4_in_rule__OneLifelineMessage__Group__4__Impl3977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__5__Impl_in_rule__OneLifelineMessage__Group__54008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__0_in_rule__OneLifelineMessage__Group__5__Impl4035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__0__Impl_in_rule__OneLifelineMessage__Group_4_1__04078 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__1_in_rule__OneLifelineMessage__Group_4_1__04081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndBlockAssignment_4_1_0_in_rule__OneLifelineMessage__Group_4_1__0__Impl4108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4_1__1__Impl_in_rule__OneLifelineMessage__Group_4_1__14138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_1_in_rule__OneLifelineMessage__Group_4_1__1__Impl4165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__0__Impl_in_rule__OneLifelineMessage__Group_5__04200 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__1_in_rule__OneLifelineMessage__Group_5__04203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__OneLifelineMessage__Group_5__0__Impl4231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_5__1__Impl_in_rule__OneLifelineMessage__Group_5__14262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__NoteAssignment_5_1_in_rule__OneLifelineMessage__Group_5__1__Impl4289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__0__Impl_in_rule__OneLifelineEndBlock__Group__04323 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__1_in_rule__OneLifelineEndBlock__Group__04326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__LifelineAssignment_0_in_rule__OneLifelineEndBlock__Group__0__Impl4353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__1__Impl_in_rule__OneLifelineEndBlock__Group__14383 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__2_in_rule__OneLifelineEndBlock__Group__14386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__EndBlockAssignment_1_in_rule__OneLifelineEndBlock__Group__1__Impl4413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__2__Impl_in_rule__OneLifelineEndBlock__Group__24443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__EndBlockCountAssignment_2_in_rule__OneLifelineEndBlock__Group__2__Impl4470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__04507 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__04510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl4537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__14567 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__14570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__OneLifelineNote__Group__1__Impl4598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__24629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl4656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Destroy__Group__0__Impl_in_rule__Destroy__Group__04692 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_rule__Destroy__Group__1_in_rule__Destroy__Group__04695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Destroy__LifelineAssignment_0_in_rule__Destroy__Group__0__Impl4722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Destroy__Group__1__Impl_in_rule__Destroy__Group__14752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Destroy__DestroyAssignment_1_in_rule__Destroy__Group__1__Impl4779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__04813 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__04816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__Fragment__Group__0__Impl4844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__14875 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__14878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl4905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__24935 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__24938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__FragmentContentsAssignment_2_in_rule__Fragment__Group__2__Impl4965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__34995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__FragmentContentsAssignment_3_in_rule__Fragment__Group__3__Impl5022 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__0__Impl_in_rule__FragmentContent__Group__05061 = new BitSet(new long[]{0x0000000380000020L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__1_in_rule__FragmentContent__Group__05064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__FragmentContent__Group__0__Impl5092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__1__Impl_in_rule__FragmentContent__Group__15123 = new BitSet(new long[]{0x0000000380000020L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__2_in_rule__FragmentContent__Group__15126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group_1__0_in_rule__FragmentContent__Group__1__Impl5153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__2__Impl_in_rule__FragmentContent__Group__25184 = new BitSet(new long[]{0x0000000280100020L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__3_in_rule__FragmentContent__Group__25187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__InteractionsAssignment_2_in_rule__FragmentContent__Group__2__Impl5214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__3__Impl_in_rule__FragmentContent__Group__35244 = new BitSet(new long[]{0x0000000280100020L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__4_in_rule__FragmentContent__Group__35247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__InteractionsAssignment_3_in_rule__FragmentContent__Group__3__Impl5274 = new BitSet(new long[]{0x0000000280000022L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group__4__Impl_in_rule__FragmentContent__Group__45305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__FragmentContent__Group__4__Impl5333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group_1__0__Impl_in_rule__FragmentContent__Group_1__05374 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group_1__1_in_rule__FragmentContent__Group_1__05377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__FragmentContent__Group_1__0__Impl5405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__Group_1__1__Impl_in_rule__FragmentContent__Group_1__15436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FragmentContent__LabelAssignment_1_1_in_rule__FragmentContent__Group_1__1__Impl5463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__05497 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__05500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__Refinement__Group__0__Impl5528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__15559 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__15562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Refinement__Group__1__Impl5590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__25621 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__25624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__Refinement__Group__2__Impl5652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__35683 = new BitSet(new long[]{0x0000000900000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__35686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_3_in_rule__Refinement__Group__3__Impl5713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__45743 = new BitSet(new long[]{0x0000000900000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__45746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_4__0_in_rule__Refinement__Group__4__Impl5773 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__55804 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Refinement__Group__6_in_rule__Refinement__Group__55807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__Refinement__Group__5__Impl5835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__6__Impl_in_rule__Refinement__Group__65866 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__7_in_rule__Refinement__Group__65869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LabelAssignment_6_in_rule__Refinement__Group__6__Impl5896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__7__Impl_in_rule__Refinement__Group__75926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Refinement__Group__7__Impl5954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_4__0__Impl_in_rule__Refinement__Group_4__06001 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group_4__1_in_rule__Refinement__Group_4__06004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__Refinement__Group_4__0__Impl6032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_4__1__Impl_in_rule__Refinement__Group_4__16063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_4_1_in_rule__Refinement__Group_4__1__Impl6090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_26129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_rule__SequenceDiagram__LocalsAssignment_3_16160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_46191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_56222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_0_16253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_1_16284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_2_16315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_3_16346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_16377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_36408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_06443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTransitionType_in_rule__TwoLifelineMessage__TransitionTypeAssignment_16478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__CaptionAssignment_26509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_46544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__TwoLifelineMessage__StartBlockLeftAssignment_5_06584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__TwoLifelineMessage__EndBlockLeftAssignment_5_1_06628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__EndBlockLeftCountAssignment_5_1_16667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__TwoLifelineMessage__StartBlockRightAssignment_6_06703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__TwoLifelineMessage__EndBlockRightAssignment_6_1_06747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__EndBlockRightCountAssignment_6_1_16786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_16817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_16848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_06883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTransitionType_in_rule__OneLifelineMessage__TransitionTypeAssignment_16918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__CaptionAssignment_36949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rule__OneLifelineMessage__StartBlockAssignment_4_06985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__OneLifelineMessage__EndBlockAssignment_4_1_07029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndBlockCountAssignment_4_1_17068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_5_17099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineEndBlock__LifelineAssignment_07134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__OneLifelineEndBlock__EndBlockAssignment_17174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineEndBlock__EndBlockCountAssignment_27213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_07248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_27283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Destroy__LifelineAssignment_07318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rule__Destroy__DestroyAssignment_17358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_17397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragmentContent_in_rule__Fragment__FragmentContentsAssignment_27428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragmentContent_in_rule__Fragment__FragmentContentsAssignment_37459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FragmentContent__LabelAssignment_1_17490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__FragmentContent__InteractionsAssignment_27521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__FragmentContent__InteractionsAssignment_37552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_37587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_4_17626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_67661 = new BitSet(new long[]{0x0000000000000002L});

}