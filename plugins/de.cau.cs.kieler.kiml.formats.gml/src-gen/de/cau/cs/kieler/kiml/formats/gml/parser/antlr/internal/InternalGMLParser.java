package de.cau.cs.kieler.kiml.formats.gml.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.kiml.formats.gml.services.GMLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGMLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_GML_INT", "RULE_GML_FLOAT", "RULE_STRING", "RULE_PREC_LINE", "RULE_WS", "'['", "']'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=7;
    public static final int T__11=11;
    public static final int T__10=10;
    public static final int RULE_GML_FLOAT=6;
    public static final int RULE_PREC_LINE=8;
    public static final int RULE_WS=9;
    public static final int EOF=-1;
    public static final int RULE_GML_INT=5;

    // delegates
    // delegators


        public InternalGMLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalGMLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalGMLParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g"; }



     	private GMLGrammarAccess grammarAccess;
     	
        public InternalGMLParser(TokenStream input, GMLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "GmlModel";	
       	}
       	
       	@Override
       	protected GMLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleGmlModel"
    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:67:1: entryRuleGmlModel returns [EObject current=null] : iv_ruleGmlModel= ruleGmlModel EOF ;
    public final EObject entryRuleGmlModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGmlModel = null;


        try {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:68:2: (iv_ruleGmlModel= ruleGmlModel EOF )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:69:2: iv_ruleGmlModel= ruleGmlModel EOF
            {
             newCompositeNode(grammarAccess.getGmlModelRule()); 
            pushFollow(FOLLOW_ruleGmlModel_in_entryRuleGmlModel75);
            iv_ruleGmlModel=ruleGmlModel();

            state._fsp--;

             current =iv_ruleGmlModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGmlModel85); 

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
    // $ANTLR end "entryRuleGmlModel"


    // $ANTLR start "ruleGmlModel"
    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:76:1: ruleGmlModel returns [EObject current=null] : ( (lv_elements_0_0= ruleElement ) )* ;
    public final EObject ruleGmlModel() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:79:28: ( ( (lv_elements_0_0= ruleElement ) )* )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:80:1: ( (lv_elements_0_0= ruleElement ) )*
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:80:1: ( (lv_elements_0_0= ruleElement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:81:1: (lv_elements_0_0= ruleElement )
            	    {
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:81:1: (lv_elements_0_0= ruleElement )
            	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:82:3: lv_elements_0_0= ruleElement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGmlModelAccess().getElementsElementParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleElement_in_ruleGmlModel130);
            	    lv_elements_0_0=ruleElement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGmlModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"elements",
            	            		lv_elements_0_0, 
            	            		"Element");
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
    // $ANTLR end "ruleGmlModel"


    // $ANTLR start "entryRuleElement"
    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:106:1: entryRuleElement returns [EObject current=null] : iv_ruleElement= ruleElement EOF ;
    public final EObject entryRuleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement = null;


        try {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:107:2: (iv_ruleElement= ruleElement EOF )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:108:2: iv_ruleElement= ruleElement EOF
            {
             newCompositeNode(grammarAccess.getElementRule()); 
            pushFollow(FOLLOW_ruleElement_in_entryRuleElement166);
            iv_ruleElement=ruleElement();

            state._fsp--;

             current =iv_ruleElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElement176); 

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
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:115:1: ruleElement returns [EObject current=null] : ( ( (lv_key_0_0= RULE_ID ) ) ( ( (lv_value_1_0= ruleValue ) ) | (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' ) ) ) ;
    public final EObject ruleElement() throws RecognitionException {
        EObject current = null;

        Token lv_key_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_value_1_0 = null;

        EObject lv_elements_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:118:28: ( ( ( (lv_key_0_0= RULE_ID ) ) ( ( (lv_value_1_0= ruleValue ) ) | (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' ) ) ) )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:119:1: ( ( (lv_key_0_0= RULE_ID ) ) ( ( (lv_value_1_0= ruleValue ) ) | (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' ) ) )
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:119:1: ( ( (lv_key_0_0= RULE_ID ) ) ( ( (lv_value_1_0= ruleValue ) ) | (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' ) ) )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:119:2: ( (lv_key_0_0= RULE_ID ) ) ( ( (lv_value_1_0= ruleValue ) ) | (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' ) )
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:119:2: ( (lv_key_0_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:120:1: (lv_key_0_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:120:1: (lv_key_0_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:121:3: lv_key_0_0= RULE_ID
            {
            lv_key_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleElement218); 

            			newLeafNode(lv_key_0_0, grammarAccess.getElementAccess().getKeyIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getElementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"key",
                    		lv_key_0_0, 
                    		"ID");
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:137:2: ( ( (lv_value_1_0= ruleValue ) ) | (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=RULE_GML_INT && LA3_0<=RULE_STRING)) ) {
                alt3=1;
            }
            else if ( (LA3_0==10) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:137:3: ( (lv_value_1_0= ruleValue ) )
                    {
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:137:3: ( (lv_value_1_0= ruleValue ) )
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:138:1: (lv_value_1_0= ruleValue )
                    {
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:138:1: (lv_value_1_0= ruleValue )
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:139:3: lv_value_1_0= ruleValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getElementAccess().getValueValueParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleValue_in_ruleElement245);
                    lv_value_1_0=ruleValue();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getElementRule());
                    	        }
                           		set(
                           			current, 
                           			"value",
                            		lv_value_1_0, 
                            		"Value");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:156:6: (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' )
                    {
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:156:6: (otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']' )
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:156:8: otherlv_2= '[' ( (lv_elements_3_0= ruleElement ) )* otherlv_4= ']'
                    {
                    otherlv_2=(Token)match(input,10,FOLLOW_10_in_ruleElement264); 

                        	newLeafNode(otherlv_2, grammarAccess.getElementAccess().getLeftSquareBracketKeyword_1_1_0());
                        
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:160:1: ( (lv_elements_3_0= ruleElement ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==RULE_ID) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:161:1: (lv_elements_3_0= ruleElement )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:161:1: (lv_elements_3_0= ruleElement )
                    	    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:162:3: lv_elements_3_0= ruleElement
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElementAccess().getElementsElementParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleElement_in_ruleElement285);
                    	    lv_elements_3_0=ruleElement();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElementRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"elements",
                    	            		lv_elements_3_0, 
                    	            		"Element");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,11,FOLLOW_11_in_ruleElement298); 

                        	newLeafNode(otherlv_4, grammarAccess.getElementAccess().getRightSquareBracketKeyword_1_1_2());
                        

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
    // $ANTLR end "ruleElement"


    // $ANTLR start "entryRuleValue"
    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:190:1: entryRuleValue returns [String current=null] : iv_ruleValue= ruleValue EOF ;
    public final String entryRuleValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleValue = null;


        try {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:191:2: (iv_ruleValue= ruleValue EOF )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:192:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_ruleValue_in_entryRuleValue337);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleValue348); 

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
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:199:1: ruleValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_GML_INT_0= RULE_GML_INT | this_GML_FLOAT_1= RULE_GML_FLOAT | this_STRING_2= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_GML_INT_0=null;
        Token this_GML_FLOAT_1=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:202:28: ( (this_GML_INT_0= RULE_GML_INT | this_GML_FLOAT_1= RULE_GML_FLOAT | this_STRING_2= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:203:1: (this_GML_INT_0= RULE_GML_INT | this_GML_FLOAT_1= RULE_GML_FLOAT | this_STRING_2= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:203:1: (this_GML_INT_0= RULE_GML_INT | this_GML_FLOAT_1= RULE_GML_FLOAT | this_STRING_2= RULE_STRING )
            int alt4=3;
            switch ( input.LA(1) ) {
            case RULE_GML_INT:
                {
                alt4=1;
                }
                break;
            case RULE_GML_FLOAT:
                {
                alt4=2;
                }
                break;
            case RULE_STRING:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:203:6: this_GML_INT_0= RULE_GML_INT
                    {
                    this_GML_INT_0=(Token)match(input,RULE_GML_INT,FOLLOW_RULE_GML_INT_in_ruleValue388); 

                    		current.merge(this_GML_INT_0);
                        
                     
                        newLeafNode(this_GML_INT_0, grammarAccess.getValueAccess().getGML_INTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:211:10: this_GML_FLOAT_1= RULE_GML_FLOAT
                    {
                    this_GML_FLOAT_1=(Token)match(input,RULE_GML_FLOAT,FOLLOW_RULE_GML_FLOAT_in_ruleValue414); 

                    		current.merge(this_GML_FLOAT_1);
                        
                     
                        newLeafNode(this_GML_FLOAT_1, grammarAccess.getValueAccess().getGML_FLOATTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.formats.gml/src-gen/de/cau/cs/kieler/kiml/formats/gml/parser/antlr/internal/InternalGML.g:219:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleValue440); 

                    		current.merge(this_STRING_2);
                        
                     
                        newLeafNode(this_STRING_2, grammarAccess.getValueAccess().getSTRINGTerminalRuleCall_2()); 
                        

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
    // $ANTLR end "ruleValue"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleGmlModel_in_entryRuleGmlModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGmlModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleGmlModel130 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleElement_in_entryRuleElement166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElement176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleElement218 = new BitSet(new long[]{0x00000000000004E0L});
    public static final BitSet FOLLOW_ruleValue_in_ruleElement245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ruleElement264 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElement285 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_11_in_ruleElement298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValue_in_entryRuleValue337 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValue348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_GML_INT_in_ruleValue388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_GML_FLOAT_in_ruleValue414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleValue440 = new BitSet(new long[]{0x0000000000000002L});

}