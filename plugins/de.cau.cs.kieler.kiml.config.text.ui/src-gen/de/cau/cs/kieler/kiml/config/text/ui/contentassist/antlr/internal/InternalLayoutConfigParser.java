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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_SIGNED_INT", "RULE_FLOAT", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'true'", "'false'", "'{'", "'}'", "'ref'", "'label'", "':'", "'layout'", "'['", "']'", "'position'", "','", "'size'", "'incoming'", "'outgoing'", "'start'", "'end'", "'bends'", "'|'", "'section'", "'->'", "'.'"
    };
    public static final int RULE_STRING=7;
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
    public static final int RULE_ID=6;
    public static final int RULE_WS=11;
    public static final int RULE_SIGNED_INT=4;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int RULE_FLOAT=5;
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


    // $ANTLR start "entryRuleProperty"
    // InternalLayoutConfig.g:300:1: entryRuleProperty : ruleProperty EOF ;
    public final void entryRuleProperty() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:301:1: ( ruleProperty EOF )
            // InternalLayoutConfig.g:302:1: ruleProperty EOF
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
    // InternalLayoutConfig.g:309:1: ruleProperty : ( ( rule__Property__Group__0 ) ) ;
    public final void ruleProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:313:2: ( ( ( rule__Property__Group__0 ) ) )
            // InternalLayoutConfig.g:314:1: ( ( rule__Property__Group__0 ) )
            {
            // InternalLayoutConfig.g:314:1: ( ( rule__Property__Group__0 ) )
            // InternalLayoutConfig.g:315:1: ( rule__Property__Group__0 )
            {
             before(grammarAccess.getPropertyAccess().getGroup()); 
            // InternalLayoutConfig.g:316:1: ( rule__Property__Group__0 )
            // InternalLayoutConfig.g:316:2: rule__Property__Group__0
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


    // $ANTLR start "entryRuleQualifiedId"
    // InternalLayoutConfig.g:328:1: entryRuleQualifiedId : ruleQualifiedId EOF ;
    public final void entryRuleQualifiedId() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:329:1: ( ruleQualifiedId EOF )
            // InternalLayoutConfig.g:330:1: ruleQualifiedId EOF
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
    // InternalLayoutConfig.g:337:1: ruleQualifiedId : ( ( rule__QualifiedId__Group__0 ) ) ;
    public final void ruleQualifiedId() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:341:2: ( ( ( rule__QualifiedId__Group__0 ) ) )
            // InternalLayoutConfig.g:342:1: ( ( rule__QualifiedId__Group__0 ) )
            {
            // InternalLayoutConfig.g:342:1: ( ( rule__QualifiedId__Group__0 ) )
            // InternalLayoutConfig.g:343:1: ( rule__QualifiedId__Group__0 )
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup()); 
            // InternalLayoutConfig.g:344:1: ( rule__QualifiedId__Group__0 )
            // InternalLayoutConfig.g:344:2: rule__QualifiedId__Group__0
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


    // $ANTLR start "entryRuleBoolean"
    // InternalLayoutConfig.g:356:1: entryRuleBoolean : ruleBoolean EOF ;
    public final void entryRuleBoolean() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:357:1: ( ruleBoolean EOF )
            // InternalLayoutConfig.g:358:1: ruleBoolean EOF
            {
             before(grammarAccess.getBooleanRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getBooleanRule()); 
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
    // $ANTLR end "entryRuleBoolean"


    // $ANTLR start "ruleBoolean"
    // InternalLayoutConfig.g:365:1: ruleBoolean : ( ( rule__Boolean__Alternatives ) ) ;
    public final void ruleBoolean() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:369:2: ( ( ( rule__Boolean__Alternatives ) ) )
            // InternalLayoutConfig.g:370:1: ( ( rule__Boolean__Alternatives ) )
            {
            // InternalLayoutConfig.g:370:1: ( ( rule__Boolean__Alternatives ) )
            // InternalLayoutConfig.g:371:1: ( rule__Boolean__Alternatives )
            {
             before(grammarAccess.getBooleanAccess().getAlternatives()); 
            // InternalLayoutConfig.g:372:1: ( rule__Boolean__Alternatives )
            // InternalLayoutConfig.g:372:2: rule__Boolean__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Boolean__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBooleanAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBoolean"


    // $ANTLR start "entryRuleNumber"
    // InternalLayoutConfig.g:384:1: entryRuleNumber : ruleNumber EOF ;
    public final void entryRuleNumber() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:385:1: ( ruleNumber EOF )
            // InternalLayoutConfig.g:386:1: ruleNumber EOF
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
    // InternalLayoutConfig.g:393:1: ruleNumber : ( ( rule__Number__Alternatives ) ) ;
    public final void ruleNumber() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:397:2: ( ( ( rule__Number__Alternatives ) ) )
            // InternalLayoutConfig.g:398:1: ( ( rule__Number__Alternatives ) )
            {
            // InternalLayoutConfig.g:398:1: ( ( rule__Number__Alternatives ) )
            // InternalLayoutConfig.g:399:1: ( rule__Number__Alternatives )
            {
             before(grammarAccess.getNumberAccess().getAlternatives()); 
            // InternalLayoutConfig.g:400:1: ( rule__Number__Alternatives )
            // InternalLayoutConfig.g:400:2: rule__Number__Alternatives
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


    // $ANTLR start "entryRulePropertyKey"
    // InternalLayoutConfig.g:412:1: entryRulePropertyKey : rulePropertyKey EOF ;
    public final void entryRulePropertyKey() throws RecognitionException {

        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalLayoutConfig.g:416:1: ( rulePropertyKey EOF )
            // InternalLayoutConfig.g:417:1: rulePropertyKey EOF
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
    // InternalLayoutConfig.g:427:1: rulePropertyKey : ( ( rule__PropertyKey__Group__0 ) ) ;
    public final void rulePropertyKey() throws RecognitionException {

        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:432:2: ( ( ( rule__PropertyKey__Group__0 ) ) )
            // InternalLayoutConfig.g:433:1: ( ( rule__PropertyKey__Group__0 ) )
            {
            // InternalLayoutConfig.g:433:1: ( ( rule__PropertyKey__Group__0 ) )
            // InternalLayoutConfig.g:434:1: ( rule__PropertyKey__Group__0 )
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup()); 
            // InternalLayoutConfig.g:435:1: ( rule__PropertyKey__Group__0 )
            // InternalLayoutConfig.g:435:2: rule__PropertyKey__Group__0
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


    // $ANTLR start "rule__EdgeLayout__Alternatives_2"
    // InternalLayoutConfig.g:448:1: rule__EdgeLayout__Alternatives_2 : ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) );
    public final void rule__EdgeLayout__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:452:1: ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) )
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
                    // InternalLayoutConfig.g:453:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    {
                    // InternalLayoutConfig.g:453:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    // InternalLayoutConfig.g:454:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0()); 
                    // InternalLayoutConfig.g:455:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    // InternalLayoutConfig.g:455:2: rule__EdgeLayout__SectionsAssignment_2_0
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
                    // InternalLayoutConfig.g:459:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    {
                    // InternalLayoutConfig.g:459:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    // InternalLayoutConfig.g:460:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    {
                    // InternalLayoutConfig.g:460:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) )
                    // InternalLayoutConfig.g:461:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalLayoutConfig.g:462:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    // InternalLayoutConfig.g:462:2: rule__EdgeLayout__SectionsAssignment_2_1
                    {
                    pushFollow(FollowSets000.FOLLOW_3);
                    rule__EdgeLayout__SectionsAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 

                    }

                    // InternalLayoutConfig.g:465:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    // InternalLayoutConfig.g:466:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalLayoutConfig.g:467:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==32) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:467:2: rule__EdgeLayout__SectionsAssignment_2_1
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


    // $ANTLR start "rule__Property__Alternatives_2"
    // InternalLayoutConfig.g:477:1: rule__Property__Alternatives_2 : ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( ( rule__Property__ValueAssignment_2_4 ) ) );
    public final void rule__Property__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:481:1: ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( ( rule__Property__ValueAssignment_2_4 ) ) )
            int alt3=5;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt3=1;
                }
                break;
            case RULE_ID:
                {
                alt3=2;
                }
                break;
            case 13:
            case 14:
                {
                alt3=3;
                }
                break;
            case RULE_SIGNED_INT:
                {
                alt3=4;
                }
                break;
            case RULE_FLOAT:
                {
                alt3=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalLayoutConfig.g:482:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    {
                    // InternalLayoutConfig.g:482:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    // InternalLayoutConfig.g:483:1: ( rule__Property__ValueAssignment_2_0 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_0()); 
                    // InternalLayoutConfig.g:484:1: ( rule__Property__ValueAssignment_2_0 )
                    // InternalLayoutConfig.g:484:2: rule__Property__ValueAssignment_2_0
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
                    // InternalLayoutConfig.g:488:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    {
                    // InternalLayoutConfig.g:488:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    // InternalLayoutConfig.g:489:1: ( rule__Property__ValueAssignment_2_1 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_1()); 
                    // InternalLayoutConfig.g:490:1: ( rule__Property__ValueAssignment_2_1 )
                    // InternalLayoutConfig.g:490:2: rule__Property__ValueAssignment_2_1
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
                    // InternalLayoutConfig.g:494:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    {
                    // InternalLayoutConfig.g:494:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    // InternalLayoutConfig.g:495:1: ( rule__Property__ValueAssignment_2_2 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_2()); 
                    // InternalLayoutConfig.g:496:1: ( rule__Property__ValueAssignment_2_2 )
                    // InternalLayoutConfig.g:496:2: rule__Property__ValueAssignment_2_2
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
                    // InternalLayoutConfig.g:500:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    {
                    // InternalLayoutConfig.g:500:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    // InternalLayoutConfig.g:501:1: ( rule__Property__ValueAssignment_2_3 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 
                    // InternalLayoutConfig.g:502:1: ( rule__Property__ValueAssignment_2_3 )
                    // InternalLayoutConfig.g:502:2: rule__Property__ValueAssignment_2_3
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Property__ValueAssignment_2_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalLayoutConfig.g:506:6: ( ( rule__Property__ValueAssignment_2_4 ) )
                    {
                    // InternalLayoutConfig.g:506:6: ( ( rule__Property__ValueAssignment_2_4 ) )
                    // InternalLayoutConfig.g:507:1: ( rule__Property__ValueAssignment_2_4 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_4()); 
                    // InternalLayoutConfig.g:508:1: ( rule__Property__ValueAssignment_2_4 )
                    // InternalLayoutConfig.g:508:2: rule__Property__ValueAssignment_2_4
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Property__ValueAssignment_2_4();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_4()); 

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


    // $ANTLR start "rule__Boolean__Alternatives"
    // InternalLayoutConfig.g:517:1: rule__Boolean__Alternatives : ( ( 'true' ) | ( 'false' ) );
    public final void rule__Boolean__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:521:1: ( ( 'true' ) | ( 'false' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==13) ) {
                alt4=1;
            }
            else if ( (LA4_0==14) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalLayoutConfig.g:522:1: ( 'true' )
                    {
                    // InternalLayoutConfig.g:522:1: ( 'true' )
                    // InternalLayoutConfig.g:523:1: 'true'
                    {
                     before(grammarAccess.getBooleanAccess().getTrueKeyword_0()); 
                    match(input,13,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getBooleanAccess().getTrueKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:530:6: ( 'false' )
                    {
                    // InternalLayoutConfig.g:530:6: ( 'false' )
                    // InternalLayoutConfig.g:531:1: 'false'
                    {
                     before(grammarAccess.getBooleanAccess().getFalseKeyword_1()); 
                    match(input,14,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getBooleanAccess().getFalseKeyword_1()); 

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
    // $ANTLR end "rule__Boolean__Alternatives"


    // $ANTLR start "rule__Number__Alternatives"
    // InternalLayoutConfig.g:543:1: rule__Number__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__Number__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:547:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
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
                    // InternalLayoutConfig.g:548:1: ( RULE_SIGNED_INT )
                    {
                    // InternalLayoutConfig.g:548:1: ( RULE_SIGNED_INT )
                    // InternalLayoutConfig.g:549:1: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:554:6: ( RULE_FLOAT )
                    {
                    // InternalLayoutConfig.g:554:6: ( RULE_FLOAT )
                    // InternalLayoutConfig.g:555:1: RULE_FLOAT
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


    // $ANTLR start "rule__RootNode__Group__0"
    // InternalLayoutConfig.g:567:1: rule__RootNode__Group__0 : rule__RootNode__Group__0__Impl rule__RootNode__Group__1 ;
    public final void rule__RootNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:571:1: ( rule__RootNode__Group__0__Impl rule__RootNode__Group__1 )
            // InternalLayoutConfig.g:572:2: rule__RootNode__Group__0__Impl rule__RootNode__Group__1
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
    // InternalLayoutConfig.g:579:1: rule__RootNode__Group__0__Impl : ( () ) ;
    public final void rule__RootNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:583:1: ( ( () ) )
            // InternalLayoutConfig.g:584:1: ( () )
            {
            // InternalLayoutConfig.g:584:1: ( () )
            // InternalLayoutConfig.g:585:1: ()
            {
             before(grammarAccess.getRootNodeAccess().getElkNodeAction_0()); 
            // InternalLayoutConfig.g:586:1: ()
            // InternalLayoutConfig.g:588:1: 
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
    // InternalLayoutConfig.g:598:1: rule__RootNode__Group__1 : rule__RootNode__Group__1__Impl ;
    public final void rule__RootNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:602:1: ( rule__RootNode__Group__1__Impl )
            // InternalLayoutConfig.g:603:2: rule__RootNode__Group__1__Impl
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
    // InternalLayoutConfig.g:609:1: rule__RootNode__Group__1__Impl : ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) ) ;
    public final void rule__RootNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:613:1: ( ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) ) )
            // InternalLayoutConfig.g:614:1: ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) )
            {
            // InternalLayoutConfig.g:614:1: ( ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* ) )
            // InternalLayoutConfig.g:615:1: ( ( rule__RootNode__ChildrenAssignment_1 ) ) ( ( rule__RootNode__ChildrenAssignment_1 )* )
            {
            // InternalLayoutConfig.g:615:1: ( ( rule__RootNode__ChildrenAssignment_1 ) )
            // InternalLayoutConfig.g:616:1: ( rule__RootNode__ChildrenAssignment_1 )
            {
             before(grammarAccess.getRootNodeAccess().getChildrenAssignment_1()); 
            // InternalLayoutConfig.g:617:1: ( rule__RootNode__ChildrenAssignment_1 )
            // InternalLayoutConfig.g:617:2: rule__RootNode__ChildrenAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_5);
            rule__RootNode__ChildrenAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRootNodeAccess().getChildrenAssignment_1()); 

            }

            // InternalLayoutConfig.g:620:1: ( ( rule__RootNode__ChildrenAssignment_1 )* )
            // InternalLayoutConfig.g:621:1: ( rule__RootNode__ChildrenAssignment_1 )*
            {
             before(grammarAccess.getRootNodeAccess().getChildrenAssignment_1()); 
            // InternalLayoutConfig.g:622:1: ( rule__RootNode__ChildrenAssignment_1 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_ID) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalLayoutConfig.g:622:2: rule__RootNode__ChildrenAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__RootNode__ChildrenAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
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
    // InternalLayoutConfig.g:637:1: rule__ElkNode__Group__0 : rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 ;
    public final void rule__ElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:641:1: ( rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 )
            // InternalLayoutConfig.g:642:2: rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1
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
    // InternalLayoutConfig.g:649:1: rule__ElkNode__Group__0__Impl : ( ( rule__ElkNode__IdentifierAssignment_0 ) ) ;
    public final void rule__ElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:653:1: ( ( ( rule__ElkNode__IdentifierAssignment_0 ) ) )
            // InternalLayoutConfig.g:654:1: ( ( rule__ElkNode__IdentifierAssignment_0 ) )
            {
            // InternalLayoutConfig.g:654:1: ( ( rule__ElkNode__IdentifierAssignment_0 ) )
            // InternalLayoutConfig.g:655:1: ( rule__ElkNode__IdentifierAssignment_0 )
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierAssignment_0()); 
            // InternalLayoutConfig.g:656:1: ( rule__ElkNode__IdentifierAssignment_0 )
            // InternalLayoutConfig.g:656:2: rule__ElkNode__IdentifierAssignment_0
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
    // InternalLayoutConfig.g:666:1: rule__ElkNode__Group__1 : rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 ;
    public final void rule__ElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:670:1: ( rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 )
            // InternalLayoutConfig.g:671:2: rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2
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
    // InternalLayoutConfig.g:678:1: rule__ElkNode__Group__1__Impl : ( '{' ) ;
    public final void rule__ElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:682:1: ( ( '{' ) )
            // InternalLayoutConfig.g:683:1: ( '{' )
            {
            // InternalLayoutConfig.g:683:1: ( '{' )
            // InternalLayoutConfig.g:684:1: '{'
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
    // InternalLayoutConfig.g:697:1: rule__ElkNode__Group__2 : rule__ElkNode__Group__2__Impl rule__ElkNode__Group__3 ;
    public final void rule__ElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:701:1: ( rule__ElkNode__Group__2__Impl rule__ElkNode__Group__3 )
            // InternalLayoutConfig.g:702:2: rule__ElkNode__Group__2__Impl rule__ElkNode__Group__3
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
    // InternalLayoutConfig.g:709:1: rule__ElkNode__Group__2__Impl : ( ( rule__ElkNode__PropertiesAssignment_2 )* ) ;
    public final void rule__ElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:713:1: ( ( ( rule__ElkNode__PropertiesAssignment_2 )* ) )
            // InternalLayoutConfig.g:714:1: ( ( rule__ElkNode__PropertiesAssignment_2 )* )
            {
            // InternalLayoutConfig.g:714:1: ( ( rule__ElkNode__PropertiesAssignment_2 )* )
            // InternalLayoutConfig.g:715:1: ( rule__ElkNode__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2()); 
            // InternalLayoutConfig.g:716:1: ( rule__ElkNode__PropertiesAssignment_2 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalLayoutConfig.g:716:2: rule__ElkNode__PropertiesAssignment_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__ElkNode__PropertiesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
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
    // InternalLayoutConfig.g:726:1: rule__ElkNode__Group__3 : rule__ElkNode__Group__3__Impl rule__ElkNode__Group__4 ;
    public final void rule__ElkNode__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:730:1: ( rule__ElkNode__Group__3__Impl rule__ElkNode__Group__4 )
            // InternalLayoutConfig.g:731:2: rule__ElkNode__Group__3__Impl rule__ElkNode__Group__4
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
    // InternalLayoutConfig.g:738:1: rule__ElkNode__Group__3__Impl : ( ( rule__ElkNode__Group_3__0 )* ) ;
    public final void rule__ElkNode__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:742:1: ( ( ( rule__ElkNode__Group_3__0 )* ) )
            // InternalLayoutConfig.g:743:1: ( ( rule__ElkNode__Group_3__0 )* )
            {
            // InternalLayoutConfig.g:743:1: ( ( rule__ElkNode__Group_3__0 )* )
            // InternalLayoutConfig.g:744:1: ( rule__ElkNode__Group_3__0 )*
            {
             before(grammarAccess.getElkNodeAccess().getGroup_3()); 
            // InternalLayoutConfig.g:745:1: ( rule__ElkNode__Group_3__0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalLayoutConfig.g:745:2: rule__ElkNode__Group_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_8);
            	    rule__ElkNode__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
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
    // InternalLayoutConfig.g:755:1: rule__ElkNode__Group__4 : rule__ElkNode__Group__4__Impl ;
    public final void rule__ElkNode__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:759:1: ( rule__ElkNode__Group__4__Impl )
            // InternalLayoutConfig.g:760:2: rule__ElkNode__Group__4__Impl
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
    // InternalLayoutConfig.g:766:1: rule__ElkNode__Group__4__Impl : ( '}' ) ;
    public final void rule__ElkNode__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:770:1: ( ( '}' ) )
            // InternalLayoutConfig.g:771:1: ( '}' )
            {
            // InternalLayoutConfig.g:771:1: ( '}' )
            // InternalLayoutConfig.g:772:1: '}'
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
    // InternalLayoutConfig.g:795:1: rule__ElkNode__Group_3__0 : rule__ElkNode__Group_3__0__Impl rule__ElkNode__Group_3__1 ;
    public final void rule__ElkNode__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:799:1: ( rule__ElkNode__Group_3__0__Impl rule__ElkNode__Group_3__1 )
            // InternalLayoutConfig.g:800:2: rule__ElkNode__Group_3__0__Impl rule__ElkNode__Group_3__1
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
    // InternalLayoutConfig.g:807:1: rule__ElkNode__Group_3__0__Impl : ( 'ref' ) ;
    public final void rule__ElkNode__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:811:1: ( ( 'ref' ) )
            // InternalLayoutConfig.g:812:1: ( 'ref' )
            {
            // InternalLayoutConfig.g:812:1: ( 'ref' )
            // InternalLayoutConfig.g:813:1: 'ref'
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
    // InternalLayoutConfig.g:826:1: rule__ElkNode__Group_3__1 : rule__ElkNode__Group_3__1__Impl ;
    public final void rule__ElkNode__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:830:1: ( rule__ElkNode__Group_3__1__Impl )
            // InternalLayoutConfig.g:831:2: rule__ElkNode__Group_3__1__Impl
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
    // InternalLayoutConfig.g:837:1: rule__ElkNode__Group_3__1__Impl : ( ( rule__ElkNode__ChildrenAssignment_3_1 ) ) ;
    public final void rule__ElkNode__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:841:1: ( ( ( rule__ElkNode__ChildrenAssignment_3_1 ) ) )
            // InternalLayoutConfig.g:842:1: ( ( rule__ElkNode__ChildrenAssignment_3_1 ) )
            {
            // InternalLayoutConfig.g:842:1: ( ( rule__ElkNode__ChildrenAssignment_3_1 ) )
            // InternalLayoutConfig.g:843:1: ( rule__ElkNode__ChildrenAssignment_3_1 )
            {
             before(grammarAccess.getElkNodeAccess().getChildrenAssignment_3_1()); 
            // InternalLayoutConfig.g:844:1: ( rule__ElkNode__ChildrenAssignment_3_1 )
            // InternalLayoutConfig.g:844:2: rule__ElkNode__ChildrenAssignment_3_1
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
    // InternalLayoutConfig.g:858:1: rule__RefElkNode__Group__0 : rule__RefElkNode__Group__0__Impl rule__RefElkNode__Group__1 ;
    public final void rule__RefElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:862:1: ( rule__RefElkNode__Group__0__Impl rule__RefElkNode__Group__1 )
            // InternalLayoutConfig.g:863:2: rule__RefElkNode__Group__0__Impl rule__RefElkNode__Group__1
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
    // InternalLayoutConfig.g:870:1: rule__RefElkNode__Group__0__Impl : ( ( rule__RefElkNode__IdentifierAssignment_0 ) ) ;
    public final void rule__RefElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:874:1: ( ( ( rule__RefElkNode__IdentifierAssignment_0 ) ) )
            // InternalLayoutConfig.g:875:1: ( ( rule__RefElkNode__IdentifierAssignment_0 ) )
            {
            // InternalLayoutConfig.g:875:1: ( ( rule__RefElkNode__IdentifierAssignment_0 ) )
            // InternalLayoutConfig.g:876:1: ( rule__RefElkNode__IdentifierAssignment_0 )
            {
             before(grammarAccess.getRefElkNodeAccess().getIdentifierAssignment_0()); 
            // InternalLayoutConfig.g:877:1: ( rule__RefElkNode__IdentifierAssignment_0 )
            // InternalLayoutConfig.g:877:2: rule__RefElkNode__IdentifierAssignment_0
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
    // InternalLayoutConfig.g:887:1: rule__RefElkNode__Group__1 : rule__RefElkNode__Group__1__Impl rule__RefElkNode__Group__2 ;
    public final void rule__RefElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:891:1: ( rule__RefElkNode__Group__1__Impl rule__RefElkNode__Group__2 )
            // InternalLayoutConfig.g:892:2: rule__RefElkNode__Group__1__Impl rule__RefElkNode__Group__2
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
    // InternalLayoutConfig.g:899:1: rule__RefElkNode__Group__1__Impl : ( '{' ) ;
    public final void rule__RefElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:903:1: ( ( '{' ) )
            // InternalLayoutConfig.g:904:1: ( '{' )
            {
            // InternalLayoutConfig.g:904:1: ( '{' )
            // InternalLayoutConfig.g:905:1: '{'
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
    // InternalLayoutConfig.g:918:1: rule__RefElkNode__Group__2 : rule__RefElkNode__Group__2__Impl rule__RefElkNode__Group__3 ;
    public final void rule__RefElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:922:1: ( rule__RefElkNode__Group__2__Impl rule__RefElkNode__Group__3 )
            // InternalLayoutConfig.g:923:2: rule__RefElkNode__Group__2__Impl rule__RefElkNode__Group__3
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
    // InternalLayoutConfig.g:930:1: rule__RefElkNode__Group__2__Impl : ( ( rule__RefElkNode__PropertiesAssignment_2 )* ) ;
    public final void rule__RefElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:934:1: ( ( ( rule__RefElkNode__PropertiesAssignment_2 )* ) )
            // InternalLayoutConfig.g:935:1: ( ( rule__RefElkNode__PropertiesAssignment_2 )* )
            {
            // InternalLayoutConfig.g:935:1: ( ( rule__RefElkNode__PropertiesAssignment_2 )* )
            // InternalLayoutConfig.g:936:1: ( rule__RefElkNode__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getRefElkNodeAccess().getPropertiesAssignment_2()); 
            // InternalLayoutConfig.g:937:1: ( rule__RefElkNode__PropertiesAssignment_2 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalLayoutConfig.g:937:2: rule__RefElkNode__PropertiesAssignment_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__RefElkNode__PropertiesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
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
    // InternalLayoutConfig.g:947:1: rule__RefElkNode__Group__3 : rule__RefElkNode__Group__3__Impl ;
    public final void rule__RefElkNode__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:951:1: ( rule__RefElkNode__Group__3__Impl )
            // InternalLayoutConfig.g:952:2: rule__RefElkNode__Group__3__Impl
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
    // InternalLayoutConfig.g:958:1: rule__RefElkNode__Group__3__Impl : ( '}' ) ;
    public final void rule__RefElkNode__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:962:1: ( ( '}' ) )
            // InternalLayoutConfig.g:963:1: ( '}' )
            {
            // InternalLayoutConfig.g:963:1: ( '}' )
            // InternalLayoutConfig.g:964:1: '}'
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
    // InternalLayoutConfig.g:985:1: rule__ElkLabel__Group__0 : rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 ;
    public final void rule__ElkLabel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:989:1: ( rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 )
            // InternalLayoutConfig.g:990:2: rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1
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
    // InternalLayoutConfig.g:997:1: rule__ElkLabel__Group__0__Impl : ( 'label' ) ;
    public final void rule__ElkLabel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1001:1: ( ( 'label' ) )
            // InternalLayoutConfig.g:1002:1: ( 'label' )
            {
            // InternalLayoutConfig.g:1002:1: ( 'label' )
            // InternalLayoutConfig.g:1003:1: 'label'
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
    // InternalLayoutConfig.g:1016:1: rule__ElkLabel__Group__1 : rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 ;
    public final void rule__ElkLabel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1020:1: ( rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 )
            // InternalLayoutConfig.g:1021:2: rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2
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
    // InternalLayoutConfig.g:1028:1: rule__ElkLabel__Group__1__Impl : ( ( rule__ElkLabel__Group_1__0 )? ) ;
    public final void rule__ElkLabel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1032:1: ( ( ( rule__ElkLabel__Group_1__0 )? ) )
            // InternalLayoutConfig.g:1033:1: ( ( rule__ElkLabel__Group_1__0 )? )
            {
            // InternalLayoutConfig.g:1033:1: ( ( rule__ElkLabel__Group_1__0 )? )
            // InternalLayoutConfig.g:1034:1: ( rule__ElkLabel__Group_1__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_1()); 
            // InternalLayoutConfig.g:1035:1: ( rule__ElkLabel__Group_1__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalLayoutConfig.g:1035:2: rule__ElkLabel__Group_1__0
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
    // InternalLayoutConfig.g:1045:1: rule__ElkLabel__Group__2 : rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 ;
    public final void rule__ElkLabel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1049:1: ( rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 )
            // InternalLayoutConfig.g:1050:2: rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3
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
    // InternalLayoutConfig.g:1057:1: rule__ElkLabel__Group__2__Impl : ( ( rule__ElkLabel__TextAssignment_2 ) ) ;
    public final void rule__ElkLabel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1061:1: ( ( ( rule__ElkLabel__TextAssignment_2 ) ) )
            // InternalLayoutConfig.g:1062:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            {
            // InternalLayoutConfig.g:1062:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            // InternalLayoutConfig.g:1063:1: ( rule__ElkLabel__TextAssignment_2 )
            {
             before(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 
            // InternalLayoutConfig.g:1064:1: ( rule__ElkLabel__TextAssignment_2 )
            // InternalLayoutConfig.g:1064:2: rule__ElkLabel__TextAssignment_2
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
    // InternalLayoutConfig.g:1074:1: rule__ElkLabel__Group__3 : rule__ElkLabel__Group__3__Impl ;
    public final void rule__ElkLabel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1078:1: ( rule__ElkLabel__Group__3__Impl )
            // InternalLayoutConfig.g:1079:2: rule__ElkLabel__Group__3__Impl
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
    // InternalLayoutConfig.g:1085:1: rule__ElkLabel__Group__3__Impl : ( ( rule__ElkLabel__Group_3__0 )? ) ;
    public final void rule__ElkLabel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1089:1: ( ( ( rule__ElkLabel__Group_3__0 )? ) )
            // InternalLayoutConfig.g:1090:1: ( ( rule__ElkLabel__Group_3__0 )? )
            {
            // InternalLayoutConfig.g:1090:1: ( ( rule__ElkLabel__Group_3__0 )? )
            // InternalLayoutConfig.g:1091:1: ( rule__ElkLabel__Group_3__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_3()); 
            // InternalLayoutConfig.g:1092:1: ( rule__ElkLabel__Group_3__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==15) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLayoutConfig.g:1092:2: rule__ElkLabel__Group_3__0
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
    // InternalLayoutConfig.g:1110:1: rule__ElkLabel__Group_1__0 : rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 ;
    public final void rule__ElkLabel__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1114:1: ( rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 )
            // InternalLayoutConfig.g:1115:2: rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1
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
    // InternalLayoutConfig.g:1122:1: rule__ElkLabel__Group_1__0__Impl : ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkLabel__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1126:1: ( ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) )
            // InternalLayoutConfig.g:1127:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            {
            // InternalLayoutConfig.g:1127:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            // InternalLayoutConfig.g:1128:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 
            // InternalLayoutConfig.g:1129:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            // InternalLayoutConfig.g:1129:2: rule__ElkLabel__IdentifierAssignment_1_0
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
    // InternalLayoutConfig.g:1139:1: rule__ElkLabel__Group_1__1 : rule__ElkLabel__Group_1__1__Impl ;
    public final void rule__ElkLabel__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1143:1: ( rule__ElkLabel__Group_1__1__Impl )
            // InternalLayoutConfig.g:1144:2: rule__ElkLabel__Group_1__1__Impl
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
    // InternalLayoutConfig.g:1150:1: rule__ElkLabel__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkLabel__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1154:1: ( ( ':' ) )
            // InternalLayoutConfig.g:1155:1: ( ':' )
            {
            // InternalLayoutConfig.g:1155:1: ( ':' )
            // InternalLayoutConfig.g:1156:1: ':'
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
    // InternalLayoutConfig.g:1173:1: rule__ElkLabel__Group_3__0 : rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 ;
    public final void rule__ElkLabel__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1177:1: ( rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 )
            // InternalLayoutConfig.g:1178:2: rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1
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
    // InternalLayoutConfig.g:1185:1: rule__ElkLabel__Group_3__0__Impl : ( '{' ) ;
    public final void rule__ElkLabel__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1189:1: ( ( '{' ) )
            // InternalLayoutConfig.g:1190:1: ( '{' )
            {
            // InternalLayoutConfig.g:1190:1: ( '{' )
            // InternalLayoutConfig.g:1191:1: '{'
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
    // InternalLayoutConfig.g:1204:1: rule__ElkLabel__Group_3__1 : rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 ;
    public final void rule__ElkLabel__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1208:1: ( rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 )
            // InternalLayoutConfig.g:1209:2: rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2
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
    // InternalLayoutConfig.g:1216:1: rule__ElkLabel__Group_3__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkLabel__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1220:1: ( ( ( ruleShapeLayout )? ) )
            // InternalLayoutConfig.g:1221:1: ( ( ruleShapeLayout )? )
            {
            // InternalLayoutConfig.g:1221:1: ( ( ruleShapeLayout )? )
            // InternalLayoutConfig.g:1222:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 
            // InternalLayoutConfig.g:1223:1: ( ruleShapeLayout )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==20) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalLayoutConfig.g:1223:3: ruleShapeLayout
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
    // InternalLayoutConfig.g:1233:1: rule__ElkLabel__Group_3__2 : rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 ;
    public final void rule__ElkLabel__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1237:1: ( rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 )
            // InternalLayoutConfig.g:1238:2: rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3
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
    // InternalLayoutConfig.g:1245:1: rule__ElkLabel__Group_3__2__Impl : ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) ;
    public final void rule__ElkLabel__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1249:1: ( ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) )
            // InternalLayoutConfig.g:1250:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            {
            // InternalLayoutConfig.g:1250:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            // InternalLayoutConfig.g:1251:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 
            // InternalLayoutConfig.g:1252:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalLayoutConfig.g:1252:2: rule__ElkLabel__PropertiesAssignment_3_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__ElkLabel__PropertiesAssignment_3_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
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
    // InternalLayoutConfig.g:1262:1: rule__ElkLabel__Group_3__3 : rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 ;
    public final void rule__ElkLabel__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1266:1: ( rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 )
            // InternalLayoutConfig.g:1267:2: rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4
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
    // InternalLayoutConfig.g:1274:1: rule__ElkLabel__Group_3__3__Impl : ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) ;
    public final void rule__ElkLabel__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1278:1: ( ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) )
            // InternalLayoutConfig.g:1279:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            {
            // InternalLayoutConfig.g:1279:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            // InternalLayoutConfig.g:1280:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            {
             before(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 
            // InternalLayoutConfig.g:1281:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==18) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalLayoutConfig.g:1281:2: rule__ElkLabel__LabelsAssignment_3_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_13);
            	    rule__ElkLabel__LabelsAssignment_3_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
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
    // InternalLayoutConfig.g:1291:1: rule__ElkLabel__Group_3__4 : rule__ElkLabel__Group_3__4__Impl ;
    public final void rule__ElkLabel__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1295:1: ( rule__ElkLabel__Group_3__4__Impl )
            // InternalLayoutConfig.g:1296:2: rule__ElkLabel__Group_3__4__Impl
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
    // InternalLayoutConfig.g:1302:1: rule__ElkLabel__Group_3__4__Impl : ( '}' ) ;
    public final void rule__ElkLabel__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1306:1: ( ( '}' ) )
            // InternalLayoutConfig.g:1307:1: ( '}' )
            {
            // InternalLayoutConfig.g:1307:1: ( '}' )
            // InternalLayoutConfig.g:1308:1: '}'
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
    // InternalLayoutConfig.g:1333:1: rule__ShapeLayout__Group__0 : rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 ;
    public final void rule__ShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1337:1: ( rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 )
            // InternalLayoutConfig.g:1338:2: rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1
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
    // InternalLayoutConfig.g:1345:1: rule__ShapeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__ShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1349:1: ( ( 'layout' ) )
            // InternalLayoutConfig.g:1350:1: ( 'layout' )
            {
            // InternalLayoutConfig.g:1350:1: ( 'layout' )
            // InternalLayoutConfig.g:1351:1: 'layout'
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
    // InternalLayoutConfig.g:1364:1: rule__ShapeLayout__Group__1 : rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 ;
    public final void rule__ShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1368:1: ( rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 )
            // InternalLayoutConfig.g:1369:2: rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2
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
    // InternalLayoutConfig.g:1376:1: rule__ShapeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__ShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1380:1: ( ( '[' ) )
            // InternalLayoutConfig.g:1381:1: ( '[' )
            {
            // InternalLayoutConfig.g:1381:1: ( '[' )
            // InternalLayoutConfig.g:1382:1: '['
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
    // InternalLayoutConfig.g:1395:1: rule__ShapeLayout__Group__2 : rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 ;
    public final void rule__ShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1399:1: ( rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 )
            // InternalLayoutConfig.g:1400:2: rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3
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
    // InternalLayoutConfig.g:1407:1: rule__ShapeLayout__Group__2__Impl : ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) ;
    public final void rule__ShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1411:1: ( ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) )
            // InternalLayoutConfig.g:1412:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            {
            // InternalLayoutConfig.g:1412:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            // InternalLayoutConfig.g:1413:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 
            // InternalLayoutConfig.g:1414:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            // InternalLayoutConfig.g:1414:2: rule__ShapeLayout__UnorderedGroup_2
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
    // InternalLayoutConfig.g:1424:1: rule__ShapeLayout__Group__3 : rule__ShapeLayout__Group__3__Impl ;
    public final void rule__ShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1428:1: ( rule__ShapeLayout__Group__3__Impl )
            // InternalLayoutConfig.g:1429:2: rule__ShapeLayout__Group__3__Impl
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
    // InternalLayoutConfig.g:1435:1: rule__ShapeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__ShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1439:1: ( ( ']' ) )
            // InternalLayoutConfig.g:1440:1: ( ']' )
            {
            // InternalLayoutConfig.g:1440:1: ( ']' )
            // InternalLayoutConfig.g:1441:1: ']'
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
    // InternalLayoutConfig.g:1462:1: rule__ShapeLayout__Group_2_0__0 : rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 ;
    public final void rule__ShapeLayout__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1466:1: ( rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 )
            // InternalLayoutConfig.g:1467:2: rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1
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
    // InternalLayoutConfig.g:1474:1: rule__ShapeLayout__Group_2_0__0__Impl : ( 'position' ) ;
    public final void rule__ShapeLayout__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1478:1: ( ( 'position' ) )
            // InternalLayoutConfig.g:1479:1: ( 'position' )
            {
            // InternalLayoutConfig.g:1479:1: ( 'position' )
            // InternalLayoutConfig.g:1480:1: 'position'
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
    // InternalLayoutConfig.g:1493:1: rule__ShapeLayout__Group_2_0__1 : rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 ;
    public final void rule__ShapeLayout__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1497:1: ( rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 )
            // InternalLayoutConfig.g:1498:2: rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2
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
    // InternalLayoutConfig.g:1505:1: rule__ShapeLayout__Group_2_0__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1509:1: ( ( ':' ) )
            // InternalLayoutConfig.g:1510:1: ( ':' )
            {
            // InternalLayoutConfig.g:1510:1: ( ':' )
            // InternalLayoutConfig.g:1511:1: ':'
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
    // InternalLayoutConfig.g:1524:1: rule__ShapeLayout__Group_2_0__2 : rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 ;
    public final void rule__ShapeLayout__Group_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1528:1: ( rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 )
            // InternalLayoutConfig.g:1529:2: rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3
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
    // InternalLayoutConfig.g:1536:1: rule__ShapeLayout__Group_2_0__2__Impl : ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1540:1: ( ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) )
            // InternalLayoutConfig.g:1541:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            {
            // InternalLayoutConfig.g:1541:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            // InternalLayoutConfig.g:1542:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 
            // InternalLayoutConfig.g:1543:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            // InternalLayoutConfig.g:1543:2: rule__ShapeLayout__XAssignment_2_0_2
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
    // InternalLayoutConfig.g:1553:1: rule__ShapeLayout__Group_2_0__3 : rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 ;
    public final void rule__ShapeLayout__Group_2_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1557:1: ( rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 )
            // InternalLayoutConfig.g:1558:2: rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4
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
    // InternalLayoutConfig.g:1565:1: rule__ShapeLayout__Group_2_0__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1569:1: ( ( ',' ) )
            // InternalLayoutConfig.g:1570:1: ( ',' )
            {
            // InternalLayoutConfig.g:1570:1: ( ',' )
            // InternalLayoutConfig.g:1571:1: ','
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
    // InternalLayoutConfig.g:1584:1: rule__ShapeLayout__Group_2_0__4 : rule__ShapeLayout__Group_2_0__4__Impl ;
    public final void rule__ShapeLayout__Group_2_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1588:1: ( rule__ShapeLayout__Group_2_0__4__Impl )
            // InternalLayoutConfig.g:1589:2: rule__ShapeLayout__Group_2_0__4__Impl
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
    // InternalLayoutConfig.g:1595:1: rule__ShapeLayout__Group_2_0__4__Impl : ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1599:1: ( ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) )
            // InternalLayoutConfig.g:1600:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            {
            // InternalLayoutConfig.g:1600:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            // InternalLayoutConfig.g:1601:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 
            // InternalLayoutConfig.g:1602:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            // InternalLayoutConfig.g:1602:2: rule__ShapeLayout__YAssignment_2_0_4
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
    // InternalLayoutConfig.g:1622:1: rule__ShapeLayout__Group_2_1__0 : rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 ;
    public final void rule__ShapeLayout__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1626:1: ( rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 )
            // InternalLayoutConfig.g:1627:2: rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1
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
    // InternalLayoutConfig.g:1634:1: rule__ShapeLayout__Group_2_1__0__Impl : ( 'size' ) ;
    public final void rule__ShapeLayout__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1638:1: ( ( 'size' ) )
            // InternalLayoutConfig.g:1639:1: ( 'size' )
            {
            // InternalLayoutConfig.g:1639:1: ( 'size' )
            // InternalLayoutConfig.g:1640:1: 'size'
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
    // InternalLayoutConfig.g:1653:1: rule__ShapeLayout__Group_2_1__1 : rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 ;
    public final void rule__ShapeLayout__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1657:1: ( rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 )
            // InternalLayoutConfig.g:1658:2: rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2
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
    // InternalLayoutConfig.g:1665:1: rule__ShapeLayout__Group_2_1__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1669:1: ( ( ':' ) )
            // InternalLayoutConfig.g:1670:1: ( ':' )
            {
            // InternalLayoutConfig.g:1670:1: ( ':' )
            // InternalLayoutConfig.g:1671:1: ':'
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
    // InternalLayoutConfig.g:1684:1: rule__ShapeLayout__Group_2_1__2 : rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 ;
    public final void rule__ShapeLayout__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1688:1: ( rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 )
            // InternalLayoutConfig.g:1689:2: rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3
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
    // InternalLayoutConfig.g:1696:1: rule__ShapeLayout__Group_2_1__2__Impl : ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1700:1: ( ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) )
            // InternalLayoutConfig.g:1701:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            {
            // InternalLayoutConfig.g:1701:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            // InternalLayoutConfig.g:1702:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 
            // InternalLayoutConfig.g:1703:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            // InternalLayoutConfig.g:1703:2: rule__ShapeLayout__WidthAssignment_2_1_2
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
    // InternalLayoutConfig.g:1713:1: rule__ShapeLayout__Group_2_1__3 : rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 ;
    public final void rule__ShapeLayout__Group_2_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1717:1: ( rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 )
            // InternalLayoutConfig.g:1718:2: rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4
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
    // InternalLayoutConfig.g:1725:1: rule__ShapeLayout__Group_2_1__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1729:1: ( ( ',' ) )
            // InternalLayoutConfig.g:1730:1: ( ',' )
            {
            // InternalLayoutConfig.g:1730:1: ( ',' )
            // InternalLayoutConfig.g:1731:1: ','
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
    // InternalLayoutConfig.g:1744:1: rule__ShapeLayout__Group_2_1__4 : rule__ShapeLayout__Group_2_1__4__Impl ;
    public final void rule__ShapeLayout__Group_2_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1748:1: ( rule__ShapeLayout__Group_2_1__4__Impl )
            // InternalLayoutConfig.g:1749:2: rule__ShapeLayout__Group_2_1__4__Impl
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
    // InternalLayoutConfig.g:1755:1: rule__ShapeLayout__Group_2_1__4__Impl : ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1759:1: ( ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) )
            // InternalLayoutConfig.g:1760:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            {
            // InternalLayoutConfig.g:1760:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            // InternalLayoutConfig.g:1761:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 
            // InternalLayoutConfig.g:1762:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            // InternalLayoutConfig.g:1762:2: rule__ShapeLayout__HeightAssignment_2_1_4
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
    // InternalLayoutConfig.g:1787:1: rule__EdgeLayout__Group__0 : rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 ;
    public final void rule__EdgeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1791:1: ( rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 )
            // InternalLayoutConfig.g:1792:2: rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1
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
    // InternalLayoutConfig.g:1799:1: rule__EdgeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__EdgeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1803:1: ( ( 'layout' ) )
            // InternalLayoutConfig.g:1804:1: ( 'layout' )
            {
            // InternalLayoutConfig.g:1804:1: ( 'layout' )
            // InternalLayoutConfig.g:1805:1: 'layout'
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
    // InternalLayoutConfig.g:1818:1: rule__EdgeLayout__Group__1 : rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 ;
    public final void rule__EdgeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1822:1: ( rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 )
            // InternalLayoutConfig.g:1823:2: rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2
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
    // InternalLayoutConfig.g:1830:1: rule__EdgeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__EdgeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1834:1: ( ( '[' ) )
            // InternalLayoutConfig.g:1835:1: ( '[' )
            {
            // InternalLayoutConfig.g:1835:1: ( '[' )
            // InternalLayoutConfig.g:1836:1: '['
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
    // InternalLayoutConfig.g:1849:1: rule__EdgeLayout__Group__2 : rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 ;
    public final void rule__EdgeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1853:1: ( rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 )
            // InternalLayoutConfig.g:1854:2: rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3
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
    // InternalLayoutConfig.g:1861:1: rule__EdgeLayout__Group__2__Impl : ( ( rule__EdgeLayout__Alternatives_2 ) ) ;
    public final void rule__EdgeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1865:1: ( ( ( rule__EdgeLayout__Alternatives_2 ) ) )
            // InternalLayoutConfig.g:1866:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            {
            // InternalLayoutConfig.g:1866:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            // InternalLayoutConfig.g:1867:1: ( rule__EdgeLayout__Alternatives_2 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 
            // InternalLayoutConfig.g:1868:1: ( rule__EdgeLayout__Alternatives_2 )
            // InternalLayoutConfig.g:1868:2: rule__EdgeLayout__Alternatives_2
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
    // InternalLayoutConfig.g:1878:1: rule__EdgeLayout__Group__3 : rule__EdgeLayout__Group__3__Impl ;
    public final void rule__EdgeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1882:1: ( rule__EdgeLayout__Group__3__Impl )
            // InternalLayoutConfig.g:1883:2: rule__EdgeLayout__Group__3__Impl
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
    // InternalLayoutConfig.g:1889:1: rule__EdgeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__EdgeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1893:1: ( ( ']' ) )
            // InternalLayoutConfig.g:1894:1: ( ']' )
            {
            // InternalLayoutConfig.g:1894:1: ( ']' )
            // InternalLayoutConfig.g:1895:1: ']'
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
    // InternalLayoutConfig.g:1916:1: rule__ElkSingleEdgeSection__Group__0 : rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 ;
    public final void rule__ElkSingleEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1920:1: ( rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 )
            // InternalLayoutConfig.g:1921:2: rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1
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
    // InternalLayoutConfig.g:1928:1: rule__ElkSingleEdgeSection__Group__0__Impl : ( () ) ;
    public final void rule__ElkSingleEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1932:1: ( ( () ) )
            // InternalLayoutConfig.g:1933:1: ( () )
            {
            // InternalLayoutConfig.g:1933:1: ( () )
            // InternalLayoutConfig.g:1934:1: ()
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 
            // InternalLayoutConfig.g:1935:1: ()
            // InternalLayoutConfig.g:1937:1: 
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
    // InternalLayoutConfig.g:1947:1: rule__ElkSingleEdgeSection__Group__1 : rule__ElkSingleEdgeSection__Group__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1951:1: ( rule__ElkSingleEdgeSection__Group__1__Impl )
            // InternalLayoutConfig.g:1952:2: rule__ElkSingleEdgeSection__Group__1__Impl
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
    // InternalLayoutConfig.g:1958:1: rule__ElkSingleEdgeSection__Group__1__Impl : ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1962:1: ( ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) ) )
            // InternalLayoutConfig.g:1963:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) )
            {
            // InternalLayoutConfig.g:1963:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1 ) )
            // InternalLayoutConfig.g:1964:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1()); 
            // InternalLayoutConfig.g:1965:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1 )
            // InternalLayoutConfig.g:1965:2: rule__ElkSingleEdgeSection__UnorderedGroup_1
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
    // InternalLayoutConfig.g:1979:1: rule__ElkSingleEdgeSection__Group_1_0__0 : rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1983:1: ( rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1 )
            // InternalLayoutConfig.g:1984:2: rule__ElkSingleEdgeSection__Group_1_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0__1
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
    // InternalLayoutConfig.g:1991:1: rule__ElkSingleEdgeSection__Group_1_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:1995:1: ( ( 'incoming' ) )
            // InternalLayoutConfig.g:1996:1: ( 'incoming' )
            {
            // InternalLayoutConfig.g:1996:1: ( 'incoming' )
            // InternalLayoutConfig.g:1997:1: 'incoming'
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
    // InternalLayoutConfig.g:2010:1: rule__ElkSingleEdgeSection__Group_1_0__1 : rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2014:1: ( rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2 )
            // InternalLayoutConfig.g:2015:2: rule__ElkSingleEdgeSection__Group_1_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0__2
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
    // InternalLayoutConfig.g:2022:1: rule__ElkSingleEdgeSection__Group_1_0__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2026:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2027:1: ( ':' )
            {
            // InternalLayoutConfig.g:2027:1: ( ':' )
            // InternalLayoutConfig.g:2028:1: ':'
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
    // InternalLayoutConfig.g:2041:1: rule__ElkSingleEdgeSection__Group_1_0__2 : rule__ElkSingleEdgeSection__Group_1_0__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2045:1: ( rule__ElkSingleEdgeSection__Group_1_0__2__Impl )
            // InternalLayoutConfig.g:2046:2: rule__ElkSingleEdgeSection__Group_1_0__2__Impl
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
    // InternalLayoutConfig.g:2052:1: rule__ElkSingleEdgeSection__Group_1_0__2__Impl : ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2056:1: ( ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) ) )
            // InternalLayoutConfig.g:2057:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) )
            {
            // InternalLayoutConfig.g:2057:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 ) )
            // InternalLayoutConfig.g:2058:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_2()); 
            // InternalLayoutConfig.g:2059:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 )
            // InternalLayoutConfig.g:2059:2: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2
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
    // InternalLayoutConfig.g:2075:1: rule__ElkSingleEdgeSection__Group_1_1__0 : rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2079:1: ( rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 )
            // InternalLayoutConfig.g:2080:2: rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1
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
    // InternalLayoutConfig.g:2087:1: rule__ElkSingleEdgeSection__Group_1_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2091:1: ( ( 'outgoing' ) )
            // InternalLayoutConfig.g:2092:1: ( 'outgoing' )
            {
            // InternalLayoutConfig.g:2092:1: ( 'outgoing' )
            // InternalLayoutConfig.g:2093:1: 'outgoing'
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
    // InternalLayoutConfig.g:2106:1: rule__ElkSingleEdgeSection__Group_1_1__1 : rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2110:1: ( rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 )
            // InternalLayoutConfig.g:2111:2: rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2
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
    // InternalLayoutConfig.g:2118:1: rule__ElkSingleEdgeSection__Group_1_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2122:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2123:1: ( ':' )
            {
            // InternalLayoutConfig.g:2123:1: ( ':' )
            // InternalLayoutConfig.g:2124:1: ':'
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
    // InternalLayoutConfig.g:2137:1: rule__ElkSingleEdgeSection__Group_1_1__2 : rule__ElkSingleEdgeSection__Group_1_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2141:1: ( rule__ElkSingleEdgeSection__Group_1_1__2__Impl )
            // InternalLayoutConfig.g:2142:2: rule__ElkSingleEdgeSection__Group_1_1__2__Impl
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
    // InternalLayoutConfig.g:2148:1: rule__ElkSingleEdgeSection__Group_1_1__2__Impl : ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2152:1: ( ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) ) )
            // InternalLayoutConfig.g:2153:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) )
            {
            // InternalLayoutConfig.g:2153:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 ) )
            // InternalLayoutConfig.g:2154:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_1_2()); 
            // InternalLayoutConfig.g:2155:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 )
            // InternalLayoutConfig.g:2155:2: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2
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
    // InternalLayoutConfig.g:2171:1: rule__ElkSingleEdgeSection__Group_1_2__0 : rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2175:1: ( rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1 )
            // InternalLayoutConfig.g:2176:2: rule__ElkSingleEdgeSection__Group_1_2__0__Impl rule__ElkSingleEdgeSection__Group_1_2__1
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
    // InternalLayoutConfig.g:2183:1: rule__ElkSingleEdgeSection__Group_1_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2187:1: ( ( 'start' ) )
            // InternalLayoutConfig.g:2188:1: ( 'start' )
            {
            // InternalLayoutConfig.g:2188:1: ( 'start' )
            // InternalLayoutConfig.g:2189:1: 'start'
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
    // InternalLayoutConfig.g:2202:1: rule__ElkSingleEdgeSection__Group_1_2__1 : rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2206:1: ( rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2 )
            // InternalLayoutConfig.g:2207:2: rule__ElkSingleEdgeSection__Group_1_2__1__Impl rule__ElkSingleEdgeSection__Group_1_2__2
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
    // InternalLayoutConfig.g:2214:1: rule__ElkSingleEdgeSection__Group_1_2__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2218:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2219:1: ( ':' )
            {
            // InternalLayoutConfig.g:2219:1: ( ':' )
            // InternalLayoutConfig.g:2220:1: ':'
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
    // InternalLayoutConfig.g:2233:1: rule__ElkSingleEdgeSection__Group_1_2__2 : rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2237:1: ( rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3 )
            // InternalLayoutConfig.g:2238:2: rule__ElkSingleEdgeSection__Group_1_2__2__Impl rule__ElkSingleEdgeSection__Group_1_2__3
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
    // InternalLayoutConfig.g:2245:1: rule__ElkSingleEdgeSection__Group_1_2__2__Impl : ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2249:1: ( ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) ) )
            // InternalLayoutConfig.g:2250:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) )
            {
            // InternalLayoutConfig.g:2250:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 ) )
            // InternalLayoutConfig.g:2251:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_2_2()); 
            // InternalLayoutConfig.g:2252:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 )
            // InternalLayoutConfig.g:2252:2: rule__ElkSingleEdgeSection__StartXAssignment_1_2_2
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
    // InternalLayoutConfig.g:2262:1: rule__ElkSingleEdgeSection__Group_1_2__3 : rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2266:1: ( rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4 )
            // InternalLayoutConfig.g:2267:2: rule__ElkSingleEdgeSection__Group_1_2__3__Impl rule__ElkSingleEdgeSection__Group_1_2__4
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
    // InternalLayoutConfig.g:2274:1: rule__ElkSingleEdgeSection__Group_1_2__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2278:1: ( ( ',' ) )
            // InternalLayoutConfig.g:2279:1: ( ',' )
            {
            // InternalLayoutConfig.g:2279:1: ( ',' )
            // InternalLayoutConfig.g:2280:1: ','
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
    // InternalLayoutConfig.g:2293:1: rule__ElkSingleEdgeSection__Group_1_2__4 : rule__ElkSingleEdgeSection__Group_1_2__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2297:1: ( rule__ElkSingleEdgeSection__Group_1_2__4__Impl )
            // InternalLayoutConfig.g:2298:2: rule__ElkSingleEdgeSection__Group_1_2__4__Impl
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
    // InternalLayoutConfig.g:2304:1: rule__ElkSingleEdgeSection__Group_1_2__4__Impl : ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2308:1: ( ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) ) )
            // InternalLayoutConfig.g:2309:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) )
            {
            // InternalLayoutConfig.g:2309:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 ) )
            // InternalLayoutConfig.g:2310:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_2_4()); 
            // InternalLayoutConfig.g:2311:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 )
            // InternalLayoutConfig.g:2311:2: rule__ElkSingleEdgeSection__StartYAssignment_1_2_4
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
    // InternalLayoutConfig.g:2331:1: rule__ElkSingleEdgeSection__Group_1_3__0 : rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2335:1: ( rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1 )
            // InternalLayoutConfig.g:2336:2: rule__ElkSingleEdgeSection__Group_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_3__1
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
    // InternalLayoutConfig.g:2343:1: rule__ElkSingleEdgeSection__Group_1_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2347:1: ( ( 'end' ) )
            // InternalLayoutConfig.g:2348:1: ( 'end' )
            {
            // InternalLayoutConfig.g:2348:1: ( 'end' )
            // InternalLayoutConfig.g:2349:1: 'end'
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
    // InternalLayoutConfig.g:2362:1: rule__ElkSingleEdgeSection__Group_1_3__1 : rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2366:1: ( rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2 )
            // InternalLayoutConfig.g:2367:2: rule__ElkSingleEdgeSection__Group_1_3__1__Impl rule__ElkSingleEdgeSection__Group_1_3__2
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
    // InternalLayoutConfig.g:2374:1: rule__ElkSingleEdgeSection__Group_1_3__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2378:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2379:1: ( ':' )
            {
            // InternalLayoutConfig.g:2379:1: ( ':' )
            // InternalLayoutConfig.g:2380:1: ':'
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
    // InternalLayoutConfig.g:2393:1: rule__ElkSingleEdgeSection__Group_1_3__2 : rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2397:1: ( rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3 )
            // InternalLayoutConfig.g:2398:2: rule__ElkSingleEdgeSection__Group_1_3__2__Impl rule__ElkSingleEdgeSection__Group_1_3__3
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
    // InternalLayoutConfig.g:2405:1: rule__ElkSingleEdgeSection__Group_1_3__2__Impl : ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2409:1: ( ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) ) )
            // InternalLayoutConfig.g:2410:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) )
            {
            // InternalLayoutConfig.g:2410:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 ) )
            // InternalLayoutConfig.g:2411:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_3_2()); 
            // InternalLayoutConfig.g:2412:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 )
            // InternalLayoutConfig.g:2412:2: rule__ElkSingleEdgeSection__EndXAssignment_1_3_2
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
    // InternalLayoutConfig.g:2422:1: rule__ElkSingleEdgeSection__Group_1_3__3 : rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2426:1: ( rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4 )
            // InternalLayoutConfig.g:2427:2: rule__ElkSingleEdgeSection__Group_1_3__3__Impl rule__ElkSingleEdgeSection__Group_1_3__4
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
    // InternalLayoutConfig.g:2434:1: rule__ElkSingleEdgeSection__Group_1_3__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2438:1: ( ( ',' ) )
            // InternalLayoutConfig.g:2439:1: ( ',' )
            {
            // InternalLayoutConfig.g:2439:1: ( ',' )
            // InternalLayoutConfig.g:2440:1: ','
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
    // InternalLayoutConfig.g:2453:1: rule__ElkSingleEdgeSection__Group_1_3__4 : rule__ElkSingleEdgeSection__Group_1_3__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2457:1: ( rule__ElkSingleEdgeSection__Group_1_3__4__Impl )
            // InternalLayoutConfig.g:2458:2: rule__ElkSingleEdgeSection__Group_1_3__4__Impl
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
    // InternalLayoutConfig.g:2464:1: rule__ElkSingleEdgeSection__Group_1_3__4__Impl : ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2468:1: ( ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) ) )
            // InternalLayoutConfig.g:2469:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) )
            {
            // InternalLayoutConfig.g:2469:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 ) )
            // InternalLayoutConfig.g:2470:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_3_4()); 
            // InternalLayoutConfig.g:2471:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 )
            // InternalLayoutConfig.g:2471:2: rule__ElkSingleEdgeSection__EndYAssignment_1_3_4
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
    // InternalLayoutConfig.g:2491:1: rule__ElkSingleEdgeSection__Group_1_4__0 : rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2495:1: ( rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1 )
            // InternalLayoutConfig.g:2496:2: rule__ElkSingleEdgeSection__Group_1_4__0__Impl rule__ElkSingleEdgeSection__Group_1_4__1
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
    // InternalLayoutConfig.g:2503:1: rule__ElkSingleEdgeSection__Group_1_4__0__Impl : ( 'bends' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2507:1: ( ( 'bends' ) )
            // InternalLayoutConfig.g:2508:1: ( 'bends' )
            {
            // InternalLayoutConfig.g:2508:1: ( 'bends' )
            // InternalLayoutConfig.g:2509:1: 'bends'
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
    // InternalLayoutConfig.g:2522:1: rule__ElkSingleEdgeSection__Group_1_4__1 : rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2526:1: ( rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2 )
            // InternalLayoutConfig.g:2527:2: rule__ElkSingleEdgeSection__Group_1_4__1__Impl rule__ElkSingleEdgeSection__Group_1_4__2
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
    // InternalLayoutConfig.g:2534:1: rule__ElkSingleEdgeSection__Group_1_4__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2538:1: ( ( ':' ) )
            // InternalLayoutConfig.g:2539:1: ( ':' )
            {
            // InternalLayoutConfig.g:2539:1: ( ':' )
            // InternalLayoutConfig.g:2540:1: ':'
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
    // InternalLayoutConfig.g:2553:1: rule__ElkSingleEdgeSection__Group_1_4__2 : rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2557:1: ( rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3 )
            // InternalLayoutConfig.g:2558:2: rule__ElkSingleEdgeSection__Group_1_4__2__Impl rule__ElkSingleEdgeSection__Group_1_4__3
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
    // InternalLayoutConfig.g:2565:1: rule__ElkSingleEdgeSection__Group_1_4__2__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2569:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) ) )
            // InternalLayoutConfig.g:2570:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) )
            {
            // InternalLayoutConfig.g:2570:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 ) )
            // InternalLayoutConfig.g:2571:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_2()); 
            // InternalLayoutConfig.g:2572:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 )
            // InternalLayoutConfig.g:2572:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2
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
    // InternalLayoutConfig.g:2582:1: rule__ElkSingleEdgeSection__Group_1_4__3 : rule__ElkSingleEdgeSection__Group_1_4__3__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2586:1: ( rule__ElkSingleEdgeSection__Group_1_4__3__Impl )
            // InternalLayoutConfig.g:2587:2: rule__ElkSingleEdgeSection__Group_1_4__3__Impl
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
    // InternalLayoutConfig.g:2593:1: rule__ElkSingleEdgeSection__Group_1_4__3__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2597:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* ) )
            // InternalLayoutConfig.g:2598:1: ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* )
            {
            // InternalLayoutConfig.g:2598:1: ( ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )* )
            // InternalLayoutConfig.g:2599:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4_3()); 
            // InternalLayoutConfig.g:2600:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==31) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalLayoutConfig.g:2600:2: rule__ElkSingleEdgeSection__Group_1_4_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_22);
            	    rule__ElkSingleEdgeSection__Group_1_4_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
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
    // InternalLayoutConfig.g:2618:1: rule__ElkSingleEdgeSection__Group_1_4_3__0 : rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2622:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1 )
            // InternalLayoutConfig.g:2623:2: rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl rule__ElkSingleEdgeSection__Group_1_4_3__1
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
    // InternalLayoutConfig.g:2630:1: rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl : ( '|' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2634:1: ( ( '|' ) )
            // InternalLayoutConfig.g:2635:1: ( '|' )
            {
            // InternalLayoutConfig.g:2635:1: ( '|' )
            // InternalLayoutConfig.g:2636:1: '|'
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
    // InternalLayoutConfig.g:2649:1: rule__ElkSingleEdgeSection__Group_1_4_3__1 : rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2653:1: ( rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl )
            // InternalLayoutConfig.g:2654:2: rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl
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
    // InternalLayoutConfig.g:2660:1: rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2664:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) ) )
            // InternalLayoutConfig.g:2665:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) )
            {
            // InternalLayoutConfig.g:2665:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 ) )
            // InternalLayoutConfig.g:2666:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_4_3_1()); 
            // InternalLayoutConfig.g:2667:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 )
            // InternalLayoutConfig.g:2667:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1
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
    // InternalLayoutConfig.g:2681:1: rule__ElkEdgeSection__Group__0 : rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 ;
    public final void rule__ElkEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2685:1: ( rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 )
            // InternalLayoutConfig.g:2686:2: rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1
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
    // InternalLayoutConfig.g:2693:1: rule__ElkEdgeSection__Group__0__Impl : ( 'section' ) ;
    public final void rule__ElkEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2697:1: ( ( 'section' ) )
            // InternalLayoutConfig.g:2698:1: ( 'section' )
            {
            // InternalLayoutConfig.g:2698:1: ( 'section' )
            // InternalLayoutConfig.g:2699:1: 'section'
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
    // InternalLayoutConfig.g:2712:1: rule__ElkEdgeSection__Group__1 : rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 ;
    public final void rule__ElkEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2716:1: ( rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 )
            // InternalLayoutConfig.g:2717:2: rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2
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
    // InternalLayoutConfig.g:2724:1: rule__ElkEdgeSection__Group__1__Impl : ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2728:1: ( ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) )
            // InternalLayoutConfig.g:2729:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            {
            // InternalLayoutConfig.g:2729:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            // InternalLayoutConfig.g:2730:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 
            // InternalLayoutConfig.g:2731:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            // InternalLayoutConfig.g:2731:2: rule__ElkEdgeSection__IdentifierAssignment_1
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
    // InternalLayoutConfig.g:2741:1: rule__ElkEdgeSection__Group__2 : rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 ;
    public final void rule__ElkEdgeSection__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2745:1: ( rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 )
            // InternalLayoutConfig.g:2746:2: rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3
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
    // InternalLayoutConfig.g:2753:1: rule__ElkEdgeSection__Group__2__Impl : ( ( rule__ElkEdgeSection__Group_2__0 )? ) ;
    public final void rule__ElkEdgeSection__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2757:1: ( ( ( rule__ElkEdgeSection__Group_2__0 )? ) )
            // InternalLayoutConfig.g:2758:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            {
            // InternalLayoutConfig.g:2758:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            // InternalLayoutConfig.g:2759:1: ( rule__ElkEdgeSection__Group_2__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 
            // InternalLayoutConfig.g:2760:1: ( rule__ElkEdgeSection__Group_2__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==33) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalLayoutConfig.g:2760:2: rule__ElkEdgeSection__Group_2__0
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
    // InternalLayoutConfig.g:2770:1: rule__ElkEdgeSection__Group__3 : rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 ;
    public final void rule__ElkEdgeSection__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2774:1: ( rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 )
            // InternalLayoutConfig.g:2775:2: rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4
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
    // InternalLayoutConfig.g:2782:1: rule__ElkEdgeSection__Group__3__Impl : ( '[' ) ;
    public final void rule__ElkEdgeSection__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2786:1: ( ( '[' ) )
            // InternalLayoutConfig.g:2787:1: ( '[' )
            {
            // InternalLayoutConfig.g:2787:1: ( '[' )
            // InternalLayoutConfig.g:2788:1: '['
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
    // InternalLayoutConfig.g:2801:1: rule__ElkEdgeSection__Group__4 : rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 ;
    public final void rule__ElkEdgeSection__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2805:1: ( rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 )
            // InternalLayoutConfig.g:2806:2: rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5
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
    // InternalLayoutConfig.g:2813:1: rule__ElkEdgeSection__Group__4__Impl : ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) ) ;
    public final void rule__ElkEdgeSection__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2817:1: ( ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) ) )
            // InternalLayoutConfig.g:2818:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) )
            {
            // InternalLayoutConfig.g:2818:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4 ) )
            // InternalLayoutConfig.g:2819:1: ( rule__ElkEdgeSection__UnorderedGroup_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4()); 
            // InternalLayoutConfig.g:2820:1: ( rule__ElkEdgeSection__UnorderedGroup_4 )
            // InternalLayoutConfig.g:2820:2: rule__ElkEdgeSection__UnorderedGroup_4
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
    // InternalLayoutConfig.g:2830:1: rule__ElkEdgeSection__Group__5 : rule__ElkEdgeSection__Group__5__Impl ;
    public final void rule__ElkEdgeSection__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2834:1: ( rule__ElkEdgeSection__Group__5__Impl )
            // InternalLayoutConfig.g:2835:2: rule__ElkEdgeSection__Group__5__Impl
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
    // InternalLayoutConfig.g:2841:1: rule__ElkEdgeSection__Group__5__Impl : ( ']' ) ;
    public final void rule__ElkEdgeSection__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2845:1: ( ( ']' ) )
            // InternalLayoutConfig.g:2846:1: ( ']' )
            {
            // InternalLayoutConfig.g:2846:1: ( ']' )
            // InternalLayoutConfig.g:2847:1: ']'
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
    // InternalLayoutConfig.g:2872:1: rule__ElkEdgeSection__Group_2__0 : rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 ;
    public final void rule__ElkEdgeSection__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2876:1: ( rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 )
            // InternalLayoutConfig.g:2877:2: rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1
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
    // InternalLayoutConfig.g:2884:1: rule__ElkEdgeSection__Group_2__0__Impl : ( '->' ) ;
    public final void rule__ElkEdgeSection__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2888:1: ( ( '->' ) )
            // InternalLayoutConfig.g:2889:1: ( '->' )
            {
            // InternalLayoutConfig.g:2889:1: ( '->' )
            // InternalLayoutConfig.g:2890:1: '->'
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
    // InternalLayoutConfig.g:2903:1: rule__ElkEdgeSection__Group_2__1 : rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 ;
    public final void rule__ElkEdgeSection__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2907:1: ( rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 )
            // InternalLayoutConfig.g:2908:2: rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2
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
    // InternalLayoutConfig.g:2915:1: rule__ElkEdgeSection__Group_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2919:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) )
            // InternalLayoutConfig.g:2920:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            {
            // InternalLayoutConfig.g:2920:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            // InternalLayoutConfig.g:2921:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 
            // InternalLayoutConfig.g:2922:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            // InternalLayoutConfig.g:2922:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1
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
    // InternalLayoutConfig.g:2932:1: rule__ElkEdgeSection__Group_2__2 : rule__ElkEdgeSection__Group_2__2__Impl ;
    public final void rule__ElkEdgeSection__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2936:1: ( rule__ElkEdgeSection__Group_2__2__Impl )
            // InternalLayoutConfig.g:2937:2: rule__ElkEdgeSection__Group_2__2__Impl
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
    // InternalLayoutConfig.g:2943:1: rule__ElkEdgeSection__Group_2__2__Impl : ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2947:1: ( ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) )
            // InternalLayoutConfig.g:2948:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            {
            // InternalLayoutConfig.g:2948:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            // InternalLayoutConfig.g:2949:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 
            // InternalLayoutConfig.g:2950:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==24) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalLayoutConfig.g:2950:2: rule__ElkEdgeSection__Group_2_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_24);
            	    rule__ElkEdgeSection__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
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
    // InternalLayoutConfig.g:2966:1: rule__ElkEdgeSection__Group_2_2__0 : rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 ;
    public final void rule__ElkEdgeSection__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2970:1: ( rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 )
            // InternalLayoutConfig.g:2971:2: rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1
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
    // InternalLayoutConfig.g:2978:1: rule__ElkEdgeSection__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:2982:1: ( ( ',' ) )
            // InternalLayoutConfig.g:2983:1: ( ',' )
            {
            // InternalLayoutConfig.g:2983:1: ( ',' )
            // InternalLayoutConfig.g:2984:1: ','
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
    // InternalLayoutConfig.g:2997:1: rule__ElkEdgeSection__Group_2_2__1 : rule__ElkEdgeSection__Group_2_2__1__Impl ;
    public final void rule__ElkEdgeSection__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3001:1: ( rule__ElkEdgeSection__Group_2_2__1__Impl )
            // InternalLayoutConfig.g:3002:2: rule__ElkEdgeSection__Group_2_2__1__Impl
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
    // InternalLayoutConfig.g:3008:1: rule__ElkEdgeSection__Group_2_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3012:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) )
            // InternalLayoutConfig.g:3013:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            {
            // InternalLayoutConfig.g:3013:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            // InternalLayoutConfig.g:3014:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 
            // InternalLayoutConfig.g:3015:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            // InternalLayoutConfig.g:3015:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1
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
    // InternalLayoutConfig.g:3029:1: rule__ElkEdgeSection__Group_4_0__0 : rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1 ;
    public final void rule__ElkEdgeSection__Group_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3033:1: ( rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1 )
            // InternalLayoutConfig.g:3034:2: rule__ElkEdgeSection__Group_4_0__0__Impl rule__ElkEdgeSection__Group_4_0__1
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
    // InternalLayoutConfig.g:3041:1: rule__ElkEdgeSection__Group_4_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkEdgeSection__Group_4_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3045:1: ( ( 'incoming' ) )
            // InternalLayoutConfig.g:3046:1: ( 'incoming' )
            {
            // InternalLayoutConfig.g:3046:1: ( 'incoming' )
            // InternalLayoutConfig.g:3047:1: 'incoming'
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
    // InternalLayoutConfig.g:3060:1: rule__ElkEdgeSection__Group_4_0__1 : rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2 ;
    public final void rule__ElkEdgeSection__Group_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3064:1: ( rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2 )
            // InternalLayoutConfig.g:3065:2: rule__ElkEdgeSection__Group_4_0__1__Impl rule__ElkEdgeSection__Group_4_0__2
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
    // InternalLayoutConfig.g:3072:1: rule__ElkEdgeSection__Group_4_0__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3076:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3077:1: ( ':' )
            {
            // InternalLayoutConfig.g:3077:1: ( ':' )
            // InternalLayoutConfig.g:3078:1: ':'
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
    // InternalLayoutConfig.g:3091:1: rule__ElkEdgeSection__Group_4_0__2 : rule__ElkEdgeSection__Group_4_0__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3095:1: ( rule__ElkEdgeSection__Group_4_0__2__Impl )
            // InternalLayoutConfig.g:3096:2: rule__ElkEdgeSection__Group_4_0__2__Impl
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
    // InternalLayoutConfig.g:3102:1: rule__ElkEdgeSection__Group_4_0__2__Impl : ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3106:1: ( ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) ) )
            // InternalLayoutConfig.g:3107:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) )
            {
            // InternalLayoutConfig.g:3107:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 ) )
            // InternalLayoutConfig.g:3108:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_2()); 
            // InternalLayoutConfig.g:3109:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 )
            // InternalLayoutConfig.g:3109:2: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2
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
    // InternalLayoutConfig.g:3125:1: rule__ElkEdgeSection__Group_4_1__0 : rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3129:1: ( rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 )
            // InternalLayoutConfig.g:3130:2: rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1
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
    // InternalLayoutConfig.g:3137:1: rule__ElkEdgeSection__Group_4_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3141:1: ( ( 'outgoing' ) )
            // InternalLayoutConfig.g:3142:1: ( 'outgoing' )
            {
            // InternalLayoutConfig.g:3142:1: ( 'outgoing' )
            // InternalLayoutConfig.g:3143:1: 'outgoing'
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
    // InternalLayoutConfig.g:3156:1: rule__ElkEdgeSection__Group_4_1__1 : rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3160:1: ( rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 )
            // InternalLayoutConfig.g:3161:2: rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2
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
    // InternalLayoutConfig.g:3168:1: rule__ElkEdgeSection__Group_4_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3172:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3173:1: ( ':' )
            {
            // InternalLayoutConfig.g:3173:1: ( ':' )
            // InternalLayoutConfig.g:3174:1: ':'
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
    // InternalLayoutConfig.g:3187:1: rule__ElkEdgeSection__Group_4_1__2 : rule__ElkEdgeSection__Group_4_1__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3191:1: ( rule__ElkEdgeSection__Group_4_1__2__Impl )
            // InternalLayoutConfig.g:3192:2: rule__ElkEdgeSection__Group_4_1__2__Impl
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
    // InternalLayoutConfig.g:3198:1: rule__ElkEdgeSection__Group_4_1__2__Impl : ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3202:1: ( ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) ) )
            // InternalLayoutConfig.g:3203:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) )
            {
            // InternalLayoutConfig.g:3203:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 ) )
            // InternalLayoutConfig.g:3204:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_1_2()); 
            // InternalLayoutConfig.g:3205:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 )
            // InternalLayoutConfig.g:3205:2: rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2
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
    // InternalLayoutConfig.g:3221:1: rule__ElkEdgeSection__Group_4_2__0 : rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1 ;
    public final void rule__ElkEdgeSection__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3225:1: ( rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1 )
            // InternalLayoutConfig.g:3226:2: rule__ElkEdgeSection__Group_4_2__0__Impl rule__ElkEdgeSection__Group_4_2__1
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
    // InternalLayoutConfig.g:3233:1: rule__ElkEdgeSection__Group_4_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3237:1: ( ( 'start' ) )
            // InternalLayoutConfig.g:3238:1: ( 'start' )
            {
            // InternalLayoutConfig.g:3238:1: ( 'start' )
            // InternalLayoutConfig.g:3239:1: 'start'
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
    // InternalLayoutConfig.g:3252:1: rule__ElkEdgeSection__Group_4_2__1 : rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2 ;
    public final void rule__ElkEdgeSection__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3256:1: ( rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2 )
            // InternalLayoutConfig.g:3257:2: rule__ElkEdgeSection__Group_4_2__1__Impl rule__ElkEdgeSection__Group_4_2__2
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
    // InternalLayoutConfig.g:3264:1: rule__ElkEdgeSection__Group_4_2__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3268:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3269:1: ( ':' )
            {
            // InternalLayoutConfig.g:3269:1: ( ':' )
            // InternalLayoutConfig.g:3270:1: ':'
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
    // InternalLayoutConfig.g:3283:1: rule__ElkEdgeSection__Group_4_2__2 : rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3 ;
    public final void rule__ElkEdgeSection__Group_4_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3287:1: ( rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3 )
            // InternalLayoutConfig.g:3288:2: rule__ElkEdgeSection__Group_4_2__2__Impl rule__ElkEdgeSection__Group_4_2__3
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
    // InternalLayoutConfig.g:3295:1: rule__ElkEdgeSection__Group_4_2__2__Impl : ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3299:1: ( ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) ) )
            // InternalLayoutConfig.g:3300:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) )
            {
            // InternalLayoutConfig.g:3300:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_2_2 ) )
            // InternalLayoutConfig.g:3301:1: ( rule__ElkEdgeSection__StartXAssignment_4_2_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_2_2()); 
            // InternalLayoutConfig.g:3302:1: ( rule__ElkEdgeSection__StartXAssignment_4_2_2 )
            // InternalLayoutConfig.g:3302:2: rule__ElkEdgeSection__StartXAssignment_4_2_2
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
    // InternalLayoutConfig.g:3312:1: rule__ElkEdgeSection__Group_4_2__3 : rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4 ;
    public final void rule__ElkEdgeSection__Group_4_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3316:1: ( rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4 )
            // InternalLayoutConfig.g:3317:2: rule__ElkEdgeSection__Group_4_2__3__Impl rule__ElkEdgeSection__Group_4_2__4
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
    // InternalLayoutConfig.g:3324:1: rule__ElkEdgeSection__Group_4_2__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3328:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3329:1: ( ',' )
            {
            // InternalLayoutConfig.g:3329:1: ( ',' )
            // InternalLayoutConfig.g:3330:1: ','
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
    // InternalLayoutConfig.g:3343:1: rule__ElkEdgeSection__Group_4_2__4 : rule__ElkEdgeSection__Group_4_2__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3347:1: ( rule__ElkEdgeSection__Group_4_2__4__Impl )
            // InternalLayoutConfig.g:3348:2: rule__ElkEdgeSection__Group_4_2__4__Impl
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
    // InternalLayoutConfig.g:3354:1: rule__ElkEdgeSection__Group_4_2__4__Impl : ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3358:1: ( ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) ) )
            // InternalLayoutConfig.g:3359:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) )
            {
            // InternalLayoutConfig.g:3359:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_2_4 ) )
            // InternalLayoutConfig.g:3360:1: ( rule__ElkEdgeSection__StartYAssignment_4_2_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_2_4()); 
            // InternalLayoutConfig.g:3361:1: ( rule__ElkEdgeSection__StartYAssignment_4_2_4 )
            // InternalLayoutConfig.g:3361:2: rule__ElkEdgeSection__StartYAssignment_4_2_4
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
    // InternalLayoutConfig.g:3381:1: rule__ElkEdgeSection__Group_4_3__0 : rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3385:1: ( rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1 )
            // InternalLayoutConfig.g:3386:2: rule__ElkEdgeSection__Group_4_3__0__Impl rule__ElkEdgeSection__Group_4_3__1
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
    // InternalLayoutConfig.g:3393:1: rule__ElkEdgeSection__Group_4_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3397:1: ( ( 'end' ) )
            // InternalLayoutConfig.g:3398:1: ( 'end' )
            {
            // InternalLayoutConfig.g:3398:1: ( 'end' )
            // InternalLayoutConfig.g:3399:1: 'end'
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
    // InternalLayoutConfig.g:3412:1: rule__ElkEdgeSection__Group_4_3__1 : rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2 ;
    public final void rule__ElkEdgeSection__Group_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3416:1: ( rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2 )
            // InternalLayoutConfig.g:3417:2: rule__ElkEdgeSection__Group_4_3__1__Impl rule__ElkEdgeSection__Group_4_3__2
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
    // InternalLayoutConfig.g:3424:1: rule__ElkEdgeSection__Group_4_3__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3428:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3429:1: ( ':' )
            {
            // InternalLayoutConfig.g:3429:1: ( ':' )
            // InternalLayoutConfig.g:3430:1: ':'
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
    // InternalLayoutConfig.g:3443:1: rule__ElkEdgeSection__Group_4_3__2 : rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3 ;
    public final void rule__ElkEdgeSection__Group_4_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3447:1: ( rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3 )
            // InternalLayoutConfig.g:3448:2: rule__ElkEdgeSection__Group_4_3__2__Impl rule__ElkEdgeSection__Group_4_3__3
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
    // InternalLayoutConfig.g:3455:1: rule__ElkEdgeSection__Group_4_3__2__Impl : ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3459:1: ( ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) ) )
            // InternalLayoutConfig.g:3460:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) )
            {
            // InternalLayoutConfig.g:3460:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_3_2 ) )
            // InternalLayoutConfig.g:3461:1: ( rule__ElkEdgeSection__EndXAssignment_4_3_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_3_2()); 
            // InternalLayoutConfig.g:3462:1: ( rule__ElkEdgeSection__EndXAssignment_4_3_2 )
            // InternalLayoutConfig.g:3462:2: rule__ElkEdgeSection__EndXAssignment_4_3_2
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
    // InternalLayoutConfig.g:3472:1: rule__ElkEdgeSection__Group_4_3__3 : rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4 ;
    public final void rule__ElkEdgeSection__Group_4_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3476:1: ( rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4 )
            // InternalLayoutConfig.g:3477:2: rule__ElkEdgeSection__Group_4_3__3__Impl rule__ElkEdgeSection__Group_4_3__4
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
    // InternalLayoutConfig.g:3484:1: rule__ElkEdgeSection__Group_4_3__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3488:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3489:1: ( ',' )
            {
            // InternalLayoutConfig.g:3489:1: ( ',' )
            // InternalLayoutConfig.g:3490:1: ','
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
    // InternalLayoutConfig.g:3503:1: rule__ElkEdgeSection__Group_4_3__4 : rule__ElkEdgeSection__Group_4_3__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3507:1: ( rule__ElkEdgeSection__Group_4_3__4__Impl )
            // InternalLayoutConfig.g:3508:2: rule__ElkEdgeSection__Group_4_3__4__Impl
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
    // InternalLayoutConfig.g:3514:1: rule__ElkEdgeSection__Group_4_3__4__Impl : ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3518:1: ( ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) ) )
            // InternalLayoutConfig.g:3519:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) )
            {
            // InternalLayoutConfig.g:3519:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_3_4 ) )
            // InternalLayoutConfig.g:3520:1: ( rule__ElkEdgeSection__EndYAssignment_4_3_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_3_4()); 
            // InternalLayoutConfig.g:3521:1: ( rule__ElkEdgeSection__EndYAssignment_4_3_4 )
            // InternalLayoutConfig.g:3521:2: rule__ElkEdgeSection__EndYAssignment_4_3_4
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
    // InternalLayoutConfig.g:3541:1: rule__ElkEdgeSection__Group_4_4__0 : rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1 ;
    public final void rule__ElkEdgeSection__Group_4_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3545:1: ( rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1 )
            // InternalLayoutConfig.g:3546:2: rule__ElkEdgeSection__Group_4_4__0__Impl rule__ElkEdgeSection__Group_4_4__1
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
    // InternalLayoutConfig.g:3553:1: rule__ElkEdgeSection__Group_4_4__0__Impl : ( 'bends' ) ;
    public final void rule__ElkEdgeSection__Group_4_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3557:1: ( ( 'bends' ) )
            // InternalLayoutConfig.g:3558:1: ( 'bends' )
            {
            // InternalLayoutConfig.g:3558:1: ( 'bends' )
            // InternalLayoutConfig.g:3559:1: 'bends'
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
    // InternalLayoutConfig.g:3572:1: rule__ElkEdgeSection__Group_4_4__1 : rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2 ;
    public final void rule__ElkEdgeSection__Group_4_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3576:1: ( rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2 )
            // InternalLayoutConfig.g:3577:2: rule__ElkEdgeSection__Group_4_4__1__Impl rule__ElkEdgeSection__Group_4_4__2
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
    // InternalLayoutConfig.g:3584:1: rule__ElkEdgeSection__Group_4_4__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3588:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3589:1: ( ':' )
            {
            // InternalLayoutConfig.g:3589:1: ( ':' )
            // InternalLayoutConfig.g:3590:1: ':'
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
    // InternalLayoutConfig.g:3603:1: rule__ElkEdgeSection__Group_4_4__2 : rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3 ;
    public final void rule__ElkEdgeSection__Group_4_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3607:1: ( rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3 )
            // InternalLayoutConfig.g:3608:2: rule__ElkEdgeSection__Group_4_4__2__Impl rule__ElkEdgeSection__Group_4_4__3
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
    // InternalLayoutConfig.g:3615:1: rule__ElkEdgeSection__Group_4_4__2__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3619:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) ) )
            // InternalLayoutConfig.g:3620:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) )
            {
            // InternalLayoutConfig.g:3620:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 ) )
            // InternalLayoutConfig.g:3621:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_2()); 
            // InternalLayoutConfig.g:3622:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_2 )
            // InternalLayoutConfig.g:3622:2: rule__ElkEdgeSection__BendPointsAssignment_4_4_2
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
    // InternalLayoutConfig.g:3632:1: rule__ElkEdgeSection__Group_4_4__3 : rule__ElkEdgeSection__Group_4_4__3__Impl ;
    public final void rule__ElkEdgeSection__Group_4_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3636:1: ( rule__ElkEdgeSection__Group_4_4__3__Impl )
            // InternalLayoutConfig.g:3637:2: rule__ElkEdgeSection__Group_4_4__3__Impl
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
    // InternalLayoutConfig.g:3643:1: rule__ElkEdgeSection__Group_4_4__3__Impl : ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_4_4__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3647:1: ( ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* ) )
            // InternalLayoutConfig.g:3648:1: ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* )
            {
            // InternalLayoutConfig.g:3648:1: ( ( rule__ElkEdgeSection__Group_4_4_3__0 )* )
            // InternalLayoutConfig.g:3649:1: ( rule__ElkEdgeSection__Group_4_4_3__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4_3()); 
            // InternalLayoutConfig.g:3650:1: ( rule__ElkEdgeSection__Group_4_4_3__0 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==31) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalLayoutConfig.g:3650:2: rule__ElkEdgeSection__Group_4_4_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_22);
            	    rule__ElkEdgeSection__Group_4_4_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
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
    // InternalLayoutConfig.g:3668:1: rule__ElkEdgeSection__Group_4_4_3__0 : rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_4_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3672:1: ( rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1 )
            // InternalLayoutConfig.g:3673:2: rule__ElkEdgeSection__Group_4_4_3__0__Impl rule__ElkEdgeSection__Group_4_4_3__1
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
    // InternalLayoutConfig.g:3680:1: rule__ElkEdgeSection__Group_4_4_3__0__Impl : ( '|' ) ;
    public final void rule__ElkEdgeSection__Group_4_4_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3684:1: ( ( '|' ) )
            // InternalLayoutConfig.g:3685:1: ( '|' )
            {
            // InternalLayoutConfig.g:3685:1: ( '|' )
            // InternalLayoutConfig.g:3686:1: '|'
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
    // InternalLayoutConfig.g:3699:1: rule__ElkEdgeSection__Group_4_4_3__1 : rule__ElkEdgeSection__Group_4_4_3__1__Impl ;
    public final void rule__ElkEdgeSection__Group_4_4_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3703:1: ( rule__ElkEdgeSection__Group_4_4_3__1__Impl )
            // InternalLayoutConfig.g:3704:2: rule__ElkEdgeSection__Group_4_4_3__1__Impl
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
    // InternalLayoutConfig.g:3710:1: rule__ElkEdgeSection__Group_4_4_3__1__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_4_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3714:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) ) )
            // InternalLayoutConfig.g:3715:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) )
            {
            // InternalLayoutConfig.g:3715:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 ) )
            // InternalLayoutConfig.g:3716:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_4_3_1()); 
            // InternalLayoutConfig.g:3717:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 )
            // InternalLayoutConfig.g:3717:2: rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1
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
    // InternalLayoutConfig.g:3731:1: rule__ElkBendPoint__Group__0 : rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 ;
    public final void rule__ElkBendPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3735:1: ( rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 )
            // InternalLayoutConfig.g:3736:2: rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1
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
    // InternalLayoutConfig.g:3743:1: rule__ElkBendPoint__Group__0__Impl : ( ( rule__ElkBendPoint__XAssignment_0 ) ) ;
    public final void rule__ElkBendPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3747:1: ( ( ( rule__ElkBendPoint__XAssignment_0 ) ) )
            // InternalLayoutConfig.g:3748:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            {
            // InternalLayoutConfig.g:3748:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            // InternalLayoutConfig.g:3749:1: ( rule__ElkBendPoint__XAssignment_0 )
            {
             before(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 
            // InternalLayoutConfig.g:3750:1: ( rule__ElkBendPoint__XAssignment_0 )
            // InternalLayoutConfig.g:3750:2: rule__ElkBendPoint__XAssignment_0
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
    // InternalLayoutConfig.g:3760:1: rule__ElkBendPoint__Group__1 : rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 ;
    public final void rule__ElkBendPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3764:1: ( rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 )
            // InternalLayoutConfig.g:3765:2: rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2
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
    // InternalLayoutConfig.g:3772:1: rule__ElkBendPoint__Group__1__Impl : ( ',' ) ;
    public final void rule__ElkBendPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3776:1: ( ( ',' ) )
            // InternalLayoutConfig.g:3777:1: ( ',' )
            {
            // InternalLayoutConfig.g:3777:1: ( ',' )
            // InternalLayoutConfig.g:3778:1: ','
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
    // InternalLayoutConfig.g:3791:1: rule__ElkBendPoint__Group__2 : rule__ElkBendPoint__Group__2__Impl ;
    public final void rule__ElkBendPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3795:1: ( rule__ElkBendPoint__Group__2__Impl )
            // InternalLayoutConfig.g:3796:2: rule__ElkBendPoint__Group__2__Impl
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
    // InternalLayoutConfig.g:3802:1: rule__ElkBendPoint__Group__2__Impl : ( ( rule__ElkBendPoint__YAssignment_2 ) ) ;
    public final void rule__ElkBendPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3806:1: ( ( ( rule__ElkBendPoint__YAssignment_2 ) ) )
            // InternalLayoutConfig.g:3807:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            {
            // InternalLayoutConfig.g:3807:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            // InternalLayoutConfig.g:3808:1: ( rule__ElkBendPoint__YAssignment_2 )
            {
             before(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 
            // InternalLayoutConfig.g:3809:1: ( rule__ElkBendPoint__YAssignment_2 )
            // InternalLayoutConfig.g:3809:2: rule__ElkBendPoint__YAssignment_2
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


    // $ANTLR start "rule__Property__Group__0"
    // InternalLayoutConfig.g:3825:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3829:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalLayoutConfig.g:3830:2: rule__Property__Group__0__Impl rule__Property__Group__1
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
    // InternalLayoutConfig.g:3837:1: rule__Property__Group__0__Impl : ( ( rule__Property__KeyAssignment_0 ) ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3841:1: ( ( ( rule__Property__KeyAssignment_0 ) ) )
            // InternalLayoutConfig.g:3842:1: ( ( rule__Property__KeyAssignment_0 ) )
            {
            // InternalLayoutConfig.g:3842:1: ( ( rule__Property__KeyAssignment_0 ) )
            // InternalLayoutConfig.g:3843:1: ( rule__Property__KeyAssignment_0 )
            {
             before(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 
            // InternalLayoutConfig.g:3844:1: ( rule__Property__KeyAssignment_0 )
            // InternalLayoutConfig.g:3844:2: rule__Property__KeyAssignment_0
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
    // InternalLayoutConfig.g:3854:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3858:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalLayoutConfig.g:3859:2: rule__Property__Group__1__Impl rule__Property__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_25);
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
    // InternalLayoutConfig.g:3866:1: rule__Property__Group__1__Impl : ( ':' ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3870:1: ( ( ':' ) )
            // InternalLayoutConfig.g:3871:1: ( ':' )
            {
            // InternalLayoutConfig.g:3871:1: ( ':' )
            // InternalLayoutConfig.g:3872:1: ':'
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
    // InternalLayoutConfig.g:3885:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3889:1: ( rule__Property__Group__2__Impl )
            // InternalLayoutConfig.g:3890:2: rule__Property__Group__2__Impl
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
    // InternalLayoutConfig.g:3896:1: rule__Property__Group__2__Impl : ( ( rule__Property__Alternatives_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3900:1: ( ( ( rule__Property__Alternatives_2 ) ) )
            // InternalLayoutConfig.g:3901:1: ( ( rule__Property__Alternatives_2 ) )
            {
            // InternalLayoutConfig.g:3901:1: ( ( rule__Property__Alternatives_2 ) )
            // InternalLayoutConfig.g:3902:1: ( rule__Property__Alternatives_2 )
            {
             before(grammarAccess.getPropertyAccess().getAlternatives_2()); 
            // InternalLayoutConfig.g:3903:1: ( rule__Property__Alternatives_2 )
            // InternalLayoutConfig.g:3903:2: rule__Property__Alternatives_2
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


    // $ANTLR start "rule__QualifiedId__Group__0"
    // InternalLayoutConfig.g:3919:1: rule__QualifiedId__Group__0 : rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 ;
    public final void rule__QualifiedId__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3923:1: ( rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 )
            // InternalLayoutConfig.g:3924:2: rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1
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
    // InternalLayoutConfig.g:3931:1: rule__QualifiedId__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3935:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:3936:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:3936:1: ( RULE_ID )
            // InternalLayoutConfig.g:3937:1: RULE_ID
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
    // InternalLayoutConfig.g:3948:1: rule__QualifiedId__Group__1 : rule__QualifiedId__Group__1__Impl ;
    public final void rule__QualifiedId__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3952:1: ( rule__QualifiedId__Group__1__Impl )
            // InternalLayoutConfig.g:3953:2: rule__QualifiedId__Group__1__Impl
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
    // InternalLayoutConfig.g:3959:1: rule__QualifiedId__Group__1__Impl : ( ( rule__QualifiedId__Group_1__0 )* ) ;
    public final void rule__QualifiedId__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3963:1: ( ( ( rule__QualifiedId__Group_1__0 )* ) )
            // InternalLayoutConfig.g:3964:1: ( ( rule__QualifiedId__Group_1__0 )* )
            {
            // InternalLayoutConfig.g:3964:1: ( ( rule__QualifiedId__Group_1__0 )* )
            // InternalLayoutConfig.g:3965:1: ( rule__QualifiedId__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup_1()); 
            // InternalLayoutConfig.g:3966:1: ( rule__QualifiedId__Group_1__0 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==34) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalLayoutConfig.g:3966:2: rule__QualifiedId__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_27);
            	    rule__QualifiedId__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // InternalLayoutConfig.g:3980:1: rule__QualifiedId__Group_1__0 : rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 ;
    public final void rule__QualifiedId__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3984:1: ( rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 )
            // InternalLayoutConfig.g:3985:2: rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1
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
    // InternalLayoutConfig.g:3992:1: rule__QualifiedId__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedId__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:3996:1: ( ( '.' ) )
            // InternalLayoutConfig.g:3997:1: ( '.' )
            {
            // InternalLayoutConfig.g:3997:1: ( '.' )
            // InternalLayoutConfig.g:3998:1: '.'
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
    // InternalLayoutConfig.g:4011:1: rule__QualifiedId__Group_1__1 : rule__QualifiedId__Group_1__1__Impl ;
    public final void rule__QualifiedId__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4015:1: ( rule__QualifiedId__Group_1__1__Impl )
            // InternalLayoutConfig.g:4016:2: rule__QualifiedId__Group_1__1__Impl
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
    // InternalLayoutConfig.g:4022:1: rule__QualifiedId__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4026:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4027:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4027:1: ( RULE_ID )
            // InternalLayoutConfig.g:4028:1: RULE_ID
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


    // $ANTLR start "rule__PropertyKey__Group__0"
    // InternalLayoutConfig.g:4043:1: rule__PropertyKey__Group__0 : rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 ;
    public final void rule__PropertyKey__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4047:1: ( rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 )
            // InternalLayoutConfig.g:4048:2: rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1
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
    // InternalLayoutConfig.g:4055:1: rule__PropertyKey__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4059:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4060:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4060:1: ( RULE_ID )
            // InternalLayoutConfig.g:4061:1: RULE_ID
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
    // InternalLayoutConfig.g:4072:1: rule__PropertyKey__Group__1 : rule__PropertyKey__Group__1__Impl ;
    public final void rule__PropertyKey__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4076:1: ( rule__PropertyKey__Group__1__Impl )
            // InternalLayoutConfig.g:4077:2: rule__PropertyKey__Group__1__Impl
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
    // InternalLayoutConfig.g:4083:1: rule__PropertyKey__Group__1__Impl : ( ( rule__PropertyKey__Group_1__0 )* ) ;
    public final void rule__PropertyKey__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4087:1: ( ( ( rule__PropertyKey__Group_1__0 )* ) )
            // InternalLayoutConfig.g:4088:1: ( ( rule__PropertyKey__Group_1__0 )* )
            {
            // InternalLayoutConfig.g:4088:1: ( ( rule__PropertyKey__Group_1__0 )* )
            // InternalLayoutConfig.g:4089:1: ( rule__PropertyKey__Group_1__0 )*
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup_1()); 
            // InternalLayoutConfig.g:4090:1: ( rule__PropertyKey__Group_1__0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==34) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalLayoutConfig.g:4090:2: rule__PropertyKey__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_27);
            	    rule__PropertyKey__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // InternalLayoutConfig.g:4104:1: rule__PropertyKey__Group_1__0 : rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 ;
    public final void rule__PropertyKey__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4108:1: ( rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 )
            // InternalLayoutConfig.g:4109:2: rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1
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
    // InternalLayoutConfig.g:4116:1: rule__PropertyKey__Group_1__0__Impl : ( '.' ) ;
    public final void rule__PropertyKey__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4120:1: ( ( '.' ) )
            // InternalLayoutConfig.g:4121:1: ( '.' )
            {
            // InternalLayoutConfig.g:4121:1: ( '.' )
            // InternalLayoutConfig.g:4122:1: '.'
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
    // InternalLayoutConfig.g:4135:1: rule__PropertyKey__Group_1__1 : rule__PropertyKey__Group_1__1__Impl ;
    public final void rule__PropertyKey__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4139:1: ( rule__PropertyKey__Group_1__1__Impl )
            // InternalLayoutConfig.g:4140:2: rule__PropertyKey__Group_1__1__Impl
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
    // InternalLayoutConfig.g:4146:1: rule__PropertyKey__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4150:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4151:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4151:1: ( RULE_ID )
            // InternalLayoutConfig.g:4152:1: RULE_ID
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
    // InternalLayoutConfig.g:4168:1: rule__ShapeLayout__UnorderedGroup_2 : ( rule__ShapeLayout__UnorderedGroup_2__0 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            
        try {
            // InternalLayoutConfig.g:4173:1: ( ( rule__ShapeLayout__UnorderedGroup_2__0 )? )
            // InternalLayoutConfig.g:4174:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            {
            // InternalLayoutConfig.g:4174:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( LA21_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt21=1;
            }
            else if ( LA21_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalLayoutConfig.g:4174:2: rule__ShapeLayout__UnorderedGroup_2__0
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
    // InternalLayoutConfig.g:4184:1: rule__ShapeLayout__UnorderedGroup_2__Impl : ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) ;
    public final void rule__ShapeLayout__UnorderedGroup_2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4189:1: ( ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4190:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4190:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( LA22_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt22=1;
            }
            else if ( LA22_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalLayoutConfig.g:4192:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4192:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4193:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
                    }
                    // InternalLayoutConfig.g:4193:108: ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    // InternalLayoutConfig.g:4194:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4200:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    // InternalLayoutConfig.g:4202:7: ( rule__ShapeLayout__Group_2_0__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 
                    // InternalLayoutConfig.g:4203:7: ( rule__ShapeLayout__Group_2_0__0 )
                    // InternalLayoutConfig.g:4203:8: rule__ShapeLayout__Group_2_0__0
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
                    // InternalLayoutConfig.g:4209:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4209:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4210:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
                    }
                    // InternalLayoutConfig.g:4210:108: ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    // InternalLayoutConfig.g:4211:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4217:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    // InternalLayoutConfig.g:4219:7: ( rule__ShapeLayout__Group_2_1__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 
                    // InternalLayoutConfig.g:4220:7: ( rule__ShapeLayout__Group_2_1__0 )
                    // InternalLayoutConfig.g:4220:8: rule__ShapeLayout__Group_2_1__0
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
    // InternalLayoutConfig.g:4235:1: rule__ShapeLayout__UnorderedGroup_2__0 : rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4239:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? )
            // InternalLayoutConfig.g:4240:2: rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_28);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4241:2: ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( LA23_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt23=1;
            }
            else if ( LA23_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalLayoutConfig.g:4241:2: rule__ShapeLayout__UnorderedGroup_2__1
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
    // InternalLayoutConfig.g:4248:1: rule__ShapeLayout__UnorderedGroup_2__1 : rule__ShapeLayout__UnorderedGroup_2__Impl ;
    public final void rule__ShapeLayout__UnorderedGroup_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4252:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl )
            // InternalLayoutConfig.g:4253:2: rule__ShapeLayout__UnorderedGroup_2__Impl
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
    // InternalLayoutConfig.g:4264:1: rule__ElkSingleEdgeSection__UnorderedGroup_1 : ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1());
            
        try {
            // InternalLayoutConfig.g:4269:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )? )
            // InternalLayoutConfig.g:4270:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )?
            {
            // InternalLayoutConfig.g:4270:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( LA24_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt24=1;
            }
            else if ( LA24_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt24=1;
            }
            else if ( LA24_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt24=1;
            }
            else if ( LA24_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt24=1;
            }
            else if ( LA24_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalLayoutConfig.g:4270:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__0
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
    // InternalLayoutConfig.g:4280:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl : ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) ) ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4285:1: ( ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4286:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4286:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) ) )
            int alt25=5;
            int LA25_0 = input.LA(1);

            if ( LA25_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt25=1;
            }
            else if ( LA25_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt25=2;
            }
            else if ( LA25_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt25=3;
            }
            else if ( LA25_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt25=4;
            }
            else if ( LA25_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt25=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalLayoutConfig.g:4288:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4288:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4289:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0)");
                    }
                    // InternalLayoutConfig.g:4289:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) ) )
                    // InternalLayoutConfig.g:4290:6: ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4296:6: ( ( rule__ElkSingleEdgeSection__Group_1_0__0 ) )
                    // InternalLayoutConfig.g:4298:7: ( rule__ElkSingleEdgeSection__Group_1_0__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0()); 
                    // InternalLayoutConfig.g:4299:7: ( rule__ElkSingleEdgeSection__Group_1_0__0 )
                    // InternalLayoutConfig.g:4299:8: rule__ElkSingleEdgeSection__Group_1_0__0
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
                    // InternalLayoutConfig.g:4305:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4305:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4306:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1)");
                    }
                    // InternalLayoutConfig.g:4306:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) ) )
                    // InternalLayoutConfig.g:4307:6: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4313:6: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 ) )
                    // InternalLayoutConfig.g:4315:7: ( rule__ElkSingleEdgeSection__Group_1_1__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 
                    // InternalLayoutConfig.g:4316:7: ( rule__ElkSingleEdgeSection__Group_1_1__0 )
                    // InternalLayoutConfig.g:4316:8: rule__ElkSingleEdgeSection__Group_1_1__0
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
                    // InternalLayoutConfig.g:4322:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4322:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) ) )
                    // InternalLayoutConfig.g:4323:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2)");
                    }
                    // InternalLayoutConfig.g:4323:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) ) )
                    // InternalLayoutConfig.g:4324:6: ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4330:6: ( ( rule__ElkSingleEdgeSection__Group_1_2__0 ) )
                    // InternalLayoutConfig.g:4332:7: ( rule__ElkSingleEdgeSection__Group_1_2__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_2()); 
                    // InternalLayoutConfig.g:4333:7: ( rule__ElkSingleEdgeSection__Group_1_2__0 )
                    // InternalLayoutConfig.g:4333:8: rule__ElkSingleEdgeSection__Group_1_2__0
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
                    // InternalLayoutConfig.g:4339:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4339:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) ) )
                    // InternalLayoutConfig.g:4340:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3)");
                    }
                    // InternalLayoutConfig.g:4340:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) ) )
                    // InternalLayoutConfig.g:4341:6: ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4347:6: ( ( rule__ElkSingleEdgeSection__Group_1_3__0 ) )
                    // InternalLayoutConfig.g:4349:7: ( rule__ElkSingleEdgeSection__Group_1_3__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_3()); 
                    // InternalLayoutConfig.g:4350:7: ( rule__ElkSingleEdgeSection__Group_1_3__0 )
                    // InternalLayoutConfig.g:4350:8: rule__ElkSingleEdgeSection__Group_1_3__0
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
                    // InternalLayoutConfig.g:4356:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4356:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) ) )
                    // InternalLayoutConfig.g:4357:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4)");
                    }
                    // InternalLayoutConfig.g:4357:117: ( ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) ) )
                    // InternalLayoutConfig.g:4358:6: ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4364:6: ( ( rule__ElkSingleEdgeSection__Group_1_4__0 ) )
                    // InternalLayoutConfig.g:4366:7: ( rule__ElkSingleEdgeSection__Group_1_4__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_4()); 
                    // InternalLayoutConfig.g:4367:7: ( rule__ElkSingleEdgeSection__Group_1_4__0 )
                    // InternalLayoutConfig.g:4367:8: rule__ElkSingleEdgeSection__Group_1_4__0
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
    // InternalLayoutConfig.g:4382:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__0 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4386:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )? )
            // InternalLayoutConfig.g:4387:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4388:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__1 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( LA26_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 0) ) {
                alt26=1;
            }
            else if ( LA26_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 1) ) {
                alt26=1;
            }
            else if ( LA26_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 2) ) {
                alt26=1;
            }
            else if ( LA26_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 3) ) {
                alt26=1;
            }
            else if ( LA26_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1(), 4) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalLayoutConfig.g:4388:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__1
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
    // InternalLayoutConfig.g:4395:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__1 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4399:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )? )
            // InternalLayoutConfig.g:4400:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4401:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__2 )?
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
                    // InternalLayoutConfig.g:4401:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__2
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
    // InternalLayoutConfig.g:4408:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__2 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4412:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )? )
            // InternalLayoutConfig.g:4413:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4414:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__3 )?
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
                    // InternalLayoutConfig.g:4414:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__3
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
    // InternalLayoutConfig.g:4421:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__3 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4425:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )? )
            // InternalLayoutConfig.g:4426:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4427:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__4 )?
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
                    // InternalLayoutConfig.g:4427:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__4
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
    // InternalLayoutConfig.g:4434:1: rule__ElkSingleEdgeSection__UnorderedGroup_1__4 : rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4438:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl )
            // InternalLayoutConfig.g:4439:2: rule__ElkSingleEdgeSection__UnorderedGroup_1__Impl
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
    // InternalLayoutConfig.g:4456:1: rule__ElkEdgeSection__UnorderedGroup_4 : ( rule__ElkEdgeSection__UnorderedGroup_4__0 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4());
            
        try {
            // InternalLayoutConfig.g:4461:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4__0 )? )
            // InternalLayoutConfig.g:4462:2: ( rule__ElkEdgeSection__UnorderedGroup_4__0 )?
            {
            // InternalLayoutConfig.g:4462:2: ( rule__ElkEdgeSection__UnorderedGroup_4__0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( LA30_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt30=1;
            }
            else if ( LA30_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt30=1;
            }
            else if ( LA30_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt30=1;
            }
            else if ( LA30_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt30=1;
            }
            else if ( LA30_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalLayoutConfig.g:4462:2: rule__ElkEdgeSection__UnorderedGroup_4__0
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
    // InternalLayoutConfig.g:4472:1: rule__ElkEdgeSection__UnorderedGroup_4__Impl : ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) ) ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalLayoutConfig.g:4477:1: ( ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) ) )
            // InternalLayoutConfig.g:4478:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) )
            {
            // InternalLayoutConfig.g:4478:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) ) )
            int alt31=5;
            int LA31_0 = input.LA(1);

            if ( LA31_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt31=1;
            }
            else if ( LA31_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt31=2;
            }
            else if ( LA31_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt31=3;
            }
            else if ( LA31_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt31=4;
            }
            else if ( LA31_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt31=5;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // InternalLayoutConfig.g:4480:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4480:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) ) )
                    // InternalLayoutConfig.g:4481:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0)");
                    }
                    // InternalLayoutConfig.g:4481:111: ( ( ( rule__ElkEdgeSection__Group_4_0__0 ) ) )
                    // InternalLayoutConfig.g:4482:6: ( ( rule__ElkEdgeSection__Group_4_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4488:6: ( ( rule__ElkEdgeSection__Group_4_0__0 ) )
                    // InternalLayoutConfig.g:4490:7: ( rule__ElkEdgeSection__Group_4_0__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0()); 
                    // InternalLayoutConfig.g:4491:7: ( rule__ElkEdgeSection__Group_4_0__0 )
                    // InternalLayoutConfig.g:4491:8: rule__ElkEdgeSection__Group_4_0__0
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
                    // InternalLayoutConfig.g:4497:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4497:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) ) )
                    // InternalLayoutConfig.g:4498:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1)");
                    }
                    // InternalLayoutConfig.g:4498:111: ( ( ( rule__ElkEdgeSection__Group_4_1__0 ) ) )
                    // InternalLayoutConfig.g:4499:6: ( ( rule__ElkEdgeSection__Group_4_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4505:6: ( ( rule__ElkEdgeSection__Group_4_1__0 ) )
                    // InternalLayoutConfig.g:4507:7: ( rule__ElkEdgeSection__Group_4_1__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 
                    // InternalLayoutConfig.g:4508:7: ( rule__ElkEdgeSection__Group_4_1__0 )
                    // InternalLayoutConfig.g:4508:8: rule__ElkEdgeSection__Group_4_1__0
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
                    // InternalLayoutConfig.g:4514:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4514:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) ) )
                    // InternalLayoutConfig.g:4515:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2)");
                    }
                    // InternalLayoutConfig.g:4515:111: ( ( ( rule__ElkEdgeSection__Group_4_2__0 ) ) )
                    // InternalLayoutConfig.g:4516:6: ( ( rule__ElkEdgeSection__Group_4_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4522:6: ( ( rule__ElkEdgeSection__Group_4_2__0 ) )
                    // InternalLayoutConfig.g:4524:7: ( rule__ElkEdgeSection__Group_4_2__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_2()); 
                    // InternalLayoutConfig.g:4525:7: ( rule__ElkEdgeSection__Group_4_2__0 )
                    // InternalLayoutConfig.g:4525:8: rule__ElkEdgeSection__Group_4_2__0
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
                    // InternalLayoutConfig.g:4531:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4531:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) ) )
                    // InternalLayoutConfig.g:4532:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3)");
                    }
                    // InternalLayoutConfig.g:4532:111: ( ( ( rule__ElkEdgeSection__Group_4_3__0 ) ) )
                    // InternalLayoutConfig.g:4533:6: ( ( rule__ElkEdgeSection__Group_4_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4539:6: ( ( rule__ElkEdgeSection__Group_4_3__0 ) )
                    // InternalLayoutConfig.g:4541:7: ( rule__ElkEdgeSection__Group_4_3__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_3()); 
                    // InternalLayoutConfig.g:4542:7: ( rule__ElkEdgeSection__Group_4_3__0 )
                    // InternalLayoutConfig.g:4542:8: rule__ElkEdgeSection__Group_4_3__0
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
                    // InternalLayoutConfig.g:4548:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) )
                    {
                    // InternalLayoutConfig.g:4548:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) ) )
                    // InternalLayoutConfig.g:4549:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4)");
                    }
                    // InternalLayoutConfig.g:4549:111: ( ( ( rule__ElkEdgeSection__Group_4_4__0 ) ) )
                    // InternalLayoutConfig.g:4550:6: ( ( rule__ElkEdgeSection__Group_4_4__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalLayoutConfig.g:4556:6: ( ( rule__ElkEdgeSection__Group_4_4__0 ) )
                    // InternalLayoutConfig.g:4558:7: ( rule__ElkEdgeSection__Group_4_4__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_4()); 
                    // InternalLayoutConfig.g:4559:7: ( rule__ElkEdgeSection__Group_4_4__0 )
                    // InternalLayoutConfig.g:4559:8: rule__ElkEdgeSection__Group_4_4__0
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
    // InternalLayoutConfig.g:4574:1: rule__ElkEdgeSection__UnorderedGroup_4__0 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4578:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )? )
            // InternalLayoutConfig.g:4579:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__1 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4580:2: ( rule__ElkEdgeSection__UnorderedGroup_4__1 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( LA32_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 0) ) {
                alt32=1;
            }
            else if ( LA32_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 1) ) {
                alt32=1;
            }
            else if ( LA32_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 2) ) {
                alt32=1;
            }
            else if ( LA32_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 3) ) {
                alt32=1;
            }
            else if ( LA32_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4(), 4) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalLayoutConfig.g:4580:2: rule__ElkEdgeSection__UnorderedGroup_4__1
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
    // InternalLayoutConfig.g:4587:1: rule__ElkEdgeSection__UnorderedGroup_4__1 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4591:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )? )
            // InternalLayoutConfig.g:4592:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__2 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4593:2: ( rule__ElkEdgeSection__UnorderedGroup_4__2 )?
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
                    // InternalLayoutConfig.g:4593:2: rule__ElkEdgeSection__UnorderedGroup_4__2
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
    // InternalLayoutConfig.g:4600:1: rule__ElkEdgeSection__UnorderedGroup_4__2 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4604:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )? )
            // InternalLayoutConfig.g:4605:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__3 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4606:2: ( rule__ElkEdgeSection__UnorderedGroup_4__3 )?
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
                    // InternalLayoutConfig.g:4606:2: rule__ElkEdgeSection__UnorderedGroup_4__3
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
    // InternalLayoutConfig.g:4613:1: rule__ElkEdgeSection__UnorderedGroup_4__3 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4617:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )? )
            // InternalLayoutConfig.g:4618:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl ( rule__ElkEdgeSection__UnorderedGroup_4__4 )?
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__ElkEdgeSection__UnorderedGroup_4__Impl();

            state._fsp--;

            // InternalLayoutConfig.g:4619:2: ( rule__ElkEdgeSection__UnorderedGroup_4__4 )?
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
                    // InternalLayoutConfig.g:4619:2: rule__ElkEdgeSection__UnorderedGroup_4__4
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
    // InternalLayoutConfig.g:4626:1: rule__ElkEdgeSection__UnorderedGroup_4__4 : rule__ElkEdgeSection__UnorderedGroup_4__Impl ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4630:1: ( rule__ElkEdgeSection__UnorderedGroup_4__Impl )
            // InternalLayoutConfig.g:4631:2: rule__ElkEdgeSection__UnorderedGroup_4__Impl
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
    // InternalLayoutConfig.g:4648:1: rule__RootNode__ChildrenAssignment_1 : ( ruleElkNode ) ;
    public final void rule__RootNode__ChildrenAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4652:1: ( ( ruleElkNode ) )
            // InternalLayoutConfig.g:4653:1: ( ruleElkNode )
            {
            // InternalLayoutConfig.g:4653:1: ( ruleElkNode )
            // InternalLayoutConfig.g:4654:1: ruleElkNode
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
    // InternalLayoutConfig.g:4663:1: rule__ElkNode__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__ElkNode__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4667:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4668:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4668:1: ( RULE_ID )
            // InternalLayoutConfig.g:4669:1: RULE_ID
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
    // InternalLayoutConfig.g:4678:1: rule__ElkNode__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__ElkNode__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4682:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4683:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4683:1: ( ruleProperty )
            // InternalLayoutConfig.g:4684:1: ruleProperty
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
    // InternalLayoutConfig.g:4693:1: rule__ElkNode__ChildrenAssignment_3_1 : ( ruleRefElkNode ) ;
    public final void rule__ElkNode__ChildrenAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4697:1: ( ( ruleRefElkNode ) )
            // InternalLayoutConfig.g:4698:1: ( ruleRefElkNode )
            {
            // InternalLayoutConfig.g:4698:1: ( ruleRefElkNode )
            // InternalLayoutConfig.g:4699:1: ruleRefElkNode
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
    // InternalLayoutConfig.g:4708:1: rule__RefElkNode__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__RefElkNode__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4712:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4713:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4713:1: ( RULE_ID )
            // InternalLayoutConfig.g:4714:1: RULE_ID
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
    // InternalLayoutConfig.g:4723:1: rule__RefElkNode__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__RefElkNode__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4727:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4728:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4728:1: ( ruleProperty )
            // InternalLayoutConfig.g:4729:1: ruleProperty
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
    // InternalLayoutConfig.g:4738:1: rule__ElkLabel__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkLabel__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4742:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:4743:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:4743:1: ( RULE_ID )
            // InternalLayoutConfig.g:4744:1: RULE_ID
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
    // InternalLayoutConfig.g:4753:1: rule__ElkLabel__TextAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ElkLabel__TextAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4757:1: ( ( RULE_STRING ) )
            // InternalLayoutConfig.g:4758:1: ( RULE_STRING )
            {
            // InternalLayoutConfig.g:4758:1: ( RULE_STRING )
            // InternalLayoutConfig.g:4759:1: RULE_STRING
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
    // InternalLayoutConfig.g:4768:1: rule__ElkLabel__PropertiesAssignment_3_2 : ( ruleProperty ) ;
    public final void rule__ElkLabel__PropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4772:1: ( ( ruleProperty ) )
            // InternalLayoutConfig.g:4773:1: ( ruleProperty )
            {
            // InternalLayoutConfig.g:4773:1: ( ruleProperty )
            // InternalLayoutConfig.g:4774:1: ruleProperty
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
    // InternalLayoutConfig.g:4783:1: rule__ElkLabel__LabelsAssignment_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkLabel__LabelsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4787:1: ( ( ruleElkLabel ) )
            // InternalLayoutConfig.g:4788:1: ( ruleElkLabel )
            {
            // InternalLayoutConfig.g:4788:1: ( ruleElkLabel )
            // InternalLayoutConfig.g:4789:1: ruleElkLabel
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
    // InternalLayoutConfig.g:4801:1: rule__ShapeLayout__XAssignment_2_0_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__XAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4805:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4806:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4806:1: ( ruleNumber )
            // InternalLayoutConfig.g:4807:1: ruleNumber
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
    // InternalLayoutConfig.g:4816:1: rule__ShapeLayout__YAssignment_2_0_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__YAssignment_2_0_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4820:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4821:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4821:1: ( ruleNumber )
            // InternalLayoutConfig.g:4822:1: ruleNumber
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
    // InternalLayoutConfig.g:4831:1: rule__ShapeLayout__WidthAssignment_2_1_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__WidthAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4835:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4836:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4836:1: ( ruleNumber )
            // InternalLayoutConfig.g:4837:1: ruleNumber
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
    // InternalLayoutConfig.g:4846:1: rule__ShapeLayout__HeightAssignment_2_1_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__HeightAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4850:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4851:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4851:1: ( ruleNumber )
            // InternalLayoutConfig.g:4852:1: ruleNumber
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
    // InternalLayoutConfig.g:4868:1: rule__EdgeLayout__SectionsAssignment_2_0 : ( ruleElkSingleEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4872:1: ( ( ruleElkSingleEdgeSection ) )
            // InternalLayoutConfig.g:4873:1: ( ruleElkSingleEdgeSection )
            {
            // InternalLayoutConfig.g:4873:1: ( ruleElkSingleEdgeSection )
            // InternalLayoutConfig.g:4874:1: ruleElkSingleEdgeSection
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
    // InternalLayoutConfig.g:4883:1: rule__EdgeLayout__SectionsAssignment_2_1 : ( ruleElkEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4887:1: ( ( ruleElkEdgeSection ) )
            // InternalLayoutConfig.g:4888:1: ( ruleElkEdgeSection )
            {
            // InternalLayoutConfig.g:4888:1: ( ruleElkEdgeSection )
            // InternalLayoutConfig.g:4889:1: ruleElkEdgeSection
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
    // InternalLayoutConfig.g:4898:1: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4902:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:4903:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:4903:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:4904:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_2_0()); 
            // InternalLayoutConfig.g:4905:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:4906:1: ruleQualifiedId
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
    // InternalLayoutConfig.g:4917:1: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4921:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:4922:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:4922:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:4923:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_1_2_0()); 
            // InternalLayoutConfig.g:4924:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:4925:1: ruleQualifiedId
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
    // InternalLayoutConfig.g:4936:1: rule__ElkSingleEdgeSection__StartXAssignment_1_2_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartXAssignment_1_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4940:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4941:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4941:1: ( ruleNumber )
            // InternalLayoutConfig.g:4942:1: ruleNumber
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
    // InternalLayoutConfig.g:4951:1: rule__ElkSingleEdgeSection__StartYAssignment_1_2_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartYAssignment_1_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4955:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4956:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4956:1: ( ruleNumber )
            // InternalLayoutConfig.g:4957:1: ruleNumber
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
    // InternalLayoutConfig.g:4966:1: rule__ElkSingleEdgeSection__EndXAssignment_1_3_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndXAssignment_1_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4970:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4971:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4971:1: ( ruleNumber )
            // InternalLayoutConfig.g:4972:1: ruleNumber
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
    // InternalLayoutConfig.g:4981:1: rule__ElkSingleEdgeSection__EndYAssignment_1_3_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndYAssignment_1_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:4985:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:4986:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:4986:1: ( ruleNumber )
            // InternalLayoutConfig.g:4987:1: ruleNumber
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
    // InternalLayoutConfig.g:4996:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5000:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5001:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5001:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5002:1: ruleElkBendPoint
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
    // InternalLayoutConfig.g:5011:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_4_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5015:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5016:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5016:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5017:1: ruleElkBendPoint
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
    // InternalLayoutConfig.g:5026:1: rule__ElkEdgeSection__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkEdgeSection__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5030:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5031:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:5031:1: ( RULE_ID )
            // InternalLayoutConfig.g:5032:1: RULE_ID
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
    // InternalLayoutConfig.g:5041:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5045:1: ( ( ( RULE_ID ) ) )
            // InternalLayoutConfig.g:5046:1: ( ( RULE_ID ) )
            {
            // InternalLayoutConfig.g:5046:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5047:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
            // InternalLayoutConfig.g:5048:1: ( RULE_ID )
            // InternalLayoutConfig.g:5049:1: RULE_ID
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
    // InternalLayoutConfig.g:5060:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5064:1: ( ( ( RULE_ID ) ) )
            // InternalLayoutConfig.g:5065:1: ( ( RULE_ID ) )
            {
            // InternalLayoutConfig.g:5065:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:5066:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
            // InternalLayoutConfig.g:5067:1: ( RULE_ID )
            // InternalLayoutConfig.g:5068:1: RULE_ID
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
    // InternalLayoutConfig.g:5079:1: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__IncomingShapeAssignment_4_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5083:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5084:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5084:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5085:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_2_0()); 
            // InternalLayoutConfig.g:5086:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5087:1: ruleQualifiedId
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
    // InternalLayoutConfig.g:5098:1: rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__OutgoingShapeAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5102:1: ( ( ( ruleQualifiedId ) ) )
            // InternalLayoutConfig.g:5103:1: ( ( ruleQualifiedId ) )
            {
            // InternalLayoutConfig.g:5103:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5104:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_1_2_0()); 
            // InternalLayoutConfig.g:5105:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5106:1: ruleQualifiedId
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
    // InternalLayoutConfig.g:5117:1: rule__ElkEdgeSection__StartXAssignment_4_2_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartXAssignment_4_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5121:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5122:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5122:1: ( ruleNumber )
            // InternalLayoutConfig.g:5123:1: ruleNumber
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
    // InternalLayoutConfig.g:5132:1: rule__ElkEdgeSection__StartYAssignment_4_2_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartYAssignment_4_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5136:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5137:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5137:1: ( ruleNumber )
            // InternalLayoutConfig.g:5138:1: ruleNumber
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
    // InternalLayoutConfig.g:5147:1: rule__ElkEdgeSection__EndXAssignment_4_3_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndXAssignment_4_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5151:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5152:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5152:1: ( ruleNumber )
            // InternalLayoutConfig.g:5153:1: ruleNumber
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
    // InternalLayoutConfig.g:5162:1: rule__ElkEdgeSection__EndYAssignment_4_3_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndYAssignment_4_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5166:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5167:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5167:1: ( ruleNumber )
            // InternalLayoutConfig.g:5168:1: ruleNumber
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
    // InternalLayoutConfig.g:5177:1: rule__ElkEdgeSection__BendPointsAssignment_4_4_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5181:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5182:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5182:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5183:1: ruleElkBendPoint
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
    // InternalLayoutConfig.g:5192:1: rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_4_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5196:1: ( ( ruleElkBendPoint ) )
            // InternalLayoutConfig.g:5197:1: ( ruleElkBendPoint )
            {
            // InternalLayoutConfig.g:5197:1: ( ruleElkBendPoint )
            // InternalLayoutConfig.g:5198:1: ruleElkBendPoint
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
    // InternalLayoutConfig.g:5207:1: rule__ElkBendPoint__XAssignment_0 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5211:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5212:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5212:1: ( ruleNumber )
            // InternalLayoutConfig.g:5213:1: ruleNumber
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
    // InternalLayoutConfig.g:5222:1: rule__ElkBendPoint__YAssignment_2 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5226:1: ( ( ruleNumber ) )
            // InternalLayoutConfig.g:5227:1: ( ruleNumber )
            {
            // InternalLayoutConfig.g:5227:1: ( ruleNumber )
            // InternalLayoutConfig.g:5228:1: ruleNumber
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
    // InternalLayoutConfig.g:5237:1: rule__Property__KeyAssignment_0 : ( rulePropertyKey ) ;
    public final void rule__Property__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5241:1: ( ( rulePropertyKey ) )
            // InternalLayoutConfig.g:5242:1: ( rulePropertyKey )
            {
            // InternalLayoutConfig.g:5242:1: ( rulePropertyKey )
            // InternalLayoutConfig.g:5243:1: rulePropertyKey
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
    // InternalLayoutConfig.g:5252:1: rule__Property__ValueAssignment_2_0 : ( RULE_STRING ) ;
    public final void rule__Property__ValueAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5256:1: ( ( RULE_STRING ) )
            // InternalLayoutConfig.g:5257:1: ( RULE_STRING )
            {
            // InternalLayoutConfig.g:5257:1: ( RULE_STRING )
            // InternalLayoutConfig.g:5258:1: RULE_STRING
            {
             before(grammarAccess.getPropertyAccess().getValueSTRINGTerminalRuleCall_2_0_0()); 
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getValueSTRINGTerminalRuleCall_2_0_0()); 

            }


            }

        }
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
    // InternalLayoutConfig.g:5267:1: rule__Property__ValueAssignment_2_1 : ( ruleQualifiedId ) ;
    public final void rule__Property__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5271:1: ( ( ruleQualifiedId ) )
            // InternalLayoutConfig.g:5272:1: ( ruleQualifiedId )
            {
            // InternalLayoutConfig.g:5272:1: ( ruleQualifiedId )
            // InternalLayoutConfig.g:5273:1: ruleQualifiedId
            {
             before(grammarAccess.getPropertyAccess().getValueQualifiedIdParserRuleCall_2_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueQualifiedIdParserRuleCall_2_1_0()); 

            }


            }

        }
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
    // InternalLayoutConfig.g:5282:1: rule__Property__ValueAssignment_2_2 : ( ruleBoolean ) ;
    public final void rule__Property__ValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5286:1: ( ( ruleBoolean ) )
            // InternalLayoutConfig.g:5287:1: ( ruleBoolean )
            {
            // InternalLayoutConfig.g:5287:1: ( ruleBoolean )
            // InternalLayoutConfig.g:5288:1: ruleBoolean
            {
             before(grammarAccess.getPropertyAccess().getValueBooleanParserRuleCall_2_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
            ruleBoolean();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueBooleanParserRuleCall_2_2_0()); 

            }


            }

        }
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
    // InternalLayoutConfig.g:5297:1: rule__Property__ValueAssignment_2_3 : ( RULE_SIGNED_INT ) ;
    public final void rule__Property__ValueAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5301:1: ( ( RULE_SIGNED_INT ) )
            // InternalLayoutConfig.g:5302:1: ( RULE_SIGNED_INT )
            {
            // InternalLayoutConfig.g:5302:1: ( RULE_SIGNED_INT )
            // InternalLayoutConfig.g:5303:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getPropertyAccess().getValueSIGNED_INTTerminalRuleCall_2_3_0()); 
            match(input,RULE_SIGNED_INT,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getValueSIGNED_INTTerminalRuleCall_2_3_0()); 

            }


            }

        }
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


    // $ANTLR start "rule__Property__ValueAssignment_2_4"
    // InternalLayoutConfig.g:5312:1: rule__Property__ValueAssignment_2_4 : ( RULE_FLOAT ) ;
    public final void rule__Property__ValueAssignment_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:5316:1: ( ( RULE_FLOAT ) )
            // InternalLayoutConfig.g:5317:1: ( RULE_FLOAT )
            {
            // InternalLayoutConfig.g:5317:1: ( RULE_FLOAT )
            // InternalLayoutConfig.g:5318:1: RULE_FLOAT
            {
             before(grammarAccess.getPropertyAccess().getValueFLOATTerminalRuleCall_2_4_0()); 
            match(input,RULE_FLOAT,FollowSets000.FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getValueFLOATTerminalRuleCall_2_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Property__ValueAssignment_2_4"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000100000002L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000042L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000030040L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020002L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010040L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000000000C0L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000150040L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000040002L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000002800000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x000000017C000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000007C000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000002L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000200200000L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000001000002L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x00000000000060F0L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000400000002L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000002800002L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x000000007C000002L});
    }


}