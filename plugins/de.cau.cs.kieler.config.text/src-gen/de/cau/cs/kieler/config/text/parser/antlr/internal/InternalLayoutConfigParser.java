package de.cau.cs.kieler.config.text.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.config.text.services.LayoutConfigGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalLayoutConfigParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_SIGNED_INT", "RULE_FLOAT", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'{'", "'ref'", "'}'", "'label'", "':'", "'layout'", "'['", "'position'", "','", "'size'", "']'", "'incoming'", "'outgoing'", "'start'", "'end'", "'bends'", "'|'", "'section'", "'->'", "'.'", "'null'", "'true'", "'false'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=11;
    public static final int RULE_SIGNED_INT=6;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int RULE_FLOAT=7;
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

        public InternalLayoutConfigParser(TokenStream input, LayoutConfigGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "RootNode";
       	}

       	@Override
       	protected LayoutConfigGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleRootNode"
    // InternalLayoutConfig.g:64:1: entryRuleRootNode returns [EObject current=null] : iv_ruleRootNode= ruleRootNode EOF ;
    public final EObject entryRuleRootNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRootNode = null;


        try {
            // InternalLayoutConfig.g:64:49: (iv_ruleRootNode= ruleRootNode EOF )
            // InternalLayoutConfig.g:65:2: iv_ruleRootNode= ruleRootNode EOF
            {
             newCompositeNode(grammarAccess.getRootNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRootNode=ruleRootNode();

            state._fsp--;

             current =iv_ruleRootNode; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRootNode"


    // $ANTLR start "ruleRootNode"
    // InternalLayoutConfig.g:71:1: ruleRootNode returns [EObject current=null] : ( () ( (lv_children_1_0= ruleElkNode ) )+ ) ;
    public final EObject ruleRootNode() throws RecognitionException {
        EObject current = null;

        EObject lv_children_1_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:77:2: ( ( () ( (lv_children_1_0= ruleElkNode ) )+ ) )
            // InternalLayoutConfig.g:78:2: ( () ( (lv_children_1_0= ruleElkNode ) )+ )
            {
            // InternalLayoutConfig.g:78:2: ( () ( (lv_children_1_0= ruleElkNode ) )+ )
            // InternalLayoutConfig.g:79:3: () ( (lv_children_1_0= ruleElkNode ) )+
            {
            // InternalLayoutConfig.g:79:3: ()
            // InternalLayoutConfig.g:80:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getRootNodeAccess().getElkNodeAction_0(),
            					current);
            			

            }

            // InternalLayoutConfig.g:86:3: ( (lv_children_1_0= ruleElkNode ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalLayoutConfig.g:87:4: (lv_children_1_0= ruleElkNode )
            	    {
            	    // InternalLayoutConfig.g:87:4: (lv_children_1_0= ruleElkNode )
            	    // InternalLayoutConfig.g:88:5: lv_children_1_0= ruleElkNode
            	    {

            	    					newCompositeNode(grammarAccess.getRootNodeAccess().getChildrenElkNodeParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_children_1_0=ruleElkNode();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getRootNodeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"children",
            	    						lv_children_1_0,
            	    						"de.cau.cs.kieler.config.text.LayoutConfig.ElkNode");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
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
    // $ANTLR end "ruleRootNode"


    // $ANTLR start "entryRuleElkNode"
    // InternalLayoutConfig.g:109:1: entryRuleElkNode returns [EObject current=null] : iv_ruleElkNode= ruleElkNode EOF ;
    public final EObject entryRuleElkNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkNode = null;


        try {
            // InternalLayoutConfig.g:109:48: (iv_ruleElkNode= ruleElkNode EOF )
            // InternalLayoutConfig.g:110:2: iv_ruleElkNode= ruleElkNode EOF
            {
             newCompositeNode(grammarAccess.getElkNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkNode=ruleElkNode();

            state._fsp--;

             current =iv_ruleElkNode; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkNode"


    // $ANTLR start "ruleElkNode"
    // InternalLayoutConfig.g:116:1: ruleElkNode returns [EObject current=null] : ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* (otherlv_3= 'ref' ( (lv_children_4_0= ruleRefElkNode ) ) )* otherlv_5= '}' ) ;
    public final EObject ruleElkNode() throws RecognitionException {
        EObject current = null;

        Token lv_identifier_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_properties_2_0 = null;

        EObject lv_children_4_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:122:2: ( ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* (otherlv_3= 'ref' ( (lv_children_4_0= ruleRefElkNode ) ) )* otherlv_5= '}' ) )
            // InternalLayoutConfig.g:123:2: ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* (otherlv_3= 'ref' ( (lv_children_4_0= ruleRefElkNode ) ) )* otherlv_5= '}' )
            {
            // InternalLayoutConfig.g:123:2: ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* (otherlv_3= 'ref' ( (lv_children_4_0= ruleRefElkNode ) ) )* otherlv_5= '}' )
            // InternalLayoutConfig.g:124:3: ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* (otherlv_3= 'ref' ( (lv_children_4_0= ruleRefElkNode ) ) )* otherlv_5= '}'
            {
            // InternalLayoutConfig.g:124:3: ( (lv_identifier_0_0= RULE_ID ) )
            // InternalLayoutConfig.g:125:4: (lv_identifier_0_0= RULE_ID )
            {
            // InternalLayoutConfig.g:125:4: (lv_identifier_0_0= RULE_ID )
            // InternalLayoutConfig.g:126:5: lv_identifier_0_0= RULE_ID
            {
            lv_identifier_0_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_identifier_0_0, grammarAccess.getElkNodeAccess().getIdentifierIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElkNodeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"identifier",
            						lv_identifier_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,13,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalLayoutConfig.g:146:3: ( (lv_properties_2_0= ruleProperty ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalLayoutConfig.g:147:4: (lv_properties_2_0= ruleProperty )
            	    {
            	    // InternalLayoutConfig.g:147:4: (lv_properties_2_0= ruleProperty )
            	    // InternalLayoutConfig.g:148:5: lv_properties_2_0= ruleProperty
            	    {

            	    					newCompositeNode(grammarAccess.getElkNodeAccess().getPropertiesPropertyParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_properties_2_0=ruleProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getElkNodeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"properties",
            	    						lv_properties_2_0,
            	    						"org.eclipse.elk.graph.text.ElkGraph.Property");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalLayoutConfig.g:165:3: (otherlv_3= 'ref' ( (lv_children_4_0= ruleRefElkNode ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==14) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalLayoutConfig.g:166:4: otherlv_3= 'ref' ( (lv_children_4_0= ruleRefElkNode ) )
            	    {
            	    otherlv_3=(Token)match(input,14,FOLLOW_6); 

            	    				newLeafNode(otherlv_3, grammarAccess.getElkNodeAccess().getRefKeyword_3_0());
            	    			
            	    // InternalLayoutConfig.g:170:4: ( (lv_children_4_0= ruleRefElkNode ) )
            	    // InternalLayoutConfig.g:171:5: (lv_children_4_0= ruleRefElkNode )
            	    {
            	    // InternalLayoutConfig.g:171:5: (lv_children_4_0= ruleRefElkNode )
            	    // InternalLayoutConfig.g:172:6: lv_children_4_0= ruleRefElkNode
            	    {

            	    						newCompositeNode(grammarAccess.getElkNodeAccess().getChildrenRefElkNodeParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_7);
            	    lv_children_4_0=ruleRefElkNode();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getElkNodeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"children",
            	    							lv_children_4_0,
            	    							"de.cau.cs.kieler.config.text.LayoutConfig.RefElkNode");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_5=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_4());
            		

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
    // $ANTLR end "ruleElkNode"


    // $ANTLR start "entryRuleRefElkNode"
    // InternalLayoutConfig.g:198:1: entryRuleRefElkNode returns [EObject current=null] : iv_ruleRefElkNode= ruleRefElkNode EOF ;
    public final EObject entryRuleRefElkNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRefElkNode = null;


        try {
            // InternalLayoutConfig.g:198:51: (iv_ruleRefElkNode= ruleRefElkNode EOF )
            // InternalLayoutConfig.g:199:2: iv_ruleRefElkNode= ruleRefElkNode EOF
            {
             newCompositeNode(grammarAccess.getRefElkNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRefElkNode=ruleRefElkNode();

            state._fsp--;

             current =iv_ruleRefElkNode; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRefElkNode"


    // $ANTLR start "ruleRefElkNode"
    // InternalLayoutConfig.g:205:1: ruleRefElkNode returns [EObject current=null] : ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' ) ;
    public final EObject ruleRefElkNode() throws RecognitionException {
        EObject current = null;

        Token lv_identifier_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_properties_2_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:211:2: ( ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' ) )
            // InternalLayoutConfig.g:212:2: ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' )
            {
            // InternalLayoutConfig.g:212:2: ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' )
            // InternalLayoutConfig.g:213:3: ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}'
            {
            // InternalLayoutConfig.g:213:3: ( (lv_identifier_0_0= RULE_ID ) )
            // InternalLayoutConfig.g:214:4: (lv_identifier_0_0= RULE_ID )
            {
            // InternalLayoutConfig.g:214:4: (lv_identifier_0_0= RULE_ID )
            // InternalLayoutConfig.g:215:5: lv_identifier_0_0= RULE_ID
            {
            lv_identifier_0_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_identifier_0_0, grammarAccess.getRefElkNodeAccess().getIdentifierIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRefElkNodeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"identifier",
            						lv_identifier_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,13,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getRefElkNodeAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalLayoutConfig.g:235:3: ( (lv_properties_2_0= ruleProperty ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_ID) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalLayoutConfig.g:236:4: (lv_properties_2_0= ruleProperty )
            	    {
            	    // InternalLayoutConfig.g:236:4: (lv_properties_2_0= ruleProperty )
            	    // InternalLayoutConfig.g:237:5: lv_properties_2_0= ruleProperty
            	    {

            	    					newCompositeNode(grammarAccess.getRefElkNodeAccess().getPropertiesPropertyParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_properties_2_0=ruleProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getRefElkNodeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"properties",
            	    						lv_properties_2_0,
            	    						"org.eclipse.elk.graph.text.ElkGraph.Property");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_3=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getRefElkNodeAccess().getRightCurlyBracketKeyword_3());
            		

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
    // $ANTLR end "ruleRefElkNode"


    // $ANTLR start "entryRuleElkLabel"
    // InternalLayoutConfig.g:262:1: entryRuleElkLabel returns [EObject current=null] : iv_ruleElkLabel= ruleElkLabel EOF ;
    public final EObject entryRuleElkLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkLabel = null;


        try {
            // InternalLayoutConfig.g:262:49: (iv_ruleElkLabel= ruleElkLabel EOF )
            // InternalLayoutConfig.g:263:2: iv_ruleElkLabel= ruleElkLabel EOF
            {
             newCompositeNode(grammarAccess.getElkLabelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkLabel=ruleElkLabel();

            state._fsp--;

             current =iv_ruleElkLabel; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkLabel"


    // $ANTLR start "ruleElkLabel"
    // InternalLayoutConfig.g:269:1: ruleElkLabel returns [EObject current=null] : (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? ) ;
    public final EObject ruleElkLabel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token lv_text_3_0=null;
        Token otherlv_4=null;
        Token otherlv_8=null;
        EObject this_ShapeLayout_5 = null;

        EObject lv_properties_6_0 = null;

        EObject lv_labels_7_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:275:2: ( (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? ) )
            // InternalLayoutConfig.g:276:2: (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? )
            {
            // InternalLayoutConfig.g:276:2: (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? )
            // InternalLayoutConfig.g:277:3: otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )?
            {
            otherlv_0=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getElkLabelAccess().getLabelKeyword_0());
            		
            // InternalLayoutConfig.g:281:3: ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalLayoutConfig.g:282:4: ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':'
                    {
                    // InternalLayoutConfig.g:282:4: ( (lv_identifier_1_0= RULE_ID ) )
                    // InternalLayoutConfig.g:283:5: (lv_identifier_1_0= RULE_ID )
                    {
                    // InternalLayoutConfig.g:283:5: (lv_identifier_1_0= RULE_ID )
                    // InternalLayoutConfig.g:284:6: lv_identifier_1_0= RULE_ID
                    {
                    lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_10); 

                    						newLeafNode(lv_identifier_1_0, grammarAccess.getElkLabelAccess().getIdentifierIDTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getElkLabelRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"identifier",
                    							lv_identifier_1_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }

                    otherlv_2=(Token)match(input,17,FOLLOW_11); 

                    				newLeafNode(otherlv_2, grammarAccess.getElkLabelAccess().getColonKeyword_1_1());
                    			

                    }
                    break;

            }

            // InternalLayoutConfig.g:305:3: ( (lv_text_3_0= RULE_STRING ) )
            // InternalLayoutConfig.g:306:4: (lv_text_3_0= RULE_STRING )
            {
            // InternalLayoutConfig.g:306:4: (lv_text_3_0= RULE_STRING )
            // InternalLayoutConfig.g:307:5: lv_text_3_0= RULE_STRING
            {
            lv_text_3_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            					newLeafNode(lv_text_3_0, grammarAccess.getElkLabelAccess().getTextSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElkLabelRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_3_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalLayoutConfig.g:323:3: (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==13) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalLayoutConfig.g:324:4: otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}'
                    {
                    otherlv_4=(Token)match(input,13,FOLLOW_13); 

                    				newLeafNode(otherlv_4, grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0());
                    			
                    // InternalLayoutConfig.g:328:4: (this_ShapeLayout_5= ruleShapeLayout[$current] )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==18) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // InternalLayoutConfig.g:329:5: this_ShapeLayout_5= ruleShapeLayout[$current]
                            {

                            					if (current==null) {
                            						current = createModelElement(grammarAccess.getElkLabelRule());
                            					}
                            					newCompositeNode(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1());
                            				
                            pushFollow(FOLLOW_14);
                            this_ShapeLayout_5=ruleShapeLayout(current);

                            state._fsp--;


                            					current = this_ShapeLayout_5;
                            					afterParserOrEnumRuleCall();
                            				

                            }
                            break;

                    }

                    // InternalLayoutConfig.g:341:4: ( (lv_properties_6_0= ruleProperty ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==RULE_ID) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:342:5: (lv_properties_6_0= ruleProperty )
                    	    {
                    	    // InternalLayoutConfig.g:342:5: (lv_properties_6_0= ruleProperty )
                    	    // InternalLayoutConfig.g:343:6: lv_properties_6_0= ruleProperty
                    	    {

                    	    						newCompositeNode(grammarAccess.getElkLabelAccess().getPropertiesPropertyParserRuleCall_3_2_0());
                    	    					
                    	    pushFollow(FOLLOW_14);
                    	    lv_properties_6_0=ruleProperty();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getElkLabelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"properties",
                    	    							lv_properties_6_0,
                    	    							"org.eclipse.elk.graph.text.ElkGraph.Property");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    // InternalLayoutConfig.g:360:4: ( (lv_labels_7_0= ruleElkLabel ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==16) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:361:5: (lv_labels_7_0= ruleElkLabel )
                    	    {
                    	    // InternalLayoutConfig.g:361:5: (lv_labels_7_0= ruleElkLabel )
                    	    // InternalLayoutConfig.g:362:6: lv_labels_7_0= ruleElkLabel
                    	    {

                    	    						newCompositeNode(grammarAccess.getElkLabelAccess().getLabelsElkLabelParserRuleCall_3_3_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_labels_7_0=ruleElkLabel();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getElkLabelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"labels",
                    	    							lv_labels_7_0,
                    	    							"org.eclipse.elk.graph.text.ElkGraph.ElkLabel");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,15,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4());
                    			

                    }
                    break;

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
    // $ANTLR end "ruleElkLabel"


    // $ANTLR start "ruleShapeLayout"
    // InternalLayoutConfig.g:389:1: ruleShapeLayout[EObject in_current] returns [EObject current=in_current] : (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' ) ;
    public final EObject ruleShapeLayout(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_x_5_0 = null;

        AntlrDatatypeRuleToken lv_y_7_0 = null;

        AntlrDatatypeRuleToken lv_width_10_0 = null;

        AntlrDatatypeRuleToken lv_height_12_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:395:2: ( (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' ) )
            // InternalLayoutConfig.g:396:2: (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' )
            {
            // InternalLayoutConfig.g:396:2: (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' )
            // InternalLayoutConfig.g:397:3: otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0());
            		
            otherlv_1=(Token)match(input,19,FOLLOW_17); 

            			newLeafNode(otherlv_1, grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalLayoutConfig.g:405:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) )
            // InternalLayoutConfig.g:406:4: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) )
            {
            // InternalLayoutConfig.g:406:4: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) )
            // InternalLayoutConfig.g:407:5: ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            				
            // InternalLayoutConfig.g:410:5: ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* )
            // InternalLayoutConfig.g:411:6: ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )*
            {
            // InternalLayoutConfig.g:411:6: ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( LA10_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                    alt10=1;
                }
                else if ( LA10_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalLayoutConfig.g:412:4: ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:412:4: ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) )
            	    // InternalLayoutConfig.g:413:5: {...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
            	    }
            	    // InternalLayoutConfig.g:413:108: ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) )
            	    // InternalLayoutConfig.g:414:6: ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
            	    					
            	    // InternalLayoutConfig.g:417:9: ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) )
            	    // InternalLayoutConfig.g:417:10: {...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "true");
            	    }
            	    // InternalLayoutConfig.g:417:19: (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) )
            	    // InternalLayoutConfig.g:417:20: otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) )
            	    {
            	    otherlv_3=(Token)match(input,20,FOLLOW_10); 

            	    									newLeafNode(otherlv_3, grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0());
            	    								
            	    otherlv_4=(Token)match(input,17,FOLLOW_18); 

            	    									newLeafNode(otherlv_4, grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1());
            	    								
            	    // InternalLayoutConfig.g:425:9: ( (lv_x_5_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:426:10: (lv_x_5_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:426:10: (lv_x_5_0= ruleNumber )
            	    // InternalLayoutConfig.g:427:11: lv_x_5_0= ruleNumber
            	    {

            	    											newCompositeNode(grammarAccess.getShapeLayoutAccess().getXNumberParserRuleCall_2_0_2_0());
            	    										
            	    pushFollow(FOLLOW_19);
            	    lv_x_5_0=ruleNumber();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    											}
            	    											set(
            	    												current,
            	    												"x",
            	    												lv_x_5_0,
            	    												"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }

            	    otherlv_6=(Token)match(input,21,FOLLOW_18); 

            	    									newLeafNode(otherlv_6, grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_0_3());
            	    								
            	    // InternalLayoutConfig.g:448:9: ( (lv_y_7_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:449:10: (lv_y_7_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:449:10: (lv_y_7_0= ruleNumber )
            	    // InternalLayoutConfig.g:450:11: lv_y_7_0= ruleNumber
            	    {

            	    											newCompositeNode(grammarAccess.getShapeLayoutAccess().getYNumberParserRuleCall_2_0_4_0());
            	    										
            	    pushFollow(FOLLOW_17);
            	    lv_y_7_0=ruleNumber();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    											}
            	    											set(
            	    												current,
            	    												"y",
            	    												lv_y_7_0,
            	    												"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalLayoutConfig.g:473:4: ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:473:4: ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) )
            	    // InternalLayoutConfig.g:474:5: {...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
            	    }
            	    // InternalLayoutConfig.g:474:108: ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) )
            	    // InternalLayoutConfig.g:475:6: ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
            	    					
            	    // InternalLayoutConfig.g:478:9: ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) )
            	    // InternalLayoutConfig.g:478:10: {...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "true");
            	    }
            	    // InternalLayoutConfig.g:478:19: (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) )
            	    // InternalLayoutConfig.g:478:20: otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) )
            	    {
            	    otherlv_8=(Token)match(input,22,FOLLOW_10); 

            	    									newLeafNode(otherlv_8, grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0());
            	    								
            	    otherlv_9=(Token)match(input,17,FOLLOW_18); 

            	    									newLeafNode(otherlv_9, grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1());
            	    								
            	    // InternalLayoutConfig.g:486:9: ( (lv_width_10_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:487:10: (lv_width_10_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:487:10: (lv_width_10_0= ruleNumber )
            	    // InternalLayoutConfig.g:488:11: lv_width_10_0= ruleNumber
            	    {

            	    											newCompositeNode(grammarAccess.getShapeLayoutAccess().getWidthNumberParserRuleCall_2_1_2_0());
            	    										
            	    pushFollow(FOLLOW_19);
            	    lv_width_10_0=ruleNumber();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    											}
            	    											set(
            	    												current,
            	    												"width",
            	    												lv_width_10_0,
            	    												"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }

            	    otherlv_11=(Token)match(input,21,FOLLOW_18); 

            	    									newLeafNode(otherlv_11, grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_1_3());
            	    								
            	    // InternalLayoutConfig.g:509:9: ( (lv_height_12_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:510:10: (lv_height_12_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:510:10: (lv_height_12_0= ruleNumber )
            	    // InternalLayoutConfig.g:511:11: lv_height_12_0= ruleNumber
            	    {

            	    											newCompositeNode(grammarAccess.getShapeLayoutAccess().getHeightNumberParserRuleCall_2_1_4_0());
            	    										
            	    pushFollow(FOLLOW_17);
            	    lv_height_12_0=ruleNumber();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    											}
            	    											set(
            	    												current,
            	    												"height",
            	    												lv_height_12_0,
            	    												"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            				

            }

            otherlv_13=(Token)match(input,23,FOLLOW_2); 

            			newLeafNode(otherlv_13, grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3());
            		

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
    // $ANTLR end "ruleShapeLayout"


    // $ANTLR start "ruleEdgeLayout"
    // InternalLayoutConfig.g:550:1: ruleEdgeLayout[EObject in_current] returns [EObject current=in_current] : (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' ) ;
    public final EObject ruleEdgeLayout(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_4=null;
        EObject lv_sections_2_0 = null;

        EObject lv_sections_3_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:556:2: ( (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' ) )
            // InternalLayoutConfig.g:557:2: (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' )
            {
            // InternalLayoutConfig.g:557:2: (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' )
            // InternalLayoutConfig.g:558:3: otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0());
            		
            otherlv_1=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_1, grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalLayoutConfig.g:566:3: ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID||(LA12_0>=23 && LA12_0<=28)) ) {
                alt12=1;
            }
            else if ( (LA12_0==30) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalLayoutConfig.g:567:4: ( (lv_sections_2_0= ruleElkSingleEdgeSection ) )
                    {
                    // InternalLayoutConfig.g:567:4: ( (lv_sections_2_0= ruleElkSingleEdgeSection ) )
                    // InternalLayoutConfig.g:568:5: (lv_sections_2_0= ruleElkSingleEdgeSection )
                    {
                    // InternalLayoutConfig.g:568:5: (lv_sections_2_0= ruleElkSingleEdgeSection )
                    // InternalLayoutConfig.g:569:6: lv_sections_2_0= ruleElkSingleEdgeSection
                    {

                    						newCompositeNode(grammarAccess.getEdgeLayoutAccess().getSectionsElkSingleEdgeSectionParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_21);
                    lv_sections_2_0=ruleElkSingleEdgeSection();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEdgeLayoutRule());
                    						}
                    						add(
                    							current,
                    							"sections",
                    							lv_sections_2_0,
                    							"org.eclipse.elk.graph.text.ElkGraph.ElkSingleEdgeSection");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:587:4: ( (lv_sections_3_0= ruleElkEdgeSection ) )+
                    {
                    // InternalLayoutConfig.g:587:4: ( (lv_sections_3_0= ruleElkEdgeSection ) )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==30) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:588:5: (lv_sections_3_0= ruleElkEdgeSection )
                    	    {
                    	    // InternalLayoutConfig.g:588:5: (lv_sections_3_0= ruleElkEdgeSection )
                    	    // InternalLayoutConfig.g:589:6: lv_sections_3_0= ruleElkEdgeSection
                    	    {

                    	    						newCompositeNode(grammarAccess.getEdgeLayoutAccess().getSectionsElkEdgeSectionParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_20);
                    	    lv_sections_3_0=ruleElkEdgeSection();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEdgeLayoutRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"sections",
                    	    							lv_sections_3_0,
                    	    							"org.eclipse.elk.graph.text.ElkGraph.ElkEdgeSection");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    }
                    break;

            }

            otherlv_4=(Token)match(input,23,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3());
            		

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
    // $ANTLR end "ruleEdgeLayout"


    // $ANTLR start "entryRuleElkSingleEdgeSection"
    // InternalLayoutConfig.g:615:1: entryRuleElkSingleEdgeSection returns [EObject current=null] : iv_ruleElkSingleEdgeSection= ruleElkSingleEdgeSection EOF ;
    public final EObject entryRuleElkSingleEdgeSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkSingleEdgeSection = null;


        try {
            // InternalLayoutConfig.g:615:61: (iv_ruleElkSingleEdgeSection= ruleElkSingleEdgeSection EOF )
            // InternalLayoutConfig.g:616:2: iv_ruleElkSingleEdgeSection= ruleElkSingleEdgeSection EOF
            {
             newCompositeNode(grammarAccess.getElkSingleEdgeSectionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkSingleEdgeSection=ruleElkSingleEdgeSection();

            state._fsp--;

             current =iv_ruleElkSingleEdgeSection; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkSingleEdgeSection"


    // $ANTLR start "ruleElkSingleEdgeSection"
    // InternalLayoutConfig.g:622:1: ruleElkSingleEdgeSection returns [EObject current=null] : ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) ) ;
    public final EObject ruleElkSingleEdgeSection() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        AntlrDatatypeRuleToken lv_startX_10_0 = null;

        AntlrDatatypeRuleToken lv_startY_12_0 = null;

        AntlrDatatypeRuleToken lv_endX_15_0 = null;

        AntlrDatatypeRuleToken lv_endY_17_0 = null;

        EObject lv_bendPoints_20_0 = null;

        EObject lv_bendPoints_22_0 = null;

        EObject lv_properties_23_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:628:2: ( ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) ) )
            // InternalLayoutConfig.g:629:2: ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) )
            {
            // InternalLayoutConfig.g:629:2: ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) )
            // InternalLayoutConfig.g:630:3: () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* )
            {
            // InternalLayoutConfig.g:630:3: ()
            // InternalLayoutConfig.g:631:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0(),
            					current);
            			

            }

            // InternalLayoutConfig.g:637:3: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* )
            // InternalLayoutConfig.g:638:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )*
            {
            // InternalLayoutConfig.g:638:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) )
            // InternalLayoutConfig.g:639:5: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) )
            {
            // InternalLayoutConfig.g:639:5: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) )
            // InternalLayoutConfig.g:640:6: ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* )
            {
             
            					  getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            					
            // InternalLayoutConfig.g:643:6: ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* )
            // InternalLayoutConfig.g:644:7: ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )*
            {
            // InternalLayoutConfig.g:644:7: ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )*
            loop13:
            do {
                int alt13=5;
                int LA13_0 = input.LA(1);

                if ( LA13_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                    alt13=1;
                }
                else if ( LA13_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                    alt13=2;
                }
                else if ( LA13_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                    alt13=3;
                }
                else if ( LA13_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                    alt13=4;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalLayoutConfig.g:645:5: ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:645:5: ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalLayoutConfig.g:646:6: {...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0)");
            	    }
            	    // InternalLayoutConfig.g:646:120: ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalLayoutConfig.g:647:7: ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0);
            	    						
            	    // InternalLayoutConfig.g:650:10: ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalLayoutConfig.g:650:11: {...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:650:20: (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalLayoutConfig.g:650:21: otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_2=(Token)match(input,24,FOLLOW_10); 

            	    										newLeafNode(otherlv_2, grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0_0());
            	    									
            	    otherlv_3=(Token)match(input,17,FOLLOW_6); 

            	    										newLeafNode(otherlv_3, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_0_1());
            	    									
            	    // InternalLayoutConfig.g:658:10: ( ( ruleQualifiedId ) )
            	    // InternalLayoutConfig.g:659:11: ( ruleQualifiedId )
            	    {
            	    // InternalLayoutConfig.g:659:11: ( ruleQualifiedId )
            	    // InternalLayoutConfig.g:660:12: ruleQualifiedId
            	    {

            	    												if (current==null) {
            	    													current = createModelElement(grammarAccess.getElkSingleEdgeSectionRule());
            	    												}
            	    											

            	    												newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0());
            	    											
            	    pushFollow(FOLLOW_22);
            	    ruleQualifiedId();

            	    state._fsp--;


            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalLayoutConfig.g:680:5: ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:680:5: ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalLayoutConfig.g:681:6: {...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1)");
            	    }
            	    // InternalLayoutConfig.g:681:120: ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalLayoutConfig.g:682:7: ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1);
            	    						
            	    // InternalLayoutConfig.g:685:10: ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalLayoutConfig.g:685:11: {...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:685:20: (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalLayoutConfig.g:685:21: otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_5=(Token)match(input,25,FOLLOW_10); 

            	    										newLeafNode(otherlv_5, grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_0_1_0());
            	    									
            	    otherlv_6=(Token)match(input,17,FOLLOW_6); 

            	    										newLeafNode(otherlv_6, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1_1());
            	    									
            	    // InternalLayoutConfig.g:693:10: ( ( ruleQualifiedId ) )
            	    // InternalLayoutConfig.g:694:11: ( ruleQualifiedId )
            	    {
            	    // InternalLayoutConfig.g:694:11: ( ruleQualifiedId )
            	    // InternalLayoutConfig.g:695:12: ruleQualifiedId
            	    {

            	    												if (current==null) {
            	    													current = createModelElement(grammarAccess.getElkSingleEdgeSectionRule());
            	    												}
            	    											

            	    												newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0());
            	    											
            	    pushFollow(FOLLOW_22);
            	    ruleQualifiedId();

            	    state._fsp--;


            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalLayoutConfig.g:715:5: ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:715:5: ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) )
            	    // InternalLayoutConfig.g:716:6: {...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2)");
            	    }
            	    // InternalLayoutConfig.g:716:120: ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) )
            	    // InternalLayoutConfig.g:717:7: ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2);
            	    						
            	    // InternalLayoutConfig.g:720:10: ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) )
            	    // InternalLayoutConfig.g:720:11: {...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:720:20: (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) )
            	    // InternalLayoutConfig.g:720:21: otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) )
            	    {
            	    otherlv_8=(Token)match(input,26,FOLLOW_10); 

            	    										newLeafNode(otherlv_8, grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_0_2_0());
            	    									
            	    otherlv_9=(Token)match(input,17,FOLLOW_18); 

            	    										newLeafNode(otherlv_9, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_2_1());
            	    									
            	    // InternalLayoutConfig.g:728:10: ( (lv_startX_10_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:729:11: (lv_startX_10_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:729:11: (lv_startX_10_0= ruleNumber )
            	    // InternalLayoutConfig.g:730:12: lv_startX_10_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_0_2_2_0());
            	    											
            	    pushFollow(FOLLOW_19);
            	    lv_startX_10_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"startX",
            	    													lv_startX_10_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }

            	    otherlv_11=(Token)match(input,21,FOLLOW_18); 

            	    										newLeafNode(otherlv_11, grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_2_3());
            	    									
            	    // InternalLayoutConfig.g:751:10: ( (lv_startY_12_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:752:11: (lv_startY_12_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:752:11: (lv_startY_12_0= ruleNumber )
            	    // InternalLayoutConfig.g:753:12: lv_startY_12_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_0_2_4_0());
            	    											
            	    pushFollow(FOLLOW_22);
            	    lv_startY_12_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"startY",
            	    													lv_startY_12_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalLayoutConfig.g:776:5: ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:776:5: ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) )
            	    // InternalLayoutConfig.g:777:6: {...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3)");
            	    }
            	    // InternalLayoutConfig.g:777:120: ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) )
            	    // InternalLayoutConfig.g:778:7: ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3);
            	    						
            	    // InternalLayoutConfig.g:781:10: ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) )
            	    // InternalLayoutConfig.g:781:11: {...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:781:20: (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) )
            	    // InternalLayoutConfig.g:781:21: otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) )
            	    {
            	    otherlv_13=(Token)match(input,27,FOLLOW_10); 

            	    										newLeafNode(otherlv_13, grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_0_3_0());
            	    									
            	    otherlv_14=(Token)match(input,17,FOLLOW_18); 

            	    										newLeafNode(otherlv_14, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_3_1());
            	    									
            	    // InternalLayoutConfig.g:789:10: ( (lv_endX_15_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:790:11: (lv_endX_15_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:790:11: (lv_endX_15_0= ruleNumber )
            	    // InternalLayoutConfig.g:791:12: lv_endX_15_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_0_3_2_0());
            	    											
            	    pushFollow(FOLLOW_19);
            	    lv_endX_15_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"endX",
            	    													lv_endX_15_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }

            	    otherlv_16=(Token)match(input,21,FOLLOW_18); 

            	    										newLeafNode(otherlv_16, grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_3_3());
            	    									
            	    // InternalLayoutConfig.g:812:10: ( (lv_endY_17_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:813:11: (lv_endY_17_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:813:11: (lv_endY_17_0= ruleNumber )
            	    // InternalLayoutConfig.g:814:12: lv_endY_17_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_0_3_4_0());
            	    											
            	    pushFollow(FOLLOW_22);
            	    lv_endY_17_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"endY",
            	    													lv_endY_17_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    						

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

             
            					  getUnorderedGroupHelper().leave(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            					

            }

            // InternalLayoutConfig.g:844:4: (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==28) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalLayoutConfig.g:845:5: otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )*
                    {
                    otherlv_18=(Token)match(input,28,FOLLOW_10); 

                    					newLeafNode(otherlv_18, grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_1_0());
                    				
                    otherlv_19=(Token)match(input,17,FOLLOW_18); 

                    					newLeafNode(otherlv_19, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1());
                    				
                    // InternalLayoutConfig.g:853:5: ( (lv_bendPoints_20_0= ruleElkBendPoint ) )
                    // InternalLayoutConfig.g:854:6: (lv_bendPoints_20_0= ruleElkBendPoint )
                    {
                    // InternalLayoutConfig.g:854:6: (lv_bendPoints_20_0= ruleElkBendPoint )
                    // InternalLayoutConfig.g:855:7: lv_bendPoints_20_0= ruleElkBendPoint
                    {

                    							newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_2_0());
                    						
                    pushFollow(FOLLOW_23);
                    lv_bendPoints_20_0=ruleElkBendPoint();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
                    							}
                    							add(
                    								current,
                    								"bendPoints",
                    								lv_bendPoints_20_0,
                    								"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalLayoutConfig.g:872:5: (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==29) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:873:6: otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) )
                    	    {
                    	    otherlv_21=(Token)match(input,29,FOLLOW_18); 

                    	    						newLeafNode(otherlv_21, grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_1_3_0());
                    	    					
                    	    // InternalLayoutConfig.g:877:6: ( (lv_bendPoints_22_0= ruleElkBendPoint ) )
                    	    // InternalLayoutConfig.g:878:7: (lv_bendPoints_22_0= ruleElkBendPoint )
                    	    {
                    	    // InternalLayoutConfig.g:878:7: (lv_bendPoints_22_0= ruleElkBendPoint )
                    	    // InternalLayoutConfig.g:879:8: lv_bendPoints_22_0= ruleElkBendPoint
                    	    {

                    	    								newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_3_1_0());
                    	    							
                    	    pushFollow(FOLLOW_23);
                    	    lv_bendPoints_22_0=ruleElkBendPoint();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"bendPoints",
                    	    									lv_bendPoints_22_0,
                    	    									"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalLayoutConfig.g:898:4: ( (lv_properties_23_0= ruleProperty ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalLayoutConfig.g:899:5: (lv_properties_23_0= ruleProperty )
            	    {
            	    // InternalLayoutConfig.g:899:5: (lv_properties_23_0= ruleProperty )
            	    // InternalLayoutConfig.g:900:6: lv_properties_23_0= ruleProperty
            	    {

            	    						newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesPropertyParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_properties_23_0=ruleProperty();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"properties",
            	    							lv_properties_23_0,
            	    							"org.eclipse.elk.graph.text.ElkGraph.Property");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


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
    // $ANTLR end "ruleElkSingleEdgeSection"


    // $ANTLR start "entryRuleElkEdgeSection"
    // InternalLayoutConfig.g:922:1: entryRuleElkEdgeSection returns [EObject current=null] : iv_ruleElkEdgeSection= ruleElkEdgeSection EOF ;
    public final EObject entryRuleElkEdgeSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkEdgeSection = null;


        try {
            // InternalLayoutConfig.g:922:55: (iv_ruleElkEdgeSection= ruleElkEdgeSection EOF )
            // InternalLayoutConfig.g:923:2: iv_ruleElkEdgeSection= ruleElkEdgeSection EOF
            {
             newCompositeNode(grammarAccess.getElkEdgeSectionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkEdgeSection=ruleElkEdgeSection();

            state._fsp--;

             current =iv_ruleElkEdgeSection; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkEdgeSection"


    // $ANTLR start "ruleElkEdgeSection"
    // InternalLayoutConfig.g:929:1: ruleElkEdgeSection returns [EObject current=null] : (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' ) ;
    public final EObject ruleElkEdgeSection() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_30=null;
        AntlrDatatypeRuleToken lv_startX_16_0 = null;

        AntlrDatatypeRuleToken lv_startY_18_0 = null;

        AntlrDatatypeRuleToken lv_endX_21_0 = null;

        AntlrDatatypeRuleToken lv_endY_23_0 = null;

        EObject lv_bendPoints_26_0 = null;

        EObject lv_bendPoints_28_0 = null;

        EObject lv_properties_29_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:935:2: ( (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' ) )
            // InternalLayoutConfig.g:936:2: (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' )
            {
            // InternalLayoutConfig.g:936:2: (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' )
            // InternalLayoutConfig.g:937:3: otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']'
            {
            otherlv_0=(Token)match(input,30,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0());
            		
            // InternalLayoutConfig.g:941:3: ( (lv_identifier_1_0= RULE_ID ) )
            // InternalLayoutConfig.g:942:4: (lv_identifier_1_0= RULE_ID )
            {
            // InternalLayoutConfig.g:942:4: (lv_identifier_1_0= RULE_ID )
            // InternalLayoutConfig.g:943:5: lv_identifier_1_0= RULE_ID
            {
            lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_24); 

            					newLeafNode(lv_identifier_1_0, grammarAccess.getElkEdgeSectionAccess().getIdentifierIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElkEdgeSectionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"identifier",
            						lv_identifier_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalLayoutConfig.g:959:3: (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==31) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalLayoutConfig.g:960:4: otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    {
                    otherlv_2=(Token)match(input,31,FOLLOW_6); 

                    				newLeafNode(otherlv_2, grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0());
                    			
                    // InternalLayoutConfig.g:964:4: ( (otherlv_3= RULE_ID ) )
                    // InternalLayoutConfig.g:965:5: (otherlv_3= RULE_ID )
                    {
                    // InternalLayoutConfig.g:965:5: (otherlv_3= RULE_ID )
                    // InternalLayoutConfig.g:966:6: otherlv_3= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getElkEdgeSectionRule());
                    						}
                    					
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_25); 

                    						newLeafNode(otherlv_3, grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0());
                    					

                    }


                    }

                    // InternalLayoutConfig.g:977:4: (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==21) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:978:5: otherlv_4= ',' ( (otherlv_5= RULE_ID ) )
                    	    {
                    	    otherlv_4=(Token)match(input,21,FOLLOW_6); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_2_2_0());
                    	    				
                    	    // InternalLayoutConfig.g:982:5: ( (otherlv_5= RULE_ID ) )
                    	    // InternalLayoutConfig.g:983:6: (otherlv_5= RULE_ID )
                    	    {
                    	    // InternalLayoutConfig.g:983:6: (otherlv_5= RULE_ID )
                    	    // InternalLayoutConfig.g:984:7: otherlv_5= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getElkEdgeSectionRule());
                    	    							}
                    	    						
                    	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_25); 

                    	    							newLeafNode(otherlv_5, grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,19,FOLLOW_26); 

            			newLeafNode(otherlv_6, grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3());
            		
            // InternalLayoutConfig.g:1001:3: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* )
            // InternalLayoutConfig.g:1002:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )*
            {
            // InternalLayoutConfig.g:1002:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) )
            // InternalLayoutConfig.g:1003:5: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) )
            {
            // InternalLayoutConfig.g:1003:5: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) )
            // InternalLayoutConfig.g:1004:6: ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* )
            {
             
            					  getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            					
            // InternalLayoutConfig.g:1007:6: ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* )
            // InternalLayoutConfig.g:1008:7: ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )*
            {
            // InternalLayoutConfig.g:1008:7: ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )*
            loop19:
            do {
                int alt19=5;
                int LA19_0 = input.LA(1);

                if ( LA19_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                    alt19=1;
                }
                else if ( LA19_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                    alt19=2;
                }
                else if ( LA19_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                    alt19=3;
                }
                else if ( LA19_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                    alt19=4;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalLayoutConfig.g:1009:5: ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:1009:5: ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalLayoutConfig.g:1010:6: {...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0)");
            	    }
            	    // InternalLayoutConfig.g:1010:114: ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalLayoutConfig.g:1011:7: ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0);
            	    						
            	    // InternalLayoutConfig.g:1014:10: ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalLayoutConfig.g:1014:11: {...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:1014:20: (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalLayoutConfig.g:1014:21: otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_8=(Token)match(input,24,FOLLOW_10); 

            	    										newLeafNode(otherlv_8, grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0_0());
            	    									
            	    otherlv_9=(Token)match(input,17,FOLLOW_6); 

            	    										newLeafNode(otherlv_9, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_0_1());
            	    									
            	    // InternalLayoutConfig.g:1022:10: ( ( ruleQualifiedId ) )
            	    // InternalLayoutConfig.g:1023:11: ( ruleQualifiedId )
            	    {
            	    // InternalLayoutConfig.g:1023:11: ( ruleQualifiedId )
            	    // InternalLayoutConfig.g:1024:12: ruleQualifiedId
            	    {

            	    												if (current==null) {
            	    													current = createModelElement(grammarAccess.getElkEdgeSectionRule());
            	    												}
            	    											

            	    												newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0());
            	    											
            	    pushFollow(FOLLOW_26);
            	    ruleQualifiedId();

            	    state._fsp--;


            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalLayoutConfig.g:1044:5: ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:1044:5: ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalLayoutConfig.g:1045:6: {...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1)");
            	    }
            	    // InternalLayoutConfig.g:1045:114: ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalLayoutConfig.g:1046:7: ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1);
            	    						
            	    // InternalLayoutConfig.g:1049:10: ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalLayoutConfig.g:1049:11: {...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:1049:20: (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalLayoutConfig.g:1049:21: otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_11=(Token)match(input,25,FOLLOW_10); 

            	    										newLeafNode(otherlv_11, grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_0_1_0());
            	    									
            	    otherlv_12=(Token)match(input,17,FOLLOW_6); 

            	    										newLeafNode(otherlv_12, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1_1());
            	    									
            	    // InternalLayoutConfig.g:1057:10: ( ( ruleQualifiedId ) )
            	    // InternalLayoutConfig.g:1058:11: ( ruleQualifiedId )
            	    {
            	    // InternalLayoutConfig.g:1058:11: ( ruleQualifiedId )
            	    // InternalLayoutConfig.g:1059:12: ruleQualifiedId
            	    {

            	    												if (current==null) {
            	    													current = createModelElement(grammarAccess.getElkEdgeSectionRule());
            	    												}
            	    											

            	    												newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0());
            	    											
            	    pushFollow(FOLLOW_26);
            	    ruleQualifiedId();

            	    state._fsp--;


            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalLayoutConfig.g:1079:5: ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:1079:5: ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) )
            	    // InternalLayoutConfig.g:1080:6: {...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2)");
            	    }
            	    // InternalLayoutConfig.g:1080:114: ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) )
            	    // InternalLayoutConfig.g:1081:7: ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2);
            	    						
            	    // InternalLayoutConfig.g:1084:10: ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) )
            	    // InternalLayoutConfig.g:1084:11: {...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:1084:20: (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) )
            	    // InternalLayoutConfig.g:1084:21: otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) )
            	    {
            	    otherlv_14=(Token)match(input,26,FOLLOW_10); 

            	    										newLeafNode(otherlv_14, grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_0_2_0());
            	    									
            	    otherlv_15=(Token)match(input,17,FOLLOW_18); 

            	    										newLeafNode(otherlv_15, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_2_1());
            	    									
            	    // InternalLayoutConfig.g:1092:10: ( (lv_startX_16_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:1093:11: (lv_startX_16_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:1093:11: (lv_startX_16_0= ruleNumber )
            	    // InternalLayoutConfig.g:1094:12: lv_startX_16_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_0_2_2_0());
            	    											
            	    pushFollow(FOLLOW_19);
            	    lv_startX_16_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"startX",
            	    													lv_startX_16_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }

            	    otherlv_17=(Token)match(input,21,FOLLOW_18); 

            	    										newLeafNode(otherlv_17, grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_2_3());
            	    									
            	    // InternalLayoutConfig.g:1115:10: ( (lv_startY_18_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:1116:11: (lv_startY_18_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:1116:11: (lv_startY_18_0= ruleNumber )
            	    // InternalLayoutConfig.g:1117:12: lv_startY_18_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_0_2_4_0());
            	    											
            	    pushFollow(FOLLOW_26);
            	    lv_startY_18_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"startY",
            	    													lv_startY_18_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalLayoutConfig.g:1140:5: ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalLayoutConfig.g:1140:5: ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) )
            	    // InternalLayoutConfig.g:1141:6: {...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3)");
            	    }
            	    // InternalLayoutConfig.g:1141:114: ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) )
            	    // InternalLayoutConfig.g:1142:7: ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) )
            	    {

            	    							getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3);
            	    						
            	    // InternalLayoutConfig.g:1145:10: ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) )
            	    // InternalLayoutConfig.g:1145:11: {...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalLayoutConfig.g:1145:20: (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) )
            	    // InternalLayoutConfig.g:1145:21: otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) )
            	    {
            	    otherlv_19=(Token)match(input,27,FOLLOW_10); 

            	    										newLeafNode(otherlv_19, grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_0_3_0());
            	    									
            	    otherlv_20=(Token)match(input,17,FOLLOW_18); 

            	    										newLeafNode(otherlv_20, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_3_1());
            	    									
            	    // InternalLayoutConfig.g:1153:10: ( (lv_endX_21_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:1154:11: (lv_endX_21_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:1154:11: (lv_endX_21_0= ruleNumber )
            	    // InternalLayoutConfig.g:1155:12: lv_endX_21_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_0_3_2_0());
            	    											
            	    pushFollow(FOLLOW_19);
            	    lv_endX_21_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"endX",
            	    													lv_endX_21_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }

            	    otherlv_22=(Token)match(input,21,FOLLOW_18); 

            	    										newLeafNode(otherlv_22, grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_3_3());
            	    									
            	    // InternalLayoutConfig.g:1176:10: ( (lv_endY_23_0= ruleNumber ) )
            	    // InternalLayoutConfig.g:1177:11: (lv_endY_23_0= ruleNumber )
            	    {
            	    // InternalLayoutConfig.g:1177:11: (lv_endY_23_0= ruleNumber )
            	    // InternalLayoutConfig.g:1178:12: lv_endY_23_0= ruleNumber
            	    {

            	    												newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_0_3_4_0());
            	    											
            	    pushFollow(FOLLOW_26);
            	    lv_endY_23_0=ruleNumber();

            	    state._fsp--;


            	    												if (current==null) {
            	    													current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    												}
            	    												set(
            	    													current,
            	    													"endY",
            	    													lv_endY_23_0,
            	    													"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    												afterParserOrEnumRuleCall();
            	    											

            	    }


            	    }


            	    }


            	    }

            	     
            	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    						

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }


            }

             
            					  getUnorderedGroupHelper().leave(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            					

            }

            // InternalLayoutConfig.g:1208:4: (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==28) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalLayoutConfig.g:1209:5: otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )*
                    {
                    otherlv_24=(Token)match(input,28,FOLLOW_10); 

                    					newLeafNode(otherlv_24, grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_1_0());
                    				
                    otherlv_25=(Token)match(input,17,FOLLOW_18); 

                    					newLeafNode(otherlv_25, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1());
                    				
                    // InternalLayoutConfig.g:1217:5: ( (lv_bendPoints_26_0= ruleElkBendPoint ) )
                    // InternalLayoutConfig.g:1218:6: (lv_bendPoints_26_0= ruleElkBendPoint )
                    {
                    // InternalLayoutConfig.g:1218:6: (lv_bendPoints_26_0= ruleElkBendPoint )
                    // InternalLayoutConfig.g:1219:7: lv_bendPoints_26_0= ruleElkBendPoint
                    {

                    							newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_2_0());
                    						
                    pushFollow(FOLLOW_27);
                    lv_bendPoints_26_0=ruleElkBendPoint();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
                    							}
                    							add(
                    								current,
                    								"bendPoints",
                    								lv_bendPoints_26_0,
                    								"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalLayoutConfig.g:1236:5: (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==29) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // InternalLayoutConfig.g:1237:6: otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) )
                    	    {
                    	    otherlv_27=(Token)match(input,29,FOLLOW_18); 

                    	    						newLeafNode(otherlv_27, grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_1_3_0());
                    	    					
                    	    // InternalLayoutConfig.g:1241:6: ( (lv_bendPoints_28_0= ruleElkBendPoint ) )
                    	    // InternalLayoutConfig.g:1242:7: (lv_bendPoints_28_0= ruleElkBendPoint )
                    	    {
                    	    // InternalLayoutConfig.g:1242:7: (lv_bendPoints_28_0= ruleElkBendPoint )
                    	    // InternalLayoutConfig.g:1243:8: lv_bendPoints_28_0= ruleElkBendPoint
                    	    {

                    	    								newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_3_1_0());
                    	    							
                    	    pushFollow(FOLLOW_27);
                    	    lv_bendPoints_28_0=ruleElkBendPoint();

                    	    state._fsp--;


                    	    								if (current==null) {
                    	    									current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
                    	    								}
                    	    								add(
                    	    									current,
                    	    									"bendPoints",
                    	    									lv_bendPoints_28_0,
                    	    									"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    	    								afterParserOrEnumRuleCall();
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalLayoutConfig.g:1262:4: ( (lv_properties_29_0= ruleProperty ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalLayoutConfig.g:1263:5: (lv_properties_29_0= ruleProperty )
            	    {
            	    // InternalLayoutConfig.g:1263:5: (lv_properties_29_0= ruleProperty )
            	    // InternalLayoutConfig.g:1264:6: lv_properties_29_0= ruleProperty
            	    {

            	    						newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getPropertiesPropertyParserRuleCall_4_2_0());
            	    					
            	    pushFollow(FOLLOW_28);
            	    lv_properties_29_0=ruleProperty();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"properties",
            	    							lv_properties_29_0,
            	    							"org.eclipse.elk.graph.text.ElkGraph.Property");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }

            otherlv_30=(Token)match(input,23,FOLLOW_2); 

            			newLeafNode(otherlv_30, grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5());
            		

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
    // $ANTLR end "ruleElkEdgeSection"


    // $ANTLR start "entryRuleElkBendPoint"
    // InternalLayoutConfig.g:1290:1: entryRuleElkBendPoint returns [EObject current=null] : iv_ruleElkBendPoint= ruleElkBendPoint EOF ;
    public final EObject entryRuleElkBendPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkBendPoint = null;


        try {
            // InternalLayoutConfig.g:1290:53: (iv_ruleElkBendPoint= ruleElkBendPoint EOF )
            // InternalLayoutConfig.g:1291:2: iv_ruleElkBendPoint= ruleElkBendPoint EOF
            {
             newCompositeNode(grammarAccess.getElkBendPointRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkBendPoint=ruleElkBendPoint();

            state._fsp--;

             current =iv_ruleElkBendPoint; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkBendPoint"


    // $ANTLR start "ruleElkBendPoint"
    // InternalLayoutConfig.g:1297:1: ruleElkBendPoint returns [EObject current=null] : ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) ) ;
    public final EObject ruleElkBendPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_x_0_0 = null;

        AntlrDatatypeRuleToken lv_y_2_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:1303:2: ( ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) ) )
            // InternalLayoutConfig.g:1304:2: ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) )
            {
            // InternalLayoutConfig.g:1304:2: ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) )
            // InternalLayoutConfig.g:1305:3: ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) )
            {
            // InternalLayoutConfig.g:1305:3: ( (lv_x_0_0= ruleNumber ) )
            // InternalLayoutConfig.g:1306:4: (lv_x_0_0= ruleNumber )
            {
            // InternalLayoutConfig.g:1306:4: (lv_x_0_0= ruleNumber )
            // InternalLayoutConfig.g:1307:5: lv_x_0_0= ruleNumber
            {

            					newCompositeNode(grammarAccess.getElkBendPointAccess().getXNumberParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_19);
            lv_x_0_0=ruleNumber();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getElkBendPointRule());
            					}
            					set(
            						current,
            						"x",
            						lv_x_0_0,
            						"org.eclipse.elk.graph.text.ElkGraph.Number");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,21,FOLLOW_18); 

            			newLeafNode(otherlv_1, grammarAccess.getElkBendPointAccess().getCommaKeyword_1());
            		
            // InternalLayoutConfig.g:1328:3: ( (lv_y_2_0= ruleNumber ) )
            // InternalLayoutConfig.g:1329:4: (lv_y_2_0= ruleNumber )
            {
            // InternalLayoutConfig.g:1329:4: (lv_y_2_0= ruleNumber )
            // InternalLayoutConfig.g:1330:5: lv_y_2_0= ruleNumber
            {

            					newCompositeNode(grammarAccess.getElkBendPointAccess().getYNumberParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_y_2_0=ruleNumber();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getElkBendPointRule());
            					}
            					set(
            						current,
            						"y",
            						lv_y_2_0,
            						"org.eclipse.elk.graph.text.ElkGraph.Number");
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
    // $ANTLR end "ruleElkBendPoint"


    // $ANTLR start "entryRuleQualifiedId"
    // InternalLayoutConfig.g:1351:1: entryRuleQualifiedId returns [String current=null] : iv_ruleQualifiedId= ruleQualifiedId EOF ;
    public final String entryRuleQualifiedId() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedId = null;


        try {
            // InternalLayoutConfig.g:1351:51: (iv_ruleQualifiedId= ruleQualifiedId EOF )
            // InternalLayoutConfig.g:1352:2: iv_ruleQualifiedId= ruleQualifiedId EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIdRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedId=ruleQualifiedId();

            state._fsp--;

             current =iv_ruleQualifiedId.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleQualifiedId"


    // $ANTLR start "ruleQualifiedId"
    // InternalLayoutConfig.g:1358:1: ruleQualifiedId returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedId() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalLayoutConfig.g:1364:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalLayoutConfig.g:1365:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalLayoutConfig.g:1365:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalLayoutConfig.g:1366:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_29); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_0());
            		
            // InternalLayoutConfig.g:1373:3: (kw= '.' this_ID_2= RULE_ID )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==32) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalLayoutConfig.g:1374:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,32,FOLLOW_6); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_29); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop23;
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
    // $ANTLR end "ruleQualifiedId"


    // $ANTLR start "entryRuleNumber"
    // InternalLayoutConfig.g:1391:1: entryRuleNumber returns [String current=null] : iv_ruleNumber= ruleNumber EOF ;
    public final String entryRuleNumber() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumber = null;


        try {
            // InternalLayoutConfig.g:1391:46: (iv_ruleNumber= ruleNumber EOF )
            // InternalLayoutConfig.g:1392:2: iv_ruleNumber= ruleNumber EOF
            {
             newCompositeNode(grammarAccess.getNumberRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumber=ruleNumber();

            state._fsp--;

             current =iv_ruleNumber.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleNumber"


    // $ANTLR start "ruleNumber"
    // InternalLayoutConfig.g:1398:1: ruleNumber returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) ;
    public final AntlrDatatypeRuleToken ruleNumber() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIGNED_INT_0=null;
        Token this_FLOAT_1=null;


        	enterRule();

        try {
            // InternalLayoutConfig.g:1404:2: ( (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) )
            // InternalLayoutConfig.g:1405:2: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            {
            // InternalLayoutConfig.g:1405:2: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_SIGNED_INT) ) {
                alt24=1;
            }
            else if ( (LA24_0==RULE_FLOAT) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // InternalLayoutConfig.g:1406:3: this_SIGNED_INT_0= RULE_SIGNED_INT
                    {
                    this_SIGNED_INT_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_2); 

                    			current.merge(this_SIGNED_INT_0);
                    		

                    			newLeafNode(this_SIGNED_INT_0, grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:1414:3: this_FLOAT_1= RULE_FLOAT
                    {
                    this_FLOAT_1=(Token)match(input,RULE_FLOAT,FOLLOW_2); 

                    			current.merge(this_FLOAT_1);
                    		

                    			newLeafNode(this_FLOAT_1, grammarAccess.getNumberAccess().getFLOATTerminalRuleCall_1());
                    		

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
    // $ANTLR end "ruleNumber"


    // $ANTLR start "entryRuleProperty"
    // InternalLayoutConfig.g:1425:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // InternalLayoutConfig.g:1425:49: (iv_ruleProperty= ruleProperty EOF )
            // InternalLayoutConfig.g:1426:2: iv_ruleProperty= ruleProperty EOF
            {
             newCompositeNode(grammarAccess.getPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProperty=ruleProperty();

            state._fsp--;

             current =iv_ruleProperty; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalLayoutConfig.g:1432:1: ruleProperty returns [EObject current=null] : ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;

        AntlrDatatypeRuleToken lv_value_3_0 = null;

        AntlrDatatypeRuleToken lv_value_4_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:1438:2: ( ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) ) )
            // InternalLayoutConfig.g:1439:2: ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) )
            {
            // InternalLayoutConfig.g:1439:2: ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) )
            // InternalLayoutConfig.g:1440:3: ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' )
            {
            // InternalLayoutConfig.g:1440:3: ( (lv_key_0_0= rulePropertyKey ) )
            // InternalLayoutConfig.g:1441:4: (lv_key_0_0= rulePropertyKey )
            {
            // InternalLayoutConfig.g:1441:4: (lv_key_0_0= rulePropertyKey )
            // InternalLayoutConfig.g:1442:5: lv_key_0_0= rulePropertyKey
            {

            					newCompositeNode(grammarAccess.getPropertyAccess().getKeyPropertyKeyParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_10);
            lv_key_0_0=rulePropertyKey();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPropertyRule());
            					}
            					set(
            						current,
            						"key",
            						lv_key_0_0,
            						"org.eclipse.elk.graph.text.ElkGraph.PropertyKey");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,17,FOLLOW_30); 

            			newLeafNode(otherlv_1, grammarAccess.getPropertyAccess().getColonKeyword_1());
            		
            // InternalLayoutConfig.g:1463:3: ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' )
            int alt25=5;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt25=1;
                }
                break;
            case RULE_ID:
                {
                alt25=2;
                }
                break;
            case RULE_SIGNED_INT:
            case RULE_FLOAT:
                {
                alt25=3;
                }
                break;
            case 34:
            case 35:
                {
                alt25=4;
                }
                break;
            case 33:
                {
                alt25=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // InternalLayoutConfig.g:1464:4: ( (lv_value_2_0= ruleStringValue ) )
                    {
                    // InternalLayoutConfig.g:1464:4: ( (lv_value_2_0= ruleStringValue ) )
                    // InternalLayoutConfig.g:1465:5: (lv_value_2_0= ruleStringValue )
                    {
                    // InternalLayoutConfig.g:1465:5: (lv_value_2_0= ruleStringValue )
                    // InternalLayoutConfig.g:1466:6: lv_value_2_0= ruleStringValue
                    {

                    						newCompositeNode(grammarAccess.getPropertyAccess().getValueStringValueParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_value_2_0=ruleStringValue();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_2_0,
                    							"org.eclipse.elk.graph.text.ElkGraph.StringValue");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:1484:4: ( (lv_value_3_0= ruleQualifiedIdValue ) )
                    {
                    // InternalLayoutConfig.g:1484:4: ( (lv_value_3_0= ruleQualifiedIdValue ) )
                    // InternalLayoutConfig.g:1485:5: (lv_value_3_0= ruleQualifiedIdValue )
                    {
                    // InternalLayoutConfig.g:1485:5: (lv_value_3_0= ruleQualifiedIdValue )
                    // InternalLayoutConfig.g:1486:6: lv_value_3_0= ruleQualifiedIdValue
                    {

                    						newCompositeNode(grammarAccess.getPropertyAccess().getValueQualifiedIdValueParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_value_3_0=ruleQualifiedIdValue();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_3_0,
                    							"org.eclipse.elk.graph.text.ElkGraph.QualifiedIdValue");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLayoutConfig.g:1504:4: ( (lv_value_4_0= ruleNumberValue ) )
                    {
                    // InternalLayoutConfig.g:1504:4: ( (lv_value_4_0= ruleNumberValue ) )
                    // InternalLayoutConfig.g:1505:5: (lv_value_4_0= ruleNumberValue )
                    {
                    // InternalLayoutConfig.g:1505:5: (lv_value_4_0= ruleNumberValue )
                    // InternalLayoutConfig.g:1506:6: lv_value_4_0= ruleNumberValue
                    {

                    						newCompositeNode(grammarAccess.getPropertyAccess().getValueNumberValueParserRuleCall_2_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_value_4_0=ruleNumberValue();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_4_0,
                    							"org.eclipse.elk.graph.text.ElkGraph.NumberValue");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalLayoutConfig.g:1524:4: ( (lv_value_5_0= ruleBooleanValue ) )
                    {
                    // InternalLayoutConfig.g:1524:4: ( (lv_value_5_0= ruleBooleanValue ) )
                    // InternalLayoutConfig.g:1525:5: (lv_value_5_0= ruleBooleanValue )
                    {
                    // InternalLayoutConfig.g:1525:5: (lv_value_5_0= ruleBooleanValue )
                    // InternalLayoutConfig.g:1526:6: lv_value_5_0= ruleBooleanValue
                    {

                    						newCompositeNode(grammarAccess.getPropertyAccess().getValueBooleanValueParserRuleCall_2_3_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_value_5_0=ruleBooleanValue();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_5_0,
                    							"org.eclipse.elk.graph.text.ElkGraph.BooleanValue");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalLayoutConfig.g:1544:4: otherlv_6= 'null'
                    {
                    otherlv_6=(Token)match(input,33,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getPropertyAccess().getNullKeyword_2_4());
                    			

                    }
                    break;

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
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRulePropertyKey"
    // InternalLayoutConfig.g:1553:1: entryRulePropertyKey returns [String current=null] : iv_rulePropertyKey= rulePropertyKey EOF ;
    public final String entryRulePropertyKey() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyKey = null;



        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalLayoutConfig.g:1555:2: (iv_rulePropertyKey= rulePropertyKey EOF )
            // InternalLayoutConfig.g:1556:2: iv_rulePropertyKey= rulePropertyKey EOF
            {
             newCompositeNode(grammarAccess.getPropertyKeyRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePropertyKey=rulePropertyKey();

            state._fsp--;

             current =iv_rulePropertyKey.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRulePropertyKey"


    // $ANTLR start "rulePropertyKey"
    // InternalLayoutConfig.g:1565:1: rulePropertyKey returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken rulePropertyKey() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();
        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalLayoutConfig.g:1572:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalLayoutConfig.g:1573:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalLayoutConfig.g:1573:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalLayoutConfig.g:1574:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_29); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_0());
            		
            // InternalLayoutConfig.g:1581:3: (kw= '.' this_ID_2= RULE_ID )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==32) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalLayoutConfig.g:1582:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,32,FOLLOW_6); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_29); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop26;
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

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "rulePropertyKey"


    // $ANTLR start "entryRuleStringValue"
    // InternalLayoutConfig.g:1602:1: entryRuleStringValue returns [String current=null] : iv_ruleStringValue= ruleStringValue EOF ;
    public final String entryRuleStringValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringValue = null;


        try {
            // InternalLayoutConfig.g:1602:51: (iv_ruleStringValue= ruleStringValue EOF )
            // InternalLayoutConfig.g:1603:2: iv_ruleStringValue= ruleStringValue EOF
            {
             newCompositeNode(grammarAccess.getStringValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringValue=ruleStringValue();

            state._fsp--;

             current =iv_ruleStringValue.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleStringValue"


    // $ANTLR start "ruleStringValue"
    // InternalLayoutConfig.g:1609:1: ruleStringValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleStringValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalLayoutConfig.g:1615:2: (this_STRING_0= RULE_STRING )
            // InternalLayoutConfig.g:1616:2: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		current.merge(this_STRING_0);
            	

            		newLeafNode(this_STRING_0, grammarAccess.getStringValueAccess().getSTRINGTerminalRuleCall());
            	

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
    // $ANTLR end "ruleStringValue"


    // $ANTLR start "entryRuleQualifiedIdValue"
    // InternalLayoutConfig.g:1626:1: entryRuleQualifiedIdValue returns [String current=null] : iv_ruleQualifiedIdValue= ruleQualifiedIdValue EOF ;
    public final String entryRuleQualifiedIdValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedIdValue = null;


        try {
            // InternalLayoutConfig.g:1626:56: (iv_ruleQualifiedIdValue= ruleQualifiedIdValue EOF )
            // InternalLayoutConfig.g:1627:2: iv_ruleQualifiedIdValue= ruleQualifiedIdValue EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIdValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedIdValue=ruleQualifiedIdValue();

            state._fsp--;

             current =iv_ruleQualifiedIdValue.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleQualifiedIdValue"


    // $ANTLR start "ruleQualifiedIdValue"
    // InternalLayoutConfig.g:1633:1: ruleQualifiedIdValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_QualifiedId_0= ruleQualifiedId ;
    public final AntlrDatatypeRuleToken ruleQualifiedIdValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_QualifiedId_0 = null;



        	enterRule();

        try {
            // InternalLayoutConfig.g:1639:2: (this_QualifiedId_0= ruleQualifiedId )
            // InternalLayoutConfig.g:1640:2: this_QualifiedId_0= ruleQualifiedId
            {

            		newCompositeNode(grammarAccess.getQualifiedIdValueAccess().getQualifiedIdParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_QualifiedId_0=ruleQualifiedId();

            state._fsp--;


            		current.merge(this_QualifiedId_0);
            	

            		afterParserOrEnumRuleCall();
            	

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
    // $ANTLR end "ruleQualifiedIdValue"


    // $ANTLR start "entryRuleNumberValue"
    // InternalLayoutConfig.g:1653:1: entryRuleNumberValue returns [String current=null] : iv_ruleNumberValue= ruleNumberValue EOF ;
    public final String entryRuleNumberValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumberValue = null;


        try {
            // InternalLayoutConfig.g:1653:51: (iv_ruleNumberValue= ruleNumberValue EOF )
            // InternalLayoutConfig.g:1654:2: iv_ruleNumberValue= ruleNumberValue EOF
            {
             newCompositeNode(grammarAccess.getNumberValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumberValue=ruleNumberValue();

            state._fsp--;

             current =iv_ruleNumberValue.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleNumberValue"


    // $ANTLR start "ruleNumberValue"
    // InternalLayoutConfig.g:1660:1: ruleNumberValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) ;
    public final AntlrDatatypeRuleToken ruleNumberValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIGNED_INT_0=null;
        Token this_FLOAT_1=null;


        	enterRule();

        try {
            // InternalLayoutConfig.g:1666:2: ( (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) )
            // InternalLayoutConfig.g:1667:2: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            {
            // InternalLayoutConfig.g:1667:2: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==RULE_SIGNED_INT) ) {
                alt27=1;
            }
            else if ( (LA27_0==RULE_FLOAT) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // InternalLayoutConfig.g:1668:3: this_SIGNED_INT_0= RULE_SIGNED_INT
                    {
                    this_SIGNED_INT_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_2); 

                    			current.merge(this_SIGNED_INT_0);
                    		

                    			newLeafNode(this_SIGNED_INT_0, grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:1676:3: this_FLOAT_1= RULE_FLOAT
                    {
                    this_FLOAT_1=(Token)match(input,RULE_FLOAT,FOLLOW_2); 

                    			current.merge(this_FLOAT_1);
                    		

                    			newLeafNode(this_FLOAT_1, grammarAccess.getNumberValueAccess().getFLOATTerminalRuleCall_1());
                    		

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
    // $ANTLR end "ruleNumberValue"


    // $ANTLR start "entryRuleBooleanValue"
    // InternalLayoutConfig.g:1687:1: entryRuleBooleanValue returns [String current=null] : iv_ruleBooleanValue= ruleBooleanValue EOF ;
    public final String entryRuleBooleanValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBooleanValue = null;


        try {
            // InternalLayoutConfig.g:1687:52: (iv_ruleBooleanValue= ruleBooleanValue EOF )
            // InternalLayoutConfig.g:1688:2: iv_ruleBooleanValue= ruleBooleanValue EOF
            {
             newCompositeNode(grammarAccess.getBooleanValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanValue=ruleBooleanValue();

            state._fsp--;

             current =iv_ruleBooleanValue.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleBooleanValue"


    // $ANTLR start "ruleBooleanValue"
    // InternalLayoutConfig.g:1694:1: ruleBooleanValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleBooleanValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLayoutConfig.g:1700:2: ( (kw= 'true' | kw= 'false' ) )
            // InternalLayoutConfig.g:1701:2: (kw= 'true' | kw= 'false' )
            {
            // InternalLayoutConfig.g:1701:2: (kw= 'true' | kw= 'false' )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==34) ) {
                alt28=1;
            }
            else if ( (LA28_0==35) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // InternalLayoutConfig.g:1702:3: kw= 'true'
                    {
                    kw=(Token)match(input,34,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBooleanValueAccess().getTrueKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalLayoutConfig.g:1708:3: kw= 'false'
                    {
                    kw=(Token)match(input,35,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBooleanValueAccess().getFalseKeyword_1());
                    		

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
    // $ANTLR end "ruleBooleanValue"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000000000000C010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000058010L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000018010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000D00000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000005F800010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x000000001F000012L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000020000012L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000080080000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000280000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x000000001F800010L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000020800010L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000E000000F0L});

}
