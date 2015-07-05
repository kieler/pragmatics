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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'lost'", "'found'", "'async'", "'create'", "'response'", "'sync'", "'Char'", "'Boolean'", "'Integer'", "'Float'", "'sequenceDiagram'", "'{'", "'}'", "'lifeline'", "'as'", "'to'", "'sourceNote'", "'targetNote'", "'note'", "'endBlock'", "'destroy'", "'fragment'", "'label'", "'refinement'", "'lifelines'", "','", "'sourceStartBlock'", "'sourceEndBlock'", "'targetStartBlock'", "'targetEndBlock'", "'startBlock'"
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:97:1: ruleLocalVariable : ( ( rule__LocalVariable__Group__0 ) ) ;
    public final void ruleLocalVariable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:101:2: ( ( ( rule__LocalVariable__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__LocalVariable__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:102:1: ( ( rule__LocalVariable__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:103:1: ( rule__LocalVariable__Group__0 )
            {
             before(grammarAccess.getLocalVariableAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:1: ( rule__LocalVariable__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:104:2: rule__LocalVariable__Group__0
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group__0_in_ruleLocalVariable154);
            rule__LocalVariable__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getGroup()); 

            }


            }

        }
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


    // $ANTLR start "entryRuleDestroyLifelineEvent"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:284:1: entryRuleDestroyLifelineEvent : ruleDestroyLifelineEvent EOF ;
    public final void entryRuleDestroyLifelineEvent() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:285:1: ( ruleDestroyLifelineEvent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:286:1: ruleDestroyLifelineEvent EOF
            {
             before(grammarAccess.getDestroyLifelineEventRule()); 
            pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent541);
            ruleDestroyLifelineEvent();

            state._fsp--;

             after(grammarAccess.getDestroyLifelineEventRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroyLifelineEvent548); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:293:1: ruleDestroyLifelineEvent : ( ( rule__DestroyLifelineEvent__Group__0 ) ) ;
    public final void ruleDestroyLifelineEvent() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:297:2: ( ( ( rule__DestroyLifelineEvent__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__DestroyLifelineEvent__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:298:1: ( ( rule__DestroyLifelineEvent__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:299:1: ( rule__DestroyLifelineEvent__Group__0 )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:1: ( rule__DestroyLifelineEvent__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:300:2: rule__DestroyLifelineEvent__Group__0
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__0_in_ruleDestroyLifelineEvent574);
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


    // $ANTLR start "entryRuleSection"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:340:1: entryRuleSection : ruleSection EOF ;
    public final void entryRuleSection() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:341:1: ( ruleSection EOF )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:342:1: ruleSection EOF
            {
             before(grammarAccess.getSectionRule()); 
            pushFollow(FOLLOW_ruleSection_in_entryRuleSection661);
            ruleSection();

            state._fsp--;

             after(grammarAccess.getSectionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSection668); 

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:349:1: ruleSection : ( ( rule__Section__Group__0 ) ) ;
    public final void ruleSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:353:2: ( ( ( rule__Section__Group__0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:354:1: ( ( rule__Section__Group__0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:354:1: ( ( rule__Section__Group__0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:355:1: ( rule__Section__Group__0 )
            {
             before(grammarAccess.getSectionAccess().getGroup()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:356:1: ( rule__Section__Group__0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:356:2: rule__Section__Group__0
            {
            pushFollow(FOLLOW_rule__Section__Group__0_in_ruleSection694);
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


    // $ANTLR start "ruleMessageType"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:397:1: ruleMessageType : ( ( rule__MessageType__Alternatives ) ) ;
    public final void ruleMessageType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:401:1: ( ( ( rule__MessageType__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:402:1: ( ( rule__MessageType__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:402:1: ( ( rule__MessageType__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:403:1: ( rule__MessageType__Alternatives )
            {
             before(grammarAccess.getMessageTypeAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:404:1: ( rule__MessageType__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:404:2: rule__MessageType__Alternatives
            {
            pushFollow(FOLLOW_rule__MessageType__Alternatives_in_ruleMessageType791);
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


    // $ANTLR start "ruleDataType"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:416:1: ruleDataType : ( ( rule__DataType__Alternatives ) ) ;
    public final void ruleDataType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:420:1: ( ( ( rule__DataType__Alternatives ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:421:1: ( ( rule__DataType__Alternatives ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:421:1: ( ( rule__DataType__Alternatives ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:422:1: ( rule__DataType__Alternatives )
            {
             before(grammarAccess.getDataTypeAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:423:1: ( rule__DataType__Alternatives )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:423:2: rule__DataType__Alternatives
            {
            pushFollow(FOLLOW_rule__DataType__Alternatives_in_ruleDataType827);
            rule__DataType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getDataTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDataType"


    // $ANTLR start "rule__Interaction__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:434:1: rule__Interaction__Alternatives : ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineEndBlock ) | ( ruleOneLifelineNote ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) );
    public final void rule__Interaction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:438:1: ( ( ruleTwoLifelineMessage ) | ( ruleOneLifelineMessage ) | ( ruleFragment ) | ( ruleOneLifelineEndBlock ) | ( ruleOneLifelineNote ) | ( ruleDestroyLifelineEvent ) | ( ruleRefinement ) )
            int alt1=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                switch ( input.LA(2) ) {
                case 14:
                case 15:
                case 16:
                case 17:
                    {
                    alt1=1;
                    }
                    break;
                case 30:
                    {
                    alt1=5;
                    }
                    break;
                case 12:
                case 13:
                    {
                    alt1=2;
                    }
                    break;
                case 31:
                    {
                    alt1=4;
                    }
                    break;
                case 32:
                    {
                    alt1=6;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }

                }
                break;
            case 33:
                {
                alt1=3;
                }
                break;
            case 35:
                {
                alt1=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:439:1: ( ruleTwoLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:439:1: ( ruleTwoLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:440:1: ruleTwoLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives862);
                    ruleTwoLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:445:6: ( ruleOneLifelineMessage )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:445:6: ( ruleOneLifelineMessage )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:446:1: ruleOneLifelineMessage
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives879);
                    ruleOneLifelineMessage();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:451:6: ( ruleFragment )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:451:6: ( ruleFragment )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:452:1: ruleFragment
                    {
                     before(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFragment_in_rule__Interaction__Alternatives896);
                    ruleFragment();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:457:6: ( ruleOneLifelineEndBlock )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:457:6: ( ruleOneLifelineEndBlock )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:458:1: ruleOneLifelineEndBlock
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineEndBlockParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleOneLifelineEndBlock_in_rule__Interaction__Alternatives913);
                    ruleOneLifelineEndBlock();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineEndBlockParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:463:6: ( ruleOneLifelineNote )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:463:6: ( ruleOneLifelineNote )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:464:1: ruleOneLifelineNote
                    {
                     before(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives930);
                    ruleOneLifelineNote();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:469:6: ( ruleDestroyLifelineEvent )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:469:6: ( ruleDestroyLifelineEvent )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:470:1: ruleDestroyLifelineEvent
                    {
                     before(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_rule__Interaction__Alternatives947);
                    ruleDestroyLifelineEvent();

                    state._fsp--;

                     after(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:475:6: ( ruleRefinement )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:475:6: ( ruleRefinement )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:476:1: ruleRefinement
                    {
                     before(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 
                    pushFollow(FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives964);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:486:1: rule__TwoLifelineMessage__Alternatives_5 : ( ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__Group_5_1__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:490:1: ( ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) ) | ( ( rule__TwoLifelineMessage__Group_5_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==38) ) {
                alt2=1;
            }
            else if ( (LA2_0==39) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:491:1: ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:491:1: ( ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:492:1: ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockAssignment_5_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:493:1: ( rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:493:2: rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_5996);
                    rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockAssignment_5_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:497:6: ( ( rule__TwoLifelineMessage__Group_5_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:497:6: ( ( rule__TwoLifelineMessage__Group_5_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:498:1: ( rule__TwoLifelineMessage__Group_5_1__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_5_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:499:1: ( rule__TwoLifelineMessage__Group_5_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:499:2: rule__TwoLifelineMessage__Group_5_1__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__0_in_rule__TwoLifelineMessage__Alternatives_51014);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:508:1: rule__TwoLifelineMessage__Alternatives_6 : ( ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__Group_6_1__0 ) ) );
    public final void rule__TwoLifelineMessage__Alternatives_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:512:1: ( ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) ) | ( ( rule__TwoLifelineMessage__Group_6_1__0 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==40) ) {
                alt3=1;
            }
            else if ( (LA3_0==41) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:513:1: ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:513:1: ( ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:514:1: ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockAssignment_6_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:515:1: ( rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:515:2: rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_61047);
                    rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockAssignment_6_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:519:6: ( ( rule__TwoLifelineMessage__Group_6_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:519:6: ( ( rule__TwoLifelineMessage__Group_6_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:520:1: ( rule__TwoLifelineMessage__Group_6_1__0 )
                    {
                     before(grammarAccess.getTwoLifelineMessageAccess().getGroup_6_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:521:1: ( rule__TwoLifelineMessage__Group_6_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:521:2: rule__TwoLifelineMessage__Group_6_1__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__0_in_rule__TwoLifelineMessage__Alternatives_61065);
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


    // $ANTLR start "rule__OneLifelineMessage__MessageTypeAlternatives_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:530:1: rule__OneLifelineMessage__MessageTypeAlternatives_1_0 : ( ( 'lost' ) | ( 'found' ) );
    public final void rule__OneLifelineMessage__MessageTypeAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:534:1: ( ( 'lost' ) | ( 'found' ) )
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
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:535:1: ( 'lost' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:535:1: ( 'lost' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:536:1: 'lost'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostKeyword_1_0_0()); 
                    match(input,12,FOLLOW_12_in_rule__OneLifelineMessage__MessageTypeAlternatives_1_01099); 
                     after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:543:6: ( 'found' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:543:6: ( 'found' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:544:1: 'found'
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeFoundKeyword_1_0_1()); 
                    match(input,13,FOLLOW_13_in_rule__OneLifelineMessage__MessageTypeAlternatives_1_01119); 
                     after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeFoundKeyword_1_0_1()); 

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
    // $ANTLR end "rule__OneLifelineMessage__MessageTypeAlternatives_1_0"


    // $ANTLR start "rule__OneLifelineMessage__Alternatives_3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:556:1: rule__OneLifelineMessage__Alternatives_3 : ( ( ( rule__OneLifelineMessage__StartBlockAssignment_3_0 ) ) | ( ( rule__OneLifelineMessage__Group_3_1__0 ) ) );
    public final void rule__OneLifelineMessage__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:560:1: ( ( ( rule__OneLifelineMessage__StartBlockAssignment_3_0 ) ) | ( ( rule__OneLifelineMessage__Group_3_1__0 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==42) ) {
                alt5=1;
            }
            else if ( (LA5_0==31) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:561:1: ( ( rule__OneLifelineMessage__StartBlockAssignment_3_0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:561:1: ( ( rule__OneLifelineMessage__StartBlockAssignment_3_0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:562:1: ( rule__OneLifelineMessage__StartBlockAssignment_3_0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getStartBlockAssignment_3_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:563:1: ( rule__OneLifelineMessage__StartBlockAssignment_3_0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:563:2: rule__OneLifelineMessage__StartBlockAssignment_3_0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__StartBlockAssignment_3_0_in_rule__OneLifelineMessage__Alternatives_31153);
                    rule__OneLifelineMessage__StartBlockAssignment_3_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getStartBlockAssignment_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:567:6: ( ( rule__OneLifelineMessage__Group_3_1__0 ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:567:6: ( ( rule__OneLifelineMessage__Group_3_1__0 ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:568:1: ( rule__OneLifelineMessage__Group_3_1__0 )
                    {
                     before(grammarAccess.getOneLifelineMessageAccess().getGroup_3_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:569:1: ( rule__OneLifelineMessage__Group_3_1__0 )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:569:2: rule__OneLifelineMessage__Group_3_1__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_3_1__0_in_rule__OneLifelineMessage__Alternatives_31171);
                    rule__OneLifelineMessage__Group_3_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOneLifelineMessageAccess().getGroup_3_1()); 

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
    // $ANTLR end "rule__OneLifelineMessage__Alternatives_3"


    // $ANTLR start "rule__MessageType__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:578:1: rule__MessageType__Alternatives : ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) );
    public final void rule__MessageType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:582:1: ( ( ( 'async' ) ) | ( ( 'create' ) ) | ( ( 'response' ) ) | ( ( 'sync' ) ) )
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
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:583:1: ( ( 'async' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:583:1: ( ( 'async' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:584:1: ( 'async' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:585:1: ( 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:585:3: 'async'
                    {
                    match(input,14,FOLLOW_14_in_rule__MessageType__Alternatives1205); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:590:6: ( ( 'create' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:590:6: ( ( 'create' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:591:1: ( 'create' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:592:1: ( 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:592:3: 'create'
                    {
                    match(input,15,FOLLOW_15_in_rule__MessageType__Alternatives1226); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:597:6: ( ( 'response' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:597:6: ( ( 'response' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:598:1: ( 'response' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:599:1: ( 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:599:3: 'response'
                    {
                    match(input,16,FOLLOW_16_in_rule__MessageType__Alternatives1247); 

                    }

                     after(grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:604:6: ( ( 'sync' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:604:6: ( ( 'sync' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:605:1: ( 'sync' )
                    {
                     before(grammarAccess.getMessageTypeAccess().getSyncEnumLiteralDeclaration_3()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:606:1: ( 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:606:3: 'sync'
                    {
                    match(input,17,FOLLOW_17_in_rule__MessageType__Alternatives1268); 

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


    // $ANTLR start "rule__DataType__Alternatives"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:616:1: rule__DataType__Alternatives : ( ( ( 'Char' ) ) | ( ( 'Boolean' ) ) | ( ( 'Integer' ) ) | ( ( 'Float' ) ) );
    public final void rule__DataType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:620:1: ( ( ( 'Char' ) ) | ( ( 'Boolean' ) ) | ( ( 'Integer' ) ) | ( ( 'Float' ) ) )
            int alt7=4;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt7=1;
                }
                break;
            case 19:
                {
                alt7=2;
                }
                break;
            case 20:
                {
                alt7=3;
                }
                break;
            case 21:
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
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:621:1: ( ( 'Char' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:621:1: ( ( 'Char' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:622:1: ( 'Char' )
                    {
                     before(grammarAccess.getDataTypeAccess().getCharEnumLiteralDeclaration_0()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:623:1: ( 'Char' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:623:3: 'Char'
                    {
                    match(input,18,FOLLOW_18_in_rule__DataType__Alternatives1304); 

                    }

                     after(grammarAccess.getDataTypeAccess().getCharEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:628:6: ( ( 'Boolean' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:628:6: ( ( 'Boolean' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:629:1: ( 'Boolean' )
                    {
                     before(grammarAccess.getDataTypeAccess().getBooleanEnumLiteralDeclaration_1()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:630:1: ( 'Boolean' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:630:3: 'Boolean'
                    {
                    match(input,19,FOLLOW_19_in_rule__DataType__Alternatives1325); 

                    }

                     after(grammarAccess.getDataTypeAccess().getBooleanEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:635:6: ( ( 'Integer' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:635:6: ( ( 'Integer' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:636:1: ( 'Integer' )
                    {
                     before(grammarAccess.getDataTypeAccess().getIntegerEnumLiteralDeclaration_2()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:637:1: ( 'Integer' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:637:3: 'Integer'
                    {
                    match(input,20,FOLLOW_20_in_rule__DataType__Alternatives1346); 

                    }

                     after(grammarAccess.getDataTypeAccess().getIntegerEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:642:6: ( ( 'Float' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:642:6: ( ( 'Float' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:643:1: ( 'Float' )
                    {
                     before(grammarAccess.getDataTypeAccess().getFloatEnumLiteralDeclaration_3()); 
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:644:1: ( 'Float' )
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:644:3: 'Float'
                    {
                    match(input,21,FOLLOW_21_in_rule__DataType__Alternatives1367); 

                    }

                     after(grammarAccess.getDataTypeAccess().getFloatEnumLiteralDeclaration_3()); 

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
    // $ANTLR end "rule__DataType__Alternatives"


    // $ANTLR start "rule__SequenceDiagram__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:656:1: rule__SequenceDiagram__Group__0 : rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 ;
    public final void rule__SequenceDiagram__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:660:1: ( rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:661:2: rule__SequenceDiagram__Group__0__Impl rule__SequenceDiagram__Group__1
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01400);
            rule__SequenceDiagram__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01403);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:668:1: rule__SequenceDiagram__Group__0__Impl : ( () ) ;
    public final void rule__SequenceDiagram__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:672:1: ( ( () ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:673:1: ( () )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:673:1: ( () )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:674:1: ()
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramAction_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:675:1: ()
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:677:1: 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:687:1: rule__SequenceDiagram__Group__1 : rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 ;
    public final void rule__SequenceDiagram__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:691:1: ( rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:692:2: rule__SequenceDiagram__Group__1__Impl rule__SequenceDiagram__Group__2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11461);
            rule__SequenceDiagram__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11464);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:699:1: rule__SequenceDiagram__Group__1__Impl : ( 'sequenceDiagram' ) ;
    public final void rule__SequenceDiagram__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:703:1: ( ( 'sequenceDiagram' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:704:1: ( 'sequenceDiagram' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:704:1: ( 'sequenceDiagram' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:705:1: 'sequenceDiagram'
            {
             before(grammarAccess.getSequenceDiagramAccess().getSequenceDiagramKeyword_1()); 
            match(input,22,FOLLOW_22_in_rule__SequenceDiagram__Group__1__Impl1492); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:718:1: rule__SequenceDiagram__Group__2 : rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 ;
    public final void rule__SequenceDiagram__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:722:1: ( rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:723:2: rule__SequenceDiagram__Group__2__Impl rule__SequenceDiagram__Group__3
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21523);
            rule__SequenceDiagram__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21526);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:730:1: rule__SequenceDiagram__Group__2__Impl : ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) ;
    public final void rule__SequenceDiagram__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:734:1: ( ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:735:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:735:1: ( ( rule__SequenceDiagram__DiagramNameAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:736:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:737:1: ( rule__SequenceDiagram__DiagramNameAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:737:2: rule__SequenceDiagram__DiagramNameAssignment_2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1553);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:747:1: rule__SequenceDiagram__Group__3 : rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 ;
    public final void rule__SequenceDiagram__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:751:1: ( rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:752:2: rule__SequenceDiagram__Group__3__Impl rule__SequenceDiagram__Group__4
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31583);
            rule__SequenceDiagram__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31586);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:759:1: rule__SequenceDiagram__Group__3__Impl : ( ( rule__SequenceDiagram__Group_3__0 )? ) ;
    public final void rule__SequenceDiagram__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:763:1: ( ( ( rule__SequenceDiagram__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:764:1: ( ( rule__SequenceDiagram__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:764:1: ( ( rule__SequenceDiagram__Group_3__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:765:1: ( rule__SequenceDiagram__Group_3__0 )?
            {
             before(grammarAccess.getSequenceDiagramAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:766:1: ( rule__SequenceDiagram__Group_3__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:766:2: rule__SequenceDiagram__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__0_in_rule__SequenceDiagram__Group__3__Impl1613);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:776:1: rule__SequenceDiagram__Group__4 : rule__SequenceDiagram__Group__4__Impl rule__SequenceDiagram__Group__5 ;
    public final void rule__SequenceDiagram__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:780:1: ( rule__SequenceDiagram__Group__4__Impl rule__SequenceDiagram__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:781:2: rule__SequenceDiagram__Group__4__Impl rule__SequenceDiagram__Group__5
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41644);
            rule__SequenceDiagram__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group__5_in_rule__SequenceDiagram__Group__41647);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:788:1: rule__SequenceDiagram__Group__4__Impl : ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* ) ;
    public final void rule__SequenceDiagram__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:792:1: ( ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:793:1: ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:793:1: ( ( rule__SequenceDiagram__LifelinesAssignment_4 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:794:1: ( rule__SequenceDiagram__LifelinesAssignment_4 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:795:1: ( rule__SequenceDiagram__LifelinesAssignment_4 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==25) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:795:2: rule__SequenceDiagram__LifelinesAssignment_4
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__LifelinesAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1674);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:805:1: rule__SequenceDiagram__Group__5 : rule__SequenceDiagram__Group__5__Impl ;
    public final void rule__SequenceDiagram__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:809:1: ( rule__SequenceDiagram__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:810:2: rule__SequenceDiagram__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group__5__Impl_in_rule__SequenceDiagram__Group__51705);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:816:1: rule__SequenceDiagram__Group__5__Impl : ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* ) ;
    public final void rule__SequenceDiagram__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:820:1: ( ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:821:1: ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:821:1: ( ( rule__SequenceDiagram__InteractionsAssignment_5 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:822:1: ( rule__SequenceDiagram__InteractionsAssignment_5 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsAssignment_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:823:1: ( rule__SequenceDiagram__InteractionsAssignment_5 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_ID||LA10_0==33||LA10_0==35) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:823:2: rule__SequenceDiagram__InteractionsAssignment_5
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__InteractionsAssignment_5_in_rule__SequenceDiagram__Group__5__Impl1732);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:845:1: rule__SequenceDiagram__Group_3__0 : rule__SequenceDiagram__Group_3__0__Impl rule__SequenceDiagram__Group_3__1 ;
    public final void rule__SequenceDiagram__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:849:1: ( rule__SequenceDiagram__Group_3__0__Impl rule__SequenceDiagram__Group_3__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:850:2: rule__SequenceDiagram__Group_3__0__Impl rule__SequenceDiagram__Group_3__1
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__0__Impl_in_rule__SequenceDiagram__Group_3__01775);
            rule__SequenceDiagram__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__1_in_rule__SequenceDiagram__Group_3__01778);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:857:1: rule__SequenceDiagram__Group_3__0__Impl : ( '{' ) ;
    public final void rule__SequenceDiagram__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:861:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:862:1: ( '{' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:862:1: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:863:1: '{'
            {
             before(grammarAccess.getSequenceDiagramAccess().getLeftCurlyBracketKeyword_3_0()); 
            match(input,23,FOLLOW_23_in_rule__SequenceDiagram__Group_3__0__Impl1806); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:876:1: rule__SequenceDiagram__Group_3__1 : rule__SequenceDiagram__Group_3__1__Impl rule__SequenceDiagram__Group_3__2 ;
    public final void rule__SequenceDiagram__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:880:1: ( rule__SequenceDiagram__Group_3__1__Impl rule__SequenceDiagram__Group_3__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:881:2: rule__SequenceDiagram__Group_3__1__Impl rule__SequenceDiagram__Group_3__2
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__1__Impl_in_rule__SequenceDiagram__Group_3__11837);
            rule__SequenceDiagram__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__2_in_rule__SequenceDiagram__Group_3__11840);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:888:1: rule__SequenceDiagram__Group_3__1__Impl : ( ( rule__SequenceDiagram__LocalsAssignment_3_1 ) ) ;
    public final void rule__SequenceDiagram__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:892:1: ( ( ( rule__SequenceDiagram__LocalsAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:893:1: ( ( rule__SequenceDiagram__LocalsAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:893:1: ( ( rule__SequenceDiagram__LocalsAssignment_3_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:894:1: ( rule__SequenceDiagram__LocalsAssignment_3_1 )
            {
             before(grammarAccess.getSequenceDiagramAccess().getLocalsAssignment_3_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:895:1: ( rule__SequenceDiagram__LocalsAssignment_3_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:895:2: rule__SequenceDiagram__LocalsAssignment_3_1
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__LocalsAssignment_3_1_in_rule__SequenceDiagram__Group_3__1__Impl1867);
            rule__SequenceDiagram__LocalsAssignment_3_1();

            state._fsp--;


            }

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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:905:1: rule__SequenceDiagram__Group_3__2 : rule__SequenceDiagram__Group_3__2__Impl rule__SequenceDiagram__Group_3__3 ;
    public final void rule__SequenceDiagram__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:909:1: ( rule__SequenceDiagram__Group_3__2__Impl rule__SequenceDiagram__Group_3__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:910:2: rule__SequenceDiagram__Group_3__2__Impl rule__SequenceDiagram__Group_3__3
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__2__Impl_in_rule__SequenceDiagram__Group_3__21897);
            rule__SequenceDiagram__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__3_in_rule__SequenceDiagram__Group_3__21900);
            rule__SequenceDiagram__Group_3__3();

            state._fsp--;


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:917:1: rule__SequenceDiagram__Group_3__2__Impl : ( ( rule__SequenceDiagram__LocalsAssignment_3_2 )* ) ;
    public final void rule__SequenceDiagram__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:921:1: ( ( ( rule__SequenceDiagram__LocalsAssignment_3_2 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:922:1: ( ( rule__SequenceDiagram__LocalsAssignment_3_2 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:922:1: ( ( rule__SequenceDiagram__LocalsAssignment_3_2 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:923:1: ( rule__SequenceDiagram__LocalsAssignment_3_2 )*
            {
             before(grammarAccess.getSequenceDiagramAccess().getLocalsAssignment_3_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:924:1: ( rule__SequenceDiagram__LocalsAssignment_3_2 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=18 && LA11_0<=21)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:924:2: rule__SequenceDiagram__LocalsAssignment_3_2
            	    {
            	    pushFollow(FOLLOW_rule__SequenceDiagram__LocalsAssignment_3_2_in_rule__SequenceDiagram__Group_3__2__Impl1927);
            	    rule__SequenceDiagram__LocalsAssignment_3_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getSequenceDiagramAccess().getLocalsAssignment_3_2()); 

            }


            }

        }
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


    // $ANTLR start "rule__SequenceDiagram__Group_3__3"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:934:1: rule__SequenceDiagram__Group_3__3 : rule__SequenceDiagram__Group_3__3__Impl ;
    public final void rule__SequenceDiagram__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:938:1: ( rule__SequenceDiagram__Group_3__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:939:2: rule__SequenceDiagram__Group_3__3__Impl
            {
            pushFollow(FOLLOW_rule__SequenceDiagram__Group_3__3__Impl_in_rule__SequenceDiagram__Group_3__31958);
            rule__SequenceDiagram__Group_3__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__3"


    // $ANTLR start "rule__SequenceDiagram__Group_3__3__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:945:1: rule__SequenceDiagram__Group_3__3__Impl : ( '}' ) ;
    public final void rule__SequenceDiagram__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:949:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:950:1: ( '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:950:1: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:951:1: '}'
            {
             before(grammarAccess.getSequenceDiagramAccess().getRightCurlyBracketKeyword_3_3()); 
            match(input,24,FOLLOW_24_in_rule__SequenceDiagram__Group_3__3__Impl1986); 
             after(grammarAccess.getSequenceDiagramAccess().getRightCurlyBracketKeyword_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__Group_3__3__Impl"


    // $ANTLR start "rule__LocalVariable__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:972:1: rule__LocalVariable__Group__0 : rule__LocalVariable__Group__0__Impl rule__LocalVariable__Group__1 ;
    public final void rule__LocalVariable__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:976:1: ( rule__LocalVariable__Group__0__Impl rule__LocalVariable__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:977:2: rule__LocalVariable__Group__0__Impl rule__LocalVariable__Group__1
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group__0__Impl_in_rule__LocalVariable__Group__02025);
            rule__LocalVariable__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalVariable__Group__1_in_rule__LocalVariable__Group__02028);
            rule__LocalVariable__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group__0"


    // $ANTLR start "rule__LocalVariable__Group__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:984:1: rule__LocalVariable__Group__0__Impl : ( ( rule__LocalVariable__TypeAssignment_0 ) ) ;
    public final void rule__LocalVariable__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:988:1: ( ( ( rule__LocalVariable__TypeAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:989:1: ( ( rule__LocalVariable__TypeAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:989:1: ( ( rule__LocalVariable__TypeAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:990:1: ( rule__LocalVariable__TypeAssignment_0 )
            {
             before(grammarAccess.getLocalVariableAccess().getTypeAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:991:1: ( rule__LocalVariable__TypeAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:991:2: rule__LocalVariable__TypeAssignment_0
            {
            pushFollow(FOLLOW_rule__LocalVariable__TypeAssignment_0_in_rule__LocalVariable__Group__0__Impl2055);
            rule__LocalVariable__TypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getTypeAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group__0__Impl"


    // $ANTLR start "rule__LocalVariable__Group__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1001:1: rule__LocalVariable__Group__1 : rule__LocalVariable__Group__1__Impl ;
    public final void rule__LocalVariable__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1005:1: ( rule__LocalVariable__Group__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1006:2: rule__LocalVariable__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalVariable__Group__1__Impl_in_rule__LocalVariable__Group__12085);
            rule__LocalVariable__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group__1"


    // $ANTLR start "rule__LocalVariable__Group__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1012:1: rule__LocalVariable__Group__1__Impl : ( ( rule__LocalVariable__NameAssignment_1 ) ) ;
    public final void rule__LocalVariable__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1016:1: ( ( ( rule__LocalVariable__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1017:1: ( ( rule__LocalVariable__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1017:1: ( ( rule__LocalVariable__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1018:1: ( rule__LocalVariable__NameAssignment_1 )
            {
             before(grammarAccess.getLocalVariableAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1019:1: ( rule__LocalVariable__NameAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1019:2: rule__LocalVariable__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__LocalVariable__NameAssignment_1_in_rule__LocalVariable__Group__1__Impl2112);
            rule__LocalVariable__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLocalVariableAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__Group__1__Impl"


    // $ANTLR start "rule__Lifeline__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1033:1: rule__Lifeline__Group__0 : rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1 ;
    public final void rule__Lifeline__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1037:1: ( rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1038:2: rule__Lifeline__Group__0__Impl rule__Lifeline__Group__1
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__0__Impl_in_rule__Lifeline__Group__02146);
            rule__Lifeline__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__1_in_rule__Lifeline__Group__02149);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1045:1: rule__Lifeline__Group__0__Impl : ( 'lifeline' ) ;
    public final void rule__Lifeline__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1049:1: ( ( 'lifeline' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1050:1: ( 'lifeline' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1050:1: ( 'lifeline' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1051:1: 'lifeline'
            {
             before(grammarAccess.getLifelineAccess().getLifelineKeyword_0()); 
            match(input,25,FOLLOW_25_in_rule__Lifeline__Group__0__Impl2177); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1064:1: rule__Lifeline__Group__1 : rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2 ;
    public final void rule__Lifeline__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1068:1: ( rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1069:2: rule__Lifeline__Group__1__Impl rule__Lifeline__Group__2
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__1__Impl_in_rule__Lifeline__Group__12208);
            rule__Lifeline__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__2_in_rule__Lifeline__Group__12211);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1076:1: rule__Lifeline__Group__1__Impl : ( ( rule__Lifeline__CaptionAssignment_1 ) ) ;
    public final void rule__Lifeline__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1080:1: ( ( ( rule__Lifeline__CaptionAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1081:1: ( ( rule__Lifeline__CaptionAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1081:1: ( ( rule__Lifeline__CaptionAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1082:1: ( rule__Lifeline__CaptionAssignment_1 )
            {
             before(grammarAccess.getLifelineAccess().getCaptionAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1083:1: ( rule__Lifeline__CaptionAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1083:2: rule__Lifeline__CaptionAssignment_1
            {
            pushFollow(FOLLOW_rule__Lifeline__CaptionAssignment_1_in_rule__Lifeline__Group__1__Impl2238);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1093:1: rule__Lifeline__Group__2 : rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3 ;
    public final void rule__Lifeline__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1097:1: ( rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1098:2: rule__Lifeline__Group__2__Impl rule__Lifeline__Group__3
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__2__Impl_in_rule__Lifeline__Group__22268);
            rule__Lifeline__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Lifeline__Group__3_in_rule__Lifeline__Group__22271);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1105:1: rule__Lifeline__Group__2__Impl : ( 'as' ) ;
    public final void rule__Lifeline__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1109:1: ( ( 'as' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1110:1: ( 'as' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1110:1: ( 'as' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1111:1: 'as'
            {
             before(grammarAccess.getLifelineAccess().getAsKeyword_2()); 
            match(input,26,FOLLOW_26_in_rule__Lifeline__Group__2__Impl2299); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1124:1: rule__Lifeline__Group__3 : rule__Lifeline__Group__3__Impl ;
    public final void rule__Lifeline__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1128:1: ( rule__Lifeline__Group__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1129:2: rule__Lifeline__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Lifeline__Group__3__Impl_in_rule__Lifeline__Group__32330);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1135:1: rule__Lifeline__Group__3__Impl : ( ( rule__Lifeline__NameAssignment_3 ) ) ;
    public final void rule__Lifeline__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1139:1: ( ( ( rule__Lifeline__NameAssignment_3 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1140:1: ( ( rule__Lifeline__NameAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1140:1: ( ( rule__Lifeline__NameAssignment_3 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1141:1: ( rule__Lifeline__NameAssignment_3 )
            {
             before(grammarAccess.getLifelineAccess().getNameAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1142:1: ( rule__Lifeline__NameAssignment_3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1142:2: rule__Lifeline__NameAssignment_3
            {
            pushFollow(FOLLOW_rule__Lifeline__NameAssignment_3_in_rule__Lifeline__Group__3__Impl2357);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1160:1: rule__TwoLifelineMessage__Group__0 : rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 ;
    public final void rule__TwoLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1164:1: ( rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1165:2: rule__TwoLifelineMessage__Group__0__Impl rule__TwoLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__02395);
            rule__TwoLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__02398);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1172:1: rule__TwoLifelineMessage__Group__0__Impl : ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1176:1: ( ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1177:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1177:1: ( ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1178:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1179:1: ( rule__TwoLifelineMessage__SourceLifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1179:2: rule__TwoLifelineMessage__SourceLifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl2425);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1189:1: rule__TwoLifelineMessage__Group__1 : rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 ;
    public final void rule__TwoLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1193:1: ( rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1194:2: rule__TwoLifelineMessage__Group__1__Impl rule__TwoLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__12455);
            rule__TwoLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__12458);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1201:1: rule__TwoLifelineMessage__Group__1__Impl : ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1205:1: ( ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1206:1: ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1206:1: ( ( rule__TwoLifelineMessage__MessageTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1207:1: ( rule__TwoLifelineMessage__MessageTypeAssignment_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1208:1: ( rule__TwoLifelineMessage__MessageTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1208:2: rule__TwoLifelineMessage__MessageTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__MessageTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl2485);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1218:1: rule__TwoLifelineMessage__Group__2 : rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 ;
    public final void rule__TwoLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1222:1: ( rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1223:2: rule__TwoLifelineMessage__Group__2__Impl rule__TwoLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__22515);
            rule__TwoLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__22518);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1230:1: rule__TwoLifelineMessage__Group__2__Impl : ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) ) ;
    public final void rule__TwoLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1234:1: ( ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1235:1: ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1235:1: ( ( rule__TwoLifelineMessage__MessageAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1236:1: ( rule__TwoLifelineMessage__MessageAssignment_2 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1237:1: ( rule__TwoLifelineMessage__MessageAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1237:2: rule__TwoLifelineMessage__MessageAssignment_2
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__MessageAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl2545);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1247:1: rule__TwoLifelineMessage__Group__3 : rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 ;
    public final void rule__TwoLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1251:1: ( rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1252:2: rule__TwoLifelineMessage__Group__3__Impl rule__TwoLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__32575);
            rule__TwoLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__32578);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1259:1: rule__TwoLifelineMessage__Group__3__Impl : ( 'to' ) ;
    public final void rule__TwoLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1263:1: ( ( 'to' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1264:1: ( 'to' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1264:1: ( 'to' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1265:1: 'to'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3()); 
            match(input,27,FOLLOW_27_in_rule__TwoLifelineMessage__Group__3__Impl2606); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1278:1: rule__TwoLifelineMessage__Group__4 : rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 ;
    public final void rule__TwoLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1282:1: ( rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1283:2: rule__TwoLifelineMessage__Group__4__Impl rule__TwoLifelineMessage__Group__5
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__42637);
            rule__TwoLifelineMessage__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__42640);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1290:1: rule__TwoLifelineMessage__Group__4__Impl : ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) ;
    public final void rule__TwoLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1294:1: ( ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1295:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1295:1: ( ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1296:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1297:1: ( rule__TwoLifelineMessage__TargetLifelineAssignment_4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1297:2: rule__TwoLifelineMessage__TargetLifelineAssignment_4
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl2667);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1307:1: rule__TwoLifelineMessage__Group__5 : rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 ;
    public final void rule__TwoLifelineMessage__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1311:1: ( rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1312:2: rule__TwoLifelineMessage__Group__5__Impl rule__TwoLifelineMessage__Group__6
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__52697);
            rule__TwoLifelineMessage__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__52700);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1319:1: rule__TwoLifelineMessage__Group__5__Impl : ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) ;
    public final void rule__TwoLifelineMessage__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1323:1: ( ( ( rule__TwoLifelineMessage__Alternatives_5 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1324:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1324:1: ( ( rule__TwoLifelineMessage__Alternatives_5 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1325:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1326:1: ( rule__TwoLifelineMessage__Alternatives_5 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=38 && LA12_0<=39)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1326:2: rule__TwoLifelineMessage__Alternatives_5
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2727);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1336:1: rule__TwoLifelineMessage__Group__6 : rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 ;
    public final void rule__TwoLifelineMessage__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1340:1: ( rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1341:2: rule__TwoLifelineMessage__Group__6__Impl rule__TwoLifelineMessage__Group__7
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__62758);
            rule__TwoLifelineMessage__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__62761);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1348:1: rule__TwoLifelineMessage__Group__6__Impl : ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) ;
    public final void rule__TwoLifelineMessage__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1352:1: ( ( ( rule__TwoLifelineMessage__Alternatives_6 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1353:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1353:1: ( ( rule__TwoLifelineMessage__Alternatives_6 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1354:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getAlternatives_6()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1355:1: ( rule__TwoLifelineMessage__Alternatives_6 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=40 && LA13_0<=41)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1355:2: rule__TwoLifelineMessage__Alternatives_6
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl2788);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1365:1: rule__TwoLifelineMessage__Group__7 : rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 ;
    public final void rule__TwoLifelineMessage__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1369:1: ( rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1370:2: rule__TwoLifelineMessage__Group__7__Impl rule__TwoLifelineMessage__Group__8
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__72819);
            rule__TwoLifelineMessage__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__72822);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1377:1: rule__TwoLifelineMessage__Group__7__Impl : ( ( rule__TwoLifelineMessage__Group_7__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1381:1: ( ( ( rule__TwoLifelineMessage__Group_7__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1382:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1382:1: ( ( rule__TwoLifelineMessage__Group_7__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1383:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1384:1: ( rule__TwoLifelineMessage__Group_7__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==28) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1384:2: rule__TwoLifelineMessage__Group_7__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl2849);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1394:1: rule__TwoLifelineMessage__Group__8 : rule__TwoLifelineMessage__Group__8__Impl ;
    public final void rule__TwoLifelineMessage__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1398:1: ( rule__TwoLifelineMessage__Group__8__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1399:2: rule__TwoLifelineMessage__Group__8__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__82880);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1405:1: rule__TwoLifelineMessage__Group__8__Impl : ( ( rule__TwoLifelineMessage__Group_8__0 )? ) ;
    public final void rule__TwoLifelineMessage__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1409:1: ( ( ( rule__TwoLifelineMessage__Group_8__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1410:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1410:1: ( ( rule__TwoLifelineMessage__Group_8__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1411:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getGroup_8()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1412:1: ( rule__TwoLifelineMessage__Group_8__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==29) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1412:2: rule__TwoLifelineMessage__Group_8__0
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl2907);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1440:1: rule__TwoLifelineMessage__Group_5_1__0 : rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1 ;
    public final void rule__TwoLifelineMessage__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1444:1: ( rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1445:2: rule__TwoLifelineMessage__Group_5_1__0__Impl rule__TwoLifelineMessage__Group_5_1__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__0__Impl_in_rule__TwoLifelineMessage__Group_5_1__02956);
            rule__TwoLifelineMessage__Group_5_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__1_in_rule__TwoLifelineMessage__Group_5_1__02959);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1452:1: rule__TwoLifelineMessage__Group_5_1__0__Impl : ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1456:1: ( ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1457:1: ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1457:1: ( ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1458:1: ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockAssignment_5_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1459:1: ( rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1459:2: rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0_in_rule__TwoLifelineMessage__Group_5_1__0__Impl2986);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1469:1: rule__TwoLifelineMessage__Group_5_1__1 : rule__TwoLifelineMessage__Group_5_1__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1473:1: ( rule__TwoLifelineMessage__Group_5_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1474:2: rule__TwoLifelineMessage__Group_5_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_5_1__1__Impl_in_rule__TwoLifelineMessage__Group_5_1__13016);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1480:1: rule__TwoLifelineMessage__Group_5_1__1__Impl : ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1484:1: ( ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1485:1: ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1485:1: ( ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1486:1: ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockCountAssignment_5_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1487:1: ( rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT_GREATER_ZERO) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1487:2: rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1_in_rule__TwoLifelineMessage__Group_5_1__1__Impl3043);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1501:1: rule__TwoLifelineMessage__Group_6_1__0 : rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1 ;
    public final void rule__TwoLifelineMessage__Group_6_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1505:1: ( rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1506:2: rule__TwoLifelineMessage__Group_6_1__0__Impl rule__TwoLifelineMessage__Group_6_1__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__0__Impl_in_rule__TwoLifelineMessage__Group_6_1__03078);
            rule__TwoLifelineMessage__Group_6_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__1_in_rule__TwoLifelineMessage__Group_6_1__03081);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1513:1: rule__TwoLifelineMessage__Group_6_1__0__Impl : ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) ) ;
    public final void rule__TwoLifelineMessage__Group_6_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1517:1: ( ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1518:1: ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1518:1: ( ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1519:1: ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockAssignment_6_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1520:1: ( rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1520:2: rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0_in_rule__TwoLifelineMessage__Group_6_1__0__Impl3108);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1530:1: rule__TwoLifelineMessage__Group_6_1__1 : rule__TwoLifelineMessage__Group_6_1__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_6_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1534:1: ( rule__TwoLifelineMessage__Group_6_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1535:2: rule__TwoLifelineMessage__Group_6_1__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_6_1__1__Impl_in_rule__TwoLifelineMessage__Group_6_1__13138);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1541:1: rule__TwoLifelineMessage__Group_6_1__1__Impl : ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? ) ;
    public final void rule__TwoLifelineMessage__Group_6_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1545:1: ( ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1546:1: ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1546:1: ( ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1547:1: ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )?
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockCountAssignment_6_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1548:1: ( rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_INT_GREATER_ZERO) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1548:2: rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1
                    {
                    pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1_in_rule__TwoLifelineMessage__Group_6_1__1__Impl3165);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1562:1: rule__TwoLifelineMessage__Group_7__0 : rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 ;
    public final void rule__TwoLifelineMessage__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1566:1: ( rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1567:2: rule__TwoLifelineMessage__Group_7__0__Impl rule__TwoLifelineMessage__Group_7__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__03200);
            rule__TwoLifelineMessage__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__03203);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1574:1: rule__TwoLifelineMessage__Group_7__0__Impl : ( 'sourceNote' ) ;
    public final void rule__TwoLifelineMessage__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1578:1: ( ( 'sourceNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1579:1: ( 'sourceNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1579:1: ( 'sourceNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1580:1: 'sourceNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0()); 
            match(input,28,FOLLOW_28_in_rule__TwoLifelineMessage__Group_7__0__Impl3231); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1593:1: rule__TwoLifelineMessage__Group_7__1 : rule__TwoLifelineMessage__Group_7__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1597:1: ( rule__TwoLifelineMessage__Group_7__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1598:2: rule__TwoLifelineMessage__Group_7__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__13262);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1604:1: rule__TwoLifelineMessage__Group_7__1__Impl : ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1608:1: ( ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1609:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1609:1: ( ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1610:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteAssignment_7_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1611:1: ( rule__TwoLifelineMessage__SourceNoteAssignment_7_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1611:2: rule__TwoLifelineMessage__SourceNoteAssignment_7_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl3289);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1625:1: rule__TwoLifelineMessage__Group_8__0 : rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 ;
    public final void rule__TwoLifelineMessage__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1629:1: ( rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1630:2: rule__TwoLifelineMessage__Group_8__0__Impl rule__TwoLifelineMessage__Group_8__1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__03323);
            rule__TwoLifelineMessage__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__03326);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1637:1: rule__TwoLifelineMessage__Group_8__0__Impl : ( 'targetNote' ) ;
    public final void rule__TwoLifelineMessage__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1641:1: ( ( 'targetNote' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1642:1: ( 'targetNote' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1642:1: ( 'targetNote' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1643:1: 'targetNote'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0()); 
            match(input,29,FOLLOW_29_in_rule__TwoLifelineMessage__Group_8__0__Impl3354); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1656:1: rule__TwoLifelineMessage__Group_8__1 : rule__TwoLifelineMessage__Group_8__1__Impl ;
    public final void rule__TwoLifelineMessage__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1660:1: ( rule__TwoLifelineMessage__Group_8__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1661:2: rule__TwoLifelineMessage__Group_8__1__Impl
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__13385);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1667:1: rule__TwoLifelineMessage__Group_8__1__Impl : ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) ;
    public final void rule__TwoLifelineMessage__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1671:1: ( ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1672:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1672:1: ( ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1673:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteAssignment_8_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1674:1: ( rule__TwoLifelineMessage__TargetNoteAssignment_8_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1674:2: rule__TwoLifelineMessage__TargetNoteAssignment_8_1
            {
            pushFollow(FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl3412);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1688:1: rule__OneLifelineMessage__Group__0 : rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 ;
    public final void rule__OneLifelineMessage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1692:1: ( rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1693:2: rule__OneLifelineMessage__Group__0__Impl rule__OneLifelineMessage__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__03446);
            rule__OneLifelineMessage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__03449);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1700:1: rule__OneLifelineMessage__Group__0__Impl : ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineMessage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1704:1: ( ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1705:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1705:1: ( ( rule__OneLifelineMessage__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1706:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1707:1: ( rule__OneLifelineMessage__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1707:2: rule__OneLifelineMessage__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl3476);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1717:1: rule__OneLifelineMessage__Group__1 : rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 ;
    public final void rule__OneLifelineMessage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1721:1: ( rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1722:2: rule__OneLifelineMessage__Group__1__Impl rule__OneLifelineMessage__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__13506);
            rule__OneLifelineMessage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__13509);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1729:1: rule__OneLifelineMessage__Group__1__Impl : ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) ) ;
    public final void rule__OneLifelineMessage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1733:1: ( ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1734:1: ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1734:1: ( ( rule__OneLifelineMessage__MessageTypeAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1735:1: ( rule__OneLifelineMessage__MessageTypeAssignment_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1736:1: ( rule__OneLifelineMessage__MessageTypeAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1736:2: rule__OneLifelineMessage__MessageTypeAssignment_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl3536);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1746:1: rule__OneLifelineMessage__Group__2 : rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 ;
    public final void rule__OneLifelineMessage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1750:1: ( rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1751:2: rule__OneLifelineMessage__Group__2__Impl rule__OneLifelineMessage__Group__3
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__23566);
            rule__OneLifelineMessage__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__23569);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1758:1: rule__OneLifelineMessage__Group__2__Impl : ( ( rule__OneLifelineMessage__CaptionAssignment_2 ) ) ;
    public final void rule__OneLifelineMessage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1762:1: ( ( ( rule__OneLifelineMessage__CaptionAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1763:1: ( ( rule__OneLifelineMessage__CaptionAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1763:1: ( ( rule__OneLifelineMessage__CaptionAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1764:1: ( rule__OneLifelineMessage__CaptionAssignment_2 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getCaptionAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1765:1: ( rule__OneLifelineMessage__CaptionAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1765:2: rule__OneLifelineMessage__CaptionAssignment_2
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__CaptionAssignment_2_in_rule__OneLifelineMessage__Group__2__Impl3596);
            rule__OneLifelineMessage__CaptionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getCaptionAssignment_2()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1775:1: rule__OneLifelineMessage__Group__3 : rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 ;
    public final void rule__OneLifelineMessage__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1779:1: ( rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1780:2: rule__OneLifelineMessage__Group__3__Impl rule__OneLifelineMessage__Group__4
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__33626);
            rule__OneLifelineMessage__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__33629);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1787:1: rule__OneLifelineMessage__Group__3__Impl : ( ( rule__OneLifelineMessage__Alternatives_3 )? ) ;
    public final void rule__OneLifelineMessage__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1791:1: ( ( ( rule__OneLifelineMessage__Alternatives_3 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1792:1: ( ( rule__OneLifelineMessage__Alternatives_3 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1792:1: ( ( rule__OneLifelineMessage__Alternatives_3 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1793:1: ( rule__OneLifelineMessage__Alternatives_3 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getAlternatives_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1794:1: ( rule__OneLifelineMessage__Alternatives_3 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==31||LA18_0==42) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1794:2: rule__OneLifelineMessage__Alternatives_3
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Alternatives_3_in_rule__OneLifelineMessage__Group__3__Impl3656);
                    rule__OneLifelineMessage__Alternatives_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineMessageAccess().getAlternatives_3()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1804:1: rule__OneLifelineMessage__Group__4 : rule__OneLifelineMessage__Group__4__Impl ;
    public final void rule__OneLifelineMessage__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1808:1: ( rule__OneLifelineMessage__Group__4__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1809:2: rule__OneLifelineMessage__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__43687);
            rule__OneLifelineMessage__Group__4__Impl();

            state._fsp--;


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1815:1: rule__OneLifelineMessage__Group__4__Impl : ( ( rule__OneLifelineMessage__Group_4__0 )? ) ;
    public final void rule__OneLifelineMessage__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1819:1: ( ( ( rule__OneLifelineMessage__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1820:1: ( ( rule__OneLifelineMessage__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1820:1: ( ( rule__OneLifelineMessage__Group_4__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1821:1: ( rule__OneLifelineMessage__Group_4__0 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1822:1: ( rule__OneLifelineMessage__Group_4__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==30) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1822:2: rule__OneLifelineMessage__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4__0_in_rule__OneLifelineMessage__Group__4__Impl3714);
                    rule__OneLifelineMessage__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineMessageAccess().getGroup_4()); 

            }


            }

        }
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


    // $ANTLR start "rule__OneLifelineMessage__Group_3_1__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1842:1: rule__OneLifelineMessage__Group_3_1__0 : rule__OneLifelineMessage__Group_3_1__0__Impl rule__OneLifelineMessage__Group_3_1__1 ;
    public final void rule__OneLifelineMessage__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1846:1: ( rule__OneLifelineMessage__Group_3_1__0__Impl rule__OneLifelineMessage__Group_3_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1847:2: rule__OneLifelineMessage__Group_3_1__0__Impl rule__OneLifelineMessage__Group_3_1__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_3_1__0__Impl_in_rule__OneLifelineMessage__Group_3_1__03755);
            rule__OneLifelineMessage__Group_3_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_3_1__1_in_rule__OneLifelineMessage__Group_3_1__03758);
            rule__OneLifelineMessage__Group_3_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_3_1__0"


    // $ANTLR start "rule__OneLifelineMessage__Group_3_1__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1854:1: rule__OneLifelineMessage__Group_3_1__0__Impl : ( ( rule__OneLifelineMessage__EndBlockAssignment_3_1_0 ) ) ;
    public final void rule__OneLifelineMessage__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1858:1: ( ( ( rule__OneLifelineMessage__EndBlockAssignment_3_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1859:1: ( ( rule__OneLifelineMessage__EndBlockAssignment_3_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1859:1: ( ( rule__OneLifelineMessage__EndBlockAssignment_3_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1860:1: ( rule__OneLifelineMessage__EndBlockAssignment_3_1_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockAssignment_3_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1861:1: ( rule__OneLifelineMessage__EndBlockAssignment_3_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1861:2: rule__OneLifelineMessage__EndBlockAssignment_3_1_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__EndBlockAssignment_3_1_0_in_rule__OneLifelineMessage__Group_3_1__0__Impl3785);
            rule__OneLifelineMessage__EndBlockAssignment_3_1_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockAssignment_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_3_1__0__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_3_1__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1871:1: rule__OneLifelineMessage__Group_3_1__1 : rule__OneLifelineMessage__Group_3_1__1__Impl ;
    public final void rule__OneLifelineMessage__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1875:1: ( rule__OneLifelineMessage__Group_3_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1876:2: rule__OneLifelineMessage__Group_3_1__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_3_1__1__Impl_in_rule__OneLifelineMessage__Group_3_1__13815);
            rule__OneLifelineMessage__Group_3_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_3_1__1"


    // $ANTLR start "rule__OneLifelineMessage__Group_3_1__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1882:1: rule__OneLifelineMessage__Group_3_1__1__Impl : ( ( rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1 )? ) ;
    public final void rule__OneLifelineMessage__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1886:1: ( ( ( rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1887:1: ( ( rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1887:1: ( ( rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1888:1: ( rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1 )?
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountAssignment_3_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1889:1: ( rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_INT_GREATER_ZERO) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1889:2: rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1
                    {
                    pushFollow(FOLLOW_rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1_in_rule__OneLifelineMessage__Group_3_1__1__Impl3842);
                    rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountAssignment_3_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_3_1__1__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_4__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1903:1: rule__OneLifelineMessage__Group_4__0 : rule__OneLifelineMessage__Group_4__0__Impl rule__OneLifelineMessage__Group_4__1 ;
    public final void rule__OneLifelineMessage__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1907:1: ( rule__OneLifelineMessage__Group_4__0__Impl rule__OneLifelineMessage__Group_4__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1908:2: rule__OneLifelineMessage__Group_4__0__Impl rule__OneLifelineMessage__Group_4__1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4__0__Impl_in_rule__OneLifelineMessage__Group_4__03877);
            rule__OneLifelineMessage__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4__1_in_rule__OneLifelineMessage__Group_4__03880);
            rule__OneLifelineMessage__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4__0"


    // $ANTLR start "rule__OneLifelineMessage__Group_4__0__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1915:1: rule__OneLifelineMessage__Group_4__0__Impl : ( 'note' ) ;
    public final void rule__OneLifelineMessage__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1919:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1920:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1920:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1921:1: 'note'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_4_0()); 
            match(input,30,FOLLOW_30_in_rule__OneLifelineMessage__Group_4__0__Impl3908); 
             after(grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4__0__Impl"


    // $ANTLR start "rule__OneLifelineMessage__Group_4__1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1934:1: rule__OneLifelineMessage__Group_4__1 : rule__OneLifelineMessage__Group_4__1__Impl ;
    public final void rule__OneLifelineMessage__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1938:1: ( rule__OneLifelineMessage__Group_4__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1939:2: rule__OneLifelineMessage__Group_4__1__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__Group_4__1__Impl_in_rule__OneLifelineMessage__Group_4__13939);
            rule__OneLifelineMessage__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4__1"


    // $ANTLR start "rule__OneLifelineMessage__Group_4__1__Impl"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1945:1: rule__OneLifelineMessage__Group_4__1__Impl : ( ( rule__OneLifelineMessage__NoteAssignment_4_1 ) ) ;
    public final void rule__OneLifelineMessage__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1949:1: ( ( ( rule__OneLifelineMessage__NoteAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1950:1: ( ( rule__OneLifelineMessage__NoteAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1950:1: ( ( rule__OneLifelineMessage__NoteAssignment_4_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1951:1: ( rule__OneLifelineMessage__NoteAssignment_4_1 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteAssignment_4_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1952:1: ( rule__OneLifelineMessage__NoteAssignment_4_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1952:2: rule__OneLifelineMessage__NoteAssignment_4_1
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__NoteAssignment_4_1_in_rule__OneLifelineMessage__Group_4__1__Impl3966);
            rule__OneLifelineMessage__NoteAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getNoteAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__Group_4__1__Impl"


    // $ANTLR start "rule__OneLifelineEndBlock__Group__0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1966:1: rule__OneLifelineEndBlock__Group__0 : rule__OneLifelineEndBlock__Group__0__Impl rule__OneLifelineEndBlock__Group__1 ;
    public final void rule__OneLifelineEndBlock__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1970:1: ( rule__OneLifelineEndBlock__Group__0__Impl rule__OneLifelineEndBlock__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1971:2: rule__OneLifelineEndBlock__Group__0__Impl rule__OneLifelineEndBlock__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__0__Impl_in_rule__OneLifelineEndBlock__Group__04000);
            rule__OneLifelineEndBlock__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__1_in_rule__OneLifelineEndBlock__Group__04003);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1978:1: rule__OneLifelineEndBlock__Group__0__Impl : ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineEndBlock__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1982:1: ( ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1983:1: ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1983:1: ( ( rule__OneLifelineEndBlock__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1984:1: ( rule__OneLifelineEndBlock__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1985:1: ( rule__OneLifelineEndBlock__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1985:2: rule__OneLifelineEndBlock__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__LifelineAssignment_0_in_rule__OneLifelineEndBlock__Group__0__Impl4030);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1995:1: rule__OneLifelineEndBlock__Group__1 : rule__OneLifelineEndBlock__Group__1__Impl rule__OneLifelineEndBlock__Group__2 ;
    public final void rule__OneLifelineEndBlock__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:1999:1: ( rule__OneLifelineEndBlock__Group__1__Impl rule__OneLifelineEndBlock__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2000:2: rule__OneLifelineEndBlock__Group__1__Impl rule__OneLifelineEndBlock__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__1__Impl_in_rule__OneLifelineEndBlock__Group__14060);
            rule__OneLifelineEndBlock__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__2_in_rule__OneLifelineEndBlock__Group__14063);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2007:1: rule__OneLifelineEndBlock__Group__1__Impl : ( 'endBlock' ) ;
    public final void rule__OneLifelineEndBlock__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2011:1: ( ( 'endBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2012:1: ( 'endBlock' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2012:1: ( 'endBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2013:1: 'endBlock'
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockKeyword_1()); 
            match(input,31,FOLLOW_31_in_rule__OneLifelineEndBlock__Group__1__Impl4091); 
             after(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockKeyword_1()); 

            }


            }

        }
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2026:1: rule__OneLifelineEndBlock__Group__2 : rule__OneLifelineEndBlock__Group__2__Impl ;
    public final void rule__OneLifelineEndBlock__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2030:1: ( rule__OneLifelineEndBlock__Group__2__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2031:2: rule__OneLifelineEndBlock__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineEndBlock__Group__2__Impl_in_rule__OneLifelineEndBlock__Group__24122);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2037:1: rule__OneLifelineEndBlock__Group__2__Impl : ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? ) ;
    public final void rule__OneLifelineEndBlock__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2041:1: ( ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2042:1: ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2042:1: ( ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2043:1: ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )?
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2044:1: ( rule__OneLifelineEndBlock__EndBlockCountAssignment_2 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==RULE_INT_GREATER_ZERO) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2044:2: rule__OneLifelineEndBlock__EndBlockCountAssignment_2
                    {
                    pushFollow(FOLLOW_rule__OneLifelineEndBlock__EndBlockCountAssignment_2_in_rule__OneLifelineEndBlock__Group__2__Impl4149);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2060:1: rule__OneLifelineNote__Group__0 : rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 ;
    public final void rule__OneLifelineNote__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2064:1: ( rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2065:2: rule__OneLifelineNote__Group__0__Impl rule__OneLifelineNote__Group__1
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__04186);
            rule__OneLifelineNote__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__04189);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2072:1: rule__OneLifelineNote__Group__0__Impl : ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) ;
    public final void rule__OneLifelineNote__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2076:1: ( ( ( rule__OneLifelineNote__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2077:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2077:1: ( ( rule__OneLifelineNote__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2078:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2079:1: ( rule__OneLifelineNote__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2079:2: rule__OneLifelineNote__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl4216);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2089:1: rule__OneLifelineNote__Group__1 : rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 ;
    public final void rule__OneLifelineNote__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2093:1: ( rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2094:2: rule__OneLifelineNote__Group__1__Impl rule__OneLifelineNote__Group__2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__14246);
            rule__OneLifelineNote__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__14249);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2101:1: rule__OneLifelineNote__Group__1__Impl : ( 'note' ) ;
    public final void rule__OneLifelineNote__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2105:1: ( ( 'note' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2106:1: ( 'note' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2106:1: ( 'note' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2107:1: 'note'
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1()); 
            match(input,30,FOLLOW_30_in_rule__OneLifelineNote__Group__1__Impl4277); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2120:1: rule__OneLifelineNote__Group__2 : rule__OneLifelineNote__Group__2__Impl ;
    public final void rule__OneLifelineNote__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2124:1: ( rule__OneLifelineNote__Group__2__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2125:2: rule__OneLifelineNote__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__24308);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2131:1: rule__OneLifelineNote__Group__2__Impl : ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) ;
    public final void rule__OneLifelineNote__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2135:1: ( ( ( rule__OneLifelineNote__NoteAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2136:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2136:1: ( ( rule__OneLifelineNote__NoteAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2137:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2138:1: ( rule__OneLifelineNote__NoteAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2138:2: rule__OneLifelineNote__NoteAssignment_2
            {
            pushFollow(FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl4335);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2154:1: rule__DestroyLifelineEvent__Group__0 : rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1 ;
    public final void rule__DestroyLifelineEvent__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2158:1: ( rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2159:2: rule__DestroyLifelineEvent__Group__0__Impl rule__DestroyLifelineEvent__Group__1
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__0__Impl_in_rule__DestroyLifelineEvent__Group__04371);
            rule__DestroyLifelineEvent__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__1_in_rule__DestroyLifelineEvent__Group__04374);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2166:1: rule__DestroyLifelineEvent__Group__0__Impl : ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) ) ;
    public final void rule__DestroyLifelineEvent__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2170:1: ( ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2171:1: ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2171:1: ( ( rule__DestroyLifelineEvent__LifelineAssignment_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2172:1: ( rule__DestroyLifelineEvent__LifelineAssignment_0 )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineAssignment_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2173:1: ( rule__DestroyLifelineEvent__LifelineAssignment_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2173:2: rule__DestroyLifelineEvent__LifelineAssignment_0
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__LifelineAssignment_0_in_rule__DestroyLifelineEvent__Group__0__Impl4401);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2183:1: rule__DestroyLifelineEvent__Group__1 : rule__DestroyLifelineEvent__Group__1__Impl ;
    public final void rule__DestroyLifelineEvent__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2187:1: ( rule__DestroyLifelineEvent__Group__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2188:2: rule__DestroyLifelineEvent__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__DestroyLifelineEvent__Group__1__Impl_in_rule__DestroyLifelineEvent__Group__14431);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2194:1: rule__DestroyLifelineEvent__Group__1__Impl : ( 'destroy' ) ;
    public final void rule__DestroyLifelineEvent__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2198:1: ( ( 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2199:1: ( 'destroy' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2199:1: ( 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2200:1: 'destroy'
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getDestroyKeyword_1()); 
            match(input,32,FOLLOW_32_in_rule__DestroyLifelineEvent__Group__1__Impl4459); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2217:1: rule__Fragment__Group__0 : rule__Fragment__Group__0__Impl rule__Fragment__Group__1 ;
    public final void rule__Fragment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2221:1: ( rule__Fragment__Group__0__Impl rule__Fragment__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2222:2: rule__Fragment__Group__0__Impl rule__Fragment__Group__1
            {
            pushFollow(FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__04494);
            rule__Fragment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__04497);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2229:1: rule__Fragment__Group__0__Impl : ( 'fragment' ) ;
    public final void rule__Fragment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2233:1: ( ( 'fragment' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2234:1: ( 'fragment' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2234:1: ( 'fragment' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2235:1: 'fragment'
            {
             before(grammarAccess.getFragmentAccess().getFragmentKeyword_0()); 
            match(input,33,FOLLOW_33_in_rule__Fragment__Group__0__Impl4525); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2248:1: rule__Fragment__Group__1 : rule__Fragment__Group__1__Impl rule__Fragment__Group__2 ;
    public final void rule__Fragment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2252:1: ( rule__Fragment__Group__1__Impl rule__Fragment__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2253:2: rule__Fragment__Group__1__Impl rule__Fragment__Group__2
            {
            pushFollow(FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__14556);
            rule__Fragment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__14559);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2260:1: rule__Fragment__Group__1__Impl : ( ( rule__Fragment__NameAssignment_1 ) ) ;
    public final void rule__Fragment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2264:1: ( ( ( rule__Fragment__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2265:1: ( ( rule__Fragment__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2265:1: ( ( rule__Fragment__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2266:1: ( rule__Fragment__NameAssignment_1 )
            {
             before(grammarAccess.getFragmentAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2267:1: ( rule__Fragment__NameAssignment_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2267:2: rule__Fragment__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl4586);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2277:1: rule__Fragment__Group__2 : rule__Fragment__Group__2__Impl rule__Fragment__Group__3 ;
    public final void rule__Fragment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2281:1: ( rule__Fragment__Group__2__Impl rule__Fragment__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2282:2: rule__Fragment__Group__2__Impl rule__Fragment__Group__3
            {
            pushFollow(FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__24616);
            rule__Fragment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__24619);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2289:1: rule__Fragment__Group__2__Impl : ( ( rule__Fragment__SectionsAssignment_2 ) ) ;
    public final void rule__Fragment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2293:1: ( ( ( rule__Fragment__SectionsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2294:1: ( ( rule__Fragment__SectionsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2294:1: ( ( rule__Fragment__SectionsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2295:1: ( rule__Fragment__SectionsAssignment_2 )
            {
             before(grammarAccess.getFragmentAccess().getSectionsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2296:1: ( rule__Fragment__SectionsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2296:2: rule__Fragment__SectionsAssignment_2
            {
            pushFollow(FOLLOW_rule__Fragment__SectionsAssignment_2_in_rule__Fragment__Group__2__Impl4646);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2306:1: rule__Fragment__Group__3 : rule__Fragment__Group__3__Impl ;
    public final void rule__Fragment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2310:1: ( rule__Fragment__Group__3__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2311:2: rule__Fragment__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__34676);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2317:1: rule__Fragment__Group__3__Impl : ( ( rule__Fragment__SectionsAssignment_3 )* ) ;
    public final void rule__Fragment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2321:1: ( ( ( rule__Fragment__SectionsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2322:1: ( ( rule__Fragment__SectionsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2322:1: ( ( rule__Fragment__SectionsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2323:1: ( rule__Fragment__SectionsAssignment_3 )*
            {
             before(grammarAccess.getFragmentAccess().getSectionsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2324:1: ( rule__Fragment__SectionsAssignment_3 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==23) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2324:2: rule__Fragment__SectionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Fragment__SectionsAssignment_3_in_rule__Fragment__Group__3__Impl4703);
            	    rule__Fragment__SectionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2342:1: rule__Section__Group__0 : rule__Section__Group__0__Impl rule__Section__Group__1 ;
    public final void rule__Section__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2346:1: ( rule__Section__Group__0__Impl rule__Section__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2347:2: rule__Section__Group__0__Impl rule__Section__Group__1
            {
            pushFollow(FOLLOW_rule__Section__Group__0__Impl_in_rule__Section__Group__04742);
            rule__Section__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__1_in_rule__Section__Group__04745);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2354:1: rule__Section__Group__0__Impl : ( '{' ) ;
    public final void rule__Section__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2358:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2359:1: ( '{' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2359:1: ( '{' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2360:1: '{'
            {
             before(grammarAccess.getSectionAccess().getLeftCurlyBracketKeyword_0()); 
            match(input,23,FOLLOW_23_in_rule__Section__Group__0__Impl4773); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2373:1: rule__Section__Group__1 : rule__Section__Group__1__Impl rule__Section__Group__2 ;
    public final void rule__Section__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2377:1: ( rule__Section__Group__1__Impl rule__Section__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2378:2: rule__Section__Group__1__Impl rule__Section__Group__2
            {
            pushFollow(FOLLOW_rule__Section__Group__1__Impl_in_rule__Section__Group__14804);
            rule__Section__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__2_in_rule__Section__Group__14807);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2385:1: rule__Section__Group__1__Impl : ( ( rule__Section__Group_1__0 )? ) ;
    public final void rule__Section__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2389:1: ( ( ( rule__Section__Group_1__0 )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2390:1: ( ( rule__Section__Group_1__0 )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2390:1: ( ( rule__Section__Group_1__0 )? )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2391:1: ( rule__Section__Group_1__0 )?
            {
             before(grammarAccess.getSectionAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2392:1: ( rule__Section__Group_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==34) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2392:2: rule__Section__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Section__Group_1__0_in_rule__Section__Group__1__Impl4834);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2402:1: rule__Section__Group__2 : rule__Section__Group__2__Impl rule__Section__Group__3 ;
    public final void rule__Section__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2406:1: ( rule__Section__Group__2__Impl rule__Section__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2407:2: rule__Section__Group__2__Impl rule__Section__Group__3
            {
            pushFollow(FOLLOW_rule__Section__Group__2__Impl_in_rule__Section__Group__24865);
            rule__Section__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__3_in_rule__Section__Group__24868);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2414:1: rule__Section__Group__2__Impl : ( ( rule__Section__InteractionsAssignment_2 ) ) ;
    public final void rule__Section__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2418:1: ( ( ( rule__Section__InteractionsAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2419:1: ( ( rule__Section__InteractionsAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2419:1: ( ( rule__Section__InteractionsAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2420:1: ( rule__Section__InteractionsAssignment_2 )
            {
             before(grammarAccess.getSectionAccess().getInteractionsAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2421:1: ( rule__Section__InteractionsAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2421:2: rule__Section__InteractionsAssignment_2
            {
            pushFollow(FOLLOW_rule__Section__InteractionsAssignment_2_in_rule__Section__Group__2__Impl4895);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2431:1: rule__Section__Group__3 : rule__Section__Group__3__Impl rule__Section__Group__4 ;
    public final void rule__Section__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2435:1: ( rule__Section__Group__3__Impl rule__Section__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2436:2: rule__Section__Group__3__Impl rule__Section__Group__4
            {
            pushFollow(FOLLOW_rule__Section__Group__3__Impl_in_rule__Section__Group__34925);
            rule__Section__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group__4_in_rule__Section__Group__34928);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2443:1: rule__Section__Group__3__Impl : ( ( rule__Section__InteractionsAssignment_3 )* ) ;
    public final void rule__Section__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2447:1: ( ( ( rule__Section__InteractionsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2448:1: ( ( rule__Section__InteractionsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2448:1: ( ( rule__Section__InteractionsAssignment_3 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2449:1: ( rule__Section__InteractionsAssignment_3 )*
            {
             before(grammarAccess.getSectionAccess().getInteractionsAssignment_3()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2450:1: ( rule__Section__InteractionsAssignment_3 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID||LA24_0==33||LA24_0==35) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2450:2: rule__Section__InteractionsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Section__InteractionsAssignment_3_in_rule__Section__Group__3__Impl4955);
            	    rule__Section__InteractionsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2460:1: rule__Section__Group__4 : rule__Section__Group__4__Impl ;
    public final void rule__Section__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2464:1: ( rule__Section__Group__4__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2465:2: rule__Section__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__Section__Group__4__Impl_in_rule__Section__Group__44986);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2471:1: rule__Section__Group__4__Impl : ( '}' ) ;
    public final void rule__Section__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2475:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2476:1: ( '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2476:1: ( '}' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2477:1: '}'
            {
             before(grammarAccess.getSectionAccess().getRightCurlyBracketKeyword_4()); 
            match(input,24,FOLLOW_24_in_rule__Section__Group__4__Impl5014); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2500:1: rule__Section__Group_1__0 : rule__Section__Group_1__0__Impl rule__Section__Group_1__1 ;
    public final void rule__Section__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2504:1: ( rule__Section__Group_1__0__Impl rule__Section__Group_1__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2505:2: rule__Section__Group_1__0__Impl rule__Section__Group_1__1
            {
            pushFollow(FOLLOW_rule__Section__Group_1__0__Impl_in_rule__Section__Group_1__05055);
            rule__Section__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Section__Group_1__1_in_rule__Section__Group_1__05058);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2512:1: rule__Section__Group_1__0__Impl : ( 'label' ) ;
    public final void rule__Section__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2516:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2517:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2517:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2518:1: 'label'
            {
             before(grammarAccess.getSectionAccess().getLabelKeyword_1_0()); 
            match(input,34,FOLLOW_34_in_rule__Section__Group_1__0__Impl5086); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2531:1: rule__Section__Group_1__1 : rule__Section__Group_1__1__Impl ;
    public final void rule__Section__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2535:1: ( rule__Section__Group_1__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2536:2: rule__Section__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Section__Group_1__1__Impl_in_rule__Section__Group_1__15117);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2542:1: rule__Section__Group_1__1__Impl : ( ( rule__Section__LabelAssignment_1_1 ) ) ;
    public final void rule__Section__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2546:1: ( ( ( rule__Section__LabelAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2547:1: ( ( rule__Section__LabelAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2547:1: ( ( rule__Section__LabelAssignment_1_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2548:1: ( rule__Section__LabelAssignment_1_1 )
            {
             before(grammarAccess.getSectionAccess().getLabelAssignment_1_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2549:1: ( rule__Section__LabelAssignment_1_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2549:2: rule__Section__LabelAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Section__LabelAssignment_1_1_in_rule__Section__Group_1__1__Impl5144);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2563:1: rule__Refinement__Group__0 : rule__Refinement__Group__0__Impl rule__Refinement__Group__1 ;
    public final void rule__Refinement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2567:1: ( rule__Refinement__Group__0__Impl rule__Refinement__Group__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2568:2: rule__Refinement__Group__0__Impl rule__Refinement__Group__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__05178);
            rule__Refinement__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__05181);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2575:1: rule__Refinement__Group__0__Impl : ( 'refinement' ) ;
    public final void rule__Refinement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2579:1: ( ( 'refinement' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2580:1: ( 'refinement' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2580:1: ( 'refinement' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2581:1: 'refinement'
            {
             before(grammarAccess.getRefinementAccess().getRefinementKeyword_0()); 
            match(input,35,FOLLOW_35_in_rule__Refinement__Group__0__Impl5209); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2594:1: rule__Refinement__Group__1 : rule__Refinement__Group__1__Impl rule__Refinement__Group__2 ;
    public final void rule__Refinement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2598:1: ( rule__Refinement__Group__1__Impl rule__Refinement__Group__2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2599:2: rule__Refinement__Group__1__Impl rule__Refinement__Group__2
            {
            pushFollow(FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__15240);
            rule__Refinement__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__15243);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2606:1: rule__Refinement__Group__1__Impl : ( 'label' ) ;
    public final void rule__Refinement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2610:1: ( ( 'label' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2611:1: ( 'label' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2611:1: ( 'label' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2612:1: 'label'
            {
             before(grammarAccess.getRefinementAccess().getLabelKeyword_1()); 
            match(input,34,FOLLOW_34_in_rule__Refinement__Group__1__Impl5271); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2625:1: rule__Refinement__Group__2 : rule__Refinement__Group__2__Impl rule__Refinement__Group__3 ;
    public final void rule__Refinement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2629:1: ( rule__Refinement__Group__2__Impl rule__Refinement__Group__3 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2630:2: rule__Refinement__Group__2__Impl rule__Refinement__Group__3
            {
            pushFollow(FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__25302);
            rule__Refinement__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__25305);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2637:1: rule__Refinement__Group__2__Impl : ( ( rule__Refinement__LabelAssignment_2 ) ) ;
    public final void rule__Refinement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2641:1: ( ( ( rule__Refinement__LabelAssignment_2 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2642:1: ( ( rule__Refinement__LabelAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2642:1: ( ( rule__Refinement__LabelAssignment_2 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2643:1: ( rule__Refinement__LabelAssignment_2 )
            {
             before(grammarAccess.getRefinementAccess().getLabelAssignment_2()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2644:1: ( rule__Refinement__LabelAssignment_2 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2644:2: rule__Refinement__LabelAssignment_2
            {
            pushFollow(FOLLOW_rule__Refinement__LabelAssignment_2_in_rule__Refinement__Group__2__Impl5332);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2654:1: rule__Refinement__Group__3 : rule__Refinement__Group__3__Impl rule__Refinement__Group__4 ;
    public final void rule__Refinement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2658:1: ( rule__Refinement__Group__3__Impl rule__Refinement__Group__4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2659:2: rule__Refinement__Group__3__Impl rule__Refinement__Group__4
            {
            pushFollow(FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__35362);
            rule__Refinement__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__35365);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2666:1: rule__Refinement__Group__3__Impl : ( 'lifelines' ) ;
    public final void rule__Refinement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2670:1: ( ( 'lifelines' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2671:1: ( 'lifelines' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2671:1: ( 'lifelines' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2672:1: 'lifelines'
            {
             before(grammarAccess.getRefinementAccess().getLifelinesKeyword_3()); 
            match(input,36,FOLLOW_36_in_rule__Refinement__Group__3__Impl5393); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2685:1: rule__Refinement__Group__4 : rule__Refinement__Group__4__Impl rule__Refinement__Group__5 ;
    public final void rule__Refinement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2689:1: ( rule__Refinement__Group__4__Impl rule__Refinement__Group__5 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2690:2: rule__Refinement__Group__4__Impl rule__Refinement__Group__5
            {
            pushFollow(FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__45424);
            rule__Refinement__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__45427);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2697:1: rule__Refinement__Group__4__Impl : ( ( rule__Refinement__LifelinesAssignment_4 ) ) ;
    public final void rule__Refinement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2701:1: ( ( ( rule__Refinement__LifelinesAssignment_4 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2702:1: ( ( rule__Refinement__LifelinesAssignment_4 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2702:1: ( ( rule__Refinement__LifelinesAssignment_4 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2703:1: ( rule__Refinement__LifelinesAssignment_4 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_4()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2704:1: ( rule__Refinement__LifelinesAssignment_4 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2704:2: rule__Refinement__LifelinesAssignment_4
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_4_in_rule__Refinement__Group__4__Impl5454);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2714:1: rule__Refinement__Group__5 : rule__Refinement__Group__5__Impl ;
    public final void rule__Refinement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2718:1: ( rule__Refinement__Group__5__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2719:2: rule__Refinement__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__55484);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2725:1: rule__Refinement__Group__5__Impl : ( ( rule__Refinement__Group_5__0 )* ) ;
    public final void rule__Refinement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2729:1: ( ( ( rule__Refinement__Group_5__0 )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2730:1: ( ( rule__Refinement__Group_5__0 )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2730:1: ( ( rule__Refinement__Group_5__0 )* )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2731:1: ( rule__Refinement__Group_5__0 )*
            {
             before(grammarAccess.getRefinementAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2732:1: ( rule__Refinement__Group_5__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==37) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2732:2: rule__Refinement__Group_5__0
            	    {
            	    pushFollow(FOLLOW_rule__Refinement__Group_5__0_in_rule__Refinement__Group__5__Impl5511);
            	    rule__Refinement__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2754:1: rule__Refinement__Group_5__0 : rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1 ;
    public final void rule__Refinement__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2758:1: ( rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2759:2: rule__Refinement__Group_5__0__Impl rule__Refinement__Group_5__1
            {
            pushFollow(FOLLOW_rule__Refinement__Group_5__0__Impl_in_rule__Refinement__Group_5__05554);
            rule__Refinement__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Refinement__Group_5__1_in_rule__Refinement__Group_5__05557);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2766:1: rule__Refinement__Group_5__0__Impl : ( ',' ) ;
    public final void rule__Refinement__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2770:1: ( ( ',' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2771:1: ( ',' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2771:1: ( ',' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2772:1: ','
            {
             before(grammarAccess.getRefinementAccess().getCommaKeyword_5_0()); 
            match(input,37,FOLLOW_37_in_rule__Refinement__Group_5__0__Impl5585); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2785:1: rule__Refinement__Group_5__1 : rule__Refinement__Group_5__1__Impl ;
    public final void rule__Refinement__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2789:1: ( rule__Refinement__Group_5__1__Impl )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2790:2: rule__Refinement__Group_5__1__Impl
            {
            pushFollow(FOLLOW_rule__Refinement__Group_5__1__Impl_in_rule__Refinement__Group_5__15616);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2796:1: rule__Refinement__Group_5__1__Impl : ( ( rule__Refinement__LifelinesAssignment_5_1 ) ) ;
    public final void rule__Refinement__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2800:1: ( ( ( rule__Refinement__LifelinesAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2801:1: ( ( rule__Refinement__LifelinesAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2801:1: ( ( rule__Refinement__LifelinesAssignment_5_1 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2802:1: ( rule__Refinement__LifelinesAssignment_5_1 )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesAssignment_5_1()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2803:1: ( rule__Refinement__LifelinesAssignment_5_1 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2803:2: rule__Refinement__LifelinesAssignment_5_1
            {
            pushFollow(FOLLOW_rule__Refinement__LifelinesAssignment_5_1_in_rule__Refinement__Group_5__1__Impl5643);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2818:1: rule__SequenceDiagram__DiagramNameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__SequenceDiagram__DiagramNameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2822:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2823:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2823:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2824:1: RULE_STRING
            {
             before(grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_25682); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2833:1: rule__SequenceDiagram__LocalsAssignment_3_1 : ( ruleLocalVariable ) ;
    public final void rule__SequenceDiagram__LocalsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2837:1: ( ( ruleLocalVariable ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2838:1: ( ruleLocalVariable )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2838:1: ( ruleLocalVariable )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2839:1: ruleLocalVariable
            {
             before(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_ruleLocalVariable_in_rule__SequenceDiagram__LocalsAssignment_3_15713);
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


    // $ANTLR start "rule__SequenceDiagram__LocalsAssignment_3_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2848:1: rule__SequenceDiagram__LocalsAssignment_3_2 : ( ruleLocalVariable ) ;
    public final void rule__SequenceDiagram__LocalsAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2852:1: ( ( ruleLocalVariable ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2853:1: ( ruleLocalVariable )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2853:1: ( ruleLocalVariable )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2854:1: ruleLocalVariable
            {
             before(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_2_0()); 
            pushFollow(FOLLOW_ruleLocalVariable_in_rule__SequenceDiagram__LocalsAssignment_3_25744);
            ruleLocalVariable();

            state._fsp--;

             after(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SequenceDiagram__LocalsAssignment_3_2"


    // $ANTLR start "rule__SequenceDiagram__LifelinesAssignment_4"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2863:1: rule__SequenceDiagram__LifelinesAssignment_4 : ( ruleLifeline ) ;
    public final void rule__SequenceDiagram__LifelinesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2867:1: ( ( ruleLifeline ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2868:1: ( ruleLifeline )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2868:1: ( ruleLifeline )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2869:1: ruleLifeline
            {
             before(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_45775);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2878:1: rule__SequenceDiagram__InteractionsAssignment_5 : ( ruleInteraction ) ;
    public final void rule__SequenceDiagram__InteractionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2882:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2883:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2883:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2884:1: ruleInteraction
            {
             before(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_55806);
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


    // $ANTLR start "rule__LocalVariable__TypeAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2893:1: rule__LocalVariable__TypeAssignment_0 : ( ruleDataType ) ;
    public final void rule__LocalVariable__TypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2897:1: ( ( ruleDataType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2898:1: ( ruleDataType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2898:1: ( ruleDataType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2899:1: ruleDataType
            {
             before(grammarAccess.getLocalVariableAccess().getTypeDataTypeEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleDataType_in_rule__LocalVariable__TypeAssignment_05837);
            ruleDataType();

            state._fsp--;

             after(grammarAccess.getLocalVariableAccess().getTypeDataTypeEnumRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__TypeAssignment_0"


    // $ANTLR start "rule__LocalVariable__NameAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2908:1: rule__LocalVariable__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__LocalVariable__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2912:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2913:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2913:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2914:1: RULE_ID
            {
             before(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_15868); 
             after(grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LocalVariable__NameAssignment_1"


    // $ANTLR start "rule__Lifeline__CaptionAssignment_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2923:1: rule__Lifeline__CaptionAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Lifeline__CaptionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2927:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2928:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2928:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2929:1: RULE_STRING
            {
             before(grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_15899); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2938:1: rule__Lifeline__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__Lifeline__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2942:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2943:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2943:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2944:1: RULE_ID
            {
             before(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_35930); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2953:1: rule__TwoLifelineMessage__SourceLifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__SourceLifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2957:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2958:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2958:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2959:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2960:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2961:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_05965); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2972:1: rule__TwoLifelineMessage__MessageTypeAssignment_1 : ( ruleMessageType ) ;
    public final void rule__TwoLifelineMessage__MessageTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2976:1: ( ( ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2977:1: ( ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2977:1: ( ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2978:1: ruleMessageType
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleMessageType_in_rule__TwoLifelineMessage__MessageTypeAssignment_16000);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2987:1: rule__TwoLifelineMessage__MessageAssignment_2 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__MessageAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2991:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2992:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2992:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:2993:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__MessageAssignment_26031); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3002:1: rule__TwoLifelineMessage__TargetLifelineAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__TwoLifelineMessage__TargetLifelineAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3006:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3007:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3007:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3008:1: ( RULE_ID )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3009:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3010:1: RULE_ID
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_46066); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3021:1: rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0 : ( ( 'sourceStartBlock' ) ) ;
    public final void rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3025:1: ( ( ( 'sourceStartBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3026:1: ( ( 'sourceStartBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3026:1: ( ( 'sourceStartBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3027:1: ( 'sourceStartBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockSourceStartBlockKeyword_5_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3028:1: ( 'sourceStartBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3029:1: 'sourceStartBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockSourceStartBlockKeyword_5_0_0()); 
            match(input,38,FOLLOW_38_in_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_06106); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3044:1: rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0 : ( ( 'sourceEndBlock' ) ) ;
    public final void rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3048:1: ( ( ( 'sourceEndBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3049:1: ( ( 'sourceEndBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3049:1: ( ( 'sourceEndBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3050:1: ( 'sourceEndBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockSourceEndBlockKeyword_5_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3051:1: ( 'sourceEndBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3052:1: 'sourceEndBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockSourceEndBlockKeyword_5_1_0_0()); 
            match(input,39,FOLLOW_39_in_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_06150); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3067:1: rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3071:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3072:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3072:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3073:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_16189); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3082:1: rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0 : ( ( 'targetStartBlock' ) ) ;
    public final void rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3086:1: ( ( ( 'targetStartBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3087:1: ( ( 'targetStartBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3087:1: ( ( 'targetStartBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3088:1: ( 'targetStartBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockTargetStartBlockKeyword_6_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3089:1: ( 'targetStartBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3090:1: 'targetStartBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockTargetStartBlockKeyword_6_0_0()); 
            match(input,40,FOLLOW_40_in_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_06225); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3105:1: rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0 : ( ( 'targetEndBlock' ) ) ;
    public final void rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3109:1: ( ( ( 'targetEndBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3110:1: ( ( 'targetEndBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3110:1: ( ( 'targetEndBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3111:1: ( 'targetEndBlock' )
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockTargetEndBlockKeyword_6_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3112:1: ( 'targetEndBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3113:1: 'targetEndBlock'
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockTargetEndBlockKeyword_6_1_0_0()); 
            match(input,41,FOLLOW_41_in_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_06269); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3128:1: rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3132:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3133:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3133:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3134:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_16308); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3143:1: rule__TwoLifelineMessage__SourceNoteAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__SourceNoteAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3147:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3148:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3148:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3149:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_16339); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3158:1: rule__TwoLifelineMessage__TargetNoteAssignment_8_1 : ( RULE_STRING ) ;
    public final void rule__TwoLifelineMessage__TargetNoteAssignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3162:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3163:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3163:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3164:1: RULE_STRING
            {
             before(grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_16370); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3173:1: rule__OneLifelineMessage__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineMessage__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3177:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3178:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3178:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3179:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3180:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3181:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_06405); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3192:1: rule__OneLifelineMessage__MessageTypeAssignment_1 : ( ( rule__OneLifelineMessage__MessageTypeAlternatives_1_0 ) ) ;
    public final void rule__OneLifelineMessage__MessageTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3196:1: ( ( ( rule__OneLifelineMessage__MessageTypeAlternatives_1_0 ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3197:1: ( ( rule__OneLifelineMessage__MessageTypeAlternatives_1_0 ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3197:1: ( ( rule__OneLifelineMessage__MessageTypeAlternatives_1_0 ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3198:1: ( rule__OneLifelineMessage__MessageTypeAlternatives_1_0 )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getMessageTypeAlternatives_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3199:1: ( rule__OneLifelineMessage__MessageTypeAlternatives_1_0 )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3199:2: rule__OneLifelineMessage__MessageTypeAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__OneLifelineMessage__MessageTypeAlternatives_1_0_in_rule__OneLifelineMessage__MessageTypeAssignment_16440);
            rule__OneLifelineMessage__MessageTypeAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getOneLifelineMessageAccess().getMessageTypeAlternatives_1_0()); 

            }


            }

        }
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


    // $ANTLR start "rule__OneLifelineMessage__CaptionAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3208:1: rule__OneLifelineMessage__CaptionAssignment_2 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__CaptionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3212:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3213:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3213:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3214:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__CaptionAssignment_26473); 
             after(grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__CaptionAssignment_2"


    // $ANTLR start "rule__OneLifelineMessage__StartBlockAssignment_3_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3223:1: rule__OneLifelineMessage__StartBlockAssignment_3_0 : ( ( 'startBlock' ) ) ;
    public final void rule__OneLifelineMessage__StartBlockAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3227:1: ( ( ( 'startBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3228:1: ( ( 'startBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3228:1: ( ( 'startBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3229:1: ( 'startBlock' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_3_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3230:1: ( 'startBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3231:1: 'startBlock'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_3_0_0()); 
            match(input,42,FOLLOW_42_in_rule__OneLifelineMessage__StartBlockAssignment_3_06509); 
             after(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_3_0_0()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__StartBlockAssignment_3_0"


    // $ANTLR start "rule__OneLifelineMessage__EndBlockAssignment_3_1_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3246:1: rule__OneLifelineMessage__EndBlockAssignment_3_1_0 : ( ( 'endBlock' ) ) ;
    public final void rule__OneLifelineMessage__EndBlockAssignment_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3250:1: ( ( ( 'endBlock' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3251:1: ( ( 'endBlock' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3251:1: ( ( 'endBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3252:1: ( 'endBlock' )
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_3_1_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3253:1: ( 'endBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3254:1: 'endBlock'
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_3_1_0_0()); 
            match(input,31,FOLLOW_31_in_rule__OneLifelineMessage__EndBlockAssignment_3_1_06553); 
             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_3_1_0_0()); 

            }

             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_3_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__EndBlockAssignment_3_1_0"


    // $ANTLR start "rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3269:1: rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3273:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3274:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3274:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3275:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_3_1_1_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndBlockCountAssignment_3_1_16592); 
             after(grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_3_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1"


    // $ANTLR start "rule__OneLifelineMessage__NoteAssignment_4_1"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3284:1: rule__OneLifelineMessage__NoteAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__OneLifelineMessage__NoteAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3288:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3289:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3289:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3290:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_4_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_4_16623); 
             after(grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OneLifelineMessage__NoteAssignment_4_1"


    // $ANTLR start "rule__OneLifelineEndBlock__LifelineAssignment_0"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3299:1: rule__OneLifelineEndBlock__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineEndBlock__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3303:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3304:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3304:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3305:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3306:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3307:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineEndBlock__LifelineAssignment_06658); 
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


    // $ANTLR start "rule__OneLifelineEndBlock__EndBlockCountAssignment_2"
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3318:1: rule__OneLifelineEndBlock__EndBlockCountAssignment_2 : ( RULE_INT_GREATER_ZERO ) ;
    public final void rule__OneLifelineEndBlock__EndBlockCountAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3322:1: ( ( RULE_INT_GREATER_ZERO ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3323:1: ( RULE_INT_GREATER_ZERO )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3323:1: ( RULE_INT_GREATER_ZERO )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3324:1: RULE_INT_GREATER_ZERO
            {
             before(grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_2_0()); 
            match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineEndBlock__EndBlockCountAssignment_26693); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3333:1: rule__OneLifelineNote__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__OneLifelineNote__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3337:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3338:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3338:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3339:1: ( RULE_ID )
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3340:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3341:1: RULE_ID
            {
             before(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_06728); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3352:1: rule__OneLifelineNote__NoteAssignment_2 : ( RULE_STRING ) ;
    public final void rule__OneLifelineNote__NoteAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3356:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3357:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3357:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3358:1: RULE_STRING
            {
             before(grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_26763); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3367:1: rule__DestroyLifelineEvent__LifelineAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__DestroyLifelineEvent__LifelineAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3371:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3372:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3372:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3373:1: ( RULE_ID )
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineCrossReference_0_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3374:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3375:1: RULE_ID
            {
             before(grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__DestroyLifelineEvent__LifelineAssignment_06798); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3386:1: rule__Fragment__NameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Fragment__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3390:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3391:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3391:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3392:1: RULE_STRING
            {
             before(grammarAccess.getFragmentAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_16833); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3401:1: rule__Fragment__SectionsAssignment_2 : ( ruleSection ) ;
    public final void rule__Fragment__SectionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3405:1: ( ( ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3406:1: ( ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3406:1: ( ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3407:1: ruleSection
            {
             before(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_26864);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3416:1: rule__Fragment__SectionsAssignment_3 : ( ruleSection ) ;
    public final void rule__Fragment__SectionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3420:1: ( ( ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3421:1: ( ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3421:1: ( ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3422:1: ruleSection
            {
             before(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_36895);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3431:1: rule__Section__LabelAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__Section__LabelAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3435:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3436:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3436:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3437:1: RULE_STRING
            {
             before(grammarAccess.getSectionAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Section__LabelAssignment_1_16926); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3446:1: rule__Section__InteractionsAssignment_2 : ( ruleInteraction ) ;
    public final void rule__Section__InteractionsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3450:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3451:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3451:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3452:1: ruleInteraction
            {
             before(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_26957);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3461:1: rule__Section__InteractionsAssignment_3 : ( ruleInteraction ) ;
    public final void rule__Section__InteractionsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3465:1: ( ( ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3466:1: ( ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3466:1: ( ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3467:1: ruleInteraction
            {
             before(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_36988);
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3476:1: rule__Refinement__LabelAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Refinement__LabelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3480:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3481:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3481:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3482:1: RULE_STRING
            {
             before(grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_27019); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3491:1: rule__Refinement__LifelinesAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3495:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3496:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3496:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3497:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3498:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3499:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_47054); 
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
    // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3510:1: rule__Refinement__LifelinesAssignment_5_1 : ( ( RULE_ID ) ) ;
    public final void rule__Refinement__LifelinesAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3514:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3515:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3515:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3516:1: ( RULE_ID )
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_5_1_0()); 
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3517:1: ( RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text.ui/src-gen/de/cau/cs/kieler/uml/sequence/text/ui/contentassist/antlr/internal/InternalSequence.g:3518:1: RULE_ID
            {
             before(grammarAccess.getRefinementAccess().getLifelinesLifelineIDTerminalRuleCall_5_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_5_17093); 
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


 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0_in_ruleSequenceDiagram94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_entryRuleLocalVariable121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalVariable128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group__0_in_ruleLocalVariable154 = new BitSet(new long[]{0x0000000000000002L});
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
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroyLifelineEvent548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__0_in_ruleDestroyLifelineEvent574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0_in_ruleFragment634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_entryRuleSection661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSection668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__0_in_ruleSection694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0_in_ruleRefinement754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MessageType__Alternatives_in_ruleMessageType791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DataType__Alternatives_in_ruleDataType827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_rule__Interaction__Alternatives862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_rule__Interaction__Alternatives879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_rule__Interaction__Alternatives896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineEndBlock_in_rule__Interaction__Alternatives913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_rule__Interaction__Alternatives930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_rule__Interaction__Alternatives947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_rule__Interaction__Alternatives964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_0_in_rule__TwoLifelineMessage__Alternatives_5996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__0_in_rule__TwoLifelineMessage__Alternatives_51014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_0_in_rule__TwoLifelineMessage__Alternatives_61047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__0_in_rule__TwoLifelineMessage__Alternatives_61065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__OneLifelineMessage__MessageTypeAlternatives_1_01099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__OneLifelineMessage__MessageTypeAlternatives_1_01119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__StartBlockAssignment_3_0_in_rule__OneLifelineMessage__Alternatives_31153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_3_1__0_in_rule__OneLifelineMessage__Alternatives_31171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__MessageType__Alternatives1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__MessageType__Alternatives1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__MessageType__Alternatives1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__MessageType__Alternatives1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__DataType__Alternatives1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__DataType__Alternatives1325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__DataType__Alternatives1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__DataType__Alternatives1367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__0__Impl_in_rule__SequenceDiagram__Group__01400 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1_in_rule__SequenceDiagram__Group__01403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__1__Impl_in_rule__SequenceDiagram__Group__11461 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2_in_rule__SequenceDiagram__Group__11464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__SequenceDiagram__Group__1__Impl1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__2__Impl_in_rule__SequenceDiagram__Group__21523 = new BitSet(new long[]{0x0000000A02800020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3_in_rule__SequenceDiagram__Group__21526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__DiagramNameAssignment_2_in_rule__SequenceDiagram__Group__2__Impl1553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__3__Impl_in_rule__SequenceDiagram__Group__31583 = new BitSet(new long[]{0x0000000A02800020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4_in_rule__SequenceDiagram__Group__31586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__0_in_rule__SequenceDiagram__Group__3__Impl1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__4__Impl_in_rule__SequenceDiagram__Group__41644 = new BitSet(new long[]{0x0000000A02800020L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__5_in_rule__SequenceDiagram__Group__41647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__LifelinesAssignment_4_in_rule__SequenceDiagram__Group__4__Impl1674 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group__5__Impl_in_rule__SequenceDiagram__Group__51705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__InteractionsAssignment_5_in_rule__SequenceDiagram__Group__5__Impl1732 = new BitSet(new long[]{0x0000000A00000022L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__0__Impl_in_rule__SequenceDiagram__Group_3__01775 = new BitSet(new long[]{0x00000000003C0000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__1_in_rule__SequenceDiagram__Group_3__01778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__SequenceDiagram__Group_3__0__Impl1806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__1__Impl_in_rule__SequenceDiagram__Group_3__11837 = new BitSet(new long[]{0x00000000013C0000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__2_in_rule__SequenceDiagram__Group_3__11840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__LocalsAssignment_3_1_in_rule__SequenceDiagram__Group_3__1__Impl1867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__2__Impl_in_rule__SequenceDiagram__Group_3__21897 = new BitSet(new long[]{0x00000000013C0000L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__3_in_rule__SequenceDiagram__Group_3__21900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__LocalsAssignment_3_2_in_rule__SequenceDiagram__Group_3__2__Impl1927 = new BitSet(new long[]{0x00000000003C0002L});
    public static final BitSet FOLLOW_rule__SequenceDiagram__Group_3__3__Impl_in_rule__SequenceDiagram__Group_3__31958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__SequenceDiagram__Group_3__3__Impl1986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group__0__Impl_in_rule__LocalVariable__Group__02025 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group__1_in_rule__LocalVariable__Group__02028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__TypeAssignment_0_in_rule__LocalVariable__Group__0__Impl2055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__Group__1__Impl_in_rule__LocalVariable__Group__12085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalVariable__NameAssignment_1_in_rule__LocalVariable__Group__1__Impl2112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__0__Impl_in_rule__Lifeline__Group__02146 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__1_in_rule__Lifeline__Group__02149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__Lifeline__Group__0__Impl2177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__1__Impl_in_rule__Lifeline__Group__12208 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__2_in_rule__Lifeline__Group__12211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__CaptionAssignment_1_in_rule__Lifeline__Group__1__Impl2238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__2__Impl_in_rule__Lifeline__Group__22268 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__3_in_rule__Lifeline__Group__22271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__Lifeline__Group__2__Impl2299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__Group__3__Impl_in_rule__Lifeline__Group__32330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lifeline__NameAssignment_3_in_rule__Lifeline__Group__3__Impl2357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__0__Impl_in_rule__TwoLifelineMessage__Group__02395 = new BitSet(new long[]{0x000000000003C000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1_in_rule__TwoLifelineMessage__Group__02398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceLifelineAssignment_0_in_rule__TwoLifelineMessage__Group__0__Impl2425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__1__Impl_in_rule__TwoLifelineMessage__Group__12455 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2_in_rule__TwoLifelineMessage__Group__12458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__MessageTypeAssignment_1_in_rule__TwoLifelineMessage__Group__1__Impl2485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__2__Impl_in_rule__TwoLifelineMessage__Group__22515 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3_in_rule__TwoLifelineMessage__Group__22518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__MessageAssignment_2_in_rule__TwoLifelineMessage__Group__2__Impl2545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__3__Impl_in_rule__TwoLifelineMessage__Group__32575 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4_in_rule__TwoLifelineMessage__Group__32578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__TwoLifelineMessage__Group__3__Impl2606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__4__Impl_in_rule__TwoLifelineMessage__Group__42637 = new BitSet(new long[]{0x000003C030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5_in_rule__TwoLifelineMessage__Group__42640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetLifelineAssignment_4_in_rule__TwoLifelineMessage__Group__4__Impl2667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__5__Impl_in_rule__TwoLifelineMessage__Group__52697 = new BitSet(new long[]{0x000003C030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6_in_rule__TwoLifelineMessage__Group__52700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_5_in_rule__TwoLifelineMessage__Group__5__Impl2727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__6__Impl_in_rule__TwoLifelineMessage__Group__62758 = new BitSet(new long[]{0x000003C030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7_in_rule__TwoLifelineMessage__Group__62761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Alternatives_6_in_rule__TwoLifelineMessage__Group__6__Impl2788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__7__Impl_in_rule__TwoLifelineMessage__Group__72819 = new BitSet(new long[]{0x000003C030000000L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8_in_rule__TwoLifelineMessage__Group__72822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0_in_rule__TwoLifelineMessage__Group__7__Impl2849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group__8__Impl_in_rule__TwoLifelineMessage__Group__82880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0_in_rule__TwoLifelineMessage__Group__8__Impl2907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__0__Impl_in_rule__TwoLifelineMessage__Group_5_1__02956 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__1_in_rule__TwoLifelineMessage__Group_5_1__02959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_0_in_rule__TwoLifelineMessage__Group_5_1__0__Impl2986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_5_1__1__Impl_in_rule__TwoLifelineMessage__Group_5_1__13016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_1_in_rule__TwoLifelineMessage__Group_5_1__1__Impl3043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__0__Impl_in_rule__TwoLifelineMessage__Group_6_1__03078 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__1_in_rule__TwoLifelineMessage__Group_6_1__03081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_0_in_rule__TwoLifelineMessage__Group_6_1__0__Impl3108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_6_1__1__Impl_in_rule__TwoLifelineMessage__Group_6_1__13138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_1_in_rule__TwoLifelineMessage__Group_6_1__1__Impl3165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__0__Impl_in_rule__TwoLifelineMessage__Group_7__03200 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1_in_rule__TwoLifelineMessage__Group_7__03203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__TwoLifelineMessage__Group_7__0__Impl3231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_7__1__Impl_in_rule__TwoLifelineMessage__Group_7__13262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__SourceNoteAssignment_7_1_in_rule__TwoLifelineMessage__Group_7__1__Impl3289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__0__Impl_in_rule__TwoLifelineMessage__Group_8__03323 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1_in_rule__TwoLifelineMessage__Group_8__03326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__TwoLifelineMessage__Group_8__0__Impl3354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__Group_8__1__Impl_in_rule__TwoLifelineMessage__Group_8__13385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TwoLifelineMessage__TargetNoteAssignment_8_1_in_rule__TwoLifelineMessage__Group_8__1__Impl3412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__0__Impl_in_rule__OneLifelineMessage__Group__03446 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1_in_rule__OneLifelineMessage__Group__03449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__LifelineAssignment_0_in_rule__OneLifelineMessage__Group__0__Impl3476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__1__Impl_in_rule__OneLifelineMessage__Group__13506 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2_in_rule__OneLifelineMessage__Group__13509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeAssignment_1_in_rule__OneLifelineMessage__Group__1__Impl3536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__2__Impl_in_rule__OneLifelineMessage__Group__23566 = new BitSet(new long[]{0x00000400C0000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3_in_rule__OneLifelineMessage__Group__23569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__CaptionAssignment_2_in_rule__OneLifelineMessage__Group__2__Impl3596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__3__Impl_in_rule__OneLifelineMessage__Group__33626 = new BitSet(new long[]{0x00000400C0000000L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4_in_rule__OneLifelineMessage__Group__33629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Alternatives_3_in_rule__OneLifelineMessage__Group__3__Impl3656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group__4__Impl_in_rule__OneLifelineMessage__Group__43687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4__0_in_rule__OneLifelineMessage__Group__4__Impl3714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_3_1__0__Impl_in_rule__OneLifelineMessage__Group_3_1__03755 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_3_1__1_in_rule__OneLifelineMessage__Group_3_1__03758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndBlockAssignment_3_1_0_in_rule__OneLifelineMessage__Group_3_1__0__Impl3785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_3_1__1__Impl_in_rule__OneLifelineMessage__Group_3_1__13815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__EndBlockCountAssignment_3_1_1_in_rule__OneLifelineMessage__Group_3_1__1__Impl3842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4__0__Impl_in_rule__OneLifelineMessage__Group_4__03877 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4__1_in_rule__OneLifelineMessage__Group_4__03880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__OneLifelineMessage__Group_4__0__Impl3908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__Group_4__1__Impl_in_rule__OneLifelineMessage__Group_4__13939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__NoteAssignment_4_1_in_rule__OneLifelineMessage__Group_4__1__Impl3966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__0__Impl_in_rule__OneLifelineEndBlock__Group__04000 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__1_in_rule__OneLifelineEndBlock__Group__04003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__LifelineAssignment_0_in_rule__OneLifelineEndBlock__Group__0__Impl4030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__1__Impl_in_rule__OneLifelineEndBlock__Group__14060 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__2_in_rule__OneLifelineEndBlock__Group__14063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__OneLifelineEndBlock__Group__1__Impl4091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__Group__2__Impl_in_rule__OneLifelineEndBlock__Group__24122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineEndBlock__EndBlockCountAssignment_2_in_rule__OneLifelineEndBlock__Group__2__Impl4149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__0__Impl_in_rule__OneLifelineNote__Group__04186 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1_in_rule__OneLifelineNote__Group__04189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__LifelineAssignment_0_in_rule__OneLifelineNote__Group__0__Impl4216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__1__Impl_in_rule__OneLifelineNote__Group__14246 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2_in_rule__OneLifelineNote__Group__14249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__OneLifelineNote__Group__1__Impl4277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__Group__2__Impl_in_rule__OneLifelineNote__Group__24308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineNote__NoteAssignment_2_in_rule__OneLifelineNote__Group__2__Impl4335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__0__Impl_in_rule__DestroyLifelineEvent__Group__04371 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__1_in_rule__DestroyLifelineEvent__Group__04374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__LifelineAssignment_0_in_rule__DestroyLifelineEvent__Group__0__Impl4401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__DestroyLifelineEvent__Group__1__Impl_in_rule__DestroyLifelineEvent__Group__14431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__DestroyLifelineEvent__Group__1__Impl4459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__0__Impl_in_rule__Fragment__Group__04494 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1_in_rule__Fragment__Group__04497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__Fragment__Group__0__Impl4525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__1__Impl_in_rule__Fragment__Group__14556 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2_in_rule__Fragment__Group__14559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__NameAssignment_1_in_rule__Fragment__Group__1__Impl4586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__2__Impl_in_rule__Fragment__Group__24616 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3_in_rule__Fragment__Group__24619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__SectionsAssignment_2_in_rule__Fragment__Group__2__Impl4646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__Group__3__Impl_in_rule__Fragment__Group__34676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Fragment__SectionsAssignment_3_in_rule__Fragment__Group__3__Impl4703 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_rule__Section__Group__0__Impl_in_rule__Section__Group__04742 = new BitSet(new long[]{0x0000000E00000020L});
    public static final BitSet FOLLOW_rule__Section__Group__1_in_rule__Section__Group__04745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Section__Group__0__Impl4773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__1__Impl_in_rule__Section__Group__14804 = new BitSet(new long[]{0x0000000E00000020L});
    public static final BitSet FOLLOW_rule__Section__Group__2_in_rule__Section__Group__14807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__0_in_rule__Section__Group__1__Impl4834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__2__Impl_in_rule__Section__Group__24865 = new BitSet(new long[]{0x0000000A01000020L});
    public static final BitSet FOLLOW_rule__Section__Group__3_in_rule__Section__Group__24868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__InteractionsAssignment_2_in_rule__Section__Group__2__Impl4895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group__3__Impl_in_rule__Section__Group__34925 = new BitSet(new long[]{0x0000000A01000020L});
    public static final BitSet FOLLOW_rule__Section__Group__4_in_rule__Section__Group__34928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__InteractionsAssignment_3_in_rule__Section__Group__3__Impl4955 = new BitSet(new long[]{0x0000000A00000022L});
    public static final BitSet FOLLOW_rule__Section__Group__4__Impl_in_rule__Section__Group__44986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__Section__Group__4__Impl5014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__0__Impl_in_rule__Section__Group_1__05055 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Section__Group_1__1_in_rule__Section__Group_1__05058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__Section__Group_1__0__Impl5086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__Group_1__1__Impl_in_rule__Section__Group_1__15117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Section__LabelAssignment_1_1_in_rule__Section__Group_1__1__Impl5144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__0__Impl_in_rule__Refinement__Group__05178 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1_in_rule__Refinement__Group__05181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__Refinement__Group__0__Impl5209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__1__Impl_in_rule__Refinement__Group__15240 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2_in_rule__Refinement__Group__15243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__Refinement__Group__1__Impl5271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__2__Impl_in_rule__Refinement__Group__25302 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3_in_rule__Refinement__Group__25305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LabelAssignment_2_in_rule__Refinement__Group__2__Impl5332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__3__Impl_in_rule__Refinement__Group__35362 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4_in_rule__Refinement__Group__35365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__Refinement__Group__3__Impl5393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__4__Impl_in_rule__Refinement__Group__45424 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5_in_rule__Refinement__Group__45427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_4_in_rule__Refinement__Group__4__Impl5454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group__5__Impl_in_rule__Refinement__Group__55484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__0_in_rule__Refinement__Group__5__Impl5511 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__0__Impl_in_rule__Refinement__Group_5__05554 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__1_in_rule__Refinement__Group_5__05557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__Refinement__Group_5__0__Impl5585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__Group_5__1__Impl_in_rule__Refinement__Group_5__15616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Refinement__LifelinesAssignment_5_1_in_rule__Refinement__Group_5__1__Impl5643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__SequenceDiagram__DiagramNameAssignment_25682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_rule__SequenceDiagram__LocalsAssignment_3_15713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_rule__SequenceDiagram__LocalsAssignment_3_25744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_rule__SequenceDiagram__LifelinesAssignment_45775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__SequenceDiagram__InteractionsAssignment_55806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataType_in_rule__LocalVariable__TypeAssignment_05837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__LocalVariable__NameAssignment_15868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Lifeline__CaptionAssignment_15899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Lifeline__NameAssignment_35930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__SourceLifelineAssignment_05965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMessageType_in_rule__TwoLifelineMessage__MessageTypeAssignment_16000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__MessageAssignment_26031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__TwoLifelineMessage__TargetLifelineAssignment_46066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_rule__TwoLifelineMessage__SourceStartBlockAssignment_5_06106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rule__TwoLifelineMessage__SourceEndBlockAssignment_5_1_06150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__SourceEndBlockCountAssignment_5_1_16189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rule__TwoLifelineMessage__TargetStartBlockAssignment_6_06225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rule__TwoLifelineMessage__TargetEndBlockAssignment_6_1_06269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__TwoLifelineMessage__TargetEndBlockCountAssignment_6_1_16308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__SourceNoteAssignment_7_16339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__TwoLifelineMessage__TargetNoteAssignment_8_16370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineMessage__LifelineAssignment_06405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OneLifelineMessage__MessageTypeAlternatives_1_0_in_rule__OneLifelineMessage__MessageTypeAssignment_16440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__CaptionAssignment_26473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rule__OneLifelineMessage__StartBlockAssignment_3_06509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__OneLifelineMessage__EndBlockAssignment_3_1_06553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineMessage__EndBlockCountAssignment_3_1_16592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineMessage__NoteAssignment_4_16623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineEndBlock__LifelineAssignment_06658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_rule__OneLifelineEndBlock__EndBlockCountAssignment_26693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OneLifelineNote__LifelineAssignment_06728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__OneLifelineNote__NoteAssignment_26763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__DestroyLifelineEvent__LifelineAssignment_06798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Fragment__NameAssignment_16833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_26864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSection_in_rule__Fragment__SectionsAssignment_36895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Section__LabelAssignment_1_16926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_26957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_rule__Section__InteractionsAssignment_36988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Refinement__LabelAssignment_27019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_47054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Refinement__LifelinesAssignment_5_17093 = new BitSet(new long[]{0x0000000000000002L});

}