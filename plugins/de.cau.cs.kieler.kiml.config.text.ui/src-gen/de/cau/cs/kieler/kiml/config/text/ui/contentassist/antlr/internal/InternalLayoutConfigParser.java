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

            if ( (LA2_0==EOF||LA2_0==RULE_ID||LA2_0==22||(LA2_0>=26 && LA2_0<=30)) ) {
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
    // InternalLayoutConfig.g:2058:1: rule__ElkSingleEdgeSection__Group__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2062:1: ( ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) )
            // InternalLayoutConfig.g:2063:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            {
            // InternalLayoutConfig.g:2063:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            // InternalLayoutConfig.g:2064:1: ( rule__ElkSingleEdgeSection__Group_1__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1()); 
            // InternalLayoutConfig.g:2065:1: ( rule__ElkSingleEdgeSection__Group_1__0 )
            // InternalLayoutConfig.g:2065:2: rule__ElkSingleEdgeSection__Group_1__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__0();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1()); 

            }


            }

        }
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


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__0"
    // InternalLayoutConfig.g:2079:1: rule__ElkSingleEdgeSection__Group_1__0 : rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2083:1: ( rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 )
            // InternalLayoutConfig.g:2084:2: rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_21);
            rule__ElkSingleEdgeSection__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__0__Impl"
    // InternalLayoutConfig.g:2091:1: rule__ElkSingleEdgeSection__Group_1__0__Impl : ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2095:1: ( ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) )
            // InternalLayoutConfig.g:2096:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            {
            // InternalLayoutConfig.g:2096:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            // InternalLayoutConfig.g:2097:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0()); 
            // InternalLayoutConfig.g:2098:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            // InternalLayoutConfig.g:2098:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__1"
    // InternalLayoutConfig.g:2108:1: rule__ElkSingleEdgeSection__Group_1__1 : rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2112:1: ( rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 )
            // InternalLayoutConfig.g:2113:2: rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_21);
            rule__ElkSingleEdgeSection__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__1__Impl"
    // InternalLayoutConfig.g:2120:1: rule__ElkSingleEdgeSection__Group_1__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2124:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) )
            // InternalLayoutConfig.g:2125:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            {
            // InternalLayoutConfig.g:2125:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            // InternalLayoutConfig.g:2126:1: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 
            // InternalLayoutConfig.g:2127:1: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==30) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalLayoutConfig.g:2127:2: rule__ElkSingleEdgeSection__Group_1_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__2"
    // InternalLayoutConfig.g:2137:1: rule__ElkSingleEdgeSection__Group_1__2 : rule__ElkSingleEdgeSection__Group_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2141:1: ( rule__ElkSingleEdgeSection__Group_1__2__Impl )
            // InternalLayoutConfig.g:2142:2: rule__ElkSingleEdgeSection__Group_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__2__Impl"
    // InternalLayoutConfig.g:2148:1: rule__ElkSingleEdgeSection__Group_1__2__Impl : ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2152:1: ( ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) )
            // InternalLayoutConfig.g:2153:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            {
            // InternalLayoutConfig.g:2153:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            // InternalLayoutConfig.g:2154:1: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesAssignment_1_2()); 
            // InternalLayoutConfig.g:2155:1: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_ID) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalLayoutConfig.g:2155:2: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__ElkSingleEdgeSection__PropertiesAssignment_1_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__0"
    // InternalLayoutConfig.g:2171:1: rule__ElkSingleEdgeSection__Group_1_0_0__0 : rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2175:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 )
            // InternalLayoutConfig.g:2176:2: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl"
    // InternalLayoutConfig.g:2183:1: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2187:1: ( ( 'incoming' ) )
            // InternalLayoutConfig.g:2188:1: ( 'incoming' )
            {
            // InternalLayoutConfig.g:2188:1: ( 'incoming' )
            // InternalLayoutConfig.g:2189:1: 'incoming'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0_0()); 
            match(input,26,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__1"
    // InternalLayoutConfig.g:2202:1: rule__ElkSingleEdgeSection__Group_1_0_0__1 : rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2206:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 )
            // InternalLayoutConfig.g:2207:2: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl"
    // InternalLayoutConfig.g:2214:1: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2218:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2219:1: ( ':' )
            {
            // InternalLayoutConfig.g:2219:1: ( ':' )
            // InternalLayoutConfig.g:2220:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_0_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__2"
    // InternalLayoutConfig.g:2233:1: rule__ElkSingleEdgeSection__Group_1_0_0__2 : rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2237:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl )
            // InternalLayoutConfig.g:2238:2: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl"
    // InternalLayoutConfig.g:2244:1: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl : ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2248:1: ( ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) )
            // InternalLayoutConfig.g:2249:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            {
            // InternalLayoutConfig.g:2249:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            // InternalLayoutConfig.g:2250:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_0_2()); 
            // InternalLayoutConfig.g:2251:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            // InternalLayoutConfig.g:2251:2: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__0"
    // InternalLayoutConfig.g:2267:1: rule__ElkSingleEdgeSection__Group_1_0_1__0 : rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2271:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 )
            // InternalLayoutConfig.g:2272:2: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl"
    // InternalLayoutConfig.g:2279:1: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2283:1: ( ( 'outgoing' ) )
            // InternalLayoutConfig.g:2284:1: ( 'outgoing' )
            {
            // InternalLayoutConfig.g:2284:1: ( 'outgoing' )
            // InternalLayoutConfig.g:2285:1: 'outgoing'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_0_1_0()); 
            match(input,27,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__1"
    // InternalLayoutConfig.g:2298:1: rule__ElkSingleEdgeSection__Group_1_0_1__1 : rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2302:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 )
            // InternalLayoutConfig.g:2303:2: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl"
    // InternalLayoutConfig.g:2310:1: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2314:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2315:1: ( ':' )
            {
            // InternalLayoutConfig.g:2315:1: ( ':' )
            // InternalLayoutConfig.g:2316:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__2"
    // InternalLayoutConfig.g:2329:1: rule__ElkSingleEdgeSection__Group_1_0_1__2 : rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2333:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl )
            // InternalLayoutConfig.g:2334:2: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl"
    // InternalLayoutConfig.g:2340:1: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl : ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2344:1: ( ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) )
            // InternalLayoutConfig.g:2345:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            {
            // InternalLayoutConfig.g:2345:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            // InternalLayoutConfig.g:2346:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_0_1_2()); 
            // InternalLayoutConfig.g:2347:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            // InternalLayoutConfig.g:2347:2: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_0_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__0"
    // InternalLayoutConfig.g:2363:1: rule__ElkSingleEdgeSection__Group_1_0_2__0 : rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2367:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 )
            // InternalLayoutConfig.g:2368:2: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl"
    // InternalLayoutConfig.g:2375:1: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2379:1: ( ( 'start' ) )
            // InternalLayoutConfig.g:2380:1: ( 'start' )
            {
            // InternalLayoutConfig.g:2380:1: ( 'start' )
            // InternalLayoutConfig.g:2381:1: 'start'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_0_2_0()); 
            match(input,28,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__1"
    // InternalLayoutConfig.g:2394:1: rule__ElkSingleEdgeSection__Group_1_0_2__1 : rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2398:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 )
            // InternalLayoutConfig.g:2399:2: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl"
    // InternalLayoutConfig.g:2406:1: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2410:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2411:1: ( ':' )
            {
            // InternalLayoutConfig.g:2411:1: ( ':' )
            // InternalLayoutConfig.g:2412:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_2_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__2"
    // InternalLayoutConfig.g:2425:1: rule__ElkSingleEdgeSection__Group_1_0_2__2 : rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2429:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 )
            // InternalLayoutConfig.g:2430:2: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl"
    // InternalLayoutConfig.g:2437:1: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl : ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2441:1: ( ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) )
            // InternalLayoutConfig.g:2442:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            {
            // InternalLayoutConfig.g:2442:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            // InternalLayoutConfig.g:2443:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_0_2_2()); 
            // InternalLayoutConfig.g:2444:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            // InternalLayoutConfig.g:2444:2: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_0_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__3"
    // InternalLayoutConfig.g:2454:1: rule__ElkSingleEdgeSection__Group_1_0_2__3 : rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2458:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 )
            // InternalLayoutConfig.g:2459:2: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl"
    // InternalLayoutConfig.g:2466:1: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2470:1: ( ( ',' ) )
            // InternalLayoutConfig.g:2471:1: ( ',' )
            {
            // InternalLayoutConfig.g:2471:1: ( ',' )
            // InternalLayoutConfig.g:2472:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_2_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__4"
    // InternalLayoutConfig.g:2485:1: rule__ElkSingleEdgeSection__Group_1_0_2__4 : rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2489:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl )
            // InternalLayoutConfig.g:2490:2: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl"
    // InternalLayoutConfig.g:2496:1: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl : ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2500:1: ( ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) )
            // InternalLayoutConfig.g:2501:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            {
            // InternalLayoutConfig.g:2501:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            // InternalLayoutConfig.g:2502:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_0_2_4()); 
            // InternalLayoutConfig.g:2503:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            // InternalLayoutConfig.g:2503:2: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_0_2_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__0"
    // InternalLayoutConfig.g:2523:1: rule__ElkSingleEdgeSection__Group_1_0_3__0 : rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2527:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 )
            // InternalLayoutConfig.g:2528:2: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl"
    // InternalLayoutConfig.g:2535:1: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2539:1: ( ( 'end' ) )
            // InternalLayoutConfig.g:2540:1: ( 'end' )
            {
            // InternalLayoutConfig.g:2540:1: ( 'end' )
            // InternalLayoutConfig.g:2541:1: 'end'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_0_3_0()); 
            match(input,29,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__1"
    // InternalLayoutConfig.g:2554:1: rule__ElkSingleEdgeSection__Group_1_0_3__1 : rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2558:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 )
            // InternalLayoutConfig.g:2559:2: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl"
    // InternalLayoutConfig.g:2566:1: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2570:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2571:1: ( ':' )
            {
            // InternalLayoutConfig.g:2571:1: ( ':' )
            // InternalLayoutConfig.g:2572:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_3_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__2"
    // InternalLayoutConfig.g:2585:1: rule__ElkSingleEdgeSection__Group_1_0_3__2 : rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2589:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 )
            // InternalLayoutConfig.g:2590:2: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl"
    // InternalLayoutConfig.g:2597:1: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl : ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2601:1: ( ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) )
            // InternalLayoutConfig.g:2602:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            {
            // InternalLayoutConfig.g:2602:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            // InternalLayoutConfig.g:2603:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_0_3_2()); 
            // InternalLayoutConfig.g:2604:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            // InternalLayoutConfig.g:2604:2: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_0_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__3"
    // InternalLayoutConfig.g:2614:1: rule__ElkSingleEdgeSection__Group_1_0_3__3 : rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2618:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 )
            // InternalLayoutConfig.g:2619:2: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl"
    // InternalLayoutConfig.g:2626:1: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2630:1: ( ( ',' ) )
            // InternalLayoutConfig.g:2631:1: ( ',' )
            {
            // InternalLayoutConfig.g:2631:1: ( ',' )
            // InternalLayoutConfig.g:2632:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_3_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__4"
    // InternalLayoutConfig.g:2645:1: rule__ElkSingleEdgeSection__Group_1_0_3__4 : rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2649:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl )
            // InternalLayoutConfig.g:2650:2: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl"
    // InternalLayoutConfig.g:2656:1: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl : ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2660:1: ( ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) )
            // InternalLayoutConfig.g:2661:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            {
            // InternalLayoutConfig.g:2661:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            // InternalLayoutConfig.g:2662:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_0_3_4()); 
            // InternalLayoutConfig.g:2663:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            // InternalLayoutConfig.g:2663:2: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_0_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__0"
    // InternalLayoutConfig.g:2683:1: rule__ElkSingleEdgeSection__Group_1_1__0 : rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2687:1: ( rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 )
            // InternalLayoutConfig.g:2688:2: rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1
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
    // InternalLayoutConfig.g:2695:1: rule__ElkSingleEdgeSection__Group_1_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2699:1: ( ( 'bends' ) )
            // InternalLayoutConfig.g:2700:1: ( 'bends' )
            {
            // InternalLayoutConfig.g:2700:1: ( 'bends' )
            // InternalLayoutConfig.g:2701:1: 'bends'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_1_0()); 
            match(input,30,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_1_0()); 

            }


            }

        }
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
    // InternalLayoutConfig.g:2714:1: rule__ElkSingleEdgeSection__Group_1_1__1 : rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2718:1: ( rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 )
            // InternalLayoutConfig.g:2719:2: rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
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
    // InternalLayoutConfig.g:2726:1: rule__ElkSingleEdgeSection__Group_1_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2730:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2731:1: ( ':' )
            {
            // InternalLayoutConfig.g:2731:1: ( ':' )
            // InternalLayoutConfig.g:2732:1: ':'
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
    // InternalLayoutConfig.g:2745:1: rule__ElkSingleEdgeSection__Group_1_1__2 : rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2749:1: ( rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 )
            // InternalLayoutConfig.g:2750:2: rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3
            {
            pushFollow(FollowSets000.FOLLOW_22);
            rule__ElkSingleEdgeSection__Group_1_1__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__3();

            state._fsp--;


            }

        }
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
    // InternalLayoutConfig.g:2757:1: rule__ElkSingleEdgeSection__Group_1_1__2__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2761:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) )
            // InternalLayoutConfig.g:2762:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            {
            // InternalLayoutConfig.g:2762:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            // InternalLayoutConfig.g:2763:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_2()); 
            // InternalLayoutConfig.g:2764:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            // InternalLayoutConfig.g:2764:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_2()); 

            }


            }

        }
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


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__3"
    // InternalLayoutConfig.g:2774:1: rule__ElkSingleEdgeSection__Group_1_1__3 : rule__ElkSingleEdgeSection__Group_1_1__3__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2778:1: ( rule__ElkSingleEdgeSection__Group_1_1__3__Impl )
            // InternalLayoutConfig.g:2779:2: rule__ElkSingleEdgeSection__Group_1_1__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__3__Impl"
    // InternalLayoutConfig.g:2785:1: rule__ElkSingleEdgeSection__Group_1_1__3__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2789:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) )
            // InternalLayoutConfig.g:2790:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            {
            // InternalLayoutConfig.g:2790:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            // InternalLayoutConfig.g:2791:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1_3()); 
            // InternalLayoutConfig.g:2792:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==31) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalLayoutConfig.g:2792:2: rule__ElkSingleEdgeSection__Group_1_1_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_23);
            	    rule__ElkSingleEdgeSection__Group_1_1_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__0"
    // InternalLayoutConfig.g:2810:1: rule__ElkSingleEdgeSection__Group_1_1_3__0 : rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2814:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 )
            // InternalLayoutConfig.g:2815:2: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl"
    // InternalLayoutConfig.g:2822:1: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2826:1: ( ( '|' ) )
            // InternalLayoutConfig.g:2827:1: ( '|' )
            {
            // InternalLayoutConfig.g:2827:1: ( '|' )
            // InternalLayoutConfig.g:2828:1: '|'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_1_3_0()); 
            match(input,31,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__1"
    // InternalLayoutConfig.g:2841:1: rule__ElkSingleEdgeSection__Group_1_1_3__1 : rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2845:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl )
            // InternalLayoutConfig.g:2846:2: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl"
    // InternalLayoutConfig.g:2852:1: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2856:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) )
            // InternalLayoutConfig.g:2857:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            {
            // InternalLayoutConfig.g:2857:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            // InternalLayoutConfig.g:2858:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_3_1()); 
            // InternalLayoutConfig.g:2859:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            // InternalLayoutConfig.g:2859:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__0"
    // InternalLayoutConfig.g:2873:1: rule__ElkEdgeSection__Group__0 : rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 ;
    public final void rule__ElkEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2877:1: ( rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 )
            // InternalLayoutConfig.g:2878:2: rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1
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
    // InternalLayoutConfig.g:2885:1: rule__ElkEdgeSection__Group__0__Impl : ( 'section' ) ;
    public final void rule__ElkEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2889:1: ( ( 'section' ) )
            // InternalLayoutConfig.g:2890:1: ( 'section' )
            {
            // InternalLayoutConfig.g:2890:1: ( 'section' )
            // InternalLayoutConfig.g:2891:1: 'section'
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
    // InternalLayoutConfig.g:2904:1: rule__ElkEdgeSection__Group__1 : rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 ;
    public final void rule__ElkEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2908:1: ( rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 )
            // InternalLayoutConfig.g:2909:2: rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_24);
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
    // InternalLayoutConfig.g:2916:1: rule__ElkEdgeSection__Group__1__Impl : ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2920:1: ( ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) )
            // InternalLayoutConfig.g:2921:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            {
            // InternalLayoutConfig.g:2921:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            // InternalLayoutConfig.g:2922:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 
            // InternalLayoutConfig.g:2923:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            // InternalLayoutConfig.g:2923:2: rule__ElkEdgeSection__IdentifierAssignment_1
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
    // InternalLayoutConfig.g:2933:1: rule__ElkEdgeSection__Group__2 : rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 ;
    public final void rule__ElkEdgeSection__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2937:1: ( rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 )
            // InternalLayoutConfig.g:2938:2: rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_24);
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
    // InternalLayoutConfig.g:2945:1: rule__ElkEdgeSection__Group__2__Impl : ( ( rule__ElkEdgeSection__Group_2__0 )? ) ;
    public final void rule__ElkEdgeSection__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2949:1: ( ( ( rule__ElkEdgeSection__Group_2__0 )? ) )
            // InternalLayoutConfig.g:2950:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            {
            // InternalLayoutConfig.g:2950:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            // InternalLayoutConfig.g:2951:1: ( rule__ElkEdgeSection__Group_2__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 
            // InternalLayoutConfig.g:2952:1: ( rule__ElkEdgeSection__Group_2__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==33) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalLayoutConfig.g:2952:2: rule__ElkEdgeSection__Group_2__0
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
    // InternalLayoutConfig.g:2962:1: rule__ElkEdgeSection__Group__3 : rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 ;
    public final void rule__ElkEdgeSection__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2966:1: ( rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 )
            // InternalLayoutConfig.g:2967:2: rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4
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
    // InternalLayoutConfig.g:2974:1: rule__ElkEdgeSection__Group__3__Impl : ( '[' ) ;
    public final void rule__ElkEdgeSection__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2978:1: ( ( '[' ) )
            // InternalLayoutConfig.g:2979:1: ( '[' )
            {
            // InternalLayoutConfig.g:2979:1: ( '[' )
            // InternalLayoutConfig.g:2980:1: '['
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
    // InternalLayoutConfig.g:2993:1: rule__ElkEdgeSection__Group__4 : rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 ;
    public final void rule__ElkEdgeSection__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2997:1: ( rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 )
            // InternalLayoutConfig.g:2998:2: rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5
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
    // InternalLayoutConfig.g:3005:1: rule__ElkEdgeSection__Group__4__Impl : ( ( rule__ElkEdgeSection__Group_4__0 ) ) ;
    public final void rule__ElkEdgeSection__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3009:1: ( ( ( rule__ElkEdgeSection__Group_4__0 ) ) )
            // InternalLayoutConfig.g:3010:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            {
            // InternalLayoutConfig.g:3010:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            // InternalLayoutConfig.g:3011:1: ( rule__ElkEdgeSection__Group_4__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4()); 
            // InternalLayoutConfig.g:3012:1: ( rule__ElkEdgeSection__Group_4__0 )
            // InternalLayoutConfig.g:3012:2: rule__ElkEdgeSection__Group_4__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4__0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4()); 

            }


            }

        }
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
    // InternalLayoutConfig.g:3022:1: rule__ElkEdgeSection__Group__5 : rule__ElkEdgeSection__Group__5__Impl ;
    public final void rule__ElkEdgeSection__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3026:1: ( rule__ElkEdgeSection__Group__5__Impl )
            // InternalLayoutConfig.g:3027:2: rule__ElkEdgeSection__Group__5__Impl
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
    // InternalLayoutConfig.g:3033:1: rule__ElkEdgeSection__Group__5__Impl : ( ']' ) ;
    public final void rule__ElkEdgeSection__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3037:1: ( ( ']' ) )
            // InternalLayoutConfig.g:3038:1: ( ']' )
            {
            // InternalLayoutConfig.g:3038:1: ( ']' )
            // InternalLayoutConfig.g:3039:1: ']'
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
    // InternalLayoutConfig.g:3064:1: rule__ElkEdgeSection__Group_2__0 : rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 ;
    public final void rule__ElkEdgeSection__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3068:1: ( rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 )
            // InternalLayoutConfig.g:3069:2: rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1
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
    // InternalLayoutConfig.g:3076:1: rule__ElkEdgeSection__Group_2__0__Impl : ( '->' ) ;
    public final void rule__ElkEdgeSection__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3080:1: ( ( '->' ) )
            // InternalLayoutConfig.g:3081:1: ( '->' )
            {
            // InternalLayoutConfig.g:3081:1: ( '->' )
            // InternalLayoutConfig.g:3082:1: '->'
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
    // InternalLayoutConfig.g:3095:1: rule__ElkEdgeSection__Group_2__1 : rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 ;
    public final void rule__ElkEdgeSection__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3099:1: ( rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 )
            // InternalLayoutConfig.g:3100:2: rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2
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
    // InternalLayoutConfig.g:3107:1: rule__ElkEdgeSection__Group_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3111:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) )
            // InternalLayoutConfig.g:3112:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            {
            // InternalLayoutConfig.g:3112:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            // InternalLayoutConfig.g:3113:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 
            // InternalLayoutConfig.g:3114:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            // InternalLayoutConfig.g:3114:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1
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
    // InternalLayoutConfig.g:3124:1: rule__ElkEdgeSection__Group_2__2 : rule__ElkEdgeSection__Group_2__2__Impl ;
    public final void rule__ElkEdgeSection__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3128:1: ( rule__ElkEdgeSection__Group_2__2__Impl )
            // InternalLayoutConfig.g:3129:2: rule__ElkEdgeSection__Group_2__2__Impl
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
    // InternalLayoutConfig.g:3135:1: rule__ElkEdgeSection__Group_2__2__Impl : ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3139:1: ( ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) )
            // InternalLayoutConfig.g:3140:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            {
            // InternalLayoutConfig.g:3140:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            // InternalLayoutConfig.g:3141:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 
            // InternalLayoutConfig.g:3142:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==24) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalLayoutConfig.g:3142:2: rule__ElkEdgeSection__Group_2_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_25);
            	    rule__ElkEdgeSection__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // InternalLayoutConfig.g:3158:1: rule__ElkEdgeSection__Group_2_2__0 : rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 ;
    public final void rule__ElkEdgeSection__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3162:1: ( rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 )
            // InternalLayoutConfig.g:3163:2: rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1
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
    // InternalLayoutConfig.g:3170:1: rule__ElkEdgeSection__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3174:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3175:1: ( ',' )
            {
            // InternalLayoutConfig.g:3175:1: ( ',' )
            // InternalLayoutConfig.g:3176:1: ','
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
    // InternalLayoutConfig.g:3189:1: rule__ElkEdgeSection__Group_2_2__1 : rule__ElkEdgeSection__Group_2_2__1__Impl ;
    public final void rule__ElkEdgeSection__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3193:1: ( rule__ElkEdgeSection__Group_2_2__1__Impl )
            // InternalLayoutConfig.g:3194:2: rule__ElkEdgeSection__Group_2_2__1__Impl
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
    // InternalLayoutConfig.g:3200:1: rule__ElkEdgeSection__Group_2_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3204:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) )
            // InternalLayoutConfig.g:3205:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            {
            // InternalLayoutConfig.g:3205:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            // InternalLayoutConfig.g:3206:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 
            // InternalLayoutConfig.g:3207:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            // InternalLayoutConfig.g:3207:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1
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


    // $ANTLR start "rule__ElkEdgeSection__Group_4__0"
    // InternalLayoutConfig.g:3221:1: rule__ElkEdgeSection__Group_4__0 : rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 ;
    public final void rule__ElkEdgeSection__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3225:1: ( rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 )
            // InternalLayoutConfig.g:3226:2: rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_21);
            rule__ElkEdgeSection__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__0__Impl"
    // InternalLayoutConfig.g:3233:1: rule__ElkEdgeSection__Group_4__0__Impl : ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) ;
    public final void rule__ElkEdgeSection__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3237:1: ( ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) )
            // InternalLayoutConfig.g:3238:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            {
            // InternalLayoutConfig.g:3238:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            // InternalLayoutConfig.g:3239:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0()); 
            // InternalLayoutConfig.g:3240:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            // InternalLayoutConfig.g:3240:2: rule__ElkEdgeSection__UnorderedGroup_4_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4_0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__1"
    // InternalLayoutConfig.g:3250:1: rule__ElkEdgeSection__Group_4__1 : rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 ;
    public final void rule__ElkEdgeSection__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3254:1: ( rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 )
            // InternalLayoutConfig.g:3255:2: rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2
            {
            pushFollow(FollowSets000.FOLLOW_21);
            rule__ElkEdgeSection__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__1__Impl"
    // InternalLayoutConfig.g:3262:1: rule__ElkEdgeSection__Group_4__1__Impl : ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) ;
    public final void rule__ElkEdgeSection__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3266:1: ( ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) )
            // InternalLayoutConfig.g:3267:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            {
            // InternalLayoutConfig.g:3267:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            // InternalLayoutConfig.g:3268:1: ( rule__ElkEdgeSection__Group_4_1__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 
            // InternalLayoutConfig.g:3269:1: ( rule__ElkEdgeSection__Group_4_1__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==30) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalLayoutConfig.g:3269:2: rule__ElkEdgeSection__Group_4_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__2"
    // InternalLayoutConfig.g:3279:1: rule__ElkEdgeSection__Group_4__2 : rule__ElkEdgeSection__Group_4__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3283:1: ( rule__ElkEdgeSection__Group_4__2__Impl )
            // InternalLayoutConfig.g:3284:2: rule__ElkEdgeSection__Group_4__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__2__Impl"
    // InternalLayoutConfig.g:3290:1: rule__ElkEdgeSection__Group_4__2__Impl : ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) ;
    public final void rule__ElkEdgeSection__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3294:1: ( ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) )
            // InternalLayoutConfig.g:3295:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            {
            // InternalLayoutConfig.g:3295:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            // InternalLayoutConfig.g:3296:1: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getPropertiesAssignment_4_2()); 
            // InternalLayoutConfig.g:3297:1: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalLayoutConfig.g:3297:2: rule__ElkEdgeSection__PropertiesAssignment_4_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__ElkEdgeSection__PropertiesAssignment_4_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getPropertiesAssignment_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__0"
    // InternalLayoutConfig.g:3313:1: rule__ElkEdgeSection__Group_4_0_0__0 : rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3317:1: ( rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 )
            // InternalLayoutConfig.g:3318:2: rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_0_0__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__0__Impl"
    // InternalLayoutConfig.g:3325:1: rule__ElkEdgeSection__Group_4_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3329:1: ( ( 'incoming' ) )
            // InternalLayoutConfig.g:3330:1: ( 'incoming' )
            {
            // InternalLayoutConfig.g:3330:1: ( 'incoming' )
            // InternalLayoutConfig.g:3331:1: 'incoming'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0_0()); 
            match(input,26,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__1"
    // InternalLayoutConfig.g:3344:1: rule__ElkEdgeSection__Group_4_0_0__1 : rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3348:1: ( rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 )
            // InternalLayoutConfig.g:3349:2: rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkEdgeSection__Group_4_0_0__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__1__Impl"
    // InternalLayoutConfig.g:3356:1: rule__ElkEdgeSection__Group_4_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3360:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3361:1: ( ':' )
            {
            // InternalLayoutConfig.g:3361:1: ( ':' )
            // InternalLayoutConfig.g:3362:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_0_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__2"
    // InternalLayoutConfig.g:3375:1: rule__ElkEdgeSection__Group_4_0_0__2 : rule__ElkEdgeSection__Group_4_0_0__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3379:1: ( rule__ElkEdgeSection__Group_4_0_0__2__Impl )
            // InternalLayoutConfig.g:3380:2: rule__ElkEdgeSection__Group_4_0_0__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_0__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__2__Impl"
    // InternalLayoutConfig.g:3386:1: rule__ElkEdgeSection__Group_4_0_0__2__Impl : ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3390:1: ( ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) )
            // InternalLayoutConfig.g:3391:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            {
            // InternalLayoutConfig.g:3391:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            // InternalLayoutConfig.g:3392:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_0_2()); 
            // InternalLayoutConfig.g:3393:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            // InternalLayoutConfig.g:3393:2: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__0"
    // InternalLayoutConfig.g:3409:1: rule__ElkEdgeSection__Group_4_0_1__0 : rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3413:1: ( rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 )
            // InternalLayoutConfig.g:3414:2: rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_0_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__0__Impl"
    // InternalLayoutConfig.g:3421:1: rule__ElkEdgeSection__Group_4_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3425:1: ( ( 'outgoing' ) )
            // InternalLayoutConfig.g:3426:1: ( 'outgoing' )
            {
            // InternalLayoutConfig.g:3426:1: ( 'outgoing' )
            // InternalLayoutConfig.g:3427:1: 'outgoing'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_0_1_0()); 
            match(input,27,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__1"
    // InternalLayoutConfig.g:3440:1: rule__ElkEdgeSection__Group_4_0_1__1 : rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3444:1: ( rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 )
            // InternalLayoutConfig.g:3445:2: rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__ElkEdgeSection__Group_4_0_1__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__1__Impl"
    // InternalLayoutConfig.g:3452:1: rule__ElkEdgeSection__Group_4_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3456:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3457:1: ( ':' )
            {
            // InternalLayoutConfig.g:3457:1: ( ':' )
            // InternalLayoutConfig.g:3458:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__2"
    // InternalLayoutConfig.g:3471:1: rule__ElkEdgeSection__Group_4_0_1__2 : rule__ElkEdgeSection__Group_4_0_1__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3475:1: ( rule__ElkEdgeSection__Group_4_0_1__2__Impl )
            // InternalLayoutConfig.g:3476:2: rule__ElkEdgeSection__Group_4_0_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__2__Impl"
    // InternalLayoutConfig.g:3482:1: rule__ElkEdgeSection__Group_4_0_1__2__Impl : ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3486:1: ( ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) )
            // InternalLayoutConfig.g:3487:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            {
            // InternalLayoutConfig.g:3487:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            // InternalLayoutConfig.g:3488:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_0_1_2()); 
            // InternalLayoutConfig.g:3489:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            // InternalLayoutConfig.g:3489:2: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_0_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__0"
    // InternalLayoutConfig.g:3505:1: rule__ElkEdgeSection__Group_4_0_2__0 : rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3509:1: ( rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 )
            // InternalLayoutConfig.g:3510:2: rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_0_2__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__0__Impl"
    // InternalLayoutConfig.g:3517:1: rule__ElkEdgeSection__Group_4_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3521:1: ( ( 'start' ) )
            // InternalLayoutConfig.g:3522:1: ( 'start' )
            {
            // InternalLayoutConfig.g:3522:1: ( 'start' )
            // InternalLayoutConfig.g:3523:1: 'start'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_0_2_0()); 
            match(input,28,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__1"
    // InternalLayoutConfig.g:3536:1: rule__ElkEdgeSection__Group_4_0_2__1 : rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3540:1: ( rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 )
            // InternalLayoutConfig.g:3541:2: rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_0_2__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__1__Impl"
    // InternalLayoutConfig.g:3548:1: rule__ElkEdgeSection__Group_4_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3552:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3553:1: ( ':' )
            {
            // InternalLayoutConfig.g:3553:1: ( ':' )
            // InternalLayoutConfig.g:3554:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_2_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__2"
    // InternalLayoutConfig.g:3567:1: rule__ElkEdgeSection__Group_4_0_2__2 : rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3571:1: ( rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 )
            // InternalLayoutConfig.g:3572:2: rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkEdgeSection__Group_4_0_2__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__2__Impl"
    // InternalLayoutConfig.g:3579:1: rule__ElkEdgeSection__Group_4_0_2__2__Impl : ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3583:1: ( ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) )
            // InternalLayoutConfig.g:3584:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            {
            // InternalLayoutConfig.g:3584:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            // InternalLayoutConfig.g:3585:1: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_0_2_2()); 
            // InternalLayoutConfig.g:3586:1: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            // InternalLayoutConfig.g:3586:2: rule__ElkEdgeSection__StartXAssignment_4_0_2_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__StartXAssignment_4_0_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_0_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__3"
    // InternalLayoutConfig.g:3596:1: rule__ElkEdgeSection__Group_4_0_2__3 : rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3600:1: ( rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 )
            // InternalLayoutConfig.g:3601:2: rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_0_2__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__3__Impl"
    // InternalLayoutConfig.g:3608:1: rule__ElkEdgeSection__Group_4_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3612:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3613:1: ( ',' )
            {
            // InternalLayoutConfig.g:3613:1: ( ',' )
            // InternalLayoutConfig.g:3614:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_2_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__4"
    // InternalLayoutConfig.g:3627:1: rule__ElkEdgeSection__Group_4_0_2__4 : rule__ElkEdgeSection__Group_4_0_2__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3631:1: ( rule__ElkEdgeSection__Group_4_0_2__4__Impl )
            // InternalLayoutConfig.g:3632:2: rule__ElkEdgeSection__Group_4_0_2__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__4__Impl"
    // InternalLayoutConfig.g:3638:1: rule__ElkEdgeSection__Group_4_0_2__4__Impl : ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3642:1: ( ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) )
            // InternalLayoutConfig.g:3643:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            {
            // InternalLayoutConfig.g:3643:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            // InternalLayoutConfig.g:3644:1: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_0_2_4()); 
            // InternalLayoutConfig.g:3645:1: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            // InternalLayoutConfig.g:3645:2: rule__ElkEdgeSection__StartYAssignment_4_0_2_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__StartYAssignment_4_0_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_0_2_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__0"
    // InternalLayoutConfig.g:3665:1: rule__ElkEdgeSection__Group_4_0_3__0 : rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3669:1: ( rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 )
            // InternalLayoutConfig.g:3670:2: rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__ElkEdgeSection__Group_4_0_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__0__Impl"
    // InternalLayoutConfig.g:3677:1: rule__ElkEdgeSection__Group_4_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3681:1: ( ( 'end' ) )
            // InternalLayoutConfig.g:3682:1: ( 'end' )
            {
            // InternalLayoutConfig.g:3682:1: ( 'end' )
            // InternalLayoutConfig.g:3683:1: 'end'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_0_3_0()); 
            match(input,29,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_0_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__1"
    // InternalLayoutConfig.g:3696:1: rule__ElkEdgeSection__Group_4_0_3__1 : rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3700:1: ( rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 )
            // InternalLayoutConfig.g:3701:2: rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_0_3__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__1__Impl"
    // InternalLayoutConfig.g:3708:1: rule__ElkEdgeSection__Group_4_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3712:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3713:1: ( ':' )
            {
            // InternalLayoutConfig.g:3713:1: ( ':' )
            // InternalLayoutConfig.g:3714:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_3_1()); 
            match(input,19,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__2"
    // InternalLayoutConfig.g:3727:1: rule__ElkEdgeSection__Group_4_0_3__2 : rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3731:1: ( rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 )
            // InternalLayoutConfig.g:3732:2: rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__ElkEdgeSection__Group_4_0_3__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__2__Impl"
    // InternalLayoutConfig.g:3739:1: rule__ElkEdgeSection__Group_4_0_3__2__Impl : ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3743:1: ( ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) )
            // InternalLayoutConfig.g:3744:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            {
            // InternalLayoutConfig.g:3744:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            // InternalLayoutConfig.g:3745:1: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_0_3_2()); 
            // InternalLayoutConfig.g:3746:1: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            // InternalLayoutConfig.g:3746:2: rule__ElkEdgeSection__EndXAssignment_4_0_3_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__EndXAssignment_4_0_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_0_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__3"
    // InternalLayoutConfig.g:3756:1: rule__ElkEdgeSection__Group_4_0_3__3 : rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3760:1: ( rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 )
            // InternalLayoutConfig.g:3761:2: rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_0_3__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__3__Impl"
    // InternalLayoutConfig.g:3768:1: rule__ElkEdgeSection__Group_4_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3772:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3773:1: ( ',' )
            {
            // InternalLayoutConfig.g:3773:1: ( ',' )
            // InternalLayoutConfig.g:3774:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_3_3()); 
            match(input,24,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_3_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__4"
    // InternalLayoutConfig.g:3787:1: rule__ElkEdgeSection__Group_4_0_3__4 : rule__ElkEdgeSection__Group_4_0_3__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3791:1: ( rule__ElkEdgeSection__Group_4_0_3__4__Impl )
            // InternalLayoutConfig.g:3792:2: rule__ElkEdgeSection__Group_4_0_3__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__4__Impl"
    // InternalLayoutConfig.g:3798:1: rule__ElkEdgeSection__Group_4_0_3__4__Impl : ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3802:1: ( ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) )
            // InternalLayoutConfig.g:3803:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            {
            // InternalLayoutConfig.g:3803:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            // InternalLayoutConfig.g:3804:1: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_0_3_4()); 
            // InternalLayoutConfig.g:3805:1: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            // InternalLayoutConfig.g:3805:2: rule__ElkEdgeSection__EndYAssignment_4_0_3_4
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__EndYAssignment_4_0_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_0_3_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__0"
    // InternalLayoutConfig.g:3825:1: rule__ElkEdgeSection__Group_4_1__0 : rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3829:1: ( rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 )
            // InternalLayoutConfig.g:3830:2: rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1
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
    // InternalLayoutConfig.g:3837:1: rule__ElkEdgeSection__Group_4_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3841:1: ( ( 'bends' ) )
            // InternalLayoutConfig.g:3842:1: ( 'bends' )
            {
            // InternalLayoutConfig.g:3842:1: ( 'bends' )
            // InternalLayoutConfig.g:3843:1: 'bends'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_1_0()); 
            match(input,30,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_1_0()); 

            }


            }

        }
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
    // InternalLayoutConfig.g:3856:1: rule__ElkEdgeSection__Group_4_1__1 : rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3860:1: ( rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 )
            // InternalLayoutConfig.g:3861:2: rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
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
    // InternalLayoutConfig.g:3868:1: rule__ElkEdgeSection__Group_4_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3872:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3873:1: ( ':' )
            {
            // InternalLayoutConfig.g:3873:1: ( ':' )
            // InternalLayoutConfig.g:3874:1: ':'
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
    // InternalLayoutConfig.g:3887:1: rule__ElkEdgeSection__Group_4_1__2 : rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 ;
    public final void rule__ElkEdgeSection__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3891:1: ( rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 )
            // InternalLayoutConfig.g:3892:2: rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3
            {
            pushFollow(FollowSets000.FOLLOW_22);
            rule__ElkEdgeSection__Group_4_1__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__3();

            state._fsp--;


            }

        }
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
    // InternalLayoutConfig.g:3899:1: rule__ElkEdgeSection__Group_4_1__2__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3903:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) )
            // InternalLayoutConfig.g:3904:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            {
            // InternalLayoutConfig.g:3904:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            // InternalLayoutConfig.g:3905:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_2()); 
            // InternalLayoutConfig.g:3906:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            // InternalLayoutConfig.g:3906:2: rule__ElkEdgeSection__BendPointsAssignment_4_1_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_2()); 

            }


            }

        }
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


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__3"
    // InternalLayoutConfig.g:3916:1: rule__ElkEdgeSection__Group_4_1__3 : rule__ElkEdgeSection__Group_4_1__3__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3920:1: ( rule__ElkEdgeSection__Group_4_1__3__Impl )
            // InternalLayoutConfig.g:3921:2: rule__ElkEdgeSection__Group_4_1__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__3__Impl"
    // InternalLayoutConfig.g:3927:1: rule__ElkEdgeSection__Group_4_1__3__Impl : ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_4_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3931:1: ( ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) )
            // InternalLayoutConfig.g:3932:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            {
            // InternalLayoutConfig.g:3932:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            // InternalLayoutConfig.g:3933:1: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1_3()); 
            // InternalLayoutConfig.g:3934:1: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==31) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalLayoutConfig.g:3934:2: rule__ElkEdgeSection__Group_4_1_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_23);
            	    rule__ElkEdgeSection__Group_4_1_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__0"
    // InternalLayoutConfig.g:3952:1: rule__ElkEdgeSection__Group_4_1_3__0 : rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3956:1: ( rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 )
            // InternalLayoutConfig.g:3957:2: rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__ElkEdgeSection__Group_4_1_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__0__Impl"
    // InternalLayoutConfig.g:3964:1: rule__ElkEdgeSection__Group_4_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3968:1: ( ( '|' ) )
            // InternalLayoutConfig.g:3969:1: ( '|' )
            {
            // InternalLayoutConfig.g:3969:1: ( '|' )
            // InternalLayoutConfig.g:3970:1: '|'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_1_3_0()); 
            match(input,31,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_1_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__1"
    // InternalLayoutConfig.g:3983:1: rule__ElkEdgeSection__Group_4_1_3__1 : rule__ElkEdgeSection__Group_4_1_3__1__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3987:1: ( rule__ElkEdgeSection__Group_4_1_3__1__Impl )
            // InternalLayoutConfig.g:3988:2: rule__ElkEdgeSection__Group_4_1_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__1__Impl"
    // InternalLayoutConfig.g:3994:1: rule__ElkEdgeSection__Group_4_1_3__1__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3998:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) )
            // InternalLayoutConfig.g:3999:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            {
            // InternalLayoutConfig.g:3999:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            // InternalLayoutConfig.g:4000:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_3_1()); 
            // InternalLayoutConfig.g:4001:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            // InternalLayoutConfig.g:4001:2: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__1__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__0"
    // InternalLayoutConfig.g:4015:1: rule__ElkBendPoint__Group__0 : rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 ;
    public final void rule__ElkBendPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4019:1: ( rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 )
            // InternalLayoutConfig.g:4020:2: rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1
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
    // InternalLayoutConfig.g:4027:1: rule__ElkBendPoint__Group__0__Impl : ( ( rule__ElkBendPoint__XAssignment_0 ) ) ;
    public final void rule__ElkBendPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4031:1: ( ( ( rule__ElkBendPoint__XAssignment_0 ) ) )
            // InternalLayoutConfig.g:4032:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            {
            // InternalLayoutConfig.g:4032:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            // InternalLayoutConfig.g:4033:1: ( rule__ElkBendPoint__XAssignment_0 )
            {
             before(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 
            // InternalLayoutConfig.g:4034:1: ( rule__ElkBendPoint__XAssignment_0 )
            // InternalLayoutConfig.g:4034:2: rule__ElkBendPoint__XAssignment_0
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
    // InternalLayoutConfig.g:4044:1: rule__ElkBendPoint__Group__1 : rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 ;
    public final void rule__ElkBendPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4048:1: ( rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 )
            // InternalLayoutConfig.g:4049:2: rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2
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
    // InternalLayoutConfig.g:4056:1: rule__ElkBendPoint__Group__1__Impl : ( ',' ) ;
    public final void rule__ElkBendPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4060:1: ( ( ',' ) )
            // InternalLayoutConfig.g:4061:1: ( ',' )
            {
            // InternalLayoutConfig.g:4061:1: ( ',' )
            // InternalLayoutConfig.g:4062:1: ','
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
    // InternalLayoutConfig.g:4075:1: rule__ElkBendPoint__Group__2 : rule__ElkBendPoint__Group__2__Impl ;
    public final void rule__ElkBendPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4079:1: ( rule__ElkBendPoint__Group__2__Impl )
            // InternalLayoutConfig.g:4080:2: rule__ElkBendPoint__Group__2__Impl
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
    // InternalLayoutConfig.g:4086:1: rule__ElkBendPoint__Group__2__Impl : ( ( rule__ElkBendPoint__YAssignment_2 ) ) ;
    public final void rule__ElkBendPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4090:1: ( ( ( rule__ElkBendPoint__YAssignment_2 ) ) )
            // InternalLayoutConfig.g:4091:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            {
            // InternalLayoutConfig.g:4091:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            // InternalLayoutConfig.g:4092:1: ( rule__ElkBendPoint__YAssignment_2 )
            {
             before(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 
            // InternalLayoutConfig.g:4093:1: ( rule__ElkBendPoint__YAssignment_2 )
            // InternalLayoutConfig.g:4093:2: rule__ElkBendPoint__YAssignment_2
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
    // InternalLayoutConfig.g:4109:1: rule__QualifiedId__Group__0 : rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 ;
    public final void rule__QualifiedId__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4113:1: ( rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 )
            // InternalLayoutConfig.g:4114:2: rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_26);
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
    // InternalLayoutConfig.g:4121:1: rule__QualifiedId__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4125:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4126:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4126:1: ( RULE_ID )
            // InternalLayoutConfig.g:4127:1: RULE_ID
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
    // InternalLayoutConfig.g:4138:1: rule__QualifiedId__Group__1 : rule__QualifiedId__Group__1__Impl ;
    public final void rule__QualifiedId__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4142:1: ( rule__QualifiedId__Group__1__Impl )
            // InternalLayoutConfig.g:4143:2: rule__QualifiedId__Group__1__Impl
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
    // InternalLayoutConfig.g:4149:1: rule__QualifiedId__Group__1__Impl : ( ( rule__QualifiedId__Group_1__0 )* ) ;
    public final void rule__QualifiedId__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4153:1: ( ( ( rule__QualifiedId__Group_1__0 )* ) )
            // InternalLayoutConfig.g:4154:1: ( ( rule__QualifiedId__Group_1__0 )* )
            {
            // InternalLayoutConfig.g:4154:1: ( ( rule__QualifiedId__Group_1__0 )* )
            // InternalLayoutConfig.g:4155:1: ( rule__QualifiedId__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup_1()); 
            // InternalLayoutConfig.g:4156:1: ( rule__QualifiedId__Group_1__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==34) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalLayoutConfig.g:4156:2: rule__QualifiedId__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_27);
            	    rule__QualifiedId__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // InternalLayoutConfig.g:4170:1: rule__QualifiedId__Group_1__0 : rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 ;
    public final void rule__QualifiedId__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4174:1: ( rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 )
            // InternalLayoutConfig.g:4175:2: rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1
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
    // InternalLayoutConfig.g:4182:1: rule__QualifiedId__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedId__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4186:1: ( ( '.' ) )
            // InternalLayoutConfig.g:4187:1: ( '.' )
            {
            // InternalLayoutConfig.g:4187:1: ( '.' )
            // InternalLayoutConfig.g:4188:1: '.'
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
    // InternalLayoutConfig.g:4201:1: rule__QualifiedId__Group_1__1 : rule__QualifiedId__Group_1__1__Impl ;
    public final void rule__QualifiedId__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4205:1: ( rule__QualifiedId__Group_1__1__Impl )
            // InternalLayoutConfig.g:4206:2: rule__QualifiedId__Group_1__1__Impl
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
    // InternalLayoutConfig.g:4212:1: rule__QualifiedId__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4216:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4217:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4217:1: ( RULE_ID )
            // InternalLayoutConfig.g:4218:1: RULE_ID
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
    // InternalLayoutConfig.g:4233:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4237:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalLayoutConfig.g:4238:2: rule__Property__Group__0__Impl rule__Property__Group__1
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
    // InternalLayoutConfig.g:4245:1: rule__Property__Group__0__Impl : ( ( rule__Property__KeyAssignment_0 ) ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4249:1: ( ( ( rule__Property__KeyAssignment_0 ) ) )
            // InternalLayoutConfig.g:4250:1: ( ( rule__Property__KeyAssignment_0 ) )
            {
            // InternalLayoutConfig.g:4250:1: ( ( rule__Property__KeyAssignment_0 ) )
            // InternalLayoutConfig.g:4251:1: ( rule__Property__KeyAssignment_0 )
            {
             before(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 
            // InternalLayoutConfig.g:4252:1: ( rule__Property__KeyAssignment_0 )
            // InternalLayoutConfig.g:4252:2: rule__Property__KeyAssignment_0
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
    // InternalLayoutConfig.g:4262:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4266:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalLayoutConfig.g:4267:2: rule__Property__Group__1__Impl rule__Property__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_28);
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
    // InternalLayoutConfig.g:4274:1: rule__Property__Group__1__Impl : ( ':' ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4278:1: ( ( ':' ) )
            // InternalLayoutConfig.g:4279:1: ( ':' )
            {
            // InternalLayoutConfig.g:4279:1: ( ':' )
            // InternalLayoutConfig.g:4280:1: ':'
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
    // InternalLayoutConfig.g:4293:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4297:1: ( rule__Property__Group__2__Impl )
            // InternalLayoutConfig.g:4298:2: rule__Property__Group__2__Impl
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
    // InternalLayoutConfig.g:4304:1: rule__Property__Group__2__Impl : ( ( rule__Property__Alternatives_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4308:1: ( ( ( rule__Property__Alternatives_2 ) ) )
            // InternalLayoutConfig.g:4309:1: ( ( rule__Property__Alternatives_2 ) )
            {
            // InternalLayoutConfig.g:4309:1: ( ( rule__Property__Alternatives_2 ) )
            // InternalLayoutConfig.g:4310:1: ( rule__Property__Alternatives_2 )
            {
             before(grammarAccess.getPropertyAccess().getAlternatives_2()); 
            // InternalLayoutConfig.g:4311:1: ( rule__Property__Alternatives_2 )
            // InternalLayoutConfig.g:4311:2: rule__Property__Alternatives_2
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
    // InternalLayoutConfig.g:4327:1: rule__PropertyKey__Group__0 : rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 ;
    public final void rule__PropertyKey__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4331:1: ( rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 )
            // InternalLayoutConfig.g:4332:2: rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_26);
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
    // InternalLayoutConfig.g:4339:1: rule__PropertyKey__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4343:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4344:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4344:1: ( RULE_ID )
            // InternalLayoutConfig.g:4345:1: RULE_ID
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
    // InternalLayoutConfig.g:4356:1: rule__PropertyKey__Group__1 : rule__PropertyKey__Group__1__Impl ;
    public final void rule__PropertyKey__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4360:1: ( rule__PropertyKey__Group__1__Impl )
            // InternalLayoutConfig.g:4361:2: rule__PropertyKey__Group__1__Impl
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
    // InternalLayoutConfig.g:4367:1: rule__PropertyKey__Group__1__Impl : ( ( rule__PropertyKey__Group_1__0 )* ) ;
    public final void rule__PropertyKey__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4371:1: ( ( ( rule__PropertyKey__Group_1__0 )* ) )
            // InternalLayoutConfig.g:4372:1: ( ( rule__PropertyKey__Group_1__0 )* )
            {
            // InternalLayoutConfig.g:4372:1: ( ( rule__PropertyKey__Group_1__0 )* )
            // InternalLayoutConfig.g:4373:1: ( rule__PropertyKey__Group_1__0 )*
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup_1()); 
            // InternalLayoutConfig.g:4374:1: ( rule__PropertyKey__Group_1__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==34) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalLayoutConfig.g:4374:2: rule__PropertyKey__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_27);
            	    rule__PropertyKey__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // InternalLayoutConfig.g:4388:1: rule__PropertyKey__Group_1__0 : rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 ;
    public final void rule__PropertyKey__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4392:1: ( rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 )
            // InternalLayoutConfig.g:4393:2: rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1
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
    // InternalLayoutConfig.g:4400:1: rule__PropertyKey__Group_1__0__Impl : ( '.' ) ;
    public final void rule__PropertyKey__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4404:1: ( ( '.' ) )
            // InternalLayoutConfig.g:4405:1: ( '.' )
            {
            // InternalLayoutConfig.g:4405:1: ( '.' )
            // InternalLayoutConfig.g:4406:1: '.'
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
    // InternalLayoutConfig.g:4419:1: rule__PropertyKey__Group_1__1 : rule__PropertyKey__Group_1__1__Impl ;
    public final void rule__PropertyKey__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4423:1: ( rule__PropertyKey__Group_1__1__Impl )
            // InternalLayoutConfig.g:4424:2: rule__PropertyKey__Group_1__1__Impl
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
    // InternalLayoutConfig.g:4430:1: rule__PropertyKey__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4434:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4435:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4435:1: ( RULE_ID )
            // InternalLayoutConfig.g:4436:1: RULE_ID
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
    // InternalLayoutConfig.g:4452:1: rule__ShapeLayout__UnorderedGroup_2 : ( rule__ShapeLayout__UnorderedGroup_2__0 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            
        try {
            // InternalLayoutConfig.g:4457:1: ( ( rule__ShapeLayout__UnorderedGroup_2__0 )? )
            // InternalLayoutConfig.g:4458:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            {
            // InternalLayoutConfig.g:4458:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( LA26_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt26=1;
            }
            else if ( LA26_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalLayoutConfig.g:4458:2: rule__ShapeLayout__UnorderedGroup_2__0
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
    // InternalLayoutConfig.g:4468:1: rule__ShapeLayout__UnorderedGroup_2__Impl : ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) ;
    public final void rule__ShapeLayout__UnorderedGroup_2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4473:1: ( ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4474:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4474:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( LA27_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt27=1;
            }
            else if ( LA27_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // InternalLayoutConfig.g:4476:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4476:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4477:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
                    }
                    // InternalLayoutConfig.g:4477:108: ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    // InternalLayoutConfig.g:4478:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4484:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    // InternalLayoutConfig.g:4486:7: ( rule__ShapeLayout__Group_2_0__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 
                    // InternalLayoutConfig.g:4487:7: ( rule__ShapeLayout__Group_2_0__0 )
                    // InternalLayoutConfig.g:4487:8: rule__ShapeLayout__Group_2_0__0
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
                    // InternalLayoutConfig.g:4493:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4493:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4494:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
                    }
                    // InternalLayoutConfig.g:4494:108: ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    // InternalLayoutConfig.g:4495:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4501:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    // InternalLayoutConfig.g:4503:7: ( rule__ShapeLayout__Group_2_1__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 
                    // InternalLayoutConfig.g:4504:7: ( rule__ShapeLayout__Group_2_1__0 )
                    // InternalLayoutConfig.g:4504:8: rule__ShapeLayout__Group_2_1__0
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
    // InternalLayoutConfig.g:4519:1: rule__ShapeLayout__UnorderedGroup_2__0 : rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4523:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? )
            // InternalLayoutConfig.g:4524:2: rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4525:2: ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( LA28_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt28=1;
            }
            else if ( LA28_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalLayoutConfig.g:4525:2: rule__ShapeLayout__UnorderedGroup_2__1
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
    // InternalLayoutConfig.g:4532:1: rule__ShapeLayout__UnorderedGroup_2__1 : rule__ShapeLayout__UnorderedGroup_2__Impl ;
    public final void rule__ShapeLayout__UnorderedGroup_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4536:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl )
            // InternalLayoutConfig.g:4537:2: rule__ShapeLayout__UnorderedGroup_2__Impl
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


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0"
    // InternalLayoutConfig.g:4548:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0 : ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            
        try {
            // InternalLayoutConfig.g:4553:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? )
            // InternalLayoutConfig.g:4554:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
            {
            // InternalLayoutConfig.g:4554:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( LA29_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt29=1;
            }
            else if ( LA29_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt29=1;
            }
            else if ( LA29_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt29=1;
            }
            else if ( LA29_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalLayoutConfig.g:4554:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0();

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

            	getUnorderedGroupHelper().leave(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl"
    // InternalLayoutConfig.g:4564:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl : ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4569:1: ( ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4570:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4570:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
            int alt30=4;
            int LA30_0 = input.LA(1);

            if ( LA30_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt30=1;
            }
            else if ( LA30_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt30=2;
            }
            else if ( LA30_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt30=3;
            }
            else if ( LA30_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt30=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // InternalLayoutConfig.g:4572:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4572:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4573:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0)");
                    }
                    // InternalLayoutConfig.g:4573:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    // InternalLayoutConfig.g:4574:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4580:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    // InternalLayoutConfig.g:4582:7: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_0()); 
                    // InternalLayoutConfig.g:4583:7: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    // InternalLayoutConfig.g:4583:8: rule__ElkSingleEdgeSection__Group_1_0_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:4589:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4589:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4590:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1)");
                    }
                    // InternalLayoutConfig.g:4590:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    // InternalLayoutConfig.g:4591:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4597:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    // InternalLayoutConfig.g:4599:7: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_1()); 
                    // InternalLayoutConfig.g:4600:7: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    // InternalLayoutConfig.g:4600:8: rule__ElkSingleEdgeSection__Group_1_0_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLayoutConfig.g:4606:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4606:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    // InternalLayoutConfig.g:4607:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2)");
                    }
                    // InternalLayoutConfig.g:4607:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    // InternalLayoutConfig.g:4608:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4614:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    // InternalLayoutConfig.g:4616:7: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_2()); 
                    // InternalLayoutConfig.g:4617:7: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    // InternalLayoutConfig.g:4617:8: rule__ElkSingleEdgeSection__Group_1_0_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalLayoutConfig.g:4623:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4623:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    // InternalLayoutConfig.g:4624:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3)");
                    }
                    // InternalLayoutConfig.g:4624:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    // InternalLayoutConfig.g:4625:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4631:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    // InternalLayoutConfig.g:4633:7: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_3()); 
                    // InternalLayoutConfig.g:4634:7: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    // InternalLayoutConfig.g:4634:8: rule__ElkSingleEdgeSection__Group_1_0_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_3()); 

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
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0"
    // InternalLayoutConfig.g:4649:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4653:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? )
            // InternalLayoutConfig.g:4654:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_30);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4655:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( LA31_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt31=1;
            }
            else if ( LA31_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt31=1;
            }
            else if ( LA31_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt31=1;
            }
            else if ( LA31_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalLayoutConfig.g:4655:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1"
    // InternalLayoutConfig.g:4662:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4666:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? )
            // InternalLayoutConfig.g:4667:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
            {
            pushFollow(FollowSets000.FOLLOW_30);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4668:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( LA32_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt32=1;
            }
            else if ( LA32_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt32=1;
            }
            else if ( LA32_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt32=1;
            }
            else if ( LA32_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalLayoutConfig.g:4668:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2"
    // InternalLayoutConfig.g:4675:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4679:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? )
            // InternalLayoutConfig.g:4680:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
            {
            pushFollow(FollowSets000.FOLLOW_30);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4681:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( LA33_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt33=1;
            }
            else if ( LA33_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt33=1;
            }
            else if ( LA33_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt33=1;
            }
            else if ( LA33_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalLayoutConfig.g:4681:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3"
    // InternalLayoutConfig.g:4688:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4692:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl )
            // InternalLayoutConfig.g:4693:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0"
    // InternalLayoutConfig.g:4708:1: rule__ElkEdgeSection__UnorderedGroup_4_0 : ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            
        try {
            // InternalLayoutConfig.g:4713:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? )
            // InternalLayoutConfig.g:4714:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
            {
            // InternalLayoutConfig.g:4714:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( LA34_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt34=1;
            }
            else if ( LA34_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt34=1;
            }
            else if ( LA34_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt34=1;
            }
            else if ( LA34_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalLayoutConfig.g:4714:2: rule__ElkEdgeSection__UnorderedGroup_4_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__0();

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

            	getUnorderedGroupHelper().leave(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl"
    // InternalLayoutConfig.g:4724:1: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl : ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4729:1: ( ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4730:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4730:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
            int alt35=4;
            int LA35_0 = input.LA(1);

            if ( LA35_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt35=1;
            }
            else if ( LA35_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt35=2;
            }
            else if ( LA35_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt35=3;
            }
            else if ( LA35_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt35=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalLayoutConfig.g:4732:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4732:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4733:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0)");
                    }
                    // InternalLayoutConfig.g:4733:113: ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    // InternalLayoutConfig.g:4734:6: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4740:6: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    // InternalLayoutConfig.g:4742:7: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_0()); 
                    // InternalLayoutConfig.g:4743:7: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    // InternalLayoutConfig.g:4743:8: rule__ElkEdgeSection__Group_4_0_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:4749:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4749:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4750:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1)");
                    }
                    // InternalLayoutConfig.g:4750:113: ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    // InternalLayoutConfig.g:4751:6: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4757:6: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    // InternalLayoutConfig.g:4759:7: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_1()); 
                    // InternalLayoutConfig.g:4760:7: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    // InternalLayoutConfig.g:4760:8: rule__ElkEdgeSection__Group_4_0_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLayoutConfig.g:4766:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4766:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    // InternalLayoutConfig.g:4767:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2)");
                    }
                    // InternalLayoutConfig.g:4767:113: ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    // InternalLayoutConfig.g:4768:6: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4774:6: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    // InternalLayoutConfig.g:4776:7: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_2()); 
                    // InternalLayoutConfig.g:4777:7: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    // InternalLayoutConfig.g:4777:8: rule__ElkEdgeSection__Group_4_0_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalLayoutConfig.g:4783:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4783:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    // InternalLayoutConfig.g:4784:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3)");
                    }
                    // InternalLayoutConfig.g:4784:113: ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    // InternalLayoutConfig.g:4785:6: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4791:6: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    // InternalLayoutConfig.g:4793:7: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_3()); 
                    // InternalLayoutConfig.g:4794:7: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    // InternalLayoutConfig.g:4794:8: rule__ElkEdgeSection__Group_4_0_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_3()); 

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
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__0"
    // InternalLayoutConfig.g:4809:1: rule__ElkEdgeSection__UnorderedGroup_4_0__0 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4813:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? )
            // InternalLayoutConfig.g:4814:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_30);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4815:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( LA36_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt36=1;
            }
            else if ( LA36_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt36=1;
            }
            else if ( LA36_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt36=1;
            }
            else if ( LA36_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalLayoutConfig.g:4815:2: rule__ElkEdgeSection__UnorderedGroup_4_0__1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__1();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__0"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__1"
    // InternalLayoutConfig.g:4822:1: rule__ElkEdgeSection__UnorderedGroup_4_0__1 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4826:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? )
            // InternalLayoutConfig.g:4827:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
            {
            pushFollow(FollowSets000.FOLLOW_30);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4828:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( LA37_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt37=1;
            }
            else if ( LA37_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt37=1;
            }
            else if ( LA37_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt37=1;
            }
            else if ( LA37_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalLayoutConfig.g:4828:2: rule__ElkEdgeSection__UnorderedGroup_4_0__2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__2();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__1"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__2"
    // InternalLayoutConfig.g:4835:1: rule__ElkEdgeSection__UnorderedGroup_4_0__2 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4839:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? )
            // InternalLayoutConfig.g:4840:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
            {
            pushFollow(FollowSets000.FOLLOW_30);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4841:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( LA38_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt38=1;
            }
            else if ( LA38_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt38=1;
            }
            else if ( LA38_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt38=1;
            }
            else if ( LA38_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalLayoutConfig.g:4841:2: rule__ElkEdgeSection__UnorderedGroup_4_0__3
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__3();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__2"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__3"
    // InternalLayoutConfig.g:4848:1: rule__ElkEdgeSection__UnorderedGroup_4_0__3 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4852:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl )
            // InternalLayoutConfig.g:4853:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__3"


    // $ANTLR start "rule__RootNode__ChildrenAssignment_1"
    // InternalLayoutConfig.g:4868:1: rule__RootNode__ChildrenAssignment_1 : ( ruleElkNode ) ;
    public final void rule__RootNode__ChildrenAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4872:1: ( ( ruleElkNode ) )
            // InternalLayoutConfig.g:4873:1: ( ruleElkNode )
            {
            // InternalLayoutConfig.g:4873:1: ( ruleElkNode )
            // InternalLayoutConfig.g:4874:1: ruleElkNode
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
    // InternalLayoutConfig.g:4883:1: rule__ElkNode__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__ElkNode__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4887:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4888:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4888:1: ( RULE_ID )
            // InternalLayoutConfig.g:4889:1: RULE_ID
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
    // InternalLayoutConfig.g:4898:1: rule__ElkNode__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__ElkNode__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4902:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4903:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4903:1: ( ruleProperty )
            // InternalLayoutConfig.g:4904:1: ruleProperty
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
    // InternalLayoutConfig.g:4913:1: rule__ElkNode__ChildrenAssignment_3_1 : ( ruleRefElkNode ) ;
    public final void rule__ElkNode__ChildrenAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4917:1: ( ( ruleRefElkNode ) )
            // InternalLayoutConfig.g:4918:1: ( ruleRefElkNode )
            {
            // InternalLayoutConfig.g:4918:1: ( ruleRefElkNode )
            // InternalLayoutConfig.g:4919:1: ruleRefElkNode
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
    // InternalLayoutConfig.g:4928:1: rule__RefElkNode__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__RefElkNode__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4932:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4933:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4933:1: ( RULE_ID )
            // InternalLayoutConfig.g:4934:1: RULE_ID
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
    // InternalLayoutConfig.g:4943:1: rule__RefElkNode__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__RefElkNode__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4947:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4948:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4948:1: ( ruleProperty )
            // InternalLayoutConfig.g:4949:1: ruleProperty
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
    // InternalLayoutConfig.g:4958:1: rule__ElkLabel__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkLabel__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4962:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4963:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4963:1: ( RULE_ID )
            // InternalLayoutConfig.g:4964:1: RULE_ID
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
    // InternalLayoutConfig.g:4973:1: rule__ElkLabel__TextAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ElkLabel__TextAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4977:1: ( ( RULE_STRING ) )
            // InternalLayoutConfig.g:4978:1: ( RULE_STRING )
            {
            // InternalLayoutConfig.g:4978:1: ( RULE_STRING )
            // InternalLayoutConfig.g:4979:1: RULE_STRING
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
    // InternalLayoutConfig.g:4988:1: rule__ElkLabel__PropertiesAssignment_3_2 : ( ruleProperty ) ;
    public final void rule__ElkLabel__PropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4992:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4993:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4993:1: ( ruleProperty )
            // InternalLayoutConfig.g:4994:1: ruleProperty
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
    // InternalLayoutConfig.g:5003:1: rule__ElkLabel__LabelsAssignment_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkLabel__LabelsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5007:1: ( ( ruleElkLabel ) )
            // InternalLayoutConfig.g:5008:1: ( ruleElkLabel )
            {
            // InternalLayoutConfig.g:5008:1: ( ruleElkLabel )
            // InternalLayoutConfig.g:5009:1: ruleElkLabel
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
    // InternalLayoutConfig.g:5021:1: rule__ShapeLayout__XAssignment_2_0_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__XAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5025:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5026:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5026:1: ( ruleNumber )
            // InternalLayoutConfig.g:5027:1: ruleNumber
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
    // InternalLayoutConfig.g:5036:1: rule__ShapeLayout__YAssignment_2_0_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__YAssignment_2_0_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5040:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5041:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5041:1: ( ruleNumber )
            // InternalLayoutConfig.g:5042:1: ruleNumber
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
    // InternalLayoutConfig.g:5051:1: rule__ShapeLayout__WidthAssignment_2_1_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__WidthAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5055:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5056:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5056:1: ( ruleNumber )
            // InternalLayoutConfig.g:5057:1: ruleNumber
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
    // InternalLayoutConfig.g:5066:1: rule__ShapeLayout__HeightAssignment_2_1_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__HeightAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5070:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5071:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5071:1: ( ruleNumber )
            // InternalLayoutConfig.g:5072:1: ruleNumber
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
    // InternalLayoutConfig.g:5088:1: rule__EdgeLayout__SectionsAssignment_2_0 : ( ruleElkSingleEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5092:1: ( ( ruleElkSingleEdgeSection ) )
            // InternalLayoutConfig.g:5093:1: ( ruleElkSingleEdgeSection )
            {
            // InternalLayoutConfig.g:5093:1: ( ruleElkSingleEdgeSection )
            // InternalLayoutConfig.g:5094:1: ruleElkSingleEdgeSection
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
    // InternalLayoutConfig.g:5103:1: rule__EdgeLayout__SectionsAssignment_2_1 : ( ruleElkEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5107:1: ( ( ruleElkEdgeSection ) )
            // InternalLayoutConfig.g:5108:1: ( ruleElkEdgeSection )
            {
            // InternalLayoutConfig.g:5108:1: ( ruleElkEdgeSection )
            // InternalLayoutConfig.g:5109:1: ruleElkEdgeSection
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


    // $ANTLR start "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2"
    // InternalLayoutConfig.g:5118:1: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5122:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5123:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5123:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5124:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0()); 
            // InternalLayoutConfig.g:5125:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5126:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_0_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_0_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2"
    // InternalLayoutConfig.g:5137:1: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5141:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5142:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5142:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5143:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0()); 
            // InternalLayoutConfig.g:5144:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5145:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_1_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_1_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2"
    // InternalLayoutConfig.g:5156:1: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5160:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5161:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5161:1: ( ruleNumber )
            // InternalLayoutConfig.g:5162:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_0_2_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_0_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4"
    // InternalLayoutConfig.g:5171:1: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5175:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5176:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5176:1: ( ruleNumber )
            // InternalLayoutConfig.g:5177:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_0_2_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_0_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2"
    // InternalLayoutConfig.g:5186:1: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5190:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5191:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5191:1: ( ruleNumber )
            // InternalLayoutConfig.g:5192:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_0_3_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_0_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4"
    // InternalLayoutConfig.g:5201:1: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5205:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5206:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5206:1: ( ruleNumber )
            // InternalLayoutConfig.g:5207:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_0_3_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_0_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2"
    // InternalLayoutConfig.g:5216:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5220:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5221:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5221:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5222:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1"
    // InternalLayoutConfig.g:5231:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5235:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5236:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5236:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5237:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1"


    // $ANTLR start "rule__ElkSingleEdgeSection__PropertiesAssignment_1_2"
    // InternalLayoutConfig.g:5246:1: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 : ( ruleProperty ) ;
    public final void rule__ElkSingleEdgeSection__PropertiesAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5250:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:5251:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:5251:1: ( ruleProperty )
            // InternalLayoutConfig.g:5252:1: ruleProperty
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesPropertyParserRuleCall_1_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesPropertyParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__PropertiesAssignment_1_2"


    // $ANTLR start "rule__ElkEdgeSection__IdentifierAssignment_1"
    // InternalLayoutConfig.g:5261:1: rule__ElkEdgeSection__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkEdgeSection__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5265:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5266:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:5266:1: ( RULE_ID )
            // InternalLayoutConfig.g:5267:1: RULE_ID
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
    // InternalLayoutConfig.g:5276:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5280:1: ( ( ( RULE_ID ) ) )
            // InternalLayoutConfig.g:5281:1: ( ( RULE_ID ) )
            {
            // InternalLayoutConfig.g:5281:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5282:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
            // InternalLayoutConfig.g:5283:1: ( RULE_ID )
            // InternalLayoutConfig.g:5284:1: RULE_ID
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
    // InternalLayoutConfig.g:5295:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5299:1: ( ( ( RULE_ID ) ) )
            // InternalLayoutConfig.g:5300:1: ( ( RULE_ID ) )
            {
            // InternalLayoutConfig.g:5300:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5301:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
            // InternalLayoutConfig.g:5302:1: ( RULE_ID )
            // InternalLayoutConfig.g:5303:1: RULE_ID
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


    // $ANTLR start "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2"
    // InternalLayoutConfig.g:5314:1: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5318:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5319:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5319:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5320:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0()); 
            // InternalLayoutConfig.g:5321:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5322:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_0_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_0_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2"
    // InternalLayoutConfig.g:5333:1: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5337:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5338:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5338:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5339:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0()); 
            // InternalLayoutConfig.g:5340:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5341:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_1_2_0_1()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_1_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2"


    // $ANTLR start "rule__ElkEdgeSection__StartXAssignment_4_0_2_2"
    // InternalLayoutConfig.g:5352:1: rule__ElkEdgeSection__StartXAssignment_4_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartXAssignment_4_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5356:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5357:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5357:1: ( ruleNumber )
            // InternalLayoutConfig.g:5358:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_0_2_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_0_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__StartXAssignment_4_0_2_2"


    // $ANTLR start "rule__ElkEdgeSection__StartYAssignment_4_0_2_4"
    // InternalLayoutConfig.g:5367:1: rule__ElkEdgeSection__StartYAssignment_4_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartYAssignment_4_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5371:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5372:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5372:1: ( ruleNumber )
            // InternalLayoutConfig.g:5373:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_0_2_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_0_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__StartYAssignment_4_0_2_4"


    // $ANTLR start "rule__ElkEdgeSection__EndXAssignment_4_0_3_2"
    // InternalLayoutConfig.g:5382:1: rule__ElkEdgeSection__EndXAssignment_4_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndXAssignment_4_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5386:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5387:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5387:1: ( ruleNumber )
            // InternalLayoutConfig.g:5388:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_0_3_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_0_3_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__EndXAssignment_4_0_3_2"


    // $ANTLR start "rule__ElkEdgeSection__EndYAssignment_4_0_3_4"
    // InternalLayoutConfig.g:5397:1: rule__ElkEdgeSection__EndYAssignment_4_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndYAssignment_4_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5401:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5402:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5402:1: ( ruleNumber )
            // InternalLayoutConfig.g:5403:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_0_3_4_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_0_3_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__EndYAssignment_4_0_3_4"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_1_2"
    // InternalLayoutConfig.g:5412:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5416:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5417:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5417:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5418:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_1_2"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1"
    // InternalLayoutConfig.g:5427:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5431:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5432:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5432:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5433:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1"


    // $ANTLR start "rule__ElkEdgeSection__PropertiesAssignment_4_2"
    // InternalLayoutConfig.g:5442:1: rule__ElkEdgeSection__PropertiesAssignment_4_2 : ( ruleProperty ) ;
    public final void rule__ElkEdgeSection__PropertiesAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5446:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:5447:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:5447:1: ( ruleProperty )
            // InternalLayoutConfig.g:5448:1: ruleProperty
            {
             before(grammarAccess.getElkEdgeSectionAccess().getPropertiesPropertyParserRuleCall_4_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getPropertiesPropertyParserRuleCall_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__PropertiesAssignment_4_2"


    // $ANTLR start "rule__ElkBendPoint__XAssignment_0"
    // InternalLayoutConfig.g:5457:1: rule__ElkBendPoint__XAssignment_0 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5461:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5462:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5462:1: ( ruleNumber )
            // InternalLayoutConfig.g:5463:1: ruleNumber
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
    // InternalLayoutConfig.g:5472:1: rule__ElkBendPoint__YAssignment_2 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5476:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5477:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5477:1: ( ruleNumber )
            // InternalLayoutConfig.g:5478:1: ruleNumber
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
    // InternalLayoutConfig.g:5487:1: rule__Property__KeyAssignment_0 : ( rulePropertyKey ) ;
    public final void rule__Property__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5491:1: ( ( rulePropertyKey ) )
            // InternalLayoutConfig.g:5492:1: ( rulePropertyKey )
            {
            // InternalLayoutConfig.g:5492:1: ( rulePropertyKey )
            // InternalLayoutConfig.g:5493:1: rulePropertyKey
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
    // InternalLayoutConfig.g:5502:1: rule__Property__ValueAssignment_2_0 : ( ruleStringValue ) ;
    public final void rule__Property__ValueAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5506:1: ( ( ruleStringValue ) )
            // InternalLayoutConfig.g:5507:1: ( ruleStringValue )
            {
            // InternalLayoutConfig.g:5507:1: ( ruleStringValue )
            // InternalLayoutConfig.g:5508:1: ruleStringValue
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
    // InternalLayoutConfig.g:5517:1: rule__Property__ValueAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__Property__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5521:1: ( ( ruleQualifiedIdValue ) )
            // InternalLayoutConfig.g:5522:1: ( ruleQualifiedIdValue )
            {
            // InternalLayoutConfig.g:5522:1: ( ruleQualifiedIdValue )
            // InternalLayoutConfig.g:5523:1: ruleQualifiedIdValue
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
    // InternalLayoutConfig.g:5532:1: rule__Property__ValueAssignment_2_2 : ( ruleNumberValue ) ;
    public final void rule__Property__ValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5536:1: ( ( ruleNumberValue ) )
            // InternalLayoutConfig.g:5537:1: ( ruleNumberValue )
            {
            // InternalLayoutConfig.g:5537:1: ( ruleNumberValue )
            // InternalLayoutConfig.g:5538:1: ruleNumberValue
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
    // InternalLayoutConfig.g:5547:1: rule__Property__ValueAssignment_2_3 : ( ruleBooleanValue ) ;
    public final void rule__Property__ValueAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5551:1: ( ( ruleBooleanValue ) )
            // InternalLayoutConfig.g:5552:1: ( ruleBooleanValue )
            {
            // InternalLayoutConfig.g:5552:1: ( ruleBooleanValue )
            // InternalLayoutConfig.g:5553:1: ruleBooleanValue
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
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x000000013C000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000003C000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000040000080L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000080000002L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000200200000L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000001000002L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000400000002L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000000000060F0L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000002800002L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x000000003C000002L});
    }


}