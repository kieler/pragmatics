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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_SIGNED_INT", "RULE_FLOAT", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'true'", "'false'", "'{'", "'}'", "'ref'", "'label'", "':'", "'layout'", "'['", "']'", "'position'", "','", "'size'", "'incoming'", "'outgoing'", "'start'", "'end'", "'bends'", "'|'", "'section'", "'->'", "'.'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=7;
    public static final int RULE_WS=11;
    public static final int RULE_SIGNED_INT=5;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int RULE_FLOAT=6;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalLayoutConfigParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalLayoutConfigParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalLayoutConfigParser.tokenNames; }
    public String getGrammarFileName() { return "InternalLayoutConfig.g"; }


     
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




    // $ANTLR start "entryRuleRootNode"
    // InternalLayoutConfig.g:60:1: entryRuleRootNode : ruleRootNode EOF ;
    public final void entryRuleRootNode() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:61:1: ( ruleRootNode EOF )
            // InternalLayoutConfig.g:62:1: ruleRootNode EOF
            {
             before(grammarAccess.getRootNodeRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleRootNode();

            state._fsp--;

             after(grammarAccess.getRootNodeRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleRootNode"


    // $ANTLR start "ruleRootNode"
    // InternalLayoutConfig.g:69:1: ruleRootNode : ( ( rule__RootNode__Group__0 ) ) ;
    public final void ruleRootNode() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:73:2: ( ( ( rule__RootNode__Group__0 ) ) )
            // InternalLayoutConfig.g:74:1: ( ( rule__RootNode__Group__0 ) )
            {
            // InternalLayoutConfig.g:74:1: ( ( rule__RootNode__Group__0 ) )
            // InternalLayoutConfig.g:75:1: ( rule__RootNode__Group__0 )
            {
             before(grammarAccess.getRootNodeAccess().getGroup()); 
            // InternalLayoutConfig.g:76:1: ( rule__RootNode__Group__0 )
            // InternalLayoutConfig.g:76:2: rule__RootNode__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RootNode__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRootNodeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRootNode"


    // $ANTLR start "entryRuleElkNode"
    // InternalLayoutConfig.g:88:1: entryRuleElkNode : ruleElkNode EOF ;
    public final void entryRuleElkNode() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:89:1: ( ruleElkNode EOF )
            // InternalLayoutConfig.g:90:1: ruleElkNode EOF
            {
             before(grammarAccess.getElkNodeRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleElkNode();

            state._fsp--;

             after(grammarAccess.getElkNodeRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkNode"


    // $ANTLR start "ruleElkNode"
    // InternalLayoutConfig.g:97:1: ruleElkNode : ( ( rule__ElkNode__Group__0 ) ) ;
    public final void ruleElkNode() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:101:2: ( ( ( rule__ElkNode__Group__0 ) ) )
            // InternalLayoutConfig.g:102:1: ( ( rule__ElkNode__Group__0 ) )
            {
            // InternalLayoutConfig.g:102:1: ( ( rule__ElkNode__Group__0 ) )
            // InternalLayoutConfig.g:103:1: ( rule__ElkNode__Group__0 )
            {
             before(grammarAccess.getElkNodeAccess().getGroup()); 
            // InternalLayoutConfig.g:104:1: ( rule__ElkNode__Group__0 )
            // InternalLayoutConfig.g:104:2: rule__ElkNode__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkNodeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElkNode"


    // $ANTLR start "entryRuleRefElkNode"
    // InternalLayoutConfig.g:116:1: entryRuleRefElkNode : ruleRefElkNode EOF ;
    public final void entryRuleRefElkNode() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:117:1: ( ruleRefElkNode EOF )
            // InternalLayoutConfig.g:118:1: ruleRefElkNode EOF
            {
             before(grammarAccess.getRefElkNodeRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleRefElkNode();

            state._fsp--;

             after(grammarAccess.getRefElkNodeRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleRefElkNode"


    // $ANTLR start "ruleRefElkNode"
    // InternalLayoutConfig.g:125:1: ruleRefElkNode : ( ( rule__RefElkNode__Group__0 ) ) ;
    public final void ruleRefElkNode() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:129:2: ( ( ( rule__RefElkNode__Group__0 ) ) )
            // InternalLayoutConfig.g:130:1: ( ( rule__RefElkNode__Group__0 ) )
            {
            // InternalLayoutConfig.g:130:1: ( ( rule__RefElkNode__Group__0 ) )
            // InternalLayoutConfig.g:131:1: ( rule__RefElkNode__Group__0 )
            {
             before(grammarAccess.getRefElkNodeAccess().getGroup()); 
            // InternalLayoutConfig.g:132:1: ( rule__RefElkNode__Group__0 )
            // InternalLayoutConfig.g:132:2: rule__RefElkNode__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RefElkNode__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRefElkNodeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRefElkNode"


    // $ANTLR start "entryRuleElkLabel"
    // InternalLayoutConfig.g:144:1: entryRuleElkLabel : ruleElkLabel EOF ;
    public final void entryRuleElkLabel() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:145:1: ( ruleElkLabel EOF )
            // InternalLayoutConfig.g:146:1: ruleElkLabel EOF
            {
             before(grammarAccess.getElkLabelRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkLabelRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkLabel"


    // $ANTLR start "ruleElkLabel"
    // InternalLayoutConfig.g:153:1: ruleElkLabel : ( ( rule__ElkLabel__Group__0 ) ) ;
    public final void ruleElkLabel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:157:2: ( ( ( rule__ElkLabel__Group__0 ) ) )
            // InternalLayoutConfig.g:158:1: ( ( rule__ElkLabel__Group__0 ) )
            {
            // InternalLayoutConfig.g:158:1: ( ( rule__ElkLabel__Group__0 ) )
            // InternalLayoutConfig.g:159:1: ( rule__ElkLabel__Group__0 )
            {
             before(grammarAccess.getElkLabelAccess().getGroup()); 
            // InternalLayoutConfig.g:160:1: ( rule__ElkLabel__Group__0 )
            // InternalLayoutConfig.g:160:2: rule__ElkLabel__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkLabelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElkLabel"


    // $ANTLR start "ruleShapeLayout"
    // InternalLayoutConfig.g:175:1: ruleShapeLayout : ( ( rule__ShapeLayout__Group__0 ) ) ;
    public final void ruleShapeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:179:2: ( ( ( rule__ShapeLayout__Group__0 ) ) )
            // InternalLayoutConfig.g:180:1: ( ( rule__ShapeLayout__Group__0 ) )
            {
            // InternalLayoutConfig.g:180:1: ( ( rule__ShapeLayout__Group__0 ) )
            // InternalLayoutConfig.g:181:1: ( rule__ShapeLayout__Group__0 )
            {
             before(grammarAccess.getShapeLayoutAccess().getGroup()); 
            // InternalLayoutConfig.g:182:1: ( rule__ShapeLayout__Group__0 )
            // InternalLayoutConfig.g:182:2: rule__ShapeLayout__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleShapeLayout"


    // $ANTLR start "ruleEdgeLayout"
    // InternalLayoutConfig.g:197:1: ruleEdgeLayout : ( ( rule__EdgeLayout__Group__0 ) ) ;
    public final void ruleEdgeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:201:2: ( ( ( rule__EdgeLayout__Group__0 ) ) )
            // InternalLayoutConfig.g:202:1: ( ( rule__EdgeLayout__Group__0 ) )
            {
            // InternalLayoutConfig.g:202:1: ( ( rule__EdgeLayout__Group__0 ) )
            // InternalLayoutConfig.g:203:1: ( rule__EdgeLayout__Group__0 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getGroup()); 
            // InternalLayoutConfig.g:204:1: ( rule__EdgeLayout__Group__0 )
            // InternalLayoutConfig.g:204:2: rule__EdgeLayout__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EdgeLayout__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEdgeLayoutAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEdgeLayout"


    // $ANTLR start "entryRuleElkSingleEdgeSection"
    // InternalLayoutConfig.g:216:1: entryRuleElkSingleEdgeSection : ruleElkSingleEdgeSection EOF ;
    public final void entryRuleElkSingleEdgeSection() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:217:1: ( ruleElkSingleEdgeSection EOF )
            // InternalLayoutConfig.g:218:1: ruleElkSingleEdgeSection EOF
            {
             before(grammarAccess.getElkSingleEdgeSectionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleElkSingleEdgeSection();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkSingleEdgeSection"


    // $ANTLR start "ruleElkSingleEdgeSection"
    // InternalLayoutConfig.g:225:1: ruleElkSingleEdgeSection : ( ( rule__ElkSingleEdgeSection__Group__0 ) ) ;
    public final void ruleElkSingleEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:229:2: ( ( ( rule__ElkSingleEdgeSection__Group__0 ) ) )
            // InternalLayoutConfig.g:230:1: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            {
            // InternalLayoutConfig.g:230:1: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            // InternalLayoutConfig.g:231:1: ( rule__ElkSingleEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup()); 
            // InternalLayoutConfig.g:232:1: ( rule__ElkSingleEdgeSection__Group__0 )
            // InternalLayoutConfig.g:232:2: rule__ElkSingleEdgeSection__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElkSingleEdgeSection"


    // $ANTLR start "entryRuleElkEdgeSection"
    // InternalLayoutConfig.g:244:1: entryRuleElkEdgeSection : ruleElkEdgeSection EOF ;
    public final void entryRuleElkEdgeSection() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:245:1: ( ruleElkEdgeSection EOF )
            // InternalLayoutConfig.g:246:1: ruleElkEdgeSection EOF
            {
             before(grammarAccess.getElkEdgeSectionRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleElkEdgeSection();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkEdgeSection"


    // $ANTLR start "ruleElkEdgeSection"
    // InternalLayoutConfig.g:253:1: ruleElkEdgeSection : ( ( rule__ElkEdgeSection__Group__0 ) ) ;
    public final void ruleElkEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:257:2: ( ( ( rule__ElkEdgeSection__Group__0 ) ) )
            // InternalLayoutConfig.g:258:1: ( ( rule__ElkEdgeSection__Group__0 ) )
            {
            // InternalLayoutConfig.g:258:1: ( ( rule__ElkEdgeSection__Group__0 ) )
            // InternalLayoutConfig.g:259:1: ( rule__ElkEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup()); 
            // InternalLayoutConfig.g:260:1: ( rule__ElkEdgeSection__Group__0 )
            // InternalLayoutConfig.g:260:2: rule__ElkEdgeSection__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElkEdgeSection"


    // $ANTLR start "entryRuleElkBendPoint"
    // InternalLayoutConfig.g:272:1: entryRuleElkBendPoint : ruleElkBendPoint EOF ;
    public final void entryRuleElkBendPoint() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:273:1: ( ruleElkBendPoint EOF )
            // InternalLayoutConfig.g:274:1: ruleElkBendPoint EOF
            {
             before(grammarAccess.getElkBendPointRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkBendPointRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkBendPoint"


    // $ANTLR start "ruleElkBendPoint"
    // InternalLayoutConfig.g:281:1: ruleElkBendPoint : ( ( rule__ElkBendPoint__Group__0 ) ) ;
    public final void ruleElkBendPoint() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:285:2: ( ( ( rule__ElkBendPoint__Group__0 ) ) )
            // InternalLayoutConfig.g:286:1: ( ( rule__ElkBendPoint__Group__0 ) )
            {
            // InternalLayoutConfig.g:286:1: ( ( rule__ElkBendPoint__Group__0 ) )
            // InternalLayoutConfig.g:287:1: ( rule__ElkBendPoint__Group__0 )
            {
             before(grammarAccess.getElkBendPointAccess().getGroup()); 
            // InternalLayoutConfig.g:288:1: ( rule__ElkBendPoint__Group__0 )
            // InternalLayoutConfig.g:288:2: rule__ElkBendPoint__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkBendPoint__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkBendPointAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElkBendPoint"


    // $ANTLR start "entryRuleQualifiedId"
    // InternalLayoutConfig.g:300:1: entryRuleQualifiedId : ruleQualifiedId EOF ;
    public final void entryRuleQualifiedId() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:301:1: ( ruleQualifiedId EOF )
            // InternalLayoutConfig.g:302:1: ruleQualifiedId EOF
            {
             before(grammarAccess.getQualifiedIdRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getQualifiedIdRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleQualifiedId"


    // $ANTLR start "ruleQualifiedId"
    // InternalLayoutConfig.g:309:1: ruleQualifiedId : ( ( rule__QualifiedId__Group__0 ) ) ;
    public final void ruleQualifiedId() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:313:2: ( ( ( rule__QualifiedId__Group__0 ) ) )
            // InternalLayoutConfig.g:314:1: ( ( rule__QualifiedId__Group__0 ) )
            {
            // InternalLayoutConfig.g:314:1: ( ( rule__QualifiedId__Group__0 ) )
            // InternalLayoutConfig.g:315:1: ( rule__QualifiedId__Group__0 )
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup()); 
            // InternalLayoutConfig.g:316:1: ( rule__QualifiedId__Group__0 )
            // InternalLayoutConfig.g:316:2: rule__QualifiedId__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__QualifiedId__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedIdAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedId"


    // $ANTLR start "entryRuleNumber"
    // InternalLayoutConfig.g:328:1: entryRuleNumber : ruleNumber EOF ;
    public final void entryRuleNumber() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:329:1: ( ruleNumber EOF )
            // InternalLayoutConfig.g:330:1: ruleNumber EOF
            {
             before(grammarAccess.getNumberRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getNumberRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleNumber"


    // $ANTLR start "ruleNumber"
    // InternalLayoutConfig.g:337:1: ruleNumber : ( ( rule__Number__Alternatives ) ) ;
    public final void ruleNumber() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:341:2: ( ( ( rule__Number__Alternatives ) ) )
            // InternalLayoutConfig.g:342:1: ( ( rule__Number__Alternatives ) )
            {
            // InternalLayoutConfig.g:342:1: ( ( rule__Number__Alternatives ) )
            // InternalLayoutConfig.g:343:1: ( rule__Number__Alternatives )
            {
             before(grammarAccess.getNumberAccess().getAlternatives()); 
            // InternalLayoutConfig.g:344:1: ( rule__Number__Alternatives )
            // InternalLayoutConfig.g:344:2: rule__Number__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Number__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getNumberAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNumber"


    // $ANTLR start "entryRuleProperty"
    // InternalLayoutConfig.g:356:1: entryRuleProperty : ruleProperty EOF ;
    public final void entryRuleProperty() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:357:1: ( ruleProperty EOF )
            // InternalLayoutConfig.g:358:1: ruleProperty EOF
            {
             before(grammarAccess.getPropertyRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getPropertyRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalLayoutConfig.g:365:1: ruleProperty : ( ( rule__Property__Group__0 ) ) ;
    public final void ruleProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:369:2: ( ( ( rule__Property__Group__0 ) ) )
            // InternalLayoutConfig.g:370:1: ( ( rule__Property__Group__0 ) )
            {
            // InternalLayoutConfig.g:370:1: ( ( rule__Property__Group__0 ) )
            // InternalLayoutConfig.g:371:1: ( rule__Property__Group__0 )
            {
             before(grammarAccess.getPropertyAccess().getGroup()); 
            // InternalLayoutConfig.g:372:1: ( rule__Property__Group__0 )
            // InternalLayoutConfig.g:372:2: rule__Property__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Property__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRulePropertyKey"
    // InternalLayoutConfig.g:384:1: entryRulePropertyKey : rulePropertyKey EOF ;
    public final void entryRulePropertyKey() throws RecognitionException {

        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalLayoutConfig.g:388:1: ( rulePropertyKey EOF )
            // InternalLayoutConfig.g:389:1: rulePropertyKey EOF
            {
             before(grammarAccess.getPropertyKeyRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            rulePropertyKey();

            state._fsp--;

             after(grammarAccess.getPropertyKeyRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	myHiddenTokenState.restore();

        }
        return ;
    }
    // $ANTLR end "entryRulePropertyKey"


    // $ANTLR start "rulePropertyKey"
    // InternalLayoutConfig.g:399:1: rulePropertyKey : ( ( rule__PropertyKey__Group__0 ) ) ;
    public final void rulePropertyKey() throws RecognitionException {

        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:404:2: ( ( ( rule__PropertyKey__Group__0 ) ) )
            // InternalLayoutConfig.g:405:1: ( ( rule__PropertyKey__Group__0 ) )
            {
            // InternalLayoutConfig.g:405:1: ( ( rule__PropertyKey__Group__0 ) )
            // InternalLayoutConfig.g:406:1: ( rule__PropertyKey__Group__0 )
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup()); 
            // InternalLayoutConfig.g:407:1: ( rule__PropertyKey__Group__0 )
            // InternalLayoutConfig.g:407:2: rule__PropertyKey__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PropertyKey__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyKeyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);
            	myHiddenTokenState.restore();

        }
        return ;
    }
    // $ANTLR end "rulePropertyKey"


    // $ANTLR start "entryRuleStringValue"
    // InternalLayoutConfig.g:420:1: entryRuleStringValue : ruleStringValue EOF ;
    public final void entryRuleStringValue() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:421:1: ( ruleStringValue EOF )
            // InternalLayoutConfig.g:422:1: ruleStringValue EOF
            {
             before(grammarAccess.getStringValueRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleStringValue();

            state._fsp--;

             after(grammarAccess.getStringValueRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleStringValue"


    // $ANTLR start "ruleStringValue"
    // InternalLayoutConfig.g:429:1: ruleStringValue : ( RULE_STRING ) ;
    public final void ruleStringValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:433:2: ( ( RULE_STRING ) )
            // InternalLayoutConfig.g:434:1: ( RULE_STRING )
            {
            // InternalLayoutConfig.g:434:1: ( RULE_STRING )
            // InternalLayoutConfig.g:435:1: RULE_STRING
            {
             before(grammarAccess.getStringValueAccess().getSTRINGTerminalRuleCall()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getStringValueAccess().getSTRINGTerminalRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStringValue"


    // $ANTLR start "entryRuleQualifiedIdValue"
    // InternalLayoutConfig.g:448:1: entryRuleQualifiedIdValue : ruleQualifiedIdValue EOF ;
    public final void entryRuleQualifiedIdValue() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:449:1: ( ruleQualifiedIdValue EOF )
            // InternalLayoutConfig.g:450:1: ruleQualifiedIdValue EOF
            {
             before(grammarAccess.getQualifiedIdValueRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleQualifiedIdValue();

            state._fsp--;

             after(grammarAccess.getQualifiedIdValueRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleQualifiedIdValue"


    // $ANTLR start "ruleQualifiedIdValue"
    // InternalLayoutConfig.g:457:1: ruleQualifiedIdValue : ( ruleQualifiedId ) ;
    public final void ruleQualifiedIdValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:461:2: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:462:1: ( ruleQualifiedId )
            {
            // InternalLayoutConfig.g:462:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:463:1: ruleQualifiedId
            {
             before(grammarAccess.getQualifiedIdValueAccess().getQualifiedIdParserRuleCall()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getQualifiedIdValueAccess().getQualifiedIdParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedIdValue"


    // $ANTLR start "entryRuleNumberValue"
    // InternalLayoutConfig.g:476:1: entryRuleNumberValue : ruleNumberValue EOF ;
    public final void entryRuleNumberValue() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:477:1: ( ruleNumberValue EOF )
            // InternalLayoutConfig.g:478:1: ruleNumberValue EOF
            {
             before(grammarAccess.getNumberValueRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleNumberValue();

            state._fsp--;

             after(grammarAccess.getNumberValueRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleNumberValue"


    // $ANTLR start "ruleNumberValue"
    // InternalLayoutConfig.g:485:1: ruleNumberValue : ( ( rule__NumberValue__Alternatives ) ) ;
    public final void ruleNumberValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:489:2: ( ( ( rule__NumberValue__Alternatives ) ) )
            // InternalLayoutConfig.g:490:1: ( ( rule__NumberValue__Alternatives ) )
            {
            // InternalLayoutConfig.g:490:1: ( ( rule__NumberValue__Alternatives ) )
            // InternalLayoutConfig.g:491:1: ( rule__NumberValue__Alternatives )
            {
             before(grammarAccess.getNumberValueAccess().getAlternatives()); 
            // InternalLayoutConfig.g:492:1: ( rule__NumberValue__Alternatives )
            // InternalLayoutConfig.g:492:2: rule__NumberValue__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NumberValue__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getNumberValueAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNumberValue"


    // $ANTLR start "entryRuleBooleanValue"
    // InternalLayoutConfig.g:504:1: entryRuleBooleanValue : ruleBooleanValue EOF ;
    public final void entryRuleBooleanValue() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:505:1: ( ruleBooleanValue EOF )
            // InternalLayoutConfig.g:506:1: ruleBooleanValue EOF
            {
             before(grammarAccess.getBooleanValueRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleBooleanValue();

            state._fsp--;

             after(grammarAccess.getBooleanValueRule()); 
            match(input,EOF,FollowSets000.FOLLOW_2); 

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
    // $ANTLR end "entryRuleBooleanValue"


    // $ANTLR start "ruleBooleanValue"
    // InternalLayoutConfig.g:513:1: ruleBooleanValue : ( ( rule__BooleanValue__Alternatives ) ) ;
    public final void ruleBooleanValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:517:2: ( ( ( rule__BooleanValue__Alternatives ) ) )
            // InternalLayoutConfig.g:518:1: ( ( rule__BooleanValue__Alternatives ) )
            {
            // InternalLayoutConfig.g:518:1: ( ( rule__BooleanValue__Alternatives ) )
            // InternalLayoutConfig.g:519:1: ( rule__BooleanValue__Alternatives )
            {
             before(grammarAccess.getBooleanValueAccess().getAlternatives()); 
            // InternalLayoutConfig.g:520:1: ( rule__BooleanValue__Alternatives )
            // InternalLayoutConfig.g:520:2: rule__BooleanValue__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BooleanValue__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBooleanValueAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBooleanValue"


    // $ANTLR start "rule__EdgeLayout__Alternatives_2"
    // InternalLayoutConfig.g:532:1: rule__EdgeLayout__Alternatives_2 : ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) );
    public final void rule__EdgeLayout__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:536:1: ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==EOF||LA2_0==22||(LA2_0>=26 && LA2_0<=30)) ) {
                alt2=1;
            }
            else if ( (LA2_0==32) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalLayoutConfig.g:537:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    {
                    // InternalLayoutConfig.g:537:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    // InternalLayoutConfig.g:538:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0()); 
                    // InternalLayoutConfig.g:539:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    // InternalLayoutConfig.g:539:2: rule__EdgeLayout__SectionsAssignment_2_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__EdgeLayout__SectionsAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:543:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    {
                    // InternalLayoutConfig.g:543:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    // InternalLayoutConfig.g:544:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    {
                    // InternalLayoutConfig.g:544:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) )
                    // InternalLayoutConfig.g:545:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalLayoutConfig.g:546:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    // InternalLayoutConfig.g:546:2: rule__EdgeLayout__SectionsAssignment_2_1
                    {
                    pushFollow(FollowSets000.FOLLOW_3);
                    rule__EdgeLayout__SectionsAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 

                    }

                    // InternalLayoutConfig.g:549:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    // InternalLayoutConfig.g:550:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalLayoutConfig.g:551:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==32) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:551:2: rule__EdgeLayout__SectionsAssignment_2_1
                    	    {
                    	    pushFollow(FollowSets000.FOLLOW_3);
                    	    rule__EdgeLayout__SectionsAssignment_2_1();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 

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
    // $ANTLR end "rule__EdgeLayout__Alternatives_2"


    // $ANTLR start "rule__Number__Alternatives"
    // InternalLayoutConfig.g:561:1: rule__Number__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__Number__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:565:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_SIGNED_INT) ) {
                alt3=1;
            }
            else if ( (LA3_0==RULE_FLOAT) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalLayoutConfig.g:566:1: ( RULE_SIGNED_INT )
                    {
                    // InternalLayoutConfig.g:566:1: ( RULE_SIGNED_INT )
                    // InternalLayoutConfig.g:567:1: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:572:6: ( RULE_FLOAT )
                    {
                    // InternalLayoutConfig.g:572:6: ( RULE_FLOAT )
                    // InternalLayoutConfig.g:573:1: RULE_FLOAT
                    {
                     before(grammarAccess.getNumberAccess().getFLOATTerminalRuleCall_1()); 
                    match(input,RULE_FLOAT,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getNumberAccess().getFLOATTerminalRuleCall_1()); 

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
    // $ANTLR end "rule__Number__Alternatives"


    // $ANTLR start "rule__Property__Alternatives_2"
    // InternalLayoutConfig.g:583:1: rule__Property__Alternatives_2 : ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) );
    public final void rule__Property__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:587:1: ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) )
            int alt4=4;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt4=1;
                }
                break;
            case RULE_ID:
                {
                alt4=2;
                }
                break;
            case RULE_SIGNED_INT:
            case RULE_FLOAT:
                {
                alt4=3;
                }
                break;
            case 13:
            case 14:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalLayoutConfig.g:588:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    {
                    // InternalLayoutConfig.g:588:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    // InternalLayoutConfig.g:589:1: ( rule__Property__ValueAssignment_2_0 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_0()); 
                    // InternalLayoutConfig.g:590:1: ( rule__Property__ValueAssignment_2_0 )
                    // InternalLayoutConfig.g:590:2: rule__Property__ValueAssignment_2_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Property__ValueAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:594:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    {
                    // InternalLayoutConfig.g:594:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    // InternalLayoutConfig.g:595:1: ( rule__Property__ValueAssignment_2_1 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_1()); 
                    // InternalLayoutConfig.g:596:1: ( rule__Property__ValueAssignment_2_1 )
                    // InternalLayoutConfig.g:596:2: rule__Property__ValueAssignment_2_1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Property__ValueAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalLayoutConfig.g:600:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    {
                    // InternalLayoutConfig.g:600:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    // InternalLayoutConfig.g:601:1: ( rule__Property__ValueAssignment_2_2 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_2()); 
                    // InternalLayoutConfig.g:602:1: ( rule__Property__ValueAssignment_2_2 )
                    // InternalLayoutConfig.g:602:2: rule__Property__ValueAssignment_2_2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Property__ValueAssignment_2_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalLayoutConfig.g:606:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    {
                    // InternalLayoutConfig.g:606:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    // InternalLayoutConfig.g:607:1: ( rule__Property__ValueAssignment_2_3 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 
                    // InternalLayoutConfig.g:608:1: ( rule__Property__ValueAssignment_2_3 )
                    // InternalLayoutConfig.g:608:2: rule__Property__ValueAssignment_2_3
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Property__ValueAssignment_2_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 

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
    // $ANTLR end "rule__Property__Alternatives_2"


    // $ANTLR start "rule__NumberValue__Alternatives"
    // InternalLayoutConfig.g:617:1: rule__NumberValue__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__NumberValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:621:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_SIGNED_INT) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_FLOAT) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalLayoutConfig.g:622:1: ( RULE_SIGNED_INT )
                    {
                    // InternalLayoutConfig.g:622:1: ( RULE_SIGNED_INT )
                    // InternalLayoutConfig.g:623:1: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:628:6: ( RULE_FLOAT )
                    {
                    // InternalLayoutConfig.g:628:6: ( RULE_FLOAT )
                    // InternalLayoutConfig.g:629:1: RULE_FLOAT
                    {
                     before(grammarAccess.getNumberValueAccess().getFLOATTerminalRuleCall_1()); 
                    match(input,RULE_FLOAT,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getNumberValueAccess().getFLOATTerminalRuleCall_1()); 

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
    // $ANTLR end "rule__NumberValue__Alternatives"


    // $ANTLR start "rule__BooleanValue__Alternatives"
    // InternalLayoutConfig.g:639:1: rule__BooleanValue__Alternatives : ( ( 'true' ) | ( 'false' ) );
    public final void rule__BooleanValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:643:1: ( ( 'true' ) | ( 'false' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==13) ) {
                alt6=1;
            }
            else if ( (LA6_0==14) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalLayoutConfig.g:644:1: ( 'true' )
                    {
                    // InternalLayoutConfig.g:644:1: ( 'true' )
                    // InternalLayoutConfig.g:645:1: 'true'
                    {
                     before(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 
                    match(input,13,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:652:6: ( 'false' )
                    {
                    // InternalLayoutConfig.g:652:6: ( 'false' )
                    // InternalLayoutConfig.g:653:1: 'false'
                    {
                     before(grammarAccess.getBooleanValueAccess().getFalseKeyword_1()); 
                    match(input,14,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getBooleanValueAccess().getFalseKeyword_1()); 

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
    // $ANTLR end "rule__BooleanValue__Alternatives"


    // $ANTLR start "rule__RootNode__Group__0"
    // InternalLayoutConfig.g:667:1: rule__RootNode__Group__0 : rule__RootNode__Group__0__Impl rule__RootNode__Group__1 ;
    public final void rule__RootNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:671:1: ( rule__RootNode__Group__0__Impl rule__RootNode__Group__1 )
            // InternalLayoutConfig.g:672:2: rule__RootNode__Group__0__Impl rule__RootNode__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__RootNode__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__RootNode__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RootNode__Group__0"


    // $ANTLR start "rule__RootNode__Group__0__Impl"
    // InternalLayoutConfig.g:679:1: rule__RootNode__Group__0__Impl : ( () ) ;
    public final void rule__RootNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:683:1: ( ( () ) )
            // InternalLayoutConfig.g:684:1: ( () )
            {
            // InternalLayoutConfig.g:684:1: ( () )
            // InternalLayoutConfig.g:685:1: ()
            {
             before(grammarAccess.getRootNodeAccess().getElkNodeAction_0()); 
            // InternalLayoutConfig.g:686:1: ()
            // InternalLayoutConfig.g:688:1: 
            {
            }

             after(grammarAccess.getRootNodeAccess().getElkNodeAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RootNode__Group__0__Impl"


    // $ANTLR start "rule__RootNode__Group__1"
    // InternalLayoutConfig.g:698:1: rule__RootNode__Group__1 : rule__RootNode__Group__1__Impl ;
    public final void rule__RootNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:702:1: ( rule__RootNode__Group__1__Impl )
            // InternalLayoutConfig.g:703:2: rule__RootNode__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RootNode__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RootNode__Group__1"


    // $ANTLR start "rule__RootNode__Group__1__Impl"
    // InternalLayoutConfig.g:709:1: rule__RootNode__Group__1__Impl : ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) ) ;
    public final void rule__RootNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:713:1: ( ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) ) )
            // InternalLayoutConfig.g:714:1: ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) )
            {
            // InternalLayoutConfig.g:714:1: ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) )
            // InternalLayoutConfig.g:715:1: ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* )
            {
            // InternalLayoutConfig.g:715:1: ( ( rule__RootNode__ChildrenAssignment_1 ) )
            // InternalLayoutConfig.g:716:1: ( rule__RootNode__ChildrenAssignment_1 )
            {
             before(grammarAccess.getRootNodeAccess().getChildrenAssignment_1()); 
            // InternalLayoutConfig.g:717:1: ( rule__RootNode__ChildrenAssignment_1 )
            // InternalLayoutConfig.g:717:2: rule__RootNode__ChildrenAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_5);
            rule__RootNode__ChildrenAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRootNodeAccess().getChildrenAssignment_1()); 

            }

            // InternalLayoutConfig.g:720:1: ( ( rule__RootNode__ChildrenAssignment_1 )* )
            // InternalLayoutConfig.g:721:1: ( rule__RootNode__ChildrenAssignment_1 )*
            {
             before(grammarAccess.getRootNodeAccess().getChildrenAssignment_1()); 
            // InternalLayoutConfig.g:722:1: ( rule__RootNode__ChildrenAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalLayoutConfig.g:722:2: rule__RootNode__ChildrenAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__RootNode__ChildrenAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getRootNodeAccess().getChildrenAssignment_1()); 

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
    // $ANTLR end "rule__RootNode__Group__1__Impl"


    // $ANTLR start "rule__ElkNode__Group__0"
    // InternalLayoutConfig.g:737:1: rule__ElkNode__Group__0 : rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 ;
    public final void rule__ElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:741:1: ( rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 )
            // InternalLayoutConfig.g:742:2: rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__ElkNode__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__0"


    // $ANTLR start "rule__ElkNode__Group__0__Impl"
    // InternalLayoutConfig.g:749:1: rule__ElkNode__Group__0__Impl : ( ( rule__ElkNode__IdentifierAssignment_0 ) ) ;
    public final void rule__ElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:753:1: ( ( ( rule__ElkNode__IdentifierAssignment_0 ) ) )
            // InternalLayoutConfig.g:754:1: ( ( rule__ElkNode__IdentifierAssignment_0 ) )
            {
            // InternalLayoutConfig.g:754:1: ( ( rule__ElkNode__IdentifierAssignment_0 ) )
            // InternalLayoutConfig.g:755:1: ( rule__ElkNode__IdentifierAssignment_0 )
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierAssignment_0()); 
            // InternalLayoutConfig.g:756:1: ( rule__ElkNode__IdentifierAssignment_0 )
            // InternalLayoutConfig.g:756:2: rule__ElkNode__IdentifierAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__IdentifierAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getElkNodeAccess().getIdentifierAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__0__Impl"


    // $ANTLR start "rule__ElkNode__Group__1"
    // InternalLayoutConfig.g:766:1: rule__ElkNode__Group__1 : rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 ;
    public final void rule__ElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:770:1: ( rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 )
            // InternalLayoutConfig.g:771:2: rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__ElkNode__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__1"


    // $ANTLR start "rule__ElkNode__Group__1__Impl"
    // InternalLayoutConfig.g:778:1: rule__ElkNode__Group__1__Impl : ( '{' ) ;
    public final void rule__ElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:782:1: ( ( '{' ) )
            // InternalLayoutConfig.g:783:1: ( '{' )
            {
            // InternalLayoutConfig.g:783:1: ( '{' )
            // InternalLayoutConfig.g:784:1: '{'
            {
             before(grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,15,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__1__Impl"


    // $ANTLR start "rule__ElkNode__Group__2"
    // InternalLayoutConfig.g:797:1: rule__ElkNode__Group__2 : rule__ElkNode__Group__2__Impl rule__ElkNode__Group__3 ;
    public final void rule__ElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:801:1: ( rule__ElkNode__Group__2__Impl rule__ElkNode__Group__3 )
            // InternalLayoutConfig.g:802:2: rule__ElkNode__Group__2__Impl rule__ElkNode__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__ElkNode__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__2"


    // $ANTLR start "rule__ElkNode__Group__2__Impl"
    // InternalLayoutConfig.g:809:1: rule__ElkNode__Group__2__Impl : ( ( rule__ElkNode__PropertiesAssignment_2 )* ) ;
    public final void rule__ElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:813:1: ( ( ( rule__ElkNode__PropertiesAssignment_2 )* ) )
            // InternalLayoutConfig.g:814:1: ( ( rule__ElkNode__PropertiesAssignment_2 )* )
            {
            // InternalLayoutConfig.g:814:1: ( ( rule__ElkNode__PropertiesAssignment_2 )* )
            // InternalLayoutConfig.g:815:1: ( rule__ElkNode__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2()); 
            // InternalLayoutConfig.g:816:1: ( rule__ElkNode__PropertiesAssignment_2 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalLayoutConfig.g:816:2: rule__ElkNode__PropertiesAssignment_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__ElkNode__PropertiesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__2__Impl"


    // $ANTLR start "rule__ElkNode__Group__3"
    // InternalLayoutConfig.g:826:1: rule__ElkNode__Group__3 : rule__ElkNode__Group__3__Impl rule__ElkNode__Group__4 ;
    public final void rule__ElkNode__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:830:1: ( rule__ElkNode__Group__3__Impl rule__ElkNode__Group__4 )
            // InternalLayoutConfig.g:831:2: rule__ElkNode__Group__3__Impl rule__ElkNode__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__ElkNode__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__3"


    // $ANTLR start "rule__ElkNode__Group__3__Impl"
    // InternalLayoutConfig.g:838:1: rule__ElkNode__Group__3__Impl : ( ( rule__ElkNode__Group_3__0 )* ) ;
    public final void rule__ElkNode__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:842:1: ( ( ( rule__ElkNode__Group_3__0 )* ) )
            // InternalLayoutConfig.g:843:1: ( ( rule__ElkNode__Group_3__0 )* )
            {
            // InternalLayoutConfig.g:843:1: ( ( rule__ElkNode__Group_3__0 )* )
            // InternalLayoutConfig.g:844:1: ( rule__ElkNode__Group_3__0 )*
            {
             before(grammarAccess.getElkNodeAccess().getGroup_3()); 
            // InternalLayoutConfig.g:845:1: ( rule__ElkNode__Group_3__0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==17) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalLayoutConfig.g:845:2: rule__ElkNode__Group_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_8);
            	    rule__ElkNode__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getElkNodeAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__3__Impl"


    // $ANTLR start "rule__ElkNode__Group__4"
    // InternalLayoutConfig.g:855:1: rule__ElkNode__Group__4 : rule__ElkNode__Group__4__Impl ;
    public final void rule__ElkNode__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:859:1: ( rule__ElkNode__Group__4__Impl )
            // InternalLayoutConfig.g:860:2: rule__ElkNode__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__4"


    // $ANTLR start "rule__ElkNode__Group__4__Impl"
    // InternalLayoutConfig.g:866:1: rule__ElkNode__Group__4__Impl : ( '}' ) ;
    public final void rule__ElkNode__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:870:1: ( ( '}' ) )
            // InternalLayoutConfig.g:871:1: ( '}' )
            {
            // InternalLayoutConfig.g:871:1: ( '}' )
            // InternalLayoutConfig.g:872:1: '}'
            {
             before(grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_4()); 
            match(input,16,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group__4__Impl"


    // $ANTLR start "rule__ElkNode__Group_3__0"
    // InternalLayoutConfig.g:895:1: rule__ElkNode__Group_3__0 : rule__ElkNode__Group_3__0__Impl rule__ElkNode__Group_3__1 ;
    public final void rule__ElkNode__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:899:1: ( rule__ElkNode__Group_3__0__Impl rule__ElkNode__Group_3__1 )
            // InternalLayoutConfig.g:900:2: rule__ElkNode__Group_3__0__Impl rule__ElkNode__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkNode__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group_3__0"


    // $ANTLR start "rule__ElkNode__Group_3__0__Impl"
    // InternalLayoutConfig.g:907:1: rule__ElkNode__Group_3__0__Impl : ( 'ref' ) ;
    public final void rule__ElkNode__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:911:1: ( ( 'ref' ) )
            // InternalLayoutConfig.g:912:1: ( 'ref' )
            {
            // InternalLayoutConfig.g:912:1: ( 'ref' )
            // InternalLayoutConfig.g:913:1: 'ref'
            {
             before(grammarAccess.getElkNodeAccess().getRefKeyword_3_0()); 
            match(input,17,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getRefKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group_3__0__Impl"


    // $ANTLR start "rule__ElkNode__Group_3__1"
    // InternalLayoutConfig.g:926:1: rule__ElkNode__Group_3__1 : rule__ElkNode__Group_3__1__Impl ;
    public final void rule__ElkNode__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:930:1: ( rule__ElkNode__Group_3__1__Impl )
            // InternalLayoutConfig.g:931:2: rule__ElkNode__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group_3__1"


    // $ANTLR start "rule__ElkNode__Group_3__1__Impl"
    // InternalLayoutConfig.g:937:1: rule__ElkNode__Group_3__1__Impl : ( ( rule__ElkNode__ChildrenAssignment_3_1 ) ) ;
    public final void rule__ElkNode__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:941:1: ( ( ( rule__ElkNode__ChildrenAssignment_3_1 ) ) )
            // InternalLayoutConfig.g:942:1: ( ( rule__ElkNode__ChildrenAssignment_3_1 ) )
            {
            // InternalLayoutConfig.g:942:1: ( ( rule__ElkNode__ChildrenAssignment_3_1 ) )
            // InternalLayoutConfig.g:943:1: ( rule__ElkNode__ChildrenAssignment_3_1 )
            {
             before(grammarAccess.getElkNodeAccess().getChildrenAssignment_3_1()); 
            // InternalLayoutConfig.g:944:1: ( rule__ElkNode__ChildrenAssignment_3_1 )
            // InternalLayoutConfig.g:944:2: rule__ElkNode__ChildrenAssignment_3_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkNode__ChildrenAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkNodeAccess().getChildrenAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__Group_3__1__Impl"


    // $ANTLR start "rule__RefElkNode__Group__0"
    // InternalLayoutConfig.g:958:1: rule__RefElkNode__Group__0 : rule__RefElkNode__Group__0__Impl rule__RefElkNode__Group__1 ;
    public final void rule__RefElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:962:1: ( rule__RefElkNode__Group__0__Impl rule__RefElkNode__Group__1 )
            // InternalLayoutConfig.g:963:2: rule__RefElkNode__Group__0__Impl rule__RefElkNode__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__RefElkNode__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__RefElkNode__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__0"


    // $ANTLR start "rule__RefElkNode__Group__0__Impl"
    // InternalLayoutConfig.g:970:1: rule__RefElkNode__Group__0__Impl : ( ( rule__RefElkNode__IdentifierAssignment_0 ) ) ;
    public final void rule__RefElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:974:1: ( ( ( rule__RefElkNode__IdentifierAssignment_0 ) ) )
            // InternalLayoutConfig.g:975:1: ( ( rule__RefElkNode__IdentifierAssignment_0 ) )
            {
            // InternalLayoutConfig.g:975:1: ( ( rule__RefElkNode__IdentifierAssignment_0 ) )
            // InternalLayoutConfig.g:976:1: ( rule__RefElkNode__IdentifierAssignment_0 )
            {
             before(grammarAccess.getRefElkNodeAccess().getIdentifierAssignment_0()); 
            // InternalLayoutConfig.g:977:1: ( rule__RefElkNode__IdentifierAssignment_0 )
            // InternalLayoutConfig.g:977:2: rule__RefElkNode__IdentifierAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RefElkNode__IdentifierAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getRefElkNodeAccess().getIdentifierAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__0__Impl"


    // $ANTLR start "rule__RefElkNode__Group__1"
    // InternalLayoutConfig.g:987:1: rule__RefElkNode__Group__1 : rule__RefElkNode__Group__1__Impl rule__RefElkNode__Group__2 ;
    public final void rule__RefElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:991:1: ( rule__RefElkNode__Group__1__Impl rule__RefElkNode__Group__2 )
            // InternalLayoutConfig.g:992:2: rule__RefElkNode__Group__1__Impl rule__RefElkNode__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__RefElkNode__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__RefElkNode__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__1"


    // $ANTLR start "rule__RefElkNode__Group__1__Impl"
    // InternalLayoutConfig.g:999:1: rule__RefElkNode__Group__1__Impl : ( '{' ) ;
    public final void rule__RefElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1003:1: ( ( '{' ) )
            // InternalLayoutConfig.g:1004:1: ( '{' )
            {
            // InternalLayoutConfig.g:1004:1: ( '{' )
            // InternalLayoutConfig.g:1005:1: '{'
            {
             before(grammarAccess.getRefElkNodeAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,15,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getRefElkNodeAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__1__Impl"


    // $ANTLR start "rule__RefElkNode__Group__2"
    // InternalLayoutConfig.g:1018:1: rule__RefElkNode__Group__2 : rule__RefElkNode__Group__2__Impl rule__RefElkNode__Group__3 ;
    public final void rule__RefElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1022:1: ( rule__RefElkNode__Group__2__Impl rule__RefElkNode__Group__3 )
            // InternalLayoutConfig.g:1023:2: rule__RefElkNode__Group__2__Impl rule__RefElkNode__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__RefElkNode__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__RefElkNode__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__2"


    // $ANTLR start "rule__RefElkNode__Group__2__Impl"
    // InternalLayoutConfig.g:1030:1: rule__RefElkNode__Group__2__Impl : ( ( rule__RefElkNode__PropertiesAssignment_2 )* ) ;
    public final void rule__RefElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1034:1: ( ( ( rule__RefElkNode__PropertiesAssignment_2 )* ) )
            // InternalLayoutConfig.g:1035:1: ( ( rule__RefElkNode__PropertiesAssignment_2 )* )
            {
            // InternalLayoutConfig.g:1035:1: ( ( rule__RefElkNode__PropertiesAssignment_2 )* )
            // InternalLayoutConfig.g:1036:1: ( rule__RefElkNode__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getRefElkNodeAccess().getPropertiesAssignment_2()); 
            // InternalLayoutConfig.g:1037:1: ( rule__RefElkNode__PropertiesAssignment_2 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_ID) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalLayoutConfig.g:1037:2: rule__RefElkNode__PropertiesAssignment_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__RefElkNode__PropertiesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getRefElkNodeAccess().getPropertiesAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__2__Impl"


    // $ANTLR start "rule__RefElkNode__Group__3"
    // InternalLayoutConfig.g:1047:1: rule__RefElkNode__Group__3 : rule__RefElkNode__Group__3__Impl ;
    public final void rule__RefElkNode__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1051:1: ( rule__RefElkNode__Group__3__Impl )
            // InternalLayoutConfig.g:1052:2: rule__RefElkNode__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RefElkNode__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__3"


    // $ANTLR start "rule__RefElkNode__Group__3__Impl"
    // InternalLayoutConfig.g:1058:1: rule__RefElkNode__Group__3__Impl : ( '}' ) ;
    public final void rule__RefElkNode__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1062:1: ( ( '}' ) )
            // InternalLayoutConfig.g:1063:1: ( '}' )
            {
            // InternalLayoutConfig.g:1063:1: ( '}' )
            // InternalLayoutConfig.g:1064:1: '}'
            {
             before(grammarAccess.getRefElkNodeAccess().getRightCurlyBracketKeyword_3()); 
            match(input,16,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getRefElkNodeAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__Group__3__Impl"


    // $ANTLR start "rule__ElkLabel__Group__0"
    // InternalLayoutConfig.g:1085:1: rule__ElkLabel__Group__0 : rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 ;
    public final void rule__ElkLabel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1089:1: ( rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 )
            // InternalLayoutConfig.g:1090:2: rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_10);
            rule__ElkLabel__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__0"


    // $ANTLR start "rule__ElkLabel__Group__0__Impl"
    // InternalLayoutConfig.g:1097:1: rule__ElkLabel__Group__0__Impl : ( 'label' ) ;
    public final void rule__ElkLabel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1101:1: ( ( 'label' ) )
            // InternalLayoutConfig.g:1102:1: ( 'label' )
            {
            // InternalLayoutConfig.g:1102:1: ( 'label' )
            // InternalLayoutConfig.g:1103:1: 'label'
            {
             before(grammarAccess.getElkLabelAccess().getLabelKeyword_0()); 
            match(input,18,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getLabelKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__0__Impl"


    // $ANTLR start "rule__ElkLabel__Group__1"
    // InternalLayoutConfig.g:1116:1: rule__ElkLabel__Group__1 : rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 ;
    public final void rule__ElkLabel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1120:1: ( rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 )
            // InternalLayoutConfig.g:1121:2: rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_10);
            rule__ElkLabel__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__1"


    // $ANTLR start "rule__ElkLabel__Group__1__Impl"
    // InternalLayoutConfig.g:1128:1: rule__ElkLabel__Group__1__Impl : ( ( rule__ElkLabel__Group_1__0 )? ) ;
    public final void rule__ElkLabel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1132:1: ( ( ( rule__ElkLabel__Group_1__0 )? ) )
            // InternalLayoutConfig.g:1133:1: ( ( rule__ElkLabel__Group_1__0 )? )
            {
            // InternalLayoutConfig.g:1133:1: ( ( rule__ElkLabel__Group_1__0 )? )
            // InternalLayoutConfig.g:1134:1: ( rule__ElkLabel__Group_1__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_1()); 
            // InternalLayoutConfig.g:1135:1: ( rule__ElkLabel__Group_1__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLayoutConfig.g:1135:2: rule__ElkLabel__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkLabel__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkLabelAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__1__Impl"


    // $ANTLR start "rule__ElkLabel__Group__2"
    // InternalLayoutConfig.g:1145:1: rule__ElkLabel__Group__2 : rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 ;
    public final void rule__ElkLabel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1149:1: ( rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 )
            // InternalLayoutConfig.g:1150:2: rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__ElkLabel__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__2"


    // $ANTLR start "rule__ElkLabel__Group__2__Impl"
    // InternalLayoutConfig.g:1157:1: rule__ElkLabel__Group__2__Impl : ( ( rule__ElkLabel__TextAssignment_2 ) ) ;
    public final void rule__ElkLabel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1161:1: ( ( ( rule__ElkLabel__TextAssignment_2 ) ) )
            // InternalLayoutConfig.g:1162:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            {
            // InternalLayoutConfig.g:1162:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            // InternalLayoutConfig.g:1163:1: ( rule__ElkLabel__TextAssignment_2 )
            {
             before(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 
            // InternalLayoutConfig.g:1164:1: ( rule__ElkLabel__TextAssignment_2 )
            // InternalLayoutConfig.g:1164:2: rule__ElkLabel__TextAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__TextAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__2__Impl"


    // $ANTLR start "rule__ElkLabel__Group__3"
    // InternalLayoutConfig.g:1174:1: rule__ElkLabel__Group__3 : rule__ElkLabel__Group__3__Impl ;
    public final void rule__ElkLabel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1178:1: ( rule__ElkLabel__Group__3__Impl )
            // InternalLayoutConfig.g:1179:2: rule__ElkLabel__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__3"


    // $ANTLR start "rule__ElkLabel__Group__3__Impl"
    // InternalLayoutConfig.g:1185:1: rule__ElkLabel__Group__3__Impl : ( ( rule__ElkLabel__Group_3__0 )? ) ;
    public final void rule__ElkLabel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1189:1: ( ( ( rule__ElkLabel__Group_3__0 )? ) )
            // InternalLayoutConfig.g:1190:1: ( ( rule__ElkLabel__Group_3__0 )? )
            {
            // InternalLayoutConfig.g:1190:1: ( ( rule__ElkLabel__Group_3__0 )? )
            // InternalLayoutConfig.g:1191:1: ( rule__ElkLabel__Group_3__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_3()); 
            // InternalLayoutConfig.g:1192:1: ( rule__ElkLabel__Group_3__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==15) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalLayoutConfig.g:1192:2: rule__ElkLabel__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkLabel__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkLabelAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group__3__Impl"


    // $ANTLR start "rule__ElkLabel__Group_1__0"
    // InternalLayoutConfig.g:1210:1: rule__ElkLabel__Group_1__0 : rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 ;
    public final void rule__ElkLabel__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1214:1: ( rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 )
            // InternalLayoutConfig.g:1215:2: rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkLabel__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_1__0"


    // $ANTLR start "rule__ElkLabel__Group_1__0__Impl"
    // InternalLayoutConfig.g:1222:1: rule__ElkLabel__Group_1__0__Impl : ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkLabel__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1226:1: ( ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) )
            // InternalLayoutConfig.g:1227:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            {
            // InternalLayoutConfig.g:1227:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            // InternalLayoutConfig.g:1228:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 
            // InternalLayoutConfig.g:1229:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            // InternalLayoutConfig.g:1229:2: rule__ElkLabel__IdentifierAssignment_1_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__IdentifierAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_1__0__Impl"


    // $ANTLR start "rule__ElkLabel__Group_1__1"
    // InternalLayoutConfig.g:1239:1: rule__ElkLabel__Group_1__1 : rule__ElkLabel__Group_1__1__Impl ;
    public final void rule__ElkLabel__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1243:1: ( rule__ElkLabel__Group_1__1__Impl )
            // InternalLayoutConfig.g:1244:2: rule__ElkLabel__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_1__1"


    // $ANTLR start "rule__ElkLabel__Group_1__1__Impl"
    // InternalLayoutConfig.g:1250:1: rule__ElkLabel__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkLabel__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1254:1: ( ( ':' ) )
            // InternalLayoutConfig.g:1255:1: ( ':' )
            {
            // InternalLayoutConfig.g:1255:1: ( ':' )
            // InternalLayoutConfig.g:1256:1: ':'
            {
             before(grammarAccess.getElkLabelAccess().getColonKeyword_1_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getColonKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_1__1__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__0"
    // InternalLayoutConfig.g:1273:1: rule__ElkLabel__Group_3__0 : rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 ;
    public final void rule__ElkLabel__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1277:1: ( rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 )
            // InternalLayoutConfig.g:1278:2: rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__ElkLabel__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__0"


    // $ANTLR start "rule__ElkLabel__Group_3__0__Impl"
    // InternalLayoutConfig.g:1285:1: rule__ElkLabel__Group_3__0__Impl : ( '{' ) ;
    public final void rule__ElkLabel__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1289:1: ( ( '{' ) )
            // InternalLayoutConfig.g:1290:1: ( '{' )
            {
            // InternalLayoutConfig.g:1290:1: ( '{' )
            // InternalLayoutConfig.g:1291:1: '{'
            {
             before(grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0()); 
            match(input,15,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__0__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__1"
    // InternalLayoutConfig.g:1304:1: rule__ElkLabel__Group_3__1 : rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 ;
    public final void rule__ElkLabel__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1308:1: ( rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 )
            // InternalLayoutConfig.g:1309:2: rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__ElkLabel__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__1"


    // $ANTLR start "rule__ElkLabel__Group_3__1__Impl"
    // InternalLayoutConfig.g:1316:1: rule__ElkLabel__Group_3__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkLabel__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1320:1: ( ( ( ruleShapeLayout )? ) )
            // InternalLayoutConfig.g:1321:1: ( ( ruleShapeLayout )? )
            {
            // InternalLayoutConfig.g:1321:1: ( ( ruleShapeLayout )? )
            // InternalLayoutConfig.g:1322:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 
            // InternalLayoutConfig.g:1323:1: ( ruleShapeLayout )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==20) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalLayoutConfig.g:1323:3: ruleShapeLayout
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleShapeLayout();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__1__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__2"
    // InternalLayoutConfig.g:1333:1: rule__ElkLabel__Group_3__2 : rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 ;
    public final void rule__ElkLabel__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1337:1: ( rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 )
            // InternalLayoutConfig.g:1338:2: rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__ElkLabel__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__2"


    // $ANTLR start "rule__ElkLabel__Group_3__2__Impl"
    // InternalLayoutConfig.g:1345:1: rule__ElkLabel__Group_3__2__Impl : ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) ;
    public final void rule__ElkLabel__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1349:1: ( ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) )
            // InternalLayoutConfig.g:1350:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            {
            // InternalLayoutConfig.g:1350:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            // InternalLayoutConfig.g:1351:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 
            // InternalLayoutConfig.g:1352:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalLayoutConfig.g:1352:2: rule__ElkLabel__PropertiesAssignment_3_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__ElkLabel__PropertiesAssignment_3_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__2__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__3"
    // InternalLayoutConfig.g:1362:1: rule__ElkLabel__Group_3__3 : rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 ;
    public final void rule__ElkLabel__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1366:1: ( rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 )
            // InternalLayoutConfig.g:1367:2: rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__ElkLabel__Group_3__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__3"


    // $ANTLR start "rule__ElkLabel__Group_3__3__Impl"
    // InternalLayoutConfig.g:1374:1: rule__ElkLabel__Group_3__3__Impl : ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) ;
    public final void rule__ElkLabel__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1378:1: ( ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) )
            // InternalLayoutConfig.g:1379:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            {
            // InternalLayoutConfig.g:1379:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            // InternalLayoutConfig.g:1380:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            {
             before(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 
            // InternalLayoutConfig.g:1381:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==18) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalLayoutConfig.g:1381:2: rule__ElkLabel__LabelsAssignment_3_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_13);
            	    rule__ElkLabel__LabelsAssignment_3_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__3__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__4"
    // InternalLayoutConfig.g:1391:1: rule__ElkLabel__Group_3__4 : rule__ElkLabel__Group_3__4__Impl ;
    public final void rule__ElkLabel__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1395:1: ( rule__ElkLabel__Group_3__4__Impl )
            // InternalLayoutConfig.g:1396:2: rule__ElkLabel__Group_3__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkLabel__Group_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__4"


    // $ANTLR start "rule__ElkLabel__Group_3__4__Impl"
    // InternalLayoutConfig.g:1402:1: rule__ElkLabel__Group_3__4__Impl : ( '}' ) ;
    public final void rule__ElkLabel__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1406:1: ( ( '}' ) )
            // InternalLayoutConfig.g:1407:1: ( '}' )
            {
            // InternalLayoutConfig.g:1407:1: ( '}' )
            // InternalLayoutConfig.g:1408:1: '}'
            {
             before(grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4()); 
            match(input,16,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__Group_3__4__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__0"
    // InternalLayoutConfig.g:1433:1: rule__ShapeLayout__Group__0 : rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 ;
    public final void rule__ShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1437:1: ( rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 )
            // InternalLayoutConfig.g:1438:2: rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__ShapeLayout__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__0"


    // $ANTLR start "rule__ShapeLayout__Group__0__Impl"
    // InternalLayoutConfig.g:1445:1: rule__ShapeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__ShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1449:1: ( ( 'layout' ) )
            // InternalLayoutConfig.g:1450:1: ( 'layout' )
            {
            // InternalLayoutConfig.g:1450:1: ( 'layout' )
            // InternalLayoutConfig.g:1451:1: 'layout'
            {
             before(grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0()); 
            match(input,20,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__0__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__1"
    // InternalLayoutConfig.g:1464:1: rule__ShapeLayout__Group__1 : rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 ;
    public final void rule__ShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1468:1: ( rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 )
            // InternalLayoutConfig.g:1469:2: rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_15);
            rule__ShapeLayout__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__1"


    // $ANTLR start "rule__ShapeLayout__Group__1__Impl"
    // InternalLayoutConfig.g:1476:1: rule__ShapeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__ShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1480:1: ( ( '[' ) )
            // InternalLayoutConfig.g:1481:1: ( '[' )
            {
            // InternalLayoutConfig.g:1481:1: ( '[' )
            // InternalLayoutConfig.g:1482:1: '['
            {
             before(grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,21,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__1__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__2"
    // InternalLayoutConfig.g:1495:1: rule__ShapeLayout__Group__2 : rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 ;
    public final void rule__ShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1499:1: ( rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 )
            // InternalLayoutConfig.g:1500:2: rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__ShapeLayout__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__2"


    // $ANTLR start "rule__ShapeLayout__Group__2__Impl"
    // InternalLayoutConfig.g:1507:1: rule__ShapeLayout__Group__2__Impl : ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) ;
    public final void rule__ShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1511:1: ( ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) )
            // InternalLayoutConfig.g:1512:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            {
            // InternalLayoutConfig.g:1512:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            // InternalLayoutConfig.g:1513:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 
            // InternalLayoutConfig.g:1514:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            // InternalLayoutConfig.g:1514:2: rule__ShapeLayout__UnorderedGroup_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__UnorderedGroup_2();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__2__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__3"
    // InternalLayoutConfig.g:1524:1: rule__ShapeLayout__Group__3 : rule__ShapeLayout__Group__3__Impl ;
    public final void rule__ShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1528:1: ( rule__ShapeLayout__Group__3__Impl )
            // InternalLayoutConfig.g:1529:2: rule__ShapeLayout__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__3"


    // $ANTLR start "rule__ShapeLayout__Group__3__Impl"
    // InternalLayoutConfig.g:1535:1: rule__ShapeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__ShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1539:1: ( ( ']' ) )
            // InternalLayoutConfig.g:1540:1: ( ']' )
            {
            // InternalLayoutConfig.g:1540:1: ( ']' )
            // InternalLayoutConfig.g:1541:1: ']'
            {
             before(grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,22,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group__3__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__0"
    // InternalLayoutConfig.g:1562:1: rule__ShapeLayout__Group_2_0__0 : rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 ;
    public final void rule__ShapeLayout__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1566:1: ( rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 )
            // InternalLayoutConfig.g:1567:2: rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ShapeLayout__Group_2_0__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__0"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__0__Impl"
    // InternalLayoutConfig.g:1574:1: rule__ShapeLayout__Group_2_0__0__Impl : ( 'position' ) ;
    public final void rule__ShapeLayout__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1578:1: ( ( 'position' ) )
            // InternalLayoutConfig.g:1579:1: ( 'position' )
            {
            // InternalLayoutConfig.g:1579:1: ( 'position' )
            // InternalLayoutConfig.g:1580:1: 'position'
            {
             before(grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0()); 
            match(input,23,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__0__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__1"
    // InternalLayoutConfig.g:1593:1: rule__ShapeLayout__Group_2_0__1 : rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 ;
    public final void rule__ShapeLayout__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1597:1: ( rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 )
            // InternalLayoutConfig.g:1598:2: rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ShapeLayout__Group_2_0__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__1"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__1__Impl"
    // InternalLayoutConfig.g:1605:1: rule__ShapeLayout__Group_2_0__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1609:1: ( ( ':' ) )
            // InternalLayoutConfig.g:1610:1: ( ':' )
            {
            // InternalLayoutConfig.g:1610:1: ( ':' )
            // InternalLayoutConfig.g:1611:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__1__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__2"
    // InternalLayoutConfig.g:1624:1: rule__ShapeLayout__Group_2_0__2 : rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 ;
    public final void rule__ShapeLayout__Group_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1628:1: ( rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 )
            // InternalLayoutConfig.g:1629:2: rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ShapeLayout__Group_2_0__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__2"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__2__Impl"
    // InternalLayoutConfig.g:1636:1: rule__ShapeLayout__Group_2_0__2__Impl : ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1640:1: ( ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) )
            // InternalLayoutConfig.g:1641:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            {
            // InternalLayoutConfig.g:1641:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            // InternalLayoutConfig.g:1642:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 
            // InternalLayoutConfig.g:1643:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            // InternalLayoutConfig.g:1643:2: rule__ShapeLayout__XAssignment_2_0_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__XAssignment_2_0_2();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__2__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__3"
    // InternalLayoutConfig.g:1653:1: rule__ShapeLayout__Group_2_0__3 : rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 ;
    public final void rule__ShapeLayout__Group_2_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1657:1: ( rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 )
            // InternalLayoutConfig.g:1658:2: rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ShapeLayout__Group_2_0__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_0__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__3"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__3__Impl"
    // InternalLayoutConfig.g:1665:1: rule__ShapeLayout__Group_2_0__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1669:1: ( ( ',' ) )
            // InternalLayoutConfig.g:1670:1: ( ',' )
            {
            // InternalLayoutConfig.g:1670:1: ( ',' )
            // InternalLayoutConfig.g:1671:1: ','
            {
             before(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_0_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__3__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__4"
    // InternalLayoutConfig.g:1684:1: rule__ShapeLayout__Group_2_0__4 : rule__ShapeLayout__Group_2_0__4__Impl ;
    public final void rule__ShapeLayout__Group_2_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1688:1: ( rule__ShapeLayout__Group_2_0__4__Impl )
            // InternalLayoutConfig.g:1689:2: rule__ShapeLayout__Group_2_0__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_0__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__4"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__4__Impl"
    // InternalLayoutConfig.g:1695:1: rule__ShapeLayout__Group_2_0__4__Impl : ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1699:1: ( ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) )
            // InternalLayoutConfig.g:1700:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            {
            // InternalLayoutConfig.g:1700:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            // InternalLayoutConfig.g:1701:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 
            // InternalLayoutConfig.g:1702:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            // InternalLayoutConfig.g:1702:2: rule__ShapeLayout__YAssignment_2_0_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__YAssignment_2_0_4();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_0__4__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__0"
    // InternalLayoutConfig.g:1722:1: rule__ShapeLayout__Group_2_1__0 : rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 ;
    public final void rule__ShapeLayout__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1726:1: ( rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 )
            // InternalLayoutConfig.g:1727:2: rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ShapeLayout__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__0"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__0__Impl"
    // InternalLayoutConfig.g:1734:1: rule__ShapeLayout__Group_2_1__0__Impl : ( 'size' ) ;
    public final void rule__ShapeLayout__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1738:1: ( ( 'size' ) )
            // InternalLayoutConfig.g:1739:1: ( 'size' )
            {
            // InternalLayoutConfig.g:1739:1: ( 'size' )
            // InternalLayoutConfig.g:1740:1: 'size'
            {
             before(grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0()); 
            match(input,25,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__0__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__1"
    // InternalLayoutConfig.g:1753:1: rule__ShapeLayout__Group_2_1__1 : rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 ;
    public final void rule__ShapeLayout__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1757:1: ( rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 )
            // InternalLayoutConfig.g:1758:2: rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ShapeLayout__Group_2_1__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__1"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__1__Impl"
    // InternalLayoutConfig.g:1765:1: rule__ShapeLayout__Group_2_1__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1769:1: ( ( ':' ) )
            // InternalLayoutConfig.g:1770:1: ( ':' )
            {
            // InternalLayoutConfig.g:1770:1: ( ':' )
            // InternalLayoutConfig.g:1771:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__1__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__2"
    // InternalLayoutConfig.g:1784:1: rule__ShapeLayout__Group_2_1__2 : rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 ;
    public final void rule__ShapeLayout__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1788:1: ( rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 )
            // InternalLayoutConfig.g:1789:2: rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ShapeLayout__Group_2_1__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__2"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__2__Impl"
    // InternalLayoutConfig.g:1796:1: rule__ShapeLayout__Group_2_1__2__Impl : ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1800:1: ( ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) )
            // InternalLayoutConfig.g:1801:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            {
            // InternalLayoutConfig.g:1801:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            // InternalLayoutConfig.g:1802:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 
            // InternalLayoutConfig.g:1803:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            // InternalLayoutConfig.g:1803:2: rule__ShapeLayout__WidthAssignment_2_1_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__WidthAssignment_2_1_2();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__2__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__3"
    // InternalLayoutConfig.g:1813:1: rule__ShapeLayout__Group_2_1__3 : rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 ;
    public final void rule__ShapeLayout__Group_2_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1817:1: ( rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 )
            // InternalLayoutConfig.g:1818:2: rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ShapeLayout__Group_2_1__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_1__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__3"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__3__Impl"
    // InternalLayoutConfig.g:1825:1: rule__ShapeLayout__Group_2_1__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1829:1: ( ( ',' ) )
            // InternalLayoutConfig.g:1830:1: ( ',' )
            {
            // InternalLayoutConfig.g:1830:1: ( ',' )
            // InternalLayoutConfig.g:1831:1: ','
            {
             before(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_1_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__3__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__4"
    // InternalLayoutConfig.g:1844:1: rule__ShapeLayout__Group_2_1__4 : rule__ShapeLayout__Group_2_1__4__Impl ;
    public final void rule__ShapeLayout__Group_2_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1848:1: ( rule__ShapeLayout__Group_2_1__4__Impl )
            // InternalLayoutConfig.g:1849:2: rule__ShapeLayout__Group_2_1__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__Group_2_1__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__4"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__4__Impl"
    // InternalLayoutConfig.g:1855:1: rule__ShapeLayout__Group_2_1__4__Impl : ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1859:1: ( ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) )
            // InternalLayoutConfig.g:1860:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            {
            // InternalLayoutConfig.g:1860:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            // InternalLayoutConfig.g:1861:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 
            // InternalLayoutConfig.g:1862:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            // InternalLayoutConfig.g:1862:2: rule__ShapeLayout__HeightAssignment_2_1_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__HeightAssignment_2_1_4();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__Group_2_1__4__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__0"
    // InternalLayoutConfig.g:1887:1: rule__EdgeLayout__Group__0 : rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 ;
    public final void rule__EdgeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1891:1: ( rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 )
            // InternalLayoutConfig.g:1892:2: rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__EdgeLayout__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__EdgeLayout__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__0"


    // $ANTLR start "rule__EdgeLayout__Group__0__Impl"
    // InternalLayoutConfig.g:1899:1: rule__EdgeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__EdgeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1903:1: ( ( 'layout' ) )
            // InternalLayoutConfig.g:1904:1: ( 'layout' )
            {
            // InternalLayoutConfig.g:1904:1: ( 'layout' )
            // InternalLayoutConfig.g:1905:1: 'layout'
            {
             before(grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0()); 
            match(input,20,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__0__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__1"
    // InternalLayoutConfig.g:1918:1: rule__EdgeLayout__Group__1 : rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 ;
    public final void rule__EdgeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1922:1: ( rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 )
            // InternalLayoutConfig.g:1923:2: rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_19);
            rule__EdgeLayout__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__EdgeLayout__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__1"


    // $ANTLR start "rule__EdgeLayout__Group__1__Impl"
    // InternalLayoutConfig.g:1930:1: rule__EdgeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__EdgeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1934:1: ( ( '[' ) )
            // InternalLayoutConfig.g:1935:1: ( '[' )
            {
            // InternalLayoutConfig.g:1935:1: ( '[' )
            // InternalLayoutConfig.g:1936:1: '['
            {
             before(grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,21,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__1__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__2"
    // InternalLayoutConfig.g:1949:1: rule__EdgeLayout__Group__2 : rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 ;
    public final void rule__EdgeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1953:1: ( rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 )
            // InternalLayoutConfig.g:1954:2: rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__EdgeLayout__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__EdgeLayout__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__2"


    // $ANTLR start "rule__EdgeLayout__Group__2__Impl"
    // InternalLayoutConfig.g:1961:1: rule__EdgeLayout__Group__2__Impl : ( ( rule__EdgeLayout__Alternatives_2 ) ) ;
    public final void rule__EdgeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1965:1: ( ( ( rule__EdgeLayout__Alternatives_2 ) ) )
            // InternalLayoutConfig.g:1966:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            {
            // InternalLayoutConfig.g:1966:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            // InternalLayoutConfig.g:1967:1: ( rule__EdgeLayout__Alternatives_2 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 
            // InternalLayoutConfig.g:1968:1: ( rule__EdgeLayout__Alternatives_2 )
            // InternalLayoutConfig.g:1968:2: rule__EdgeLayout__Alternatives_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EdgeLayout__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__2__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__3"
    // InternalLayoutConfig.g:1978:1: rule__EdgeLayout__Group__3 : rule__EdgeLayout__Group__3__Impl ;
    public final void rule__EdgeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1982:1: ( rule__EdgeLayout__Group__3__Impl )
            // InternalLayoutConfig.g:1983:2: rule__EdgeLayout__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EdgeLayout__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__3"


    // $ANTLR start "rule__EdgeLayout__Group__3__Impl"
    // InternalLayoutConfig.g:1989:1: rule__EdgeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__EdgeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1993:1: ( ( ']' ) )
            // InternalLayoutConfig.g:1994:1: ( ']' )
            {
            // InternalLayoutConfig.g:1994:1: ( ']' )
            // InternalLayoutConfig.g:1995:1: ']'
            {
             before(grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,22,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__Group__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__0"
    // InternalLayoutConfig.g:2016:1: rule__ElkSingleEdgeSection__Group__0 : rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 ;
    public final void rule__ElkSingleEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2020:1: ( rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 )
            // InternalLayoutConfig.g:2021:2: rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_20);
            rule__ElkSingleEdgeSection__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__0__Impl"
    // InternalLayoutConfig.g:2028:1: rule__ElkSingleEdgeSection__Group__0__Impl : ( () ) ;
    public final void rule__ElkSingleEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2032:1: ( ( () ) )
            // InternalLayoutConfig.g:2033:1: ( () )
            {
            // InternalLayoutConfig.g:2033:1: ( () )
            // InternalLayoutConfig.g:2034:1: ()
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 
            // InternalLayoutConfig.g:2035:1: ()
            // InternalLayoutConfig.g:2037:1: 
            {
            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__1"
    // InternalLayoutConfig.g:2047:1: rule__ElkSingleEdgeSection__Group__1 : rule__ElkSingleEdgeSection__Group__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2051:1: ( rule__ElkSingleEdgeSection__Group__1__Impl )
            // InternalLayoutConfig.g:2052:2: rule__ElkSingleEdgeSection__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__1__Impl"
    // InternalLayoutConfig.g:2058:1: rule__ElkSingleEdgeSection__Group__1__Impl : ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2062:1: ( ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) ) )
            // InternalLayoutConfig.g:2063:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) )
            {
            // InternalLayoutConfig.g:2063:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) )
            // InternalLayoutConfig.g:2064:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1()); 
            // InternalLayoutConfig.g:2065:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1 )
            // InternalLayoutConfig.g:2065:2: rule__ElkSingleEdgeSection__UnorderedGroup_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__0"
    // InternalLayoutConfig.g:2079:1: rule__ElkSingleEdgeSection__Group_1_0__0 : rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2083:1: ( rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1 )
            // InternalLayoutConfig.g:2084:2: rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_0__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__0__Impl"
    // InternalLayoutConfig.g:2091:1: rule__ElkSingleEdgeSection__Group_1_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2095:1: ( ( 'incoming' ) )
            // InternalLayoutConfig.g:2096:1: ( 'incoming' )
            {
            // InternalLayoutConfig.g:2096:1: ( 'incoming' )
            // InternalLayoutConfig.g:2097:1: 'incoming'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0()); 
            match(input,26,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__1"
    // InternalLayoutConfig.g:2110:1: rule__ElkSingleEdgeSection__Group_1_0__1 : rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2114:1: ( rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2 )
            // InternalLayoutConfig.g:2115:2: rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkSingleEdgeSection__Group_1_0__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__1__Impl"
    // InternalLayoutConfig.g:2122:1: rule__ElkSingleEdgeSection__Group_1_0__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2126:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2127:1: ( ':' )
            {
            // InternalLayoutConfig.g:2127:1: ( ':' )
            // InternalLayoutConfig.g:2128:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__2"
    // InternalLayoutConfig.g:2141:1: rule__ElkSingleEdgeSection__Group_1_0__2 : rule__ElkSingleEdgeSection__Group_1_0__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2145:1: ( rule__ElkSingleEdgeSection__Group_1_0__2__Impl )
            // InternalLayoutConfig.g:2146:2: rule__ElkSingleEdgeSection__Group_1_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0__2__Impl"
    // InternalLayoutConfig.g:2152:1: rule__ElkSingleEdgeSection__Group_1_0__2__Impl : ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2156:1: ( ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) ) )
            // InternalLayoutConfig.g:2157:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) )
            {
            // InternalLayoutConfig.g:2157:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) )
            // InternalLayoutConfig.g:2158:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_2()); 
            // InternalLayoutConfig.g:2159:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 )
            // InternalLayoutConfig.g:2159:2: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__0"
    // InternalLayoutConfig.g:2175:1: rule__ElkSingleEdgeSection__Group_1_1__0 : rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2179:1: ( rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 )
            // InternalLayoutConfig.g:2180:2: rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__0__Impl"
    // InternalLayoutConfig.g:2187:1: rule__ElkSingleEdgeSection__Group_1_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2191:1: ( ( 'outgoing' ) )
            // InternalLayoutConfig.g:2192:1: ( 'outgoing' )
            {
            // InternalLayoutConfig.g:2192:1: ( 'outgoing' )
            // InternalLayoutConfig.g:2193:1: 'outgoing'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_1_0()); 
            match(input,27,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__1"
    // InternalLayoutConfig.g:2206:1: rule__ElkSingleEdgeSection__Group_1_1__1 : rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2210:1: ( rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 )
            // InternalLayoutConfig.g:2211:2: rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkSingleEdgeSection__Group_1_1__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__1__Impl"
    // InternalLayoutConfig.g:2218:1: rule__ElkSingleEdgeSection__Group_1_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2222:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2223:1: ( ':' )
            {
            // InternalLayoutConfig.g:2223:1: ( ':' )
            // InternalLayoutConfig.g:2224:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__2"
    // InternalLayoutConfig.g:2237:1: rule__ElkSingleEdgeSection__Group_1_1__2 : rule__ElkSingleEdgeSection__Group_1_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2241:1: ( rule__ElkSingleEdgeSection__Group_1_1__2__Impl )
            // InternalLayoutConfig.g:2242:2: rule__ElkSingleEdgeSection__Group_1_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__2__Impl"
    // InternalLayoutConfig.g:2248:1: rule__ElkSingleEdgeSection__Group_1_1__2__Impl : ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2252:1: ( ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) ) )
            // InternalLayoutConfig.g:2253:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) )
            {
            // InternalLayoutConfig.g:2253:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) )
            // InternalLayoutConfig.g:2254:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_1_2()); 
            // InternalLayoutConfig.g:2255:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 )
            // InternalLayoutConfig.g:2255:2: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__0"
    // InternalLayoutConfig.g:2271:1: rule__ElkSingleEdgeSection__Group_1_2__0 : rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2275:1: ( rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1 )
            // InternalLayoutConfig.g:2276:2: rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_2__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__0__Impl"
    // InternalLayoutConfig.g:2283:1: rule__ElkSingleEdgeSection__Group_1_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2287:1: ( ( 'start' ) )
            // InternalLayoutConfig.g:2288:1: ( 'start' )
            {
            // InternalLayoutConfig.g:2288:1: ( 'start' )
            // InternalLayoutConfig.g:2289:1: 'start'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_2_0()); 
            match(input,28,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__1"
    // InternalLayoutConfig.g:2302:1: rule__ElkSingleEdgeSection__Group_1_2__1 : rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2306:1: ( rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2 )
            // InternalLayoutConfig.g:2307:2: rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_2__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__1__Impl"
    // InternalLayoutConfig.g:2314:1: rule__ElkSingleEdgeSection__Group_1_2__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2318:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2319:1: ( ':' )
            {
            // InternalLayoutConfig.g:2319:1: ( ':' )
            // InternalLayoutConfig.g:2320:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_2_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__2"
    // InternalLayoutConfig.g:2333:1: rule__ElkSingleEdgeSection__Group_1_2__2 : rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2337:1: ( rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3 )
            // InternalLayoutConfig.g:2338:2: rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkSingleEdgeSection__Group_1_2__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__2__Impl"
    // InternalLayoutConfig.g:2345:1: rule__ElkSingleEdgeSection__Group_1_2__2__Impl : ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2349:1: ( ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) ) )
            // InternalLayoutConfig.g:2350:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) )
            {
            // InternalLayoutConfig.g:2350:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) )
            // InternalLayoutConfig.g:2351:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_2_2()); 
            // InternalLayoutConfig.g:2352:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 )
            // InternalLayoutConfig.g:2352:2: rule__ElkSingleEdgeSection__StartXAssignment_1_2_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__StartXAssignment_1_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__3"
    // InternalLayoutConfig.g:2362:1: rule__ElkSingleEdgeSection__Group_1_2__3 : rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2366:1: ( rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4 )
            // InternalLayoutConfig.g:2367:2: rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_2__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__3__Impl"
    // InternalLayoutConfig.g:2374:1: rule__ElkSingleEdgeSection__Group_1_2__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2378:1: ( ( ',' ) )
            // InternalLayoutConfig.g:2379:1: ( ',' )
            {
            // InternalLayoutConfig.g:2379:1: ( ',' )
            // InternalLayoutConfig.g:2380:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_2_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__4"
    // InternalLayoutConfig.g:2393:1: rule__ElkSingleEdgeSection__Group_1_2__4 : rule__ElkSingleEdgeSection__Group_1_2__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2397:1: ( rule__ElkSingleEdgeSection__Group_1_2__4__Impl )
            // InternalLayoutConfig.g:2398:2: rule__ElkSingleEdgeSection__Group_1_2__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_2__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_2__4__Impl"
    // InternalLayoutConfig.g:2404:1: rule__ElkSingleEdgeSection__Group_1_2__4__Impl : ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2408:1: ( ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) ) )
            // InternalLayoutConfig.g:2409:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) )
            {
            // InternalLayoutConfig.g:2409:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) )
            // InternalLayoutConfig.g:2410:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_2_4()); 
            // InternalLayoutConfig.g:2411:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 )
            // InternalLayoutConfig.g:2411:2: rule__ElkSingleEdgeSection__StartYAssignment_1_2_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__StartYAssignment_1_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_2_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_2__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__0"
    // InternalLayoutConfig.g:2431:1: rule__ElkSingleEdgeSection__Group_1_3__0 : rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2435:1: ( rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1 )
            // InternalLayoutConfig.g:2436:2: rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__0__Impl"
    // InternalLayoutConfig.g:2443:1: rule__ElkSingleEdgeSection__Group_1_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2447:1: ( ( 'end' ) )
            // InternalLayoutConfig.g:2448:1: ( 'end' )
            {
            // InternalLayoutConfig.g:2448:1: ( 'end' )
            // InternalLayoutConfig.g:2449:1: 'end'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_3_0()); 
            match(input,29,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__1"
    // InternalLayoutConfig.g:2462:1: rule__ElkSingleEdgeSection__Group_1_3__1 : rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2466:1: ( rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2 )
            // InternalLayoutConfig.g:2467:2: rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_3__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__1__Impl"
    // InternalLayoutConfig.g:2474:1: rule__ElkSingleEdgeSection__Group_1_3__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2478:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2479:1: ( ':' )
            {
            // InternalLayoutConfig.g:2479:1: ( ':' )
            // InternalLayoutConfig.g:2480:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_3_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__2"
    // InternalLayoutConfig.g:2493:1: rule__ElkSingleEdgeSection__Group_1_3__2 : rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2497:1: ( rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3 )
            // InternalLayoutConfig.g:2498:2: rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkSingleEdgeSection__Group_1_3__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__2__Impl"
    // InternalLayoutConfig.g:2505:1: rule__ElkSingleEdgeSection__Group_1_3__2__Impl : ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2509:1: ( ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) ) )
            // InternalLayoutConfig.g:2510:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) )
            {
            // InternalLayoutConfig.g:2510:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) )
            // InternalLayoutConfig.g:2511:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_3_2()); 
            // InternalLayoutConfig.g:2512:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 )
            // InternalLayoutConfig.g:2512:2: rule__ElkSingleEdgeSection__EndXAssignment_1_3_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__EndXAssignment_1_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__3"
    // InternalLayoutConfig.g:2522:1: rule__ElkSingleEdgeSection__Group_1_3__3 : rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2526:1: ( rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4 )
            // InternalLayoutConfig.g:2527:2: rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_3__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__3__Impl"
    // InternalLayoutConfig.g:2534:1: rule__ElkSingleEdgeSection__Group_1_3__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2538:1: ( ( ',' ) )
            // InternalLayoutConfig.g:2539:1: ( ',' )
            {
            // InternalLayoutConfig.g:2539:1: ( ',' )
            // InternalLayoutConfig.g:2540:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_3_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__4"
    // InternalLayoutConfig.g:2553:1: rule__ElkSingleEdgeSection__Group_1_3__4 : rule__ElkSingleEdgeSection__Group_1_3__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2557:1: ( rule__ElkSingleEdgeSection__Group_1_3__4__Impl )
            // InternalLayoutConfig.g:2558:2: rule__ElkSingleEdgeSection__Group_1_3__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_3__4__Impl"
    // InternalLayoutConfig.g:2564:1: rule__ElkSingleEdgeSection__Group_1_3__4__Impl : ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2568:1: ( ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) ) )
            // InternalLayoutConfig.g:2569:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) )
            {
            // InternalLayoutConfig.g:2569:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) )
            // InternalLayoutConfig.g:2570:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_3_4()); 
            // InternalLayoutConfig.g:2571:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 )
            // InternalLayoutConfig.g:2571:2: rule__ElkSingleEdgeSection__EndYAssignment_1_3_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__EndYAssignment_1_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_3__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__0"
    // InternalLayoutConfig.g:2591:1: rule__ElkSingleEdgeSection__Group_1_4__0 : rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2595:1: ( rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1 )
            // InternalLayoutConfig.g:2596:2: rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__0__Impl"
    // InternalLayoutConfig.g:2603:1: rule__ElkSingleEdgeSection__Group_1_4__0__Impl : ( 'bends' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2607:1: ( ( 'bends' ) )
            // InternalLayoutConfig.g:2608:1: ( 'bends' )
            {
            // InternalLayoutConfig.g:2608:1: ( 'bends' )
            // InternalLayoutConfig.g:2609:1: 'bends'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_4_0()); 
            match(input,30,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__1"
    // InternalLayoutConfig.g:2622:1: rule__ElkSingleEdgeSection__Group_1_4__1 : rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2626:1: ( rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2 )
            // InternalLayoutConfig.g:2627:2: rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_4__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__1__Impl"
    // InternalLayoutConfig.g:2634:1: rule__ElkSingleEdgeSection__Group_1_4__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2638:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2639:1: ( ':' )
            {
            // InternalLayoutConfig.g:2639:1: ( ':' )
            // InternalLayoutConfig.g:2640:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_4_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__2"
    // InternalLayoutConfig.g:2653:1: rule__ElkSingleEdgeSection__Group_1_4__2 : rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2657:1: ( rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3 )
            // InternalLayoutConfig.g:2658:2: rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3
            {
            pushFollow(FollowSets000.FOLLOW_21);
            rule__ElkSingleEdgeSection__Group_1_4__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__2__Impl"
    // InternalLayoutConfig.g:2665:1: rule__ElkSingleEdgeSection__Group_1_4__2__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2669:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) ) )
            // InternalLayoutConfig.g:2670:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) )
            {
            // InternalLayoutConfig.g:2670:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) )
            // InternalLayoutConfig.g:2671:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_2()); 
            // InternalLayoutConfig.g:2672:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 )
            // InternalLayoutConfig.g:2672:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__3"
    // InternalLayoutConfig.g:2682:1: rule__ElkSingleEdgeSection__Group_1_4__3 : rule__ElkSingleEdgeSection__Group_1_4__3__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2686:1: ( rule__ElkSingleEdgeSection__Group_1_4__3__Impl )
            // InternalLayoutConfig.g:2687:2: rule__ElkSingleEdgeSection__Group_1_4__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4__3__Impl"
    // InternalLayoutConfig.g:2693:1: rule__ElkSingleEdgeSection__Group_1_4__3__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2697:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* ) )
            // InternalLayoutConfig.g:2698:1: ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* )
            {
            // InternalLayoutConfig.g:2698:1: ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* )
            // InternalLayoutConfig.g:2699:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4_3()); 
            // InternalLayoutConfig.g:2700:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==31) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalLayoutConfig.g:2700:2: rule__ElkSingleEdgeSection__Group_1_4_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_22);
            	    rule__ElkSingleEdgeSection__Group_1_4_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__0"
    // InternalLayoutConfig.g:2718:1: rule__ElkSingleEdgeSection__Group_1_4_3__0 : rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2722:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1 )
            // InternalLayoutConfig.g:2723:2: rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl"
    // InternalLayoutConfig.g:2730:1: rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl : ( '|' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2734:1: ( ( '|' ) )
            // InternalLayoutConfig.g:2735:1: ( '|' )
            {
            // InternalLayoutConfig.g:2735:1: ( '|' )
            // InternalLayoutConfig.g:2736:1: '|'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_4_3_0()); 
            match(input,31,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__1"
    // InternalLayoutConfig.g:2749:1: rule__ElkSingleEdgeSection__Group_1_4_3__1 : rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2753:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl )
            // InternalLayoutConfig.g:2754:2: rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl"
    // InternalLayoutConfig.g:2760:1: rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2764:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) ) )
            // InternalLayoutConfig.g:2765:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) )
            {
            // InternalLayoutConfig.g:2765:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) )
            // InternalLayoutConfig.g:2766:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_3_1()); 
            // InternalLayoutConfig.g:2767:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 )
            // InternalLayoutConfig.g:2767:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__0"
    // InternalLayoutConfig.g:2781:1: rule__ElkEdgeSection__Group__0 : rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 ;
    public final void rule__ElkEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2785:1: ( rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 )
            // InternalLayoutConfig.g:2786:2: rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkEdgeSection__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__0"


    // $ANTLR start "rule__ElkEdgeSection__Group__0__Impl"
    // InternalLayoutConfig.g:2793:1: rule__ElkEdgeSection__Group__0__Impl : ( 'section' ) ;
    public final void rule__ElkEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2797:1: ( ( 'section' ) )
            // InternalLayoutConfig.g:2798:1: ( 'section' )
            {
            // InternalLayoutConfig.g:2798:1: ( 'section' )
            // InternalLayoutConfig.g:2799:1: 'section'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0()); 
            match(input,32,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__1"
    // InternalLayoutConfig.g:2812:1: rule__ElkEdgeSection__Group__1 : rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 ;
    public final void rule__ElkEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2816:1: ( rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 )
            // InternalLayoutConfig.g:2817:2: rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_23);
            rule__ElkEdgeSection__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__1"


    // $ANTLR start "rule__ElkEdgeSection__Group__1__Impl"
    // InternalLayoutConfig.g:2824:1: rule__ElkEdgeSection__Group__1__Impl : ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2828:1: ( ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) )
            // InternalLayoutConfig.g:2829:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            {
            // InternalLayoutConfig.g:2829:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            // InternalLayoutConfig.g:2830:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 
            // InternalLayoutConfig.g:2831:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            // InternalLayoutConfig.g:2831:2: rule__ElkEdgeSection__IdentifierAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__IdentifierAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__2"
    // InternalLayoutConfig.g:2841:1: rule__ElkEdgeSection__Group__2 : rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 ;
    public final void rule__ElkEdgeSection__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2845:1: ( rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 )
            // InternalLayoutConfig.g:2846:2: rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_23);
            rule__ElkEdgeSection__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__2"


    // $ANTLR start "rule__ElkEdgeSection__Group__2__Impl"
    // InternalLayoutConfig.g:2853:1: rule__ElkEdgeSection__Group__2__Impl : ( ( rule__ElkEdgeSection__Group_2__0 )? ) ;
    public final void rule__ElkEdgeSection__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2857:1: ( ( ( rule__ElkEdgeSection__Group_2__0 )? ) )
            // InternalLayoutConfig.g:2858:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            {
            // InternalLayoutConfig.g:2858:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            // InternalLayoutConfig.g:2859:1: ( rule__ElkEdgeSection__Group_2__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 
            // InternalLayoutConfig.g:2860:1: ( rule__ElkEdgeSection__Group_2__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==33) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalLayoutConfig.g:2860:2: rule__ElkEdgeSection__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__3"
    // InternalLayoutConfig.g:2870:1: rule__ElkEdgeSection__Group__3 : rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 ;
    public final void rule__ElkEdgeSection__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2874:1: ( rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 )
            // InternalLayoutConfig.g:2875:2: rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_20);
            rule__ElkEdgeSection__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__3"


    // $ANTLR start "rule__ElkEdgeSection__Group__3__Impl"
    // InternalLayoutConfig.g:2882:1: rule__ElkEdgeSection__Group__3__Impl : ( '[' ) ;
    public final void rule__ElkEdgeSection__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2886:1: ( ( '[' ) )
            // InternalLayoutConfig.g:2887:1: ( '[' )
            {
            // InternalLayoutConfig.g:2887:1: ( '[' )
            // InternalLayoutConfig.g:2888:1: '['
            {
             before(grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3()); 
            match(input,21,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__4"
    // InternalLayoutConfig.g:2901:1: rule__ElkEdgeSection__Group__4 : rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 ;
    public final void rule__ElkEdgeSection__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2905:1: ( rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 )
            // InternalLayoutConfig.g:2906:2: rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__ElkEdgeSection__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__4"


    // $ANTLR start "rule__ElkEdgeSection__Group__4__Impl"
    // InternalLayoutConfig.g:2913:1: rule__ElkEdgeSection__Group__4__Impl : ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) ) ;
    public final void rule__ElkEdgeSection__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2917:1: ( ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) ) )
            // InternalLayoutConfig.g:2918:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) )
            {
            // InternalLayoutConfig.g:2918:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) )
            // InternalLayoutConfig.g:2919:1: ( rule__ElkEdgeSection__UnorderedGroup_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4()); 
            // InternalLayoutConfig.g:2920:1: ( rule__ElkEdgeSection__UnorderedGroup_4 )
            // InternalLayoutConfig.g:2920:2: rule__ElkEdgeSection__UnorderedGroup_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__5"
    // InternalLayoutConfig.g:2930:1: rule__ElkEdgeSection__Group__5 : rule__ElkEdgeSection__Group__5__Impl ;
    public final void rule__ElkEdgeSection__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2934:1: ( rule__ElkEdgeSection__Group__5__Impl )
            // InternalLayoutConfig.g:2935:2: rule__ElkEdgeSection__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__5"


    // $ANTLR start "rule__ElkEdgeSection__Group__5__Impl"
    // InternalLayoutConfig.g:2941:1: rule__ElkEdgeSection__Group__5__Impl : ( ']' ) ;
    public final void rule__ElkEdgeSection__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2945:1: ( ( ']' ) )
            // InternalLayoutConfig.g:2946:1: ( ']' )
            {
            // InternalLayoutConfig.g:2946:1: ( ']' )
            // InternalLayoutConfig.g:2947:1: ']'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5()); 
            match(input,22,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group__5__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__0"
    // InternalLayoutConfig.g:2972:1: rule__ElkEdgeSection__Group_2__0 : rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 ;
    public final void rule__ElkEdgeSection__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2976:1: ( rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 )
            // InternalLayoutConfig.g:2977:2: rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkEdgeSection__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__0__Impl"
    // InternalLayoutConfig.g:2984:1: rule__ElkEdgeSection__Group_2__0__Impl : ( '->' ) ;
    public final void rule__ElkEdgeSection__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2988:1: ( ( '->' ) )
            // InternalLayoutConfig.g:2989:1: ( '->' )
            {
            // InternalLayoutConfig.g:2989:1: ( '->' )
            // InternalLayoutConfig.g:2990:1: '->'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 
            match(input,33,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__1"
    // InternalLayoutConfig.g:3003:1: rule__ElkEdgeSection__Group_2__1 : rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 ;
    public final void rule__ElkEdgeSection__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3007:1: ( rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 )
            // InternalLayoutConfig.g:3008:2: rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkEdgeSection__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__1__Impl"
    // InternalLayoutConfig.g:3015:1: rule__ElkEdgeSection__Group_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3019:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) )
            // InternalLayoutConfig.g:3020:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            {
            // InternalLayoutConfig.g:3020:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            // InternalLayoutConfig.g:3021:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 
            // InternalLayoutConfig.g:3022:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            // InternalLayoutConfig.g:3022:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__2"
    // InternalLayoutConfig.g:3032:1: rule__ElkEdgeSection__Group_2__2 : rule__ElkEdgeSection__Group_2__2__Impl ;
    public final void rule__ElkEdgeSection__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3036:1: ( rule__ElkEdgeSection__Group_2__2__Impl )
            // InternalLayoutConfig.g:3037:2: rule__ElkEdgeSection__Group_2__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__2__Impl"
    // InternalLayoutConfig.g:3043:1: rule__ElkEdgeSection__Group_2__2__Impl : ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3047:1: ( ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) )
            // InternalLayoutConfig.g:3048:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            {
            // InternalLayoutConfig.g:3048:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            // InternalLayoutConfig.g:3049:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 
            // InternalLayoutConfig.g:3050:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==24) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalLayoutConfig.g:3050:2: rule__ElkEdgeSection__Group_2_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_24);
            	    rule__ElkEdgeSection__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__0"
    // InternalLayoutConfig.g:3066:1: rule__ElkEdgeSection__Group_2_2__0 : rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 ;
    public final void rule__ElkEdgeSection__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3070:1: ( rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 )
            // InternalLayoutConfig.g:3071:2: rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkEdgeSection__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_2_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__0__Impl"
    // InternalLayoutConfig.g:3078:1: rule__ElkEdgeSection__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3082:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3083:1: ( ',' )
            {
            // InternalLayoutConfig.g:3083:1: ( ',' )
            // InternalLayoutConfig.g:3084:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_2_2_0()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__1"
    // InternalLayoutConfig.g:3097:1: rule__ElkEdgeSection__Group_2_2__1 : rule__ElkEdgeSection__Group_2_2__1__Impl ;
    public final void rule__ElkEdgeSection__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3101:1: ( rule__ElkEdgeSection__Group_2_2__1__Impl )
            // InternalLayoutConfig.g:3102:2: rule__ElkEdgeSection__Group_2_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_2_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__1__Impl"
    // InternalLayoutConfig.g:3108:1: rule__ElkEdgeSection__Group_2_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3112:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) )
            // InternalLayoutConfig.g:3113:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            {
            // InternalLayoutConfig.g:3113:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            // InternalLayoutConfig.g:3114:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 
            // InternalLayoutConfig.g:3115:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            // InternalLayoutConfig.g:3115:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__0"
    // InternalLayoutConfig.g:3129:1: rule__ElkEdgeSection__Group_4_0__0 : rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1 ;
    public final void rule__ElkEdgeSection__Group_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3133:1: ( rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1 )
            // InternalLayoutConfig.g:3134:2: rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_0__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__0__Impl"
    // InternalLayoutConfig.g:3141:1: rule__ElkEdgeSection__Group_4_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkEdgeSection__Group_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3145:1: ( ( 'incoming' ) )
            // InternalLayoutConfig.g:3146:1: ( 'incoming' )
            {
            // InternalLayoutConfig.g:3146:1: ( 'incoming' )
            // InternalLayoutConfig.g:3147:1: 'incoming'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0()); 
            match(input,26,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__1"
    // InternalLayoutConfig.g:3160:1: rule__ElkEdgeSection__Group_4_0__1 : rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2 ;
    public final void rule__ElkEdgeSection__Group_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3164:1: ( rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2 )
            // InternalLayoutConfig.g:3165:2: rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkEdgeSection__Group_4_0__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__1__Impl"
    // InternalLayoutConfig.g:3172:1: rule__ElkEdgeSection__Group_4_0__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3176:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3177:1: ( ':' )
            {
            // InternalLayoutConfig.g:3177:1: ( ':' )
            // InternalLayoutConfig.g:3178:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__2"
    // InternalLayoutConfig.g:3191:1: rule__ElkEdgeSection__Group_4_0__2 : rule__ElkEdgeSection__Group_4_0__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3195:1: ( rule__ElkEdgeSection__Group_4_0__2__Impl )
            // InternalLayoutConfig.g:3196:2: rule__ElkEdgeSection__Group_4_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0__2__Impl"
    // InternalLayoutConfig.g:3202:1: rule__ElkEdgeSection__Group_4_0__2__Impl : ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3206:1: ( ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) ) )
            // InternalLayoutConfig.g:3207:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) )
            {
            // InternalLayoutConfig.g:3207:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) )
            // InternalLayoutConfig.g:3208:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_2()); 
            // InternalLayoutConfig.g:3209:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 )
            // InternalLayoutConfig.g:3209:2: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__0"
    // InternalLayoutConfig.g:3225:1: rule__ElkEdgeSection__Group_4_1__0 : rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3229:1: ( rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 )
            // InternalLayoutConfig.g:3230:2: rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__0__Impl"
    // InternalLayoutConfig.g:3237:1: rule__ElkEdgeSection__Group_4_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3241:1: ( ( 'outgoing' ) )
            // InternalLayoutConfig.g:3242:1: ( 'outgoing' )
            {
            // InternalLayoutConfig.g:3242:1: ( 'outgoing' )
            // InternalLayoutConfig.g:3243:1: 'outgoing'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_1_0()); 
            match(input,27,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__1"
    // InternalLayoutConfig.g:3256:1: rule__ElkEdgeSection__Group_4_1__1 : rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3260:1: ( rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 )
            // InternalLayoutConfig.g:3261:2: rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkEdgeSection__Group_4_1__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__1__Impl"
    // InternalLayoutConfig.g:3268:1: rule__ElkEdgeSection__Group_4_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3272:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3273:1: ( ':' )
            {
            // InternalLayoutConfig.g:3273:1: ( ':' )
            // InternalLayoutConfig.g:3274:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__2"
    // InternalLayoutConfig.g:3287:1: rule__ElkEdgeSection__Group_4_1__2 : rule__ElkEdgeSection__Group_4_1__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3291:1: ( rule__ElkEdgeSection__Group_4_1__2__Impl )
            // InternalLayoutConfig.g:3292:2: rule__ElkEdgeSection__Group_4_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__2__Impl"
    // InternalLayoutConfig.g:3298:1: rule__ElkEdgeSection__Group_4_1__2__Impl : ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3302:1: ( ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) ) )
            // InternalLayoutConfig.g:3303:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) )
            {
            // InternalLayoutConfig.g:3303:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) )
            // InternalLayoutConfig.g:3304:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_1_2()); 
            // InternalLayoutConfig.g:3305:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 )
            // InternalLayoutConfig.g:3305:2: rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__0"
    // InternalLayoutConfig.g:3321:1: rule__ElkEdgeSection__Group_4_2__0 : rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1 ;
    public final void rule__ElkEdgeSection__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3325:1: ( rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1 )
            // InternalLayoutConfig.g:3326:2: rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_2__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__0__Impl"
    // InternalLayoutConfig.g:3333:1: rule__ElkEdgeSection__Group_4_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3337:1: ( ( 'start' ) )
            // InternalLayoutConfig.g:3338:1: ( 'start' )
            {
            // InternalLayoutConfig.g:3338:1: ( 'start' )
            // InternalLayoutConfig.g:3339:1: 'start'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_2_0()); 
            match(input,28,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__1"
    // InternalLayoutConfig.g:3352:1: rule__ElkEdgeSection__Group_4_2__1 : rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2 ;
    public final void rule__ElkEdgeSection__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3356:1: ( rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2 )
            // InternalLayoutConfig.g:3357:2: rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_2__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__1__Impl"
    // InternalLayoutConfig.g:3364:1: rule__ElkEdgeSection__Group_4_2__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3368:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3369:1: ( ':' )
            {
            // InternalLayoutConfig.g:3369:1: ( ':' )
            // InternalLayoutConfig.g:3370:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_2_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__2"
    // InternalLayoutConfig.g:3383:1: rule__ElkEdgeSection__Group_4_2__2 : rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3 ;
    public final void rule__ElkEdgeSection__Group_4_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3387:1: ( rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3 )
            // InternalLayoutConfig.g:3388:2: rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkEdgeSection__Group_4_2__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__2__Impl"
    // InternalLayoutConfig.g:3395:1: rule__ElkEdgeSection__Group_4_2__2__Impl : ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3399:1: ( ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) ) )
            // InternalLayoutConfig.g:3400:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) )
            {
            // InternalLayoutConfig.g:3400:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) )
            // InternalLayoutConfig.g:3401:1: ( rule__ElkEdgeSection__StartXAssignment_4_2_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_2_2()); 
            // InternalLayoutConfig.g:3402:1: ( rule__ElkEdgeSection__StartXAssignment_4_2_2 )
            // InternalLayoutConfig.g:3402:2: rule__ElkEdgeSection__StartXAssignment_4_2_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__StartXAssignment_4_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__3"
    // InternalLayoutConfig.g:3412:1: rule__ElkEdgeSection__Group_4_2__3 : rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4 ;
    public final void rule__ElkEdgeSection__Group_4_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3416:1: ( rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4 )
            // InternalLayoutConfig.g:3417:2: rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_2__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__3__Impl"
    // InternalLayoutConfig.g:3424:1: rule__ElkEdgeSection__Group_4_2__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3428:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3429:1: ( ',' )
            {
            // InternalLayoutConfig.g:3429:1: ( ',' )
            // InternalLayoutConfig.g:3430:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_2_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__4"
    // InternalLayoutConfig.g:3443:1: rule__ElkEdgeSection__Group_4_2__4 : rule__ElkEdgeSection__Group_4_2__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3447:1: ( rule__ElkEdgeSection__Group_4_2__4__Impl )
            // InternalLayoutConfig.g:3448:2: rule__ElkEdgeSection__Group_4_2__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_2__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_2__4__Impl"
    // InternalLayoutConfig.g:3454:1: rule__ElkEdgeSection__Group_4_2__4__Impl : ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3458:1: ( ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) ) )
            // InternalLayoutConfig.g:3459:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) )
            {
            // InternalLayoutConfig.g:3459:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) )
            // InternalLayoutConfig.g:3460:1: ( rule__ElkEdgeSection__StartYAssignment_4_2_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_2_4()); 
            // InternalLayoutConfig.g:3461:1: ( rule__ElkEdgeSection__StartYAssignment_4_2_4 )
            // InternalLayoutConfig.g:3461:2: rule__ElkEdgeSection__StartYAssignment_4_2_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__StartYAssignment_4_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_2_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_2__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__0"
    // InternalLayoutConfig.g:3481:1: rule__ElkEdgeSection__Group_4_3__0 : rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3485:1: ( rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1 )
            // InternalLayoutConfig.g:3486:2: rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__0__Impl"
    // InternalLayoutConfig.g:3493:1: rule__ElkEdgeSection__Group_4_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3497:1: ( ( 'end' ) )
            // InternalLayoutConfig.g:3498:1: ( 'end' )
            {
            // InternalLayoutConfig.g:3498:1: ( 'end' )
            // InternalLayoutConfig.g:3499:1: 'end'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_3_0()); 
            match(input,29,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__1"
    // InternalLayoutConfig.g:3512:1: rule__ElkEdgeSection__Group_4_3__1 : rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2 ;
    public final void rule__ElkEdgeSection__Group_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3516:1: ( rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2 )
            // InternalLayoutConfig.g:3517:2: rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_3__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__1__Impl"
    // InternalLayoutConfig.g:3524:1: rule__ElkEdgeSection__Group_4_3__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3528:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3529:1: ( ':' )
            {
            // InternalLayoutConfig.g:3529:1: ( ':' )
            // InternalLayoutConfig.g:3530:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_3_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__2"
    // InternalLayoutConfig.g:3543:1: rule__ElkEdgeSection__Group_4_3__2 : rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3 ;
    public final void rule__ElkEdgeSection__Group_4_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3547:1: ( rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3 )
            // InternalLayoutConfig.g:3548:2: rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkEdgeSection__Group_4_3__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__2__Impl"
    // InternalLayoutConfig.g:3555:1: rule__ElkEdgeSection__Group_4_3__2__Impl : ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3559:1: ( ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) ) )
            // InternalLayoutConfig.g:3560:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) )
            {
            // InternalLayoutConfig.g:3560:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) )
            // InternalLayoutConfig.g:3561:1: ( rule__ElkEdgeSection__EndXAssignment_4_3_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_3_2()); 
            // InternalLayoutConfig.g:3562:1: ( rule__ElkEdgeSection__EndXAssignment_4_3_2 )
            // InternalLayoutConfig.g:3562:2: rule__ElkEdgeSection__EndXAssignment_4_3_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__EndXAssignment_4_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__3"
    // InternalLayoutConfig.g:3572:1: rule__ElkEdgeSection__Group_4_3__3 : rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4 ;
    public final void rule__ElkEdgeSection__Group_4_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3576:1: ( rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4 )
            // InternalLayoutConfig.g:3577:2: rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_3__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__3__Impl"
    // InternalLayoutConfig.g:3584:1: rule__ElkEdgeSection__Group_4_3__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3588:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3589:1: ( ',' )
            {
            // InternalLayoutConfig.g:3589:1: ( ',' )
            // InternalLayoutConfig.g:3590:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_3_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__4"
    // InternalLayoutConfig.g:3603:1: rule__ElkEdgeSection__Group_4_3__4 : rule__ElkEdgeSection__Group_4_3__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3607:1: ( rule__ElkEdgeSection__Group_4_3__4__Impl )
            // InternalLayoutConfig.g:3608:2: rule__ElkEdgeSection__Group_4_3__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_3__4__Impl"
    // InternalLayoutConfig.g:3614:1: rule__ElkEdgeSection__Group_4_3__4__Impl : ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3618:1: ( ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) ) )
            // InternalLayoutConfig.g:3619:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) )
            {
            // InternalLayoutConfig.g:3619:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) )
            // InternalLayoutConfig.g:3620:1: ( rule__ElkEdgeSection__EndYAssignment_4_3_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_3_4()); 
            // InternalLayoutConfig.g:3621:1: ( rule__ElkEdgeSection__EndYAssignment_4_3_4 )
            // InternalLayoutConfig.g:3621:2: rule__ElkEdgeSection__EndYAssignment_4_3_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__EndYAssignment_4_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_3__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__0"
    // InternalLayoutConfig.g:3641:1: rule__ElkEdgeSection__Group_4_4__0 : rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1 ;
    public final void rule__ElkEdgeSection__Group_4_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3645:1: ( rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1 )
            // InternalLayoutConfig.g:3646:2: rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__0__Impl"
    // InternalLayoutConfig.g:3653:1: rule__ElkEdgeSection__Group_4_4__0__Impl : ( 'bends' ) ;
    public final void rule__ElkEdgeSection__Group_4_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3657:1: ( ( 'bends' ) )
            // InternalLayoutConfig.g:3658:1: ( 'bends' )
            {
            // InternalLayoutConfig.g:3658:1: ( 'bends' )
            // InternalLayoutConfig.g:3659:1: 'bends'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_4_0()); 
            match(input,30,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__1"
    // InternalLayoutConfig.g:3672:1: rule__ElkEdgeSection__Group_4_4__1 : rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2 ;
    public final void rule__ElkEdgeSection__Group_4_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3676:1: ( rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2 )
            // InternalLayoutConfig.g:3677:2: rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_4__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__1__Impl"
    // InternalLayoutConfig.g:3684:1: rule__ElkEdgeSection__Group_4_4__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3688:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3689:1: ( ':' )
            {
            // InternalLayoutConfig.g:3689:1: ( ':' )
            // InternalLayoutConfig.g:3690:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_4_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__2"
    // InternalLayoutConfig.g:3703:1: rule__ElkEdgeSection__Group_4_4__2 : rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3 ;
    public final void rule__ElkEdgeSection__Group_4_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3707:1: ( rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3 )
            // InternalLayoutConfig.g:3708:2: rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3
            {
            pushFollow(FollowSets000.FOLLOW_21);
            rule__ElkEdgeSection__Group_4_4__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__2__Impl"
    // InternalLayoutConfig.g:3715:1: rule__ElkEdgeSection__Group_4_4__2__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3719:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) ) )
            // InternalLayoutConfig.g:3720:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) )
            {
            // InternalLayoutConfig.g:3720:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) )
            // InternalLayoutConfig.g:3721:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_2()); 
            // InternalLayoutConfig.g:3722:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 )
            // InternalLayoutConfig.g:3722:2: rule__ElkEdgeSection__BendPointsAssignment_4_4_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_4_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__3"
    // InternalLayoutConfig.g:3732:1: rule__ElkEdgeSection__Group_4_4__3 : rule__ElkEdgeSection__Group_4_4__3__Impl ;
    public final void rule__ElkEdgeSection__Group_4_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3736:1: ( rule__ElkEdgeSection__Group_4_4__3__Impl )
            // InternalLayoutConfig.g:3737:2: rule__ElkEdgeSection__Group_4_4__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4__3__Impl"
    // InternalLayoutConfig.g:3743:1: rule__ElkEdgeSection__Group_4_4__3__Impl : ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_4_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3747:1: ( ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* ) )
            // InternalLayoutConfig.g:3748:1: ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* )
            {
            // InternalLayoutConfig.g:3748:1: ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* )
            // InternalLayoutConfig.g:3749:1: ( rule__ElkEdgeSection__Group_4_4_3__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4_3()); 
            // InternalLayoutConfig.g:3750:1: ( rule__ElkEdgeSection__Group_4_4_3__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==31) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalLayoutConfig.g:3750:2: rule__ElkEdgeSection__Group_4_4_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_22);
            	    rule__ElkEdgeSection__Group_4_4_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__0"
    // InternalLayoutConfig.g:3768:1: rule__ElkEdgeSection__Group_4_4_3__0 : rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3772:1: ( rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1 )
            // InternalLayoutConfig.g:3773:2: rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_4_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__0__Impl"
    // InternalLayoutConfig.g:3780:1: rule__ElkEdgeSection__Group_4_4_3__0__Impl : ( '|' ) ;
    public final void rule__ElkEdgeSection__Group_4_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3784:1: ( ( '|' ) )
            // InternalLayoutConfig.g:3785:1: ( '|' )
            {
            // InternalLayoutConfig.g:3785:1: ( '|' )
            // InternalLayoutConfig.g:3786:1: '|'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_4_3_0()); 
            match(input,31,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_4_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__1"
    // InternalLayoutConfig.g:3799:1: rule__ElkEdgeSection__Group_4_4_3__1 : rule__ElkEdgeSection__Group_4_4_3__1__Impl ;
    public final void rule__ElkEdgeSection__Group_4_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3803:1: ( rule__ElkEdgeSection__Group_4_4_3__1__Impl )
            // InternalLayoutConfig.g:3804:2: rule__ElkEdgeSection__Group_4_4_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_4_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_4_3__1__Impl"
    // InternalLayoutConfig.g:3810:1: rule__ElkEdgeSection__Group_4_4_3__1__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3814:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) ) )
            // InternalLayoutConfig.g:3815:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) )
            {
            // InternalLayoutConfig.g:3815:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) )
            // InternalLayoutConfig.g:3816:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_3_1()); 
            // InternalLayoutConfig.g:3817:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 )
            // InternalLayoutConfig.g:3817:2: rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_4_3__1__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__0"
    // InternalLayoutConfig.g:3831:1: rule__ElkBendPoint__Group__0 : rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 ;
    public final void rule__ElkBendPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3835:1: ( rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 )
            // InternalLayoutConfig.g:3836:2: rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkBendPoint__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkBendPoint__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__Group__0"


    // $ANTLR start "rule__ElkBendPoint__Group__0__Impl"
    // InternalLayoutConfig.g:3843:1: rule__ElkBendPoint__Group__0__Impl : ( ( rule__ElkBendPoint__XAssignment_0 ) ) ;
    public final void rule__ElkBendPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3847:1: ( ( ( rule__ElkBendPoint__XAssignment_0 ) ) )
            // InternalLayoutConfig.g:3848:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            {
            // InternalLayoutConfig.g:3848:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            // InternalLayoutConfig.g:3849:1: ( rule__ElkBendPoint__XAssignment_0 )
            {
             before(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 
            // InternalLayoutConfig.g:3850:1: ( rule__ElkBendPoint__XAssignment_0 )
            // InternalLayoutConfig.g:3850:2: rule__ElkBendPoint__XAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkBendPoint__XAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__Group__0__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__1"
    // InternalLayoutConfig.g:3860:1: rule__ElkBendPoint__Group__1 : rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 ;
    public final void rule__ElkBendPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3864:1: ( rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 )
            // InternalLayoutConfig.g:3865:2: rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkBendPoint__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkBendPoint__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__Group__1"


    // $ANTLR start "rule__ElkBendPoint__Group__1__Impl"
    // InternalLayoutConfig.g:3872:1: rule__ElkBendPoint__Group__1__Impl : ( ',' ) ;
    public final void rule__ElkBendPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3876:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3877:1: ( ',' )
            {
            // InternalLayoutConfig.g:3877:1: ( ',' )
            // InternalLayoutConfig.g:3878:1: ','
            {
             before(grammarAccess.getElkBendPointAccess().getCommaKeyword_1()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkBendPointAccess().getCommaKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__Group__1__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__2"
    // InternalLayoutConfig.g:3891:1: rule__ElkBendPoint__Group__2 : rule__ElkBendPoint__Group__2__Impl ;
    public final void rule__ElkBendPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3895:1: ( rule__ElkBendPoint__Group__2__Impl )
            // InternalLayoutConfig.g:3896:2: rule__ElkBendPoint__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkBendPoint__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__Group__2"


    // $ANTLR start "rule__ElkBendPoint__Group__2__Impl"
    // InternalLayoutConfig.g:3902:1: rule__ElkBendPoint__Group__2__Impl : ( ( rule__ElkBendPoint__YAssignment_2 ) ) ;
    public final void rule__ElkBendPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3906:1: ( ( ( rule__ElkBendPoint__YAssignment_2 ) ) )
            // InternalLayoutConfig.g:3907:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            {
            // InternalLayoutConfig.g:3907:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            // InternalLayoutConfig.g:3908:1: ( rule__ElkBendPoint__YAssignment_2 )
            {
             before(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 
            // InternalLayoutConfig.g:3909:1: ( rule__ElkBendPoint__YAssignment_2 )
            // InternalLayoutConfig.g:3909:2: rule__ElkBendPoint__YAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkBendPoint__YAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__Group__2__Impl"


    // $ANTLR start "rule__QualifiedId__Group__0"
    // InternalLayoutConfig.g:3925:1: rule__QualifiedId__Group__0 : rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 ;
    public final void rule__QualifiedId__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3929:1: ( rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 )
            // InternalLayoutConfig.g:3930:2: rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_25);
            rule__QualifiedId__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__QualifiedId__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group__0"


    // $ANTLR start "rule__QualifiedId__Group__0__Impl"
    // InternalLayoutConfig.g:3937:1: rule__QualifiedId__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3941:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:3942:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:3942:1: ( RULE_ID )
            // InternalLayoutConfig.g:3943:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group__0__Impl"


    // $ANTLR start "rule__QualifiedId__Group__1"
    // InternalLayoutConfig.g:3954:1: rule__QualifiedId__Group__1 : rule__QualifiedId__Group__1__Impl ;
    public final void rule__QualifiedId__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3958:1: ( rule__QualifiedId__Group__1__Impl )
            // InternalLayoutConfig.g:3959:2: rule__QualifiedId__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__QualifiedId__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group__1"


    // $ANTLR start "rule__QualifiedId__Group__1__Impl"
    // InternalLayoutConfig.g:3965:1: rule__QualifiedId__Group__1__Impl : ( ( rule__QualifiedId__Group_1__0 )* ) ;
    public final void rule__QualifiedId__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3969:1: ( ( ( rule__QualifiedId__Group_1__0 )* ) )
            // InternalLayoutConfig.g:3970:1: ( ( rule__QualifiedId__Group_1__0 )* )
            {
            // InternalLayoutConfig.g:3970:1: ( ( rule__QualifiedId__Group_1__0 )* )
            // InternalLayoutConfig.g:3971:1: ( rule__QualifiedId__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup_1()); 
            // InternalLayoutConfig.g:3972:1: ( rule__QualifiedId__Group_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==34) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalLayoutConfig.g:3972:2: rule__QualifiedId__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_26);
            	    rule__QualifiedId__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getQualifiedIdAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group__1__Impl"


    // $ANTLR start "rule__QualifiedId__Group_1__0"
    // InternalLayoutConfig.g:3986:1: rule__QualifiedId__Group_1__0 : rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 ;
    public final void rule__QualifiedId__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3990:1: ( rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 )
            // InternalLayoutConfig.g:3991:2: rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__QualifiedId__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__QualifiedId__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group_1__0"


    // $ANTLR start "rule__QualifiedId__Group_1__0__Impl"
    // InternalLayoutConfig.g:3998:1: rule__QualifiedId__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedId__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4002:1: ( ( '.' ) )
            // InternalLayoutConfig.g:4003:1: ( '.' )
            {
            // InternalLayoutConfig.g:4003:1: ( '.' )
            // InternalLayoutConfig.g:4004:1: '.'
            {
             before(grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0()); 
            match(input,34,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedId__Group_1__1"
    // InternalLayoutConfig.g:4017:1: rule__QualifiedId__Group_1__1 : rule__QualifiedId__Group_1__1__Impl ;
    public final void rule__QualifiedId__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4021:1: ( rule__QualifiedId__Group_1__1__Impl )
            // InternalLayoutConfig.g:4022:2: rule__QualifiedId__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__QualifiedId__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group_1__1"


    // $ANTLR start "rule__QualifiedId__Group_1__1__Impl"
    // InternalLayoutConfig.g:4028:1: rule__QualifiedId__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4032:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4033:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4033:1: ( RULE_ID )
            // InternalLayoutConfig.g:4034:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedId__Group_1__1__Impl"


    // $ANTLR start "rule__Property__Group__0"
    // InternalLayoutConfig.g:4049:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4053:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalLayoutConfig.g:4054:2: rule__Property__Group__0__Impl rule__Property__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__Property__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__Property__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__Group__0"


    // $ANTLR start "rule__Property__Group__0__Impl"
    // InternalLayoutConfig.g:4061:1: rule__Property__Group__0__Impl : ( ( rule__Property__KeyAssignment_0 ) ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4065:1: ( ( ( rule__Property__KeyAssignment_0 ) ) )
            // InternalLayoutConfig.g:4066:1: ( ( rule__Property__KeyAssignment_0 ) )
            {
            // InternalLayoutConfig.g:4066:1: ( ( rule__Property__KeyAssignment_0 ) )
            // InternalLayoutConfig.g:4067:1: ( rule__Property__KeyAssignment_0 )
            {
             before(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 
            // InternalLayoutConfig.g:4068:1: ( rule__Property__KeyAssignment_0 )
            // InternalLayoutConfig.g:4068:2: rule__Property__KeyAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Property__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__Group__0__Impl"


    // $ANTLR start "rule__Property__Group__1"
    // InternalLayoutConfig.g:4078:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4082:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalLayoutConfig.g:4083:2: rule__Property__Group__1__Impl rule__Property__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_27);
            rule__Property__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__Property__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__Group__1"


    // $ANTLR start "rule__Property__Group__1__Impl"
    // InternalLayoutConfig.g:4090:1: rule__Property__Group__1__Impl : ( ':' ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4094:1: ( ( ':' ) )
            // InternalLayoutConfig.g:4095:1: ( ':' )
            {
            // InternalLayoutConfig.g:4095:1: ( ':' )
            // InternalLayoutConfig.g:4096:1: ':'
            {
             before(grammarAccess.getPropertyAccess().getColonKeyword_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__Group__1__Impl"


    // $ANTLR start "rule__Property__Group__2"
    // InternalLayoutConfig.g:4109:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4113:1: ( rule__Property__Group__2__Impl )
            // InternalLayoutConfig.g:4114:2: rule__Property__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Property__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__Group__2"


    // $ANTLR start "rule__Property__Group__2__Impl"
    // InternalLayoutConfig.g:4120:1: rule__Property__Group__2__Impl : ( ( rule__Property__Alternatives_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4124:1: ( ( ( rule__Property__Alternatives_2 ) ) )
            // InternalLayoutConfig.g:4125:1: ( ( rule__Property__Alternatives_2 ) )
            {
            // InternalLayoutConfig.g:4125:1: ( ( rule__Property__Alternatives_2 ) )
            // InternalLayoutConfig.g:4126:1: ( rule__Property__Alternatives_2 )
            {
             before(grammarAccess.getPropertyAccess().getAlternatives_2()); 
            // InternalLayoutConfig.g:4127:1: ( rule__Property__Alternatives_2 )
            // InternalLayoutConfig.g:4127:2: rule__Property__Alternatives_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Property__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getAlternatives_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__Group__2__Impl"


    // $ANTLR start "rule__PropertyKey__Group__0"
    // InternalLayoutConfig.g:4143:1: rule__PropertyKey__Group__0 : rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 ;
    public final void rule__PropertyKey__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4147:1: ( rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 )
            // InternalLayoutConfig.g:4148:2: rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_25);
            rule__PropertyKey__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__PropertyKey__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group__0"


    // $ANTLR start "rule__PropertyKey__Group__0__Impl"
    // InternalLayoutConfig.g:4155:1: rule__PropertyKey__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4159:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4160:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4160:1: ( RULE_ID )
            // InternalLayoutConfig.g:4161:1: RULE_ID
            {
             before(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group__0__Impl"


    // $ANTLR start "rule__PropertyKey__Group__1"
    // InternalLayoutConfig.g:4172:1: rule__PropertyKey__Group__1 : rule__PropertyKey__Group__1__Impl ;
    public final void rule__PropertyKey__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4176:1: ( rule__PropertyKey__Group__1__Impl )
            // InternalLayoutConfig.g:4177:2: rule__PropertyKey__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PropertyKey__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group__1"


    // $ANTLR start "rule__PropertyKey__Group__1__Impl"
    // InternalLayoutConfig.g:4183:1: rule__PropertyKey__Group__1__Impl : ( ( rule__PropertyKey__Group_1__0 )* ) ;
    public final void rule__PropertyKey__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4187:1: ( ( ( rule__PropertyKey__Group_1__0 )* ) )
            // InternalLayoutConfig.g:4188:1: ( ( rule__PropertyKey__Group_1__0 )* )
            {
            // InternalLayoutConfig.g:4188:1: ( ( rule__PropertyKey__Group_1__0 )* )
            // InternalLayoutConfig.g:4189:1: ( rule__PropertyKey__Group_1__0 )*
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup_1()); 
            // InternalLayoutConfig.g:4190:1: ( rule__PropertyKey__Group_1__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==34) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalLayoutConfig.g:4190:2: rule__PropertyKey__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_26);
            	    rule__PropertyKey__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getPropertyKeyAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group__1__Impl"


    // $ANTLR start "rule__PropertyKey__Group_1__0"
    // InternalLayoutConfig.g:4204:1: rule__PropertyKey__Group_1__0 : rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 ;
    public final void rule__PropertyKey__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4208:1: ( rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 )
            // InternalLayoutConfig.g:4209:2: rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__PropertyKey__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__PropertyKey__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group_1__0"


    // $ANTLR start "rule__PropertyKey__Group_1__0__Impl"
    // InternalLayoutConfig.g:4216:1: rule__PropertyKey__Group_1__0__Impl : ( '.' ) ;
    public final void rule__PropertyKey__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4220:1: ( ( '.' ) )
            // InternalLayoutConfig.g:4221:1: ( '.' )
            {
            // InternalLayoutConfig.g:4221:1: ( '.' )
            // InternalLayoutConfig.g:4222:1: '.'
            {
             before(grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0()); 
            match(input,34,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group_1__0__Impl"


    // $ANTLR start "rule__PropertyKey__Group_1__1"
    // InternalLayoutConfig.g:4235:1: rule__PropertyKey__Group_1__1 : rule__PropertyKey__Group_1__1__Impl ;
    public final void rule__PropertyKey__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4239:1: ( rule__PropertyKey__Group_1__1__Impl )
            // InternalLayoutConfig.g:4240:2: rule__PropertyKey__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PropertyKey__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group_1__1"


    // $ANTLR start "rule__PropertyKey__Group_1__1__Impl"
    // InternalLayoutConfig.g:4246:1: rule__PropertyKey__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4250:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4251:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4251:1: ( RULE_ID )
            // InternalLayoutConfig.g:4252:1: RULE_ID
            {
             before(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyKey__Group_1__1__Impl"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2"
    // InternalLayoutConfig.g:4268:1: rule__ShapeLayout__UnorderedGroup_2 : ( rule__ShapeLayout__UnorderedGroup_2__0 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            
        try {
            // InternalLayoutConfig.g:4273:1: ( ( rule__ShapeLayout__UnorderedGroup_2__0 )? )
            // InternalLayoutConfig.g:4274:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            {
            // InternalLayoutConfig.g:4274:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( LA22_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt22=1;
            }
            else if ( LA22_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalLayoutConfig.g:4274:2: rule__ShapeLayout__UnorderedGroup_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ShapeLayout__UnorderedGroup_2__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2__Impl"
    // InternalLayoutConfig.g:4284:1: rule__ShapeLayout__UnorderedGroup_2__Impl : ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) ;
    public final void rule__ShapeLayout__UnorderedGroup_2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4289:1: ( ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4290:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4290:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( LA23_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt23=1;
            }
            else if ( LA23_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // InternalLayoutConfig.g:4292:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4292:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4293:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
                    }
                    // InternalLayoutConfig.g:4293:108: ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    // InternalLayoutConfig.g:4294:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4300:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    // InternalLayoutConfig.g:4302:7: ( rule__ShapeLayout__Group_2_0__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 
                    // InternalLayoutConfig.g:4303:7: ( rule__ShapeLayout__Group_2_0__0 )
                    // InternalLayoutConfig.g:4303:8: rule__ShapeLayout__Group_2_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ShapeLayout__Group_2_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:4309:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4309:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4310:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
                    }
                    // InternalLayoutConfig.g:4310:108: ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    // InternalLayoutConfig.g:4311:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4317:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    // InternalLayoutConfig.g:4319:7: ( rule__ShapeLayout__Group_2_1__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 
                    // InternalLayoutConfig.g:4320:7: ( rule__ShapeLayout__Group_2_1__0 )
                    // InternalLayoutConfig.g:4320:8: rule__ShapeLayout__Group_2_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ShapeLayout__Group_2_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2__Impl"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2__0"
    // InternalLayoutConfig.g:4335:1: rule__ShapeLayout__UnorderedGroup_2__0 : rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4339:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? )
            // InternalLayoutConfig.g:4340:2: rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_28);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4341:2: ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( LA24_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt24=1;
            }
            else if ( LA24_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalLayoutConfig.g:4341:2: rule__ShapeLayout__UnorderedGroup_2__1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ShapeLayout__UnorderedGroup_2__1();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2__0"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2__1"
    // InternalLayoutConfig.g:4348:1: rule__ShapeLayout__UnorderedGroup_2__1 : rule__ShapeLayout__UnorderedGroup_2__Impl ;
    public final void rule__ShapeLayout__UnorderedGroup_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4352:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl )
            // InternalLayoutConfig.g:4353:2: rule__ShapeLayout__UnorderedGroup_2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1"
    // InternalLayoutConfig.g:4364:1: rule__ElkSingleEdgeSection__UnorderedGroup_1 : ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1());
            
        try {
            // InternalLayoutConfig.g:4369:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )? )
            // InternalLayoutConfig.g:4370:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )?
            {
            // InternalLayoutConfig.g:4370:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( LA25_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt25=1;
            }
            else if ( LA25_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt25=1;
            }
            else if ( LA25_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt25=1;
            }
            else if ( LA25_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt25=1;
            }
            else if ( LA25_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalLayoutConfig.g:4370:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl"
    // InternalLayoutConfig.g:4380:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl : ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) ) ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4385:1: ( ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4386:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4386:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) )
            int alt26=5;
            int LA26_0 = input.LA(1);

            if ( LA26_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt26=1;
            }
            else if ( LA26_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt26=2;
            }
            else if ( LA26_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt26=3;
            }
            else if ( LA26_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt26=4;
            }
            else if ( LA26_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt26=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // InternalLayoutConfig.g:4388:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4388:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4389:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0)");
                    }
                    // InternalLayoutConfig.g:4389:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) )
                    // InternalLayoutConfig.g:4390:6: ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4396:6: ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) )
                    // InternalLayoutConfig.g:4398:7: ( rule__ElkSingleEdgeSection__Group_1_0__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0()); 
                    // InternalLayoutConfig.g:4399:7: ( rule__ElkSingleEdgeSection__Group_1_0__0 )
                    // InternalLayoutConfig.g:4399:8: rule__ElkSingleEdgeSection__Group_1_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:4405:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4405:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4406:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1)");
                    }
                    // InternalLayoutConfig.g:4406:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) )
                    // InternalLayoutConfig.g:4407:6: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4413:6: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) )
                    // InternalLayoutConfig.g:4415:7: ( rule__ElkSingleEdgeSection__Group_1_1__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 
                    // InternalLayoutConfig.g:4416:7: ( rule__ElkSingleEdgeSection__Group_1_1__0 )
                    // InternalLayoutConfig.g:4416:8: rule__ElkSingleEdgeSection__Group_1_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLayoutConfig.g:4422:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4422:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) )
                    // InternalLayoutConfig.g:4423:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2)");
                    }
                    // InternalLayoutConfig.g:4423:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) )
                    // InternalLayoutConfig.g:4424:6: ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4430:6: ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) )
                    // InternalLayoutConfig.g:4432:7: ( rule__ElkSingleEdgeSection__Group_1_2__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_2()); 
                    // InternalLayoutConfig.g:4433:7: ( rule__ElkSingleEdgeSection__Group_1_2__0 )
                    // InternalLayoutConfig.g:4433:8: rule__ElkSingleEdgeSection__Group_1_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalLayoutConfig.g:4439:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4439:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) )
                    // InternalLayoutConfig.g:4440:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3)");
                    }
                    // InternalLayoutConfig.g:4440:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) )
                    // InternalLayoutConfig.g:4441:6: ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4447:6: ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) )
                    // InternalLayoutConfig.g:4449:7: ( rule__ElkSingleEdgeSection__Group_1_3__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_3()); 
                    // InternalLayoutConfig.g:4450:7: ( rule__ElkSingleEdgeSection__Group_1_3__0 )
                    // InternalLayoutConfig.g:4450:8: rule__ElkSingleEdgeSection__Group_1_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalLayoutConfig.g:4456:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4456:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) )
                    // InternalLayoutConfig.g:4457:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4)");
                    }
                    // InternalLayoutConfig.g:4457:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) )
                    // InternalLayoutConfig.g:4458:6: ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4464:6: ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) )
                    // InternalLayoutConfig.g:4466:7: ( rule__ElkSingleEdgeSection__Group_1_4__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4()); 
                    // InternalLayoutConfig.g:4467:7: ( rule__ElkSingleEdgeSection__Group_1_4__0 )
                    // InternalLayoutConfig.g:4467:8: rule__ElkSingleEdgeSection__Group_1_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__0"
    // InternalLayoutConfig.g:4482:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__0 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4486:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )? )
            // InternalLayoutConfig.g:4487:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4488:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( LA27_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt27=1;
            }
            else if ( LA27_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt27=1;
            }
            else if ( LA27_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt27=1;
            }
            else if ( LA27_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt27=1;
            }
            else if ( LA27_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalLayoutConfig.g:4488:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__1();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__1"
    // InternalLayoutConfig.g:4495:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__1 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4499:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )? )
            // InternalLayoutConfig.g:4500:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4501:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( LA28_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt28=1;
            }
            else if ( LA28_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt28=1;
            }
            else if ( LA28_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt28=1;
            }
            else if ( LA28_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt28=1;
            }
            else if ( LA28_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalLayoutConfig.g:4501:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__2();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__2"
    // InternalLayoutConfig.g:4508:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__2 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4512:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )? )
            // InternalLayoutConfig.g:4513:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4514:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( LA29_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt29=1;
            }
            else if ( LA29_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt29=1;
            }
            else if ( LA29_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt29=1;
            }
            else if ( LA29_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt29=1;
            }
            else if ( LA29_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalLayoutConfig.g:4514:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__3
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__3();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__3"
    // InternalLayoutConfig.g:4521:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__3 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4525:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )? )
            // InternalLayoutConfig.g:4526:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4527:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( LA30_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt30=1;
            }
            else if ( LA30_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt30=1;
            }
            else if ( LA30_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt30=1;
            }
            else if ( LA30_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt30=1;
            }
            else if ( LA30_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalLayoutConfig.g:4527:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__4
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1__4();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1__4"
    // InternalLayoutConfig.g:4534:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__4 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4538:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl )
            // InternalLayoutConfig.g:4539:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1__4"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4"
    // InternalLayoutConfig.g:4556:1: rule__ElkEdgeSection__UnorderedGroup_4 : ( rule__ElkEdgeSection__UnorderedGroup_4__0 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4());
            
        try {
            // InternalLayoutConfig.g:4561:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4__0 )? )
            // InternalLayoutConfig.g:4562:2: ( rule__ElkEdgeSection__UnorderedGroup_4__0 )?
            {
            // InternalLayoutConfig.g:4562:2: ( rule__ElkEdgeSection__UnorderedGroup_4__0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( LA31_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt31=1;
            }
            else if ( LA31_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt31=1;
            }
            else if ( LA31_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt31=1;
            }
            else if ( LA31_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt31=1;
            }
            else if ( LA31_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalLayoutConfig.g:4562:2: rule__ElkEdgeSection__UnorderedGroup_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__Impl"
    // InternalLayoutConfig.g:4572:1: rule__ElkEdgeSection__UnorderedGroup_4__Impl : ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) ) ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4577:1: ( ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4578:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4578:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) )
            int alt32=5;
            int LA32_0 = input.LA(1);

            if ( LA32_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt32=1;
            }
            else if ( LA32_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt32=2;
            }
            else if ( LA32_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt32=3;
            }
            else if ( LA32_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt32=4;
            }
            else if ( LA32_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt32=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // InternalLayoutConfig.g:4580:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4580:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4581:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0)");
                    }
                    // InternalLayoutConfig.g:4581:111: ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) )
                    // InternalLayoutConfig.g:4582:6: ( ( rule__ElkEdgeSection__Group_4_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4588:6: ( ( rule__ElkEdgeSection__Group_4_0__0 ) )
                    // InternalLayoutConfig.g:4590:7: ( rule__ElkEdgeSection__Group_4_0__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0()); 
                    // InternalLayoutConfig.g:4591:7: ( rule__ElkEdgeSection__Group_4_0__0 )
                    // InternalLayoutConfig.g:4591:8: rule__ElkEdgeSection__Group_4_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:4597:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4597:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4598:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1)");
                    }
                    // InternalLayoutConfig.g:4598:111: ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) )
                    // InternalLayoutConfig.g:4599:6: ( ( rule__ElkEdgeSection__Group_4_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4605:6: ( ( rule__ElkEdgeSection__Group_4_1__0 ) )
                    // InternalLayoutConfig.g:4607:7: ( rule__ElkEdgeSection__Group_4_1__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 
                    // InternalLayoutConfig.g:4608:7: ( rule__ElkEdgeSection__Group_4_1__0 )
                    // InternalLayoutConfig.g:4608:8: rule__ElkEdgeSection__Group_4_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLayoutConfig.g:4614:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4614:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) )
                    // InternalLayoutConfig.g:4615:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2)");
                    }
                    // InternalLayoutConfig.g:4615:111: ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) )
                    // InternalLayoutConfig.g:4616:6: ( ( rule__ElkEdgeSection__Group_4_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4622:6: ( ( rule__ElkEdgeSection__Group_4_2__0 ) )
                    // InternalLayoutConfig.g:4624:7: ( rule__ElkEdgeSection__Group_4_2__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_2()); 
                    // InternalLayoutConfig.g:4625:7: ( rule__ElkEdgeSection__Group_4_2__0 )
                    // InternalLayoutConfig.g:4625:8: rule__ElkEdgeSection__Group_4_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalLayoutConfig.g:4631:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4631:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) )
                    // InternalLayoutConfig.g:4632:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3)");
                    }
                    // InternalLayoutConfig.g:4632:111: ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) )
                    // InternalLayoutConfig.g:4633:6: ( ( rule__ElkEdgeSection__Group_4_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4639:6: ( ( rule__ElkEdgeSection__Group_4_3__0 ) )
                    // InternalLayoutConfig.g:4641:7: ( rule__ElkEdgeSection__Group_4_3__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_3()); 
                    // InternalLayoutConfig.g:4642:7: ( rule__ElkEdgeSection__Group_4_3__0 )
                    // InternalLayoutConfig.g:4642:8: rule__ElkEdgeSection__Group_4_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_3()); 

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalLayoutConfig.g:4648:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4648:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) )
                    // InternalLayoutConfig.g:4649:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4)");
                    }
                    // InternalLayoutConfig.g:4649:111: ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) )
                    // InternalLayoutConfig.g:4650:6: ( ( rule__ElkEdgeSection__Group_4_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4656:6: ( ( rule__ElkEdgeSection__Group_4_4__0 ) )
                    // InternalLayoutConfig.g:4658:7: ( rule__ElkEdgeSection__Group_4_4__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4()); 
                    // InternalLayoutConfig.g:4659:7: ( rule__ElkEdgeSection__Group_4_4__0 )
                    // InternalLayoutConfig.g:4659:8: rule__ElkEdgeSection__Group_4_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__0"
    // InternalLayoutConfig.g:4674:1: rule__ElkEdgeSection__UnorderedGroup_4__0 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4678:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )? )
            // InternalLayoutConfig.g:4679:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4680:2: ( rule__ElkEdgeSection__UnorderedGroup_4__1 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( LA33_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt33=1;
            }
            else if ( LA33_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt33=1;
            }
            else if ( LA33_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt33=1;
            }
            else if ( LA33_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt33=1;
            }
            else if ( LA33_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalLayoutConfig.g:4680:2: rule__ElkEdgeSection__UnorderedGroup_4__1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__1();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__0"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__1"
    // InternalLayoutConfig.g:4687:1: rule__ElkEdgeSection__UnorderedGroup_4__1 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4691:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )? )
            // InternalLayoutConfig.g:4692:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4693:2: ( rule__ElkEdgeSection__UnorderedGroup_4__2 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( LA34_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt34=1;
            }
            else if ( LA34_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt34=1;
            }
            else if ( LA34_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt34=1;
            }
            else if ( LA34_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt34=1;
            }
            else if ( LA34_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalLayoutConfig.g:4693:2: rule__ElkEdgeSection__UnorderedGroup_4__2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__2();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__1"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__2"
    // InternalLayoutConfig.g:4700:1: rule__ElkEdgeSection__UnorderedGroup_4__2 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4704:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )? )
            // InternalLayoutConfig.g:4705:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4706:2: ( rule__ElkEdgeSection__UnorderedGroup_4__3 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( LA35_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt35=1;
            }
            else if ( LA35_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt35=1;
            }
            else if ( LA35_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt35=1;
            }
            else if ( LA35_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt35=1;
            }
            else if ( LA35_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalLayoutConfig.g:4706:2: rule__ElkEdgeSection__UnorderedGroup_4__3
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__3();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__2"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__3"
    // InternalLayoutConfig.g:4713:1: rule__ElkEdgeSection__UnorderedGroup_4__3 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4717:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )? )
            // InternalLayoutConfig.g:4718:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4719:2: ( rule__ElkEdgeSection__UnorderedGroup_4__4 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( LA36_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt36=1;
            }
            else if ( LA36_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt36=1;
            }
            else if ( LA36_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt36=1;
            }
            else if ( LA36_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt36=1;
            }
            else if ( LA36_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalLayoutConfig.g:4719:2: rule__ElkEdgeSection__UnorderedGroup_4__4
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4__4();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__3"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4__4"
    // InternalLayoutConfig.g:4726:1: rule__ElkEdgeSection__UnorderedGroup_4__4 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4730:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl )
            // InternalLayoutConfig.g:4731:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4__4"


    // $ANTLR start "rule__RootNode__ChildrenAssignment_1"
    // InternalLayoutConfig.g:4748:1: rule__RootNode__ChildrenAssignment_1 : ( ruleElkNode ) ;
    public final void rule__RootNode__ChildrenAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4752:1: ( ( ruleElkNode ) )
            // InternalLayoutConfig.g:4753:1: ( ruleElkNode )
            {
            // InternalLayoutConfig.g:4753:1: ( ruleElkNode )
            // InternalLayoutConfig.g:4754:1: ruleElkNode
            {
             before(grammarAccess.getRootNodeAccess().getChildrenElkNodeParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkNode();

            state._fsp--;

             after(grammarAccess.getRootNodeAccess().getChildrenElkNodeParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RootNode__ChildrenAssignment_1"


    // $ANTLR start "rule__ElkNode__IdentifierAssignment_0"
    // InternalLayoutConfig.g:4763:1: rule__ElkNode__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__ElkNode__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4767:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4768:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4768:1: ( RULE_ID )
            // InternalLayoutConfig.g:4769:1: RULE_ID
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getIdentifierIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__IdentifierAssignment_0"


    // $ANTLR start "rule__ElkNode__PropertiesAssignment_2"
    // InternalLayoutConfig.g:4778:1: rule__ElkNode__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__ElkNode__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4782:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4783:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4783:1: ( ruleProperty )
            // InternalLayoutConfig.g:4784:1: ruleProperty
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesPropertyParserRuleCall_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getPropertiesPropertyParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__PropertiesAssignment_2"


    // $ANTLR start "rule__ElkNode__ChildrenAssignment_3_1"
    // InternalLayoutConfig.g:4793:1: rule__ElkNode__ChildrenAssignment_3_1 : ( ruleRefElkNode ) ;
    public final void rule__ElkNode__ChildrenAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4797:1: ( ( ruleRefElkNode ) )
            // InternalLayoutConfig.g:4798:1: ( ruleRefElkNode )
            {
            // InternalLayoutConfig.g:4798:1: ( ruleRefElkNode )
            // InternalLayoutConfig.g:4799:1: ruleRefElkNode
            {
             before(grammarAccess.getElkNodeAccess().getChildrenRefElkNodeParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleRefElkNode();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getChildrenRefElkNodeParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkNode__ChildrenAssignment_3_1"


    // $ANTLR start "rule__RefElkNode__IdentifierAssignment_0"
    // InternalLayoutConfig.g:4808:1: rule__RefElkNode__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__RefElkNode__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4812:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4813:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4813:1: ( RULE_ID )
            // InternalLayoutConfig.g:4814:1: RULE_ID
            {
             before(grammarAccess.getRefElkNodeAccess().getIdentifierIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getRefElkNodeAccess().getIdentifierIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__IdentifierAssignment_0"


    // $ANTLR start "rule__RefElkNode__PropertiesAssignment_2"
    // InternalLayoutConfig.g:4823:1: rule__RefElkNode__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__RefElkNode__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4827:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4828:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4828:1: ( ruleProperty )
            // InternalLayoutConfig.g:4829:1: ruleProperty
            {
             before(grammarAccess.getRefElkNodeAccess().getPropertiesPropertyParserRuleCall_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getRefElkNodeAccess().getPropertiesPropertyParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RefElkNode__PropertiesAssignment_2"


    // $ANTLR start "rule__ElkLabel__IdentifierAssignment_1_0"
    // InternalLayoutConfig.g:4838:1: rule__ElkLabel__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkLabel__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4842:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4843:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4843:1: ( RULE_ID )
            // InternalLayoutConfig.g:4844:1: RULE_ID
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__IdentifierAssignment_1_0"


    // $ANTLR start "rule__ElkLabel__TextAssignment_2"
    // InternalLayoutConfig.g:4853:1: rule__ElkLabel__TextAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ElkLabel__TextAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4857:1: ( ( RULE_STRING ) )
            // InternalLayoutConfig.g:4858:1: ( RULE_STRING )
            {
            // InternalLayoutConfig.g:4858:1: ( RULE_STRING )
            // InternalLayoutConfig.g:4859:1: RULE_STRING
            {
             before(grammarAccess.getElkLabelAccess().getTextSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getTextSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__TextAssignment_2"


    // $ANTLR start "rule__ElkLabel__PropertiesAssignment_3_2"
    // InternalLayoutConfig.g:4868:1: rule__ElkLabel__PropertiesAssignment_3_2 : ( ruleProperty ) ;
    public final void rule__ElkLabel__PropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4872:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4873:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4873:1: ( ruleProperty )
            // InternalLayoutConfig.g:4874:1: ruleProperty
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesPropertyParserRuleCall_3_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkLabelAccess().getPropertiesPropertyParserRuleCall_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__PropertiesAssignment_3_2"


    // $ANTLR start "rule__ElkLabel__LabelsAssignment_3_3"
    // InternalLayoutConfig.g:4883:1: rule__ElkLabel__LabelsAssignment_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkLabel__LabelsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4887:1: ( ( ruleElkLabel ) )
            // InternalLayoutConfig.g:4888:1: ( ruleElkLabel )
            {
            // InternalLayoutConfig.g:4888:1: ( ruleElkLabel )
            // InternalLayoutConfig.g:4889:1: ruleElkLabel
            {
             before(grammarAccess.getElkLabelAccess().getLabelsElkLabelParserRuleCall_3_3_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkLabelAccess().getLabelsElkLabelParserRuleCall_3_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkLabel__LabelsAssignment_3_3"


    // $ANTLR start "rule__ShapeLayout__XAssignment_2_0_2"
    // InternalLayoutConfig.g:4901:1: rule__ShapeLayout__XAssignment_2_0_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__XAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4905:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4906:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4906:1: ( ruleNumber )
            // InternalLayoutConfig.g:4907:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getXNumberParserRuleCall_2_0_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getXNumberParserRuleCall_2_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__XAssignment_2_0_2"


    // $ANTLR start "rule__ShapeLayout__YAssignment_2_0_4"
    // InternalLayoutConfig.g:4916:1: rule__ShapeLayout__YAssignment_2_0_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__YAssignment_2_0_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4920:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4921:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4921:1: ( ruleNumber )
            // InternalLayoutConfig.g:4922:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getYNumberParserRuleCall_2_0_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getYNumberParserRuleCall_2_0_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__YAssignment_2_0_4"


    // $ANTLR start "rule__ShapeLayout__WidthAssignment_2_1_2"
    // InternalLayoutConfig.g:4931:1: rule__ShapeLayout__WidthAssignment_2_1_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__WidthAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4935:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4936:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4936:1: ( ruleNumber )
            // InternalLayoutConfig.g:4937:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthNumberParserRuleCall_2_1_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getWidthNumberParserRuleCall_2_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__WidthAssignment_2_1_2"


    // $ANTLR start "rule__ShapeLayout__HeightAssignment_2_1_4"
    // InternalLayoutConfig.g:4946:1: rule__ShapeLayout__HeightAssignment_2_1_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__HeightAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4950:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4951:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4951:1: ( ruleNumber )
            // InternalLayoutConfig.g:4952:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightNumberParserRuleCall_2_1_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getHeightNumberParserRuleCall_2_1_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__HeightAssignment_2_1_4"


    // $ANTLR start "rule__EdgeLayout__SectionsAssignment_2_0"
    // InternalLayoutConfig.g:4968:1: rule__EdgeLayout__SectionsAssignment_2_0 : ( ruleElkSingleEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4972:1: ( ( ruleElkSingleEdgeSection ) )
            // InternalLayoutConfig.g:4973:1: ( ruleElkSingleEdgeSection )
            {
            // InternalLayoutConfig.g:4973:1: ( ruleElkSingleEdgeSection )
            // InternalLayoutConfig.g:4974:1: ruleElkSingleEdgeSection
            {
             before(grammarAccess.getEdgeLayoutAccess().getSectionsElkSingleEdgeSectionParserRuleCall_2_0_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkSingleEdgeSection();

            state._fsp--;

             after(grammarAccess.getEdgeLayoutAccess().getSectionsElkSingleEdgeSectionParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__SectionsAssignment_2_0"


    // $ANTLR start "rule__EdgeLayout__SectionsAssignment_2_1"
    // InternalLayoutConfig.g:4983:1: rule__EdgeLayout__SectionsAssignment_2_1 : ( ruleElkEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4987:1: ( ( ruleElkEdgeSection ) )
            // InternalLayoutConfig.g:4988:1: ( ruleElkEdgeSection )
            {
            // InternalLayoutConfig.g:4988:1: ( ruleElkEdgeSection )
            // InternalLayoutConfig.g:4989:1: ruleElkEdgeSection
            {
             before(grammarAccess.getEdgeLayoutAccess().getSectionsElkEdgeSectionParserRuleCall_2_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkEdgeSection();

            state._fsp--;

             after(grammarAccess.getEdgeLayoutAccess().getSectionsElkEdgeSectionParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EdgeLayout__SectionsAssignment_2_1"


    // $ANTLR start "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2"
    // InternalLayoutConfig.g:4998:1: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5002:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5003:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5003:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5004:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_2_0()); 
            // InternalLayoutConfig.g:5005:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5006:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2"
    // InternalLayoutConfig.g:5017:1: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5021:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5022:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5022:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5023:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_1_2_0()); 
            // InternalLayoutConfig.g:5024:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5025:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_1_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_1_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartXAssignment_1_2_2"
    // InternalLayoutConfig.g:5036:1: rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartXAssignment_1_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5040:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5041:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5041:1: ( ruleNumber )
            // InternalLayoutConfig.g:5042:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_2_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__StartXAssignment_1_2_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartYAssignment_1_2_4"
    // InternalLayoutConfig.g:5051:1: rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartYAssignment_1_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5055:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5056:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5056:1: ( ruleNumber )
            // InternalLayoutConfig.g:5057:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_2_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__StartYAssignment_1_2_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndXAssignment_1_3_2"
    // InternalLayoutConfig.g:5066:1: rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndXAssignment_1_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5070:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5071:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5071:1: ( ruleNumber )
            // InternalLayoutConfig.g:5072:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_3_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__EndXAssignment_1_3_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndYAssignment_1_3_4"
    // InternalLayoutConfig.g:5081:1: rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndYAssignment_1_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5085:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5086:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5086:1: ( ruleNumber )
            // InternalLayoutConfig.g:5087:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_3_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__EndYAssignment_1_3_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2"
    // InternalLayoutConfig.g:5096:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5100:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5101:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5101:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5102:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1"
    // InternalLayoutConfig.g:5111:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5115:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5116:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5116:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5117:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_4_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1"


    // $ANTLR start "rule__ElkEdgeSection__IdentifierAssignment_1"
    // InternalLayoutConfig.g:5126:1: rule__ElkEdgeSection__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkEdgeSection__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5130:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5131:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:5131:1: ( RULE_ID )
            // InternalLayoutConfig.g:5132:1: RULE_ID
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getIdentifierIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__IdentifierAssignment_1"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1"
    // InternalLayoutConfig.g:5141:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5145:1: ( ( ( RULE_ID ) ) )
            // InternalLayoutConfig.g:5146:1: ( ( RULE_ID ) )
            {
            // InternalLayoutConfig.g:5146:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5147:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
            // InternalLayoutConfig.g:5148:1: ( RULE_ID )
            // InternalLayoutConfig.g:5149:1: RULE_ID
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_1_0_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_1_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1"
    // InternalLayoutConfig.g:5160:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5164:1: ( ( ( RULE_ID ) ) )
            // InternalLayoutConfig.g:5165:1: ( ( RULE_ID ) )
            {
            // InternalLayoutConfig.g:5165:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5166:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
            // InternalLayoutConfig.g:5167:1: ( RULE_ID )
            // InternalLayoutConfig.g:5168:1: RULE_ID
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_2_1_0_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_2_1_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1"


    // $ANTLR start "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2"
    // InternalLayoutConfig.g:5179:1: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5183:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5184:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5184:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5185:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_2_0()); 
            // InternalLayoutConfig.g:5186:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5187:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2"
    // InternalLayoutConfig.g:5198:1: rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5202:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5203:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5203:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5204:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_1_2_0()); 
            // InternalLayoutConfig.g:5205:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5206:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_1_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_1_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2"


    // $ANTLR start "rule__ElkEdgeSection__StartXAssignment_4_2_2"
    // InternalLayoutConfig.g:5217:1: rule__ElkEdgeSection__StartXAssignment_4_2_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartXAssignment_4_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5221:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5222:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5222:1: ( ruleNumber )
            // InternalLayoutConfig.g:5223:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_2_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__StartXAssignment_4_2_2"


    // $ANTLR start "rule__ElkEdgeSection__StartYAssignment_4_2_4"
    // InternalLayoutConfig.g:5232:1: rule__ElkEdgeSection__StartYAssignment_4_2_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartYAssignment_4_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5236:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5237:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5237:1: ( ruleNumber )
            // InternalLayoutConfig.g:5238:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_2_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__StartYAssignment_4_2_4"


    // $ANTLR start "rule__ElkEdgeSection__EndXAssignment_4_3_2"
    // InternalLayoutConfig.g:5247:1: rule__ElkEdgeSection__EndXAssignment_4_3_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndXAssignment_4_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5251:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5252:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5252:1: ( ruleNumber )
            // InternalLayoutConfig.g:5253:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_3_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__EndXAssignment_4_3_2"


    // $ANTLR start "rule__ElkEdgeSection__EndYAssignment_4_3_4"
    // InternalLayoutConfig.g:5262:1: rule__ElkEdgeSection__EndYAssignment_4_3_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndYAssignment_4_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5266:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5267:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5267:1: ( ruleNumber )
            // InternalLayoutConfig.g:5268:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_3_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__EndYAssignment_4_3_4"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_4_2"
    // InternalLayoutConfig.g:5277:1: rule__ElkEdgeSection__BendPointsAssignment_4_4_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5281:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5282:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5282:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5283:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_4_2"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1"
    // InternalLayoutConfig.g:5292:1: rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5296:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5297:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5297:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5298:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_4_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1"


    // $ANTLR start "rule__ElkBendPoint__XAssignment_0"
    // InternalLayoutConfig.g:5307:1: rule__ElkBendPoint__XAssignment_0 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5311:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5312:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5312:1: ( ruleNumber )
            // InternalLayoutConfig.g:5313:1: ruleNumber
            {
             before(grammarAccess.getElkBendPointAccess().getXNumberParserRuleCall_0_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkBendPointAccess().getXNumberParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__XAssignment_0"


    // $ANTLR start "rule__ElkBendPoint__YAssignment_2"
    // InternalLayoutConfig.g:5322:1: rule__ElkBendPoint__YAssignment_2 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5326:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5327:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5327:1: ( ruleNumber )
            // InternalLayoutConfig.g:5328:1: ruleNumber
            {
             before(grammarAccess.getElkBendPointAccess().getYNumberParserRuleCall_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkBendPointAccess().getYNumberParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkBendPoint__YAssignment_2"


    // $ANTLR start "rule__Property__KeyAssignment_0"
    // InternalLayoutConfig.g:5337:1: rule__Property__KeyAssignment_0 : ( rulePropertyKey ) ;
    public final void rule__Property__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5341:1: ( ( rulePropertyKey ) )
            // InternalLayoutConfig.g:5342:1: ( rulePropertyKey )
            {
            // InternalLayoutConfig.g:5342:1: ( rulePropertyKey )
            // InternalLayoutConfig.g:5343:1: rulePropertyKey
            {
             before(grammarAccess.getPropertyAccess().getKeyPropertyKeyParserRuleCall_0_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            rulePropertyKey();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getKeyPropertyKeyParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__KeyAssignment_0"


    // $ANTLR start "rule__Property__ValueAssignment_2_0"
    // InternalLayoutConfig.g:5352:1: rule__Property__ValueAssignment_2_0 : ( ruleStringValue ) ;
    public final void rule__Property__ValueAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5356:1: ( ( ruleStringValue ) )
            // InternalLayoutConfig.g:5357:1: ( ruleStringValue )
            {
            // InternalLayoutConfig.g:5357:1: ( ruleStringValue )
            // InternalLayoutConfig.g:5358:1: ruleStringValue
            {
             before(grammarAccess.getPropertyAccess().getValueStringValueParserRuleCall_2_0_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleStringValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueStringValueParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__ValueAssignment_2_0"


    // $ANTLR start "rule__Property__ValueAssignment_2_1"
    // InternalLayoutConfig.g:5367:1: rule__Property__ValueAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__Property__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5371:1: ( ( ruleQualifiedIdValue ) )
            // InternalLayoutConfig.g:5372:1: ( ruleQualifiedIdValue )
            {
            // InternalLayoutConfig.g:5372:1: ( ruleQualifiedIdValue )
            // InternalLayoutConfig.g:5373:1: ruleQualifiedIdValue
            {
             before(grammarAccess.getPropertyAccess().getValueQualifiedIdValueParserRuleCall_2_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedIdValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueQualifiedIdValueParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__ValueAssignment_2_1"


    // $ANTLR start "rule__Property__ValueAssignment_2_2"
    // InternalLayoutConfig.g:5382:1: rule__Property__ValueAssignment_2_2 : ( ruleNumberValue ) ;
    public final void rule__Property__ValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5386:1: ( ( ruleNumberValue ) )
            // InternalLayoutConfig.g:5387:1: ( ruleNumberValue )
            {
            // InternalLayoutConfig.g:5387:1: ( ruleNumberValue )
            // InternalLayoutConfig.g:5388:1: ruleNumberValue
            {
             before(grammarAccess.getPropertyAccess().getValueNumberValueParserRuleCall_2_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumberValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueNumberValueParserRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__ValueAssignment_2_2"


    // $ANTLR start "rule__Property__ValueAssignment_2_3"
    // InternalLayoutConfig.g:5397:1: rule__Property__ValueAssignment_2_3 : ( ruleBooleanValue ) ;
    public final void rule__Property__ValueAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5401:1: ( ( ruleBooleanValue ) )
            // InternalLayoutConfig.g:5402:1: ( ruleBooleanValue )
            {
            // InternalLayoutConfig.g:5402:1: ( ruleBooleanValue )
            // InternalLayoutConfig.g:5403:1: ruleBooleanValue
            {
             before(grammarAccess.getPropertyAccess().getValueBooleanValueParserRuleCall_2_3_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleBooleanValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueBooleanValueParserRuleCall_2_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__ValueAssignment_2_3"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000100000002L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000080L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000082L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000030080L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020002L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010080L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000090L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000150080L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000040002L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000002800000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x000000017C000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000007C000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000002L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000200200000L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000001000002L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000400000002L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x00000000000060F0L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000002800002L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x000000007C000002L});
    }


}