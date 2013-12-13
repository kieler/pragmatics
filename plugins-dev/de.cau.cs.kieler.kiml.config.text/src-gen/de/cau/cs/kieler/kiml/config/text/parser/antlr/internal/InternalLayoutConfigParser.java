package de.cau.cs.kieler.kiml.config.text.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.kiml.config.text.services.LayoutConfigGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalLayoutConfigParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

    // delegates
    // delegators


        public InternalLayoutConfigParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalLayoutConfigParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalLayoutConfigParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g"; }



     	private LayoutConfigGrammarAccess grammarAccess;
     	
        public InternalLayoutConfigParser(TokenStream input, LayoutConfigGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "KGraphElement";	
       	}
       	
       	@Override
       	protected LayoutConfigGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleKGraphElement"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:67:1: entryRuleKGraphElement returns [EObject current=null] : iv_ruleKGraphElement= ruleKGraphElement EOF ;
    public final EObject entryRuleKGraphElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGraphElement = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:68:2: (iv_ruleKGraphElement= ruleKGraphElement EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:69:2: iv_ruleKGraphElement= ruleKGraphElement EOF
            {
             newCompositeNode(grammarAccess.getKGraphElementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGraphElement_in_entryRuleKGraphElement75);
            iv_ruleKGraphElement=ruleKGraphElement();

            state._fsp--;

             current =iv_ruleKGraphElement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGraphElement85); 

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
    // $ANTLR end "entryRuleKGraphElement"


    // $ANTLR start "ruleKGraphElement"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:76:1: ruleKGraphElement returns [EObject current=null] : ( () ( (lv_data_1_0= ruleKIdentifier ) )* ) ;
    public final EObject ruleKGraphElement() throws RecognitionException {
        EObject current = null;

        EObject lv_data_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:79:28: ( ( () ( (lv_data_1_0= ruleKIdentifier ) )* ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:80:1: ( () ( (lv_data_1_0= ruleKIdentifier ) )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:80:1: ( () ( (lv_data_1_0= ruleKIdentifier ) )* )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:80:2: () ( (lv_data_1_0= ruleKIdentifier ) )*
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:80:2: ()
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:81:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGraphElementAccess().getKNodeAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:86:2: ( (lv_data_1_0= ruleKIdentifier ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_STRING && LA1_0<=RULE_ID)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:87:1: (lv_data_1_0= ruleKIdentifier )
            	    {
            	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:87:1: (lv_data_1_0= ruleKIdentifier )
            	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:88:3: lv_data_1_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKGraphElementAccess().getDataKIdentifierParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKIdentifier_in_ruleKGraphElement140);
            	    lv_data_1_0=ruleKIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getKGraphElementRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"data",
            	            		lv_data_1_0, 
            	            		"KIdentifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKGraphElement"


    // $ANTLR start "entryRuleKIdentifier"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:112:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:113:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:114:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier177);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKIdentifier187); 

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
    // $ANTLR end "entryRuleKIdentifier"


    // $ANTLR start "ruleKIdentifier"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:121:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_id_1_0 = null;

        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:124:28: ( ( () ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:1: ( () ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:1: ( () ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:2: () ( (lv_id_1_0= ruleEString ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:2: ()
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:126:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:131:2: ( (lv_id_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:132:1: (lv_id_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:132:1: (lv_id_1_0= ruleEString )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:133:3: lv_id_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKIdentifierAccess().getIdEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKIdentifier242);
            lv_id_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKIdentifierRule());
            	        }
                   		set(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleKIdentifier254); 

                	newLeafNode(otherlv_2, grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:153:1: ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:153:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    {
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:153:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:154:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:154:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:155:3: lv_persistentEntries_3_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKIdentifier276);
                    lv_persistentEntries_3_0=rulePersistentEntry();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKIdentifierRule());
                    	        }
                           		add(
                           			current, 
                           			"persistentEntries",
                            		lv_persistentEntries_3_0, 
                            		"PersistentEntry");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:171:2: ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==RULE_ID) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:172:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:172:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:173:3: lv_persistentEntries_4_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKIdentifier297);
                    	    lv_persistentEntries_4_0=rulePersistentEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKIdentifierRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"persistentEntries",
                    	            		lv_persistentEntries_4_0, 
                    	            		"PersistentEntry");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKIdentifier312); 

                	newLeafNode(otherlv_5, grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKIdentifier"


    // $ANTLR start "entryRulePersistentEntry"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:201:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:202:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:203:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry348);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry358); 

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
    // $ANTLR end "entryRulePersistentEntry"


    // $ANTLR start "rulePersistentEntry"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:210:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleQualifiedID ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:213:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleQualifiedID ) ) ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleQualifiedID ) ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleQualifiedID ) ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= ruleQualifiedID ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:215:1: (lv_key_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:215:1: (lv_key_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:216:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rulePersistentEntry404);
            lv_key_0_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"key",
                    		lv_key_0_0, 
                    		"QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,13,FollowSets000.FOLLOW_13_in_rulePersistentEntry416); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:236:1: ( (lv_value_2_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:237:1: (lv_value_2_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:237:1: (lv_value_2_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:238:3: lv_value_2_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueQualifiedIDParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rulePersistentEntry437);
            lv_value_2_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_2_0, 
                    		"QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePersistentEntry"


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:262:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:263:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:264:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString474);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString485); 

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
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:271:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:274:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:275:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:275:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_STRING) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:275:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString525); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:283:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString551); 

                    		current.merge(this_ID_1);
                        
                     
                        newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleQualifiedID"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:298:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:299:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:300:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID597);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleQualifiedID608); 

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
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:307:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:310:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:311:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:311:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:311:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleQualifiedID648); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:318:1: (kw= '.' this_ID_2= RULE_ID )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==14) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:319:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleQualifiedID667); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleQualifiedID682); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedID"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKGraphElement_in_entryRuleKGraphElement75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGraphElement85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKIdentifier_in_ruleKGraphElement140 = new BitSet(new long[]{0x0000000000000032L});
        public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier177 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier187 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKIdentifier242 = new BitSet(new long[]{0x0000000000000800L});
        public static final BitSet FOLLOW_11_in_ruleKIdentifier254 = new BitSet(new long[]{0x0000000000001020L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier276 = new BitSet(new long[]{0x0000000000001020L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier297 = new BitSet(new long[]{0x0000000000001020L});
        public static final BitSet FOLLOW_12_in_ruleKIdentifier312 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry348 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry358 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rulePersistentEntry404 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_13_in_rulePersistentEntry416 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rulePersistentEntry437 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString474 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString485 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString525 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString551 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID597 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID608 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID648 = new BitSet(new long[]{0x0000000000004002L});
        public static final BitSet FOLLOW_14_in_ruleQualifiedID667 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID682 = new BitSet(new long[]{0x0000000000004002L});
    }


}