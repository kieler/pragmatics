package de.cau.cs.kieler.ksbase.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import de.cau.cs.kieler.ksbase.services.FeatureDefinitionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalFeatureDefinitionParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'ModelPackage = '", "';'", "'FeatureMenuTitle = '", "'ModelPackagePath = '", "'DiagramPackage = '", "'TransformationFile = '", "'Feature'", "'{'", "'MethodName = '", "'MenuEntry = '", "'}'", "'File = '", "'Parameter = '"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

        public InternalFeatureDefinitionParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g"; }


     
     	private FeatureDefinitionGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(FeatureDefinitionGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }




    // $ANTLR start entryRuleFeatureDefinitions
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:55:1: entryRuleFeatureDefinitions : ruleFeatureDefinitions EOF ;
    public final void entryRuleFeatureDefinitions() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:55:29: ( ruleFeatureDefinitions EOF )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:56:1: ruleFeatureDefinitions EOF
            {
             before(grammarAccess.getFeatureDefinitionsRule()); 
            pushFollow(FOLLOW_ruleFeatureDefinitions_in_entryRuleFeatureDefinitions60);
            ruleFeatureDefinitions();
            _fsp--;

             after(grammarAccess.getFeatureDefinitionsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureDefinitions67); 

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
    // $ANTLR end entryRuleFeatureDefinitions


    // $ANTLR start ruleFeatureDefinitions
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:63:1: ruleFeatureDefinitions : ( ( rule__FeatureDefinitions__Group__0 ) ) ;
    public final void ruleFeatureDefinitions() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:67:2: ( ( ( rule__FeatureDefinitions__Group__0 ) ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:68:1: ( ( rule__FeatureDefinitions__Group__0 ) )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:68:1: ( ( rule__FeatureDefinitions__Group__0 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:69:1: ( rule__FeatureDefinitions__Group__0 )
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getGroup()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:70:1: ( rule__FeatureDefinitions__Group__0 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:70:2: rule__FeatureDefinitions__Group__0
            {
            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__0_in_ruleFeatureDefinitions94);
            rule__FeatureDefinitions__Group__0();
            _fsp--;


            }

             after(grammarAccess.getFeatureDefinitionsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleFeatureDefinitions


    // $ANTLR start entryRuleFeatureType
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:82:1: entryRuleFeatureType : ruleFeatureType EOF ;
    public final void entryRuleFeatureType() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:82:22: ( ruleFeatureType EOF )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:83:1: ruleFeatureType EOF
            {
             before(grammarAccess.getFeatureTypeRule()); 
            pushFollow(FOLLOW_ruleFeatureType_in_entryRuleFeatureType120);
            ruleFeatureType();
            _fsp--;

             after(grammarAccess.getFeatureTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureType127); 

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
    // $ANTLR end entryRuleFeatureType


    // $ANTLR start ruleFeatureType
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:90:1: ruleFeatureType : ( ( rule__FeatureType__Group__0 ) ) ;
    public final void ruleFeatureType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:94:2: ( ( ( rule__FeatureType__Group__0 ) ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:95:1: ( ( rule__FeatureType__Group__0 ) )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:95:1: ( ( rule__FeatureType__Group__0 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:96:1: ( rule__FeatureType__Group__0 )
            {
             before(grammarAccess.getFeatureTypeAccess().getGroup()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:97:1: ( rule__FeatureType__Group__0 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:97:2: rule__FeatureType__Group__0
            {
            pushFollow(FOLLOW_rule__FeatureType__Group__0_in_ruleFeatureType154);
            rule__FeatureType__Group__0();
            _fsp--;


            }

             after(grammarAccess.getFeatureTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleFeatureType


    // $ANTLR start rule__FeatureDefinitions__Group__0
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:111:1: rule__FeatureDefinitions__Group__0 : ( 'ModelPackage = ' ) rule__FeatureDefinitions__Group__1 ;
    public final void rule__FeatureDefinitions__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:115:1: ( ( 'ModelPackage = ' ) rule__FeatureDefinitions__Group__1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:116:1: ( 'ModelPackage = ' ) rule__FeatureDefinitions__Group__1
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:116:1: ( 'ModelPackage = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:117:1: 'ModelPackage = '
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getModelPackageKeyword_0()); 
            match(input,11,FOLLOW_11_in_rule__FeatureDefinitions__Group__0193); 
             after(grammarAccess.getFeatureDefinitionsAccess().getModelPackageKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__1_in_rule__FeatureDefinitions__Group__0203);
            rule__FeatureDefinitions__Group__1();
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
    // $ANTLR end rule__FeatureDefinitions__Group__0


    // $ANTLR start rule__FeatureDefinitions__Group__1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:131:1: rule__FeatureDefinitions__Group__1 : ( ( rule__FeatureDefinitions__ModelNameAssignment_1 ) ) rule__FeatureDefinitions__Group__2 ;
    public final void rule__FeatureDefinitions__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:135:1: ( ( ( rule__FeatureDefinitions__ModelNameAssignment_1 ) ) rule__FeatureDefinitions__Group__2 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:136:1: ( ( rule__FeatureDefinitions__ModelNameAssignment_1 ) ) rule__FeatureDefinitions__Group__2
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:136:1: ( ( rule__FeatureDefinitions__ModelNameAssignment_1 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:137:1: ( rule__FeatureDefinitions__ModelNameAssignment_1 )
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getModelNameAssignment_1()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:138:1: ( rule__FeatureDefinitions__ModelNameAssignment_1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:138:2: rule__FeatureDefinitions__ModelNameAssignment_1
            {
            pushFollow(FOLLOW_rule__FeatureDefinitions__ModelNameAssignment_1_in_rule__FeatureDefinitions__Group__1231);
            rule__FeatureDefinitions__ModelNameAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getFeatureDefinitionsAccess().getModelNameAssignment_1()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__2_in_rule__FeatureDefinitions__Group__1240);
            rule__FeatureDefinitions__Group__2();
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
    // $ANTLR end rule__FeatureDefinitions__Group__1


    // $ANTLR start rule__FeatureDefinitions__Group__2
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:149:1: rule__FeatureDefinitions__Group__2 : ( ';' ) rule__FeatureDefinitions__Group__3 ;
    public final void rule__FeatureDefinitions__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:153:1: ( ( ';' ) rule__FeatureDefinitions__Group__3 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:154:1: ( ';' ) rule__FeatureDefinitions__Group__3
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:154:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:155:1: ';'
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_2()); 
            match(input,12,FOLLOW_12_in_rule__FeatureDefinitions__Group__2269); 
             after(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_2()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__3_in_rule__FeatureDefinitions__Group__2279);
            rule__FeatureDefinitions__Group__3();
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
    // $ANTLR end rule__FeatureDefinitions__Group__2


    // $ANTLR start rule__FeatureDefinitions__Group__3
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:169:1: rule__FeatureDefinitions__Group__3 : ( ( rule__FeatureDefinitions__Group_3__0 )? ) rule__FeatureDefinitions__Group__4 ;
    public final void rule__FeatureDefinitions__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:173:1: ( ( ( rule__FeatureDefinitions__Group_3__0 )? ) rule__FeatureDefinitions__Group__4 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:174:1: ( ( rule__FeatureDefinitions__Group_3__0 )? ) rule__FeatureDefinitions__Group__4
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:174:1: ( ( rule__FeatureDefinitions__Group_3__0 )? )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:175:1: ( rule__FeatureDefinitions__Group_3__0 )?
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:176:1: ( rule__FeatureDefinitions__Group_3__0 )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==14) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:176:2: rule__FeatureDefinitions__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__FeatureDefinitions__Group_3__0_in_rule__FeatureDefinitions__Group__3307);
                    rule__FeatureDefinitions__Group_3__0();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureDefinitionsAccess().getGroup_3()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__4_in_rule__FeatureDefinitions__Group__3317);
            rule__FeatureDefinitions__Group__4();
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
    // $ANTLR end rule__FeatureDefinitions__Group__3


    // $ANTLR start rule__FeatureDefinitions__Group__4
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:187:1: rule__FeatureDefinitions__Group__4 : ( ( rule__FeatureDefinitions__Group_4__0 )? ) rule__FeatureDefinitions__Group__5 ;
    public final void rule__FeatureDefinitions__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:191:1: ( ( ( rule__FeatureDefinitions__Group_4__0 )? ) rule__FeatureDefinitions__Group__5 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:192:1: ( ( rule__FeatureDefinitions__Group_4__0 )? ) rule__FeatureDefinitions__Group__5
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:192:1: ( ( rule__FeatureDefinitions__Group_4__0 )? )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:193:1: ( rule__FeatureDefinitions__Group_4__0 )?
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:194:1: ( rule__FeatureDefinitions__Group_4__0 )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==15) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:194:2: rule__FeatureDefinitions__Group_4__0
                    {
                    pushFollow(FOLLOW_rule__FeatureDefinitions__Group_4__0_in_rule__FeatureDefinitions__Group__4345);
                    rule__FeatureDefinitions__Group_4__0();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureDefinitionsAccess().getGroup_4()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__5_in_rule__FeatureDefinitions__Group__4355);
            rule__FeatureDefinitions__Group__5();
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
    // $ANTLR end rule__FeatureDefinitions__Group__4


    // $ANTLR start rule__FeatureDefinitions__Group__5
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:205:1: rule__FeatureDefinitions__Group__5 : ( 'FeatureMenuTitle = ' ) rule__FeatureDefinitions__Group__6 ;
    public final void rule__FeatureDefinitions__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:209:1: ( ( 'FeatureMenuTitle = ' ) rule__FeatureDefinitions__Group__6 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:210:1: ( 'FeatureMenuTitle = ' ) rule__FeatureDefinitions__Group__6
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:210:1: ( 'FeatureMenuTitle = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:211:1: 'FeatureMenuTitle = '
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleKeyword_5()); 
            match(input,13,FOLLOW_13_in_rule__FeatureDefinitions__Group__5384); 
             after(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleKeyword_5()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__6_in_rule__FeatureDefinitions__Group__5394);
            rule__FeatureDefinitions__Group__6();
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
    // $ANTLR end rule__FeatureDefinitions__Group__5


    // $ANTLR start rule__FeatureDefinitions__Group__6
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:225:1: rule__FeatureDefinitions__Group__6 : ( ( rule__FeatureDefinitions__FeatureMenuTitleAssignment_6 ) ) rule__FeatureDefinitions__Group__7 ;
    public final void rule__FeatureDefinitions__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:229:1: ( ( ( rule__FeatureDefinitions__FeatureMenuTitleAssignment_6 ) ) rule__FeatureDefinitions__Group__7 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:230:1: ( ( rule__FeatureDefinitions__FeatureMenuTitleAssignment_6 ) ) rule__FeatureDefinitions__Group__7
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:230:1: ( ( rule__FeatureDefinitions__FeatureMenuTitleAssignment_6 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:231:1: ( rule__FeatureDefinitions__FeatureMenuTitleAssignment_6 )
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleAssignment_6()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:232:1: ( rule__FeatureDefinitions__FeatureMenuTitleAssignment_6 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:232:2: rule__FeatureDefinitions__FeatureMenuTitleAssignment_6
            {
            pushFollow(FOLLOW_rule__FeatureDefinitions__FeatureMenuTitleAssignment_6_in_rule__FeatureDefinitions__Group__6422);
            rule__FeatureDefinitions__FeatureMenuTitleAssignment_6();
            _fsp--;


            }

             after(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleAssignment_6()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__7_in_rule__FeatureDefinitions__Group__6431);
            rule__FeatureDefinitions__Group__7();
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
    // $ANTLR end rule__FeatureDefinitions__Group__6


    // $ANTLR start rule__FeatureDefinitions__Group__7
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:243:1: rule__FeatureDefinitions__Group__7 : ( ';' ) rule__FeatureDefinitions__Group__8 ;
    public final void rule__FeatureDefinitions__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:247:1: ( ( ';' ) rule__FeatureDefinitions__Group__8 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:248:1: ( ';' ) rule__FeatureDefinitions__Group__8
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:248:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:249:1: ';'
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_7()); 
            match(input,12,FOLLOW_12_in_rule__FeatureDefinitions__Group__7460); 
             after(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_7()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__8_in_rule__FeatureDefinitions__Group__7470);
            rule__FeatureDefinitions__Group__8();
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
    // $ANTLR end rule__FeatureDefinitions__Group__7


    // $ANTLR start rule__FeatureDefinitions__Group__8
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:263:1: rule__FeatureDefinitions__Group__8 : ( ( rule__FeatureDefinitions__Group_8__0 )? ) rule__FeatureDefinitions__Group__9 ;
    public final void rule__FeatureDefinitions__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:267:1: ( ( ( rule__FeatureDefinitions__Group_8__0 )? ) rule__FeatureDefinitions__Group__9 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:268:1: ( ( rule__FeatureDefinitions__Group_8__0 )? ) rule__FeatureDefinitions__Group__9
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:268:1: ( ( rule__FeatureDefinitions__Group_8__0 )? )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:269:1: ( rule__FeatureDefinitions__Group_8__0 )?
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getGroup_8()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:270:1: ( rule__FeatureDefinitions__Group_8__0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==16) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:270:2: rule__FeatureDefinitions__Group_8__0
                    {
                    pushFollow(FOLLOW_rule__FeatureDefinitions__Group_8__0_in_rule__FeatureDefinitions__Group__8498);
                    rule__FeatureDefinitions__Group_8__0();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureDefinitionsAccess().getGroup_8()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group__9_in_rule__FeatureDefinitions__Group__8508);
            rule__FeatureDefinitions__Group__9();
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
    // $ANTLR end rule__FeatureDefinitions__Group__8


    // $ANTLR start rule__FeatureDefinitions__Group__9
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:281:1: rule__FeatureDefinitions__Group__9 : ( ( rule__FeatureDefinitions__ElementsAssignment_9 )* ) ;
    public final void rule__FeatureDefinitions__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:285:1: ( ( ( rule__FeatureDefinitions__ElementsAssignment_9 )* ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:286:1: ( ( rule__FeatureDefinitions__ElementsAssignment_9 )* )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:286:1: ( ( rule__FeatureDefinitions__ElementsAssignment_9 )* )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:287:1: ( rule__FeatureDefinitions__ElementsAssignment_9 )*
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getElementsAssignment_9()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:288:1: ( rule__FeatureDefinitions__ElementsAssignment_9 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:288:2: rule__FeatureDefinitions__ElementsAssignment_9
            	    {
            	    pushFollow(FOLLOW_rule__FeatureDefinitions__ElementsAssignment_9_in_rule__FeatureDefinitions__Group__9536);
            	    rule__FeatureDefinitions__ElementsAssignment_9();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getFeatureDefinitionsAccess().getElementsAssignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__Group__9


    // $ANTLR start rule__FeatureDefinitions__Group_3__0
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:318:1: rule__FeatureDefinitions__Group_3__0 : ( 'ModelPackagePath = ' ) rule__FeatureDefinitions__Group_3__1 ;
    public final void rule__FeatureDefinitions__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:322:1: ( ( 'ModelPackagePath = ' ) rule__FeatureDefinitions__Group_3__1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:323:1: ( 'ModelPackagePath = ' ) rule__FeatureDefinitions__Group_3__1
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:323:1: ( 'ModelPackagePath = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:324:1: 'ModelPackagePath = '
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getModelPackagePathKeyword_3_0()); 
            match(input,14,FOLLOW_14_in_rule__FeatureDefinitions__Group_3__0592); 
             after(grammarAccess.getFeatureDefinitionsAccess().getModelPackagePathKeyword_3_0()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group_3__1_in_rule__FeatureDefinitions__Group_3__0602);
            rule__FeatureDefinitions__Group_3__1();
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
    // $ANTLR end rule__FeatureDefinitions__Group_3__0


    // $ANTLR start rule__FeatureDefinitions__Group_3__1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:338:1: rule__FeatureDefinitions__Group_3__1 : ( ( rule__FeatureDefinitions__ModelPathAssignment_3_1 ) ) rule__FeatureDefinitions__Group_3__2 ;
    public final void rule__FeatureDefinitions__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:342:1: ( ( ( rule__FeatureDefinitions__ModelPathAssignment_3_1 ) ) rule__FeatureDefinitions__Group_3__2 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:343:1: ( ( rule__FeatureDefinitions__ModelPathAssignment_3_1 ) ) rule__FeatureDefinitions__Group_3__2
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:343:1: ( ( rule__FeatureDefinitions__ModelPathAssignment_3_1 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:344:1: ( rule__FeatureDefinitions__ModelPathAssignment_3_1 )
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getModelPathAssignment_3_1()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:345:1: ( rule__FeatureDefinitions__ModelPathAssignment_3_1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:345:2: rule__FeatureDefinitions__ModelPathAssignment_3_1
            {
            pushFollow(FOLLOW_rule__FeatureDefinitions__ModelPathAssignment_3_1_in_rule__FeatureDefinitions__Group_3__1630);
            rule__FeatureDefinitions__ModelPathAssignment_3_1();
            _fsp--;


            }

             after(grammarAccess.getFeatureDefinitionsAccess().getModelPathAssignment_3_1()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group_3__2_in_rule__FeatureDefinitions__Group_3__1639);
            rule__FeatureDefinitions__Group_3__2();
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
    // $ANTLR end rule__FeatureDefinitions__Group_3__1


    // $ANTLR start rule__FeatureDefinitions__Group_3__2
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:356:1: rule__FeatureDefinitions__Group_3__2 : ( ';' ) ;
    public final void rule__FeatureDefinitions__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:360:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:361:1: ( ';' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:361:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:362:1: ';'
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_3_2()); 
            match(input,12,FOLLOW_12_in_rule__FeatureDefinitions__Group_3__2668); 
             after(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__Group_3__2


    // $ANTLR start rule__FeatureDefinitions__Group_4__0
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:381:1: rule__FeatureDefinitions__Group_4__0 : ( 'DiagramPackage = ' ) rule__FeatureDefinitions__Group_4__1 ;
    public final void rule__FeatureDefinitions__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:385:1: ( ( 'DiagramPackage = ' ) rule__FeatureDefinitions__Group_4__1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:386:1: ( 'DiagramPackage = ' ) rule__FeatureDefinitions__Group_4__1
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:386:1: ( 'DiagramPackage = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:387:1: 'DiagramPackage = '
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getDiagramPackageKeyword_4_0()); 
            match(input,15,FOLLOW_15_in_rule__FeatureDefinitions__Group_4__0710); 
             after(grammarAccess.getFeatureDefinitionsAccess().getDiagramPackageKeyword_4_0()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group_4__1_in_rule__FeatureDefinitions__Group_4__0720);
            rule__FeatureDefinitions__Group_4__1();
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
    // $ANTLR end rule__FeatureDefinitions__Group_4__0


    // $ANTLR start rule__FeatureDefinitions__Group_4__1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:401:1: rule__FeatureDefinitions__Group_4__1 : ( ( rule__FeatureDefinitions__DiagramPackageAssignment_4_1 ) ) rule__FeatureDefinitions__Group_4__2 ;
    public final void rule__FeatureDefinitions__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:405:1: ( ( ( rule__FeatureDefinitions__DiagramPackageAssignment_4_1 ) ) rule__FeatureDefinitions__Group_4__2 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:406:1: ( ( rule__FeatureDefinitions__DiagramPackageAssignment_4_1 ) ) rule__FeatureDefinitions__Group_4__2
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:406:1: ( ( rule__FeatureDefinitions__DiagramPackageAssignment_4_1 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:407:1: ( rule__FeatureDefinitions__DiagramPackageAssignment_4_1 )
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getDiagramPackageAssignment_4_1()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:408:1: ( rule__FeatureDefinitions__DiagramPackageAssignment_4_1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:408:2: rule__FeatureDefinitions__DiagramPackageAssignment_4_1
            {
            pushFollow(FOLLOW_rule__FeatureDefinitions__DiagramPackageAssignment_4_1_in_rule__FeatureDefinitions__Group_4__1748);
            rule__FeatureDefinitions__DiagramPackageAssignment_4_1();
            _fsp--;


            }

             after(grammarAccess.getFeatureDefinitionsAccess().getDiagramPackageAssignment_4_1()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group_4__2_in_rule__FeatureDefinitions__Group_4__1757);
            rule__FeatureDefinitions__Group_4__2();
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
    // $ANTLR end rule__FeatureDefinitions__Group_4__1


    // $ANTLR start rule__FeatureDefinitions__Group_4__2
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:419:1: rule__FeatureDefinitions__Group_4__2 : ( ';' ) ;
    public final void rule__FeatureDefinitions__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:423:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:424:1: ( ';' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:424:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:425:1: ';'
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_4_2()); 
            match(input,12,FOLLOW_12_in_rule__FeatureDefinitions__Group_4__2786); 
             after(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__Group_4__2


    // $ANTLR start rule__FeatureDefinitions__Group_8__0
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:444:1: rule__FeatureDefinitions__Group_8__0 : ( 'TransformationFile = ' ) rule__FeatureDefinitions__Group_8__1 ;
    public final void rule__FeatureDefinitions__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:448:1: ( ( 'TransformationFile = ' ) rule__FeatureDefinitions__Group_8__1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:449:1: ( 'TransformationFile = ' ) rule__FeatureDefinitions__Group_8__1
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:449:1: ( 'TransformationFile = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:450:1: 'TransformationFile = '
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getTransformationFileKeyword_8_0()); 
            match(input,16,FOLLOW_16_in_rule__FeatureDefinitions__Group_8__0828); 
             after(grammarAccess.getFeatureDefinitionsAccess().getTransformationFileKeyword_8_0()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group_8__1_in_rule__FeatureDefinitions__Group_8__0838);
            rule__FeatureDefinitions__Group_8__1();
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
    // $ANTLR end rule__FeatureDefinitions__Group_8__0


    // $ANTLR start rule__FeatureDefinitions__Group_8__1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:464:1: rule__FeatureDefinitions__Group_8__1 : ( ( rule__FeatureDefinitions__FeatureFileAssignment_8_1 ) ) rule__FeatureDefinitions__Group_8__2 ;
    public final void rule__FeatureDefinitions__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:468:1: ( ( ( rule__FeatureDefinitions__FeatureFileAssignment_8_1 ) ) rule__FeatureDefinitions__Group_8__2 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:469:1: ( ( rule__FeatureDefinitions__FeatureFileAssignment_8_1 ) ) rule__FeatureDefinitions__Group_8__2
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:469:1: ( ( rule__FeatureDefinitions__FeatureFileAssignment_8_1 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:470:1: ( rule__FeatureDefinitions__FeatureFileAssignment_8_1 )
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getFeatureFileAssignment_8_1()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:471:1: ( rule__FeatureDefinitions__FeatureFileAssignment_8_1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:471:2: rule__FeatureDefinitions__FeatureFileAssignment_8_1
            {
            pushFollow(FOLLOW_rule__FeatureDefinitions__FeatureFileAssignment_8_1_in_rule__FeatureDefinitions__Group_8__1866);
            rule__FeatureDefinitions__FeatureFileAssignment_8_1();
            _fsp--;


            }

             after(grammarAccess.getFeatureDefinitionsAccess().getFeatureFileAssignment_8_1()); 

            }

            pushFollow(FOLLOW_rule__FeatureDefinitions__Group_8__2_in_rule__FeatureDefinitions__Group_8__1875);
            rule__FeatureDefinitions__Group_8__2();
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
    // $ANTLR end rule__FeatureDefinitions__Group_8__1


    // $ANTLR start rule__FeatureDefinitions__Group_8__2
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:482:1: rule__FeatureDefinitions__Group_8__2 : ( ';' ) ;
    public final void rule__FeatureDefinitions__Group_8__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:486:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:487:1: ( ';' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:487:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:488:1: ';'
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_8_2()); 
            match(input,12,FOLLOW_12_in_rule__FeatureDefinitions__Group_8__2904); 
             after(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_8_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__Group_8__2


    // $ANTLR start rule__FeatureType__Group__0
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:507:1: rule__FeatureType__Group__0 : ( 'Feature' ) rule__FeatureType__Group__1 ;
    public final void rule__FeatureType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:511:1: ( ( 'Feature' ) rule__FeatureType__Group__1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:512:1: ( 'Feature' ) rule__FeatureType__Group__1
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:512:1: ( 'Feature' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:513:1: 'Feature'
            {
             before(grammarAccess.getFeatureTypeAccess().getFeatureKeyword_0()); 
            match(input,17,FOLLOW_17_in_rule__FeatureType__Group__0946); 
             after(grammarAccess.getFeatureTypeAccess().getFeatureKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__1_in_rule__FeatureType__Group__0956);
            rule__FeatureType__Group__1();
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
    // $ANTLR end rule__FeatureType__Group__0


    // $ANTLR start rule__FeatureType__Group__1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:527:1: rule__FeatureType__Group__1 : ( ( rule__FeatureType__NameAssignment_1 ) ) rule__FeatureType__Group__2 ;
    public final void rule__FeatureType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:531:1: ( ( ( rule__FeatureType__NameAssignment_1 ) ) rule__FeatureType__Group__2 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:532:1: ( ( rule__FeatureType__NameAssignment_1 ) ) rule__FeatureType__Group__2
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:532:1: ( ( rule__FeatureType__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:533:1: ( rule__FeatureType__NameAssignment_1 )
            {
             before(grammarAccess.getFeatureTypeAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:534:1: ( rule__FeatureType__NameAssignment_1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:534:2: rule__FeatureType__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__FeatureType__NameAssignment_1_in_rule__FeatureType__Group__1984);
            rule__FeatureType__NameAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getFeatureTypeAccess().getNameAssignment_1()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__2_in_rule__FeatureType__Group__1993);
            rule__FeatureType__Group__2();
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
    // $ANTLR end rule__FeatureType__Group__1


    // $ANTLR start rule__FeatureType__Group__2
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:545:1: rule__FeatureType__Group__2 : ( '{' ) rule__FeatureType__Group__3 ;
    public final void rule__FeatureType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:549:1: ( ( '{' ) rule__FeatureType__Group__3 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:550:1: ( '{' ) rule__FeatureType__Group__3
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:550:1: ( '{' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:551:1: '{'
            {
             before(grammarAccess.getFeatureTypeAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,18,FOLLOW_18_in_rule__FeatureType__Group__21022); 
             after(grammarAccess.getFeatureTypeAccess().getLeftCurlyBracketKeyword_2()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__3_in_rule__FeatureType__Group__21032);
            rule__FeatureType__Group__3();
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
    // $ANTLR end rule__FeatureType__Group__2


    // $ANTLR start rule__FeatureType__Group__3
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:565:1: rule__FeatureType__Group__3 : ( ( rule__FeatureType__Group_3__0 )? ) rule__FeatureType__Group__4 ;
    public final void rule__FeatureType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:569:1: ( ( ( rule__FeatureType__Group_3__0 )? ) rule__FeatureType__Group__4 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:570:1: ( ( rule__FeatureType__Group_3__0 )? ) rule__FeatureType__Group__4
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:570:1: ( ( rule__FeatureType__Group_3__0 )? )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:571:1: ( rule__FeatureType__Group_3__0 )?
            {
             before(grammarAccess.getFeatureTypeAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:572:1: ( rule__FeatureType__Group_3__0 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==22) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:572:2: rule__FeatureType__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__FeatureType__Group_3__0_in_rule__FeatureType__Group__31060);
                    rule__FeatureType__Group_3__0();
                    _fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFeatureTypeAccess().getGroup_3()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__4_in_rule__FeatureType__Group__31070);
            rule__FeatureType__Group__4();
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
    // $ANTLR end rule__FeatureType__Group__3


    // $ANTLR start rule__FeatureType__Group__4
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:583:1: rule__FeatureType__Group__4 : ( 'MethodName = ' ) rule__FeatureType__Group__5 ;
    public final void rule__FeatureType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:587:1: ( ( 'MethodName = ' ) rule__FeatureType__Group__5 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:588:1: ( 'MethodName = ' ) rule__FeatureType__Group__5
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:588:1: ( 'MethodName = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:589:1: 'MethodName = '
            {
             before(grammarAccess.getFeatureTypeAccess().getMethodNameKeyword_4()); 
            match(input,19,FOLLOW_19_in_rule__FeatureType__Group__41099); 
             after(grammarAccess.getFeatureTypeAccess().getMethodNameKeyword_4()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__5_in_rule__FeatureType__Group__41109);
            rule__FeatureType__Group__5();
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
    // $ANTLR end rule__FeatureType__Group__4


    // $ANTLR start rule__FeatureType__Group__5
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:603:1: rule__FeatureType__Group__5 : ( ( rule__FeatureType__MethodNameAssignment_5 ) ) rule__FeatureType__Group__6 ;
    public final void rule__FeatureType__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:607:1: ( ( ( rule__FeatureType__MethodNameAssignment_5 ) ) rule__FeatureType__Group__6 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:608:1: ( ( rule__FeatureType__MethodNameAssignment_5 ) ) rule__FeatureType__Group__6
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:608:1: ( ( rule__FeatureType__MethodNameAssignment_5 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:609:1: ( rule__FeatureType__MethodNameAssignment_5 )
            {
             before(grammarAccess.getFeatureTypeAccess().getMethodNameAssignment_5()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:610:1: ( rule__FeatureType__MethodNameAssignment_5 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:610:2: rule__FeatureType__MethodNameAssignment_5
            {
            pushFollow(FOLLOW_rule__FeatureType__MethodNameAssignment_5_in_rule__FeatureType__Group__51137);
            rule__FeatureType__MethodNameAssignment_5();
            _fsp--;


            }

             after(grammarAccess.getFeatureTypeAccess().getMethodNameAssignment_5()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__6_in_rule__FeatureType__Group__51146);
            rule__FeatureType__Group__6();
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
    // $ANTLR end rule__FeatureType__Group__5


    // $ANTLR start rule__FeatureType__Group__6
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:621:1: rule__FeatureType__Group__6 : ( ';' ) rule__FeatureType__Group__7 ;
    public final void rule__FeatureType__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:625:1: ( ( ';' ) rule__FeatureType__Group__7 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:626:1: ( ';' ) rule__FeatureType__Group__7
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:626:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:627:1: ';'
            {
             before(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_6()); 
            match(input,12,FOLLOW_12_in_rule__FeatureType__Group__61175); 
             after(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_6()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__7_in_rule__FeatureType__Group__61185);
            rule__FeatureType__Group__7();
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
    // $ANTLR end rule__FeatureType__Group__6


    // $ANTLR start rule__FeatureType__Group__7
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:641:1: rule__FeatureType__Group__7 : ( ( ( rule__FeatureType__Group_7__0 ) ) ( ( rule__FeatureType__Group_7__0 )* ) ) rule__FeatureType__Group__8 ;
    public final void rule__FeatureType__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:645:1: ( ( ( ( rule__FeatureType__Group_7__0 ) ) ( ( rule__FeatureType__Group_7__0 )* ) ) rule__FeatureType__Group__8 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:646:1: ( ( ( rule__FeatureType__Group_7__0 ) ) ( ( rule__FeatureType__Group_7__0 )* ) ) rule__FeatureType__Group__8
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:646:1: ( ( ( rule__FeatureType__Group_7__0 ) ) ( ( rule__FeatureType__Group_7__0 )* ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:647:1: ( ( rule__FeatureType__Group_7__0 ) ) ( ( rule__FeatureType__Group_7__0 )* )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:647:1: ( ( rule__FeatureType__Group_7__0 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:648:1: ( rule__FeatureType__Group_7__0 )
            {
             before(grammarAccess.getFeatureTypeAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:649:1: ( rule__FeatureType__Group_7__0 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:649:2: rule__FeatureType__Group_7__0
            {
            pushFollow(FOLLOW_rule__FeatureType__Group_7__0_in_rule__FeatureType__Group__71215);
            rule__FeatureType__Group_7__0();
            _fsp--;


            }

             after(grammarAccess.getFeatureTypeAccess().getGroup_7()); 

            }

            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:652:1: ( ( rule__FeatureType__Group_7__0 )* )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:653:1: ( rule__FeatureType__Group_7__0 )*
            {
             before(grammarAccess.getFeatureTypeAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:654:1: ( rule__FeatureType__Group_7__0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==23) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:654:2: rule__FeatureType__Group_7__0
            	    {
            	    pushFollow(FOLLOW_rule__FeatureType__Group_7__0_in_rule__FeatureType__Group__71227);
            	    rule__FeatureType__Group_7__0();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getFeatureTypeAccess().getGroup_7()); 

            }


            }

            pushFollow(FOLLOW_rule__FeatureType__Group__8_in_rule__FeatureType__Group__71239);
            rule__FeatureType__Group__8();
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
    // $ANTLR end rule__FeatureType__Group__7


    // $ANTLR start rule__FeatureType__Group__8
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:666:1: rule__FeatureType__Group__8 : ( 'MenuEntry = ' ) rule__FeatureType__Group__9 ;
    public final void rule__FeatureType__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:670:1: ( ( 'MenuEntry = ' ) rule__FeatureType__Group__9 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:671:1: ( 'MenuEntry = ' ) rule__FeatureType__Group__9
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:671:1: ( 'MenuEntry = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:672:1: 'MenuEntry = '
            {
             before(grammarAccess.getFeatureTypeAccess().getMenuEntryKeyword_8()); 
            match(input,20,FOLLOW_20_in_rule__FeatureType__Group__81268); 
             after(grammarAccess.getFeatureTypeAccess().getMenuEntryKeyword_8()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__9_in_rule__FeatureType__Group__81278);
            rule__FeatureType__Group__9();
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
    // $ANTLR end rule__FeatureType__Group__8


    // $ANTLR start rule__FeatureType__Group__9
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:686:1: rule__FeatureType__Group__9 : ( ( rule__FeatureType__MenuEntryAssignment_9 ) ) rule__FeatureType__Group__10 ;
    public final void rule__FeatureType__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:690:1: ( ( ( rule__FeatureType__MenuEntryAssignment_9 ) ) rule__FeatureType__Group__10 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:691:1: ( ( rule__FeatureType__MenuEntryAssignment_9 ) ) rule__FeatureType__Group__10
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:691:1: ( ( rule__FeatureType__MenuEntryAssignment_9 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:692:1: ( rule__FeatureType__MenuEntryAssignment_9 )
            {
             before(grammarAccess.getFeatureTypeAccess().getMenuEntryAssignment_9()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:693:1: ( rule__FeatureType__MenuEntryAssignment_9 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:693:2: rule__FeatureType__MenuEntryAssignment_9
            {
            pushFollow(FOLLOW_rule__FeatureType__MenuEntryAssignment_9_in_rule__FeatureType__Group__91306);
            rule__FeatureType__MenuEntryAssignment_9();
            _fsp--;


            }

             after(grammarAccess.getFeatureTypeAccess().getMenuEntryAssignment_9()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__10_in_rule__FeatureType__Group__91315);
            rule__FeatureType__Group__10();
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
    // $ANTLR end rule__FeatureType__Group__9


    // $ANTLR start rule__FeatureType__Group__10
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:704:1: rule__FeatureType__Group__10 : ( ';' ) rule__FeatureType__Group__11 ;
    public final void rule__FeatureType__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:708:1: ( ( ';' ) rule__FeatureType__Group__11 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:709:1: ( ';' ) rule__FeatureType__Group__11
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:709:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:710:1: ';'
            {
             before(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_10()); 
            match(input,12,FOLLOW_12_in_rule__FeatureType__Group__101344); 
             after(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_10()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group__11_in_rule__FeatureType__Group__101354);
            rule__FeatureType__Group__11();
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
    // $ANTLR end rule__FeatureType__Group__10


    // $ANTLR start rule__FeatureType__Group__11
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:724:1: rule__FeatureType__Group__11 : ( '}' ) ;
    public final void rule__FeatureType__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:728:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:729:1: ( '}' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:729:1: ( '}' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:730:1: '}'
            {
             before(grammarAccess.getFeatureTypeAccess().getRightCurlyBracketKeyword_11()); 
            match(input,21,FOLLOW_21_in_rule__FeatureType__Group__111383); 
             after(grammarAccess.getFeatureTypeAccess().getRightCurlyBracketKeyword_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__Group__11


    // $ANTLR start rule__FeatureType__Group_3__0
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:767:1: rule__FeatureType__Group_3__0 : ( 'File = ' ) rule__FeatureType__Group_3__1 ;
    public final void rule__FeatureType__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:771:1: ( ( 'File = ' ) rule__FeatureType__Group_3__1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:772:1: ( 'File = ' ) rule__FeatureType__Group_3__1
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:772:1: ( 'File = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:773:1: 'File = '
            {
             before(grammarAccess.getFeatureTypeAccess().getFileKeyword_3_0()); 
            match(input,22,FOLLOW_22_in_rule__FeatureType__Group_3__01443); 
             after(grammarAccess.getFeatureTypeAccess().getFileKeyword_3_0()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group_3__1_in_rule__FeatureType__Group_3__01453);
            rule__FeatureType__Group_3__1();
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
    // $ANTLR end rule__FeatureType__Group_3__0


    // $ANTLR start rule__FeatureType__Group_3__1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:787:1: rule__FeatureType__Group_3__1 : ( ( rule__FeatureType__FileNameAssignment_3_1 ) ) rule__FeatureType__Group_3__2 ;
    public final void rule__FeatureType__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:791:1: ( ( ( rule__FeatureType__FileNameAssignment_3_1 ) ) rule__FeatureType__Group_3__2 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:792:1: ( ( rule__FeatureType__FileNameAssignment_3_1 ) ) rule__FeatureType__Group_3__2
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:792:1: ( ( rule__FeatureType__FileNameAssignment_3_1 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:793:1: ( rule__FeatureType__FileNameAssignment_3_1 )
            {
             before(grammarAccess.getFeatureTypeAccess().getFileNameAssignment_3_1()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:794:1: ( rule__FeatureType__FileNameAssignment_3_1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:794:2: rule__FeatureType__FileNameAssignment_3_1
            {
            pushFollow(FOLLOW_rule__FeatureType__FileNameAssignment_3_1_in_rule__FeatureType__Group_3__11481);
            rule__FeatureType__FileNameAssignment_3_1();
            _fsp--;


            }

             after(grammarAccess.getFeatureTypeAccess().getFileNameAssignment_3_1()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group_3__2_in_rule__FeatureType__Group_3__11490);
            rule__FeatureType__Group_3__2();
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
    // $ANTLR end rule__FeatureType__Group_3__1


    // $ANTLR start rule__FeatureType__Group_3__2
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:805:1: rule__FeatureType__Group_3__2 : ( ';' ) ;
    public final void rule__FeatureType__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:809:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:810:1: ( ';' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:810:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:811:1: ';'
            {
             before(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_3_2()); 
            match(input,12,FOLLOW_12_in_rule__FeatureType__Group_3__21519); 
             after(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__Group_3__2


    // $ANTLR start rule__FeatureType__Group_7__0
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:830:1: rule__FeatureType__Group_7__0 : ( 'Parameter = ' ) rule__FeatureType__Group_7__1 ;
    public final void rule__FeatureType__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:834:1: ( ( 'Parameter = ' ) rule__FeatureType__Group_7__1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:835:1: ( 'Parameter = ' ) rule__FeatureType__Group_7__1
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:835:1: ( 'Parameter = ' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:836:1: 'Parameter = '
            {
             before(grammarAccess.getFeatureTypeAccess().getParameterKeyword_7_0()); 
            match(input,23,FOLLOW_23_in_rule__FeatureType__Group_7__01561); 
             after(grammarAccess.getFeatureTypeAccess().getParameterKeyword_7_0()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group_7__1_in_rule__FeatureType__Group_7__01571);
            rule__FeatureType__Group_7__1();
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
    // $ANTLR end rule__FeatureType__Group_7__0


    // $ANTLR start rule__FeatureType__Group_7__1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:850:1: rule__FeatureType__Group_7__1 : ( ( rule__FeatureType__ParameterAssignment_7_1 ) ) rule__FeatureType__Group_7__2 ;
    public final void rule__FeatureType__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:854:1: ( ( ( rule__FeatureType__ParameterAssignment_7_1 ) ) rule__FeatureType__Group_7__2 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:855:1: ( ( rule__FeatureType__ParameterAssignment_7_1 ) ) rule__FeatureType__Group_7__2
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:855:1: ( ( rule__FeatureType__ParameterAssignment_7_1 ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:856:1: ( rule__FeatureType__ParameterAssignment_7_1 )
            {
             before(grammarAccess.getFeatureTypeAccess().getParameterAssignment_7_1()); 
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:857:1: ( rule__FeatureType__ParameterAssignment_7_1 )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:857:2: rule__FeatureType__ParameterAssignment_7_1
            {
            pushFollow(FOLLOW_rule__FeatureType__ParameterAssignment_7_1_in_rule__FeatureType__Group_7__11599);
            rule__FeatureType__ParameterAssignment_7_1();
            _fsp--;


            }

             after(grammarAccess.getFeatureTypeAccess().getParameterAssignment_7_1()); 

            }

            pushFollow(FOLLOW_rule__FeatureType__Group_7__2_in_rule__FeatureType__Group_7__11608);
            rule__FeatureType__Group_7__2();
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
    // $ANTLR end rule__FeatureType__Group_7__1


    // $ANTLR start rule__FeatureType__Group_7__2
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:868:1: rule__FeatureType__Group_7__2 : ( ';' ) ;
    public final void rule__FeatureType__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:872:1: ( ( ';' ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:873:1: ( ';' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:873:1: ( ';' )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:874:1: ';'
            {
             before(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_7_2()); 
            match(input,12,FOLLOW_12_in_rule__FeatureType__Group_7__21637); 
             after(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_7_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__Group_7__2


    // $ANTLR start rule__FeatureDefinitions__ModelNameAssignment_1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:893:1: rule__FeatureDefinitions__ModelNameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__FeatureDefinitions__ModelNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:897:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:898:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:898:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:899:1: RULE_STRING
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getModelNameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__ModelNameAssignment_11678); 
             after(grammarAccess.getFeatureDefinitionsAccess().getModelNameSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__ModelNameAssignment_1


    // $ANTLR start rule__FeatureDefinitions__ModelPathAssignment_3_1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:908:1: rule__FeatureDefinitions__ModelPathAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__FeatureDefinitions__ModelPathAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:912:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:913:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:913:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:914:1: RULE_STRING
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getModelPathSTRINGTerminalRuleCall_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__ModelPathAssignment_3_11709); 
             after(grammarAccess.getFeatureDefinitionsAccess().getModelPathSTRINGTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__ModelPathAssignment_3_1


    // $ANTLR start rule__FeatureDefinitions__DiagramPackageAssignment_4_1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:923:1: rule__FeatureDefinitions__DiagramPackageAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__FeatureDefinitions__DiagramPackageAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:927:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:928:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:928:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:929:1: RULE_STRING
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getDiagramPackageSTRINGTerminalRuleCall_4_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__DiagramPackageAssignment_4_11740); 
             after(grammarAccess.getFeatureDefinitionsAccess().getDiagramPackageSTRINGTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__DiagramPackageAssignment_4_1


    // $ANTLR start rule__FeatureDefinitions__FeatureMenuTitleAssignment_6
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:938:1: rule__FeatureDefinitions__FeatureMenuTitleAssignment_6 : ( RULE_STRING ) ;
    public final void rule__FeatureDefinitions__FeatureMenuTitleAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:942:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:943:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:943:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:944:1: RULE_STRING
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleSTRINGTerminalRuleCall_6_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__FeatureMenuTitleAssignment_61771); 
             after(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleSTRINGTerminalRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__FeatureMenuTitleAssignment_6


    // $ANTLR start rule__FeatureDefinitions__FeatureFileAssignment_8_1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:953:1: rule__FeatureDefinitions__FeatureFileAssignment_8_1 : ( RULE_STRING ) ;
    public final void rule__FeatureDefinitions__FeatureFileAssignment_8_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:957:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:958:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:958:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:959:1: RULE_STRING
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getFeatureFileSTRINGTerminalRuleCall_8_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__FeatureFileAssignment_8_11802); 
             after(grammarAccess.getFeatureDefinitionsAccess().getFeatureFileSTRINGTerminalRuleCall_8_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__FeatureFileAssignment_8_1


    // $ANTLR start rule__FeatureDefinitions__ElementsAssignment_9
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:968:1: rule__FeatureDefinitions__ElementsAssignment_9 : ( ruleFeatureType ) ;
    public final void rule__FeatureDefinitions__ElementsAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:972:1: ( ( ruleFeatureType ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:973:1: ( ruleFeatureType )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:973:1: ( ruleFeatureType )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:974:1: ruleFeatureType
            {
             before(grammarAccess.getFeatureDefinitionsAccess().getElementsFeatureTypeParserRuleCall_9_0()); 
            pushFollow(FOLLOW_ruleFeatureType_in_rule__FeatureDefinitions__ElementsAssignment_91833);
            ruleFeatureType();
            _fsp--;

             after(grammarAccess.getFeatureDefinitionsAccess().getElementsFeatureTypeParserRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureDefinitions__ElementsAssignment_9


    // $ANTLR start rule__FeatureType__NameAssignment_1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:983:1: rule__FeatureType__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__FeatureType__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:987:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:988:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:988:1: ( RULE_ID )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:989:1: RULE_ID
            {
             before(grammarAccess.getFeatureTypeAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__FeatureType__NameAssignment_11864); 
             after(grammarAccess.getFeatureTypeAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__NameAssignment_1


    // $ANTLR start rule__FeatureType__FileNameAssignment_3_1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:998:1: rule__FeatureType__FileNameAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__FeatureType__FileNameAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1002:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1003:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1003:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1004:1: RULE_STRING
            {
             before(grammarAccess.getFeatureTypeAccess().getFileNameSTRINGTerminalRuleCall_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureType__FileNameAssignment_3_11895); 
             after(grammarAccess.getFeatureTypeAccess().getFileNameSTRINGTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__FileNameAssignment_3_1


    // $ANTLR start rule__FeatureType__MethodNameAssignment_5
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1013:1: rule__FeatureType__MethodNameAssignment_5 : ( RULE_STRING ) ;
    public final void rule__FeatureType__MethodNameAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1017:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1018:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1018:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1019:1: RULE_STRING
            {
             before(grammarAccess.getFeatureTypeAccess().getMethodNameSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureType__MethodNameAssignment_51926); 
             after(grammarAccess.getFeatureTypeAccess().getMethodNameSTRINGTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__MethodNameAssignment_5


    // $ANTLR start rule__FeatureType__ParameterAssignment_7_1
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1028:1: rule__FeatureType__ParameterAssignment_7_1 : ( RULE_STRING ) ;
    public final void rule__FeatureType__ParameterAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1032:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1033:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1033:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1034:1: RULE_STRING
            {
             before(grammarAccess.getFeatureTypeAccess().getParameterSTRINGTerminalRuleCall_7_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureType__ParameterAssignment_7_11957); 
             after(grammarAccess.getFeatureTypeAccess().getParameterSTRINGTerminalRuleCall_7_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__ParameterAssignment_7_1


    // $ANTLR start rule__FeatureType__MenuEntryAssignment_9
    // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1043:1: rule__FeatureType__MenuEntryAssignment_9 : ( RULE_STRING ) ;
    public final void rule__FeatureType__MenuEntryAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1047:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1048:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1048:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl.ui/src-gen/de/cau/cs/kieler/ksbase/contentassist/antlr/internal/InternalFeatureDefinition.g:1049:1: RULE_STRING
            {
             before(grammarAccess.getFeatureTypeAccess().getMenuEntrySTRINGTerminalRuleCall_9_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__FeatureType__MenuEntryAssignment_91988); 
             after(grammarAccess.getFeatureTypeAccess().getMenuEntrySTRINGTerminalRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__FeatureType__MenuEntryAssignment_9


 

    public static final BitSet FOLLOW_ruleFeatureDefinitions_in_entryRuleFeatureDefinitions60 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureDefinitions67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__0_in_ruleFeatureDefinitions94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureType_in_entryRuleFeatureType120 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureType127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__0_in_ruleFeatureType154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__FeatureDefinitions__Group__0193 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__1_in_rule__FeatureDefinitions__Group__0203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__ModelNameAssignment_1_in_rule__FeatureDefinitions__Group__1231 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__2_in_rule__FeatureDefinitions__Group__1240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureDefinitions__Group__2269 = new BitSet(new long[]{0x000000000000E000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__3_in_rule__FeatureDefinitions__Group__2279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_3__0_in_rule__FeatureDefinitions__Group__3307 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__4_in_rule__FeatureDefinitions__Group__3317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_4__0_in_rule__FeatureDefinitions__Group__4345 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__5_in_rule__FeatureDefinitions__Group__4355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__FeatureDefinitions__Group__5384 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__6_in_rule__FeatureDefinitions__Group__5394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__FeatureMenuTitleAssignment_6_in_rule__FeatureDefinitions__Group__6422 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__7_in_rule__FeatureDefinitions__Group__6431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureDefinitions__Group__7460 = new BitSet(new long[]{0x0000000000030002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__8_in_rule__FeatureDefinitions__Group__7470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_8__0_in_rule__FeatureDefinitions__Group__8498 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group__9_in_rule__FeatureDefinitions__Group__8508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__ElementsAssignment_9_in_rule__FeatureDefinitions__Group__9536 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_14_in_rule__FeatureDefinitions__Group_3__0592 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_3__1_in_rule__FeatureDefinitions__Group_3__0602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__ModelPathAssignment_3_1_in_rule__FeatureDefinitions__Group_3__1630 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_3__2_in_rule__FeatureDefinitions__Group_3__1639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureDefinitions__Group_3__2668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__FeatureDefinitions__Group_4__0710 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_4__1_in_rule__FeatureDefinitions__Group_4__0720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__DiagramPackageAssignment_4_1_in_rule__FeatureDefinitions__Group_4__1748 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_4__2_in_rule__FeatureDefinitions__Group_4__1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureDefinitions__Group_4__2786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__FeatureDefinitions__Group_8__0828 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_8__1_in_rule__FeatureDefinitions__Group_8__0838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__FeatureFileAssignment_8_1_in_rule__FeatureDefinitions__Group_8__1866 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureDefinitions__Group_8__2_in_rule__FeatureDefinitions__Group_8__1875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureDefinitions__Group_8__2904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__FeatureType__Group__0946 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__1_in_rule__FeatureType__Group__0956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__NameAssignment_1_in_rule__FeatureType__Group__1984 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__2_in_rule__FeatureType__Group__1993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__FeatureType__Group__21022 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__3_in_rule__FeatureType__Group__21032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__Group_3__0_in_rule__FeatureType__Group__31060 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__4_in_rule__FeatureType__Group__31070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__FeatureType__Group__41099 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__5_in_rule__FeatureType__Group__41109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__MethodNameAssignment_5_in_rule__FeatureType__Group__51137 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__6_in_rule__FeatureType__Group__51146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureType__Group__61175 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__7_in_rule__FeatureType__Group__61185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__Group_7__0_in_rule__FeatureType__Group__71215 = new BitSet(new long[]{0x0000000000900000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group_7__0_in_rule__FeatureType__Group__71227 = new BitSet(new long[]{0x0000000000900000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__8_in_rule__FeatureType__Group__71239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__FeatureType__Group__81268 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__9_in_rule__FeatureType__Group__81278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__MenuEntryAssignment_9_in_rule__FeatureType__Group__91306 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__10_in_rule__FeatureType__Group__91315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureType__Group__101344 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group__11_in_rule__FeatureType__Group__101354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__FeatureType__Group__111383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__FeatureType__Group_3__01443 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureType__Group_3__1_in_rule__FeatureType__Group_3__01453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__FileNameAssignment_3_1_in_rule__FeatureType__Group_3__11481 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group_3__2_in_rule__FeatureType__Group_3__11490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureType__Group_3__21519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__FeatureType__Group_7__01561 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FeatureType__Group_7__1_in_rule__FeatureType__Group_7__01571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FeatureType__ParameterAssignment_7_1_in_rule__FeatureType__Group_7__11599 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FeatureType__Group_7__2_in_rule__FeatureType__Group_7__11608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__FeatureType__Group_7__21637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__ModelNameAssignment_11678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__ModelPathAssignment_3_11709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__DiagramPackageAssignment_4_11740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__FeatureMenuTitleAssignment_61771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureDefinitions__FeatureFileAssignment_8_11802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureType_in_rule__FeatureDefinitions__ElementsAssignment_91833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__FeatureType__NameAssignment_11864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureType__FileNameAssignment_3_11895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureType__MethodNameAssignment_51926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureType__ParameterAssignment_7_11957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__FeatureType__MenuEntryAssignment_91988 = new BitSet(new long[]{0x0000000000000002L});

}