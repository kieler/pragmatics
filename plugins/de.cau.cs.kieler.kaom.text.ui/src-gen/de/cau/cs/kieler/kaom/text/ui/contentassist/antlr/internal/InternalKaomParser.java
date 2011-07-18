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
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalKaomParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_COMMENT_ANNOTATION", "RULE_BOOLEAN", "RULE_INT", "RULE_FLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "';'", "'entity'", "'{'", "'}'", "'link'", "'to'", "'port'", "'relation'", "'@'", "'('", "')'", "'['", "']'", "'import'", "'.'"
    };
    public static final int RULE_BOOLEAN=7;
    public static final int RULE_ID=5;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_FLOAT=9;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__19=19;
    public static final int RULE_STRING=4;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__14=14;
    public static final int RULE_INT=8;
    public static final int RULE_WS=12;
    public static final int RULE_COMMENT_ANNOTATION=6;

    // delegates
    // delegators


        public InternalKaomParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalKaomParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalKaomParser.tokenNames; }
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




    // $ANTLR start "entryRuleTopLevelEntity"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:62:1: entryRuleTopLevelEntity : ruleTopLevelEntity EOF ;
    public final void entryRuleTopLevelEntity() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:63:1: ( ruleTopLevelEntity EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:64:1: ruleTopLevelEntity EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity67);
            ruleTopLevelEntity();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTopLevelEntity74); if (state.failed) return ;

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
    // $ANTLR end "entryRuleTopLevelEntity"


    // $ANTLR start "ruleTopLevelEntity"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:71:1: ruleTopLevelEntity : ( ( rule__TopLevelEntity__Group__0 ) ) ;
    public final void ruleTopLevelEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:75:2: ( ( ( rule__TopLevelEntity__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:76:1: ( ( rule__TopLevelEntity__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:76:1: ( ( rule__TopLevelEntity__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:77:1: ( rule__TopLevelEntity__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:78:1: ( rule__TopLevelEntity__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:78:2: rule__TopLevelEntity__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__0_in_ruleTopLevelEntity100);
            rule__TopLevelEntity__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getGroup()); 
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
    // $ANTLR end "ruleTopLevelEntity"


    // $ANTLR start "entryRuleEntity"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:90:1: entryRuleEntity : ruleEntity EOF ;
    public final void entryRuleEntity() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:91:1: ( ruleEntity EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:92:1: ruleEntity EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_entryRuleEntity127);
            ruleEntity();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEntity134); if (state.failed) return ;

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
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:99:1: ruleEntity : ( ( rule__Entity__Group__0 ) ) ;
    public final void ruleEntity() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:103:2: ( ( ( rule__Entity__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:104:1: ( ( rule__Entity__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:104:1: ( ( rule__Entity__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:105:1: ( rule__Entity__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:106:1: ( rule__Entity__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:106:2: rule__Entity__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__0_in_ruleEntity160);
            rule__Entity__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getGroup()); 
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
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleLink"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:120:1: entryRuleLink : ruleLink EOF ;
    public final void entryRuleLink() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:121:1: ( ruleLink EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:122:1: ruleLink EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_entryRuleLink189);
            ruleLink();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLink196); if (state.failed) return ;

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
    // $ANTLR end "entryRuleLink"


    // $ANTLR start "ruleLink"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:129:1: ruleLink : ( ( rule__Link__Group__0 ) ) ;
    public final void ruleLink() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:133:2: ( ( ( rule__Link__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:134:1: ( ( rule__Link__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:134:1: ( ( rule__Link__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:135:1: ( rule__Link__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:136:1: ( rule__Link__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:136:2: rule__Link__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__0_in_ruleLink222);
            rule__Link__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getGroup()); 
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
    // $ANTLR end "ruleLink"


    // $ANTLR start "entryRulePort"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:148:1: entryRulePort : rulePort EOF ;
    public final void entryRulePort() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:149:1: ( rulePort EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:150:1: rulePort EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_rulePort_in_entryRulePort249);
            rulePort();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePort256); if (state.failed) return ;

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
    // $ANTLR end "entryRulePort"


    // $ANTLR start "rulePort"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:157:1: rulePort : ( ( rule__Port__Group__0 ) ) ;
    public final void rulePort() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:161:2: ( ( ( rule__Port__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:162:1: ( ( rule__Port__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:162:1: ( ( rule__Port__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:163:1: ( rule__Port__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:164:1: ( rule__Port__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:164:2: rule__Port__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__0_in_rulePort282);
            rule__Port__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getGroup()); 
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
    // $ANTLR end "rulePort"


    // $ANTLR start "entryRuleRelation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:176:1: entryRuleRelation : ruleRelation EOF ;
    public final void entryRuleRelation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:177:1: ( ruleRelation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:178:1: ruleRelation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_entryRuleRelation309);
            ruleRelation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleRelation316); if (state.failed) return ;

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
    // $ANTLR end "entryRuleRelation"


    // $ANTLR start "ruleRelation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:185:1: ruleRelation : ( ( rule__Relation__Group__0 ) ) ;
    public final void ruleRelation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:189:2: ( ( ( rule__Relation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:190:1: ( ( rule__Relation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:190:1: ( ( rule__Relation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:191:1: ( rule__Relation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:192:1: ( rule__Relation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:192:2: rule__Relation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__0_in_ruleRelation342);
            rule__Relation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getGroup()); 
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
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:204:1: entryRuleAnnotation : ruleAnnotation EOF ;
    public final void entryRuleAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:205:1: ( ruleAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:206:1: ruleAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_entryRuleAnnotation369);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleAnnotation376); if (state.failed) return ;

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
    // $ANTLR end "entryRuleAnnotation"


    // $ANTLR start "ruleAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:213:1: ruleAnnotation : ( ( rule__Annotation__Alternatives ) ) ;
    public final void ruleAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:217:2: ( ( ( rule__Annotation__Alternatives ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:218:1: ( ( rule__Annotation__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:218:1: ( ( rule__Annotation__Alternatives ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:219:1: ( rule__Annotation__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnnotationAccess().getAlternatives()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:220:1: ( rule__Annotation__Alternatives )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:220:2: rule__Annotation__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__Annotation__Alternatives_in_ruleAnnotation402);
            rule__Annotation__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnnotationAccess().getAlternatives()); 
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
    // $ANTLR end "ruleAnnotation"


    // $ANTLR start "entryRuleCommentAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:232:1: entryRuleCommentAnnotation : ruleCommentAnnotation EOF ;
    public final void entryRuleCommentAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:233:1: ( ruleCommentAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:234:1: ruleCommentAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCommentAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation429);
            ruleCommentAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCommentAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCommentAnnotation436); if (state.failed) return ;

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
    // $ANTLR end "entryRuleCommentAnnotation"


    // $ANTLR start "ruleCommentAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:241:1: ruleCommentAnnotation : ( ( rule__CommentAnnotation__ValueAssignment ) ) ;
    public final void ruleCommentAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:245:2: ( ( ( rule__CommentAnnotation__ValueAssignment ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:246:1: ( ( rule__CommentAnnotation__ValueAssignment ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:246:1: ( ( rule__CommentAnnotation__ValueAssignment ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:247:1: ( rule__CommentAnnotation__ValueAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCommentAnnotationAccess().getValueAssignment()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:248:1: ( rule__CommentAnnotation__ValueAssignment )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:248:2: rule__CommentAnnotation__ValueAssignment
            {
            pushFollow(FollowSets000.FOLLOW_rule__CommentAnnotation__ValueAssignment_in_ruleCommentAnnotation462);
            rule__CommentAnnotation__ValueAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCommentAnnotationAccess().getValueAssignment()); 
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
    // $ANTLR end "ruleCommentAnnotation"


    // $ANTLR start "entryRuleTagAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:260:1: entryRuleTagAnnotation : ruleTagAnnotation EOF ;
    public final void entryRuleTagAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:261:1: ( ruleTagAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:262:1: ruleTagAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation489);
            ruleTagAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTagAnnotation496); if (state.failed) return ;

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
    // $ANTLR end "entryRuleTagAnnotation"


    // $ANTLR start "ruleTagAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:269:1: ruleTagAnnotation : ( ( rule__TagAnnotation__Group__0 ) ) ;
    public final void ruleTagAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:273:2: ( ( ( rule__TagAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:274:1: ( ( rule__TagAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:274:1: ( ( rule__TagAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:275:1: ( rule__TagAnnotation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:276:1: ( rule__TagAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:276:2: rule__TagAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__0_in_ruleTagAnnotation522);
            rule__TagAnnotation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getGroup()); 
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
    // $ANTLR end "ruleTagAnnotation"


    // $ANTLR start "entryRuleKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:288:1: entryRuleKeyStringValueAnnotation : ruleKeyStringValueAnnotation EOF ;
    public final void entryRuleKeyStringValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:289:1: ( ruleKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:290:1: ruleKeyStringValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation549);
            ruleKeyStringValueAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation556); if (state.failed) return ;

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
    // $ANTLR end "entryRuleKeyStringValueAnnotation"


    // $ANTLR start "ruleKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:297:1: ruleKeyStringValueAnnotation : ( ( rule__KeyStringValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyStringValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:301:2: ( ( ( rule__KeyStringValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:302:1: ( ( rule__KeyStringValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:302:1: ( ( rule__KeyStringValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:303:1: ( rule__KeyStringValueAnnotation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:304:1: ( rule__KeyStringValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:304:2: rule__KeyStringValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__0_in_ruleKeyStringValueAnnotation582);
            rule__KeyStringValueAnnotation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getGroup()); 
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
    // $ANTLR end "ruleKeyStringValueAnnotation"


    // $ANTLR start "entryRuleTypedKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:316:1: entryRuleTypedKeyStringValueAnnotation : ruleTypedKeyStringValueAnnotation EOF ;
    public final void entryRuleTypedKeyStringValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:317:1: ( ruleTypedKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:318:1: ruleTypedKeyStringValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation609);
            ruleTypedKeyStringValueAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation616); if (state.failed) return ;

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
    // $ANTLR end "entryRuleTypedKeyStringValueAnnotation"


    // $ANTLR start "ruleTypedKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:325:1: ruleTypedKeyStringValueAnnotation : ( ( rule__TypedKeyStringValueAnnotation__Group__0 ) ) ;
    public final void ruleTypedKeyStringValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:329:2: ( ( ( rule__TypedKeyStringValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:330:1: ( ( rule__TypedKeyStringValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:330:1: ( ( rule__TypedKeyStringValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:331:1: ( rule__TypedKeyStringValueAnnotation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:332:1: ( rule__TypedKeyStringValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:332:2: rule__TypedKeyStringValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__0_in_ruleTypedKeyStringValueAnnotation642);
            rule__TypedKeyStringValueAnnotation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getGroup()); 
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
    // $ANTLR end "ruleTypedKeyStringValueAnnotation"


    // $ANTLR start "entryRuleKeyBooleanValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:344:1: entryRuleKeyBooleanValueAnnotation : ruleKeyBooleanValueAnnotation EOF ;
    public final void entryRuleKeyBooleanValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:345:1: ( ruleKeyBooleanValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:346:1: ruleKeyBooleanValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation669);
            ruleKeyBooleanValueAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation676); if (state.failed) return ;

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
    // $ANTLR end "entryRuleKeyBooleanValueAnnotation"


    // $ANTLR start "ruleKeyBooleanValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:353:1: ruleKeyBooleanValueAnnotation : ( ( rule__KeyBooleanValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyBooleanValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:357:2: ( ( ( rule__KeyBooleanValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:358:1: ( ( rule__KeyBooleanValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:358:1: ( ( rule__KeyBooleanValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:359:1: ( rule__KeyBooleanValueAnnotation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:360:1: ( rule__KeyBooleanValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:360:2: rule__KeyBooleanValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__0_in_ruleKeyBooleanValueAnnotation702);
            rule__KeyBooleanValueAnnotation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup()); 
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
    // $ANTLR end "ruleKeyBooleanValueAnnotation"


    // $ANTLR start "entryRuleKeyIntValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:372:1: entryRuleKeyIntValueAnnotation : ruleKeyIntValueAnnotation EOF ;
    public final void entryRuleKeyIntValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:373:1: ( ruleKeyIntValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:374:1: ruleKeyIntValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation729);
            ruleKeyIntValueAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation736); if (state.failed) return ;

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
    // $ANTLR end "entryRuleKeyIntValueAnnotation"


    // $ANTLR start "ruleKeyIntValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:381:1: ruleKeyIntValueAnnotation : ( ( rule__KeyIntValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyIntValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:385:2: ( ( ( rule__KeyIntValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:386:1: ( ( rule__KeyIntValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:386:1: ( ( rule__KeyIntValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:387:1: ( rule__KeyIntValueAnnotation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:388:1: ( rule__KeyIntValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:388:2: rule__KeyIntValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__0_in_ruleKeyIntValueAnnotation762);
            rule__KeyIntValueAnnotation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getGroup()); 
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
    // $ANTLR end "ruleKeyIntValueAnnotation"


    // $ANTLR start "entryRuleKeyFloatValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:400:1: entryRuleKeyFloatValueAnnotation : ruleKeyFloatValueAnnotation EOF ;
    public final void entryRuleKeyFloatValueAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:401:1: ( ruleKeyFloatValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:402:1: ruleKeyFloatValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation789);
            ruleKeyFloatValueAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation796); if (state.failed) return ;

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
    // $ANTLR end "entryRuleKeyFloatValueAnnotation"


    // $ANTLR start "ruleKeyFloatValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:409:1: ruleKeyFloatValueAnnotation : ( ( rule__KeyFloatValueAnnotation__Group__0 ) ) ;
    public final void ruleKeyFloatValueAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:413:2: ( ( ( rule__KeyFloatValueAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:414:1: ( ( rule__KeyFloatValueAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:414:1: ( ( rule__KeyFloatValueAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:415:1: ( rule__KeyFloatValueAnnotation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:416:1: ( rule__KeyFloatValueAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:416:2: rule__KeyFloatValueAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__0_in_ruleKeyFloatValueAnnotation822);
            rule__KeyFloatValueAnnotation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup()); 
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
    // $ANTLR end "ruleKeyFloatValueAnnotation"


    // $ANTLR start "entryRuleImportAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:428:1: entryRuleImportAnnotation : ruleImportAnnotation EOF ;
    public final void entryRuleImportAnnotation() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:429:1: ( ruleImportAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:430:1: ruleImportAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImportAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleImportAnnotation_in_entryRuleImportAnnotation849);
            ruleImportAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getImportAnnotationRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleImportAnnotation856); if (state.failed) return ;

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
    // $ANTLR end "entryRuleImportAnnotation"


    // $ANTLR start "ruleImportAnnotation"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:437:1: ruleImportAnnotation : ( ( rule__ImportAnnotation__Group__0 ) ) ;
    public final void ruleImportAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:441:2: ( ( ( rule__ImportAnnotation__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:442:1: ( ( rule__ImportAnnotation__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:442:1: ( ( rule__ImportAnnotation__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:443:1: ( rule__ImportAnnotation__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImportAnnotationAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:444:1: ( rule__ImportAnnotation__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:444:2: rule__ImportAnnotation__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__ImportAnnotation__Group__0_in_ruleImportAnnotation882);
            rule__ImportAnnotation__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImportAnnotationAccess().getGroup()); 
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
    // $ANTLR end "ruleImportAnnotation"


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:456:1: entryRuleEString : ruleEString EOF ;
    public final void entryRuleEString() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:457:1: ( ruleEString EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:458:1: ruleEString EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEStringRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString909);
            ruleEString();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEStringRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString916); if (state.failed) return ;

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
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:465:1: ruleEString : ( ( rule__EString__Alternatives ) ) ;
    public final void ruleEString() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:469:2: ( ( ( rule__EString__Alternatives ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:470:1: ( ( rule__EString__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:470:1: ( ( rule__EString__Alternatives ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:471:1: ( rule__EString__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEStringAccess().getAlternatives()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:472:1: ( rule__EString__Alternatives )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:472:2: rule__EString__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__EString__Alternatives_in_ruleEString942);
            rule__EString__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEStringAccess().getAlternatives()); 
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
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleExtendedID"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:484:1: entryRuleExtendedID : ruleExtendedID EOF ;
    public final void entryRuleExtendedID() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:485:1: ( ruleExtendedID EOF )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:486:1: ruleExtendedID EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExtendedIDRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_entryRuleExtendedID969);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExtendedIDRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleExtendedID976); if (state.failed) return ;

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
    // $ANTLR end "entryRuleExtendedID"


    // $ANTLR start "ruleExtendedID"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:493:1: ruleExtendedID : ( ( rule__ExtendedID__Group__0 ) ) ;
    public final void ruleExtendedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:497:2: ( ( ( rule__ExtendedID__Group__0 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:498:1: ( ( rule__ExtendedID__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:498:1: ( ( rule__ExtendedID__Group__0 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:499:1: ( rule__ExtendedID__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExtendedIDAccess().getGroup()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:500:1: ( rule__ExtendedID__Group__0 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:500:2: rule__ExtendedID__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group__0_in_ruleExtendedID1002);
            rule__ExtendedID__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExtendedIDAccess().getGroup()); 
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
    // $ANTLR end "ruleExtendedID"


    // $ANTLR start "rule__TopLevelEntity__Alternatives_2_4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:512:1: rule__TopLevelEntity__Alternatives_2_4 : ( ( ( rule__TopLevelEntity__Group_2_4_0__0 ) ) | ( ';' ) );
    public final void rule__TopLevelEntity__Alternatives_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:516:1: ( ( ( rule__TopLevelEntity__Group_2_4_0__0 ) ) | ( ';' ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==16) ) {
                alt1=1;
            }
            else if ( (LA1_0==14) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:517:1: ( ( rule__TopLevelEntity__Group_2_4_0__0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:517:1: ( ( rule__TopLevelEntity__Group_2_4_0__0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:518:1: ( rule__TopLevelEntity__Group_2_4_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTopLevelEntityAccess().getGroup_2_4_0()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:519:1: ( rule__TopLevelEntity__Group_2_4_0__0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:519:2: rule__TopLevelEntity__Group_2_4_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2_4_0__0_in_rule__TopLevelEntity__Alternatives_2_41038);
                    rule__TopLevelEntity__Group_2_4_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTopLevelEntityAccess().getGroup_2_4_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:523:6: ( ';' )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:523:6: ( ';' )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:524:1: ';'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTopLevelEntityAccess().getSemicolonKeyword_2_4_1()); 
                    }
                    match(input,14,FollowSets000.FOLLOW_14_in_rule__TopLevelEntity__Alternatives_2_41057); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTopLevelEntityAccess().getSemicolonKeyword_2_4_1()); 
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
    // $ANTLR end "rule__TopLevelEntity__Alternatives_2_4"


    // $ANTLR start "rule__TopLevelEntity__Alternatives_2_4_0_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:536:1: rule__TopLevelEntity__Alternatives_2_4_0_1 : ( ( ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 ) ) | ( ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 ) ) | ( ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 ) ) | ( ( rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3 ) ) );
    public final void rule__TopLevelEntity__Alternatives_2_4_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:540:1: ( ( ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 ) ) | ( ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 ) ) | ( ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 ) ) | ( ( rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3 ) ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case RULE_COMMENT_ANNOTATION:
                {
                int LA2_1 = input.LA(2);

                if ( (synpred2_InternalKaom()) ) {
                    alt2=1;
                }
                else if ( (synpred3_InternalKaom()) ) {
                    alt2=2;
                }
                else if ( (synpred4_InternalKaom()) ) {
                    alt2=3;
                }
                else if ( (true) ) {
                    alt2=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
                }
                break;
            case 22:
                {
                int LA2_2 = input.LA(2);

                if ( (synpred2_InternalKaom()) ) {
                    alt2=1;
                }
                else if ( (synpred3_InternalKaom()) ) {
                    alt2=2;
                }
                else if ( (synpred4_InternalKaom()) ) {
                    alt2=3;
                }
                else if ( (true) ) {
                    alt2=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

                    throw nvae;
                }
                }
                break;
            case 15:
                {
                alt2=1;
                }
                break;
            case 18:
                {
                alt2=2;
                }
                break;
            case 20:
                {
                alt2=3;
                }
                break;
            case 21:
                {
                alt2=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:541:1: ( ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:541:1: ( ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:542:1: ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTopLevelEntityAccess().getChildEntitiesAssignment_2_4_0_1_0()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:543:1: ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:543:2: rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0_in_rule__TopLevelEntity__Alternatives_2_4_0_11091);
                    rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTopLevelEntityAccess().getChildEntitiesAssignment_2_4_0_1_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:547:6: ( ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:547:6: ( ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:548:1: ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTopLevelEntityAccess().getChildLinksAssignment_2_4_0_1_1()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:549:1: ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:549:2: rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1_in_rule__TopLevelEntity__Alternatives_2_4_0_11109);
                    rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTopLevelEntityAccess().getChildLinksAssignment_2_4_0_1_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:553:6: ( ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:553:6: ( ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:554:1: ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTopLevelEntityAccess().getChildPortsAssignment_2_4_0_1_2()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:555:1: ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:555:2: rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2_in_rule__TopLevelEntity__Alternatives_2_4_0_11127);
                    rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTopLevelEntityAccess().getChildPortsAssignment_2_4_0_1_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:559:6: ( ( rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:559:6: ( ( rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:560:1: ( rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTopLevelEntityAccess().getChildRelationsAssignment_2_4_0_1_3()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:561:1: ( rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:561:2: rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3_in_rule__TopLevelEntity__Alternatives_2_4_0_11145);
                    rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTopLevelEntityAccess().getChildRelationsAssignment_2_4_0_1_3()); 
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
    // $ANTLR end "rule__TopLevelEntity__Alternatives_2_4_0_1"


    // $ANTLR start "rule__Entity__Alternatives_5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:570:1: rule__Entity__Alternatives_5 : ( ( ( rule__Entity__Group_5_0__0 ) ) | ( ';' ) );
    public final void rule__Entity__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:574:1: ( ( ( rule__Entity__Group_5_0__0 ) ) | ( ';' ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==16) ) {
                alt3=1;
            }
            else if ( (LA3_0==14) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:575:1: ( ( rule__Entity__Group_5_0__0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:575:1: ( ( rule__Entity__Group_5_0__0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:576:1: ( rule__Entity__Group_5_0__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEntityAccess().getGroup_5_0()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:577:1: ( rule__Entity__Group_5_0__0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:577:2: rule__Entity__Group_5_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__0_in_rule__Entity__Alternatives_51178);
                    rule__Entity__Group_5_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEntityAccess().getGroup_5_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:581:6: ( ';' )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:581:6: ( ';' )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:582:1: ';'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEntityAccess().getSemicolonKeyword_5_1()); 
                    }
                    match(input,14,FollowSets000.FOLLOW_14_in_rule__Entity__Alternatives_51197); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEntityAccess().getSemicolonKeyword_5_1()); 
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
    // $ANTLR end "rule__Entity__Alternatives_5"


    // $ANTLR start "rule__Entity__Alternatives_5_0_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:594:1: rule__Entity__Alternatives_5_0_1 : ( ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) ) | ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) ) | ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) ) | ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) ) );
    public final void rule__Entity__Alternatives_5_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:598:1: ( ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) ) | ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) ) | ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) ) | ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) ) )
            int alt4=4;
            switch ( input.LA(1) ) {
            case RULE_COMMENT_ANNOTATION:
                {
                int LA4_1 = input.LA(2);

                if ( (synpred6_InternalKaom()) ) {
                    alt4=1;
                }
                else if ( (synpred7_InternalKaom()) ) {
                    alt4=2;
                }
                else if ( (synpred8_InternalKaom()) ) {
                    alt4=3;
                }
                else if ( (true) ) {
                    alt4=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case 22:
                {
                int LA4_2 = input.LA(2);

                if ( (synpred6_InternalKaom()) ) {
                    alt4=1;
                }
                else if ( (synpred7_InternalKaom()) ) {
                    alt4=2;
                }
                else if ( (synpred8_InternalKaom()) ) {
                    alt4=3;
                }
                else if ( (true) ) {
                    alt4=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
                }
                break;
            case 15:
                {
                alt4=1;
                }
                break;
            case 18:
                {
                alt4=2;
                }
                break;
            case 20:
                {
                alt4=3;
                }
                break;
            case 21:
                {
                alt4=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:599:1: ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:599:1: ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:600:1: ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEntityAccess().getChildEntitiesAssignment_5_0_1_0()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:601:1: ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:601:2: rule__Entity__ChildEntitiesAssignment_5_0_1_0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildEntitiesAssignment_5_0_1_0_in_rule__Entity__Alternatives_5_0_11231);
                    rule__Entity__ChildEntitiesAssignment_5_0_1_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEntityAccess().getChildEntitiesAssignment_5_0_1_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:605:6: ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:605:6: ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:606:1: ( rule__Entity__ChildLinksAssignment_5_0_1_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEntityAccess().getChildLinksAssignment_5_0_1_1()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:607:1: ( rule__Entity__ChildLinksAssignment_5_0_1_1 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:607:2: rule__Entity__ChildLinksAssignment_5_0_1_1
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildLinksAssignment_5_0_1_1_in_rule__Entity__Alternatives_5_0_11249);
                    rule__Entity__ChildLinksAssignment_5_0_1_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEntityAccess().getChildLinksAssignment_5_0_1_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:611:6: ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:611:6: ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:612:1: ( rule__Entity__ChildPortsAssignment_5_0_1_2 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEntityAccess().getChildPortsAssignment_5_0_1_2()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:613:1: ( rule__Entity__ChildPortsAssignment_5_0_1_2 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:613:2: rule__Entity__ChildPortsAssignment_5_0_1_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildPortsAssignment_5_0_1_2_in_rule__Entity__Alternatives_5_0_11267);
                    rule__Entity__ChildPortsAssignment_5_0_1_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEntityAccess().getChildPortsAssignment_5_0_1_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:617:6: ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:617:6: ( ( rule__Entity__ChildRelationsAssignment_5_0_1_3 ) )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:618:1: ( rule__Entity__ChildRelationsAssignment_5_0_1_3 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEntityAccess().getChildRelationsAssignment_5_0_1_3()); 
                    }
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:619:1: ( rule__Entity__ChildRelationsAssignment_5_0_1_3 )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:619:2: rule__Entity__ChildRelationsAssignment_5_0_1_3
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildRelationsAssignment_5_0_1_3_in_rule__Entity__Alternatives_5_0_11285);
                    rule__Entity__ChildRelationsAssignment_5_0_1_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEntityAccess().getChildRelationsAssignment_5_0_1_3()); 
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
    // $ANTLR end "rule__Entity__Alternatives_5_0_1"


    // $ANTLR start "rule__Annotation__Alternatives"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:629:1: rule__Annotation__Alternatives : ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleTypedKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) );
    public final void rule__Annotation__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:633:1: ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleTypedKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) )
            int alt5=7;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:634:1: ( ruleCommentAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:634:1: ( ruleCommentAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:635:1: ruleCommentAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_rule__Annotation__Alternatives1319);
                    ruleCommentAnnotation();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:640:6: ( ruleTagAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:640:6: ( ruleTagAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:641:1: ruleTagAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_rule__Annotation__Alternatives1336);
                    ruleTagAnnotation();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:646:6: ( ruleKeyStringValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:646:6: ( ruleKeyStringValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:647:1: ruleKeyStringValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_rule__Annotation__Alternatives1353);
                    ruleKeyStringValueAnnotation();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:652:6: ( ruleTypedKeyStringValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:652:6: ( ruleTypedKeyStringValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:653:1: ruleTypedKeyStringValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAnnotationAccess().getTypedKeyStringValueAnnotationParserRuleCall_3()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_rule__Annotation__Alternatives1370);
                    ruleTypedKeyStringValueAnnotation();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAnnotationAccess().getTypedKeyStringValueAnnotationParserRuleCall_3()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:658:6: ( ruleKeyBooleanValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:658:6: ( ruleKeyBooleanValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:659:1: ruleKeyBooleanValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_4()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_rule__Annotation__Alternatives1387);
                    ruleKeyBooleanValueAnnotation();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_4()); 
                    }

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:664:6: ( ruleKeyIntValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:664:6: ( ruleKeyIntValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:665:1: ruleKeyIntValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_5()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_rule__Annotation__Alternatives1404);
                    ruleKeyIntValueAnnotation();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_5()); 
                    }

                    }


                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:670:6: ( ruleKeyFloatValueAnnotation )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:670:6: ( ruleKeyFloatValueAnnotation )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:671:1: ruleKeyFloatValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_6()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_rule__Annotation__Alternatives1421);
                    ruleKeyFloatValueAnnotation();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_6()); 
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
    // $ANTLR end "rule__Annotation__Alternatives"


    // $ANTLR start "rule__EString__Alternatives"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:681:1: rule__EString__Alternatives : ( ( RULE_STRING ) | ( RULE_ID ) );
    public final void rule__EString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:685:1: ( ( RULE_STRING ) | ( RULE_ID ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_STRING) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_ID) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:686:1: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:686:1: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:687:1: RULE_STRING
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                    }
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__EString__Alternatives1453); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:692:6: ( RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:692:6: ( RULE_ID )
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:693:1: RULE_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                    }
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__EString__Alternatives1470); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
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
    // $ANTLR end "rule__EString__Alternatives"


    // $ANTLR start "rule__TopLevelEntity__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:705:1: rule__TopLevelEntity__Group__0 : rule__TopLevelEntity__Group__0__Impl rule__TopLevelEntity__Group__1 ;
    public final void rule__TopLevelEntity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:709:1: ( rule__TopLevelEntity__Group__0__Impl rule__TopLevelEntity__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:710:2: rule__TopLevelEntity__Group__0__Impl rule__TopLevelEntity__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__0__Impl_in_rule__TopLevelEntity__Group__01500);
            rule__TopLevelEntity__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__1_in_rule__TopLevelEntity__Group__01503);
            rule__TopLevelEntity__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group__0"


    // $ANTLR start "rule__TopLevelEntity__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:717:1: rule__TopLevelEntity__Group__0__Impl : ( () ) ;
    public final void rule__TopLevelEntity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:721:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:722:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:722:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:723:1: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getEntityAction_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:724:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:726:1: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getEntityAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group__0__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:736:1: rule__TopLevelEntity__Group__1 : rule__TopLevelEntity__Group__1__Impl rule__TopLevelEntity__Group__2 ;
    public final void rule__TopLevelEntity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:740:1: ( rule__TopLevelEntity__Group__1__Impl rule__TopLevelEntity__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:741:2: rule__TopLevelEntity__Group__1__Impl rule__TopLevelEntity__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__1__Impl_in_rule__TopLevelEntity__Group__11561);
            rule__TopLevelEntity__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__2_in_rule__TopLevelEntity__Group__11564);
            rule__TopLevelEntity__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group__1"


    // $ANTLR start "rule__TopLevelEntity__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:748:1: rule__TopLevelEntity__Group__1__Impl : ( ( rule__TopLevelEntity__AnnotationsAssignment_1 )* ) ;
    public final void rule__TopLevelEntity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:752:1: ( ( ( rule__TopLevelEntity__AnnotationsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:753:1: ( ( rule__TopLevelEntity__AnnotationsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:753:1: ( ( rule__TopLevelEntity__AnnotationsAssignment_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:754:1: ( rule__TopLevelEntity__AnnotationsAssignment_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:755:1: ( rule__TopLevelEntity__AnnotationsAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==27) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:755:2: rule__TopLevelEntity__AnnotationsAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__AnnotationsAssignment_1_in_rule__TopLevelEntity__Group__1__Impl1591);
            	    rule__TopLevelEntity__AnnotationsAssignment_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_1()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group__1__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:765:1: rule__TopLevelEntity__Group__2 : rule__TopLevelEntity__Group__2__Impl ;
    public final void rule__TopLevelEntity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:769:1: ( rule__TopLevelEntity__Group__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:770:2: rule__TopLevelEntity__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group__2__Impl_in_rule__TopLevelEntity__Group__21622);
            rule__TopLevelEntity__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group__2"


    // $ANTLR start "rule__TopLevelEntity__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:776:1: rule__TopLevelEntity__Group__2__Impl : ( ( rule__TopLevelEntity__Group_2__0 )? ) ;
    public final void rule__TopLevelEntity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:780:1: ( ( ( rule__TopLevelEntity__Group_2__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:781:1: ( ( rule__TopLevelEntity__Group_2__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:781:1: ( ( rule__TopLevelEntity__Group_2__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:782:1: ( rule__TopLevelEntity__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getGroup_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:783:1: ( rule__TopLevelEntity__Group_2__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_COMMENT_ANNOTATION||LA8_0==15||LA8_0==22) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:783:2: rule__TopLevelEntity__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__0_in_rule__TopLevelEntity__Group__2__Impl1649);
                    rule__TopLevelEntity__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getGroup_2()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group__2__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:799:1: rule__TopLevelEntity__Group_2__0 : rule__TopLevelEntity__Group_2__0__Impl rule__TopLevelEntity__Group_2__1 ;
    public final void rule__TopLevelEntity__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:803:1: ( rule__TopLevelEntity__Group_2__0__Impl rule__TopLevelEntity__Group_2__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:804:2: rule__TopLevelEntity__Group_2__0__Impl rule__TopLevelEntity__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__0__Impl_in_rule__TopLevelEntity__Group_2__01686);
            rule__TopLevelEntity__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__1_in_rule__TopLevelEntity__Group_2__01689);
            rule__TopLevelEntity__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2__0"


    // $ANTLR start "rule__TopLevelEntity__Group_2__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:811:1: rule__TopLevelEntity__Group_2__0__Impl : ( ( rule__TopLevelEntity__AnnotationsAssignment_2_0 )* ) ;
    public final void rule__TopLevelEntity__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:815:1: ( ( ( rule__TopLevelEntity__AnnotationsAssignment_2_0 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:816:1: ( ( rule__TopLevelEntity__AnnotationsAssignment_2_0 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:816:1: ( ( rule__TopLevelEntity__AnnotationsAssignment_2_0 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:817:1: ( rule__TopLevelEntity__AnnotationsAssignment_2_0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_2_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:818:1: ( rule__TopLevelEntity__AnnotationsAssignment_2_0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_COMMENT_ANNOTATION||LA9_0==22) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:818:2: rule__TopLevelEntity__AnnotationsAssignment_2_0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__AnnotationsAssignment_2_0_in_rule__TopLevelEntity__Group_2__0__Impl1716);
            	    rule__TopLevelEntity__AnnotationsAssignment_2_0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_2_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2__0__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:828:1: rule__TopLevelEntity__Group_2__1 : rule__TopLevelEntity__Group_2__1__Impl rule__TopLevelEntity__Group_2__2 ;
    public final void rule__TopLevelEntity__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:832:1: ( rule__TopLevelEntity__Group_2__1__Impl rule__TopLevelEntity__Group_2__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:833:2: rule__TopLevelEntity__Group_2__1__Impl rule__TopLevelEntity__Group_2__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__1__Impl_in_rule__TopLevelEntity__Group_2__11747);
            rule__TopLevelEntity__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__2_in_rule__TopLevelEntity__Group_2__11750);
            rule__TopLevelEntity__Group_2__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2__1"


    // $ANTLR start "rule__TopLevelEntity__Group_2__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:840:1: rule__TopLevelEntity__Group_2__1__Impl : ( 'entity' ) ;
    public final void rule__TopLevelEntity__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:844:1: ( ( 'entity' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:845:1: ( 'entity' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:845:1: ( 'entity' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:846:1: 'entity'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getEntityKeyword_2_1()); 
            }
            match(input,15,FollowSets000.FOLLOW_15_in_rule__TopLevelEntity__Group_2__1__Impl1778); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getEntityKeyword_2_1()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2__1__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:859:1: rule__TopLevelEntity__Group_2__2 : rule__TopLevelEntity__Group_2__2__Impl rule__TopLevelEntity__Group_2__3 ;
    public final void rule__TopLevelEntity__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:863:1: ( rule__TopLevelEntity__Group_2__2__Impl rule__TopLevelEntity__Group_2__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:864:2: rule__TopLevelEntity__Group_2__2__Impl rule__TopLevelEntity__Group_2__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__2__Impl_in_rule__TopLevelEntity__Group_2__21809);
            rule__TopLevelEntity__Group_2__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__3_in_rule__TopLevelEntity__Group_2__21812);
            rule__TopLevelEntity__Group_2__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2__2"


    // $ANTLR start "rule__TopLevelEntity__Group_2__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:871:1: rule__TopLevelEntity__Group_2__2__Impl : ( ( rule__TopLevelEntity__IdAssignment_2_2 ) ) ;
    public final void rule__TopLevelEntity__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:875:1: ( ( ( rule__TopLevelEntity__IdAssignment_2_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:876:1: ( ( rule__TopLevelEntity__IdAssignment_2_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:876:1: ( ( rule__TopLevelEntity__IdAssignment_2_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:877:1: ( rule__TopLevelEntity__IdAssignment_2_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getIdAssignment_2_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:878:1: ( rule__TopLevelEntity__IdAssignment_2_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:878:2: rule__TopLevelEntity__IdAssignment_2_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__IdAssignment_2_2_in_rule__TopLevelEntity__Group_2__2__Impl1839);
            rule__TopLevelEntity__IdAssignment_2_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getIdAssignment_2_2()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2__2__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:888:1: rule__TopLevelEntity__Group_2__3 : rule__TopLevelEntity__Group_2__3__Impl rule__TopLevelEntity__Group_2__4 ;
    public final void rule__TopLevelEntity__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:892:1: ( rule__TopLevelEntity__Group_2__3__Impl rule__TopLevelEntity__Group_2__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:893:2: rule__TopLevelEntity__Group_2__3__Impl rule__TopLevelEntity__Group_2__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__3__Impl_in_rule__TopLevelEntity__Group_2__31869);
            rule__TopLevelEntity__Group_2__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__4_in_rule__TopLevelEntity__Group_2__31872);
            rule__TopLevelEntity__Group_2__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2__3"


    // $ANTLR start "rule__TopLevelEntity__Group_2__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:900:1: rule__TopLevelEntity__Group_2__3__Impl : ( ( rule__TopLevelEntity__NameAssignment_2_3 )? ) ;
    public final void rule__TopLevelEntity__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:904:1: ( ( ( rule__TopLevelEntity__NameAssignment_2_3 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:905:1: ( ( rule__TopLevelEntity__NameAssignment_2_3 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:905:1: ( ( rule__TopLevelEntity__NameAssignment_2_3 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:906:1: ( rule__TopLevelEntity__NameAssignment_2_3 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getNameAssignment_2_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:907:1: ( rule__TopLevelEntity__NameAssignment_2_3 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_STRING) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:907:2: rule__TopLevelEntity__NameAssignment_2_3
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__NameAssignment_2_3_in_rule__TopLevelEntity__Group_2__3__Impl1899);
                    rule__TopLevelEntity__NameAssignment_2_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getNameAssignment_2_3()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2__3__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2__4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:917:1: rule__TopLevelEntity__Group_2__4 : rule__TopLevelEntity__Group_2__4__Impl ;
    public final void rule__TopLevelEntity__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:921:1: ( rule__TopLevelEntity__Group_2__4__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:922:2: rule__TopLevelEntity__Group_2__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2__4__Impl_in_rule__TopLevelEntity__Group_2__41930);
            rule__TopLevelEntity__Group_2__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2__4"


    // $ANTLR start "rule__TopLevelEntity__Group_2__4__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:928:1: rule__TopLevelEntity__Group_2__4__Impl : ( ( rule__TopLevelEntity__Alternatives_2_4 ) ) ;
    public final void rule__TopLevelEntity__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:932:1: ( ( ( rule__TopLevelEntity__Alternatives_2_4 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:933:1: ( ( rule__TopLevelEntity__Alternatives_2_4 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:933:1: ( ( rule__TopLevelEntity__Alternatives_2_4 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:934:1: ( rule__TopLevelEntity__Alternatives_2_4 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getAlternatives_2_4()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:935:1: ( rule__TopLevelEntity__Alternatives_2_4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:935:2: rule__TopLevelEntity__Alternatives_2_4
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Alternatives_2_4_in_rule__TopLevelEntity__Group_2__4__Impl1957);
            rule__TopLevelEntity__Alternatives_2_4();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getAlternatives_2_4()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2__4__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2_4_0__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:955:1: rule__TopLevelEntity__Group_2_4_0__0 : rule__TopLevelEntity__Group_2_4_0__0__Impl rule__TopLevelEntity__Group_2_4_0__1 ;
    public final void rule__TopLevelEntity__Group_2_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:959:1: ( rule__TopLevelEntity__Group_2_4_0__0__Impl rule__TopLevelEntity__Group_2_4_0__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:960:2: rule__TopLevelEntity__Group_2_4_0__0__Impl rule__TopLevelEntity__Group_2_4_0__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2_4_0__0__Impl_in_rule__TopLevelEntity__Group_2_4_0__01997);
            rule__TopLevelEntity__Group_2_4_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2_4_0__1_in_rule__TopLevelEntity__Group_2_4_0__02000);
            rule__TopLevelEntity__Group_2_4_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2_4_0__0"


    // $ANTLR start "rule__TopLevelEntity__Group_2_4_0__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:967:1: rule__TopLevelEntity__Group_2_4_0__0__Impl : ( '{' ) ;
    public final void rule__TopLevelEntity__Group_2_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:971:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:972:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:972:1: ( '{' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:973:1: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getLeftCurlyBracketKeyword_2_4_0_0()); 
            }
            match(input,16,FollowSets000.FOLLOW_16_in_rule__TopLevelEntity__Group_2_4_0__0__Impl2028); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getLeftCurlyBracketKeyword_2_4_0_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2_4_0__0__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2_4_0__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:986:1: rule__TopLevelEntity__Group_2_4_0__1 : rule__TopLevelEntity__Group_2_4_0__1__Impl rule__TopLevelEntity__Group_2_4_0__2 ;
    public final void rule__TopLevelEntity__Group_2_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:990:1: ( rule__TopLevelEntity__Group_2_4_0__1__Impl rule__TopLevelEntity__Group_2_4_0__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:991:2: rule__TopLevelEntity__Group_2_4_0__1__Impl rule__TopLevelEntity__Group_2_4_0__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2_4_0__1__Impl_in_rule__TopLevelEntity__Group_2_4_0__12059);
            rule__TopLevelEntity__Group_2_4_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2_4_0__2_in_rule__TopLevelEntity__Group_2_4_0__12062);
            rule__TopLevelEntity__Group_2_4_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2_4_0__1"


    // $ANTLR start "rule__TopLevelEntity__Group_2_4_0__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:998:1: rule__TopLevelEntity__Group_2_4_0__1__Impl : ( ( rule__TopLevelEntity__Alternatives_2_4_0_1 )* ) ;
    public final void rule__TopLevelEntity__Group_2_4_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1002:1: ( ( ( rule__TopLevelEntity__Alternatives_2_4_0_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1003:1: ( ( rule__TopLevelEntity__Alternatives_2_4_0_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1003:1: ( ( rule__TopLevelEntity__Alternatives_2_4_0_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1004:1: ( rule__TopLevelEntity__Alternatives_2_4_0_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getAlternatives_2_4_0_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1005:1: ( rule__TopLevelEntity__Alternatives_2_4_0_1 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_COMMENT_ANNOTATION||LA11_0==15||LA11_0==18||(LA11_0>=20 && LA11_0<=22)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1005:2: rule__TopLevelEntity__Alternatives_2_4_0_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Alternatives_2_4_0_1_in_rule__TopLevelEntity__Group_2_4_0__1__Impl2089);
            	    rule__TopLevelEntity__Alternatives_2_4_0_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getAlternatives_2_4_0_1()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2_4_0__1__Impl"


    // $ANTLR start "rule__TopLevelEntity__Group_2_4_0__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1015:1: rule__TopLevelEntity__Group_2_4_0__2 : rule__TopLevelEntity__Group_2_4_0__2__Impl ;
    public final void rule__TopLevelEntity__Group_2_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1019:1: ( rule__TopLevelEntity__Group_2_4_0__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1020:2: rule__TopLevelEntity__Group_2_4_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__Group_2_4_0__2__Impl_in_rule__TopLevelEntity__Group_2_4_0__22120);
            rule__TopLevelEntity__Group_2_4_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TopLevelEntity__Group_2_4_0__2"


    // $ANTLR start "rule__TopLevelEntity__Group_2_4_0__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1026:1: rule__TopLevelEntity__Group_2_4_0__2__Impl : ( '}' ) ;
    public final void rule__TopLevelEntity__Group_2_4_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1030:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1031:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1031:1: ( '}' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1032:1: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getRightCurlyBracketKeyword_2_4_0_2()); 
            }
            match(input,17,FollowSets000.FOLLOW_17_in_rule__TopLevelEntity__Group_2_4_0__2__Impl2148); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getRightCurlyBracketKeyword_2_4_0_2()); 
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
    // $ANTLR end "rule__TopLevelEntity__Group_2_4_0__2__Impl"


    // $ANTLR start "rule__Entity__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1051:1: rule__Entity__Group__0 : rule__Entity__Group__0__Impl rule__Entity__Group__1 ;
    public final void rule__Entity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1055:1: ( rule__Entity__Group__0__Impl rule__Entity__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1056:2: rule__Entity__Group__0__Impl rule__Entity__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__0__Impl_in_rule__Entity__Group__02185);
            rule__Entity__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__1_in_rule__Entity__Group__02188);
            rule__Entity__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__0"


    // $ANTLR start "rule__Entity__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1063:1: rule__Entity__Group__0__Impl : ( () ) ;
    public final void rule__Entity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1067:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1068:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1068:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1069:1: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getEntityAction_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1070:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1072:1: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getEntityAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__0__Impl"


    // $ANTLR start "rule__Entity__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1082:1: rule__Entity__Group__1 : rule__Entity__Group__1__Impl rule__Entity__Group__2 ;
    public final void rule__Entity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1086:1: ( rule__Entity__Group__1__Impl rule__Entity__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1087:2: rule__Entity__Group__1__Impl rule__Entity__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__1__Impl_in_rule__Entity__Group__12246);
            rule__Entity__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__2_in_rule__Entity__Group__12249);
            rule__Entity__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__1"


    // $ANTLR start "rule__Entity__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1094:1: rule__Entity__Group__1__Impl : ( ( rule__Entity__AnnotationsAssignment_1 )* ) ;
    public final void rule__Entity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1098:1: ( ( ( rule__Entity__AnnotationsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1099:1: ( ( rule__Entity__AnnotationsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1099:1: ( ( rule__Entity__AnnotationsAssignment_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1100:1: ( rule__Entity__AnnotationsAssignment_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getAnnotationsAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1101:1: ( rule__Entity__AnnotationsAssignment_1 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_COMMENT_ANNOTATION||LA12_0==22) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1101:2: rule__Entity__AnnotationsAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Entity__AnnotationsAssignment_1_in_rule__Entity__Group__1__Impl2276);
            	    rule__Entity__AnnotationsAssignment_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getAnnotationsAssignment_1()); 
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
    // $ANTLR end "rule__Entity__Group__1__Impl"


    // $ANTLR start "rule__Entity__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1111:1: rule__Entity__Group__2 : rule__Entity__Group__2__Impl rule__Entity__Group__3 ;
    public final void rule__Entity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1115:1: ( rule__Entity__Group__2__Impl rule__Entity__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1116:2: rule__Entity__Group__2__Impl rule__Entity__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__2__Impl_in_rule__Entity__Group__22307);
            rule__Entity__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__3_in_rule__Entity__Group__22310);
            rule__Entity__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__2"


    // $ANTLR start "rule__Entity__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1123:1: rule__Entity__Group__2__Impl : ( 'entity' ) ;
    public final void rule__Entity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1127:1: ( ( 'entity' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1128:1: ( 'entity' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1128:1: ( 'entity' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1129:1: 'entity'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getEntityKeyword_2()); 
            }
            match(input,15,FollowSets000.FOLLOW_15_in_rule__Entity__Group__2__Impl2338); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getEntityKeyword_2()); 
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
    // $ANTLR end "rule__Entity__Group__2__Impl"


    // $ANTLR start "rule__Entity__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1142:1: rule__Entity__Group__3 : rule__Entity__Group__3__Impl rule__Entity__Group__4 ;
    public final void rule__Entity__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1146:1: ( rule__Entity__Group__3__Impl rule__Entity__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1147:2: rule__Entity__Group__3__Impl rule__Entity__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__3__Impl_in_rule__Entity__Group__32369);
            rule__Entity__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__4_in_rule__Entity__Group__32372);
            rule__Entity__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__3"


    // $ANTLR start "rule__Entity__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1154:1: rule__Entity__Group__3__Impl : ( ( rule__Entity__IdAssignment_3 ) ) ;
    public final void rule__Entity__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1158:1: ( ( ( rule__Entity__IdAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1159:1: ( ( rule__Entity__IdAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1159:1: ( ( rule__Entity__IdAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1160:1: ( rule__Entity__IdAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getIdAssignment_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1161:1: ( rule__Entity__IdAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1161:2: rule__Entity__IdAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__IdAssignment_3_in_rule__Entity__Group__3__Impl2399);
            rule__Entity__IdAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getIdAssignment_3()); 
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
    // $ANTLR end "rule__Entity__Group__3__Impl"


    // $ANTLR start "rule__Entity__Group__4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1171:1: rule__Entity__Group__4 : rule__Entity__Group__4__Impl rule__Entity__Group__5 ;
    public final void rule__Entity__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1175:1: ( rule__Entity__Group__4__Impl rule__Entity__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1176:2: rule__Entity__Group__4__Impl rule__Entity__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__4__Impl_in_rule__Entity__Group__42429);
            rule__Entity__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__5_in_rule__Entity__Group__42432);
            rule__Entity__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__4"


    // $ANTLR start "rule__Entity__Group__4__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1183:1: rule__Entity__Group__4__Impl : ( ( rule__Entity__NameAssignment_4 )? ) ;
    public final void rule__Entity__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1187:1: ( ( ( rule__Entity__NameAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1188:1: ( ( rule__Entity__NameAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1188:1: ( ( rule__Entity__NameAssignment_4 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1189:1: ( rule__Entity__NameAssignment_4 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getNameAssignment_4()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1190:1: ( rule__Entity__NameAssignment_4 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1190:2: rule__Entity__NameAssignment_4
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Entity__NameAssignment_4_in_rule__Entity__Group__4__Impl2459);
                    rule__Entity__NameAssignment_4();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getNameAssignment_4()); 
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
    // $ANTLR end "rule__Entity__Group__4__Impl"


    // $ANTLR start "rule__Entity__Group__5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1200:1: rule__Entity__Group__5 : rule__Entity__Group__5__Impl ;
    public final void rule__Entity__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1204:1: ( rule__Entity__Group__5__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1205:2: rule__Entity__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group__5__Impl_in_rule__Entity__Group__52490);
            rule__Entity__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group__5"


    // $ANTLR start "rule__Entity__Group__5__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1211:1: rule__Entity__Group__5__Impl : ( ( rule__Entity__Alternatives_5 ) ) ;
    public final void rule__Entity__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1215:1: ( ( ( rule__Entity__Alternatives_5 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1216:1: ( ( rule__Entity__Alternatives_5 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1216:1: ( ( rule__Entity__Alternatives_5 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1217:1: ( rule__Entity__Alternatives_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getAlternatives_5()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1218:1: ( rule__Entity__Alternatives_5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1218:2: rule__Entity__Alternatives_5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Alternatives_5_in_rule__Entity__Group__5__Impl2517);
            rule__Entity__Alternatives_5();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getAlternatives_5()); 
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
    // $ANTLR end "rule__Entity__Group__5__Impl"


    // $ANTLR start "rule__Entity__Group_5_0__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1240:1: rule__Entity__Group_5_0__0 : rule__Entity__Group_5_0__0__Impl rule__Entity__Group_5_0__1 ;
    public final void rule__Entity__Group_5_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1244:1: ( rule__Entity__Group_5_0__0__Impl rule__Entity__Group_5_0__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1245:2: rule__Entity__Group_5_0__0__Impl rule__Entity__Group_5_0__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__0__Impl_in_rule__Entity__Group_5_0__02559);
            rule__Entity__Group_5_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__1_in_rule__Entity__Group_5_0__02562);
            rule__Entity__Group_5_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5_0__0"


    // $ANTLR start "rule__Entity__Group_5_0__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1252:1: rule__Entity__Group_5_0__0__Impl : ( '{' ) ;
    public final void rule__Entity__Group_5_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1256:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1257:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1257:1: ( '{' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1258:1: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_0_0()); 
            }
            match(input,16,FollowSets000.FOLLOW_16_in_rule__Entity__Group_5_0__0__Impl2590); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_0_0()); 
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
    // $ANTLR end "rule__Entity__Group_5_0__0__Impl"


    // $ANTLR start "rule__Entity__Group_5_0__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1271:1: rule__Entity__Group_5_0__1 : rule__Entity__Group_5_0__1__Impl rule__Entity__Group_5_0__2 ;
    public final void rule__Entity__Group_5_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1275:1: ( rule__Entity__Group_5_0__1__Impl rule__Entity__Group_5_0__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1276:2: rule__Entity__Group_5_0__1__Impl rule__Entity__Group_5_0__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__1__Impl_in_rule__Entity__Group_5_0__12621);
            rule__Entity__Group_5_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__2_in_rule__Entity__Group_5_0__12624);
            rule__Entity__Group_5_0__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5_0__1"


    // $ANTLR start "rule__Entity__Group_5_0__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1283:1: rule__Entity__Group_5_0__1__Impl : ( ( rule__Entity__Alternatives_5_0_1 )* ) ;
    public final void rule__Entity__Group_5_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1287:1: ( ( ( rule__Entity__Alternatives_5_0_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1288:1: ( ( rule__Entity__Alternatives_5_0_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1288:1: ( ( rule__Entity__Alternatives_5_0_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1289:1: ( rule__Entity__Alternatives_5_0_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getAlternatives_5_0_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1290:1: ( rule__Entity__Alternatives_5_0_1 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_COMMENT_ANNOTATION||LA14_0==15||LA14_0==18||(LA14_0>=20 && LA14_0<=22)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1290:2: rule__Entity__Alternatives_5_0_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Entity__Alternatives_5_0_1_in_rule__Entity__Group_5_0__1__Impl2651);
            	    rule__Entity__Alternatives_5_0_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getAlternatives_5_0_1()); 
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
    // $ANTLR end "rule__Entity__Group_5_0__1__Impl"


    // $ANTLR start "rule__Entity__Group_5_0__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1300:1: rule__Entity__Group_5_0__2 : rule__Entity__Group_5_0__2__Impl ;
    public final void rule__Entity__Group_5_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1304:1: ( rule__Entity__Group_5_0__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1305:2: rule__Entity__Group_5_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Entity__Group_5_0__2__Impl_in_rule__Entity__Group_5_0__22682);
            rule__Entity__Group_5_0__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Entity__Group_5_0__2"


    // $ANTLR start "rule__Entity__Group_5_0__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1311:1: rule__Entity__Group_5_0__2__Impl : ( '}' ) ;
    public final void rule__Entity__Group_5_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1315:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1316:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1316:1: ( '}' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1317:1: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_0_2()); 
            }
            match(input,17,FollowSets000.FOLLOW_17_in_rule__Entity__Group_5_0__2__Impl2710); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_0_2()); 
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
    // $ANTLR end "rule__Entity__Group_5_0__2__Impl"


    // $ANTLR start "rule__Link__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1336:1: rule__Link__Group__0 : rule__Link__Group__0__Impl rule__Link__Group__1 ;
    public final void rule__Link__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1340:1: ( rule__Link__Group__0__Impl rule__Link__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1341:2: rule__Link__Group__0__Impl rule__Link__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__0__Impl_in_rule__Link__Group__02747);
            rule__Link__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__1_in_rule__Link__Group__02750);
            rule__Link__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Link__Group__0"


    // $ANTLR start "rule__Link__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1348:1: rule__Link__Group__0__Impl : ( ( rule__Link__AnnotationsAssignment_0 )* ) ;
    public final void rule__Link__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1352:1: ( ( ( rule__Link__AnnotationsAssignment_0 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1353:1: ( ( rule__Link__AnnotationsAssignment_0 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1353:1: ( ( rule__Link__AnnotationsAssignment_0 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1354:1: ( rule__Link__AnnotationsAssignment_0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getAnnotationsAssignment_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1355:1: ( rule__Link__AnnotationsAssignment_0 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_COMMENT_ANNOTATION||LA15_0==22) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1355:2: rule__Link__AnnotationsAssignment_0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Link__AnnotationsAssignment_0_in_rule__Link__Group__0__Impl2777);
            	    rule__Link__AnnotationsAssignment_0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getAnnotationsAssignment_0()); 
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
    // $ANTLR end "rule__Link__Group__0__Impl"


    // $ANTLR start "rule__Link__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1365:1: rule__Link__Group__1 : rule__Link__Group__1__Impl rule__Link__Group__2 ;
    public final void rule__Link__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1369:1: ( rule__Link__Group__1__Impl rule__Link__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1370:2: rule__Link__Group__1__Impl rule__Link__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__1__Impl_in_rule__Link__Group__12808);
            rule__Link__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__2_in_rule__Link__Group__12811);
            rule__Link__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Link__Group__1"


    // $ANTLR start "rule__Link__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1377:1: rule__Link__Group__1__Impl : ( 'link' ) ;
    public final void rule__Link__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1381:1: ( ( 'link' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1382:1: ( 'link' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1382:1: ( 'link' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1383:1: 'link'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getLinkKeyword_1()); 
            }
            match(input,18,FollowSets000.FOLLOW_18_in_rule__Link__Group__1__Impl2839); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getLinkKeyword_1()); 
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
    // $ANTLR end "rule__Link__Group__1__Impl"


    // $ANTLR start "rule__Link__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1396:1: rule__Link__Group__2 : rule__Link__Group__2__Impl rule__Link__Group__3 ;
    public final void rule__Link__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1400:1: ( rule__Link__Group__2__Impl rule__Link__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1401:2: rule__Link__Group__2__Impl rule__Link__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__2__Impl_in_rule__Link__Group__22870);
            rule__Link__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__3_in_rule__Link__Group__22873);
            rule__Link__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Link__Group__2"


    // $ANTLR start "rule__Link__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1408:1: rule__Link__Group__2__Impl : ( ( rule__Link__NameAssignment_2 )? ) ;
    public final void rule__Link__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1412:1: ( ( ( rule__Link__NameAssignment_2 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1413:1: ( ( rule__Link__NameAssignment_2 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1413:1: ( ( rule__Link__NameAssignment_2 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1414:1: ( rule__Link__NameAssignment_2 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getNameAssignment_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1415:1: ( rule__Link__NameAssignment_2 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_STRING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1415:2: rule__Link__NameAssignment_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Link__NameAssignment_2_in_rule__Link__Group__2__Impl2900);
                    rule__Link__NameAssignment_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getNameAssignment_2()); 
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
    // $ANTLR end "rule__Link__Group__2__Impl"


    // $ANTLR start "rule__Link__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1425:1: rule__Link__Group__3 : rule__Link__Group__3__Impl rule__Link__Group__4 ;
    public final void rule__Link__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1429:1: ( rule__Link__Group__3__Impl rule__Link__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1430:2: rule__Link__Group__3__Impl rule__Link__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__3__Impl_in_rule__Link__Group__32931);
            rule__Link__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__4_in_rule__Link__Group__32934);
            rule__Link__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Link__Group__3"


    // $ANTLR start "rule__Link__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1437:1: rule__Link__Group__3__Impl : ( ( rule__Link__SourceAssignment_3 ) ) ;
    public final void rule__Link__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1441:1: ( ( ( rule__Link__SourceAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1442:1: ( ( rule__Link__SourceAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1442:1: ( ( rule__Link__SourceAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1443:1: ( rule__Link__SourceAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getSourceAssignment_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1444:1: ( rule__Link__SourceAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1444:2: rule__Link__SourceAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__SourceAssignment_3_in_rule__Link__Group__3__Impl2961);
            rule__Link__SourceAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getSourceAssignment_3()); 
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
    // $ANTLR end "rule__Link__Group__3__Impl"


    // $ANTLR start "rule__Link__Group__4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1454:1: rule__Link__Group__4 : rule__Link__Group__4__Impl rule__Link__Group__5 ;
    public final void rule__Link__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1458:1: ( rule__Link__Group__4__Impl rule__Link__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1459:2: rule__Link__Group__4__Impl rule__Link__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__4__Impl_in_rule__Link__Group__42991);
            rule__Link__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__5_in_rule__Link__Group__42994);
            rule__Link__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Link__Group__4"


    // $ANTLR start "rule__Link__Group__4__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1466:1: rule__Link__Group__4__Impl : ( 'to' ) ;
    public final void rule__Link__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1470:1: ( ( 'to' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1471:1: ( 'to' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1471:1: ( 'to' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1472:1: 'to'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getToKeyword_4()); 
            }
            match(input,19,FollowSets000.FOLLOW_19_in_rule__Link__Group__4__Impl3022); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getToKeyword_4()); 
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
    // $ANTLR end "rule__Link__Group__4__Impl"


    // $ANTLR start "rule__Link__Group__5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1485:1: rule__Link__Group__5 : rule__Link__Group__5__Impl rule__Link__Group__6 ;
    public final void rule__Link__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1489:1: ( rule__Link__Group__5__Impl rule__Link__Group__6 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1490:2: rule__Link__Group__5__Impl rule__Link__Group__6
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__5__Impl_in_rule__Link__Group__53053);
            rule__Link__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__6_in_rule__Link__Group__53056);
            rule__Link__Group__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Link__Group__5"


    // $ANTLR start "rule__Link__Group__5__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1497:1: rule__Link__Group__5__Impl : ( ( rule__Link__TargetAssignment_5 ) ) ;
    public final void rule__Link__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1501:1: ( ( ( rule__Link__TargetAssignment_5 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1502:1: ( ( rule__Link__TargetAssignment_5 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1502:1: ( ( rule__Link__TargetAssignment_5 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1503:1: ( rule__Link__TargetAssignment_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getTargetAssignment_5()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1504:1: ( rule__Link__TargetAssignment_5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1504:2: rule__Link__TargetAssignment_5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__TargetAssignment_5_in_rule__Link__Group__5__Impl3083);
            rule__Link__TargetAssignment_5();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getTargetAssignment_5()); 
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
    // $ANTLR end "rule__Link__Group__5__Impl"


    // $ANTLR start "rule__Link__Group__6"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1514:1: rule__Link__Group__6 : rule__Link__Group__6__Impl ;
    public final void rule__Link__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1518:1: ( rule__Link__Group__6__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1519:2: rule__Link__Group__6__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Link__Group__6__Impl_in_rule__Link__Group__63113);
            rule__Link__Group__6__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Link__Group__6"


    // $ANTLR start "rule__Link__Group__6__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1525:1: rule__Link__Group__6__Impl : ( ';' ) ;
    public final void rule__Link__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1529:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1530:1: ( ';' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1530:1: ( ';' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1531:1: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getSemicolonKeyword_6()); 
            }
            match(input,14,FollowSets000.FOLLOW_14_in_rule__Link__Group__6__Impl3141); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getSemicolonKeyword_6()); 
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
    // $ANTLR end "rule__Link__Group__6__Impl"


    // $ANTLR start "rule__Port__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1558:1: rule__Port__Group__0 : rule__Port__Group__0__Impl rule__Port__Group__1 ;
    public final void rule__Port__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1562:1: ( rule__Port__Group__0__Impl rule__Port__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1563:2: rule__Port__Group__0__Impl rule__Port__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__0__Impl_in_rule__Port__Group__03186);
            rule__Port__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__1_in_rule__Port__Group__03189);
            rule__Port__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Port__Group__0"


    // $ANTLR start "rule__Port__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1570:1: rule__Port__Group__0__Impl : ( () ) ;
    public final void rule__Port__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1574:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1575:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1575:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1576:1: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getPortAction_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1577:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1579:1: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getPortAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Port__Group__0__Impl"


    // $ANTLR start "rule__Port__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1589:1: rule__Port__Group__1 : rule__Port__Group__1__Impl rule__Port__Group__2 ;
    public final void rule__Port__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1593:1: ( rule__Port__Group__1__Impl rule__Port__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1594:2: rule__Port__Group__1__Impl rule__Port__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__1__Impl_in_rule__Port__Group__13247);
            rule__Port__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__2_in_rule__Port__Group__13250);
            rule__Port__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Port__Group__1"


    // $ANTLR start "rule__Port__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1601:1: rule__Port__Group__1__Impl : ( ( rule__Port__AnnotationsAssignment_1 )* ) ;
    public final void rule__Port__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1605:1: ( ( ( rule__Port__AnnotationsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1606:1: ( ( rule__Port__AnnotationsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1606:1: ( ( rule__Port__AnnotationsAssignment_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1607:1: ( rule__Port__AnnotationsAssignment_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getAnnotationsAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1608:1: ( rule__Port__AnnotationsAssignment_1 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_COMMENT_ANNOTATION||LA17_0==22) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1608:2: rule__Port__AnnotationsAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Port__AnnotationsAssignment_1_in_rule__Port__Group__1__Impl3277);
            	    rule__Port__AnnotationsAssignment_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getAnnotationsAssignment_1()); 
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
    // $ANTLR end "rule__Port__Group__1__Impl"


    // $ANTLR start "rule__Port__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1618:1: rule__Port__Group__2 : rule__Port__Group__2__Impl rule__Port__Group__3 ;
    public final void rule__Port__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1622:1: ( rule__Port__Group__2__Impl rule__Port__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1623:2: rule__Port__Group__2__Impl rule__Port__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__2__Impl_in_rule__Port__Group__23308);
            rule__Port__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__3_in_rule__Port__Group__23311);
            rule__Port__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Port__Group__2"


    // $ANTLR start "rule__Port__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1630:1: rule__Port__Group__2__Impl : ( 'port' ) ;
    public final void rule__Port__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1634:1: ( ( 'port' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1635:1: ( 'port' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1635:1: ( 'port' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1636:1: 'port'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getPortKeyword_2()); 
            }
            match(input,20,FollowSets000.FOLLOW_20_in_rule__Port__Group__2__Impl3339); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getPortKeyword_2()); 
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
    // $ANTLR end "rule__Port__Group__2__Impl"


    // $ANTLR start "rule__Port__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1649:1: rule__Port__Group__3 : rule__Port__Group__3__Impl rule__Port__Group__4 ;
    public final void rule__Port__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1653:1: ( rule__Port__Group__3__Impl rule__Port__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1654:2: rule__Port__Group__3__Impl rule__Port__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__3__Impl_in_rule__Port__Group__33370);
            rule__Port__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__4_in_rule__Port__Group__33373);
            rule__Port__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Port__Group__3"


    // $ANTLR start "rule__Port__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1661:1: rule__Port__Group__3__Impl : ( ( rule__Port__IdAssignment_3 ) ) ;
    public final void rule__Port__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1665:1: ( ( ( rule__Port__IdAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1666:1: ( ( rule__Port__IdAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1666:1: ( ( rule__Port__IdAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1667:1: ( rule__Port__IdAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getIdAssignment_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1668:1: ( rule__Port__IdAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1668:2: rule__Port__IdAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__IdAssignment_3_in_rule__Port__Group__3__Impl3400);
            rule__Port__IdAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getIdAssignment_3()); 
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
    // $ANTLR end "rule__Port__Group__3__Impl"


    // $ANTLR start "rule__Port__Group__4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1678:1: rule__Port__Group__4 : rule__Port__Group__4__Impl rule__Port__Group__5 ;
    public final void rule__Port__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1682:1: ( rule__Port__Group__4__Impl rule__Port__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1683:2: rule__Port__Group__4__Impl rule__Port__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__4__Impl_in_rule__Port__Group__43430);
            rule__Port__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__5_in_rule__Port__Group__43433);
            rule__Port__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Port__Group__4"


    // $ANTLR start "rule__Port__Group__4__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1690:1: rule__Port__Group__4__Impl : ( ( rule__Port__NameAssignment_4 )? ) ;
    public final void rule__Port__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1694:1: ( ( ( rule__Port__NameAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1695:1: ( ( rule__Port__NameAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1695:1: ( ( rule__Port__NameAssignment_4 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1696:1: ( rule__Port__NameAssignment_4 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getNameAssignment_4()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1697:1: ( rule__Port__NameAssignment_4 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_STRING) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1697:2: rule__Port__NameAssignment_4
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Port__NameAssignment_4_in_rule__Port__Group__4__Impl3460);
                    rule__Port__NameAssignment_4();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getNameAssignment_4()); 
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
    // $ANTLR end "rule__Port__Group__4__Impl"


    // $ANTLR start "rule__Port__Group__5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1707:1: rule__Port__Group__5 : rule__Port__Group__5__Impl ;
    public final void rule__Port__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1711:1: ( rule__Port__Group__5__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1712:2: rule__Port__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Port__Group__5__Impl_in_rule__Port__Group__53491);
            rule__Port__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Port__Group__5"


    // $ANTLR start "rule__Port__Group__5__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1718:1: rule__Port__Group__5__Impl : ( ';' ) ;
    public final void rule__Port__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1722:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1723:1: ( ';' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1723:1: ( ';' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1724:1: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getSemicolonKeyword_5()); 
            }
            match(input,14,FollowSets000.FOLLOW_14_in_rule__Port__Group__5__Impl3519); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getSemicolonKeyword_5()); 
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
    // $ANTLR end "rule__Port__Group__5__Impl"


    // $ANTLR start "rule__Relation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1749:1: rule__Relation__Group__0 : rule__Relation__Group__0__Impl rule__Relation__Group__1 ;
    public final void rule__Relation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1753:1: ( rule__Relation__Group__0__Impl rule__Relation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1754:2: rule__Relation__Group__0__Impl rule__Relation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__0__Impl_in_rule__Relation__Group__03562);
            rule__Relation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__1_in_rule__Relation__Group__03565);
            rule__Relation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__0"


    // $ANTLR start "rule__Relation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1761:1: rule__Relation__Group__0__Impl : ( () ) ;
    public final void rule__Relation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1765:1: ( ( () ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1766:1: ( () )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1766:1: ( () )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1767:1: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getRelationAction_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1768:1: ()
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1770:1: 
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getRelationAction_0()); 
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__0__Impl"


    // $ANTLR start "rule__Relation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1780:1: rule__Relation__Group__1 : rule__Relation__Group__1__Impl rule__Relation__Group__2 ;
    public final void rule__Relation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1784:1: ( rule__Relation__Group__1__Impl rule__Relation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1785:2: rule__Relation__Group__1__Impl rule__Relation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__1__Impl_in_rule__Relation__Group__13623);
            rule__Relation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__2_in_rule__Relation__Group__13626);
            rule__Relation__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__1"


    // $ANTLR start "rule__Relation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1792:1: rule__Relation__Group__1__Impl : ( ( rule__Relation__AnnotationsAssignment_1 )* ) ;
    public final void rule__Relation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1796:1: ( ( ( rule__Relation__AnnotationsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1797:1: ( ( rule__Relation__AnnotationsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1797:1: ( ( rule__Relation__AnnotationsAssignment_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1798:1: ( rule__Relation__AnnotationsAssignment_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getAnnotationsAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1799:1: ( rule__Relation__AnnotationsAssignment_1 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_COMMENT_ANNOTATION||LA19_0==22) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1799:2: rule__Relation__AnnotationsAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__Relation__AnnotationsAssignment_1_in_rule__Relation__Group__1__Impl3653);
            	    rule__Relation__AnnotationsAssignment_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getAnnotationsAssignment_1()); 
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
    // $ANTLR end "rule__Relation__Group__1__Impl"


    // $ANTLR start "rule__Relation__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1809:1: rule__Relation__Group__2 : rule__Relation__Group__2__Impl rule__Relation__Group__3 ;
    public final void rule__Relation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1813:1: ( rule__Relation__Group__2__Impl rule__Relation__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1814:2: rule__Relation__Group__2__Impl rule__Relation__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__2__Impl_in_rule__Relation__Group__23684);
            rule__Relation__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__3_in_rule__Relation__Group__23687);
            rule__Relation__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__2"


    // $ANTLR start "rule__Relation__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1821:1: rule__Relation__Group__2__Impl : ( 'relation' ) ;
    public final void rule__Relation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1825:1: ( ( 'relation' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1826:1: ( 'relation' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1826:1: ( 'relation' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1827:1: 'relation'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getRelationKeyword_2()); 
            }
            match(input,21,FollowSets000.FOLLOW_21_in_rule__Relation__Group__2__Impl3715); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getRelationKeyword_2()); 
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
    // $ANTLR end "rule__Relation__Group__2__Impl"


    // $ANTLR start "rule__Relation__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1840:1: rule__Relation__Group__3 : rule__Relation__Group__3__Impl rule__Relation__Group__4 ;
    public final void rule__Relation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1844:1: ( rule__Relation__Group__3__Impl rule__Relation__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1845:2: rule__Relation__Group__3__Impl rule__Relation__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__3__Impl_in_rule__Relation__Group__33746);
            rule__Relation__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__4_in_rule__Relation__Group__33749);
            rule__Relation__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__3"


    // $ANTLR start "rule__Relation__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1852:1: rule__Relation__Group__3__Impl : ( ( rule__Relation__IdAssignment_3 ) ) ;
    public final void rule__Relation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1856:1: ( ( ( rule__Relation__IdAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1857:1: ( ( rule__Relation__IdAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1857:1: ( ( rule__Relation__IdAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1858:1: ( rule__Relation__IdAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getIdAssignment_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1859:1: ( rule__Relation__IdAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1859:2: rule__Relation__IdAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__IdAssignment_3_in_rule__Relation__Group__3__Impl3776);
            rule__Relation__IdAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getIdAssignment_3()); 
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
    // $ANTLR end "rule__Relation__Group__3__Impl"


    // $ANTLR start "rule__Relation__Group__4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1869:1: rule__Relation__Group__4 : rule__Relation__Group__4__Impl rule__Relation__Group__5 ;
    public final void rule__Relation__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1873:1: ( rule__Relation__Group__4__Impl rule__Relation__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1874:2: rule__Relation__Group__4__Impl rule__Relation__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__4__Impl_in_rule__Relation__Group__43806);
            rule__Relation__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__5_in_rule__Relation__Group__43809);
            rule__Relation__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__4"


    // $ANTLR start "rule__Relation__Group__4__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1881:1: rule__Relation__Group__4__Impl : ( ( rule__Relation__NameAssignment_4 )? ) ;
    public final void rule__Relation__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1885:1: ( ( ( rule__Relation__NameAssignment_4 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1886:1: ( ( rule__Relation__NameAssignment_4 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1886:1: ( ( rule__Relation__NameAssignment_4 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1887:1: ( rule__Relation__NameAssignment_4 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getNameAssignment_4()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1888:1: ( rule__Relation__NameAssignment_4 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_STRING) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1888:2: rule__Relation__NameAssignment_4
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__Relation__NameAssignment_4_in_rule__Relation__Group__4__Impl3836);
                    rule__Relation__NameAssignment_4();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getNameAssignment_4()); 
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
    // $ANTLR end "rule__Relation__Group__4__Impl"


    // $ANTLR start "rule__Relation__Group__5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1898:1: rule__Relation__Group__5 : rule__Relation__Group__5__Impl ;
    public final void rule__Relation__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1902:1: ( rule__Relation__Group__5__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1903:2: rule__Relation__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__Relation__Group__5__Impl_in_rule__Relation__Group__53867);
            rule__Relation__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__5"


    // $ANTLR start "rule__Relation__Group__5__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1909:1: rule__Relation__Group__5__Impl : ( ';' ) ;
    public final void rule__Relation__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1913:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1914:1: ( ';' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1914:1: ( ';' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1915:1: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getSemicolonKeyword_5()); 
            }
            match(input,14,FollowSets000.FOLLOW_14_in_rule__Relation__Group__5__Impl3895); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getSemicolonKeyword_5()); 
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
    // $ANTLR end "rule__Relation__Group__5__Impl"


    // $ANTLR start "rule__TagAnnotation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1940:1: rule__TagAnnotation__Group__0 : rule__TagAnnotation__Group__0__Impl rule__TagAnnotation__Group__1 ;
    public final void rule__TagAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1944:1: ( rule__TagAnnotation__Group__0__Impl rule__TagAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1945:2: rule__TagAnnotation__Group__0__Impl rule__TagAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__0__Impl_in_rule__TagAnnotation__Group__03938);
            rule__TagAnnotation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__1_in_rule__TagAnnotation__Group__03941);
            rule__TagAnnotation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TagAnnotation__Group__0"


    // $ANTLR start "rule__TagAnnotation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1952:1: rule__TagAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__TagAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1956:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1957:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1957:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1958:1: '@'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0()); 
            }
            match(input,22,FollowSets000.FOLLOW_22_in_rule__TagAnnotation__Group__0__Impl3969); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0()); 
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
    // $ANTLR end "rule__TagAnnotation__Group__0__Impl"


    // $ANTLR start "rule__TagAnnotation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1971:1: rule__TagAnnotation__Group__1 : rule__TagAnnotation__Group__1__Impl rule__TagAnnotation__Group__2 ;
    public final void rule__TagAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1975:1: ( rule__TagAnnotation__Group__1__Impl rule__TagAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1976:2: rule__TagAnnotation__Group__1__Impl rule__TagAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__1__Impl_in_rule__TagAnnotation__Group__14000);
            rule__TagAnnotation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__2_in_rule__TagAnnotation__Group__14003);
            rule__TagAnnotation__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TagAnnotation__Group__1"


    // $ANTLR start "rule__TagAnnotation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1983:1: rule__TagAnnotation__Group__1__Impl : ( ( rule__TagAnnotation__NameAssignment_1 ) ) ;
    public final void rule__TagAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1987:1: ( ( ( rule__TagAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1988:1: ( ( rule__TagAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1988:1: ( ( rule__TagAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1989:1: ( rule__TagAnnotation__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getNameAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1990:1: ( rule__TagAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:1990:2: rule__TagAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__NameAssignment_1_in_rule__TagAnnotation__Group__1__Impl4030);
            rule__TagAnnotation__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getNameAssignment_1()); 
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
    // $ANTLR end "rule__TagAnnotation__Group__1__Impl"


    // $ANTLR start "rule__TagAnnotation__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2000:1: rule__TagAnnotation__Group__2 : rule__TagAnnotation__Group__2__Impl ;
    public final void rule__TagAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2004:1: ( rule__TagAnnotation__Group__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2005:2: rule__TagAnnotation__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group__2__Impl_in_rule__TagAnnotation__Group__24060);
            rule__TagAnnotation__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TagAnnotation__Group__2"


    // $ANTLR start "rule__TagAnnotation__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2011:1: rule__TagAnnotation__Group__2__Impl : ( ( rule__TagAnnotation__Group_2__0 )? ) ;
    public final void rule__TagAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2015:1: ( ( ( rule__TagAnnotation__Group_2__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2016:1: ( ( rule__TagAnnotation__Group_2__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2016:1: ( ( rule__TagAnnotation__Group_2__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2017:1: ( rule__TagAnnotation__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getGroup_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2018:1: ( rule__TagAnnotation__Group_2__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==23) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2018:2: rule__TagAnnotation__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group_2__0_in_rule__TagAnnotation__Group__2__Impl4087);
                    rule__TagAnnotation__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getGroup_2()); 
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
    // $ANTLR end "rule__TagAnnotation__Group__2__Impl"


    // $ANTLR start "rule__TagAnnotation__Group_2__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2034:1: rule__TagAnnotation__Group_2__0 : rule__TagAnnotation__Group_2__0__Impl rule__TagAnnotation__Group_2__1 ;
    public final void rule__TagAnnotation__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2038:1: ( rule__TagAnnotation__Group_2__0__Impl rule__TagAnnotation__Group_2__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2039:2: rule__TagAnnotation__Group_2__0__Impl rule__TagAnnotation__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group_2__0__Impl_in_rule__TagAnnotation__Group_2__04124);
            rule__TagAnnotation__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group_2__1_in_rule__TagAnnotation__Group_2__04127);
            rule__TagAnnotation__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TagAnnotation__Group_2__0"


    // $ANTLR start "rule__TagAnnotation__Group_2__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2046:1: rule__TagAnnotation__Group_2__0__Impl : ( '(' ) ;
    public final void rule__TagAnnotation__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2050:1: ( ( '(' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2051:1: ( '(' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2051:1: ( '(' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2052:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getLeftParenthesisKeyword_2_0()); 
            }
            match(input,23,FollowSets000.FOLLOW_23_in_rule__TagAnnotation__Group_2__0__Impl4155); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getLeftParenthesisKeyword_2_0()); 
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
    // $ANTLR end "rule__TagAnnotation__Group_2__0__Impl"


    // $ANTLR start "rule__TagAnnotation__Group_2__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2065:1: rule__TagAnnotation__Group_2__1 : rule__TagAnnotation__Group_2__1__Impl rule__TagAnnotation__Group_2__2 ;
    public final void rule__TagAnnotation__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2069:1: ( rule__TagAnnotation__Group_2__1__Impl rule__TagAnnotation__Group_2__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2070:2: rule__TagAnnotation__Group_2__1__Impl rule__TagAnnotation__Group_2__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group_2__1__Impl_in_rule__TagAnnotation__Group_2__14186);
            rule__TagAnnotation__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group_2__2_in_rule__TagAnnotation__Group_2__14189);
            rule__TagAnnotation__Group_2__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TagAnnotation__Group_2__1"


    // $ANTLR start "rule__TagAnnotation__Group_2__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2077:1: rule__TagAnnotation__Group_2__1__Impl : ( ( rule__TagAnnotation__AnnotationsAssignment_2_1 )* ) ;
    public final void rule__TagAnnotation__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2081:1: ( ( ( rule__TagAnnotation__AnnotationsAssignment_2_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2082:1: ( ( rule__TagAnnotation__AnnotationsAssignment_2_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2082:1: ( ( rule__TagAnnotation__AnnotationsAssignment_2_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2083:1: ( rule__TagAnnotation__AnnotationsAssignment_2_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getAnnotationsAssignment_2_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2084:1: ( rule__TagAnnotation__AnnotationsAssignment_2_1 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_COMMENT_ANNOTATION||LA22_0==22) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2084:2: rule__TagAnnotation__AnnotationsAssignment_2_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__AnnotationsAssignment_2_1_in_rule__TagAnnotation__Group_2__1__Impl4216);
            	    rule__TagAnnotation__AnnotationsAssignment_2_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getAnnotationsAssignment_2_1()); 
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
    // $ANTLR end "rule__TagAnnotation__Group_2__1__Impl"


    // $ANTLR start "rule__TagAnnotation__Group_2__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2094:1: rule__TagAnnotation__Group_2__2 : rule__TagAnnotation__Group_2__2__Impl ;
    public final void rule__TagAnnotation__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2098:1: ( rule__TagAnnotation__Group_2__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2099:2: rule__TagAnnotation__Group_2__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TagAnnotation__Group_2__2__Impl_in_rule__TagAnnotation__Group_2__24247);
            rule__TagAnnotation__Group_2__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TagAnnotation__Group_2__2"


    // $ANTLR start "rule__TagAnnotation__Group_2__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2105:1: rule__TagAnnotation__Group_2__2__Impl : ( ')' ) ;
    public final void rule__TagAnnotation__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2109:1: ( ( ')' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2110:1: ( ')' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2110:1: ( ')' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2111:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getRightParenthesisKeyword_2_2()); 
            }
            match(input,24,FollowSets000.FOLLOW_24_in_rule__TagAnnotation__Group_2__2__Impl4275); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getRightParenthesisKeyword_2_2()); 
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
    // $ANTLR end "rule__TagAnnotation__Group_2__2__Impl"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2130:1: rule__KeyStringValueAnnotation__Group__0 : rule__KeyStringValueAnnotation__Group__0__Impl rule__KeyStringValueAnnotation__Group__1 ;
    public final void rule__KeyStringValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2134:1: ( rule__KeyStringValueAnnotation__Group__0__Impl rule__KeyStringValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2135:2: rule__KeyStringValueAnnotation__Group__0__Impl rule__KeyStringValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__0__Impl_in_rule__KeyStringValueAnnotation__Group__04312);
            rule__KeyStringValueAnnotation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__1_in_rule__KeyStringValueAnnotation__Group__04315);
            rule__KeyStringValueAnnotation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__0"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2142:1: rule__KeyStringValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyStringValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2146:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2147:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2147:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2148:1: '@'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0()); 
            }
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyStringValueAnnotation__Group__0__Impl4343); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__0__Impl"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2161:1: rule__KeyStringValueAnnotation__Group__1 : rule__KeyStringValueAnnotation__Group__1__Impl rule__KeyStringValueAnnotation__Group__2 ;
    public final void rule__KeyStringValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2165:1: ( rule__KeyStringValueAnnotation__Group__1__Impl rule__KeyStringValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2166:2: rule__KeyStringValueAnnotation__Group__1__Impl rule__KeyStringValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__1__Impl_in_rule__KeyStringValueAnnotation__Group__14374);
            rule__KeyStringValueAnnotation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__2_in_rule__KeyStringValueAnnotation__Group__14377);
            rule__KeyStringValueAnnotation__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__1"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2173:1: rule__KeyStringValueAnnotation__Group__1__Impl : ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyStringValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2177:1: ( ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2178:1: ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2178:1: ( ( rule__KeyStringValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2179:1: ( rule__KeyStringValueAnnotation__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getNameAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2180:1: ( rule__KeyStringValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2180:2: rule__KeyStringValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__NameAssignment_1_in_rule__KeyStringValueAnnotation__Group__1__Impl4404);
            rule__KeyStringValueAnnotation__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getNameAssignment_1()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__1__Impl"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2190:1: rule__KeyStringValueAnnotation__Group__2 : rule__KeyStringValueAnnotation__Group__2__Impl rule__KeyStringValueAnnotation__Group__3 ;
    public final void rule__KeyStringValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2194:1: ( rule__KeyStringValueAnnotation__Group__2__Impl rule__KeyStringValueAnnotation__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2195:2: rule__KeyStringValueAnnotation__Group__2__Impl rule__KeyStringValueAnnotation__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__2__Impl_in_rule__KeyStringValueAnnotation__Group__24434);
            rule__KeyStringValueAnnotation__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__3_in_rule__KeyStringValueAnnotation__Group__24437);
            rule__KeyStringValueAnnotation__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__2"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2202:1: rule__KeyStringValueAnnotation__Group__2__Impl : ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyStringValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2206:1: ( ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2207:1: ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2207:1: ( ( rule__KeyStringValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2208:1: ( rule__KeyStringValueAnnotation__ValueAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getValueAssignment_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2209:1: ( rule__KeyStringValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2209:2: rule__KeyStringValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__ValueAssignment_2_in_rule__KeyStringValueAnnotation__Group__2__Impl4464);
            rule__KeyStringValueAnnotation__ValueAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getValueAssignment_2()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__2__Impl"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2219:1: rule__KeyStringValueAnnotation__Group__3 : rule__KeyStringValueAnnotation__Group__3__Impl ;
    public final void rule__KeyStringValueAnnotation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2223:1: ( rule__KeyStringValueAnnotation__Group__3__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2224:2: rule__KeyStringValueAnnotation__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group__3__Impl_in_rule__KeyStringValueAnnotation__Group__34494);
            rule__KeyStringValueAnnotation__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__3"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2230:1: rule__KeyStringValueAnnotation__Group__3__Impl : ( ( rule__KeyStringValueAnnotation__Group_3__0 )? ) ;
    public final void rule__KeyStringValueAnnotation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2234:1: ( ( ( rule__KeyStringValueAnnotation__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2235:1: ( ( rule__KeyStringValueAnnotation__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2235:1: ( ( rule__KeyStringValueAnnotation__Group_3__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2236:1: ( rule__KeyStringValueAnnotation__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getGroup_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2237:1: ( rule__KeyStringValueAnnotation__Group_3__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==23) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2237:2: rule__KeyStringValueAnnotation__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group_3__0_in_rule__KeyStringValueAnnotation__Group__3__Impl4521);
                    rule__KeyStringValueAnnotation__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getGroup_3()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__Group__3__Impl"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group_3__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2255:1: rule__KeyStringValueAnnotation__Group_3__0 : rule__KeyStringValueAnnotation__Group_3__0__Impl rule__KeyStringValueAnnotation__Group_3__1 ;
    public final void rule__KeyStringValueAnnotation__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2259:1: ( rule__KeyStringValueAnnotation__Group_3__0__Impl rule__KeyStringValueAnnotation__Group_3__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2260:2: rule__KeyStringValueAnnotation__Group_3__0__Impl rule__KeyStringValueAnnotation__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group_3__0__Impl_in_rule__KeyStringValueAnnotation__Group_3__04560);
            rule__KeyStringValueAnnotation__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group_3__1_in_rule__KeyStringValueAnnotation__Group_3__04563);
            rule__KeyStringValueAnnotation__Group_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyStringValueAnnotation__Group_3__0"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group_3__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2267:1: rule__KeyStringValueAnnotation__Group_3__0__Impl : ( '(' ) ;
    public final void rule__KeyStringValueAnnotation__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2271:1: ( ( '(' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2272:1: ( '(' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2272:1: ( '(' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2273:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
            }
            match(input,23,FollowSets000.FOLLOW_23_in_rule__KeyStringValueAnnotation__Group_3__0__Impl4591); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__Group_3__0__Impl"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group_3__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2286:1: rule__KeyStringValueAnnotation__Group_3__1 : rule__KeyStringValueAnnotation__Group_3__1__Impl rule__KeyStringValueAnnotation__Group_3__2 ;
    public final void rule__KeyStringValueAnnotation__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2290:1: ( rule__KeyStringValueAnnotation__Group_3__1__Impl rule__KeyStringValueAnnotation__Group_3__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2291:2: rule__KeyStringValueAnnotation__Group_3__1__Impl rule__KeyStringValueAnnotation__Group_3__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group_3__1__Impl_in_rule__KeyStringValueAnnotation__Group_3__14622);
            rule__KeyStringValueAnnotation__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group_3__2_in_rule__KeyStringValueAnnotation__Group_3__14625);
            rule__KeyStringValueAnnotation__Group_3__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyStringValueAnnotation__Group_3__1"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group_3__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2298:1: rule__KeyStringValueAnnotation__Group_3__1__Impl : ( ( rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1 )* ) ;
    public final void rule__KeyStringValueAnnotation__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2302:1: ( ( ( rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2303:1: ( ( rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2303:1: ( ( rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2304:1: ( rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2305:1: ( rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_COMMENT_ANNOTATION||LA24_0==22) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2305:2: rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyStringValueAnnotation__Group_3__1__Impl4652);
            	    rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__Group_3__1__Impl"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group_3__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2315:1: rule__KeyStringValueAnnotation__Group_3__2 : rule__KeyStringValueAnnotation__Group_3__2__Impl ;
    public final void rule__KeyStringValueAnnotation__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2319:1: ( rule__KeyStringValueAnnotation__Group_3__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2320:2: rule__KeyStringValueAnnotation__Group_3__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyStringValueAnnotation__Group_3__2__Impl_in_rule__KeyStringValueAnnotation__Group_3__24683);
            rule__KeyStringValueAnnotation__Group_3__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyStringValueAnnotation__Group_3__2"


    // $ANTLR start "rule__KeyStringValueAnnotation__Group_3__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2326:1: rule__KeyStringValueAnnotation__Group_3__2__Impl : ( ')' ) ;
    public final void rule__KeyStringValueAnnotation__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2330:1: ( ( ')' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2331:1: ( ')' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2331:1: ( ')' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2332:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
            }
            match(input,24,FollowSets000.FOLLOW_24_in_rule__KeyStringValueAnnotation__Group_3__2__Impl4711); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__Group_3__2__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2351:1: rule__TypedKeyStringValueAnnotation__Group__0 : rule__TypedKeyStringValueAnnotation__Group__0__Impl rule__TypedKeyStringValueAnnotation__Group__1 ;
    public final void rule__TypedKeyStringValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2355:1: ( rule__TypedKeyStringValueAnnotation__Group__0__Impl rule__TypedKeyStringValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2356:2: rule__TypedKeyStringValueAnnotation__Group__0__Impl rule__TypedKeyStringValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__0__Impl_in_rule__TypedKeyStringValueAnnotation__Group__04748);
            rule__TypedKeyStringValueAnnotation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__1_in_rule__TypedKeyStringValueAnnotation__Group__04751);
            rule__TypedKeyStringValueAnnotation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__0"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2363:1: rule__TypedKeyStringValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2367:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2368:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2368:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2369:1: '@'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getCommercialAtKeyword_0()); 
            }
            match(input,22,FollowSets000.FOLLOW_22_in_rule__TypedKeyStringValueAnnotation__Group__0__Impl4779); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getCommercialAtKeyword_0()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__0__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2382:1: rule__TypedKeyStringValueAnnotation__Group__1 : rule__TypedKeyStringValueAnnotation__Group__1__Impl rule__TypedKeyStringValueAnnotation__Group__2 ;
    public final void rule__TypedKeyStringValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2386:1: ( rule__TypedKeyStringValueAnnotation__Group__1__Impl rule__TypedKeyStringValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2387:2: rule__TypedKeyStringValueAnnotation__Group__1__Impl rule__TypedKeyStringValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__1__Impl_in_rule__TypedKeyStringValueAnnotation__Group__14810);
            rule__TypedKeyStringValueAnnotation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__2_in_rule__TypedKeyStringValueAnnotation__Group__14813);
            rule__TypedKeyStringValueAnnotation__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__1"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2394:1: rule__TypedKeyStringValueAnnotation__Group__1__Impl : ( ( rule__TypedKeyStringValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2398:1: ( ( ( rule__TypedKeyStringValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2399:1: ( ( rule__TypedKeyStringValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2399:1: ( ( rule__TypedKeyStringValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2400:1: ( rule__TypedKeyStringValueAnnotation__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getNameAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2401:1: ( rule__TypedKeyStringValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2401:2: rule__TypedKeyStringValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__NameAssignment_1_in_rule__TypedKeyStringValueAnnotation__Group__1__Impl4840);
            rule__TypedKeyStringValueAnnotation__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getNameAssignment_1()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__1__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2411:1: rule__TypedKeyStringValueAnnotation__Group__2 : rule__TypedKeyStringValueAnnotation__Group__2__Impl rule__TypedKeyStringValueAnnotation__Group__3 ;
    public final void rule__TypedKeyStringValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2415:1: ( rule__TypedKeyStringValueAnnotation__Group__2__Impl rule__TypedKeyStringValueAnnotation__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2416:2: rule__TypedKeyStringValueAnnotation__Group__2__Impl rule__TypedKeyStringValueAnnotation__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__2__Impl_in_rule__TypedKeyStringValueAnnotation__Group__24870);
            rule__TypedKeyStringValueAnnotation__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__3_in_rule__TypedKeyStringValueAnnotation__Group__24873);
            rule__TypedKeyStringValueAnnotation__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__2"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2423:1: rule__TypedKeyStringValueAnnotation__Group__2__Impl : ( '[' ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2427:1: ( ( '[' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2428:1: ( '[' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2428:1: ( '[' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2429:1: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftSquareBracketKeyword_2()); 
            }
            match(input,25,FollowSets000.FOLLOW_25_in_rule__TypedKeyStringValueAnnotation__Group__2__Impl4901); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftSquareBracketKeyword_2()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__2__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2442:1: rule__TypedKeyStringValueAnnotation__Group__3 : rule__TypedKeyStringValueAnnotation__Group__3__Impl rule__TypedKeyStringValueAnnotation__Group__4 ;
    public final void rule__TypedKeyStringValueAnnotation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2446:1: ( rule__TypedKeyStringValueAnnotation__Group__3__Impl rule__TypedKeyStringValueAnnotation__Group__4 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2447:2: rule__TypedKeyStringValueAnnotation__Group__3__Impl rule__TypedKeyStringValueAnnotation__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__3__Impl_in_rule__TypedKeyStringValueAnnotation__Group__34932);
            rule__TypedKeyStringValueAnnotation__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__4_in_rule__TypedKeyStringValueAnnotation__Group__34935);
            rule__TypedKeyStringValueAnnotation__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__3"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2454:1: rule__TypedKeyStringValueAnnotation__Group__3__Impl : ( ( rule__TypedKeyStringValueAnnotation__TypeAssignment_3 ) ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2458:1: ( ( ( rule__TypedKeyStringValueAnnotation__TypeAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2459:1: ( ( rule__TypedKeyStringValueAnnotation__TypeAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2459:1: ( ( rule__TypedKeyStringValueAnnotation__TypeAssignment_3 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2460:1: ( rule__TypedKeyStringValueAnnotation__TypeAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getTypeAssignment_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2461:1: ( rule__TypedKeyStringValueAnnotation__TypeAssignment_3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2461:2: rule__TypedKeyStringValueAnnotation__TypeAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__TypeAssignment_3_in_rule__TypedKeyStringValueAnnotation__Group__3__Impl4962);
            rule__TypedKeyStringValueAnnotation__TypeAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getTypeAssignment_3()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__3__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2471:1: rule__TypedKeyStringValueAnnotation__Group__4 : rule__TypedKeyStringValueAnnotation__Group__4__Impl rule__TypedKeyStringValueAnnotation__Group__5 ;
    public final void rule__TypedKeyStringValueAnnotation__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2475:1: ( rule__TypedKeyStringValueAnnotation__Group__4__Impl rule__TypedKeyStringValueAnnotation__Group__5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2476:2: rule__TypedKeyStringValueAnnotation__Group__4__Impl rule__TypedKeyStringValueAnnotation__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__4__Impl_in_rule__TypedKeyStringValueAnnotation__Group__44992);
            rule__TypedKeyStringValueAnnotation__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__5_in_rule__TypedKeyStringValueAnnotation__Group__44995);
            rule__TypedKeyStringValueAnnotation__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__4"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__4__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2483:1: rule__TypedKeyStringValueAnnotation__Group__4__Impl : ( ']' ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2487:1: ( ( ']' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2488:1: ( ']' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2488:1: ( ']' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2489:1: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightSquareBracketKeyword_4()); 
            }
            match(input,26,FollowSets000.FOLLOW_26_in_rule__TypedKeyStringValueAnnotation__Group__4__Impl5023); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightSquareBracketKeyword_4()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__4__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2502:1: rule__TypedKeyStringValueAnnotation__Group__5 : rule__TypedKeyStringValueAnnotation__Group__5__Impl rule__TypedKeyStringValueAnnotation__Group__6 ;
    public final void rule__TypedKeyStringValueAnnotation__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2506:1: ( rule__TypedKeyStringValueAnnotation__Group__5__Impl rule__TypedKeyStringValueAnnotation__Group__6 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2507:2: rule__TypedKeyStringValueAnnotation__Group__5__Impl rule__TypedKeyStringValueAnnotation__Group__6
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__5__Impl_in_rule__TypedKeyStringValueAnnotation__Group__55054);
            rule__TypedKeyStringValueAnnotation__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__6_in_rule__TypedKeyStringValueAnnotation__Group__55057);
            rule__TypedKeyStringValueAnnotation__Group__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__5"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__5__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2514:1: rule__TypedKeyStringValueAnnotation__Group__5__Impl : ( ( rule__TypedKeyStringValueAnnotation__ValueAssignment_5 ) ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2518:1: ( ( ( rule__TypedKeyStringValueAnnotation__ValueAssignment_5 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2519:1: ( ( rule__TypedKeyStringValueAnnotation__ValueAssignment_5 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2519:1: ( ( rule__TypedKeyStringValueAnnotation__ValueAssignment_5 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2520:1: ( rule__TypedKeyStringValueAnnotation__ValueAssignment_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getValueAssignment_5()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2521:1: ( rule__TypedKeyStringValueAnnotation__ValueAssignment_5 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2521:2: rule__TypedKeyStringValueAnnotation__ValueAssignment_5
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__ValueAssignment_5_in_rule__TypedKeyStringValueAnnotation__Group__5__Impl5084);
            rule__TypedKeyStringValueAnnotation__ValueAssignment_5();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getValueAssignment_5()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__5__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__6"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2531:1: rule__TypedKeyStringValueAnnotation__Group__6 : rule__TypedKeyStringValueAnnotation__Group__6__Impl ;
    public final void rule__TypedKeyStringValueAnnotation__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2535:1: ( rule__TypedKeyStringValueAnnotation__Group__6__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2536:2: rule__TypedKeyStringValueAnnotation__Group__6__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group__6__Impl_in_rule__TypedKeyStringValueAnnotation__Group__65114);
            rule__TypedKeyStringValueAnnotation__Group__6__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__6"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group__6__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2542:1: rule__TypedKeyStringValueAnnotation__Group__6__Impl : ( ( rule__TypedKeyStringValueAnnotation__Group_6__0 )? ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2546:1: ( ( ( rule__TypedKeyStringValueAnnotation__Group_6__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2547:1: ( ( rule__TypedKeyStringValueAnnotation__Group_6__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2547:1: ( ( rule__TypedKeyStringValueAnnotation__Group_6__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2548:1: ( rule__TypedKeyStringValueAnnotation__Group_6__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getGroup_6()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2549:1: ( rule__TypedKeyStringValueAnnotation__Group_6__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==23) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2549:2: rule__TypedKeyStringValueAnnotation__Group_6__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__0_in_rule__TypedKeyStringValueAnnotation__Group__6__Impl5141);
                    rule__TypedKeyStringValueAnnotation__Group_6__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getGroup_6()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group__6__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group_6__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2573:1: rule__TypedKeyStringValueAnnotation__Group_6__0 : rule__TypedKeyStringValueAnnotation__Group_6__0__Impl rule__TypedKeyStringValueAnnotation__Group_6__1 ;
    public final void rule__TypedKeyStringValueAnnotation__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2577:1: ( rule__TypedKeyStringValueAnnotation__Group_6__0__Impl rule__TypedKeyStringValueAnnotation__Group_6__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2578:2: rule__TypedKeyStringValueAnnotation__Group_6__0__Impl rule__TypedKeyStringValueAnnotation__Group_6__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__0__Impl_in_rule__TypedKeyStringValueAnnotation__Group_6__05186);
            rule__TypedKeyStringValueAnnotation__Group_6__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__1_in_rule__TypedKeyStringValueAnnotation__Group_6__05189);
            rule__TypedKeyStringValueAnnotation__Group_6__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group_6__0"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group_6__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2585:1: rule__TypedKeyStringValueAnnotation__Group_6__0__Impl : ( '(' ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2589:1: ( ( '(' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2590:1: ( '(' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2590:1: ( '(' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2591:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_6_0()); 
            }
            match(input,23,FollowSets000.FOLLOW_23_in_rule__TypedKeyStringValueAnnotation__Group_6__0__Impl5217); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_6_0()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group_6__0__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group_6__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2604:1: rule__TypedKeyStringValueAnnotation__Group_6__1 : rule__TypedKeyStringValueAnnotation__Group_6__1__Impl rule__TypedKeyStringValueAnnotation__Group_6__2 ;
    public final void rule__TypedKeyStringValueAnnotation__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2608:1: ( rule__TypedKeyStringValueAnnotation__Group_6__1__Impl rule__TypedKeyStringValueAnnotation__Group_6__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2609:2: rule__TypedKeyStringValueAnnotation__Group_6__1__Impl rule__TypedKeyStringValueAnnotation__Group_6__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__1__Impl_in_rule__TypedKeyStringValueAnnotation__Group_6__15248);
            rule__TypedKeyStringValueAnnotation__Group_6__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__2_in_rule__TypedKeyStringValueAnnotation__Group_6__15251);
            rule__TypedKeyStringValueAnnotation__Group_6__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group_6__1"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group_6__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2616:1: rule__TypedKeyStringValueAnnotation__Group_6__1__Impl : ( ( rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1 )* ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2620:1: ( ( ( rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2621:1: ( ( rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2621:1: ( ( rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2622:1: ( rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getAnnotationsAssignment_6_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2623:1: ( rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_COMMENT_ANNOTATION||LA26_0==22) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2623:2: rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1_in_rule__TypedKeyStringValueAnnotation__Group_6__1__Impl5278);
            	    rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getAnnotationsAssignment_6_1()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group_6__1__Impl"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group_6__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2633:1: rule__TypedKeyStringValueAnnotation__Group_6__2 : rule__TypedKeyStringValueAnnotation__Group_6__2__Impl ;
    public final void rule__TypedKeyStringValueAnnotation__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2637:1: ( rule__TypedKeyStringValueAnnotation__Group_6__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2638:2: rule__TypedKeyStringValueAnnotation__Group_6__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__2__Impl_in_rule__TypedKeyStringValueAnnotation__Group_6__25309);
            rule__TypedKeyStringValueAnnotation__Group_6__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group_6__2"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__Group_6__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2644:1: rule__TypedKeyStringValueAnnotation__Group_6__2__Impl : ( ')' ) ;
    public final void rule__TypedKeyStringValueAnnotation__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2648:1: ( ( ')' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2649:1: ( ')' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2649:1: ( ')' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2650:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightParenthesisKeyword_6_2()); 
            }
            match(input,24,FollowSets000.FOLLOW_24_in_rule__TypedKeyStringValueAnnotation__Group_6__2__Impl5337); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightParenthesisKeyword_6_2()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__Group_6__2__Impl"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2669:1: rule__KeyBooleanValueAnnotation__Group__0 : rule__KeyBooleanValueAnnotation__Group__0__Impl rule__KeyBooleanValueAnnotation__Group__1 ;
    public final void rule__KeyBooleanValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2673:1: ( rule__KeyBooleanValueAnnotation__Group__0__Impl rule__KeyBooleanValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2674:2: rule__KeyBooleanValueAnnotation__Group__0__Impl rule__KeyBooleanValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__0__Impl_in_rule__KeyBooleanValueAnnotation__Group__05374);
            rule__KeyBooleanValueAnnotation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__1_in_rule__KeyBooleanValueAnnotation__Group__05377);
            rule__KeyBooleanValueAnnotation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__0"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2681:1: rule__KeyBooleanValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyBooleanValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2685:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2686:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2686:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2687:1: '@'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0()); 
            }
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyBooleanValueAnnotation__Group__0__Impl5405); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__0__Impl"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2700:1: rule__KeyBooleanValueAnnotation__Group__1 : rule__KeyBooleanValueAnnotation__Group__1__Impl rule__KeyBooleanValueAnnotation__Group__2 ;
    public final void rule__KeyBooleanValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2704:1: ( rule__KeyBooleanValueAnnotation__Group__1__Impl rule__KeyBooleanValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2705:2: rule__KeyBooleanValueAnnotation__Group__1__Impl rule__KeyBooleanValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__1__Impl_in_rule__KeyBooleanValueAnnotation__Group__15436);
            rule__KeyBooleanValueAnnotation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__2_in_rule__KeyBooleanValueAnnotation__Group__15439);
            rule__KeyBooleanValueAnnotation__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__1"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2712:1: rule__KeyBooleanValueAnnotation__Group__1__Impl : ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyBooleanValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2716:1: ( ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2717:1: ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2717:1: ( ( rule__KeyBooleanValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2718:1: ( rule__KeyBooleanValueAnnotation__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2719:1: ( rule__KeyBooleanValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2719:2: rule__KeyBooleanValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__NameAssignment_1_in_rule__KeyBooleanValueAnnotation__Group__1__Impl5466);
            rule__KeyBooleanValueAnnotation__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameAssignment_1()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__1__Impl"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2729:1: rule__KeyBooleanValueAnnotation__Group__2 : rule__KeyBooleanValueAnnotation__Group__2__Impl rule__KeyBooleanValueAnnotation__Group__3 ;
    public final void rule__KeyBooleanValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2733:1: ( rule__KeyBooleanValueAnnotation__Group__2__Impl rule__KeyBooleanValueAnnotation__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2734:2: rule__KeyBooleanValueAnnotation__Group__2__Impl rule__KeyBooleanValueAnnotation__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__2__Impl_in_rule__KeyBooleanValueAnnotation__Group__25496);
            rule__KeyBooleanValueAnnotation__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__3_in_rule__KeyBooleanValueAnnotation__Group__25499);
            rule__KeyBooleanValueAnnotation__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__2"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2741:1: rule__KeyBooleanValueAnnotation__Group__2__Impl : ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyBooleanValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2745:1: ( ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2746:1: ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2746:1: ( ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2747:1: ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueAssignment_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2748:1: ( rule__KeyBooleanValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2748:2: rule__KeyBooleanValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__ValueAssignment_2_in_rule__KeyBooleanValueAnnotation__Group__2__Impl5526);
            rule__KeyBooleanValueAnnotation__ValueAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueAssignment_2()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__2__Impl"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2758:1: rule__KeyBooleanValueAnnotation__Group__3 : rule__KeyBooleanValueAnnotation__Group__3__Impl ;
    public final void rule__KeyBooleanValueAnnotation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2762:1: ( rule__KeyBooleanValueAnnotation__Group__3__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2763:2: rule__KeyBooleanValueAnnotation__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group__3__Impl_in_rule__KeyBooleanValueAnnotation__Group__35556);
            rule__KeyBooleanValueAnnotation__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__3"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2769:1: rule__KeyBooleanValueAnnotation__Group__3__Impl : ( ( rule__KeyBooleanValueAnnotation__Group_3__0 )? ) ;
    public final void rule__KeyBooleanValueAnnotation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2773:1: ( ( ( rule__KeyBooleanValueAnnotation__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2774:1: ( ( rule__KeyBooleanValueAnnotation__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2774:1: ( ( rule__KeyBooleanValueAnnotation__Group_3__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2775:1: ( rule__KeyBooleanValueAnnotation__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2776:1: ( rule__KeyBooleanValueAnnotation__Group_3__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==23) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2776:2: rule__KeyBooleanValueAnnotation__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__0_in_rule__KeyBooleanValueAnnotation__Group__3__Impl5583);
                    rule__KeyBooleanValueAnnotation__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup_3()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group__3__Impl"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group_3__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2794:1: rule__KeyBooleanValueAnnotation__Group_3__0 : rule__KeyBooleanValueAnnotation__Group_3__0__Impl rule__KeyBooleanValueAnnotation__Group_3__1 ;
    public final void rule__KeyBooleanValueAnnotation__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2798:1: ( rule__KeyBooleanValueAnnotation__Group_3__0__Impl rule__KeyBooleanValueAnnotation__Group_3__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2799:2: rule__KeyBooleanValueAnnotation__Group_3__0__Impl rule__KeyBooleanValueAnnotation__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__0__Impl_in_rule__KeyBooleanValueAnnotation__Group_3__05622);
            rule__KeyBooleanValueAnnotation__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__1_in_rule__KeyBooleanValueAnnotation__Group_3__05625);
            rule__KeyBooleanValueAnnotation__Group_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group_3__0"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group_3__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2806:1: rule__KeyBooleanValueAnnotation__Group_3__0__Impl : ( '(' ) ;
    public final void rule__KeyBooleanValueAnnotation__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2810:1: ( ( '(' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2811:1: ( '(' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2811:1: ( '(' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2812:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
            }
            match(input,23,FollowSets000.FOLLOW_23_in_rule__KeyBooleanValueAnnotation__Group_3__0__Impl5653); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group_3__0__Impl"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group_3__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2825:1: rule__KeyBooleanValueAnnotation__Group_3__1 : rule__KeyBooleanValueAnnotation__Group_3__1__Impl rule__KeyBooleanValueAnnotation__Group_3__2 ;
    public final void rule__KeyBooleanValueAnnotation__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2829:1: ( rule__KeyBooleanValueAnnotation__Group_3__1__Impl rule__KeyBooleanValueAnnotation__Group_3__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2830:2: rule__KeyBooleanValueAnnotation__Group_3__1__Impl rule__KeyBooleanValueAnnotation__Group_3__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__1__Impl_in_rule__KeyBooleanValueAnnotation__Group_3__15684);
            rule__KeyBooleanValueAnnotation__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__2_in_rule__KeyBooleanValueAnnotation__Group_3__15687);
            rule__KeyBooleanValueAnnotation__Group_3__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group_3__1"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group_3__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2837:1: rule__KeyBooleanValueAnnotation__Group_3__1__Impl : ( ( rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1 )* ) ;
    public final void rule__KeyBooleanValueAnnotation__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2841:1: ( ( ( rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2842:1: ( ( rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2842:1: ( ( rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2843:1: ( rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2844:1: ( rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_COMMENT_ANNOTATION||LA28_0==22) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2844:2: rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyBooleanValueAnnotation__Group_3__1__Impl5714);
            	    rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group_3__1__Impl"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group_3__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2854:1: rule__KeyBooleanValueAnnotation__Group_3__2 : rule__KeyBooleanValueAnnotation__Group_3__2__Impl ;
    public final void rule__KeyBooleanValueAnnotation__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2858:1: ( rule__KeyBooleanValueAnnotation__Group_3__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2859:2: rule__KeyBooleanValueAnnotation__Group_3__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__2__Impl_in_rule__KeyBooleanValueAnnotation__Group_3__25745);
            rule__KeyBooleanValueAnnotation__Group_3__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group_3__2"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__Group_3__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2865:1: rule__KeyBooleanValueAnnotation__Group_3__2__Impl : ( ')' ) ;
    public final void rule__KeyBooleanValueAnnotation__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2869:1: ( ( ')' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2870:1: ( ')' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2870:1: ( ')' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2871:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
            }
            match(input,24,FollowSets000.FOLLOW_24_in_rule__KeyBooleanValueAnnotation__Group_3__2__Impl5773); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__Group_3__2__Impl"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2890:1: rule__KeyIntValueAnnotation__Group__0 : rule__KeyIntValueAnnotation__Group__0__Impl rule__KeyIntValueAnnotation__Group__1 ;
    public final void rule__KeyIntValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2894:1: ( rule__KeyIntValueAnnotation__Group__0__Impl rule__KeyIntValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2895:2: rule__KeyIntValueAnnotation__Group__0__Impl rule__KeyIntValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__0__Impl_in_rule__KeyIntValueAnnotation__Group__05810);
            rule__KeyIntValueAnnotation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__1_in_rule__KeyIntValueAnnotation__Group__05813);
            rule__KeyIntValueAnnotation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__0"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2902:1: rule__KeyIntValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyIntValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2906:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2907:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2907:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2908:1: '@'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0()); 
            }
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyIntValueAnnotation__Group__0__Impl5841); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__0__Impl"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2921:1: rule__KeyIntValueAnnotation__Group__1 : rule__KeyIntValueAnnotation__Group__1__Impl rule__KeyIntValueAnnotation__Group__2 ;
    public final void rule__KeyIntValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2925:1: ( rule__KeyIntValueAnnotation__Group__1__Impl rule__KeyIntValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2926:2: rule__KeyIntValueAnnotation__Group__1__Impl rule__KeyIntValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__1__Impl_in_rule__KeyIntValueAnnotation__Group__15872);
            rule__KeyIntValueAnnotation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__2_in_rule__KeyIntValueAnnotation__Group__15875);
            rule__KeyIntValueAnnotation__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__1"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2933:1: rule__KeyIntValueAnnotation__Group__1__Impl : ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyIntValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2937:1: ( ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2938:1: ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2938:1: ( ( rule__KeyIntValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2939:1: ( rule__KeyIntValueAnnotation__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getNameAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2940:1: ( rule__KeyIntValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2940:2: rule__KeyIntValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__NameAssignment_1_in_rule__KeyIntValueAnnotation__Group__1__Impl5902);
            rule__KeyIntValueAnnotation__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getNameAssignment_1()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__1__Impl"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2950:1: rule__KeyIntValueAnnotation__Group__2 : rule__KeyIntValueAnnotation__Group__2__Impl rule__KeyIntValueAnnotation__Group__3 ;
    public final void rule__KeyIntValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2954:1: ( rule__KeyIntValueAnnotation__Group__2__Impl rule__KeyIntValueAnnotation__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2955:2: rule__KeyIntValueAnnotation__Group__2__Impl rule__KeyIntValueAnnotation__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__2__Impl_in_rule__KeyIntValueAnnotation__Group__25932);
            rule__KeyIntValueAnnotation__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__3_in_rule__KeyIntValueAnnotation__Group__25935);
            rule__KeyIntValueAnnotation__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__2"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2962:1: rule__KeyIntValueAnnotation__Group__2__Impl : ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyIntValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2966:1: ( ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2967:1: ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2967:1: ( ( rule__KeyIntValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2968:1: ( rule__KeyIntValueAnnotation__ValueAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getValueAssignment_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2969:1: ( rule__KeyIntValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2969:2: rule__KeyIntValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__ValueAssignment_2_in_rule__KeyIntValueAnnotation__Group__2__Impl5962);
            rule__KeyIntValueAnnotation__ValueAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getValueAssignment_2()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__2__Impl"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2979:1: rule__KeyIntValueAnnotation__Group__3 : rule__KeyIntValueAnnotation__Group__3__Impl ;
    public final void rule__KeyIntValueAnnotation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2983:1: ( rule__KeyIntValueAnnotation__Group__3__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2984:2: rule__KeyIntValueAnnotation__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group__3__Impl_in_rule__KeyIntValueAnnotation__Group__35992);
            rule__KeyIntValueAnnotation__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__3"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2990:1: rule__KeyIntValueAnnotation__Group__3__Impl : ( ( rule__KeyIntValueAnnotation__Group_3__0 )? ) ;
    public final void rule__KeyIntValueAnnotation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2994:1: ( ( ( rule__KeyIntValueAnnotation__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2995:1: ( ( rule__KeyIntValueAnnotation__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2995:1: ( ( rule__KeyIntValueAnnotation__Group_3__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2996:1: ( rule__KeyIntValueAnnotation__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getGroup_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2997:1: ( rule__KeyIntValueAnnotation__Group_3__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==23) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:2997:2: rule__KeyIntValueAnnotation__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group_3__0_in_rule__KeyIntValueAnnotation__Group__3__Impl6019);
                    rule__KeyIntValueAnnotation__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getGroup_3()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__Group__3__Impl"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group_3__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3015:1: rule__KeyIntValueAnnotation__Group_3__0 : rule__KeyIntValueAnnotation__Group_3__0__Impl rule__KeyIntValueAnnotation__Group_3__1 ;
    public final void rule__KeyIntValueAnnotation__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3019:1: ( rule__KeyIntValueAnnotation__Group_3__0__Impl rule__KeyIntValueAnnotation__Group_3__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3020:2: rule__KeyIntValueAnnotation__Group_3__0__Impl rule__KeyIntValueAnnotation__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group_3__0__Impl_in_rule__KeyIntValueAnnotation__Group_3__06058);
            rule__KeyIntValueAnnotation__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group_3__1_in_rule__KeyIntValueAnnotation__Group_3__06061);
            rule__KeyIntValueAnnotation__Group_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyIntValueAnnotation__Group_3__0"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group_3__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3027:1: rule__KeyIntValueAnnotation__Group_3__0__Impl : ( '(' ) ;
    public final void rule__KeyIntValueAnnotation__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3031:1: ( ( '(' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3032:1: ( '(' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3032:1: ( '(' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3033:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
            }
            match(input,23,FollowSets000.FOLLOW_23_in_rule__KeyIntValueAnnotation__Group_3__0__Impl6089); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__Group_3__0__Impl"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group_3__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3046:1: rule__KeyIntValueAnnotation__Group_3__1 : rule__KeyIntValueAnnotation__Group_3__1__Impl rule__KeyIntValueAnnotation__Group_3__2 ;
    public final void rule__KeyIntValueAnnotation__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3050:1: ( rule__KeyIntValueAnnotation__Group_3__1__Impl rule__KeyIntValueAnnotation__Group_3__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3051:2: rule__KeyIntValueAnnotation__Group_3__1__Impl rule__KeyIntValueAnnotation__Group_3__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group_3__1__Impl_in_rule__KeyIntValueAnnotation__Group_3__16120);
            rule__KeyIntValueAnnotation__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group_3__2_in_rule__KeyIntValueAnnotation__Group_3__16123);
            rule__KeyIntValueAnnotation__Group_3__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyIntValueAnnotation__Group_3__1"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group_3__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3058:1: rule__KeyIntValueAnnotation__Group_3__1__Impl : ( ( rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1 )* ) ;
    public final void rule__KeyIntValueAnnotation__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3062:1: ( ( ( rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3063:1: ( ( rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3063:1: ( ( rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3064:1: ( rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3065:1: ( rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_COMMENT_ANNOTATION||LA30_0==22) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3065:2: rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyIntValueAnnotation__Group_3__1__Impl6150);
            	    rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__Group_3__1__Impl"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group_3__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3075:1: rule__KeyIntValueAnnotation__Group_3__2 : rule__KeyIntValueAnnotation__Group_3__2__Impl ;
    public final void rule__KeyIntValueAnnotation__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3079:1: ( rule__KeyIntValueAnnotation__Group_3__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3080:2: rule__KeyIntValueAnnotation__Group_3__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyIntValueAnnotation__Group_3__2__Impl_in_rule__KeyIntValueAnnotation__Group_3__26181);
            rule__KeyIntValueAnnotation__Group_3__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyIntValueAnnotation__Group_3__2"


    // $ANTLR start "rule__KeyIntValueAnnotation__Group_3__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3086:1: rule__KeyIntValueAnnotation__Group_3__2__Impl : ( ')' ) ;
    public final void rule__KeyIntValueAnnotation__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3090:1: ( ( ')' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3091:1: ( ')' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3091:1: ( ')' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3092:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
            }
            match(input,24,FollowSets000.FOLLOW_24_in_rule__KeyIntValueAnnotation__Group_3__2__Impl6209); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__Group_3__2__Impl"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3111:1: rule__KeyFloatValueAnnotation__Group__0 : rule__KeyFloatValueAnnotation__Group__0__Impl rule__KeyFloatValueAnnotation__Group__1 ;
    public final void rule__KeyFloatValueAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3115:1: ( rule__KeyFloatValueAnnotation__Group__0__Impl rule__KeyFloatValueAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3116:2: rule__KeyFloatValueAnnotation__Group__0__Impl rule__KeyFloatValueAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__0__Impl_in_rule__KeyFloatValueAnnotation__Group__06246);
            rule__KeyFloatValueAnnotation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__1_in_rule__KeyFloatValueAnnotation__Group__06249);
            rule__KeyFloatValueAnnotation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__0"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3123:1: rule__KeyFloatValueAnnotation__Group__0__Impl : ( '@' ) ;
    public final void rule__KeyFloatValueAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3127:1: ( ( '@' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3128:1: ( '@' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3128:1: ( '@' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3129:1: '@'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0()); 
            }
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KeyFloatValueAnnotation__Group__0__Impl6277); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__0__Impl"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3142:1: rule__KeyFloatValueAnnotation__Group__1 : rule__KeyFloatValueAnnotation__Group__1__Impl rule__KeyFloatValueAnnotation__Group__2 ;
    public final void rule__KeyFloatValueAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3146:1: ( rule__KeyFloatValueAnnotation__Group__1__Impl rule__KeyFloatValueAnnotation__Group__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3147:2: rule__KeyFloatValueAnnotation__Group__1__Impl rule__KeyFloatValueAnnotation__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__1__Impl_in_rule__KeyFloatValueAnnotation__Group__16308);
            rule__KeyFloatValueAnnotation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__2_in_rule__KeyFloatValueAnnotation__Group__16311);
            rule__KeyFloatValueAnnotation__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__1"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3154:1: rule__KeyFloatValueAnnotation__Group__1__Impl : ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) ) ;
    public final void rule__KeyFloatValueAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3158:1: ( ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3159:1: ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3159:1: ( ( rule__KeyFloatValueAnnotation__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3160:1: ( rule__KeyFloatValueAnnotation__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getNameAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3161:1: ( rule__KeyFloatValueAnnotation__NameAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3161:2: rule__KeyFloatValueAnnotation__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__NameAssignment_1_in_rule__KeyFloatValueAnnotation__Group__1__Impl6338);
            rule__KeyFloatValueAnnotation__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getNameAssignment_1()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__1__Impl"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3171:1: rule__KeyFloatValueAnnotation__Group__2 : rule__KeyFloatValueAnnotation__Group__2__Impl rule__KeyFloatValueAnnotation__Group__3 ;
    public final void rule__KeyFloatValueAnnotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3175:1: ( rule__KeyFloatValueAnnotation__Group__2__Impl rule__KeyFloatValueAnnotation__Group__3 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3176:2: rule__KeyFloatValueAnnotation__Group__2__Impl rule__KeyFloatValueAnnotation__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__2__Impl_in_rule__KeyFloatValueAnnotation__Group__26368);
            rule__KeyFloatValueAnnotation__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__3_in_rule__KeyFloatValueAnnotation__Group__26371);
            rule__KeyFloatValueAnnotation__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__2"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3183:1: rule__KeyFloatValueAnnotation__Group__2__Impl : ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) ) ;
    public final void rule__KeyFloatValueAnnotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3187:1: ( ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3188:1: ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3188:1: ( ( rule__KeyFloatValueAnnotation__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3189:1: ( rule__KeyFloatValueAnnotation__ValueAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getValueAssignment_2()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3190:1: ( rule__KeyFloatValueAnnotation__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3190:2: rule__KeyFloatValueAnnotation__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__ValueAssignment_2_in_rule__KeyFloatValueAnnotation__Group__2__Impl6398);
            rule__KeyFloatValueAnnotation__ValueAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getValueAssignment_2()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__2__Impl"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3200:1: rule__KeyFloatValueAnnotation__Group__3 : rule__KeyFloatValueAnnotation__Group__3__Impl ;
    public final void rule__KeyFloatValueAnnotation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3204:1: ( rule__KeyFloatValueAnnotation__Group__3__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3205:2: rule__KeyFloatValueAnnotation__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group__3__Impl_in_rule__KeyFloatValueAnnotation__Group__36428);
            rule__KeyFloatValueAnnotation__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__3"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group__3__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3211:1: rule__KeyFloatValueAnnotation__Group__3__Impl : ( ( rule__KeyFloatValueAnnotation__Group_3__0 )? ) ;
    public final void rule__KeyFloatValueAnnotation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3215:1: ( ( ( rule__KeyFloatValueAnnotation__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3216:1: ( ( rule__KeyFloatValueAnnotation__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3216:1: ( ( rule__KeyFloatValueAnnotation__Group_3__0 )? )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3217:1: ( rule__KeyFloatValueAnnotation__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup_3()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3218:1: ( rule__KeyFloatValueAnnotation__Group_3__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==23) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3218:2: rule__KeyFloatValueAnnotation__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group_3__0_in_rule__KeyFloatValueAnnotation__Group__3__Impl6455);
                    rule__KeyFloatValueAnnotation__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup_3()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group__3__Impl"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group_3__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3236:1: rule__KeyFloatValueAnnotation__Group_3__0 : rule__KeyFloatValueAnnotation__Group_3__0__Impl rule__KeyFloatValueAnnotation__Group_3__1 ;
    public final void rule__KeyFloatValueAnnotation__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3240:1: ( rule__KeyFloatValueAnnotation__Group_3__0__Impl rule__KeyFloatValueAnnotation__Group_3__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3241:2: rule__KeyFloatValueAnnotation__Group_3__0__Impl rule__KeyFloatValueAnnotation__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group_3__0__Impl_in_rule__KeyFloatValueAnnotation__Group_3__06494);
            rule__KeyFloatValueAnnotation__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group_3__1_in_rule__KeyFloatValueAnnotation__Group_3__06497);
            rule__KeyFloatValueAnnotation__Group_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group_3__0"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group_3__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3248:1: rule__KeyFloatValueAnnotation__Group_3__0__Impl : ( '(' ) ;
    public final void rule__KeyFloatValueAnnotation__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3252:1: ( ( '(' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3253:1: ( '(' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3253:1: ( '(' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3254:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
            }
            match(input,23,FollowSets000.FOLLOW_23_in_rule__KeyFloatValueAnnotation__Group_3__0__Impl6525); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getLeftParenthesisKeyword_3_0()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group_3__0__Impl"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group_3__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3267:1: rule__KeyFloatValueAnnotation__Group_3__1 : rule__KeyFloatValueAnnotation__Group_3__1__Impl rule__KeyFloatValueAnnotation__Group_3__2 ;
    public final void rule__KeyFloatValueAnnotation__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3271:1: ( rule__KeyFloatValueAnnotation__Group_3__1__Impl rule__KeyFloatValueAnnotation__Group_3__2 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3272:2: rule__KeyFloatValueAnnotation__Group_3__1__Impl rule__KeyFloatValueAnnotation__Group_3__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group_3__1__Impl_in_rule__KeyFloatValueAnnotation__Group_3__16556);
            rule__KeyFloatValueAnnotation__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group_3__2_in_rule__KeyFloatValueAnnotation__Group_3__16559);
            rule__KeyFloatValueAnnotation__Group_3__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group_3__1"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group_3__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3279:1: rule__KeyFloatValueAnnotation__Group_3__1__Impl : ( ( rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1 )* ) ;
    public final void rule__KeyFloatValueAnnotation__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3283:1: ( ( ( rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3284:1: ( ( rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3284:1: ( ( rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3285:1: ( rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3286:1: ( rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_COMMENT_ANNOTATION||LA32_0==22) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3286:2: rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyFloatValueAnnotation__Group_3__1__Impl6586);
            	    rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAssignment_3_1()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group_3__1__Impl"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group_3__2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3296:1: rule__KeyFloatValueAnnotation__Group_3__2 : rule__KeyFloatValueAnnotation__Group_3__2__Impl ;
    public final void rule__KeyFloatValueAnnotation__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3300:1: ( rule__KeyFloatValueAnnotation__Group_3__2__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3301:2: rule__KeyFloatValueAnnotation__Group_3__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KeyFloatValueAnnotation__Group_3__2__Impl_in_rule__KeyFloatValueAnnotation__Group_3__26617);
            rule__KeyFloatValueAnnotation__Group_3__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group_3__2"


    // $ANTLR start "rule__KeyFloatValueAnnotation__Group_3__2__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3307:1: rule__KeyFloatValueAnnotation__Group_3__2__Impl : ( ')' ) ;
    public final void rule__KeyFloatValueAnnotation__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3311:1: ( ( ')' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3312:1: ( ')' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3312:1: ( ')' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3313:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
            }
            match(input,24,FollowSets000.FOLLOW_24_in_rule__KeyFloatValueAnnotation__Group_3__2__Impl6645); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getRightParenthesisKeyword_3_2()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__Group_3__2__Impl"


    // $ANTLR start "rule__ImportAnnotation__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3332:1: rule__ImportAnnotation__Group__0 : rule__ImportAnnotation__Group__0__Impl rule__ImportAnnotation__Group__1 ;
    public final void rule__ImportAnnotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3336:1: ( rule__ImportAnnotation__Group__0__Impl rule__ImportAnnotation__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3337:2: rule__ImportAnnotation__Group__0__Impl rule__ImportAnnotation__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__ImportAnnotation__Group__0__Impl_in_rule__ImportAnnotation__Group__06682);
            rule__ImportAnnotation__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__ImportAnnotation__Group__1_in_rule__ImportAnnotation__Group__06685);
            rule__ImportAnnotation__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportAnnotation__Group__0"


    // $ANTLR start "rule__ImportAnnotation__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3344:1: rule__ImportAnnotation__Group__0__Impl : ( 'import' ) ;
    public final void rule__ImportAnnotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3348:1: ( ( 'import' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3349:1: ( 'import' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3349:1: ( 'import' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3350:1: 'import'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImportAnnotationAccess().getImportKeyword_0()); 
            }
            match(input,27,FollowSets000.FOLLOW_27_in_rule__ImportAnnotation__Group__0__Impl6713); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getImportAnnotationAccess().getImportKeyword_0()); 
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
    // $ANTLR end "rule__ImportAnnotation__Group__0__Impl"


    // $ANTLR start "rule__ImportAnnotation__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3363:1: rule__ImportAnnotation__Group__1 : rule__ImportAnnotation__Group__1__Impl ;
    public final void rule__ImportAnnotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3367:1: ( rule__ImportAnnotation__Group__1__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3368:2: rule__ImportAnnotation__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__ImportAnnotation__Group__1__Impl_in_rule__ImportAnnotation__Group__16744);
            rule__ImportAnnotation__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportAnnotation__Group__1"


    // $ANTLR start "rule__ImportAnnotation__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3374:1: rule__ImportAnnotation__Group__1__Impl : ( ( rule__ImportAnnotation__ImportURIAssignment_1 ) ) ;
    public final void rule__ImportAnnotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3378:1: ( ( ( rule__ImportAnnotation__ImportURIAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3379:1: ( ( rule__ImportAnnotation__ImportURIAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3379:1: ( ( rule__ImportAnnotation__ImportURIAssignment_1 ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3380:1: ( rule__ImportAnnotation__ImportURIAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImportAnnotationAccess().getImportURIAssignment_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3381:1: ( rule__ImportAnnotation__ImportURIAssignment_1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3381:2: rule__ImportAnnotation__ImportURIAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__ImportAnnotation__ImportURIAssignment_1_in_rule__ImportAnnotation__Group__1__Impl6771);
            rule__ImportAnnotation__ImportURIAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getImportAnnotationAccess().getImportURIAssignment_1()); 
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
    // $ANTLR end "rule__ImportAnnotation__Group__1__Impl"


    // $ANTLR start "rule__ExtendedID__Group__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3395:1: rule__ExtendedID__Group__0 : rule__ExtendedID__Group__0__Impl rule__ExtendedID__Group__1 ;
    public final void rule__ExtendedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3399:1: ( rule__ExtendedID__Group__0__Impl rule__ExtendedID__Group__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3400:2: rule__ExtendedID__Group__0__Impl rule__ExtendedID__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group__0__Impl_in_rule__ExtendedID__Group__06805);
            rule__ExtendedID__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group__1_in_rule__ExtendedID__Group__06808);
            rule__ExtendedID__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExtendedID__Group__0"


    // $ANTLR start "rule__ExtendedID__Group__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3407:1: rule__ExtendedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__ExtendedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3411:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3412:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3412:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3413:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_0()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__ExtendedID__Group__0__Impl6835); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_0()); 
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
    // $ANTLR end "rule__ExtendedID__Group__0__Impl"


    // $ANTLR start "rule__ExtendedID__Group__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3424:1: rule__ExtendedID__Group__1 : rule__ExtendedID__Group__1__Impl ;
    public final void rule__ExtendedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3428:1: ( rule__ExtendedID__Group__1__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3429:2: rule__ExtendedID__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group__1__Impl_in_rule__ExtendedID__Group__16864);
            rule__ExtendedID__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExtendedID__Group__1"


    // $ANTLR start "rule__ExtendedID__Group__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3435:1: rule__ExtendedID__Group__1__Impl : ( ( rule__ExtendedID__Group_1__0 )* ) ;
    public final void rule__ExtendedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3439:1: ( ( ( rule__ExtendedID__Group_1__0 )* ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3440:1: ( ( rule__ExtendedID__Group_1__0 )* )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3440:1: ( ( rule__ExtendedID__Group_1__0 )* )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3441:1: ( rule__ExtendedID__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExtendedIDAccess().getGroup_1()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3442:1: ( rule__ExtendedID__Group_1__0 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==28) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3442:2: rule__ExtendedID__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group_1__0_in_rule__ExtendedID__Group__1__Impl6891);
            	    rule__ExtendedID__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExtendedIDAccess().getGroup_1()); 
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
    // $ANTLR end "rule__ExtendedID__Group__1__Impl"


    // $ANTLR start "rule__ExtendedID__Group_1__0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3456:1: rule__ExtendedID__Group_1__0 : rule__ExtendedID__Group_1__0__Impl rule__ExtendedID__Group_1__1 ;
    public final void rule__ExtendedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3460:1: ( rule__ExtendedID__Group_1__0__Impl rule__ExtendedID__Group_1__1 )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3461:2: rule__ExtendedID__Group_1__0__Impl rule__ExtendedID__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group_1__0__Impl_in_rule__ExtendedID__Group_1__06926);
            rule__ExtendedID__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group_1__1_in_rule__ExtendedID__Group_1__06929);
            rule__ExtendedID__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExtendedID__Group_1__0"


    // $ANTLR start "rule__ExtendedID__Group_1__0__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3468:1: rule__ExtendedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__ExtendedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3472:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3473:1: ( '.' )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3473:1: ( '.' )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3474:1: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExtendedIDAccess().getFullStopKeyword_1_0()); 
            }
            match(input,28,FollowSets000.FOLLOW_28_in_rule__ExtendedID__Group_1__0__Impl6957); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExtendedIDAccess().getFullStopKeyword_1_0()); 
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
    // $ANTLR end "rule__ExtendedID__Group_1__0__Impl"


    // $ANTLR start "rule__ExtendedID__Group_1__1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3487:1: rule__ExtendedID__Group_1__1 : rule__ExtendedID__Group_1__1__Impl ;
    public final void rule__ExtendedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3491:1: ( rule__ExtendedID__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3492:2: rule__ExtendedID__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__ExtendedID__Group_1__1__Impl_in_rule__ExtendedID__Group_1__16988);
            rule__ExtendedID__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExtendedID__Group_1__1"


    // $ANTLR start "rule__ExtendedID__Group_1__1__Impl"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3498:1: rule__ExtendedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__ExtendedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3502:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3503:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3503:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3504:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_1_1()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__ExtendedID__Group_1__1__Impl7015); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_1_1()); 
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
    // $ANTLR end "rule__ExtendedID__Group_1__1__Impl"


    // $ANTLR start "rule__TopLevelEntity__AnnotationsAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3520:1: rule__TopLevelEntity__AnnotationsAssignment_1 : ( ruleImportAnnotation ) ;
    public final void rule__TopLevelEntity__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3524:1: ( ( ruleImportAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3525:1: ( ruleImportAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3525:1: ( ruleImportAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3526:1: ruleImportAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getAnnotationsImportAnnotationParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleImportAnnotation_in_rule__TopLevelEntity__AnnotationsAssignment_17053);
            ruleImportAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getAnnotationsImportAnnotationParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__AnnotationsAssignment_1"


    // $ANTLR start "rule__TopLevelEntity__AnnotationsAssignment_2_0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3535:1: rule__TopLevelEntity__AnnotationsAssignment_2_0 : ( ruleAnnotation ) ;
    public final void rule__TopLevelEntity__AnnotationsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3539:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3540:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3540:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3541:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getAnnotationsAnnotationParserRuleCall_2_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__TopLevelEntity__AnnotationsAssignment_2_07084);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getAnnotationsAnnotationParserRuleCall_2_0_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__AnnotationsAssignment_2_0"


    // $ANTLR start "rule__TopLevelEntity__IdAssignment_2_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3550:1: rule__TopLevelEntity__IdAssignment_2_2 : ( RULE_ID ) ;
    public final void rule__TopLevelEntity__IdAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3554:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3555:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3555:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3556:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getIdIDTerminalRuleCall_2_2_0()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__TopLevelEntity__IdAssignment_2_27115); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getIdIDTerminalRuleCall_2_2_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__IdAssignment_2_2"


    // $ANTLR start "rule__TopLevelEntity__NameAssignment_2_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3565:1: rule__TopLevelEntity__NameAssignment_2_3 : ( RULE_STRING ) ;
    public final void rule__TopLevelEntity__NameAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3569:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3570:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3570:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3571:1: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getNameSTRINGTerminalRuleCall_2_3_0()); 
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__TopLevelEntity__NameAssignment_2_37146); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getNameSTRINGTerminalRuleCall_2_3_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__NameAssignment_2_3"


    // $ANTLR start "rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3580:1: rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 : ( ruleEntity ) ;
    public final void rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3584:1: ( ( ruleEntity ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3585:1: ( ruleEntity )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3585:1: ( ruleEntity )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3586:1: ruleEntity
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_2_4_0_1_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_07177);
            ruleEntity();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_2_4_0_1_0_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0"


    // $ANTLR start "rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3595:1: rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 : ( ruleLink ) ;
    public final void rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3599:1: ( ( ruleLink ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3600:1: ( ruleLink )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3600:1: ( ruleLink )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3601:1: ruleLink
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_2_4_0_1_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_17208);
            ruleLink();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_2_4_0_1_1_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1"


    // $ANTLR start "rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3610:1: rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 : ( rulePort ) ;
    public final void rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3614:1: ( ( rulePort ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3615:1: ( rulePort )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3615:1: ( rulePort )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3616:1: rulePort
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_2_4_0_1_2_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_rulePort_in_rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_27239);
            rulePort();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_2_4_0_1_2_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2"


    // $ANTLR start "rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3625:1: rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3 : ( ruleRelation ) ;
    public final void rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3629:1: ( ( ruleRelation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3630:1: ( ruleRelation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3630:1: ( ruleRelation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3631:1: ruleRelation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_2_4_0_1_3_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_37270);
            ruleRelation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_2_4_0_1_3_0()); 
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
    // $ANTLR end "rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3"


    // $ANTLR start "rule__Entity__AnnotationsAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3640:1: rule__Entity__AnnotationsAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Entity__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3644:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3645:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3645:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3646:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Entity__AnnotationsAssignment_17301);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__Entity__AnnotationsAssignment_1"


    // $ANTLR start "rule__Entity__IdAssignment_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3655:1: rule__Entity__IdAssignment_3 : ( RULE_ID ) ;
    public final void rule__Entity__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3659:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3660:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3660:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3661:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getIdIDTerminalRuleCall_3_0()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Entity__IdAssignment_37332); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getIdIDTerminalRuleCall_3_0()); 
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
    // $ANTLR end "rule__Entity__IdAssignment_3"


    // $ANTLR start "rule__Entity__NameAssignment_4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3670:1: rule__Entity__NameAssignment_4 : ( RULE_STRING ) ;
    public final void rule__Entity__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3674:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3675:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3675:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3676:1: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getNameSTRINGTerminalRuleCall_4_0()); 
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Entity__NameAssignment_47363); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getNameSTRINGTerminalRuleCall_4_0()); 
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
    // $ANTLR end "rule__Entity__NameAssignment_4"


    // $ANTLR start "rule__Entity__ChildEntitiesAssignment_5_0_1_0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3685:1: rule__Entity__ChildEntitiesAssignment_5_0_1_0 : ( ruleEntity ) ;
    public final void rule__Entity__ChildEntitiesAssignment_5_0_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3689:1: ( ( ruleEntity ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3690:1: ( ruleEntity )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3690:1: ( ruleEntity )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3691:1: ruleEntity
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_rule__Entity__ChildEntitiesAssignment_5_0_1_07394);
            ruleEntity();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0()); 
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
    // $ANTLR end "rule__Entity__ChildEntitiesAssignment_5_0_1_0"


    // $ANTLR start "rule__Entity__ChildLinksAssignment_5_0_1_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3700:1: rule__Entity__ChildLinksAssignment_5_0_1_1 : ( ruleLink ) ;
    public final void rule__Entity__ChildLinksAssignment_5_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3704:1: ( ( ruleLink ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3705:1: ( ruleLink )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3705:1: ( ruleLink )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3706:1: ruleLink
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_rule__Entity__ChildLinksAssignment_5_0_1_17425);
            ruleLink();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0()); 
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
    // $ANTLR end "rule__Entity__ChildLinksAssignment_5_0_1_1"


    // $ANTLR start "rule__Entity__ChildPortsAssignment_5_0_1_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3715:1: rule__Entity__ChildPortsAssignment_5_0_1_2 : ( rulePort ) ;
    public final void rule__Entity__ChildPortsAssignment_5_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3719:1: ( ( rulePort ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3720:1: ( rulePort )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3720:1: ( rulePort )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3721:1: rulePort
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_rulePort_in_rule__Entity__ChildPortsAssignment_5_0_1_27456);
            rulePort();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0()); 
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
    // $ANTLR end "rule__Entity__ChildPortsAssignment_5_0_1_2"


    // $ANTLR start "rule__Entity__ChildRelationsAssignment_5_0_1_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3730:1: rule__Entity__ChildRelationsAssignment_5_0_1_3 : ( ruleRelation ) ;
    public final void rule__Entity__ChildRelationsAssignment_5_0_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3734:1: ( ( ruleRelation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3735:1: ( ruleRelation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3735:1: ( ruleRelation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3736:1: ruleRelation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_rule__Entity__ChildRelationsAssignment_5_0_1_37487);
            ruleRelation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0()); 
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
    // $ANTLR end "rule__Entity__ChildRelationsAssignment_5_0_1_3"


    // $ANTLR start "rule__Link__AnnotationsAssignment_0"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3745:1: rule__Link__AnnotationsAssignment_0 : ( ruleAnnotation ) ;
    public final void rule__Link__AnnotationsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3749:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3750:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3750:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3751:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getAnnotationsAnnotationParserRuleCall_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Link__AnnotationsAssignment_07518);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getAnnotationsAnnotationParserRuleCall_0_0()); 
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
    // $ANTLR end "rule__Link__AnnotationsAssignment_0"


    // $ANTLR start "rule__Link__NameAssignment_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3760:1: rule__Link__NameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Link__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3764:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3765:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3765:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3766:1: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getNameSTRINGTerminalRuleCall_2_0()); 
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Link__NameAssignment_27549); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getNameSTRINGTerminalRuleCall_2_0()); 
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
    // $ANTLR end "rule__Link__NameAssignment_2"


    // $ANTLR start "rule__Link__SourceAssignment_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3775:1: rule__Link__SourceAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__Link__SourceAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3779:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3780:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3780:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3781:1: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getSourceLinkableCrossReference_3_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3782:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3783:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getSourceLinkableIDTerminalRuleCall_3_0_1()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Link__SourceAssignment_37584); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getSourceLinkableIDTerminalRuleCall_3_0_1()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getSourceLinkableCrossReference_3_0()); 
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
    // $ANTLR end "rule__Link__SourceAssignment_3"


    // $ANTLR start "rule__Link__TargetAssignment_5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3794:1: rule__Link__TargetAssignment_5 : ( ( RULE_ID ) ) ;
    public final void rule__Link__TargetAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3798:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3799:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3799:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3800:1: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getTargetLinkableCrossReference_5_0()); 
            }
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3801:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3802:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLinkAccess().getTargetLinkableIDTerminalRuleCall_5_0_1()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Link__TargetAssignment_57623); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getTargetLinkableIDTerminalRuleCall_5_0_1()); 
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLinkAccess().getTargetLinkableCrossReference_5_0()); 
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
    // $ANTLR end "rule__Link__TargetAssignment_5"


    // $ANTLR start "rule__Port__AnnotationsAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3813:1: rule__Port__AnnotationsAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Port__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3817:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3818:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3818:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3819:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Port__AnnotationsAssignment_17658);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__Port__AnnotationsAssignment_1"


    // $ANTLR start "rule__Port__IdAssignment_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3828:1: rule__Port__IdAssignment_3 : ( RULE_ID ) ;
    public final void rule__Port__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3832:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3833:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3833:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3834:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getIdIDTerminalRuleCall_3_0()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Port__IdAssignment_37689); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getIdIDTerminalRuleCall_3_0()); 
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
    // $ANTLR end "rule__Port__IdAssignment_3"


    // $ANTLR start "rule__Port__NameAssignment_4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3843:1: rule__Port__NameAssignment_4 : ( RULE_STRING ) ;
    public final void rule__Port__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3847:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3848:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3848:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3849:1: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPortAccess().getNameSTRINGTerminalRuleCall_4_0()); 
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Port__NameAssignment_47720); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPortAccess().getNameSTRINGTerminalRuleCall_4_0()); 
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
    // $ANTLR end "rule__Port__NameAssignment_4"


    // $ANTLR start "rule__Relation__AnnotationsAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3858:1: rule__Relation__AnnotationsAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Relation__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3862:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3863:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3863:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3864:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__Relation__AnnotationsAssignment_17751);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__Relation__AnnotationsAssignment_1"


    // $ANTLR start "rule__Relation__IdAssignment_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3873:1: rule__Relation__IdAssignment_3 : ( RULE_ID ) ;
    public final void rule__Relation__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3877:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3878:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3878:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3879:1: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getIdIDTerminalRuleCall_3_0()); 
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__Relation__IdAssignment_37782); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getIdIDTerminalRuleCall_3_0()); 
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
    // $ANTLR end "rule__Relation__IdAssignment_3"


    // $ANTLR start "rule__Relation__NameAssignment_4"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3888:1: rule__Relation__NameAssignment_4 : ( RULE_STRING ) ;
    public final void rule__Relation__NameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3892:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3893:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3893:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3894:1: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRelationAccess().getNameSTRINGTerminalRuleCall_4_0()); 
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__Relation__NameAssignment_47813); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRelationAccess().getNameSTRINGTerminalRuleCall_4_0()); 
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
    // $ANTLR end "rule__Relation__NameAssignment_4"


    // $ANTLR start "rule__CommentAnnotation__ValueAssignment"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3903:1: rule__CommentAnnotation__ValueAssignment : ( RULE_COMMENT_ANNOTATION ) ;
    public final void rule__CommentAnnotation__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3907:1: ( ( RULE_COMMENT_ANNOTATION ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3908:1: ( RULE_COMMENT_ANNOTATION )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3908:1: ( RULE_COMMENT_ANNOTATION )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3909:1: RULE_COMMENT_ANNOTATION
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0()); 
            }
            match(input,RULE_COMMENT_ANNOTATION,FollowSets000.FOLLOW_RULE_COMMENT_ANNOTATION_in_rule__CommentAnnotation__ValueAssignment7844); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0()); 
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
    // $ANTLR end "rule__CommentAnnotation__ValueAssignment"


    // $ANTLR start "rule__TagAnnotation__NameAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3918:1: rule__TagAnnotation__NameAssignment_1 : ( ruleExtendedID ) ;
    public final void rule__TagAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3922:1: ( ( ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3923:1: ( ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3923:1: ( ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3924:1: ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_rule__TagAnnotation__NameAssignment_17875);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__TagAnnotation__NameAssignment_1"


    // $ANTLR start "rule__TagAnnotation__AnnotationsAssignment_2_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3933:1: rule__TagAnnotation__AnnotationsAssignment_2_1 : ( ruleAnnotation ) ;
    public final void rule__TagAnnotation__AnnotationsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3937:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3938:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3938:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3939:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTagAnnotationAccess().getAnnotationsAnnotationParserRuleCall_2_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__TagAnnotation__AnnotationsAssignment_2_17906);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTagAnnotationAccess().getAnnotationsAnnotationParserRuleCall_2_1_0()); 
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
    // $ANTLR end "rule__TagAnnotation__AnnotationsAssignment_2_1"


    // $ANTLR start "rule__KeyStringValueAnnotation__NameAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3948:1: rule__KeyStringValueAnnotation__NameAssignment_1 : ( ruleExtendedID ) ;
    public final void rule__KeyStringValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3952:1: ( ( ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3953:1: ( ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3953:1: ( ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3954:1: ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_rule__KeyStringValueAnnotation__NameAssignment_17937);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__NameAssignment_1"


    // $ANTLR start "rule__KeyStringValueAnnotation__ValueAssignment_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3963:1: rule__KeyStringValueAnnotation__ValueAssignment_2 : ( ruleEString ) ;
    public final void rule__KeyStringValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3967:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3968:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3968:1: ( ruleEString )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3969:1: ruleEString
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rule__KeyStringValueAnnotation__ValueAssignment_27968);
            ruleEString();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__ValueAssignment_2"


    // $ANTLR start "rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3978:1: rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1 : ( ruleAnnotation ) ;
    public final void rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3982:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3983:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3983:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3984:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__KeyStringValueAnnotation__AnnotationsAssignment_3_17999);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
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
    // $ANTLR end "rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__NameAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3993:1: rule__TypedKeyStringValueAnnotation__NameAssignment_1 : ( ruleExtendedID ) ;
    public final void rule__TypedKeyStringValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3997:1: ( ( ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3998:1: ( ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3998:1: ( ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:3999:1: ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_rule__TypedKeyStringValueAnnotation__NameAssignment_18030);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__NameAssignment_1"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__TypeAssignment_3"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4008:1: rule__TypedKeyStringValueAnnotation__TypeAssignment_3 : ( ruleExtendedID ) ;
    public final void rule__TypedKeyStringValueAnnotation__TypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4012:1: ( ( ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4013:1: ( ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4013:1: ( ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4014:1: ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getTypeExtendedIDParserRuleCall_3_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_rule__TypedKeyStringValueAnnotation__TypeAssignment_38061);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getTypeExtendedIDParserRuleCall_3_0()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__TypeAssignment_3"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__ValueAssignment_5"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4023:1: rule__TypedKeyStringValueAnnotation__ValueAssignment_5 : ( ruleEString ) ;
    public final void rule__TypedKeyStringValueAnnotation__ValueAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4027:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4028:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4028:1: ( ruleEString )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4029:1: ruleEString
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_5_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rule__TypedKeyStringValueAnnotation__ValueAssignment_58092);
            ruleEString();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_5_0()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__ValueAssignment_5"


    // $ANTLR start "rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4038:1: rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1 : ( ruleAnnotation ) ;
    public final void rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4042:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4043:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4043:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4044:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_6_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_18123);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_6_1_0()); 
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
    // $ANTLR end "rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__NameAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4053:1: rule__KeyBooleanValueAnnotation__NameAssignment_1 : ( ruleExtendedID ) ;
    public final void rule__KeyBooleanValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4057:1: ( ( ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4058:1: ( ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4058:1: ( ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4059:1: ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_rule__KeyBooleanValueAnnotation__NameAssignment_18154);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__NameAssignment_1"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__ValueAssignment_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4068:1: rule__KeyBooleanValueAnnotation__ValueAssignment_2 : ( RULE_BOOLEAN ) ;
    public final void rule__KeyBooleanValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4072:1: ( ( RULE_BOOLEAN ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4073:1: ( RULE_BOOLEAN )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4073:1: ( RULE_BOOLEAN )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4074:1: RULE_BOOLEAN
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0()); 
            }
            match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_rule__KeyBooleanValueAnnotation__ValueAssignment_28185); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__ValueAssignment_2"


    // $ANTLR start "rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4083:1: rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1 : ( ruleAnnotation ) ;
    public final void rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4087:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4088:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4088:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4089:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_18216);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
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
    // $ANTLR end "rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1"


    // $ANTLR start "rule__KeyIntValueAnnotation__NameAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4098:1: rule__KeyIntValueAnnotation__NameAssignment_1 : ( ruleExtendedID ) ;
    public final void rule__KeyIntValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4102:1: ( ( ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4103:1: ( ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4103:1: ( ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4104:1: ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_rule__KeyIntValueAnnotation__NameAssignment_18247);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__NameAssignment_1"


    // $ANTLR start "rule__KeyIntValueAnnotation__ValueAssignment_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4113:1: rule__KeyIntValueAnnotation__ValueAssignment_2 : ( RULE_INT ) ;
    public final void rule__KeyIntValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4117:1: ( ( RULE_INT ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4118:1: ( RULE_INT )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4118:1: ( RULE_INT )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4119:1: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0()); 
            }
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__KeyIntValueAnnotation__ValueAssignment_28278); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__ValueAssignment_2"


    // $ANTLR start "rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4128:1: rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1 : ( ruleAnnotation ) ;
    public final void rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4132:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4133:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4133:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4134:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__KeyIntValueAnnotation__AnnotationsAssignment_3_18309);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
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
    // $ANTLR end "rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1"


    // $ANTLR start "rule__KeyFloatValueAnnotation__NameAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4143:1: rule__KeyFloatValueAnnotation__NameAssignment_1 : ( ruleExtendedID ) ;
    public final void rule__KeyFloatValueAnnotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4147:1: ( ( ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4148:1: ( ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4148:1: ( ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4149:1: ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_rule__KeyFloatValueAnnotation__NameAssignment_18340);
            ruleExtendedID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__NameAssignment_1"


    // $ANTLR start "rule__KeyFloatValueAnnotation__ValueAssignment_2"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4158:1: rule__KeyFloatValueAnnotation__ValueAssignment_2 : ( RULE_FLOAT ) ;
    public final void rule__KeyFloatValueAnnotation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4162:1: ( ( RULE_FLOAT ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4163:1: ( RULE_FLOAT )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4163:1: ( RULE_FLOAT )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4164:1: RULE_FLOAT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0()); 
            }
            match(input,RULE_FLOAT,FollowSets000.FOLLOW_RULE_FLOAT_in_rule__KeyFloatValueAnnotation__ValueAssignment_28371); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__ValueAssignment_2"


    // $ANTLR start "rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4173:1: rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1 : ( ruleAnnotation ) ;
    public final void rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4177:1: ( ( ruleAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4178:1: ( ruleAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4178:1: ( ruleAnnotation )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4179:1: ruleAnnotation
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_18402);
            ruleAnnotation();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
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
    // $ANTLR end "rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1"


    // $ANTLR start "rule__ImportAnnotation__ImportURIAssignment_1"
    // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4188:1: rule__ImportAnnotation__ImportURIAssignment_1 : ( RULE_STRING ) ;
    public final void rule__ImportAnnotation__ImportURIAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4192:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4193:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4193:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:4194:1: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getImportAnnotationAccess().getImportURISTRINGTerminalRuleCall_1_0()); 
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__ImportAnnotation__ImportURIAssignment_18433); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getImportAnnotationAccess().getImportURISTRINGTerminalRuleCall_1_0()); 
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
    // $ANTLR end "rule__ImportAnnotation__ImportURIAssignment_1"

    // $ANTLR start synpred2_InternalKaom
    public final void synpred2_InternalKaom_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:541:1: ( ( ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 ) ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:541:1: ( ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 ) )
        {
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:541:1: ( ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:542:1: ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getTopLevelEntityAccess().getChildEntitiesAssignment_2_4_0_1_0()); 
        }
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:543:1: ( rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0 )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:543:2: rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0
        {
        pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0_in_synpred2_InternalKaom1091);
        rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred2_InternalKaom

    // $ANTLR start synpred3_InternalKaom
    public final void synpred3_InternalKaom_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:547:6: ( ( ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 ) ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:547:6: ( ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 ) )
        {
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:547:6: ( ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:548:1: ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getTopLevelEntityAccess().getChildLinksAssignment_2_4_0_1_1()); 
        }
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:549:1: ( rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1 )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:549:2: rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1
        {
        pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1_in_synpred3_InternalKaom1109);
        rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred3_InternalKaom

    // $ANTLR start synpred4_InternalKaom
    public final void synpred4_InternalKaom_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:553:6: ( ( ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 ) ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:553:6: ( ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 ) )
        {
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:553:6: ( ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:554:1: ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getTopLevelEntityAccess().getChildPortsAssignment_2_4_0_1_2()); 
        }
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:555:1: ( rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2 )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:555:2: rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2
        {
        pushFollow(FollowSets000.FOLLOW_rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2_in_synpred4_InternalKaom1127);
        rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred4_InternalKaom

    // $ANTLR start synpred6_InternalKaom
    public final void synpred6_InternalKaom_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:599:1: ( ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:599:1: ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) )
        {
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:599:1: ( ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:600:1: ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getEntityAccess().getChildEntitiesAssignment_5_0_1_0()); 
        }
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:601:1: ( rule__Entity__ChildEntitiesAssignment_5_0_1_0 )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:601:2: rule__Entity__ChildEntitiesAssignment_5_0_1_0
        {
        pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildEntitiesAssignment_5_0_1_0_in_synpred6_InternalKaom1231);
        rule__Entity__ChildEntitiesAssignment_5_0_1_0();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred6_InternalKaom

    // $ANTLR start synpred7_InternalKaom
    public final void synpred7_InternalKaom_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:605:6: ( ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:605:6: ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) )
        {
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:605:6: ( ( rule__Entity__ChildLinksAssignment_5_0_1_1 ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:606:1: ( rule__Entity__ChildLinksAssignment_5_0_1_1 )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getEntityAccess().getChildLinksAssignment_5_0_1_1()); 
        }
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:607:1: ( rule__Entity__ChildLinksAssignment_5_0_1_1 )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:607:2: rule__Entity__ChildLinksAssignment_5_0_1_1
        {
        pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildLinksAssignment_5_0_1_1_in_synpred7_InternalKaom1249);
        rule__Entity__ChildLinksAssignment_5_0_1_1();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred7_InternalKaom

    // $ANTLR start synpred8_InternalKaom
    public final void synpred8_InternalKaom_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:611:6: ( ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:611:6: ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) )
        {
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:611:6: ( ( rule__Entity__ChildPortsAssignment_5_0_1_2 ) )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:612:1: ( rule__Entity__ChildPortsAssignment_5_0_1_2 )
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getEntityAccess().getChildPortsAssignment_5_0_1_2()); 
        }
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:613:1: ( rule__Entity__ChildPortsAssignment_5_0_1_2 )
        // ../de.cau.cs.kieler.kaom.text.ui/src-gen/de/cau/cs/kieler/kaom/text/ui/contentassist/antlr/internal/InternalKaom.g:613:2: rule__Entity__ChildPortsAssignment_5_0_1_2
        {
        pushFollow(FollowSets000.FOLLOW_rule__Entity__ChildPortsAssignment_5_0_1_2_in_synpred8_InternalKaom1267);
        rule__Entity__ChildPortsAssignment_5_0_1_2();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred8_InternalKaom

    // Delegated rules

    public final boolean synpred2_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\14\uffff";
    static final String DFA5_eofS =
        "\3\uffff\1\5\7\uffff\1\5";
    static final String DFA5_minS =
        "\1\6\1\uffff\1\5\1\4\1\5\6\uffff\1\4";
    static final String DFA5_maxS =
        "\1\26\1\uffff\1\5\1\34\1\5\6\uffff\1\34";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\3\uffff\1\2\1\6\1\4\1\7\1\3\1\5\1\uffff";
    static final String DFA5_specialS =
        "\14\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\17\uffff\1\2",
            "",
            "\1\3",
            "\2\11\1\5\1\12\1\6\1\10\5\uffff\1\5\2\uffff\1\5\1\uffff\5\5"+
            "\1\7\2\uffff\1\4",
            "\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\11\1\5\1\12\1\6\1\10\5\uffff\1\5\2\uffff\1\5\1\uffff\5\5"+
            "\1\7\2\uffff\1\4"
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "629:1: rule__Annotation__Alternatives : ( ( ruleCommentAnnotation ) | ( ruleTagAnnotation ) | ( ruleKeyStringValueAnnotation ) | ( ruleTypedKeyStringValueAnnotation ) | ( ruleKeyBooleanValueAnnotation ) | ( ruleKeyIntValueAnnotation ) | ( ruleKeyFloatValueAnnotation ) );";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity67 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTopLevelEntity74 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__0_in_ruleTopLevelEntity100 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity127 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEntity134 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__0_in_ruleEntity160 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_entryRuleLink189 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLink196 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__0_in_ruleLink222 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_entryRulePort249 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePort256 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__0_in_rulePort282 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_entryRuleRelation309 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleRelation316 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__0_in_ruleRelation342 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_entryRuleAnnotation369 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleAnnotation376 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Annotation__Alternatives_in_ruleAnnotation402 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation429 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCommentAnnotation436 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__CommentAnnotation__ValueAssignment_in_ruleCommentAnnotation462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation489 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTagAnnotation496 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__0_in_ruleTagAnnotation522 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation549 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__0_in_ruleKeyStringValueAnnotation582 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation609 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation616 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__0_in_ruleTypedKeyStringValueAnnotation642 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation669 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation676 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__0_in_ruleKeyBooleanValueAnnotation702 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation729 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation736 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__0_in_ruleKeyIntValueAnnotation762 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation789 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation796 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__0_in_ruleKeyFloatValueAnnotation822 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleImportAnnotation_in_entryRuleImportAnnotation849 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleImportAnnotation856 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ImportAnnotation__Group__0_in_ruleImportAnnotation882 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString909 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString916 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EString__Alternatives_in_ruleEString942 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_entryRuleExtendedID969 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleExtendedID976 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group__0_in_ruleExtendedID1002 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2_4_0__0_in_rule__TopLevelEntity__Alternatives_2_41038 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__TopLevelEntity__Alternatives_2_41057 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0_in_rule__TopLevelEntity__Alternatives_2_4_0_11091 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1_in_rule__TopLevelEntity__Alternatives_2_4_0_11109 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2_in_rule__TopLevelEntity__Alternatives_2_4_0_11127 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3_in_rule__TopLevelEntity__Alternatives_2_4_0_11145 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__0_in_rule__Entity__Alternatives_51178 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Entity__Alternatives_51197 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildEntitiesAssignment_5_0_1_0_in_rule__Entity__Alternatives_5_0_11231 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildLinksAssignment_5_0_1_1_in_rule__Entity__Alternatives_5_0_11249 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildPortsAssignment_5_0_1_2_in_rule__Entity__Alternatives_5_0_11267 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildRelationsAssignment_5_0_1_3_in_rule__Entity__Alternatives_5_0_11285 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_rule__Annotation__Alternatives1319 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_rule__Annotation__Alternatives1336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_rule__Annotation__Alternatives1353 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_rule__Annotation__Alternatives1370 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_rule__Annotation__Alternatives1387 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_rule__Annotation__Alternatives1404 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_rule__Annotation__Alternatives1421 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__EString__Alternatives1453 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__EString__Alternatives1470 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__0__Impl_in_rule__TopLevelEntity__Group__01500 = new BitSet(new long[]{0x0000000008408040L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__1_in_rule__TopLevelEntity__Group__01503 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__1__Impl_in_rule__TopLevelEntity__Group__11561 = new BitSet(new long[]{0x0000000008408040L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__2_in_rule__TopLevelEntity__Group__11564 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__AnnotationsAssignment_1_in_rule__TopLevelEntity__Group__1__Impl1591 = new BitSet(new long[]{0x0000000008000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group__2__Impl_in_rule__TopLevelEntity__Group__21622 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__0_in_rule__TopLevelEntity__Group__2__Impl1649 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__0__Impl_in_rule__TopLevelEntity__Group_2__01686 = new BitSet(new long[]{0x0000000000408040L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__1_in_rule__TopLevelEntity__Group_2__01689 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__AnnotationsAssignment_2_0_in_rule__TopLevelEntity__Group_2__0__Impl1716 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__1__Impl_in_rule__TopLevelEntity__Group_2__11747 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__2_in_rule__TopLevelEntity__Group_2__11750 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__TopLevelEntity__Group_2__1__Impl1778 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__2__Impl_in_rule__TopLevelEntity__Group_2__21809 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__3_in_rule__TopLevelEntity__Group_2__21812 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__IdAssignment_2_2_in_rule__TopLevelEntity__Group_2__2__Impl1839 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__3__Impl_in_rule__TopLevelEntity__Group_2__31869 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__4_in_rule__TopLevelEntity__Group_2__31872 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__NameAssignment_2_3_in_rule__TopLevelEntity__Group_2__3__Impl1899 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2__4__Impl_in_rule__TopLevelEntity__Group_2__41930 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Alternatives_2_4_in_rule__TopLevelEntity__Group_2__4__Impl1957 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2_4_0__0__Impl_in_rule__TopLevelEntity__Group_2_4_0__01997 = new BitSet(new long[]{0x0000000000768040L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2_4_0__1_in_rule__TopLevelEntity__Group_2_4_0__02000 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_rule__TopLevelEntity__Group_2_4_0__0__Impl2028 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2_4_0__1__Impl_in_rule__TopLevelEntity__Group_2_4_0__12059 = new BitSet(new long[]{0x0000000000768040L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2_4_0__2_in_rule__TopLevelEntity__Group_2_4_0__12062 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Alternatives_2_4_0_1_in_rule__TopLevelEntity__Group_2_4_0__1__Impl2089 = new BitSet(new long[]{0x0000000000748042L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__Group_2_4_0__2__Impl_in_rule__TopLevelEntity__Group_2_4_0__22120 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__TopLevelEntity__Group_2_4_0__2__Impl2148 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__0__Impl_in_rule__Entity__Group__02185 = new BitSet(new long[]{0x0000000000408040L});
        public static final BitSet FOLLOW_rule__Entity__Group__1_in_rule__Entity__Group__02188 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__1__Impl_in_rule__Entity__Group__12246 = new BitSet(new long[]{0x0000000000408040L});
        public static final BitSet FOLLOW_rule__Entity__Group__2_in_rule__Entity__Group__12249 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__AnnotationsAssignment_1_in_rule__Entity__Group__1__Impl2276 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Entity__Group__2__Impl_in_rule__Entity__Group__22307 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Entity__Group__3_in_rule__Entity__Group__22310 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__Entity__Group__2__Impl2338 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__3__Impl_in_rule__Entity__Group__32369 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_rule__Entity__Group__4_in_rule__Entity__Group__32372 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__IdAssignment_3_in_rule__Entity__Group__3__Impl2399 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__4__Impl_in_rule__Entity__Group__42429 = new BitSet(new long[]{0x0000000000014010L});
        public static final BitSet FOLLOW_rule__Entity__Group__5_in_rule__Entity__Group__42432 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__NameAssignment_4_in_rule__Entity__Group__4__Impl2459 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group__5__Impl_in_rule__Entity__Group__52490 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Alternatives_5_in_rule__Entity__Group__5__Impl2517 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__0__Impl_in_rule__Entity__Group_5_0__02559 = new BitSet(new long[]{0x0000000000768040L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__1_in_rule__Entity__Group_5_0__02562 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_rule__Entity__Group_5_0__0__Impl2590 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__1__Impl_in_rule__Entity__Group_5_0__12621 = new BitSet(new long[]{0x0000000000768040L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__2_in_rule__Entity__Group_5_0__12624 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__Alternatives_5_0_1_in_rule__Entity__Group_5_0__1__Impl2651 = new BitSet(new long[]{0x0000000000748042L});
        public static final BitSet FOLLOW_rule__Entity__Group_5_0__2__Impl_in_rule__Entity__Group_5_0__22682 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__Entity__Group_5_0__2__Impl2710 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__0__Impl_in_rule__Link__Group__02747 = new BitSet(new long[]{0x0000000000440040L});
        public static final BitSet FOLLOW_rule__Link__Group__1_in_rule__Link__Group__02750 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__AnnotationsAssignment_0_in_rule__Link__Group__0__Impl2777 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Link__Group__1__Impl_in_rule__Link__Group__12808 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__Link__Group__2_in_rule__Link__Group__12811 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_rule__Link__Group__1__Impl2839 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__2__Impl_in_rule__Link__Group__22870 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__Link__Group__3_in_rule__Link__Group__22873 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__NameAssignment_2_in_rule__Link__Group__2__Impl2900 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__3__Impl_in_rule__Link__Group__32931 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_rule__Link__Group__4_in_rule__Link__Group__32934 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__SourceAssignment_3_in_rule__Link__Group__3__Impl2961 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__4__Impl_in_rule__Link__Group__42991 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Link__Group__5_in_rule__Link__Group__42994 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_rule__Link__Group__4__Impl3022 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__5__Impl_in_rule__Link__Group__53053 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__Link__Group__6_in_rule__Link__Group__53056 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__TargetAssignment_5_in_rule__Link__Group__5__Impl3083 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Link__Group__6__Impl_in_rule__Link__Group__63113 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Link__Group__6__Impl3141 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__0__Impl_in_rule__Port__Group__03186 = new BitSet(new long[]{0x0000000000500040L});
        public static final BitSet FOLLOW_rule__Port__Group__1_in_rule__Port__Group__03189 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__1__Impl_in_rule__Port__Group__13247 = new BitSet(new long[]{0x0000000000500040L});
        public static final BitSet FOLLOW_rule__Port__Group__2_in_rule__Port__Group__13250 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__AnnotationsAssignment_1_in_rule__Port__Group__1__Impl3277 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Port__Group__2__Impl_in_rule__Port__Group__23308 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Port__Group__3_in_rule__Port__Group__23311 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_20_in_rule__Port__Group__2__Impl3339 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__3__Impl_in_rule__Port__Group__33370 = new BitSet(new long[]{0x0000000000004010L});
        public static final BitSet FOLLOW_rule__Port__Group__4_in_rule__Port__Group__33373 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__IdAssignment_3_in_rule__Port__Group__3__Impl3400 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__4__Impl_in_rule__Port__Group__43430 = new BitSet(new long[]{0x0000000000004010L});
        public static final BitSet FOLLOW_rule__Port__Group__5_in_rule__Port__Group__43433 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__NameAssignment_4_in_rule__Port__Group__4__Impl3460 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Port__Group__5__Impl_in_rule__Port__Group__53491 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Port__Group__5__Impl3519 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__0__Impl_in_rule__Relation__Group__03562 = new BitSet(new long[]{0x0000000000748040L});
        public static final BitSet FOLLOW_rule__Relation__Group__1_in_rule__Relation__Group__03565 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__1__Impl_in_rule__Relation__Group__13623 = new BitSet(new long[]{0x0000000000748040L});
        public static final BitSet FOLLOW_rule__Relation__Group__2_in_rule__Relation__Group__13626 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__AnnotationsAssignment_1_in_rule__Relation__Group__1__Impl3653 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__Relation__Group__2__Impl_in_rule__Relation__Group__23684 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__Relation__Group__3_in_rule__Relation__Group__23687 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_21_in_rule__Relation__Group__2__Impl3715 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__3__Impl_in_rule__Relation__Group__33746 = new BitSet(new long[]{0x0000000000004010L});
        public static final BitSet FOLLOW_rule__Relation__Group__4_in_rule__Relation__Group__33749 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__IdAssignment_3_in_rule__Relation__Group__3__Impl3776 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__4__Impl_in_rule__Relation__Group__43806 = new BitSet(new long[]{0x0000000000004010L});
        public static final BitSet FOLLOW_rule__Relation__Group__5_in_rule__Relation__Group__43809 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__NameAssignment_4_in_rule__Relation__Group__4__Impl3836 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Relation__Group__5__Impl_in_rule__Relation__Group__53867 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__Relation__Group__5__Impl3895 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__0__Impl_in_rule__TagAnnotation__Group__03938 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__1_in_rule__TagAnnotation__Group__03941 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__TagAnnotation__Group__0__Impl3969 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__1__Impl_in_rule__TagAnnotation__Group__14000 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__2_in_rule__TagAnnotation__Group__14003 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__NameAssignment_1_in_rule__TagAnnotation__Group__1__Impl4030 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group__2__Impl_in_rule__TagAnnotation__Group__24060 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group_2__0_in_rule__TagAnnotation__Group__2__Impl4087 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group_2__0__Impl_in_rule__TagAnnotation__Group_2__04124 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group_2__1_in_rule__TagAnnotation__Group_2__04127 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__TagAnnotation__Group_2__0__Impl4155 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group_2__1__Impl_in_rule__TagAnnotation__Group_2__14186 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group_2__2_in_rule__TagAnnotation__Group_2__14189 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TagAnnotation__AnnotationsAssignment_2_1_in_rule__TagAnnotation__Group_2__1__Impl4216 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__TagAnnotation__Group_2__2__Impl_in_rule__TagAnnotation__Group_2__24247 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__TagAnnotation__Group_2__2__Impl4275 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__0__Impl_in_rule__KeyStringValueAnnotation__Group__04312 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__1_in_rule__KeyStringValueAnnotation__Group__04315 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyStringValueAnnotation__Group__0__Impl4343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__1__Impl_in_rule__KeyStringValueAnnotation__Group__14374 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__2_in_rule__KeyStringValueAnnotation__Group__14377 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__NameAssignment_1_in_rule__KeyStringValueAnnotation__Group__1__Impl4404 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__2__Impl_in_rule__KeyStringValueAnnotation__Group__24434 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__3_in_rule__KeyStringValueAnnotation__Group__24437 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__ValueAssignment_2_in_rule__KeyStringValueAnnotation__Group__2__Impl4464 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group__3__Impl_in_rule__KeyStringValueAnnotation__Group__34494 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group_3__0_in_rule__KeyStringValueAnnotation__Group__3__Impl4521 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group_3__0__Impl_in_rule__KeyStringValueAnnotation__Group_3__04560 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group_3__1_in_rule__KeyStringValueAnnotation__Group_3__04563 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__KeyStringValueAnnotation__Group_3__0__Impl4591 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group_3__1__Impl_in_rule__KeyStringValueAnnotation__Group_3__14622 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group_3__2_in_rule__KeyStringValueAnnotation__Group_3__14625 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyStringValueAnnotation__Group_3__1__Impl4652 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__KeyStringValueAnnotation__Group_3__2__Impl_in_rule__KeyStringValueAnnotation__Group_3__24683 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__KeyStringValueAnnotation__Group_3__2__Impl4711 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__0__Impl_in_rule__TypedKeyStringValueAnnotation__Group__04748 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__1_in_rule__TypedKeyStringValueAnnotation__Group__04751 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__TypedKeyStringValueAnnotation__Group__0__Impl4779 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__1__Impl_in_rule__TypedKeyStringValueAnnotation__Group__14810 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__2_in_rule__TypedKeyStringValueAnnotation__Group__14813 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__NameAssignment_1_in_rule__TypedKeyStringValueAnnotation__Group__1__Impl4840 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__2__Impl_in_rule__TypedKeyStringValueAnnotation__Group__24870 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__3_in_rule__TypedKeyStringValueAnnotation__Group__24873 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_rule__TypedKeyStringValueAnnotation__Group__2__Impl4901 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__3__Impl_in_rule__TypedKeyStringValueAnnotation__Group__34932 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__4_in_rule__TypedKeyStringValueAnnotation__Group__34935 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__TypeAssignment_3_in_rule__TypedKeyStringValueAnnotation__Group__3__Impl4962 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__4__Impl_in_rule__TypedKeyStringValueAnnotation__Group__44992 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__5_in_rule__TypedKeyStringValueAnnotation__Group__44995 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_rule__TypedKeyStringValueAnnotation__Group__4__Impl5023 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__5__Impl_in_rule__TypedKeyStringValueAnnotation__Group__55054 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__6_in_rule__TypedKeyStringValueAnnotation__Group__55057 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__ValueAssignment_5_in_rule__TypedKeyStringValueAnnotation__Group__5__Impl5084 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group__6__Impl_in_rule__TypedKeyStringValueAnnotation__Group__65114 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__0_in_rule__TypedKeyStringValueAnnotation__Group__6__Impl5141 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__0__Impl_in_rule__TypedKeyStringValueAnnotation__Group_6__05186 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__1_in_rule__TypedKeyStringValueAnnotation__Group_6__05189 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__TypedKeyStringValueAnnotation__Group_6__0__Impl5217 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__1__Impl_in_rule__TypedKeyStringValueAnnotation__Group_6__15248 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__2_in_rule__TypedKeyStringValueAnnotation__Group_6__15251 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_1_in_rule__TypedKeyStringValueAnnotation__Group_6__1__Impl5278 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__TypedKeyStringValueAnnotation__Group_6__2__Impl_in_rule__TypedKeyStringValueAnnotation__Group_6__25309 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__TypedKeyStringValueAnnotation__Group_6__2__Impl5337 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__0__Impl_in_rule__KeyBooleanValueAnnotation__Group__05374 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__1_in_rule__KeyBooleanValueAnnotation__Group__05377 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyBooleanValueAnnotation__Group__0__Impl5405 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__1__Impl_in_rule__KeyBooleanValueAnnotation__Group__15436 = new BitSet(new long[]{0x0000000000000080L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__2_in_rule__KeyBooleanValueAnnotation__Group__15439 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__NameAssignment_1_in_rule__KeyBooleanValueAnnotation__Group__1__Impl5466 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__2__Impl_in_rule__KeyBooleanValueAnnotation__Group__25496 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__3_in_rule__KeyBooleanValueAnnotation__Group__25499 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__ValueAssignment_2_in_rule__KeyBooleanValueAnnotation__Group__2__Impl5526 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group__3__Impl_in_rule__KeyBooleanValueAnnotation__Group__35556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__0_in_rule__KeyBooleanValueAnnotation__Group__3__Impl5583 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__0__Impl_in_rule__KeyBooleanValueAnnotation__Group_3__05622 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__1_in_rule__KeyBooleanValueAnnotation__Group_3__05625 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__KeyBooleanValueAnnotation__Group_3__0__Impl5653 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__1__Impl_in_rule__KeyBooleanValueAnnotation__Group_3__15684 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__2_in_rule__KeyBooleanValueAnnotation__Group_3__15687 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyBooleanValueAnnotation__Group_3__1__Impl5714 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__KeyBooleanValueAnnotation__Group_3__2__Impl_in_rule__KeyBooleanValueAnnotation__Group_3__25745 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__KeyBooleanValueAnnotation__Group_3__2__Impl5773 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__0__Impl_in_rule__KeyIntValueAnnotation__Group__05810 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__1_in_rule__KeyIntValueAnnotation__Group__05813 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyIntValueAnnotation__Group__0__Impl5841 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__1__Impl_in_rule__KeyIntValueAnnotation__Group__15872 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__2_in_rule__KeyIntValueAnnotation__Group__15875 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__NameAssignment_1_in_rule__KeyIntValueAnnotation__Group__1__Impl5902 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__2__Impl_in_rule__KeyIntValueAnnotation__Group__25932 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__3_in_rule__KeyIntValueAnnotation__Group__25935 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__ValueAssignment_2_in_rule__KeyIntValueAnnotation__Group__2__Impl5962 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group__3__Impl_in_rule__KeyIntValueAnnotation__Group__35992 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group_3__0_in_rule__KeyIntValueAnnotation__Group__3__Impl6019 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group_3__0__Impl_in_rule__KeyIntValueAnnotation__Group_3__06058 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group_3__1_in_rule__KeyIntValueAnnotation__Group_3__06061 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__KeyIntValueAnnotation__Group_3__0__Impl6089 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group_3__1__Impl_in_rule__KeyIntValueAnnotation__Group_3__16120 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group_3__2_in_rule__KeyIntValueAnnotation__Group_3__16123 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyIntValueAnnotation__Group_3__1__Impl6150 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__KeyIntValueAnnotation__Group_3__2__Impl_in_rule__KeyIntValueAnnotation__Group_3__26181 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__KeyIntValueAnnotation__Group_3__2__Impl6209 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__0__Impl_in_rule__KeyFloatValueAnnotation__Group__06246 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__1_in_rule__KeyFloatValueAnnotation__Group__06249 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KeyFloatValueAnnotation__Group__0__Impl6277 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__1__Impl_in_rule__KeyFloatValueAnnotation__Group__16308 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__2_in_rule__KeyFloatValueAnnotation__Group__16311 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__NameAssignment_1_in_rule__KeyFloatValueAnnotation__Group__1__Impl6338 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__2__Impl_in_rule__KeyFloatValueAnnotation__Group__26368 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__3_in_rule__KeyFloatValueAnnotation__Group__26371 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__ValueAssignment_2_in_rule__KeyFloatValueAnnotation__Group__2__Impl6398 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group__3__Impl_in_rule__KeyFloatValueAnnotation__Group__36428 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group_3__0_in_rule__KeyFloatValueAnnotation__Group__3__Impl6455 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group_3__0__Impl_in_rule__KeyFloatValueAnnotation__Group_3__06494 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group_3__1_in_rule__KeyFloatValueAnnotation__Group_3__06497 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__KeyFloatValueAnnotation__Group_3__0__Impl6525 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group_3__1__Impl_in_rule__KeyFloatValueAnnotation__Group_3__16556 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group_3__2_in_rule__KeyFloatValueAnnotation__Group_3__16559 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1_in_rule__KeyFloatValueAnnotation__Group_3__1__Impl6586 = new BitSet(new long[]{0x0000000000400042L});
        public static final BitSet FOLLOW_rule__KeyFloatValueAnnotation__Group_3__2__Impl_in_rule__KeyFloatValueAnnotation__Group_3__26617 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__KeyFloatValueAnnotation__Group_3__2__Impl6645 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ImportAnnotation__Group__0__Impl_in_rule__ImportAnnotation__Group__06682 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_rule__ImportAnnotation__Group__1_in_rule__ImportAnnotation__Group__06685 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_rule__ImportAnnotation__Group__0__Impl6713 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ImportAnnotation__Group__1__Impl_in_rule__ImportAnnotation__Group__16744 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ImportAnnotation__ImportURIAssignment_1_in_rule__ImportAnnotation__Group__1__Impl6771 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group__0__Impl_in_rule__ExtendedID__Group__06805 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group__1_in_rule__ExtendedID__Group__06808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__ExtendedID__Group__0__Impl6835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group__1__Impl_in_rule__ExtendedID__Group__16864 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group_1__0_in_rule__ExtendedID__Group__1__Impl6891 = new BitSet(new long[]{0x0000000010000002L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group_1__0__Impl_in_rule__ExtendedID__Group_1__06926 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group_1__1_in_rule__ExtendedID__Group_1__06929 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_rule__ExtendedID__Group_1__0__Impl6957 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ExtendedID__Group_1__1__Impl_in_rule__ExtendedID__Group_1__16988 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__ExtendedID__Group_1__1__Impl7015 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleImportAnnotation_in_rule__TopLevelEntity__AnnotationsAssignment_17053 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__TopLevelEntity__AnnotationsAssignment_2_07084 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__TopLevelEntity__IdAssignment_2_27115 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__TopLevelEntity__NameAssignment_2_37146 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_07177 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_17208 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_27239 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_37270 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Entity__AnnotationsAssignment_17301 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Entity__IdAssignment_37332 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Entity__NameAssignment_47363 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_rule__Entity__ChildEntitiesAssignment_5_0_1_07394 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_rule__Entity__ChildLinksAssignment_5_0_1_17425 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_rule__Entity__ChildPortsAssignment_5_0_1_27456 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_rule__Entity__ChildRelationsAssignment_5_0_1_37487 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Link__AnnotationsAssignment_07518 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Link__NameAssignment_27549 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Link__SourceAssignment_37584 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Link__TargetAssignment_57623 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Port__AnnotationsAssignment_17658 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Port__IdAssignment_37689 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Port__NameAssignment_47720 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__Relation__AnnotationsAssignment_17751 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__Relation__IdAssignment_37782 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__Relation__NameAssignment_47813 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_COMMENT_ANNOTATION_in_rule__CommentAnnotation__ValueAssignment7844 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_rule__TagAnnotation__NameAssignment_17875 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__TagAnnotation__AnnotationsAssignment_2_17906 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_rule__KeyStringValueAnnotation__NameAssignment_17937 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rule__KeyStringValueAnnotation__ValueAssignment_27968 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__KeyStringValueAnnotation__AnnotationsAssignment_3_17999 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_rule__TypedKeyStringValueAnnotation__NameAssignment_18030 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_rule__TypedKeyStringValueAnnotation__TypeAssignment_38061 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rule__TypedKeyStringValueAnnotation__ValueAssignment_58092 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__TypedKeyStringValueAnnotation__AnnotationsAssignment_6_18123 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_rule__KeyBooleanValueAnnotation__NameAssignment_18154 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__KeyBooleanValueAnnotation__ValueAssignment_28185 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_18216 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_rule__KeyIntValueAnnotation__NameAssignment_18247 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__KeyIntValueAnnotation__ValueAssignment_28278 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__KeyIntValueAnnotation__AnnotationsAssignment_3_18309 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_rule__KeyFloatValueAnnotation__NameAssignment_18340 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_FLOAT_in_rule__KeyFloatValueAnnotation__ValueAssignment_28371 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_18402 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__ImportAnnotation__ImportURIAssignment_18433 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0_in_synpred2_InternalKaom1091 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1_in_synpred3_InternalKaom1109 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2_in_synpred4_InternalKaom1127 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildEntitiesAssignment_5_0_1_0_in_synpred6_InternalKaom1231 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildLinksAssignment_5_0_1_1_in_synpred7_InternalKaom1249 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__Entity__ChildPortsAssignment_5_0_1_2_in_synpred8_InternalKaom1267 = new BitSet(new long[]{0x0000000000000002L});
    }


}