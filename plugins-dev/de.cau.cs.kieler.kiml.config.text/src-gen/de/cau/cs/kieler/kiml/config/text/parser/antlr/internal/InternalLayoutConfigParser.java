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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_BOOLEAN", "RULE_STRING", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_BOOLEAN=5;
    public static final int RULE_ID=4;
    public static final int RULE_STRING=6;
    public static final int T__15=15;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_NATURAL=8;
    public static final int RULE_WS=11;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;
    public static final int RULE_TFLOAT=7;

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

                if ( (LA1_0==RULE_ID) ) {
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
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:121:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:124:28: ( ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:2: () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:125:2: ()
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:126:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:131:2: ( (lv_id_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:132:1: (lv_id_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:132:1: (lv_id_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:133:3: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleKIdentifier238); 

            			newLeafNode(lv_id_1_0, grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKIdentifierRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKIdentifier255); 

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
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKIdentifier277);
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
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKIdentifier298);
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

            otherlv_5=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKIdentifier313); 

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
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry349);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry359); 

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
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:210:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:213:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:214:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:215:1: (lv_key_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:215:1: (lv_key_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:216:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rulePersistentEntry405);
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

            otherlv_1=(Token)match(input,14,FollowSets000.FOLLOW_14_in_rulePersistentEntry417); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:236:1: ( (lv_value_2_0= rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:237:1: (lv_value_2_0= rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:237:1: (lv_value_2_0= rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:238:3: lv_value_2_0= rulePropertyValue
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_rulePropertyValue_in_rulePersistentEntry438);
            lv_value_2_0=rulePropertyValue();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_2_0, 
                    		"PropertyValue");
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


    // $ANTLR start "entryRulePropertyValue"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:262:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:263:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:264:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePropertyValue_in_entryRulePropertyValue475);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePropertyValue486); 

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
    // $ANTLR end "entryRulePropertyValue"


    // $ANTLR start "rulePropertyValue"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:271:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:274:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:275:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:275:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            int alt4=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt4=1;
                }
                break;
            case RULE_STRING:
                {
                alt4=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt4=3;
                }
                break;
            case RULE_ID:
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
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:275:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_rulePropertyValue526); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:283:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rulePropertyValue552); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:292:5: this_Float_2= ruleFloat
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleFloat_in_rulePropertyValue585);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    		current.merge(this_Float_2);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:304:5: this_QualifiedID_3= ruleQualifiedID
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_rulePropertyValue618);
                    this_QualifiedID_3=ruleQualifiedID();

                    state._fsp--;


                    		current.merge(this_QualifiedID_3);
                        
                     
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "rulePropertyValue"


    // $ANTLR start "entryRuleQualifiedID"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:322:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:323:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:324:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID664);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleQualifiedID675); 

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
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:331:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:334:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:335:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:335:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:335:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleQualifiedID715); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:342:1: (kw= '.' this_ID_2= RULE_ID )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==15) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:343:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleQualifiedID734); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleQualifiedID749); 

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


    // $ANTLR start "entryRuleFloat"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:363:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:364:2: (iv_ruleFloat= ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:365:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleFloat_in_entryRuleFloat797);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleFloat808); 

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
    // $ANTLR end "entryRuleFloat"


    // $ANTLR start "ruleFloat"
    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:372:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:375:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:376:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:376:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_TFLOAT) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_NATURAL) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:376:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FollowSets000.FOLLOW_RULE_TFLOAT_in_ruleFloat848); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.config.text/src-gen/de/cau/cs/kieler/kiml/config/text/parser/antlr/internal/InternalLayoutConfig.g:384:10: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FollowSets000.FOLLOW_RULE_NATURAL_in_ruleFloat874); 

                    		current.merge(this_NATURAL_1);
                        
                     
                        newLeafNode(this_NATURAL_1, grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                        

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
    // $ANTLR end "ruleFloat"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKGraphElement_in_entryRuleKGraphElement75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGraphElement85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKIdentifier_in_ruleKGraphElement140 = new BitSet(new long[]{0x0000000000000012L});
        public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier177 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier187 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleKIdentifier238 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKIdentifier255 = new BitSet(new long[]{0x0000000000002010L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier277 = new BitSet(new long[]{0x0000000000002010L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier298 = new BitSet(new long[]{0x0000000000002010L});
        public static final BitSet FOLLOW_13_in_ruleKIdentifier313 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry349 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry359 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rulePersistentEntry405 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_rulePersistentEntry417 = new BitSet(new long[]{0x00000000000001F0L});
        public static final BitSet FOLLOW_rulePropertyValue_in_rulePersistentEntry438 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue475 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue486 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_rulePropertyValue526 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rulePropertyValue552 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleFloat_in_rulePropertyValue585 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_rulePropertyValue618 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID664 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID675 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID715 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_15_in_ruleQualifiedID734 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID749 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat797 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleFloat808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_TFLOAT_in_ruleFloat848 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_NATURAL_in_ruleFloat874 = new BitSet(new long[]{0x0000000000000002L});
    }


}