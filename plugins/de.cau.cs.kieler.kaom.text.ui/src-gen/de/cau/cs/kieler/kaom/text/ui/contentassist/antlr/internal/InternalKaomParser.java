package de.cau.cs.kieler.kaom.text.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import de.cau.cs.kieler.kaom.text.services.KaomGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKaomParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_COMMENT_ANNOTATION", "RULE_BOOLEAN", "RULE_INT", "RULE_FLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "';'", "'entity'", "'{'", "'}'", "'link'", "'to'", "'port'", "'relation'", "'@'"
    };
    public static final int RULE_BOOLEAN=7;
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int RULE_ANY_OTHER=13;
    public static final int RULE_INT=8;
    public static final int RULE_WS=12;
    public static final int RULE_FLOAT=9;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_COMMENT_ANNOTATION=6;
    public static final int RULE_ML_COMMENT=10;

        public InternalKaomParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g"; }


     
     	private KaomGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(KaomGrammarAccess grammarAccess) {
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




    // $ANTLR start entryRuleTopLevelEntity
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:61:1: entryRuleTopLevelEntity : ruleTopLevelEntity EOF ;
    public final void entryRuleTopLevelEntity() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:62:1: ( ruleTopLevelEntity EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:63:1: ruleTopLevelEntity EOF
            {
             before(grammarAccess.getTopLevelEntityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity61);
            ruleTopLevelEntity();
            _fsp--;

             after(grammarAccess.getTopLevelEntityRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTopLevelEntity68); 

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
    // $ANTLR end entryRuleTopLevelEntity


    // $ANTLR start ruleTopLevelEntity
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:70:1: ruleTopLevelEntity : ( ( rule__TopLevelEntity__Group__0 ) ) ;
    public final void ruleTopLevelEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:74:2: ( ( ( rule__TopLevelEntity__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:75:1: ( ( rule__TopLevelEntity__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:75:1: ( ( rule__TopLevelEntity__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:76:1: ( rule__TopLevelEntity__Group__0 )
            {
             before(grammarAccess.getTopLevelEntityAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:77:1: ( rule__TopLevelEntity__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:77:2: rule__TopLevelEntity__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__0_in_ruleTopLevelEntity94);
            rule__TopLevelEntity__Group__0();
            _fsp--;


            }

             after(grammarAccess.getTopLevelEntityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleTopLevelEntity


    // $ANTLR start entryRuleEntity
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:89:1: entryRuleEntity : ruleEntity EOF ;
    public final void entryRuleEntity() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:90:1: ( ruleEntity EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:91:1: ruleEntity EOF
            {
             before(grammarAccess.getEntityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_entryRuleEntity121);
            ruleEntity();
            _fsp--;

             after(grammarAccess.getEntityRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEntity128); 

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
    // $ANTLR end entryRuleEntity


    // $ANTLR start ruleEntity
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:98:1: ruleEntity : ( ( rule__Entity__Group__0 ) ) ;
    public final void ruleEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:102:2: ( ( ( rule__Entity__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:103:1: ( ( rule__Entity__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:103:1: ( ( rule__Entity__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:104:1: ( rule__Entity__Group__0 )
            {
             before(grammarAccess.getEntityAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:105:1: ( rule__Entity__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:105:2: rule__Entity__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__0_in_ruleEntity154);
            rule__Entity__Group__0();
            _fsp--;


            }

             after(grammarAccess.getEntityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleEntity


    // $ANTLR start entryRuleLink
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:119:1: entryRuleLink : ruleLink EOF ;
    public final void entryRuleLink() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:120:1: ( ruleLink EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:121:1: ruleLink EOF
            {
             before(grammarAccess.getLinkRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_entryRuleLink183);
            ruleLink();
            _fsp--;

             after(grammarAccess.getLinkRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLink190); 

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
    // $ANTLR end entryRuleLink


    // $ANTLR start ruleLink
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:128:1: ruleLink : ( ( rule__Link__Group__0 ) ) ;
    public final void ruleLink() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:132:2: ( ( ( rule__Link__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:133:1: ( ( rule__Link__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:133:1: ( ( rule__Link__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:134:1: ( rule__Link__Group__0 )
            {
             before(grammarAccess.getLinkAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:135:1: ( rule__Link__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:135:2: rule__Link__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__0_in_ruleLink216);
            rule__Link__Group__0();
            _fsp--;


            }

             after(grammarAccess.getLinkAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleLink


    // $ANTLR start entryRulePort
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:147:1: entryRulePort : rulePort EOF ;
    public final void entryRulePort() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:148:1: ( rulePort EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:149:1: rulePort EOF
            {
             before(grammarAccess.getPortRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePort_in_entryRulePort243);
            rulePort();
            _fsp--;

             after(grammarAccess.getPortRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePort250); 

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
    // $ANTLR end entryRulePort


    // $ANTLR start rulePort
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:156:1: rulePort : ( ( rule__Port__Group__0 ) ) ;
    public final void rulePort() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:160:2: ( ( ( rule__Port__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:161:1: ( ( rule__Port__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:161:1: ( ( rule__Port__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:162:1: ( rule__Port__Group__0 )
            {
             before(grammarAccess.getPortAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:163:1: ( rule__Port__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:163:2: rule__Port__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__0_in_rulePort276);
            rule__Port__Group__0();
            _fsp--;


            }

             after(grammarAccess.getPortAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rulePort


    // $ANTLR start entryRuleRelation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:175:1: entryRuleRelation : ruleRelation EOF ;
    public final void entryRuleRelation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:176:1: ( ruleRelation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:177:1: ruleRelation EOF
            {
             before(grammarAccess.getRelationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_entryRuleRelation303);
            ruleRelation();
            _fsp--;

             after(grammarAccess.getRelationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleRelation310); 

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
    // $ANTLR end entryRuleRelation


    // $ANTLR start ruleRelation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:184:1: ruleRelation : ( ( rule__Relation__Group__0 ) ) ;
    public final void ruleRelation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:188:2: ( ( ( rule__Relation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:189:1: ( ( rule__Relation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:189:1: ( ( rule__Relation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:190:1: ( rule__Relation__Group__0 )
            {
             before(grammarAccess.getRelationAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:191:1: ( rule__Relation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:191:2: rule__Relation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__0_in_ruleRelation336);
            rule__Relation__Group__0();
            _fsp--;


            }

             after(grammarAccess.getRelationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleRelation


    // $ANTLR start entryRuleAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:203:1: entryRuleAnnotation : ruleAnnotation EOF ;
    public final void entryRuleAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:204:1: ( ruleAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:205:1: ruleAnnotation EOF
            {
             before(grammarAccess.getAnnotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_entryRuleAnnotation363);
            ruleAnnotation();
            _fsp--;

             after(grammarAccess.getAnnotationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleAnnotation370); 

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
    // $ANTLR end entryRuleAnnotation


    // $ANTLR start ruleAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:212:1: ruleAnnotation : ( ( rule__Annotation__Alternatives ) ) ;
    public final void ruleAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:216:2: ( ( ( rule__Annotation__Alternatives ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:217:1: ( ( rule__Annotation__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:217:1: ( ( rule__Annotation__Alternatives ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:218:1: ( rule__Annotation__Alternatives )
            {
             before(grammarAccess.getAnnotationAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:219:1: ( rule__Annotation__Alternatives )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:219:2: rule__Annotation__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__Annotation__Alternatives_in_ruleAnnotation396);
            rule__Annotation__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getAnnotationAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleAnnotation


    // $ANTLR start entryRuleCommentAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:231:1: entryRuleCommentAnnotation : ruleCommentAnnotation EOF ;
    public final void entryRuleCommentAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:232:1: ( ruleCommentAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:233:1: ruleCommentAnnotation EOF
            {
             before(grammarAccess.getCommentAnnotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation423);
            ruleCommentAnnotation();
            _fsp--;

             after(grammarAccess.getCommentAnnotationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCommentAnnotation430); 

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
    // $ANTLR end entryRuleCommentAnnotation


    // $ANTLR start ruleCommentAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:240:1: ruleCommentAnnotation : ( ( rule__CommentAnnotation__ValueAssignment ) ) ;
    public final void ruleCommentAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:244:2: ( ( ( rule__CommentAnnotation__ValueAssignment ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:245:1: ( ( rule__CommentAnnotation__ValueAssignment ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:245:1: ( ( rule__CommentAnnotation__ValueAssignment ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:246:1: ( rule__CommentAnnotation__ValueAssignment )
            {
             before(grammarAccess.getCommentAnnotationAccess().getValueAssignment()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:247:1: ( rule__CommentAnnotation__ValueAssignment )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:247:2: rule__CommentAnnotation__ValueAssignment
            {
            pushFollow(FollowSets000.FOLLOW_rule__CommentAnnotation__ValueAssignment_in_ruleCommentAnnotation456);
            rule__CommentAnnotation__ValueAssignment();
            _fsp--;


            }

             after(grammarAccess.getCommentAnnotationAccess().getValueAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleCommentAnnotation


    // $ANTLR start entryRuleTagAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:259:1: entryRuleTagAnnotation : ruleTagAnnotation EOF ;
    public final void entryRuleTagAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:260:1: ( ruleTagAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:261:1: ruleTagAnnotation EOF
            {
             before(grammarAccess.getTagAnnotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation483);
            ruleTagAnnotation();
            _fsp--;

             after(grammarAccess.getTagAnnotationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTagAnnotation490); 

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
    // $ANTLR end entryRuleTagAnnotation


    // $ANTLR start ruleTagAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:268:1: ruleTagAnnotation : ( ( rule__TagAnnotation__Group__0 ) ) ;
    public final void ruleTagAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:272:2: ( ( ( rule__TagAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:273:1: ( ( rule__TagAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:273:1: ( ( rule__TagAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:274:1: ( rule__TagAnnotation__Group__0 )
            {
             before(grammarAccess.getTagAnnotationAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:275:1: ( rule__TagAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:275:2: rule__TagAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__0_in_ruleTagAnnotation516);
            rule__TagAnnotation__Group__0();
            _fsp--;


            }

             after(grammarAccess.getTagAnnotationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleTagAnnotation


    // $ANTLR start entryRuleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:287:1: entryRuleKeyStringValueAnnotation : ruleKeyStringValueAnnotation EOF ;
    public final void entryRuleKeyStringValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:288:1: ( ruleKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:289:1: ruleKeyStringValueAnnotation EOF
            {
             before(grammarAccess.getKeyStringValueAnnotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation543);
            ruleKeyStringValueAnnotation();
            _fsp--;

             after(grammarAccess.getKeyStringValueAnnotationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation550); 

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
    // $ANTLR end entryRuleKeyStringValueAnnotation


    // $ANTLR start ruleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:296:1: ruleKeyStringValueAnnotation : ( ( rule__KeyStringValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyStringValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:300:2: ( ( ( rule__KeyStringValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:301:1: ( ( rule__KeyStringValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:301:1: ( ( rule__KeyStringValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:302:1: ( rule__KeyStringValueAnnotation__Group__0 )
            {
             before(grammarAccess.getKeyStringValueAnnotationAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:303:1: ( rule__KeyStringValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:303:2: rule__KeyStringValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__0_in_ruleKeyStringValueAnnotation576);
            rule__KeyStringValueAnnotation__Group__0();
            _fsp--;


            }

             after(grammarAccess.getKeyStringValueAnnotationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleKeyStringValueAnnotation


    // $ANTLR start entryRuleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:315:1: entryRuleKeyBooleanValueAnnotation : ruleKeyBooleanValueAnnotation EOF ;
    public final void entryRuleKeyBooleanValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:316:1: ( ruleKeyBooleanValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:317:1: ruleKeyBooleanValueAnnotation EOF
            {
             before(grammarAccess.getKeyBooleanValueAnnotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation603);
            ruleKeyBooleanValueAnnotation();
            _fsp--;

             after(grammarAccess.getKeyBooleanValueAnnotationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation610); 

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
    // $ANTLR end entryRuleKeyBooleanValueAnnotation


    // $ANTLR start ruleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:324:1: ruleKeyBooleanValueAnnotation : ( ( rule__KeyBooleanValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyBooleanValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:328:2: ( ( ( rule__KeyBooleanValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:329:1: ( ( rule__KeyBooleanValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:329:1: ( ( rule__KeyBooleanValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:330:1: ( rule__KeyBooleanValueAnnotation__Group__0 )
            {
             before(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:331:1: ( rule__KeyBooleanValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:331:2: rule__KeyBooleanValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__0_in_ruleKeyBooleanValueAnnotation636);
            rule__KeyBooleanValueAnnotation__Group__0();
            _fsp--;


            }

             after(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleKeyBooleanValueAnnotation


    // $ANTLR start entryRuleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:343:1: entryRuleKeyIntValueAnnotation : ruleKeyIntValueAnnotation EOF ;
    public final void entryRuleKeyIntValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:344:1: ( ruleKeyIntValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:345:1: ruleKeyIntValueAnnotation EOF
            {
             before(grammarAccess.getKeyIntValueAnnotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation663);
            ruleKeyIntValueAnnotation();
            _fsp--;

             after(grammarAccess.getKeyIntValueAnnotationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation670); 

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
    // $ANTLR end entryRuleKeyIntValueAnnotation


    // $ANTLR start ruleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:352:1: ruleKeyIntValueAnnotation : ( ( rule__KeyIntValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyIntValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:356:2: ( ( ( rule__KeyIntValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:357:1: ( ( rule__KeyIntValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:357:1: ( ( rule__KeyIntValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:358:1: ( rule__KeyIntValueAnnotation__Group__0 )
            {
             before(grammarAccess.getKeyIntValueAnnotationAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:359:1: ( rule__KeyIntValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:359:2: rule__KeyIntValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__0_in_ruleKeyIntValueAnnotation696);
            rule__KeyIntValueAnnotation__Group__0();
            _fsp--;


            }

             after(grammarAccess.getKeyIntValueAnnotationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleKeyIntValueAnnotation


    // $ANTLR start entryRuleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:371:1: entryRuleKeyFloatValueAnnotation : ruleKeyFloatValueAnnotation EOF ;
    public final void entryRuleKeyFloatValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:372:1: ( ruleKeyFloatValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:373:1: ruleKeyFloatValueAnnotation EOF
            {
             before(grammarAccess.getKeyFloatValueAnnotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation723);
            ruleKeyFloatValueAnnotation();
            _fsp--;

             after(grammarAccess.getKeyFloatValueAnnotationRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation730); 

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
    // $ANTLR end entryRuleKeyFloatValueAnnotation


    // $ANTLR start ruleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:380:1: ruleKeyFloatValueAnnotation : ( ( rule__KeyFloatValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyFloatValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:384:2: ( ( ( rule__KeyFloatValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:385:1: ( ( rule__KeyFloatValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:385:1: ( ( rule__KeyFloatValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:386:1: ( rule__KeyFloatValueAnnotation__Group__0 )
            {
             before(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:387:1: ( rule__KeyFloatValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:387:2: rule__KeyFloatValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__0_in_ruleKeyFloatValueAnnotation756);
            rule__KeyFloatValueAnnotation__Group__0();
            _fsp--;


            }

             after(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleKeyFloatValueAnnotation


    // $ANTLR start entryRuleEString
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:401:1: entryRuleEString : ruleEString EOF ;
    public final void entryRuleEString() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:402:1: ( ruleEString EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:403:1: ruleEString EOF
            {
             before(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString785);
            ruleEString();
            _fsp--;

             after(grammarAccess.getEStringRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString792); 

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
    // $ANTLR end entryRuleEString


    // $ANTLR start ruleEString
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:410:1: ruleEString : ( ( rule__EString__Alternatives ) ) ;
    public final void ruleEString() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:414:2: ( ( ( rule__EString__Alternatives ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:415:1: ( ( rule__EString__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:415:1: ( ( rule__EString__Alternatives ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:416:1: ( rule__EString__Alternatives )
            {
             before(grammarAccess.getEStringAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:417:1: ( rule__EString__Alternatives )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:417:2: rule__EString__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__EString__Alternatives_in_ruleEString818);
            rule__EString__Alternatives();
            _fsp--;


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
    // $ANTLR end ruleEString


    // $ANTLR start rule__TopLevelEntity__Alternatives_1_4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:429:1: rule__TopLevelEntity__Alternatives_1_4 : ( ( ( rule__TopLevelEntity__Group_1_4_0__0 ) ) | ( ';' ) );
    public final void rule__TopLevelEntity__Alternatives_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:433:1: ( ( ( rule__TopLevelEntity__Group_1_4_0__0 ) ) | ( ';' ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==16) ) {
                alt1=1;
            }
            else if ( (LA1_0==14) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("429:1: rule__TopLevelEntity__Alternatives_1_4 : ( ( ( rule__TopLevelEntity__Group_1_4_0__0 ) ) | ( ';' ) );", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:434:1: ( ( rule__TopLevelEntity__Group_1_4_0__0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:434:1: ( ( rule__TopLevelEntity__Group_1_4_0__0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:435:1: ( rule__TopLevelEntity__Group_1_4_0__0 )
                    {
                     before(grammarAccess.getTopLevelEntityAccess().getGroup_1_4_0()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:436:1: ( rule__TopLevelEntity__Group_1_4_0__0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:436:2: rule__TopLevelEntity__Group_1_4_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1_4_0__0_in_rule__TopLevelEntity__Alternatives_1_4854);
                    rule__TopLevelEntity__Group_1_4_0__0();
                    _fsp--;


                    }

                     after(grammarAccess.getTopLevelEntityAccess().getGroup_1_4_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:440:6: ( ';' )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:440:6: ( ';' )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:441:1: ';'
                    {
                     before(grammarAccess.getTopLevelEntityAccess().getSemicolonKeyword_1_4_1()); 
                    match(input,14,FollowSets000.FOLLOW_14_in_rule__TopLevelEntity__Alternatives_1_4873); 
                     after(grammarAccess.getTopLevelEntityAccess().getSemicolonKeyword_1_4_1()); 

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
    // $ANTLR end rule__TopLevelEntity__Alternatives_1_4


    // $ANTLR start rule__TopLevelEntity__Alternatives_1_4_0_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:453:1: rule__TopLevelEntity__Alternatives_1_4_0_1 : ( ( ( rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 ) ) | ( ( rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 ) ) | ( ( rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 ) ) | ( ( rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 ) ) );
    public final void rule__TopLevelEntity__Alternatives_1_4_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:457:1: ( ( ( rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 ) ) | ( ( rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 ) ) | ( ( rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 ) ) | ( ( rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 ) ) )
            int alt2=4;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:458:1: ( ( rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:458:1: ( ( rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:459:1: ( rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 )
                    {
                     before(grammarAccess.getTopLevelEntityAccess().getChildEntitiesAssignment_1_4_0_1_0()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:460:1: ( rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:460:2: rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0_in_rule__TopLevelEntity__Alternatives_1_4_0_1907);
                    rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0();
                    _fsp--;


                    }

                     after(grammarAccess.getTopLevelEntityAccess().getChildEntitiesAssignment_1_4_0_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:464:6: ( ( rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:464:6: ( ( rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:465:1: ( rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 )
                    {
                     before(grammarAccess.getTopLevelEntityAccess().getChildLinksAssignment_1_4_0_1_1()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:466:1: ( rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:466:2: rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1_in_rule__TopLevelEntity__Alternatives_1_4_0_1925);
                    rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1();
                    _fsp--;


                    }

                     after(grammarAccess.getTopLevelEntityAccess().getChildLinksAssignment_1_4_0_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:470:6: ( ( rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:470:6: ( ( rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:471:1: ( rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 )
                    {
                     before(grammarAccess.getTopLevelEntityAccess().getChildPortsAssignment_1_4_0_1_2()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:472:1: ( rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:472:2: rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2_in_rule__TopLevelEntity__Alternatives_1_4_0_1943);
                    rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2();
                    _fsp--;


                    }

                     after(grammarAccess.getTopLevelEntityAccess().getChildPortsAssignment_1_4_0_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:476:6: ( ( rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:476:6: ( ( rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:477:1: ( rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 )
                    {
                     before(grammarAccess.getTopLevelEntityAccess().getChildRelationsAssignment_1_4_0_1_3()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:478:1: ( rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:478:2: rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3_in_rule__TopLevelEntity__Alternatives_1_4_0_1961);
                    rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3();
                    _fsp--;


                    }

                     after(grammarAccess.getTopLevelEntityAccess().getChildRelationsAssignment_1_4_0_1_3()); 

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
    // $ANTLR end rule__TopLevelEntity__Alternatives_1_4_0_1


    // $ANTLR start rule__Entity__Alternatives_5
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:487:1: rule__Entity__Alternatives_5 : ( ( ( rule__Entity__Group_5_0__0 ) ) | ( ';' ) );
    public final void rule__Entity__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:491:1: ( ( ( rule__Entity__Group_5_0__0 ) ) | ( ';' ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==16) ) {
                alt3=1;
            }
            else if ( (LA3_0==14) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("487:1: rule__Entity__Alternatives_5 : ( ( ( rule__Entity__Group_5_0__0 ) ) | ( ';' ) );", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:492:1: ( ( rule__Entity__Group_5_0__0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:492:1: ( ( rule__Entity__Group_5_0__0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:493:1: ( rule__Entity__Group_5_0__0 )
                    {
                     before(grammarAccess.getEntityAccess().getGroup_5_0()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:494:1: ( rule__Entity__Group_5_0__0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:494:2: rule__Entity__Group_5_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__0_in_rule__Entity__Alternatives_5994);
                    rule__Entity__Group_5_0__0();
                    _fsp--;


                    }

                     after(grammarAccess.getEntityAccess().getGroup_5_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:498:6: ( ';' )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:498:6: ( ';' )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:499:1: ';'
                    {
                     before(grammarAccess.getEntityAccess().getSemicolonKeyword_5_1()); 
                    match(input,14,FollowSets000.FOLLOW_14_in_rule__Entity__Alternatives_51013); 
                     after(grammarAccess.getEntityAccess().getSemicolonKeyword_5_1()); 

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
    // $ANTLR end rule__Entity__Alternatives_5


    // $ANTLR start rule__Entity__Alternatives_5_0_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:511:1: rule__Entity__Alternatives_5_0_1 : ( ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) ) | ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) ) | ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) ) | ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) ) );
    public final void rule__Entity__Alternatives_5_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:515:1: ( ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) ) | ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) ) | ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) ) | ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) ) )
            int alt4=4;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:516:1: ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:516:1: ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:517:1: ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 )
                    {
                     before(grammarAccess.getEntityAccess().getChildEntitiesAssignment_5_0_1_0()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:518:1: ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:518:2: rule__Entity__ChildEntitiesAssignment_5_0_1_0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildEntitiesAssignment_5_0_1_0_in_rule__Entity__Alternatives_5_0_11047);
                    rule__Entity__ChildEntitiesAssignment_5_0_1_0();
                    _fsp--;


                    }

                     after(grammarAccess.getEntityAccess().getChildEntitiesAssignment_5_0_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:522:6: ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:522:6: ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:523:1: ( rule__Entity__ChildLinksAssignment_5_0_1_1 )
                    {
                     before(grammarAccess.getEntityAccess().getChildLinksAssignment_5_0_1_1()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:524:1: ( rule__Entity__ChildLinksAssignment_5_0_1_1 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:524:2: rule__Entity__ChildLinksAssignment_5_0_1_1
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildLinksAssignment_5_0_1_1_in_rule__Entity__Alternatives_5_0_11065);
                    rule__Entity__ChildLinksAssignment_5_0_1_1();
                    _fsp--;


                    }

                     after(grammarAccess.getEntityAccess().getChildLinksAssignment_5_0_1_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:528:6: ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:528:6: ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:529:1: ( rule__Entity__ChildPortsAssignment_5_0_1_2 )
                    {
                     before(grammarAccess.getEntityAccess().getChildPortsAssignment_5_0_1_2()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:530:1: ( rule__Entity__ChildPortsAssignment_5_0_1_2 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:530:2: rule__Entity__ChildPortsAssignment_5_0_1_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildPortsAssignment_5_0_1_2_in_rule__Entity__Alternatives_5_0_11083);
                    rule__Entity__ChildPortsAssignment_5_0_1_2();
                    _fsp--;


                    }

                     after(grammarAccess.getEntityAccess().getChildPortsAssignment_5_0_1_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:534:6: ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:534:6: ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:535:1: ( rule__Entity__ChildRelationsAssignment_5_0_1_3 )
                    {
                     before(grammarAccess.getEntityAccess().getChildRelationsAssignment_5_0_1_3()); 
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:536:1: ( rule__Entity__ChildRelationsAssignment_5_0_1_3 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:536:2: rule__Entity__ChildRelationsAssignment_5_0_1_3
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildRelationsAssignment_5_0_1_3_in_rule__Entity__Alternatives_5_0_11101);
                    rule__Entity__ChildRelationsAssignment_5_0_1_3();
                    _fsp--;


                    }

                     after(grammarAccess.getEntityAccess().getChildRelationsAssignment_5_0_1_3()); 

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
    // $ANTLR end rule__Entity__Alternatives_5_0_1


    // $ANTLR start rule__Annotation__Alternatives
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:546:1: rule__Annotation__Alternatives : ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) );
    public final void rule__Annotation__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:550:1: ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) )
            int alt5=6;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_COMMENT_ANNOTATION) ) {
                alt5=1;
            }
            else if ( (LA5_0==22) ) {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==RULE_ID) ) {
                    switch ( input.LA(3) ) {
                    case RULE_FLOAT:
                        {
                        alt5=6;
                        }
                        break;
                    case EOF:
                    case RULE_COMMENT_ANNOTATION:
                    case 15:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                        {
                        alt5=2;
                        }
                        break;
                    case RULE_BOOLEAN:
                        {
                        alt5=4;
                        }
                        break;
                    case RULE_INT:
                        {
                        alt5=5;
                        }
                        break;
                    case RULE_STRING:
                    case RULE_ID:
                        {
                        alt5=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("546:1: rule__Annotation__Alternatives : ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) );", 5, 3, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("546:1: rule__Annotation__Alternatives : ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) );", 5, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("546:1: rule__Annotation__Alternatives : ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:551:1: ( ruleCommentAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:551:1: ( ruleCommentAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:552:1: ruleCommentAnnotation
                    {
                     before(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0()); 
                    pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_rule__Annotation__Alternatives1135);
                    ruleCommentAnnotation();
                    _fsp--;

                     after(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:557:6: ( ruleTagAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:557:6: ( ruleTagAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:558:1: ruleTagAnnotation
                    {
                     before(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1()); 
                    pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_rule__Annotation__Alternatives1152);
                    ruleTagAnnotation();
                    _fsp--;

                     after(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:563:6: ( ruleKeyStringValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:563:6: ( ruleKeyStringValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:564:1: ruleKeyStringValueAnnotation
                    {
                     before(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2()); 
                    pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_rule__Annotation__Alternatives1169);
                    ruleKeyStringValueAnnotation();
                    _fsp--;

                     after(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:569:6: ( ruleKeyBooleanValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:569:6: ( ruleKeyBooleanValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:570:1: ruleKeyBooleanValueAnnotation
                    {
                     before(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_3()); 
                    pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_rule__Annotation__Alternatives1186);
                    ruleKeyBooleanValueAnnotation();
                    _fsp--;

                     after(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:575:6: ( ruleKeyIntValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:575:6: ( ruleKeyIntValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:576:1: ruleKeyIntValueAnnotation
                    {
                     before(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_4()); 
                    pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_rule__Annotation__Alternatives1203);
                    ruleKeyIntValueAnnotation();
                    _fsp--;

                     after(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:581:6: ( ruleKeyFloatValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:581:6: ( ruleKeyFloatValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:582:1: ruleKeyFloatValueAnnotation
                    {
                     before(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_5()); 
                    pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_rule__Annotation__Alternatives1220);
                    ruleKeyFloatValueAnnotation();
                    _fsp--;

                     after(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_5()); 

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
    // $ANTLR end rule__Annotation__Alternatives


    // $ANTLR start rule__EString__Alternatives
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:592:1: rule__EString__Alternatives : ( ( RULE_STRING ) | ( RULE_ID ) );
    public final void rule__EString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:596:1: ( ( RULE_STRING ) | ( RULE_ID ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_STRING) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_ID) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("592:1: rule__EString__Alternatives : ( ( RULE_STRING ) | ( RULE_ID ) );", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:597:1: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:597:1: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:598:1: RULE_STRING
                    {
                     before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__EString__Alternatives1252); 
                     after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:603:6: ( RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:603:6: ( RULE_ID )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:604:1: RULE_ID
                    {
                     before(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__EString__Alternatives1269); 
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
    // $ANTLR end rule__EString__Alternatives


    // $ANTLR start rule__TopLevelEntity__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:616:1: rule__TopLevelEntity__Group__0 : rule__TopLevelEntity__Group__0__Impl rule__TopLevelEntity__Group__1 ;
    public final void rule__TopLevelEntity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:620:1: ( rule__TopLevelEntity__Group__0__Impl rule__TopLevelEntity__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:621:2: rule__TopLevelEntity__Group__0__Impl rule__TopLevelEntity__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__0__Impl_in_rule__TopLevelEntity__Group__01299);
            rule__TopLevelEntity__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__1_in_rule__TopLevelEntity__Group__01302);
            rule__TopLevelEntity__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group__0


    // $ANTLR start rule__TopLevelEntity__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:628:1: rule__TopLevelEntity__Group__0__Impl : ( () ) ;
    public final void rule__TopLevelEntity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:632:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:633:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:633:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:634:1: ()
            {
             before(grammarAccess.getTopLevelEntityAccess().getEntityAction_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:635:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:637:1: 
            {
            }

             after(grammarAccess.getTopLevelEntityAccess().getEntityAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group__0__Impl


    // $ANTLR start rule__TopLevelEntity__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:647:1: rule__TopLevelEntity__Group__1 : rule__TopLevelEntity__Group__1__Impl ;
    public final void rule__TopLevelEntity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:651:1: ( rule__TopLevelEntity__Group__1__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:652:2: rule__TopLevelEntity__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__1__Impl_in_rule__TopLevelEntity__Group__11360);
            rule__TopLevelEntity__Group__1__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group__1


    // $ANTLR start rule__TopLevelEntity__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:658:1: rule__TopLevelEntity__Group__1__Impl : ( ( rule__TopLevelEntity__Group_1__0 )? ) ;
    public final void rule__TopLevelEntity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:662:1: ( ( ( rule__TopLevelEntity__Group_1__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:663:1: ( ( rule__TopLevelEntity__Group_1__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:663:1: ( ( rule__TopLevelEntity__Group_1__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:664:1: ( rule__TopLevelEntity__Group_1__0 )?
            {
             before(grammarAccess.getTopLevelEntityAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:665:1: ( rule__TopLevelEntity__Group_1__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_COMMENT_ANNOTATION||LA7_0==15||LA7_0==22) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:665:2: rule__TopLevelEntity__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__0_in_rule__TopLevelEntity__Group__1__Impl1387);
                    rule__TopLevelEntity__Group_1__0();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTopLevelEntityAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group__1__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:679:1: rule__TopLevelEntity__Group_1__0 : rule__TopLevelEntity__Group_1__0__Impl rule__TopLevelEntity__Group_1__1 ;
    public final void rule__TopLevelEntity__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:683:1: ( rule__TopLevelEntity__Group_1__0__Impl rule__TopLevelEntity__Group_1__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:684:2: rule__TopLevelEntity__Group_1__0__Impl rule__TopLevelEntity__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__0__Impl_in_rule__TopLevelEntity__Group_1__01422);
            rule__TopLevelEntity__Group_1__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__1_in_rule__TopLevelEntity__Group_1__01425);
            rule__TopLevelEntity__Group_1__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__0


    // $ANTLR start rule__TopLevelEntity__Group_1__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:691:1: rule__TopLevelEntity__Group_1__0__Impl : ( ( rule__TopLevelEntity__AnnotationsAssignment_1_0 )* ) ;
    public final void rule__TopLevelEntity__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:695:1: ( ( ( rule__TopLevelEntity__AnnotationsAssignment_1_0 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:696:1: ( ( rule__TopLevelEntity__AnnotationsAssignment_1_0 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:696:1: ( ( rule__TopLevelEntity__AnnotationsAssignment_1_0 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:697:1: ( rule__TopLevelEntity__AnnotationsAssignment_1_0 )*
            {
             before(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_1_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:698:1: ( rule__TopLevelEntity__AnnotationsAssignment_1_0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_COMMENT_ANNOTATION||LA8_0==22) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:698:2: rule__TopLevelEntity__AnnotationsAssignment_1_0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__AnnotationsAssignment_1_0_in_rule__TopLevelEntity__Group_1__0__Impl1452);
            	    rule__TopLevelEntity__AnnotationsAssignment_1_0();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__0__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:708:1: rule__TopLevelEntity__Group_1__1 : rule__TopLevelEntity__Group_1__1__Impl rule__TopLevelEntity__Group_1__2 ;
    public final void rule__TopLevelEntity__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:712:1: ( rule__TopLevelEntity__Group_1__1__Impl rule__TopLevelEntity__Group_1__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:713:2: rule__TopLevelEntity__Group_1__1__Impl rule__TopLevelEntity__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__1__Impl_in_rule__TopLevelEntity__Group_1__11483);
            rule__TopLevelEntity__Group_1__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__2_in_rule__TopLevelEntity__Group_1__11486);
            rule__TopLevelEntity__Group_1__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__1


    // $ANTLR start rule__TopLevelEntity__Group_1__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:720:1: rule__TopLevelEntity__Group_1__1__Impl : ( 'entity' ) ;
    public final void rule__TopLevelEntity__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:724:1: ( ( 'entity' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:725:1: ( 'entity' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:725:1: ( 'entity' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:726:1: 'entity'
            {
             before(grammarAccess.getTopLevelEntityAccess().getEntityKeyword_1_1()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__TopLevelEntity__Group_1__1__Impl1514); 
             after(grammarAccess.getTopLevelEntityAccess().getEntityKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__1__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:739:1: rule__TopLevelEntity__Group_1__2 : rule__TopLevelEntity__Group_1__2__Impl rule__TopLevelEntity__Group_1__3 ;
    public final void rule__TopLevelEntity__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:743:1: ( rule__TopLevelEntity__Group_1__2__Impl rule__TopLevelEntity__Group_1__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:744:2: rule__TopLevelEntity__Group_1__2__Impl rule__TopLevelEntity__Group_1__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__2__Impl_in_rule__TopLevelEntity__Group_1__21545);
            rule__TopLevelEntity__Group_1__2__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__3_in_rule__TopLevelEntity__Group_1__21548);
            rule__TopLevelEntity__Group_1__3();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__2


    // $ANTLR start rule__TopLevelEntity__Group_1__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:751:1: rule__TopLevelEntity__Group_1__2__Impl : ( ( rule__TopLevelEntity__IdAssignment_1_2 ) ) ;
    public final void rule__TopLevelEntity__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:755:1: ( ( ( rule__TopLevelEntity__IdAssignment_1_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:756:1: ( ( rule__TopLevelEntity__IdAssignment_1_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:756:1: ( ( rule__TopLevelEntity__IdAssignment_1_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:757:1: ( rule__TopLevelEntity__IdAssignment_1_2 )
            {
             before(grammarAccess.getTopLevelEntityAccess().getIdAssignment_1_2()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:758:1: ( rule__TopLevelEntity__IdAssignment_1_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:758:2: rule__TopLevelEntity__IdAssignment_1_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__IdAssignment_1_2_in_rule__TopLevelEntity__Group_1__2__Impl1575);
            rule__TopLevelEntity__IdAssignment_1_2();
            _fsp--;


            }

             after(grammarAccess.getTopLevelEntityAccess().getIdAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__2__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1__3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:768:1: rule__TopLevelEntity__Group_1__3 : rule__TopLevelEntity__Group_1__3__Impl rule__TopLevelEntity__Group_1__4 ;
    public final void rule__TopLevelEntity__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:772:1: ( rule__TopLevelEntity__Group_1__3__Impl rule__TopLevelEntity__Group_1__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:773:2: rule__TopLevelEntity__Group_1__3__Impl rule__TopLevelEntity__Group_1__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__3__Impl_in_rule__TopLevelEntity__Group_1__31605);
            rule__TopLevelEntity__Group_1__3__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__4_in_rule__TopLevelEntity__Group_1__31608);
            rule__TopLevelEntity__Group_1__4();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__3


    // $ANTLR start rule__TopLevelEntity__Group_1__3__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:780:1: rule__TopLevelEntity__Group_1__3__Impl : ( ( rule__TopLevelEntity__NameAssignment_1_3 )? ) ;
    public final void rule__TopLevelEntity__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:784:1: ( ( ( rule__TopLevelEntity__NameAssignment_1_3 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:785:1: ( ( rule__TopLevelEntity__NameAssignment_1_3 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:785:1: ( ( rule__TopLevelEntity__NameAssignment_1_3 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:786:1: ( rule__TopLevelEntity__NameAssignment_1_3 )?
            {
             before(grammarAccess.getTopLevelEntityAccess().getNameAssignment_1_3()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:787:1: ( rule__TopLevelEntity__NameAssignment_1_3 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_STRING) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:787:2: rule__TopLevelEntity__NameAssignment_1_3
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__NameAssignment_1_3_in_rule__TopLevelEntity__Group_1__3__Impl1635);
                    rule__TopLevelEntity__NameAssignment_1_3();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getTopLevelEntityAccess().getNameAssignment_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__3__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1__4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:797:1: rule__TopLevelEntity__Group_1__4 : rule__TopLevelEntity__Group_1__4__Impl ;
    public final void rule__TopLevelEntity__Group_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:801:1: ( rule__TopLevelEntity__Group_1__4__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:802:2: rule__TopLevelEntity__Group_1__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1__4__Impl_in_rule__TopLevelEntity__Group_1__41666);
            rule__TopLevelEntity__Group_1__4__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__4


    // $ANTLR start rule__TopLevelEntity__Group_1__4__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:808:1: rule__TopLevelEntity__Group_1__4__Impl : ( ( rule__TopLevelEntity__Alternatives_1_4 ) ) ;
    public final void rule__TopLevelEntity__Group_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:812:1: ( ( ( rule__TopLevelEntity__Alternatives_1_4 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:813:1: ( ( rule__TopLevelEntity__Alternatives_1_4 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:813:1: ( ( rule__TopLevelEntity__Alternatives_1_4 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:814:1: ( rule__TopLevelEntity__Alternatives_1_4 )
            {
             before(grammarAccess.getTopLevelEntityAccess().getAlternatives_1_4()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:815:1: ( rule__TopLevelEntity__Alternatives_1_4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:815:2: rule__TopLevelEntity__Alternatives_1_4
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Alternatives_1_4_in_rule__TopLevelEntity__Group_1__4__Impl1693);
            rule__TopLevelEntity__Alternatives_1_4();
            _fsp--;


            }

             after(grammarAccess.getTopLevelEntityAccess().getAlternatives_1_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1__4__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1_4_0__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:835:1: rule__TopLevelEntity__Group_1_4_0__0 : rule__TopLevelEntity__Group_1_4_0__0__Impl rule__TopLevelEntity__Group_1_4_0__1 ;
    public final void rule__TopLevelEntity__Group_1_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:839:1: ( rule__TopLevelEntity__Group_1_4_0__0__Impl rule__TopLevelEntity__Group_1_4_0__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:840:2: rule__TopLevelEntity__Group_1_4_0__0__Impl rule__TopLevelEntity__Group_1_4_0__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1_4_0__0__Impl_in_rule__TopLevelEntity__Group_1_4_0__01733);
            rule__TopLevelEntity__Group_1_4_0__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1_4_0__1_in_rule__TopLevelEntity__Group_1_4_0__01736);
            rule__TopLevelEntity__Group_1_4_0__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1_4_0__0


    // $ANTLR start rule__TopLevelEntity__Group_1_4_0__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:847:1: rule__TopLevelEntity__Group_1_4_0__0__Impl : ( '{' ) ;
    public final void rule__TopLevelEntity__Group_1_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:851:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:852:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:852:1: ( '{' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:853:1: '{'
            {
             before(grammarAccess.getTopLevelEntityAccess().getLeftCurlyBracketKeyword_1_4_0_0()); 
            match(input,16,FollowSets000.FOLLOW_16_in_rule__TopLevelEntity__Group_1_4_0__0__Impl1764); 
             after(grammarAccess.getTopLevelEntityAccess().getLeftCurlyBracketKeyword_1_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1_4_0__0__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1_4_0__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:866:1: rule__TopLevelEntity__Group_1_4_0__1 : rule__TopLevelEntity__Group_1_4_0__1__Impl rule__TopLevelEntity__Group_1_4_0__2 ;
    public final void rule__TopLevelEntity__Group_1_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:870:1: ( rule__TopLevelEntity__Group_1_4_0__1__Impl rule__TopLevelEntity__Group_1_4_0__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:871:2: rule__TopLevelEntity__Group_1_4_0__1__Impl rule__TopLevelEntity__Group_1_4_0__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1_4_0__1__Impl_in_rule__TopLevelEntity__Group_1_4_0__11795);
            rule__TopLevelEntity__Group_1_4_0__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1_4_0__2_in_rule__TopLevelEntity__Group_1_4_0__11798);
            rule__TopLevelEntity__Group_1_4_0__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1_4_0__1


    // $ANTLR start rule__TopLevelEntity__Group_1_4_0__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:878:1: rule__TopLevelEntity__Group_1_4_0__1__Impl : ( ( rule__TopLevelEntity__Alternatives_1_4_0_1 )* ) ;
    public final void rule__TopLevelEntity__Group_1_4_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:882:1: ( ( ( rule__TopLevelEntity__Alternatives_1_4_0_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:883:1: ( ( rule__TopLevelEntity__Alternatives_1_4_0_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:883:1: ( ( rule__TopLevelEntity__Alternatives_1_4_0_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:884:1: ( rule__TopLevelEntity__Alternatives_1_4_0_1 )*
            {
             before(grammarAccess.getTopLevelEntityAccess().getAlternatives_1_4_0_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:885:1: ( rule__TopLevelEntity__Alternatives_1_4_0_1 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_COMMENT_ANNOTATION||LA10_0==15||LA10_0==18||(LA10_0>=20 && LA10_0<=22)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:885:2: rule__TopLevelEntity__Alternatives_1_4_0_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Alternatives_1_4_0_1_in_rule__TopLevelEntity__Group_1_4_0__1__Impl1825);
            	    rule__TopLevelEntity__Alternatives_1_4_0_1();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getTopLevelEntityAccess().getAlternatives_1_4_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1_4_0__1__Impl


    // $ANTLR start rule__TopLevelEntity__Group_1_4_0__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:895:1: rule__TopLevelEntity__Group_1_4_0__2 : rule__TopLevelEntity__Group_1_4_0__2__Impl ;
    public final void rule__TopLevelEntity__Group_1_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:899:1: ( rule__TopLevelEntity__Group_1_4_0__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:900:2: rule__TopLevelEntity__Group_1_4_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_1_4_0__2__Impl_in_rule__TopLevelEntity__Group_1_4_0__21856);
            rule__TopLevelEntity__Group_1_4_0__2__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1_4_0__2


    // $ANTLR start rule__TopLevelEntity__Group_1_4_0__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:906:1: rule__TopLevelEntity__Group_1_4_0__2__Impl : ( '}' ) ;
    public final void rule__TopLevelEntity__Group_1_4_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:910:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:911:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:911:1: ( '}' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:912:1: '}'
            {
             before(grammarAccess.getTopLevelEntityAccess().getRightCurlyBracketKeyword_1_4_0_2()); 
            match(input,17,FollowSets000.FOLLOW_17_in_rule__TopLevelEntity__Group_1_4_0__2__Impl1884); 
             after(grammarAccess.getTopLevelEntityAccess().getRightCurlyBracketKeyword_1_4_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__Group_1_4_0__2__Impl


    // $ANTLR start rule__Entity__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:931:1: rule__Entity__Group__0 : rule__Entity__Group__0__Impl rule__Entity__Group__1 ;
    public final void rule__Entity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:935:1: ( rule__Entity__Group__0__Impl rule__Entity__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:936:2: rule__Entity__Group__0__Impl rule__Entity__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__0__Impl_in_rule__Entity__Group__01921);
            rule__Entity__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__1_in_rule__Entity__Group__01924);
            rule__Entity__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__0


    // $ANTLR start rule__Entity__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:943:1: rule__Entity__Group__0__Impl : ( () ) ;
    public final void rule__Entity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:947:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:948:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:948:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:949:1: ()
            {
             before(grammarAccess.getEntityAccess().getEntityAction_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:950:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:952:1: 
            {
            }

             after(grammarAccess.getEntityAccess().getEntityAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__0__Impl


    // $ANTLR start rule__Entity__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:962:1: rule__Entity__Group__1 : rule__Entity__Group__1__Impl rule__Entity__Group__2 ;
    public final void rule__Entity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:966:1: ( rule__Entity__Group__1__Impl rule__Entity__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:967:2: rule__Entity__Group__1__Impl rule__Entity__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__1__Impl_in_rule__Entity__Group__11982);
            rule__Entity__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__2_in_rule__Entity__Group__11985);
            rule__Entity__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__1


    // $ANTLR start rule__Entity__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:974:1: rule__Entity__Group__1__Impl : ( ( rule__Entity__AnnotationsAssignment_1 )* ) ;
    public final void rule__Entity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:978:1: ( ( ( rule__Entity__AnnotationsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:979:1: ( ( rule__Entity__AnnotationsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:979:1: ( ( rule__Entity__AnnotationsAssignment_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:980:1: ( rule__Entity__AnnotationsAssignment_1 )*
            {
             before(grammarAccess.getEntityAccess().getAnnotationsAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:981:1: ( rule__Entity__AnnotationsAssignment_1 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_COMMENT_ANNOTATION||LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:981:2: rule__Entity__AnnotationsAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Entity__AnnotationsAssignment_1_in_rule__Entity__Group__1__Impl2012);
            	    rule__Entity__AnnotationsAssignment_1();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getAnnotationsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__1__Impl


    // $ANTLR start rule__Entity__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:991:1: rule__Entity__Group__2 : rule__Entity__Group__2__Impl rule__Entity__Group__3 ;
    public final void rule__Entity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:995:1: ( rule__Entity__Group__2__Impl rule__Entity__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:996:2: rule__Entity__Group__2__Impl rule__Entity__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__2__Impl_in_rule__Entity__Group__22043);
            rule__Entity__Group__2__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__3_in_rule__Entity__Group__22046);
            rule__Entity__Group__3();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__2


    // $ANTLR start rule__Entity__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1003:1: rule__Entity__Group__2__Impl : ( 'entity' ) ;
    public final void rule__Entity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1007:1: ( ( 'entity' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1008:1: ( 'entity' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1008:1: ( 'entity' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1009:1: 'entity'
            {
             before(grammarAccess.getEntityAccess().getEntityKeyword_2()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__Entity__Group__2__Impl2074); 
             after(grammarAccess.getEntityAccess().getEntityKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__2__Impl


    // $ANTLR start rule__Entity__Group__3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1022:1: rule__Entity__Group__3 : rule__Entity__Group__3__Impl rule__Entity__Group__4 ;
    public final void rule__Entity__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1026:1: ( rule__Entity__Group__3__Impl rule__Entity__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1027:2: rule__Entity__Group__3__Impl rule__Entity__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__3__Impl_in_rule__Entity__Group__32105);
            rule__Entity__Group__3__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__4_in_rule__Entity__Group__32108);
            rule__Entity__Group__4();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__3


    // $ANTLR start rule__Entity__Group__3__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1034:1: rule__Entity__Group__3__Impl : ( ( rule__Entity__IdAssignment_3 ) ) ;
    public final void rule__Entity__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1038:1: ( ( ( rule__Entity__IdAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1039:1: ( ( rule__Entity__IdAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1039:1: ( ( rule__Entity__IdAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1040:1: ( rule__Entity__IdAssignment_3 )
            {
             before(grammarAccess.getEntityAccess().getIdAssignment_3()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1041:1: ( rule__Entity__IdAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1041:2: rule__Entity__IdAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__IdAssignment_3_in_rule__Entity__Group__3__Impl2135);
            rule__Entity__IdAssignment_3();
            _fsp--;


            }

             after(grammarAccess.getEntityAccess().getIdAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__3__Impl


    // $ANTLR start rule__Entity__Group__4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1051:1: rule__Entity__Group__4 : rule__Entity__Group__4__Impl rule__Entity__Group__5 ;
    public final void rule__Entity__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1055:1: ( rule__Entity__Group__4__Impl rule__Entity__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1056:2: rule__Entity__Group__4__Impl rule__Entity__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__4__Impl_in_rule__Entity__Group__42165);
            rule__Entity__Group__4__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__5_in_rule__Entity__Group__42168);
            rule__Entity__Group__5();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__4


    // $ANTLR start rule__Entity__Group__4__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1063:1: rule__Entity__Group__4__Impl : ( ( rule__Entity__NameAssignment_4 )? ) ;
    public final void rule__Entity__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1067:1: ( ( ( rule__Entity__NameAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1068:1: ( ( rule__Entity__NameAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1068:1: ( ( rule__Entity__NameAssignment_4 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1069:1: ( rule__Entity__NameAssignment_4 )?
            {
             before(grammarAccess.getEntityAccess().getNameAssignment_4()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1070:1: ( rule__Entity__NameAssignment_4 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_STRING) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1070:2: rule__Entity__NameAssignment_4
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__NameAssignment_4_in_rule__Entity__Group__4__Impl2195);
                    rule__Entity__NameAssignment_4();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEntityAccess().getNameAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__4__Impl


    // $ANTLR start rule__Entity__Group__5
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1080:1: rule__Entity__Group__5 : rule__Entity__Group__5__Impl ;
    public final void rule__Entity__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1084:1: ( rule__Entity__Group__5__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1085:2: rule__Entity__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__5__Impl_in_rule__Entity__Group__52226);
            rule__Entity__Group__5__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__5


    // $ANTLR start rule__Entity__Group__5__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1091:1: rule__Entity__Group__5__Impl : ( ( rule__Entity__Alternatives_5 ) ) ;
    public final void rule__Entity__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1095:1: ( ( ( rule__Entity__Alternatives_5 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1096:1: ( ( rule__Entity__Alternatives_5 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1096:1: ( ( rule__Entity__Alternatives_5 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1097:1: ( rule__Entity__Alternatives_5 )
            {
             before(grammarAccess.getEntityAccess().getAlternatives_5()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1098:1: ( rule__Entity__Alternatives_5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1098:2: rule__Entity__Alternatives_5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Alternatives_5_in_rule__Entity__Group__5__Impl2253);
            rule__Entity__Alternatives_5();
            _fsp--;


            }

             after(grammarAccess.getEntityAccess().getAlternatives_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group__5__Impl


    // $ANTLR start rule__Entity__Group_5_0__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1120:1: rule__Entity__Group_5_0__0 : rule__Entity__Group_5_0__0__Impl rule__Entity__Group_5_0__1 ;
    public final void rule__Entity__Group_5_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1124:1: ( rule__Entity__Group_5_0__0__Impl rule__Entity__Group_5_0__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1125:2: rule__Entity__Group_5_0__0__Impl rule__Entity__Group_5_0__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__0__Impl_in_rule__Entity__Group_5_0__02295);
            rule__Entity__Group_5_0__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__1_in_rule__Entity__Group_5_0__02298);
            rule__Entity__Group_5_0__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group_5_0__0


    // $ANTLR start rule__Entity__Group_5_0__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1132:1: rule__Entity__Group_5_0__0__Impl : ( '{' ) ;
    public final void rule__Entity__Group_5_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1136:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1137:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1137:1: ( '{' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1138:1: '{'
            {
             before(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_0_0()); 
            match(input,16,FollowSets000.FOLLOW_16_in_rule__Entity__Group_5_0__0__Impl2326); 
             after(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group_5_0__0__Impl


    // $ANTLR start rule__Entity__Group_5_0__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1151:1: rule__Entity__Group_5_0__1 : rule__Entity__Group_5_0__1__Impl rule__Entity__Group_5_0__2 ;
    public final void rule__Entity__Group_5_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1155:1: ( rule__Entity__Group_5_0__1__Impl rule__Entity__Group_5_0__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1156:2: rule__Entity__Group_5_0__1__Impl rule__Entity__Group_5_0__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__1__Impl_in_rule__Entity__Group_5_0__12357);
            rule__Entity__Group_5_0__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__2_in_rule__Entity__Group_5_0__12360);
            rule__Entity__Group_5_0__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group_5_0__1


    // $ANTLR start rule__Entity__Group_5_0__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1163:1: rule__Entity__Group_5_0__1__Impl : ( ( rule__Entity__Alternatives_5_0_1 )* ) ;
    public final void rule__Entity__Group_5_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1167:1: ( ( ( rule__Entity__Alternatives_5_0_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1168:1: ( ( rule__Entity__Alternatives_5_0_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1168:1: ( ( rule__Entity__Alternatives_5_0_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1169:1: ( rule__Entity__Alternatives_5_0_1 )*
            {
             before(grammarAccess.getEntityAccess().getAlternatives_5_0_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1170:1: ( rule__Entity__Alternatives_5_0_1 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_COMMENT_ANNOTATION||LA13_0==15||LA13_0==18||(LA13_0>=20 && LA13_0<=22)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1170:2: rule__Entity__Alternatives_5_0_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Entity__Alternatives_5_0_1_in_rule__Entity__Group_5_0__1__Impl2387);
            	    rule__Entity__Alternatives_5_0_1();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getEntityAccess().getAlternatives_5_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group_5_0__1__Impl


    // $ANTLR start rule__Entity__Group_5_0__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1180:1: rule__Entity__Group_5_0__2 : rule__Entity__Group_5_0__2__Impl ;
    public final void rule__Entity__Group_5_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1184:1: ( rule__Entity__Group_5_0__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1185:2: rule__Entity__Group_5_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__2__Impl_in_rule__Entity__Group_5_0__22418);
            rule__Entity__Group_5_0__2__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group_5_0__2


    // $ANTLR start rule__Entity__Group_5_0__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1191:1: rule__Entity__Group_5_0__2__Impl : ( '}' ) ;
    public final void rule__Entity__Group_5_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1195:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1196:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1196:1: ( '}' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1197:1: '}'
            {
             before(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_0_2()); 
            match(input,17,FollowSets000.FOLLOW_17_in_rule__Entity__Group_5_0__2__Impl2446); 
             after(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__Group_5_0__2__Impl


    // $ANTLR start rule__Link__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1216:1: rule__Link__Group__0 : rule__Link__Group__0__Impl rule__Link__Group__1 ;
    public final void rule__Link__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1220:1: ( rule__Link__Group__0__Impl rule__Link__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1221:2: rule__Link__Group__0__Impl rule__Link__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__0__Impl_in_rule__Link__Group__02483);
            rule__Link__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__1_in_rule__Link__Group__02486);
            rule__Link__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__0


    // $ANTLR start rule__Link__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1228:1: rule__Link__Group__0__Impl : ( ( rule__Link__AnnotationsAssignment_0 )* ) ;
    public final void rule__Link__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1232:1: ( ( ( rule__Link__AnnotationsAssignment_0 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1233:1: ( ( rule__Link__AnnotationsAssignment_0 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1233:1: ( ( rule__Link__AnnotationsAssignment_0 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1234:1: ( rule__Link__AnnotationsAssignment_0 )*
            {
             before(grammarAccess.getLinkAccess().getAnnotationsAssignment_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1235:1: ( rule__Link__AnnotationsAssignment_0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_COMMENT_ANNOTATION||LA14_0==22) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1235:2: rule__Link__AnnotationsAssignment_0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Link__AnnotationsAssignment_0_in_rule__Link__Group__0__Impl2513);
            	    rule__Link__AnnotationsAssignment_0();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getLinkAccess().getAnnotationsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__0__Impl


    // $ANTLR start rule__Link__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1245:1: rule__Link__Group__1 : rule__Link__Group__1__Impl rule__Link__Group__2 ;
    public final void rule__Link__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1249:1: ( rule__Link__Group__1__Impl rule__Link__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1250:2: rule__Link__Group__1__Impl rule__Link__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__1__Impl_in_rule__Link__Group__12544);
            rule__Link__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__2_in_rule__Link__Group__12547);
            rule__Link__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__1


    // $ANTLR start rule__Link__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1257:1: rule__Link__Group__1__Impl : ( 'link' ) ;
    public final void rule__Link__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1261:1: ( ( 'link' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1262:1: ( 'link' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1262:1: ( 'link' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1263:1: 'link'
            {
             before(grammarAccess.getLinkAccess().getLinkKeyword_1()); 
            match(input,18,FollowSets000.FOLLOW_18_in_rule__Link__Group__1__Impl2575); 
             after(grammarAccess.getLinkAccess().getLinkKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__1__Impl


    // $ANTLR start rule__Link__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1276:1: rule__Link__Group__2 : rule__Link__Group__2__Impl rule__Link__Group__3 ;
    public final void rule__Link__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1280:1: ( rule__Link__Group__2__Impl rule__Link__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1281:2: rule__Link__Group__2__Impl rule__Link__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__2__Impl_in_rule__Link__Group__22606);
            rule__Link__Group__2__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__3_in_rule__Link__Group__22609);
            rule__Link__Group__3();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__2


    // $ANTLR start rule__Link__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1288:1: rule__Link__Group__2__Impl : ( ( rule__Link__NameAssignment_2 )? ) ;
    public final void rule__Link__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1292:1: ( ( ( rule__Link__NameAssignment_2 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1293:1: ( ( rule__Link__NameAssignment_2 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1293:1: ( ( rule__Link__NameAssignment_2 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1294:1: ( rule__Link__NameAssignment_2 )?
            {
             before(grammarAccess.getLinkAccess().getNameAssignment_2()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1295:1: ( rule__Link__NameAssignment_2 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_STRING) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1295:2: rule__Link__NameAssignment_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Link__NameAssignment_2_in_rule__Link__Group__2__Impl2636);
                    rule__Link__NameAssignment_2();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLinkAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__2__Impl


    // $ANTLR start rule__Link__Group__3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1305:1: rule__Link__Group__3 : rule__Link__Group__3__Impl rule__Link__Group__4 ;
    public final void rule__Link__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1309:1: ( rule__Link__Group__3__Impl rule__Link__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1310:2: rule__Link__Group__3__Impl rule__Link__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__3__Impl_in_rule__Link__Group__32667);
            rule__Link__Group__3__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__4_in_rule__Link__Group__32670);
            rule__Link__Group__4();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__3


    // $ANTLR start rule__Link__Group__3__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1317:1: rule__Link__Group__3__Impl : ( ( rule__Link__SourceAssignment_3 ) ) ;
    public final void rule__Link__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1321:1: ( ( ( rule__Link__SourceAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1322:1: ( ( rule__Link__SourceAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1322:1: ( ( rule__Link__SourceAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1323:1: ( rule__Link__SourceAssignment_3 )
            {
             before(grammarAccess.getLinkAccess().getSourceAssignment_3()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1324:1: ( rule__Link__SourceAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1324:2: rule__Link__SourceAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__SourceAssignment_3_in_rule__Link__Group__3__Impl2697);
            rule__Link__SourceAssignment_3();
            _fsp--;


            }

             after(grammarAccess.getLinkAccess().getSourceAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__3__Impl


    // $ANTLR start rule__Link__Group__4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1334:1: rule__Link__Group__4 : rule__Link__Group__4__Impl rule__Link__Group__5 ;
    public final void rule__Link__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1338:1: ( rule__Link__Group__4__Impl rule__Link__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1339:2: rule__Link__Group__4__Impl rule__Link__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__4__Impl_in_rule__Link__Group__42727);
            rule__Link__Group__4__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__5_in_rule__Link__Group__42730);
            rule__Link__Group__5();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__4


    // $ANTLR start rule__Link__Group__4__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1346:1: rule__Link__Group__4__Impl : ( 'to' ) ;
    public final void rule__Link__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1350:1: ( ( 'to' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1351:1: ( 'to' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1351:1: ( 'to' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1352:1: 'to'
            {
             before(grammarAccess.getLinkAccess().getToKeyword_4()); 
            match(input,19,FollowSets000.FOLLOW_19_in_rule__Link__Group__4__Impl2758); 
             after(grammarAccess.getLinkAccess().getToKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__4__Impl


    // $ANTLR start rule__Link__Group__5
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1365:1: rule__Link__Group__5 : rule__Link__Group__5__Impl rule__Link__Group__6 ;
    public final void rule__Link__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1369:1: ( rule__Link__Group__5__Impl rule__Link__Group__6 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1370:2: rule__Link__Group__5__Impl rule__Link__Group__6
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__5__Impl_in_rule__Link__Group__52789);
            rule__Link__Group__5__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__6_in_rule__Link__Group__52792);
            rule__Link__Group__6();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__5


    // $ANTLR start rule__Link__Group__5__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1377:1: rule__Link__Group__5__Impl : ( ( rule__Link__TargetAssignment_5 ) ) ;
    public final void rule__Link__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1381:1: ( ( ( rule__Link__TargetAssignment_5 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1382:1: ( ( rule__Link__TargetAssignment_5 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1382:1: ( ( rule__Link__TargetAssignment_5 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1383:1: ( rule__Link__TargetAssignment_5 )
            {
             before(grammarAccess.getLinkAccess().getTargetAssignment_5()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1384:1: ( rule__Link__TargetAssignment_5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1384:2: rule__Link__TargetAssignment_5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__TargetAssignment_5_in_rule__Link__Group__5__Impl2819);
            rule__Link__TargetAssignment_5();
            _fsp--;


            }

             after(grammarAccess.getLinkAccess().getTargetAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__5__Impl


    // $ANTLR start rule__Link__Group__6
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1394:1: rule__Link__Group__6 : rule__Link__Group__6__Impl ;
    public final void rule__Link__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1398:1: ( rule__Link__Group__6__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1399:2: rule__Link__Group__6__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__6__Impl_in_rule__Link__Group__62849);
            rule__Link__Group__6__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__6


    // $ANTLR start rule__Link__Group__6__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1405:1: rule__Link__Group__6__Impl : ( ';' ) ;
    public final void rule__Link__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1409:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1410:1: ( ';' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1410:1: ( ';' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1411:1: ';'
            {
             before(grammarAccess.getLinkAccess().getSemicolonKeyword_6()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__Link__Group__6__Impl2877); 
             after(grammarAccess.getLinkAccess().getSemicolonKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__Group__6__Impl


    // $ANTLR start rule__Port__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1438:1: rule__Port__Group__0 : rule__Port__Group__0__Impl rule__Port__Group__1 ;
    public final void rule__Port__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1442:1: ( rule__Port__Group__0__Impl rule__Port__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1443:2: rule__Port__Group__0__Impl rule__Port__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__0__Impl_in_rule__Port__Group__02922);
            rule__Port__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__1_in_rule__Port__Group__02925);
            rule__Port__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__0


    // $ANTLR start rule__Port__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1450:1: rule__Port__Group__0__Impl : ( () ) ;
    public final void rule__Port__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1454:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1455:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1455:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1456:1: ()
            {
             before(grammarAccess.getPortAccess().getPortAction_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1457:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1459:1: 
            {
            }

             after(grammarAccess.getPortAccess().getPortAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__0__Impl


    // $ANTLR start rule__Port__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1469:1: rule__Port__Group__1 : rule__Port__Group__1__Impl rule__Port__Group__2 ;
    public final void rule__Port__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1473:1: ( rule__Port__Group__1__Impl rule__Port__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1474:2: rule__Port__Group__1__Impl rule__Port__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__1__Impl_in_rule__Port__Group__12983);
            rule__Port__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__2_in_rule__Port__Group__12986);
            rule__Port__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__1


    // $ANTLR start rule__Port__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1481:1: rule__Port__Group__1__Impl : ( ( rule__Port__AnnotationsAssignment_1 )* ) ;
    public final void rule__Port__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1485:1: ( ( ( rule__Port__AnnotationsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1486:1: ( ( rule__Port__AnnotationsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1486:1: ( ( rule__Port__AnnotationsAssignment_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1487:1: ( rule__Port__AnnotationsAssignment_1 )*
            {
             before(grammarAccess.getPortAccess().getAnnotationsAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1488:1: ( rule__Port__AnnotationsAssignment_1 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_COMMENT_ANNOTATION||LA16_0==22) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1488:2: rule__Port__AnnotationsAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Port__AnnotationsAssignment_1_in_rule__Port__Group__1__Impl3013);
            	    rule__Port__AnnotationsAssignment_1();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getPortAccess().getAnnotationsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__1__Impl


    // $ANTLR start rule__Port__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1498:1: rule__Port__Group__2 : rule__Port__Group__2__Impl rule__Port__Group__3 ;
    public final void rule__Port__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1502:1: ( rule__Port__Group__2__Impl rule__Port__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1503:2: rule__Port__Group__2__Impl rule__Port__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__2__Impl_in_rule__Port__Group__23044);
            rule__Port__Group__2__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__3_in_rule__Port__Group__23047);
            rule__Port__Group__3();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__2


    // $ANTLR start rule__Port__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1510:1: rule__Port__Group__2__Impl : ( 'port' ) ;
    public final void rule__Port__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1514:1: ( ( 'port' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1515:1: ( 'port' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1515:1: ( 'port' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1516:1: 'port'
            {
             before(grammarAccess.getPortAccess().getPortKeyword_2()); 
            match(input,20,FollowSets000.FOLLOW_20_in_rule__Port__Group__2__Impl3075); 
             after(grammarAccess.getPortAccess().getPortKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__2__Impl


    // $ANTLR start rule__Port__Group__3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1529:1: rule__Port__Group__3 : rule__Port__Group__3__Impl rule__Port__Group__4 ;
    public final void rule__Port__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1533:1: ( rule__Port__Group__3__Impl rule__Port__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1534:2: rule__Port__Group__3__Impl rule__Port__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__3__Impl_in_rule__Port__Group__33106);
            rule__Port__Group__3__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__4_in_rule__Port__Group__33109);
            rule__Port__Group__4();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__3


    // $ANTLR start rule__Port__Group__3__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1541:1: rule__Port__Group__3__Impl : ( ( rule__Port__IdAssignment_3 ) ) ;
    public final void rule__Port__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1545:1: ( ( ( rule__Port__IdAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1546:1: ( ( rule__Port__IdAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1546:1: ( ( rule__Port__IdAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1547:1: ( rule__Port__IdAssignment_3 )
            {
             before(grammarAccess.getPortAccess().getIdAssignment_3()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1548:1: ( rule__Port__IdAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1548:2: rule__Port__IdAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__IdAssignment_3_in_rule__Port__Group__3__Impl3136);
            rule__Port__IdAssignment_3();
            _fsp--;


            }

             after(grammarAccess.getPortAccess().getIdAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__3__Impl


    // $ANTLR start rule__Port__Group__4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1558:1: rule__Port__Group__4 : rule__Port__Group__4__Impl rule__Port__Group__5 ;
    public final void rule__Port__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1562:1: ( rule__Port__Group__4__Impl rule__Port__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1563:2: rule__Port__Group__4__Impl rule__Port__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__4__Impl_in_rule__Port__Group__43166);
            rule__Port__Group__4__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__5_in_rule__Port__Group__43169);
            rule__Port__Group__5();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__4


    // $ANTLR start rule__Port__Group__4__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1570:1: rule__Port__Group__4__Impl : ( ( rule__Port__NameAssignment_4 )? ) ;
    public final void rule__Port__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1574:1: ( ( ( rule__Port__NameAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1575:1: ( ( rule__Port__NameAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1575:1: ( ( rule__Port__NameAssignment_4 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1576:1: ( rule__Port__NameAssignment_4 )?
            {
             before(grammarAccess.getPortAccess().getNameAssignment_4()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1577:1: ( rule__Port__NameAssignment_4 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_STRING) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1577:2: rule__Port__NameAssignment_4
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Port__NameAssignment_4_in_rule__Port__Group__4__Impl3196);
                    rule__Port__NameAssignment_4();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPortAccess().getNameAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__4__Impl


    // $ANTLR start rule__Port__Group__5
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1587:1: rule__Port__Group__5 : rule__Port__Group__5__Impl ;
    public final void rule__Port__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1591:1: ( rule__Port__Group__5__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1592:2: rule__Port__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__5__Impl_in_rule__Port__Group__53227);
            rule__Port__Group__5__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__5


    // $ANTLR start rule__Port__Group__5__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1598:1: rule__Port__Group__5__Impl : ( ';' ) ;
    public final void rule__Port__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1602:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1603:1: ( ';' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1603:1: ( ';' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1604:1: ';'
            {
             before(grammarAccess.getPortAccess().getSemicolonKeyword_5()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__Port__Group__5__Impl3255); 
             after(grammarAccess.getPortAccess().getSemicolonKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__Group__5__Impl


    // $ANTLR start rule__Relation__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1629:1: rule__Relation__Group__0 : rule__Relation__Group__0__Impl rule__Relation__Group__1 ;
    public final void rule__Relation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1633:1: ( rule__Relation__Group__0__Impl rule__Relation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1634:2: rule__Relation__Group__0__Impl rule__Relation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__0__Impl_in_rule__Relation__Group__03298);
            rule__Relation__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__1_in_rule__Relation__Group__03301);
            rule__Relation__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__0


    // $ANTLR start rule__Relation__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1641:1: rule__Relation__Group__0__Impl : ( () ) ;
    public final void rule__Relation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1645:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1646:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1646:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1647:1: ()
            {
             before(grammarAccess.getRelationAccess().getRelationAction_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1648:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1650:1: 
            {
            }

             after(grammarAccess.getRelationAccess().getRelationAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__0__Impl


    // $ANTLR start rule__Relation__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1660:1: rule__Relation__Group__1 : rule__Relation__Group__1__Impl rule__Relation__Group__2 ;
    public final void rule__Relation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1664:1: ( rule__Relation__Group__1__Impl rule__Relation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1665:2: rule__Relation__Group__1__Impl rule__Relation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__1__Impl_in_rule__Relation__Group__13359);
            rule__Relation__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__2_in_rule__Relation__Group__13362);
            rule__Relation__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__1


    // $ANTLR start rule__Relation__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1672:1: rule__Relation__Group__1__Impl : ( ( rule__Relation__AnnotationsAssignment_1 )* ) ;
    public final void rule__Relation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1676:1: ( ( ( rule__Relation__AnnotationsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1677:1: ( ( rule__Relation__AnnotationsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1677:1: ( ( rule__Relation__AnnotationsAssignment_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1678:1: ( rule__Relation__AnnotationsAssignment_1 )*
            {
             before(grammarAccess.getRelationAccess().getAnnotationsAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1679:1: ( rule__Relation__AnnotationsAssignment_1 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_COMMENT_ANNOTATION||LA18_0==22) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1679:2: rule__Relation__AnnotationsAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Relation__AnnotationsAssignment_1_in_rule__Relation__Group__1__Impl3389);
            	    rule__Relation__AnnotationsAssignment_1();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getRelationAccess().getAnnotationsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__1__Impl


    // $ANTLR start rule__Relation__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1689:1: rule__Relation__Group__2 : rule__Relation__Group__2__Impl rule__Relation__Group__3 ;
    public final void rule__Relation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1693:1: ( rule__Relation__Group__2__Impl rule__Relation__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1694:2: rule__Relation__Group__2__Impl rule__Relation__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__2__Impl_in_rule__Relation__Group__23420);
            rule__Relation__Group__2__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__3_in_rule__Relation__Group__23423);
            rule__Relation__Group__3();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__2


    // $ANTLR start rule__Relation__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1701:1: rule__Relation__Group__2__Impl : ( 'relation' ) ;
    public final void rule__Relation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1705:1: ( ( 'relation' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1706:1: ( 'relation' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1706:1: ( 'relation' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1707:1: 'relation'
            {
             before(grammarAccess.getRelationAccess().getRelationKeyword_2()); 
            match(input,21,FollowSets000.FOLLOW_21_in_rule__Relation__Group__2__Impl3451); 
             after(grammarAccess.getRelationAccess().getRelationKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__2__Impl


    // $ANTLR start rule__Relation__Group__3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1720:1: rule__Relation__Group__3 : rule__Relation__Group__3__Impl rule__Relation__Group__4 ;
    public final void rule__Relation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1724:1: ( rule__Relation__Group__3__Impl rule__Relation__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1725:2: rule__Relation__Group__3__Impl rule__Relation__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__3__Impl_in_rule__Relation__Group__33482);
            rule__Relation__Group__3__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__4_in_rule__Relation__Group__33485);
            rule__Relation__Group__4();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__3


    // $ANTLR start rule__Relation__Group__3__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1732:1: rule__Relation__Group__3__Impl : ( ( rule__Relation__IdAssignment_3 ) ) ;
    public final void rule__Relation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1736:1: ( ( ( rule__Relation__IdAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1737:1: ( ( rule__Relation__IdAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1737:1: ( ( rule__Relation__IdAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1738:1: ( rule__Relation__IdAssignment_3 )
            {
             before(grammarAccess.getRelationAccess().getIdAssignment_3()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1739:1: ( rule__Relation__IdAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1739:2: rule__Relation__IdAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__IdAssignment_3_in_rule__Relation__Group__3__Impl3512);
            rule__Relation__IdAssignment_3();
            _fsp--;


            }

             after(grammarAccess.getRelationAccess().getIdAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__3__Impl


    // $ANTLR start rule__Relation__Group__4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1749:1: rule__Relation__Group__4 : rule__Relation__Group__4__Impl rule__Relation__Group__5 ;
    public final void rule__Relation__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1753:1: ( rule__Relation__Group__4__Impl rule__Relation__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1754:2: rule__Relation__Group__4__Impl rule__Relation__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__4__Impl_in_rule__Relation__Group__43542);
            rule__Relation__Group__4__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__5_in_rule__Relation__Group__43545);
            rule__Relation__Group__5();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__4


    // $ANTLR start rule__Relation__Group__4__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1761:1: rule__Relation__Group__4__Impl : ( ( rule__Relation__NameAssignment_4 )? ) ;
    public final void rule__Relation__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1765:1: ( ( ( rule__Relation__NameAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1766:1: ( ( rule__Relation__NameAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1766:1: ( ( rule__Relation__NameAssignment_4 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1767:1: ( rule__Relation__NameAssignment_4 )?
            {
             before(grammarAccess.getRelationAccess().getNameAssignment_4()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1768:1: ( rule__Relation__NameAssignment_4 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_STRING) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1768:2: rule__Relation__NameAssignment_4
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Relation__NameAssignment_4_in_rule__Relation__Group__4__Impl3572);
                    rule__Relation__NameAssignment_4();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRelationAccess().getNameAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__4__Impl


    // $ANTLR start rule__Relation__Group__5
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1778:1: rule__Relation__Group__5 : rule__Relation__Group__5__Impl ;
    public final void rule__Relation__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1782:1: ( rule__Relation__Group__5__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1783:2: rule__Relation__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__5__Impl_in_rule__Relation__Group__53603);
            rule__Relation__Group__5__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__5


    // $ANTLR start rule__Relation__Group__5__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1789:1: rule__Relation__Group__5__Impl : ( ';' ) ;
    public final void rule__Relation__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1793:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1794:1: ( ';' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1794:1: ( ';' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1795:1: ';'
            {
             before(grammarAccess.getRelationAccess().getSemicolonKeyword_5()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__Relation__Group__5__Impl3631); 
             after(grammarAccess.getRelationAccess().getSemicolonKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__Group__5__Impl


    // $ANTLR start rule__TagAnnotation__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1820:1: rule__TagAnnotation__Group__0 : rule__TagAnnotation__Group__0__Impl rule__TagAnnotation__Group__1 ;
    public final void rule__TagAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1824:1: ( rule__TagAnnotation__Group__0__Impl rule__TagAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1825:2: rule__TagAnnotation__Group__0__Impl rule__TagAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__0__Impl_in_rule__TagAnnotation__Group__03674);
            rule__TagAnnotation__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__1_in_rule__TagAnnotation__Group__03677);
            rule__TagAnnotation__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TagAnnotation__Group__0


    // $ANTLR start rule__TagAnnotation__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1832:1: rule__TagAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__TagAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1836:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1837:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1837:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1838:1: '@'
            {
             before(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0()); 
            match(input,22,FollowSets000.FOLLOW_22_in_rule__TagAnnotation__Group__0__Impl3705); 
             after(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TagAnnotation__Group__0__Impl


    // $ANTLR start rule__TagAnnotation__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1851:1: rule__TagAnnotation__Group__1 : rule__TagAnnotation__Group__1__Impl ;
    public final void rule__TagAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1855:1: ( rule__TagAnnotation__Group__1__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1856:2: rule__TagAnnotation__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__1__Impl_in_rule__TagAnnotation__Group__13736);
            rule__TagAnnotation__Group__1__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TagAnnotation__Group__1


    // $ANTLR start rule__TagAnnotation__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1862:1: rule__TagAnnotation__Group__1__Impl : ( ( rule__TagAnnotation__NameAssignment_1 ) ) ;
    public final void rule__TagAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1866:1: ( ( ( rule__TagAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1867:1: ( ( rule__TagAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1867:1: ( ( rule__TagAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1868:1: ( rule__TagAnnotation__NameAssignment_1 )
            {
             before(grammarAccess.getTagAnnotationAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1869:1: ( rule__TagAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1869:2: rule__TagAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__NameAssignment_1_in_rule__TagAnnotation__Group__1__Impl3763);
            rule__TagAnnotation__NameAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getTagAnnotationAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TagAnnotation__Group__1__Impl


    // $ANTLR start rule__KeyStringValueAnnotation__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1883:1: rule__KeyStringValueAnnotation__Group__0 : rule__KeyStringValueAnnotation__Group__0__Impl rule__KeyStringValueAnnotation__Group__1 ;
    public final void rule__KeyStringValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1887:1: ( rule__KeyStringValueAnnotation__Group__0__Impl rule__KeyStringValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1888:2: rule__KeyStringValueAnnotation__Group__0__Impl rule__KeyStringValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__0__Impl_in_rule__KeyStringValueAnnotation__Group__03797);
            rule__KeyStringValueAnnotation__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__1_in_rule__KeyStringValueAnnotation__Group__03800);
            rule__KeyStringValueAnnotation__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__Group__0


    // $ANTLR start rule__KeyStringValueAnnotation__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1895:1: rule__KeyStringValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyStringValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1899:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1900:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1900:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1901:1: '@'
            {
             before(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0()); 
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyStringValueAnnotation__Group__0__Impl3828); 
             after(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__Group__0__Impl


    // $ANTLR start rule__KeyStringValueAnnotation__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1914:1: rule__KeyStringValueAnnotation__Group__1 : rule__KeyStringValueAnnotation__Group__1__Impl rule__KeyStringValueAnnotation__Group__2 ;
    public final void rule__KeyStringValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1918:1: ( rule__KeyStringValueAnnotation__Group__1__Impl rule__KeyStringValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1919:2: rule__KeyStringValueAnnotation__Group__1__Impl rule__KeyStringValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__1__Impl_in_rule__KeyStringValueAnnotation__Group__13859);
            rule__KeyStringValueAnnotation__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__2_in_rule__KeyStringValueAnnotation__Group__13862);
            rule__KeyStringValueAnnotation__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__Group__1


    // $ANTLR start rule__KeyStringValueAnnotation__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1926:1: rule__KeyStringValueAnnotation__Group__1__Impl : ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyStringValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1930:1: ( ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1931:1: ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1931:1: ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1932:1: ( rule__KeyStringValueAnnotation__NameAssignment_1 )
            {
             before(grammarAccess.getKeyStringValueAnnotationAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1933:1: ( rule__KeyStringValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1933:2: rule__KeyStringValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__NameAssignment_1_in_rule__KeyStringValueAnnotation__Group__1__Impl3889);
            rule__KeyStringValueAnnotation__NameAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getKeyStringValueAnnotationAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__Group__1__Impl


    // $ANTLR start rule__KeyStringValueAnnotation__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1943:1: rule__KeyStringValueAnnotation__Group__2 : rule__KeyStringValueAnnotation__Group__2__Impl ;
    public final void rule__KeyStringValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1947:1: ( rule__KeyStringValueAnnotation__Group__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1948:2: rule__KeyStringValueAnnotation__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__2__Impl_in_rule__KeyStringValueAnnotation__Group__23919);
            rule__KeyStringValueAnnotation__Group__2__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__Group__2


    // $ANTLR start rule__KeyStringValueAnnotation__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1954:1: rule__KeyStringValueAnnotation__Group__2__Impl : ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyStringValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1958:1: ( ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1959:1: ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1959:1: ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1960:1: ( rule__KeyStringValueAnnotation__ValueAssignment_2 )
            {
             before(grammarAccess.getKeyStringValueAnnotationAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1961:1: ( rule__KeyStringValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1961:2: rule__KeyStringValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__ValueAssignment_2_in_rule__KeyStringValueAnnotation__Group__2__Impl3946);
            rule__KeyStringValueAnnotation__ValueAssignment_2();
            _fsp--;


            }

             after(grammarAccess.getKeyStringValueAnnotationAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__Group__2__Impl


    // $ANTLR start rule__KeyBooleanValueAnnotation__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1977:1: rule__KeyBooleanValueAnnotation__Group__0 : rule__KeyBooleanValueAnnotation__Group__0__Impl rule__KeyBooleanValueAnnotation__Group__1 ;
    public final void rule__KeyBooleanValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1981:1: ( rule__KeyBooleanValueAnnotation__Group__0__Impl rule__KeyBooleanValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1982:2: rule__KeyBooleanValueAnnotation__Group__0__Impl rule__KeyBooleanValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__0__Impl_in_rule__KeyBooleanValueAnnotation__Group__03982);
            rule__KeyBooleanValueAnnotation__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__1_in_rule__KeyBooleanValueAnnotation__Group__03985);
            rule__KeyBooleanValueAnnotation__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__Group__0


    // $ANTLR start rule__KeyBooleanValueAnnotation__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1989:1: rule__KeyBooleanValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyBooleanValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1993:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1994:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1994:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1995:1: '@'
            {
             before(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0()); 
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyBooleanValueAnnotation__Group__0__Impl4013); 
             after(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__Group__0__Impl


    // $ANTLR start rule__KeyBooleanValueAnnotation__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2008:1: rule__KeyBooleanValueAnnotation__Group__1 : rule__KeyBooleanValueAnnotation__Group__1__Impl rule__KeyBooleanValueAnnotation__Group__2 ;
    public final void rule__KeyBooleanValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2012:1: ( rule__KeyBooleanValueAnnotation__Group__1__Impl rule__KeyBooleanValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2013:2: rule__KeyBooleanValueAnnotation__Group__1__Impl rule__KeyBooleanValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__1__Impl_in_rule__KeyBooleanValueAnnotation__Group__14044);
            rule__KeyBooleanValueAnnotation__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__2_in_rule__KeyBooleanValueAnnotation__Group__14047);
            rule__KeyBooleanValueAnnotation__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__Group__1


    // $ANTLR start rule__KeyBooleanValueAnnotation__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2020:1: rule__KeyBooleanValueAnnotation__Group__1__Impl : ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyBooleanValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2024:1: ( ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2025:1: ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2025:1: ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2026:1: ( rule__KeyBooleanValueAnnotation__NameAssignment_1 )
            {
             before(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2027:1: ( rule__KeyBooleanValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2027:2: rule__KeyBooleanValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__NameAssignment_1_in_rule__KeyBooleanValueAnnotation__Group__1__Impl4074);
            rule__KeyBooleanValueAnnotation__NameAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__Group__1__Impl


    // $ANTLR start rule__KeyBooleanValueAnnotation__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2037:1: rule__KeyBooleanValueAnnotation__Group__2 : rule__KeyBooleanValueAnnotation__Group__2__Impl ;
    public final void rule__KeyBooleanValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2041:1: ( rule__KeyBooleanValueAnnotation__Group__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2042:2: rule__KeyBooleanValueAnnotation__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__2__Impl_in_rule__KeyBooleanValueAnnotation__Group__24104);
            rule__KeyBooleanValueAnnotation__Group__2__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__Group__2


    // $ANTLR start rule__KeyBooleanValueAnnotation__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2048:1: rule__KeyBooleanValueAnnotation__Group__2__Impl : ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyBooleanValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2052:1: ( ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2053:1: ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2053:1: ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2054:1: ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 )
            {
             before(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2055:1: ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2055:2: rule__KeyBooleanValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__ValueAssignment_2_in_rule__KeyBooleanValueAnnotation__Group__2__Impl4131);
            rule__KeyBooleanValueAnnotation__ValueAssignment_2();
            _fsp--;


            }

             after(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__Group__2__Impl


    // $ANTLR start rule__KeyIntValueAnnotation__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2071:1: rule__KeyIntValueAnnotation__Group__0 : rule__KeyIntValueAnnotation__Group__0__Impl rule__KeyIntValueAnnotation__Group__1 ;
    public final void rule__KeyIntValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2075:1: ( rule__KeyIntValueAnnotation__Group__0__Impl rule__KeyIntValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2076:2: rule__KeyIntValueAnnotation__Group__0__Impl rule__KeyIntValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__0__Impl_in_rule__KeyIntValueAnnotation__Group__04167);
            rule__KeyIntValueAnnotation__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__1_in_rule__KeyIntValueAnnotation__Group__04170);
            rule__KeyIntValueAnnotation__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__Group__0


    // $ANTLR start rule__KeyIntValueAnnotation__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2083:1: rule__KeyIntValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyIntValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2087:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2088:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2088:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2089:1: '@'
            {
             before(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0()); 
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyIntValueAnnotation__Group__0__Impl4198); 
             after(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__Group__0__Impl


    // $ANTLR start rule__KeyIntValueAnnotation__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2102:1: rule__KeyIntValueAnnotation__Group__1 : rule__KeyIntValueAnnotation__Group__1__Impl rule__KeyIntValueAnnotation__Group__2 ;
    public final void rule__KeyIntValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2106:1: ( rule__KeyIntValueAnnotation__Group__1__Impl rule__KeyIntValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2107:2: rule__KeyIntValueAnnotation__Group__1__Impl rule__KeyIntValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__1__Impl_in_rule__KeyIntValueAnnotation__Group__14229);
            rule__KeyIntValueAnnotation__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__2_in_rule__KeyIntValueAnnotation__Group__14232);
            rule__KeyIntValueAnnotation__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__Group__1


    // $ANTLR start rule__KeyIntValueAnnotation__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2114:1: rule__KeyIntValueAnnotation__Group__1__Impl : ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyIntValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2118:1: ( ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2119:1: ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2119:1: ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2120:1: ( rule__KeyIntValueAnnotation__NameAssignment_1 )
            {
             before(grammarAccess.getKeyIntValueAnnotationAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2121:1: ( rule__KeyIntValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2121:2: rule__KeyIntValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__NameAssignment_1_in_rule__KeyIntValueAnnotation__Group__1__Impl4259);
            rule__KeyIntValueAnnotation__NameAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getKeyIntValueAnnotationAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__Group__1__Impl


    // $ANTLR start rule__KeyIntValueAnnotation__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2131:1: rule__KeyIntValueAnnotation__Group__2 : rule__KeyIntValueAnnotation__Group__2__Impl ;
    public final void rule__KeyIntValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2135:1: ( rule__KeyIntValueAnnotation__Group__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2136:2: rule__KeyIntValueAnnotation__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__2__Impl_in_rule__KeyIntValueAnnotation__Group__24289);
            rule__KeyIntValueAnnotation__Group__2__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__Group__2


    // $ANTLR start rule__KeyIntValueAnnotation__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2142:1: rule__KeyIntValueAnnotation__Group__2__Impl : ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyIntValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2146:1: ( ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2147:1: ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2147:1: ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2148:1: ( rule__KeyIntValueAnnotation__ValueAssignment_2 )
            {
             before(grammarAccess.getKeyIntValueAnnotationAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2149:1: ( rule__KeyIntValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2149:2: rule__KeyIntValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__ValueAssignment_2_in_rule__KeyIntValueAnnotation__Group__2__Impl4316);
            rule__KeyIntValueAnnotation__ValueAssignment_2();
            _fsp--;


            }

             after(grammarAccess.getKeyIntValueAnnotationAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__Group__2__Impl


    // $ANTLR start rule__KeyFloatValueAnnotation__Group__0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2165:1: rule__KeyFloatValueAnnotation__Group__0 : rule__KeyFloatValueAnnotation__Group__0__Impl rule__KeyFloatValueAnnotation__Group__1 ;
    public final void rule__KeyFloatValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2169:1: ( rule__KeyFloatValueAnnotation__Group__0__Impl rule__KeyFloatValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2170:2: rule__KeyFloatValueAnnotation__Group__0__Impl rule__KeyFloatValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__0__Impl_in_rule__KeyFloatValueAnnotation__Group__04352);
            rule__KeyFloatValueAnnotation__Group__0__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__1_in_rule__KeyFloatValueAnnotation__Group__04355);
            rule__KeyFloatValueAnnotation__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__Group__0


    // $ANTLR start rule__KeyFloatValueAnnotation__Group__0__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2177:1: rule__KeyFloatValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyFloatValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2181:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2182:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2182:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2183:1: '@'
            {
             before(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0()); 
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyFloatValueAnnotation__Group__0__Impl4383); 
             after(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__Group__0__Impl


    // $ANTLR start rule__KeyFloatValueAnnotation__Group__1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2196:1: rule__KeyFloatValueAnnotation__Group__1 : rule__KeyFloatValueAnnotation__Group__1__Impl rule__KeyFloatValueAnnotation__Group__2 ;
    public final void rule__KeyFloatValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2200:1: ( rule__KeyFloatValueAnnotation__Group__1__Impl rule__KeyFloatValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2201:2: rule__KeyFloatValueAnnotation__Group__1__Impl rule__KeyFloatValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__1__Impl_in_rule__KeyFloatValueAnnotation__Group__14414);
            rule__KeyFloatValueAnnotation__Group__1__Impl();
            _fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__2_in_rule__KeyFloatValueAnnotation__Group__14417);
            rule__KeyFloatValueAnnotation__Group__2();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__Group__1


    // $ANTLR start rule__KeyFloatValueAnnotation__Group__1__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2208:1: rule__KeyFloatValueAnnotation__Group__1__Impl : ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyFloatValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2212:1: ( ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2213:1: ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2213:1: ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2214:1: ( rule__KeyFloatValueAnnotation__NameAssignment_1 )
            {
             before(grammarAccess.getKeyFloatValueAnnotationAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2215:1: ( rule__KeyFloatValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2215:2: rule__KeyFloatValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__NameAssignment_1_in_rule__KeyFloatValueAnnotation__Group__1__Impl4444);
            rule__KeyFloatValueAnnotation__NameAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getKeyFloatValueAnnotationAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__Group__1__Impl


    // $ANTLR start rule__KeyFloatValueAnnotation__Group__2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2225:1: rule__KeyFloatValueAnnotation__Group__2 : rule__KeyFloatValueAnnotation__Group__2__Impl ;
    public final void rule__KeyFloatValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2229:1: ( rule__KeyFloatValueAnnotation__Group__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2230:2: rule__KeyFloatValueAnnotation__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__2__Impl_in_rule__KeyFloatValueAnnotation__Group__24474);
            rule__KeyFloatValueAnnotation__Group__2__Impl();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__Group__2


    // $ANTLR start rule__KeyFloatValueAnnotation__Group__2__Impl
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2236:1: rule__KeyFloatValueAnnotation__Group__2__Impl : ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyFloatValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2240:1: ( ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2241:1: ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2241:1: ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2242:1: ( rule__KeyFloatValueAnnotation__ValueAssignment_2 )
            {
             before(grammarAccess.getKeyFloatValueAnnotationAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2243:1: ( rule__KeyFloatValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2243:2: rule__KeyFloatValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__ValueAssignment_2_in_rule__KeyFloatValueAnnotation__Group__2__Impl4501);
            rule__KeyFloatValueAnnotation__ValueAssignment_2();
            _fsp--;


            }

             after(grammarAccess.getKeyFloatValueAnnotationAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__Group__2__Impl


    // $ANTLR start rule__TopLevelEntity__AnnotationsAssignment_1_0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2261:1: rule__TopLevelEntity__AnnotationsAssignment_1_0 : ( ruleAnnotation ) ;
    public final void rule__TopLevelEntity__AnnotationsAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2265:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2266:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2266:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2267:1: ruleAnnotation
            {
             before(grammarAccess.getTopLevelEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__TopLevelEntity__AnnotationsAssignment_1_04543);
            ruleAnnotation();
            _fsp--;

             after(grammarAccess.getTopLevelEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__AnnotationsAssignment_1_0


    // $ANTLR start rule__TopLevelEntity__IdAssignment_1_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2276:1: rule__TopLevelEntity__IdAssignment_1_2 : ( RULE_ID ) ;
    public final void rule__TopLevelEntity__IdAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2280:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2281:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2281:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2282:1: RULE_ID
            {
             before(grammarAccess.getTopLevelEntityAccess().getIdIDTerminalRuleCall_1_2_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__TopLevelEntity__IdAssignment_1_24574); 
             after(grammarAccess.getTopLevelEntityAccess().getIdIDTerminalRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__IdAssignment_1_2


    // $ANTLR start rule__TopLevelEntity__NameAssignment_1_3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2291:1: rule__TopLevelEntity__NameAssignment_1_3 : ( RULE_STRING ) ;
    public final void rule__TopLevelEntity__NameAssignment_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2295:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2296:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2296:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2297:1: RULE_STRING
            {
             before(grammarAccess.getTopLevelEntityAccess().getNameSTRINGTerminalRuleCall_1_3_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__TopLevelEntity__NameAssignment_1_34605); 
             after(grammarAccess.getTopLevelEntityAccess().getNameSTRINGTerminalRuleCall_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__NameAssignment_1_3


    // $ANTLR start rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2306:1: rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 : ( ruleEntity ) ;
    public final void rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2310:1: ( ( ruleEntity ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2311:1: ( ruleEntity )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2311:1: ( ruleEntity )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2312:1: ruleEntity
            {
             before(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_1_4_0_1_0_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_04636);
            ruleEntity();
            _fsp--;

             after(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_1_4_0_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0


    // $ANTLR start rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2321:1: rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 : ( ruleLink ) ;
    public final void rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2325:1: ( ( ruleLink ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2326:1: ( ruleLink )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2326:1: ( ruleLink )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2327:1: ruleLink
            {
             before(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_1_4_0_1_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_14667);
            ruleLink();
            _fsp--;

             after(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_1_4_0_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1


    // $ANTLR start rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2336:1: rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 : ( rulePort ) ;
    public final void rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2340:1: ( ( rulePort ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2341:1: ( rulePort )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2341:1: ( rulePort )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2342:1: rulePort
            {
             before(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_1_4_0_1_2_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePort_in_rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_24698);
            rulePort();
            _fsp--;

             after(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_1_4_0_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2


    // $ANTLR start rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2351:1: rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 : ( ruleRelation ) ;
    public final void rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2355:1: ( ( ruleRelation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2356:1: ( ruleRelation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2356:1: ( ruleRelation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2357:1: ruleRelation
            {
             before(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_1_4_0_1_3_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_34729);
            ruleRelation();
            _fsp--;

             after(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_1_4_0_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3


    // $ANTLR start rule__Entity__AnnotationsAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2366:1: rule__Entity__AnnotationsAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Entity__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2370:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2371:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2371:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2372:1: ruleAnnotation
            {
             before(grammarAccess.getEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Entity__AnnotationsAssignment_14760);
            ruleAnnotation();
            _fsp--;

             after(grammarAccess.getEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__AnnotationsAssignment_1


    // $ANTLR start rule__Entity__IdAssignment_3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2381:1: rule__Entity__IdAssignment_3 : ( RULE_ID ) ;
    public final void rule__Entity__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2385:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2386:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2386:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2387:1: RULE_ID
            {
             before(grammarAccess.getEntityAccess().getIdIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Entity__IdAssignment_34791); 
             after(grammarAccess.getEntityAccess().getIdIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__IdAssignment_3


    // $ANTLR start rule__Entity__NameAssignment_4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2396:1: rule__Entity__NameAssignment_4 : ( RULE_STRING ) ;
    public final void rule__Entity__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2400:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2401:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2401:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2402:1: RULE_STRING
            {
             before(grammarAccess.getEntityAccess().getNameSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Entity__NameAssignment_44822); 
             after(grammarAccess.getEntityAccess().getNameSTRINGTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__NameAssignment_4


    // $ANTLR start rule__Entity__ChildEntitiesAssignment_5_0_1_0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2411:1: rule__Entity__ChildEntitiesAssignment_5_0_1_0 : ( ruleEntity ) ;
    public final void rule__Entity__ChildEntitiesAssignment_5_0_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2415:1: ( ( ruleEntity ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2416:1: ( ruleEntity )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2416:1: ( ruleEntity )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2417:1: ruleEntity
            {
             before(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_rule__Entity__ChildEntitiesAssignment_5_0_1_04853);
            ruleEntity();
            _fsp--;

             after(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__ChildEntitiesAssignment_5_0_1_0


    // $ANTLR start rule__Entity__ChildLinksAssignment_5_0_1_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2426:1: rule__Entity__ChildLinksAssignment_5_0_1_1 : ( ruleLink ) ;
    public final void rule__Entity__ChildLinksAssignment_5_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2430:1: ( ( ruleLink ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2431:1: ( ruleLink )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2431:1: ( ruleLink )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2432:1: ruleLink
            {
             before(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_rule__Entity__ChildLinksAssignment_5_0_1_14884);
            ruleLink();
            _fsp--;

             after(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__ChildLinksAssignment_5_0_1_1


    // $ANTLR start rule__Entity__ChildPortsAssignment_5_0_1_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2441:1: rule__Entity__ChildPortsAssignment_5_0_1_2 : ( rulePort ) ;
    public final void rule__Entity__ChildPortsAssignment_5_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2445:1: ( ( rulePort ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2446:1: ( rulePort )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2446:1: ( rulePort )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2447:1: rulePort
            {
             before(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePort_in_rule__Entity__ChildPortsAssignment_5_0_1_24915);
            rulePort();
            _fsp--;

             after(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__ChildPortsAssignment_5_0_1_2


    // $ANTLR start rule__Entity__ChildRelationsAssignment_5_0_1_3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2456:1: rule__Entity__ChildRelationsAssignment_5_0_1_3 : ( ruleRelation ) ;
    public final void rule__Entity__ChildRelationsAssignment_5_0_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2460:1: ( ( ruleRelation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2461:1: ( ruleRelation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2461:1: ( ruleRelation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2462:1: ruleRelation
            {
             before(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_rule__Entity__ChildRelationsAssignment_5_0_1_34946);
            ruleRelation();
            _fsp--;

             after(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Entity__ChildRelationsAssignment_5_0_1_3


    // $ANTLR start rule__Link__AnnotationsAssignment_0
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2471:1: rule__Link__AnnotationsAssignment_0 : ( ruleAnnotation ) ;
    public final void rule__Link__AnnotationsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2475:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2476:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2476:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2477:1: ruleAnnotation
            {
             before(grammarAccess.getLinkAccess().getAnnotationsAnnotationParserRuleCall_0_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Link__AnnotationsAssignment_04977);
            ruleAnnotation();
            _fsp--;

             after(grammarAccess.getLinkAccess().getAnnotationsAnnotationParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__AnnotationsAssignment_0


    // $ANTLR start rule__Link__NameAssignment_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2486:1: rule__Link__NameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Link__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2490:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2491:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2491:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2492:1: RULE_STRING
            {
             before(grammarAccess.getLinkAccess().getNameSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Link__NameAssignment_25008); 
             after(grammarAccess.getLinkAccess().getNameSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__NameAssignment_2


    // $ANTLR start rule__Link__SourceAssignment_3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2501:1: rule__Link__SourceAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__Link__SourceAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2505:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2506:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2506:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2507:1: ( RULE_ID )
            {
             before(grammarAccess.getLinkAccess().getSourceLinkableCrossReference_3_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2508:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2509:1: RULE_ID
            {
             before(grammarAccess.getLinkAccess().getSourceLinkableIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Link__SourceAssignment_35043); 
             after(grammarAccess.getLinkAccess().getSourceLinkableIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getLinkAccess().getSourceLinkableCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__SourceAssignment_3


    // $ANTLR start rule__Link__TargetAssignment_5
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2520:1: rule__Link__TargetAssignment_5 : ( ( RULE_ID ) ) ;
    public final void rule__Link__TargetAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2524:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2525:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2525:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2526:1: ( RULE_ID )
            {
             before(grammarAccess.getLinkAccess().getTargetLinkableCrossReference_5_0()); 
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2527:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2528:1: RULE_ID
            {
             before(grammarAccess.getLinkAccess().getTargetLinkableIDTerminalRuleCall_5_0_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Link__TargetAssignment_55082); 
             after(grammarAccess.getLinkAccess().getTargetLinkableIDTerminalRuleCall_5_0_1()); 

            }

             after(grammarAccess.getLinkAccess().getTargetLinkableCrossReference_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Link__TargetAssignment_5


    // $ANTLR start rule__Port__AnnotationsAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2539:1: rule__Port__AnnotationsAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Port__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2543:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2544:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2544:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2545:1: ruleAnnotation
            {
             before(grammarAccess.getPortAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Port__AnnotationsAssignment_15117);
            ruleAnnotation();
            _fsp--;

             after(grammarAccess.getPortAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__AnnotationsAssignment_1


    // $ANTLR start rule__Port__IdAssignment_3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2554:1: rule__Port__IdAssignment_3 : ( RULE_ID ) ;
    public final void rule__Port__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2558:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2559:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2559:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2560:1: RULE_ID
            {
             before(grammarAccess.getPortAccess().getIdIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Port__IdAssignment_35148); 
             after(grammarAccess.getPortAccess().getIdIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__IdAssignment_3


    // $ANTLR start rule__Port__NameAssignment_4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2569:1: rule__Port__NameAssignment_4 : ( RULE_STRING ) ;
    public final void rule__Port__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2573:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2574:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2574:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2575:1: RULE_STRING
            {
             before(grammarAccess.getPortAccess().getNameSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Port__NameAssignment_45179); 
             after(grammarAccess.getPortAccess().getNameSTRINGTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Port__NameAssignment_4


    // $ANTLR start rule__Relation__AnnotationsAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2584:1: rule__Relation__AnnotationsAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Relation__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2588:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2589:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2589:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2590:1: ruleAnnotation
            {
             before(grammarAccess.getRelationAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Relation__AnnotationsAssignment_15210);
            ruleAnnotation();
            _fsp--;

             after(grammarAccess.getRelationAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__AnnotationsAssignment_1


    // $ANTLR start rule__Relation__IdAssignment_3
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2599:1: rule__Relation__IdAssignment_3 : ( RULE_ID ) ;
    public final void rule__Relation__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2603:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2604:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2604:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2605:1: RULE_ID
            {
             before(grammarAccess.getRelationAccess().getIdIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Relation__IdAssignment_35241); 
             after(grammarAccess.getRelationAccess().getIdIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__IdAssignment_3


    // $ANTLR start rule__Relation__NameAssignment_4
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2614:1: rule__Relation__NameAssignment_4 : ( RULE_STRING ) ;
    public final void rule__Relation__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2618:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2619:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2619:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2620:1: RULE_STRING
            {
             before(grammarAccess.getRelationAccess().getNameSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Relation__NameAssignment_45272); 
             after(grammarAccess.getRelationAccess().getNameSTRINGTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Relation__NameAssignment_4


    // $ANTLR start rule__CommentAnnotation__ValueAssignment
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2629:1: rule__CommentAnnotation__ValueAssignment : ( RULE_COMMENT_ANNOTATION ) ;
    public final void rule__CommentAnnotation__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2633:1: ( ( RULE_COMMENT_ANNOTATION ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2634:1: ( RULE_COMMENT_ANNOTATION )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2634:1: ( RULE_COMMENT_ANNOTATION )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2635:1: RULE_COMMENT_ANNOTATION
            {
             before(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0()); 
            match(input,RULE_COMMENT_ANNOTATION,FollowSets000.FOLLOW_RULE_COMMENT_ANNOTATION_in_rule__CommentAnnotation__ValueAssignment5303); 
             after(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__CommentAnnotation__ValueAssignment


    // $ANTLR start rule__TagAnnotation__NameAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2644:1: rule__TagAnnotation__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__TagAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2648:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2649:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2649:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2650:1: RULE_ID
            {
             before(grammarAccess.getTagAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__TagAnnotation__NameAssignment_15334); 
             after(grammarAccess.getTagAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__TagAnnotation__NameAssignment_1


    // $ANTLR start rule__KeyStringValueAnnotation__NameAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2659:1: rule__KeyStringValueAnnotation__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__KeyStringValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2663:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2664:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2664:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2665:1: RULE_ID
            {
             before(grammarAccess.getKeyStringValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__KeyStringValueAnnotation__NameAssignment_15365); 
             after(grammarAccess.getKeyStringValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__NameAssignment_1


    // $ANTLR start rule__KeyStringValueAnnotation__ValueAssignment_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2674:1: rule__KeyStringValueAnnotation__ValueAssignment_2 : ( ruleEString ) ;
    public final void rule__KeyStringValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2678:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2679:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2679:1: ( ruleEString )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2680:1: ruleEString
            {
             before(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rule__KeyStringValueAnnotation__ValueAssignment_25396);
            ruleEString();
            _fsp--;

             after(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyStringValueAnnotation__ValueAssignment_2


    // $ANTLR start rule__KeyBooleanValueAnnotation__NameAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2689:1: rule__KeyBooleanValueAnnotation__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__KeyBooleanValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2693:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2694:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2694:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2695:1: RULE_ID
            {
             before(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__KeyBooleanValueAnnotation__NameAssignment_15427); 
             after(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__NameAssignment_1


    // $ANTLR start rule__KeyBooleanValueAnnotation__ValueAssignment_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2704:1: rule__KeyBooleanValueAnnotation__ValueAssignment_2 : ( RULE_BOOLEAN ) ;
    public final void rule__KeyBooleanValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2708:1: ( ( RULE_BOOLEAN ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2709:1: ( RULE_BOOLEAN )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2709:1: ( RULE_BOOLEAN )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2710:1: RULE_BOOLEAN
            {
             before(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0()); 
            match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_rule__KeyBooleanValueAnnotation__ValueAssignment_25458); 
             after(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyBooleanValueAnnotation__ValueAssignment_2


    // $ANTLR start rule__KeyIntValueAnnotation__NameAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2719:1: rule__KeyIntValueAnnotation__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__KeyIntValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2723:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2724:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2724:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2725:1: RULE_ID
            {
             before(grammarAccess.getKeyIntValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__KeyIntValueAnnotation__NameAssignment_15489); 
             after(grammarAccess.getKeyIntValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__NameAssignment_1


    // $ANTLR start rule__KeyIntValueAnnotation__ValueAssignment_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2734:1: rule__KeyIntValueAnnotation__ValueAssignment_2 : ( RULE_INT ) ;
    public final void rule__KeyIntValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2738:1: ( ( RULE_INT ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2739:1: ( RULE_INT )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2739:1: ( RULE_INT )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2740:1: RULE_INT
            {
             before(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__KeyIntValueAnnotation__ValueAssignment_25520); 
             after(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyIntValueAnnotation__ValueAssignment_2


    // $ANTLR start rule__KeyFloatValueAnnotation__NameAssignment_1
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2749:1: rule__KeyFloatValueAnnotation__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__KeyFloatValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2753:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2754:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2754:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2755:1: RULE_ID
            {
             before(grammarAccess.getKeyFloatValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__KeyFloatValueAnnotation__NameAssignment_15551); 
             after(grammarAccess.getKeyFloatValueAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__NameAssignment_1


    // $ANTLR start rule__KeyFloatValueAnnotation__ValueAssignment_2
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2764:1: rule__KeyFloatValueAnnotation__ValueAssignment_2 : ( RULE_FLOAT ) ;
    public final void rule__KeyFloatValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2768:1: ( ( RULE_FLOAT ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2769:1: ( RULE_FLOAT )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2769:1: ( RULE_FLOAT )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2770:1: RULE_FLOAT
            {
             before(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0()); 
            match(input,RULE_FLOAT,FollowSets000.FOLLOW_RULE_FLOAT_in_rule__KeyFloatValueAnnotation__ValueAssignment_25582); 
             after(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__KeyFloatValueAnnotation__ValueAssignment_2


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA2_eotS =
        "\15\uffff";
    static final String DFA2_eofS =
        "\15\uffff";
    static final String DFA2_minS =
        "\2\6\1\5\4\uffff\1\4\5\6";
    static final String DFA2_maxS =
        "\2\26\1\5\4\uffff\6\26";
    static final String DFA2_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\6\uffff";
    static final String DFA2_specialS =
        "\15\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\7",
            "",
            "",
            "",
            "",
            "\1\10\1\11\1\1\1\13\1\14\1\12\5\uffff\1\3\2\uffff\1\4\1\uffff"+
            "\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2"
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
            return "453:1: rule__TopLevelEntity__Alternatives_1_4_0_1 : ( ( ( rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0 ) ) | ( ( rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1 ) ) | ( ( rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2 ) ) | ( ( rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3 ) ) );";
        }
    }
    static final String DFA4_eotS =
        "\15\uffff";
    static final String DFA4_eofS =
        "\15\uffff";
    static final String DFA4_minS =
        "\2\6\1\5\4\uffff\1\4\5\6";
    static final String DFA4_maxS =
        "\2\26\1\5\4\uffff\6\26";
    static final String DFA4_acceptS =
        "\3\uffff\1\1\1\2\1\3\1\4\6\uffff";
    static final String DFA4_specialS =
        "\15\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\7",
            "",
            "",
            "",
            "",
            "\1\13\1\14\1\1\1\10\1\12\1\11\5\uffff\1\3\2\uffff\1\4\1\uffff"+
            "\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2",
            "\1\1\10\uffff\1\3\2\uffff\1\4\1\uffff\1\5\1\6\1\2"
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "511:1: rule__Entity__Alternatives_5_0_1 : ( ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) ) | ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) ) | ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) ) | ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) ) );";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity61 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTopLevelEntity68 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__0_in_ruleTopLevelEntity94 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity121 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEntity128 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__0_in_ruleEntity154 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_entryRuleLink183 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLink190 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__0_in_ruleLink216 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_entryRulePort243 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePort250 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__0_in_rulePort276 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_entryRuleRelation303 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleRelation310 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__0_in_ruleRelation336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_entryRuleAnnotation363 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleAnnotation370 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Annotation__Alternatives_in_ruleAnnotation396 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation423 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCommentAnnotation430 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__CommentAnnotation__ValueAssignment_in_ruleCommentAnnotation456 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation483 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTagAnnotation490 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__0_in_ruleTagAnnotation516 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation543 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation550 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__0_in_ruleKeyStringValueAnnotation576 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation603 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation610 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__0_in_ruleKeyBooleanValueAnnotation636 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation663 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation670 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__0_in_ruleKeyIntValueAnnotation696 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation723 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation730 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__0_in_ruleKeyFloatValueAnnotation756 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString785 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString792 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EString__Alternatives_in_ruleEString818 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1_4_0__0_in_rule__TopLevelEntity__Alternatives_1_4854 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__TopLevelEntity__Alternatives_1_4873 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_0_in_rule__TopLevelEntity__Alternatives_1_4_0_1907 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_1_in_rule__TopLevelEntity__Alternatives_1_4_0_1925 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_2_in_rule__TopLevelEntity__Alternatives_1_4_0_1943 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_3_in_rule__TopLevelEntity__Alternatives_1_4_0_1961 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__0_in_rule__Entity__Alternatives_5994 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Entity__Alternatives_51013 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildEntitiesAssignment_5_0_1_0_in_rule__Entity__Alternatives_5_0_11047 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildLinksAssignment_5_0_1_1_in_rule__Entity__Alternatives_5_0_11065 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildPortsAssignment_5_0_1_2_in_rule__Entity__Alternatives_5_0_11083 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildRelationsAssignment_5_0_1_3_in_rule__Entity__Alternatives_5_0_11101 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_rule__Annotation__Alternatives1135 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_rule__Annotation__Alternatives1152 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_rule__Annotation__Alternatives1169 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_rule__Annotation__Alternatives1186 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_rule__Annotation__Alternatives1203 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_rule__Annotation__Alternatives1220 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__EString__Alternatives1252 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__EString__Alternatives1269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__0__Impl_in_rule__TopLevelEntity__Group__01299 = new BitSet(new long[]{0x0000000000408042L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__1_in_rule__TopLevelEntity__Group__01302 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__1__Impl_in_rule__TopLevelEntity__Group__11360 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__0_in_rule__TopLevelEntity__Group__1__Impl1387 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__0__Impl_in_rule__TopLevelEntity__Group_1__01422 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__1_in_rule__TopLevelEntity__Group_1__01425 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__AnnotationsAssignment_1_0_in_rule__TopLevelEntity__Group_1__0__Impl1452 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__1__Impl_in_rule__TopLevelEntity__Group_1__11483 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__2_in_rule__TopLevelEntity__Group_1__11486 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__TopLevelEntity__Group_1__1__Impl1514 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__2__Impl_in_rule__TopLevelEntity__Group_1__21545 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__3_in_rule__TopLevelEntity__Group_1__21548 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__IdAssignment_1_2_in_rule__TopLevelEntity__Group_1__2__Impl1575 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__3__Impl_in_rule__TopLevelEntity__Group_1__31605 = new BitSet(new long[]{0x0000000000014000L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__4_in_rule__TopLevelEntity__Group_1__31608 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__NameAssignment_1_3_in_rule__TopLevelEntity__Group_1__3__Impl1635 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1__4__Impl_in_rule__TopLevelEntity__Group_1__41666 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Alternatives_1_4_in_rule__TopLevelEntity__Group_1__4__Impl1693 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1_4_0__0__Impl_in_rule__TopLevelEntity__Group_1_4_0__01733 = new BitSet(new long[]{0x0000000000768040L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1_4_0__1_in_rule__TopLevelEntity__Group_1_4_0__01736 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_rule__TopLevelEntity__Group_1_4_0__0__Impl1764 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1_4_0__1__Impl_in_rule__TopLevelEntity__Group_1_4_0__11795 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1_4_0__2_in_rule__TopLevelEntity__Group_1_4_0__11798 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Alternatives_1_4_0_1_in_rule__TopLevelEntity__Group_1_4_0__1__Impl1825 = new BitSet(new long[]{0x0000000000748042L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_1_4_0__2__Impl_in_rule__TopLevelEntity__Group_1_4_0__21856 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__TopLevelEntity__Group_1_4_0__2__Impl1884 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__0__Impl_in_rule__Entity__Group__01921 = new BitSet(new long[]{0x0000000000408040L});
        public static final BitSet FOLLOW_rule__Entity__Group__1_in_rule__Entity__Group__01924 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__1__Impl_in_rule__Entity__Group__11982 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_rule__Entity__Group__2_in_rule__Entity__Group__11985 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__AnnotationsAssignment_1_in_rule__Entity__Group__1__Impl2012 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Entity__Group__2__Impl_in_rule__Entity__Group__22043 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Entity__Group__3_in_rule__Entity__Group__22046 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__Entity__Group__2__Impl2074 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__3__Impl_in_rule__Entity__Group__32105 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_rule__Entity__Group__4_in_rule__Entity__Group__32108 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__IdAssignment_3_in_rule__Entity__Group__3__Impl2135 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__4__Impl_in_rule__Entity__Group__42165 = new BitSet(new long[]{0x0000000000014000L});
        public static final BitSet FOLLOW_rule__Entity__Group__5_in_rule__Entity__Group__42168 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__NameAssignment_4_in_rule__Entity__Group__4__Impl2195 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__5__Impl_in_rule__Entity__Group__52226 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Alternatives_5_in_rule__Entity__Group__5__Impl2253 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__0__Impl_in_rule__Entity__Group_5_0__02295 = new BitSet(new long[]{0x0000000000768040L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__1_in_rule__Entity__Group_5_0__02298 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_rule__Entity__Group_5_0__0__Impl2326 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__1__Impl_in_rule__Entity__Group_5_0__12357 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__2_in_rule__Entity__Group_5_0__12360 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Alternatives_5_0_1_in_rule__Entity__Group_5_0__1__Impl2387 = new BitSet(new long[]{0x0000000000748042L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__2__Impl_in_rule__Entity__Group_5_0__22418 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__Entity__Group_5_0__2__Impl2446 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__0__Impl_in_rule__Link__Group__02483 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_rule__Link__Group__1_in_rule__Link__Group__02486 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__AnnotationsAssignment_0_in_rule__Link__Group__0__Impl2513 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Link__Group__1__Impl_in_rule__Link__Group__12544 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__Link__Group__2_in_rule__Link__Group__12547 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_rule__Link__Group__1__Impl2575 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__2__Impl_in_rule__Link__Group__22606 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Link__Group__3_in_rule__Link__Group__22609 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__NameAssignment_2_in_rule__Link__Group__2__Impl2636 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__3__Impl_in_rule__Link__Group__32667 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_rule__Link__Group__4_in_rule__Link__Group__32670 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__SourceAssignment_3_in_rule__Link__Group__3__Impl2697 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__4__Impl_in_rule__Link__Group__42727 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Link__Group__5_in_rule__Link__Group__42730 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_rule__Link__Group__4__Impl2758 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__5__Impl_in_rule__Link__Group__52789 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__Link__Group__6_in_rule__Link__Group__52792 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__TargetAssignment_5_in_rule__Link__Group__5__Impl2819 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__6__Impl_in_rule__Link__Group__62849 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Link__Group__6__Impl2877 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__0__Impl_in_rule__Port__Group__02922 = new BitSet(new long[]{0x0000000000500040L});
        public static final BitSet FOLLOW_rule__Port__Group__1_in_rule__Port__Group__02925 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__1__Impl_in_rule__Port__Group__12983 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_rule__Port__Group__2_in_rule__Port__Group__12986 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__AnnotationsAssignment_1_in_rule__Port__Group__1__Impl3013 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Port__Group__2__Impl_in_rule__Port__Group__23044 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Port__Group__3_in_rule__Port__Group__23047 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_20_in_rule__Port__Group__2__Impl3075 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__3__Impl_in_rule__Port__Group__33106 = new BitSet(new long[]{0x0000000000004010L});
        public static final BitSet FOLLOW_rule__Port__Group__4_in_rule__Port__Group__33109 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__IdAssignment_3_in_rule__Port__Group__3__Impl3136 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__4__Impl_in_rule__Port__Group__43166 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__Port__Group__5_in_rule__Port__Group__43169 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__NameAssignment_4_in_rule__Port__Group__4__Impl3196 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__5__Impl_in_rule__Port__Group__53227 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Port__Group__5__Impl3255 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__0__Impl_in_rule__Relation__Group__03298 = new BitSet(new long[]{0x0000000000600040L});
        public static final BitSet FOLLOW_rule__Relation__Group__1_in_rule__Relation__Group__03301 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__1__Impl_in_rule__Relation__Group__13359 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_rule__Relation__Group__2_in_rule__Relation__Group__13362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__AnnotationsAssignment_1_in_rule__Relation__Group__1__Impl3389 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Relation__Group__2__Impl_in_rule__Relation__Group__23420 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Relation__Group__3_in_rule__Relation__Group__23423 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_21_in_rule__Relation__Group__2__Impl3451 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__3__Impl_in_rule__Relation__Group__33482 = new BitSet(new long[]{0x0000000000004010L});
        public static final BitSet FOLLOW_rule__Relation__Group__4_in_rule__Relation__Group__33485 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__IdAssignment_3_in_rule__Relation__Group__3__Impl3512 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__4__Impl_in_rule__Relation__Group__43542 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__Relation__Group__5_in_rule__Relation__Group__43545 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__NameAssignment_4_in_rule__Relation__Group__4__Impl3572 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__5__Impl_in_rule__Relation__Group__53603 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Relation__Group__5__Impl3631 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__0__Impl_in_rule__TagAnnotation__Group__03674 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__1_in_rule__TagAnnotation__Group__03677 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__TagAnnotation__Group__0__Impl3705 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__1__Impl_in_rule__TagAnnotation__Group__13736 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__NameAssignment_1_in_rule__TagAnnotation__Group__1__Impl3763 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__0__Impl_in_rule__KeyStringValueAnnotation__Group__03797 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__1_in_rule__KeyStringValueAnnotation__Group__03800 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyStringValueAnnotation__Group__0__Impl3828 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__1__Impl_in_rule__KeyStringValueAnnotation__Group__13859 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__2_in_rule__KeyStringValueAnnotation__Group__13862 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__NameAssignment_1_in_rule__KeyStringValueAnnotation__Group__1__Impl3889 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__2__Impl_in_rule__KeyStringValueAnnotation__Group__23919 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__ValueAssignment_2_in_rule__KeyStringValueAnnotation__Group__2__Impl3946 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__0__Impl_in_rule__KeyBooleanValueAnnotation__Group__03982 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__1_in_rule__KeyBooleanValueAnnotation__Group__03985 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyBooleanValueAnnotation__Group__0__Impl4013 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__1__Impl_in_rule__KeyBooleanValueAnnotation__Group__14044 = new BitSet(new long[]{0x0000000000000080L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__2_in_rule__KeyBooleanValueAnnotation__Group__14047 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__NameAssignment_1_in_rule__KeyBooleanValueAnnotation__Group__1__Impl4074 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__2__Impl_in_rule__KeyBooleanValueAnnotation__Group__24104 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__ValueAssignment_2_in_rule__KeyBooleanValueAnnotation__Group__2__Impl4131 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__0__Impl_in_rule__KeyIntValueAnnotation__Group__04167 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__1_in_rule__KeyIntValueAnnotation__Group__04170 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyIntValueAnnotation__Group__0__Impl4198 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__1__Impl_in_rule__KeyIntValueAnnotation__Group__14229 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__2_in_rule__KeyIntValueAnnotation__Group__14232 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__NameAssignment_1_in_rule__KeyIntValueAnnotation__Group__1__Impl4259 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__2__Impl_in_rule__KeyIntValueAnnotation__Group__24289 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__ValueAssignment_2_in_rule__KeyIntValueAnnotation__Group__2__Impl4316 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__0__Impl_in_rule__KeyFloatValueAnnotation__Group__04352 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__1_in_rule__KeyFloatValueAnnotation__Group__04355 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyFloatValueAnnotation__Group__0__Impl4383 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__1__Impl_in_rule__KeyFloatValueAnnotation__Group__14414 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__2_in_rule__KeyFloatValueAnnotation__Group__14417 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__NameAssignment_1_in_rule__KeyFloatValueAnnotation__Group__1__Impl4444 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__2__Impl_in_rule__KeyFloatValueAnnotation__Group__24474 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__ValueAssignment_2_in_rule__KeyFloatValueAnnotation__Group__2__Impl4501 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__TopLevelEntity__AnnotationsAssignment_1_04543 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__TopLevelEntity__IdAssignment_1_24574 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__TopLevelEntity__NameAssignment_1_34605 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_rule__TopLevelEntity__ChildEntitiesAssignment_1_4_0_1_04636 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_rule__TopLevelEntity__ChildLinksAssignment_1_4_0_1_14667 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_rule__TopLevelEntity__ChildPortsAssignment_1_4_0_1_24698 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_rule__TopLevelEntity__ChildRelationsAssignment_1_4_0_1_34729 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Entity__AnnotationsAssignment_14760 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Entity__IdAssignment_34791 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Entity__NameAssignment_44822 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_rule__Entity__ChildEntitiesAssignment_5_0_1_04853 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_rule__Entity__ChildLinksAssignment_5_0_1_14884 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_rule__Entity__ChildPortsAssignment_5_0_1_24915 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_rule__Entity__ChildRelationsAssignment_5_0_1_34946 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Link__AnnotationsAssignment_04977 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Link__NameAssignment_25008 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Link__SourceAssignment_35043 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Link__TargetAssignment_55082 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Port__AnnotationsAssignment_15117 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Port__IdAssignment_35148 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Port__NameAssignment_45179 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Relation__AnnotationsAssignment_15210 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Relation__IdAssignment_35241 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Relation__NameAssignment_45272 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_COMMENT_ANNOTATION_in_rule__CommentAnnotation__ValueAssignment5303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__TagAnnotation__NameAssignment_15334 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__KeyStringValueAnnotation__NameAssignment_15365 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rule__KeyStringValueAnnotation__ValueAssignment_25396 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__KeyBooleanValueAnnotation__NameAssignment_15427 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__KeyBooleanValueAnnotation__ValueAssignment_25458 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__KeyIntValueAnnotation__NameAssignment_15489 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__KeyIntValueAnnotation__ValueAssignment_25520 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__KeyFloatValueAnnotation__NameAssignment_15551 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_FLOAT_in_rule__KeyFloatValueAnnotation__ValueAssignment_25582 = new BitSet(new long[]{0x0000000000000002L});
    }


}