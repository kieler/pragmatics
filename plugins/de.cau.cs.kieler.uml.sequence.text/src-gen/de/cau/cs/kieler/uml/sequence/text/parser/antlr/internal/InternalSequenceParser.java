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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'sequenceDiagram'", "'{'", "'}'", "'Char'", "'Boolean'", "'Integer'", "'Float'", "'lifeline'", "'as'", "'to'", "'sourceStartBlock'", "'sourceEndBlock'", "'targetStartBlock'", "'targetEndBlock'", "'sourceNote'", "'targetNote'", "'lost'", "'found'", "'startBlock'", "'endBlock'", "'note'", "'destroy'", "'fragment'", "'label'", "'refinement'", "'lifelines'", "','", "'async'", "'create'", "'response'", "'sync'"
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:77:1: ruleSequenceDiagram returns [EObject current=null] : ( () otherlv_1= 'sequenceDiagram' ( (lv_DiagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) )* otherlv_5= '}' )? ( (lv_lifelines_6_0= ruleLifeline ) )* ( (lv_interactions_7_0= ruleInteraction ) )* ) ;
    public final EObject ruleSequenceDiagram() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_DiagramName_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_locals_4_0 = null;

        EObject lv_lifelines_6_0 = null;

        EObject lv_interactions_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:80:28: ( ( () otherlv_1= 'sequenceDiagram' ( (lv_DiagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) )* otherlv_5= '}' )? ( (lv_lifelines_6_0= ruleLifeline ) )* ( (lv_interactions_7_0= ruleInteraction ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:1: ( () otherlv_1= 'sequenceDiagram' ( (lv_DiagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) )* otherlv_5= '}' )? ( (lv_lifelines_6_0= ruleLifeline ) )* ( (lv_interactions_7_0= ruleInteraction ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:1: ( () otherlv_1= 'sequenceDiagram' ( (lv_DiagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) )* otherlv_5= '}' )? ( (lv_lifelines_6_0= ruleLifeline ) )* ( (lv_interactions_7_0= ruleInteraction ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:2: () otherlv_1= 'sequenceDiagram' ( (lv_DiagramName_2_0= RULE_STRING ) ) (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) )* otherlv_5= '}' )? ( (lv_lifelines_6_0= ruleLifeline ) )* ( (lv_interactions_7_0= ruleInteraction ) )*
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
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:91:1: ( (lv_DiagramName_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:92:1: (lv_DiagramName_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:92:1: (lv_DiagramName_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:93:3: lv_DiagramName_2_0= RULE_STRING
            {
            lv_DiagramName_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSequenceDiagram148); 

            			newLeafNode(lv_DiagramName_2_0, grammarAccess.getSequenceDiagramAccess().getDiagramNameSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSequenceDiagramRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"DiagramName",
                    		lv_DiagramName_2_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:109:2: (otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) )* otherlv_5= '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:109:4: otherlv_3= '{' ( (lv_locals_4_0= ruleLocalVariable ) )* otherlv_5= '}'
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleSequenceDiagram166); 

                        	newLeafNode(otherlv_3, grammarAccess.getSequenceDiagramAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:113:1: ( (lv_locals_4_0= ruleLocalVariable ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>=15 && LA1_0<=18)) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
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
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleSequenceDiagram200); 

                        	newLeafNode(otherlv_5, grammarAccess.getSequenceDiagramAccess().getRightCurlyBracketKeyword_3_2());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:135:3: ( (lv_lifelines_6_0= ruleLifeline ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==19) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:136:1: (lv_lifelines_6_0= ruleLifeline )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:136:1: (lv_lifelines_6_0= ruleLifeline )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:137:3: lv_lifelines_6_0= ruleLifeline
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLifeline_in_ruleSequenceDiagram223);
            	    lv_lifelines_6_0=ruleLifeline();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"lifelines",
            	            		lv_lifelines_6_0, 
            	            		"Lifeline");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:153:3: ( (lv_interactions_7_0= ruleInteraction ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_ID||LA4_0==34||LA4_0==36) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:154:1: (lv_interactions_7_0= ruleInteraction )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:154:1: (lv_interactions_7_0= ruleInteraction )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:155:3: lv_interactions_7_0= ruleInteraction
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInteraction_in_ruleSequenceDiagram245);
            	    lv_interactions_7_0=ruleInteraction();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"interactions",
            	            		lv_interactions_7_0, 
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:179:1: entryRuleLocalVariable returns [EObject current=null] : iv_ruleLocalVariable= ruleLocalVariable EOF ;
    public final EObject entryRuleLocalVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalVariable = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:180:2: (iv_ruleLocalVariable= ruleLocalVariable EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:181:2: iv_ruleLocalVariable= ruleLocalVariable EOF
            {
             newCompositeNode(grammarAccess.getLocalVariableRule()); 
            pushFollow(FOLLOW_ruleLocalVariable_in_entryRuleLocalVariable282);
            iv_ruleLocalVariable=ruleLocalVariable();

            state._fsp--;

             current =iv_ruleLocalVariable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalVariable292); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:188:1: ruleLocalVariable returns [EObject current=null] : ( (otherlv_0= 'Char' ( (lv_name_1_0= RULE_ID ) ) ) | (otherlv_2= 'Boolean' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'Integer' ( (lv_name_5_0= RULE_ID ) ) ) | (otherlv_6= 'Float' ( (lv_name_7_0= RULE_ID ) ) ) ) ;
    public final EObject ruleLocalVariable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token lv_name_7_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:191:28: ( ( (otherlv_0= 'Char' ( (lv_name_1_0= RULE_ID ) ) ) | (otherlv_2= 'Boolean' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'Integer' ( (lv_name_5_0= RULE_ID ) ) ) | (otherlv_6= 'Float' ( (lv_name_7_0= RULE_ID ) ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:192:1: ( (otherlv_0= 'Char' ( (lv_name_1_0= RULE_ID ) ) ) | (otherlv_2= 'Boolean' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'Integer' ( (lv_name_5_0= RULE_ID ) ) ) | (otherlv_6= 'Float' ( (lv_name_7_0= RULE_ID ) ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:192:1: ( (otherlv_0= 'Char' ( (lv_name_1_0= RULE_ID ) ) ) | (otherlv_2= 'Boolean' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'Integer' ( (lv_name_5_0= RULE_ID ) ) ) | (otherlv_6= 'Float' ( (lv_name_7_0= RULE_ID ) ) ) )
            int alt5=4;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt5=1;
                }
                break;
            case 16:
                {
                alt5=2;
                }
                break;
            case 17:
                {
                alt5=3;
                }
                break;
            case 18:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:192:2: (otherlv_0= 'Char' ( (lv_name_1_0= RULE_ID ) ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:192:2: (otherlv_0= 'Char' ( (lv_name_1_0= RULE_ID ) ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:192:4: otherlv_0= 'Char' ( (lv_name_1_0= RULE_ID ) )
                    {
                    otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleLocalVariable330); 

                        	newLeafNode(otherlv_0, grammarAccess.getLocalVariableAccess().getCharKeyword_0_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:196:1: ( (lv_name_1_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:197:1: (lv_name_1_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:197:1: (lv_name_1_0= RULE_ID )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:198:3: lv_name_1_0= RULE_ID
                    {
                    lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLocalVariable347); 

                    			newLeafNode(lv_name_1_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_0_1_0()); 
                    		

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
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:215:6: (otherlv_2= 'Boolean' ( (lv_name_3_0= RULE_ID ) ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:215:6: (otherlv_2= 'Boolean' ( (lv_name_3_0= RULE_ID ) ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:215:8: otherlv_2= 'Boolean' ( (lv_name_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleLocalVariable372); 

                        	newLeafNode(otherlv_2, grammarAccess.getLocalVariableAccess().getBooleanKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:219:1: ( (lv_name_3_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:220:1: (lv_name_3_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:220:1: (lv_name_3_0= RULE_ID )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:221:3: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLocalVariable389); 

                    			newLeafNode(lv_name_3_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLocalVariableRule());
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
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:238:6: (otherlv_4= 'Integer' ( (lv_name_5_0= RULE_ID ) ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:238:6: (otherlv_4= 'Integer' ( (lv_name_5_0= RULE_ID ) ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:238:8: otherlv_4= 'Integer' ( (lv_name_5_0= RULE_ID ) )
                    {
                    otherlv_4=(Token)match(input,17,FOLLOW_17_in_ruleLocalVariable414); 

                        	newLeafNode(otherlv_4, grammarAccess.getLocalVariableAccess().getIntegerKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:242:1: ( (lv_name_5_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:243:1: (lv_name_5_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:243:1: (lv_name_5_0= RULE_ID )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:244:3: lv_name_5_0= RULE_ID
                    {
                    lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLocalVariable431); 

                    			newLeafNode(lv_name_5_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_2_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLocalVariableRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_5_0, 
                            		"ID");
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:261:6: (otherlv_6= 'Float' ( (lv_name_7_0= RULE_ID ) ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:261:6: (otherlv_6= 'Float' ( (lv_name_7_0= RULE_ID ) ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:261:8: otherlv_6= 'Float' ( (lv_name_7_0= RULE_ID ) )
                    {
                    otherlv_6=(Token)match(input,18,FOLLOW_18_in_ruleLocalVariable456); 

                        	newLeafNode(otherlv_6, grammarAccess.getLocalVariableAccess().getFloatKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:265:1: ( (lv_name_7_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:266:1: (lv_name_7_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:266:1: (lv_name_7_0= RULE_ID )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:267:3: lv_name_7_0= RULE_ID
                    {
                    lv_name_7_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLocalVariable473); 

                    			newLeafNode(lv_name_7_0, grammarAccess.getLocalVariableAccess().getNameIDTerminalRuleCall_3_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLocalVariableRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_7_0, 
                            		"ID");
                    	    

                    }


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
    // $ANTLR end "ruleLocalVariable"


    // $ANTLR start "entryRuleLifeline"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:291:1: entryRuleLifeline returns [EObject current=null] : iv_ruleLifeline= ruleLifeline EOF ;
    public final EObject entryRuleLifeline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLifeline = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:292:2: (iv_ruleLifeline= ruleLifeline EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:293:2: iv_ruleLifeline= ruleLifeline EOF
            {
             newCompositeNode(grammarAccess.getLifelineRule()); 
            pushFollow(FOLLOW_ruleLifeline_in_entryRuleLifeline515);
            iv_ruleLifeline=ruleLifeline();

            state._fsp--;

             current =iv_ruleLifeline; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLifeline525); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:300:1: ruleLifeline returns [EObject current=null] : (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) ;
    public final EObject ruleLifeline() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_caption_1_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:303:28: ( (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:304:1: (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:304:1: (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:304:3: otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleLifeline562); 

                	newLeafNode(otherlv_0, grammarAccess.getLifelineAccess().getLifelineKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:308:1: ( (lv_caption_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:309:1: (lv_caption_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:309:1: (lv_caption_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:310:3: lv_caption_1_0= RULE_STRING
            {
            lv_caption_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLifeline579); 

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

            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleLifeline596); 

                	newLeafNode(otherlv_2, grammarAccess.getLifelineAccess().getAsKeyword_2());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:330:1: ( (lv_name_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:331:1: (lv_name_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:331:1: (lv_name_3_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:332:3: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLifeline613); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:356:1: entryRuleInteraction returns [EObject current=null] : iv_ruleInteraction= ruleInteraction EOF ;
    public final EObject entryRuleInteraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInteraction = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:357:2: (iv_ruleInteraction= ruleInteraction EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:358:2: iv_ruleInteraction= ruleInteraction EOF
            {
             newCompositeNode(grammarAccess.getInteractionRule()); 
            pushFollow(FOLLOW_ruleInteraction_in_entryRuleInteraction654);
            iv_ruleInteraction=ruleInteraction();

            state._fsp--;

             current =iv_ruleInteraction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInteraction664); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:365:1: ruleInteraction returns [EObject current=null] : (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_Destroy_5= ruleDestroy | this_Refinement_6= ruleRefinement ) ;
    public final EObject ruleInteraction() throws RecognitionException {
        EObject current = null;

        EObject this_TwoLifelineMessage_0 = null;

        EObject this_OneLifelineMessage_1 = null;

        EObject this_Fragment_2 = null;

        EObject this_OneLifelineEndBlock_3 = null;

        EObject this_OneLifelineNote_4 = null;

        EObject this_Destroy_5 = null;

        EObject this_Refinement_6 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:368:28: ( (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_Destroy_5= ruleDestroy | this_Refinement_6= ruleRefinement ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:369:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_Destroy_5= ruleDestroy | this_Refinement_6= ruleRefinement )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:369:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_Destroy_5= ruleDestroy | this_Refinement_6= ruleRefinement )
            int alt6=7;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:370:5: this_TwoLifelineMessage_0= ruleTwoLifelineMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleTwoLifelineMessage_in_ruleInteraction711);
                    this_TwoLifelineMessage_0=ruleTwoLifelineMessage();

                    state._fsp--;

                     
                            current = this_TwoLifelineMessage_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:380:5: this_OneLifelineMessage_1= ruleOneLifelineMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineMessage_in_ruleInteraction738);
                    this_OneLifelineMessage_1=ruleOneLifelineMessage();

                    state._fsp--;

                     
                            current = this_OneLifelineMessage_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:390:5: this_Fragment_2= ruleFragment
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleFragment_in_ruleInteraction765);
                    this_Fragment_2=ruleFragment();

                    state._fsp--;

                     
                            current = this_Fragment_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:400:5: this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineEndBlockParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineEndBlock_in_ruleInteraction792);
                    this_OneLifelineEndBlock_3=ruleOneLifelineEndBlock();

                    state._fsp--;

                     
                            current = this_OneLifelineEndBlock_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:410:5: this_OneLifelineNote_4= ruleOneLifelineNote
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineNote_in_ruleInteraction819);
                    this_OneLifelineNote_4=ruleOneLifelineNote();

                    state._fsp--;

                     
                            current = this_OneLifelineNote_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:420:5: this_Destroy_5= ruleDestroy
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getDestroyParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleDestroy_in_ruleInteraction846);
                    this_Destroy_5=ruleDestroy();

                    state._fsp--;

                     
                            current = this_Destroy_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:430:5: this_Refinement_6= ruleRefinement
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleRefinement_in_ruleInteraction873);
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:446:1: entryRuleTwoLifelineMessage returns [EObject current=null] : iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF ;
    public final EObject entryRuleTwoLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTwoLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:447:2: (iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:448:2: iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getTwoLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage908);
            iv_ruleTwoLifelineMessage=ruleTwoLifelineMessage();

            state._fsp--;

             current =iv_ruleTwoLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTwoLifelineMessage918); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:455:1: ruleTwoLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) ( (lv_caption_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) ) | ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_startBlockRight_8_0= 'targetStartBlock' ) ) | ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleTwoLifelineMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_caption_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_startBlockLeft_5_0=null;
        Token lv_endBlockLeft_6_0=null;
        Token lv_endBlockLeftCount_7_0=null;
        Token lv_startBlockRight_8_0=null;
        Token lv_endBlockRight_9_0=null;
        Token lv_endBlockRightCount_10_0=null;
        Token otherlv_11=null;
        Token lv_sourceNote_12_0=null;
        Token otherlv_13=null;
        Token lv_targetNote_14_0=null;
        Enumerator lv_transitionType_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:458:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) ( (lv_caption_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) ) | ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_startBlockRight_8_0= 'targetStartBlock' ) ) | ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:459:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) ( (lv_caption_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) ) | ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_startBlockRight_8_0= 'targetStartBlock' ) ) | ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:459:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) ( (lv_caption_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) ) | ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_startBlockRight_8_0= 'targetStartBlock' ) ) | ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:459:2: ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) ( (lv_caption_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) ) | ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_startBlockRight_8_0= 'targetStartBlock' ) ) | ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )? (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:459:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:460:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:460:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:461:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage963); 

            		newLeafNode(otherlv_0, grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:472:2: ( (lv_transitionType_1_0= ruleTransitionType ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:473:1: (lv_transitionType_1_0= ruleTransitionType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:473:1: (lv_transitionType_1_0= ruleTransitionType )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:474:3: lv_transitionType_1_0= ruleTransitionType
            {
             
            	        newCompositeNode(grammarAccess.getTwoLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleTransitionType_in_ruleTwoLifelineMessage984);
            lv_transitionType_1_0=ruleTransitionType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTwoLifelineMessageRule());
            	        }
                   		set(
                   			current, 
                   			"transitionType",
                    		lv_transitionType_1_0, 
                    		"TransitionType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:490:2: ( (lv_caption_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:491:1: (lv_caption_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:491:1: (lv_caption_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:492:3: lv_caption_2_0= RULE_STRING
            {
            lv_caption_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1001); 

            			newLeafNode(lv_caption_2_0, grammarAccess.getTwoLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"caption",
                    		lv_caption_2_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleTwoLifelineMessage1018); 

                	newLeafNode(otherlv_3, grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:512:1: ( (otherlv_4= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:513:1: (otherlv_4= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:513:1: (otherlv_4= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:514:3: otherlv_4= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage1038); 

            		newLeafNode(otherlv_4, grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:525:2: ( ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) ) | ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt8=3;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==22) ) {
                alt8=1;
            }
            else if ( (LA8_0==23) ) {
                alt8=2;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:525:3: ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:525:3: ( (lv_startBlockLeft_5_0= 'sourceStartBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:526:1: (lv_startBlockLeft_5_0= 'sourceStartBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:526:1: (lv_startBlockLeft_5_0= 'sourceStartBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:527:3: lv_startBlockLeft_5_0= 'sourceStartBlock'
                    {
                    lv_startBlockLeft_5_0=(Token)match(input,22,FOLLOW_22_in_ruleTwoLifelineMessage1057); 

                            newLeafNode(lv_startBlockLeft_5_0, grammarAccess.getTwoLifelineMessageAccess().getStartBlockLeftSourceStartBlockKeyword_5_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startBlockLeft", true, "sourceStartBlock");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:541:6: ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:541:6: ( ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:541:7: ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) ) ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:541:7: ( (lv_endBlockLeft_6_0= 'sourceEndBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:542:1: (lv_endBlockLeft_6_0= 'sourceEndBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:542:1: (lv_endBlockLeft_6_0= 'sourceEndBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:543:3: lv_endBlockLeft_6_0= 'sourceEndBlock'
                    {
                    lv_endBlockLeft_6_0=(Token)match(input,23,FOLLOW_23_in_ruleTwoLifelineMessage1095); 

                            newLeafNode(lv_endBlockLeft_6_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftSourceEndBlockKeyword_5_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endBlockLeft", true, "sourceEndBlock");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:556:2: ( (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==RULE_INT_GREATER_ZERO) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:557:1: (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:557:1: (lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:558:3: lv_endBlockLeftCount_7_0= RULE_INT_GREATER_ZERO
                            {
                            lv_endBlockLeftCount_7_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1125); 

                            			newLeafNode(lv_endBlockLeftCount_7_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockLeftCountINT_GREATER_ZEROTerminalRuleCall_5_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endBlockLeftCount",
                                    		lv_endBlockLeftCount_7_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:574:6: ( ( (lv_startBlockRight_8_0= 'targetStartBlock' ) ) | ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt10=3;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==24) ) {
                alt10=1;
            }
            else if ( (LA10_0==25) ) {
                alt10=2;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:574:7: ( (lv_startBlockRight_8_0= 'targetStartBlock' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:574:7: ( (lv_startBlockRight_8_0= 'targetStartBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:575:1: (lv_startBlockRight_8_0= 'targetStartBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:575:1: (lv_startBlockRight_8_0= 'targetStartBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:576:3: lv_startBlockRight_8_0= 'targetStartBlock'
                    {
                    lv_startBlockRight_8_0=(Token)match(input,24,FOLLOW_24_in_ruleTwoLifelineMessage1153); 

                            newLeafNode(lv_startBlockRight_8_0, grammarAccess.getTwoLifelineMessageAccess().getStartBlockRightTargetStartBlockKeyword_6_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startBlockRight", true, "targetStartBlock");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:590:6: ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:590:6: ( ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:590:7: ( (lv_endBlockRight_9_0= 'targetEndBlock' ) ) ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:590:7: ( (lv_endBlockRight_9_0= 'targetEndBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:591:1: (lv_endBlockRight_9_0= 'targetEndBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:591:1: (lv_endBlockRight_9_0= 'targetEndBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:592:3: lv_endBlockRight_9_0= 'targetEndBlock'
                    {
                    lv_endBlockRight_9_0=(Token)match(input,25,FOLLOW_25_in_ruleTwoLifelineMessage1191); 

                            newLeafNode(lv_endBlockRight_9_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightTargetEndBlockKeyword_6_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endBlockRight", true, "targetEndBlock");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:605:2: ( (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO ) )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==RULE_INT_GREATER_ZERO) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:606:1: (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:606:1: (lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:607:3: lv_endBlockRightCount_10_0= RULE_INT_GREATER_ZERO
                            {
                            lv_endBlockRightCount_10_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1221); 

                            			newLeafNode(lv_endBlockRightCount_10_0, grammarAccess.getTwoLifelineMessageAccess().getEndBlockRightCountINT_GREATER_ZEROTerminalRuleCall_6_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endBlockRightCount",
                                    		lv_endBlockRightCount_10_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:623:6: (otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==26) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:623:8: otherlv_11= 'sourceNote' ( (lv_sourceNote_12_0= RULE_STRING ) )
                    {
                    otherlv_11=(Token)match(input,26,FOLLOW_26_in_ruleTwoLifelineMessage1243); 

                        	newLeafNode(otherlv_11, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:627:1: ( (lv_sourceNote_12_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:628:1: (lv_sourceNote_12_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:628:1: (lv_sourceNote_12_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:629:3: lv_sourceNote_12_0= RULE_STRING
                    {
                    lv_sourceNote_12_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1260); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:645:4: (otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==27) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:645:6: otherlv_13= 'targetNote' ( (lv_targetNote_14_0= RULE_STRING ) )
                    {
                    otherlv_13=(Token)match(input,27,FOLLOW_27_in_ruleTwoLifelineMessage1280); 

                        	newLeafNode(otherlv_13, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:649:1: ( (lv_targetNote_14_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:650:1: (lv_targetNote_14_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:650:1: (lv_targetNote_14_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:651:3: lv_targetNote_14_0= RULE_STRING
                    {
                    lv_targetNote_14_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1297); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:675:1: entryRuleOneLifelineMessage returns [EObject current=null] : iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF ;
    public final EObject entryRuleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:676:2: (iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:677:2: iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1340);
            iv_ruleOneLifelineMessage=ruleOneLifelineMessage();

            state._fsp--;

             current =iv_ruleOneLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineMessage1350); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:684:1: ruleOneLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) (otherlv_2= 'lost' | otherlv_3= 'found' ) ( (lv_caption_4_0= RULE_STRING ) ) ( ( (lv_startBlock_5_0= 'startBlock' ) ) | ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_caption_4_0=null;
        Token lv_startBlock_5_0=null;
        Token lv_endBlock_6_0=null;
        Token lv_endBlockCount_7_0=null;
        Token otherlv_8=null;
        Token lv_note_9_0=null;
        Enumerator lv_transitionType_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:687:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) (otherlv_2= 'lost' | otherlv_3= 'found' ) ( (lv_caption_4_0= RULE_STRING ) ) ( ( (lv_startBlock_5_0= 'startBlock' ) ) | ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:688:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) (otherlv_2= 'lost' | otherlv_3= 'found' ) ( (lv_caption_4_0= RULE_STRING ) ) ( ( (lv_startBlock_5_0= 'startBlock' ) ) | ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:688:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) (otherlv_2= 'lost' | otherlv_3= 'found' ) ( (lv_caption_4_0= RULE_STRING ) ) ( ( (lv_startBlock_5_0= 'startBlock' ) ) | ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:688:2: ( (otherlv_0= RULE_ID ) ) ( (lv_transitionType_1_0= ruleTransitionType ) ) (otherlv_2= 'lost' | otherlv_3= 'found' ) ( (lv_caption_4_0= RULE_STRING ) ) ( ( (lv_startBlock_5_0= 'startBlock' ) ) | ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:688:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:689:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:689:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:690:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineMessage1395); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:701:2: ( (lv_transitionType_1_0= ruleTransitionType ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:702:1: (lv_transitionType_1_0= ruleTransitionType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:702:1: (lv_transitionType_1_0= ruleTransitionType )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:703:3: lv_transitionType_1_0= ruleTransitionType
            {
             
            	        newCompositeNode(grammarAccess.getOneLifelineMessageAccess().getTransitionTypeTransitionTypeEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleTransitionType_in_ruleOneLifelineMessage1416);
            lv_transitionType_1_0=ruleTransitionType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOneLifelineMessageRule());
            	        }
                   		set(
                   			current, 
                   			"transitionType",
                    		lv_transitionType_1_0, 
                    		"TransitionType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:719:2: (otherlv_2= 'lost' | otherlv_3= 'found' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==28) ) {
                alt13=1;
            }
            else if ( (LA13_0==29) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:719:4: otherlv_2= 'lost'
                    {
                    otherlv_2=(Token)match(input,28,FOLLOW_28_in_ruleOneLifelineMessage1429); 

                        	newLeafNode(otherlv_2, grammarAccess.getOneLifelineMessageAccess().getLostKeyword_2_0());
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:724:7: otherlv_3= 'found'
                    {
                    otherlv_3=(Token)match(input,29,FOLLOW_29_in_ruleOneLifelineMessage1447); 

                        	newLeafNode(otherlv_3, grammarAccess.getOneLifelineMessageAccess().getFoundKeyword_2_1());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:728:2: ( (lv_caption_4_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:729:1: (lv_caption_4_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:729:1: (lv_caption_4_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:730:3: lv_caption_4_0= RULE_STRING
            {
            lv_caption_4_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1465); 

            			newLeafNode(lv_caption_4_0, grammarAccess.getOneLifelineMessageAccess().getCaptionSTRINGTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"caption",
                    		lv_caption_4_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:746:2: ( ( (lv_startBlock_5_0= 'startBlock' ) ) | ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt15=3;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==30) ) {
                alt15=1;
            }
            else if ( (LA15_0==31) ) {
                alt15=2;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:746:3: ( (lv_startBlock_5_0= 'startBlock' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:746:3: ( (lv_startBlock_5_0= 'startBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:747:1: (lv_startBlock_5_0= 'startBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:747:1: (lv_startBlock_5_0= 'startBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:748:3: lv_startBlock_5_0= 'startBlock'
                    {
                    lv_startBlock_5_0=(Token)match(input,30,FOLLOW_30_in_ruleOneLifelineMessage1489); 

                            newLeafNode(lv_startBlock_5_0, grammarAccess.getOneLifelineMessageAccess().getStartBlockStartBlockKeyword_4_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startBlock", true, "startBlock");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:762:6: ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:762:6: ( ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:762:7: ( (lv_endBlock_6_0= 'endBlock' ) ) ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:762:7: ( (lv_endBlock_6_0= 'endBlock' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:763:1: (lv_endBlock_6_0= 'endBlock' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:763:1: (lv_endBlock_6_0= 'endBlock' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:764:3: lv_endBlock_6_0= 'endBlock'
                    {
                    lv_endBlock_6_0=(Token)match(input,31,FOLLOW_31_in_ruleOneLifelineMessage1527); 

                            newLeafNode(lv_endBlock_6_0, grammarAccess.getOneLifelineMessageAccess().getEndBlockEndBlockKeyword_4_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endBlock", true, "endBlock");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:777:2: ( (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==RULE_INT_GREATER_ZERO) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:778:1: (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:778:1: (lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:779:3: lv_endBlockCount_7_0= RULE_INT_GREATER_ZERO
                            {
                            lv_endBlockCount_7_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineMessage1557); 

                            			newLeafNode(lv_endBlockCount_7_0, grammarAccess.getOneLifelineMessageAccess().getEndBlockCountINT_GREATER_ZEROTerminalRuleCall_4_1_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endBlockCount",
                                    		lv_endBlockCount_7_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:795:6: (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==32) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:795:8: otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,32,FOLLOW_32_in_ruleOneLifelineMessage1579); 

                        	newLeafNode(otherlv_8, grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:799:1: ( (lv_note_9_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:800:1: (lv_note_9_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:800:1: (lv_note_9_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:801:3: lv_note_9_0= RULE_STRING
                    {
                    lv_note_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1596); 

                    			newLeafNode(lv_note_9_0, grammarAccess.getOneLifelineMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"note",
                            		lv_note_9_0, 
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:825:1: entryRuleOneLifelineEndBlock returns [EObject current=null] : iv_ruleOneLifelineEndBlock= ruleOneLifelineEndBlock EOF ;
    public final EObject entryRuleOneLifelineEndBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineEndBlock = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:826:2: (iv_ruleOneLifelineEndBlock= ruleOneLifelineEndBlock EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:827:2: iv_ruleOneLifelineEndBlock= ruleOneLifelineEndBlock EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineEndBlockRule()); 
            pushFollow(FOLLOW_ruleOneLifelineEndBlock_in_entryRuleOneLifelineEndBlock1639);
            iv_ruleOneLifelineEndBlock=ruleOneLifelineEndBlock();

            state._fsp--;

             current =iv_ruleOneLifelineEndBlock; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineEndBlock1649); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:834:1: ruleOneLifelineEndBlock returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_endBlock_1_0= 'endBlock' ) ) ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? ) ;
    public final EObject ruleOneLifelineEndBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_endBlock_1_0=null;
        Token lv_endBlockCount_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:837:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_endBlock_1_0= 'endBlock' ) ) ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:838:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_endBlock_1_0= 'endBlock' ) ) ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:838:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_endBlock_1_0= 'endBlock' ) ) ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:838:2: ( (otherlv_0= RULE_ID ) ) ( (lv_endBlock_1_0= 'endBlock' ) ) ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:838:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:839:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:839:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:840:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineEndBlockRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineEndBlock1694); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineEndBlockAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:851:2: ( (lv_endBlock_1_0= 'endBlock' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:852:1: (lv_endBlock_1_0= 'endBlock' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:852:1: (lv_endBlock_1_0= 'endBlock' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:853:3: lv_endBlock_1_0= 'endBlock'
            {
            lv_endBlock_1_0=(Token)match(input,31,FOLLOW_31_in_ruleOneLifelineEndBlock1712); 

                    newLeafNode(lv_endBlock_1_0, grammarAccess.getOneLifelineEndBlockAccess().getEndBlockEndBlockKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineEndBlockRule());
            	        }
                   		setWithLastConsumed(current, "endBlock", true, "endBlock");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:866:2: ( (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_INT_GREATER_ZERO) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:867:1: (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:867:1: (lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:868:3: lv_endBlockCount_2_0= RULE_INT_GREATER_ZERO
                    {
                    lv_endBlockCount_2_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineEndBlock1742); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:892:1: entryRuleOneLifelineNote returns [EObject current=null] : iv_ruleOneLifelineNote= ruleOneLifelineNote EOF ;
    public final EObject entryRuleOneLifelineNote() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineNote = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:893:2: (iv_ruleOneLifelineNote= ruleOneLifelineNote EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:894:2: iv_ruleOneLifelineNote= ruleOneLifelineNote EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineNoteRule()); 
            pushFollow(FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote1784);
            iv_ruleOneLifelineNote=ruleOneLifelineNote();

            state._fsp--;

             current =iv_ruleOneLifelineNote; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineNote1794); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:901:1: ruleOneLifelineNote returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleOneLifelineNote() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_note_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:904:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:905:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:905:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:905:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:905:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:906:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:906:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:907:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineNoteRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineNote1839); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            otherlv_1=(Token)match(input,32,FOLLOW_32_in_ruleOneLifelineNote1851); 

                	newLeafNode(otherlv_1, grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:922:1: ( (lv_note_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:923:1: (lv_note_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:923:1: (lv_note_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:924:3: lv_note_2_0= RULE_STRING
            {
            lv_note_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineNote1868); 

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


    // $ANTLR start "entryRuleDestroy"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:948:1: entryRuleDestroy returns [EObject current=null] : iv_ruleDestroy= ruleDestroy EOF ;
    public final EObject entryRuleDestroy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDestroy = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:949:2: (iv_ruleDestroy= ruleDestroy EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:950:2: iv_ruleDestroy= ruleDestroy EOF
            {
             newCompositeNode(grammarAccess.getDestroyRule()); 
            pushFollow(FOLLOW_ruleDestroy_in_entryRuleDestroy1909);
            iv_ruleDestroy=ruleDestroy();

            state._fsp--;

             current =iv_ruleDestroy; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroy1919); 

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
    // $ANTLR end "entryRuleDestroy"


    // $ANTLR start "ruleDestroy"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:957:1: ruleDestroy returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_destroy_1_0= 'destroy' ) ) ) ;
    public final EObject ruleDestroy() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_destroy_1_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:960:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_destroy_1_0= 'destroy' ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:961:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_destroy_1_0= 'destroy' ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:961:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_destroy_1_0= 'destroy' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:961:2: ( (otherlv_0= RULE_ID ) ) ( (lv_destroy_1_0= 'destroy' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:961:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:962:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:962:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:963:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDestroyRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDestroy1964); 

            		newLeafNode(otherlv_0, grammarAccess.getDestroyAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:974:2: ( (lv_destroy_1_0= 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:975:1: (lv_destroy_1_0= 'destroy' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:975:1: (lv_destroy_1_0= 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:976:3: lv_destroy_1_0= 'destroy'
            {
            lv_destroy_1_0=(Token)match(input,33,FOLLOW_33_in_ruleDestroy1982); 

                    newLeafNode(lv_destroy_1_0, grammarAccess.getDestroyAccess().getDestroyDestroyKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDestroyRule());
            	        }
                   		setWithLastConsumed(current, "destroy", true, "destroy");
            	    

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
    // $ANTLR end "ruleDestroy"


    // $ANTLR start "entryRuleFragment"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:997:1: entryRuleFragment returns [EObject current=null] : iv_ruleFragment= ruleFragment EOF ;
    public final EObject entryRuleFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFragment = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:998:2: (iv_ruleFragment= ruleFragment EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:999:2: iv_ruleFragment= ruleFragment EOF
            {
             newCompositeNode(grammarAccess.getFragmentRule()); 
            pushFollow(FOLLOW_ruleFragment_in_entryRuleFragment2031);
            iv_ruleFragment=ruleFragment();

            state._fsp--;

             current =iv_ruleFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragment2041); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1006:1: ruleFragment returns [EObject current=null] : (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_fragmentContents_2_0= ruleFragmentContent ) ) ( (lv_fragmentContents_3_0= ruleFragmentContent ) )* ) ;
    public final EObject ruleFragment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        EObject lv_fragmentContents_2_0 = null;

        EObject lv_fragmentContents_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1009:28: ( (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_fragmentContents_2_0= ruleFragmentContent ) ) ( (lv_fragmentContents_3_0= ruleFragmentContent ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1010:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_fragmentContents_2_0= ruleFragmentContent ) ) ( (lv_fragmentContents_3_0= ruleFragmentContent ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1010:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_fragmentContents_2_0= ruleFragmentContent ) ) ( (lv_fragmentContents_3_0= ruleFragmentContent ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1010:3: otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_fragmentContents_2_0= ruleFragmentContent ) ) ( (lv_fragmentContents_3_0= ruleFragmentContent ) )*
            {
            otherlv_0=(Token)match(input,34,FOLLOW_34_in_ruleFragment2078); 

                	newLeafNode(otherlv_0, grammarAccess.getFragmentAccess().getFragmentKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1014:1: ( (lv_name_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1015:1: (lv_name_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1015:1: (lv_name_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1016:3: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFragment2095); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1032:2: ( (lv_fragmentContents_2_0= ruleFragmentContent ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1033:1: (lv_fragmentContents_2_0= ruleFragmentContent )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1033:1: (lv_fragmentContents_2_0= ruleFragmentContent )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1034:3: lv_fragmentContents_2_0= ruleFragmentContent
            {
             
            	        newCompositeNode(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleFragmentContent_in_ruleFragment2121);
            lv_fragmentContents_2_0=ruleFragmentContent();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFragmentRule());
            	        }
                   		add(
                   			current, 
                   			"fragmentContents",
                    		lv_fragmentContents_2_0, 
                    		"FragmentContent");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1050:2: ( (lv_fragmentContents_3_0= ruleFragmentContent ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==13) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1051:1: (lv_fragmentContents_3_0= ruleFragmentContent )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1051:1: (lv_fragmentContents_3_0= ruleFragmentContent )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1052:3: lv_fragmentContents_3_0= ruleFragmentContent
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFragmentAccess().getFragmentContentsFragmentContentParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleFragmentContent_in_ruleFragment2142);
            	    lv_fragmentContents_3_0=ruleFragmentContent();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFragmentRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"fragmentContents",
            	            		lv_fragmentContents_3_0, 
            	            		"FragmentContent");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
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


    // $ANTLR start "entryRuleFragmentContent"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1076:1: entryRuleFragmentContent returns [EObject current=null] : iv_ruleFragmentContent= ruleFragmentContent EOF ;
    public final EObject entryRuleFragmentContent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFragmentContent = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1077:2: (iv_ruleFragmentContent= ruleFragmentContent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1078:2: iv_ruleFragmentContent= ruleFragmentContent EOF
            {
             newCompositeNode(grammarAccess.getFragmentContentRule()); 
            pushFollow(FOLLOW_ruleFragmentContent_in_entryRuleFragmentContent2179);
            iv_ruleFragmentContent=ruleFragmentContent();

            state._fsp--;

             current =iv_ruleFragmentContent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragmentContent2189); 

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
    // $ANTLR end "entryRuleFragmentContent"


    // $ANTLR start "ruleFragmentContent"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1085:1: ruleFragmentContent returns [EObject current=null] : (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) ;
    public final EObject ruleFragmentContent() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_label_2_0=null;
        Token otherlv_5=null;
        EObject lv_interactions_3_0 = null;

        EObject lv_interactions_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1088:28: ( (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1089:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1089:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1089:3: otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_13_in_ruleFragmentContent2226); 

                	newLeafNode(otherlv_0, grammarAccess.getFragmentContentAccess().getLeftCurlyBracketKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1093:1: (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==35) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1093:3: otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,35,FOLLOW_35_in_ruleFragmentContent2239); 

                        	newLeafNode(otherlv_1, grammarAccess.getFragmentContentAccess().getLabelKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1097:1: ( (lv_label_2_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1098:1: (lv_label_2_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1098:1: (lv_label_2_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1099:3: lv_label_2_0= RULE_STRING
                    {
                    lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFragmentContent2256); 

                    			newLeafNode(lv_label_2_0, grammarAccess.getFragmentContentAccess().getLabelSTRINGTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getFragmentContentRule());
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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1115:4: ( (lv_interactions_3_0= ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1116:1: (lv_interactions_3_0= ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1116:1: (lv_interactions_3_0= ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1117:3: lv_interactions_3_0= ruleInteraction
            {
             
            	        newCompositeNode(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleInteraction_in_ruleFragmentContent2284);
            lv_interactions_3_0=ruleInteraction();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFragmentContentRule());
            	        }
                   		add(
                   			current, 
                   			"interactions",
                    		lv_interactions_3_0, 
                    		"Interaction");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1133:2: ( (lv_interactions_4_0= ruleInteraction ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID||LA20_0==34||LA20_0==36) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1134:1: (lv_interactions_4_0= ruleInteraction )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1134:1: (lv_interactions_4_0= ruleInteraction )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1135:3: lv_interactions_4_0= ruleInteraction
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFragmentContentAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInteraction_in_ruleFragmentContent2305);
            	    lv_interactions_4_0=ruleInteraction();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFragmentContentRule());
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
            	    break loop20;
                }
            } while (true);

            otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleFragmentContent2318); 

                	newLeafNode(otherlv_5, grammarAccess.getFragmentContentAccess().getRightCurlyBracketKeyword_4());
                

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
    // $ANTLR end "ruleFragmentContent"


    // $ANTLR start "entryRuleRefinement"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1163:1: entryRuleRefinement returns [EObject current=null] : iv_ruleRefinement= ruleRefinement EOF ;
    public final EObject entryRuleRefinement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRefinement = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1164:2: (iv_ruleRefinement= ruleRefinement EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1165:2: iv_ruleRefinement= ruleRefinement EOF
            {
             newCompositeNode(grammarAccess.getRefinementRule()); 
            pushFollow(FOLLOW_ruleRefinement_in_entryRuleRefinement2354);
            iv_ruleRefinement=ruleRefinement();

            state._fsp--;

             current =iv_ruleRefinement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRefinement2364); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1172:1: ruleRefinement returns [EObject current=null] : (otherlv_0= 'refinement' otherlv_1= '{' otherlv_2= 'lifelines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) otherlv_8= '}' ) ;
    public final EObject ruleRefinement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_label_7_0=null;
        Token otherlv_8=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1175:28: ( (otherlv_0= 'refinement' otherlv_1= '{' otherlv_2= 'lifelines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) otherlv_8= '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1176:1: (otherlv_0= 'refinement' otherlv_1= '{' otherlv_2= 'lifelines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) otherlv_8= '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1176:1: (otherlv_0= 'refinement' otherlv_1= '{' otherlv_2= 'lifelines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) otherlv_8= '}' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1176:3: otherlv_0= 'refinement' otherlv_1= '{' otherlv_2= 'lifelines' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,36,FOLLOW_36_in_ruleRefinement2401); 

                	newLeafNode(otherlv_0, grammarAccess.getRefinementAccess().getRefinementKeyword_0());
                
            otherlv_1=(Token)match(input,13,FOLLOW_13_in_ruleRefinement2413); 

                	newLeafNode(otherlv_1, grammarAccess.getRefinementAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,37,FOLLOW_37_in_ruleRefinement2425); 

                	newLeafNode(otherlv_2, grammarAccess.getRefinementAccess().getLifelinesKeyword_2());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1188:1: ( (otherlv_3= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1189:1: (otherlv_3= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1189:1: (otherlv_3= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1190:3: otherlv_3= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getRefinementRule());
            	        }
                    
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2445); 

            		newLeafNode(otherlv_3, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_3_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1201:2: (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==38) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1201:4: otherlv_4= ',' ( (otherlv_5= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,38,FOLLOW_38_in_ruleRefinement2458); 

            	        	newLeafNode(otherlv_4, grammarAccess.getRefinementAccess().getCommaKeyword_4_0());
            	        
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1205:1: ( (otherlv_5= RULE_ID ) )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1206:1: (otherlv_5= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1206:1: (otherlv_5= RULE_ID )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1207:3: otherlv_5= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getRefinementRule());
            	    	        }
            	            
            	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2478); 

            	    		newLeafNode(otherlv_5, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_1_0()); 
            	    	

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            otherlv_6=(Token)match(input,35,FOLLOW_35_in_ruleRefinement2492); 

                	newLeafNode(otherlv_6, grammarAccess.getRefinementAccess().getLabelKeyword_5());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1222:1: ( (lv_label_7_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1223:1: (lv_label_7_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1223:1: (lv_label_7_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1224:3: lv_label_7_0= RULE_STRING
            {
            lv_label_7_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleRefinement2509); 

            			newLeafNode(lv_label_7_0, grammarAccess.getRefinementAccess().getLabelSTRINGTerminalRuleCall_6_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRefinementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_7_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_8=(Token)match(input,14,FOLLOW_14_in_ruleRefinement2526); 

                	newLeafNode(otherlv_8, grammarAccess.getRefinementAccess().getRightCurlyBracketKeyword_7());
                

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


    // $ANTLR start "ruleTransitionType"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1252:1: ruleTransitionType returns [Enumerator current=null] : ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) ) ;
    public final Enumerator ruleTransitionType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1254:28: ( ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1255:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1255:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) )
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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1255:2: (enumLiteral_0= 'async' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1255:2: (enumLiteral_0= 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1255:4: enumLiteral_0= 'async'
                    {
                    enumLiteral_0=(Token)match(input,39,FOLLOW_39_in_ruleTransitionType2576); 

                            current = grammarAccess.getTransitionTypeAccess().getAsyncEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getTransitionTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1261:6: (enumLiteral_1= 'create' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1261:6: (enumLiteral_1= 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1261:8: enumLiteral_1= 'create'
                    {
                    enumLiteral_1=(Token)match(input,40,FOLLOW_40_in_ruleTransitionType2593); 

                            current = grammarAccess.getTransitionTypeAccess().getCreateEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getTransitionTypeAccess().getCreateEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1267:6: (enumLiteral_2= 'response' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1267:6: (enumLiteral_2= 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1267:8: enumLiteral_2= 'response'
                    {
                    enumLiteral_2=(Token)match(input,41,FOLLOW_41_in_ruleTransitionType2610); 

                            current = grammarAccess.getTransitionTypeAccess().getResponseEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getTransitionTypeAccess().getResponseEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1273:6: (enumLiteral_3= 'sync' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1273:6: (enumLiteral_3= 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1273:8: enumLiteral_3= 'sync'
                    {
                    enumLiteral_3=(Token)match(input,42,FOLLOW_42_in_ruleTransitionType2627); 

                            current = grammarAccess.getTransitionTypeAccess().getSyncEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getTransitionTypeAccess().getSyncEnumLiteralDeclaration_3()); 
                        

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
    // $ANTLR end "ruleTransitionType"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\15\uffff";
    static final String DFA6_eofS =
        "\15\uffff";
    static final String DFA6_minS =
        "\1\5\1\37\4\uffff\4\4\3\uffff";
    static final String DFA6_maxS =
        "\1\44\1\52\4\uffff\4\35\3\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\3\1\7\1\5\1\4\4\uffff\1\6\1\1\1\2";
    static final String DFA6_specialS =
        "\15\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1\34\uffff\1\2\1\uffff\1\3",
            "\1\5\1\4\1\12\5\uffff\1\6\1\7\1\10\1\11",
            "",
            "",
            "",
            "",
            "\1\13\27\uffff\2\14",
            "\1\13\27\uffff\2\14",
            "\1\13\27\uffff\2\14",
            "\1\13\27\uffff\2\14",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "369:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineEndBlock_3= ruleOneLifelineEndBlock | this_OneLifelineNote_4= ruleOneLifelineNote | this_Destroy_5= ruleDestroy | this_Refinement_6= ruleRefinement )";
        }
    }
 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleSequenceDiagram131 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSequenceDiagram148 = new BitSet(new long[]{0x0000001400082022L});
    public static final BitSet FOLLOW_13_in_ruleSequenceDiagram166 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_ruleSequenceDiagram187 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_14_in_ruleSequenceDiagram200 = new BitSet(new long[]{0x0000001400080022L});
    public static final BitSet FOLLOW_ruleLifeline_in_ruleSequenceDiagram223 = new BitSet(new long[]{0x0000001400080022L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSequenceDiagram245 = new BitSet(new long[]{0x0000001400000022L});
    public static final BitSet FOLLOW_ruleLocalVariable_in_entryRuleLocalVariable282 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalVariable292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleLocalVariable330 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLocalVariable347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleLocalVariable372 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLocalVariable389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleLocalVariable414 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLocalVariable431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleLocalVariable456 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLocalVariable473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLifeline_in_entryRuleLifeline515 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLifeline525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleLifeline562 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLifeline579 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleLifeline596 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLifeline613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_entryRuleInteraction654 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInteraction664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_ruleInteraction711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_ruleInteraction738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_ruleInteraction765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineEndBlock_in_ruleInteraction792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_ruleInteraction819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroy_in_ruleInteraction846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_ruleInteraction873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage908 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTwoLifelineMessage918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage963 = new BitSet(new long[]{0x0000078000000000L});
    public static final BitSet FOLLOW_ruleTransitionType_in_ruleTwoLifelineMessage984 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1001 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleTwoLifelineMessage1018 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage1038 = new BitSet(new long[]{0x000000000FC00002L});
    public static final BitSet FOLLOW_22_in_ruleTwoLifelineMessage1057 = new BitSet(new long[]{0x000000000F000002L});
    public static final BitSet FOLLOW_23_in_ruleTwoLifelineMessage1095 = new BitSet(new long[]{0x000000000F000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1125 = new BitSet(new long[]{0x000000000F000002L});
    public static final BitSet FOLLOW_24_in_ruleTwoLifelineMessage1153 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_25_in_ruleTwoLifelineMessage1191 = new BitSet(new long[]{0x000000000C000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1221 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_26_in_ruleTwoLifelineMessage1243 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1260 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_ruleTwoLifelineMessage1280 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1340 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineMessage1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineMessage1395 = new BitSet(new long[]{0x0000078000000000L});
    public static final BitSet FOLLOW_ruleTransitionType_in_ruleOneLifelineMessage1416 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_28_in_ruleOneLifelineMessage1429 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_29_in_ruleOneLifelineMessage1447 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1465 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_30_in_ruleOneLifelineMessage1489 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_31_in_ruleOneLifelineMessage1527 = new BitSet(new long[]{0x0000000100000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineMessage1557 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_ruleOneLifelineMessage1579 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineEndBlock_in_entryRuleOneLifelineEndBlock1639 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineEndBlock1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineEndBlock1694 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleOneLifelineEndBlock1712 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineEndBlock1742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote1784 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineNote1794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineNote1839 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleOneLifelineNote1851 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineNote1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroy_in_entryRuleDestroy1909 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroy1919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDestroy1964 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleDestroy1982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment2031 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleFragment2078 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFragment2095 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleFragmentContent_in_ruleFragment2121 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ruleFragmentContent_in_ruleFragment2142 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ruleFragmentContent_in_entryRuleFragmentContent2179 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragmentContent2189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleFragmentContent2226 = new BitSet(new long[]{0x0000001C00000020L});
    public static final BitSet FOLLOW_35_in_ruleFragmentContent2239 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFragmentContent2256 = new BitSet(new long[]{0x0000001400000020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleFragmentContent2284 = new BitSet(new long[]{0x0000001400004020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleFragmentContent2305 = new BitSet(new long[]{0x0000001400004020L});
    public static final BitSet FOLLOW_14_in_ruleFragmentContent2318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement2354 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement2364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleRefinement2401 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleRefinement2413 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_ruleRefinement2425 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2445 = new BitSet(new long[]{0x0000004800000000L});
    public static final BitSet FOLLOW_38_in_ruleRefinement2458 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2478 = new BitSet(new long[]{0x0000004800000000L});
    public static final BitSet FOLLOW_35_in_ruleRefinement2492 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleRefinement2509 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleRefinement2526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleTransitionType2576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleTransitionType2593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleTransitionType2610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleTransitionType2627 = new BitSet(new long[]{0x0000000000000002L});

}