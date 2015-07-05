package de.cau.cs.kieler.uml.sequence.text.parser.antlr.internal; 

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
import de.cau.cs.kieler.uml.sequence.text.services.SequenceGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSequenceParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'sequenceDiagram'", "'{'", "'}'", "'lifeline'", "'as'", "'to'", "'sourceStartBlock'", "'sourceEndBlock'", "'targetStartBlock'", "'targetEndBlock'", "'sourceNote'", "'targetNote'", "'lost'", "'found'", "'startBlock'", "'endBlock'", "'note'", "'destroy'", "'fragment'", "'label'", "'refinement'", "'lifelines'", "','", "'async'", "'create'", "'response'", "'sync'", "'Char'", "'Boolean'", "'Integer'", "'Float'"
    };
    public static final int T__42=42;
    public static final int RULE_ID=5;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_SL_COMMENT=9;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=8;
    public static final int RULE_INT_GREATER_ZERO=6;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int RULE_STRING=4;
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
    public static final int T__12=12;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_INT=7;
    public static final int RULE_WS=10;

    // delegates
    // delegators


        public InternalSequenceParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSequenceParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSequenceParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g"; }



     	private SequenceGrammarAccess grammarAccess;
     	
        public InternalSequenceParser(TokenStream input, SequenceGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "SequenceDiagram";	
       	}
       	
       	@Override
       	protected SequenceGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleSequenceDiagram"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:68:1: entryRuleSequenceDiagram returns [EObject current=null] : iv_ruleSequenceDiagram= ruleSequenceDiagram EOF ;
    public final EObject entryRuleSequenceDiagram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequenceDiagram = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:69:2: (iv_ruleSequenceDiagram= ruleSequenceDiagram EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:70:2: iv_ruleSequenceDiagram= ruleSequenceDiagram EOF
            {
             newCompositeNode(grammarAccess.getSequenceDiagramRule()); 
            pushFollow(FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram75);
            iv_ruleSequenceDiagram=ruleSequenceDiagram();

            state._fsp--;

             current =iv_ruleSequenceDiagram; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceDiagram85); 

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
    // $ANTLR end "entryRuleSequenceDiagram"


    // $ANTLR start "ruleSequenceDiagram"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:77:1: ruleSequenceDiagram returns [EObject current=null] : ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) ) ( (lv_locals_5_0= ruleLocalVariable ) )* otherlv_6= '}' )? ( (lv_lifelines_7_0= ruleLifeline ) )* ( (lv_interactions_8_0= ruleInteraction ) )* ) ;
    public final EObject ruleSequenceDiagram() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_diagramName_2_0=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        EObject lv_locals_4_0 = null;

        EObject lv_locals_5_0 = null;

        EObject lv_lifelines_7_0 = null;

        EObject lv_interactions_8_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:80:28: ( ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) ) ( (lv_locals_5_0= ruleLocalVariable ) )* otherlv_6= '}' )? ( (lv_lifelines_7_0= ruleLifeline ) )* ( (lv_interactions_8_0= ruleInteraction ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:1: ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) ) ( (lv_locals_5_0= ruleLocalVariable ) )* otherlv_6= '}' )? ( (lv_lifelines_7_0= ruleLifeline ) )* ( (lv_interactions_8_0= ruleInteraction ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:1: ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) ) ( (lv_locals_5_0= ruleLocalVariable ) )* otherlv_6= '}' )? ( (lv_lifelines_7_0= ruleLifeline ) )* ( (lv_interactions_8_0= ruleInteraction ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:2: () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) ) ( (lv_locals_5_0= ruleLocalVariable ) )* otherlv_6= '}' )? ( (lv_lifelines_7_0= ruleLifeline ) )* ( (lv_interactions_8_0= ruleInteraction ) )*
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:2: ()
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:82:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSequenceDiagramAccess().getSequenceDiagramAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleSequenceDiagram131); 

                	newLeafNode(otherlv_1, grammarAccess.getSequenceDiagramAccess().getSequenceDiagramKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:91:1: ( (lv_diagramName_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:92:1: (lv_diagramName_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:92:1: (lv_diagramName_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:93:3: lv_diagramName_2_0= RULE_STRING
            {
            lv_diagramName_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSequenceDiagram148); 

            			newLeafNode(lv_diagramName_2_0, grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSequenceDiagramRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"diagramName",
                    		lv_diagramName_2_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:109:2: (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) ) ( (lv_locals_5_0= ruleLocalVariable ) )* otherlv_6= '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:109:4: otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) ) ( (lv_locals_5_0= ruleLocalVariable ) )* otherlv_6= '}'
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleSequenceDiagram166); 

                        	newLeafNode(otherlv_3, grammarAccess.getSequenceDiagramAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:113:1: ( (lv_locals_4_0= ruleLocalVariable ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:114:1: (lv_locals_4_0= ruleLocalVariable )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:114:1: (lv_locals_4_0= ruleLocalVariable )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:115:3: lv_locals_4_0= ruleLocalVariable
                    {
                     
                    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleLocalVariable_in_ruleSequenceDiagram187);
                    lv_locals_4_0=ruleLocalVariable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
                    	        }
                           		add(
                           			current, 
                           			"locals",
                            		lv_locals_4_0, 
                            		"LocalVariable");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:131:2: ( (lv_locals_5_0= ruleLocalVariable ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>=39 && LA1_0<=42)) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:132:1: (lv_locals_5_0= ruleLocalVariable )
                    	    {
                    	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:132:1: (lv_locals_5_0= ruleLocalVariable )
                    	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:133:3: lv_locals_5_0= ruleLocalVariable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getLocalsLocalVariableParserRuleCall_3_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleLocalVariable_in_ruleSequenceDiagram208);
                    	    lv_locals_5_0=ruleLocalVariable();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"locals",
                    	            		lv_locals_5_0, 
                    	            		"LocalVariable");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,14,FOLLOW_14_in_ruleSequenceDiagram221); 

                        	newLeafNode(otherlv_6, grammarAccess.getSequenceDiagramAccess().getRightCurlyBracketKeyword_3_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:153:3: ( (lv_lifelines_7_0= ruleLifeline ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==15) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:154:1: (lv_lifelines_7_0= ruleLifeline )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:154:1: (lv_lifelines_7_0= ruleLifeline )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:155:3: lv_lifelines_7_0= ruleLifeline
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLifeline_in_ruleSequenceDiagram244);
            	    lv_lifelines_7_0=ruleLifeline();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"lifelines",
            	            		lv_lifelines_7_0, 
            	            		"Lifeline");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:171:3: ( (lv_interactions_8_0= ruleInteraction ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_ID||LA4_0==30||LA4_0==32) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:172:1: (lv_interactions_8_0= ruleInteraction )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:172:1: (lv_interactions_8_0= ruleInteraction )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:173:3: lv_interactions_8_0= ruleInteraction
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInteraction_in_ruleSequenceDiagram266);
            	    lv_interactions_8_0=ruleInteraction();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"interactions",
            	            		lv_interactions_8_0, 
            	            		"Interaction");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
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
    // $ANTLR end "ruleSequenceDiagram"


    // $ANTLR start "entryRuleLocalVariable"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:197:1: entryRuleLocalVariable returns [EObject current=null] : iv_ruleLocalVariable= ruleLocalVariable EOF ;
    public final EObject entryRuleLocalVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalVariable = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:198:2: (iv_ruleLocalVariable= ruleLocalVariable EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:199:2: iv_ruleLocalVariable= ruleLocalVariable EOF
            {
             newCompositeNode(grammarAccess.getLocalVariableRule()); 
            pushFollow(FOLLOW_ruleLocalVariable_in_entryRuleLocalVariable303);
            iv_ruleLocalVariable=ruleLocalVariable();

            state._fsp--;

             current =iv_ruleLocalVariable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalVariable313); 

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
    // $ANTLR end "entryRuleLocalVariable"


    // $ANTLR start "ruleLocalVariable"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:206:1: ruleLocalVariable returns [EObject current=null] : ( ( (lv_type_0_0= ruleDataType ) ) ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleLocalVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Enumerator lv_type_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:209:28: ( ( ( (lv_type_0_0= ruleDataType ) ) ( (lv_name_1_0= RULE_ID ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:210:1: ( ( (lv_type_0_0= ruleDataType ) ) ( (lv_name_1_0= RULE_ID ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:210:1: ( ( (lv_type_0_0= ruleDataType ) ) ( (lv_name_1_0= RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:210:2: ( (lv_type_0_0= ruleDataType ) ) ( (lv_name_1_0= RULE_ID ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:210:2: ( (lv_type_0_0= ruleDataType ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:211:1: (lv_type_0_0= ruleDataType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:211:1: (lv_type_0_0= ruleDataType )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:212:3: lv_type_0_0= ruleDataType
            {
             
            	        newCompositeNode(grammarAccess.getLocalVariableAccess().getTypeDataTypeEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleDataType_in_ruleLocalVariable359);
            lv_type_0_0=ruleDataType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLocalVariableRule());
            	        }
                   		set(
                   			current, 
                   			"type",
                    		lv_type_0_0, 
                    		"DataType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:228:2: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:229:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:229:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:230:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLocalVariable376); 

            			newLeafNode(lv_name_1_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalVariableRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

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
    // $ANTLR end "ruleLocalVariable"


    // $ANTLR start "entryRuleLifeline"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:254:1: entryRuleLifeline returns [EObject current=null] : iv_ruleLifeline= ruleLifeline EOF ;
    public final EObject entryRuleLifeline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLifeline = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:255:2: (iv_ruleLifeline= ruleLifeline EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:256:2: iv_ruleLifeline= ruleLifeline EOF
            {
             newCompositeNode(grammarAccess.getLifelineRule()); 
            pushFollow(FOLLOW_ruleLifeline_in_entryRuleLifeline417);
            iv_ruleLifeline=ruleLifeline();

            state._fsp--;

             current =iv_ruleLifeline; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLifeline427); 

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
    // $ANTLR end "entryRuleLifeline"


    // $ANTLR start "ruleLifeline"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:263:1: ruleLifeline returns [EObject current=null] : (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) ;
    public final EObject ruleLifeline() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_caption_1_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:266:28: ( (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:267:1: (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:267:1: (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:267:3: otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleLifeline464); 

                	newLeafNode(otherlv_0, grammarAccess.getLifelineAccess().getLifelineKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:271:1: ( (lv_caption_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:272:1: (lv_caption_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:272:1: (lv_caption_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:273:3: lv_caption_1_0= RULE_STRING
            {
            lv_caption_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLifeline481); 

            			newLeafNode(lv_caption_1_0, grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLifelineRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"caption",
                    		lv_caption_1_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleLifeline498); 

                	newLeafNode(otherlv_2, grammarAccess.getLifelineAccess().getAsKeyword_2());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:293:1: ( (lv_name_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:294:1: (lv_name_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:294:1: (lv_name_3_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:295:3: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLifeline515); 

            			newLeafNode(lv_name_3_0, grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLifelineRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_3_0, 
                    		"ID");
            	    

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
    // $ANTLR end "ruleLifeline"


    // $ANTLR start "entryRuleInteraction"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:319:1: entryRuleInteraction returns [EObject current=null] : iv_ruleInteraction= ruleInteraction EOF ;
    public final EObject entryRuleInteraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInteraction = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:320:2: (iv_ruleInteraction= ruleInteraction EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:321:2: iv_ruleInteraction= ruleInteraction EOF
            {
             newCompositeNode(grammarAccess.getInteractionRule()); 
            pushFollow(FOLLOW_ruleInteraction_in_entryRuleInteraction556);
            iv_ruleInteraction=ruleInteraction();

            state._fsp--;

             current =iv_ruleInteraction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInteraction566); 

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
    // $ANTLR end "entryRuleInteraction"


    // $ANTLR start "ruleInteraction"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:328:1: ruleInteraction returns [EObject current=null] : (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement ) ;
    public final EObject ruleInteraction() throws RecognitionException {
        EObject current = null;

        EObject this_TwoLifelineMessage_0 = null;

        EObject this_OneLifelineMessage_1 = null;

        EObject this_Fragment_2 = null;

        EObject this_OneLifelineEndBlock_3 = null;

        EObject this_OneLifelineNote_4 = null;

        EObject this_DestroyLifelineEvent_5 = null;

        EObject this_Refinement_6 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:331:28: ( (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:332:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:332:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement )
            int alt5=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                switch ( input.LA(2) ) {
                case 27:
                    {
                    alt5=4;
                    }
                    break;
                case 35:
                case 36:
                case 37:
                case 38:
                    {
                    alt5=1;
                    }
                    break;
                case 28:
                    {
                    alt5=5;
                    }
                    break;
                case 29:
                    {
                    alt5=6;
                    }
                    break;
                case 24:
                case 25:
                    {
                    alt5=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }

                }
                break;
            case 30:
                {
                alt5=3;
                }
                break;
            case 32:
                {
                alt5=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:333:5: this_TwoLifelineMessage_0= ruleTwoLifelineMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleTwoLifelineMessage_in_ruleInteraction613);
                    this_TwoLifelineMessage_0=ruleTwoLifelineMessage();

                    state._fsp--;

                     
                            current = this_TwoLifelineMessage_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:343:5: this_OneLifelineMessage_1= ruleOneLifelineMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineMessage_in_ruleInteraction640);
                    this_OneLifelineMessage_1=ruleOneLifelineMessage();

                    state._fsp--;

                     
                            current = this_OneLifelineMessage_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:353:5: this_Fragment_2= ruleFragment
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleFragment_in_ruleInteraction667);
                    this_Fragment_2=ruleFragment();

                    state._fsp--;

                     
                            current = this_Fragment_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:363:5: this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineEndBlockParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineEndBlock_in_ruleInteraction694);
                    this_OneLifelineEndBlock_3=ruleOneLifelineEndBlock();

                    state._fsp--;

                     
                            current = this_OneLifelineEndBlock_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:373:5: this_OneLifelineNote_4= ruleOneLifelineNote
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineNote_in_ruleInteraction721);
                    this_OneLifelineNote_4=ruleOneLifelineNote();

                    state._fsp--;

                     
                            current = this_OneLifelineNote_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:383:5: this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_ruleInteraction748);
                    this_DestroyLifelineEvent_5=ruleDestroyLifelineEvent();

                    state._fsp--;

                     
                            current = this_DestroyLifelineEvent_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:393:5: this_Refinement_6= ruleRefinement
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleRefinement_in_ruleInteraction775);
                    this_Refinement_6=ruleRefinement();

                    state._fsp--;

                     
                            current = this_Refinement_6; 
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
    // $ANTLR end "ruleInteraction"


    // $ANTLR start "entryRuleTwoLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:409:1: entryRuleTwoLifelineMessage returns [EObject current=null] : iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF ;
    public final EObject entryRuleTwoLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTwoLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:410:2: (iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:411:2: iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getTwoLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage810);
            iv_ruleTwoLifelineMessage=ruleTwoLifelineMessage();

            state._fsp--;

             current =iv_ruleTwoLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTwoLifelineMessage820); 

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
    // $ANTLR end "entryRuleTwoLifelineMessage"


    // $ANTLR start "ruleTwoLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:418:1: ruleTwoLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) ) | ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) ) | ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleTwoLifelineMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_message_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_sourceStartBlock_5_0=null;
        Token lv_sourceEndBlock_6_0=null;
        Token lv_sourceEndBlockCount_7_0=null;
        Token lv_targetStartBlock_8_0=null;
        Token lv_targetEndBlock_9_0=null;
        Token lv_targetEndBlockCount_10_0=null;
        Token otherlv_11=null;
        Token lv_sourceNote_12_0=null;
        Token otherlv_13=null;
        Token lv_targetNote_14_0=null;
        Enumerator lv_messageType_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:421:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) ) | ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) ) | ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:422:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) ) | ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) ) | ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:422:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) ) | ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) ) | ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:422:2: ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) ) | ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) ) | ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:422:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:423:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:423:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:424:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage865); 

            		newLeafNode(otherlv_0, grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:435:2: ( (lv_messageType_1_0= ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:436:1: (lv_messageType_1_0= ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:436:1: (lv_messageType_1_0= ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:437:3: lv_messageType_1_0= ruleMessageType
            {
             
            	        newCompositeNode(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleMessageType_in_ruleTwoLifelineMessage886);
            lv_messageType_1_0=ruleMessageType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTwoLifelineMessageRule());
            	        }
                   		set(
                   			current, 
                   			"messageType",
                    		lv_messageType_1_0, 
                    		"MessageType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:453:2: ( (lv_message_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:454:1: (lv_message_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:454:1: (lv_message_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:455:3: lv_message_2_0= RULE_STRING
            {
            lv_message_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage903); 

            			newLeafNode(lv_message_2_0, grammarAccess.getTwoLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"message",
                    		lv_message_2_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_17_in_ruleTwoLifelineMessage920); 

                	newLeafNode(otherlv_3, grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:475:1: ( (otherlv_4= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:476:1: (otherlv_4= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:476:1: (otherlv_4= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:477:3: otherlv_4= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage940); 

            		newLeafNode(otherlv_4, grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:488:2: ( ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) ) | ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt7=3;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==18) ) {
                alt7=1;
            }
            else if ( (LA7_0==19) ) {
                alt7=2;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:488:3: ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:488:3: ( (lv_sourceStartBlock_5_0= 'sourceStartBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:489:1: (lv_sourceStartBlock_5_0= 'sourceStartBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:489:1: (lv_sourceStartBlock_5_0= 'sourceStartBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:490:3: lv_sourceStartBlock_5_0= 'sourceStartBlock'
                    {
                    lv_sourceStartBlock_5_0=(Token)match(input,18,FOLLOW_18_in_ruleTwoLifelineMessage959); 

                            newLeafNode(lv_sourceStartBlock_5_0, grammarAccess.getTwoLifelineMessageAccess().getSourceStartBlockSourceStartBlockKeyword_5_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "sourceStartBlock", true, "sourceStartBlock");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:504:6: ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:504:6: ( ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:504:7: ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) ) ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:504:7: ( (lv_sourceEndBlock_6_0= 'sourceEndBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:505:1: (lv_sourceEndBlock_6_0= 'sourceEndBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:505:1: (lv_sourceEndBlock_6_0= 'sourceEndBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:506:3: lv_sourceEndBlock_6_0= 'sourceEndBlock'
                    {
                    lv_sourceEndBlock_6_0=(Token)match(input,19,FOLLOW_19_in_ruleTwoLifelineMessage997); 

                            newLeafNode(lv_sourceEndBlock_6_0, grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockSourceEndBlockKeyword_5_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "sourceEndBlock", true, "sourceEndBlock");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:519:2: ( (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==RULE_INT_GREATER_ZERO) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:520:1: (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:520:1: (lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:521:3: lv_sourceEndBlockCount_7_0= RULE_INT_GREATER_ZERO
                            {
                            lv_sourceEndBlockCount_7_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1027); 

                            			newLeafNode(lv_sourceEndBlockCount_7_0, grammarAccess.getTwoLifelineMessageAccess().getSourceEndBlockCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"sourceEndBlockCount",
                                    		lv_sourceEndBlockCount_7_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:537:6: ( ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) ) | ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt9=3;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==20) ) {
                alt9=1;
            }
            else if ( (LA9_0==21) ) {
                alt9=2;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:537:7: ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:537:7: ( (lv_targetStartBlock_8_0= 'targetStartBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:538:1: (lv_targetStartBlock_8_0= 'targetStartBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:538:1: (lv_targetStartBlock_8_0= 'targetStartBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:539:3: lv_targetStartBlock_8_0= 'targetStartBlock'
                    {
                    lv_targetStartBlock_8_0=(Token)match(input,20,FOLLOW_20_in_ruleTwoLifelineMessage1055); 

                            newLeafNode(lv_targetStartBlock_8_0, grammarAccess.getTwoLifelineMessageAccess().getTargetStartBlockTargetStartBlockKeyword_6_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "targetStartBlock", true, "targetStartBlock");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:553:6: ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:553:6: ( ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:553:7: ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) ) ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:553:7: ( (lv_targetEndBlock_9_0= 'targetEndBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:554:1: (lv_targetEndBlock_9_0= 'targetEndBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:554:1: (lv_targetEndBlock_9_0= 'targetEndBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:555:3: lv_targetEndBlock_9_0= 'targetEndBlock'
                    {
                    lv_targetEndBlock_9_0=(Token)match(input,21,FOLLOW_21_in_ruleTwoLifelineMessage1093); 

                            newLeafNode(lv_targetEndBlock_9_0, grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockTargetEndBlockKeyword_6_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "targetEndBlock", true, "targetEndBlock");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:568:2: ( (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO ) )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==RULE_INT_GREATER_ZERO) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:569:1: (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:569:1: (lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:570:3: lv_targetEndBlockCount_10_0= RULE_INT_GREATER_ZERO
                            {
                            lv_targetEndBlockCount_10_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1123); 

                            			newLeafNode(lv_targetEndBlockCount_10_0, grammarAccess.getTwoLifelineMessageAccess().getTargetEndBlockCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"targetEndBlockCount",
                                    		lv_targetEndBlockCount_10_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:586:6: (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==22) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:586:8: otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) )
                    {
                    otherlv_11=(Token)match(input,22,FOLLOW_22_in_ruleTwoLifelineMessage1145); 

                        	newLeafNode(otherlv_11, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:590:1: ( (lv_sourceNote_12_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:591:1: (lv_sourceNote_12_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:591:1: (lv_sourceNote_12_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:592:3: lv_sourceNote_12_0= RULE_STRING
                    {
                    lv_sourceNote_12_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1162); 

                    			newLeafNode(lv_sourceNote_12_0, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sourceNote",
                            		lv_sourceNote_12_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:608:4: (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:608:6: otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) )
                    {
                    otherlv_13=(Token)match(input,23,FOLLOW_23_in_ruleTwoLifelineMessage1182); 

                        	newLeafNode(otherlv_13, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:612:1: ( (lv_targetNote_14_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:613:1: (lv_targetNote_14_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:613:1: (lv_targetNote_14_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:614:3: lv_targetNote_14_0= RULE_STRING
                    {
                    lv_targetNote_14_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1199); 

                    			newLeafNode(lv_targetNote_14_0, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"targetNote",
                            		lv_targetNote_14_0, 
                            		"STRING");
                    	    

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
    // $ANTLR end "ruleTwoLifelineMessage"


    // $ANTLR start "entryRuleOneLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:638:1: entryRuleOneLifelineMessage returns [EObject current=null] : iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF ;
    public final EObject entryRuleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:639:2: (iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:640:2: iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1242);
            iv_ruleOneLifelineMessage=ruleOneLifelineMessage();

            state._fsp--;

             current =iv_ruleOneLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineMessage1252); 

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
    // $ANTLR end "entryRuleOneLifelineMessage"


    // $ANTLR start "ruleOneLifelineMessage"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:647:1: ruleOneLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) ) ) ( (lv_caption_2_0= RULE_STRING ) ) ( ( (lv_startBlock_3_0= 'startBlock' ) ) | ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_6= 'note' ( (lv_note_7_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_messageType_1_1=null;
        Token lv_messageType_1_2=null;
        Token lv_caption_2_0=null;
        Token lv_startBlock_3_0=null;
        Token lv_endBlock_4_0=null;
        Token lv_endBlockCount_5_0=null;
        Token otherlv_6=null;
        Token lv_note_7_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:650:28: ( ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) ) ) ( (lv_caption_2_0= RULE_STRING ) ) ( ( (lv_startBlock_3_0= 'startBlock' ) ) | ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_6= 'note' ( (lv_note_7_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:651:1: ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) ) ) ( (lv_caption_2_0= RULE_STRING ) ) ( ( (lv_startBlock_3_0= 'startBlock' ) ) | ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_6= 'note' ( (lv_note_7_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:651:1: ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) ) ) ( (lv_caption_2_0= RULE_STRING ) ) ( ( (lv_startBlock_3_0= 'startBlock' ) ) | ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_6= 'note' ( (lv_note_7_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:651:2: ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) ) ) ( (lv_caption_2_0= RULE_STRING ) ) ( ( (lv_startBlock_3_0= 'startBlock' ) ) | ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_6= 'note' ( (lv_note_7_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:651:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:652:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:652:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:653:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineMessage1297); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:664:2: ( ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:665:1: ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:665:1: ( (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:666:1: (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:666:1: (lv_messageType_1_1= 'lost' | lv_messageType_1_2= 'found' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==24) ) {
                alt12=1;
            }
            else if ( (LA12_0==25) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:667:3: lv_messageType_1_1= 'lost'
                    {
                    lv_messageType_1_1=(Token)match(input,24,FOLLOW_24_in_ruleOneLifelineMessage1317); 

                            newLeafNode(lv_messageType_1_1, grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "messageType", lv_messageType_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:679:8: lv_messageType_1_2= 'found'
                    {
                    lv_messageType_1_2=(Token)match(input,25,FOLLOW_25_in_ruleOneLifelineMessage1346); 

                            newLeafNode(lv_messageType_1_2, grammarAccess.getOneLifelineMessageAccess().getMessageTypeFoundKeyword_1_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "messageType", lv_messageType_1_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:694:2: ( (lv_caption_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:695:1: (lv_caption_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:695:1: (lv_caption_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:696:3: lv_caption_2_0= RULE_STRING
            {
            lv_caption_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1379); 

            			newLeafNode(lv_caption_2_0, grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"caption",
                    		lv_caption_2_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:712:2: ( ( (lv_startBlock_3_0= 'startBlock' ) ) | ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt14=3;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==26) ) {
                alt14=1;
            }
            else if ( (LA14_0==27) ) {
                alt14=2;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:712:3: ( (lv_startBlock_3_0= 'startBlock' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:712:3: ( (lv_startBlock_3_0= 'startBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:713:1: (lv_startBlock_3_0= 'startBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:713:1: (lv_startBlock_3_0= 'startBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:714:3: lv_startBlock_3_0= 'startBlock'
                    {
                    lv_startBlock_3_0=(Token)match(input,26,FOLLOW_26_in_ruleOneLifelineMessage1403); 

                            newLeafNode(lv_startBlock_3_0, grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_3_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startBlock", true, "startBlock");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:728:6: ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:728:6: ( ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:728:7: ( (lv_endBlock_4_0= 'endBlock' ) ) ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:728:7: ( (lv_endBlock_4_0= 'endBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:729:1: (lv_endBlock_4_0= 'endBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:729:1: (lv_endBlock_4_0= 'endBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:730:3: lv_endBlock_4_0= 'endBlock'
                    {
                    lv_endBlock_4_0=(Token)match(input,27,FOLLOW_27_in_ruleOneLifelineMessage1441); 

                            newLeafNode(lv_endBlock_4_0, grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_3_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endBlock", true, "endBlock");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:743:2: ( (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO ) )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==RULE_INT_GREATER_ZERO) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:744:1: (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:744:1: (lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:745:3: lv_endBlockCount_5_0= RULE_INT_GREATER_ZERO
                            {
                            lv_endBlockCount_5_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineMessage1471); 

                            			newLeafNode(lv_endBlockCount_5_0, grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_3_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endBlockCount",
                                    		lv_endBlockCount_5_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:761:6: (otherlv_6= 'note' ( (lv_note_7_0= RULE_STRING ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==28) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:761:8: otherlv_6= 'note' ( (lv_note_7_0= RULE_STRING ) )
                    {
                    otherlv_6=(Token)match(input,28,FOLLOW_28_in_ruleOneLifelineMessage1493); 

                        	newLeafNode(otherlv_6, grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:765:1: ( (lv_note_7_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:766:1: (lv_note_7_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:766:1: (lv_note_7_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:767:3: lv_note_7_0= RULE_STRING
                    {
                    lv_note_7_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1510); 

                    			newLeafNode(lv_note_7_0, grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_4_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"note",
                            		lv_note_7_0, 
                            		"STRING");
                    	    

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
    // $ANTLR end "ruleOneLifelineMessage"


    // $ANTLR start "entryRuleOneLifelineEndBlock"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:791:1: entryRuleOneLifelineEndBlock returns [EObject current=null] : iv_ruleOneLifelineEndBlock= ruleOneLifelineEndBlock EOF ;
    public final EObject entryRuleOneLifelineEndBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineEndBlock = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:792:2: (iv_ruleOneLifelineEndBlock= ruleOneLifelineEndBlock EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:793:2: iv_ruleOneLifelineEndBlock= ruleOneLifelineEndBlock EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineEndBlockRule()); 
            pushFollow(FOLLOW_ruleOneLifelineEndBlock_in_entryRuleOneLifelineEndBlock1553);
            iv_ruleOneLifelineEndBlock=ruleOneLifelineEndBlock();

            state._fsp--;

             current =iv_ruleOneLifelineEndBlock; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineEndBlock1563); 

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
    // $ANTLR end "entryRuleOneLifelineEndBlock"


    // $ANTLR start "ruleOneLifelineEndBlock"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:800:1: ruleOneLifelineEndBlock returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'endBlock' ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? ) ;
    public final EObject ruleOneLifelineEndBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_endBlockCount_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:803:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'endBlock' ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:804:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'endBlock' ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:804:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'endBlock' ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:804:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= 'endBlock' ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:804:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:805:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:805:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:806:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineEndBlockRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineEndBlock1608); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            otherlv_1=(Token)match(input,27,FOLLOW_27_in_ruleOneLifelineEndBlock1620); 

                	newLeafNode(otherlv_1, grammarAccess.getOneLifelineEndBlockAccess().getEndBlockKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:821:1: ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT_GREATER_ZERO) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:822:1: (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:822:1: (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:823:3: lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO
                    {
                    lv_endBlockCount_2_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineEndBlock1637); 

                    			newLeafNode(lv_endBlockCount_2_0, grammarAccess.getOneLifelineEndBlockAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineEndBlockRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"endBlockCount",
                            		lv_endBlockCount_2_0, 
                            		"INT_GREATER_ZERO");
                    	    

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
    // $ANTLR end "ruleOneLifelineEndBlock"


    // $ANTLR start "entryRuleOneLifelineNote"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:847:1: entryRuleOneLifelineNote returns [EObject current=null] : iv_ruleOneLifelineNote= ruleOneLifelineNote EOF ;
    public final EObject entryRuleOneLifelineNote() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineNote = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:848:2: (iv_ruleOneLifelineNote= ruleOneLifelineNote EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:849:2: iv_ruleOneLifelineNote= ruleOneLifelineNote EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineNoteRule()); 
            pushFollow(FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote1679);
            iv_ruleOneLifelineNote=ruleOneLifelineNote();

            state._fsp--;

             current =iv_ruleOneLifelineNote; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineNote1689); 

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
    // $ANTLR end "entryRuleOneLifelineNote"


    // $ANTLR start "ruleOneLifelineNote"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:856:1: ruleOneLifelineNote returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleOneLifelineNote() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_note_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:859:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:860:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:860:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:860:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:860:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:861:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:861:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:862:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineNoteRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineNote1734); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleOneLifelineNote1746); 

                	newLeafNode(otherlv_1, grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:877:1: ( (lv_note_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:878:1: (lv_note_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:878:1: (lv_note_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:879:3: lv_note_2_0= RULE_STRING
            {
            lv_note_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineNote1763); 

            			newLeafNode(lv_note_2_0, grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineNoteRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"note",
                    		lv_note_2_0, 
                    		"STRING");
            	    

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
    // $ANTLR end "ruleOneLifelineNote"


    // $ANTLR start "entryRuleDestroyLifelineEvent"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:903:1: entryRuleDestroyLifelineEvent returns [EObject current=null] : iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF ;
    public final EObject entryRuleDestroyLifelineEvent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDestroyLifelineEvent = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:904:2: (iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:905:2: iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF
            {
             newCompositeNode(grammarAccess.getDestroyLifelineEventRule()); 
            pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent1804);
            iv_ruleDestroyLifelineEvent=ruleDestroyLifelineEvent();

            state._fsp--;

             current =iv_ruleDestroyLifelineEvent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroyLifelineEvent1814); 

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
    // $ANTLR end "entryRuleDestroyLifelineEvent"


    // $ANTLR start "ruleDestroyLifelineEvent"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:912:1: ruleDestroyLifelineEvent returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' ) ;
    public final EObject ruleDestroyLifelineEvent() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:915:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:916:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:916:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:916:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy'
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:916:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:917:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:917:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:918:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDestroyLifelineEventRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDestroyLifelineEvent1859); 

            		newLeafNode(otherlv_0, grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            otherlv_1=(Token)match(input,29,FOLLOW_29_in_ruleDestroyLifelineEvent1871); 

                	newLeafNode(otherlv_1, grammarAccess.getDestroyLifelineEventAccess().getDestroyKeyword_1());
                

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
    // $ANTLR end "ruleDestroyLifelineEvent"


    // $ANTLR start "entryRuleFragment"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:941:1: entryRuleFragment returns [EObject current=null] : iv_ruleFragment= ruleFragment EOF ;
    public final EObject entryRuleFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFragment = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:942:2: (iv_ruleFragment= ruleFragment EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:943:2: iv_ruleFragment= ruleFragment EOF
            {
             newCompositeNode(grammarAccess.getFragmentRule()); 
            pushFollow(FOLLOW_ruleFragment_in_entryRuleFragment1907);
            iv_ruleFragment=ruleFragment();

            state._fsp--;

             current =iv_ruleFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragment1917); 

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
    // $ANTLR end "entryRuleFragment"


    // $ANTLR start "ruleFragment"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:950:1: ruleFragment returns [EObject current=null] : (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* ) ;
    public final EObject ruleFragment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        EObject lv_sections_2_0 = null;

        EObject lv_sections_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:953:28: ( (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:954:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:954:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:954:3: otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )*
            {
            otherlv_0=(Token)match(input,30,FOLLOW_30_in_ruleFragment1954); 

                	newLeafNode(otherlv_0, grammarAccess.getFragmentAccess().getFragmentKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:958:1: ( (lv_name_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:959:1: (lv_name_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:959:1: (lv_name_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:960:3: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFragment1971); 

            			newLeafNode(lv_name_1_0, grammarAccess.getFragmentAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getFragmentRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:976:2: ( (lv_sections_2_0= ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:977:1: (lv_sections_2_0= ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:977:1: (lv_sections_2_0= ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:978:3: lv_sections_2_0= ruleSection
            {
             
            	        newCompositeNode(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSection_in_ruleFragment1997);
            lv_sections_2_0=ruleSection();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFragmentRule());
            	        }
                   		add(
                   			current, 
                   			"sections",
                    		lv_sections_2_0, 
                    		"Section");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:994:2: ( (lv_sections_3_0= ruleSection ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==13) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:995:1: (lv_sections_3_0= ruleSection )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:995:1: (lv_sections_3_0= ruleSection )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:996:3: lv_sections_3_0= ruleSection
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSection_in_ruleFragment2018);
            	    lv_sections_3_0=ruleSection();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFragmentRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"sections",
            	            		lv_sections_3_0, 
            	            		"Section");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
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
    // $ANTLR end "ruleFragment"


    // $ANTLR start "entryRuleSection"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1020:1: entryRuleSection returns [EObject current=null] : iv_ruleSection= ruleSection EOF ;
    public final EObject entryRuleSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSection = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1021:2: (iv_ruleSection= ruleSection EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1022:2: iv_ruleSection= ruleSection EOF
            {
             newCompositeNode(grammarAccess.getSectionRule()); 
            pushFollow(FOLLOW_ruleSection_in_entryRuleSection2055);
            iv_ruleSection=ruleSection();

            state._fsp--;

             current =iv_ruleSection; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSection2065); 

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
    // $ANTLR end "entryRuleSection"


    // $ANTLR start "ruleSection"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1029:1: ruleSection returns [EObject current=null] : (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) ;
    public final EObject ruleSection() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_label_2_0=null;
        Token otherlv_5=null;
        EObject lv_interactions_3_0 = null;

        EObject lv_interactions_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1032:28: ( (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1033:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1033:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1033:3: otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_13_in_ruleSection2102); 

                	newLeafNode(otherlv_0, grammarAccess.getSectionAccess().getLeftCurlyBracketKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1037:1: (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==31) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1037:3: otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,31,FOLLOW_31_in_ruleSection2115); 

                        	newLeafNode(otherlv_1, grammarAccess.getSectionAccess().getLabelKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1041:1: ( (lv_label_2_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1042:1: (lv_label_2_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1042:1: (lv_label_2_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1043:3: lv_label_2_0= RULE_STRING
                    {
                    lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSection2132); 

                    			newLeafNode(lv_label_2_0, grammarAccess.getSectionAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSectionRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"label",
                            		lv_label_2_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1059:4: ( (lv_interactions_3_0= ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1060:1: (lv_interactions_3_0= ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1060:1: (lv_interactions_3_0= ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1061:3: lv_interactions_3_0= ruleInteraction
            {
             
            	        newCompositeNode(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleInteraction_in_ruleSection2160);
            lv_interactions_3_0=ruleInteraction();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSectionRule());
            	        }
                   		add(
                   			current, 
                   			"interactions",
                    		lv_interactions_3_0, 
                    		"Interaction");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1077:2: ( (lv_interactions_4_0= ruleInteraction ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID||LA19_0==30||LA19_0==32) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1078:1: (lv_interactions_4_0= ruleInteraction )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1078:1: (lv_interactions_4_0= ruleInteraction )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1079:3: lv_interactions_4_0= ruleInteraction
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInteraction_in_ruleSection2181);
            	    lv_interactions_4_0=ruleInteraction();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSectionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"interactions",
            	            		lv_interactions_4_0, 
            	            		"Interaction");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleSection2194); 

                	newLeafNode(otherlv_5, grammarAccess.getSectionAccess().getRightCurlyBracketKeyword_4());
                

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
    // $ANTLR end "ruleSection"


    // $ANTLR start "entryRuleRefinement"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1107:1: entryRuleRefinement returns [EObject current=null] : iv_ruleRefinement= ruleRefinement EOF ;
    public final EObject entryRuleRefinement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRefinement = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1108:2: (iv_ruleRefinement= ruleRefinement EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1109:2: iv_ruleRefinement= ruleRefinement EOF
            {
             newCompositeNode(grammarAccess.getRefinementRule()); 
            pushFollow(FOLLOW_ruleRefinement_in_entryRuleRefinement2230);
            iv_ruleRefinement=ruleRefinement();

            state._fsp--;

             current =iv_ruleRefinement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRefinement2240); 

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
    // $ANTLR end "entryRuleRefinement"


    // $ANTLR start "ruleRefinement"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1116:1: ruleRefinement returns [EObject current=null] : (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* ) ;
    public final EObject ruleRefinement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_label_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1119:28: ( (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1120:1: (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1120:1: (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1120:3: otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleRefinement2277); 

                	newLeafNode(otherlv_0, grammarAccess.getRefinementAccess().getRefinementKeyword_0());
                
            otherlv_1=(Token)match(input,31,FOLLOW_31_in_ruleRefinement2289); 

                	newLeafNode(otherlv_1, grammarAccess.getRefinementAccess().getLabelKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1128:1: ( (lv_label_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1129:1: (lv_label_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1129:1: (lv_label_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1130:3: lv_label_2_0= RULE_STRING
            {
            lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleRefinement2306); 

            			newLeafNode(lv_label_2_0, grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRefinementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_2_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_3=(Token)match(input,33,FOLLOW_33_in_ruleRefinement2323); 

                	newLeafNode(otherlv_3, grammarAccess.getRefinementAccess().getLifelinesKeyword_3());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1150:1: ( (otherlv_4= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1151:1: (otherlv_4= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1151:1: (otherlv_4= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1152:3: otherlv_4= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getRefinementRule());
            	        }
                    
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2343); 

            		newLeafNode(otherlv_4, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1163:2: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==34) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1163:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
            	    {
            	    otherlv_5=(Token)match(input,34,FOLLOW_34_in_ruleRefinement2356); 

            	        	newLeafNode(otherlv_5, grammarAccess.getRefinementAccess().getCommaKeyword_5_0());
            	        
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1167:1: ( (otherlv_6= RULE_ID ) )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1168:1: (otherlv_6= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1168:1: (otherlv_6= RULE_ID )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1169:3: otherlv_6= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getRefinementRule());
            	    	        }
            	            
            	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2376); 

            	    		newLeafNode(otherlv_6, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_5_1_0()); 
            	    	

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // $ANTLR end "ruleRefinement"


    // $ANTLR start "ruleMessageType"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1188:1: ruleMessageType returns [Enumerator current=null] : ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) ) ;
    public final Enumerator ruleMessageType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1190:28: ( ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1191:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1191:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) )
            int alt21=4;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt21=1;
                }
                break;
            case 36:
                {
                alt21=2;
                }
                break;
            case 37:
                {
                alt21=3;
                }
                break;
            case 38:
                {
                alt21=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1191:2: (enumLiteral_0= 'async' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1191:2: (enumLiteral_0= 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1191:4: enumLiteral_0= 'async'
                    {
                    enumLiteral_0=(Token)match(input,35,FOLLOW_35_in_ruleMessageType2428); 

                            current = grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1197:6: (enumLiteral_1= 'create' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1197:6: (enumLiteral_1= 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1197:8: enumLiteral_1= 'create'
                    {
                    enumLiteral_1=(Token)match(input,36,FOLLOW_36_in_ruleMessageType2445); 

                            current = grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1203:6: (enumLiteral_2= 'response' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1203:6: (enumLiteral_2= 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1203:8: enumLiteral_2= 'response'
                    {
                    enumLiteral_2=(Token)match(input,37,FOLLOW_37_in_ruleMessageType2462); 

                            current = grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1209:6: (enumLiteral_3= 'sync' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1209:6: (enumLiteral_3= 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1209:8: enumLiteral_3= 'sync'
                    {
                    enumLiteral_3=(Token)match(input,38,FOLLOW_38_in_ruleMessageType2479); 

                            current = grammarAccess.getMessageTypeAccess().getSyncEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getMessageTypeAccess().getSyncEnumLiteralDeclaration_3()); 
                        

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
    // $ANTLR end "ruleMessageType"


    // $ANTLR start "ruleDataType"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1219:1: ruleDataType returns [Enumerator current=null] : ( (enumLiteral_0= 'Char' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Integer' ) | (enumLiteral_3= 'Float' ) ) ;
    public final Enumerator ruleDataType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1221:28: ( ( (enumLiteral_0= 'Char' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Integer' ) | (enumLiteral_3= 'Float' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1222:1: ( (enumLiteral_0= 'Char' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Integer' ) | (enumLiteral_3= 'Float' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1222:1: ( (enumLiteral_0= 'Char' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Integer' ) | (enumLiteral_3= 'Float' ) )
            int alt22=4;
            switch ( input.LA(1) ) {
            case 39:
                {
                alt22=1;
                }
                break;
            case 40:
                {
                alt22=2;
                }
                break;
            case 41:
                {
                alt22=3;
                }
                break;
            case 42:
                {
                alt22=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1222:2: (enumLiteral_0= 'Char' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1222:2: (enumLiteral_0= 'Char' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1222:4: enumLiteral_0= 'Char'
                    {
                    enumLiteral_0=(Token)match(input,39,FOLLOW_39_in_ruleDataType2524); 

                            current = grammarAccess.getDataTypeAccess().getCharEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getDataTypeAccess().getCharEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:6: (enumLiteral_1= 'Boolean' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:6: (enumLiteral_1= 'Boolean' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:8: enumLiteral_1= 'Boolean'
                    {
                    enumLiteral_1=(Token)match(input,40,FOLLOW_40_in_ruleDataType2541); 

                            current = grammarAccess.getDataTypeAccess().getBooleanEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getDataTypeAccess().getBooleanEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1234:6: (enumLiteral_2= 'Integer' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1234:6: (enumLiteral_2= 'Integer' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1234:8: enumLiteral_2= 'Integer'
                    {
                    enumLiteral_2=(Token)match(input,41,FOLLOW_41_in_ruleDataType2558); 

                            current = grammarAccess.getDataTypeAccess().getIntegerEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getDataTypeAccess().getIntegerEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1240:6: (enumLiteral_3= 'Float' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1240:6: (enumLiteral_3= 'Float' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1240:8: enumLiteral_3= 'Float'
                    {
                    enumLiteral_3=(Token)match(input,42,FOLLOW_42_in_ruleDataType2575); 

                            current = grammarAccess.getDataTypeAccess().getFloatEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getDataTypeAccess().getFloatEnumLiteralDeclaration_3()); 
                        

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
    // $ANTLR end "ruleDataType"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleSequenceDiagram131 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSequenceDiagram148 = new BitSet(new long[]{0x000000014000A022L});
    public static final BitSet FOLLOW_13_in_ruleSequenceDiagram166 = new BitSet(new long[]{0x0000078000000000L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_ruleSequenceDiagram187 = new BitSet(new long[]{0x0000078000004000L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_ruleSequenceDiagram208 = new BitSet(new long[]{0x0000078000004000L});
    public static final BitSet FOLLOW_14_in_ruleSequenceDiagram221 = new BitSet(new long[]{0x0000000140008022L});
    public static final BitSet FOLLOW_ruleLifeline_in_ruleSequenceDiagram244 = new BitSet(new long[]{0x0000000140008022L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSequenceDiagram266 = new BitSet(new long[]{0x0000000140000022L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_entryRuleLocalVariable303 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalVariable313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataType_in_ruleLocalVariable359 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLocalVariable376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_entryRuleLifeline417 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLifeline427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleLifeline464 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLifeline481 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleLifeline498 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLifeline515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_entryRuleInteraction556 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInteraction566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_ruleInteraction613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_ruleInteraction640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_ruleInteraction667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineEndBlock_in_ruleInteraction694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_ruleInteraction721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_ruleInteraction748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_ruleInteraction775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage810 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTwoLifelineMessage820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage865 = new BitSet(new long[]{0x0000007800000000L});
    public static final BitSet FOLLOW_ruleMessageType_in_ruleTwoLifelineMessage886 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage903 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleTwoLifelineMessage920 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage940 = new BitSet(new long[]{0x0000000000FC0002L});
    public static final BitSet FOLLOW_18_in_ruleTwoLifelineMessage959 = new BitSet(new long[]{0x0000000000F00002L});
    public static final BitSet FOLLOW_19_in_ruleTwoLifelineMessage997 = new BitSet(new long[]{0x0000000000F00042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1027 = new BitSet(new long[]{0x0000000000F00002L});
    public static final BitSet FOLLOW_20_in_ruleTwoLifelineMessage1055 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_21_in_ruleTwoLifelineMessage1093 = new BitSet(new long[]{0x0000000000C00042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1123 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_22_in_ruleTwoLifelineMessage1145 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1162 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_ruleTwoLifelineMessage1182 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1242 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineMessage1252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineMessage1297 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_24_in_ruleOneLifelineMessage1317 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_25_in_ruleOneLifelineMessage1346 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1379 = new BitSet(new long[]{0x000000001C000002L});
    public static final BitSet FOLLOW_26_in_ruleOneLifelineMessage1403 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_27_in_ruleOneLifelineMessage1441 = new BitSet(new long[]{0x0000000010000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineMessage1471 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleOneLifelineMessage1493 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineEndBlock_in_entryRuleOneLifelineEndBlock1553 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineEndBlock1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineEndBlock1608 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleOneLifelineEndBlock1620 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineEndBlock1637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote1679 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineNote1689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineNote1734 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleOneLifelineNote1746 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineNote1763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent1804 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroyLifelineEvent1814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDestroyLifelineEvent1859 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleDestroyLifelineEvent1871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment1907 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment1917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleFragment1954 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFragment1971 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleSection_in_ruleFragment1997 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ruleSection_in_ruleFragment2018 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ruleSection_in_entryRuleSection2055 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSection2065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleSection2102 = new BitSet(new long[]{0x00000001C0000020L});
    public static final BitSet FOLLOW_31_in_ruleSection2115 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSection2132 = new BitSet(new long[]{0x0000000140000020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSection2160 = new BitSet(new long[]{0x0000000140004020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSection2181 = new BitSet(new long[]{0x0000000140004020L});
    public static final BitSet FOLLOW_14_in_ruleSection2194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement2230 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement2240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleRefinement2277 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleRefinement2289 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleRefinement2306 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleRefinement2323 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2343 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_ruleRefinement2356 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2376 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_35_in_ruleMessageType2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleMessageType2445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleMessageType2462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleMessageType2479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleDataType2524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleDataType2541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleDataType2558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleDataType2575 = new BitSet(new long[]{0x0000000000000002L});

}