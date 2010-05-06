package de.cau.cs.kieler.kiml.graphviz.dot.parser.antlr.internal; 

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
    public static final int RULE_STRING=7;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_INT=5;
    public static final int RULE_PREC_LINE=8;
    public static final int RULE_WS=11;
    public static final int RULE_FLOAT=6;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;

        public InternalGraphvizDotParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g"; }



     	private GraphvizDotGrammarAccess grammarAccess;
     	
        public InternalGraphvizDotParser(TokenStream input, IAstFactory factory, GraphvizDotGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "GraphvizModel";	
       	}
       	
       	@Override
       	protected GraphvizDotGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleGraphvizModel
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:78:1: entryRuleGraphvizModel returns [EObject current=null] : iv_ruleGraphvizModel= ruleGraphvizModel EOF ;
    public final EObject entryRuleGraphvizModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGraphvizModel = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:79:2: (iv_ruleGraphvizModel= ruleGraphvizModel EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:80:2: iv_ruleGraphvizModel= ruleGraphvizModel EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGraphvizModelRule(), currentNode); 
            pushFollow(FOLLOW_ruleGraphvizModel_in_entryRuleGraphvizModel75);
            iv_ruleGraphvizModel=ruleGraphvizModel();
            _fsp--;

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
    // $ANTLR end entryRuleGraphvizModel


    // $ANTLR start ruleGraphvizModel
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:87:1: ruleGraphvizModel returns [EObject current=null] : ( (lv_graphs_0_0= ruleGraph ) )* ;
    public final EObject ruleGraphvizModel() throws RecognitionException {
        EObject current = null;

        EObject lv_graphs_0_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:92:6: ( ( (lv_graphs_0_0= ruleGraph ) )* )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:93:1: ( (lv_graphs_0_0= ruleGraph ) )*
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:93:1: ( (lv_graphs_0_0= ruleGraph ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13||(LA1_0>=25 && LA1_0<=26)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:94:1: (lv_graphs_0_0= ruleGraph )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:94:1: (lv_graphs_0_0= ruleGraph )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:95:3: lv_graphs_0_0= ruleGraph
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getGraphvizModelAccess().getGraphsGraphParserRuleCall_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleGraph_in_ruleGraphvizModel130);
            	    lv_graphs_0_0=ruleGraph();
            	    _fsp--;


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
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:125:1: entryRuleGraph returns [EObject current=null] : iv_ruleGraph= ruleGraph EOF ;
    public final EObject entryRuleGraph() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGraph = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:126:2: (iv_ruleGraph= ruleGraph EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:127:2: iv_ruleGraph= ruleGraph EOF
            {
             currentNode = createCompositeNode(grammarAccess.getGraphRule(), currentNode); 
            pushFollow(FOLLOW_ruleGraph_in_entryRuleGraph166);
            iv_ruleGraph=ruleGraph();
            _fsp--;

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
    // $ANTLR end entryRuleGraph


    // $ANTLR start ruleGraph
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:134:1: ruleGraph returns [EObject current=null] : ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' ) ;
    public final EObject ruleGraph() throws RecognitionException {
        EObject current = null;

        Token lv_strict_0_0=null;
        Token lv_name_2_0=null;
        Enumerator lv_type_1_0 = null;

        EObject lv_statements_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:139:6: ( ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:140:1: ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:140:1: ( ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:140:2: ( (lv_strict_0_0= 'strict' ) )? ( (lv_type_1_0= ruleGraphType ) ) ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:140:2: ( (lv_strict_0_0= 'strict' ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:141:1: (lv_strict_0_0= 'strict' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:141:1: (lv_strict_0_0= 'strict' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:142:3: lv_strict_0_0= 'strict'
                    {
                    lv_strict_0_0=(Token)input.LT(1);
                    match(input,13,FOLLOW_13_in_ruleGraph219); 

                            createLeafNode(grammarAccess.getGraphAccess().getStrictStrictKeyword_0_0(), "strict"); 
                        

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
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:161:3: ( (lv_type_1_0= ruleGraphType ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:162:1: (lv_type_1_0= ruleGraphType )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:162:1: (lv_type_1_0= ruleGraphType )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:163:3: lv_type_1_0= ruleGraphType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getGraphAccess().getTypeGraphTypeEnumRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleGraphType_in_ruleGraph254);
            lv_type_1_0=ruleGraphType();
            _fsp--;


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

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:185:2: ( (lv_name_2_0= RULE_ID ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:186:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:186:1: (lv_name_2_0= RULE_ID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:187:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGraph271); 

                    			createLeafNode(grammarAccess.getGraphAccess().getNameIDTerminalRuleCall_2_0(), "name"); 
                    		

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
                    break;

            }

            match(input,14,FOLLOW_14_in_ruleGraph287); 

                    createLeafNode(grammarAccess.getGraphAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:213:1: ( (lv_statements_4_0= ruleStatement ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=RULE_ID && LA4_0<=RULE_STRING)||LA4_0==20||LA4_0==25||(LA4_0>=27 && LA4_0<=28)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:214:1: (lv_statements_4_0= ruleStatement )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:214:1: (lv_statements_4_0= ruleStatement )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:215:3: lv_statements_4_0= ruleStatement
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getGraphAccess().getStatementsStatementParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleStatement_in_ruleGraph308);
            	    lv_statements_4_0=ruleStatement();
            	    _fsp--;


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
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,15,FOLLOW_15_in_ruleGraph319); 

                    createLeafNode(grammarAccess.getGraphAccess().getRightCurlyBracketKeyword_5(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:249:1: entryRuleStatement returns [EObject current=null] : iv_ruleStatement= ruleStatement EOF ;
    public final EObject entryRuleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:250:2: (iv_ruleStatement= ruleStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:251:2: iv_ruleStatement= ruleStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleStatement_in_entryRuleStatement355);
            iv_ruleStatement=ruleStatement();
            _fsp--;

             current =iv_ruleStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStatement365); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:258:1: ruleStatement returns [EObject current=null] : ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) ( ';' )? ) ;
    public final EObject ruleStatement() throws RecognitionException {
        EObject current = null;

        EObject this_EdgeStatement_0 = null;

        EObject this_NodeStatement_1 = null;

        EObject this_Attribute_2 = null;

        EObject this_AttributeStatement_3 = null;

        EObject this_Subgraph_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:263:6: ( ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) ( ';' )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:264:1: ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) ( ';' )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:264:1: ( (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) ( ';' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph ) ( ';' )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )
            int alt5=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                switch ( input.LA(2) ) {
                case 22:
                    {
                    switch ( input.LA(3) ) {
                    case RULE_ID:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 11, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_INT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 12, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_FLOAT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 13, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_STRING:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 14, input);

                            throw nvae;
                        }

                        }
                        break;
                    case 29:
                        {
                        int LA5_15 = input.LA(4);

                        if ( ((LA5_15>=23 && LA5_15<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_15==EOF||(LA5_15>=RULE_ID && LA5_15<=RULE_STRING)||(LA5_15>=15 && LA5_15<=17)||LA5_15==20||LA5_15==25||(LA5_15>=27 && LA5_15<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 30:
                        {
                        int LA5_16 = input.LA(4);

                        if ( ((LA5_16>=23 && LA5_16<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_16==EOF||(LA5_16>=RULE_ID && LA5_16<=RULE_STRING)||(LA5_16>=15 && LA5_16<=17)||LA5_16==20||LA5_16==25||(LA5_16>=27 && LA5_16<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 31:
                        {
                        int LA5_17 = input.LA(4);

                        if ( ((LA5_17>=23 && LA5_17<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_17==EOF||(LA5_17>=RULE_ID && LA5_17<=RULE_STRING)||(LA5_17>=15 && LA5_17<=17)||LA5_17==20||LA5_17==25||(LA5_17>=27 && LA5_17<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 32:
                        {
                        int LA5_18 = input.LA(4);

                        if ( ((LA5_18>=23 && LA5_18<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_18==EOF||(LA5_18>=RULE_ID && LA5_18<=RULE_STRING)||(LA5_18>=15 && LA5_18<=17)||LA5_18==20||LA5_18==25||(LA5_18>=27 && LA5_18<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 33:
                        {
                        int LA5_19 = input.LA(4);

                        if ( (LA5_19==EOF||(LA5_19>=RULE_ID && LA5_19<=RULE_STRING)||(LA5_19>=15 && LA5_19<=17)||LA5_19==20||LA5_19==25||(LA5_19>=27 && LA5_19<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_19>=23 && LA5_19<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 34:
                        {
                        int LA5_20 = input.LA(4);

                        if ( (LA5_20==EOF||(LA5_20>=RULE_ID && LA5_20<=RULE_STRING)||(LA5_20>=15 && LA5_20<=17)||LA5_20==20||LA5_20==25||(LA5_20>=27 && LA5_20<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_20>=23 && LA5_20<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 35:
                        {
                        int LA5_21 = input.LA(4);

                        if ( (LA5_21==EOF||(LA5_21>=RULE_ID && LA5_21<=RULE_STRING)||(LA5_21>=15 && LA5_21<=17)||LA5_21==20||LA5_21==25||(LA5_21>=27 && LA5_21<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_21>=23 && LA5_21<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 36:
                        {
                        int LA5_22 = input.LA(4);

                        if ( (LA5_22==EOF||(LA5_22>=RULE_ID && LA5_22<=RULE_STRING)||(LA5_22>=15 && LA5_22<=17)||LA5_22==20||LA5_22==25||(LA5_22>=27 && LA5_22<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_22>=23 && LA5_22<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 22, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 37:
                        {
                        int LA5_23 = input.LA(4);

                        if ( (LA5_23==EOF||(LA5_23>=RULE_ID && LA5_23<=RULE_STRING)||(LA5_23>=15 && LA5_23<=17)||LA5_23==20||LA5_23==25||(LA5_23>=27 && LA5_23<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_23>=23 && LA5_23<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 23, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 38:
                        {
                        int LA5_24 = input.LA(4);

                        if ( (LA5_24==EOF||(LA5_24>=RULE_ID && LA5_24<=RULE_STRING)||(LA5_24>=15 && LA5_24<=17)||LA5_24==20||LA5_24==25||(LA5_24>=27 && LA5_24<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_24>=23 && LA5_24<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 24, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 7, input);

                        throw nvae;
                    }

                    }
                    break;
                case EOF:
                case RULE_ID:
                case RULE_INT:
                case RULE_FLOAT:
                case RULE_STRING:
                case 15:
                case 16:
                case 17:
                case 20:
                case 25:
                case 27:
                case 28:
                    {
                    alt5=2;
                    }
                    break;
                case 21:
                    {
                    alt5=3;
                    }
                    break;
                case 23:
                case 24:
                    {
                    alt5=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 1, input);

                    throw nvae;
                }

                }
                break;
            case RULE_INT:
                {
                switch ( input.LA(2) ) {
                case 22:
                    {
                    switch ( input.LA(3) ) {
                    case RULE_ID:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 11, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_INT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 12, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_FLOAT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 13, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_STRING:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 14, input);

                            throw nvae;
                        }

                        }
                        break;
                    case 29:
                        {
                        int LA5_15 = input.LA(4);

                        if ( ((LA5_15>=23 && LA5_15<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_15==EOF||(LA5_15>=RULE_ID && LA5_15<=RULE_STRING)||(LA5_15>=15 && LA5_15<=17)||LA5_15==20||LA5_15==25||(LA5_15>=27 && LA5_15<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 30:
                        {
                        int LA5_16 = input.LA(4);

                        if ( ((LA5_16>=23 && LA5_16<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_16==EOF||(LA5_16>=RULE_ID && LA5_16<=RULE_STRING)||(LA5_16>=15 && LA5_16<=17)||LA5_16==20||LA5_16==25||(LA5_16>=27 && LA5_16<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 31:
                        {
                        int LA5_17 = input.LA(4);

                        if ( ((LA5_17>=23 && LA5_17<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_17==EOF||(LA5_17>=RULE_ID && LA5_17<=RULE_STRING)||(LA5_17>=15 && LA5_17<=17)||LA5_17==20||LA5_17==25||(LA5_17>=27 && LA5_17<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 32:
                        {
                        int LA5_18 = input.LA(4);

                        if ( ((LA5_18>=23 && LA5_18<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_18==EOF||(LA5_18>=RULE_ID && LA5_18<=RULE_STRING)||(LA5_18>=15 && LA5_18<=17)||LA5_18==20||LA5_18==25||(LA5_18>=27 && LA5_18<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 33:
                        {
                        int LA5_19 = input.LA(4);

                        if ( (LA5_19==EOF||(LA5_19>=RULE_ID && LA5_19<=RULE_STRING)||(LA5_19>=15 && LA5_19<=17)||LA5_19==20||LA5_19==25||(LA5_19>=27 && LA5_19<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_19>=23 && LA5_19<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 34:
                        {
                        int LA5_20 = input.LA(4);

                        if ( (LA5_20==EOF||(LA5_20>=RULE_ID && LA5_20<=RULE_STRING)||(LA5_20>=15 && LA5_20<=17)||LA5_20==20||LA5_20==25||(LA5_20>=27 && LA5_20<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_20>=23 && LA5_20<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 35:
                        {
                        int LA5_21 = input.LA(4);

                        if ( (LA5_21==EOF||(LA5_21>=RULE_ID && LA5_21<=RULE_STRING)||(LA5_21>=15 && LA5_21<=17)||LA5_21==20||LA5_21==25||(LA5_21>=27 && LA5_21<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_21>=23 && LA5_21<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 36:
                        {
                        int LA5_22 = input.LA(4);

                        if ( (LA5_22==EOF||(LA5_22>=RULE_ID && LA5_22<=RULE_STRING)||(LA5_22>=15 && LA5_22<=17)||LA5_22==20||LA5_22==25||(LA5_22>=27 && LA5_22<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_22>=23 && LA5_22<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 22, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 37:
                        {
                        int LA5_23 = input.LA(4);

                        if ( (LA5_23==EOF||(LA5_23>=RULE_ID && LA5_23<=RULE_STRING)||(LA5_23>=15 && LA5_23<=17)||LA5_23==20||LA5_23==25||(LA5_23>=27 && LA5_23<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_23>=23 && LA5_23<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 23, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 38:
                        {
                        int LA5_24 = input.LA(4);

                        if ( (LA5_24==EOF||(LA5_24>=RULE_ID && LA5_24<=RULE_STRING)||(LA5_24>=15 && LA5_24<=17)||LA5_24==20||LA5_24==25||(LA5_24>=27 && LA5_24<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_24>=23 && LA5_24<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 24, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 7, input);

                        throw nvae;
                    }

                    }
                    break;
                case EOF:
                case RULE_ID:
                case RULE_INT:
                case RULE_FLOAT:
                case RULE_STRING:
                case 15:
                case 16:
                case 17:
                case 20:
                case 25:
                case 27:
                case 28:
                    {
                    alt5=2;
                    }
                    break;
                case 23:
                case 24:
                    {
                    alt5=1;
                    }
                    break;
                case 21:
                    {
                    alt5=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 2, input);

                    throw nvae;
                }

                }
                break;
            case RULE_FLOAT:
                {
                switch ( input.LA(2) ) {
                case 22:
                    {
                    switch ( input.LA(3) ) {
                    case RULE_ID:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 11, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_INT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 12, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_FLOAT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 13, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_STRING:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 14, input);

                            throw nvae;
                        }

                        }
                        break;
                    case 29:
                        {
                        int LA5_15 = input.LA(4);

                        if ( ((LA5_15>=23 && LA5_15<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_15==EOF||(LA5_15>=RULE_ID && LA5_15<=RULE_STRING)||(LA5_15>=15 && LA5_15<=17)||LA5_15==20||LA5_15==25||(LA5_15>=27 && LA5_15<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 30:
                        {
                        int LA5_16 = input.LA(4);

                        if ( ((LA5_16>=23 && LA5_16<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_16==EOF||(LA5_16>=RULE_ID && LA5_16<=RULE_STRING)||(LA5_16>=15 && LA5_16<=17)||LA5_16==20||LA5_16==25||(LA5_16>=27 && LA5_16<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 31:
                        {
                        int LA5_17 = input.LA(4);

                        if ( ((LA5_17>=23 && LA5_17<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_17==EOF||(LA5_17>=RULE_ID && LA5_17<=RULE_STRING)||(LA5_17>=15 && LA5_17<=17)||LA5_17==20||LA5_17==25||(LA5_17>=27 && LA5_17<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 32:
                        {
                        int LA5_18 = input.LA(4);

                        if ( ((LA5_18>=23 && LA5_18<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_18==EOF||(LA5_18>=RULE_ID && LA5_18<=RULE_STRING)||(LA5_18>=15 && LA5_18<=17)||LA5_18==20||LA5_18==25||(LA5_18>=27 && LA5_18<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 33:
                        {
                        int LA5_19 = input.LA(4);

                        if ( (LA5_19==EOF||(LA5_19>=RULE_ID && LA5_19<=RULE_STRING)||(LA5_19>=15 && LA5_19<=17)||LA5_19==20||LA5_19==25||(LA5_19>=27 && LA5_19<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_19>=23 && LA5_19<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 34:
                        {
                        int LA5_20 = input.LA(4);

                        if ( (LA5_20==EOF||(LA5_20>=RULE_ID && LA5_20<=RULE_STRING)||(LA5_20>=15 && LA5_20<=17)||LA5_20==20||LA5_20==25||(LA5_20>=27 && LA5_20<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_20>=23 && LA5_20<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 35:
                        {
                        int LA5_21 = input.LA(4);

                        if ( (LA5_21==EOF||(LA5_21>=RULE_ID && LA5_21<=RULE_STRING)||(LA5_21>=15 && LA5_21<=17)||LA5_21==20||LA5_21==25||(LA5_21>=27 && LA5_21<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_21>=23 && LA5_21<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 36:
                        {
                        int LA5_22 = input.LA(4);

                        if ( (LA5_22==EOF||(LA5_22>=RULE_ID && LA5_22<=RULE_STRING)||(LA5_22>=15 && LA5_22<=17)||LA5_22==20||LA5_22==25||(LA5_22>=27 && LA5_22<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_22>=23 && LA5_22<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 22, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 37:
                        {
                        int LA5_23 = input.LA(4);

                        if ( (LA5_23==EOF||(LA5_23>=RULE_ID && LA5_23<=RULE_STRING)||(LA5_23>=15 && LA5_23<=17)||LA5_23==20||LA5_23==25||(LA5_23>=27 && LA5_23<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_23>=23 && LA5_23<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 23, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 38:
                        {
                        int LA5_24 = input.LA(4);

                        if ( (LA5_24==EOF||(LA5_24>=RULE_ID && LA5_24<=RULE_STRING)||(LA5_24>=15 && LA5_24<=17)||LA5_24==20||LA5_24==25||(LA5_24>=27 && LA5_24<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_24>=23 && LA5_24<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 24, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 7, input);

                        throw nvae;
                    }

                    }
                    break;
                case 23:
                case 24:
                    {
                    alt5=1;
                    }
                    break;
                case EOF:
                case RULE_ID:
                case RULE_INT:
                case RULE_FLOAT:
                case RULE_STRING:
                case 15:
                case 16:
                case 17:
                case 20:
                case 25:
                case 27:
                case 28:
                    {
                    alt5=2;
                    }
                    break;
                case 21:
                    {
                    alt5=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 3, input);

                    throw nvae;
                }

                }
                break;
            case RULE_STRING:
                {
                switch ( input.LA(2) ) {
                case 21:
                    {
                    alt5=3;
                    }
                    break;
                case 22:
                    {
                    switch ( input.LA(3) ) {
                    case RULE_ID:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 11, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_INT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 12, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_FLOAT:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 13, input);

                            throw nvae;
                        }

                        }
                        break;
                    case RULE_STRING:
                        {
                        switch ( input.LA(4) ) {
                        case 22:
                            {
                            switch ( input.LA(5) ) {
                            case 29:
                                {
                                int LA5_26 = input.LA(6);

                                if ( ((LA5_26>=23 && LA5_26<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_26==EOF||(LA5_26>=RULE_ID && LA5_26<=RULE_STRING)||(LA5_26>=15 && LA5_26<=17)||LA5_26==20||LA5_26==25||(LA5_26>=27 && LA5_26<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 26, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 30:
                                {
                                int LA5_27 = input.LA(6);

                                if ( ((LA5_27>=23 && LA5_27<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_27==EOF||(LA5_27>=RULE_ID && LA5_27<=RULE_STRING)||(LA5_27>=15 && LA5_27<=17)||LA5_27==20||LA5_27==25||(LA5_27>=27 && LA5_27<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 27, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 31:
                                {
                                int LA5_28 = input.LA(6);

                                if ( (LA5_28==EOF||(LA5_28>=RULE_ID && LA5_28<=RULE_STRING)||(LA5_28>=15 && LA5_28<=17)||LA5_28==20||LA5_28==25||(LA5_28>=27 && LA5_28<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_28>=23 && LA5_28<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 28, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 32:
                                {
                                int LA5_29 = input.LA(6);

                                if ( ((LA5_29>=23 && LA5_29<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_29==EOF||(LA5_29>=RULE_ID && LA5_29<=RULE_STRING)||(LA5_29>=15 && LA5_29<=17)||LA5_29==20||LA5_29==25||(LA5_29>=27 && LA5_29<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 29, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 33:
                                {
                                int LA5_30 = input.LA(6);

                                if ( ((LA5_30>=23 && LA5_30<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_30==EOF||(LA5_30>=RULE_ID && LA5_30<=RULE_STRING)||(LA5_30>=15 && LA5_30<=17)||LA5_30==20||LA5_30==25||(LA5_30>=27 && LA5_30<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 30, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 34:
                                {
                                int LA5_31 = input.LA(6);

                                if ( ((LA5_31>=23 && LA5_31<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_31==EOF||(LA5_31>=RULE_ID && LA5_31<=RULE_STRING)||(LA5_31>=15 && LA5_31<=17)||LA5_31==20||LA5_31==25||(LA5_31>=27 && LA5_31<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 31, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 35:
                                {
                                int LA5_32 = input.LA(6);

                                if ( ((LA5_32>=23 && LA5_32<=24)) ) {
                                    alt5=1;
                                }
                                else if ( (LA5_32==EOF||(LA5_32>=RULE_ID && LA5_32<=RULE_STRING)||(LA5_32>=15 && LA5_32<=17)||LA5_32==20||LA5_32==25||(LA5_32>=27 && LA5_32<=28)) ) {
                                    alt5=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 32, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 36:
                                {
                                int LA5_33 = input.LA(6);

                                if ( (LA5_33==EOF||(LA5_33>=RULE_ID && LA5_33<=RULE_STRING)||(LA5_33>=15 && LA5_33<=17)||LA5_33==20||LA5_33==25||(LA5_33>=27 && LA5_33<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_33>=23 && LA5_33<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 33, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 37:
                                {
                                int LA5_34 = input.LA(6);

                                if ( (LA5_34==EOF||(LA5_34>=RULE_ID && LA5_34<=RULE_STRING)||(LA5_34>=15 && LA5_34<=17)||LA5_34==20||LA5_34==25||(LA5_34>=27 && LA5_34<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_34>=23 && LA5_34<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 34, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case 38:
                                {
                                int LA5_35 = input.LA(6);

                                if ( (LA5_35==EOF||(LA5_35>=RULE_ID && LA5_35<=RULE_STRING)||(LA5_35>=15 && LA5_35<=17)||LA5_35==20||LA5_35==25||(LA5_35>=27 && LA5_35<=28)) ) {
                                    alt5=2;
                                }
                                else if ( ((LA5_35>=23 && LA5_35<=24)) ) {
                                    alt5=1;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 35, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 25, input);

                                throw nvae;
                            }

                            }
                            break;
                        case EOF:
                        case RULE_ID:
                        case RULE_INT:
                        case RULE_FLOAT:
                        case RULE_STRING:
                        case 15:
                        case 16:
                        case 17:
                        case 20:
                        case 25:
                        case 27:
                        case 28:
                            {
                            alt5=2;
                            }
                            break;
                        case 23:
                        case 24:
                            {
                            alt5=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 14, input);

                            throw nvae;
                        }

                        }
                        break;
                    case 29:
                        {
                        int LA5_15 = input.LA(4);

                        if ( ((LA5_15>=23 && LA5_15<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_15==EOF||(LA5_15>=RULE_ID && LA5_15<=RULE_STRING)||(LA5_15>=15 && LA5_15<=17)||LA5_15==20||LA5_15==25||(LA5_15>=27 && LA5_15<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 30:
                        {
                        int LA5_16 = input.LA(4);

                        if ( ((LA5_16>=23 && LA5_16<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_16==EOF||(LA5_16>=RULE_ID && LA5_16<=RULE_STRING)||(LA5_16>=15 && LA5_16<=17)||LA5_16==20||LA5_16==25||(LA5_16>=27 && LA5_16<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 16, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 31:
                        {
                        int LA5_17 = input.LA(4);

                        if ( ((LA5_17>=23 && LA5_17<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_17==EOF||(LA5_17>=RULE_ID && LA5_17<=RULE_STRING)||(LA5_17>=15 && LA5_17<=17)||LA5_17==20||LA5_17==25||(LA5_17>=27 && LA5_17<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 17, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 32:
                        {
                        int LA5_18 = input.LA(4);

                        if ( ((LA5_18>=23 && LA5_18<=24)) ) {
                            alt5=1;
                        }
                        else if ( (LA5_18==EOF||(LA5_18>=RULE_ID && LA5_18<=RULE_STRING)||(LA5_18>=15 && LA5_18<=17)||LA5_18==20||LA5_18==25||(LA5_18>=27 && LA5_18<=28)) ) {
                            alt5=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 18, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 33:
                        {
                        int LA5_19 = input.LA(4);

                        if ( (LA5_19==EOF||(LA5_19>=RULE_ID && LA5_19<=RULE_STRING)||(LA5_19>=15 && LA5_19<=17)||LA5_19==20||LA5_19==25||(LA5_19>=27 && LA5_19<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_19>=23 && LA5_19<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 19, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 34:
                        {
                        int LA5_20 = input.LA(4);

                        if ( (LA5_20==EOF||(LA5_20>=RULE_ID && LA5_20<=RULE_STRING)||(LA5_20>=15 && LA5_20<=17)||LA5_20==20||LA5_20==25||(LA5_20>=27 && LA5_20<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_20>=23 && LA5_20<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 20, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 35:
                        {
                        int LA5_21 = input.LA(4);

                        if ( (LA5_21==EOF||(LA5_21>=RULE_ID && LA5_21<=RULE_STRING)||(LA5_21>=15 && LA5_21<=17)||LA5_21==20||LA5_21==25||(LA5_21>=27 && LA5_21<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_21>=23 && LA5_21<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 21, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 36:
                        {
                        int LA5_22 = input.LA(4);

                        if ( (LA5_22==EOF||(LA5_22>=RULE_ID && LA5_22<=RULE_STRING)||(LA5_22>=15 && LA5_22<=17)||LA5_22==20||LA5_22==25||(LA5_22>=27 && LA5_22<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_22>=23 && LA5_22<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 22, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 37:
                        {
                        int LA5_23 = input.LA(4);

                        if ( (LA5_23==EOF||(LA5_23>=RULE_ID && LA5_23<=RULE_STRING)||(LA5_23>=15 && LA5_23<=17)||LA5_23==20||LA5_23==25||(LA5_23>=27 && LA5_23<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_23>=23 && LA5_23<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 23, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 38:
                        {
                        int LA5_24 = input.LA(4);

                        if ( (LA5_24==EOF||(LA5_24>=RULE_ID && LA5_24<=RULE_STRING)||(LA5_24>=15 && LA5_24<=17)||LA5_24==20||LA5_24==25||(LA5_24>=27 && LA5_24<=28)) ) {
                            alt5=2;
                        }
                        else if ( ((LA5_24>=23 && LA5_24<=24)) ) {
                            alt5=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 24, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 7, input);

                        throw nvae;
                    }

                    }
                    break;
                case 23:
                case 24:
                    {
                    alt5=1;
                    }
                    break;
                case EOF:
                case RULE_ID:
                case RULE_INT:
                case RULE_FLOAT:
                case RULE_STRING:
                case 15:
                case 16:
                case 17:
                case 20:
                case 25:
                case 27:
                case 28:
                    {
                    alt5=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 4, input);

                    throw nvae;
                }

                }
                break;
            case 25:
            case 27:
            case 28:
                {
                alt5=4;
                }
                break;
            case 20:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("264:2: (this_EdgeStatement_0= ruleEdgeStatement | this_NodeStatement_1= ruleNodeStatement | this_Attribute_2= ruleAttribute | this_AttributeStatement_3= ruleAttributeStatement | this_Subgraph_4= ruleSubgraph )", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:265:5: this_EdgeStatement_0= ruleEdgeStatement
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getStatementAccess().getEdgeStatementParserRuleCall_0_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleEdgeStatement_in_ruleStatement413);
                    this_EdgeStatement_0=ruleEdgeStatement();
                    _fsp--;

                     
                            current = this_EdgeStatement_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:275:5: this_NodeStatement_1= ruleNodeStatement
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getStatementAccess().getNodeStatementParserRuleCall_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleNodeStatement_in_ruleStatement440);
                    this_NodeStatement_1=ruleNodeStatement();
                    _fsp--;

                     
                            current = this_NodeStatement_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:285:5: this_Attribute_2= ruleAttribute
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getStatementAccess().getAttributeParserRuleCall_0_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAttribute_in_ruleStatement467);
                    this_Attribute_2=ruleAttribute();
                    _fsp--;

                     
                            current = this_Attribute_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:295:5: this_AttributeStatement_3= ruleAttributeStatement
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getStatementAccess().getAttributeStatementParserRuleCall_0_3(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleAttributeStatement_in_ruleStatement494);
                    this_AttributeStatement_3=ruleAttributeStatement();
                    _fsp--;

                     
                            current = this_AttributeStatement_3; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:305:5: this_Subgraph_4= ruleSubgraph
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getStatementAccess().getSubgraphParserRuleCall_0_4(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleSubgraph_in_ruleStatement521);
                    this_Subgraph_4=ruleSubgraph();
                    _fsp--;

                     
                            current = this_Subgraph_4; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:313:2: ( ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==16) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:313:4: ';'
                    {
                    match(input,16,FOLLOW_16_in_ruleStatement532); 

                            createLeafNode(grammarAccess.getStatementAccess().getSemicolonKeyword_1(), null); 
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:325:1: entryRuleEdgeStatement returns [EObject current=null] : iv_ruleEdgeStatement= ruleEdgeStatement EOF ;
    public final EObject entryRuleEdgeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:326:2: (iv_ruleEdgeStatement= ruleEdgeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:327:2: iv_ruleEdgeStatement= ruleEdgeStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEdgeStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleEdgeStatement_in_entryRuleEdgeStatement570);
            iv_ruleEdgeStatement=ruleEdgeStatement();
            _fsp--;

             current =iv_ruleEdgeStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEdgeStatement580); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:334:1: ruleEdgeStatement returns [EObject current=null] : ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ ( '[' ( (lv_attributes_3_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* ']' )? ) ;
    public final EObject ruleEdgeStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_sourceNode_0_0 = null;

        EObject lv_edgeTargets_1_0 = null;

        EObject lv_attributes_3_0 = null;

        EObject lv_attributes_5_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:339:6: ( ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ ( '[' ( (lv_attributes_3_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* ']' )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:340:1: ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ ( '[' ( (lv_attributes_3_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* ']' )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:340:1: ( ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ ( '[' ( (lv_attributes_3_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* ']' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:340:2: ( (lv_sourceNode_0_0= ruleNode ) ) ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+ ( '[' ( (lv_attributes_3_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* ']' )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:340:2: ( (lv_sourceNode_0_0= ruleNode ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:341:1: (lv_sourceNode_0_0= ruleNode )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:341:1: (lv_sourceNode_0_0= ruleNode )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:342:3: lv_sourceNode_0_0= ruleNode
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getSourceNodeNodeParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleNode_in_ruleEdgeStatement626);
            lv_sourceNode_0_0=ruleNode();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"sourceNode",
            	        		lv_sourceNode_0_0, 
            	        		"Node", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:364:2: ( (lv_edgeTargets_1_0= ruleEdgeTarget ) )+
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
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:365:1: (lv_edgeTargets_1_0= ruleEdgeTarget )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:365:1: (lv_edgeTargets_1_0= ruleEdgeTarget )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:366:3: lv_edgeTargets_1_0= ruleEdgeTarget
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getEdgeTargetsEdgeTargetParserRuleCall_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleEdgeTarget_in_ruleEdgeStatement647);
            	    lv_edgeTargets_1_0=ruleEdgeTarget();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"edgeTargets",
            	    	        		lv_edgeTargets_1_0, 
            	    	        		"EdgeTarget", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

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

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:388:3: ( '[' ( (lv_attributes_3_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* ']' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==17) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:388:5: '[' ( (lv_attributes_3_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )* ']'
                    {
                    match(input,17,FOLLOW_17_in_ruleEdgeStatement659); 

                            createLeafNode(grammarAccess.getEdgeStatementAccess().getLeftSquareBracketKeyword_2_0(), null); 
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:392:1: ( (lv_attributes_3_0= ruleListAttribute ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:393:1: (lv_attributes_3_0= ruleListAttribute )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:393:1: (lv_attributes_3_0= ruleListAttribute )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:394:3: lv_attributes_3_0= ruleListAttribute
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getAttributesListAttributeParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleListAttribute_in_ruleEdgeStatement680);
                    lv_attributes_3_0=ruleListAttribute();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"attributes",
                    	        		lv_attributes_3_0, 
                    	        		"ListAttribute", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:416:2: ( ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>=RULE_ID && LA9_0<=RULE_STRING)||LA9_0==18) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:416:3: ( ',' )? ( (lv_attributes_5_0= ruleListAttribute ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:416:3: ( ',' )?
                    	    int alt8=2;
                    	    int LA8_0 = input.LA(1);

                    	    if ( (LA8_0==18) ) {
                    	        alt8=1;
                    	    }
                    	    switch (alt8) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:416:5: ','
                    	            {
                    	            match(input,18,FOLLOW_18_in_ruleEdgeStatement692); 

                    	                    createLeafNode(grammarAccess.getEdgeStatementAccess().getCommaKeyword_2_2_0(), null); 
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:420:3: ( (lv_attributes_5_0= ruleListAttribute ) )
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:421:1: (lv_attributes_5_0= ruleListAttribute )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:421:1: (lv_attributes_5_0= ruleListAttribute )
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:422:3: lv_attributes_5_0= ruleListAttribute
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEdgeStatementAccess().getAttributesListAttributeParserRuleCall_2_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleListAttribute_in_ruleEdgeStatement715);
                    	    lv_attributes_5_0=ruleListAttribute();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEdgeStatementRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"attributes",
                    	    	        		lv_attributes_5_0, 
                    	    	        		"ListAttribute", 
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
                    	    break loop9;
                        }
                    } while (true);

                    match(input,19,FOLLOW_19_in_ruleEdgeStatement727); 

                            createLeafNode(grammarAccess.getEdgeStatementAccess().getRightSquareBracketKeyword_2_3(), null); 
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:456:1: entryRuleEdgeTarget returns [EObject current=null] : iv_ruleEdgeTarget= ruleEdgeTarget EOF ;
    public final EObject entryRuleEdgeTarget() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdgeTarget = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:457:2: (iv_ruleEdgeTarget= ruleEdgeTarget EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:458:2: iv_ruleEdgeTarget= ruleEdgeTarget EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEdgeTargetRule(), currentNode); 
            pushFollow(FOLLOW_ruleEdgeTarget_in_entryRuleEdgeTarget765);
            iv_ruleEdgeTarget=ruleEdgeTarget();
            _fsp--;

             current =iv_ruleEdgeTarget; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEdgeTarget775); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:465:1: ruleEdgeTarget returns [EObject current=null] : ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) ) ;
    public final EObject ruleEdgeTarget() throws RecognitionException {
        EObject current = null;

        Enumerator lv_operator_0_0 = null;

        EObject lv_targetSubgraph_1_0 = null;

        EObject lv_targetnode_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:470:6: ( ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:471:1: ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:471:1: ( ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:471:2: ( (lv_operator_0_0= ruleEdgeOperator ) ) ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:471:2: ( (lv_operator_0_0= ruleEdgeOperator ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:472:1: (lv_operator_0_0= ruleEdgeOperator )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:472:1: (lv_operator_0_0= ruleEdgeOperator )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:473:3: lv_operator_0_0= ruleEdgeOperator
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEdgeTargetAccess().getOperatorEdgeOperatorEnumRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEdgeOperator_in_ruleEdgeTarget821);
            lv_operator_0_0=ruleEdgeOperator();
            _fsp--;


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

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:495:2: ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            else if ( ((LA11_0>=RULE_ID && LA11_0<=RULE_STRING)) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("495:2: ( ( (lv_targetSubgraph_1_0= ruleSubgraph ) ) | ( (lv_targetnode_2_0= ruleNode ) ) )", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:495:3: ( (lv_targetSubgraph_1_0= ruleSubgraph ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:495:3: ( (lv_targetSubgraph_1_0= ruleSubgraph ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:496:1: (lv_targetSubgraph_1_0= ruleSubgraph )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:496:1: (lv_targetSubgraph_1_0= ruleSubgraph )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:497:3: lv_targetSubgraph_1_0= ruleSubgraph
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEdgeTargetAccess().getTargetSubgraphSubgraphParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleSubgraph_in_ruleEdgeTarget843);
                    lv_targetSubgraph_1_0=ruleSubgraph();
                    _fsp--;


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
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:520:6: ( (lv_targetnode_2_0= ruleNode ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:520:6: ( (lv_targetnode_2_0= ruleNode ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:521:1: (lv_targetnode_2_0= ruleNode )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:521:1: (lv_targetnode_2_0= ruleNode )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:522:3: lv_targetnode_2_0= ruleNode
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEdgeTargetAccess().getTargetnodeNodeParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleNode_in_ruleEdgeTarget870);
                    lv_targetnode_2_0=ruleNode();
                    _fsp--;


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
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:552:1: entryRuleNodeStatement returns [EObject current=null] : iv_ruleNodeStatement= ruleNodeStatement EOF ;
    public final EObject entryRuleNodeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:553:2: (iv_ruleNodeStatement= ruleNodeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:554:2: iv_ruleNodeStatement= ruleNodeStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNodeStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleNodeStatement_in_entryRuleNodeStatement907);
            iv_ruleNodeStatement=ruleNodeStatement();
            _fsp--;

             current =iv_ruleNodeStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNodeStatement917); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:561:1: ruleNodeStatement returns [EObject current=null] : ( ( (lv_node_0_0= ruleNode ) ) ( '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )? ) ;
    public final EObject ruleNodeStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_node_0_0 = null;

        EObject lv_attributes_2_0 = null;

        EObject lv_attributes_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:566:6: ( ( ( (lv_node_0_0= ruleNode ) ) ( '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:567:1: ( ( (lv_node_0_0= ruleNode ) ) ( '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:567:1: ( ( (lv_node_0_0= ruleNode ) ) ( '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:567:2: ( (lv_node_0_0= ruleNode ) ) ( '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:567:2: ( (lv_node_0_0= ruleNode ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:568:1: (lv_node_0_0= ruleNode )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:568:1: (lv_node_0_0= ruleNode )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:569:3: lv_node_0_0= ruleNode
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeStatementAccess().getNodeNodeParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleNode_in_ruleNodeStatement963);
            lv_node_0_0=ruleNode();
            _fsp--;


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

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:591:2: ( '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==17) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:591:4: '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']'
                    {
                    match(input,17,FOLLOW_17_in_ruleNodeStatement974); 

                            createLeafNode(grammarAccess.getNodeStatementAccess().getLeftSquareBracketKeyword_1_0(), null); 
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:595:1: ( (lv_attributes_2_0= ruleListAttribute ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:596:1: (lv_attributes_2_0= ruleListAttribute )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:596:1: (lv_attributes_2_0= ruleListAttribute )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:597:3: lv_attributes_2_0= ruleListAttribute
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeStatementAccess().getAttributesListAttributeParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleListAttribute_in_ruleNodeStatement995);
                    lv_attributes_2_0=ruleListAttribute();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNodeStatementRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"attributes",
                    	        		lv_attributes_2_0, 
                    	        		"ListAttribute", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:619:2: ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>=RULE_ID && LA13_0<=RULE_STRING)||LA13_0==18) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:619:3: ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:619:3: ( ',' )?
                    	    int alt12=2;
                    	    int LA12_0 = input.LA(1);

                    	    if ( (LA12_0==18) ) {
                    	        alt12=1;
                    	    }
                    	    switch (alt12) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:619:5: ','
                    	            {
                    	            match(input,18,FOLLOW_18_in_ruleNodeStatement1007); 

                    	                    createLeafNode(grammarAccess.getNodeStatementAccess().getCommaKeyword_1_2_0(), null); 
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:623:3: ( (lv_attributes_4_0= ruleListAttribute ) )
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:624:1: (lv_attributes_4_0= ruleListAttribute )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:624:1: (lv_attributes_4_0= ruleListAttribute )
                    	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:625:3: lv_attributes_4_0= ruleListAttribute
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getNodeStatementAccess().getAttributesListAttributeParserRuleCall_1_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleListAttribute_in_ruleNodeStatement1030);
                    	    lv_attributes_4_0=ruleListAttribute();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getNodeStatementRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"attributes",
                    	    	        		lv_attributes_4_0, 
                    	    	        		"ListAttribute", 
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
                    	    break loop13;
                        }
                    } while (true);

                    match(input,19,FOLLOW_19_in_ruleNodeStatement1042); 

                            createLeafNode(grammarAccess.getNodeStatementAccess().getRightSquareBracketKeyword_1_3(), null); 
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:659:1: entryRuleAttributeStatement returns [EObject current=null] : iv_ruleAttributeStatement= ruleAttributeStatement EOF ;
    public final EObject entryRuleAttributeStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttributeStatement = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:660:2: (iv_ruleAttributeStatement= ruleAttributeStatement EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:661:2: iv_ruleAttributeStatement= ruleAttributeStatement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAttributeStatementRule(), currentNode); 
            pushFollow(FOLLOW_ruleAttributeStatement_in_entryRuleAttributeStatement1080);
            iv_ruleAttributeStatement=ruleAttributeStatement();
            _fsp--;

             current =iv_ruleAttributeStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttributeStatement1090); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:668:1: ruleAttributeStatement returns [EObject current=null] : ( ( (lv_type_0_0= ruleAttributeType ) ) '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' ) ;
    public final EObject ruleAttributeStatement() throws RecognitionException {
        EObject current = null;

        Enumerator lv_type_0_0 = null;

        EObject lv_attributes_2_0 = null;

        EObject lv_attributes_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:673:6: ( ( ( (lv_type_0_0= ruleAttributeType ) ) '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:674:1: ( ( (lv_type_0_0= ruleAttributeType ) ) '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:674:1: ( ( (lv_type_0_0= ruleAttributeType ) ) '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:674:2: ( (lv_type_0_0= ruleAttributeType ) ) '[' ( (lv_attributes_2_0= ruleListAttribute ) ) ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )* ']'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:674:2: ( (lv_type_0_0= ruleAttributeType ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:675:1: (lv_type_0_0= ruleAttributeType )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:675:1: (lv_type_0_0= ruleAttributeType )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:676:3: lv_type_0_0= ruleAttributeType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAttributeStatementAccess().getTypeAttributeTypeEnumRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleAttributeType_in_ruleAttributeStatement1136);
            lv_type_0_0=ruleAttributeType();
            _fsp--;


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

            match(input,17,FOLLOW_17_in_ruleAttributeStatement1146); 

                    createLeafNode(grammarAccess.getAttributeStatementAccess().getLeftSquareBracketKeyword_1(), null); 
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:702:1: ( (lv_attributes_2_0= ruleListAttribute ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:703:1: (lv_attributes_2_0= ruleListAttribute )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:703:1: (lv_attributes_2_0= ruleListAttribute )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:704:3: lv_attributes_2_0= ruleListAttribute
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAttributeStatementAccess().getAttributesListAttributeParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleListAttribute_in_ruleAttributeStatement1167);
            lv_attributes_2_0=ruleListAttribute();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAttributeStatementRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		add(
            	       			current, 
            	       			"attributes",
            	        		lv_attributes_2_0, 
            	        		"ListAttribute", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:726:2: ( ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=RULE_ID && LA16_0<=RULE_STRING)||LA16_0==18) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:726:3: ( ',' )? ( (lv_attributes_4_0= ruleListAttribute ) )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:726:3: ( ',' )?
            	    int alt15=2;
            	    int LA15_0 = input.LA(1);

            	    if ( (LA15_0==18) ) {
            	        alt15=1;
            	    }
            	    switch (alt15) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:726:5: ','
            	            {
            	            match(input,18,FOLLOW_18_in_ruleAttributeStatement1179); 

            	                    createLeafNode(grammarAccess.getAttributeStatementAccess().getCommaKeyword_3_0(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:730:3: ( (lv_attributes_4_0= ruleListAttribute ) )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:731:1: (lv_attributes_4_0= ruleListAttribute )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:731:1: (lv_attributes_4_0= ruleListAttribute )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:732:3: lv_attributes_4_0= ruleListAttribute
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAttributeStatementAccess().getAttributesListAttributeParserRuleCall_3_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleListAttribute_in_ruleAttributeStatement1202);
            	    lv_attributes_4_0=ruleListAttribute();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAttributeStatementRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"attributes",
            	    	        		lv_attributes_4_0, 
            	    	        		"ListAttribute", 
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
            	    break loop16;
                }
            } while (true);

            match(input,19,FOLLOW_19_in_ruleAttributeStatement1214); 

                    createLeafNode(grammarAccess.getAttributeStatementAccess().getRightSquareBracketKeyword_4(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:766:1: entryRuleSubgraph returns [EObject current=null] : iv_ruleSubgraph= ruleSubgraph EOF ;
    public final EObject entryRuleSubgraph() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubgraph = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:767:2: (iv_ruleSubgraph= ruleSubgraph EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:768:2: iv_ruleSubgraph= ruleSubgraph EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSubgraphRule(), currentNode); 
            pushFollow(FOLLOW_ruleSubgraph_in_entryRuleSubgraph1250);
            iv_ruleSubgraph=ruleSubgraph();
            _fsp--;

             current =iv_ruleSubgraph; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSubgraph1260); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:775:1: ruleSubgraph returns [EObject current=null] : ( () 'subgraph' ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' ) ;
    public final EObject ruleSubgraph() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_0=null;
        EObject lv_statements_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:780:6: ( ( () 'subgraph' ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:781:1: ( () 'subgraph' ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:781:1: ( () 'subgraph' ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}' )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:781:2: () 'subgraph' ( (lv_name_2_0= RULE_ID ) )? '{' ( (lv_statements_4_0= ruleStatement ) )* '}'
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:781:2: ()
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:782:5: 
            {
             
                    temp=factory.create(grammarAccess.getSubgraphAccess().getSubgraphAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getSubgraphAccess().getSubgraphAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,20,FOLLOW_20_in_ruleSubgraph1304); 

                    createLeafNode(grammarAccess.getSubgraphAccess().getSubgraphKeyword_1(), null); 
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:796:1: ( (lv_name_2_0= RULE_ID ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ID) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:797:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:797:1: (lv_name_2_0= RULE_ID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:798:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSubgraph1321); 

                    			createLeafNode(grammarAccess.getSubgraphAccess().getNameIDTerminalRuleCall_2_0(), "name"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSubgraphRule().getType().getClassifier());
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
                    break;

            }

            match(input,14,FOLLOW_14_in_ruleSubgraph1337); 

                    createLeafNode(grammarAccess.getSubgraphAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:824:1: ( (lv_statements_4_0= ruleStatement ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=RULE_ID && LA18_0<=RULE_STRING)||LA18_0==20||LA18_0==25||(LA18_0>=27 && LA18_0<=28)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:825:1: (lv_statements_4_0= ruleStatement )
            	    {
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:825:1: (lv_statements_4_0= ruleStatement )
            	    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:826:3: lv_statements_4_0= ruleStatement
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getSubgraphAccess().getStatementsStatementParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleStatement_in_ruleSubgraph1358);
            	    lv_statements_4_0=ruleStatement();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getSubgraphRule().getType().getClassifier());
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
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            match(input,15,FOLLOW_15_in_ruleSubgraph1369); 

                    createLeafNode(grammarAccess.getSubgraphAccess().getRightCurlyBracketKeyword_5(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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


    // $ANTLR start entryRuleAttribute
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:860:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:861:2: (iv_ruleAttribute= ruleAttribute EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:862:2: iv_ruleAttribute= ruleAttribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAttributeRule(), currentNode); 
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute1405);
            iv_ruleAttribute=ruleAttribute();
            _fsp--;

             current =iv_ruleAttribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute1415); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:869:1: ruleAttribute returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) '=' ( (lv_value_2_0= ruleDotID ) ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:874:6: ( ( ( (lv_name_0_0= ruleDotID ) ) '=' ( (lv_value_2_0= ruleDotID ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:875:1: ( ( (lv_name_0_0= ruleDotID ) ) '=' ( (lv_value_2_0= ruleDotID ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:875:1: ( ( (lv_name_0_0= ruleDotID ) ) '=' ( (lv_value_2_0= ruleDotID ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:875:2: ( (lv_name_0_0= ruleDotID ) ) '=' ( (lv_value_2_0= ruleDotID ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:875:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:876:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:876:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:877:3: lv_name_0_0= ruleDotID
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAttributeAccess().getNameDotIDParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleAttribute1461);
            lv_name_0_0=ruleDotID();
            _fsp--;


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

            match(input,21,FOLLOW_21_in_ruleAttribute1471); 

                    createLeafNode(grammarAccess.getAttributeAccess().getEqualsSignKeyword_1(), null); 
                
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:903:1: ( (lv_value_2_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:904:1: (lv_value_2_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:904:1: (lv_value_2_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:905:3: lv_value_2_0= ruleDotID
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAttributeAccess().getValueDotIDParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleAttribute1492);
            lv_value_2_0=ruleDotID();
            _fsp--;


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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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


    // $ANTLR start entryRuleListAttribute
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:935:1: entryRuleListAttribute returns [EObject current=null] : iv_ruleListAttribute= ruleListAttribute EOF ;
    public final EObject entryRuleListAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListAttribute = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:936:2: (iv_ruleListAttribute= ruleListAttribute EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:937:2: iv_ruleListAttribute= ruleListAttribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getListAttributeRule(), currentNode); 
            pushFollow(FOLLOW_ruleListAttribute_in_entryRuleListAttribute1528);
            iv_ruleListAttribute=ruleListAttribute();
            _fsp--;

             current =iv_ruleListAttribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleListAttribute1538); 

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
    // $ANTLR end entryRuleListAttribute


    // $ANTLR start ruleListAttribute
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:944:1: ruleListAttribute returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? ) ;
    public final EObject ruleListAttribute() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:949:6: ( ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:950:1: ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:950:1: ( ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:950:2: ( (lv_name_0_0= ruleDotID ) ) ( '=' ( (lv_value_2_0= ruleDotID ) ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:950:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:951:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:951:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:952:3: lv_name_0_0= ruleDotID
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getListAttributeAccess().getNameDotIDParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleListAttribute1584);
            lv_name_0_0=ruleDotID();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getListAttributeRule().getType().getClassifier());
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

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:974:2: ( '=' ( (lv_value_2_0= ruleDotID ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==21) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:974:4: '=' ( (lv_value_2_0= ruleDotID ) )
                    {
                    match(input,21,FOLLOW_21_in_ruleListAttribute1595); 

                            createLeafNode(grammarAccess.getListAttributeAccess().getEqualsSignKeyword_1_0(), null); 
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:978:1: ( (lv_value_2_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:979:1: (lv_value_2_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:979:1: (lv_value_2_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:980:3: lv_value_2_0= ruleDotID
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getListAttributeAccess().getValueDotIDParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleDotID_in_ruleListAttribute1616);
                    lv_value_2_0=ruleDotID();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getListAttributeRule().getType().getClassifier());
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
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleListAttribute


    // $ANTLR start entryRuleNode
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1010:1: entryRuleNode returns [EObject current=null] : iv_ruleNode= ruleNode EOF ;
    public final EObject entryRuleNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNode = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1011:2: (iv_ruleNode= ruleNode EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1012:2: iv_ruleNode= ruleNode EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNodeRule(), currentNode); 
            pushFollow(FOLLOW_ruleNode_in_entryRuleNode1654);
            iv_ruleNode=ruleNode();
            _fsp--;

             current =iv_ruleNode; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNode1664); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1019:1: ruleNode returns [EObject current=null] : ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? ) ;
    public final EObject ruleNode() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_port_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1024:6: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1025:1: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1025:1: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )? )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1025:2: ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_port_2_0= rulePort ) ) )?
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1025:2: ( (lv_name_0_0= ruleDotID ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1026:1: (lv_name_0_0= ruleDotID )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1026:1: (lv_name_0_0= ruleDotID )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1027:3: lv_name_0_0= ruleDotID
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeAccess().getNameDotIDParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleDotID_in_ruleNode1710);
            lv_name_0_0=ruleDotID();
            _fsp--;


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

            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1049:2: ( ':' ( (lv_port_2_0= rulePort ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==22) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1049:4: ':' ( (lv_port_2_0= rulePort ) )
                    {
                    match(input,22,FOLLOW_22_in_ruleNode1721); 

                            createLeafNode(grammarAccess.getNodeAccess().getColonKeyword_1_0(), null); 
                        
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1053:1: ( (lv_port_2_0= rulePort ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1054:1: (lv_port_2_0= rulePort )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1054:1: (lv_port_2_0= rulePort )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1055:3: lv_port_2_0= rulePort
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeAccess().getPortPortParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulePort_in_ruleNode1742);
                    lv_port_2_0=rulePort();
                    _fsp--;


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
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1085:1: entryRulePort returns [EObject current=null] : iv_rulePort= rulePort EOF ;
    public final EObject entryRulePort() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePort = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1086:2: (iv_rulePort= rulePort EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1087:2: iv_rulePort= rulePort EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPortRule(), currentNode); 
            pushFollow(FOLLOW_rulePort_in_entryRulePort1780);
            iv_rulePort=rulePort();
            _fsp--;

             current =iv_rulePort; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePort1790); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1094:1: rulePort returns [EObject current=null] : ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) ) ;
    public final EObject rulePort() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        Enumerator lv_compass_pt_2_0 = null;

        Enumerator lv_compass_pt_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1099:6: ( ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1100:1: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1100:1: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=RULE_ID && LA22_0<=RULE_STRING)) ) {
                alt22=1;
            }
            else if ( ((LA22_0>=29 && LA22_0<=38)) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1100:1: ( ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? ) | ( (lv_compass_pt_3_0= ruleCompassPoint ) ) )", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1100:2: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1100:2: ( ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )? )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1100:3: ( (lv_name_0_0= ruleDotID ) ) ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )?
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1100:3: ( (lv_name_0_0= ruleDotID ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1101:1: (lv_name_0_0= ruleDotID )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1101:1: (lv_name_0_0= ruleDotID )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1102:3: lv_name_0_0= ruleDotID
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getNameDotIDParserRuleCall_0_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleDotID_in_rulePort1837);
                    lv_name_0_0=ruleDotID();
                    _fsp--;


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

                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1124:2: ( ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) ) )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==22) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1124:4: ':' ( (lv_compass_pt_2_0= ruleCompassPoint ) )
                            {
                            match(input,22,FOLLOW_22_in_rulePort1848); 

                                    createLeafNode(grammarAccess.getPortAccess().getColonKeyword_0_1_0(), null); 
                                
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1128:1: ( (lv_compass_pt_2_0= ruleCompassPoint ) )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1129:1: (lv_compass_pt_2_0= ruleCompassPoint )
                            {
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1129:1: (lv_compass_pt_2_0= ruleCompassPoint )
                            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1130:3: lv_compass_pt_2_0= ruleCompassPoint
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getCompass_ptCompassPointEnumRuleCall_0_1_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleCompassPoint_in_rulePort1869);
                            lv_compass_pt_2_0=ruleCompassPoint();
                            _fsp--;


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
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1153:6: ( (lv_compass_pt_3_0= ruleCompassPoint ) )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1153:6: ( (lv_compass_pt_3_0= ruleCompassPoint ) )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1154:1: (lv_compass_pt_3_0= ruleCompassPoint )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1154:1: (lv_compass_pt_3_0= ruleCompassPoint )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1155:3: lv_compass_pt_3_0= ruleCompassPoint
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getCompass_ptCompassPointEnumRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleCompassPoint_in_rulePort1899);
                    lv_compass_pt_3_0=ruleCompassPoint();
                    _fsp--;


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
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1185:1: entryRuleDotID returns [String current=null] : iv_ruleDotID= ruleDotID EOF ;
    public final String entryRuleDotID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDotID = null;


        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1186:2: (iv_ruleDotID= ruleDotID EOF )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1187:2: iv_ruleDotID= ruleDotID EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDotIDRule(), currentNode); 
            pushFollow(FOLLOW_ruleDotID_in_entryRuleDotID1936);
            iv_ruleDotID=ruleDotID();
            _fsp--;

             current =iv_ruleDotID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDotID1947); 

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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1194:1: ruleDotID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleDotID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_INT_1=null;
        Token this_FLOAT_2=null;
        Token this_STRING_3=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1199:6: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1200:1: (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1200:1: (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING )
            int alt23=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt23=1;
                }
                break;
            case RULE_INT:
                {
                alt23=2;
                }
                break;
            case RULE_FLOAT:
                {
                alt23=3;
                }
                break;
            case RULE_STRING:
                {
                alt23=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1200:1: (this_ID_0= RULE_ID | this_INT_1= RULE_INT | this_FLOAT_2= RULE_FLOAT | this_STRING_3= RULE_STRING )", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1200:6: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDotID1987); 

                    		current.merge(this_ID_0);
                        
                     
                        createLeafNode(grammarAccess.getDotIDAccess().getIDTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1208:10: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleDotID2013); 

                    		current.merge(this_INT_1);
                        
                     
                        createLeafNode(grammarAccess.getDotIDAccess().getINTTerminalRuleCall_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1216:10: this_FLOAT_2= RULE_FLOAT
                    {
                    this_FLOAT_2=(Token)input.LT(1);
                    match(input,RULE_FLOAT,FOLLOW_RULE_FLOAT_in_ruleDotID2039); 

                    		current.merge(this_FLOAT_2);
                        
                     
                        createLeafNode(grammarAccess.getDotIDAccess().getFLOATTerminalRuleCall_2(), null); 
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1224:10: this_STRING_3= RULE_STRING
                    {
                    this_STRING_3=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDotID2065); 

                    		current.merge(this_STRING_3);
                        
                     
                        createLeafNode(grammarAccess.getDotIDAccess().getSTRINGTerminalRuleCall_3(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1239:1: ruleEdgeOperator returns [Enumerator current=null] : ( ( '->' ) | ( '--' ) ) ;
    public final Enumerator ruleEdgeOperator() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1243:6: ( ( ( '->' ) | ( '--' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1244:1: ( ( '->' ) | ( '--' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1244:1: ( ( '->' ) | ( '--' ) )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==23) ) {
                alt24=1;
            }
            else if ( (LA24_0==24) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1244:1: ( ( '->' ) | ( '--' ) )", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1244:2: ( '->' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1244:2: ( '->' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1244:4: '->'
                    {
                    match(input,23,FOLLOW_23_in_ruleEdgeOperator2122); 

                            current = grammarAccess.getEdgeOperatorAccess().getDirectedEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getEdgeOperatorAccess().getDirectedEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1250:6: ( '--' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1250:6: ( '--' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1250:8: '--'
                    {
                    match(input,24,FOLLOW_24_in_ruleEdgeOperator2137); 

                            current = grammarAccess.getEdgeOperatorAccess().getUndirectedEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getEdgeOperatorAccess().getUndirectedEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1260:1: ruleGraphType returns [Enumerator current=null] : ( ( 'graph' ) | ( 'digraph' ) ) ;
    public final Enumerator ruleGraphType() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1264:6: ( ( ( 'graph' ) | ( 'digraph' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1265:1: ( ( 'graph' ) | ( 'digraph' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1265:1: ( ( 'graph' ) | ( 'digraph' ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==25) ) {
                alt25=1;
            }
            else if ( (LA25_0==26) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1265:1: ( ( 'graph' ) | ( 'digraph' ) )", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1265:2: ( 'graph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1265:2: ( 'graph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1265:4: 'graph'
                    {
                    match(input,25,FOLLOW_25_in_ruleGraphType2180); 

                            current = grammarAccess.getGraphTypeAccess().getGraphEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getGraphTypeAccess().getGraphEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1271:6: ( 'digraph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1271:6: ( 'digraph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1271:8: 'digraph'
                    {
                    match(input,26,FOLLOW_26_in_ruleGraphType2195); 

                            current = grammarAccess.getGraphTypeAccess().getDigraphEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getGraphTypeAccess().getDigraphEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1281:1: ruleAttributeType returns [Enumerator current=null] : ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) ) ;
    public final Enumerator ruleAttributeType() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1285:6: ( ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1286:1: ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1286:1: ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) )
            int alt26=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt26=1;
                }
                break;
            case 27:
                {
                alt26=2;
                }
                break;
            case 28:
                {
                alt26=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1286:1: ( ( 'graph' ) | ( 'node' ) | ( 'edge' ) )", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1286:2: ( 'graph' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1286:2: ( 'graph' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1286:4: 'graph'
                    {
                    match(input,25,FOLLOW_25_in_ruleAttributeType2238); 

                            current = grammarAccess.getAttributeTypeAccess().getGraphEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getAttributeTypeAccess().getGraphEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1292:6: ( 'node' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1292:6: ( 'node' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1292:8: 'node'
                    {
                    match(input,27,FOLLOW_27_in_ruleAttributeType2253); 

                            current = grammarAccess.getAttributeTypeAccess().getNodeEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getAttributeTypeAccess().getNodeEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1298:6: ( 'edge' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1298:6: ( 'edge' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1298:8: 'edge'
                    {
                    match(input,28,FOLLOW_28_in_ruleAttributeType2268); 

                            current = grammarAccess.getAttributeTypeAccess().getEdgeEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getAttributeTypeAccess().getEdgeEnumLiteralDeclaration_2(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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
    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1308:1: ruleCompassPoint returns [Enumerator current=null] : ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) | ( 'c' ) | ( '_' ) ) ;
    public final Enumerator ruleCompassPoint() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1312:6: ( ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) | ( 'c' ) | ( '_' ) ) )
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1313:1: ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) | ( 'c' ) | ( '_' ) )
            {
            // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1313:1: ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) | ( 'c' ) | ( '_' ) )
            int alt27=10;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt27=1;
                }
                break;
            case 30:
                {
                alt27=2;
                }
                break;
            case 31:
                {
                alt27=3;
                }
                break;
            case 32:
                {
                alt27=4;
                }
                break;
            case 33:
                {
                alt27=5;
                }
                break;
            case 34:
                {
                alt27=6;
                }
                break;
            case 35:
                {
                alt27=7;
                }
                break;
            case 36:
                {
                alt27=8;
                }
                break;
            case 37:
                {
                alt27=9;
                }
                break;
            case 38:
                {
                alt27=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1313:1: ( ( 'n' ) | ( 'ne' ) | ( 'e' ) | ( 'se' ) | ( 's' ) | ( 'sw' ) | ( 'w' ) | ( 'nw' ) | ( 'c' ) | ( '_' ) )", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1313:2: ( 'n' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1313:2: ( 'n' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1313:4: 'n'
                    {
                    match(input,29,FOLLOW_29_in_ruleCompassPoint2311); 

                            current = grammarAccess.getCompassPointAccess().getNorthEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getNorthEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1319:6: ( 'ne' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1319:6: ( 'ne' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1319:8: 'ne'
                    {
                    match(input,30,FOLLOW_30_in_ruleCompassPoint2326); 

                            current = grammarAccess.getCompassPointAccess().getNorthEastEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getNorthEastEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1325:6: ( 'e' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1325:6: ( 'e' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1325:8: 'e'
                    {
                    match(input,31,FOLLOW_31_in_ruleCompassPoint2341); 

                            current = grammarAccess.getCompassPointAccess().getEastEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getEastEnumLiteralDeclaration_2(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1331:6: ( 'se' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1331:6: ( 'se' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1331:8: 'se'
                    {
                    match(input,32,FOLLOW_32_in_ruleCompassPoint2356); 

                            current = grammarAccess.getCompassPointAccess().getSouthEastEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getSouthEastEnumLiteralDeclaration_3(), null); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1337:6: ( 's' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1337:6: ( 's' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1337:8: 's'
                    {
                    match(input,33,FOLLOW_33_in_ruleCompassPoint2371); 

                            current = grammarAccess.getCompassPointAccess().getSouthEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getSouthEnumLiteralDeclaration_4(), null); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1343:6: ( 'sw' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1343:6: ( 'sw' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1343:8: 'sw'
                    {
                    match(input,34,FOLLOW_34_in_ruleCompassPoint2386); 

                            current = grammarAccess.getCompassPointAccess().getSouthWestEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getSouthWestEnumLiteralDeclaration_5(), null); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1349:6: ( 'w' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1349:6: ( 'w' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1349:8: 'w'
                    {
                    match(input,35,FOLLOW_35_in_ruleCompassPoint2401); 

                            current = grammarAccess.getCompassPointAccess().getWestEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getWestEnumLiteralDeclaration_6(), null); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1355:6: ( 'nw' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1355:6: ( 'nw' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1355:8: 'nw'
                    {
                    match(input,36,FOLLOW_36_in_ruleCompassPoint2416); 

                            current = grammarAccess.getCompassPointAccess().getNorthWestEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getNorthWestEnumLiteralDeclaration_7(), null); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1361:6: ( 'c' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1361:6: ( 'c' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1361:8: 'c'
                    {
                    match(input,37,FOLLOW_37_in_ruleCompassPoint2431); 

                            current = grammarAccess.getCompassPointAccess().getCenterEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getCenterEnumLiteralDeclaration_8(), null); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1367:6: ( '_' )
                    {
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1367:6: ( '_' )
                    // ../de.cau.cs.kieler.kiml.graphviz.dot/src-gen/de/cau/cs/kieler/kiml/graphviz/dot/parser/antlr/internal/InternalGraphvizDot.g:1367:8: '_'
                    {
                    match(input,38,FOLLOW_38_in_ruleCompassPoint2446); 

                            current = grammarAccess.getCompassPointAccess().getBlankEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getCompassPointAccess().getBlankEnumLiteralDeclaration_9(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
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


 

    public static final BitSet FOLLOW_ruleGraphvizModel_in_entryRuleGraphvizModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGraphvizModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGraph_in_ruleGraphvizModel130 = new BitSet(new long[]{0x0000000006002002L});
    public static final BitSet FOLLOW_ruleGraph_in_entryRuleGraph166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGraph176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleGraph219 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_ruleGraphType_in_ruleGraph254 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGraph271 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleGraph287 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleGraph308 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_15_in_ruleGraph319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStatement_in_entryRuleStatement355 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStatement365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeStatement_in_ruleStatement413 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleNodeStatement_in_ruleStatement440 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleStatement467 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleAttributeStatement_in_ruleStatement494 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_ruleStatement521 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleStatement532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeStatement_in_entryRuleEdgeStatement570 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEdgeStatement580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleEdgeStatement626 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_ruleEdgeTarget_in_ruleEdgeStatement647 = new BitSet(new long[]{0x0000000001820002L});
    public static final BitSet FOLLOW_17_in_ruleEdgeStatement659 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleEdgeStatement680 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_18_in_ruleEdgeStatement692 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleEdgeStatement715 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_19_in_ruleEdgeStatement727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeTarget_in_entryRuleEdgeTarget765 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEdgeTarget775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEdgeOperator_in_ruleEdgeTarget821 = new BitSet(new long[]{0x00000000001000F0L});
    public static final BitSet FOLLOW_ruleSubgraph_in_ruleEdgeTarget843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleEdgeTarget870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNodeStatement_in_entryRuleNodeStatement907 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNodeStatement917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_ruleNodeStatement963 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_ruleNodeStatement974 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleNodeStatement995 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_18_in_ruleNodeStatement1007 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleNodeStatement1030 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_19_in_ruleNodeStatement1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeStatement_in_entryRuleAttributeStatement1080 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttributeStatement1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttributeType_in_ruleAttributeStatement1136 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleAttributeStatement1146 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleAttributeStatement1167 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_18_in_ruleAttributeStatement1179 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleListAttribute_in_ruleAttributeStatement1202 = new BitSet(new long[]{0x00000000000C00F0L});
    public static final BitSet FOLLOW_19_in_ruleAttributeStatement1214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSubgraph_in_entryRuleSubgraph1250 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSubgraph1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleSubgraph1304 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSubgraph1321 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleSubgraph1337 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleSubgraph1358 = new BitSet(new long[]{0x000000001A1080F0L});
    public static final BitSet FOLLOW_15_in_ruleSubgraph1369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute1405 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute1415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleAttribute1461 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleAttribute1471 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleAttribute1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListAttribute_in_entryRuleListAttribute1528 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListAttribute1538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleListAttribute1584 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleListAttribute1595 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleListAttribute1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNode_in_entryRuleNode1654 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNode1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_ruleNode1710 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_ruleNode1721 = new BitSet(new long[]{0x0000007FE00000F0L});
    public static final BitSet FOLLOW_rulePort_in_ruleNode1742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePort_in_entryRulePort1780 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePort1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_rulePort1837 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_rulePort1848 = new BitSet(new long[]{0x0000007FE0000000L});
    public static final BitSet FOLLOW_ruleCompassPoint_in_rulePort1869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompassPoint_in_rulePort1899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDotID_in_entryRuleDotID1936 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDotID1947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDotID1987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleDotID2013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_FLOAT_in_ruleDotID2039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDotID2065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleEdgeOperator2122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleEdgeOperator2137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleGraphType2180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleGraphType2195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleAttributeType2238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleAttributeType2253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleAttributeType2268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleCompassPoint2311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleCompassPoint2326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleCompassPoint2341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleCompassPoint2356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleCompassPoint2371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleCompassPoint2386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleCompassPoint2401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleCompassPoint2416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleCompassPoint2431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleCompassPoint2446 = new BitSet(new long[]{0x0000000000000002L});

}