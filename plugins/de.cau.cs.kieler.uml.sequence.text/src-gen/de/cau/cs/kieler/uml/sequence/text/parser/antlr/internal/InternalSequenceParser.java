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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'sequenceDiagram'", "'lifeline'", "'as'", "'usecase'", "'to'", "'sourceStartEndExec'", "'sourceStartExec'", "'sourceEndExec'", "'targetStartEndExec'", "'targetStartExec'", "'targetEndExec'", "'sourceNote'", "'targetNote'", "'lost'", "'found'", "'startEndExec'", "'startExec'", "'endExec'", "'note'", "'sync'", "'async'", "'response'", "'self'", "'destroy'", "'fragment'", "'{'", "'label'", "'}'", "'refinement'", "'lifelines'", "','", "'create'"
    };
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
    public static final int T__29=29;
    public static final int RULE_INT_GREATER_ZERO=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:77:1: ruleSequenceDiagram returns [EObject current=null] : ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) ( (lv_lifelines_3_0= ruleLifeline ) )* ( (lv_interactions_4_0= ruleInteraction ) )* ) ;
    public final EObject ruleSequenceDiagram() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_diagramName_2_0=null;
        EObject lv_lifelines_3_0 = null;

        EObject lv_interactions_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:80:28: ( ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) ( (lv_lifelines_3_0= ruleLifeline ) )* ( (lv_interactions_4_0= ruleInteraction ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:1: ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) ( (lv_lifelines_3_0= ruleLifeline ) )* ( (lv_interactions_4_0= ruleInteraction ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:1: ( () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) ( (lv_lifelines_3_0= ruleLifeline ) )* ( (lv_interactions_4_0= ruleInteraction ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:81:2: () otherlv_1= 'sequenceDiagram' ( (lv_diagramName_2_0= RULE_STRING ) ) ( (lv_lifelines_3_0= ruleLifeline ) )* ( (lv_interactions_4_0= ruleInteraction ) )*
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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:109:2: ( (lv_lifelines_3_0= ruleLifeline ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13||LA1_0==15) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:110:1: (lv_lifelines_3_0= ruleLifeline )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:110:1: (lv_lifelines_3_0= ruleLifeline )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:111:3: lv_lifelines_3_0= ruleLifeline
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getLifelinesLifelineParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLifeline_in_ruleSequenceDiagram174);
            	    lv_lifelines_3_0=ruleLifeline();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"lifelines",
            	            		lv_lifelines_3_0, 
            	            		"Lifeline");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:127:3: ( (lv_interactions_4_0= ruleInteraction ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID||LA2_0==36||LA2_0==40) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:128:1: (lv_interactions_4_0= ruleInteraction )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:128:1: (lv_interactions_4_0= ruleInteraction )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:129:3: lv_interactions_4_0= ruleInteraction
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSequenceDiagramAccess().getInteractionsInteractionParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInteraction_in_ruleSequenceDiagram196);
            	    lv_interactions_4_0=ruleInteraction();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSequenceDiagramRule());
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
            	    break loop2;
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


    // $ANTLR start "entryRuleLifeline"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:153:1: entryRuleLifeline returns [EObject current=null] : iv_ruleLifeline= ruleLifeline EOF ;
    public final EObject entryRuleLifeline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLifeline = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:154:2: (iv_ruleLifeline= ruleLifeline EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:155:2: iv_ruleLifeline= ruleLifeline EOF
            {
             newCompositeNode(grammarAccess.getLifelineRule()); 
            pushFollow(FOLLOW_ruleLifeline_in_entryRuleLifeline233);
            iv_ruleLifeline=ruleLifeline();

            state._fsp--;

             current =iv_ruleLifeline; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLifeline243); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:162:1: ruleLifeline returns [EObject current=null] : ( (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'usecase' ( (lv_usecaseCaption_5_0= RULE_STRING ) ) otherlv_6= 'as' ( (lv_name_7_0= RULE_ID ) ) ) ) ;
    public final EObject ruleLifeline() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_caption_1_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token lv_usecaseCaption_5_0=null;
        Token otherlv_6=null;
        Token lv_name_7_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:165:28: ( ( (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'usecase' ( (lv_usecaseCaption_5_0= RULE_STRING ) ) otherlv_6= 'as' ( (lv_name_7_0= RULE_ID ) ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:166:1: ( (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'usecase' ( (lv_usecaseCaption_5_0= RULE_STRING ) ) otherlv_6= 'as' ( (lv_name_7_0= RULE_ID ) ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:166:1: ( (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) ) | (otherlv_4= 'usecase' ( (lv_usecaseCaption_5_0= RULE_STRING ) ) otherlv_6= 'as' ( (lv_name_7_0= RULE_ID ) ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==13) ) {
                alt3=1;
            }
            else if ( (LA3_0==15) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:166:2: (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:166:2: (otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:166:4: otherlv_0= 'lifeline' ( (lv_caption_1_0= RULE_STRING ) ) otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) )
                    {
                    otherlv_0=(Token)match(input,13,FOLLOW_13_in_ruleLifeline281); 

                        	newLeafNode(otherlv_0, grammarAccess.getLifelineAccess().getLifelineKeyword_0_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:170:1: ( (lv_caption_1_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:171:1: (lv_caption_1_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:171:1: (lv_caption_1_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:172:3: lv_caption_1_0= RULE_STRING
                    {
                    lv_caption_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLifeline298); 

                    			newLeafNode(lv_caption_1_0, grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_0_1_0()); 
                    		

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

                    otherlv_2=(Token)match(input,14,FOLLOW_14_in_ruleLifeline315); 

                        	newLeafNode(otherlv_2, grammarAccess.getLifelineAccess().getAsKeyword_0_2());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:192:1: ( (lv_name_3_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:193:1: (lv_name_3_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:193:1: (lv_name_3_0= RULE_ID )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:194:3: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLifeline332); 

                    			newLeafNode(lv_name_3_0, grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_0_3_0()); 
                    		

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
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:211:6: (otherlv_4= 'usecase' ( (lv_usecaseCaption_5_0= RULE_STRING ) ) otherlv_6= 'as' ( (lv_name_7_0= RULE_ID ) ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:211:6: (otherlv_4= 'usecase' ( (lv_usecaseCaption_5_0= RULE_STRING ) ) otherlv_6= 'as' ( (lv_name_7_0= RULE_ID ) ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:211:8: otherlv_4= 'usecase' ( (lv_usecaseCaption_5_0= RULE_STRING ) ) otherlv_6= 'as' ( (lv_name_7_0= RULE_ID ) )
                    {
                    otherlv_4=(Token)match(input,15,FOLLOW_15_in_ruleLifeline357); 

                        	newLeafNode(otherlv_4, grammarAccess.getLifelineAccess().getUsecaseKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:215:1: ( (lv_usecaseCaption_5_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:216:1: (lv_usecaseCaption_5_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:216:1: (lv_usecaseCaption_5_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:217:3: lv_usecaseCaption_5_0= RULE_STRING
                    {
                    lv_usecaseCaption_5_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLifeline374); 

                    			newLeafNode(lv_usecaseCaption_5_0, grammarAccess.getLifelineAccess().getUsecaseCaptionSTRINGTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLifelineRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"usecaseCaption",
                            		lv_usecaseCaption_5_0, 
                            		"STRING");
                    	    

                    }


                    }

                    otherlv_6=(Token)match(input,14,FOLLOW_14_in_ruleLifeline391); 

                        	newLeafNode(otherlv_6, grammarAccess.getLifelineAccess().getAsKeyword_1_2());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:237:1: ( (lv_name_7_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:238:1: (lv_name_7_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:238:1: (lv_name_7_0= RULE_ID )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:239:3: lv_name_7_0= RULE_ID
                    {
                    lv_name_7_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLifeline408); 

                    			newLeafNode(lv_name_7_0, grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_1_3_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLifelineRule());
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
    // $ANTLR end "ruleLifeline"


    // $ANTLR start "entryRuleInteraction"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:263:1: entryRuleInteraction returns [EObject current=null] : iv_ruleInteraction= ruleInteraction EOF ;
    public final EObject entryRuleInteraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInteraction = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:264:2: (iv_ruleInteraction= ruleInteraction EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:265:2: iv_ruleInteraction= ruleInteraction EOF
            {
             newCompositeNode(grammarAccess.getInteractionRule()); 
            pushFollow(FOLLOW_ruleInteraction_in_entryRuleInteraction450);
            iv_ruleInteraction=ruleInteraction();

            state._fsp--;

             current =iv_ruleInteraction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInteraction460); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:272:1: ruleInteraction returns [EObject current=null] : (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineNote_3= ruleOneLifelineNote | this_SelfMessage_4= ruleSelfMessage | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement ) ;
    public final EObject ruleInteraction() throws RecognitionException {
        EObject current = null;

        EObject this_TwoLifelineMessage_0 = null;

        EObject this_OneLifelineMessage_1 = null;

        EObject this_Fragment_2 = null;

        EObject this_OneLifelineNote_3 = null;

        EObject this_SelfMessage_4 = null;

        EObject this_DestroyLifelineEvent_5 = null;

        EObject this_Refinement_6 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:275:28: ( (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineNote_3= ruleOneLifelineNote | this_SelfMessage_4= ruleSelfMessage | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:276:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineNote_3= ruleOneLifelineNote | this_SelfMessage_4= ruleSelfMessage | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:276:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineNote_3= ruleOneLifelineNote | this_SelfMessage_4= ruleSelfMessage | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement )
            int alt4=7;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:277:5: this_TwoLifelineMessage_0= ruleTwoLifelineMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getTwoLifelineMessageParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleTwoLifelineMessage_in_ruleInteraction507);
                    this_TwoLifelineMessage_0=ruleTwoLifelineMessage();

                    state._fsp--;

                     
                            current = this_TwoLifelineMessage_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:287:5: this_OneLifelineMessage_1= ruleOneLifelineMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineMessageParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineMessage_in_ruleInteraction534);
                    this_OneLifelineMessage_1=ruleOneLifelineMessage();

                    state._fsp--;

                     
                            current = this_OneLifelineMessage_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:297:5: this_Fragment_2= ruleFragment
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getFragmentParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleFragment_in_ruleInteraction561);
                    this_Fragment_2=ruleFragment();

                    state._fsp--;

                     
                            current = this_Fragment_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:307:5: this_OneLifelineNote_3= ruleOneLifelineNote
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getOneLifelineNoteParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleOneLifelineNote_in_ruleInteraction588);
                    this_OneLifelineNote_3=ruleOneLifelineNote();

                    state._fsp--;

                     
                            current = this_OneLifelineNote_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:317:5: this_SelfMessage_4= ruleSelfMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getSelfMessageParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleSelfMessage_in_ruleInteraction615);
                    this_SelfMessage_4=ruleSelfMessage();

                    state._fsp--;

                     
                            current = this_SelfMessage_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:327:5: this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_ruleInteraction642);
                    this_DestroyLifelineEvent_5=ruleDestroyLifelineEvent();

                    state._fsp--;

                     
                            current = this_DestroyLifelineEvent_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:337:5: this_Refinement_6= ruleRefinement
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleRefinement_in_ruleInteraction669);
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:353:1: entryRuleTwoLifelineMessage returns [EObject current=null] : iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF ;
    public final EObject entryRuleTwoLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTwoLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:354:2: (iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:355:2: iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getTwoLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage704);
            iv_ruleTwoLifelineMessage=ruleTwoLifelineMessage();

            state._fsp--;

             current =iv_ruleTwoLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTwoLifelineMessage714); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:362:1: ruleTwoLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleTwoLifelineMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_message_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_sourceStartEndExec_5_0=null;
        Token lv_sourceStartExec_6_0=null;
        Token lv_sourceEndExec_7_0=null;
        Token lv_sourceEndExecCount_8_0=null;
        Token lv_targetStartEndExec_9_0=null;
        Token lv_targetStartExec_10_0=null;
        Token lv_targetEndExec_11_0=null;
        Token lv_targetEndExecCount_12_0=null;
        Token otherlv_13=null;
        Token lv_sourceNote_14_0=null;
        Token otherlv_15=null;
        Token lv_targetNote_16_0=null;
        Enumerator lv_messageType_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:365:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:366:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:366:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:366:2: ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:366:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:367:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:367:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:368:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage759); 

            		newLeafNode(otherlv_0, grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:379:2: ( (lv_messageType_1_0= ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:380:1: (lv_messageType_1_0= ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:380:1: (lv_messageType_1_0= ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:381:3: lv_messageType_1_0= ruleMessageType
            {
             
            	        newCompositeNode(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleMessageType_in_ruleTwoLifelineMessage780);
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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:397:2: ( (lv_message_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:398:1: (lv_message_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:398:1: (lv_message_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:399:3: lv_message_2_0= RULE_STRING
            {
            lv_message_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage797); 

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

            otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleTwoLifelineMessage814); 

                	newLeafNode(otherlv_3, grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:419:1: ( (otherlv_4= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:420:1: (otherlv_4= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:420:1: (otherlv_4= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:421:3: otherlv_4= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage834); 

            		newLeafNode(otherlv_4, grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:432:2: ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt6=4;
            switch ( input.LA(1) ) {
                case 17:
                    {
                    alt6=1;
                    }
                    break;
                case 18:
                    {
                    alt6=2;
                    }
                    break;
                case 19:
                    {
                    alt6=3;
                    }
                    break;
            }

            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:432:3: ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:432:3: ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:433:1: (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:433:1: (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:434:3: lv_sourceStartEndExec_5_0= 'sourceStartEndExec'
                    {
                    lv_sourceStartEndExec_5_0=(Token)match(input,17,FOLLOW_17_in_ruleTwoLifelineMessage853); 

                            newLeafNode(lv_sourceStartEndExec_5_0, grammarAccess.getTwoLifelineMessageAccess().getSourceStartEndExecSourceStartEndExecKeyword_5_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "sourceStartEndExec", true, "sourceStartEndExec");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:448:6: ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:448:6: ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:449:1: (lv_sourceStartExec_6_0= 'sourceStartExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:449:1: (lv_sourceStartExec_6_0= 'sourceStartExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:450:3: lv_sourceStartExec_6_0= 'sourceStartExec'
                    {
                    lv_sourceStartExec_6_0=(Token)match(input,18,FOLLOW_18_in_ruleTwoLifelineMessage890); 

                            newLeafNode(lv_sourceStartExec_6_0, grammarAccess.getTwoLifelineMessageAccess().getSourceStartExecSourceStartExecKeyword_5_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "sourceStartExec", true, "sourceStartExec");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:464:6: ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:464:6: ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:464:7: ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:464:7: ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:465:1: (lv_sourceEndExec_7_0= 'sourceEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:465:1: (lv_sourceEndExec_7_0= 'sourceEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:466:3: lv_sourceEndExec_7_0= 'sourceEndExec'
                    {
                    lv_sourceEndExec_7_0=(Token)match(input,19,FOLLOW_19_in_ruleTwoLifelineMessage928); 

                            newLeafNode(lv_sourceEndExec_7_0, grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecSourceEndExecKeyword_5_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "sourceEndExec", true, "sourceEndExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:479:2: ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO ) )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==RULE_INT_GREATER_ZERO) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:480:1: (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:480:1: (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:481:3: lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO
                            {
                            lv_sourceEndExecCount_8_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage958); 

                            			newLeafNode(lv_sourceEndExecCount_8_0, grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecCountINT_GREATER_ZEROTerminalRuleCall_5_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"sourceEndExecCount",
                                    		lv_sourceEndExecCount_8_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:497:6: ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt8=4;
            switch ( input.LA(1) ) {
                case 20:
                    {
                    alt8=1;
                    }
                    break;
                case 21:
                    {
                    alt8=2;
                    }
                    break;
                case 22:
                    {
                    alt8=3;
                    }
                    break;
            }

            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:497:7: ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:497:7: ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:498:1: (lv_targetStartEndExec_9_0= 'targetStartEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:498:1: (lv_targetStartEndExec_9_0= 'targetStartEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:499:3: lv_targetStartEndExec_9_0= 'targetStartEndExec'
                    {
                    lv_targetStartEndExec_9_0=(Token)match(input,20,FOLLOW_20_in_ruleTwoLifelineMessage986); 

                            newLeafNode(lv_targetStartEndExec_9_0, grammarAccess.getTwoLifelineMessageAccess().getTargetStartEndExecTargetStartEndExecKeyword_6_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "targetStartEndExec", true, "targetStartEndExec");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:513:6: ( (lv_targetStartExec_10_0= 'targetStartExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:513:6: ( (lv_targetStartExec_10_0= 'targetStartExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:514:1: (lv_targetStartExec_10_0= 'targetStartExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:514:1: (lv_targetStartExec_10_0= 'targetStartExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:515:3: lv_targetStartExec_10_0= 'targetStartExec'
                    {
                    lv_targetStartExec_10_0=(Token)match(input,21,FOLLOW_21_in_ruleTwoLifelineMessage1023); 

                            newLeafNode(lv_targetStartExec_10_0, grammarAccess.getTwoLifelineMessageAccess().getTargetStartExecTargetStartExecKeyword_6_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "targetStartExec", true, "targetStartExec");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:529:6: ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:529:6: ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:529:7: ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:529:7: ( (lv_targetEndExec_11_0= 'targetEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:530:1: (lv_targetEndExec_11_0= 'targetEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:530:1: (lv_targetEndExec_11_0= 'targetEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:531:3: lv_targetEndExec_11_0= 'targetEndExec'
                    {
                    lv_targetEndExec_11_0=(Token)match(input,22,FOLLOW_22_in_ruleTwoLifelineMessage1061); 

                            newLeafNode(lv_targetEndExec_11_0, grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecTargetEndExecKeyword_6_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "targetEndExec", true, "targetEndExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:544:2: ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO ) )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==RULE_INT_GREATER_ZERO) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:545:1: (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:545:1: (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:546:3: lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO
                            {
                            lv_targetEndExecCount_12_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1091); 

                            			newLeafNode(lv_targetEndExecCount_12_0, grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecCountINT_GREATER_ZEROTerminalRuleCall_6_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"targetEndExecCount",
                                    		lv_targetEndExecCount_12_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:562:6: (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==23) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:562:8: otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) )
                    {
                    otherlv_13=(Token)match(input,23,FOLLOW_23_in_ruleTwoLifelineMessage1113); 

                        	newLeafNode(otherlv_13, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:566:1: ( (lv_sourceNote_14_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:567:1: (lv_sourceNote_14_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:567:1: (lv_sourceNote_14_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:568:3: lv_sourceNote_14_0= RULE_STRING
                    {
                    lv_sourceNote_14_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1130); 

                    			newLeafNode(lv_sourceNote_14_0, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"sourceNote",
                            		lv_sourceNote_14_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:584:4: (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==24) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:584:6: otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) )
                    {
                    otherlv_15=(Token)match(input,24,FOLLOW_24_in_ruleTwoLifelineMessage1150); 

                        	newLeafNode(otherlv_15, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:588:1: ( (lv_targetNote_16_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:589:1: (lv_targetNote_16_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:589:1: (lv_targetNote_16_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:590:3: lv_targetNote_16_0= RULE_STRING
                    {
                    lv_targetNote_16_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1167); 

                    			newLeafNode(lv_targetNote_16_0, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteSTRINGTerminalRuleCall_8_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"targetNote",
                            		lv_targetNote_16_0, 
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:614:1: entryRuleOneLifelineMessage returns [EObject current=null] : iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF ;
    public final EObject entryRuleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:615:2: (iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:616:2: iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1210);
            iv_ruleOneLifelineMessage=ruleOneLifelineMessage();

            state._fsp--;

             current =iv_ruleOneLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineMessage1220); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:623:1: ruleOneLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_messageTypeLostAndFound_2_1=null;
        Token lv_messageTypeLostAndFound_2_2=null;
        Token lv_message_3_0=null;
        Token lv_startEndExec_4_0=null;
        Token lv_startExec_5_0=null;
        Token lv_endExec_6_0=null;
        Token lv_endExecCount_7_0=null;
        Token otherlv_8=null;
        Token lv_note_9_0=null;
        Enumerator lv_messageType_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:626:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:627:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:627:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:627:2: ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageType ) ) ( ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:627:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:628:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:628:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:629:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineMessage1265); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:640:2: ( (lv_messageType_1_0= ruleMessageType ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:641:1: (lv_messageType_1_0= ruleMessageType )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:641:1: (lv_messageType_1_0= ruleMessageType )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:642:3: lv_messageType_1_0= ruleMessageType
            {
             
            	        newCompositeNode(grammarAccess.getOneLifelineMessageAccess().getMessageTypeMessageTypeEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleMessageType_in_ruleOneLifelineMessage1286);
            lv_messageType_1_0=ruleMessageType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOneLifelineMessageRule());
            	        }
                   		set(
                   			current, 
                   			"messageType",
                    		lv_messageType_1_0, 
                    		"MessageType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:658:2: ( ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:659:1: ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:659:1: ( (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:660:1: (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:660:1: (lv_messageTypeLostAndFound_2_1= 'lost' | lv_messageTypeLostAndFound_2_2= 'found' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==25) ) {
                alt11=1;
            }
            else if ( (LA11_0==26) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:661:3: lv_messageTypeLostAndFound_2_1= 'lost'
                    {
                    lv_messageTypeLostAndFound_2_1=(Token)match(input,25,FOLLOW_25_in_ruleOneLifelineMessage1306); 

                            newLeafNode(lv_messageTypeLostAndFound_2_1, grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundLostKeyword_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "messageTypeLostAndFound", lv_messageTypeLostAndFound_2_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:673:8: lv_messageTypeLostAndFound_2_2= 'found'
                    {
                    lv_messageTypeLostAndFound_2_2=(Token)match(input,26,FOLLOW_26_in_ruleOneLifelineMessage1335); 

                            newLeafNode(lv_messageTypeLostAndFound_2_2, grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundFoundKeyword_2_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "messageTypeLostAndFound", lv_messageTypeLostAndFound_2_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:688:2: ( (lv_message_3_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:689:1: (lv_message_3_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:689:1: (lv_message_3_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:690:3: lv_message_3_0= RULE_STRING
            {
            lv_message_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1368); 

            			newLeafNode(lv_message_3_0, grammarAccess.getOneLifelineMessageAccess().getMessageSTRINGTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"message",
                    		lv_message_3_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:706:2: ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt13=4;
            switch ( input.LA(1) ) {
                case 27:
                    {
                    alt13=1;
                    }
                    break;
                case 28:
                    {
                    alt13=2;
                    }
                    break;
                case 29:
                    {
                    alt13=3;
                    }
                    break;
            }

            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:706:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:706:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:707:1: (lv_startEndExec_4_0= 'startEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:707:1: (lv_startEndExec_4_0= 'startEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:708:3: lv_startEndExec_4_0= 'startEndExec'
                    {
                    lv_startEndExec_4_0=(Token)match(input,27,FOLLOW_27_in_ruleOneLifelineMessage1392); 

                            newLeafNode(lv_startEndExec_4_0, grammarAccess.getOneLifelineMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startEndExec", true, "startEndExec");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:722:6: ( (lv_startExec_5_0= 'startExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:722:6: ( (lv_startExec_5_0= 'startExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:723:1: (lv_startExec_5_0= 'startExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:723:1: (lv_startExec_5_0= 'startExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:724:3: lv_startExec_5_0= 'startExec'
                    {
                    lv_startExec_5_0=(Token)match(input,28,FOLLOW_28_in_ruleOneLifelineMessage1429); 

                            newLeafNode(lv_startExec_5_0, grammarAccess.getOneLifelineMessageAccess().getStartExecStartExecKeyword_4_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startExec", true, "startExec");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:738:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:738:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:738:7: ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:738:7: ( (lv_endExec_6_0= 'endExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:739:1: (lv_endExec_6_0= 'endExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:739:1: (lv_endExec_6_0= 'endExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:740:3: lv_endExec_6_0= 'endExec'
                    {
                    lv_endExec_6_0=(Token)match(input,29,FOLLOW_29_in_ruleOneLifelineMessage1467); 

                            newLeafNode(lv_endExec_6_0, grammarAccess.getOneLifelineMessageAccess().getEndExecEndExecKeyword_4_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endExec", true, "endExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:753:2: ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==RULE_INT_GREATER_ZERO) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:754:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:754:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:755:3: lv_endExecCount_7_0= RULE_INT_GREATER_ZERO
                            {
                            lv_endExecCount_7_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineMessage1497); 

                            			newLeafNode(lv_endExecCount_7_0, grammarAccess.getOneLifelineMessageAccess().getEndExecCountINT_GREATER_ZEROTerminalRuleCall_4_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endExecCount",
                                    		lv_endExecCount_7_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:771:6: (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==30) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:771:8: otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,30,FOLLOW_30_in_ruleOneLifelineMessage1519); 

                        	newLeafNode(otherlv_8, grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:775:1: ( (lv_note_9_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:776:1: (lv_note_9_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:776:1: (lv_note_9_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:777:3: lv_note_9_0= RULE_STRING
                    {
                    lv_note_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1536); 

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


    // $ANTLR start "entryRuleSelfMessage"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:801:1: entryRuleSelfMessage returns [EObject current=null] : iv_ruleSelfMessage= ruleSelfMessage EOF ;
    public final EObject entryRuleSelfMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelfMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:802:2: (iv_ruleSelfMessage= ruleSelfMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:803:2: iv_ruleSelfMessage= ruleSelfMessage EOF
            {
             newCompositeNode(grammarAccess.getSelfMessageRule()); 
            pushFollow(FOLLOW_ruleSelfMessage_in_entryRuleSelfMessage1579);
            iv_ruleSelfMessage=ruleSelfMessage();

            state._fsp--;

             current =iv_ruleSelfMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelfMessage1589); 

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
    // $ANTLR end "entryRuleSelfMessage"


    // $ANTLR start "ruleSelfMessage"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:810:1: ruleSelfMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleSelfMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_messageType_1_1=null;
        Token lv_messageType_1_2=null;
        Token lv_messageType_1_3=null;
        Token otherlv_2=null;
        Token lv_message_3_0=null;
        Token lv_startEndExec_4_0=null;
        Token lv_startExec_5_0=null;
        Token lv_endExec_6_0=null;
        Token lv_endExecCount_7_0=null;
        Token otherlv_8=null;
        Token lv_note_9_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:813:28: ( ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:814:1: ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:814:1: ( ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:814:2: ( (otherlv_0= RULE_ID ) ) ( ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:814:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:815:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:815:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:816:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getSelfMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSelfMessage1634); 

            		newLeafNode(otherlv_0, grammarAccess.getSelfMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:827:2: ( ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:828:1: ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:828:1: ( (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:829:1: (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:829:1: (lv_messageType_1_1= 'sync' | lv_messageType_1_2= 'async' | lv_messageType_1_3= 'response' )
            int alt15=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt15=1;
                }
                break;
            case 32:
                {
                alt15=2;
                }
                break;
            case 33:
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:830:3: lv_messageType_1_1= 'sync'
                    {
                    lv_messageType_1_1=(Token)match(input,31,FOLLOW_31_in_ruleSelfMessage1654); 

                            newLeafNode(lv_messageType_1_1, grammarAccess.getSelfMessageAccess().getMessageTypeSyncKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
                    	        }
                           		setWithLastConsumed(current, "messageType", lv_messageType_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:842:8: lv_messageType_1_2= 'async'
                    {
                    lv_messageType_1_2=(Token)match(input,32,FOLLOW_32_in_ruleSelfMessage1683); 

                            newLeafNode(lv_messageType_1_2, grammarAccess.getSelfMessageAccess().getMessageTypeAsyncKeyword_1_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
                    	        }
                           		setWithLastConsumed(current, "messageType", lv_messageType_1_2, null);
                    	    

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:854:8: lv_messageType_1_3= 'response'
                    {
                    lv_messageType_1_3=(Token)match(input,33,FOLLOW_33_in_ruleSelfMessage1712); 

                            newLeafNode(lv_messageType_1_3, grammarAccess.getSelfMessageAccess().getMessageTypeResponseKeyword_1_0_2());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
                    	        }
                           		setWithLastConsumed(current, "messageType", lv_messageType_1_3, null);
                    	    

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,34,FOLLOW_34_in_ruleSelfMessage1740); 

                	newLeafNode(otherlv_2, grammarAccess.getSelfMessageAccess().getSelfKeyword_2());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:873:1: ( (lv_message_3_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:874:1: (lv_message_3_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:874:1: (lv_message_3_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:875:3: lv_message_3_0= RULE_STRING
            {
            lv_message_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSelfMessage1757); 

            			newLeafNode(lv_message_3_0, grammarAccess.getSelfMessageAccess().getMessageSTRINGTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelfMessageRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"message",
                    		lv_message_3_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:891:2: ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? ) )?
            int alt17=4;
            switch ( input.LA(1) ) {
                case 27:
                    {
                    alt17=1;
                    }
                    break;
                case 28:
                    {
                    alt17=2;
                    }
                    break;
                case 29:
                    {
                    alt17=3;
                    }
                    break;
            }

            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:891:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:891:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:892:1: (lv_startEndExec_4_0= 'startEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:892:1: (lv_startEndExec_4_0= 'startEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:893:3: lv_startEndExec_4_0= 'startEndExec'
                    {
                    lv_startEndExec_4_0=(Token)match(input,27,FOLLOW_27_in_ruleSelfMessage1781); 

                            newLeafNode(lv_startEndExec_4_0, grammarAccess.getSelfMessageAccess().getStartEndExecStartEndExecKeyword_4_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startEndExec", true, "startEndExec");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:907:6: ( (lv_startExec_5_0= 'startExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:907:6: ( (lv_startExec_5_0= 'startExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:908:1: (lv_startExec_5_0= 'startExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:908:1: (lv_startExec_5_0= 'startExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:909:3: lv_startExec_5_0= 'startExec'
                    {
                    lv_startExec_5_0=(Token)match(input,28,FOLLOW_28_in_ruleSelfMessage1818); 

                            newLeafNode(lv_startExec_5_0, grammarAccess.getSelfMessageAccess().getStartExecStartExecKeyword_4_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
                    	        }
                           		setWithLastConsumed(current, "startExec", true, "startExec");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:923:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:923:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:923:7: ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:923:7: ( (lv_endExec_6_0= 'endExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:924:1: (lv_endExec_6_0= 'endExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:924:1: (lv_endExec_6_0= 'endExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:925:3: lv_endExec_6_0= 'endExec'
                    {
                    lv_endExec_6_0=(Token)match(input,29,FOLLOW_29_in_ruleSelfMessage1856); 

                            newLeafNode(lv_endExec_6_0, grammarAccess.getSelfMessageAccess().getEndExecEndExecKeyword_4_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endExec", true, "endExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:938:2: ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO ) )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==RULE_INT_GREATER_ZERO) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:939:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:939:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:940:3: lv_endExecCount_7_0= RULE_INT_GREATER_ZERO
                            {
                            lv_endExecCount_7_0=(Token)match(input,RULE_INT_GREATER_ZERO,FOLLOW_RULE_INT_GREATER_ZERO_in_ruleSelfMessage1886); 

                            			newLeafNode(lv_endExecCount_7_0, grammarAccess.getSelfMessageAccess().getEndExecCountINT_GREATER_ZEROTerminalRuleCall_4_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getSelfMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endExecCount",
                                    		lv_endExecCount_7_0, 
                                    		"INT_GREATER_ZERO");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:956:6: (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==30) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:956:8: otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,30,FOLLOW_30_in_ruleSelfMessage1908); 

                        	newLeafNode(otherlv_8, grammarAccess.getSelfMessageAccess().getNoteKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:960:1: ( (lv_note_9_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:961:1: (lv_note_9_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:961:1: (lv_note_9_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:962:3: lv_note_9_0= RULE_STRING
                    {
                    lv_note_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSelfMessage1925); 

                    			newLeafNode(lv_note_9_0, grammarAccess.getSelfMessageAccess().getNoteSTRINGTerminalRuleCall_5_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
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
    // $ANTLR end "ruleSelfMessage"


    // $ANTLR start "entryRuleOneLifelineNote"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:986:1: entryRuleOneLifelineNote returns [EObject current=null] : iv_ruleOneLifelineNote= ruleOneLifelineNote EOF ;
    public final EObject entryRuleOneLifelineNote() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineNote = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:987:2: (iv_ruleOneLifelineNote= ruleOneLifelineNote EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:988:2: iv_ruleOneLifelineNote= ruleOneLifelineNote EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineNoteRule()); 
            pushFollow(FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote1968);
            iv_ruleOneLifelineNote=ruleOneLifelineNote();

            state._fsp--;

             current =iv_ruleOneLifelineNote; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineNote1978); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:995:1: ruleOneLifelineNote returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleOneLifelineNote() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_note_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:998:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:999:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:999:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:999:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= 'note' ( (lv_note_2_0= RULE_STRING ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:999:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1000:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1000:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1001:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineNoteRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineNote2023); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            otherlv_1=(Token)match(input,30,FOLLOW_30_in_ruleOneLifelineNote2035); 

                	newLeafNode(otherlv_1, grammarAccess.getOneLifelineNoteAccess().getNoteKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1016:1: ( (lv_note_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1017:1: (lv_note_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1017:1: (lv_note_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1018:3: lv_note_2_0= RULE_STRING
            {
            lv_note_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineNote2052); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1042:1: entryRuleDestroyLifelineEvent returns [EObject current=null] : iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF ;
    public final EObject entryRuleDestroyLifelineEvent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDestroyLifelineEvent = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1043:2: (iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1044:2: iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF
            {
             newCompositeNode(grammarAccess.getDestroyLifelineEventRule()); 
            pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent2093);
            iv_ruleDestroyLifelineEvent=ruleDestroyLifelineEvent();

            state._fsp--;

             current =iv_ruleDestroyLifelineEvent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroyLifelineEvent2103); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1051:1: ruleDestroyLifelineEvent returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' ) ;
    public final EObject ruleDestroyLifelineEvent() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1054:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1055:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1055:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1055:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy'
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1055:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1056:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1056:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1057:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDestroyLifelineEventRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDestroyLifelineEvent2148); 

            		newLeafNode(otherlv_0, grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            otherlv_1=(Token)match(input,35,FOLLOW_35_in_ruleDestroyLifelineEvent2160); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1080:1: entryRuleFragment returns [EObject current=null] : iv_ruleFragment= ruleFragment EOF ;
    public final EObject entryRuleFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFragment = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1081:2: (iv_ruleFragment= ruleFragment EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1082:2: iv_ruleFragment= ruleFragment EOF
            {
             newCompositeNode(grammarAccess.getFragmentRule()); 
            pushFollow(FOLLOW_ruleFragment_in_entryRuleFragment2196);
            iv_ruleFragment=ruleFragment();

            state._fsp--;

             current =iv_ruleFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragment2206); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1089:1: ruleFragment returns [EObject current=null] : (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* ) ;
    public final EObject ruleFragment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        EObject lv_sections_2_0 = null;

        EObject lv_sections_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1092:28: ( (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1093:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1093:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1093:3: otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )*
            {
            otherlv_0=(Token)match(input,36,FOLLOW_36_in_ruleFragment2243); 

                	newLeafNode(otherlv_0, grammarAccess.getFragmentAccess().getFragmentKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1097:1: ( (lv_name_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1098:1: (lv_name_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1098:1: (lv_name_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1099:3: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFragment2260); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1115:2: ( (lv_sections_2_0= ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1116:1: (lv_sections_2_0= ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1116:1: (lv_sections_2_0= ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1117:3: lv_sections_2_0= ruleSection
            {
             
            	        newCompositeNode(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSection_in_ruleFragment2286);
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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1133:2: ( (lv_sections_3_0= ruleSection ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==37) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1134:1: (lv_sections_3_0= ruleSection )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1134:1: (lv_sections_3_0= ruleSection )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1135:3: lv_sections_3_0= ruleSection
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSection_in_ruleFragment2307);
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
            	    break loop19;
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1159:1: entryRuleSection returns [EObject current=null] : iv_ruleSection= ruleSection EOF ;
    public final EObject entryRuleSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSection = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1160:2: (iv_ruleSection= ruleSection EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1161:2: iv_ruleSection= ruleSection EOF
            {
             newCompositeNode(grammarAccess.getSectionRule()); 
            pushFollow(FOLLOW_ruleSection_in_entryRuleSection2344);
            iv_ruleSection=ruleSection();

            state._fsp--;

             current =iv_ruleSection; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSection2354); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1168:1: ruleSection returns [EObject current=null] : (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) ;
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
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1171:28: ( (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1172:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1172:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1172:3: otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,37,FOLLOW_37_in_ruleSection2391); 

                	newLeafNode(otherlv_0, grammarAccess.getSectionAccess().getLeftCurlyBracketKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1176:1: (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==38) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1176:3: otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,38,FOLLOW_38_in_ruleSection2404); 

                        	newLeafNode(otherlv_1, grammarAccess.getSectionAccess().getLabelKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1180:1: ( (lv_label_2_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1181:1: (lv_label_2_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1181:1: (lv_label_2_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1182:3: lv_label_2_0= RULE_STRING
                    {
                    lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSection2421); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1198:4: ( (lv_interactions_3_0= ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1199:1: (lv_interactions_3_0= ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1199:1: (lv_interactions_3_0= ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1200:3: lv_interactions_3_0= ruleInteraction
            {
             
            	        newCompositeNode(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleInteraction_in_ruleSection2449);
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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1216:2: ( (lv_interactions_4_0= ruleInteraction ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID||LA21_0==36||LA21_0==40) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1217:1: (lv_interactions_4_0= ruleInteraction )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1217:1: (lv_interactions_4_0= ruleInteraction )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1218:3: lv_interactions_4_0= ruleInteraction
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInteraction_in_ruleSection2470);
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
            	    break loop21;
                }
            } while (true);

            otherlv_5=(Token)match(input,39,FOLLOW_39_in_ruleSection2483); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1246:1: entryRuleRefinement returns [EObject current=null] : iv_ruleRefinement= ruleRefinement EOF ;
    public final EObject entryRuleRefinement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRefinement = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1247:2: (iv_ruleRefinement= ruleRefinement EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1248:2: iv_ruleRefinement= ruleRefinement EOF
            {
             newCompositeNode(grammarAccess.getRefinementRule()); 
            pushFollow(FOLLOW_ruleRefinement_in_entryRuleRefinement2519);
            iv_ruleRefinement=ruleRefinement();

            state._fsp--;

             current =iv_ruleRefinement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRefinement2529); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1255:1: ruleRefinement returns [EObject current=null] : (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* ) ;
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
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1258:28: ( (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1259:1: (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1259:1: (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1259:3: otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            {
            otherlv_0=(Token)match(input,40,FOLLOW_40_in_ruleRefinement2566); 

                	newLeafNode(otherlv_0, grammarAccess.getRefinementAccess().getRefinementKeyword_0());
                
            otherlv_1=(Token)match(input,38,FOLLOW_38_in_ruleRefinement2578); 

                	newLeafNode(otherlv_1, grammarAccess.getRefinementAccess().getLabelKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1267:1: ( (lv_label_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1268:1: (lv_label_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1268:1: (lv_label_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1269:3: lv_label_2_0= RULE_STRING
            {
            lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleRefinement2595); 

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

            otherlv_3=(Token)match(input,41,FOLLOW_41_in_ruleRefinement2612); 

                	newLeafNode(otherlv_3, grammarAccess.getRefinementAccess().getLifelinesKeyword_3());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1289:1: ( (otherlv_4= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1290:1: (otherlv_4= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1290:1: (otherlv_4= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1291:3: otherlv_4= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getRefinementRule());
            	        }
                    
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2632); 

            		newLeafNode(otherlv_4, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1302:2: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==42) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1302:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
            	    {
            	    otherlv_5=(Token)match(input,42,FOLLOW_42_in_ruleRefinement2645); 

            	        	newLeafNode(otherlv_5, grammarAccess.getRefinementAccess().getCommaKeyword_5_0());
            	        
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1306:1: ( (otherlv_6= RULE_ID ) )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1307:1: (otherlv_6= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1307:1: (otherlv_6= RULE_ID )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1308:3: otherlv_6= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getRefinementRule());
            	    	        }
            	            
            	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2665); 

            	    		newLeafNode(otherlv_6, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_5_1_0()); 
            	    	

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1327:1: ruleMessageType returns [Enumerator current=null] : ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) ) ;
    public final Enumerator ruleMessageType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1329:28: ( ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1330:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1330:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'create' ) | (enumLiteral_2= 'response' ) | (enumLiteral_3= 'sync' ) )
            int alt23=4;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt23=1;
                }
                break;
            case 43:
                {
                alt23=2;
                }
                break;
            case 33:
                {
                alt23=3;
                }
                break;
            case 31:
                {
                alt23=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1330:2: (enumLiteral_0= 'async' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1330:2: (enumLiteral_0= 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1330:4: enumLiteral_0= 'async'
                    {
                    enumLiteral_0=(Token)match(input,32,FOLLOW_32_in_ruleMessageType2717); 

                            current = grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getMessageTypeAccess().getAsyncEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1336:6: (enumLiteral_1= 'create' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1336:6: (enumLiteral_1= 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1336:8: enumLiteral_1= 'create'
                    {
                    enumLiteral_1=(Token)match(input,43,FOLLOW_43_in_ruleMessageType2734); 

                            current = grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getMessageTypeAccess().getCreateEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1342:6: (enumLiteral_2= 'response' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1342:6: (enumLiteral_2= 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1342:8: enumLiteral_2= 'response'
                    {
                    enumLiteral_2=(Token)match(input,33,FOLLOW_33_in_ruleMessageType2751); 

                            current = grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getMessageTypeAccess().getResponseEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1348:6: (enumLiteral_3= 'sync' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1348:6: (enumLiteral_3= 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1348:8: enumLiteral_3= 'sync'
                    {
                    enumLiteral_3=(Token)match(input,31,FOLLOW_31_in_ruleMessageType2768); 

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

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\15\uffff";
    static final String DFA4_eofS =
        "\15\uffff";
    static final String DFA4_minS =
        "\1\5\1\36\3\uffff\4\4\4\uffff";
    static final String DFA4_maxS =
        "\1\50\1\53\3\uffff\1\42\1\32\2\42\4\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\3\1\7\1\4\4\uffff\1\6\1\1\1\5\1\2";
    static final String DFA4_specialS =
        "\15\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\36\uffff\1\2\3\uffff\1\3",
            "\1\4\1\10\1\5\1\7\1\uffff\1\11\7\uffff\1\6",
            "",
            "",
            "",
            "\1\12\24\uffff\2\14\7\uffff\1\13",
            "\1\12\24\uffff\2\14",
            "\1\12\24\uffff\2\14\7\uffff\1\13",
            "\1\12\24\uffff\2\14\7\uffff\1\13",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "276:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_OneLifelineNote_3= ruleOneLifelineNote | this_SelfMessage_4= ruleSelfMessage | this_DestroyLifelineEvent_5= ruleDestroyLifelineEvent | this_Refinement_6= ruleRefinement )";
        }
    }
 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleSequenceDiagram131 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSequenceDiagram148 = new BitSet(new long[]{0x000001100000A022L});
    public static final BitSet FOLLOW_ruleLifeline_in_ruleSequenceDiagram174 = new BitSet(new long[]{0x000001100000A022L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSequenceDiagram196 = new BitSet(new long[]{0x0000011000000022L});
    public static final BitSet FOLLOW_ruleLifeline_in_entryRuleLifeline233 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLifeline243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleLifeline281 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLifeline298 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleLifeline315 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLifeline332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleLifeline357 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLifeline374 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleLifeline391 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLifeline408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInteraction_in_entryRuleInteraction450 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInteraction460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_ruleInteraction507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_ruleInteraction534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_ruleInteraction561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_ruleInteraction588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelfMessage_in_ruleInteraction615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_ruleInteraction642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_ruleInteraction669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage704 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTwoLifelineMessage714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage759 = new BitSet(new long[]{0x0000080380000000L});
    public static final BitSet FOLLOW_ruleMessageType_in_ruleTwoLifelineMessage780 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage797 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTwoLifelineMessage814 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage834 = new BitSet(new long[]{0x0000000001FE0002L});
    public static final BitSet FOLLOW_17_in_ruleTwoLifelineMessage853 = new BitSet(new long[]{0x0000000001F00002L});
    public static final BitSet FOLLOW_18_in_ruleTwoLifelineMessage890 = new BitSet(new long[]{0x0000000001F00002L});
    public static final BitSet FOLLOW_19_in_ruleTwoLifelineMessage928 = new BitSet(new long[]{0x0000000001F00042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage958 = new BitSet(new long[]{0x0000000001F00002L});
    public static final BitSet FOLLOW_20_in_ruleTwoLifelineMessage986 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_21_in_ruleTwoLifelineMessage1023 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_22_in_ruleTwoLifelineMessage1061 = new BitSet(new long[]{0x0000000001800042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleTwoLifelineMessage1091 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_23_in_ruleTwoLifelineMessage1113 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1130 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleTwoLifelineMessage1150 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1210 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineMessage1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineMessage1265 = new BitSet(new long[]{0x0000080380000000L});
    public static final BitSet FOLLOW_ruleMessageType_in_ruleOneLifelineMessage1286 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_25_in_ruleOneLifelineMessage1306 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_26_in_ruleOneLifelineMessage1335 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1368 = new BitSet(new long[]{0x0000000078000002L});
    public static final BitSet FOLLOW_27_in_ruleOneLifelineMessage1392 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_28_in_ruleOneLifelineMessage1429 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_29_in_ruleOneLifelineMessage1467 = new BitSet(new long[]{0x0000000040000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleOneLifelineMessage1497 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleOneLifelineMessage1519 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelfMessage_in_entryRuleSelfMessage1579 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelfMessage1589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSelfMessage1634 = new BitSet(new long[]{0x0000000380000000L});
    public static final BitSet FOLLOW_31_in_ruleSelfMessage1654 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_32_in_ruleSelfMessage1683 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_33_in_ruleSelfMessage1712 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleSelfMessage1740 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSelfMessage1757 = new BitSet(new long[]{0x0000000078000002L});
    public static final BitSet FOLLOW_27_in_ruleSelfMessage1781 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_28_in_ruleSelfMessage1818 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_29_in_ruleSelfMessage1856 = new BitSet(new long[]{0x0000000040000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_in_ruleSelfMessage1886 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleSelfMessage1908 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSelfMessage1925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineNote_in_entryRuleOneLifelineNote1968 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineNote1978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineNote2023 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleOneLifelineNote2035 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineNote2052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent2093 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroyLifelineEvent2103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDestroyLifelineEvent2148 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleDestroyLifelineEvent2160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment2196 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment2206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleFragment2243 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFragment2260 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ruleSection_in_ruleFragment2286 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_ruleSection_in_ruleFragment2307 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_ruleSection_in_entryRuleSection2344 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSection2354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleSection2391 = new BitSet(new long[]{0x0000015000000020L});
    public static final BitSet FOLLOW_38_in_ruleSection2404 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSection2421 = new BitSet(new long[]{0x0000011000000020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSection2449 = new BitSet(new long[]{0x0000019000000020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSection2470 = new BitSet(new long[]{0x0000019000000020L});
    public static final BitSet FOLLOW_39_in_ruleSection2483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement2519 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement2529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleRefinement2566 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleRefinement2578 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleRefinement2595 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleRefinement2612 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2632 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_ruleRefinement2645 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2665 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_32_in_ruleMessageType2717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleMessageType2734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleMessageType2751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleMessageType2768 = new BitSet(new long[]{0x0000000000000002L});

}