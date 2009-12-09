package de.cau.cs.kieler.kiml.graphviz.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import de.cau.cs.kieler.kiml.graphviz.services.DotGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class InternalDotParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_FLOAT", "RULE_STRING", "RULE_PREC_LINE", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'strict'", "'{'", "'}'", "'='", "';'", "'subgraph'", "'['", "','", "']'", "':'", "'->'", "'--'", "'graph'", "'digraph'", "'node'", "'edge'", "'n'", "'ne'", "'e'", "'se'", "'s'", "'sw'", "'w'", "'nw'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_INT=8;
    public static final int RULE_PREC_LINE=7;
    public static final int RULE_WS=11;
    public static final int RULE_FLOAT=5;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;

        public InternalDotParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[65+1];
         }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g"; }


     
     	private DotGrammarAccess grammarAccess;
     	
        public InternalDotParser(TokenStream input, IAstFactory factory, DotGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "GraphvizModel";	
       	} 



    // $ANTLR start entryRuleGraphvizModel
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:74:1: entryRuleGraphvizModel returns [EObject current=null] : iv_ruleGraphvizModel= ruleGraphvizModel EOF ;
    public final EObject entryRuleGraphvizModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGraphvizModel = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:74:55: (iv_ruleGraphvizModel= ruleGraphvizModel EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:75:2: iv_ruleGraphvizModel= ruleGraphvizModel EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getGraphvizModelRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleGraphvizModel_in_entryRuleGraphvizModel79);
            iv_ruleGraphvizModel=ruleGraphvizModel();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleGraphvizModel; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGraphvizModel89); if (failed) return current;

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
    // $ANTLR end entryRuleGraphvizModel


    // $ANTLR start ruleGraphvizModel
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:82:1: ruleGraphvizModel returns [EObject current=null] : ( (lv_graphs_0_0= ruleGraph ) )* ;
    public final EObject ruleGraphvizModel() throws RecognitionException {
        EObject current = null;

        EObject lv_graphs_0_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:87:6: ( ( (lv_graphs_0_0= ruleGraph ) )* )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:88:1: ( (lv_graphs_0_0= ruleGraph ) )*
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:88:1: ( (lv_graphs_0_0= ruleGraph ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13||(LA1_0>=25 && LA1_0<=26)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:89:1: (lv_graphs_0_0= ruleGraph )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:89:1: (lv_graphs_0_0= ruleGraph )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:90:3: lv_graphs_0_0= ruleGraph
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getGraphvizModelAccess().getGraphsGraphParserRuleCall_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleGraph_in_ruleGraphvizModel134);
            	    lv_graphs_0_0=ruleGraph();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getGraphvizModelRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"graphs",
            	      	        		lv_graphs_0_0, 
            	      	        		"Graph", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleGraphvizModel


    // $ANTLR start entryRuleGraph
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:119:1: entryRuleGraph returns [EObject current=null] : iv_ruleGraph= ruleGraph EOF ;
    public final EObject entryRuleGraph() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGraph = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:119:47: (iv_ruleGraph= ruleGraph EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:120:2: iv_ruleGraph= ruleGraph EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getGraphRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleGraph_in_entryRuleGraph167);
            iv_ruleGraph=ruleGraph();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleGraph; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGraph177); if (failed) return current;

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
    // $ANTLR end entryRuleGraph


    // $ANTLR start ruleGraph
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:127:1: ruleGraph returns [EObject current=null] : ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' ) ;
    public final EObject ruleGraph() throws RecognitionException {
        EObject current = null;

        Token lv_strict_0_0=null;
        Token lv_name_2_0=null;
        Enumerator lv_type_1_0 = null;

        EObject lv_statements_4_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:132:6: ( ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:133:1: ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:133:1: ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:133:2: ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:133:2: ( (lv_strict_0_0= 'strict' ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:134:1: (lv_strict_0_0= 'strict' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:134:1: (lv_strict_0_0= 'strict' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:135:3: lv_strict_0_0= 'strict'
                    {
                    lv_strict_0_0=(Token)input.LT(1);
                    match(input,13,FOLLOW_13_in_ruleGraph219); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getGraphAccess().getStrictStrictKeyword_0_0(), "strict"); 
                          
                    }
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getGraphRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode, current);
                      	        }
                      	        
                      	        try {
                      	       		set(current, "strict", true, "strict", lastConsumedNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:154:3: ( (lv_type_1_0= ruleGraphType ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:155:1: (lv_type_1_0= ruleGraphType )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:155:1: (lv_type_1_0= ruleGraphType )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:156:3: lv_type_1_0= ruleGraphType
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getGraphAccess().getTypeGraphTypeEnumRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleGraphType_in_ruleGraph254);
            lv_type_1_0=ruleGraphType();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getGraphRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"type",
              	        		lv_type_1_0, 
              	        		"GraphType", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:178:2: ( (lv_name_2_0= RULE_ID ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:179:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:179:1: (lv_name_2_0= RULE_ID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:180:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGraph271); if (failed) return current;
                    if ( backtracking==0 ) {

                      			createLeafNode(grammarAccess.getGraphAccess().getNameIDTerminalRuleCall_2_0(), "name"); 
                      		
                    }
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getGraphRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode, current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"name",
                      	        		lv_name_2_0, 
                      	        		"ID", 
                      	        		lastConsumedNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	    
                    }

                    }


                    }
                    break;

            }

            match(input,14,FOLLOW_14_in_ruleGraph286); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getGraphAccess().getLeftCurlyBracketKeyword_3(), null); 
                  
            }
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:206:1: ( (lv_statements_4_0= ruleStatement ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=RULE_ID && LA4_0<=RULE_STRING)||LA4_0==14||LA4_0==18||LA4_0==25||(LA4_0>=27 && LA4_0<=28)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:207:1: (lv_statements_4_0= ruleStatement )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:207:1: (lv_statements_4_0= ruleStatement )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:208:3: lv_statements_4_0= ruleStatement
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getGraphAccess().getStatementsStatementParserRuleCall_4_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleStatement_in_ruleGraph307);
            	    lv_statements_4_0=ruleStatement();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getGraphRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"statements",
            	      	        		lv_statements_4_0, 
            	      	        		"Statement", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,15,FOLLOW_15_in_ruleGraph317); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getGraphAccess().getRightCurlyBracketKeyword_5(), null); 
                  
            }

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleGraph


    // $ANTLR start entryRuleStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:241:1: entryRuleStatement returns [EObject current=null] : iv_ruleStatement= ruleStatement EOF ;
    public final EObject entryRuleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:241:51: (iv_ruleStatement= ruleStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:242:2: iv_ruleStatement= ruleStatement EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getStatementRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleStatement_in_entryRuleStatement350);
            iv_ruleStatement=ruleStatement();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStatement360); if (failed) return current;

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
    // $ANTLR end entryRuleStatement


    // $ANTLR start ruleStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:249:1: ruleStatement returns [EObject current=null] : ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) ) ( ';' )? ) ;
    public final EObject ruleStatement() throws RecognitionException {
        EObject current = null;

        EObject this_EdgeStatement_0 = null;

        EObject this_NodeStatement_1 = null;

        EObject this_AttributeStatement_2 = null;

        EObject this_Subgraph_3 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_value_6_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:254:6: ( ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) ) ( ';' )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:255:1: ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) ) ( ';' )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:255:1: ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) ) ( ';' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) ) ( ';' )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) )
            int alt5=5;
            switch ( input.LA(1) ) {
            case 18:
                {
                int LA5_1 = input.LA(2);

                if ( (synpred5()) ) {
                    alt5=1;
                }
                else if ( (synpred8()) ) {
                    alt5=4;
                }
                else {
                    if (backtracking>0) {failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) )", 5, 1, input);

                    throw nvae;
                }
                }
                break;
            case 14:
                {
                int LA5_2 = input.LA(2);

                if ( (synpred5()) ) {
                    alt5=1;
                }
                else if ( (synpred8()) ) {
                    alt5=4;
                }
                else {
                    if (backtracking>0) {failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) )", 5, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ID:
                {
                int LA5_3 = input.LA(2);

                if ( (synpred5()) ) {
                    alt5=1;
                }
                else if ( (synpred6()) ) {
                    alt5=2;
                }
                else if ( (true) ) {
                    alt5=5;
                }
                else {
                    if (backtracking>0) {failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) )", 5, 3, input);

                    throw nvae;
                }
                }
                break;
            case RULE_FLOAT:
                {
                int LA5_4 = input.LA(2);

                if ( (synpred5()) ) {
                    alt5=1;
                }
                else if ( (synpred6()) ) {
                    alt5=2;
                }
                else if ( (true) ) {
                    alt5=5;
                }
                else {
                    if (backtracking>0) {failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) )", 5, 4, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                int LA5_5 = input.LA(2);

                if ( (synpred5()) ) {
                    alt5=1;
                }
                else if ( (synpred6()) ) {
                    alt5=2;
                }
                else if ( (true) ) {
                    alt5=5;
                }
                else {
                    if (backtracking>0) {failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) )", 5, 5, input);

                    throw nvae;
                }
                }
                break;
            case 25:
            case 27:
            case 28:
                {
                alt5=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("255:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_AttributeStatement_2= ruleAttributeStatement | this_Subgraph_3= ruleSubgraph | ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) ) )", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:256:5: this_EdgeStatement_0= ruleEdgeStatement
                    {
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getStatementAccess().getEdgeStatementParserRuleCall_0_0(), currentNode); 
                          
                    }
                    pushFollow(FOLLOW_ruleEdgeStatement_in_ruleStatement408);
                    this_EdgeStatement_0=ruleEdgeStatement();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_EdgeStatement_0; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:266:5: this_NodeStatement_1= ruleNodeStatement
                    {
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getStatementAccess().getNodeStatementParserRuleCall_0_1(), currentNode); 
                          
                    }
                    pushFollow(FOLLOW_ruleNodeStatement_in_ruleStatement435);
                    this_NodeStatement_1=ruleNodeStatement();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_NodeStatement_1; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:276:5: this_AttributeStatement_2= ruleAttributeStatement
                    {
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getStatementAccess().getAttributeStatementParserRuleCall_0_2(), currentNode); 
                          
                    }
                    pushFollow(FOLLOW_ruleAttributeStatement_in_ruleStatement462);
                    this_AttributeStatement_2=ruleAttributeStatement();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_AttributeStatement_2; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:286:5: this_Subgraph_3= ruleSubgraph
                    {
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getStatementAccess().getSubgraphParserRuleCall_0_3(), currentNode); 
                          
                    }
                    pushFollow(FOLLOW_ruleSubgraph_in_ruleStatement489);
                    this_Subgraph_3=ruleSubgraph();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_Subgraph_3; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:295:6: ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:295:6: ( ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:295:7: ( (lv_name_4_0= ruleDotID ) ) '=' ( (lv_value_6_0= ruleDotID ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:295:7: ( (lv_name_4_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:296:1: (lv_name_4_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:296:1: (lv_name_4_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:297:3: lv_name_4_0= ruleDotID
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getStatementAccess().getNameDotIDParserRuleCall_0_4_0_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleDotID_in_ruleStatement516);
                    lv_name_4_0=ruleDotID();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getStatementRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"name",
                      	        		lv_name_4_0, 
                      	        		"DotID", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }

                    match(input,16,FOLLOW_16_in_ruleStatement525); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getStatementAccess().getEqualsSignKeyword_0_4_1(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:323:1: ( (lv_value_6_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:324:1: (lv_value_6_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:324:1: (lv_value_6_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:325:3: lv_value_6_0= ruleDotID
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getStatementAccess().getValueDotIDParserRuleCall_0_4_2_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleDotID_in_ruleStatement546);
                    lv_value_6_0=ruleDotID();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getStatementRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"value",
                      	        		lv_value_6_0, 
                      	        		"DotID", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:347:4: ( ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:347:5: ';'
                    {
                    match(input,17,FOLLOW_17_in_ruleStatement558); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getStatementAccess().getSemicolonKeyword_1(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleStatement


    // $ANTLR start entryRuleEdgeStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:358:1: entryRuleEdgeStatement returns [EObject current=null] : iv_ruleEdgeStatement= ruleEdgeStatement EOF ;
    public final EObject entryRuleEdgeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:358:55: (iv_ruleEdgeStatement= ruleEdgeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:359:2: iv_ruleEdgeStatement= ruleEdgeStatement EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getEdgeStatementRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleEdgeStatement_in_entryRuleEdgeStatement593);
            iv_ruleEdgeStatement=ruleEdgeStatement();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleEdgeStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleEdgeStatement603); if (failed) return current;

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
    // $ANTLR end entryRuleEdgeStatement


    // $ANTLR start ruleEdgeStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:366:1: ruleEdgeStatement returns [EObject current=null] : ( ( ( (lv_sourceSubgraph_0_0= ruleSubgraph ) ) | ( (lv_sourceNode_1_0= ruleNode ) ) ) ( (lv_edgeTargets_2_0= ruleEdgeTarget ) )+ ( (lv_attributes_3_0= ruleAttributeList ) )? ) ;
    public final EObject ruleEdgeStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_sourceSubgraph_0_0 = null;

        EObject lv_sourceNode_1_0 = null;

        EObject lv_edgeTargets_2_0 = null;

        EObject lv_attributes_3_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:371:6: ( ( ( ( (lv_sourceSubgraph_0_0= ruleSubgraph ) ) | ( (lv_sourceNode_1_0= ruleNode ) ) ) ( (lv_edgeTargets_2_0= ruleEdgeTarget ) )+ ( (lv_attributes_3_0= ruleAttributeList ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:372:1: ( ( ( (lv_sourceSubgraph_0_0= ruleSubgraph ) ) | ( (lv_sourceNode_1_0= ruleNode ) ) ) ( (lv_edgeTargets_2_0= ruleEdgeTarget ) )+ ( (lv_attributes_3_0= ruleAttributeList ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:372:1: ( ( ( (lv_sourceSubgraph_0_0= ruleSubgraph ) ) | ( (lv_sourceNode_1_0= ruleNode ) ) ) ( (lv_edgeTargets_2_0= ruleEdgeTarget ) )+ ( (lv_attributes_3_0= ruleAttributeList ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:372:2: ( ( (lv_sourceSubgraph_0_0= ruleSubgraph ) ) | ( (lv_sourceNode_1_0= ruleNode ) ) ) ( (lv_edgeTargets_2_0= ruleEdgeTarget ) )+ ( (lv_attributes_3_0= ruleAttributeList ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:372:2: ( ( (lv_sourceSubgraph_0_0= ruleSubgraph ) ) | ( (lv_sourceNode_1_0= ruleNode ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==14||LA7_0==18) ) {
                alt7=1;
            }
            else if ( ((LA7_0>=RULE_ID && LA7_0<=RULE_STRING)) ) {
                alt7=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("372:2: ( ( (lv_sourceSubgraph_0_0= ruleSubgraph ) ) | ( (lv_sourceNode_1_0= ruleNode ) ) )", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:372:3: ( (lv_sourceSubgraph_0_0= ruleSubgraph ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:372:3: ( (lv_sourceSubgraph_0_0= ruleSubgraph ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:373:1: (lv_sourceSubgraph_0_0= ruleSubgraph )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:373:1: (lv_sourceSubgraph_0_0= ruleSubgraph )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:374:3: lv_sourceSubgraph_0_0= ruleSubgraph
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getSourceSubgraphSubgraphParserRuleCall_0_0_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleSubgraph_in_ruleEdgeStatement650);
                    lv_sourceSubgraph_0_0=ruleSubgraph();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"sourceSubgraph",
                      	        		lv_sourceSubgraph_0_0, 
                      	        		"Subgraph", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:397:6: ( (lv_sourceNode_1_0= ruleNode ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:397:6: ( (lv_sourceNode_1_0= ruleNode ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:398:1: (lv_sourceNode_1_0= ruleNode )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:398:1: (lv_sourceNode_1_0= ruleNode )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:399:3: lv_sourceNode_1_0= ruleNode
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getSourceNodeNodeParserRuleCall_0_1_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNode_in_ruleEdgeStatement677);
                    lv_sourceNode_1_0=ruleNode();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"sourceNode",
                      	        		lv_sourceNode_1_0, 
                      	        		"Node", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:421:3: ( (lv_edgeTargets_2_0= ruleEdgeTarget ) )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=23 && LA8_0<=24)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:422:1: (lv_edgeTargets_2_0= ruleEdgeTarget )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:422:1: (lv_edgeTargets_2_0= ruleEdgeTarget )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:423:3: lv_edgeTargets_2_0= ruleEdgeTarget
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getEdgeTargetsEdgeTargetParserRuleCall_1_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleEdgeTarget_in_ruleEdgeStatement699);
            	    lv_edgeTargets_2_0=ruleEdgeTarget();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"edgeTargets",
            	      	        		lv_edgeTargets_2_0, 
            	      	        		"EdgeTarget", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
            	    if (backtracking>0) {failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:445:3: ( (lv_attributes_3_0= ruleAttributeList ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==19) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:446:1: (lv_attributes_3_0= ruleAttributeList )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:446:1: (lv_attributes_3_0= ruleAttributeList )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:447:3: lv_attributes_3_0= ruleAttributeList
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getAttributesAttributeListParserRuleCall_2_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleAttributeList_in_ruleEdgeStatement721);
                    lv_attributes_3_0=ruleAttributeList();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"attributes",
                      	        		lv_attributes_3_0, 
                      	        		"AttributeList", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleEdgeStatement


    // $ANTLR start entryRuleEdgeTarget
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:476:1: entryRuleEdgeTarget returns [EObject current=null] : iv_ruleEdgeTarget= ruleEdgeTarget EOF ;
    public final EObject entryRuleEdgeTarget() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeTarget = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:476:52: (iv_ruleEdgeTarget= ruleEdgeTarget EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:477:2: iv_ruleEdgeTarget= ruleEdgeTarget EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getEdgeTargetRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleEdgeTarget_in_entryRuleEdgeTarget755);
            iv_ruleEdgeTarget=ruleEdgeTarget();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleEdgeTarget; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleEdgeTarget765); if (failed) return current;

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
    // $ANTLR end entryRuleEdgeTarget


    // $ANTLR start ruleEdgeTarget
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:484:1: ruleEdgeTarget returns [EObject current=null] : ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) ) ;
    public final EObject ruleEdgeTarget() throws RecognitionException {
        EObject current = null;

        Enumerator lv_operator_0_0 = null;

        EObject lv_targetSubgraph_1_0 = null;

        EObject lv_targetnode_2_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:489:6: ( ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:490:1: ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:490:1: ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:490:2: ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:490:2: ( (lv_operator_0_0= ruleEdgeOperator ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:491:1: (lv_operator_0_0= ruleEdgeOperator )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:491:1: (lv_operator_0_0= ruleEdgeOperator )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:492:3: lv_operator_0_0= ruleEdgeOperator
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getEdgeTargetAccess().getOperatorEdgeOperatorEnumRuleCall_0_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleEdgeOperator_in_ruleEdgeTarget811);
            lv_operator_0_0=ruleEdgeOperator();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getEdgeTargetRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"operator",
              	        		lv_operator_0_0, 
              	        		"EdgeOperator", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:514:2: ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==14||LA10_0==18) ) {
                alt10=1;
            }
            else if ( ((LA10_0>=RULE_ID && LA10_0<=RULE_STRING)) ) {
                alt10=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("514:2: ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:514:3: ( (lv_targetSubgraph_1_0= ruleSubgraph ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:514:3: ( (lv_targetSubgraph_1_0= ruleSubgraph ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:515:1: (lv_targetSubgraph_1_0= ruleSubgraph )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:515:1: (lv_targetSubgraph_1_0= ruleSubgraph )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:516:3: lv_targetSubgraph_1_0= ruleSubgraph
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getEdgeTargetAccess().getTargetSubgraphSubgraphParserRuleCall_1_0_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleSubgraph_in_ruleEdgeTarget833);
                    lv_targetSubgraph_1_0=ruleSubgraph();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getEdgeTargetRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"targetSubgraph",
                      	        		lv_targetSubgraph_1_0, 
                      	        		"Subgraph", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:539:6: ( (lv_targetnode_2_0= ruleNode ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:539:6: ( (lv_targetnode_2_0= ruleNode ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:540:1: (lv_targetnode_2_0= ruleNode )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:540:1: (lv_targetnode_2_0= ruleNode )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:541:3: lv_targetnode_2_0= ruleNode
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getEdgeTargetAccess().getTargetnodeNodeParserRuleCall_1_1_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNode_in_ruleEdgeTarget860);
                    lv_targetnode_2_0=ruleNode();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getEdgeTargetRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"targetnode",
                      	        		lv_targetnode_2_0, 
                      	        		"Node", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleEdgeTarget


    // $ANTLR start entryRuleNodeStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:570:1: entryRuleNodeStatement returns [EObject current=null] : iv_ruleNodeStatement= ruleNodeStatement EOF ;
    public final EObject entryRuleNodeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:570:55: (iv_ruleNodeStatement= ruleNodeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:571:2: iv_ruleNodeStatement= ruleNodeStatement EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getNodeStatementRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleNodeStatement_in_entryRuleNodeStatement894);
            iv_ruleNodeStatement=ruleNodeStatement();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleNodeStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNodeStatement904); if (failed) return current;

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
    // $ANTLR end entryRuleNodeStatement


    // $ANTLR start ruleNodeStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:578:1: ruleNodeStatement returns [EObject current=null] : ( ( (lv_node_0_0= ruleNode ) ) ( (lv_attributes_1_0= ruleAttributeList ) )? ) ;
    public final EObject ruleNodeStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_node_0_0 = null;

        EObject lv_attributes_1_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:583:6: ( ( ( (lv_node_0_0= ruleNode ) ) ( (lv_attributes_1_0= ruleAttributeList ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:584:1: ( ( (lv_node_0_0= ruleNode ) ) ( (lv_attributes_1_0= ruleAttributeList ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:584:1: ( ( (lv_node_0_0= ruleNode ) ) ( (lv_attributes_1_0= ruleAttributeList ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:584:2: ( (lv_node_0_0= ruleNode ) ) ( (lv_attributes_1_0= ruleAttributeList ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:584:2: ( (lv_node_0_0= ruleNode ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:585:1: (lv_node_0_0= ruleNode )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:585:1: (lv_node_0_0= ruleNode )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:586:3: lv_node_0_0= ruleNode
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getNodeStatementAccess().getNodeNodeParserRuleCall_0_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleNode_in_ruleNodeStatement950);
            lv_node_0_0=ruleNode();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getNodeStatementRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"node",
              	        		lv_node_0_0, 
              	        		"Node", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:608:2: ( (lv_attributes_1_0= ruleAttributeList ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==19) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:609:1: (lv_attributes_1_0= ruleAttributeList )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:609:1: (lv_attributes_1_0= ruleAttributeList )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:610:3: lv_attributes_1_0= ruleAttributeList
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getNodeStatementAccess().getAttributesAttributeListParserRuleCall_1_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleAttributeList_in_ruleNodeStatement971);
                    lv_attributes_1_0=ruleAttributeList();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getNodeStatementRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"attributes",
                      	        		lv_attributes_1_0, 
                      	        		"AttributeList", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleNodeStatement


    // $ANTLR start entryRuleAttributeStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:639:1: entryRuleAttributeStatement returns [EObject current=null] : iv_ruleAttributeStatement= ruleAttributeStatement EOF ;
    public final EObject entryRuleAttributeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:639:60: (iv_ruleAttributeStatement= ruleAttributeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:640:2: iv_ruleAttributeStatement= ruleAttributeStatement EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getAttributeStatementRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleAttributeStatement_in_entryRuleAttributeStatement1005);
            iv_ruleAttributeStatement=ruleAttributeStatement();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleAttributeStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeStatement1015); if (failed) return current;

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
    // $ANTLR end entryRuleAttributeStatement


    // $ANTLR start ruleAttributeStatement
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:647:1: ruleAttributeStatement returns [EObject current=null] : ( ( (lv_type_0_0= ruleAttributeType ) ) ( (lv_attributes_1_0= ruleAttributeList ) ) ) ;
    public final EObject ruleAttributeStatement() throws RecognitionException {
        EObject current = null;

        Enumerator lv_type_0_0 = null;

        EObject lv_attributes_1_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:652:6: ( ( ( (lv_type_0_0= ruleAttributeType ) ) ( (lv_attributes_1_0= ruleAttributeList ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:653:1: ( ( (lv_type_0_0= ruleAttributeType ) ) ( (lv_attributes_1_0= ruleAttributeList ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:653:1: ( ( (lv_type_0_0= ruleAttributeType ) ) ( (lv_attributes_1_0= ruleAttributeList ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:653:2: ( (lv_type_0_0= ruleAttributeType ) ) ( (lv_attributes_1_0= ruleAttributeList ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:653:2: ( (lv_type_0_0= ruleAttributeType ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:654:1: (lv_type_0_0= ruleAttributeType )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:654:1: (lv_type_0_0= ruleAttributeType )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:655:3: lv_type_0_0= ruleAttributeType
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getAttributeStatementAccess().getTypeAttributeTypeEnumRuleCall_0_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleAttributeType_in_ruleAttributeStatement1061);
            lv_type_0_0=ruleAttributeType();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getAttributeStatementRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"type",
              	        		lv_type_0_0, 
              	        		"AttributeType", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:677:2: ( (lv_attributes_1_0= ruleAttributeList ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:678:1: (lv_attributes_1_0= ruleAttributeList )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:678:1: (lv_attributes_1_0= ruleAttributeList )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:679:3: lv_attributes_1_0= ruleAttributeList
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getAttributeStatementAccess().getAttributesAttributeListParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleAttributeList_in_ruleAttributeStatement1082);
            lv_attributes_1_0=ruleAttributeList();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getAttributeStatementRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"attributes",
              	        		lv_attributes_1_0, 
              	        		"AttributeList", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleAttributeStatement


    // $ANTLR start entryRuleSubgraph
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:708:1: entryRuleSubgraph returns [EObject current=null] : iv_ruleSubgraph= ruleSubgraph EOF ;
    public final EObject entryRuleSubgraph() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubgraph = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:708:50: (iv_ruleSubgraph= ruleSubgraph EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:709:2: iv_ruleSubgraph= ruleSubgraph EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getSubgraphRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleSubgraph_in_entryRuleSubgraph1115);
            iv_ruleSubgraph=ruleSubgraph();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleSubgraph; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubgraph1125); if (failed) return current;

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
    // $ANTLR end entryRuleSubgraph


    // $ANTLR start ruleSubgraph
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:716:1: ruleSubgraph returns [EObject current=null] : ( ( 'subgraph' ( (lv_name_1_0= RULE_ID ) )? )? '{' ( (lv_statements_3_0= ruleStatement ) )* '}' ) ;
    public final EObject ruleSubgraph() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        EObject lv_statements_3_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:721:6: ( ( ( 'subgraph' ( (lv_name_1_0= RULE_ID ) )? )? '{' ( (lv_statements_3_0= ruleStatement ) )* '}' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:722:1: ( ( 'subgraph' ( (lv_name_1_0= RULE_ID ) )? )? '{' ( (lv_statements_3_0= ruleStatement ) )* '}' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:722:1: ( ( 'subgraph' ( (lv_name_1_0= RULE_ID ) )? )? '{' ( (lv_statements_3_0= ruleStatement ) )* '}' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:722:2: ( 'subgraph' ( (lv_name_1_0= RULE_ID ) )? )? '{' ( (lv_statements_3_0= ruleStatement ) )* '}'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:722:2: ( 'subgraph' ( (lv_name_1_0= RULE_ID ) )? )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==18) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:722:3: 'subgraph' ( (lv_name_1_0= RULE_ID ) )?
                    {
                    match(input,18,FOLLOW_18_in_ruleSubgraph1160); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getSubgraphAccess().getSubgraphKeyword_0_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:726:1: ( (lv_name_1_0= RULE_ID ) )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==RULE_ID) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:727:1: (lv_name_1_0= RULE_ID )
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:727:1: (lv_name_1_0= RULE_ID )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:728:3: lv_name_1_0= RULE_ID
                            {
                            lv_name_1_0=(Token)input.LT(1);
                            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSubgraph1177); if (failed) return current;
                            if ( backtracking==0 ) {

                              			createLeafNode(grammarAccess.getSubgraphAccess().getNameIDTerminalRuleCall_0_1_0(), "name"); 
                              		
                            }
                            if ( backtracking==0 ) {

                              	        if (current==null) {
                              	            current = factory.create(grammarAccess.getSubgraphRule().getType().getClassifier());
                              	            associateNodeWithAstElement(currentNode, current);
                              	        }
                              	        try {
                              	       		set(
                              	       			current, 
                              	       			"name",
                              	        		lv_name_1_0, 
                              	        		"ID", 
                              	        		lastConsumedNode);
                              	        } catch (ValueConverterException vce) {
                              				handleValueConverterException(vce);
                              	        }
                              	    
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,14,FOLLOW_14_in_ruleSubgraph1194); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getSubgraphAccess().getLeftCurlyBracketKeyword_1(), null); 
                  
            }
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:754:1: ( (lv_statements_3_0= ruleStatement ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=RULE_ID && LA14_0<=RULE_STRING)||LA14_0==14||LA14_0==18||LA14_0==25||(LA14_0>=27 && LA14_0<=28)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:755:1: (lv_statements_3_0= ruleStatement )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:755:1: (lv_statements_3_0= ruleStatement )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:756:3: lv_statements_3_0= ruleStatement
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getSubgraphAccess().getStatementsStatementParserRuleCall_2_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleStatement_in_ruleSubgraph1215);
            	    lv_statements_3_0=ruleStatement();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getSubgraphRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"statements",
            	      	        		lv_statements_3_0, 
            	      	        		"Statement", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            match(input,15,FOLLOW_15_in_ruleSubgraph1225); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getSubgraphAccess().getRightCurlyBracketKeyword_3(), null); 
                  
            }

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleSubgraph


    // $ANTLR start entryRuleAttributeList
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:789:1: entryRuleAttributeList returns [EObject current=null] : iv_ruleAttributeList= ruleAttributeList EOF ;
    public final EObject entryRuleAttributeList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeList = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:789:55: (iv_ruleAttributeList= ruleAttributeList EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:790:2: iv_ruleAttributeList= ruleAttributeList EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getAttributeListRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleAttributeList_in_entryRuleAttributeList1258);
            iv_ruleAttributeList=ruleAttributeList();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleAttributeList; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeList1268); if (failed) return current;

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
    // $ANTLR end entryRuleAttributeList


    // $ANTLR start ruleAttributeList
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:797:1: ruleAttributeList returns [EObject current=null] : ( '[' ( (lv_entries_1_0= ruleAttribute ) ) ( ',' ( (lv_entries_3_0= ruleAttribute ) ) )* ']' ) ;
    public final EObject ruleAttributeList() throws RecognitionException {
        EObject current = null;

        EObject lv_entries_1_0 = null;

        EObject lv_entries_3_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:802:6: ( ( '[' ( (lv_entries_1_0= ruleAttribute ) ) ( ',' ( (lv_entries_3_0= ruleAttribute ) ) )* ']' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:803:1: ( '[' ( (lv_entries_1_0= ruleAttribute ) ) ( ',' ( (lv_entries_3_0= ruleAttribute ) ) )* ']' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:803:1: ( '[' ( (lv_entries_1_0= ruleAttribute ) ) ( ',' ( (lv_entries_3_0= ruleAttribute ) ) )* ']' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:803:2: '[' ( (lv_entries_1_0= ruleAttribute ) ) ( ',' ( (lv_entries_3_0= ruleAttribute ) ) )* ']'
            {
            match(input,19,FOLLOW_19_in_ruleAttributeList1302); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getAttributeListAccess().getLeftSquareBracketKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:807:1: ( (lv_entries_1_0= ruleAttribute ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:808:1: (lv_entries_1_0= ruleAttribute )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:808:1: (lv_entries_1_0= ruleAttribute )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:809:3: lv_entries_1_0= ruleAttribute
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getAttributeListAccess().getEntriesAttributeParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeList1323);
            lv_entries_1_0=ruleAttribute();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getAttributeListRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		add(
              	       			current, 
              	       			"entries",
              	        		lv_entries_1_0, 
              	        		"Attribute", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:831:2: ( ',' ( (lv_entries_3_0= ruleAttribute ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==20) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:831:3: ',' ( (lv_entries_3_0= ruleAttribute ) )
            	    {
            	    match(input,20,FOLLOW_20_in_ruleAttributeList1333); if (failed) return current;
            	    if ( backtracking==0 ) {

            	              createLeafNode(grammarAccess.getAttributeListAccess().getCommaKeyword_2_0(), null); 
            	          
            	    }
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:835:1: ( (lv_entries_3_0= ruleAttribute ) )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:836:1: (lv_entries_3_0= ruleAttribute )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:836:1: (lv_entries_3_0= ruleAttribute )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:837:3: lv_entries_3_0= ruleAttribute
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getAttributeListAccess().getEntriesAttributeParserRuleCall_2_1_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleAttributeList1354);
            	    lv_entries_3_0=ruleAttribute();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getAttributeListRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"entries",
            	      	        		lv_entries_3_0, 
            	      	        		"Attribute", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            match(input,21,FOLLOW_21_in_ruleAttributeList1365); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getAttributeListAccess().getRightSquareBracketKeyword_3(), null); 
                  
            }

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleAttributeList


    // $ANTLR start entryRuleAttribute
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:870:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:870:51: (iv_ruleAttribute= ruleAttribute EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:871:2: iv_ruleAttribute= ruleAttribute EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getAttributeRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute1398);
            iv_ruleAttribute=ruleAttribute();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleAttribute; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute1408); if (failed) return current;

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
    // $ANTLR end entryRuleAttribute


    // $ANTLR start ruleAttribute
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:878:1: ruleAttribute returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:883:6: ( ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:884:1: ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:884:1: ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:884:2: ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:884:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:885:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:885:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:886:3: lv_name_0_0= ruleDotID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getAttributeAccess().getNameDotIDParserRuleCall_0_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleDotID_in_ruleAttribute1454);
            lv_name_0_0=ruleDotID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getAttributeRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_0_0, 
              	        		"DotID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:908:2: ( '=' ( (lv_value_2_0= ruleDotID ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==16) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:908:3: '=' ( (lv_value_2_0= ruleDotID ) )
                    {
                    match(input,16,FOLLOW_16_in_ruleAttribute1464); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getAttributeAccess().getEqualsSignKeyword_1_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:912:1: ( (lv_value_2_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:913:1: (lv_value_2_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:913:1: (lv_value_2_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:914:3: lv_value_2_0= ruleDotID
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getAttributeAccess().getValueDotIDParserRuleCall_1_1_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleDotID_in_ruleAttribute1485);
                    lv_value_2_0=ruleDotID();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getAttributeRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"value",
                      	        		lv_value_2_0, 
                      	        		"DotID", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleAttribute


    // $ANTLR start entryRuleNode
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:943:1: entryRuleNode returns [EObject current=null] : iv_ruleNode= ruleNode EOF ;
    public final EObject entryRuleNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNode = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:943:46: (iv_ruleNode= ruleNode EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:944:2: iv_ruleNode= ruleNode EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getNodeRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleNode_in_entryRuleNode1520);
            iv_ruleNode=ruleNode();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleNode; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNode1530); if (failed) return current;

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
    // $ANTLR end entryRuleNode


    // $ANTLR start ruleNode
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:951:1: ruleNode returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? ) ;
    public final EObject ruleNode() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_port_2_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:956:6: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:957:1: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:957:1: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:957:2: ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:957:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:958:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:958:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:959:3: lv_name_0_0= ruleDotID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getNodeAccess().getNameDotIDParserRuleCall_0_0(), currentNode); 
              	    
            }
            pushFollow(FOLLOW_ruleDotID_in_ruleNode1576);
            lv_name_0_0=ruleDotID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getNodeRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_0_0, 
              	        		"DotID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:981:2: ( ':' ( (lv_port_2_0= rulePort ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==22) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:981:3: ':' ( (lv_port_2_0= rulePort ) )
                    {
                    match(input,22,FOLLOW_22_in_ruleNode1586); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getNodeAccess().getColonKeyword_1_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:985:1: ( (lv_port_2_0= rulePort ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:986:1: (lv_port_2_0= rulePort )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:986:1: (lv_port_2_0= rulePort )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:987:3: lv_port_2_0= rulePort
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getNodeAccess().getPortPortParserRuleCall_1_1_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePort_in_ruleNode1607);
                    lv_port_2_0=rulePort();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getNodeRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"port",
                      	        		lv_port_2_0, 
                      	        		"Port", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleNode


    // $ANTLR start entryRulePort
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1016:1: entryRulePort returns [EObject current=null] : iv_rulePort= rulePort EOF ;
    public final EObject entryRulePort() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePort = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1016:46: (iv_rulePort= rulePort EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1017:2: iv_rulePort= rulePort EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getPortRule(), currentNode); 
            }
            pushFollow(FOLLOW_rulePort_in_entryRulePort1642);
            iv_rulePort=rulePort();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_rulePort; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePort1652); if (failed) return current;

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
    // $ANTLR end entryRulePort


    // $ANTLR start rulePort
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1024:1: rulePort returns [EObject current=null] : ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) ) ;
    public final EObject rulePort() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        Enumerator lv_compass_pt_2_0 = null;

        Enumerator lv_compass_pt_3_0 = null;


         @SuppressWarnings("unused") EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1029:6: ( ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1030:1: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1030:1: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_ID && LA19_0<=RULE_STRING)) ) {
                alt19=1;
            }
            else if ( ((LA19_0>=29 && LA19_0<=36)) ) {
                alt19=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("1030:1: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1030:2: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1030:2: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1030:3: ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )?
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1030:3: ( (lv_name_0_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1031:1: (lv_name_0_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1031:1: (lv_name_0_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1032:3: lv_name_0_0= ruleDotID
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getNameDotIDParserRuleCall_0_0_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleDotID_in_rulePort1699);
                    lv_name_0_0=ruleDotID();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"name",
                      	        		lv_name_0_0, 
                      	        		"DotID", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1054:2: ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==22) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1054:3: ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) )
                            {
                            match(input,22,FOLLOW_22_in_rulePort1709); if (failed) return current;
                            if ( backtracking==0 ) {

                                      createLeafNode(grammarAccess.getPortAccess().getColonKeyword_0_1_0(), null); 
                                  
                            }
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1058:1: ( (lv_compass_pt_2_0= ruleCompassPoint ) )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1059:1: (lv_compass_pt_2_0= ruleCompassPoint )
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1059:1: (lv_compass_pt_2_0= ruleCompassPoint )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1060:3: lv_compass_pt_2_0= ruleCompassPoint
                            {
                            if ( backtracking==0 ) {
                               
                              	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getCompass_ptCompassPointEnumRuleCall_0_1_1_0(), currentNode); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleCompassPoint_in_rulePort1730);
                            lv_compass_pt_2_0=ruleCompassPoint();
                            _fsp--;
                            if (failed) return current;
                            if ( backtracking==0 ) {

                              	        if (current==null) {
                              	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
                              	            associateNodeWithAstElement(currentNode.getParent(), current);
                              	        }
                              	        try {
                              	       		set(
                              	       			current, 
                              	       			"compass_pt",
                              	        		lv_compass_pt_2_0, 
                              	        		"CompassPoint", 
                              	        		currentNode);
                              	        } catch (ValueConverterException vce) {
                              				handleValueConverterException(vce);
                              	        }
                              	        currentNode = currentNode.getParent();
                              	    
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
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1083:6: ( (lv_compass_pt_3_0= ruleCompassPoint ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1083:6: ( (lv_compass_pt_3_0= ruleCompassPoint ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1084:1: (lv_compass_pt_3_0= ruleCompassPoint )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1084:1: (lv_compass_pt_3_0= ruleCompassPoint )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1085:3: lv_compass_pt_3_0= ruleCompassPoint
                    {
                    if ( backtracking==0 ) {
                       
                      	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getCompass_ptCompassPointEnumRuleCall_1_0(), currentNode); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleCompassPoint_in_rulePort1760);
                    lv_compass_pt_3_0=ruleCompassPoint();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode.getParent(), current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"compass_pt",
                      	        		lv_compass_pt_3_0, 
                      	        		"CompassPoint", 
                      	        		currentNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	        currentNode = currentNode.getParent();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end rulePort


    // $ANTLR start entryRuleDotID
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1114:1: entryRuleDotID returns [String current=null] : iv_ruleDotID= ruleDotID EOF ;
    public final String entryRuleDotID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDotID = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1114:46: (iv_ruleDotID= ruleDotID EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1115:2: iv_ruleDotID= ruleDotID EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getDotIDRule(), currentNode); 
            }
            pushFollow(FOLLOW_ruleDotID_in_entryRuleDotID1794);
            iv_ruleDotID=ruleDotID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleDotID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDotID1805); if (failed) return current;

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
    // $ANTLR end entryRuleDotID


    // $ANTLR start ruleDotID
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1122:1: ruleDotID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_FLOAT_1= RULE_FLOAT | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDotID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_FLOAT_1=null;
        Token this_STRING_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1127:6: ( (this_ID_0= RULE_ID | this_FLOAT_1= RULE_FLOAT | this_STRING_2= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1128:1: (this_ID_0= RULE_ID | this_FLOAT_1= RULE_FLOAT | this_STRING_2= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1128:1: (this_ID_0= RULE_ID | this_FLOAT_1= RULE_FLOAT | this_STRING_2= RULE_STRING )
            int alt20=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt20=1;
                }
                break;
            case RULE_FLOAT:
                {
                alt20=2;
                }
                break;
            case RULE_STRING:
                {
                alt20=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("1128:1: (this_ID_0= RULE_ID | this_FLOAT_1= RULE_FLOAT | this_STRING_2= RULE_STRING )", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1128:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDotID1845); if (failed) return current;
                    if ( backtracking==0 ) {

                      		current.merge(this_ID_0);
                          
                    }
                    if ( backtracking==0 ) {
                       
                          createLeafNode(grammarAccess.getDotIDAccess().getIDTerminalRuleCall_0(), null); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1136:10: this_FLOAT_1= RULE_FLOAT
                    {
                    this_FLOAT_1=(Token)input.LT(1);
                    match(input,RULE_FLOAT,FOLLOW_RULE_FLOAT_in_ruleDotID1871); if (failed) return current;
                    if ( backtracking==0 ) {

                      		current.merge(this_FLOAT_1);
                          
                    }
                    if ( backtracking==0 ) {
                       
                          createLeafNode(grammarAccess.getDotIDAccess().getFLOATTerminalRuleCall_1(), null); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1144:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDotID1897); if (failed) return current;
                    if ( backtracking==0 ) {

                      		current.merge(this_STRING_2);
                          
                    }
                    if ( backtracking==0 ) {
                       
                          createLeafNode(grammarAccess.getDotIDAccess().getSTRINGTerminalRuleCall_2(), null); 
                          
                    }

                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
              	    lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleDotID


    // $ANTLR start ruleEdgeOperator
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1159:1: ruleEdgeOperator returns [Enumerator current=null] : ( ( '->' ) | ( '--' ) ) ;
    public final Enumerator ruleEdgeOperator() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1163:6: ( ( ( '->' ) | ( '--' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1164:1: ( ( '->' ) | ( '--' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1164:1: ( ( '->' ) | ( '--' ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==23) ) {
                alt21=1;
            }
            else if ( (LA21_0==24) ) {
                alt21=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("1164:1: ( ( '->' ) | ( '--' ) )", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1164:2: ( '->' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1164:2: ( '->' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1164:4: '->'
                    {
                    match(input,23,FOLLOW_23_in_ruleEdgeOperator1954); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getEdgeOperatorAccess().getDirectedEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getEdgeOperatorAccess().getDirectedEnumLiteralDeclaration_0(), null); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1170:6: ( '--' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1170:6: ( '--' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1170:8: '--'
                    {
                    match(input,24,FOLLOW_24_in_ruleEdgeOperator1969); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getEdgeOperatorAccess().getUndirectedEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getEdgeOperatorAccess().getUndirectedEnumLiteralDeclaration_1(), null); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleEdgeOperator


    // $ANTLR start ruleGraphType
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1180:1: ruleGraphType returns [Enumerator current=null] : ( ( 'graph' ) | ( 'digraph' ) ) ;
    public final Enumerator ruleGraphType() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1184:6: ( ( ( 'graph' ) | ( 'digraph' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1185:1: ( ( 'graph' ) | ( 'digraph' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1185:1: ( ( 'graph' ) | ( 'digraph' ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==25) ) {
                alt22=1;
            }
            else if ( (LA22_0==26) ) {
                alt22=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("1185:1: ( ( 'graph' ) | ( 'digraph' ) )", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1185:2: ( 'graph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1185:2: ( 'graph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1185:4: 'graph'
                    {
                    match(input,25,FOLLOW_25_in_ruleGraphType2012); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getGraphTypeAccess().getGraphEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getGraphTypeAccess().getGraphEnumLiteralDeclaration_0(), null); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1191:6: ( 'digraph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1191:6: ( 'digraph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1191:8: 'digraph'
                    {
                    match(input,26,FOLLOW_26_in_ruleGraphType2027); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getGraphTypeAccess().getDigraphEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getGraphTypeAccess().getDigraphEnumLiteralDeclaration_1(), null); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleGraphType


    // $ANTLR start ruleAttributeType
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1201:1: ruleAttributeType returns [Enumerator current=null] : ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) ) ;
    public final Enumerator ruleAttributeType() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1205:6: ( ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1206:1: ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1206:1: ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) )
            int alt23=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt23=1;
                }
                break;
            case 27:
                {
                alt23=2;
                }
                break;
            case 28:
                {
                alt23=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("1206:1: ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) )", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1206:2: ( 'graph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1206:2: ( 'graph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1206:4: 'graph'
                    {
                    match(input,25,FOLLOW_25_in_ruleAttributeType2070); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getAttributeTypeAccess().getGraphEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getAttributeTypeAccess().getGraphEnumLiteralDeclaration_0(), null); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1212:6: ( 'node' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1212:6: ( 'node' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1212:8: 'node'
                    {
                    match(input,27,FOLLOW_27_in_ruleAttributeType2085); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getAttributeTypeAccess().getNodeEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getAttributeTypeAccess().getNodeEnumLiteralDeclaration_1(), null); 
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1218:6: ( 'edge' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1218:6: ( 'edge' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1218:8: 'edge'
                    {
                    match(input,28,FOLLOW_28_in_ruleAttributeType2100); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getAttributeTypeAccess().getEdgeEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getAttributeTypeAccess().getEdgeEnumLiteralDeclaration_2(), null); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleAttributeType


    // $ANTLR start ruleCompassPoint
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1228:1: ruleCompassPoint returns [Enumerator current=null] : ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) ) ;
    public final Enumerator ruleCompassPoint() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1232:6: ( ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1233:1: ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1233:1: ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) )
            int alt24=8;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt24=1;
                }
                break;
            case 30:
                {
                alt24=2;
                }
                break;
            case 31:
                {
                alt24=3;
                }
                break;
            case 32:
                {
                alt24=4;
                }
                break;
            case 33:
                {
                alt24=5;
                }
                break;
            case 34:
                {
                alt24=6;
                }
                break;
            case 35:
                {
                alt24=7;
                }
                break;
            case 36:
                {
                alt24=8;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("1233:1: ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) )", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1233:2: ( 'n' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1233:2: ( 'n' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1233:4: 'n'
                    {
                    match(input,29,FOLLOW_29_in_ruleCompassPoint2143); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getNorthEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getNorthEnumLiteralDeclaration_0(), null); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1239:6: ( 'ne' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1239:6: ( 'ne' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1239:8: 'ne'
                    {
                    match(input,30,FOLLOW_30_in_ruleCompassPoint2158); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getNortheastEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getNortheastEnumLiteralDeclaration_1(), null); 
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1245:6: ( 'e' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1245:6: ( 'e' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1245:8: 'e'
                    {
                    match(input,31,FOLLOW_31_in_ruleCompassPoint2173); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getEastEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getEastEnumLiteralDeclaration_2(), null); 
                          
                    }

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1251:6: ( 'se' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1251:6: ( 'se' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1251:8: 'se'
                    {
                    match(input,32,FOLLOW_32_in_ruleCompassPoint2188); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getSoutheastEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getSoutheastEnumLiteralDeclaration_3(), null); 
                          
                    }

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1257:6: ( 's' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1257:6: ( 's' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1257:8: 's'
                    {
                    match(input,33,FOLLOW_33_in_ruleCompassPoint2203); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getSouthEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getSouthEnumLiteralDeclaration_4(), null); 
                          
                    }

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1263:6: ( 'sw' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1263:6: ( 'sw' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1263:8: 'sw'
                    {
                    match(input,34,FOLLOW_34_in_ruleCompassPoint2218); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getSouthwestEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getSouthwestEnumLiteralDeclaration_5(), null); 
                          
                    }

                    }


                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1269:6: ( 'w' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1269:6: ( 'w' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1269:8: 'w'
                    {
                    match(input,35,FOLLOW_35_in_ruleCompassPoint2233); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getWestEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getWestEnumLiteralDeclaration_6(), null); 
                          
                    }

                    }


                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1275:6: ( 'nw' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1275:6: ( 'nw' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:1275:8: 'nw'
                    {
                    match(input,36,FOLLOW_36_in_ruleCompassPoint2248); if (failed) return current;
                    if ( backtracking==0 ) {

                              current = grammarAccess.getCompassPointAccess().getNorthwestEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                              createLeafNode(grammarAccess.getCompassPointAccess().getNorthwestEnumLiteralDeclaration_7(), null); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleCompassPoint

    // $ANTLR start synpred5
    public final void synpred5_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:256:5: ( ruleEdgeStatement )
        // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:256:5: ruleEdgeStatement
        {
        if ( backtracking==0 ) {
           
                  currentNode=createCompositeNode(grammarAccess.getStatementAccess().getEdgeStatementParserRuleCall_0_0(), currentNode); 
              
        }
        pushFollow(FOLLOW_ruleEdgeStatement_in_synpred5408);
        ruleEdgeStatement();
        _fsp--;
        if (failed) return ;

        }
    }
    // $ANTLR end synpred5

    // $ANTLR start synpred6
    public final void synpred6_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:266:5: ( ruleNodeStatement )
        // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:266:5: ruleNodeStatement
        {
        if ( backtracking==0 ) {
           
                  currentNode=createCompositeNode(grammarAccess.getStatementAccess().getNodeStatementParserRuleCall_0_1(), currentNode); 
              
        }
        pushFollow(FOLLOW_ruleNodeStatement_in_synpred6435);
        ruleNodeStatement();
        _fsp--;
        if (failed) return ;

        }
    }
    // $ANTLR end synpred6

    // $ANTLR start synpred8
    public final void synpred8_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:286:5: ( ruleSubgraph )
        // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/parser/antlr/internal/InternalDot.g:286:5: ruleSubgraph
        {
        if ( backtracking==0 ) {
           
                  currentNode=createCompositeNode(grammarAccess.getStatementAccess().getSubgraphParserRuleCall_0_3(), currentNode); 
              
        }
        pushFollow(FOLLOW_ruleSubgraph_in_synpred8489);
        ruleSubgraph();
        _fsp--;
        if (failed) return ;

        }
    }
    // $ANTLR end synpred8

    public final boolean synpred5() {
        backtracking++;
        int start = input.mark();
        try {
            synpred5_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred6() {
        backtracking++;
        int start = input.mark();
        try {
            synpred6_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred8() {
        backtracking++;
        int start = input.mark();
        try {
            synpred8_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_ruleGraphvizModel_in_entryRuleGraphvizModel79 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGraphvizModel89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGraph_in_ruleGraphvizModel134 = new BitSet(new long[]{0x0000000006002002L});
    public static final BitSet FOLLOW_ruleGraph_in_entryRuleGraph167 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGraph177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleGraph219 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_ruleGraphType_in_ruleGraph254 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGraph271 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleGraph286 = new BitSet(new long[]{0x000000001A04C070L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleGraph307 = new BitSet(new long[]{0x000000001A04C070L});
    public static final BitSet FOLLOW_15_in_ruleGraph317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStatement_in_entryRuleStatement350 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStatement360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeStatement_in_ruleStatement408 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleNodeStatement_in_ruleStatement435 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleAttributeStatement_in_ruleStatement462 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_ruleStatement489 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleStatement516 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleStatement525 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleStatement546 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_ruleStatement558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeStatement_in_entryRuleEdgeStatement593 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEdgeStatement603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_ruleEdgeStatement650 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_ruleNode_in_ruleEdgeStatement677 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_ruleEdgeTarget_in_ruleEdgeStatement699 = new BitSet(new long[]{0x0000000001880002L});
    public static final BitSet FOLLOW_ruleAttributeList_in_ruleEdgeStatement721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeTarget_in_entryRuleEdgeTarget755 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEdgeTarget765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeOperator_in_ruleEdgeTarget811 = new BitSet(new long[]{0x0000000000044070L});
    public static final BitSet FOLLOW_ruleSubgraph_in_ruleEdgeTarget833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleEdgeTarget860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNodeStatement_in_entryRuleNodeStatement894 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNodeStatement904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleNodeStatement950 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_ruleAttributeList_in_ruleNodeStatement971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeStatement_in_entryRuleAttributeStatement1005 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeStatement1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeType_in_ruleAttributeStatement1061 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleAttributeList_in_ruleAttributeStatement1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_entryRuleSubgraph1115 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubgraph1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleSubgraph1160 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSubgraph1177 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleSubgraph1194 = new BitSet(new long[]{0x000000001A04C070L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleSubgraph1215 = new BitSet(new long[]{0x000000001A04C070L});
    public static final BitSet FOLLOW_15_in_ruleSubgraph1225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeList_in_entryRuleAttributeList1258 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeList1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleAttributeList1302 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeList1323 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_20_in_ruleAttributeList1333 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleAttributeList1354 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_21_in_ruleAttributeList1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute1398 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute1408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleAttribute1454 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleAttribute1464 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleAttribute1485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_entryRuleNode1520 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNode1530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleNode1576 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_ruleNode1586 = new BitSet(new long[]{0x0000001FE0000070L});
    public static final BitSet FOLLOW_rulePort_in_ruleNode1607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePort_in_entryRulePort1642 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePort1652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_rulePort1699 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_rulePort1709 = new BitSet(new long[]{0x0000001FE0000000L});
    public static final BitSet FOLLOW_ruleCompassPoint_in_rulePort1730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompassPoint_in_rulePort1760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_entryRuleDotID1794 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDotID1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDotID1845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_FLOAT_in_ruleDotID1871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDotID1897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleEdgeOperator1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleEdgeOperator1969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleGraphType2012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleGraphType2027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleAttributeType2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleAttributeType2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleAttributeType2100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleCompassPoint2143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleCompassPoint2158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleCompassPoint2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleCompassPoint2188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleCompassPoint2203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleCompassPoint2218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleCompassPoint2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleCompassPoint2248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeStatement_in_synpred5408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNodeStatement_in_synpred6435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_synpred8489 = new BitSet(new long[]{0x0000000000000002L});

}