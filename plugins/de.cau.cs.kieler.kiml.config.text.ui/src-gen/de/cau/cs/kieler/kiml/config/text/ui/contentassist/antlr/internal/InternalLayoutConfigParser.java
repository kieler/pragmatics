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
    public static final int RULE_TFLOAT=6;
    public static final int RULE_ID=8;
    public static final int RULE_WS=11;
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__15=15;
    public static final int RULE_NATURAL=7;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;

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




    // $ANTLR start "entryRuleKGraphElement"
    // InternalLayoutConfig.g:60:1: entryRuleKGraphElement : ruleKGraphElement EOF ;
    public final void entryRuleKGraphElement() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:61:1: ( ruleKGraphElement EOF )
            // InternalLayoutConfig.g:62:1: ruleKGraphElement EOF
            {
             before(grammarAccess.getKGraphElementRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleKGraphElement();

            state._fsp--;

             after(grammarAccess.getKGraphElementRule()); 
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
    // $ANTLR end "entryRuleKGraphElement"


    // $ANTLR start "ruleKGraphElement"
    // InternalLayoutConfig.g:69:1: ruleKGraphElement : ( ( rule__KGraphElement__Group__0 ) ) ;
    public final void ruleKGraphElement() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:73:2: ( ( ( rule__KGraphElement__Group__0 ) ) )
            // InternalLayoutConfig.g:74:1: ( ( rule__KGraphElement__Group__0 ) )
            {
            // InternalLayoutConfig.g:74:1: ( ( rule__KGraphElement__Group__0 ) )
            // InternalLayoutConfig.g:75:1: ( rule__KGraphElement__Group__0 )
            {
             before(grammarAccess.getKGraphElementAccess().getGroup()); 
            // InternalLayoutConfig.g:76:1: ( rule__KGraphElement__Group__0 )
            // InternalLayoutConfig.g:76:2: rule__KGraphElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:88:1: entryRuleKIdentifier : ruleKIdentifier EOF ;
    public final void entryRuleKIdentifier() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:89:1: ( ruleKIdentifier EOF )
            // InternalLayoutConfig.g:90:1: ruleKIdentifier EOF
            {
             before(grammarAccess.getKIdentifierRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKIdentifierRule()); 
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
    // $ANTLR end "entryRuleKIdentifier"


    // $ANTLR start "ruleKIdentifier"
    // InternalLayoutConfig.g:97:1: ruleKIdentifier : ( ( rule__KIdentifier__Group__0 ) ) ;
    public final void ruleKIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:101:2: ( ( ( rule__KIdentifier__Group__0 ) ) )
            // InternalLayoutConfig.g:102:1: ( ( rule__KIdentifier__Group__0 ) )
            {
            // InternalLayoutConfig.g:102:1: ( ( rule__KIdentifier__Group__0 ) )
            // InternalLayoutConfig.g:103:1: ( rule__KIdentifier__Group__0 )
            {
             before(grammarAccess.getKIdentifierAccess().getGroup()); 
            // InternalLayoutConfig.g:104:1: ( rule__KIdentifier__Group__0 )
            // InternalLayoutConfig.g:104:2: rule__KIdentifier__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:116:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:117:1: ( rulePersistentEntry EOF )
            // InternalLayoutConfig.g:118:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
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
    // $ANTLR end "entryRulePersistentEntry"


    // $ANTLR start "rulePersistentEntry"
    // InternalLayoutConfig.g:125:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:129:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // InternalLayoutConfig.g:130:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // InternalLayoutConfig.g:130:1: ( ( rule__PersistentEntry__Group__0 ) )
            // InternalLayoutConfig.g:131:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // InternalLayoutConfig.g:132:1: ( rule__PersistentEntry__Group__0 )
            // InternalLayoutConfig.g:132:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:144:1: entryRulePropertyValue : rulePropertyValue EOF ;
    public final void entryRulePropertyValue() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:145:1: ( rulePropertyValue EOF )
            // InternalLayoutConfig.g:146:1: rulePropertyValue EOF
            {
             before(grammarAccess.getPropertyValueRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPropertyValueRule()); 
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
    // $ANTLR end "entryRulePropertyValue"


    // $ANTLR start "rulePropertyValue"
    // InternalLayoutConfig.g:153:1: rulePropertyValue : ( ( rule__PropertyValue__Alternatives ) ) ;
    public final void rulePropertyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:157:2: ( ( ( rule__PropertyValue__Alternatives ) ) )
            // InternalLayoutConfig.g:158:1: ( ( rule__PropertyValue__Alternatives ) )
            {
            // InternalLayoutConfig.g:158:1: ( ( rule__PropertyValue__Alternatives ) )
            // InternalLayoutConfig.g:159:1: ( rule__PropertyValue__Alternatives )
            {
             before(grammarAccess.getPropertyValueAccess().getAlternatives()); 
            // InternalLayoutConfig.g:160:1: ( rule__PropertyValue__Alternatives )
            // InternalLayoutConfig.g:160:2: rule__PropertyValue__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:172:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:173:1: ( ruleQualifiedID EOF )
            // InternalLayoutConfig.g:174:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
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
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // InternalLayoutConfig.g:181:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:185:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // InternalLayoutConfig.g:186:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // InternalLayoutConfig.g:186:1: ( ( rule__QualifiedID__Group__0 ) )
            // InternalLayoutConfig.g:187:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // InternalLayoutConfig.g:188:1: ( rule__QualifiedID__Group__0 )
            // InternalLayoutConfig.g:188:2: rule__QualifiedID__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:200:1: entryRuleFloat : ruleFloat EOF ;
    public final void entryRuleFloat() throws RecognitionException {
        try {
            // InternalLayoutConfig.g:201:1: ( ruleFloat EOF )
            // InternalLayoutConfig.g:202:1: ruleFloat EOF
            {
             before(grammarAccess.getFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_1);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRule()); 
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
    // $ANTLR end "entryRuleFloat"


    // $ANTLR start "ruleFloat"
    // InternalLayoutConfig.g:209:1: ruleFloat : ( ( rule__Float__Alternatives ) ) ;
    public final void ruleFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:213:2: ( ( ( rule__Float__Alternatives ) ) )
            // InternalLayoutConfig.g:214:1: ( ( rule__Float__Alternatives ) )
            {
            // InternalLayoutConfig.g:214:1: ( ( rule__Float__Alternatives ) )
            // InternalLayoutConfig.g:215:1: ( rule__Float__Alternatives )
            {
             before(grammarAccess.getFloatAccess().getAlternatives()); 
            // InternalLayoutConfig.g:216:1: ( rule__Float__Alternatives )
            // InternalLayoutConfig.g:216:2: rule__Float__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:228:1: rule__PropertyValue__Alternatives : ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) );
    public final void rule__PropertyValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:232:1: ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) )
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
                    // InternalLayoutConfig.g:233:1: ( RULE_BOOLEAN )
                    {
                    // InternalLayoutConfig.g:233:1: ( RULE_BOOLEAN )
                    // InternalLayoutConfig.g:234:1: RULE_BOOLEAN
                    {
                     before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                    match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:239:6: ( RULE_STRING )
                    {
                    // InternalLayoutConfig.g:239:6: ( RULE_STRING )
                    // InternalLayoutConfig.g:240:1: RULE_STRING
                    {
                     before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalLayoutConfig.g:245:6: ( ruleFloat )
                    {
                    // InternalLayoutConfig.g:245:6: ( ruleFloat )
                    // InternalLayoutConfig.g:246:1: ruleFloat
                    {
                     before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleFloat();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalLayoutConfig.g:251:6: ( ruleQualifiedID )
                    {
                    // InternalLayoutConfig.g:251:6: ( ruleQualifiedID )
                    // InternalLayoutConfig.g:252:1: ruleQualifiedID
                    {
                     before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:262:1: rule__Float__Alternatives : ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) );
    public final void rule__Float__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:266:1: ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) )
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
                    // InternalLayoutConfig.g:267:1: ( RULE_TFLOAT )
                    {
                    // InternalLayoutConfig.g:267:1: ( RULE_TFLOAT )
                    // InternalLayoutConfig.g:268:1: RULE_TFLOAT
                    {
                     before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                    match(input,RULE_TFLOAT,FollowSets000.FOLLOW_2); 
                     after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:273:6: ( RULE_NATURAL )
                    {
                    // InternalLayoutConfig.g:273:6: ( RULE_NATURAL )
                    // InternalLayoutConfig.g:274:1: RULE_NATURAL
                    {
                     before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                    match(input,RULE_NATURAL,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:286:1: rule__KGraphElement__Group__0 : rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1 ;
    public final void rule__KGraphElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:290:1: ( rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1 )
            // InternalLayoutConfig.g:291:2: rule__KGraphElement__Group__0__Impl rule__KGraphElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__KGraphElement__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:298:1: rule__KGraphElement__Group__0__Impl : ( () ) ;
    public final void rule__KGraphElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:302:1: ( ( () ) )
            // InternalLayoutConfig.g:303:1: ( () )
            {
            // InternalLayoutConfig.g:303:1: ( () )
            // InternalLayoutConfig.g:304:1: ()
            {
             before(grammarAccess.getKGraphElementAccess().getKNodeAction_0()); 
            // InternalLayoutConfig.g:305:1: ()
            // InternalLayoutConfig.g:307:1: 
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
    // InternalLayoutConfig.g:317:1: rule__KGraphElement__Group__1 : rule__KGraphElement__Group__1__Impl ;
    public final void rule__KGraphElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:321:1: ( rule__KGraphElement__Group__1__Impl )
            // InternalLayoutConfig.g:322:2: rule__KGraphElement__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:328:1: rule__KGraphElement__Group__1__Impl : ( ( rule__KGraphElement__DataAssignment_1 )* ) ;
    public final void rule__KGraphElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:332:1: ( ( ( rule__KGraphElement__DataAssignment_1 )* ) )
            // InternalLayoutConfig.g:333:1: ( ( rule__KGraphElement__DataAssignment_1 )* )
            {
            // InternalLayoutConfig.g:333:1: ( ( rule__KGraphElement__DataAssignment_1 )* )
            // InternalLayoutConfig.g:334:1: ( rule__KGraphElement__DataAssignment_1 )*
            {
             before(grammarAccess.getKGraphElementAccess().getDataAssignment_1()); 
            // InternalLayoutConfig.g:335:1: ( rule__KGraphElement__DataAssignment_1 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalLayoutConfig.g:335:2: rule__KGraphElement__DataAssignment_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_4);
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
    // InternalLayoutConfig.g:349:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:353:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // InternalLayoutConfig.g:354:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:361:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:365:1: ( ( () ) )
            // InternalLayoutConfig.g:366:1: ( () )
            {
            // InternalLayoutConfig.g:366:1: ( () )
            // InternalLayoutConfig.g:367:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // InternalLayoutConfig.g:368:1: ()
            // InternalLayoutConfig.g:370:1: 
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
    // InternalLayoutConfig.g:380:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:384:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // InternalLayoutConfig.g:385:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_5);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:392:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:396:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // InternalLayoutConfig.g:397:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // InternalLayoutConfig.g:397:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // InternalLayoutConfig.g:398:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // InternalLayoutConfig.g:399:1: ( rule__KIdentifier__IdAssignment_1 )
            // InternalLayoutConfig.g:399:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:409:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:413:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // InternalLayoutConfig.g:414:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:421:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:425:1: ( ( '{' ) )
            // InternalLayoutConfig.g:426:1: ( '{' )
            {
            // InternalLayoutConfig.g:426:1: ( '{' )
            // InternalLayoutConfig.g:427:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,12,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:440:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:444:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // InternalLayoutConfig.g:445:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:452:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:456:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // InternalLayoutConfig.g:457:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // InternalLayoutConfig.g:457:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // InternalLayoutConfig.g:458:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // InternalLayoutConfig.g:459:1: ( rule__KIdentifier__Group_3__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalLayoutConfig.g:459:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:469:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:473:1: ( rule__KIdentifier__Group__4__Impl )
            // InternalLayoutConfig.g:474:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:480:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:484:1: ( ( '}' ) )
            // InternalLayoutConfig.g:485:1: ( '}' )
            {
            // InternalLayoutConfig.g:485:1: ( '}' )
            // InternalLayoutConfig.g:486:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,13,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:509:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:513:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // InternalLayoutConfig.g:514:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:521:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:525:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // InternalLayoutConfig.g:526:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // InternalLayoutConfig.g:526:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // InternalLayoutConfig.g:527:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // InternalLayoutConfig.g:528:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // InternalLayoutConfig.g:528:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:538:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:542:1: ( rule__KIdentifier__Group_3__1__Impl )
            // InternalLayoutConfig.g:543:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:549:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:553:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // InternalLayoutConfig.g:554:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // InternalLayoutConfig.g:554:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // InternalLayoutConfig.g:555:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // InternalLayoutConfig.g:556:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ID) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalLayoutConfig.g:556:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_4);
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
    // InternalLayoutConfig.g:570:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:574:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // InternalLayoutConfig.g:575:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:582:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:586:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // InternalLayoutConfig.g:587:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // InternalLayoutConfig.g:587:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // InternalLayoutConfig.g:588:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // InternalLayoutConfig.g:589:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // InternalLayoutConfig.g:589:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:599:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:603:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // InternalLayoutConfig.g:604:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:611:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:615:1: ( ( ':' ) )
            // InternalLayoutConfig.g:616:1: ( ':' )
            {
            // InternalLayoutConfig.g:616:1: ( ':' )
            // InternalLayoutConfig.g:617:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,14,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:630:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:634:1: ( rule__PersistentEntry__Group__2__Impl )
            // InternalLayoutConfig.g:635:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:641:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:645:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // InternalLayoutConfig.g:646:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // InternalLayoutConfig.g:646:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // InternalLayoutConfig.g:647:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // InternalLayoutConfig.g:648:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // InternalLayoutConfig.g:648:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:664:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:668:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // InternalLayoutConfig.g:669:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:676:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:680:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:681:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:681:1: ( RULE_ID )
            // InternalLayoutConfig.g:682:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:693:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:697:1: ( rule__QualifiedID__Group__1__Impl )
            // InternalLayoutConfig.g:698:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:704:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:708:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // InternalLayoutConfig.g:709:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // InternalLayoutConfig.g:709:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // InternalLayoutConfig.g:710:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // InternalLayoutConfig.g:711:1: ( rule__QualifiedID__Group_1__0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==15) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalLayoutConfig.g:711:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_10);
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
    // InternalLayoutConfig.g:725:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:729:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // InternalLayoutConfig.g:730:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:737:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:741:1: ( ( '.' ) )
            // InternalLayoutConfig.g:742:1: ( '.' )
            {
            // InternalLayoutConfig.g:742:1: ( '.' )
            // InternalLayoutConfig.g:743:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,15,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:756:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:760:1: ( rule__QualifiedID__Group_1__1__Impl )
            // InternalLayoutConfig.g:761:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:767:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:771:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:772:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:772:1: ( RULE_ID )
            // InternalLayoutConfig.g:773:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:789:1: rule__KGraphElement__DataAssignment_1 : ( ruleKIdentifier ) ;
    public final void rule__KGraphElement__DataAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:793:1: ( ( ruleKIdentifier ) )
            // InternalLayoutConfig.g:794:1: ( ruleKIdentifier )
            {
            // InternalLayoutConfig.g:794:1: ( ruleKIdentifier )
            // InternalLayoutConfig.g:795:1: ruleKIdentifier
            {
             before(grammarAccess.getKGraphElementAccess().getDataKIdentifierParserRuleCall_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:804:1: rule__KIdentifier__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:808:1: ( ( RULE_ID ) )
            // InternalLayoutConfig.g:809:1: ( RULE_ID )
            {
            // InternalLayoutConfig.g:809:1: ( RULE_ID )
            // InternalLayoutConfig.g:810:1: RULE_ID
            {
             before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FollowSets000.FOLLOW_2); 
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
    // InternalLayoutConfig.g:819:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:823:1: ( ( rulePersistentEntry ) )
            // InternalLayoutConfig.g:824:1: ( rulePersistentEntry )
            {
            // InternalLayoutConfig.g:824:1: ( rulePersistentEntry )
            // InternalLayoutConfig.g:825:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:834:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:838:1: ( ( rulePersistentEntry ) )
            // InternalLayoutConfig.g:839:1: ( rulePersistentEntry )
            {
            // InternalLayoutConfig.g:839:1: ( rulePersistentEntry )
            // InternalLayoutConfig.g:840:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:849:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:853:1: ( ( ruleQualifiedID ) )
            // InternalLayoutConfig.g:854:1: ( ruleQualifiedID )
            {
            // InternalLayoutConfig.g:854:1: ( ruleQualifiedID )
            // InternalLayoutConfig.g:855:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
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
    // InternalLayoutConfig.g:864:1: rule__PersistentEntry__ValueAssignment_2 : ( rulePropertyValue ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalLayoutConfig.g:868:1: ( ( rulePropertyValue ) )
            // InternalLayoutConfig.g:869:1: ( rulePropertyValue )
            {
            // InternalLayoutConfig.g:869:1: ( rulePropertyValue )
            // InternalLayoutConfig.g:870:1: rulePropertyValue
            {
             before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            pushFollow(FollowSets000.FOLLOW_2);
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
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000102L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002100L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000000001F0L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008002L});
    }


}