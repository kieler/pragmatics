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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT_GREATER_ZERO_OR_ALL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'sequenceDiagram'", "'lifeline'", "'as'", "'usecase'", "'to'", "'sourceStartEndExec'", "'sourceStartExec'", "'sourceEndExec'", "'targetStartEndExec'", "'targetStartExec'", "'targetEndExec'", "'sourceNote'", "'targetNote'", "'startEndExec'", "'startExec'", "'endExec'", "'note'", "'self'", "'destroy'", "'fragment'", "'{'", "'label'", "'}'", "'refinement'", "'lifelines'", "','", "'async'", "'response'", "'sync'", "'create'", "'lost'", "'found'"
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
    public static final int RULE_INT_GREATER_ZERO_OR_ALL=6;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
    public static final int T__29=29;
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

                if ( (LA2_0==RULE_ID||LA2_0==31||LA2_0==35) ) {
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:272:1: ruleInteraction returns [EObject current=null] : (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_SelfMessage_3= ruleSelfMessage | this_DestroyLifelineEvent_4= ruleDestroyLifelineEvent | this_Refinement_5= ruleRefinement ) ;
    public final EObject ruleInteraction() throws RecognitionException {
        EObject current = null;

        EObject this_TwoLifelineMessage_0 = null;

        EObject this_OneLifelineMessage_1 = null;

        EObject this_Fragment_2 = null;

        EObject this_SelfMessage_3 = null;

        EObject this_DestroyLifelineEvent_4 = null;

        EObject this_Refinement_5 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:275:28: ( (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_SelfMessage_3= ruleSelfMessage | this_DestroyLifelineEvent_4= ruleDestroyLifelineEvent | this_Refinement_5= ruleRefinement ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:276:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_SelfMessage_3= ruleSelfMessage | this_DestroyLifelineEvent_4= ruleDestroyLifelineEvent | this_Refinement_5= ruleRefinement )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:276:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_SelfMessage_3= ruleSelfMessage | this_DestroyLifelineEvent_4= ruleDestroyLifelineEvent | this_Refinement_5= ruleRefinement )
            int alt4=6;
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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:307:5: this_SelfMessage_3= ruleSelfMessage
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getSelfMessageParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleSelfMessage_in_ruleInteraction588);
                    this_SelfMessage_3=ruleSelfMessage();

                    state._fsp--;

                     
                            current = this_SelfMessage_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:317:5: this_DestroyLifelineEvent_4= ruleDestroyLifelineEvent
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getDestroyLifelineEventParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_ruleInteraction615);
                    this_DestroyLifelineEvent_4=ruleDestroyLifelineEvent();

                    state._fsp--;

                     
                            current = this_DestroyLifelineEvent_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:327:5: this_Refinement_5= ruleRefinement
                    {
                     
                            newCompositeNode(grammarAccess.getInteractionAccess().getRefinementParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleRefinement_in_ruleInteraction642);
                    this_Refinement_5=ruleRefinement();

                    state._fsp--;

                     
                            current = this_Refinement_5; 
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:343:1: entryRuleTwoLifelineMessage returns [EObject current=null] : iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF ;
    public final EObject entryRuleTwoLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTwoLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:344:2: (iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:345:2: iv_ruleTwoLifelineMessage= ruleTwoLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getTwoLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage677);
            iv_ruleTwoLifelineMessage=ruleTwoLifelineMessage();

            state._fsp--;

             current =iv_ruleTwoLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTwoLifelineMessage687); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:352:1: ruleTwoLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeTwo ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? ) ;
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
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:355:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeTwo ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:356:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeTwo ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:356:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeTwo ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:356:2: ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeTwo ) ) ( (lv_message_2_0= RULE_STRING ) ) otherlv_3= 'to' ( (otherlv_4= RULE_ID ) ) ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )? (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:356:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:357:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:357:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:358:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage732); 

            		newLeafNode(otherlv_0, grammarAccess.getTwoLifelineMessageAccess().getSourceLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:369:2: ( (lv_messageType_1_0= ruleMessageTypeTwo ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:370:1: (lv_messageType_1_0= ruleMessageTypeTwo )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:370:1: (lv_messageType_1_0= ruleMessageTypeTwo )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:371:3: lv_messageType_1_0= ruleMessageTypeTwo
            {
             
            	        newCompositeNode(grammarAccess.getTwoLifelineMessageAccess().getMessageTypeMessageTypeTwoEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleMessageTypeTwo_in_ruleTwoLifelineMessage753);
            lv_messageType_1_0=ruleMessageTypeTwo();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTwoLifelineMessageRule());
            	        }
                   		set(
                   			current, 
                   			"messageType",
                    		lv_messageType_1_0, 
                    		"MessageTypeTwo");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:387:2: ( (lv_message_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:388:1: (lv_message_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:388:1: (lv_message_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:389:3: lv_message_2_0= RULE_STRING
            {
            lv_message_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage770); 

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

            otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleTwoLifelineMessage787); 

                	newLeafNode(otherlv_3, grammarAccess.getTwoLifelineMessageAccess().getToKeyword_3());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:409:1: ( (otherlv_4= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:410:1: (otherlv_4= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:410:1: (otherlv_4= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:411:3: otherlv_4= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
            	        }
                    
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTwoLifelineMessage807); 

            		newLeafNode(otherlv_4, grammarAccess.getTwoLifelineMessageAccess().getTargetLifelineLifelineCrossReference_4_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:422:2: ( ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) ) | ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) ) | ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )?
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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:422:3: ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:422:3: ( (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:423:1: (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:423:1: (lv_sourceStartEndExec_5_0= 'sourceStartEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:424:3: lv_sourceStartEndExec_5_0= 'sourceStartEndExec'
                    {
                    lv_sourceStartEndExec_5_0=(Token)match(input,17,FOLLOW_17_in_ruleTwoLifelineMessage826); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:438:6: ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:438:6: ( (lv_sourceStartExec_6_0= 'sourceStartExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:439:1: (lv_sourceStartExec_6_0= 'sourceStartExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:439:1: (lv_sourceStartExec_6_0= 'sourceStartExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:440:3: lv_sourceStartExec_6_0= 'sourceStartExec'
                    {
                    lv_sourceStartExec_6_0=(Token)match(input,18,FOLLOW_18_in_ruleTwoLifelineMessage863); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:454:6: ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:454:6: ( ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:454:7: ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) ) ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:454:7: ( (lv_sourceEndExec_7_0= 'sourceEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:455:1: (lv_sourceEndExec_7_0= 'sourceEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:455:1: (lv_sourceEndExec_7_0= 'sourceEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:456:3: lv_sourceEndExec_7_0= 'sourceEndExec'
                    {
                    lv_sourceEndExec_7_0=(Token)match(input,19,FOLLOW_19_in_ruleTwoLifelineMessage901); 

                            newLeafNode(lv_sourceEndExec_7_0, grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecSourceEndExecKeyword_5_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "sourceEndExec", true, "sourceEndExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:469:2: ( (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==RULE_INT_GREATER_ZERO_OR_ALL) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:470:1: (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:470:1: (lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:471:3: lv_sourceEndExecCount_8_0= RULE_INT_GREATER_ZERO_OR_ALL
                            {
                            lv_sourceEndExecCount_8_0=(Token)match(input,RULE_INT_GREATER_ZERO_OR_ALL,FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleTwoLifelineMessage931); 

                            			newLeafNode(lv_sourceEndExecCount_8_0, grammarAccess.getTwoLifelineMessageAccess().getSourceEndExecCountINT_GREATER_ZERO_OR_ALLTerminalRuleCall_5_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"sourceEndExecCount",
                                    		lv_sourceEndExecCount_8_0, 
                                    		"INT_GREATER_ZERO_OR_ALL");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:487:6: ( ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) ) | ( (lv_targetStartExec_10_0= 'targetStartExec' ) ) | ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )?
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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:487:7: ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:487:7: ( (lv_targetStartEndExec_9_0= 'targetStartEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:488:1: (lv_targetStartEndExec_9_0= 'targetStartEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:488:1: (lv_targetStartEndExec_9_0= 'targetStartEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:489:3: lv_targetStartEndExec_9_0= 'targetStartEndExec'
                    {
                    lv_targetStartEndExec_9_0=(Token)match(input,20,FOLLOW_20_in_ruleTwoLifelineMessage959); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:503:6: ( (lv_targetStartExec_10_0= 'targetStartExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:503:6: ( (lv_targetStartExec_10_0= 'targetStartExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:504:1: (lv_targetStartExec_10_0= 'targetStartExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:504:1: (lv_targetStartExec_10_0= 'targetStartExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:505:3: lv_targetStartExec_10_0= 'targetStartExec'
                    {
                    lv_targetStartExec_10_0=(Token)match(input,21,FOLLOW_21_in_ruleTwoLifelineMessage996); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:519:6: ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:519:6: ( ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:519:7: ( (lv_targetEndExec_11_0= 'targetEndExec' ) ) ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:519:7: ( (lv_targetEndExec_11_0= 'targetEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:520:1: (lv_targetEndExec_11_0= 'targetEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:520:1: (lv_targetEndExec_11_0= 'targetEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:521:3: lv_targetEndExec_11_0= 'targetEndExec'
                    {
                    lv_targetEndExec_11_0=(Token)match(input,22,FOLLOW_22_in_ruleTwoLifelineMessage1034); 

                            newLeafNode(lv_targetEndExec_11_0, grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecTargetEndExecKeyword_6_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "targetEndExec", true, "targetEndExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:534:2: ( (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==RULE_INT_GREATER_ZERO_OR_ALL) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:535:1: (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:535:1: (lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:536:3: lv_targetEndExecCount_12_0= RULE_INT_GREATER_ZERO_OR_ALL
                            {
                            lv_targetEndExecCount_12_0=(Token)match(input,RULE_INT_GREATER_ZERO_OR_ALL,FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleTwoLifelineMessage1064); 

                            			newLeafNode(lv_targetEndExecCount_12_0, grammarAccess.getTwoLifelineMessageAccess().getTargetEndExecCountINT_GREATER_ZERO_OR_ALLTerminalRuleCall_6_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getTwoLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"targetEndExecCount",
                                    		lv_targetEndExecCount_12_0, 
                                    		"INT_GREATER_ZERO_OR_ALL");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:552:6: (otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==23) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:552:8: otherlv_13= 'sourceNote' ( (lv_sourceNote_14_0= RULE_STRING ) )
                    {
                    otherlv_13=(Token)match(input,23,FOLLOW_23_in_ruleTwoLifelineMessage1086); 

                        	newLeafNode(otherlv_13, grammarAccess.getTwoLifelineMessageAccess().getSourceNoteKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:556:1: ( (lv_sourceNote_14_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:557:1: (lv_sourceNote_14_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:557:1: (lv_sourceNote_14_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:558:3: lv_sourceNote_14_0= RULE_STRING
                    {
                    lv_sourceNote_14_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1103); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:574:4: (otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==24) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:574:6: otherlv_15= 'targetNote' ( (lv_targetNote_16_0= RULE_STRING ) )
                    {
                    otherlv_15=(Token)match(input,24,FOLLOW_24_in_ruleTwoLifelineMessage1123); 

                        	newLeafNode(otherlv_15, grammarAccess.getTwoLifelineMessageAccess().getTargetNoteKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:578:1: ( (lv_targetNote_16_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:579:1: (lv_targetNote_16_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:579:1: (lv_targetNote_16_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:580:3: lv_targetNote_16_0= RULE_STRING
                    {
                    lv_targetNote_16_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1140); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:604:1: entryRuleOneLifelineMessage returns [EObject current=null] : iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF ;
    public final EObject entryRuleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOneLifelineMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:605:2: (iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:606:2: iv_ruleOneLifelineMessage= ruleOneLifelineMessage EOF
            {
             newCompositeNode(grammarAccess.getOneLifelineMessageRule()); 
            pushFollow(FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1183);
            iv_ruleOneLifelineMessage=ruleOneLifelineMessage();

            state._fsp--;

             current =iv_ruleOneLifelineMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOneLifelineMessage1193); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:613:1: ruleOneLifelineMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) ( (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleOneLifelineMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_message_3_0=null;
        Token lv_startEndExec_4_0=null;
        Token lv_startExec_5_0=null;
        Token lv_endExec_6_0=null;
        Token lv_endExecCount_7_0=null;
        Token otherlv_8=null;
        Token lv_note_9_0=null;
        Enumerator lv_messageType_1_0 = null;

        Enumerator lv_messageTypeLostAndFound_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:616:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) ( (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:617:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) ( (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:617:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) ( (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:617:2: ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) ( (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound ) ) ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:617:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:618:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:618:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:619:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOneLifelineMessage1238); 

            		newLeafNode(otherlv_0, grammarAccess.getOneLifelineMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:630:2: ( (lv_messageType_1_0= ruleMessageTypeOne ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:631:1: (lv_messageType_1_0= ruleMessageTypeOne )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:631:1: (lv_messageType_1_0= ruleMessageTypeOne )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:632:3: lv_messageType_1_0= ruleMessageTypeOne
            {
             
            	        newCompositeNode(grammarAccess.getOneLifelineMessageAccess().getMessageTypeMessageTypeOneEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleMessageTypeOne_in_ruleOneLifelineMessage1259);
            lv_messageType_1_0=ruleMessageTypeOne();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOneLifelineMessageRule());
            	        }
                   		set(
                   			current, 
                   			"messageType",
                    		lv_messageType_1_0, 
                    		"MessageTypeOne");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:648:2: ( (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:649:1: (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:649:1: (lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:650:3: lv_messageTypeLostAndFound_2_0= ruleMessageTypeLostAndFound
            {
             
            	        newCompositeNode(grammarAccess.getOneLifelineMessageAccess().getMessageTypeLostAndFoundMessageTypeLostAndFoundEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleMessageTypeLostAndFound_in_ruleOneLifelineMessage1280);
            lv_messageTypeLostAndFound_2_0=ruleMessageTypeLostAndFound();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOneLifelineMessageRule());
            	        }
                   		set(
                   			current, 
                   			"messageTypeLostAndFound",
                    		lv_messageTypeLostAndFound_2_0, 
                    		"MessageTypeLostAndFound");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:666:2: ( (lv_message_3_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:667:1: (lv_message_3_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:667:1: (lv_message_3_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:668:3: lv_message_3_0= RULE_STRING
            {
            lv_message_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1297); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:684:2: ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )?
            int alt12=4;
            switch ( input.LA(1) ) {
                case 25:
                    {
                    alt12=1;
                    }
                    break;
                case 26:
                    {
                    alt12=2;
                    }
                    break;
                case 27:
                    {
                    alt12=3;
                    }
                    break;
            }

            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:684:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:684:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:685:1: (lv_startEndExec_4_0= 'startEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:685:1: (lv_startEndExec_4_0= 'startEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:686:3: lv_startEndExec_4_0= 'startEndExec'
                    {
                    lv_startEndExec_4_0=(Token)match(input,25,FOLLOW_25_in_ruleOneLifelineMessage1321); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:700:6: ( (lv_startExec_5_0= 'startExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:700:6: ( (lv_startExec_5_0= 'startExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:701:1: (lv_startExec_5_0= 'startExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:701:1: (lv_startExec_5_0= 'startExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:702:3: lv_startExec_5_0= 'startExec'
                    {
                    lv_startExec_5_0=(Token)match(input,26,FOLLOW_26_in_ruleOneLifelineMessage1358); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:716:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:716:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:716:7: ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:716:7: ( (lv_endExec_6_0= 'endExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:717:1: (lv_endExec_6_0= 'endExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:717:1: (lv_endExec_6_0= 'endExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:718:3: lv_endExec_6_0= 'endExec'
                    {
                    lv_endExec_6_0=(Token)match(input,27,FOLLOW_27_in_ruleOneLifelineMessage1396); 

                            newLeafNode(lv_endExec_6_0, grammarAccess.getOneLifelineMessageAccess().getEndExecEndExecKeyword_4_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endExec", true, "endExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:731:2: ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==RULE_INT_GREATER_ZERO_OR_ALL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:732:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:732:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:733:3: lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL
                            {
                            lv_endExecCount_7_0=(Token)match(input,RULE_INT_GREATER_ZERO_OR_ALL,FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleOneLifelineMessage1426); 

                            			newLeafNode(lv_endExecCount_7_0, grammarAccess.getOneLifelineMessageAccess().getEndExecCountINT_GREATER_ZERO_OR_ALLTerminalRuleCall_4_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getOneLifelineMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endExecCount",
                                    		lv_endExecCount_7_0, 
                                    		"INT_GREATER_ZERO_OR_ALL");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:749:6: (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==28) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:749:8: otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,28,FOLLOW_28_in_ruleOneLifelineMessage1448); 

                        	newLeafNode(otherlv_8, grammarAccess.getOneLifelineMessageAccess().getNoteKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:753:1: ( (lv_note_9_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:754:1: (lv_note_9_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:754:1: (lv_note_9_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:755:3: lv_note_9_0= RULE_STRING
                    {
                    lv_note_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1465); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:779:1: entryRuleSelfMessage returns [EObject current=null] : iv_ruleSelfMessage= ruleSelfMessage EOF ;
    public final EObject entryRuleSelfMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelfMessage = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:780:2: (iv_ruleSelfMessage= ruleSelfMessage EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:781:2: iv_ruleSelfMessage= ruleSelfMessage EOF
            {
             newCompositeNode(grammarAccess.getSelfMessageRule()); 
            pushFollow(FOLLOW_ruleSelfMessage_in_entryRuleSelfMessage1508);
            iv_ruleSelfMessage=ruleSelfMessage();

            state._fsp--;

             current =iv_ruleSelfMessage; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelfMessage1518); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:788:1: ruleSelfMessage returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleSelfMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
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
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:791:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:792:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:792:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )? )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:792:2: ( (otherlv_0= RULE_ID ) ) ( (lv_messageType_1_0= ruleMessageTypeOne ) ) otherlv_2= 'self' ( (lv_message_3_0= RULE_STRING ) ) ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )? (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:792:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:793:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:793:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:794:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getSelfMessageRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSelfMessage1563); 

            		newLeafNode(otherlv_0, grammarAccess.getSelfMessageAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:805:2: ( (lv_messageType_1_0= ruleMessageTypeOne ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:806:1: (lv_messageType_1_0= ruleMessageTypeOne )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:806:1: (lv_messageType_1_0= ruleMessageTypeOne )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:807:3: lv_messageType_1_0= ruleMessageTypeOne
            {
             
            	        newCompositeNode(grammarAccess.getSelfMessageAccess().getMessageTypeMessageTypeOneEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleMessageTypeOne_in_ruleSelfMessage1584);
            lv_messageType_1_0=ruleMessageTypeOne();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelfMessageRule());
            	        }
                   		set(
                   			current, 
                   			"messageType",
                    		lv_messageType_1_0, 
                    		"MessageTypeOne");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,29,FOLLOW_29_in_ruleSelfMessage1596); 

                	newLeafNode(otherlv_2, grammarAccess.getSelfMessageAccess().getSelfKeyword_2());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:827:1: ( (lv_message_3_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:828:1: (lv_message_3_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:828:1: (lv_message_3_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:829:3: lv_message_3_0= RULE_STRING
            {
            lv_message_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSelfMessage1613); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:845:2: ( ( (lv_startEndExec_4_0= 'startEndExec' ) ) | ( (lv_startExec_5_0= 'startExec' ) ) | ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? ) )?
            int alt15=4;
            switch ( input.LA(1) ) {
                case 25:
                    {
                    alt15=1;
                    }
                    break;
                case 26:
                    {
                    alt15=2;
                    }
                    break;
                case 27:
                    {
                    alt15=3;
                    }
                    break;
            }

            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:845:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:845:3: ( (lv_startEndExec_4_0= 'startEndExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:846:1: (lv_startEndExec_4_0= 'startEndExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:846:1: (lv_startEndExec_4_0= 'startEndExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:847:3: lv_startEndExec_4_0= 'startEndExec'
                    {
                    lv_startEndExec_4_0=(Token)match(input,25,FOLLOW_25_in_ruleSelfMessage1637); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:861:6: ( (lv_startExec_5_0= 'startExec' ) )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:861:6: ( (lv_startExec_5_0= 'startExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:862:1: (lv_startExec_5_0= 'startExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:862:1: (lv_startExec_5_0= 'startExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:863:3: lv_startExec_5_0= 'startExec'
                    {
                    lv_startExec_5_0=(Token)match(input,26,FOLLOW_26_in_ruleSelfMessage1674); 

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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:877:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:877:6: ( ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )? )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:877:7: ( (lv_endExec_6_0= 'endExec' ) ) ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:877:7: ( (lv_endExec_6_0= 'endExec' ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:878:1: (lv_endExec_6_0= 'endExec' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:878:1: (lv_endExec_6_0= 'endExec' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:879:3: lv_endExec_6_0= 'endExec'
                    {
                    lv_endExec_6_0=(Token)match(input,27,FOLLOW_27_in_ruleSelfMessage1712); 

                            newLeafNode(lv_endExec_6_0, grammarAccess.getSelfMessageAccess().getEndExecEndExecKeyword_4_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSelfMessageRule());
                    	        }
                           		setWithLastConsumed(current, "endExec", true, "endExec");
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:892:2: ( (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==RULE_INT_GREATER_ZERO_OR_ALL) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:893:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            {
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:893:1: (lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL )
                            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:894:3: lv_endExecCount_7_0= RULE_INT_GREATER_ZERO_OR_ALL
                            {
                            lv_endExecCount_7_0=(Token)match(input,RULE_INT_GREATER_ZERO_OR_ALL,FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleSelfMessage1742); 

                            			newLeafNode(lv_endExecCount_7_0, grammarAccess.getSelfMessageAccess().getEndExecCountINT_GREATER_ZERO_OR_ALLTerminalRuleCall_4_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getSelfMessageRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"endExecCount",
                                    		lv_endExecCount_7_0, 
                                    		"INT_GREATER_ZERO_OR_ALL");
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:910:6: (otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==28) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:910:8: otherlv_8= 'note' ( (lv_note_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,28,FOLLOW_28_in_ruleSelfMessage1764); 

                        	newLeafNode(otherlv_8, grammarAccess.getSelfMessageAccess().getNoteKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:914:1: ( (lv_note_9_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:915:1: (lv_note_9_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:915:1: (lv_note_9_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:916:3: lv_note_9_0= RULE_STRING
                    {
                    lv_note_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSelfMessage1781); 

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


    // $ANTLR start "entryRuleDestroyLifelineEvent"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:940:1: entryRuleDestroyLifelineEvent returns [EObject current=null] : iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF ;
    public final EObject entryRuleDestroyLifelineEvent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDestroyLifelineEvent = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:941:2: (iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:942:2: iv_ruleDestroyLifelineEvent= ruleDestroyLifelineEvent EOF
            {
             newCompositeNode(grammarAccess.getDestroyLifelineEventRule()); 
            pushFollow(FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent1824);
            iv_ruleDestroyLifelineEvent=ruleDestroyLifelineEvent();

            state._fsp--;

             current =iv_ruleDestroyLifelineEvent; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDestroyLifelineEvent1834); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:949:1: ruleDestroyLifelineEvent returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' ) ;
    public final EObject ruleDestroyLifelineEvent() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:952:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:953:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:953:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:953:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= 'destroy'
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:953:2: ( (otherlv_0= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:954:1: (otherlv_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:954:1: (otherlv_0= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:955:3: otherlv_0= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDestroyLifelineEventRule());
            	        }
                    
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDestroyLifelineEvent1879); 

            		newLeafNode(otherlv_0, grammarAccess.getDestroyLifelineEventAccess().getLifelineLifelineCrossReference_0_0()); 
            	

            }


            }

            otherlv_1=(Token)match(input,30,FOLLOW_30_in_ruleDestroyLifelineEvent1891); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:978:1: entryRuleFragment returns [EObject current=null] : iv_ruleFragment= ruleFragment EOF ;
    public final EObject entryRuleFragment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFragment = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:979:2: (iv_ruleFragment= ruleFragment EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:980:2: iv_ruleFragment= ruleFragment EOF
            {
             newCompositeNode(grammarAccess.getFragmentRule()); 
            pushFollow(FOLLOW_ruleFragment_in_entryRuleFragment1927);
            iv_ruleFragment=ruleFragment();

            state._fsp--;

             current =iv_ruleFragment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFragment1937); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:987:1: ruleFragment returns [EObject current=null] : (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* ) ;
    public final EObject ruleFragment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        EObject lv_sections_2_0 = null;

        EObject lv_sections_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:990:28: ( (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:991:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:991:1: (otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:991:3: otherlv_0= 'fragment' ( (lv_name_1_0= RULE_STRING ) ) ( (lv_sections_2_0= ruleSection ) ) ( (lv_sections_3_0= ruleSection ) )*
            {
            otherlv_0=(Token)match(input,31,FOLLOW_31_in_ruleFragment1974); 

                	newLeafNode(otherlv_0, grammarAccess.getFragmentAccess().getFragmentKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:995:1: ( (lv_name_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:996:1: (lv_name_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:996:1: (lv_name_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:997:3: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFragment1991); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1013:2: ( (lv_sections_2_0= ruleSection ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1014:1: (lv_sections_2_0= ruleSection )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1014:1: (lv_sections_2_0= ruleSection )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1015:3: lv_sections_2_0= ruleSection
            {
             
            	        newCompositeNode(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSection_in_ruleFragment2017);
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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1031:2: ( (lv_sections_3_0= ruleSection ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==32) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1032:1: (lv_sections_3_0= ruleSection )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1032:1: (lv_sections_3_0= ruleSection )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1033:3: lv_sections_3_0= ruleSection
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFragmentAccess().getSectionsSectionParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSection_in_ruleFragment2038);
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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1057:1: entryRuleSection returns [EObject current=null] : iv_ruleSection= ruleSection EOF ;
    public final EObject entryRuleSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSection = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1058:2: (iv_ruleSection= ruleSection EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1059:2: iv_ruleSection= ruleSection EOF
            {
             newCompositeNode(grammarAccess.getSectionRule()); 
            pushFollow(FOLLOW_ruleSection_in_entryRuleSection2075);
            iv_ruleSection=ruleSection();

            state._fsp--;

             current =iv_ruleSection; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSection2085); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1066:1: ruleSection returns [EObject current=null] : (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) ;
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
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1069:28: ( (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1070:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1070:1: (otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}' )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1070:3: otherlv_0= '{' (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )? ( (lv_interactions_3_0= ruleInteraction ) ) ( (lv_interactions_4_0= ruleInteraction ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleSection2122); 

                	newLeafNode(otherlv_0, grammarAccess.getSectionAccess().getLeftCurlyBracketKeyword_0());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1074:1: (otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==33) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1074:3: otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) )
                    {
                    otherlv_1=(Token)match(input,33,FOLLOW_33_in_ruleSection2135); 

                        	newLeafNode(otherlv_1, grammarAccess.getSectionAccess().getLabelKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1078:1: ( (lv_label_2_0= RULE_STRING ) )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1079:1: (lv_label_2_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1079:1: (lv_label_2_0= RULE_STRING )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1080:3: lv_label_2_0= RULE_STRING
                    {
                    lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSection2152); 

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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1096:4: ( (lv_interactions_3_0= ruleInteraction ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1097:1: (lv_interactions_3_0= ruleInteraction )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1097:1: (lv_interactions_3_0= ruleInteraction )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1098:3: lv_interactions_3_0= ruleInteraction
            {
             
            	        newCompositeNode(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleInteraction_in_ruleSection2180);
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

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1114:2: ( (lv_interactions_4_0= ruleInteraction ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID||LA19_0==31||LA19_0==35) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1115:1: (lv_interactions_4_0= ruleInteraction )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1115:1: (lv_interactions_4_0= ruleInteraction )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1116:3: lv_interactions_4_0= ruleInteraction
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSectionAccess().getInteractionsInteractionParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInteraction_in_ruleSection2201);
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

            otherlv_5=(Token)match(input,34,FOLLOW_34_in_ruleSection2214); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1144:1: entryRuleRefinement returns [EObject current=null] : iv_ruleRefinement= ruleRefinement EOF ;
    public final EObject entryRuleRefinement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRefinement = null;


        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1145:2: (iv_ruleRefinement= ruleRefinement EOF )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1146:2: iv_ruleRefinement= ruleRefinement EOF
            {
             newCompositeNode(grammarAccess.getRefinementRule()); 
            pushFollow(FOLLOW_ruleRefinement_in_entryRuleRefinement2250);
            iv_ruleRefinement=ruleRefinement();

            state._fsp--;

             current =iv_ruleRefinement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRefinement2260); 

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
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1153:1: ruleRefinement returns [EObject current=null] : (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* ) ;
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
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1156:28: ( (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1157:1: (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1157:1: (otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1157:3: otherlv_0= 'refinement' otherlv_1= 'label' ( (lv_label_2_0= RULE_STRING ) ) otherlv_3= 'lifelines' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            {
            otherlv_0=(Token)match(input,35,FOLLOW_35_in_ruleRefinement2297); 

                	newLeafNode(otherlv_0, grammarAccess.getRefinementAccess().getRefinementKeyword_0());
                
            otherlv_1=(Token)match(input,33,FOLLOW_33_in_ruleRefinement2309); 

                	newLeafNode(otherlv_1, grammarAccess.getRefinementAccess().getLabelKeyword_1());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1165:1: ( (lv_label_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1166:1: (lv_label_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1166:1: (lv_label_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1167:3: lv_label_2_0= RULE_STRING
            {
            lv_label_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleRefinement2326); 

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

            otherlv_3=(Token)match(input,36,FOLLOW_36_in_ruleRefinement2343); 

                	newLeafNode(otherlv_3, grammarAccess.getRefinementAccess().getLifelinesKeyword_3());
                
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1187:1: ( (otherlv_4= RULE_ID ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1188:1: (otherlv_4= RULE_ID )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1188:1: (otherlv_4= RULE_ID )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1189:3: otherlv_4= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getRefinementRule());
            	        }
                    
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2363); 

            		newLeafNode(otherlv_4, grammarAccess.getRefinementAccess().getLifelinesLifelineCrossReference_4_0()); 
            	

            }


            }

            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1200:2: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==37) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1200:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
            	    {
            	    otherlv_5=(Token)match(input,37,FOLLOW_37_in_ruleRefinement2376); 

            	        	newLeafNode(otherlv_5, grammarAccess.getRefinementAccess().getCommaKeyword_5_0());
            	        
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1204:1: ( (otherlv_6= RULE_ID ) )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1205:1: (otherlv_6= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1205:1: (otherlv_6= RULE_ID )
            	    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1206:3: otherlv_6= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getRefinementRule());
            	    	        }
            	            
            	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRefinement2396); 

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


    // $ANTLR start "ruleMessageTypeOne"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1225:1: ruleMessageTypeOne returns [Enumerator current=null] : ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) ) ;
    public final Enumerator ruleMessageTypeOne() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1227:28: ( ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) )
            int alt21=3;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt21=1;
                }
                break;
            case 39:
                {
                alt21=2;
                }
                break;
            case 40:
                {
                alt21=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:2: (enumLiteral_0= 'async' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:2: (enumLiteral_0= 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1228:4: enumLiteral_0= 'async'
                    {
                    enumLiteral_0=(Token)match(input,38,FOLLOW_38_in_ruleMessageTypeOne2448); 

                            current = grammarAccess.getMessageTypeOneAccess().getAsyncEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getMessageTypeOneAccess().getAsyncEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1234:6: (enumLiteral_1= 'response' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1234:6: (enumLiteral_1= 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1234:8: enumLiteral_1= 'response'
                    {
                    enumLiteral_1=(Token)match(input,39,FOLLOW_39_in_ruleMessageTypeOne2465); 

                            current = grammarAccess.getMessageTypeOneAccess().getResponseEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getMessageTypeOneAccess().getResponseEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1240:6: (enumLiteral_2= 'sync' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1240:6: (enumLiteral_2= 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1240:8: enumLiteral_2= 'sync'
                    {
                    enumLiteral_2=(Token)match(input,40,FOLLOW_40_in_ruleMessageTypeOne2482); 

                            current = grammarAccess.getMessageTypeOneAccess().getSyncEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getMessageTypeOneAccess().getSyncEnumLiteralDeclaration_2()); 
                        

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
    // $ANTLR end "ruleMessageTypeOne"


    // $ANTLR start "ruleMessageTypeTwo"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1250:1: ruleMessageTypeTwo returns [Enumerator current=null] : ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) | (enumLiteral_3= 'create' ) ) ;
    public final Enumerator ruleMessageTypeTwo() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1252:28: ( ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) | (enumLiteral_3= 'create' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1253:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) | (enumLiteral_3= 'create' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1253:1: ( (enumLiteral_0= 'async' ) | (enumLiteral_1= 'response' ) | (enumLiteral_2= 'sync' ) | (enumLiteral_3= 'create' ) )
            int alt22=4;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt22=1;
                }
                break;
            case 39:
                {
                alt22=2;
                }
                break;
            case 40:
                {
                alt22=3;
                }
                break;
            case 41:
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
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1253:2: (enumLiteral_0= 'async' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1253:2: (enumLiteral_0= 'async' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1253:4: enumLiteral_0= 'async'
                    {
                    enumLiteral_0=(Token)match(input,38,FOLLOW_38_in_ruleMessageTypeTwo2527); 

                            current = grammarAccess.getMessageTypeTwoAccess().getAsyncEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getMessageTypeTwoAccess().getAsyncEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1259:6: (enumLiteral_1= 'response' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1259:6: (enumLiteral_1= 'response' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1259:8: enumLiteral_1= 'response'
                    {
                    enumLiteral_1=(Token)match(input,39,FOLLOW_39_in_ruleMessageTypeTwo2544); 

                            current = grammarAccess.getMessageTypeTwoAccess().getResponseEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getMessageTypeTwoAccess().getResponseEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1265:6: (enumLiteral_2= 'sync' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1265:6: (enumLiteral_2= 'sync' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1265:8: enumLiteral_2= 'sync'
                    {
                    enumLiteral_2=(Token)match(input,40,FOLLOW_40_in_ruleMessageTypeTwo2561); 

                            current = grammarAccess.getMessageTypeTwoAccess().getSyncEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getMessageTypeTwoAccess().getSyncEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1271:6: (enumLiteral_3= 'create' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1271:6: (enumLiteral_3= 'create' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1271:8: enumLiteral_3= 'create'
                    {
                    enumLiteral_3=(Token)match(input,41,FOLLOW_41_in_ruleMessageTypeTwo2578); 

                            current = grammarAccess.getMessageTypeTwoAccess().getCreateEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getMessageTypeTwoAccess().getCreateEnumLiteralDeclaration_3()); 
                        

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
    // $ANTLR end "ruleMessageTypeTwo"


    // $ANTLR start "ruleMessageTypeLostAndFound"
    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1281:1: ruleMessageTypeLostAndFound returns [Enumerator current=null] : ( (enumLiteral_0= 'lost' ) | (enumLiteral_1= 'found' ) ) ;
    public final Enumerator ruleMessageTypeLostAndFound() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1283:28: ( ( (enumLiteral_0= 'lost' ) | (enumLiteral_1= 'found' ) ) )
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1284:1: ( (enumLiteral_0= 'lost' ) | (enumLiteral_1= 'found' ) )
            {
            // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1284:1: ( (enumLiteral_0= 'lost' ) | (enumLiteral_1= 'found' ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==42) ) {
                alt23=1;
            }
            else if ( (LA23_0==43) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1284:2: (enumLiteral_0= 'lost' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1284:2: (enumLiteral_0= 'lost' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1284:4: enumLiteral_0= 'lost'
                    {
                    enumLiteral_0=(Token)match(input,42,FOLLOW_42_in_ruleMessageTypeLostAndFound2623); 

                            current = grammarAccess.getMessageTypeLostAndFoundAccess().getLostEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getMessageTypeLostAndFoundAccess().getLostEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1290:6: (enumLiteral_1= 'found' )
                    {
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1290:6: (enumLiteral_1= 'found' )
                    // ../de.cau.cs.kieler.uml.sequence.text/src-gen/de/cau/cs/kieler/uml/sequence/text/parser/antlr/internal/InternalSequence.g:1290:8: enumLiteral_1= 'found'
                    {
                    enumLiteral_1=(Token)match(input,43,FOLLOW_43_in_ruleMessageTypeLostAndFound2640); 

                            current = grammarAccess.getMessageTypeLostAndFoundAccess().getFoundEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getMessageTypeLostAndFoundAccess().getFoundEnumLiteralDeclaration_1()); 
                        

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
    // $ANTLR end "ruleMessageTypeLostAndFound"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA4_eotS =
        "\13\uffff";
    static final String DFA4_eofS =
        "\13\uffff";
    static final String DFA4_minS =
        "\1\5\1\36\2\uffff\3\4\4\uffff";
    static final String DFA4_maxS =
        "\1\43\1\51\2\uffff\3\53\4\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\3\1\6\3\uffff\1\5\1\1\1\2\1\4";
    static final String DFA4_specialS =
        "\13\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\31\uffff\1\2\3\uffff\1\3",
            "\1\7\7\uffff\1\4\1\5\1\6\1\10",
            "",
            "",
            "\1\10\30\uffff\1\12\14\uffff\2\11",
            "\1\10\30\uffff\1\12\14\uffff\2\11",
            "\1\10\30\uffff\1\12\14\uffff\2\11",
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
            return "276:1: (this_TwoLifelineMessage_0= ruleTwoLifelineMessage | this_OneLifelineMessage_1= ruleOneLifelineMessage | this_Fragment_2= ruleFragment | this_SelfMessage_3= ruleSelfMessage | this_DestroyLifelineEvent_4= ruleDestroyLifelineEvent | this_Refinement_5= ruleRefinement )";
        }
    }
 

    public static final BitSet FOLLOW_ruleSequenceDiagram_in_entryRuleSequenceDiagram75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceDiagram85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleSequenceDiagram131 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSequenceDiagram148 = new BitSet(new long[]{0x000000088000A022L});
    public static final BitSet FOLLOW_ruleLifeline_in_ruleSequenceDiagram174 = new BitSet(new long[]{0x000000088000A022L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSequenceDiagram196 = new BitSet(new long[]{0x0000000880000022L});
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
    public static final BitSet FOLLOW_ruleSelfMessage_in_ruleInteraction588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_ruleInteraction615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_ruleInteraction642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTwoLifelineMessage_in_entryRuleTwoLifelineMessage677 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTwoLifelineMessage687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage732 = new BitSet(new long[]{0x000003C000000000L});
    public static final BitSet FOLLOW_ruleMessageTypeTwo_in_ruleTwoLifelineMessage753 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage770 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTwoLifelineMessage787 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTwoLifelineMessage807 = new BitSet(new long[]{0x0000000001FE0002L});
    public static final BitSet FOLLOW_17_in_ruleTwoLifelineMessage826 = new BitSet(new long[]{0x0000000001F00002L});
    public static final BitSet FOLLOW_18_in_ruleTwoLifelineMessage863 = new BitSet(new long[]{0x0000000001F00002L});
    public static final BitSet FOLLOW_19_in_ruleTwoLifelineMessage901 = new BitSet(new long[]{0x0000000001F00042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleTwoLifelineMessage931 = new BitSet(new long[]{0x0000000001F00002L});
    public static final BitSet FOLLOW_20_in_ruleTwoLifelineMessage959 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_21_in_ruleTwoLifelineMessage996 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_22_in_ruleTwoLifelineMessage1034 = new BitSet(new long[]{0x0000000001800042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleTwoLifelineMessage1064 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_23_in_ruleTwoLifelineMessage1086 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1103 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleTwoLifelineMessage1123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleTwoLifelineMessage1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOneLifelineMessage_in_entryRuleOneLifelineMessage1183 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOneLifelineMessage1193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOneLifelineMessage1238 = new BitSet(new long[]{0x000001C000000000L});
    public static final BitSet FOLLOW_ruleMessageTypeOne_in_ruleOneLifelineMessage1259 = new BitSet(new long[]{0x00000C0000000000L});
    public static final BitSet FOLLOW_ruleMessageTypeLostAndFound_in_ruleOneLifelineMessage1280 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1297 = new BitSet(new long[]{0x000000001E000002L});
    public static final BitSet FOLLOW_25_in_ruleOneLifelineMessage1321 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_26_in_ruleOneLifelineMessage1358 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_27_in_ruleOneLifelineMessage1396 = new BitSet(new long[]{0x0000000010000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleOneLifelineMessage1426 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleOneLifelineMessage1448 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleOneLifelineMessage1465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelfMessage_in_entryRuleSelfMessage1508 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelfMessage1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSelfMessage1563 = new BitSet(new long[]{0x000001C000000000L});
    public static final BitSet FOLLOW_ruleMessageTypeOne_in_ruleSelfMessage1584 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleSelfMessage1596 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSelfMessage1613 = new BitSet(new long[]{0x000000001E000002L});
    public static final BitSet FOLLOW_25_in_ruleSelfMessage1637 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_26_in_ruleSelfMessage1674 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_27_in_ruleSelfMessage1712 = new BitSet(new long[]{0x0000000010000042L});
    public static final BitSet FOLLOW_RULE_INT_GREATER_ZERO_OR_ALL_in_ruleSelfMessage1742 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleSelfMessage1764 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSelfMessage1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDestroyLifelineEvent_in_entryRuleDestroyLifelineEvent1824 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDestroyLifelineEvent1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDestroyLifelineEvent1879 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleDestroyLifelineEvent1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFragment_in_entryRuleFragment1927 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFragment1937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleFragment1974 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFragment1991 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ruleSection_in_ruleFragment2017 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_ruleSection_in_ruleFragment2038 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_ruleSection_in_entryRuleSection2075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSection2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleSection2122 = new BitSet(new long[]{0x0000000A80000020L});
    public static final BitSet FOLLOW_33_in_ruleSection2135 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSection2152 = new BitSet(new long[]{0x0000000880000020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSection2180 = new BitSet(new long[]{0x0000000C80000020L});
    public static final BitSet FOLLOW_ruleInteraction_in_ruleSection2201 = new BitSet(new long[]{0x0000000C80000020L});
    public static final BitSet FOLLOW_34_in_ruleSection2214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRefinement_in_entryRuleRefinement2250 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRefinement2260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleRefinement2297 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleRefinement2309 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleRefinement2326 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleRefinement2343 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2363 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_ruleRefinement2376 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRefinement2396 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_38_in_ruleMessageTypeOne2448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleMessageTypeOne2465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleMessageTypeOne2482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleMessageTypeTwo2527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleMessageTypeTwo2544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleMessageTypeTwo2561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleMessageTypeTwo2578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleMessageTypeLostAndFound2623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleMessageTypeLostAndFound2640 = new BitSet(new long[]{0x0000000000000002L});

}