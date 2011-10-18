package de.cau.cs.kieler.kiml.graphviz.dot.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.kiml.graphviz.dot.services.GraphvizDotGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGraphvizDotParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_FLOAT", "RULE_STRING", "RULE_PREC_LINE", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'strict'", "'{'", "'}'", "';'", "'['", "','", "']'", "'subgraph'", "'='", "':'", "'->'", "'--'", "'graph'", "'digraph'", "'node'", "'edge'", "'n'", "'ne'", "'e'", "'se'", "'s'", "'sw'", "'w'", "'nw'", "'c'", "'_'"
    };
    public static final int RULE_ID=4;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_PREC_LINE=8;
    public static final int RULE_FLOAT=6;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int RULE_STRING=7;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_INT=5;
    public static final int RULE_WS=11;

    // delegates
    // delegators


        public InternalGraphvizDotParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalGraphvizDotParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalGraphvizDotParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g"; }



     	private GraphvizDotGrammarAccess grammarAccess;
     	
        public InternalGraphvizDotParser(TokenStream input, GraphvizDotGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "GraphvizModel";	
       	}
       	
       	@Override
       	protected GraphvizDotGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleGraphvizModel"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:68:1: entryRuleGraphvizModel returns [EObject current=null] : iv_ruleGraphvizModel= ruleGraphvizModel EOF ;
    public final EObject entryRuleGraphvizModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGraphvizModel = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:69:2: (iv_ruleGraphvizModel= ruleGraphvizModel EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:70:2: iv_ruleGraphvizModel= ruleGraphvizModel EOF
            {
             newCompositeNode(grammarAccess.getGraphvizModelRule()); 
            pushFollow(FOLLOW_ruleGraphvizModel_in_entryRuleGraphvizModel75);
            iv_ruleGraphvizModel=ruleGraphvizModel();

            state._fsp--;

             current =iv_ruleGraphvizModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGraphvizModel85); 

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
    // $ANTLR end "entryRuleGraphvizModel"


    // $ANTLR start "ruleGraphvizModel"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:77:1: ruleGraphvizModel returns [EObject current=null] : ( (lv_graphs_0_0= ruleGraph ) )* ;
    public final EObject ruleGraphvizModel() throws RecognitionException {
        EObject current = null;

        EObject lv_graphs_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:80:28: ( ( (lv_graphs_0_0= ruleGraph ) )* )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:81:1: ( (lv_graphs_0_0= ruleGraph ) )*
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:81:1: ( (lv_graphs_0_0= ruleGraph ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13||(LA1_0>=25 && LA1_0<=26)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:82:1: (lv_graphs_0_0= ruleGraph )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:82:1: (lv_graphs_0_0= ruleGraph )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:83:3: lv_graphs_0_0= ruleGraph
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGraphvizModelAccess().getGraphsGraphParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleGraph_in_ruleGraphvizModel130);
            	    lv_graphs_0_0=ruleGraph();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGraphvizModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"graphs",
            	            		lv_graphs_0_0, 
            	            		"Graph");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


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
    // $ANTLR end "ruleGraphvizModel"


    // $ANTLR start "entryRuleGraph"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:107:1: entryRuleGraph returns [EObject current=null] : iv_ruleGraph= ruleGraph EOF ;
    public final EObject entryRuleGraph() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGraph = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:108:2: (iv_ruleGraph= ruleGraph EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:109:2: iv_ruleGraph= ruleGraph EOF
            {
             newCompositeNode(grammarAccess.getGraphRule()); 
            pushFollow(FOLLOW_ruleGraph_in_entryRuleGraph166);
            iv_ruleGraph=ruleGraph();

            state._fsp--;

             current =iv_ruleGraph; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGraph176); 

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
    // $ANTLR end "entryRuleGraph"


    // $ANTLR start "ruleGraph"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:116:1: ruleGraph returns [EObject current=null] : ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' ) ;
    public final EObject ruleGraph() throws RecognitionException {
        EObject current = null;

        Token lv_strict_0_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_type_1_0 = null;

        EObject lv_statements_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:119:28: ( ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:120:1: ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:120:1: ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:120:2: ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:120:2: ( (lv_strict_0_0= 'strict' ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:121:1: (lv_strict_0_0= 'strict' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:121:1: (lv_strict_0_0= 'strict' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:122:3: lv_strict_0_0= 'strict'
                    {
                    lv_strict_0_0=(Token)match(input,13,FOLLOW_13_in_ruleGraph219); 

                            newLeafNode(lv_strict_0_0, grammarAccess.getGraphAccess().getStrictStrictKeyword_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getGraphRule());
                    	        }
                           		setWithLastConsumed(current, "strict", true, "strict");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:135:3: ( (lv_type_1_0= ruleGraphType ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:136:1: (lv_type_1_0= ruleGraphType )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:136:1: (lv_type_1_0= ruleGraphType )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:137:3: lv_type_1_0= ruleGraphType
            {
             
            	        newCompositeNode(grammarAccess.getGraphAccess().getTypeGraphTypeEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleGraphType_in_ruleGraph254);
            lv_type_1_0=ruleGraphType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGraphRule());
            	        }
                   		set(
                   			current, 
                   			"type",
                    		lv_type_1_0, 
                    		"GraphType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:153:2: ( (lv_name_2_0= RULE_ID ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:154:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:154:1: (lv_name_2_0= RULE_ID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:155:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGraph271); 

                    			newLeafNode(lv_name_2_0, grammarAccess.getGraphAccess().getNameIDTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getGraphRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_2_0, 
                            		"ID");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,14,FOLLOW_14_in_ruleGraph289); 

                	newLeafNode(otherlv_3, grammarAccess.getGraphAccess().getLeftCurlyBracketKeyword_3());
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:175:1: ( (lv_statements_4_0= ruleStatement ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=RULE_ID && LA4_0<=RULE_STRING)||LA4_0==20||LA4_0==25||(LA4_0>=27 && LA4_0<=28)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:176:1: (lv_statements_4_0= ruleStatement )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:176:1: (lv_statements_4_0= ruleStatement )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:177:3: lv_statements_4_0= ruleStatement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGraphAccess().getStatementsStatementParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleStatement_in_ruleGraph310);
            	    lv_statements_4_0=ruleStatement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGraphRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"statements",
            	            		lv_statements_4_0, 
            	            		"Statement");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_5=(Token)match(input,15,FOLLOW_15_in_ruleGraph323); 

                	newLeafNode(otherlv_5, grammarAccess.getGraphAccess().getRightCurlyBracketKeyword_5());
                

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
    // $ANTLR end "ruleGraph"


    // $ANTLR start "entryRuleStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:205:1: entryRuleStatement returns [EObject current=null] : iv_ruleStatement= ruleStatement EOF ;
    public final EObject entryRuleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:206:2: (iv_ruleStatement= ruleStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:207:2: iv_ruleStatement= ruleStatement EOF
            {
             newCompositeNode(grammarAccess.getStatementRule()); 
            pushFollow(FOLLOW_ruleStatement_in_entryRuleStatement359);
            iv_ruleStatement=ruleStatement();

            state._fsp--;

             current =iv_ruleStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStatement369); 

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
    // $ANTLR end "entryRuleStatement"


    // $ANTLR start "ruleStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:214:1: ruleStatement returns [EObject current=null] : ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) (otherlv_5= ';' )? ) ;
    public final EObject ruleStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_5=null;
        EObject this_EdgeStatement_0 = null;

        EObject this_NodeStatement_1 = null;

        EObject this_Attribute_2 = null;

        EObject this_AttributeStatement_3 = null;

        EObject this_Subgraph_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:217:28: ( ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) (otherlv_5= ';' )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:218:1: ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) (otherlv_5= ';' )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:218:1: ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) (otherlv_5= ';' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:218:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) (otherlv_5= ';' )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:218:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )
            int alt5=5;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:219:5: this_EdgeStatement_0= ruleEdgeStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getEdgeStatementParserRuleCall_0_0()); 
                        
                    pushFollow(FOLLOW_ruleEdgeStatement_in_ruleStatement417);
                    this_EdgeStatement_0=ruleEdgeStatement();

                    state._fsp--;

                     
                            current = this_EdgeStatement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:229:5: this_NodeStatement_1= ruleNodeStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getNodeStatementParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleNodeStatement_in_ruleStatement444);
                    this_NodeStatement_1=ruleNodeStatement();

                    state._fsp--;

                     
                            current = this_NodeStatement_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:239:5: this_Attribute_2= ruleAttribute
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getAttributeParserRuleCall_0_2()); 
                        
                    pushFollow(FOLLOW_ruleAttribute_in_ruleStatement471);
                    this_Attribute_2=ruleAttribute();

                    state._fsp--;

                     
                            current = this_Attribute_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:249:5: this_AttributeStatement_3= ruleAttributeStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getAttributeStatementParserRuleCall_0_3()); 
                        
                    pushFollow(FOLLOW_ruleAttributeStatement_in_ruleStatement498);
                    this_AttributeStatement_3=ruleAttributeStatement();

                    state._fsp--;

                     
                            current = this_AttributeStatement_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:259:5: this_Subgraph_4= ruleSubgraph
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getSubgraphParserRuleCall_0_4()); 
                        
                    pushFollow(FOLLOW_ruleSubgraph_in_ruleStatement525);
                    this_Subgraph_4=ruleSubgraph();

                    state._fsp--;

                     
                            current = this_Subgraph_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:267:2: (otherlv_5= ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==16) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:267:4: otherlv_5= ';'
                    {
                    otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleStatement538); 

                        	newLeafNode(otherlv_5, grammarAccess.getStatementAccess().getSemicolonKeyword_1());
                        

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
    // $ANTLR end "ruleStatement"


    // $ANTLR start "entryRuleEdgeStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:279:1: entryRuleEdgeStatement returns [EObject current=null] : iv_ruleEdgeStatement= ruleEdgeStatement EOF ;
    public final EObject entryRuleEdgeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:280:2: (iv_ruleEdgeStatement= ruleEdgeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:281:2: iv_ruleEdgeStatement= ruleEdgeStatement EOF
            {
             newCompositeNode(grammarAccess.getEdgeStatementRule()); 
            pushFollow(FOLLOW_ruleEdgeStatement_in_entryRuleEdgeStatement576);
            iv_ruleEdgeStatement=ruleEdgeStatement();

            state._fsp--;

             current =iv_ruleEdgeStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEdgeStatement586); 

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
    // $ANTLR end "entryRuleEdgeStatement"


    // $ANTLR start "ruleEdgeStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:288:1: ruleEdgeStatement returns [EObject current=null] : ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ (otherlv_2= '[' ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )? otherlv_6= ']' )? ) ;
    public final EObject ruleEdgeStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_sourceNode_0_0 = null;

        EObject lv_edgeTargets_1_0 = null;

        EObject lv_attributes_3_0 = null;

        EObject lv_attributes_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:291:28: ( ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ (otherlv_2= '[' ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )? otherlv_6= ']' )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:292:1: ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ (otherlv_2= '[' ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )? otherlv_6= ']' )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:292:1: ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ (otherlv_2= '[' ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )? otherlv_6= ']' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:292:2: ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ (otherlv_2= '[' ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )? otherlv_6= ']' )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:292:2: ( (lv_sourceNode_0_0= ruleNode ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:293:1: (lv_sourceNode_0_0= ruleNode )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:293:1: (lv_sourceNode_0_0= ruleNode )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:294:3: lv_sourceNode_0_0= ruleNode
            {
             
            	        newCompositeNode(grammarAccess.getEdgeStatementAccess().getSourceNodeNodeParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleNode_in_ruleEdgeStatement632);
            lv_sourceNode_0_0=ruleNode();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEdgeStatementRule());
            	        }
                   		set(
                   			current, 
                   			"sourceNode",
                    		lv_sourceNode_0_0, 
                    		"Node");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:310:2: ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=23 && LA7_0<=24)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:311:1: (lv_edgeTargets_1_0= ruleEdgeTarget )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:311:1: (lv_edgeTargets_1_0= ruleEdgeTarget )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:312:3: lv_edgeTargets_1_0= ruleEdgeTarget
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getEdgeStatementAccess().getEdgeTargetsEdgeTargetParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleEdgeTarget_in_ruleEdgeStatement653);
            	    lv_edgeTargets_1_0=ruleEdgeTarget();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getEdgeStatementRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"edgeTargets",
            	            		lv_edgeTargets_1_0, 
            	            		"EdgeTarget");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:328:3: (otherlv_2= '[' ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )? otherlv_6= ']' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==17) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:328:5: otherlv_2= '[' ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )? otherlv_6= ']'
                    {
                    otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleEdgeStatement667); 

                        	newLeafNode(otherlv_2, grammarAccess.getEdgeStatementAccess().getLeftSquareBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:332:1: ( ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0>=RULE_ID && LA10_0<=RULE_STRING)) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:332:2: ( (lv_attributes_3_0= ruleListAttribute ) ) ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )*
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:332:2: ( (lv_attributes_3_0= ruleListAttribute ) )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:333:1: (lv_attributes_3_0= ruleListAttribute )
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:333:1: (lv_attributes_3_0= ruleListAttribute )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:334:3: lv_attributes_3_0= ruleListAttribute
                            {
                             
                            	        newCompositeNode(grammarAccess.getEdgeStatementAccess().getAttributesListAttributeParserRuleCall_2_1_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleListAttribute_in_ruleEdgeStatement689);
                            lv_attributes_3_0=ruleListAttribute();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getEdgeStatementRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"attributes",
                                    		lv_attributes_3_0, 
                                    		"ListAttribute");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:350:2: ( (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )*
                            loop9:
                            do {
                                int alt9=2;
                                int LA9_0 = input.LA(1);

                                if ( ((LA9_0>=RULE_ID && LA9_0<=RULE_STRING)||LA9_0==18) ) {
                                    alt9=1;
                                }


                                switch (alt9) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:350:3: (otherlv_4= ',' )? ( (lv_attributes_5_0= ruleListAttribute ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:350:3: (otherlv_4= ',' )?
                            	    int alt8=2;
                            	    int LA8_0 = input.LA(1);

                            	    if ( (LA8_0==18) ) {
                            	        alt8=1;
                            	    }
                            	    switch (alt8) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:350:5: otherlv_4= ','
                            	            {
                            	            otherlv_4=(Token)match(input,18,FOLLOW_18_in_ruleEdgeStatement703); 

                            	                	newLeafNode(otherlv_4, grammarAccess.getEdgeStatementAccess().getCommaKeyword_2_1_1_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:354:3: ( (lv_attributes_5_0= ruleListAttribute ) )
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:355:1: (lv_attributes_5_0= ruleListAttribute )
                            	    {
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:355:1: (lv_attributes_5_0= ruleListAttribute )
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:356:3: lv_attributes_5_0= ruleListAttribute
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getEdgeStatementAccess().getAttributesListAttributeParserRuleCall_2_1_1_1_0()); 
                            	    	    
                            	    pushFollow(FOLLOW_ruleListAttribute_in_ruleEdgeStatement726);
                            	    lv_attributes_5_0=ruleListAttribute();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getEdgeStatementRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"attributes",
                            	            		lv_attributes_5_0, 
                            	            		"ListAttribute");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop9;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_6=(Token)match(input,19,FOLLOW_19_in_ruleEdgeStatement742); 

                        	newLeafNode(otherlv_6, grammarAccess.getEdgeStatementAccess().getRightSquareBracketKeyword_2_2());
                        

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
    // $ANTLR end "ruleEdgeStatement"


    // $ANTLR start "entryRuleEdgeTarget"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:384:1: entryRuleEdgeTarget returns [EObject current=null] : iv_ruleEdgeTarget= ruleEdgeTarget EOF ;
    public final EObject entryRuleEdgeTarget() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeTarget = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:385:2: (iv_ruleEdgeTarget= ruleEdgeTarget EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:386:2: iv_ruleEdgeTarget= ruleEdgeTarget EOF
            {
             newCompositeNode(grammarAccess.getEdgeTargetRule()); 
            pushFollow(FOLLOW_ruleEdgeTarget_in_entryRuleEdgeTarget780);
            iv_ruleEdgeTarget=ruleEdgeTarget();

            state._fsp--;

             current =iv_ruleEdgeTarget; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEdgeTarget790); 

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
    // $ANTLR end "entryRuleEdgeTarget"


    // $ANTLR start "ruleEdgeTarget"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:393:1: ruleEdgeTarget returns [EObject current=null] : ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) ) ;
    public final EObject ruleEdgeTarget() throws RecognitionException {
        EObject current = null;

        Enumerator lv_operator_0_0 = null;

        EObject lv_targetSubgraph_1_0 = null;

        EObject lv_targetnode_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:396:28: ( ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:397:1: ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:397:1: ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:397:2: ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:397:2: ( (lv_operator_0_0= ruleEdgeOperator ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:398:1: (lv_operator_0_0= ruleEdgeOperator )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:398:1: (lv_operator_0_0= ruleEdgeOperator )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:399:3: lv_operator_0_0= ruleEdgeOperator
            {
             
            	        newCompositeNode(grammarAccess.getEdgeTargetAccess().getOperatorEdgeOperatorEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleEdgeOperator_in_ruleEdgeTarget836);
            lv_operator_0_0=ruleEdgeOperator();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEdgeTargetRule());
            	        }
                   		set(
                   			current, 
                   			"operator",
                    		lv_operator_0_0, 
                    		"EdgeOperator");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:415:2: ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==20) ) {
                alt12=1;
            }
            else if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_STRING)) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:415:3: ( (lv_targetSubgraph_1_0= ruleSubgraph ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:415:3: ( (lv_targetSubgraph_1_0= ruleSubgraph ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:416:1: (lv_targetSubgraph_1_0= ruleSubgraph )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:416:1: (lv_targetSubgraph_1_0= ruleSubgraph )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:417:3: lv_targetSubgraph_1_0= ruleSubgraph
                    {
                     
                    	        newCompositeNode(grammarAccess.getEdgeTargetAccess().getTargetSubgraphSubgraphParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSubgraph_in_ruleEdgeTarget858);
                    lv_targetSubgraph_1_0=ruleSubgraph();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getEdgeTargetRule());
                    	        }
                           		set(
                           			current, 
                           			"targetSubgraph",
                            		lv_targetSubgraph_1_0, 
                            		"Subgraph");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:434:6: ( (lv_targetnode_2_0= ruleNode ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:434:6: ( (lv_targetnode_2_0= ruleNode ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:435:1: (lv_targetnode_2_0= ruleNode )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:435:1: (lv_targetnode_2_0= ruleNode )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:436:3: lv_targetnode_2_0= ruleNode
                    {
                     
                    	        newCompositeNode(grammarAccess.getEdgeTargetAccess().getTargetnodeNodeParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleNode_in_ruleEdgeTarget885);
                    lv_targetnode_2_0=ruleNode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getEdgeTargetRule());
                    	        }
                           		set(
                           			current, 
                           			"targetnode",
                            		lv_targetnode_2_0, 
                            		"Node");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


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
    // $ANTLR end "ruleEdgeTarget"


    // $ANTLR start "entryRuleNodeStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:460:1: entryRuleNodeStatement returns [EObject current=null] : iv_ruleNodeStatement= ruleNodeStatement EOF ;
    public final EObject entryRuleNodeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:461:2: (iv_ruleNodeStatement= ruleNodeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:462:2: iv_ruleNodeStatement= ruleNodeStatement EOF
            {
             newCompositeNode(grammarAccess.getNodeStatementRule()); 
            pushFollow(FOLLOW_ruleNodeStatement_in_entryRuleNodeStatement922);
            iv_ruleNodeStatement=ruleNodeStatement();

            state._fsp--;

             current =iv_ruleNodeStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNodeStatement932); 

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
    // $ANTLR end "entryRuleNodeStatement"


    // $ANTLR start "ruleNodeStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:469:1: ruleNodeStatement returns [EObject current=null] : ( ( (lv_node_0_0= ruleNode ) ) (otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )? ) ;
    public final EObject ruleNodeStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_node_0_0 = null;

        EObject lv_attributes_2_0 = null;

        EObject lv_attributes_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:472:28: ( ( ( (lv_node_0_0= ruleNode ) ) (otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:473:1: ( ( (lv_node_0_0= ruleNode ) ) (otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:473:1: ( ( (lv_node_0_0= ruleNode ) ) (otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:473:2: ( (lv_node_0_0= ruleNode ) ) (otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:473:2: ( (lv_node_0_0= ruleNode ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:474:1: (lv_node_0_0= ruleNode )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:474:1: (lv_node_0_0= ruleNode )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:475:3: lv_node_0_0= ruleNode
            {
             
            	        newCompositeNode(grammarAccess.getNodeStatementAccess().getNodeNodeParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleNode_in_ruleNodeStatement978);
            lv_node_0_0=ruleNode();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNodeStatementRule());
            	        }
                   		set(
                   			current, 
                   			"node",
                    		lv_node_0_0, 
                    		"Node");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:491:2: (otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==17) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:491:4: otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']'
                    {
                    otherlv_1=(Token)match(input,17,FOLLOW_17_in_ruleNodeStatement991); 

                        	newLeafNode(otherlv_1, grammarAccess.getNodeStatementAccess().getLeftSquareBracketKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:495:1: ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>=RULE_ID && LA15_0<=RULE_STRING)) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:495:2: ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )*
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:495:2: ( (lv_attributes_2_0= ruleListAttribute ) )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:496:1: (lv_attributes_2_0= ruleListAttribute )
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:496:1: (lv_attributes_2_0= ruleListAttribute )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:497:3: lv_attributes_2_0= ruleListAttribute
                            {
                             
                            	        newCompositeNode(grammarAccess.getNodeStatementAccess().getAttributesListAttributeParserRuleCall_1_1_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleListAttribute_in_ruleNodeStatement1013);
                            lv_attributes_2_0=ruleListAttribute();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getNodeStatementRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"attributes",
                                    		lv_attributes_2_0, 
                                    		"ListAttribute");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:513:2: ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )*
                            loop14:
                            do {
                                int alt14=2;
                                int LA14_0 = input.LA(1);

                                if ( ((LA14_0>=RULE_ID && LA14_0<=RULE_STRING)||LA14_0==18) ) {
                                    alt14=1;
                                }


                                switch (alt14) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:513:3: (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:513:3: (otherlv_3= ',' )?
                            	    int alt13=2;
                            	    int LA13_0 = input.LA(1);

                            	    if ( (LA13_0==18) ) {
                            	        alt13=1;
                            	    }
                            	    switch (alt13) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:513:5: otherlv_3= ','
                            	            {
                            	            otherlv_3=(Token)match(input,18,FOLLOW_18_in_ruleNodeStatement1027); 

                            	                	newLeafNode(otherlv_3, grammarAccess.getNodeStatementAccess().getCommaKeyword_1_1_1_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:517:3: ( (lv_attributes_4_0= ruleListAttribute ) )
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:518:1: (lv_attributes_4_0= ruleListAttribute )
                            	    {
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:518:1: (lv_attributes_4_0= ruleListAttribute )
                            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:519:3: lv_attributes_4_0= ruleListAttribute
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getNodeStatementAccess().getAttributesListAttributeParserRuleCall_1_1_1_1_0()); 
                            	    	    
                            	    pushFollow(FOLLOW_ruleListAttribute_in_ruleNodeStatement1050);
                            	    lv_attributes_4_0=ruleListAttribute();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getNodeStatementRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"attributes",
                            	            		lv_attributes_4_0, 
                            	            		"ListAttribute");
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

                    otherlv_5=(Token)match(input,19,FOLLOW_19_in_ruleNodeStatement1066); 

                        	newLeafNode(otherlv_5, grammarAccess.getNodeStatementAccess().getRightSquareBracketKeyword_1_2());
                        

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
    // $ANTLR end "ruleNodeStatement"


    // $ANTLR start "entryRuleAttributeStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:547:1: entryRuleAttributeStatement returns [EObject current=null] : iv_ruleAttributeStatement= ruleAttributeStatement EOF ;
    public final EObject entryRuleAttributeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:548:2: (iv_ruleAttributeStatement= ruleAttributeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:549:2: iv_ruleAttributeStatement= ruleAttributeStatement EOF
            {
             newCompositeNode(grammarAccess.getAttributeStatementRule()); 
            pushFollow(FOLLOW_ruleAttributeStatement_in_entryRuleAttributeStatement1104);
            iv_ruleAttributeStatement=ruleAttributeStatement();

            state._fsp--;

             current =iv_ruleAttributeStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeStatement1114); 

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
    // $ANTLR end "entryRuleAttributeStatement"


    // $ANTLR start "ruleAttributeStatement"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:556:1: ruleAttributeStatement returns [EObject current=null] : ( ( (lv_type_0_0= ruleAttributeType ) ) otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' ) ;
    public final EObject ruleAttributeStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_type_0_0 = null;

        EObject lv_attributes_2_0 = null;

        EObject lv_attributes_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:559:28: ( ( ( (lv_type_0_0= ruleAttributeType ) ) otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:560:1: ( ( (lv_type_0_0= ruleAttributeType ) ) otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:560:1: ( ( (lv_type_0_0= ruleAttributeType ) ) otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:560:2: ( (lv_type_0_0= ruleAttributeType ) ) otherlv_1= '[' ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )? otherlv_5= ']'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:560:2: ( (lv_type_0_0= ruleAttributeType ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:561:1: (lv_type_0_0= ruleAttributeType )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:561:1: (lv_type_0_0= ruleAttributeType )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:562:3: lv_type_0_0= ruleAttributeType
            {
             
            	        newCompositeNode(grammarAccess.getAttributeStatementAccess().getTypeAttributeTypeEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAttributeType_in_ruleAttributeStatement1160);
            lv_type_0_0=ruleAttributeType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAttributeStatementRule());
            	        }
                   		set(
                   			current, 
                   			"type",
                    		lv_type_0_0, 
                    		"AttributeType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,17,FOLLOW_17_in_ruleAttributeStatement1172); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeStatementAccess().getLeftSquareBracketKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:582:1: ( ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_ID && LA19_0<=RULE_STRING)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:582:2: ( (lv_attributes_2_0= ruleListAttribute ) ) ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )*
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:582:2: ( (lv_attributes_2_0= ruleListAttribute ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:583:1: (lv_attributes_2_0= ruleListAttribute )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:583:1: (lv_attributes_2_0= ruleListAttribute )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:584:3: lv_attributes_2_0= ruleListAttribute
                    {
                     
                    	        newCompositeNode(grammarAccess.getAttributeStatementAccess().getAttributesListAttributeParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleListAttribute_in_ruleAttributeStatement1194);
                    lv_attributes_2_0=ruleListAttribute();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAttributeStatementRule());
                    	        }
                           		add(
                           			current, 
                           			"attributes",
                            		lv_attributes_2_0, 
                            		"ListAttribute");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:600:2: ( (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>=RULE_ID && LA18_0<=RULE_STRING)||LA18_0==18) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:600:3: (otherlv_3= ',' )? ( (lv_attributes_4_0= ruleListAttribute ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:600:3: (otherlv_3= ',' )?
                    	    int alt17=2;
                    	    int LA17_0 = input.LA(1);

                    	    if ( (LA17_0==18) ) {
                    	        alt17=1;
                    	    }
                    	    switch (alt17) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:600:5: otherlv_3= ','
                    	            {
                    	            otherlv_3=(Token)match(input,18,FOLLOW_18_in_ruleAttributeStatement1208); 

                    	                	newLeafNode(otherlv_3, grammarAccess.getAttributeStatementAccess().getCommaKeyword_2_1_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:604:3: ( (lv_attributes_4_0= ruleListAttribute ) )
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:605:1: (lv_attributes_4_0= ruleListAttribute )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:605:1: (lv_attributes_4_0= ruleListAttribute )
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:606:3: lv_attributes_4_0= ruleListAttribute
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAttributeStatementAccess().getAttributesListAttributeParserRuleCall_2_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleListAttribute_in_ruleAttributeStatement1231);
                    	    lv_attributes_4_0=ruleListAttribute();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getAttributeStatementRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"attributes",
                    	            		lv_attributes_4_0, 
                    	            		"ListAttribute");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,19,FOLLOW_19_in_ruleAttributeStatement1247); 

                	newLeafNode(otherlv_5, grammarAccess.getAttributeStatementAccess().getRightSquareBracketKeyword_3());
                

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
    // $ANTLR end "ruleAttributeStatement"


    // $ANTLR start "entryRuleSubgraph"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:634:1: entryRuleSubgraph returns [EObject current=null] : iv_ruleSubgraph= ruleSubgraph EOF ;
    public final EObject entryRuleSubgraph() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubgraph = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:635:2: (iv_ruleSubgraph= ruleSubgraph EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:636:2: iv_ruleSubgraph= ruleSubgraph EOF
            {
             newCompositeNode(grammarAccess.getSubgraphRule()); 
            pushFollow(FOLLOW_ruleSubgraph_in_entryRuleSubgraph1283);
            iv_ruleSubgraph=ruleSubgraph();

            state._fsp--;

             current =iv_ruleSubgraph; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubgraph1293); 

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
    // $ANTLR end "entryRuleSubgraph"


    // $ANTLR start "ruleSubgraph"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:643:1: ruleSubgraph returns [EObject current=null] : ( () otherlv_1= 'subgraph' ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' ) ;
    public final EObject ruleSubgraph() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_statements_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:646:28: ( ( () otherlv_1= 'subgraph' ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:647:1: ( () otherlv_1= 'subgraph' ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:647:1: ( () otherlv_1= 'subgraph' ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:647:2: () otherlv_1= 'subgraph' ( (lv_name_2_0= RULE_ID ) )? otherlv_3= '{' ( (lv_statements_4_0= ruleStatement ) )* otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:647:2: ()
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:648:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSubgraphAccess().getSubgraphAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleSubgraph1339); 

                	newLeafNode(otherlv_1, grammarAccess.getSubgraphAccess().getSubgraphKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:657:1: ( (lv_name_2_0= RULE_ID ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ID) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:658:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:658:1: (lv_name_2_0= RULE_ID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:659:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSubgraph1356); 

                    			newLeafNode(lv_name_2_0, grammarAccess.getSubgraphAccess().getNameIDTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSubgraphRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_2_0, 
                            		"ID");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,14,FOLLOW_14_in_ruleSubgraph1374); 

                	newLeafNode(otherlv_3, grammarAccess.getSubgraphAccess().getLeftCurlyBracketKeyword_3());
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:679:1: ( (lv_statements_4_0= ruleStatement ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=RULE_ID && LA21_0<=RULE_STRING)||LA21_0==20||LA21_0==25||(LA21_0>=27 && LA21_0<=28)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:680:1: (lv_statements_4_0= ruleStatement )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:680:1: (lv_statements_4_0= ruleStatement )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:681:3: lv_statements_4_0= ruleStatement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSubgraphAccess().getStatementsStatementParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleStatement_in_ruleSubgraph1395);
            	    lv_statements_4_0=ruleStatement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSubgraphRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"statements",
            	            		lv_statements_4_0, 
            	            		"Statement");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            otherlv_5=(Token)match(input,15,FOLLOW_15_in_ruleSubgraph1408); 

                	newLeafNode(otherlv_5, grammarAccess.getSubgraphAccess().getRightCurlyBracketKeyword_5());
                

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
    // $ANTLR end "ruleSubgraph"


    // $ANTLR start "entryRuleAttribute"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:709:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:710:2: (iv_ruleAttribute= ruleAttribute EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:711:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute1444);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute1454); 

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
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:718:1: ruleAttribute returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:721:28: ( ( ( (lv_name_0_0= ruleDotID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:722:1: ( ( (lv_name_0_0= ruleDotID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:722:1: ( ( (lv_name_0_0= ruleDotID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:722:2: ( (lv_name_0_0= ruleDotID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:722:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:723:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:723:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:724:3: lv_name_0_0= ruleDotID
            {
             
            	        newCompositeNode(grammarAccess.getAttributeAccess().getNameDotIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleAttribute1500);
            lv_name_0_0=ruleDotID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAttributeRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"DotID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleAttribute1512); 

                	newLeafNode(otherlv_1, grammarAccess.getAttributeAccess().getEqualsSignKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:744:1: ( (lv_value_2_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:745:1: (lv_value_2_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:745:1: (lv_value_2_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:746:3: lv_value_2_0= ruleDotID
            {
             
            	        newCompositeNode(grammarAccess.getAttributeAccess().getValueDotIDParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleAttribute1533);
            lv_value_2_0=ruleDotID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAttributeRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_2_0, 
                    		"DotID");
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
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleListAttribute"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:770:1: entryRuleListAttribute returns [EObject current=null] : iv_ruleListAttribute= ruleListAttribute EOF ;
    public final EObject entryRuleListAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListAttribute = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:771:2: (iv_ruleListAttribute= ruleListAttribute EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:772:2: iv_ruleListAttribute= ruleListAttribute EOF
            {
             newCompositeNode(grammarAccess.getListAttributeRule()); 
            pushFollow(FOLLOW_ruleListAttribute_in_entryRuleListAttribute1569);
            iv_ruleListAttribute=ruleListAttribute();

            state._fsp--;

             current =iv_ruleListAttribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleListAttribute1579); 

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
    // $ANTLR end "entryRuleListAttribute"


    // $ANTLR start "ruleListAttribute"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:779:1: ruleListAttribute returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )? ) ;
    public final EObject ruleListAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:782:28: ( ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:783:1: ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:783:1: ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:783:2: ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:783:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:784:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:784:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:785:3: lv_name_0_0= ruleDotID
            {
             
            	        newCompositeNode(grammarAccess.getListAttributeAccess().getNameDotIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleListAttribute1625);
            lv_name_0_0=ruleDotID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getListAttributeRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"DotID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:801:2: (otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==21) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:801:4: otherlv_1= '=' ( (lv_value_2_0= ruleDotID ) )
                    {
                    otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleListAttribute1638); 

                        	newLeafNode(otherlv_1, grammarAccess.getListAttributeAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:805:1: ( (lv_value_2_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:806:1: (lv_value_2_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:806:1: (lv_value_2_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:807:3: lv_value_2_0= ruleDotID
                    {
                     
                    	        newCompositeNode(grammarAccess.getListAttributeAccess().getValueDotIDParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDotID_in_ruleListAttribute1659);
                    lv_value_2_0=ruleDotID();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getListAttributeRule());
                    	        }
                           		set(
                           			current, 
                           			"value",
                            		lv_value_2_0, 
                            		"DotID");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


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
    // $ANTLR end "ruleListAttribute"


    // $ANTLR start "entryRuleNode"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:831:1: entryRuleNode returns [EObject current=null] : iv_ruleNode= ruleNode EOF ;
    public final EObject entryRuleNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNode = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:832:2: (iv_ruleNode= ruleNode EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:833:2: iv_ruleNode= ruleNode EOF
            {
             newCompositeNode(grammarAccess.getNodeRule()); 
            pushFollow(FOLLOW_ruleNode_in_entryRuleNode1697);
            iv_ruleNode=ruleNode();

            state._fsp--;

             current =iv_ruleNode; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNode1707); 

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
    // $ANTLR end "entryRuleNode"


    // $ANTLR start "ruleNode"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:840:1: ruleNode returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_port_2_0= rulePort ) ) )? ) ;
    public final EObject ruleNode() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_port_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:843:28: ( ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_port_2_0= rulePort ) ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:844:1: ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_port_2_0= rulePort ) ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:844:1: ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_port_2_0= rulePort ) ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:844:2: ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_port_2_0= rulePort ) ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:844:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:845:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:845:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:846:3: lv_name_0_0= ruleDotID
            {
             
            	        newCompositeNode(grammarAccess.getNodeAccess().getNameDotIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleNode1753);
            lv_name_0_0=ruleDotID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNodeRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"DotID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:862:2: (otherlv_1= ':' ( (lv_port_2_0= rulePort ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==22) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:862:4: otherlv_1= ':' ( (lv_port_2_0= rulePort ) )
                    {
                    otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleNode1766); 

                        	newLeafNode(otherlv_1, grammarAccess.getNodeAccess().getColonKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:866:1: ( (lv_port_2_0= rulePort ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:867:1: (lv_port_2_0= rulePort )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:867:1: (lv_port_2_0= rulePort )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:868:3: lv_port_2_0= rulePort
                    {
                     
                    	        newCompositeNode(grammarAccess.getNodeAccess().getPortPortParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulePort_in_ruleNode1787);
                    lv_port_2_0=rulePort();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getNodeRule());
                    	        }
                           		set(
                           			current, 
                           			"port",
                            		lv_port_2_0, 
                            		"Port");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


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
    // $ANTLR end "ruleNode"


    // $ANTLR start "entryRulePort"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:892:1: entryRulePort returns [EObject current=null] : iv_rulePort= rulePort EOF ;
    public final EObject entryRulePort() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePort = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:893:2: (iv_rulePort= rulePort EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:894:2: iv_rulePort= rulePort EOF
            {
             newCompositeNode(grammarAccess.getPortRule()); 
            pushFollow(FOLLOW_rulePort_in_entryRulePort1825);
            iv_rulePort=rulePort();

            state._fsp--;

             current =iv_rulePort; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePort1835); 

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
    // $ANTLR end "entryRulePort"


    // $ANTLR start "rulePort"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:901:1: rulePort returns [EObject current=null] : ( ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) ) ;
    public final EObject rulePort() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        Enumerator lv_compass_pt_2_0 = null;

        Enumerator lv_compass_pt_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:904:28: ( ( ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:905:1: ( ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:905:1: ( ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=RULE_ID && LA25_0<=RULE_STRING)) ) {
                alt25=1;
            }
            else if ( ((LA25_0>=29 && LA25_0<=38)) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:905:2: ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:905:2: ( ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:905:3: ( (lv_name_0_0= ruleDotID ) ) (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )?
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:905:3: ( (lv_name_0_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:906:1: (lv_name_0_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:906:1: (lv_name_0_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:907:3: lv_name_0_0= ruleDotID
                    {
                     
                    	        newCompositeNode(grammarAccess.getPortAccess().getNameDotIDParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleDotID_in_rulePort1882);
                    lv_name_0_0=ruleDotID();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPortRule());
                    	        }
                           		set(
                           			current, 
                           			"name",
                            		lv_name_0_0, 
                            		"DotID");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:923:2: (otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==22) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:923:4: otherlv_1= ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) )
                            {
                            otherlv_1=(Token)match(input,22,FOLLOW_22_in_rulePort1895); 

                                	newLeafNode(otherlv_1, grammarAccess.getPortAccess().getColonKeyword_0_1_0());
                                
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:927:1: ( (lv_compass_pt_2_0= ruleCompassPoint ) )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:928:1: (lv_compass_pt_2_0= ruleCompassPoint )
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:928:1: (lv_compass_pt_2_0= ruleCompassPoint )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:929:3: lv_compass_pt_2_0= ruleCompassPoint
                            {
                             
                            	        newCompositeNode(grammarAccess.getPortAccess().getCompass_ptCompassPointEnumRuleCall_0_1_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleCompassPoint_in_rulePort1916);
                            lv_compass_pt_2_0=ruleCompassPoint();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getPortRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"compass_pt",
                                    		lv_compass_pt_2_0, 
                                    		"CompassPoint");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:946:6: ( (lv_compass_pt_3_0= ruleCompassPoint ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:946:6: ( (lv_compass_pt_3_0= ruleCompassPoint ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:947:1: (lv_compass_pt_3_0= ruleCompassPoint )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:947:1: (lv_compass_pt_3_0= ruleCompassPoint )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:948:3: lv_compass_pt_3_0= ruleCompassPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getPortAccess().getCompass_ptCompassPointEnumRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleCompassPoint_in_rulePort1946);
                    lv_compass_pt_3_0=ruleCompassPoint();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPortRule());
                    	        }
                           		set(
                           			current, 
                           			"compass_pt",
                            		lv_compass_pt_3_0, 
                            		"CompassPoint");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


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
    // $ANTLR end "rulePort"


    // $ANTLR start "entryRuleDotID"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:972:1: entryRuleDotID returns [String current=null] : iv_ruleDotID= ruleDotID EOF ;
    public final String entryRuleDotID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDotID = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:973:2: (iv_ruleDotID= ruleDotID EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:974:2: iv_ruleDotID= ruleDotID EOF
            {
             newCompositeNode(grammarAccess.getDotIDRule()); 
            pushFollow(FOLLOW_ruleDotID_in_entryRuleDotID1983);
            iv_ruleDotID=ruleDotID();

            state._fsp--;

             current =iv_ruleDotID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDotID1994); 

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
    // $ANTLR end "entryRuleDotID"


    // $ANTLR start "ruleDotID"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:981:1: ruleDotID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDotID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_INT_1=null;
        Token this_FLOAT_2=null;
        Token this_STRING_3=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:984:28: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:985:1: (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:985:1: (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING )
            int alt26=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt26=1;
                }
                break;
            case RULE_INT:
                {
                alt26=2;
                }
                break;
            case RULE_FLOAT:
                {
                alt26=3;
                }
                break;
            case RULE_STRING:
                {
                alt26=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:985:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDotID2034); 

                    		current.merge(this_ID_0);
                        
                     
                        newLeafNode(this_ID_0, grammarAccess.getDotIDAccess().getIDTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:993:10: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleDotID2060); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getDotIDAccess().getINTTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1001:10: this_FLOAT_2= RULE_FLOAT
                    {
                    this_FLOAT_2=(Token)match(input,RULE_FLOAT,FOLLOW_RULE_FLOAT_in_ruleDotID2086); 

                    		current.merge(this_FLOAT_2);
                        
                     
                        newLeafNode(this_FLOAT_2, grammarAccess.getDotIDAccess().getFLOATTerminalRuleCall_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1009:10: this_STRING_3= RULE_STRING
                    {
                    this_STRING_3=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDotID2112); 

                    		current.merge(this_STRING_3);
                        
                     
                        newLeafNode(this_STRING_3, grammarAccess.getDotIDAccess().getSTRINGTerminalRuleCall_3()); 
                        

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
    // $ANTLR end "ruleDotID"


    // $ANTLR start "ruleEdgeOperator"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1024:1: ruleEdgeOperator returns [Enumerator current=null] : ( (enumLiteral_0= '->' ) | (enumLiteral_1= '--' ) ) ;
    public final Enumerator ruleEdgeOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1026:28: ( ( (enumLiteral_0= '->' ) | (enumLiteral_1= '--' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1027:1: ( (enumLiteral_0= '->' ) | (enumLiteral_1= '--' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1027:1: ( (enumLiteral_0= '->' ) | (enumLiteral_1= '--' ) )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==23) ) {
                alt27=1;
            }
            else if ( (LA27_0==24) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1027:2: (enumLiteral_0= '->' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1027:2: (enumLiteral_0= '->' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1027:4: enumLiteral_0= '->'
                    {
                    enumLiteral_0=(Token)match(input,23,FOLLOW_23_in_ruleEdgeOperator2171); 

                            current = grammarAccess.getEdgeOperatorAccess().getDirectedEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getEdgeOperatorAccess().getDirectedEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1033:6: (enumLiteral_1= '--' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1033:6: (enumLiteral_1= '--' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1033:8: enumLiteral_1= '--'
                    {
                    enumLiteral_1=(Token)match(input,24,FOLLOW_24_in_ruleEdgeOperator2188); 

                            current = grammarAccess.getEdgeOperatorAccess().getUndirectedEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getEdgeOperatorAccess().getUndirectedEnumLiteralDeclaration_1()); 
                        

                    }


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
    // $ANTLR end "ruleEdgeOperator"


    // $ANTLR start "ruleGraphType"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1043:1: ruleGraphType returns [Enumerator current=null] : ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'digraph' ) ) ;
    public final Enumerator ruleGraphType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1045:28: ( ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'digraph' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1046:1: ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'digraph' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1046:1: ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'digraph' ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==25) ) {
                alt28=1;
            }
            else if ( (LA28_0==26) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1046:2: (enumLiteral_0= 'graph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1046:2: (enumLiteral_0= 'graph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1046:4: enumLiteral_0= 'graph'
                    {
                    enumLiteral_0=(Token)match(input,25,FOLLOW_25_in_ruleGraphType2233); 

                            current = grammarAccess.getGraphTypeAccess().getGraphEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getGraphTypeAccess().getGraphEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1052:6: (enumLiteral_1= 'digraph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1052:6: (enumLiteral_1= 'digraph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1052:8: enumLiteral_1= 'digraph'
                    {
                    enumLiteral_1=(Token)match(input,26,FOLLOW_26_in_ruleGraphType2250); 

                            current = grammarAccess.getGraphTypeAccess().getDigraphEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getGraphTypeAccess().getDigraphEnumLiteralDeclaration_1()); 
                        

                    }


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
    // $ANTLR end "ruleGraphType"


    // $ANTLR start "ruleAttributeType"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1062:1: ruleAttributeType returns [Enumerator current=null] : ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'node' ) | (enumLiteral_2= 'edge' ) ) ;
    public final Enumerator ruleAttributeType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1064:28: ( ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'node' ) | (enumLiteral_2= 'edge' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1065:1: ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'node' ) | (enumLiteral_2= 'edge' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1065:1: ( (enumLiteral_0= 'graph' ) | (enumLiteral_1= 'node' ) | (enumLiteral_2= 'edge' ) )
            int alt29=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt29=1;
                }
                break;
            case 27:
                {
                alt29=2;
                }
                break;
            case 28:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1065:2: (enumLiteral_0= 'graph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1065:2: (enumLiteral_0= 'graph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1065:4: enumLiteral_0= 'graph'
                    {
                    enumLiteral_0=(Token)match(input,25,FOLLOW_25_in_ruleAttributeType2295); 

                            current = grammarAccess.getAttributeTypeAccess().getGraphEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getAttributeTypeAccess().getGraphEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1071:6: (enumLiteral_1= 'node' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1071:6: (enumLiteral_1= 'node' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1071:8: enumLiteral_1= 'node'
                    {
                    enumLiteral_1=(Token)match(input,27,FOLLOW_27_in_ruleAttributeType2312); 

                            current = grammarAccess.getAttributeTypeAccess().getNodeEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getAttributeTypeAccess().getNodeEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1077:6: (enumLiteral_2= 'edge' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1077:6: (enumLiteral_2= 'edge' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1077:8: enumLiteral_2= 'edge'
                    {
                    enumLiteral_2=(Token)match(input,28,FOLLOW_28_in_ruleAttributeType2329); 

                            current = grammarAccess.getAttributeTypeAccess().getEdgeEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getAttributeTypeAccess().getEdgeEnumLiteralDeclaration_2()); 
                        

                    }


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
    // $ANTLR end "ruleAttributeType"


    // $ANTLR start "ruleCompassPoint"
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1087:1: ruleCompassPoint returns [Enumerator current=null] : ( (enumLiteral_0= 'n' ) | (enumLiteral_1= 'ne' ) | (enumLiteral_2= 'e' ) | (enumLiteral_3= 'se' ) | (enumLiteral_4= 's' ) | (enumLiteral_5= 'sw' ) | (enumLiteral_6= 'w' ) | (enumLiteral_7= 'nw' ) | (enumLiteral_8= 'c' ) | (enumLiteral_9= '_' ) ) ;
    public final Enumerator ruleCompassPoint() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1089:28: ( ( (enumLiteral_0= 'n' ) | (enumLiteral_1= 'ne' ) | (enumLiteral_2= 'e' ) | (enumLiteral_3= 'se' ) | (enumLiteral_4= 's' ) | (enumLiteral_5= 'sw' ) | (enumLiteral_6= 'w' ) | (enumLiteral_7= 'nw' ) | (enumLiteral_8= 'c' ) | (enumLiteral_9= '_' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1090:1: ( (enumLiteral_0= 'n' ) | (enumLiteral_1= 'ne' ) | (enumLiteral_2= 'e' ) | (enumLiteral_3= 'se' ) | (enumLiteral_4= 's' ) | (enumLiteral_5= 'sw' ) | (enumLiteral_6= 'w' ) | (enumLiteral_7= 'nw' ) | (enumLiteral_8= 'c' ) | (enumLiteral_9= '_' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1090:1: ( (enumLiteral_0= 'n' ) | (enumLiteral_1= 'ne' ) | (enumLiteral_2= 'e' ) | (enumLiteral_3= 'se' ) | (enumLiteral_4= 's' ) | (enumLiteral_5= 'sw' ) | (enumLiteral_6= 'w' ) | (enumLiteral_7= 'nw' ) | (enumLiteral_8= 'c' ) | (enumLiteral_9= '_' ) )
            int alt30=10;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt30=1;
                }
                break;
            case 30:
                {
                alt30=2;
                }
                break;
            case 31:
                {
                alt30=3;
                }
                break;
            case 32:
                {
                alt30=4;
                }
                break;
            case 33:
                {
                alt30=5;
                }
                break;
            case 34:
                {
                alt30=6;
                }
                break;
            case 35:
                {
                alt30=7;
                }
                break;
            case 36:
                {
                alt30=8;
                }
                break;
            case 37:
                {
                alt30=9;
                }
                break;
            case 38:
                {
                alt30=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1090:2: (enumLiteral_0= 'n' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1090:2: (enumLiteral_0= 'n' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1090:4: enumLiteral_0= 'n'
                    {
                    enumLiteral_0=(Token)match(input,29,FOLLOW_29_in_ruleCompassPoint2374); 

                            current = grammarAccess.getCompassPointAccess().getNorthEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getCompassPointAccess().getNorthEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1096:6: (enumLiteral_1= 'ne' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1096:6: (enumLiteral_1= 'ne' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1096:8: enumLiteral_1= 'ne'
                    {
                    enumLiteral_1=(Token)match(input,30,FOLLOW_30_in_ruleCompassPoint2391); 

                            current = grammarAccess.getCompassPointAccess().getNorthEastEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getCompassPointAccess().getNorthEastEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1102:6: (enumLiteral_2= 'e' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1102:6: (enumLiteral_2= 'e' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1102:8: enumLiteral_2= 'e'
                    {
                    enumLiteral_2=(Token)match(input,31,FOLLOW_31_in_ruleCompassPoint2408); 

                            current = grammarAccess.getCompassPointAccess().getEastEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getCompassPointAccess().getEastEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1108:6: (enumLiteral_3= 'se' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1108:6: (enumLiteral_3= 'se' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1108:8: enumLiteral_3= 'se'
                    {
                    enumLiteral_3=(Token)match(input,32,FOLLOW_32_in_ruleCompassPoint2425); 

                            current = grammarAccess.getCompassPointAccess().getSouthEastEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getCompassPointAccess().getSouthEastEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1114:6: (enumLiteral_4= 's' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1114:6: (enumLiteral_4= 's' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1114:8: enumLiteral_4= 's'
                    {
                    enumLiteral_4=(Token)match(input,33,FOLLOW_33_in_ruleCompassPoint2442); 

                            current = grammarAccess.getCompassPointAccess().getSouthEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getCompassPointAccess().getSouthEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1120:6: (enumLiteral_5= 'sw' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1120:6: (enumLiteral_5= 'sw' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1120:8: enumLiteral_5= 'sw'
                    {
                    enumLiteral_5=(Token)match(input,34,FOLLOW_34_in_ruleCompassPoint2459); 

                            current = grammarAccess.getCompassPointAccess().getSouthWestEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getCompassPointAccess().getSouthWestEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1126:6: (enumLiteral_6= 'w' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1126:6: (enumLiteral_6= 'w' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1126:8: enumLiteral_6= 'w'
                    {
                    enumLiteral_6=(Token)match(input,35,FOLLOW_35_in_ruleCompassPoint2476); 

                            current = grammarAccess.getCompassPointAccess().getWestEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getCompassPointAccess().getWestEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1132:6: (enumLiteral_7= 'nw' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1132:6: (enumLiteral_7= 'nw' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1132:8: enumLiteral_7= 'nw'
                    {
                    enumLiteral_7=(Token)match(input,36,FOLLOW_36_in_ruleCompassPoint2493); 

                            current = grammarAccess.getCompassPointAccess().getNorthWestEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getCompassPointAccess().getNorthWestEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1138:6: (enumLiteral_8= 'c' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1138:6: (enumLiteral_8= 'c' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1138:8: enumLiteral_8= 'c'
                    {
                    enumLiteral_8=(Token)match(input,37,FOLLOW_37_in_ruleCompassPoint2510); 

                            current = grammarAccess.getCompassPointAccess().getCenterEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getCompassPointAccess().getCenterEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1144:6: (enumLiteral_9= '_' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1144:6: (enumLiteral_9= '_' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1144:8: enumLiteral_9= '_'
                    {
                    enumLiteral_9=(Token)match(input,38,FOLLOW_38_in_ruleCompassPoint2527); 

                            current = grammarAccess.getCompassPointAccess().getBlankEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getCompassPointAccess().getBlankEnumLiteralDeclaration_9()); 
                        

                    }


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
    // $ANTLR end "ruleCompassPoint"

    // Delegated rules


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\44\uffff";
    static final String DFA5_eofS =
        "\1\uffff\4\10\6\uffff\16\10\1\uffff\12\10";
    static final String DFA5_minS =
        "\5\4\2\uffff\1\4\3\uffff\16\4\1\35\12\4";
    static final String DFA5_maxS =
        "\5\34\2\uffff\1\46\3\uffff\16\34\1\46\12\34";
    static final String DFA5_acceptS =
        "\5\uffff\1\4\1\5\1\uffff\1\2\1\3\1\1\31\uffff";
    static final String DFA5_specialS =
        "\44\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\1\2\1\3\1\4\14\uffff\1\6\4\uffff\1\5\1\uffff\2\5",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\11\1\7\2\12\1\10\1\uffff"+
            "\2\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\11\1\7\2\12\1\10\1\uffff"+
            "\2\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\11\1\7\2\12\1\10\1\uffff"+
            "\2\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\11\1\7\2\12\1\10\1\uffff"+
            "\2\10",
            "",
            "",
            "\1\13\1\14\1\15\1\16\25\uffff\1\17\1\20\1\21\1\22\1\23\1\24"+
            "\1\25\1\26\1\27\1\30",
            "",
            "",
            "",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\uffff\1\31\2\12\1\10\1\uffff"+
            "\2\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\uffff\1\31\2\12\1\10\1\uffff"+
            "\2\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\uffff\1\31\2\12\1\10\1\uffff"+
            "\2\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\1\uffff\1\31\2\12\1\10\1\uffff"+
            "\2\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10",
            "\4\10\7\uffff\3\10\2\uffff\1\10\2\uffff\2\12\1\10\1\uffff\2"+
            "\10"
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
            return "218:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )";
        }
    }
 

    public static final BitSet FOLLOW_ruleGraphvizModel_in_entryRuleGraphvizModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGraphvizModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGraph_in_ruleGraphvizModel130 = new BitSet(new long[]{0x0000000006002002L});
    public static final BitSet FOLLOW_ruleGraph_in_entryRuleGraph166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGraph176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleGraph219 = new BitSet(new long[]{0x0000000006006010L});
    public static final BitSet FOLLOW_ruleGraphType_in_ruleGraph254 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGraph271 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleGraph289 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleGraph310 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_15_in_ruleGraph323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStatement_in_entryRuleStatement359 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStatement369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeStatement_in_ruleStatement417 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleNodeStatement_in_ruleStatement444 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleStatement471 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleAttributeStatement_in_ruleStatement498 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_ruleStatement525 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleStatement538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeStatement_in_entryRuleEdgeStatement576 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEdgeStatement586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleEdgeStatement632 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_ruleEdgeTarget_in_ruleEdgeStatement653 = new BitSet(new long[]{0x0000000001820002L});
    public static final BitSet FOLLOW_17_in_ruleEdgeStatement667 = new BitSet(new long[]{0x00000000000800F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleEdgeStatement689 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_18_in_ruleEdgeStatement703 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleEdgeStatement726 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_19_in_ruleEdgeStatement742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeTarget_in_entryRuleEdgeTarget780 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEdgeTarget790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeOperator_in_ruleEdgeTarget836 = new BitSet(new long[]{0x000000001A1000F0L});
    public static final BitSet FOLLOW_ruleSubgraph_in_ruleEdgeTarget858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleEdgeTarget885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNodeStatement_in_entryRuleNodeStatement922 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNodeStatement932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleNodeStatement978 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_ruleNodeStatement991 = new BitSet(new long[]{0x00000000000800F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleNodeStatement1013 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_18_in_ruleNodeStatement1027 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleNodeStatement1050 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_19_in_ruleNodeStatement1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeStatement_in_entryRuleAttributeStatement1104 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeStatement1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeType_in_ruleAttributeStatement1160 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleAttributeStatement1172 = new BitSet(new long[]{0x00000000000800F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleAttributeStatement1194 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_18_in_ruleAttributeStatement1208 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleAttributeStatement1231 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_19_in_ruleAttributeStatement1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_entryRuleSubgraph1283 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubgraph1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleSubgraph1339 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSubgraph1356 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleSubgraph1374 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleSubgraph1395 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_15_in_ruleSubgraph1408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute1444 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleAttribute1500 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleAttribute1512 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleAttribute1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListAttribute_in_entryRuleListAttribute1569 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListAttribute1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleListAttribute1625 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleListAttribute1638 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleListAttribute1659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_entryRuleNode1697 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNode1707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleNode1753 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_ruleNode1766 = new BitSet(new long[]{0x0000007FE00000F0L});
    public static final BitSet FOLLOW_rulePort_in_ruleNode1787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePort_in_entryRulePort1825 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePort1835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_rulePort1882 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_rulePort1895 = new BitSet(new long[]{0x0000007FE00000F0L});
    public static final BitSet FOLLOW_ruleCompassPoint_in_rulePort1916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompassPoint_in_rulePort1946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_entryRuleDotID1983 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDotID1994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDotID2034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleDotID2060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_FLOAT_in_ruleDotID2086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDotID2112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleEdgeOperator2171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleEdgeOperator2188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleGraphType2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleGraphType2250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleAttributeType2295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleAttributeType2312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleAttributeType2329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleCompassPoint2374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleCompassPoint2391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleCompassPoint2408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleCompassPoint2425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleCompassPoint2442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleCompassPoint2459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleCompassPoint2476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleCompassPoint2493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleCompassPoint2510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleCompassPoint2527 = new BitSet(new long[]{0x0000000000000002L});

}