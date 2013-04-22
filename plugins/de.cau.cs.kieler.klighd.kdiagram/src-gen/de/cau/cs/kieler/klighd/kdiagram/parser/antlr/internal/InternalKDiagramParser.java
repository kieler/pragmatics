package de.cau.cs.kieler.klighd.kdiagram.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.klighd.kdiagram.services.KDiagramGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalKDiagramParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_HEX", "RULE_INT", "RULE_DECIMAL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'package'", "';'", "'diagram'", "'import'", "'static'", "'extension'", "'.'", "'*'", "'for'", "':'", "'nodes'", "'='", "'{'", "'depictedBy'", "'}'", "'+='", "'||'", "'&&'", "'=='", "'!='", "'instanceof'", "'>='", "'<='", "'>'", "'<'", "'->'", "'..'", "'=>'", "'<>'", "'?:'", "'<=>'", "'+'", "'-'", "'**'", "'/'", "'%'", "'!'", "'as'", "'?.'", "'*.'", "','", "'('", "')'", "'['", "'|'", "']'", "'if'", "'else'", "'switch'", "'default'", "'case'", "'while'", "'do'", "'var'", "'val'", "'super'", "'::'", "'new'", "'false'", "'true'", "'null'", "'typeof'", "'throw'", "'return'", "'try'", "'finally'", "'catch'", "'?'", "'extends'", "'&'"
    };
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int RULE_ID=4;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__29=29;
    public static final int T__65=65;
    public static final int T__28=28;
    public static final int T__62=62;
    public static final int T__27=27;
    public static final int T__63=63;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int EOF=-1;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__19=19;
    public static final int T__57=57;
    public static final int RULE_HEX=6;
    public static final int T__58=58;
    public static final int T__16=16;
    public static final int T__51=51;
    public static final int T__15=15;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__18=18;
    public static final int T__54=54;
    public static final int T__17=17;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__59=59;
    public static final int RULE_INT=7;
    public static final int RULE_DECIMAL=8;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__80=80;
    public static final int T__46=46;
    public static final int T__81=81;
    public static final int T__47=47;
    public static final int T__82=82;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_SL_COMMENT=10;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_STRING=5;
    public static final int T__32=32;
    public static final int T__71=71;
    public static final int T__33=33;
    public static final int T__72=72;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__70=70;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_WS=11;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;

    // delegates
    // delegators


        public InternalKDiagramParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalKDiagramParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalKDiagramParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g"; }



     	private KDiagramGrammarAccess grammarAccess;
     	
        public InternalKDiagramParser(TokenStream input, KDiagramGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "DiagramSynthesis";	
       	}
       	
       	@Override
       	protected KDiagramGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleDiagramSynthesis"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:67:1: entryRuleDiagramSynthesis returns [EObject current=null] : iv_ruleDiagramSynthesis= ruleDiagramSynthesis EOF ;
    public final EObject entryRuleDiagramSynthesis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDiagramSynthesis = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:68:2: (iv_ruleDiagramSynthesis= ruleDiagramSynthesis EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:69:2: iv_ruleDiagramSynthesis= ruleDiagramSynthesis EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDiagramSynthesisRule()); 
            }
            pushFollow(FOLLOW_ruleDiagramSynthesis_in_entryRuleDiagramSynthesis75);
            iv_ruleDiagramSynthesis=ruleDiagramSynthesis();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDiagramSynthesis; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDiagramSynthesis85); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDiagramSynthesis"


    // $ANTLR start "ruleDiagramSynthesis"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:76:1: ruleDiagramSynthesis returns [EObject current=null] : ( (otherlv_0= 'package' ( (lv_packageName_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleImport ) )* otherlv_4= 'diagram' ( (lv_name_5_0= ruleQualifiedName ) ) ( (lv_mapping_6_0= ruleMappingDefinition ) ) ) ;
    public final EObject ruleDiagramSynthesis() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_packageName_1_0 = null;

        EObject lv_imports_3_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        EObject lv_mapping_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:79:28: ( ( (otherlv_0= 'package' ( (lv_packageName_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleImport ) )* otherlv_4= 'diagram' ( (lv_name_5_0= ruleQualifiedName ) ) ( (lv_mapping_6_0= ruleMappingDefinition ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:80:1: ( (otherlv_0= 'package' ( (lv_packageName_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleImport ) )* otherlv_4= 'diagram' ( (lv_name_5_0= ruleQualifiedName ) ) ( (lv_mapping_6_0= ruleMappingDefinition ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:80:1: ( (otherlv_0= 'package' ( (lv_packageName_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleImport ) )* otherlv_4= 'diagram' ( (lv_name_5_0= ruleQualifiedName ) ) ( (lv_mapping_6_0= ruleMappingDefinition ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:80:2: (otherlv_0= 'package' ( (lv_packageName_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleImport ) )* otherlv_4= 'diagram' ( (lv_name_5_0= ruleQualifiedName ) ) ( (lv_mapping_6_0= ruleMappingDefinition ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:80:2: (otherlv_0= 'package' ( (lv_packageName_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:80:4: otherlv_0= 'package' ( (lv_packageName_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )?
                    {
                    otherlv_0=(Token)match(input,13,FOLLOW_13_in_ruleDiagramSynthesis123); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getDiagramSynthesisAccess().getPackageKeyword_0_0());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:84:1: ( (lv_packageName_1_0= ruleQualifiedName ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:85:1: (lv_packageName_1_0= ruleQualifiedName )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:85:1: (lv_packageName_1_0= ruleQualifiedName )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:86:3: lv_packageName_1_0= ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDiagramSynthesisAccess().getPackageNameQualifiedNameParserRuleCall_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleDiagramSynthesis144);
                    lv_packageName_1_0=ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDiagramSynthesisRule());
                      	        }
                             		set(
                             			current, 
                             			"packageName",
                              		lv_packageName_1_0, 
                              		"QualifiedName");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:102:2: (otherlv_2= ';' )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==14) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:102:4: otherlv_2= ';'
                            {
                            otherlv_2=(Token)match(input,14,FOLLOW_14_in_ruleDiagramSynthesis157); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getDiagramSynthesisAccess().getSemicolonKeyword_0_2());
                                  
                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:106:5: ( (lv_imports_3_0= ruleImport ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:107:1: (lv_imports_3_0= ruleImport )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:107:1: (lv_imports_3_0= ruleImport )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:108:3: lv_imports_3_0= ruleImport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getDiagramSynthesisAccess().getImportsImportParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImport_in_ruleDiagramSynthesis182);
            	    lv_imports_3_0=ruleImport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getDiagramSynthesisRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"imports",
            	              		lv_imports_3_0, 
            	              		"Import");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_15_in_ruleDiagramSynthesis195); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getDiagramSynthesisAccess().getDiagramKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:128:1: ( (lv_name_5_0= ruleQualifiedName ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:129:1: (lv_name_5_0= ruleQualifiedName )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:129:1: (lv_name_5_0= ruleQualifiedName )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:130:3: lv_name_5_0= ruleQualifiedName
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getDiagramSynthesisAccess().getNameQualifiedNameParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleDiagramSynthesis216);
            lv_name_5_0=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDiagramSynthesisRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_5_0, 
                      		"QualifiedName");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:146:2: ( (lv_mapping_6_0= ruleMappingDefinition ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:147:1: (lv_mapping_6_0= ruleMappingDefinition )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:147:1: (lv_mapping_6_0= ruleMappingDefinition )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:148:3: lv_mapping_6_0= ruleMappingDefinition
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getDiagramSynthesisAccess().getMappingMappingDefinitionParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleMappingDefinition_in_ruleDiagramSynthesis237);
            lv_mapping_6_0=ruleMappingDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDiagramSynthesisRule());
              	        }
                     		set(
                     			current, 
                     			"mapping",
                      		lv_mapping_6_0, 
                      		"MappingDefinition");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDiagramSynthesis"


    // $ANTLR start "entryRuleImport"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:172:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:173:2: (iv_ruleImport= ruleImport EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:174:2: iv_ruleImport= ruleImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportRule()); 
            }
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport273);
            iv_ruleImport=ruleImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport283); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:181:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) ) ) (otherlv_8= ';' )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_static_1_0=null;
        Token lv_extension_2_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_importedNamespace_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:184:28: ( (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) ) ) (otherlv_8= ';' )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:185:1: (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) ) ) (otherlv_8= ';' )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:185:1: (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) ) ) (otherlv_8= ';' )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:185:3: otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) ) ) (otherlv_8= ';' )?
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleImport320); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:189:1: ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) ) )
            int alt5=3;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:189:2: ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:189:2: ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:189:3: ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*'
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:189:3: ( (lv_static_1_0= 'static' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:190:1: (lv_static_1_0= 'static' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:190:1: (lv_static_1_0= 'static' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:191:3: lv_static_1_0= 'static'
                    {
                    lv_static_1_0=(Token)match(input,17,FOLLOW_17_in_ruleImport340); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_static_1_0, grammarAccess.getImportAccess().getStaticStaticKeyword_1_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getImportRule());
                      	        }
                             		setWithLastConsumed(current, "static", true, "static");
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:204:2: ( (lv_extension_2_0= 'extension' ) )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==18) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:205:1: (lv_extension_2_0= 'extension' )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:205:1: (lv_extension_2_0= 'extension' )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:206:3: lv_extension_2_0= 'extension'
                            {
                            lv_extension_2_0=(Token)match(input,18,FOLLOW_18_in_ruleImport371); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_extension_2_0, grammarAccess.getImportAccess().getExtensionExtensionKeyword_1_0_1_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getImportRule());
                              	        }
                                     		setWithLastConsumed(current, "extension", true, "extension");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:219:3: ( ( ruleQualifiedName ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:220:1: ( ruleQualifiedName )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:220:1: ( ruleQualifiedName )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:221:3: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getImportRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImportAccess().getImportedTypeJvmTypeCrossReference_1_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleImport408);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,19,FOLLOW_19_in_ruleImport420); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getImportAccess().getFullStopKeyword_1_0_3());
                          
                    }
                    otherlv_5=(Token)match(input,20,FOLLOW_20_in_ruleImport432); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getImportAccess().getAsteriskKeyword_1_0_4());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:243:6: ( ( ruleQualifiedName ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:243:6: ( ( ruleQualifiedName ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:244:1: ( ruleQualifiedName )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:244:1: ( ruleQualifiedName )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:245:3: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getImportRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImportAccess().getImportedTypeJvmTypeCrossReference_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedName_in_ruleImport462);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:259:6: ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:259:6: ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:260:1: (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:260:1: (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:261:3: lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildCardParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedNameWithWildCard_in_ruleImport489);
                    lv_importedNamespace_7_0=ruleQualifiedNameWithWildCard();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getImportRule());
                      	        }
                             		set(
                             			current, 
                             			"importedNamespace",
                              		lv_importedNamespace_7_0, 
                              		"QualifiedNameWithWildCard");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:277:3: (otherlv_8= ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==14) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:277:5: otherlv_8= ';'
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_14_in_ruleImport503); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getImportAccess().getSemicolonKeyword_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleQualifiedNameWithWildCard"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:289:1: entryRuleQualifiedNameWithWildCard returns [String current=null] : iv_ruleQualifiedNameWithWildCard= ruleQualifiedNameWithWildCard EOF ;
    public final String entryRuleQualifiedNameWithWildCard() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedNameWithWildCard = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:290:2: (iv_ruleQualifiedNameWithWildCard= ruleQualifiedNameWithWildCard EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:291:2: iv_ruleQualifiedNameWithWildCard= ruleQualifiedNameWithWildCard EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameWithWildCardRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedNameWithWildCard_in_entryRuleQualifiedNameWithWildCard542);
            iv_ruleQualifiedNameWithWildCard=ruleQualifiedNameWithWildCard();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedNameWithWildCard.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedNameWithWildCard553); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedNameWithWildCard"


    // $ANTLR start "ruleQualifiedNameWithWildCard"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:298:1: ruleQualifiedNameWithWildCard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedNameWithWildCard() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:301:28: ( (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:302:1: (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:302:1: (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:303:5: this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*'
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getQualifiedNameWithWildCardAccess().getQualifiedNameParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleQualifiedNameWithWildCard600);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_QualifiedName_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            kw=(Token)match(input,19,FOLLOW_19_in_ruleQualifiedNameWithWildCard618); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getQualifiedNameWithWildCardAccess().getFullStopKeyword_1()); 
                  
            }
            kw=(Token)match(input,20,FOLLOW_20_in_ruleQualifiedNameWithWildCard631); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getQualifiedNameWithWildCardAccess().getAsteriskKeyword_2()); 
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNameWithWildCard"


    // $ANTLR start "entryRuleMappingDefinition"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:333:1: entryRuleMappingDefinition returns [EObject current=null] : iv_ruleMappingDefinition= ruleMappingDefinition EOF ;
    public final EObject entryRuleMappingDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMappingDefinition = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:334:2: (iv_ruleMappingDefinition= ruleMappingDefinition EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:335:2: iv_ruleMappingDefinition= ruleMappingDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMappingDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleMappingDefinition_in_entryRuleMappingDefinition671);
            iv_ruleMappingDefinition=ruleMappingDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMappingDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMappingDefinition681); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMappingDefinition"


    // $ANTLR start "ruleMappingDefinition"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:342:1: ruleMappingDefinition returns [EObject current=null] : (otherlv_0= 'for' ( (lv_type_1_0= ruleJvmParameterizedTypeReference ) ) ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' )? ( (lv_nodeMappings_4_0= ruleNodeMapping ) )+ ) ;
    public final EObject ruleMappingDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_type_1_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_nodeMappings_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:345:28: ( (otherlv_0= 'for' ( (lv_type_1_0= ruleJvmParameterizedTypeReference ) ) ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' )? ( (lv_nodeMappings_4_0= ruleNodeMapping ) )+ ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:346:1: (otherlv_0= 'for' ( (lv_type_1_0= ruleJvmParameterizedTypeReference ) ) ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' )? ( (lv_nodeMappings_4_0= ruleNodeMapping ) )+ )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:346:1: (otherlv_0= 'for' ( (lv_type_1_0= ruleJvmParameterizedTypeReference ) ) ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' )? ( (lv_nodeMappings_4_0= ruleNodeMapping ) )+ )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:346:3: otherlv_0= 'for' ( (lv_type_1_0= ruleJvmParameterizedTypeReference ) ) ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' )? ( (lv_nodeMappings_4_0= ruleNodeMapping ) )+
            {
            otherlv_0=(Token)match(input,21,FOLLOW_21_in_ruleMappingDefinition718); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getMappingDefinitionAccess().getForKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:350:1: ( (lv_type_1_0= ruleJvmParameterizedTypeReference ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:351:1: (lv_type_1_0= ruleJvmParameterizedTypeReference )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:351:1: (lv_type_1_0= ruleJvmParameterizedTypeReference )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:352:3: lv_type_1_0= ruleJvmParameterizedTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMappingDefinitionAccess().getTypeJvmParameterizedTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmParameterizedTypeReference_in_ruleMappingDefinition739);
            lv_type_1_0=ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMappingDefinitionRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_1_0, 
                      		"JvmParameterizedTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:368:2: ( (lv_name_2_0= ruleValidID ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:369:1: (lv_name_2_0= ruleValidID )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:369:1: (lv_name_2_0= ruleValidID )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:370:3: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMappingDefinitionAccess().getNameValidIDParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleValidID_in_ruleMappingDefinition760);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMappingDefinitionRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_2_0, 
                      		"ValidID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:386:2: (otherlv_3= ':' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==22) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:386:4: otherlv_3= ':'
                    {
                    otherlv_3=(Token)match(input,22,FOLLOW_22_in_ruleMappingDefinition773); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getMappingDefinitionAccess().getColonKeyword_3());
                          
                    }

                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:390:3: ( (lv_nodeMappings_4_0= ruleNodeMapping ) )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==23) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:391:1: (lv_nodeMappings_4_0= ruleNodeMapping )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:391:1: (lv_nodeMappings_4_0= ruleNodeMapping )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:392:3: lv_nodeMappings_4_0= ruleNodeMapping
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMappingDefinitionAccess().getNodeMappingsNodeMappingParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleNodeMapping_in_ruleMappingDefinition796);
            	    lv_nodeMappings_4_0=ruleNodeMapping();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getMappingDefinitionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"nodeMappings",
            	              		lv_nodeMappings_4_0, 
            	              		"NodeMapping");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMappingDefinition"


    // $ANTLR start "entryRuleNodeMapping"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:416:1: entryRuleNodeMapping returns [EObject current=null] : iv_ruleNodeMapping= ruleNodeMapping EOF ;
    public final EObject entryRuleNodeMapping() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodeMapping = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:417:2: (iv_ruleNodeMapping= ruleNodeMapping EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:418:2: iv_ruleNodeMapping= ruleNodeMapping EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNodeMappingRule()); 
            }
            pushFollow(FOLLOW_ruleNodeMapping_in_entryRuleNodeMapping833);
            iv_ruleNodeMapping=ruleNodeMapping();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNodeMapping; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNodeMapping843); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNodeMapping"


    // $ANTLR start "ruleNodeMapping"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:425:1: ruleNodeMapping returns [EObject current=null] : (otherlv_0= 'nodes' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_elements_3_0= ruleXExpression ) ) otherlv_4= '{' otherlv_5= 'depictedBy' ( ( ruleQualifiedName ) ) otherlv_7= '}' ) ;
    public final EObject ruleNodeMapping() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_elements_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:428:28: ( (otherlv_0= 'nodes' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_elements_3_0= ruleXExpression ) ) otherlv_4= '{' otherlv_5= 'depictedBy' ( ( ruleQualifiedName ) ) otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:429:1: (otherlv_0= 'nodes' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_elements_3_0= ruleXExpression ) ) otherlv_4= '{' otherlv_5= 'depictedBy' ( ( ruleQualifiedName ) ) otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:429:1: (otherlv_0= 'nodes' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_elements_3_0= ruleXExpression ) ) otherlv_4= '{' otherlv_5= 'depictedBy' ( ( ruleQualifiedName ) ) otherlv_7= '}' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:429:3: otherlv_0= 'nodes' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_elements_3_0= ruleXExpression ) ) otherlv_4= '{' otherlv_5= 'depictedBy' ( ( ruleQualifiedName ) ) otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleNodeMapping880); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getNodeMappingAccess().getNodesKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:433:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:434:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:434:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:435:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNodeMapping897); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_1_0, grammarAccess.getNodeMappingAccess().getNameIDTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getNodeMappingRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ID");
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleNodeMapping914); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getNodeMappingAccess().getEqualsSignKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:455:1: ( (lv_elements_3_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:456:1: (lv_elements_3_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:456:1: (lv_elements_3_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:457:3: lv_elements_3_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNodeMappingAccess().getElementsXExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleNodeMapping935);
            lv_elements_3_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNodeMappingRule());
              	        }
                     		set(
                     			current, 
                     			"elements",
                      		lv_elements_3_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,25,FOLLOW_25_in_ruleNodeMapping947); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getNodeMappingAccess().getLeftCurlyBracketKeyword_4());
                  
            }
            otherlv_5=(Token)match(input,26,FOLLOW_26_in_ruleNodeMapping959); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getNodeMappingAccess().getDepictedByKeyword_5());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:481:1: ( ( ruleQualifiedName ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:482:1: ( ruleQualifiedName )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:482:1: ( ruleQualifiedName )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:483:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getNodeMappingRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNodeMappingAccess().getFigureTypeJvmTypeCrossReference_6_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleNodeMapping982);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_7=(Token)match(input,27,FOLLOW_27_in_ruleNodeMapping994); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getNodeMappingAccess().getRightCurlyBracketKeyword_7());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNodeMapping"


    // $ANTLR start "entryRuleXExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:510:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:511:2: (iv_ruleXExpression= ruleXExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:512:2: iv_ruleXExpression= ruleXExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXExpression_in_entryRuleXExpression1032);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpression1042); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXExpression"


    // $ANTLR start "ruleXExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:519:1: ruleXExpression returns [EObject current=null] : this_XAssignment_0= ruleXAssignment ;
    public final EObject ruleXExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XAssignment_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:522:28: (this_XAssignment_0= ruleXAssignment )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:524:5: this_XAssignment_0= ruleXAssignment
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXExpressionAccess().getXAssignmentParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleXAssignment_in_ruleXExpression1088);
            this_XAssignment_0=ruleXAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XAssignment_0; 
                      afterParserOrEnumRuleCall();
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpression"


    // $ANTLR start "entryRuleXAssignment"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:540:1: entryRuleXAssignment returns [EObject current=null] : iv_ruleXAssignment= ruleXAssignment EOF ;
    public final EObject entryRuleXAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAssignment = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:541:2: (iv_ruleXAssignment= ruleXAssignment EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:542:2: iv_ruleXAssignment= ruleXAssignment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXAssignmentRule()); 
            }
            pushFollow(FOLLOW_ruleXAssignment_in_entryRuleXAssignment1122);
            iv_ruleXAssignment=ruleXAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXAssignment; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXAssignment1132); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXAssignment"


    // $ANTLR start "ruleXAssignment"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:549:1: ruleXAssignment returns [EObject current=null] : ( ( () ( ( ruleValidID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) ) ;
    public final EObject ruleXAssignment() throws RecognitionException {
        EObject current = null;

        EObject lv_value_3_0 = null;

        EObject this_XOrExpression_4 = null;

        EObject lv_rightOperand_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:552:28: ( ( ( () ( ( ruleValidID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:553:1: ( ( () ( ( ruleValidID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:553:1: ( ( () ( ( ruleValidID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==EOF||(LA10_1>=RULE_ID && LA10_1<=RULE_DECIMAL)||LA10_1==14||(LA10_1>=19 && LA10_1<=22)||LA10_1==25||(LA10_1>=27 && LA10_1<=56)||(LA10_1>=58 && LA10_1<=79)) ) {
                    alt10=2;
                }
                else if ( (LA10_1==24) ) {
                    alt10=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA10_0>=RULE_STRING && LA10_0<=RULE_DECIMAL)||LA10_0==21||LA10_0==25||LA10_0==37||(LA10_0>=44 && LA10_0<=45)||LA10_0==49||LA10_0==54||LA10_0==56||LA10_0==59||LA10_0==61||(LA10_0>=64 && LA10_0<=65)||LA10_0==68||(LA10_0>=70 && LA10_0<=77)) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:553:2: ( () ( ( ruleValidID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:553:2: ( () ( ( ruleValidID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:553:3: () ( ( ruleValidID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:553:3: ()
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:554:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getXAssignmentAccess().getXAssignmentAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:559:2: ( ( ruleValidID ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:560:1: ( ruleValidID )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:560:1: ( ruleValidID )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:561:3: ruleValidID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getXAssignmentRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXAssignmentAccess().getFeatureJvmIdentifiableElementCrossReference_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleValidID_in_ruleXAssignment1190);
                    ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXAssignmentAccess().getOpSingleAssignParserRuleCall_0_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleOpSingleAssign_in_ruleXAssignment1206);
                    ruleOpSingleAssign();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              afterParserOrEnumRuleCall();
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:582:1: ( (lv_value_3_0= ruleXAssignment ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:583:1: (lv_value_3_0= ruleXAssignment )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:583:1: (lv_value_3_0= ruleXAssignment )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:584:3: lv_value_3_0= ruleXAssignment
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXAssignmentAccess().getValueXAssignmentParserRuleCall_0_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXAssignment_in_ruleXAssignment1226);
                    lv_value_3_0=ruleXAssignment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXAssignmentRule());
                      	        }
                             		set(
                             			current, 
                             			"value",
                              		lv_value_3_0, 
                              		"XAssignment");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:601:6: (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:601:6: (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:602:5: this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )?
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXAssignmentAccess().getXOrExpressionParserRuleCall_1_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXOrExpression_in_ruleXAssignment1256);
                    this_XOrExpression_4=ruleXOrExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XOrExpression_4; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:1: ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==28) ) {
                        int LA9_1 = input.LA(2);

                        if ( (synpred1_InternalKDiagram()) ) {
                            alt9=1;
                        }
                    }
                    switch (alt9) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:2: ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:2: ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:3: ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:615:6: ( () ( ( ruleOpMultiAssign ) ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:615:7: () ( ( ruleOpMultiAssign ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:615:7: ()
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:616:5: 
                            {
                            if ( state.backtracking==0 ) {

                                      current = forceCreateModelElementAndSet(
                                          grammarAccess.getXAssignmentAccess().getXBinaryOperationLeftOperandAction_1_1_0_0_0(),
                                          current);
                                  
                            }

                            }

                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:621:2: ( ( ruleOpMultiAssign ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:622:1: ( ruleOpMultiAssign )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:622:1: ( ruleOpMultiAssign )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:623:3: ruleOpMultiAssign
                            {
                            if ( state.backtracking==0 ) {

                              			if (current==null) {
                              	            current = createModelElement(grammarAccess.getXAssignmentRule());
                              	        }
                                      
                            }
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXAssignmentAccess().getFeatureJvmIdentifiableElementCrossReference_1_1_0_0_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleOpMultiAssign_in_ruleXAssignment1309);
                            ruleOpMultiAssign();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {
                               
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }


                            }

                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:636:4: ( (lv_rightOperand_7_0= ruleXAssignment ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:637:1: (lv_rightOperand_7_0= ruleXAssignment )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:637:1: (lv_rightOperand_7_0= ruleXAssignment )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:638:3: lv_rightOperand_7_0= ruleXAssignment
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXAssignmentAccess().getRightOperandXAssignmentParserRuleCall_1_1_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleXAssignment_in_ruleXAssignment1332);
                            lv_rightOperand_7_0=ruleXAssignment();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXAssignmentRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"rightOperand",
                                      		lv_rightOperand_7_0, 
                                      		"XAssignment");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXAssignment"


    // $ANTLR start "entryRuleOpSingleAssign"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:662:1: entryRuleOpSingleAssign returns [String current=null] : iv_ruleOpSingleAssign= ruleOpSingleAssign EOF ;
    public final String entryRuleOpSingleAssign() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpSingleAssign = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:663:2: (iv_ruleOpSingleAssign= ruleOpSingleAssign EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:664:2: iv_ruleOpSingleAssign= ruleOpSingleAssign EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpSingleAssignRule()); 
            }
            pushFollow(FOLLOW_ruleOpSingleAssign_in_entryRuleOpSingleAssign1372);
            iv_ruleOpSingleAssign=ruleOpSingleAssign();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpSingleAssign.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpSingleAssign1383); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpSingleAssign"


    // $ANTLR start "ruleOpSingleAssign"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:671:1: ruleOpSingleAssign returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '=' ;
    public final AntlrDatatypeRuleToken ruleOpSingleAssign() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:674:28: (kw= '=' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:676:2: kw= '='
            {
            kw=(Token)match(input,24,FOLLOW_24_in_ruleOpSingleAssign1420); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getOpSingleAssignAccess().getEqualsSignKeyword()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpSingleAssign"


    // $ANTLR start "entryRuleOpMultiAssign"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:689:1: entryRuleOpMultiAssign returns [String current=null] : iv_ruleOpMultiAssign= ruleOpMultiAssign EOF ;
    public final String entryRuleOpMultiAssign() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpMultiAssign = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:690:2: (iv_ruleOpMultiAssign= ruleOpMultiAssign EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:691:2: iv_ruleOpMultiAssign= ruleOpMultiAssign EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpMultiAssignRule()); 
            }
            pushFollow(FOLLOW_ruleOpMultiAssign_in_entryRuleOpMultiAssign1460);
            iv_ruleOpMultiAssign=ruleOpMultiAssign();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpMultiAssign.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpMultiAssign1471); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpMultiAssign"


    // $ANTLR start "ruleOpMultiAssign"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:698:1: ruleOpMultiAssign returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '+=' ;
    public final AntlrDatatypeRuleToken ruleOpMultiAssign() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:701:28: (kw= '+=' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:703:2: kw= '+='
            {
            kw=(Token)match(input,28,FOLLOW_28_in_ruleOpMultiAssign1508); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getPlusSignEqualsSignKeyword()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpMultiAssign"


    // $ANTLR start "entryRuleXOrExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:716:1: entryRuleXOrExpression returns [EObject current=null] : iv_ruleXOrExpression= ruleXOrExpression EOF ;
    public final EObject entryRuleXOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOrExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:717:2: (iv_ruleXOrExpression= ruleXOrExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:718:2: iv_ruleXOrExpression= ruleXOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXOrExpression_in_entryRuleXOrExpression1547);
            iv_ruleXOrExpression=ruleXOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOrExpression1557); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXOrExpression"


    // $ANTLR start "ruleXOrExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:725:1: ruleXOrExpression returns [EObject current=null] : (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* ) ;
    public final EObject ruleXOrExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XAndExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:728:28: ( (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:729:1: (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:729:1: (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:730:5: this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXOrExpressionAccess().getXAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXAndExpression_in_ruleXOrExpression1604);
            this_XAndExpression_0=ruleXAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XAndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:1: ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==29) ) {
                    int LA11_2 = input.LA(2);

                    if ( (synpred2_InternalKDiagram()) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:2: ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:2: ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:3: ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:743:6: ( () ( ( ruleOpOr ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:743:7: () ( ( ruleOpOr ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:743:7: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:744:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXOrExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:749:2: ( ( ruleOpOr ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:750:1: ( ruleOpOr )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:750:1: ( ruleOpOr )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:751:3: ruleOpOr
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXOrExpressionRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXOrExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOpOr_in_ruleXOrExpression1657);
            	    ruleOpOr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:764:4: ( (lv_rightOperand_3_0= ruleXAndExpression ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:765:1: (lv_rightOperand_3_0= ruleXAndExpression )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:765:1: (lv_rightOperand_3_0= ruleXAndExpression )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:766:3: lv_rightOperand_3_0= ruleXAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXOrExpressionAccess().getRightOperandXAndExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXAndExpression_in_ruleXOrExpression1680);
            	    lv_rightOperand_3_0=ruleXAndExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXOrExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"rightOperand",
            	              		lv_rightOperand_3_0, 
            	              		"XAndExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXOrExpression"


    // $ANTLR start "entryRuleOpOr"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:790:1: entryRuleOpOr returns [String current=null] : iv_ruleOpOr= ruleOpOr EOF ;
    public final String entryRuleOpOr() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpOr = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:791:2: (iv_ruleOpOr= ruleOpOr EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:792:2: iv_ruleOpOr= ruleOpOr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpOrRule()); 
            }
            pushFollow(FOLLOW_ruleOpOr_in_entryRuleOpOr1719);
            iv_ruleOpOr=ruleOpOr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpOr.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpOr1730); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpOr"


    // $ANTLR start "ruleOpOr"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:799:1: ruleOpOr returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '||' ;
    public final AntlrDatatypeRuleToken ruleOpOr() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:802:28: (kw= '||' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:804:2: kw= '||'
            {
            kw=(Token)match(input,29,FOLLOW_29_in_ruleOpOr1767); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getOpOrAccess().getVerticalLineVerticalLineKeyword()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpOr"


    // $ANTLR start "entryRuleXAndExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:817:1: entryRuleXAndExpression returns [EObject current=null] : iv_ruleXAndExpression= ruleXAndExpression EOF ;
    public final EObject entryRuleXAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAndExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:818:2: (iv_ruleXAndExpression= ruleXAndExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:819:2: iv_ruleXAndExpression= ruleXAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXAndExpression_in_entryRuleXAndExpression1806);
            iv_ruleXAndExpression=ruleXAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXAndExpression1816); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXAndExpression"


    // $ANTLR start "ruleXAndExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:826:1: ruleXAndExpression returns [EObject current=null] : (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* ) ;
    public final EObject ruleXAndExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XEqualityExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:829:28: ( (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:830:1: (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:830:1: (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:831:5: this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXAndExpressionAccess().getXEqualityExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXEqualityExpression_in_ruleXAndExpression1863);
            this_XEqualityExpression_0=ruleXEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XEqualityExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:1: ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==30) ) {
                    int LA12_2 = input.LA(2);

                    if ( (synpred3_InternalKDiagram()) ) {
                        alt12=1;
                    }


                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:2: ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:2: ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:3: ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:844:6: ( () ( ( ruleOpAnd ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:844:7: () ( ( ruleOpAnd ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:844:7: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:845:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXAndExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:850:2: ( ( ruleOpAnd ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:851:1: ( ruleOpAnd )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:851:1: ( ruleOpAnd )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:852:3: ruleOpAnd
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXAndExpressionRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXAndExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOpAnd_in_ruleXAndExpression1916);
            	    ruleOpAnd();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:865:4: ( (lv_rightOperand_3_0= ruleXEqualityExpression ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:866:1: (lv_rightOperand_3_0= ruleXEqualityExpression )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:866:1: (lv_rightOperand_3_0= ruleXEqualityExpression )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:867:3: lv_rightOperand_3_0= ruleXEqualityExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXAndExpressionAccess().getRightOperandXEqualityExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXEqualityExpression_in_ruleXAndExpression1939);
            	    lv_rightOperand_3_0=ruleXEqualityExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXAndExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"rightOperand",
            	              		lv_rightOperand_3_0, 
            	              		"XEqualityExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXAndExpression"


    // $ANTLR start "entryRuleOpAnd"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:891:1: entryRuleOpAnd returns [String current=null] : iv_ruleOpAnd= ruleOpAnd EOF ;
    public final String entryRuleOpAnd() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpAnd = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:892:2: (iv_ruleOpAnd= ruleOpAnd EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:893:2: iv_ruleOpAnd= ruleOpAnd EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpAndRule()); 
            }
            pushFollow(FOLLOW_ruleOpAnd_in_entryRuleOpAnd1978);
            iv_ruleOpAnd=ruleOpAnd();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpAnd.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpAnd1989); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpAnd"


    // $ANTLR start "ruleOpAnd"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:900:1: ruleOpAnd returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '&&' ;
    public final AntlrDatatypeRuleToken ruleOpAnd() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:903:28: (kw= '&&' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:905:2: kw= '&&'
            {
            kw=(Token)match(input,30,FOLLOW_30_in_ruleOpAnd2026); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getOpAndAccess().getAmpersandAmpersandKeyword()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpAnd"


    // $ANTLR start "entryRuleXEqualityExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:918:1: entryRuleXEqualityExpression returns [EObject current=null] : iv_ruleXEqualityExpression= ruleXEqualityExpression EOF ;
    public final EObject entryRuleXEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXEqualityExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:919:2: (iv_ruleXEqualityExpression= ruleXEqualityExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:920:2: iv_ruleXEqualityExpression= ruleXEqualityExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXEqualityExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXEqualityExpression_in_entryRuleXEqualityExpression2065);
            iv_ruleXEqualityExpression=ruleXEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXEqualityExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXEqualityExpression2075); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXEqualityExpression"


    // $ANTLR start "ruleXEqualityExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:927:1: ruleXEqualityExpression returns [EObject current=null] : (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* ) ;
    public final EObject ruleXEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XRelationalExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:930:28: ( (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:931:1: (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:931:1: (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:932:5: this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXEqualityExpressionAccess().getXRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXRelationalExpression_in_ruleXEqualityExpression2122);
            this_XRelationalExpression_0=ruleXRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XRelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:1: ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==31) ) {
                    int LA13_2 = input.LA(2);

                    if ( (synpred4_InternalKDiagram()) ) {
                        alt13=1;
                    }


                }
                else if ( (LA13_0==32) ) {
                    int LA13_3 = input.LA(2);

                    if ( (synpred4_InternalKDiagram()) ) {
                        alt13=1;
                    }


                }


                switch (alt13) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:2: ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:2: ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:3: ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:945:6: ( () ( ( ruleOpEquality ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:945:7: () ( ( ruleOpEquality ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:945:7: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:946:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXEqualityExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:951:2: ( ( ruleOpEquality ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:952:1: ( ruleOpEquality )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:952:1: ( ruleOpEquality )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:953:3: ruleOpEquality
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXEqualityExpressionRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXEqualityExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOpEquality_in_ruleXEqualityExpression2175);
            	    ruleOpEquality();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:966:4: ( (lv_rightOperand_3_0= ruleXRelationalExpression ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:967:1: (lv_rightOperand_3_0= ruleXRelationalExpression )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:967:1: (lv_rightOperand_3_0= ruleXRelationalExpression )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:968:3: lv_rightOperand_3_0= ruleXRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXEqualityExpressionAccess().getRightOperandXRelationalExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXRelationalExpression_in_ruleXEqualityExpression2198);
            	    lv_rightOperand_3_0=ruleXRelationalExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXEqualityExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"rightOperand",
            	              		lv_rightOperand_3_0, 
            	              		"XRelationalExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXEqualityExpression"


    // $ANTLR start "entryRuleOpEquality"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:992:1: entryRuleOpEquality returns [String current=null] : iv_ruleOpEquality= ruleOpEquality EOF ;
    public final String entryRuleOpEquality() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpEquality = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:993:2: (iv_ruleOpEquality= ruleOpEquality EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:994:2: iv_ruleOpEquality= ruleOpEquality EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpEqualityRule()); 
            }
            pushFollow(FOLLOW_ruleOpEquality_in_entryRuleOpEquality2237);
            iv_ruleOpEquality=ruleOpEquality();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpEquality.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpEquality2248); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpEquality"


    // $ANTLR start "ruleOpEquality"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1001:1: ruleOpEquality returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '==' | kw= '!=' ) ;
    public final AntlrDatatypeRuleToken ruleOpEquality() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1004:28: ( (kw= '==' | kw= '!=' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1005:1: (kw= '==' | kw= '!=' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1005:1: (kw= '==' | kw= '!=' )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==31) ) {
                alt14=1;
            }
            else if ( (LA14_0==32) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1006:2: kw= '=='
                    {
                    kw=(Token)match(input,31,FOLLOW_31_in_ruleOpEquality2286); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpEqualityAccess().getEqualsSignEqualsSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1013:2: kw= '!='
                    {
                    kw=(Token)match(input,32,FOLLOW_32_in_ruleOpEquality2305); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpEqualityAccess().getExclamationMarkEqualsSignKeyword_1()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpEquality"


    // $ANTLR start "entryRuleXRelationalExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1026:1: entryRuleXRelationalExpression returns [EObject current=null] : iv_ruleXRelationalExpression= ruleXRelationalExpression EOF ;
    public final EObject entryRuleXRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXRelationalExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1027:2: (iv_ruleXRelationalExpression= ruleXRelationalExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1028:2: iv_ruleXRelationalExpression= ruleXRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXRelationalExpression_in_entryRuleXRelationalExpression2345);
            iv_ruleXRelationalExpression=ruleXRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXRelationalExpression2355); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXRelationalExpression"


    // $ANTLR start "ruleXRelationalExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1035:1: ruleXRelationalExpression returns [EObject current=null] : (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* ) ;
    public final EObject ruleXRelationalExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XOtherOperatorExpression_0 = null;

        EObject lv_type_3_0 = null;

        EObject lv_rightOperand_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1038:28: ( (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1039:1: (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1039:1: (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1040:5: this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getXOtherOperatorExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXOtherOperatorExpression_in_ruleXRelationalExpression2402);
            this_XOtherOperatorExpression_0=ruleXOtherOperatorExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XOtherOperatorExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:1: ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )*
            loop15:
            do {
                int alt15=3;
                switch ( input.LA(1) ) {
                case 36:
                    {
                    int LA15_2 = input.LA(2);

                    if ( (synpred6_InternalKDiagram()) ) {
                        alt15=2;
                    }


                    }
                    break;
                case 37:
                    {
                    int LA15_3 = input.LA(2);

                    if ( (synpred6_InternalKDiagram()) ) {
                        alt15=2;
                    }


                    }
                    break;
                case 33:
                    {
                    int LA15_4 = input.LA(2);

                    if ( (synpred5_InternalKDiagram()) ) {
                        alt15=1;
                    }


                    }
                    break;
                case 34:
                    {
                    int LA15_5 = input.LA(2);

                    if ( (synpred6_InternalKDiagram()) ) {
                        alt15=2;
                    }


                    }
                    break;
                case 35:
                    {
                    int LA15_6 = input.LA(2);

                    if ( (synpred6_InternalKDiagram()) ) {
                        alt15=2;
                    }


                    }
                    break;

                }

                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:2: ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:2: ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:3: ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:3: ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:4: ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1050:5: ( () otherlv_2= 'instanceof' )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1050:6: () otherlv_2= 'instanceof'
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1050:6: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1051:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXRelationalExpressionAccess().getXInstanceOfExpressionExpressionAction_1_0_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,33,FOLLOW_33_in_ruleXRelationalExpression2438); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getXRelationalExpressionAccess().getInstanceofKeyword_1_0_0_0_1());
            	          
            	    }

            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1060:3: ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1061:1: (lv_type_3_0= ruleJvmTypeReference )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1061:1: (lv_type_3_0= ruleJvmTypeReference )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1062:3: lv_type_3_0= ruleJvmTypeReference
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getTypeJvmTypeReferenceParserRuleCall_1_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleXRelationalExpression2461);
            	    lv_type_3_0=ruleJvmTypeReference();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXRelationalExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"type",
            	              		lv_type_3_0, 
            	              		"JvmTypeReference");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:6: ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:6: ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:7: ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:7: ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:8: ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1084:6: ( () ( ( ruleOpCompare ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1084:7: () ( ( ruleOpCompare ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1084:7: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1085:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXRelationalExpressionAccess().getXBinaryOperationLeftOperandAction_1_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1090:2: ( ( ruleOpCompare ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1091:1: ( ruleOpCompare )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1091:1: ( ruleOpCompare )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1092:3: ruleOpCompare
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXRelationalExpressionRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOpCompare_in_ruleXRelationalExpression2522);
            	    ruleOpCompare();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1105:4: ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1106:1: (lv_rightOperand_6_0= ruleXOtherOperatorExpression )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1106:1: (lv_rightOperand_6_0= ruleXOtherOperatorExpression )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1107:3: lv_rightOperand_6_0= ruleXOtherOperatorExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getRightOperandXOtherOperatorExpressionParserRuleCall_1_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXOtherOperatorExpression_in_ruleXRelationalExpression2545);
            	    lv_rightOperand_6_0=ruleXOtherOperatorExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXRelationalExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"rightOperand",
            	              		lv_rightOperand_6_0, 
            	              		"XOtherOperatorExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXRelationalExpression"


    // $ANTLR start "entryRuleOpCompare"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1131:1: entryRuleOpCompare returns [String current=null] : iv_ruleOpCompare= ruleOpCompare EOF ;
    public final String entryRuleOpCompare() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpCompare = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1132:2: (iv_ruleOpCompare= ruleOpCompare EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1133:2: iv_ruleOpCompare= ruleOpCompare EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpCompareRule()); 
            }
            pushFollow(FOLLOW_ruleOpCompare_in_entryRuleOpCompare2585);
            iv_ruleOpCompare=ruleOpCompare();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpCompare.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpCompare2596); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpCompare"


    // $ANTLR start "ruleOpCompare"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1140:1: ruleOpCompare returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' ) ;
    public final AntlrDatatypeRuleToken ruleOpCompare() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1143:28: ( (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1144:1: (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1144:1: (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' )
            int alt16=4;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt16=1;
                }
                break;
            case 35:
                {
                alt16=2;
                }
                break;
            case 36:
                {
                alt16=3;
                }
                break;
            case 37:
                {
                alt16=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1145:2: kw= '>='
                    {
                    kw=(Token)match(input,34,FOLLOW_34_in_ruleOpCompare2634); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignEqualsSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1152:2: kw= '<='
                    {
                    kw=(Token)match(input,35,FOLLOW_35_in_ruleOpCompare2653); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpCompareAccess().getLessThanSignEqualsSignKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1159:2: kw= '>'
                    {
                    kw=(Token)match(input,36,FOLLOW_36_in_ruleOpCompare2672); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1166:2: kw= '<'
                    {
                    kw=(Token)match(input,37,FOLLOW_37_in_ruleOpCompare2691); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpCompareAccess().getLessThanSignKeyword_3()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpCompare"


    // $ANTLR start "entryRuleXOtherOperatorExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1179:1: entryRuleXOtherOperatorExpression returns [EObject current=null] : iv_ruleXOtherOperatorExpression= ruleXOtherOperatorExpression EOF ;
    public final EObject entryRuleXOtherOperatorExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOtherOperatorExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1180:2: (iv_ruleXOtherOperatorExpression= ruleXOtherOperatorExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1181:2: iv_ruleXOtherOperatorExpression= ruleXOtherOperatorExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXOtherOperatorExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXOtherOperatorExpression_in_entryRuleXOtherOperatorExpression2731);
            iv_ruleXOtherOperatorExpression=ruleXOtherOperatorExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXOtherOperatorExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXOtherOperatorExpression2741); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXOtherOperatorExpression"


    // $ANTLR start "ruleXOtherOperatorExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1188:1: ruleXOtherOperatorExpression returns [EObject current=null] : (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* ) ;
    public final EObject ruleXOtherOperatorExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XAdditiveExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1191:28: ( (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1192:1: (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1192:1: (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1193:5: this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXOtherOperatorExpressionAccess().getXAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXAdditiveExpression_in_ruleXOtherOperatorExpression2788);
            this_XAdditiveExpression_0=ruleXAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XAdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:1: ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )*
            loop17:
            do {
                int alt17=2;
                alt17 = dfa17.predict(input);
                switch (alt17) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:2: ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:2: ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:3: ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1206:6: ( () ( ( ruleOpOther ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1206:7: () ( ( ruleOpOther ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1206:7: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1207:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXOtherOperatorExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1212:2: ( ( ruleOpOther ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1213:1: ( ruleOpOther )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1213:1: ( ruleOpOther )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1214:3: ruleOpOther
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXOtherOperatorExpressionRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXOtherOperatorExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOpOther_in_ruleXOtherOperatorExpression2841);
            	    ruleOpOther();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1227:4: ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1228:1: (lv_rightOperand_3_0= ruleXAdditiveExpression )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1228:1: (lv_rightOperand_3_0= ruleXAdditiveExpression )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1229:3: lv_rightOperand_3_0= ruleXAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXOtherOperatorExpressionAccess().getRightOperandXAdditiveExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXAdditiveExpression_in_ruleXOtherOperatorExpression2864);
            	    lv_rightOperand_3_0=ruleXAdditiveExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXOtherOperatorExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"rightOperand",
            	              		lv_rightOperand_3_0, 
            	              		"XAdditiveExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXOtherOperatorExpression"


    // $ANTLR start "entryRuleOpOther"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1253:1: entryRuleOpOther returns [String current=null] : iv_ruleOpOther= ruleOpOther EOF ;
    public final String entryRuleOpOther() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpOther = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1254:2: (iv_ruleOpOther= ruleOpOther EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1255:2: iv_ruleOpOther= ruleOpOther EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpOtherRule()); 
            }
            pushFollow(FOLLOW_ruleOpOther_in_entryRuleOpOther2903);
            iv_ruleOpOther=ruleOpOther();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpOther.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpOther2914); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpOther"


    // $ANTLR start "ruleOpOther"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1262:1: ruleOpOther returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '->' | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' ) ) | kw= '<>' | kw= '?:' | kw= '<=>' ) ;
    public final AntlrDatatypeRuleToken ruleOpOther() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1265:28: ( (kw= '->' | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' ) ) | kw= '<>' | kw= '?:' | kw= '<=>' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1266:1: (kw= '->' | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' ) ) | kw= '<>' | kw= '?:' | kw= '<=>' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1266:1: (kw= '->' | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' ) ) | kw= '<>' | kw= '?:' | kw= '<=>' )
            int alt20=8;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt20=1;
                }
                break;
            case 39:
                {
                alt20=2;
                }
                break;
            case 40:
                {
                alt20=3;
                }
                break;
            case 36:
                {
                alt20=4;
                }
                break;
            case 37:
                {
                alt20=5;
                }
                break;
            case 41:
                {
                alt20=6;
                }
                break;
            case 42:
                {
                alt20=7;
                }
                break;
            case 43:
                {
                alt20=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1267:2: kw= '->'
                    {
                    kw=(Token)match(input,38,FOLLOW_38_in_ruleOpOther2952); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getHyphenMinusGreaterThanSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1274:2: kw= '..'
                    {
                    kw=(Token)match(input,39,FOLLOW_39_in_ruleOpOther2971); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getFullStopFullStopKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1281:2: kw= '=>'
                    {
                    kw=(Token)match(input,40,FOLLOW_40_in_ruleOpOther2990); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getEqualsSignGreaterThanSignKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1287:6: (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1287:6: (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1288:2: kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' )
                    {
                    kw=(Token)match(input,36,FOLLOW_36_in_ruleOpOther3010); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_3_0()); 
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1293:1: ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' )
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==36) ) {
                        int LA18_1 = input.LA(2);

                        if ( (LA18_1==EOF||(LA18_1>=RULE_ID && LA18_1<=RULE_DECIMAL)||LA18_1==21||LA18_1==25||LA18_1==37||(LA18_1>=44 && LA18_1<=45)||LA18_1==49||LA18_1==54||LA18_1==56||LA18_1==59||LA18_1==61||(LA18_1>=64 && LA18_1<=65)||LA18_1==68||(LA18_1>=70 && LA18_1<=77)) ) {
                            alt18=2;
                        }
                        else if ( (LA18_1==36) && (synpred8_InternalKDiagram())) {
                            alt18=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 18, 1, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 0, input);

                        throw nvae;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1293:2: ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1293:2: ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1293:3: ( ( '>' '>' ) )=> (kw= '>' kw= '>' )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1297:5: (kw= '>' kw= '>' )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1298:2: kw= '>' kw= '>'
                            {
                            kw=(Token)match(input,36,FOLLOW_36_in_ruleOpOther3041); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_3_1_0_0_0()); 
                                  
                            }
                            kw=(Token)match(input,36,FOLLOW_36_in_ruleOpOther3054); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_3_1_0_0_1()); 
                                  
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1311:2: kw= '>'
                            {
                            kw=(Token)match(input,36,FOLLOW_36_in_ruleOpOther3075); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_3_1_1()); 
                                  
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1317:6: (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1317:6: (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1318:2: kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' )
                    {
                    kw=(Token)match(input,37,FOLLOW_37_in_ruleOpOther3097); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_4_0()); 
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1323:1: ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' )
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==37) ) {
                        int LA19_1 = input.LA(2);

                        if ( (synpred9_InternalKDiagram()) ) {
                            alt19=1;
                        }
                        else if ( (true) ) {
                            alt19=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 19, 1, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1323:2: ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1323:2: ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1323:3: ( ( '<' '<' ) )=> (kw= '<' kw= '<' )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1327:5: (kw= '<' kw= '<' )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1328:2: kw= '<' kw= '<'
                            {
                            kw=(Token)match(input,37,FOLLOW_37_in_ruleOpOther3128); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_4_1_0_0_0()); 
                                  
                            }
                            kw=(Token)match(input,37,FOLLOW_37_in_ruleOpOther3141); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_4_1_0_0_1()); 
                                  
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1341:2: kw= '<'
                            {
                            kw=(Token)match(input,37,FOLLOW_37_in_ruleOpOther3162); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_4_1_1()); 
                                  
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1348:2: kw= '<>'
                    {
                    kw=(Token)match(input,41,FOLLOW_41_in_ruleOpOther3183); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignGreaterThanSignKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1355:2: kw= '?:'
                    {
                    kw=(Token)match(input,42,FOLLOW_42_in_ruleOpOther3202); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getQuestionMarkColonKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1362:2: kw= '<=>'
                    {
                    kw=(Token)match(input,43,FOLLOW_43_in_ruleOpOther3221); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignEqualsSignGreaterThanSignKeyword_7()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpOther"


    // $ANTLR start "entryRuleXAdditiveExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1375:1: entryRuleXAdditiveExpression returns [EObject current=null] : iv_ruleXAdditiveExpression= ruleXAdditiveExpression EOF ;
    public final EObject entryRuleXAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAdditiveExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1376:2: (iv_ruleXAdditiveExpression= ruleXAdditiveExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1377:2: iv_ruleXAdditiveExpression= ruleXAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXAdditiveExpression_in_entryRuleXAdditiveExpression3261);
            iv_ruleXAdditiveExpression=ruleXAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXAdditiveExpression3271); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXAdditiveExpression"


    // $ANTLR start "ruleXAdditiveExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1384:1: ruleXAdditiveExpression returns [EObject current=null] : (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleXAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XMultiplicativeExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1387:28: ( (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1388:1: (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1388:1: (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1389:5: this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXAdditiveExpressionAccess().getXMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXMultiplicativeExpression_in_ruleXAdditiveExpression3318);
            this_XMultiplicativeExpression_0=ruleXMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XMultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:1: ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==44) ) {
                    int LA21_2 = input.LA(2);

                    if ( (synpred10_InternalKDiagram()) ) {
                        alt21=1;
                    }


                }
                else if ( (LA21_0==45) ) {
                    int LA21_3 = input.LA(2);

                    if ( (synpred10_InternalKDiagram()) ) {
                        alt21=1;
                    }


                }


                switch (alt21) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:2: ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:2: ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:3: ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1402:6: ( () ( ( ruleOpAdd ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1402:7: () ( ( ruleOpAdd ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1402:7: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1403:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXAdditiveExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1408:2: ( ( ruleOpAdd ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1409:1: ( ruleOpAdd )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1409:1: ( ruleOpAdd )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1410:3: ruleOpAdd
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXAdditiveExpressionRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXAdditiveExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOpAdd_in_ruleXAdditiveExpression3371);
            	    ruleOpAdd();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1423:4: ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1424:1: (lv_rightOperand_3_0= ruleXMultiplicativeExpression )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1424:1: (lv_rightOperand_3_0= ruleXMultiplicativeExpression )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1425:3: lv_rightOperand_3_0= ruleXMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXAdditiveExpressionAccess().getRightOperandXMultiplicativeExpressionParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXMultiplicativeExpression_in_ruleXAdditiveExpression3394);
            	    lv_rightOperand_3_0=ruleXMultiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXAdditiveExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"rightOperand",
            	              		lv_rightOperand_3_0, 
            	              		"XMultiplicativeExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXAdditiveExpression"


    // $ANTLR start "entryRuleOpAdd"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1449:1: entryRuleOpAdd returns [String current=null] : iv_ruleOpAdd= ruleOpAdd EOF ;
    public final String entryRuleOpAdd() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpAdd = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1450:2: (iv_ruleOpAdd= ruleOpAdd EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1451:2: iv_ruleOpAdd= ruleOpAdd EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpAddRule()); 
            }
            pushFollow(FOLLOW_ruleOpAdd_in_entryRuleOpAdd3433);
            iv_ruleOpAdd=ruleOpAdd();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpAdd.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpAdd3444); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpAdd"


    // $ANTLR start "ruleOpAdd"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1458:1: ruleOpAdd returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' ) ;
    public final AntlrDatatypeRuleToken ruleOpAdd() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1461:28: ( (kw= '+' | kw= '-' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1462:1: (kw= '+' | kw= '-' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1462:1: (kw= '+' | kw= '-' )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==44) ) {
                alt22=1;
            }
            else if ( (LA22_0==45) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1463:2: kw= '+'
                    {
                    kw=(Token)match(input,44,FOLLOW_44_in_ruleOpAdd3482); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpAddAccess().getPlusSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1470:2: kw= '-'
                    {
                    kw=(Token)match(input,45,FOLLOW_45_in_ruleOpAdd3501); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpAddAccess().getHyphenMinusKeyword_1()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpAdd"


    // $ANTLR start "entryRuleXMultiplicativeExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1483:1: entryRuleXMultiplicativeExpression returns [EObject current=null] : iv_ruleXMultiplicativeExpression= ruleXMultiplicativeExpression EOF ;
    public final EObject entryRuleXMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXMultiplicativeExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1484:2: (iv_ruleXMultiplicativeExpression= ruleXMultiplicativeExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1485:2: iv_ruleXMultiplicativeExpression= ruleXMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXMultiplicativeExpression_in_entryRuleXMultiplicativeExpression3541);
            iv_ruleXMultiplicativeExpression=ruleXMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXMultiplicativeExpression3551); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXMultiplicativeExpression"


    // $ANTLR start "ruleXMultiplicativeExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1492:1: ruleXMultiplicativeExpression returns [EObject current=null] : (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* ) ;
    public final EObject ruleXMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XUnaryOperation_0 = null;

        EObject lv_rightOperand_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1495:28: ( (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1496:1: (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1496:1: (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1497:5: this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXMultiplicativeExpressionAccess().getXUnaryOperationParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXUnaryOperation_in_ruleXMultiplicativeExpression3598);
            this_XUnaryOperation_0=ruleXUnaryOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XUnaryOperation_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:1: ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )*
            loop23:
            do {
                int alt23=2;
                switch ( input.LA(1) ) {
                case 20:
                    {
                    int LA23_2 = input.LA(2);

                    if ( (synpred11_InternalKDiagram()) ) {
                        alt23=1;
                    }


                    }
                    break;
                case 46:
                    {
                    int LA23_3 = input.LA(2);

                    if ( (synpred11_InternalKDiagram()) ) {
                        alt23=1;
                    }


                    }
                    break;
                case 47:
                    {
                    int LA23_4 = input.LA(2);

                    if ( (synpred11_InternalKDiagram()) ) {
                        alt23=1;
                    }


                    }
                    break;
                case 48:
                    {
                    int LA23_5 = input.LA(2);

                    if ( (synpred11_InternalKDiagram()) ) {
                        alt23=1;
                    }


                    }
                    break;

                }

                switch (alt23) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:2: ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:2: ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:3: ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1510:6: ( () ( ( ruleOpMulti ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1510:7: () ( ( ruleOpMulti ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1510:7: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1511:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXMultiplicativeExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1516:2: ( ( ruleOpMulti ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1517:1: ( ruleOpMulti )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1517:1: ( ruleOpMulti )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1518:3: ruleOpMulti
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXMultiplicativeExpressionRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXMultiplicativeExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOpMulti_in_ruleXMultiplicativeExpression3651);
            	    ruleOpMulti();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1531:4: ( (lv_rightOperand_3_0= ruleXUnaryOperation ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1532:1: (lv_rightOperand_3_0= ruleXUnaryOperation )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1532:1: (lv_rightOperand_3_0= ruleXUnaryOperation )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1533:3: lv_rightOperand_3_0= ruleXUnaryOperation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXMultiplicativeExpressionAccess().getRightOperandXUnaryOperationParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXUnaryOperation_in_ruleXMultiplicativeExpression3674);
            	    lv_rightOperand_3_0=ruleXUnaryOperation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXMultiplicativeExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"rightOperand",
            	              		lv_rightOperand_3_0, 
            	              		"XUnaryOperation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXMultiplicativeExpression"


    // $ANTLR start "entryRuleOpMulti"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1557:1: entryRuleOpMulti returns [String current=null] : iv_ruleOpMulti= ruleOpMulti EOF ;
    public final String entryRuleOpMulti() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpMulti = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1558:2: (iv_ruleOpMulti= ruleOpMulti EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1559:2: iv_ruleOpMulti= ruleOpMulti EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpMultiRule()); 
            }
            pushFollow(FOLLOW_ruleOpMulti_in_entryRuleOpMulti3713);
            iv_ruleOpMulti=ruleOpMulti();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpMulti.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpMulti3724); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpMulti"


    // $ANTLR start "ruleOpMulti"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1566:1: ruleOpMulti returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '**' | kw= '/' | kw= '%' ) ;
    public final AntlrDatatypeRuleToken ruleOpMulti() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1569:28: ( (kw= '*' | kw= '**' | kw= '/' | kw= '%' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1570:1: (kw= '*' | kw= '**' | kw= '/' | kw= '%' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1570:1: (kw= '*' | kw= '**' | kw= '/' | kw= '%' )
            int alt24=4;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt24=1;
                }
                break;
            case 46:
                {
                alt24=2;
                }
                break;
            case 47:
                {
                alt24=3;
                }
                break;
            case 48:
                {
                alt24=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1571:2: kw= '*'
                    {
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleOpMulti3762); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpMultiAccess().getAsteriskKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1578:2: kw= '**'
                    {
                    kw=(Token)match(input,46,FOLLOW_46_in_ruleOpMulti3781); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpMultiAccess().getAsteriskAsteriskKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1585:2: kw= '/'
                    {
                    kw=(Token)match(input,47,FOLLOW_47_in_ruleOpMulti3800); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpMultiAccess().getSolidusKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1592:2: kw= '%'
                    {
                    kw=(Token)match(input,48,FOLLOW_48_in_ruleOpMulti3819); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpMultiAccess().getPercentSignKeyword_3()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpMulti"


    // $ANTLR start "entryRuleXUnaryOperation"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1605:1: entryRuleXUnaryOperation returns [EObject current=null] : iv_ruleXUnaryOperation= ruleXUnaryOperation EOF ;
    public final EObject entryRuleXUnaryOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXUnaryOperation = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1606:2: (iv_ruleXUnaryOperation= ruleXUnaryOperation EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1607:2: iv_ruleXUnaryOperation= ruleXUnaryOperation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXUnaryOperationRule()); 
            }
            pushFollow(FOLLOW_ruleXUnaryOperation_in_entryRuleXUnaryOperation3859);
            iv_ruleXUnaryOperation=ruleXUnaryOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXUnaryOperation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXUnaryOperation3869); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXUnaryOperation"


    // $ANTLR start "ruleXUnaryOperation"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1614:1: ruleXUnaryOperation returns [EObject current=null] : ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXCastedExpression ) ) ) | this_XCastedExpression_3= ruleXCastedExpression ) ;
    public final EObject ruleXUnaryOperation() throws RecognitionException {
        EObject current = null;

        EObject lv_operand_2_0 = null;

        EObject this_XCastedExpression_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1617:28: ( ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXCastedExpression ) ) ) | this_XCastedExpression_3= ruleXCastedExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1618:1: ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXCastedExpression ) ) ) | this_XCastedExpression_3= ruleXCastedExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1618:1: ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXCastedExpression ) ) ) | this_XCastedExpression_3= ruleXCastedExpression )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=44 && LA25_0<=45)||LA25_0==49) ) {
                alt25=1;
            }
            else if ( ((LA25_0>=RULE_ID && LA25_0<=RULE_DECIMAL)||LA25_0==21||LA25_0==25||LA25_0==37||LA25_0==54||LA25_0==56||LA25_0==59||LA25_0==61||(LA25_0>=64 && LA25_0<=65)||LA25_0==68||(LA25_0>=70 && LA25_0<=77)) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1618:2: ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXCastedExpression ) ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1618:2: ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXCastedExpression ) ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1618:3: () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXCastedExpression ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1618:3: ()
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1619:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getXUnaryOperationAccess().getXUnaryOperationAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1624:2: ( ( ruleOpUnary ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1625:1: ( ruleOpUnary )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1625:1: ( ruleOpUnary )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1626:3: ruleOpUnary
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getXUnaryOperationRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXUnaryOperationAccess().getFeatureJvmIdentifiableElementCrossReference_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleOpUnary_in_ruleXUnaryOperation3927);
                    ruleOpUnary();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1639:2: ( (lv_operand_2_0= ruleXCastedExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1640:1: (lv_operand_2_0= ruleXCastedExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1640:1: (lv_operand_2_0= ruleXCastedExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1641:3: lv_operand_2_0= ruleXCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXUnaryOperationAccess().getOperandXCastedExpressionParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXCastedExpression_in_ruleXUnaryOperation3948);
                    lv_operand_2_0=ruleXCastedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXUnaryOperationRule());
                      	        }
                             		set(
                             			current, 
                             			"operand",
                              		lv_operand_2_0, 
                              		"XCastedExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1659:5: this_XCastedExpression_3= ruleXCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXUnaryOperationAccess().getXCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXCastedExpression_in_ruleXUnaryOperation3977);
                    this_XCastedExpression_3=ruleXCastedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XCastedExpression_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXUnaryOperation"


    // $ANTLR start "entryRuleOpUnary"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1675:1: entryRuleOpUnary returns [String current=null] : iv_ruleOpUnary= ruleOpUnary EOF ;
    public final String entryRuleOpUnary() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpUnary = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1676:2: (iv_ruleOpUnary= ruleOpUnary EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1677:2: iv_ruleOpUnary= ruleOpUnary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpUnaryRule()); 
            }
            pushFollow(FOLLOW_ruleOpUnary_in_entryRuleOpUnary4013);
            iv_ruleOpUnary=ruleOpUnary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpUnary.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOpUnary4024); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpUnary"


    // $ANTLR start "ruleOpUnary"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1684:1: ruleOpUnary returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '!' | kw= '-' | kw= '+' ) ;
    public final AntlrDatatypeRuleToken ruleOpUnary() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1687:28: ( (kw= '!' | kw= '-' | kw= '+' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1688:1: (kw= '!' | kw= '-' | kw= '+' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1688:1: (kw= '!' | kw= '-' | kw= '+' )
            int alt26=3;
            switch ( input.LA(1) ) {
            case 49:
                {
                alt26=1;
                }
                break;
            case 45:
                {
                alt26=2;
                }
                break;
            case 44:
                {
                alt26=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1689:2: kw= '!'
                    {
                    kw=(Token)match(input,49,FOLLOW_49_in_ruleOpUnary4062); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpUnaryAccess().getExclamationMarkKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1696:2: kw= '-'
                    {
                    kw=(Token)match(input,45,FOLLOW_45_in_ruleOpUnary4081); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpUnaryAccess().getHyphenMinusKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1703:2: kw= '+'
                    {
                    kw=(Token)match(input,44,FOLLOW_44_in_ruleOpUnary4100); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getOpUnaryAccess().getPlusSignKeyword_2()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpUnary"


    // $ANTLR start "entryRuleXCastedExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1716:1: entryRuleXCastedExpression returns [EObject current=null] : iv_ruleXCastedExpression= ruleXCastedExpression EOF ;
    public final EObject entryRuleXCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXCastedExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1717:2: (iv_ruleXCastedExpression= ruleXCastedExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1718:2: iv_ruleXCastedExpression= ruleXCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXCastedExpression_in_entryRuleXCastedExpression4140);
            iv_ruleXCastedExpression=ruleXCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXCastedExpression4150); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXCastedExpression"


    // $ANTLR start "ruleXCastedExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1725:1: ruleXCastedExpression returns [EObject current=null] : (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* ) ;
    public final EObject ruleXCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XMemberFeatureCall_0 = null;

        EObject lv_type_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1728:28: ( (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1729:1: (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1729:1: (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1730:5: this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXCastedExpressionAccess().getXMemberFeatureCallParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXMemberFeatureCall_in_ruleXCastedExpression4197);
            this_XMemberFeatureCall_0=ruleXMemberFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XMemberFeatureCall_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:1: ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==50) ) {
                    int LA27_2 = input.LA(2);

                    if ( (synpred12_InternalKDiagram()) ) {
                        alt27=1;
                    }


                }


                switch (alt27) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:2: ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:2: ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:3: ( ( () 'as' ) )=> ( () otherlv_2= 'as' )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1740:5: ( () otherlv_2= 'as' )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1740:6: () otherlv_2= 'as'
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1740:6: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1741:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXCastedExpressionAccess().getXCastedExpressionTargetAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,50,FOLLOW_50_in_ruleXCastedExpression4232); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getXCastedExpressionAccess().getAsKeyword_1_0_0_1());
            	          
            	    }

            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1750:3: ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1751:1: (lv_type_3_0= ruleJvmTypeReference )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1751:1: (lv_type_3_0= ruleJvmTypeReference )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1752:3: lv_type_3_0= ruleJvmTypeReference
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXCastedExpressionAccess().getTypeJvmTypeReferenceParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleXCastedExpression4255);
            	    lv_type_3_0=ruleJvmTypeReference();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXCastedExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"type",
            	              		lv_type_3_0, 
            	              		"JvmTypeReference");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXCastedExpression"


    // $ANTLR start "entryRuleXMemberFeatureCall"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1776:1: entryRuleXMemberFeatureCall returns [EObject current=null] : iv_ruleXMemberFeatureCall= ruleXMemberFeatureCall EOF ;
    public final EObject entryRuleXMemberFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXMemberFeatureCall = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1777:2: (iv_ruleXMemberFeatureCall= ruleXMemberFeatureCall EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1778:2: iv_ruleXMemberFeatureCall= ruleXMemberFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXMemberFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleXMemberFeatureCall_in_entryRuleXMemberFeatureCall4293);
            iv_ruleXMemberFeatureCall=ruleXMemberFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXMemberFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXMemberFeatureCall4303); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXMemberFeatureCall"


    // $ANTLR start "ruleXMemberFeatureCall"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1785:1: ruleXMemberFeatureCall returns [EObject current=null] : (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? ) )* ) ;
    public final EObject ruleXMemberFeatureCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_7=null;
        Token lv_nullSafe_8_0=null;
        Token lv_spreading_9_0=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token lv_explicitOperationCall_16_0=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        EObject this_XPrimaryExpression_0 = null;

        EObject lv_value_5_0 = null;

        EObject lv_typeArguments_11_0 = null;

        EObject lv_typeArguments_13_0 = null;

        EObject lv_memberCallArguments_17_0 = null;

        EObject lv_memberCallArguments_18_0 = null;

        EObject lv_memberCallArguments_20_0 = null;

        EObject lv_memberCallArguments_22_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1788:28: ( (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? ) )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1789:1: (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? ) )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1789:1: (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? ) )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1790:5: this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getXPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleXPrimaryExpression_in_ruleXMemberFeatureCall4350);
            this_XPrimaryExpression_0=ruleXPrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XPrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:1: ( ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? ) )*
            loop35:
            do {
                int alt35=3;
                switch ( input.LA(1) ) {
                case 19:
                    {
                    int LA35_2 = input.LA(2);

                    if ( (synpred13_InternalKDiagram()) ) {
                        alt35=1;
                    }
                    else if ( (synpred14_InternalKDiagram()) ) {
                        alt35=2;
                    }


                    }
                    break;
                case 51:
                    {
                    int LA35_3 = input.LA(2);

                    if ( (synpred14_InternalKDiagram()) ) {
                        alt35=2;
                    }


                    }
                    break;
                case 52:
                    {
                    int LA35_4 = input.LA(2);

                    if ( (synpred14_InternalKDiagram()) ) {
                        alt35=2;
                    }


                    }
                    break;

                }

                switch (alt35) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:2: ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:2: ( ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:3: ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) ) ( (lv_value_5_0= ruleXAssignment ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:3: ( ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:4: ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )=> ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1804:25: ( () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1804:26: () otherlv_2= '.' ( ( ruleValidID ) ) ruleOpSingleAssign
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1804:26: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1805:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXMemberFeatureCallAccess().getXAssignmentAssignableAction_1_0_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,19,FOLLOW_19_in_ruleXMemberFeatureCall4399); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getXMemberFeatureCallAccess().getFullStopKeyword_1_0_0_0_1());
            	          
            	    }
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1814:1: ( ( ruleValidID ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1815:1: ( ruleValidID )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1815:1: ( ruleValidID )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1816:3: ruleValidID
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleValidID_in_ruleXMemberFeatureCall4422);
            	    ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getOpSingleAssignParserRuleCall_1_0_0_0_3()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleOpSingleAssign_in_ruleXMemberFeatureCall4438);
            	    ruleOpSingleAssign();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	              afterParserOrEnumRuleCall();
            	          
            	    }

            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1837:3: ( (lv_value_5_0= ruleXAssignment ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1838:1: (lv_value_5_0= ruleXAssignment )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1838:1: (lv_value_5_0= ruleXAssignment )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1839:3: lv_value_5_0= ruleXAssignment
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getValueXAssignmentParserRuleCall_1_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXAssignment_in_ruleXMemberFeatureCall4460);
            	    lv_value_5_0=ruleXAssignment();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"value",
            	              		lv_value_5_0, 
            	              		"XAssignment");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:6: ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:6: ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )? )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:7: ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) ) (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )? ( ( ruleValidID ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )?
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:7: ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:8: ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )=> ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1872:7: ( () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1872:8: () (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1872:8: ()
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1873:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getXMemberFeatureCallAccess().getXMemberFeatureCallMemberCallTargetAction_1_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1878:2: (otherlv_7= '.' | ( (lv_nullSafe_8_0= '?.' ) ) | ( (lv_spreading_9_0= '*.' ) ) )
            	    int alt28=3;
            	    switch ( input.LA(1) ) {
            	    case 19:
            	        {
            	        alt28=1;
            	        }
            	        break;
            	    case 51:
            	        {
            	        alt28=2;
            	        }
            	        break;
            	    case 52:
            	        {
            	        alt28=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 28, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt28) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1878:4: otherlv_7= '.'
            	            {
            	            otherlv_7=(Token)match(input,19,FOLLOW_19_in_ruleXMemberFeatureCall4546); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_7, grammarAccess.getXMemberFeatureCallAccess().getFullStopKeyword_1_1_0_0_1_0());
            	                  
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1883:6: ( (lv_nullSafe_8_0= '?.' ) )
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1883:6: ( (lv_nullSafe_8_0= '?.' ) )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1884:1: (lv_nullSafe_8_0= '?.' )
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1884:1: (lv_nullSafe_8_0= '?.' )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1885:3: lv_nullSafe_8_0= '?.'
            	            {
            	            lv_nullSafe_8_0=(Token)match(input,51,FOLLOW_51_in_ruleXMemberFeatureCall4570); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_nullSafe_8_0, grammarAccess.getXMemberFeatureCallAccess().getNullSafeQuestionMarkFullStopKeyword_1_1_0_0_1_1_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	              	        }
            	                     		setWithLastConsumed(current, "nullSafe", true, "?.");
            	              	    
            	            }

            	            }


            	            }


            	            }
            	            break;
            	        case 3 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1899:6: ( (lv_spreading_9_0= '*.' ) )
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1899:6: ( (lv_spreading_9_0= '*.' ) )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1900:1: (lv_spreading_9_0= '*.' )
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1900:1: (lv_spreading_9_0= '*.' )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1901:3: lv_spreading_9_0= '*.'
            	            {
            	            lv_spreading_9_0=(Token)match(input,52,FOLLOW_52_in_ruleXMemberFeatureCall4607); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_spreading_9_0, grammarAccess.getXMemberFeatureCallAccess().getSpreadingAsteriskFullStopKeyword_1_1_0_0_1_2_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	              	        }
            	                     		setWithLastConsumed(current, "spreading", true, "*.");
            	              	    
            	            }

            	            }


            	            }


            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1914:5: (otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>' )?
            	    int alt30=2;
            	    int LA30_0 = input.LA(1);

            	    if ( (LA30_0==37) ) {
            	        alt30=1;
            	    }
            	    switch (alt30) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1914:7: otherlv_10= '<' ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) ) (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )* otherlv_14= '>'
            	            {
            	            otherlv_10=(Token)match(input,37,FOLLOW_37_in_ruleXMemberFeatureCall4636); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_10, grammarAccess.getXMemberFeatureCallAccess().getLessThanSignKeyword_1_1_1_0());
            	                  
            	            }
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1918:1: ( (lv_typeArguments_11_0= ruleJvmArgumentTypeReference ) )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1919:1: (lv_typeArguments_11_0= ruleJvmArgumentTypeReference )
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1919:1: (lv_typeArguments_11_0= ruleJvmArgumentTypeReference )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1920:3: lv_typeArguments_11_0= ruleJvmArgumentTypeReference
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_1_1_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleXMemberFeatureCall4657);
            	            lv_typeArguments_11_0=ruleJvmArgumentTypeReference();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	              	        }
            	                     		add(
            	                     			current, 
            	                     			"typeArguments",
            	                      		lv_typeArguments_11_0, 
            	                      		"JvmArgumentTypeReference");
            	              	        afterParserOrEnumRuleCall();
            	              	    
            	            }

            	            }


            	            }

            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1936:2: (otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) ) )*
            	            loop29:
            	            do {
            	                int alt29=2;
            	                int LA29_0 = input.LA(1);

            	                if ( (LA29_0==53) ) {
            	                    alt29=1;
            	                }


            	                switch (alt29) {
            	            	case 1 :
            	            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1936:4: otherlv_12= ',' ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) )
            	            	    {
            	            	    otherlv_12=(Token)match(input,53,FOLLOW_53_in_ruleXMemberFeatureCall4670); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_12, grammarAccess.getXMemberFeatureCallAccess().getCommaKeyword_1_1_1_2_0());
            	            	          
            	            	    }
            	            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1940:1: ( (lv_typeArguments_13_0= ruleJvmArgumentTypeReference ) )
            	            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1941:1: (lv_typeArguments_13_0= ruleJvmArgumentTypeReference )
            	            	    {
            	            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1941:1: (lv_typeArguments_13_0= ruleJvmArgumentTypeReference )
            	            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1942:3: lv_typeArguments_13_0= ruleJvmArgumentTypeReference
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_1_2_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleXMemberFeatureCall4691);
            	            	    lv_typeArguments_13_0=ruleJvmArgumentTypeReference();

            	            	    state._fsp--;
            	            	    if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	      	        if (current==null) {
            	            	      	            current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	            	      	        }
            	            	             		add(
            	            	             			current, 
            	            	             			"typeArguments",
            	            	              		lv_typeArguments_13_0, 
            	            	              		"JvmArgumentTypeReference");
            	            	      	        afterParserOrEnumRuleCall();
            	            	      	    
            	            	    }

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop29;
            	                }
            	            } while (true);

            	            otherlv_14=(Token)match(input,36,FOLLOW_36_in_ruleXMemberFeatureCall4705); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_14, grammarAccess.getXMemberFeatureCallAccess().getGreaterThanSignKeyword_1_1_1_3());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1962:3: ( ( ruleValidID ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1963:1: ( ruleValidID )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1963:1: ( ruleValidID )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1964:3: ruleValidID
            	    {
            	    if ( state.backtracking==0 ) {

            	      			if (current==null) {
            	      	            current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	      	        }
            	              
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getFeatureJvmIdentifiableElementCrossReference_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleValidID_in_ruleXMemberFeatureCall4730);
            	    ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {
            	       
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1977:2: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )?
            	    int alt33=2;
            	    alt33 = dfa33.predict(input);
            	    switch (alt33) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1977:3: ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')'
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1977:3: ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1977:4: ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' )
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1984:1: (lv_explicitOperationCall_16_0= '(' )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1985:3: lv_explicitOperationCall_16_0= '('
            	            {
            	            lv_explicitOperationCall_16_0=(Token)match(input,54,FOLLOW_54_in_ruleXMemberFeatureCall4764); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_explicitOperationCall_16_0, grammarAccess.getXMemberFeatureCallAccess().getExplicitOperationCallLeftParenthesisKeyword_1_1_3_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	              	        }
            	                     		setWithLastConsumed(current, "explicitOperationCall", true, "(");
            	              	    
            	            }

            	            }


            	            }

            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )?
            	            int alt32=3;
            	            alt32 = dfa32.predict(input);
            	            switch (alt32) {
            	                case 1 :
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) )
            	                    {
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) )
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:4: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure )
            	                    {
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2015:1: (lv_memberCallArguments_17_0= ruleXShortClosure )
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2016:3: lv_memberCallArguments_17_0= ruleXShortClosure
            	                    {
            	                    if ( state.backtracking==0 ) {
            	                       
            	                      	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXShortClosureParserRuleCall_1_1_3_1_0_0()); 
            	                      	    
            	                    }
            	                    pushFollow(FOLLOW_ruleXShortClosure_in_ruleXMemberFeatureCall4849);
            	                    lv_memberCallArguments_17_0=ruleXShortClosure();

            	                    state._fsp--;
            	                    if (state.failed) return current;
            	                    if ( state.backtracking==0 ) {

            	                      	        if (current==null) {
            	                      	            current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	                      	        }
            	                             		add(
            	                             			current, 
            	                             			"memberCallArguments",
            	                              		lv_memberCallArguments_17_0, 
            	                              		"XShortClosure");
            	                      	        afterParserOrEnumRuleCall();
            	                      	    
            	                    }

            	                    }


            	                    }


            	                    }
            	                    break;
            	                case 2 :
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2033:6: ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* )
            	                    {
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2033:6: ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* )
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2033:7: ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )*
            	                    {
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2033:7: ( (lv_memberCallArguments_18_0= ruleXExpression ) )
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2034:1: (lv_memberCallArguments_18_0= ruleXExpression )
            	                    {
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2034:1: (lv_memberCallArguments_18_0= ruleXExpression )
            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2035:3: lv_memberCallArguments_18_0= ruleXExpression
            	                    {
            	                    if ( state.backtracking==0 ) {
            	                       
            	                      	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXExpressionParserRuleCall_1_1_3_1_1_0_0()); 
            	                      	    
            	                    }
            	                    pushFollow(FOLLOW_ruleXExpression_in_ruleXMemberFeatureCall4877);
            	                    lv_memberCallArguments_18_0=ruleXExpression();

            	                    state._fsp--;
            	                    if (state.failed) return current;
            	                    if ( state.backtracking==0 ) {

            	                      	        if (current==null) {
            	                      	            current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	                      	        }
            	                             		add(
            	                             			current, 
            	                             			"memberCallArguments",
            	                              		lv_memberCallArguments_18_0, 
            	                              		"XExpression");
            	                      	        afterParserOrEnumRuleCall();
            	                      	    
            	                    }

            	                    }


            	                    }

            	                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2051:2: (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )*
            	                    loop31:
            	                    do {
            	                        int alt31=2;
            	                        int LA31_0 = input.LA(1);

            	                        if ( (LA31_0==53) ) {
            	                            alt31=1;
            	                        }


            	                        switch (alt31) {
            	                    	case 1 :
            	                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2051:4: otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) )
            	                    	    {
            	                    	    otherlv_19=(Token)match(input,53,FOLLOW_53_in_ruleXMemberFeatureCall4890); if (state.failed) return current;
            	                    	    if ( state.backtracking==0 ) {

            	                    	          	newLeafNode(otherlv_19, grammarAccess.getXMemberFeatureCallAccess().getCommaKeyword_1_1_3_1_1_1_0());
            	                    	          
            	                    	    }
            	                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2055:1: ( (lv_memberCallArguments_20_0= ruleXExpression ) )
            	                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2056:1: (lv_memberCallArguments_20_0= ruleXExpression )
            	                    	    {
            	                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2056:1: (lv_memberCallArguments_20_0= ruleXExpression )
            	                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2057:3: lv_memberCallArguments_20_0= ruleXExpression
            	                    	    {
            	                    	    if ( state.backtracking==0 ) {
            	                    	       
            	                    	      	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXExpressionParserRuleCall_1_1_3_1_1_1_1_0()); 
            	                    	      	    
            	                    	    }
            	                    	    pushFollow(FOLLOW_ruleXExpression_in_ruleXMemberFeatureCall4911);
            	                    	    lv_memberCallArguments_20_0=ruleXExpression();

            	                    	    state._fsp--;
            	                    	    if (state.failed) return current;
            	                    	    if ( state.backtracking==0 ) {

            	                    	      	        if (current==null) {
            	                    	      	            current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	                    	      	        }
            	                    	             		add(
            	                    	             			current, 
            	                    	             			"memberCallArguments",
            	                    	              		lv_memberCallArguments_20_0, 
            	                    	              		"XExpression");
            	                    	      	        afterParserOrEnumRuleCall();
            	                    	      	    
            	                    	    }

            	                    	    }


            	                    	    }


            	                    	    }
            	                    	    break;

            	                    	default :
            	                    	    break loop31;
            	                        }
            	                    } while (true);


            	                    }


            	                    }
            	                    break;

            	            }

            	            otherlv_21=(Token)match(input,55,FOLLOW_55_in_ruleXMemberFeatureCall4928); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_21, grammarAccess.getXMemberFeatureCallAccess().getRightParenthesisKeyword_1_1_3_2());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2077:3: ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )?
            	    int alt34=2;
            	    alt34 = dfa34.predict(input);
            	    switch (alt34) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2077:4: ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure )
            	            {
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2080:1: (lv_memberCallArguments_22_0= ruleXClosure )
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2081:3: lv_memberCallArguments_22_0= ruleXClosure
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXClosureParserRuleCall_1_1_4_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleXClosure_in_ruleXMemberFeatureCall4963);
            	            lv_memberCallArguments_22_0=ruleXClosure();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	              	        }
            	                     		add(
            	                     			current, 
            	                     			"memberCallArguments",
            	                      		lv_memberCallArguments_22_0, 
            	                      		"XClosure");
            	              	        afterParserOrEnumRuleCall();
            	              	    
            	            }

            	            }


            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXMemberFeatureCall"


    // $ANTLR start "entryRuleXPrimaryExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2105:1: entryRuleXPrimaryExpression returns [EObject current=null] : iv_ruleXPrimaryExpression= ruleXPrimaryExpression EOF ;
    public final EObject entryRuleXPrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPrimaryExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2106:2: (iv_ruleXPrimaryExpression= ruleXPrimaryExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2107:2: iv_ruleXPrimaryExpression= ruleXPrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXPrimaryExpression_in_entryRuleXPrimaryExpression5003);
            iv_ruleXPrimaryExpression=ruleXPrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXPrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXPrimaryExpression5013); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXPrimaryExpression"


    // $ANTLR start "ruleXPrimaryExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2114:1: ruleXPrimaryExpression returns [EObject current=null] : (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | this_XFeatureCall_3= ruleXFeatureCall | this_XLiteral_4= ruleXLiteral | this_XIfExpression_5= ruleXIfExpression | this_XForLoopExpression_6= ruleXForLoopExpression | this_XWhileExpression_7= ruleXWhileExpression | this_XDoWhileExpression_8= ruleXDoWhileExpression | this_XThrowExpression_9= ruleXThrowExpression | this_XReturnExpression_10= ruleXReturnExpression | this_XTryCatchFinallyExpression_11= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_12= ruleXParenthesizedExpression ) ;
    public final EObject ruleXPrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XConstructorCall_0 = null;

        EObject this_XBlockExpression_1 = null;

        EObject this_XSwitchExpression_2 = null;

        EObject this_XFeatureCall_3 = null;

        EObject this_XLiteral_4 = null;

        EObject this_XIfExpression_5 = null;

        EObject this_XForLoopExpression_6 = null;

        EObject this_XWhileExpression_7 = null;

        EObject this_XDoWhileExpression_8 = null;

        EObject this_XThrowExpression_9 = null;

        EObject this_XReturnExpression_10 = null;

        EObject this_XTryCatchFinallyExpression_11 = null;

        EObject this_XParenthesizedExpression_12 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2117:28: ( (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | this_XFeatureCall_3= ruleXFeatureCall | this_XLiteral_4= ruleXLiteral | this_XIfExpression_5= ruleXIfExpression | this_XForLoopExpression_6= ruleXForLoopExpression | this_XWhileExpression_7= ruleXWhileExpression | this_XDoWhileExpression_8= ruleXDoWhileExpression | this_XThrowExpression_9= ruleXThrowExpression | this_XReturnExpression_10= ruleXReturnExpression | this_XTryCatchFinallyExpression_11= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_12= ruleXParenthesizedExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2118:1: (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | this_XFeatureCall_3= ruleXFeatureCall | this_XLiteral_4= ruleXLiteral | this_XIfExpression_5= ruleXIfExpression | this_XForLoopExpression_6= ruleXForLoopExpression | this_XWhileExpression_7= ruleXWhileExpression | this_XDoWhileExpression_8= ruleXDoWhileExpression | this_XThrowExpression_9= ruleXThrowExpression | this_XReturnExpression_10= ruleXReturnExpression | this_XTryCatchFinallyExpression_11= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_12= ruleXParenthesizedExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2118:1: (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | this_XFeatureCall_3= ruleXFeatureCall | this_XLiteral_4= ruleXLiteral | this_XIfExpression_5= ruleXIfExpression | this_XForLoopExpression_6= ruleXForLoopExpression | this_XWhileExpression_7= ruleXWhileExpression | this_XDoWhileExpression_8= ruleXDoWhileExpression | this_XThrowExpression_9= ruleXThrowExpression | this_XReturnExpression_10= ruleXReturnExpression | this_XTryCatchFinallyExpression_11= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_12= ruleXParenthesizedExpression )
            int alt36=13;
            alt36 = dfa36.predict(input);
            switch (alt36) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2119:5: this_XConstructorCall_0= ruleXConstructorCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXConstructorCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXConstructorCall_in_ruleXPrimaryExpression5060);
                    this_XConstructorCall_0=ruleXConstructorCall();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XConstructorCall_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2129:5: this_XBlockExpression_1= ruleXBlockExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXBlockExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXBlockExpression_in_ruleXPrimaryExpression5087);
                    this_XBlockExpression_1=ruleXBlockExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XBlockExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2139:5: this_XSwitchExpression_2= ruleXSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXSwitchExpression_in_ruleXPrimaryExpression5114);
                    this_XSwitchExpression_2=ruleXSwitchExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XSwitchExpression_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2149:5: this_XFeatureCall_3= ruleXFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXFeatureCallParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXFeatureCall_in_ruleXPrimaryExpression5141);
                    this_XFeatureCall_3=ruleXFeatureCall();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XFeatureCall_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2159:5: this_XLiteral_4= ruleXLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXLiteral_in_ruleXPrimaryExpression5168);
                    this_XLiteral_4=ruleXLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XLiteral_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2169:5: this_XIfExpression_5= ruleXIfExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXIfExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXIfExpression_in_ruleXPrimaryExpression5195);
                    this_XIfExpression_5=ruleXIfExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XIfExpression_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2179:5: this_XForLoopExpression_6= ruleXForLoopExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXForLoopExpressionParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXForLoopExpression_in_ruleXPrimaryExpression5222);
                    this_XForLoopExpression_6=ruleXForLoopExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XForLoopExpression_6; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2189:5: this_XWhileExpression_7= ruleXWhileExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXWhileExpressionParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXWhileExpression_in_ruleXPrimaryExpression5249);
                    this_XWhileExpression_7=ruleXWhileExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XWhileExpression_7; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2199:5: this_XDoWhileExpression_8= ruleXDoWhileExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXDoWhileExpressionParserRuleCall_8()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXDoWhileExpression_in_ruleXPrimaryExpression5276);
                    this_XDoWhileExpression_8=ruleXDoWhileExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XDoWhileExpression_8; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2209:5: this_XThrowExpression_9= ruleXThrowExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXThrowExpressionParserRuleCall_9()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXThrowExpression_in_ruleXPrimaryExpression5303);
                    this_XThrowExpression_9=ruleXThrowExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XThrowExpression_9; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2219:5: this_XReturnExpression_10= ruleXReturnExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXReturnExpressionParserRuleCall_10()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXReturnExpression_in_ruleXPrimaryExpression5330);
                    this_XReturnExpression_10=ruleXReturnExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XReturnExpression_10; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2229:5: this_XTryCatchFinallyExpression_11= ruleXTryCatchFinallyExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXTryCatchFinallyExpressionParserRuleCall_11()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXTryCatchFinallyExpression_in_ruleXPrimaryExpression5357);
                    this_XTryCatchFinallyExpression_11=ruleXTryCatchFinallyExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XTryCatchFinallyExpression_11; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 13 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2239:5: this_XParenthesizedExpression_12= ruleXParenthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXParenthesizedExpressionParserRuleCall_12()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXParenthesizedExpression_in_ruleXPrimaryExpression5384);
                    this_XParenthesizedExpression_12=ruleXParenthesizedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XParenthesizedExpression_12; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXPrimaryExpression"


    // $ANTLR start "entryRuleXLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2255:1: entryRuleXLiteral returns [EObject current=null] : iv_ruleXLiteral= ruleXLiteral EOF ;
    public final EObject entryRuleXLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXLiteral = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2256:2: (iv_ruleXLiteral= ruleXLiteral EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2257:2: iv_ruleXLiteral= ruleXLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleXLiteral_in_entryRuleXLiteral5419);
            iv_ruleXLiteral=ruleXLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXLiteral5429); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXLiteral"


    // $ANTLR start "ruleXLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2264:1: ruleXLiteral returns [EObject current=null] : ( ( ( ( () '[' ) )=>this_XClosure_0= ruleXClosure ) | this_XBooleanLiteral_1= ruleXBooleanLiteral | this_XNumberLiteral_2= ruleXNumberLiteral | this_XNullLiteral_3= ruleXNullLiteral | this_XStringLiteral_4= ruleXStringLiteral | this_XTypeLiteral_5= ruleXTypeLiteral ) ;
    public final EObject ruleXLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_XClosure_0 = null;

        EObject this_XBooleanLiteral_1 = null;

        EObject this_XNumberLiteral_2 = null;

        EObject this_XNullLiteral_3 = null;

        EObject this_XStringLiteral_4 = null;

        EObject this_XTypeLiteral_5 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2267:28: ( ( ( ( ( () '[' ) )=>this_XClosure_0= ruleXClosure ) | this_XBooleanLiteral_1= ruleXBooleanLiteral | this_XNumberLiteral_2= ruleXNumberLiteral | this_XNullLiteral_3= ruleXNullLiteral | this_XStringLiteral_4= ruleXStringLiteral | this_XTypeLiteral_5= ruleXTypeLiteral ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:1: ( ( ( ( () '[' ) )=>this_XClosure_0= ruleXClosure ) | this_XBooleanLiteral_1= ruleXBooleanLiteral | this_XNumberLiteral_2= ruleXNumberLiteral | this_XNullLiteral_3= ruleXNullLiteral | this_XStringLiteral_4= ruleXStringLiteral | this_XTypeLiteral_5= ruleXTypeLiteral )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:1: ( ( ( ( () '[' ) )=>this_XClosure_0= ruleXClosure ) | this_XBooleanLiteral_1= ruleXBooleanLiteral | this_XNumberLiteral_2= ruleXNumberLiteral | this_XNullLiteral_3= ruleXNullLiteral | this_XStringLiteral_4= ruleXStringLiteral | this_XTypeLiteral_5= ruleXTypeLiteral )
            int alt37=6;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==56) && (synpred18_InternalKDiagram())) {
                alt37=1;
            }
            else if ( ((LA37_0>=71 && LA37_0<=72)) ) {
                alt37=2;
            }
            else if ( ((LA37_0>=RULE_HEX && LA37_0<=RULE_DECIMAL)) ) {
                alt37=3;
            }
            else if ( (LA37_0==73) ) {
                alt37=4;
            }
            else if ( (LA37_0==RULE_STRING) ) {
                alt37=5;
            }
            else if ( (LA37_0==74) ) {
                alt37=6;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:2: ( ( ( () '[' ) )=>this_XClosure_0= ruleXClosure )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:2: ( ( ( () '[' ) )=>this_XClosure_0= ruleXClosure )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:3: ( ( () '[' ) )=>this_XClosure_0= ruleXClosure
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXLiteralAccess().getXClosureParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXClosure_in_ruleXLiteral5489);
                    this_XClosure_0=ruleXClosure();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XClosure_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2281:5: this_XBooleanLiteral_1= ruleXBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXLiteralAccess().getXBooleanLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXBooleanLiteral_in_ruleXLiteral5517);
                    this_XBooleanLiteral_1=ruleXBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XBooleanLiteral_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2291:5: this_XNumberLiteral_2= ruleXNumberLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXLiteralAccess().getXNumberLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXNumberLiteral_in_ruleXLiteral5544);
                    this_XNumberLiteral_2=ruleXNumberLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XNumberLiteral_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2301:5: this_XNullLiteral_3= ruleXNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXLiteralAccess().getXNullLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXNullLiteral_in_ruleXLiteral5571);
                    this_XNullLiteral_3=ruleXNullLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XNullLiteral_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2311:5: this_XStringLiteral_4= ruleXStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXLiteralAccess().getXStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXStringLiteral_in_ruleXLiteral5598);
                    this_XStringLiteral_4=ruleXStringLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XStringLiteral_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2321:5: this_XTypeLiteral_5= ruleXTypeLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXLiteralAccess().getXTypeLiteralParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXTypeLiteral_in_ruleXLiteral5625);
                    this_XTypeLiteral_5=ruleXTypeLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XTypeLiteral_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXLiteral"


    // $ANTLR start "entryRuleXClosure"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2337:1: entryRuleXClosure returns [EObject current=null] : iv_ruleXClosure= ruleXClosure EOF ;
    public final EObject entryRuleXClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClosure = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2338:2: (iv_ruleXClosure= ruleXClosure EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2339:2: iv_ruleXClosure= ruleXClosure EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXClosureRule()); 
            }
            pushFollow(FOLLOW_ruleXClosure_in_entryRuleXClosure5660);
            iv_ruleXClosure=ruleXClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXClosure; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXClosure5670); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXClosure"


    // $ANTLR start "ruleXClosure"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2346:1: ruleXClosure returns [EObject current=null] : ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' ) ;
    public final EObject ruleXClosure() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_explicitSyntax_5_0=null;
        Token otherlv_7=null;
        EObject lv_declaredFormalParameters_2_0 = null;

        EObject lv_declaredFormalParameters_4_0 = null;

        EObject lv_expression_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2349:28: ( ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2350:1: ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2350:1: ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2350:2: ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']'
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2350:2: ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2350:3: ( ( () '[' ) )=> ( () otherlv_1= '[' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2352:5: ( () otherlv_1= '[' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2352:6: () otherlv_1= '['
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2352:6: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2353:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXClosureAccess().getXClosureAction_0_0_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,56,FOLLOW_56_in_ruleXClosure5730); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXClosureAccess().getLeftSquareBracketKeyword_0_0_1());
                  
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:3: ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )?
            int alt40=2;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:4: ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2377:6: ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2377:7: ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2377:7: ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==RULE_ID||LA39_0==40||LA39_0==54) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2377:8: ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )*
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2377:8: ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2378:1: (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2378:1: (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2379:3: lv_declaredFormalParameters_2_0= ruleJvmFormalParameter
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_1_0_0_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleJvmFormalParameter_in_ruleXClosure5803);
                            lv_declaredFormalParameters_2_0=ruleJvmFormalParameter();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXClosureRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"declaredFormalParameters",
                                      		lv_declaredFormalParameters_2_0, 
                                      		"JvmFormalParameter");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2395:2: (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )*
                            loop38:
                            do {
                                int alt38=2;
                                int LA38_0 = input.LA(1);

                                if ( (LA38_0==53) ) {
                                    alt38=1;
                                }


                                switch (alt38) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2395:4: otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) )
                            	    {
                            	    otherlv_3=(Token)match(input,53,FOLLOW_53_in_ruleXClosure5816); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_3, grammarAccess.getXClosureAccess().getCommaKeyword_1_0_0_1_0());
                            	          
                            	    }
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2399:1: ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2400:1: (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter )
                            	    {
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2400:1: (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2401:3: lv_declaredFormalParameters_4_0= ruleJvmFormalParameter
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getXClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_1_0_0_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleJvmFormalParameter_in_ruleXClosure5837);
                            	    lv_declaredFormalParameters_4_0=ruleJvmFormalParameter();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getXClosureRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"declaredFormalParameters",
                            	              		lv_declaredFormalParameters_4_0, 
                            	              		"JvmFormalParameter");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop38;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2417:6: ( (lv_explicitSyntax_5_0= '|' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2418:1: (lv_explicitSyntax_5_0= '|' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2418:1: (lv_explicitSyntax_5_0= '|' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2419:3: lv_explicitSyntax_5_0= '|'
                    {
                    lv_explicitSyntax_5_0=(Token)match(input,57,FOLLOW_57_in_ruleXClosure5859); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_explicitSyntax_5_0, grammarAccess.getXClosureAccess().getExplicitSyntaxVerticalLineKeyword_1_0_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getXClosureRule());
                      	        }
                             		setWithLastConsumed(current, "explicitSyntax", true, "|");
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2432:5: ( (lv_expression_6_0= ruleXExpressionInClosure ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2433:1: (lv_expression_6_0= ruleXExpressionInClosure )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2433:1: (lv_expression_6_0= ruleXExpressionInClosure )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2434:3: lv_expression_6_0= ruleXExpressionInClosure
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXClosureAccess().getExpressionXExpressionInClosureParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpressionInClosure_in_ruleXClosure5896);
            lv_expression_6_0=ruleXExpressionInClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXClosureRule());
              	        }
                     		set(
                     			current, 
                     			"expression",
                      		lv_expression_6_0, 
                      		"XExpressionInClosure");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_7=(Token)match(input,58,FOLLOW_58_in_ruleXClosure5908); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getXClosureAccess().getRightSquareBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXClosure"


    // $ANTLR start "entryRuleXExpressionInClosure"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2462:1: entryRuleXExpressionInClosure returns [EObject current=null] : iv_ruleXExpressionInClosure= ruleXExpressionInClosure EOF ;
    public final EObject entryRuleXExpressionInClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionInClosure = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2463:2: (iv_ruleXExpressionInClosure= ruleXExpressionInClosure EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2464:2: iv_ruleXExpressionInClosure= ruleXExpressionInClosure EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionInClosureRule()); 
            }
            pushFollow(FOLLOW_ruleXExpressionInClosure_in_entryRuleXExpressionInClosure5944);
            iv_ruleXExpressionInClosure=ruleXExpressionInClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpressionInClosure; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionInClosure5954); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXExpressionInClosure"


    // $ANTLR start "ruleXExpressionInClosure"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2471:1: ruleXExpressionInClosure returns [EObject current=null] : ( () ( ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) ) (otherlv_2= ';' )? )* ) ;
    public final EObject ruleXExpressionInClosure() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_expressions_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2474:28: ( ( () ( ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) ) (otherlv_2= ';' )? )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2475:1: ( () ( ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) ) (otherlv_2= ';' )? )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2475:1: ( () ( ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) ) (otherlv_2= ';' )? )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2475:2: () ( ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) ) (otherlv_2= ';' )? )*
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2475:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2476:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXExpressionInClosureAccess().getXBlockExpressionAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2481:2: ( ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) ) (otherlv_2= ';' )? )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=RULE_ID && LA42_0<=RULE_DECIMAL)||LA42_0==21||LA42_0==25||LA42_0==37||(LA42_0>=44 && LA42_0<=45)||LA42_0==49||LA42_0==54||LA42_0==56||LA42_0==59||LA42_0==61||(LA42_0>=64 && LA42_0<=68)||(LA42_0>=70 && LA42_0<=77)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2481:3: ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) ) (otherlv_2= ';' )?
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2481:3: ( (lv_expressions_1_0= ruleXExpressionInsideBlock ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2482:1: (lv_expressions_1_0= ruleXExpressionInsideBlock )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2482:1: (lv_expressions_1_0= ruleXExpressionInsideBlock )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2483:3: lv_expressions_1_0= ruleXExpressionInsideBlock
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXExpressionInClosureAccess().getExpressionsXExpressionInsideBlockParserRuleCall_1_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXExpressionInsideBlock_in_ruleXExpressionInClosure6010);
            	    lv_expressions_1_0=ruleXExpressionInsideBlock();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXExpressionInClosureRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"expressions",
            	              		lv_expressions_1_0, 
            	              		"XExpressionInsideBlock");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2499:2: (otherlv_2= ';' )?
            	    int alt41=2;
            	    int LA41_0 = input.LA(1);

            	    if ( (LA41_0==14) ) {
            	        alt41=1;
            	    }
            	    switch (alt41) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2499:4: otherlv_2= ';'
            	            {
            	            otherlv_2=(Token)match(input,14,FOLLOW_14_in_ruleXExpressionInClosure6023); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_2, grammarAccess.getXExpressionInClosureAccess().getSemicolonKeyword_1_1());
            	                  
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpressionInClosure"


    // $ANTLR start "entryRuleXShortClosure"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2511:1: entryRuleXShortClosure returns [EObject current=null] : iv_ruleXShortClosure= ruleXShortClosure EOF ;
    public final EObject entryRuleXShortClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXShortClosure = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2512:2: (iv_ruleXShortClosure= ruleXShortClosure EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2513:2: iv_ruleXShortClosure= ruleXShortClosure EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXShortClosureRule()); 
            }
            pushFollow(FOLLOW_ruleXShortClosure_in_entryRuleXShortClosure6063);
            iv_ruleXShortClosure=ruleXShortClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXShortClosure; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXShortClosure6073); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXShortClosure"


    // $ANTLR start "ruleXShortClosure"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2520:1: ruleXShortClosure returns [EObject current=null] : ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) ) ;
    public final EObject ruleXShortClosure() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token lv_explicitSyntax_4_0=null;
        EObject lv_declaredFormalParameters_1_0 = null;

        EObject lv_declaredFormalParameters_3_0 = null;

        EObject lv_expression_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2523:28: ( ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2524:1: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2524:1: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2524:2: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2524:2: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2524:3: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2540:6: ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2540:7: () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2540:7: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2541:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXShortClosureAccess().getXClosureAction_0_0_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2546:2: ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==RULE_ID||LA44_0==40||LA44_0==54) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2546:3: ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )*
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2546:3: ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2547:1: (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2547:1: (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2548:3: lv_declaredFormalParameters_1_0= ruleJvmFormalParameter
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXShortClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_0_0_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmFormalParameter_in_ruleXShortClosure6181);
                    lv_declaredFormalParameters_1_0=ruleJvmFormalParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXShortClosureRule());
                      	        }
                             		add(
                             			current, 
                             			"declaredFormalParameters",
                              		lv_declaredFormalParameters_1_0, 
                              		"JvmFormalParameter");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2564:2: (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==53) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2564:4: otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,53,FOLLOW_53_in_ruleXShortClosure6194); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getXShortClosureAccess().getCommaKeyword_0_0_1_1_0());
                    	          
                    	    }
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2568:1: ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2569:1: (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter )
                    	    {
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2569:1: (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2570:3: lv_declaredFormalParameters_3_0= ruleJvmFormalParameter
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getXShortClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_0_0_1_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleJvmFormalParameter_in_ruleXShortClosure6215);
                    	    lv_declaredFormalParameters_3_0=ruleJvmFormalParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getXShortClosureRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"declaredFormalParameters",
                    	              		lv_declaredFormalParameters_3_0, 
                    	              		"JvmFormalParameter");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2586:6: ( (lv_explicitSyntax_4_0= '|' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2587:1: (lv_explicitSyntax_4_0= '|' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2587:1: (lv_explicitSyntax_4_0= '|' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2588:3: lv_explicitSyntax_4_0= '|'
            {
            lv_explicitSyntax_4_0=(Token)match(input,57,FOLLOW_57_in_ruleXShortClosure6237); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_explicitSyntax_4_0, grammarAccess.getXShortClosureAccess().getExplicitSyntaxVerticalLineKeyword_0_0_2_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getXShortClosureRule());
              	        }
                     		setWithLastConsumed(current, "explicitSyntax", true, "|");
              	    
            }

            }


            }


            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2601:4: ( (lv_expression_5_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2602:1: (lv_expression_5_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2602:1: (lv_expression_5_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2603:3: lv_expression_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXShortClosureAccess().getExpressionXExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXShortClosure6273);
            lv_expression_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXShortClosureRule());
              	        }
                     		set(
                     			current, 
                     			"expression",
                      		lv_expression_5_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXShortClosure"


    // $ANTLR start "entryRuleXParenthesizedExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2627:1: entryRuleXParenthesizedExpression returns [EObject current=null] : iv_ruleXParenthesizedExpression= ruleXParenthesizedExpression EOF ;
    public final EObject entryRuleXParenthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXParenthesizedExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2628:2: (iv_ruleXParenthesizedExpression= ruleXParenthesizedExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2629:2: iv_ruleXParenthesizedExpression= ruleXParenthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXParenthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXParenthesizedExpression_in_entryRuleXParenthesizedExpression6309);
            iv_ruleXParenthesizedExpression=ruleXParenthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXParenthesizedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXParenthesizedExpression6319); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXParenthesizedExpression"


    // $ANTLR start "ruleXParenthesizedExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2636:1: ruleXParenthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' ) ;
    public final EObject ruleXParenthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_XExpression_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2639:28: ( (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2640:1: (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2640:1: (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2640:3: otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,54,FOLLOW_54_in_ruleXParenthesizedExpression6356); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getXParenthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getXParenthesizedExpressionAccess().getXExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXParenthesizedExpression6378);
            this_XExpression_1=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_XExpression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,55,FOLLOW_55_in_ruleXParenthesizedExpression6389); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getXParenthesizedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXParenthesizedExpression"


    // $ANTLR start "entryRuleXIfExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2665:1: entryRuleXIfExpression returns [EObject current=null] : iv_ruleXIfExpression= ruleXIfExpression EOF ;
    public final EObject entryRuleXIfExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXIfExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2666:2: (iv_ruleXIfExpression= ruleXIfExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2667:2: iv_ruleXIfExpression= ruleXIfExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXIfExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXIfExpression_in_entryRuleXIfExpression6425);
            iv_ruleXIfExpression=ruleXIfExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXIfExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXIfExpression6435); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXIfExpression"


    // $ANTLR start "ruleXIfExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2674:1: ruleXIfExpression returns [EObject current=null] : ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? ) ;
    public final EObject ruleXIfExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_if_3_0 = null;

        EObject lv_then_5_0 = null;

        EObject lv_else_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2677:28: ( ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2678:1: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2678:1: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2678:2: () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )?
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2678:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2679:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXIfExpressionAccess().getXIfExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,59,FOLLOW_59_in_ruleXIfExpression6481); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXIfExpressionAccess().getIfKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,54,FOLLOW_54_in_ruleXIfExpression6493); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getXIfExpressionAccess().getLeftParenthesisKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2692:1: ( (lv_if_3_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2693:1: (lv_if_3_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2693:1: (lv_if_3_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2694:3: lv_if_3_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXIfExpressionAccess().getIfXExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXIfExpression6514);
            lv_if_3_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXIfExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"if",
                      		lv_if_3_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,55,FOLLOW_55_in_ruleXIfExpression6526); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getXIfExpressionAccess().getRightParenthesisKeyword_4());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2714:1: ( (lv_then_5_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2715:1: (lv_then_5_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2715:1: (lv_then_5_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2716:3: lv_then_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXIfExpressionAccess().getThenXExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXIfExpression6547);
            lv_then_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXIfExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"then",
                      		lv_then_5_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2732:2: ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==60) ) {
                int LA45_1 = input.LA(2);

                if ( (synpred22_InternalKDiagram()) ) {
                    alt45=1;
                }
            }
            switch (alt45) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2732:3: ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2732:3: ( ( 'else' )=>otherlv_6= 'else' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2732:4: ( 'else' )=>otherlv_6= 'else'
                    {
                    otherlv_6=(Token)match(input,60,FOLLOW_60_in_ruleXIfExpression6568); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getXIfExpressionAccess().getElseKeyword_6_0());
                          
                    }

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2737:2: ( (lv_else_7_0= ruleXExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2738:1: (lv_else_7_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2738:1: (lv_else_7_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2739:3: lv_else_7_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXIfExpressionAccess().getElseXExpressionParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXIfExpression6590);
                    lv_else_7_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXIfExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"else",
                              		lv_else_7_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXIfExpression"


    // $ANTLR start "entryRuleXSwitchExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2763:1: entryRuleXSwitchExpression returns [EObject current=null] : iv_ruleXSwitchExpression= ruleXSwitchExpression EOF ;
    public final EObject entryRuleXSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSwitchExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2764:2: (iv_ruleXSwitchExpression= ruleXSwitchExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2765:2: iv_ruleXSwitchExpression= ruleXSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXSwitchExpression_in_entryRuleXSwitchExpression6628);
            iv_ruleXSwitchExpression=ruleXSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXSwitchExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXSwitchExpression6638); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSwitchExpression"


    // $ANTLR start "ruleXSwitchExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2772:1: ruleXSwitchExpression returns [EObject current=null] : ( () otherlv_1= 'switch' ( ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) ) | ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )+ (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' ) ;
    public final EObject ruleXSwitchExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        AntlrDatatypeRuleToken lv_localVarName_2_0 = null;

        EObject lv_switch_4_0 = null;

        AntlrDatatypeRuleToken lv_localVarName_6_0 = null;

        EObject lv_switch_8_0 = null;

        EObject lv_cases_11_0 = null;

        EObject lv_default_14_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2775:28: ( ( () otherlv_1= 'switch' ( ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) ) | ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )+ (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2776:1: ( () otherlv_1= 'switch' ( ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) ) | ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )+ (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2776:1: ( () otherlv_1= 'switch' ( ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) ) | ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )+ (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2776:2: () otherlv_1= 'switch' ( ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) ) | ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )+ (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}'
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2776:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2777:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXSwitchExpressionAccess().getXSwitchExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,61,FOLLOW_61_in_ruleXSwitchExpression6684); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXSwitchExpressionAccess().getSwitchKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:1: ( ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) ) | ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' ) )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( ((LA47_0>=RULE_ID && LA47_0<=RULE_DECIMAL)||LA47_0==21||LA47_0==25||LA47_0==37||(LA47_0>=44 && LA47_0<=45)||LA47_0==49||LA47_0==56||LA47_0==59||LA47_0==61||(LA47_0>=64 && LA47_0<=65)||LA47_0==68||(LA47_0>=70 && LA47_0<=77)) ) {
                alt47=1;
            }
            else if ( (LA47_0==54) ) {
                int LA47_2 = input.LA(2);

                if ( (LA47_2==RULE_ID) ) {
                    int LA47_3 = input.LA(3);

                    if ( ((LA47_3>=19 && LA47_3<=20)||LA47_3==24||(LA47_3>=28 && LA47_3<=48)||(LA47_3>=50 && LA47_3<=52)||(LA47_3>=54 && LA47_3<=56)||LA47_3==69) ) {
                        alt47=1;
                    }
                    else if ( (LA47_3==22) && (synpred24_InternalKDiagram())) {
                        alt47=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 47, 3, input);

                        throw nvae;
                    }
                }
                else if ( ((LA47_2>=RULE_STRING && LA47_2<=RULE_DECIMAL)||LA47_2==21||LA47_2==25||LA47_2==37||(LA47_2>=44 && LA47_2<=45)||LA47_2==49||LA47_2==54||LA47_2==56||LA47_2==59||LA47_2==61||(LA47_2>=64 && LA47_2<=65)||LA47_2==68||(LA47_2>=70 && LA47_2<=77)) ) {
                    alt47=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 47, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:2: ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:2: ( ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:3: ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )? ( (lv_switch_4_0= ruleXExpression ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:3: ( ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' ) )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==RULE_ID) ) {
                        int LA46_1 = input.LA(2);

                        if ( (LA46_1==22) && (synpred23_InternalKDiagram())) {
                            alt46=1;
                        }
                    }
                    switch (alt46) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:4: ( ( ( ( ruleValidID ) ) ':' ) )=> ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2791:5: ( ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':' )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2791:6: ( (lv_localVarName_2_0= ruleValidID ) ) otherlv_3= ':'
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2791:6: ( (lv_localVarName_2_0= ruleValidID ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2792:1: (lv_localVarName_2_0= ruleValidID )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2792:1: (lv_localVarName_2_0= ruleValidID )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2793:3: lv_localVarName_2_0= ruleValidID
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getLocalVarNameValidIDParserRuleCall_2_0_0_0_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleValidID_in_ruleXSwitchExpression6727);
                            lv_localVarName_2_0=ruleValidID();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"localVarName",
                                      		lv_localVarName_2_0, 
                                      		"ValidID");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            otherlv_3=(Token)match(input,22,FOLLOW_22_in_ruleXSwitchExpression6739); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_3, grammarAccess.getXSwitchExpressionAccess().getColonKeyword_2_0_0_0_1());
                                  
                            }

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2813:4: ( (lv_switch_4_0= ruleXExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2814:1: (lv_switch_4_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2814:1: (lv_switch_4_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2815:3: lv_switch_4_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getSwitchXExpressionParserRuleCall_2_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXSwitchExpression6763);
                    lv_switch_4_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"switch",
                              		lv_switch_4_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:6: ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:6: ( ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:7: ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) ) ( (lv_switch_8_0= ruleXExpression ) ) otherlv_9= ')'
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:7: ( ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:8: ( ( '(' ( ( ruleValidID ) ) ':' ) )=> (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2838:5: (otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2838:7: otherlv_5= '(' ( (lv_localVarName_6_0= ruleValidID ) ) otherlv_7= ':'
                    {
                    otherlv_5=(Token)match(input,54,FOLLOW_54_in_ruleXSwitchExpression6807); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getXSwitchExpressionAccess().getLeftParenthesisKeyword_2_1_0_0_0());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2842:1: ( (lv_localVarName_6_0= ruleValidID ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2843:1: (lv_localVarName_6_0= ruleValidID )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2843:1: (lv_localVarName_6_0= ruleValidID )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2844:3: lv_localVarName_6_0= ruleValidID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getLocalVarNameValidIDParserRuleCall_2_1_0_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleValidID_in_ruleXSwitchExpression6828);
                    lv_localVarName_6_0=ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"localVarName",
                              		lv_localVarName_6_0, 
                              		"ValidID");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_7=(Token)match(input,22,FOLLOW_22_in_ruleXSwitchExpression6840); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getXSwitchExpressionAccess().getColonKeyword_2_1_0_0_2());
                          
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2864:3: ( (lv_switch_8_0= ruleXExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2865:1: (lv_switch_8_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2865:1: (lv_switch_8_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2866:3: lv_switch_8_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getSwitchXExpressionParserRuleCall_2_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXSwitchExpression6863);
                    lv_switch_8_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"switch",
                              		lv_switch_8_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_9=(Token)match(input,55,FOLLOW_55_in_ruleXSwitchExpression6875); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getXSwitchExpressionAccess().getRightParenthesisKeyword_2_1_2());
                          
                    }

                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,25,FOLLOW_25_in_ruleXSwitchExpression6889); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_10, grammarAccess.getXSwitchExpressionAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2890:1: ( (lv_cases_11_0= ruleXCasePart ) )+
            int cnt48=0;
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==RULE_ID||LA48_0==22||LA48_0==40||LA48_0==54||LA48_0==63) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2891:1: (lv_cases_11_0= ruleXCasePart )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2891:1: (lv_cases_11_0= ruleXCasePart )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2892:3: lv_cases_11_0= ruleXCasePart
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getCasesXCasePartParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXCasePart_in_ruleXSwitchExpression6910);
            	    lv_cases_11_0=ruleXCasePart();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"cases",
            	              		lv_cases_11_0, 
            	              		"XCasePart");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt48 >= 1 ) break loop48;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(48, input);
                        throw eee;
                }
                cnt48++;
            } while (true);

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2908:3: (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==62) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2908:5: otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) )
                    {
                    otherlv_12=(Token)match(input,62,FOLLOW_62_in_ruleXSwitchExpression6924); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getXSwitchExpressionAccess().getDefaultKeyword_5_0());
                          
                    }
                    otherlv_13=(Token)match(input,22,FOLLOW_22_in_ruleXSwitchExpression6936); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getXSwitchExpressionAccess().getColonKeyword_5_1());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2916:1: ( (lv_default_14_0= ruleXExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2917:1: (lv_default_14_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2917:1: (lv_default_14_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2918:3: lv_default_14_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getDefaultXExpressionParserRuleCall_5_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXSwitchExpression6957);
                    lv_default_14_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"default",
                              		lv_default_14_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_15=(Token)match(input,27,FOLLOW_27_in_ruleXSwitchExpression6971); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_15, grammarAccess.getXSwitchExpressionAccess().getRightCurlyBracketKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSwitchExpression"


    // $ANTLR start "entryRuleXCasePart"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2946:1: entryRuleXCasePart returns [EObject current=null] : iv_ruleXCasePart= ruleXCasePart EOF ;
    public final EObject entryRuleXCasePart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXCasePart = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2947:2: (iv_ruleXCasePart= ruleXCasePart EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2948:2: iv_ruleXCasePart= ruleXCasePart EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXCasePartRule()); 
            }
            pushFollow(FOLLOW_ruleXCasePart_in_entryRuleXCasePart7007);
            iv_ruleXCasePart=ruleXCasePart();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXCasePart; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXCasePart7017); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXCasePart"


    // $ANTLR start "ruleXCasePart"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2955:1: ruleXCasePart returns [EObject current=null] : ( ( (lv_typeGuard_0_0= ruleJvmTypeReference ) )? (otherlv_1= 'case' ( (lv_case_2_0= ruleXExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleXExpression ) ) ) ;
    public final EObject ruleXCasePart() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_typeGuard_0_0 = null;

        EObject lv_case_2_0 = null;

        EObject lv_then_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2958:28: ( ( ( (lv_typeGuard_0_0= ruleJvmTypeReference ) )? (otherlv_1= 'case' ( (lv_case_2_0= ruleXExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleXExpression ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2959:1: ( ( (lv_typeGuard_0_0= ruleJvmTypeReference ) )? (otherlv_1= 'case' ( (lv_case_2_0= ruleXExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleXExpression ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2959:1: ( ( (lv_typeGuard_0_0= ruleJvmTypeReference ) )? (otherlv_1= 'case' ( (lv_case_2_0= ruleXExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleXExpression ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2959:2: ( (lv_typeGuard_0_0= ruleJvmTypeReference ) )? (otherlv_1= 'case' ( (lv_case_2_0= ruleXExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleXExpression ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2959:2: ( (lv_typeGuard_0_0= ruleJvmTypeReference ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==RULE_ID||LA50_0==40||LA50_0==54) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2960:1: (lv_typeGuard_0_0= ruleJvmTypeReference )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2960:1: (lv_typeGuard_0_0= ruleJvmTypeReference )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2961:3: lv_typeGuard_0_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXCasePartAccess().getTypeGuardJvmTypeReferenceParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleXCasePart7063);
                    lv_typeGuard_0_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXCasePartRule());
                      	        }
                             		set(
                             			current, 
                             			"typeGuard",
                              		lv_typeGuard_0_0, 
                              		"JvmTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2977:3: (otherlv_1= 'case' ( (lv_case_2_0= ruleXExpression ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==63) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2977:5: otherlv_1= 'case' ( (lv_case_2_0= ruleXExpression ) )
                    {
                    otherlv_1=(Token)match(input,63,FOLLOW_63_in_ruleXCasePart7077); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getXCasePartAccess().getCaseKeyword_1_0());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2981:1: ( (lv_case_2_0= ruleXExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2982:1: (lv_case_2_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2982:1: (lv_case_2_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2983:3: lv_case_2_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXCasePartAccess().getCaseXExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXCasePart7098);
                    lv_case_2_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXCasePartRule());
                      	        }
                             		set(
                             			current, 
                             			"case",
                              		lv_case_2_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,22,FOLLOW_22_in_ruleXCasePart7112); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getXCasePartAccess().getColonKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3003:1: ( (lv_then_4_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3004:1: (lv_then_4_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3004:1: (lv_then_4_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3005:3: lv_then_4_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXCasePartAccess().getThenXExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXCasePart7133);
            lv_then_4_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXCasePartRule());
              	        }
                     		set(
                     			current, 
                     			"then",
                      		lv_then_4_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXCasePart"


    // $ANTLR start "entryRuleXForLoopExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3029:1: entryRuleXForLoopExpression returns [EObject current=null] : iv_ruleXForLoopExpression= ruleXForLoopExpression EOF ;
    public final EObject entryRuleXForLoopExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXForLoopExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3030:2: (iv_ruleXForLoopExpression= ruleXForLoopExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3031:2: iv_ruleXForLoopExpression= ruleXForLoopExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXForLoopExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXForLoopExpression_in_entryRuleXForLoopExpression7169);
            iv_ruleXForLoopExpression=ruleXForLoopExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXForLoopExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXForLoopExpression7179); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXForLoopExpression"


    // $ANTLR start "ruleXForLoopExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3038:1: ruleXForLoopExpression returns [EObject current=null] : ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) ) ;
    public final EObject ruleXForLoopExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_declaredParam_3_0 = null;

        EObject lv_forExpression_5_0 = null;

        EObject lv_eachExpression_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3041:28: ( ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3042:1: ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3042:1: ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3042:2: () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3042:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3043:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXForLoopExpressionAccess().getXForLoopExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleXForLoopExpression7225); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXForLoopExpressionAccess().getForKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,54,FOLLOW_54_in_ruleXForLoopExpression7237); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getXForLoopExpressionAccess().getLeftParenthesisKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3056:1: ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3057:1: (lv_declaredParam_3_0= ruleJvmFormalParameter )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3057:1: (lv_declaredParam_3_0= ruleJvmFormalParameter )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3058:3: lv_declaredParam_3_0= ruleJvmFormalParameter
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXForLoopExpressionAccess().getDeclaredParamJvmFormalParameterParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmFormalParameter_in_ruleXForLoopExpression7258);
            lv_declaredParam_3_0=ruleJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXForLoopExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"declaredParam",
                      		lv_declaredParam_3_0, 
                      		"JvmFormalParameter");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,22,FOLLOW_22_in_ruleXForLoopExpression7270); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getXForLoopExpressionAccess().getColonKeyword_4());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3078:1: ( (lv_forExpression_5_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3079:1: (lv_forExpression_5_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3079:1: (lv_forExpression_5_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3080:3: lv_forExpression_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXForLoopExpressionAccess().getForExpressionXExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXForLoopExpression7291);
            lv_forExpression_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXForLoopExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"forExpression",
                      		lv_forExpression_5_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_6=(Token)match(input,55,FOLLOW_55_in_ruleXForLoopExpression7303); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getXForLoopExpressionAccess().getRightParenthesisKeyword_6());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3100:1: ( (lv_eachExpression_7_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3101:1: (lv_eachExpression_7_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3101:1: (lv_eachExpression_7_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3102:3: lv_eachExpression_7_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXForLoopExpressionAccess().getEachExpressionXExpressionParserRuleCall_7_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXForLoopExpression7324);
            lv_eachExpression_7_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXForLoopExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"eachExpression",
                      		lv_eachExpression_7_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXForLoopExpression"


    // $ANTLR start "entryRuleXWhileExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3126:1: entryRuleXWhileExpression returns [EObject current=null] : iv_ruleXWhileExpression= ruleXWhileExpression EOF ;
    public final EObject entryRuleXWhileExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3127:2: (iv_ruleXWhileExpression= ruleXWhileExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3128:2: iv_ruleXWhileExpression= ruleXWhileExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXWhileExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXWhileExpression_in_entryRuleXWhileExpression7360);
            iv_ruleXWhileExpression=ruleXWhileExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXWhileExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXWhileExpression7370); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXWhileExpression"


    // $ANTLR start "ruleXWhileExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3135:1: ruleXWhileExpression returns [EObject current=null] : ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) ) ;
    public final EObject ruleXWhileExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_predicate_3_0 = null;

        EObject lv_body_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3138:28: ( ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3139:1: ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3139:1: ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3139:2: () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3139:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3140:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXWhileExpressionAccess().getXWhileExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,64,FOLLOW_64_in_ruleXWhileExpression7416); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXWhileExpressionAccess().getWhileKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,54,FOLLOW_54_in_ruleXWhileExpression7428); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getXWhileExpressionAccess().getLeftParenthesisKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3153:1: ( (lv_predicate_3_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3154:1: (lv_predicate_3_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3154:1: (lv_predicate_3_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3155:3: lv_predicate_3_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXWhileExpressionAccess().getPredicateXExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXWhileExpression7449);
            lv_predicate_3_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXWhileExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"predicate",
                      		lv_predicate_3_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,55,FOLLOW_55_in_ruleXWhileExpression7461); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getXWhileExpressionAccess().getRightParenthesisKeyword_4());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3175:1: ( (lv_body_5_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3176:1: (lv_body_5_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3176:1: (lv_body_5_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3177:3: lv_body_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXWhileExpressionAccess().getBodyXExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXWhileExpression7482);
            lv_body_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXWhileExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"body",
                      		lv_body_5_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXWhileExpression"


    // $ANTLR start "entryRuleXDoWhileExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3201:1: entryRuleXDoWhileExpression returns [EObject current=null] : iv_ruleXDoWhileExpression= ruleXDoWhileExpression EOF ;
    public final EObject entryRuleXDoWhileExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXDoWhileExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3202:2: (iv_ruleXDoWhileExpression= ruleXDoWhileExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3203:2: iv_ruleXDoWhileExpression= ruleXDoWhileExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXDoWhileExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXDoWhileExpression_in_entryRuleXDoWhileExpression7518);
            iv_ruleXDoWhileExpression=ruleXDoWhileExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXDoWhileExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXDoWhileExpression7528); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXDoWhileExpression"


    // $ANTLR start "ruleXDoWhileExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3210:1: ruleXDoWhileExpression returns [EObject current=null] : ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' ) ;
    public final EObject ruleXDoWhileExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_body_2_0 = null;

        EObject lv_predicate_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3213:28: ( ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3214:1: ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3214:1: ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3214:2: () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')'
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3214:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3215:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXDoWhileExpressionAccess().getXDoWhileExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,65,FOLLOW_65_in_ruleXDoWhileExpression7574); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXDoWhileExpressionAccess().getDoKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3224:1: ( (lv_body_2_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3225:1: (lv_body_2_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3225:1: (lv_body_2_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3226:3: lv_body_2_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXDoWhileExpressionAccess().getBodyXExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXDoWhileExpression7595);
            lv_body_2_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXDoWhileExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"body",
                      		lv_body_2_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,64,FOLLOW_64_in_ruleXDoWhileExpression7607); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getXDoWhileExpressionAccess().getWhileKeyword_3());
                  
            }
            otherlv_4=(Token)match(input,54,FOLLOW_54_in_ruleXDoWhileExpression7619); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getXDoWhileExpressionAccess().getLeftParenthesisKeyword_4());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3250:1: ( (lv_predicate_5_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3251:1: (lv_predicate_5_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3251:1: (lv_predicate_5_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3252:3: lv_predicate_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXDoWhileExpressionAccess().getPredicateXExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXDoWhileExpression7640);
            lv_predicate_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXDoWhileExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"predicate",
                      		lv_predicate_5_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_6=(Token)match(input,55,FOLLOW_55_in_ruleXDoWhileExpression7652); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getXDoWhileExpressionAccess().getRightParenthesisKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXDoWhileExpression"


    // $ANTLR start "entryRuleXBlockExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3280:1: entryRuleXBlockExpression returns [EObject current=null] : iv_ruleXBlockExpression= ruleXBlockExpression EOF ;
    public final EObject entryRuleXBlockExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXBlockExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3281:2: (iv_ruleXBlockExpression= ruleXBlockExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3282:2: iv_ruleXBlockExpression= ruleXBlockExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXBlockExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXBlockExpression_in_entryRuleXBlockExpression7688);
            iv_ruleXBlockExpression=ruleXBlockExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXBlockExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXBlockExpression7698); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXBlockExpression"


    // $ANTLR start "ruleXBlockExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3289:1: ruleXBlockExpression returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) ) (otherlv_3= ';' )? )* otherlv_4= '}' ) ;
    public final EObject ruleXBlockExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_expressions_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3292:28: ( ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) ) (otherlv_3= ';' )? )* otherlv_4= '}' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3293:1: ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) ) (otherlv_3= ';' )? )* otherlv_4= '}' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3293:1: ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) ) (otherlv_3= ';' )? )* otherlv_4= '}' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3293:2: () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) ) (otherlv_3= ';' )? )* otherlv_4= '}'
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3293:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3294:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXBlockExpressionAccess().getXBlockExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleXBlockExpression7744); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXBlockExpressionAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3303:1: ( ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) ) (otherlv_3= ';' )? )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( ((LA53_0>=RULE_ID && LA53_0<=RULE_DECIMAL)||LA53_0==21||LA53_0==25||LA53_0==37||(LA53_0>=44 && LA53_0<=45)||LA53_0==49||LA53_0==54||LA53_0==56||LA53_0==59||LA53_0==61||(LA53_0>=64 && LA53_0<=68)||(LA53_0>=70 && LA53_0<=77)) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3303:2: ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) ) (otherlv_3= ';' )?
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3303:2: ( (lv_expressions_2_0= ruleXExpressionInsideBlock ) )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3304:1: (lv_expressions_2_0= ruleXExpressionInsideBlock )
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3304:1: (lv_expressions_2_0= ruleXExpressionInsideBlock )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3305:3: lv_expressions_2_0= ruleXExpressionInsideBlock
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getXBlockExpressionAccess().getExpressionsXExpressionInsideBlockParserRuleCall_2_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleXExpressionInsideBlock_in_ruleXBlockExpression7766);
            	    lv_expressions_2_0=ruleXExpressionInsideBlock();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getXBlockExpressionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"expressions",
            	              		lv_expressions_2_0, 
            	              		"XExpressionInsideBlock");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3321:2: (otherlv_3= ';' )?
            	    int alt52=2;
            	    int LA52_0 = input.LA(1);

            	    if ( (LA52_0==14) ) {
            	        alt52=1;
            	    }
            	    switch (alt52) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3321:4: otherlv_3= ';'
            	            {
            	            otherlv_3=(Token)match(input,14,FOLLOW_14_in_ruleXBlockExpression7779); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_3, grammarAccess.getXBlockExpressionAccess().getSemicolonKeyword_2_1());
            	                  
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);

            otherlv_4=(Token)match(input,27,FOLLOW_27_in_ruleXBlockExpression7795); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getXBlockExpressionAccess().getRightCurlyBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXBlockExpression"


    // $ANTLR start "entryRuleXExpressionInsideBlock"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3337:1: entryRuleXExpressionInsideBlock returns [EObject current=null] : iv_ruleXExpressionInsideBlock= ruleXExpressionInsideBlock EOF ;
    public final EObject entryRuleXExpressionInsideBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionInsideBlock = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3338:2: (iv_ruleXExpressionInsideBlock= ruleXExpressionInsideBlock EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3339:2: iv_ruleXExpressionInsideBlock= ruleXExpressionInsideBlock EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionInsideBlockRule()); 
            }
            pushFollow(FOLLOW_ruleXExpressionInsideBlock_in_entryRuleXExpressionInsideBlock7831);
            iv_ruleXExpressionInsideBlock=ruleXExpressionInsideBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpressionInsideBlock; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXExpressionInsideBlock7841); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXExpressionInsideBlock"


    // $ANTLR start "ruleXExpressionInsideBlock"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3346:1: ruleXExpressionInsideBlock returns [EObject current=null] : (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression ) ;
    public final EObject ruleXExpressionInsideBlock() throws RecognitionException {
        EObject current = null;

        EObject this_XVariableDeclaration_0 = null;

        EObject this_XExpression_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3349:28: ( (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3350:1: (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3350:1: (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( ((LA54_0>=66 && LA54_0<=67)) ) {
                alt54=1;
            }
            else if ( ((LA54_0>=RULE_ID && LA54_0<=RULE_DECIMAL)||LA54_0==21||LA54_0==25||LA54_0==37||(LA54_0>=44 && LA54_0<=45)||LA54_0==49||LA54_0==54||LA54_0==56||LA54_0==59||LA54_0==61||(LA54_0>=64 && LA54_0<=65)||LA54_0==68||(LA54_0>=70 && LA54_0<=77)) ) {
                alt54=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3351:5: this_XVariableDeclaration_0= ruleXVariableDeclaration
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXExpressionInsideBlockAccess().getXVariableDeclarationParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXVariableDeclaration_in_ruleXExpressionInsideBlock7888);
                    this_XVariableDeclaration_0=ruleXVariableDeclaration();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XVariableDeclaration_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3361:5: this_XExpression_1= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getXExpressionInsideBlockAccess().getXExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXExpressionInsideBlock7915);
                    this_XExpression_1=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpressionInsideBlock"


    // $ANTLR start "entryRuleXVariableDeclaration"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3377:1: entryRuleXVariableDeclaration returns [EObject current=null] : iv_ruleXVariableDeclaration= ruleXVariableDeclaration EOF ;
    public final EObject entryRuleXVariableDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVariableDeclaration = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3378:2: (iv_ruleXVariableDeclaration= ruleXVariableDeclaration EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3379:2: iv_ruleXVariableDeclaration= ruleXVariableDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXVariableDeclarationRule()); 
            }
            pushFollow(FOLLOW_ruleXVariableDeclaration_in_entryRuleXVariableDeclaration7950);
            iv_ruleXVariableDeclaration=ruleXVariableDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXVariableDeclaration; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXVariableDeclaration7960); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVariableDeclaration"


    // $ANTLR start "ruleXVariableDeclaration"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3386:1: ruleXVariableDeclaration returns [EObject current=null] : ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? ) ;
    public final EObject ruleXVariableDeclaration() throws RecognitionException {
        EObject current = null;

        Token lv_writeable_1_0=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        EObject lv_type_3_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        EObject lv_right_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3389:28: ( ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3390:1: ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3390:1: ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3390:2: () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )?
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3390:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3391:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXVariableDeclarationAccess().getXVariableDeclarationAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3396:2: ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==66) ) {
                alt55=1;
            }
            else if ( (LA55_0==67) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3396:3: ( (lv_writeable_1_0= 'var' ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3396:3: ( (lv_writeable_1_0= 'var' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3397:1: (lv_writeable_1_0= 'var' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3397:1: (lv_writeable_1_0= 'var' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3398:3: lv_writeable_1_0= 'var'
                    {
                    lv_writeable_1_0=(Token)match(input,66,FOLLOW_66_in_ruleXVariableDeclaration8013); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_writeable_1_0, grammarAccess.getXVariableDeclarationAccess().getWriteableVarKeyword_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getXVariableDeclarationRule());
                      	        }
                             		setWithLastConsumed(current, "writeable", true, "var");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3412:7: otherlv_2= 'val'
                    {
                    otherlv_2=(Token)match(input,67,FOLLOW_67_in_ruleXVariableDeclaration8044); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getXVariableDeclarationAccess().getValKeyword_1_1());
                          
                    }

                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:2: ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==RULE_ID) ) {
                int LA56_1 = input.LA(2);

                if ( (synpred25_InternalKDiagram()) ) {
                    alt56=1;
                }
                else if ( (true) ) {
                    alt56=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA56_0==54) && (synpred25_InternalKDiagram())) {
                alt56=1;
            }
            else if ( (LA56_0==40) && (synpred25_InternalKDiagram())) {
                alt56=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:3: ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:3: ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:4: ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3424:6: ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3424:7: ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3424:7: ( (lv_type_3_0= ruleJvmTypeReference ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3425:1: (lv_type_3_0= ruleJvmTypeReference )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3425:1: (lv_type_3_0= ruleJvmTypeReference )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3426:3: lv_type_3_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getTypeJvmTypeReferenceParserRuleCall_2_0_0_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleXVariableDeclaration8092);
                    lv_type_3_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      	        }
                             		set(
                             			current, 
                             			"type",
                              		lv_type_3_0, 
                              		"JvmTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3442:2: ( (lv_name_4_0= ruleValidID ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3443:1: (lv_name_4_0= ruleValidID )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3443:1: (lv_name_4_0= ruleValidID )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3444:3: lv_name_4_0= ruleValidID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getNameValidIDParserRuleCall_2_0_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleValidID_in_ruleXVariableDeclaration8113);
                    lv_name_4_0=ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_4_0, 
                              		"ValidID");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3461:6: ( (lv_name_5_0= ruleValidID ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3461:6: ( (lv_name_5_0= ruleValidID ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3462:1: (lv_name_5_0= ruleValidID )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3462:1: (lv_name_5_0= ruleValidID )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3463:3: lv_name_5_0= ruleValidID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getNameValidIDParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleValidID_in_ruleXVariableDeclaration8142);
                    lv_name_5_0=ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_5_0, 
                              		"ValidID");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3479:3: (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==24) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3479:5: otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) )
                    {
                    otherlv_6=(Token)match(input,24,FOLLOW_24_in_ruleXVariableDeclaration8156); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getXVariableDeclarationAccess().getEqualsSignKeyword_3_0());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3483:1: ( (lv_right_7_0= ruleXExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3484:1: (lv_right_7_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3484:1: (lv_right_7_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3485:3: lv_right_7_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getRightXExpressionParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXVariableDeclaration8177);
                    lv_right_7_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_7_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVariableDeclaration"


    // $ANTLR start "entryRuleJvmFormalParameter"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3509:1: entryRuleJvmFormalParameter returns [EObject current=null] : iv_ruleJvmFormalParameter= ruleJvmFormalParameter EOF ;
    public final EObject entryRuleJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmFormalParameter = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3510:2: (iv_ruleJvmFormalParameter= ruleJvmFormalParameter EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3511:2: iv_ruleJvmFormalParameter= ruleJvmFormalParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmFormalParameterRule()); 
            }
            pushFollow(FOLLOW_ruleJvmFormalParameter_in_entryRuleJvmFormalParameter8215);
            iv_ruleJvmFormalParameter=ruleJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmFormalParameter; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmFormalParameter8225); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmFormalParameter"


    // $ANTLR start "ruleJvmFormalParameter"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3518:1: ruleJvmFormalParameter returns [EObject current=null] : ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) ) ;
    public final EObject ruleJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject lv_parameterType_0_0 = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3521:28: ( ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3522:1: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3522:1: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3522:2: ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3522:2: ( (lv_parameterType_0_0= ruleJvmTypeReference ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_ID) ) {
                int LA58_1 = input.LA(2);

                if ( (LA58_1==RULE_ID||LA58_1==19||LA58_1==37||LA58_1==56) ) {
                    alt58=1;
                }
            }
            else if ( (LA58_0==40||LA58_0==54) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3523:1: (lv_parameterType_0_0= ruleJvmTypeReference )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3523:1: (lv_parameterType_0_0= ruleJvmTypeReference )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3524:3: lv_parameterType_0_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmFormalParameterAccess().getParameterTypeJvmTypeReferenceParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmFormalParameter8271);
                    lv_parameterType_0_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJvmFormalParameterRule());
                      	        }
                             		set(
                             			current, 
                             			"parameterType",
                              		lv_parameterType_0_0, 
                              		"JvmTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3540:3: ( (lv_name_1_0= ruleValidID ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3541:1: (lv_name_1_0= ruleValidID )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3541:1: (lv_name_1_0= ruleValidID )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3542:3: lv_name_1_0= ruleValidID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmFormalParameterAccess().getNameValidIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleValidID_in_ruleJvmFormalParameter8293);
            lv_name_1_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getJvmFormalParameterRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ValidID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmFormalParameter"


    // $ANTLR start "entryRuleFullJvmFormalParameter"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3566:1: entryRuleFullJvmFormalParameter returns [EObject current=null] : iv_ruleFullJvmFormalParameter= ruleFullJvmFormalParameter EOF ;
    public final EObject entryRuleFullJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullJvmFormalParameter = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3567:2: (iv_ruleFullJvmFormalParameter= ruleFullJvmFormalParameter EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3568:2: iv_ruleFullJvmFormalParameter= ruleFullJvmFormalParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFullJvmFormalParameterRule()); 
            }
            pushFollow(FOLLOW_ruleFullJvmFormalParameter_in_entryRuleFullJvmFormalParameter8329);
            iv_ruleFullJvmFormalParameter=ruleFullJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFullJvmFormalParameter; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFullJvmFormalParameter8339); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFullJvmFormalParameter"


    // $ANTLR start "ruleFullJvmFormalParameter"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3575:1: ruleFullJvmFormalParameter returns [EObject current=null] : ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) ) ;
    public final EObject ruleFullJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject lv_parameterType_0_0 = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3578:28: ( ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3579:1: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3579:1: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3579:2: ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3579:2: ( (lv_parameterType_0_0= ruleJvmTypeReference ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3580:1: (lv_parameterType_0_0= ruleJvmTypeReference )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3580:1: (lv_parameterType_0_0= ruleJvmTypeReference )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3581:3: lv_parameterType_0_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFullJvmFormalParameterAccess().getParameterTypeJvmTypeReferenceParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleFullJvmFormalParameter8385);
            lv_parameterType_0_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFullJvmFormalParameterRule());
              	        }
                     		set(
                     			current, 
                     			"parameterType",
                      		lv_parameterType_0_0, 
                      		"JvmTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3597:2: ( (lv_name_1_0= ruleValidID ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3598:1: (lv_name_1_0= ruleValidID )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3598:1: (lv_name_1_0= ruleValidID )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3599:3: lv_name_1_0= ruleValidID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFullJvmFormalParameterAccess().getNameValidIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleValidID_in_ruleFullJvmFormalParameter8406);
            lv_name_1_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFullJvmFormalParameterRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ValidID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFullJvmFormalParameter"


    // $ANTLR start "entryRuleXFeatureCall"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3623:1: entryRuleXFeatureCall returns [EObject current=null] : iv_ruleXFeatureCall= ruleXFeatureCall EOF ;
    public final EObject entryRuleXFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFeatureCall = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3624:2: (iv_ruleXFeatureCall= ruleXFeatureCall EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3625:2: iv_ruleXFeatureCall= ruleXFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleXFeatureCall_in_entryRuleXFeatureCall8442);
            iv_ruleXFeatureCall=ruleXFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXFeatureCall8452); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFeatureCall"


    // $ANTLR start "ruleXFeatureCall"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3632:1: ruleXFeatureCall returns [EObject current=null] : ( () ( ( ruleStaticQualifier ) )? (otherlv_2= '<' ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) ) (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )* otherlv_6= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure ) )? ) ;
    public final EObject ruleXFeatureCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_explicitOperationCall_8_0=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject lv_typeArguments_3_0 = null;

        EObject lv_typeArguments_5_0 = null;

        EObject lv_featureCallArguments_9_0 = null;

        EObject lv_featureCallArguments_10_0 = null;

        EObject lv_featureCallArguments_12_0 = null;

        EObject lv_featureCallArguments_14_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3635:28: ( ( () ( ( ruleStaticQualifier ) )? (otherlv_2= '<' ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) ) (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )* otherlv_6= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure ) )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3636:1: ( () ( ( ruleStaticQualifier ) )? (otherlv_2= '<' ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) ) (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )* otherlv_6= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure ) )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3636:1: ( () ( ( ruleStaticQualifier ) )? (otherlv_2= '<' ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) ) (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )* otherlv_6= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure ) )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3636:2: () ( ( ruleStaticQualifier ) )? (otherlv_2= '<' ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) ) (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )* otherlv_6= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure ) )?
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3636:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3637:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXFeatureCallAccess().getXFeatureCallAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3642:2: ( ( ruleStaticQualifier ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==RULE_ID) ) {
                int LA59_1 = input.LA(2);

                if ( (LA59_1==69) ) {
                    alt59=1;
                }
            }
            switch (alt59) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3643:1: ( ruleStaticQualifier )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3643:1: ( ruleStaticQualifier )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3644:3: ruleStaticQualifier
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getXFeatureCallRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getDeclaringTypeJvmDeclaredTypeCrossReference_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleStaticQualifier_in_ruleXFeatureCall8509);
                    ruleStaticQualifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3657:3: (otherlv_2= '<' ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) ) (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )* otherlv_6= '>' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==37) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3657:5: otherlv_2= '<' ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) ) (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )* otherlv_6= '>'
                    {
                    otherlv_2=(Token)match(input,37,FOLLOW_37_in_ruleXFeatureCall8523); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getXFeatureCallAccess().getLessThanSignKeyword_2_0());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3661:1: ( (lv_typeArguments_3_0= ruleJvmArgumentTypeReference ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3662:1: (lv_typeArguments_3_0= ruleJvmArgumentTypeReference )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3662:1: (lv_typeArguments_3_0= ruleJvmArgumentTypeReference )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3663:3: lv_typeArguments_3_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleXFeatureCall8544);
                    lv_typeArguments_3_0=ruleJvmArgumentTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                      	        }
                             		add(
                             			current, 
                             			"typeArguments",
                              		lv_typeArguments_3_0, 
                              		"JvmArgumentTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3679:2: (otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==53) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3679:4: otherlv_4= ',' ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_4=(Token)match(input,53,FOLLOW_53_in_ruleXFeatureCall8557); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_4, grammarAccess.getXFeatureCallAccess().getCommaKeyword_2_2_0());
                    	          
                    	    }
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3683:1: ( (lv_typeArguments_5_0= ruleJvmArgumentTypeReference ) )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3684:1: (lv_typeArguments_5_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3684:1: (lv_typeArguments_5_0= ruleJvmArgumentTypeReference )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3685:3: lv_typeArguments_5_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_2_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleXFeatureCall8578);
                    	    lv_typeArguments_5_0=ruleJvmArgumentTypeReference();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeArguments",
                    	              		lv_typeArguments_5_0, 
                    	              		"JvmArgumentTypeReference");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,36,FOLLOW_36_in_ruleXFeatureCall8592); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getXFeatureCallAccess().getGreaterThanSignKeyword_2_3());
                          
                    }

                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3705:3: ( ( ruleIdOrSuper ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3706:1: ( ruleIdOrSuper )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3706:1: ( ruleIdOrSuper )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3707:3: ruleIdOrSuper
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getXFeatureCallRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureJvmIdentifiableElementCrossReference_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdOrSuper_in_ruleXFeatureCall8617);
            ruleIdOrSuper();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3720:2: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )?
            int alt64=2;
            alt64 = dfa64.predict(input);
            switch (alt64) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3720:3: ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')'
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3720:3: ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3720:4: ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3727:1: (lv_explicitOperationCall_8_0= '(' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3728:3: lv_explicitOperationCall_8_0= '('
                    {
                    lv_explicitOperationCall_8_0=(Token)match(input,54,FOLLOW_54_in_ruleXFeatureCall8651); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_explicitOperationCall_8_0, grammarAccess.getXFeatureCallAccess().getExplicitOperationCallLeftParenthesisKeyword_4_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getXFeatureCallRule());
                      	        }
                             		setWithLastConsumed(current, "explicitOperationCall", true, "(");
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )?
                    int alt63=3;
                    alt63 = dfa63.predict(input);
                    switch (alt63) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:4: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3758:1: (lv_featureCallArguments_9_0= ruleXShortClosure )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3759:3: lv_featureCallArguments_9_0= ruleXShortClosure
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXShortClosureParserRuleCall_4_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleXShortClosure_in_ruleXFeatureCall8736);
                            lv_featureCallArguments_9_0=ruleXShortClosure();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"featureCallArguments",
                                      		lv_featureCallArguments_9_0, 
                                      		"XShortClosure");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3776:6: ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3776:6: ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3776:7: ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )*
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3776:7: ( (lv_featureCallArguments_10_0= ruleXExpression ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3777:1: (lv_featureCallArguments_10_0= ruleXExpression )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3777:1: (lv_featureCallArguments_10_0= ruleXExpression )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3778:3: lv_featureCallArguments_10_0= ruleXExpression
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXExpressionParserRuleCall_4_1_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleXExpression_in_ruleXFeatureCall8764);
                            lv_featureCallArguments_10_0=ruleXExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"featureCallArguments",
                                      		lv_featureCallArguments_10_0, 
                                      		"XExpression");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3794:2: (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )*
                            loop62:
                            do {
                                int alt62=2;
                                int LA62_0 = input.LA(1);

                                if ( (LA62_0==53) ) {
                                    alt62=1;
                                }


                                switch (alt62) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3794:4: otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) )
                            	    {
                            	    otherlv_11=(Token)match(input,53,FOLLOW_53_in_ruleXFeatureCall8777); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_11, grammarAccess.getXFeatureCallAccess().getCommaKeyword_4_1_1_1_0());
                            	          
                            	    }
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3798:1: ( (lv_featureCallArguments_12_0= ruleXExpression ) )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3799:1: (lv_featureCallArguments_12_0= ruleXExpression )
                            	    {
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3799:1: (lv_featureCallArguments_12_0= ruleXExpression )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3800:3: lv_featureCallArguments_12_0= ruleXExpression
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXExpressionParserRuleCall_4_1_1_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleXExpression_in_ruleXFeatureCall8798);
                            	    lv_featureCallArguments_12_0=ruleXExpression();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"featureCallArguments",
                            	              		lv_featureCallArguments_12_0, 
                            	              		"XExpression");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop62;
                                }
                            } while (true);


                            }


                            }
                            break;

                    }

                    otherlv_13=(Token)match(input,55,FOLLOW_55_in_ruleXFeatureCall8815); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getXFeatureCallAccess().getRightParenthesisKeyword_4_2());
                          
                    }

                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3820:3: ( ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure ) )?
            int alt65=2;
            alt65 = dfa65.predict(input);
            switch (alt65) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3820:4: ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3823:1: (lv_featureCallArguments_14_0= ruleXClosure )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3824:3: lv_featureCallArguments_14_0= ruleXClosure
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXClosureParserRuleCall_5_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXClosure_in_ruleXFeatureCall8850);
                    lv_featureCallArguments_14_0=ruleXClosure();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                      	        }
                             		add(
                             			current, 
                             			"featureCallArguments",
                              		lv_featureCallArguments_14_0, 
                              		"XClosure");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFeatureCall"


    // $ANTLR start "entryRuleIdOrSuper"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3848:1: entryRuleIdOrSuper returns [String current=null] : iv_ruleIdOrSuper= ruleIdOrSuper EOF ;
    public final String entryRuleIdOrSuper() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdOrSuper = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3849:2: (iv_ruleIdOrSuper= ruleIdOrSuper EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3850:2: iv_ruleIdOrSuper= ruleIdOrSuper EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdOrSuperRule()); 
            }
            pushFollow(FOLLOW_ruleIdOrSuper_in_entryRuleIdOrSuper8888);
            iv_ruleIdOrSuper=ruleIdOrSuper();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdOrSuper.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdOrSuper8899); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIdOrSuper"


    // $ANTLR start "ruleIdOrSuper"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3857:1: ruleIdOrSuper returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID | kw= 'super' ) ;
    public final AntlrDatatypeRuleToken ruleIdOrSuper() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3860:28: ( (this_ValidID_0= ruleValidID | kw= 'super' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3861:1: (this_ValidID_0= ruleValidID | kw= 'super' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3861:1: (this_ValidID_0= ruleValidID | kw= 'super' )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==RULE_ID) ) {
                alt66=1;
            }
            else if ( (LA66_0==68) ) {
                alt66=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }
            switch (alt66) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3862:5: this_ValidID_0= ruleValidID
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getIdOrSuperAccess().getValidIDParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleValidID_in_ruleIdOrSuper8946);
                    this_ValidID_0=ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_ValidID_0);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3874:2: kw= 'super'
                    {
                    kw=(Token)match(input,68,FOLLOW_68_in_ruleIdOrSuper8970); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getIdOrSuperAccess().getSuperKeyword_1()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIdOrSuper"


    // $ANTLR start "entryRuleStaticQualifier"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3887:1: entryRuleStaticQualifier returns [String current=null] : iv_ruleStaticQualifier= ruleStaticQualifier EOF ;
    public final String entryRuleStaticQualifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStaticQualifier = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3888:2: (iv_ruleStaticQualifier= ruleStaticQualifier EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3889:2: iv_ruleStaticQualifier= ruleStaticQualifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStaticQualifierRule()); 
            }
            pushFollow(FOLLOW_ruleStaticQualifier_in_entryRuleStaticQualifier9011);
            iv_ruleStaticQualifier=ruleStaticQualifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStaticQualifier.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStaticQualifier9022); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStaticQualifier"


    // $ANTLR start "ruleStaticQualifier"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3896:1: ruleStaticQualifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID kw= '::' )+ ;
    public final AntlrDatatypeRuleToken ruleStaticQualifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3899:28: ( (this_ValidID_0= ruleValidID kw= '::' )+ )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3900:1: (this_ValidID_0= ruleValidID kw= '::' )+
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3900:1: (this_ValidID_0= ruleValidID kw= '::' )+
            int cnt67=0;
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==RULE_ID) ) {
                    int LA67_2 = input.LA(2);

                    if ( (LA67_2==69) ) {
                        alt67=1;
                    }


                }


                switch (alt67) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3901:5: this_ValidID_0= ruleValidID kw= '::'
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getStaticQualifierAccess().getValidIDParserRuleCall_0()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleValidID_in_ruleStaticQualifier9069);
            	    this_ValidID_0=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ValidID_0);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              afterParserOrEnumRuleCall();
            	          
            	    }
            	    kw=(Token)match(input,69,FOLLOW_69_in_ruleStaticQualifier9087); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getStaticQualifierAccess().getColonColonKeyword_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt67 >= 1 ) break loop67;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(67, input);
                        throw eee;
                }
                cnt67++;
            } while (true);


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStaticQualifier"


    // $ANTLR start "entryRuleXConstructorCall"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3925:1: entryRuleXConstructorCall returns [EObject current=null] : iv_ruleXConstructorCall= ruleXConstructorCall EOF ;
    public final EObject entryRuleXConstructorCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXConstructorCall = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3926:2: (iv_ruleXConstructorCall= ruleXConstructorCall EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3927:2: iv_ruleXConstructorCall= ruleXConstructorCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXConstructorCallRule()); 
            }
            pushFollow(FOLLOW_ruleXConstructorCall_in_entryRuleXConstructorCall9128);
            iv_ruleXConstructorCall=ruleXConstructorCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXConstructorCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXConstructorCall9138); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXConstructorCall"


    // $ANTLR start "ruleXConstructorCall"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3934:1: ruleXConstructorCall returns [EObject current=null] : ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? ) ;
    public final EObject ruleXConstructorCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject lv_typeArguments_4_0 = null;

        EObject lv_typeArguments_6_0 = null;

        EObject lv_arguments_9_0 = null;

        EObject lv_arguments_10_0 = null;

        EObject lv_arguments_12_0 = null;

        EObject lv_arguments_14_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3937:28: ( ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3938:1: ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3938:1: ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3938:2: () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )?
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3938:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3939:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXConstructorCallAccess().getXConstructorCallAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,70,FOLLOW_70_in_ruleXConstructorCall9184); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXConstructorCallAccess().getNewKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3948:1: ( ( ruleQualifiedName ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3949:1: ( ruleQualifiedName )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3949:1: ( ruleQualifiedName )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3950:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getXConstructorCallRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXConstructorCallAccess().getConstructorJvmConstructorCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleXConstructorCall9207);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3963:2: ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )?
            int alt69=2;
            alt69 = dfa69.predict(input);
            switch (alt69) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3963:3: ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>'
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3963:3: ( ( '<' )=>otherlv_3= '<' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3963:4: ( '<' )=>otherlv_3= '<'
                    {
                    otherlv_3=(Token)match(input,37,FOLLOW_37_in_ruleXConstructorCall9228); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getXConstructorCallAccess().getLessThanSignKeyword_3_0());
                          
                    }

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3968:2: ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3969:1: (lv_typeArguments_4_0= ruleJvmArgumentTypeReference )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3969:1: (lv_typeArguments_4_0= ruleJvmArgumentTypeReference )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3970:3: lv_typeArguments_4_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXConstructorCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleXConstructorCall9250);
                    lv_typeArguments_4_0=ruleJvmArgumentTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                      	        }
                             		add(
                             			current, 
                             			"typeArguments",
                              		lv_typeArguments_4_0, 
                              		"JvmArgumentTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3986:2: (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==53) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3986:4: otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_5=(Token)match(input,53,FOLLOW_53_in_ruleXConstructorCall9263); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getXConstructorCallAccess().getCommaKeyword_3_2_0());
                    	          
                    	    }
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3990:1: ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3991:1: (lv_typeArguments_6_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3991:1: (lv_typeArguments_6_0= ruleJvmArgumentTypeReference )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3992:3: lv_typeArguments_6_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getXConstructorCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_3_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleXConstructorCall9284);
                    	    lv_typeArguments_6_0=ruleJvmArgumentTypeReference();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"typeArguments",
                    	              		lv_typeArguments_6_0, 
                    	              		"JvmArgumentTypeReference");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop68;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,36,FOLLOW_36_in_ruleXConstructorCall9298); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getXConstructorCallAccess().getGreaterThanSignKeyword_3_3());
                          
                    }

                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4012:3: ( ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )?
            int alt72=2;
            alt72 = dfa72.predict(input);
            switch (alt72) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4012:4: ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')'
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4012:4: ( ( '(' )=>otherlv_8= '(' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4012:5: ( '(' )=>otherlv_8= '('
                    {
                    otherlv_8=(Token)match(input,54,FOLLOW_54_in_ruleXConstructorCall9321); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getXConstructorCallAccess().getLeftParenthesisKeyword_4_0());
                          
                    }

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )?
                    int alt71=3;
                    alt71 = dfa71.predict(input);
                    switch (alt71) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:4: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4034:1: (lv_arguments_9_0= ruleXShortClosure )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4035:3: lv_arguments_9_0= ruleXShortClosure
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXShortClosureParserRuleCall_4_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleXShortClosure_in_ruleXConstructorCall9394);
                            lv_arguments_9_0=ruleXShortClosure();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"arguments",
                                      		lv_arguments_9_0, 
                                      		"XShortClosure");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4052:6: ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4052:6: ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4052:7: ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )*
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4052:7: ( (lv_arguments_10_0= ruleXExpression ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4053:1: (lv_arguments_10_0= ruleXExpression )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4053:1: (lv_arguments_10_0= ruleXExpression )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4054:3: lv_arguments_10_0= ruleXExpression
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXExpressionParserRuleCall_4_1_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleXExpression_in_ruleXConstructorCall9422);
                            lv_arguments_10_0=ruleXExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"arguments",
                                      		lv_arguments_10_0, 
                                      		"XExpression");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4070:2: (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )*
                            loop70:
                            do {
                                int alt70=2;
                                int LA70_0 = input.LA(1);

                                if ( (LA70_0==53) ) {
                                    alt70=1;
                                }


                                switch (alt70) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4070:4: otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) )
                            	    {
                            	    otherlv_11=(Token)match(input,53,FOLLOW_53_in_ruleXConstructorCall9435); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_11, grammarAccess.getXConstructorCallAccess().getCommaKeyword_4_1_1_1_0());
                            	          
                            	    }
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4074:1: ( (lv_arguments_12_0= ruleXExpression ) )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4075:1: (lv_arguments_12_0= ruleXExpression )
                            	    {
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4075:1: (lv_arguments_12_0= ruleXExpression )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4076:3: lv_arguments_12_0= ruleXExpression
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXExpressionParserRuleCall_4_1_1_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleXExpression_in_ruleXConstructorCall9456);
                            	    lv_arguments_12_0=ruleXExpression();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"arguments",
                            	              		lv_arguments_12_0, 
                            	              		"XExpression");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop70;
                                }
                            } while (true);


                            }


                            }
                            break;

                    }

                    otherlv_13=(Token)match(input,55,FOLLOW_55_in_ruleXConstructorCall9473); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getXConstructorCallAccess().getRightParenthesisKeyword_4_2());
                          
                    }

                    }
                    break;

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4096:3: ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )?
            int alt73=2;
            alt73 = dfa73.predict(input);
            switch (alt73) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4096:4: ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4099:1: (lv_arguments_14_0= ruleXClosure )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4100:3: lv_arguments_14_0= ruleXClosure
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXClosureParserRuleCall_5_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXClosure_in_ruleXConstructorCall9508);
                    lv_arguments_14_0=ruleXClosure();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                      	        }
                             		add(
                             			current, 
                             			"arguments",
                              		lv_arguments_14_0, 
                              		"XClosure");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXConstructorCall"


    // $ANTLR start "entryRuleXBooleanLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4124:1: entryRuleXBooleanLiteral returns [EObject current=null] : iv_ruleXBooleanLiteral= ruleXBooleanLiteral EOF ;
    public final EObject entryRuleXBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXBooleanLiteral = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4125:2: (iv_ruleXBooleanLiteral= ruleXBooleanLiteral EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4126:2: iv_ruleXBooleanLiteral= ruleXBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleXBooleanLiteral_in_entryRuleXBooleanLiteral9545);
            iv_ruleXBooleanLiteral=ruleXBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXBooleanLiteral9555); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXBooleanLiteral"


    // $ANTLR start "ruleXBooleanLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4133:1: ruleXBooleanLiteral returns [EObject current=null] : ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) ;
    public final EObject ruleXBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_isTrue_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4136:28: ( ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4137:1: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4137:1: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4137:2: () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4137:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4138:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXBooleanLiteralAccess().getXBooleanLiteralAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4143:2: (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==71) ) {
                alt74=1;
            }
            else if ( (LA74_0==72) ) {
                alt74=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4143:4: otherlv_1= 'false'
                    {
                    otherlv_1=(Token)match(input,71,FOLLOW_71_in_ruleXBooleanLiteral9602); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getXBooleanLiteralAccess().getFalseKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4148:6: ( (lv_isTrue_2_0= 'true' ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4148:6: ( (lv_isTrue_2_0= 'true' ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4149:1: (lv_isTrue_2_0= 'true' )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4149:1: (lv_isTrue_2_0= 'true' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4150:3: lv_isTrue_2_0= 'true'
                    {
                    lv_isTrue_2_0=(Token)match(input,72,FOLLOW_72_in_ruleXBooleanLiteral9626); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isTrue_2_0, grammarAccess.getXBooleanLiteralAccess().getIsTrueTrueKeyword_1_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getXBooleanLiteralRule());
                      	        }
                             		setWithLastConsumed(current, "isTrue", true, "true");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXBooleanLiteral"


    // $ANTLR start "entryRuleXNullLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4171:1: entryRuleXNullLiteral returns [EObject current=null] : iv_ruleXNullLiteral= ruleXNullLiteral EOF ;
    public final EObject entryRuleXNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXNullLiteral = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4172:2: (iv_ruleXNullLiteral= ruleXNullLiteral EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4173:2: iv_ruleXNullLiteral= ruleXNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleXNullLiteral_in_entryRuleXNullLiteral9676);
            iv_ruleXNullLiteral=ruleXNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXNullLiteral9686); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXNullLiteral"


    // $ANTLR start "ruleXNullLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4180:1: ruleXNullLiteral returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleXNullLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4183:28: ( ( () otherlv_1= 'null' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4184:1: ( () otherlv_1= 'null' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4184:1: ( () otherlv_1= 'null' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4184:2: () otherlv_1= 'null'
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4184:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4185:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXNullLiteralAccess().getXNullLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,73,FOLLOW_73_in_ruleXNullLiteral9732); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXNullLiteralAccess().getNullKeyword_1());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXNullLiteral"


    // $ANTLR start "entryRuleXNumberLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4202:1: entryRuleXNumberLiteral returns [EObject current=null] : iv_ruleXNumberLiteral= ruleXNumberLiteral EOF ;
    public final EObject entryRuleXNumberLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXNumberLiteral = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4203:2: (iv_ruleXNumberLiteral= ruleXNumberLiteral EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4204:2: iv_ruleXNumberLiteral= ruleXNumberLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXNumberLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleXNumberLiteral_in_entryRuleXNumberLiteral9768);
            iv_ruleXNumberLiteral=ruleXNumberLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXNumberLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXNumberLiteral9778); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXNumberLiteral"


    // $ANTLR start "ruleXNumberLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4211:1: ruleXNumberLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= ruleNumber ) ) ) ;
    public final EObject ruleXNumberLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4214:28: ( ( () ( (lv_value_1_0= ruleNumber ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4215:1: ( () ( (lv_value_1_0= ruleNumber ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4215:1: ( () ( (lv_value_1_0= ruleNumber ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4215:2: () ( (lv_value_1_0= ruleNumber ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4215:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4216:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXNumberLiteralAccess().getXNumberLiteralAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4221:2: ( (lv_value_1_0= ruleNumber ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4222:1: (lv_value_1_0= ruleNumber )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4222:1: (lv_value_1_0= ruleNumber )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4223:3: lv_value_1_0= ruleNumber
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXNumberLiteralAccess().getValueNumberParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumber_in_ruleXNumberLiteral9833);
            lv_value_1_0=ruleNumber();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXNumberLiteralRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"Number");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXNumberLiteral"


    // $ANTLR start "entryRuleXStringLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4247:1: entryRuleXStringLiteral returns [EObject current=null] : iv_ruleXStringLiteral= ruleXStringLiteral EOF ;
    public final EObject entryRuleXStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStringLiteral = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4248:2: (iv_ruleXStringLiteral= ruleXStringLiteral EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4249:2: iv_ruleXStringLiteral= ruleXStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleXStringLiteral_in_entryRuleXStringLiteral9869);
            iv_ruleXStringLiteral=ruleXStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXStringLiteral9879); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXStringLiteral"


    // $ANTLR start "ruleXStringLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4256:1: ruleXStringLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleXStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4259:28: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4260:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4260:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4260:2: () ( (lv_value_1_0= RULE_STRING ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4260:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4261:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXStringLiteralAccess().getXStringLiteralAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4266:2: ( (lv_value_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4267:1: (lv_value_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4267:1: (lv_value_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4268:3: lv_value_1_0= RULE_STRING
            {
            lv_value_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleXStringLiteral9930); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_1_0, grammarAccess.getXStringLiteralAccess().getValueSTRINGTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getXStringLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_1_0, 
                      		"STRING");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXStringLiteral"


    // $ANTLR start "entryRuleXTypeLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4292:1: entryRuleXTypeLiteral returns [EObject current=null] : iv_ruleXTypeLiteral= ruleXTypeLiteral EOF ;
    public final EObject entryRuleXTypeLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXTypeLiteral = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4293:2: (iv_ruleXTypeLiteral= ruleXTypeLiteral EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4294:2: iv_ruleXTypeLiteral= ruleXTypeLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXTypeLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleXTypeLiteral_in_entryRuleXTypeLiteral9971);
            iv_ruleXTypeLiteral=ruleXTypeLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXTypeLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXTypeLiteral9981); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXTypeLiteral"


    // $ANTLR start "ruleXTypeLiteral"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4301:1: ruleXTypeLiteral returns [EObject current=null] : ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) otherlv_4= ')' ) ;
    public final EObject ruleXTypeLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4304:28: ( ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) otherlv_4= ')' ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4305:1: ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) otherlv_4= ')' )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4305:1: ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) otherlv_4= ')' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4305:2: () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) otherlv_4= ')'
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4305:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4306:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXTypeLiteralAccess().getXTypeLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,74,FOLLOW_74_in_ruleXTypeLiteral10027); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXTypeLiteralAccess().getTypeofKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,54,FOLLOW_54_in_ruleXTypeLiteral10039); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getXTypeLiteralAccess().getLeftParenthesisKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4319:1: ( ( ruleQualifiedName ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4320:1: ( ruleQualifiedName )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4320:1: ( ruleQualifiedName )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4321:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getXTypeLiteralRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXTypeLiteralAccess().getTypeJvmTypeCrossReference_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleXTypeLiteral10062);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,55,FOLLOW_55_in_ruleXTypeLiteral10074); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getXTypeLiteralAccess().getRightParenthesisKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXTypeLiteral"


    // $ANTLR start "entryRuleXThrowExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4346:1: entryRuleXThrowExpression returns [EObject current=null] : iv_ruleXThrowExpression= ruleXThrowExpression EOF ;
    public final EObject entryRuleXThrowExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXThrowExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4347:2: (iv_ruleXThrowExpression= ruleXThrowExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4348:2: iv_ruleXThrowExpression= ruleXThrowExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXThrowExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXThrowExpression_in_entryRuleXThrowExpression10110);
            iv_ruleXThrowExpression=ruleXThrowExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXThrowExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXThrowExpression10120); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXThrowExpression"


    // $ANTLR start "ruleXThrowExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4355:1: ruleXThrowExpression returns [EObject current=null] : ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) ) ;
    public final EObject ruleXThrowExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4358:28: ( ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4359:1: ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4359:1: ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4359:2: () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4359:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4360:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXThrowExpressionAccess().getXThrowExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,75,FOLLOW_75_in_ruleXThrowExpression10166); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXThrowExpressionAccess().getThrowKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4369:1: ( (lv_expression_2_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4370:1: (lv_expression_2_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4370:1: (lv_expression_2_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4371:3: lv_expression_2_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXThrowExpressionAccess().getExpressionXExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXThrowExpression10187);
            lv_expression_2_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXThrowExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"expression",
                      		lv_expression_2_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXThrowExpression"


    // $ANTLR start "entryRuleXReturnExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4395:1: entryRuleXReturnExpression returns [EObject current=null] : iv_ruleXReturnExpression= ruleXReturnExpression EOF ;
    public final EObject entryRuleXReturnExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXReturnExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4396:2: (iv_ruleXReturnExpression= ruleXReturnExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4397:2: iv_ruleXReturnExpression= ruleXReturnExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXReturnExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXReturnExpression_in_entryRuleXReturnExpression10223);
            iv_ruleXReturnExpression=ruleXReturnExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXReturnExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXReturnExpression10233); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXReturnExpression"


    // $ANTLR start "ruleXReturnExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4404:1: ruleXReturnExpression returns [EObject current=null] : ( () otherlv_1= 'return' ( ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression ) )? ) ;
    public final EObject ruleXReturnExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4407:28: ( ( () otherlv_1= 'return' ( ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression ) )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4408:1: ( () otherlv_1= 'return' ( ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression ) )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4408:1: ( () otherlv_1= 'return' ( ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression ) )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4408:2: () otherlv_1= 'return' ( ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression ) )?
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4408:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4409:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXReturnExpressionAccess().getXReturnExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,76,FOLLOW_76_in_ruleXReturnExpression10279); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXReturnExpressionAccess().getReturnKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4418:1: ( ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression ) )?
            int alt75=2;
            alt75 = dfa75.predict(input);
            switch (alt75) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4418:2: ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4423:1: (lv_expression_2_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4424:3: lv_expression_2_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXReturnExpressionAccess().getExpressionXExpressionParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXReturnExpression10310);
                    lv_expression_2_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXReturnExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"expression",
                              		lv_expression_2_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXReturnExpression"


    // $ANTLR start "entryRuleXTryCatchFinallyExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4448:1: entryRuleXTryCatchFinallyExpression returns [EObject current=null] : iv_ruleXTryCatchFinallyExpression= ruleXTryCatchFinallyExpression EOF ;
    public final EObject entryRuleXTryCatchFinallyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXTryCatchFinallyExpression = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4449:2: (iv_ruleXTryCatchFinallyExpression= ruleXTryCatchFinallyExpression EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4450:2: iv_ruleXTryCatchFinallyExpression= ruleXTryCatchFinallyExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleXTryCatchFinallyExpression_in_entryRuleXTryCatchFinallyExpression10347);
            iv_ruleXTryCatchFinallyExpression=ruleXTryCatchFinallyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXTryCatchFinallyExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXTryCatchFinallyExpression10357); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXTryCatchFinallyExpression"


    // $ANTLR start "ruleXTryCatchFinallyExpression"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4457:1: ruleXTryCatchFinallyExpression returns [EObject current=null] : ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) ) ;
    public final EObject ruleXTryCatchFinallyExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_expression_2_0 = null;

        EObject lv_catchClauses_3_0 = null;

        EObject lv_finallyExpression_5_0 = null;

        EObject lv_finallyExpression_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4460:28: ( ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4461:1: ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4461:1: ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4461:2: () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4461:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4462:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getXTryCatchFinallyExpressionAccess().getXTryCatchFinallyExpressionAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,77,FOLLOW_77_in_ruleXTryCatchFinallyExpression10403); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXTryCatchFinallyExpressionAccess().getTryKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4471:1: ( (lv_expression_2_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4472:1: (lv_expression_2_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4472:1: (lv_expression_2_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4473:3: lv_expression_2_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getExpressionXExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXTryCatchFinallyExpression10424);
            lv_expression_2_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"expression",
                      		lv_expression_2_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:2: ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==79) ) {
                alt78=1;
            }
            else if ( (LA78_0==78) ) {
                alt78=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }
            switch (alt78) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:3: ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:3: ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:4: ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )?
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:4: ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+
                    int cnt76=0;
                    loop76:
                    do {
                        int alt76=2;
                        int LA76_0 = input.LA(1);

                        if ( (LA76_0==79) ) {
                            int LA76_2 = input.LA(2);

                            if ( (synpred34_InternalKDiagram()) ) {
                                alt76=1;
                            }


                        }


                        switch (alt76) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:5: ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause )
                    	    {
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4491:1: (lv_catchClauses_3_0= ruleXCatchClause )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4492:3: lv_catchClauses_3_0= ruleXCatchClause
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getCatchClausesXCatchClauseParserRuleCall_3_0_0_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleXCatchClause_in_ruleXTryCatchFinallyExpression10454);
                    	    lv_catchClauses_3_0=ruleXCatchClause();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"catchClauses",
                    	              		lv_catchClauses_3_0, 
                    	              		"XCatchClause");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt76 >= 1 ) break loop76;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(76, input);
                                throw eee;
                        }
                        cnt76++;
                    } while (true);

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4508:3: ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==78) ) {
                        int LA77_1 = input.LA(2);

                        if ( (synpred35_InternalKDiagram()) ) {
                            alt77=1;
                        }
                    }
                    switch (alt77) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4508:4: ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4508:4: ( ( 'finally' )=>otherlv_4= 'finally' )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4508:5: ( 'finally' )=>otherlv_4= 'finally'
                            {
                            otherlv_4=(Token)match(input,78,FOLLOW_78_in_ruleXTryCatchFinallyExpression10476); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyKeyword_3_0_1_0());
                                  
                            }

                            }

                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4513:2: ( (lv_finallyExpression_5_0= ruleXExpression ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4514:1: (lv_finallyExpression_5_0= ruleXExpression )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4514:1: (lv_finallyExpression_5_0= ruleXExpression )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4515:3: lv_finallyExpression_5_0= ruleXExpression
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyExpressionXExpressionParserRuleCall_3_0_1_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleXExpression_in_ruleXTryCatchFinallyExpression10498);
                            lv_finallyExpression_5_0=ruleXExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"finallyExpression",
                                      		lv_finallyExpression_5_0, 
                                      		"XExpression");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4532:6: (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4532:6: (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4532:8: otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) )
                    {
                    otherlv_6=(Token)match(input,78,FOLLOW_78_in_ruleXTryCatchFinallyExpression10520); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyKeyword_3_1_0());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4536:1: ( (lv_finallyExpression_7_0= ruleXExpression ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4537:1: (lv_finallyExpression_7_0= ruleXExpression )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4537:1: (lv_finallyExpression_7_0= ruleXExpression )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4538:3: lv_finallyExpression_7_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyExpressionXExpressionParserRuleCall_3_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleXExpression_in_ruleXTryCatchFinallyExpression10541);
                    lv_finallyExpression_7_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"finallyExpression",
                              		lv_finallyExpression_7_0, 
                              		"XExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXTryCatchFinallyExpression"


    // $ANTLR start "entryRuleXCatchClause"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4562:1: entryRuleXCatchClause returns [EObject current=null] : iv_ruleXCatchClause= ruleXCatchClause EOF ;
    public final EObject entryRuleXCatchClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXCatchClause = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4563:2: (iv_ruleXCatchClause= ruleXCatchClause EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4564:2: iv_ruleXCatchClause= ruleXCatchClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXCatchClauseRule()); 
            }
            pushFollow(FOLLOW_ruleXCatchClause_in_entryRuleXCatchClause10579);
            iv_ruleXCatchClause=ruleXCatchClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXCatchClause; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXCatchClause10589); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXCatchClause"


    // $ANTLR start "ruleXCatchClause"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4571:1: ruleXCatchClause returns [EObject current=null] : ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) ) ;
    public final EObject ruleXCatchClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_declaredParam_2_0 = null;

        EObject lv_expression_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4574:28: ( ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4575:1: ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4575:1: ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4575:2: ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4575:2: ( ( 'catch' )=>otherlv_0= 'catch' )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4575:3: ( 'catch' )=>otherlv_0= 'catch'
            {
            otherlv_0=(Token)match(input,79,FOLLOW_79_in_ruleXCatchClause10634); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getXCatchClauseAccess().getCatchKeyword_0());
                  
            }

            }

            otherlv_1=(Token)match(input,54,FOLLOW_54_in_ruleXCatchClause10647); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getXCatchClauseAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4584:1: ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4585:1: (lv_declaredParam_2_0= ruleFullJvmFormalParameter )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4585:1: (lv_declaredParam_2_0= ruleFullJvmFormalParameter )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4586:3: lv_declaredParam_2_0= ruleFullJvmFormalParameter
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXCatchClauseAccess().getDeclaredParamFullJvmFormalParameterParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleFullJvmFormalParameter_in_ruleXCatchClause10668);
            lv_declaredParam_2_0=ruleFullJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXCatchClauseRule());
              	        }
                     		set(
                     			current, 
                     			"declaredParam",
                      		lv_declaredParam_2_0, 
                      		"FullJvmFormalParameter");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,55,FOLLOW_55_in_ruleXCatchClause10680); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getXCatchClauseAccess().getRightParenthesisKeyword_3());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4606:1: ( (lv_expression_4_0= ruleXExpression ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4607:1: (lv_expression_4_0= ruleXExpression )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4607:1: (lv_expression_4_0= ruleXExpression )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4608:3: lv_expression_4_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXCatchClauseAccess().getExpressionXExpressionParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleXExpression_in_ruleXCatchClause10701);
            lv_expression_4_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXCatchClauseRule());
              	        }
                     		set(
                     			current, 
                     			"expression",
                      		lv_expression_4_0, 
                      		"XExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXCatchClause"


    // $ANTLR start "entryRuleQualifiedName"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4632:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4633:2: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4634:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName10738);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedName.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedName10749); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4641:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;

        AntlrDatatypeRuleToken this_ValidID_2 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4644:28: ( (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4645:1: (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4645:1: (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4646:5: this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleValidID_in_ruleQualifiedName10796);
            this_ValidID_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ValidID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4656:1: ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==19) ) {
                    int LA79_2 = input.LA(2);

                    if ( (LA79_2==RULE_ID) ) {
                        int LA79_3 = input.LA(3);

                        if ( (synpred37_InternalKDiagram()) ) {
                            alt79=1;
                        }


                    }


                }


                switch (alt79) {
            	case 1 :
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4656:2: ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID
            	    {
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4656:2: ( ( '.' )=>kw= '.' )
            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4656:3: ( '.' )=>kw= '.'
            	    {
            	    kw=(Token)match(input,19,FOLLOW_19_in_ruleQualifiedName10824); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            	          
            	    }

            	    }

            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleValidID_in_ruleQualifiedName10847);
            	    this_ValidID_2=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ValidID_2);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              afterParserOrEnumRuleCall();
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleNumber"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4683:1: entryRuleNumber returns [String current=null] : iv_ruleNumber= ruleNumber EOF ;
    public final String entryRuleNumber() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumber = null;


         
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        	
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4687:2: (iv_ruleNumber= ruleNumber EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4688:2: iv_ruleNumber= ruleNumber EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumberRule()); 
            }
            pushFollow(FOLLOW_ruleNumber_in_entryRuleNumber10901);
            iv_ruleNumber=ruleNumber();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumber.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumber10912); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleNumber"


    // $ANTLR start "ruleNumber"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4698:1: ruleNumber returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) ) ;
    public final AntlrDatatypeRuleToken ruleNumber() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_HEX_0=null;
        Token this_INT_1=null;
        Token this_DECIMAL_2=null;
        Token kw=null;
        Token this_INT_4=null;
        Token this_DECIMAL_5=null;

         enterRule(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4702:28: ( (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4703:1: (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4703:1: (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==RULE_HEX) ) {
                alt83=1;
            }
            else if ( ((LA83_0>=RULE_INT && LA83_0<=RULE_DECIMAL)) ) {
                alt83=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }
            switch (alt83) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4703:6: this_HEX_0= RULE_HEX
                    {
                    this_HEX_0=(Token)match(input,RULE_HEX,FOLLOW_RULE_HEX_in_ruleNumber10956); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_HEX_0);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_HEX_0, grammarAccess.getNumberAccess().getHEXTerminalRuleCall_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4711:6: ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4711:6: ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4711:7: (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )?
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4711:7: (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL )
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==RULE_INT) ) {
                        alt80=1;
                    }
                    else if ( (LA80_0==RULE_DECIMAL) ) {
                        alt80=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 80, 0, input);

                        throw nvae;
                    }
                    switch (alt80) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4711:12: this_INT_1= RULE_INT
                            {
                            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleNumber10984); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              		current.merge(this_INT_1);
                                  
                            }
                            if ( state.backtracking==0 ) {
                               
                                  newLeafNode(this_INT_1, grammarAccess.getNumberAccess().getINTTerminalRuleCall_1_0_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4719:10: this_DECIMAL_2= RULE_DECIMAL
                            {
                            this_DECIMAL_2=(Token)match(input,RULE_DECIMAL,FOLLOW_RULE_DECIMAL_in_ruleNumber11010); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              		current.merge(this_DECIMAL_2);
                                  
                            }
                            if ( state.backtracking==0 ) {
                               
                                  newLeafNode(this_DECIMAL_2, grammarAccess.getNumberAccess().getDECIMALTerminalRuleCall_1_0_1()); 
                                  
                            }

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4726:2: (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )?
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==19) ) {
                        int LA82_1 = input.LA(2);

                        if ( ((LA82_1>=RULE_INT && LA82_1<=RULE_DECIMAL)) ) {
                            alt82=1;
                        }
                    }
                    switch (alt82) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4727:2: kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL )
                            {
                            kw=(Token)match(input,19,FOLLOW_19_in_ruleNumber11030); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNumberAccess().getFullStopKeyword_1_1_0()); 
                                  
                            }
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4732:1: (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL )
                            int alt81=2;
                            int LA81_0 = input.LA(1);

                            if ( (LA81_0==RULE_INT) ) {
                                alt81=1;
                            }
                            else if ( (LA81_0==RULE_DECIMAL) ) {
                                alt81=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 81, 0, input);

                                throw nvae;
                            }
                            switch (alt81) {
                                case 1 :
                                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4732:6: this_INT_4= RULE_INT
                                    {
                                    this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleNumber11046); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      		current.merge(this_INT_4);
                                          
                                    }
                                    if ( state.backtracking==0 ) {
                                       
                                          newLeafNode(this_INT_4, grammarAccess.getNumberAccess().getINTTerminalRuleCall_1_1_1_0()); 
                                          
                                    }

                                    }
                                    break;
                                case 2 :
                                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4740:10: this_DECIMAL_5= RULE_DECIMAL
                                    {
                                    this_DECIMAL_5=(Token)match(input,RULE_DECIMAL,FOLLOW_RULE_DECIMAL_in_ruleNumber11072); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      		current.merge(this_DECIMAL_5);
                                          
                                    }
                                    if ( state.backtracking==0 ) {
                                       
                                          newLeafNode(this_DECIMAL_5, grammarAccess.getNumberAccess().getDECIMALTerminalRuleCall_1_1_1_1()); 
                                          
                                    }

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleNumber"


    // $ANTLR start "entryRuleJvmTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4758:1: entryRuleJvmTypeReference returns [EObject current=null] : iv_ruleJvmTypeReference= ruleJvmTypeReference EOF ;
    public final EObject entryRuleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmTypeReference = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4759:2: (iv_ruleJvmTypeReference= ruleJvmTypeReference EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4760:2: iv_ruleJvmTypeReference= ruleJvmTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_entryRuleJvmTypeReference11125);
            iv_ruleJvmTypeReference=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmTypeReference11135); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmTypeReference"


    // $ANTLR start "ruleJvmTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4767:1: ruleJvmTypeReference returns [EObject current=null] : ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) | this_XFunctionTypeRef_4= ruleXFunctionTypeRef ) ;
    public final EObject ruleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject this_JvmParameterizedTypeReference_0 = null;

        EObject this_XFunctionTypeRef_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4770:28: ( ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) | this_XFunctionTypeRef_4= ruleXFunctionTypeRef ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4771:1: ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) | this_XFunctionTypeRef_4= ruleXFunctionTypeRef )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4771:1: ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* ) | this_XFunctionTypeRef_4= ruleXFunctionTypeRef )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==RULE_ID) ) {
                alt85=1;
            }
            else if ( (LA85_0==40||LA85_0==54) ) {
                alt85=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4771:2: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4771:2: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )* )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4772:5: this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )*
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getJvmParameterizedTypeReferenceParserRuleCall_0_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJvmParameterizedTypeReference_in_ruleJvmTypeReference11183);
                    this_JvmParameterizedTypeReference_0=ruleJvmParameterizedTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JvmParameterizedTypeReference_0; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4780:1: ( ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' ) )*
                    loop84:
                    do {
                        int alt84=2;
                        int LA84_0 = input.LA(1);

                        if ( (LA84_0==56) ) {
                            int LA84_2 = input.LA(2);

                            if ( (LA84_2==58) ) {
                                int LA84_3 = input.LA(3);

                                if ( (synpred38_InternalKDiagram()) ) {
                                    alt84=1;
                                }


                            }


                        }


                        switch (alt84) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4780:2: ( ( () '[' ']' ) )=> ( () otherlv_2= '[' otherlv_3= ']' )
                    	    {
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4783:5: ( () otherlv_2= '[' otherlv_3= ']' )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4783:6: () otherlv_2= '[' otherlv_3= ']'
                    	    {
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4783:6: ()
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4784:5: 
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	              current = forceCreateModelElementAndSet(
                    	                  grammarAccess.getJvmTypeReferenceAccess().getJvmGenericArrayTypeReferenceComponentTypeAction_0_1_0_0(),
                    	                  current);
                    	          
                    	    }

                    	    }

                    	    otherlv_2=(Token)match(input,56,FOLLOW_56_in_ruleJvmTypeReference11221); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getJvmTypeReferenceAccess().getLeftSquareBracketKeyword_0_1_0_1());
                    	          
                    	    }
                    	    otherlv_3=(Token)match(input,58,FOLLOW_58_in_ruleJvmTypeReference11233); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getJvmTypeReferenceAccess().getRightSquareBracketKeyword_0_1_0_2());
                    	          
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop84;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4799:5: this_XFunctionTypeRef_4= ruleXFunctionTypeRef
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getXFunctionTypeRefParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleXFunctionTypeRef_in_ruleJvmTypeReference11265);
                    this_XFunctionTypeRef_4=ruleXFunctionTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_XFunctionTypeRef_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmTypeReference"


    // $ANTLR start "entryRuleXFunctionTypeRef"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4815:1: entryRuleXFunctionTypeRef returns [EObject current=null] : iv_ruleXFunctionTypeRef= ruleXFunctionTypeRef EOF ;
    public final EObject entryRuleXFunctionTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionTypeRef = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4816:2: (iv_ruleXFunctionTypeRef= ruleXFunctionTypeRef EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4817:2: iv_ruleXFunctionTypeRef= ruleXFunctionTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXFunctionTypeRefRule()); 
            }
            pushFollow(FOLLOW_ruleXFunctionTypeRef_in_entryRuleXFunctionTypeRef11300);
            iv_ruleXFunctionTypeRef=ruleXFunctionTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXFunctionTypeRef; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleXFunctionTypeRef11310); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFunctionTypeRef"


    // $ANTLR start "ruleXFunctionTypeRef"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4824:1: ruleXFunctionTypeRef returns [EObject current=null] : ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleXFunctionTypeRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_paramTypes_1_0 = null;

        EObject lv_paramTypes_3_0 = null;

        EObject lv_returnType_6_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4827:28: ( ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4828:1: ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4828:1: ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4828:2: (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4828:2: (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==54) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4828:4: otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')'
                    {
                    otherlv_0=(Token)match(input,54,FOLLOW_54_in_ruleXFunctionTypeRef11348); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getXFunctionTypeRefAccess().getLeftParenthesisKeyword_0_0());
                          
                    }
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4832:1: ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==RULE_ID||LA87_0==40||LA87_0==54) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4832:2: ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )*
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4832:2: ( (lv_paramTypes_1_0= ruleJvmTypeReference ) )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4833:1: (lv_paramTypes_1_0= ruleJvmTypeReference )
                            {
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4833:1: (lv_paramTypes_1_0= ruleJvmTypeReference )
                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4834:3: lv_paramTypes_1_0= ruleJvmTypeReference
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleXFunctionTypeRef11370);
                            lv_paramTypes_1_0=ruleJvmTypeReference();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getXFunctionTypeRefRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"paramTypes",
                                      		lv_paramTypes_1_0, 
                                      		"JvmTypeReference");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4850:2: (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )*
                            loop86:
                            do {
                                int alt86=2;
                                int LA86_0 = input.LA(1);

                                if ( (LA86_0==53) ) {
                                    alt86=1;
                                }


                                switch (alt86) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4850:4: otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) )
                            	    {
                            	    otherlv_2=(Token)match(input,53,FOLLOW_53_in_ruleXFunctionTypeRef11383); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_2, grammarAccess.getXFunctionTypeRefAccess().getCommaKeyword_0_1_1_0());
                            	          
                            	    }
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4854:1: ( (lv_paramTypes_3_0= ruleJvmTypeReference ) )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4855:1: (lv_paramTypes_3_0= ruleJvmTypeReference )
                            	    {
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4855:1: (lv_paramTypes_3_0= ruleJvmTypeReference )
                            	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4856:3: lv_paramTypes_3_0= ruleJvmTypeReference
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleXFunctionTypeRef11404);
                            	    lv_paramTypes_3_0=ruleJvmTypeReference();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getXFunctionTypeRefRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"paramTypes",
                            	              		lv_paramTypes_3_0, 
                            	              		"JvmTypeReference");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop86;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_4=(Token)match(input,55,FOLLOW_55_in_ruleXFunctionTypeRef11420); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getXFunctionTypeRefAccess().getRightParenthesisKeyword_0_2());
                          
                    }

                    }
                    break;

            }

            otherlv_5=(Token)match(input,40,FOLLOW_40_in_ruleXFunctionTypeRef11434); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getXFunctionTypeRefAccess().getEqualsSignGreaterThanSignKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4880:1: ( (lv_returnType_6_0= ruleJvmTypeReference ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4881:1: (lv_returnType_6_0= ruleJvmTypeReference )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4881:1: (lv_returnType_6_0= ruleJvmTypeReference )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4882:3: lv_returnType_6_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getXFunctionTypeRefAccess().getReturnTypeJvmTypeReferenceParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleXFunctionTypeRef11455);
            lv_returnType_6_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getXFunctionTypeRefRule());
              	        }
                     		set(
                     			current, 
                     			"returnType",
                      		lv_returnType_6_0, 
                      		"JvmTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunctionTypeRef"


    // $ANTLR start "entryRuleJvmParameterizedTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4906:1: entryRuleJvmParameterizedTypeReference returns [EObject current=null] : iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF ;
    public final EObject entryRuleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmParameterizedTypeReference = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4907:2: (iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4908:2: iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmParameterizedTypeReference_in_entryRuleJvmParameterizedTypeReference11491);
            iv_ruleJvmParameterizedTypeReference=ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmParameterizedTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmParameterizedTypeReference11501); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmParameterizedTypeReference"


    // $ANTLR start "ruleJvmParameterizedTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4915:1: ruleJvmParameterizedTypeReference returns [EObject current=null] : ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ) ;
    public final EObject ruleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_arguments_2_0 = null;

        EObject lv_arguments_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4918:28: ( ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4919:1: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4919:1: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4919:2: ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4919:2: ( ( ruleQualifiedName ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4920:1: ( ruleQualifiedName )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4920:1: ( ruleQualifiedName )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4921:3: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getJvmParameterizedTypeReferenceRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedName_in_ruleJvmParameterizedTypeReference11549);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4934:2: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?
            int alt90=2;
            alt90 = dfa90.predict(input);
            switch (alt90) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4934:3: ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>'
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4934:3: ( ( '<' )=>otherlv_1= '<' )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4934:4: ( '<' )=>otherlv_1= '<'
                    {
                    otherlv_1=(Token)match(input,37,FOLLOW_37_in_ruleJvmParameterizedTypeReference11570); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_0());
                          
                    }

                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4939:2: ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4940:1: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4940:1: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4941:3: lv_arguments_2_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference11592);
                    lv_arguments_2_0=ruleJvmArgumentTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                      	        }
                             		add(
                             			current, 
                             			"arguments",
                              		lv_arguments_2_0, 
                              		"JvmArgumentTypeReference");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4957:2: (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )*
                    loop89:
                    do {
                        int alt89=2;
                        int LA89_0 = input.LA(1);

                        if ( (LA89_0==53) ) {
                            alt89=1;
                        }


                        switch (alt89) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4957:4: otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_3=(Token)match(input,53,FOLLOW_53_in_ruleJvmParameterizedTypeReference11605); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_2_0());
                    	          
                    	    }
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4961:1: ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4962:1: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4962:1: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4963:3: lv_arguments_4_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference11626);
                    	    lv_arguments_4_0=ruleJvmArgumentTypeReference();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"arguments",
                    	              		lv_arguments_4_0, 
                    	              		"JvmArgumentTypeReference");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop89;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,36,FOLLOW_36_in_ruleJvmParameterizedTypeReference11640); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_3());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmParameterizedTypeReference"


    // $ANTLR start "entryRuleJvmArgumentTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4991:1: entryRuleJvmArgumentTypeReference returns [EObject current=null] : iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF ;
    public final EObject entryRuleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmArgumentTypeReference = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4992:2: (iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4993:2: iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmArgumentTypeReference_in_entryRuleJvmArgumentTypeReference11678);
            iv_ruleJvmArgumentTypeReference=ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmArgumentTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmArgumentTypeReference11688); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmArgumentTypeReference"


    // $ANTLR start "ruleJvmArgumentTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5000:1: ruleJvmArgumentTypeReference returns [EObject current=null] : (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) ;
    public final EObject ruleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject this_JvmTypeReference_0 = null;

        EObject this_JvmWildcardTypeReference_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5003:28: ( (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5004:1: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5004:1: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==RULE_ID||LA91_0==40||LA91_0==54) ) {
                alt91=1;
            }
            else if ( (LA91_0==80) ) {
                alt91=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5005:5: this_JvmTypeReference_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmTypeReferenceParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmArgumentTypeReference11735);
                    this_JvmTypeReference_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JvmTypeReference_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5015:5: this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmWildcardTypeReferenceParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleJvmWildcardTypeReference_in_ruleJvmArgumentTypeReference11762);
                    this_JvmWildcardTypeReference_1=ruleJvmWildcardTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_JvmWildcardTypeReference_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmArgumentTypeReference"


    // $ANTLR start "entryRuleJvmWildcardTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5031:1: entryRuleJvmWildcardTypeReference returns [EObject current=null] : iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF ;
    public final EObject entryRuleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmWildcardTypeReference = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5032:2: (iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5033:2: iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_ruleJvmWildcardTypeReference_in_entryRuleJvmWildcardTypeReference11797);
            iv_ruleJvmWildcardTypeReference=ruleJvmWildcardTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmWildcardTypeReference; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmWildcardTypeReference11807); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmWildcardTypeReference"


    // $ANTLR start "ruleJvmWildcardTypeReference"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5040:1: ruleJvmWildcardTypeReference returns [EObject current=null] : ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? ) ;
    public final EObject ruleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_constraints_2_0 = null;

        EObject lv_constraints_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5043:28: ( ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5044:1: ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5044:1: ( () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )? )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5044:2: () otherlv_1= '?' ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )?
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5044:2: ()
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5045:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getJvmWildcardTypeReferenceAccess().getJvmWildcardTypeReferenceAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,80,FOLLOW_80_in_ruleJvmWildcardTypeReference11853); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getJvmWildcardTypeReferenceAccess().getQuestionMarkKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5054:1: ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) | ( (lv_constraints_3_0= ruleJvmLowerBound ) ) )?
            int alt92=3;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==81) ) {
                alt92=1;
            }
            else if ( (LA92_0==68) ) {
                alt92=2;
            }
            switch (alt92) {
                case 1 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5054:2: ( (lv_constraints_2_0= ruleJvmUpperBound ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5054:2: ( (lv_constraints_2_0= ruleJvmUpperBound ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5055:1: (lv_constraints_2_0= ruleJvmUpperBound )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5055:1: (lv_constraints_2_0= ruleJvmUpperBound )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5056:3: lv_constraints_2_0= ruleJvmUpperBound
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmUpperBound_in_ruleJvmWildcardTypeReference11875);
                    lv_constraints_2_0=ruleJvmUpperBound();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                      	        }
                             		add(
                             			current, 
                             			"constraints",
                              		lv_constraints_2_0, 
                              		"JvmUpperBound");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5073:6: ( (lv_constraints_3_0= ruleJvmLowerBound ) )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5073:6: ( (lv_constraints_3_0= ruleJvmLowerBound ) )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5074:1: (lv_constraints_3_0= ruleJvmLowerBound )
                    {
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5074:1: (lv_constraints_3_0= ruleJvmLowerBound )
                    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5075:3: lv_constraints_3_0= ruleJvmLowerBound
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleJvmLowerBound_in_ruleJvmWildcardTypeReference11902);
                    lv_constraints_3_0=ruleJvmLowerBound();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                      	        }
                             		add(
                             			current, 
                             			"constraints",
                              		lv_constraints_3_0, 
                              		"JvmLowerBound");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmWildcardTypeReference"


    // $ANTLR start "entryRuleJvmUpperBound"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5099:1: entryRuleJvmUpperBound returns [EObject current=null] : iv_ruleJvmUpperBound= ruleJvmUpperBound EOF ;
    public final EObject entryRuleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmUpperBound = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5100:2: (iv_ruleJvmUpperBound= ruleJvmUpperBound EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5101:2: iv_ruleJvmUpperBound= ruleJvmUpperBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmUpperBoundRule()); 
            }
            pushFollow(FOLLOW_ruleJvmUpperBound_in_entryRuleJvmUpperBound11940);
            iv_ruleJvmUpperBound=ruleJvmUpperBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmUpperBound; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmUpperBound11950); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmUpperBound"


    // $ANTLR start "ruleJvmUpperBound"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5108:1: ruleJvmUpperBound returns [EObject current=null] : (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5111:28: ( (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5112:1: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5112:1: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5112:3: otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,81,FOLLOW_81_in_ruleJvmUpperBound11987); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJvmUpperBoundAccess().getExtendsKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5116:1: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5117:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5117:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5118:3: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmUpperBound12008);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getJvmUpperBoundRule());
              	        }
                     		set(
                     			current, 
                     			"typeReference",
                      		lv_typeReference_1_0, 
                      		"JvmTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmUpperBound"


    // $ANTLR start "entryRuleJvmUpperBoundAnded"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5142:1: entryRuleJvmUpperBoundAnded returns [EObject current=null] : iv_ruleJvmUpperBoundAnded= ruleJvmUpperBoundAnded EOF ;
    public final EObject entryRuleJvmUpperBoundAnded() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmUpperBoundAnded = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5143:2: (iv_ruleJvmUpperBoundAnded= ruleJvmUpperBoundAnded EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5144:2: iv_ruleJvmUpperBoundAnded= ruleJvmUpperBoundAnded EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmUpperBoundAndedRule()); 
            }
            pushFollow(FOLLOW_ruleJvmUpperBoundAnded_in_entryRuleJvmUpperBoundAnded12044);
            iv_ruleJvmUpperBoundAnded=ruleJvmUpperBoundAnded();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmUpperBoundAnded; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmUpperBoundAnded12054); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmUpperBoundAnded"


    // $ANTLR start "ruleJvmUpperBoundAnded"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5151:1: ruleJvmUpperBoundAnded returns [EObject current=null] : (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmUpperBoundAnded() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5154:28: ( (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5155:1: (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5155:1: (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5155:3: otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,82,FOLLOW_82_in_ruleJvmUpperBoundAnded12091); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJvmUpperBoundAndedAccess().getAmpersandKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5159:1: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5160:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5160:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5161:3: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmUpperBoundAndedAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmUpperBoundAnded12112);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getJvmUpperBoundAndedRule());
              	        }
                     		set(
                     			current, 
                     			"typeReference",
                      		lv_typeReference_1_0, 
                      		"JvmTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmUpperBoundAnded"


    // $ANTLR start "entryRuleJvmLowerBound"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5185:1: entryRuleJvmLowerBound returns [EObject current=null] : iv_ruleJvmLowerBound= ruleJvmLowerBound EOF ;
    public final EObject entryRuleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmLowerBound = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5186:2: (iv_ruleJvmLowerBound= ruleJvmLowerBound EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5187:2: iv_ruleJvmLowerBound= ruleJvmLowerBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmLowerBoundRule()); 
            }
            pushFollow(FOLLOW_ruleJvmLowerBound_in_entryRuleJvmLowerBound12148);
            iv_ruleJvmLowerBound=ruleJvmLowerBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmLowerBound; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleJvmLowerBound12158); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJvmLowerBound"


    // $ANTLR start "ruleJvmLowerBound"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5194:1: ruleJvmLowerBound returns [EObject current=null] : (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5197:28: ( (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5198:1: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5198:1: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5198:3: otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,68,FOLLOW_68_in_ruleJvmLowerBound12195); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getJvmLowerBoundAccess().getSuperKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5202:1: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5203:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5203:1: (lv_typeReference_1_0= ruleJvmTypeReference )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5204:3: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleJvmTypeReference_in_ruleJvmLowerBound12216);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getJvmLowerBoundRule());
              	        }
                     		set(
                     			current, 
                     			"typeReference",
                      		lv_typeReference_1_0, 
                      		"JvmTypeReference");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJvmLowerBound"


    // $ANTLR start "entryRuleValidID"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5230:1: entryRuleValidID returns [String current=null] : iv_ruleValidID= ruleValidID EOF ;
    public final String entryRuleValidID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleValidID = null;


        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5231:2: (iv_ruleValidID= ruleValidID EOF )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5232:2: iv_ruleValidID= ruleValidID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValidIDRule()); 
            }
            pushFollow(FOLLOW_ruleValidID_in_entryRuleValidID12255);
            iv_ruleValidID=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValidID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleValidID12266); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValidID"


    // $ANTLR start "ruleValidID"
    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5239:1: ruleValidID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleValidID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5242:28: (this_ID_0= RULE_ID )
            // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:5243:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleValidID12305); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getValidIDAccess().getIDTerminalRuleCall()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidID"

    // $ANTLR start synpred1_InternalKDiagram
    public final void synpred1_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:3: ( ( () ( ( ruleOpMultiAssign ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:4: ( () ( ( ruleOpMultiAssign ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:4: ( () ( ( ruleOpMultiAssign ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:5: () ( ( ruleOpMultiAssign ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:610:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:611:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:611:2: ( ( ruleOpMultiAssign ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:612:1: ( ruleOpMultiAssign )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:612:1: ( ruleOpMultiAssign )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:613:3: ruleOpMultiAssign
        {
        pushFollow(FOLLOW_ruleOpMultiAssign_in_synpred1_InternalKDiagram1277);
        ruleOpMultiAssign();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred1_InternalKDiagram

    // $ANTLR start synpred2_InternalKDiagram
    public final void synpred2_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:3: ( ( () ( ( ruleOpOr ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:4: ( () ( ( ruleOpOr ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:4: ( () ( ( ruleOpOr ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:5: () ( ( ruleOpOr ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:738:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:739:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:739:2: ( ( ruleOpOr ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:740:1: ( ruleOpOr )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:740:1: ( ruleOpOr )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:741:3: ruleOpOr
        {
        pushFollow(FOLLOW_ruleOpOr_in_synpred2_InternalKDiagram1625);
        ruleOpOr();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred2_InternalKDiagram

    // $ANTLR start synpred3_InternalKDiagram
    public final void synpred3_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:3: ( ( () ( ( ruleOpAnd ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:4: ( () ( ( ruleOpAnd ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:4: ( () ( ( ruleOpAnd ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:5: () ( ( ruleOpAnd ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:839:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:840:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:840:2: ( ( ruleOpAnd ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:841:1: ( ruleOpAnd )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:841:1: ( ruleOpAnd )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:842:3: ruleOpAnd
        {
        pushFollow(FOLLOW_ruleOpAnd_in_synpred3_InternalKDiagram1884);
        ruleOpAnd();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalKDiagram

    // $ANTLR start synpred4_InternalKDiagram
    public final void synpred4_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:3: ( ( () ( ( ruleOpEquality ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:4: ( () ( ( ruleOpEquality ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:4: ( () ( ( ruleOpEquality ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:5: () ( ( ruleOpEquality ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:940:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:941:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:941:2: ( ( ruleOpEquality ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:942:1: ( ruleOpEquality )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:942:1: ( ruleOpEquality )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:943:3: ruleOpEquality
        {
        pushFollow(FOLLOW_ruleOpEquality_in_synpred4_InternalKDiagram2143);
        ruleOpEquality();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred4_InternalKDiagram

    // $ANTLR start synpred5_InternalKDiagram
    public final void synpred5_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:4: ( ( () 'instanceof' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:5: ( () 'instanceof' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:5: ( () 'instanceof' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:6: () 'instanceof'
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1048:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1049:1: 
        {
        }

        match(input,33,FOLLOW_33_in_synpred5_InternalKDiagram2419); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred5_InternalKDiagram

    // $ANTLR start synpred6_InternalKDiagram
    public final void synpred6_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:8: ( ( () ( ( ruleOpCompare ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:9: ( () ( ( ruleOpCompare ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:9: ( () ( ( ruleOpCompare ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:10: () ( ( ruleOpCompare ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1079:10: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1080:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1080:2: ( ( ruleOpCompare ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1081:1: ( ruleOpCompare )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1081:1: ( ruleOpCompare )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1082:3: ruleOpCompare
        {
        pushFollow(FOLLOW_ruleOpCompare_in_synpred6_InternalKDiagram2490);
        ruleOpCompare();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred6_InternalKDiagram

    // $ANTLR start synpred7_InternalKDiagram
    public final void synpred7_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:3: ( ( () ( ( ruleOpOther ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:4: ( () ( ( ruleOpOther ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:4: ( () ( ( ruleOpOther ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:5: () ( ( ruleOpOther ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1201:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1202:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1202:2: ( ( ruleOpOther ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1203:1: ( ruleOpOther )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1203:1: ( ruleOpOther )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1204:3: ruleOpOther
        {
        pushFollow(FOLLOW_ruleOpOther_in_synpred7_InternalKDiagram2809);
        ruleOpOther();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred7_InternalKDiagram

    // $ANTLR start synpred8_InternalKDiagram
    public final void synpred8_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1293:3: ( ( '>' '>' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1293:4: ( '>' '>' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1293:4: ( '>' '>' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1294:2: '>' '>'
        {
        match(input,36,FOLLOW_36_in_synpred8_InternalKDiagram3025); if (state.failed) return ;
        match(input,36,FOLLOW_36_in_synpred8_InternalKDiagram3030); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred8_InternalKDiagram

    // $ANTLR start synpred9_InternalKDiagram
    public final void synpred9_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1323:3: ( ( '<' '<' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1323:4: ( '<' '<' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1323:4: ( '<' '<' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1324:2: '<' '<'
        {
        match(input,37,FOLLOW_37_in_synpred9_InternalKDiagram3112); if (state.failed) return ;
        match(input,37,FOLLOW_37_in_synpred9_InternalKDiagram3117); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred9_InternalKDiagram

    // $ANTLR start synpred10_InternalKDiagram
    public final void synpred10_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:3: ( ( () ( ( ruleOpAdd ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:4: ( () ( ( ruleOpAdd ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:4: ( () ( ( ruleOpAdd ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:5: () ( ( ruleOpAdd ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1397:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1398:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1398:2: ( ( ruleOpAdd ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1399:1: ( ruleOpAdd )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1399:1: ( ruleOpAdd )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1400:3: ruleOpAdd
        {
        pushFollow(FOLLOW_ruleOpAdd_in_synpred10_InternalKDiagram3339);
        ruleOpAdd();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred10_InternalKDiagram

    // $ANTLR start synpred11_InternalKDiagram
    public final void synpred11_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:3: ( ( () ( ( ruleOpMulti ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:4: ( () ( ( ruleOpMulti ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:4: ( () ( ( ruleOpMulti ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:5: () ( ( ruleOpMulti ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1505:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1506:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1506:2: ( ( ruleOpMulti ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1507:1: ( ruleOpMulti )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1507:1: ( ruleOpMulti )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1508:3: ruleOpMulti
        {
        pushFollow(FOLLOW_ruleOpMulti_in_synpred11_InternalKDiagram3619);
        ruleOpMulti();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred11_InternalKDiagram

    // $ANTLR start synpred12_InternalKDiagram
    public final void synpred12_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:3: ( ( () 'as' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:4: ( () 'as' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:4: ( () 'as' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:5: () 'as'
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1738:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1739:1: 
        {
        }

        match(input,50,FOLLOW_50_in_synpred12_InternalKDiagram4213); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred12_InternalKDiagram

    // $ANTLR start synpred13_InternalKDiagram
    public final void synpred13_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:4: ( ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:5: ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:5: ( () '.' ( ( ruleValidID ) ) ruleOpSingleAssign )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:6: () '.' ( ( ruleValidID ) ) ruleOpSingleAssign
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1798:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1799:1: 
        {
        }

        match(input,19,FOLLOW_19_in_synpred13_InternalKDiagram4367); if (state.failed) return ;
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1800:1: ( ( ruleValidID ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1801:1: ( ruleValidID )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1801:1: ( ruleValidID )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1802:3: ruleValidID
        {
        pushFollow(FOLLOW_ruleValidID_in_synpred13_InternalKDiagram4376);
        ruleValidID();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        pushFollow(FOLLOW_ruleOpSingleAssign_in_synpred13_InternalKDiagram4382);
        ruleOpSingleAssign();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred13_InternalKDiagram

    // $ANTLR start synpred14_InternalKDiagram
    public final void synpred14_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:8: ( ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:9: ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:9: ( () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:10: () ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1856:10: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1857:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1857:2: ( '.' | ( ( '?.' ) ) | ( ( '*.' ) ) )
        int alt93=3;
        switch ( input.LA(1) ) {
        case 19:
            {
            alt93=1;
            }
            break;
        case 51:
            {
            alt93=2;
            }
            break;
        case 52:
            {
            alt93=3;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 93, 0, input);

            throw nvae;
        }

        switch (alt93) {
            case 1 :
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1857:4: '.'
                {
                match(input,19,FOLLOW_19_in_synpred14_InternalKDiagram4485); if (state.failed) return ;

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1859:6: ( ( '?.' ) )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1859:6: ( ( '?.' ) )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1860:1: ( '?.' )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1860:1: ( '?.' )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1861:2: '?.'
                {
                match(input,51,FOLLOW_51_in_synpred14_InternalKDiagram4499); if (state.failed) return ;

                }


                }


                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1866:6: ( ( '*.' ) )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1866:6: ( ( '*.' ) )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1867:1: ( '*.' )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1867:1: ( '*.' )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1868:2: '*.'
                {
                match(input,52,FOLLOW_52_in_synpred14_InternalKDiagram4519); if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred14_InternalKDiagram

    // $ANTLR start synpred15_InternalKDiagram
    public final void synpred15_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1977:4: ( ( '(' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1978:1: ( '(' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1978:1: ( '(' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1979:2: '('
        {
        match(input,54,FOLLOW_54_in_synpred15_InternalKDiagram4746); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred15_InternalKDiagram

    // $ANTLR start synpred16_InternalKDiagram
    public final void synpred16_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:4: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:5: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:5: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:6: () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1998:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1999:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1999:2: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt95=2;
        int LA95_0 = input.LA(1);

        if ( (LA95_0==RULE_ID||LA95_0==40||LA95_0==54) ) {
            alt95=1;
        }
        switch (alt95) {
            case 1 :
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1999:3: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:1999:3: ( ( ruleJvmFormalParameter ) )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2000:1: ( ruleJvmFormalParameter )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2000:1: ( ruleJvmFormalParameter )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2001:1: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred16_InternalKDiagram4798);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2003:2: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop94:
                do {
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==53) ) {
                        alt94=1;
                    }


                    switch (alt94) {
                	case 1 :
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2003:4: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,53,FOLLOW_53_in_synpred16_InternalKDiagram4805); if (state.failed) return ;
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2004:1: ( ( ruleJvmFormalParameter ) )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2005:1: ( ruleJvmFormalParameter )
                	    {
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2005:1: ( ruleJvmFormalParameter )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2006:1: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred16_InternalKDiagram4812);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop94;
                    }
                } while (true);


                }
                break;

        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2008:6: ( ( '|' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2009:1: ( '|' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2009:1: ( '|' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2010:2: '|'
        {
        match(input,57,FOLLOW_57_in_synpred16_InternalKDiagram4826); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred16_InternalKDiagram

    // $ANTLR start synpred17_InternalKDiagram
    public final void synpred17_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2077:4: ( ( () '[' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2077:5: ( () '[' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2077:5: ( () '[' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2077:6: () '['
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2077:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2078:1: 
        {
        }

        match(input,56,FOLLOW_56_in_synpred17_InternalKDiagram4946); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred17_InternalKDiagram

    // $ANTLR start synpred18_InternalKDiagram
    public final void synpred18_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:3: ( ( () '[' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:4: ( () '[' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:4: ( () '[' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:5: () '['
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2268:5: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2269:1: 
        {
        }

        match(input,56,FOLLOW_56_in_synpred18_InternalKDiagram5470); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred18_InternalKDiagram

    // $ANTLR start synpred20_InternalKDiagram
    public final void synpred20_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:4: ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:5: ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:5: ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:6: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:6: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt97=2;
        int LA97_0 = input.LA(1);

        if ( (LA97_0==RULE_ID||LA97_0==40||LA97_0==54) ) {
            alt97=1;
        }
        switch (alt97) {
            case 1 :
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:7: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2362:7: ( ( ruleJvmFormalParameter ) )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2363:1: ( ruleJvmFormalParameter )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2363:1: ( ruleJvmFormalParameter )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2364:1: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred20_InternalKDiagram5749);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2366:2: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop96:
                do {
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==53) ) {
                        alt96=1;
                    }


                    switch (alt96) {
                	case 1 :
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2366:4: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,53,FOLLOW_53_in_synpred20_InternalKDiagram5756); if (state.failed) return ;
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2367:1: ( ( ruleJvmFormalParameter ) )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2368:1: ( ruleJvmFormalParameter )
                	    {
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2368:1: ( ruleJvmFormalParameter )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2369:1: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred20_InternalKDiagram5763);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop96;
                    }
                } while (true);


                }
                break;

        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2371:6: ( ( '|' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2372:1: ( '|' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2372:1: ( '|' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2373:2: '|'
        {
        match(input,57,FOLLOW_57_in_synpred20_InternalKDiagram5777); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred20_InternalKDiagram

    // $ANTLR start synpred22_InternalKDiagram
    public final void synpred22_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2732:4: ( 'else' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2732:6: 'else'
        {
        match(input,60,FOLLOW_60_in_synpred22_InternalKDiagram6560); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred22_InternalKDiagram

    // $ANTLR start synpred23_InternalKDiagram
    public final void synpred23_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:4: ( ( ( ( ruleValidID ) ) ':' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:5: ( ( ( ruleValidID ) ) ':' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:5: ( ( ( ruleValidID ) ) ':' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:6: ( ( ruleValidID ) ) ':'
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2786:6: ( ( ruleValidID ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2787:1: ( ruleValidID )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2787:1: ( ruleValidID )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2788:1: ruleValidID
        {
        pushFollow(FOLLOW_ruleValidID_in_synpred23_InternalKDiagram6702);
        ruleValidID();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,22,FOLLOW_22_in_synpred23_InternalKDiagram6708); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred23_InternalKDiagram

    // $ANTLR start synpred24_InternalKDiagram
    public final void synpred24_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:8: ( ( '(' ( ( ruleValidID ) ) ':' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:9: ( '(' ( ( ruleValidID ) ) ':' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:9: ( '(' ( ( ruleValidID ) ) ':' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2832:11: '(' ( ( ruleValidID ) ) ':'
        {
        match(input,54,FOLLOW_54_in_synpred24_InternalKDiagram6784); if (state.failed) return ;
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2833:1: ( ( ruleValidID ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2834:1: ( ruleValidID )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2834:1: ( ruleValidID )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:2835:1: ruleValidID
        {
        pushFollow(FOLLOW_ruleValidID_in_synpred24_InternalKDiagram6791);
        ruleValidID();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,22,FOLLOW_22_in_synpred24_InternalKDiagram6797); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred24_InternalKDiagram

    // $ANTLR start synpred25_InternalKDiagram
    public final void synpred25_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:4: ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:5: ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:5: ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:6: ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3416:6: ( ( ruleJvmTypeReference ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3417:1: ( ruleJvmTypeReference )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3417:1: ( ruleJvmTypeReference )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3418:1: ruleJvmTypeReference
        {
        pushFollow(FOLLOW_ruleJvmTypeReference_in_synpred25_InternalKDiagram8062);
        ruleJvmTypeReference();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3420:2: ( ( ruleValidID ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3421:1: ( ruleValidID )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3421:1: ( ruleValidID )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3422:1: ruleValidID
        {
        pushFollow(FOLLOW_ruleValidID_in_synpred25_InternalKDiagram8071);
        ruleValidID();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred25_InternalKDiagram

    // $ANTLR start synpred26_InternalKDiagram
    public final void synpred26_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3720:4: ( ( '(' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3721:1: ( '(' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3721:1: ( '(' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3722:2: '('
        {
        match(input,54,FOLLOW_54_in_synpred26_InternalKDiagram8633); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred26_InternalKDiagram

    // $ANTLR start synpred27_InternalKDiagram
    public final void synpred27_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:4: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:5: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:5: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:6: () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3741:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3742:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3742:2: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt101=2;
        int LA101_0 = input.LA(1);

        if ( (LA101_0==RULE_ID||LA101_0==40||LA101_0==54) ) {
            alt101=1;
        }
        switch (alt101) {
            case 1 :
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3742:3: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3742:3: ( ( ruleJvmFormalParameter ) )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3743:1: ( ruleJvmFormalParameter )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3743:1: ( ruleJvmFormalParameter )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3744:1: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred27_InternalKDiagram8685);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3746:2: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop100:
                do {
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==53) ) {
                        alt100=1;
                    }


                    switch (alt100) {
                	case 1 :
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3746:4: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,53,FOLLOW_53_in_synpred27_InternalKDiagram8692); if (state.failed) return ;
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3747:1: ( ( ruleJvmFormalParameter ) )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3748:1: ( ruleJvmFormalParameter )
                	    {
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3748:1: ( ruleJvmFormalParameter )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3749:1: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred27_InternalKDiagram8699);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop100;
                    }
                } while (true);


                }
                break;

        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3751:6: ( ( '|' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3752:1: ( '|' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3752:1: ( '|' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3753:2: '|'
        {
        match(input,57,FOLLOW_57_in_synpred27_InternalKDiagram8713); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred27_InternalKDiagram

    // $ANTLR start synpred28_InternalKDiagram
    public final void synpred28_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3820:4: ( ( () '[' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3820:5: ( () '[' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3820:5: ( () '[' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3820:6: () '['
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3820:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3821:1: 
        {
        }

        match(input,56,FOLLOW_56_in_synpred28_InternalKDiagram8833); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred28_InternalKDiagram

    // $ANTLR start synpred29_InternalKDiagram
    public final void synpred29_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3963:4: ( '<' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:3963:6: '<'
        {
        match(input,37,FOLLOW_37_in_synpred29_InternalKDiagram9220); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred29_InternalKDiagram

    // $ANTLR start synpred30_InternalKDiagram
    public final void synpred30_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4012:5: ( '(' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4012:7: '('
        {
        match(input,54,FOLLOW_54_in_synpred30_InternalKDiagram9313); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred30_InternalKDiagram

    // $ANTLR start synpred31_InternalKDiagram
    public final void synpred31_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:4: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:5: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:5: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:6: () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4017:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4018:1: 
        {
        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4018:2: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt103=2;
        int LA103_0 = input.LA(1);

        if ( (LA103_0==RULE_ID||LA103_0==40||LA103_0==54) ) {
            alt103=1;
        }
        switch (alt103) {
            case 1 :
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4018:3: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4018:3: ( ( ruleJvmFormalParameter ) )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4019:1: ( ruleJvmFormalParameter )
                {
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4019:1: ( ruleJvmFormalParameter )
                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4020:1: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred31_InternalKDiagram9343);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4022:2: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop102:
                do {
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==53) ) {
                        alt102=1;
                    }


                    switch (alt102) {
                	case 1 :
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4022:4: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,53,FOLLOW_53_in_synpred31_InternalKDiagram9350); if (state.failed) return ;
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4023:1: ( ( ruleJvmFormalParameter ) )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4024:1: ( ruleJvmFormalParameter )
                	    {
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4024:1: ( ruleJvmFormalParameter )
                	    // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4025:1: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_ruleJvmFormalParameter_in_synpred31_InternalKDiagram9357);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop102;
                    }
                } while (true);


                }
                break;

        }

        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4027:6: ( ( '|' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4028:1: ( '|' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4028:1: ( '|' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4029:2: '|'
        {
        match(input,57,FOLLOW_57_in_synpred31_InternalKDiagram9371); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred31_InternalKDiagram

    // $ANTLR start synpred32_InternalKDiagram
    public final void synpred32_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4096:4: ( ( () '[' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4096:5: ( () '[' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4096:5: ( () '[' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4096:6: () '['
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4096:6: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4097:1: 
        {
        }

        match(input,56,FOLLOW_56_in_synpred32_InternalKDiagram9491); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred32_InternalKDiagram

    // $ANTLR start synpred33_InternalKDiagram
    public final void synpred33_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4418:2: ( ( ruleXExpression ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4419:1: ( ruleXExpression )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4419:1: ( ruleXExpression )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4420:1: ruleXExpression
        {
        pushFollow(FOLLOW_ruleXExpression_in_synpred33_InternalKDiagram10293);
        ruleXExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred33_InternalKDiagram

    // $ANTLR start synpred34_InternalKDiagram
    public final void synpred34_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:5: ( 'catch' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4489:7: 'catch'
        {
        match(input,79,FOLLOW_79_in_synpred34_InternalKDiagram10438); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_InternalKDiagram

    // $ANTLR start synpred35_InternalKDiagram
    public final void synpred35_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4508:5: ( 'finally' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4508:7: 'finally'
        {
        match(input,78,FOLLOW_78_in_synpred35_InternalKDiagram10468); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_InternalKDiagram

    // $ANTLR start synpred37_InternalKDiagram
    public final void synpred37_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4656:3: ( '.' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4657:2: '.'
        {
        match(input,19,FOLLOW_19_in_synpred37_InternalKDiagram10815); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred37_InternalKDiagram

    // $ANTLR start synpred38_InternalKDiagram
    public final void synpred38_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4780:2: ( ( () '[' ']' ) )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4780:3: ( () '[' ']' )
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4780:3: ( () '[' ']' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4780:4: () '[' ']'
        {
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4780:4: ()
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4781:1: 
        {
        }

        match(input,56,FOLLOW_56_in_synpred38_InternalKDiagram11198); if (state.failed) return ;
        match(input,58,FOLLOW_58_in_synpred38_InternalKDiagram11202); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred38_InternalKDiagram

    // $ANTLR start synpred39_InternalKDiagram
    public final void synpred39_InternalKDiagram_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4934:4: ( '<' )
        // ../de.cau.cs.kieler.klighd.kdiagram/src-gen/de/cau/cs/kieler/klighd/kdiagram/parser/antlr/internal/InternalKDiagram.g:4934:6: '<'
        {
        match(input,37,FOLLOW_37_in_synpred39_InternalKDiagram11562); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred39_InternalKDiagram

    // Delegated rules

    public final boolean synpred17_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred26_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred39_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred39_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred24_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred29_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred29_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred28_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred28_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred38_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred38_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred32_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred23_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred23_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred37_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred33_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred33_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred22_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred25_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred25_InternalKDiagram_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalKDiagram() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalKDiagram_fragment(); // can never throw exception
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
    protected DFA17 dfa17 = new DFA17(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA32 dfa32 = new DFA32(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA36 dfa36 = new DFA36(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA64 dfa64 = new DFA64(this);
    protected DFA63 dfa63 = new DFA63(this);
    protected DFA65 dfa65 = new DFA65(this);
    protected DFA69 dfa69 = new DFA69(this);
    protected DFA72 dfa72 = new DFA72(this);
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA73 dfa73 = new DFA73(this);
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA90 dfa90 = new DFA90(this);
    static final String DFA5_eotS =
        "\7\uffff";
    static final String DFA5_eofS =
        "\2\uffff\1\4\3\uffff\1\4";
    static final String DFA5_minS =
        "\1\4\1\uffff\1\16\1\4\2\uffff\1\16";
    static final String DFA5_maxS =
        "\1\21\1\uffff\1\23\1\24\2\uffff\1\23";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\2\uffff\1\2\1\3\1\uffff";
    static final String DFA5_specialS =
        "\7\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\2\14\uffff\1\1",
            "",
            "\3\4\2\uffff\1\3",
            "\1\6\17\uffff\1\5",
            "",
            "",
            "\3\4\2\uffff\1\3"
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
            return "189:1: ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedName ) ) otherlv_4= '.' otherlv_5= '*' ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildCard ) ) )";
        }
    }
    static final String DFA17_eotS =
        "\13\uffff";
    static final String DFA17_eofS =
        "\1\1\12\uffff";
    static final String DFA17_minS =
        "\1\4\1\uffff\10\0\1\uffff";
    static final String DFA17_maxS =
        "\1\117\1\uffff\10\0\1\uffff";
    static final String DFA17_acceptS =
        "\1\uffff\1\2\10\uffff\1\1";
    static final String DFA17_specialS =
        "\2\uffff\1\7\1\6\1\4\1\3\1\5\1\2\1\1\1\0\1\uffff}>";
    static final String[] DFA17_transitionS = {
            "\5\1\5\uffff\1\1\4\uffff\4\1\2\uffff\1\1\1\uffff\11\1\1\2\1"+
            "\3\1\4\1\5\1\6\1\7\1\10\1\11\15\1\1\uffff\13\1\1\uffff\12\1",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            ""
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "()* loopback of 1201:1: ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_9 = input.LA(1);

                         
                        int index17_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_9);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA17_8 = input.LA(1);

                         
                        int index17_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_8);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA17_7 = input.LA(1);

                         
                        int index17_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_7);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA17_5 = input.LA(1);

                         
                        int index17_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA17_4 = input.LA(1);

                         
                        int index17_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA17_6 = input.LA(1);

                         
                        int index17_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA17_3 = input.LA(1);

                         
                        int index17_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_3);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA17_2 = input.LA(1);

                         
                        int index17_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalKDiagram()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index17_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA33_eotS =
        "\101\uffff";
    static final String DFA33_eofS =
        "\1\2\100\uffff";
    static final String DFA33_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA33_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA33_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA33_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA33_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\33\2\1\1\2"+
            "\2\1\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "1977:2: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_16_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )? otherlv_21= ')' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA33_1 = input.LA(1);

                         
                        int index33_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred15_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index33_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 33, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA32_eotS =
        "\36\uffff";
    static final String DFA32_eofS =
        "\36\uffff";
    static final String DFA32_minS =
        "\1\4\2\0\33\uffff";
    static final String DFA32_maxS =
        "\1\115\2\0\33\uffff";
    static final String DFA32_acceptS =
        "\3\uffff\2\1\1\2\27\uffff\1\3";
    static final String DFA32_specialS =
        "\1\0\1\1\1\2\33\uffff}>";
    static final String[] DFA32_transitionS = {
            "\1\1\4\5\14\uffff\1\5\3\uffff\1\5\13\uffff\1\5\2\uffff\1\3\3"+
            "\uffff\2\5\3\uffff\1\5\4\uffff\1\2\1\35\1\5\1\4\1\uffff\1\5"+
            "\1\uffff\1\5\2\uffff\2\5\2\uffff\1\5\1\uffff\10\5",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA32_eot = DFA.unpackEncodedString(DFA32_eotS);
    static final short[] DFA32_eof = DFA.unpackEncodedString(DFA32_eofS);
    static final char[] DFA32_min = DFA.unpackEncodedStringToUnsignedChars(DFA32_minS);
    static final char[] DFA32_max = DFA.unpackEncodedStringToUnsignedChars(DFA32_maxS);
    static final short[] DFA32_accept = DFA.unpackEncodedString(DFA32_acceptS);
    static final short[] DFA32_special = DFA.unpackEncodedString(DFA32_specialS);
    static final short[][] DFA32_transition;

    static {
        int numStates = DFA32_transitionS.length;
        DFA32_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA32_transition[i] = DFA.unpackEncodedString(DFA32_transitionS[i]);
        }
    }

    class DFA32 extends DFA {

        public DFA32(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 32;
            this.eot = DFA32_eot;
            this.eof = DFA32_eof;
            this.min = DFA32_min;
            this.max = DFA32_max;
            this.accept = DFA32_accept;
            this.special = DFA32_special;
            this.transition = DFA32_transition;
        }
        public String getDescription() {
            return "1998:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_17_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_18_0= ruleXExpression ) ) (otherlv_19= ',' ( (lv_memberCallArguments_20_0= ruleXExpression ) ) )* ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA32_0 = input.LA(1);

                         
                        int index32_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA32_0==RULE_ID) ) {s = 1;}

                        else if ( (LA32_0==54) ) {s = 2;}

                        else if ( (LA32_0==40) && (synpred16_InternalKDiagram())) {s = 3;}

                        else if ( (LA32_0==57) && (synpred16_InternalKDiagram())) {s = 4;}

                        else if ( ((LA32_0>=RULE_STRING && LA32_0<=RULE_DECIMAL)||LA32_0==21||LA32_0==25||LA32_0==37||(LA32_0>=44 && LA32_0<=45)||LA32_0==49||LA32_0==56||LA32_0==59||LA32_0==61||(LA32_0>=64 && LA32_0<=65)||LA32_0==68||(LA32_0>=70 && LA32_0<=77)) ) {s = 5;}

                        else if ( (LA32_0==55) ) {s = 29;}

                         
                        input.seek(index32_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA32_1 = input.LA(1);

                         
                        int index32_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index32_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA32_2 = input.LA(1);

                         
                        int index32_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index32_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 32, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA34_eotS =
        "\101\uffff";
    static final String DFA34_eofS =
        "\1\2\100\uffff";
    static final String DFA34_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA34_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA34_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA34_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA34_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\35\2\1\1\1"+
            "\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
    static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
    static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
    static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
    static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
    static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
    static final short[][] DFA34_transition;

    static {
        int numStates = DFA34_transitionS.length;
        DFA34_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
        }
    }

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = DFA34_eot;
            this.eof = DFA34_eof;
            this.min = DFA34_min;
            this.max = DFA34_max;
            this.accept = DFA34_accept;
            this.special = DFA34_special;
            this.transition = DFA34_transition;
        }
        public String getDescription() {
            return "2077:3: ( ( ( () '[' ) )=> (lv_memberCallArguments_22_0= ruleXClosure ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA34_1 = input.LA(1);

                         
                        int index34_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index34_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 34, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA36_eotS =
        "\16\uffff";
    static final String DFA36_eofS =
        "\16\uffff";
    static final String DFA36_minS =
        "\1\4\15\uffff";
    static final String DFA36_maxS =
        "\1\115\15\uffff";
    static final String DFA36_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15";
    static final String DFA36_specialS =
        "\16\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\4\4\5\14\uffff\1\7\3\uffff\1\2\13\uffff\1\4\20\uffff\1\15"+
            "\1\uffff\1\5\2\uffff\1\6\1\uffff\1\3\2\uffff\1\10\1\11\2\uffff"+
            "\1\4\1\uffff\1\1\4\5\1\12\1\13\1\14",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "2118:1: (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | this_XFeatureCall_3= ruleXFeatureCall | this_XLiteral_4= ruleXLiteral | this_XIfExpression_5= ruleXIfExpression | this_XForLoopExpression_6= ruleXForLoopExpression | this_XWhileExpression_7= ruleXWhileExpression | this_XDoWhileExpression_8= ruleXDoWhileExpression | this_XThrowExpression_9= ruleXThrowExpression | this_XReturnExpression_10= ruleXReturnExpression | this_XTryCatchFinallyExpression_11= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_12= ruleXParenthesizedExpression )";
        }
    }
    static final String DFA40_eotS =
        "\40\uffff";
    static final String DFA40_eofS =
        "\40\uffff";
    static final String DFA40_minS =
        "\1\4\2\0\35\uffff";
    static final String DFA40_maxS =
        "\1\115\2\0\35\uffff";
    static final String DFA40_acceptS =
        "\3\uffff\2\1\1\2\32\uffff";
    static final String DFA40_specialS =
        "\1\0\1\1\1\2\35\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\1\4\5\14\uffff\1\5\3\uffff\1\5\13\uffff\1\5\2\uffff\1\3\3"+
            "\uffff\2\5\3\uffff\1\5\4\uffff\1\2\1\uffff\1\5\1\4\2\5\1\uffff"+
            "\1\5\2\uffff\5\5\1\uffff\10\5",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "2362:3: ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA40_0 = input.LA(1);

                         
                        int index40_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA40_0==RULE_ID) ) {s = 1;}

                        else if ( (LA40_0==54) ) {s = 2;}

                        else if ( (LA40_0==40) && (synpred20_InternalKDiagram())) {s = 3;}

                        else if ( (LA40_0==57) && (synpred20_InternalKDiagram())) {s = 4;}

                        else if ( ((LA40_0>=RULE_STRING && LA40_0<=RULE_DECIMAL)||LA40_0==21||LA40_0==25||LA40_0==37||(LA40_0>=44 && LA40_0<=45)||LA40_0==49||LA40_0==56||(LA40_0>=58 && LA40_0<=59)||LA40_0==61||(LA40_0>=64 && LA40_0<=68)||(LA40_0>=70 && LA40_0<=77)) ) {s = 5;}

                         
                        input.seek(index40_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA40_1 = input.LA(1);

                         
                        int index40_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index40_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA40_2 = input.LA(1);

                         
                        int index40_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index40_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 40, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA64_eotS =
        "\101\uffff";
    static final String DFA64_eofS =
        "\1\2\100\uffff";
    static final String DFA64_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA64_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA64_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA64_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA64_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\33\2\1\1\2"+
            "\2\1\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA64_eot = DFA.unpackEncodedString(DFA64_eotS);
    static final short[] DFA64_eof = DFA.unpackEncodedString(DFA64_eofS);
    static final char[] DFA64_min = DFA.unpackEncodedStringToUnsignedChars(DFA64_minS);
    static final char[] DFA64_max = DFA.unpackEncodedStringToUnsignedChars(DFA64_maxS);
    static final short[] DFA64_accept = DFA.unpackEncodedString(DFA64_acceptS);
    static final short[] DFA64_special = DFA.unpackEncodedString(DFA64_specialS);
    static final short[][] DFA64_transition;

    static {
        int numStates = DFA64_transitionS.length;
        DFA64_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA64_transition[i] = DFA.unpackEncodedString(DFA64_transitionS[i]);
        }
    }

    class DFA64 extends DFA {

        public DFA64(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 64;
            this.eot = DFA64_eot;
            this.eof = DFA64_eof;
            this.min = DFA64_min;
            this.max = DFA64_max;
            this.accept = DFA64_accept;
            this.special = DFA64_special;
            this.transition = DFA64_transition;
        }
        public String getDescription() {
            return "3720:2: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA64_1 = input.LA(1);

                         
                        int index64_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index64_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 64, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA63_eotS =
        "\36\uffff";
    static final String DFA63_eofS =
        "\36\uffff";
    static final String DFA63_minS =
        "\1\4\2\0\33\uffff";
    static final String DFA63_maxS =
        "\1\115\2\0\33\uffff";
    static final String DFA63_acceptS =
        "\3\uffff\2\1\1\2\27\uffff\1\3";
    static final String DFA63_specialS =
        "\1\0\1\1\1\2\33\uffff}>";
    static final String[] DFA63_transitionS = {
            "\1\1\4\5\14\uffff\1\5\3\uffff\1\5\13\uffff\1\5\2\uffff\1\3\3"+
            "\uffff\2\5\3\uffff\1\5\4\uffff\1\2\1\35\1\5\1\4\1\uffff\1\5"+
            "\1\uffff\1\5\2\uffff\2\5\2\uffff\1\5\1\uffff\10\5",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA63_eot = DFA.unpackEncodedString(DFA63_eotS);
    static final short[] DFA63_eof = DFA.unpackEncodedString(DFA63_eofS);
    static final char[] DFA63_min = DFA.unpackEncodedStringToUnsignedChars(DFA63_minS);
    static final char[] DFA63_max = DFA.unpackEncodedStringToUnsignedChars(DFA63_maxS);
    static final short[] DFA63_accept = DFA.unpackEncodedString(DFA63_acceptS);
    static final short[] DFA63_special = DFA.unpackEncodedString(DFA63_specialS);
    static final short[][] DFA63_transition;

    static {
        int numStates = DFA63_transitionS.length;
        DFA63_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA63_transition[i] = DFA.unpackEncodedString(DFA63_transitionS[i]);
        }
    }

    class DFA63 extends DFA {

        public DFA63(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 63;
            this.eot = DFA63_eot;
            this.eof = DFA63_eof;
            this.min = DFA63_min;
            this.max = DFA63_max;
            this.accept = DFA63_accept;
            this.special = DFA63_special;
            this.transition = DFA63_transition;
        }
        public String getDescription() {
            return "3741:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_9_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_featureCallArguments_12_0= ruleXExpression ) ) )* ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA63_0 = input.LA(1);

                         
                        int index63_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA63_0==RULE_ID) ) {s = 1;}

                        else if ( (LA63_0==54) ) {s = 2;}

                        else if ( (LA63_0==40) && (synpred27_InternalKDiagram())) {s = 3;}

                        else if ( (LA63_0==57) && (synpred27_InternalKDiagram())) {s = 4;}

                        else if ( ((LA63_0>=RULE_STRING && LA63_0<=RULE_DECIMAL)||LA63_0==21||LA63_0==25||LA63_0==37||(LA63_0>=44 && LA63_0<=45)||LA63_0==49||LA63_0==56||LA63_0==59||LA63_0==61||(LA63_0>=64 && LA63_0<=65)||LA63_0==68||(LA63_0>=70 && LA63_0<=77)) ) {s = 5;}

                        else if ( (LA63_0==55) ) {s = 29;}

                         
                        input.seek(index63_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA63_1 = input.LA(1);

                         
                        int index63_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index63_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA63_2 = input.LA(1);

                         
                        int index63_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index63_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 63, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA65_eotS =
        "\101\uffff";
    static final String DFA65_eofS =
        "\1\2\100\uffff";
    static final String DFA65_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA65_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA65_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA65_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA65_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\35\2\1\1\1"+
            "\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA65_eot = DFA.unpackEncodedString(DFA65_eotS);
    static final short[] DFA65_eof = DFA.unpackEncodedString(DFA65_eofS);
    static final char[] DFA65_min = DFA.unpackEncodedStringToUnsignedChars(DFA65_minS);
    static final char[] DFA65_max = DFA.unpackEncodedStringToUnsignedChars(DFA65_maxS);
    static final short[] DFA65_accept = DFA.unpackEncodedString(DFA65_acceptS);
    static final short[] DFA65_special = DFA.unpackEncodedString(DFA65_specialS);
    static final short[][] DFA65_transition;

    static {
        int numStates = DFA65_transitionS.length;
        DFA65_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA65_transition[i] = DFA.unpackEncodedString(DFA65_transitionS[i]);
        }
    }

    class DFA65 extends DFA {

        public DFA65(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 65;
            this.eot = DFA65_eot;
            this.eof = DFA65_eof;
            this.min = DFA65_min;
            this.max = DFA65_max;
            this.accept = DFA65_accept;
            this.special = DFA65_special;
            this.transition = DFA65_transition;
        }
        public String getDescription() {
            return "3820:3: ( ( ( () '[' ) )=> (lv_featureCallArguments_14_0= ruleXClosure ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA65_1 = input.LA(1);

                         
                        int index65_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index65_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 65, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA69_eotS =
        "\101\uffff";
    static final String DFA69_eofS =
        "\1\2\100\uffff";
    static final String DFA69_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA69_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA69_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA69_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA69_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\12\2\1\1\23"+
            "\2\1\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA69_eot = DFA.unpackEncodedString(DFA69_eotS);
    static final short[] DFA69_eof = DFA.unpackEncodedString(DFA69_eofS);
    static final char[] DFA69_min = DFA.unpackEncodedStringToUnsignedChars(DFA69_minS);
    static final char[] DFA69_max = DFA.unpackEncodedStringToUnsignedChars(DFA69_maxS);
    static final short[] DFA69_accept = DFA.unpackEncodedString(DFA69_acceptS);
    static final short[] DFA69_special = DFA.unpackEncodedString(DFA69_specialS);
    static final short[][] DFA69_transition;

    static {
        int numStates = DFA69_transitionS.length;
        DFA69_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA69_transition[i] = DFA.unpackEncodedString(DFA69_transitionS[i]);
        }
    }

    class DFA69 extends DFA {

        public DFA69(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 69;
            this.eot = DFA69_eot;
            this.eof = DFA69_eof;
            this.min = DFA69_min;
            this.max = DFA69_max;
            this.accept = DFA69_accept;
            this.special = DFA69_special;
            this.transition = DFA69_transition;
        }
        public String getDescription() {
            return "3963:2: ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA69_1 = input.LA(1);

                         
                        int index69_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred29_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index69_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 69, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA72_eotS =
        "\101\uffff";
    static final String DFA72_eofS =
        "\1\2\100\uffff";
    static final String DFA72_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA72_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA72_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA72_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA72_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\33\2\1\1\2"+
            "\2\1\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA72_eot = DFA.unpackEncodedString(DFA72_eotS);
    static final short[] DFA72_eof = DFA.unpackEncodedString(DFA72_eofS);
    static final char[] DFA72_min = DFA.unpackEncodedStringToUnsignedChars(DFA72_minS);
    static final char[] DFA72_max = DFA.unpackEncodedStringToUnsignedChars(DFA72_maxS);
    static final short[] DFA72_accept = DFA.unpackEncodedString(DFA72_acceptS);
    static final short[] DFA72_special = DFA.unpackEncodedString(DFA72_specialS);
    static final short[][] DFA72_transition;

    static {
        int numStates = DFA72_transitionS.length;
        DFA72_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA72_transition[i] = DFA.unpackEncodedString(DFA72_transitionS[i]);
        }
    }

    class DFA72 extends DFA {

        public DFA72(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 72;
            this.eot = DFA72_eot;
            this.eof = DFA72_eof;
            this.min = DFA72_min;
            this.max = DFA72_max;
            this.accept = DFA72_accept;
            this.special = DFA72_special;
            this.transition = DFA72_transition;
        }
        public String getDescription() {
            return "4012:3: ( ( ( '(' )=>otherlv_8= '(' ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA72_1 = input.LA(1);

                         
                        int index72_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index72_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 72, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA71_eotS =
        "\36\uffff";
    static final String DFA71_eofS =
        "\36\uffff";
    static final String DFA71_minS =
        "\1\4\2\0\33\uffff";
    static final String DFA71_maxS =
        "\1\115\2\0\33\uffff";
    static final String DFA71_acceptS =
        "\3\uffff\2\1\1\2\27\uffff\1\3";
    static final String DFA71_specialS =
        "\1\0\1\1\1\2\33\uffff}>";
    static final String[] DFA71_transitionS = {
            "\1\1\4\5\14\uffff\1\5\3\uffff\1\5\13\uffff\1\5\2\uffff\1\3\3"+
            "\uffff\2\5\3\uffff\1\5\4\uffff\1\2\1\35\1\5\1\4\1\uffff\1\5"+
            "\1\uffff\1\5\2\uffff\2\5\2\uffff\1\5\1\uffff\10\5",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "4017:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA71_0 = input.LA(1);

                         
                        int index71_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA71_0==RULE_ID) ) {s = 1;}

                        else if ( (LA71_0==54) ) {s = 2;}

                        else if ( (LA71_0==40) && (synpred31_InternalKDiagram())) {s = 3;}

                        else if ( (LA71_0==57) && (synpred31_InternalKDiagram())) {s = 4;}

                        else if ( ((LA71_0>=RULE_STRING && LA71_0<=RULE_DECIMAL)||LA71_0==21||LA71_0==25||LA71_0==37||(LA71_0>=44 && LA71_0<=45)||LA71_0==49||LA71_0==56||LA71_0==59||LA71_0==61||(LA71_0>=64 && LA71_0<=65)||LA71_0==68||(LA71_0>=70 && LA71_0<=77)) ) {s = 5;}

                        else if ( (LA71_0==55) ) {s = 29;}

                         
                        input.seek(index71_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA71_1 = input.LA(1);

                         
                        int index71_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred31_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index71_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA71_2 = input.LA(1);

                         
                        int index71_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred31_InternalKDiagram()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index71_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 71, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA73_eotS =
        "\101\uffff";
    static final String DFA73_eofS =
        "\1\2\100\uffff";
    static final String DFA73_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA73_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA73_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA73_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA73_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\35\2\1\1\1"+
            "\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA73_eot = DFA.unpackEncodedString(DFA73_eotS);
    static final short[] DFA73_eof = DFA.unpackEncodedString(DFA73_eofS);
    static final char[] DFA73_min = DFA.unpackEncodedStringToUnsignedChars(DFA73_minS);
    static final char[] DFA73_max = DFA.unpackEncodedStringToUnsignedChars(DFA73_maxS);
    static final short[] DFA73_accept = DFA.unpackEncodedString(DFA73_acceptS);
    static final short[] DFA73_special = DFA.unpackEncodedString(DFA73_specialS);
    static final short[][] DFA73_transition;

    static {
        int numStates = DFA73_transitionS.length;
        DFA73_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA73_transition[i] = DFA.unpackEncodedString(DFA73_transitionS[i]);
        }
    }

    class DFA73 extends DFA {

        public DFA73(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 73;
            this.eot = DFA73_eot;
            this.eof = DFA73_eof;
            this.min = DFA73_min;
            this.max = DFA73_max;
            this.accept = DFA73_accept;
            this.special = DFA73_special;
            this.transition = DFA73_transition;
        }
        public String getDescription() {
            return "4096:3: ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA73_1 = input.LA(1);

                         
                        int index73_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred32_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index73_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 73, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA75_eotS =
        "\101\uffff";
    static final String DFA75_eofS =
        "\1\33\100\uffff";
    static final String DFA75_minS =
        "\1\4\32\0\46\uffff";
    static final String DFA75_maxS =
        "\1\117\32\0\46\uffff";
    static final String DFA75_acceptS =
        "\33\uffff\1\2\44\uffff\1\1";
    static final String DFA75_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\46\uffff}>";
    static final String[] DFA75_transitionS = {
            "\1\1\1\21\1\15\1\16\1\17\5\uffff\1\33\4\uffff\2\33\1\24\1\33"+
            "\2\uffff\1\6\1\uffff\12\33\1\10\6\33\1\4\1\3\3\33\1\2\4\33\1"+
            "\32\1\33\1\12\1\uffff\1\33\1\23\1\33\1\7\2\33\1\25\1\26\2\33"+
            "\1\11\1\uffff\1\5\1\13\1\14\1\20\1\22\1\27\1\30\1\31\2\33",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA75_eot = DFA.unpackEncodedString(DFA75_eotS);
    static final short[] DFA75_eof = DFA.unpackEncodedString(DFA75_eofS);
    static final char[] DFA75_min = DFA.unpackEncodedStringToUnsignedChars(DFA75_minS);
    static final char[] DFA75_max = DFA.unpackEncodedStringToUnsignedChars(DFA75_maxS);
    static final short[] DFA75_accept = DFA.unpackEncodedString(DFA75_acceptS);
    static final short[] DFA75_special = DFA.unpackEncodedString(DFA75_specialS);
    static final short[][] DFA75_transition;

    static {
        int numStates = DFA75_transitionS.length;
        DFA75_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA75_transition[i] = DFA.unpackEncodedString(DFA75_transitionS[i]);
        }
    }

    class DFA75 extends DFA {

        public DFA75(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 75;
            this.eot = DFA75_eot;
            this.eof = DFA75_eof;
            this.min = DFA75_min;
            this.max = DFA75_max;
            this.accept = DFA75_accept;
            this.special = DFA75_special;
            this.transition = DFA75_transition;
        }
        public String getDescription() {
            return "4418:1: ( ( ( ruleXExpression ) )=> (lv_expression_2_0= ruleXExpression ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA75_1 = input.LA(1);

                         
                        int index75_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA75_2 = input.LA(1);

                         
                        int index75_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA75_3 = input.LA(1);

                         
                        int index75_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA75_4 = input.LA(1);

                         
                        int index75_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA75_5 = input.LA(1);

                         
                        int index75_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA75_6 = input.LA(1);

                         
                        int index75_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA75_7 = input.LA(1);

                         
                        int index75_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA75_8 = input.LA(1);

                         
                        int index75_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA75_9 = input.LA(1);

                         
                        int index75_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA75_10 = input.LA(1);

                         
                        int index75_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA75_11 = input.LA(1);

                         
                        int index75_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA75_12 = input.LA(1);

                         
                        int index75_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA75_13 = input.LA(1);

                         
                        int index75_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA75_14 = input.LA(1);

                         
                        int index75_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA75_15 = input.LA(1);

                         
                        int index75_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA75_16 = input.LA(1);

                         
                        int index75_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA75_17 = input.LA(1);

                         
                        int index75_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_17);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA75_18 = input.LA(1);

                         
                        int index75_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_18);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA75_19 = input.LA(1);

                         
                        int index75_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_19);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA75_20 = input.LA(1);

                         
                        int index75_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_20);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA75_21 = input.LA(1);

                         
                        int index75_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_21);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA75_22 = input.LA(1);

                         
                        int index75_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_22);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA75_23 = input.LA(1);

                         
                        int index75_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_23);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA75_24 = input.LA(1);

                         
                        int index75_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_24);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA75_25 = input.LA(1);

                         
                        int index75_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_25);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA75_26 = input.LA(1);

                         
                        int index75_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 27;}

                         
                        input.seek(index75_26);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 75, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA90_eotS =
        "\101\uffff";
    static final String DFA90_eofS =
        "\1\2\100\uffff";
    static final String DFA90_minS =
        "\1\4\1\0\77\uffff";
    static final String DFA90_maxS =
        "\1\117\1\0\77\uffff";
    static final String DFA90_acceptS =
        "\2\uffff\1\2\75\uffff\1\1";
    static final String DFA90_specialS =
        "\1\uffff\1\0\77\uffff}>";
    static final String[] DFA90_transitionS = {
            "\5\2\5\uffff\1\2\4\uffff\4\2\2\uffff\1\2\1\uffff\12\2\1\1\23"+
            "\2\1\uffff\13\2\1\uffff\12\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA90_eot = DFA.unpackEncodedString(DFA90_eotS);
    static final short[] DFA90_eof = DFA.unpackEncodedString(DFA90_eofS);
    static final char[] DFA90_min = DFA.unpackEncodedStringToUnsignedChars(DFA90_minS);
    static final char[] DFA90_max = DFA.unpackEncodedStringToUnsignedChars(DFA90_maxS);
    static final short[] DFA90_accept = DFA.unpackEncodedString(DFA90_acceptS);
    static final short[] DFA90_special = DFA.unpackEncodedString(DFA90_specialS);
    static final short[][] DFA90_transition;

    static {
        int numStates = DFA90_transitionS.length;
        DFA90_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA90_transition[i] = DFA.unpackEncodedString(DFA90_transitionS[i]);
        }
    }

    class DFA90 extends DFA {

        public DFA90(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 90;
            this.eot = DFA90_eot;
            this.eof = DFA90_eof;
            this.min = DFA90_min;
            this.max = DFA90_max;
            this.accept = DFA90_accept;
            this.special = DFA90_special;
            this.transition = DFA90_transition;
        }
        public String getDescription() {
            return "4934:2: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA90_1 = input.LA(1);

                         
                        int index90_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred39_InternalKDiagram()) ) {s = 64;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index90_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 90, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleDiagramSynthesis_in_entryRuleDiagramSynthesis75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDiagramSynthesis85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleDiagramSynthesis123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleDiagramSynthesis144 = new BitSet(new long[]{0x000000000001C000L});
    public static final BitSet FOLLOW_14_in_ruleDiagramSynthesis157 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_ruleImport_in_ruleDiagramSynthesis182 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_15_in_ruleDiagramSynthesis195 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleDiagramSynthesis216 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_ruleMappingDefinition_in_ruleDiagramSynthesis237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport273 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleImport320 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_17_in_ruleImport340 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_18_in_ruleImport371 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleImport408 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleImport420 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleImport432 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleImport462 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithWildCard_in_ruleImport489 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_ruleImport503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithWildCard_in_entryRuleQualifiedNameWithWildCard542 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedNameWithWildCard553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleQualifiedNameWithWildCard600 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleQualifiedNameWithWildCard618 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleQualifiedNameWithWildCard631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMappingDefinition_in_entryRuleMappingDefinition671 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMappingDefinition681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleMappingDefinition718 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleJvmParameterizedTypeReference_in_ruleMappingDefinition739 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleMappingDefinition760 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_22_in_ruleMappingDefinition773 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_ruleNodeMapping_in_ruleMappingDefinition796 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_ruleNodeMapping_in_entryRuleNodeMapping833 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNodeMapping843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleNodeMapping880 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNodeMapping897 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleNodeMapping914 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleNodeMapping935 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleNodeMapping947 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleNodeMapping959 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleNodeMapping982 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleNodeMapping994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_entryRuleXExpression1032 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpression1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXAssignment_in_ruleXExpression1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXAssignment_in_entryRuleXAssignment1122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXAssignment1132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleXAssignment1190 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleOpSingleAssign_in_ruleXAssignment1206 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXAssignment_in_ruleXAssignment1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOrExpression_in_ruleXAssignment1256 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ruleOpMultiAssign_in_ruleXAssignment1309 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXAssignment_in_ruleXAssignment1332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpSingleAssign_in_entryRuleOpSingleAssign1372 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpSingleAssign1383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleOpSingleAssign1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpMultiAssign_in_entryRuleOpMultiAssign1460 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpMultiAssign1471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleOpMultiAssign1508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOrExpression_in_entryRuleXOrExpression1547 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOrExpression1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXAndExpression_in_ruleXOrExpression1604 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ruleOpOr_in_ruleXOrExpression1657 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXAndExpression_in_ruleXOrExpression1680 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ruleOpOr_in_entryRuleOpOr1719 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpOr1730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleOpOr1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXAndExpression_in_entryRuleXAndExpression1806 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXAndExpression1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXEqualityExpression_in_ruleXAndExpression1863 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ruleOpAnd_in_ruleXAndExpression1916 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXEqualityExpression_in_ruleXAndExpression1939 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ruleOpAnd_in_entryRuleOpAnd1978 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpAnd1989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleOpAnd2026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXEqualityExpression_in_entryRuleXEqualityExpression2065 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXEqualityExpression2075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXRelationalExpression_in_ruleXEqualityExpression2122 = new BitSet(new long[]{0x0000000180000002L});
    public static final BitSet FOLLOW_ruleOpEquality_in_ruleXEqualityExpression2175 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXRelationalExpression_in_ruleXEqualityExpression2198 = new BitSet(new long[]{0x0000000180000002L});
    public static final BitSet FOLLOW_ruleOpEquality_in_entryRuleOpEquality2237 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpEquality2248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleOpEquality2286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleOpEquality2305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXRelationalExpression_in_entryRuleXRelationalExpression2345 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXRelationalExpression2355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOtherOperatorExpression_in_ruleXRelationalExpression2402 = new BitSet(new long[]{0x0000003E00000002L});
    public static final BitSet FOLLOW_33_in_ruleXRelationalExpression2438 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleXRelationalExpression2461 = new BitSet(new long[]{0x0000003E00000002L});
    public static final BitSet FOLLOW_ruleOpCompare_in_ruleXRelationalExpression2522 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXOtherOperatorExpression_in_ruleXRelationalExpression2545 = new BitSet(new long[]{0x0000003E00000002L});
    public static final BitSet FOLLOW_ruleOpCompare_in_entryRuleOpCompare2585 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpCompare2596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleOpCompare2634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleOpCompare2653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleOpCompare2672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleOpCompare2691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXOtherOperatorExpression_in_entryRuleXOtherOperatorExpression2731 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXOtherOperatorExpression2741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXAdditiveExpression_in_ruleXOtherOperatorExpression2788 = new BitSet(new long[]{0x00000FF000000002L});
    public static final BitSet FOLLOW_ruleOpOther_in_ruleXOtherOperatorExpression2841 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXAdditiveExpression_in_ruleXOtherOperatorExpression2864 = new BitSet(new long[]{0x00000FF000000002L});
    public static final BitSet FOLLOW_ruleOpOther_in_entryRuleOpOther2903 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpOther2914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleOpOther2952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleOpOther2971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleOpOther2990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleOpOther3010 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleOpOther3041 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleOpOther3054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleOpOther3075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleOpOther3097 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_ruleOpOther3128 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_ruleOpOther3141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleOpOther3162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleOpOther3183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleOpOther3202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleOpOther3221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXAdditiveExpression_in_entryRuleXAdditiveExpression3261 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXAdditiveExpression3271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXMultiplicativeExpression_in_ruleXAdditiveExpression3318 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_ruleOpAdd_in_ruleXAdditiveExpression3371 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXMultiplicativeExpression_in_ruleXAdditiveExpression3394 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_ruleOpAdd_in_entryRuleOpAdd3433 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpAdd3444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleOpAdd3482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleOpAdd3501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXMultiplicativeExpression_in_entryRuleXMultiplicativeExpression3541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXMultiplicativeExpression3551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXUnaryOperation_in_ruleXMultiplicativeExpression3598 = new BitSet(new long[]{0x0001C00000100002L});
    public static final BitSet FOLLOW_ruleOpMulti_in_ruleXMultiplicativeExpression3651 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXUnaryOperation_in_ruleXMultiplicativeExpression3674 = new BitSet(new long[]{0x0001C00000100002L});
    public static final BitSet FOLLOW_ruleOpMulti_in_entryRuleOpMulti3713 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpMulti3724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleOpMulti3762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleOpMulti3781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleOpMulti3800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleOpMulti3819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXUnaryOperation_in_entryRuleXUnaryOperation3859 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXUnaryOperation3869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpUnary_in_ruleXUnaryOperation3927 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXCastedExpression_in_ruleXUnaryOperation3948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXCastedExpression_in_ruleXUnaryOperation3977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpUnary_in_entryRuleOpUnary4013 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOpUnary4024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleOpUnary4062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleOpUnary4081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleOpUnary4100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXCastedExpression_in_entryRuleXCastedExpression4140 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXCastedExpression4150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXMemberFeatureCall_in_ruleXCastedExpression4197 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50_in_ruleXCastedExpression4232 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleXCastedExpression4255 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ruleXMemberFeatureCall_in_entryRuleXMemberFeatureCall4293 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXMemberFeatureCall4303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXPrimaryExpression_in_ruleXMemberFeatureCall4350 = new BitSet(new long[]{0x0018000000080002L});
    public static final BitSet FOLLOW_19_in_ruleXMemberFeatureCall4399 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleXMemberFeatureCall4422 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleOpSingleAssign_in_ruleXMemberFeatureCall4438 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXAssignment_in_ruleXMemberFeatureCall4460 = new BitSet(new long[]{0x0018000000080002L});
    public static final BitSet FOLLOW_19_in_ruleXMemberFeatureCall4546 = new BitSet(new long[]{0x0000002000000010L});
    public static final BitSet FOLLOW_51_in_ruleXMemberFeatureCall4570 = new BitSet(new long[]{0x0000002000000010L});
    public static final BitSet FOLLOW_52_in_ruleXMemberFeatureCall4607 = new BitSet(new long[]{0x0000002000000010L});
    public static final BitSet FOLLOW_37_in_ruleXMemberFeatureCall4636 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleXMemberFeatureCall4657 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_53_in_ruleXMemberFeatureCall4670 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleXMemberFeatureCall4691 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_36_in_ruleXMemberFeatureCall4705 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleXMemberFeatureCall4730 = new BitSet(new long[]{0x0158000000080002L});
    public static final BitSet FOLLOW_54_in_ruleXMemberFeatureCall4764 = new BitSet(new long[]{0x2BC23120022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXShortClosure_in_ruleXMemberFeatureCall4849 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXMemberFeatureCall4877 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_53_in_ruleXMemberFeatureCall4890 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXMemberFeatureCall4911 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXMemberFeatureCall4928 = new BitSet(new long[]{0x0118000000080002L});
    public static final BitSet FOLLOW_ruleXClosure_in_ruleXMemberFeatureCall4963 = new BitSet(new long[]{0x0018000000080002L});
    public static final BitSet FOLLOW_ruleXPrimaryExpression_in_entryRuleXPrimaryExpression5003 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXPrimaryExpression5013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXConstructorCall_in_ruleXPrimaryExpression5060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXBlockExpression_in_ruleXPrimaryExpression5087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXSwitchExpression_in_ruleXPrimaryExpression5114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXFeatureCall_in_ruleXPrimaryExpression5141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXLiteral_in_ruleXPrimaryExpression5168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXIfExpression_in_ruleXPrimaryExpression5195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXForLoopExpression_in_ruleXPrimaryExpression5222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXWhileExpression_in_ruleXPrimaryExpression5249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXDoWhileExpression_in_ruleXPrimaryExpression5276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXThrowExpression_in_ruleXPrimaryExpression5303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXReturnExpression_in_ruleXPrimaryExpression5330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXTryCatchFinallyExpression_in_ruleXPrimaryExpression5357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXParenthesizedExpression_in_ruleXPrimaryExpression5384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXLiteral_in_entryRuleXLiteral5419 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXLiteral5429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXClosure_in_ruleXLiteral5489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXBooleanLiteral_in_ruleXLiteral5517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXNumberLiteral_in_ruleXLiteral5544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXNullLiteral_in_ruleXLiteral5571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXStringLiteral_in_ruleXLiteral5598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXTypeLiteral_in_ruleXLiteral5625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXClosure_in_entryRuleXClosure5660 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXClosure5670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleXClosure5730 = new BitSet(new long[]{0x2F423120022001F0L,0x0000000000003FDFL});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_ruleXClosure5803 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_53_in_ruleXClosure5816 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_ruleXClosure5837 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_57_in_ruleXClosure5859 = new BitSet(new long[]{0x2D423020022001F0L,0x0000000000003FDFL});
    public static final BitSet FOLLOW_ruleXExpressionInClosure_in_ruleXClosure5896 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleXClosure5908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionInClosure_in_entryRuleXExpressionInClosure5944 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionInClosure5954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionInsideBlock_in_ruleXExpressionInClosure6010 = new BitSet(new long[]{0x29423020022041F2L,0x0000000000003FDFL});
    public static final BitSet FOLLOW_14_in_ruleXExpressionInClosure6023 = new BitSet(new long[]{0x29423020022001F2L,0x0000000000003FDFL});
    public static final BitSet FOLLOW_ruleXShortClosure_in_entryRuleXShortClosure6063 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXShortClosure6073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_ruleXShortClosure6181 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_53_in_ruleXShortClosure6194 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_ruleXShortClosure6215 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_57_in_ruleXShortClosure6237 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXShortClosure6273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXParenthesizedExpression_in_entryRuleXParenthesizedExpression6309 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXParenthesizedExpression6319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleXParenthesizedExpression6356 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXParenthesizedExpression6378 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXParenthesizedExpression6389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXIfExpression_in_entryRuleXIfExpression6425 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXIfExpression6435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ruleXIfExpression6481 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ruleXIfExpression6493 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXIfExpression6514 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXIfExpression6526 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXIfExpression6547 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleXIfExpression6568 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXIfExpression6590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXSwitchExpression_in_entryRuleXSwitchExpression6628 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXSwitchExpression6638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleXSwitchExpression6684 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleXSwitchExpression6727 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleXSwitchExpression6739 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXSwitchExpression6763 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_54_in_ruleXSwitchExpression6807 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleXSwitchExpression6828 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleXSwitchExpression6840 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXSwitchExpression6863 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXSwitchExpression6875 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleXSwitchExpression6889 = new BitSet(new long[]{0x8040010000400010L});
    public static final BitSet FOLLOW_ruleXCasePart_in_ruleXSwitchExpression6910 = new BitSet(new long[]{0xC040010008400010L});
    public static final BitSet FOLLOW_62_in_ruleXSwitchExpression6924 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleXSwitchExpression6936 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXSwitchExpression6957 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleXSwitchExpression6971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXCasePart_in_entryRuleXCasePart7007 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXCasePart7017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleXCasePart7063 = new BitSet(new long[]{0x8000000000400000L});
    public static final BitSet FOLLOW_63_in_ruleXCasePart7077 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXCasePart7098 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleXCasePart7112 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXCasePart7133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXForLoopExpression_in_entryRuleXForLoopExpression7169 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXForLoopExpression7179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleXForLoopExpression7225 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ruleXForLoopExpression7237 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_ruleXForLoopExpression7258 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleXForLoopExpression7270 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXForLoopExpression7291 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXForLoopExpression7303 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXForLoopExpression7324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXWhileExpression_in_entryRuleXWhileExpression7360 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXWhileExpression7370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleXWhileExpression7416 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ruleXWhileExpression7428 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXWhileExpression7449 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXWhileExpression7461 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXWhileExpression7482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXDoWhileExpression_in_entryRuleXDoWhileExpression7518 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXDoWhileExpression7528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleXDoWhileExpression7574 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXDoWhileExpression7595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleXDoWhileExpression7607 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ruleXDoWhileExpression7619 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXDoWhileExpression7640 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXDoWhileExpression7652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXBlockExpression_in_entryRuleXBlockExpression7688 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXBlockExpression7698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleXBlockExpression7744 = new BitSet(new long[]{0x294230200A2001F0L,0x0000000000003FDFL});
    public static final BitSet FOLLOW_ruleXExpressionInsideBlock_in_ruleXBlockExpression7766 = new BitSet(new long[]{0x294230200A2041F0L,0x0000000000003FDFL});
    public static final BitSet FOLLOW_14_in_ruleXBlockExpression7779 = new BitSet(new long[]{0x294230200A2001F0L,0x0000000000003FDFL});
    public static final BitSet FOLLOW_27_in_ruleXBlockExpression7795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpressionInsideBlock_in_entryRuleXExpressionInsideBlock7831 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXExpressionInsideBlock7841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXVariableDeclaration_in_ruleXExpressionInsideBlock7888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXExpressionInsideBlock7915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXVariableDeclaration_in_entryRuleXVariableDeclaration7950 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXVariableDeclaration7960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_ruleXVariableDeclaration8013 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_67_in_ruleXVariableDeclaration8044 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleXVariableDeclaration8092 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleXVariableDeclaration8113 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleXVariableDeclaration8142 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleXVariableDeclaration8156 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXVariableDeclaration8177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_entryRuleJvmFormalParameter8215 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmFormalParameter8225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmFormalParameter8271 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleJvmFormalParameter8293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFullJvmFormalParameter_in_entryRuleFullJvmFormalParameter8329 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFullJvmFormalParameter8339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleFullJvmFormalParameter8385 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleFullJvmFormalParameter8406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXFeatureCall_in_entryRuleXFeatureCall8442 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXFeatureCall8452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStaticQualifier_in_ruleXFeatureCall8509 = new BitSet(new long[]{0x0000002000000010L,0x0000000000000010L});
    public static final BitSet FOLLOW_37_in_ruleXFeatureCall8523 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleXFeatureCall8544 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_53_in_ruleXFeatureCall8557 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleXFeatureCall8578 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_36_in_ruleXFeatureCall8592 = new BitSet(new long[]{0x0000002000000010L,0x0000000000000010L});
    public static final BitSet FOLLOW_ruleIdOrSuper_in_ruleXFeatureCall8617 = new BitSet(new long[]{0x0140000000000002L});
    public static final BitSet FOLLOW_54_in_ruleXFeatureCall8651 = new BitSet(new long[]{0x2BC23120022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXShortClosure_in_ruleXFeatureCall8736 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXFeatureCall8764 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_53_in_ruleXFeatureCall8777 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXFeatureCall8798 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXFeatureCall8815 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_ruleXClosure_in_ruleXFeatureCall8850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdOrSuper_in_entryRuleIdOrSuper8888 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdOrSuper8899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleIdOrSuper8946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleIdOrSuper8970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStaticQualifier_in_entryRuleStaticQualifier9011 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStaticQualifier9022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleStaticQualifier9069 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleStaticQualifier9087 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleXConstructorCall_in_entryRuleXConstructorCall9128 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXConstructorCall9138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_ruleXConstructorCall9184 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleXConstructorCall9207 = new BitSet(new long[]{0x0140002000000002L});
    public static final BitSet FOLLOW_37_in_ruleXConstructorCall9228 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleXConstructorCall9250 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_53_in_ruleXConstructorCall9263 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleXConstructorCall9284 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_36_in_ruleXConstructorCall9298 = new BitSet(new long[]{0x0140000000000002L});
    public static final BitSet FOLLOW_54_in_ruleXConstructorCall9321 = new BitSet(new long[]{0x2BC23120022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXShortClosure_in_ruleXConstructorCall9394 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXConstructorCall9422 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_53_in_ruleXConstructorCall9435 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXConstructorCall9456 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXConstructorCall9473 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_ruleXClosure_in_ruleXConstructorCall9508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXBooleanLiteral_in_entryRuleXBooleanLiteral9545 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXBooleanLiteral9555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_ruleXBooleanLiteral9602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleXBooleanLiteral9626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXNullLiteral_in_entryRuleXNullLiteral9676 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXNullLiteral9686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleXNullLiteral9732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXNumberLiteral_in_entryRuleXNumberLiteral9768 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXNumberLiteral9778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumber_in_ruleXNumberLiteral9833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXStringLiteral_in_entryRuleXStringLiteral9869 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXStringLiteral9879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleXStringLiteral9930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXTypeLiteral_in_entryRuleXTypeLiteral9971 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXTypeLiteral9981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleXTypeLiteral10027 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ruleXTypeLiteral10039 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleXTypeLiteral10062 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXTypeLiteral10074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXThrowExpression_in_entryRuleXThrowExpression10110 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXThrowExpression10120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleXThrowExpression10166 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXThrowExpression10187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXReturnExpression_in_entryRuleXReturnExpression10223 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXReturnExpression10233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleXReturnExpression10279 = new BitSet(new long[]{0x29423020022001F2L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXReturnExpression10310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXTryCatchFinallyExpression_in_entryRuleXTryCatchFinallyExpression10347 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXTryCatchFinallyExpression10357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleXTryCatchFinallyExpression10403 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXTryCatchFinallyExpression10424 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C000L});
    public static final BitSet FOLLOW_ruleXCatchClause_in_ruleXTryCatchFinallyExpression10454 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
    public static final BitSet FOLLOW_78_in_ruleXTryCatchFinallyExpression10476 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXTryCatchFinallyExpression10498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleXTryCatchFinallyExpression10520 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXTryCatchFinallyExpression10541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXCatchClause_in_entryRuleXCatchClause10579 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXCatchClause10589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleXCatchClause10634 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ruleXCatchClause10647 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleFullJvmFormalParameter_in_ruleXCatchClause10668 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXCatchClause10680 = new BitSet(new long[]{0x29423020022001F0L,0x0000000000003FD3L});
    public static final BitSet FOLLOW_ruleXExpression_in_ruleXCatchClause10701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName10738 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedName10749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleQualifiedName10796 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_ruleQualifiedName10824 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_ruleQualifiedName10847 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ruleNumber_in_entryRuleNumber10901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumber10912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_HEX_in_ruleNumber10956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleNumber10984 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_RULE_DECIMAL_in_ruleNumber11010 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_ruleNumber11030 = new BitSet(new long[]{0x0000000000000180L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleNumber11046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DECIMAL_in_ruleNumber11072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_entryRuleJvmTypeReference11125 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmTypeReference11135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmParameterizedTypeReference_in_ruleJvmTypeReference11183 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_56_in_ruleJvmTypeReference11221 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleJvmTypeReference11233 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_ruleXFunctionTypeRef_in_ruleJvmTypeReference11265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXFunctionTypeRef_in_entryRuleXFunctionTypeRef11300 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXFunctionTypeRef11310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleXFunctionTypeRef11348 = new BitSet(new long[]{0x00C0010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleXFunctionTypeRef11370 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_53_in_ruleXFunctionTypeRef11383 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleXFunctionTypeRef11404 = new BitSet(new long[]{0x00A0000000000000L});
    public static final BitSet FOLLOW_55_in_ruleXFunctionTypeRef11420 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleXFunctionTypeRef11434 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleXFunctionTypeRef11455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmParameterizedTypeReference_in_entryRuleJvmParameterizedTypeReference11491 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmParameterizedTypeReference11501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_ruleJvmParameterizedTypeReference11549 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_ruleJvmParameterizedTypeReference11570 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference11592 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_53_in_ruleJvmParameterizedTypeReference11605 = new BitSet(new long[]{0x0040010000000010L,0x0000000000010000L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_ruleJvmParameterizedTypeReference11626 = new BitSet(new long[]{0x0020001000000000L});
    public static final BitSet FOLLOW_36_in_ruleJvmParameterizedTypeReference11640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmArgumentTypeReference_in_entryRuleJvmArgumentTypeReference11678 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmArgumentTypeReference11688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmArgumentTypeReference11735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmWildcardTypeReference_in_ruleJvmArgumentTypeReference11762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmWildcardTypeReference_in_entryRuleJvmWildcardTypeReference11797 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmWildcardTypeReference11807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleJvmWildcardTypeReference11853 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020010L});
    public static final BitSet FOLLOW_ruleJvmUpperBound_in_ruleJvmWildcardTypeReference11875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmLowerBound_in_ruleJvmWildcardTypeReference11902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmUpperBound_in_entryRuleJvmUpperBound11940 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmUpperBound11950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleJvmUpperBound11987 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmUpperBound12008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmUpperBoundAnded_in_entryRuleJvmUpperBoundAnded12044 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmUpperBoundAnded12054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleJvmUpperBoundAnded12091 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmUpperBoundAnded12112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmLowerBound_in_entryRuleJvmLowerBound12148 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJvmLowerBound12158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleJvmLowerBound12195 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_ruleJvmLowerBound12216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_entryRuleValidID12255 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValidID12266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleValidID12305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpMultiAssign_in_synpred1_InternalKDiagram1277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpOr_in_synpred2_InternalKDiagram1625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpAnd_in_synpred3_InternalKDiagram1884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpEquality_in_synpred4_InternalKDiagram2143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_synpred5_InternalKDiagram2419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpCompare_in_synpred6_InternalKDiagram2490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpOther_in_synpred7_InternalKDiagram2809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_synpred8_InternalKDiagram3025 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_synpred8_InternalKDiagram3030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_synpred9_InternalKDiagram3112 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_synpred9_InternalKDiagram3117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpAdd_in_synpred10_InternalKDiagram3339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOpMulti_in_synpred11_InternalKDiagram3619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_synpred12_InternalKDiagram4213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_synpred13_InternalKDiagram4367 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_synpred13_InternalKDiagram4376 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleOpSingleAssign_in_synpred13_InternalKDiagram4382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_synpred14_InternalKDiagram4485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_synpred14_InternalKDiagram4499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_synpred14_InternalKDiagram4519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_synpred15_InternalKDiagram4746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred16_InternalKDiagram4798 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_53_in_synpred16_InternalKDiagram4805 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred16_InternalKDiagram4812 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_57_in_synpred16_InternalKDiagram4826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_synpred17_InternalKDiagram4946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_synpred18_InternalKDiagram5470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred20_InternalKDiagram5749 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_53_in_synpred20_InternalKDiagram5756 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred20_InternalKDiagram5763 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_57_in_synpred20_InternalKDiagram5777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_synpred22_InternalKDiagram6560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValidID_in_synpred23_InternalKDiagram6702 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred23_InternalKDiagram6708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_synpred24_InternalKDiagram6784 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_synpred24_InternalKDiagram6791 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred24_InternalKDiagram6797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmTypeReference_in_synpred25_InternalKDiagram8062 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleValidID_in_synpred25_InternalKDiagram8071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_synpred26_InternalKDiagram8633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred27_InternalKDiagram8685 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_53_in_synpred27_InternalKDiagram8692 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred27_InternalKDiagram8699 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_57_in_synpred27_InternalKDiagram8713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_synpred28_InternalKDiagram8833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_synpred29_InternalKDiagram9220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_synpred30_InternalKDiagram9313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred31_InternalKDiagram9343 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_53_in_synpred31_InternalKDiagram9350 = new BitSet(new long[]{0x0040010000000010L});
    public static final BitSet FOLLOW_ruleJvmFormalParameter_in_synpred31_InternalKDiagram9357 = new BitSet(new long[]{0x0220000000000000L});
    public static final BitSet FOLLOW_57_in_synpred31_InternalKDiagram9371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_synpred32_InternalKDiagram9491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXExpression_in_synpred33_InternalKDiagram10293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_synpred34_InternalKDiagram10438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_synpred35_InternalKDiagram10468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_synpred37_InternalKDiagram10815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_synpred38_InternalKDiagram11198 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_synpred38_InternalKDiagram11202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_synpred39_InternalKDiagram11562 = new BitSet(new long[]{0x0000000000000002L});

}