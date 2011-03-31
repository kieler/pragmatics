package de.cau.cs.kieler.kaom.text.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import de.cau.cs.kieler.kaom.text.services.KaomGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKaomParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_COMMENT_ANNOTATION", "RULE_BOOLEAN", "RULE_INT", "RULE_FLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'entity'", "'{'", "'}'", "';'", "'link'", "'to'", "'port'", "'relation'", "'@'"
    };
    public static final int RULE_BOOLEAN=7;
    public static final int RULE_ID=4;
    public static final int RULE_STRING=5;
    public static final int RULE_ANY_OTHER=13;
    public static final int RULE_INT=8;
    public static final int RULE_WS=12;
    public static final int RULE_FLOAT=9;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_COMMENT_ANNOTATION=6;
    public static final int RULE_ML_COMMENT=10;

        public InternalKaomParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g"; }



     	private KaomGrammarAccess grammarAccess;
     	
        public InternalKaomParser(TokenStream input, IAstFactory factory, KaomGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "TopLevelEntity";	
       	}
       	
       	@Override
       	protected KaomGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleTopLevelEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:77:1: entryRuleTopLevelEntity returns [EObject current=null] : iv_ruleTopLevelEntity= ruleTopLevelEntity EOF ;
    public final EObject entryRuleTopLevelEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTopLevelEntity = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:78:2: (iv_ruleTopLevelEntity= ruleTopLevelEntity EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:79:2: iv_ruleTopLevelEntity= ruleTopLevelEntity EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTopLevelEntityRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity75);
            iv_ruleTopLevelEntity=ruleTopLevelEntity();
            _fsp--;

             current =iv_ruleTopLevelEntity; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTopLevelEntity85); 

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
    // $ANTLR end entryRuleTopLevelEntity


    // $ANTLR start ruleTopLevelEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:86:1: ruleTopLevelEntity returns [EObject current=null] : ( () ( ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )? ) ;
    public final EObject ruleTopLevelEntity() throws RecognitionException {
        EObject current = null;

        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        EObject lv_annotations_1_0 = null;

        EObject lv_childEntities_6_0 = null;

        EObject lv_childLinks_7_0 = null;

        EObject lv_childPorts_8_0 = null;

        EObject lv_childRelations_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:91:6: ( ( () ( ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:92:1: ( () ( ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:92:1: ( () ( ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:92:2: () ( ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )?
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:92:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:93:5: 
            {
             
                    temp=factory.create(grammarAccess.getTopLevelEntityAccess().getEntityAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getTopLevelEntityAccess().getEntityAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:103:2: ( ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_COMMENT_ANNOTATION||LA5_0==14||LA5_0==22) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:103:3: ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:103:3: ( (lv_annotations_1_0= ruleAnnotation ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_COMMENT_ANNOTATION||LA1_0==22) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:104:1: (lv_annotations_1_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:104:1: (lv_annotations_1_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:105:3: lv_annotations_1_0= ruleAnnotation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTopLevelEntity141);
                    	    lv_annotations_1_0=ruleAnnotation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"annotations",
                    	    	        		lv_annotations_1_0, 
                    	    	        		"Annotation", 
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

                    match(input,14,FollowSets000.FOLLOW_14_in_ruleTopLevelEntity152); 

                            createLeafNode(grammarAccess.getTopLevelEntityAccess().getEntityKeyword_1_1(), null); 
                        
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:131:1: ( (lv_id_3_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:132:1: (lv_id_3_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:132:1: (lv_id_3_0= RULE_ID )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:133:3: lv_id_3_0= RULE_ID
                    {
                    lv_id_3_0=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleTopLevelEntity169); 

                    			createLeafNode(grammarAccess.getTopLevelEntityAccess().getIdIDTerminalRuleCall_1_2_0(), "id"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"id",
                    	        		lv_id_3_0, 
                    	        		"ID", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:155:2: ( (lv_name_4_0= RULE_STRING ) )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==RULE_STRING) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:156:1: (lv_name_4_0= RULE_STRING )
                            {
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:156:1: (lv_name_4_0= RULE_STRING )
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:157:3: lv_name_4_0= RULE_STRING
                            {
                            lv_name_4_0=(Token)input.LT(1);
                            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleTopLevelEntity191); 

                            			createLeafNode(grammarAccess.getTopLevelEntityAccess().getNameSTRINGTerminalRuleCall_1_3_0(), "name"); 
                            		

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"name",
                            	        		lv_name_4_0, 
                            	        		"STRING", 
                            	        		lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:179:3: ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==15) ) {
                        alt4=1;
                    }
                    else if ( (LA4_0==17) ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("179:3: ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )", 4, 0, input);

                        throw nvae;
                    }
                    switch (alt4) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:179:4: ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' )
                            {
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:179:4: ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' )
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:179:6: '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}'
                            {
                            match(input,15,FollowSets000.FOLLOW_15_in_ruleTopLevelEntity209); 

                                    createLeafNode(grammarAccess.getTopLevelEntityAccess().getLeftCurlyBracketKeyword_1_4_0_0(), null); 
                                
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:183:1: ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )*
                            loop3:
                            do {
                                int alt3=5;
                                alt3 = dfa3.predict(input);
                                switch (alt3) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:183:2: ( (lv_childEntities_6_0= ruleEntity ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:183:2: ( (lv_childEntities_6_0= ruleEntity ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:184:1: (lv_childEntities_6_0= ruleEntity )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:184:1: (lv_childEntities_6_0= ruleEntity )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:185:3: lv_childEntities_6_0= ruleEntity
                            	    {
                            	     
                            	    	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_1_4_0_1_0_0(), currentNode); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleEntity_in_ruleTopLevelEntity231);
                            	    lv_childEntities_6_0=ruleEntity();
                            	    _fsp--;


                            	    	        if (current==null) {
                            	    	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	    	        }
                            	    	        try {
                            	    	       		add(
                            	    	       			current, 
                            	    	       			"childEntities",
                            	    	        		lv_childEntities_6_0, 
                            	    	        		"Entity", 
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
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:208:6: ( (lv_childLinks_7_0= ruleLink ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:208:6: ( (lv_childLinks_7_0= ruleLink ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:209:1: (lv_childLinks_7_0= ruleLink )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:209:1: (lv_childLinks_7_0= ruleLink )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:210:3: lv_childLinks_7_0= ruleLink
                            	    {
                            	     
                            	    	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_1_4_0_1_1_0(), currentNode); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleTopLevelEntity258);
                            	    lv_childLinks_7_0=ruleLink();
                            	    _fsp--;


                            	    	        if (current==null) {
                            	    	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	    	        }
                            	    	        try {
                            	    	       		add(
                            	    	       			current, 
                            	    	       			"childLinks",
                            	    	        		lv_childLinks_7_0, 
                            	    	        		"Link", 
                            	    	        		currentNode);
                            	    	        } catch (ValueConverterException vce) {
                            	    				handleValueConverterException(vce);
                            	    	        }
                            	    	        currentNode = currentNode.getParent();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;
                            	case 3 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:233:6: ( (lv_childPorts_8_0= rulePort ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:233:6: ( (lv_childPorts_8_0= rulePort ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:234:1: (lv_childPorts_8_0= rulePort )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:234:1: (lv_childPorts_8_0= rulePort )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:235:3: lv_childPorts_8_0= rulePort
                            	    {
                            	     
                            	    	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_1_4_0_1_2_0(), currentNode); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_rulePort_in_ruleTopLevelEntity285);
                            	    lv_childPorts_8_0=rulePort();
                            	    _fsp--;


                            	    	        if (current==null) {
                            	    	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	    	        }
                            	    	        try {
                            	    	       		add(
                            	    	       			current, 
                            	    	       			"childPorts",
                            	    	        		lv_childPorts_8_0, 
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
                            	case 4 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:258:6: ( (lv_childRelations_9_0= ruleRelation ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:258:6: ( (lv_childRelations_9_0= ruleRelation ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:259:1: (lv_childRelations_9_0= ruleRelation )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:259:1: (lv_childRelations_9_0= ruleRelation )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:260:3: lv_childRelations_9_0= ruleRelation
                            	    {
                            	     
                            	    	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_1_4_0_1_3_0(), currentNode); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleRelation_in_ruleTopLevelEntity312);
                            	    lv_childRelations_9_0=ruleRelation();
                            	    _fsp--;


                            	    	        if (current==null) {
                            	    	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	    	        }
                            	    	        try {
                            	    	       		add(
                            	    	       			current, 
                            	    	       			"childRelations",
                            	    	        		lv_childRelations_9_0, 
                            	    	        		"Relation", 
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
                            	    break loop3;
                                }
                            } while (true);

                            match(input,16,FollowSets000.FOLLOW_16_in_ruleTopLevelEntity324); 

                                    createLeafNode(grammarAccess.getTopLevelEntityAccess().getRightCurlyBracketKeyword_1_4_0_2(), null); 
                                

                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:287:7: ';'
                            {
                            match(input,17,FollowSets000.FOLLOW_17_in_ruleTopLevelEntity341); 

                                    createLeafNode(grammarAccess.getTopLevelEntityAccess().getSemicolonKeyword_1_4_1(), null); 
                                

                            }
                            break;

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
    // $ANTLR end ruleTopLevelEntity


    // $ANTLR start entryRuleEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:299:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:300:2: (iv_ruleEntity= ruleEntity EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:301:2: iv_ruleEntity= ruleEntity EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEntityRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_entryRuleEntity380);
            iv_ruleEntity=ruleEntity();
            _fsp--;

             current =iv_ruleEntity; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEntity390); 

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
    // $ANTLR end entryRuleEntity


    // $ANTLR start ruleEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:308:1: ruleEntity returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        EObject lv_annotations_1_0 = null;

        EObject lv_childEntities_6_0 = null;

        EObject lv_childLinks_7_0 = null;

        EObject lv_childPorts_8_0 = null;

        EObject lv_childRelations_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:313:6: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:314:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:314:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:314:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:314:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:315:5: 
            {
             
                    temp=factory.create(grammarAccess.getEntityAccess().getEntityAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getEntityAccess().getEntityAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:325:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_COMMENT_ANNOTATION||LA6_0==22) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:326:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:326:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:327:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleEntity445);
            	    lv_annotations_1_0=ruleAnnotation();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"annotations",
            	    	        		lv_annotations_1_0, 
            	    	        		"Annotation", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match(input,14,FollowSets000.FOLLOW_14_in_ruleEntity456); 

                    createLeafNode(grammarAccess.getEntityAccess().getEntityKeyword_2(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:353:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:354:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:354:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:355:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEntity473); 

            			createLeafNode(grammarAccess.getEntityAccess().getIdIDTerminalRuleCall_3_0(), "id"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"id",
            	        		lv_id_3_0, 
            	        		"ID", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:377:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:378:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:378:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:379:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEntity495); 

                    			createLeafNode(grammarAccess.getEntityAccess().getNameSTRINGTerminalRuleCall_4_0(), "name"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_4_0, 
                    	        		"STRING", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:401:3: ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==15) ) {
                alt9=1;
            }
            else if ( (LA9_0==17) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("401:3: ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:401:4: ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:401:4: ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:401:6: '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEntity513); 

                            createLeafNode(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_0_0(), null); 
                        
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:405:1: ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )*
                    loop8:
                    do {
                        int alt8=5;
                        alt8 = dfa8.predict(input);
                        switch (alt8) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:405:2: ( (lv_childEntities_6_0= ruleEntity ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:405:2: ( (lv_childEntities_6_0= ruleEntity ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:406:1: (lv_childEntities_6_0= ruleEntity )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:406:1: (lv_childEntities_6_0= ruleEntity )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:407:3: lv_childEntities_6_0= ruleEntity
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEntity_in_ruleEntity535);
                    	    lv_childEntities_6_0=ruleEntity();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"childEntities",
                    	    	        		lv_childEntities_6_0, 
                    	    	        		"Entity", 
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
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:430:6: ( (lv_childLinks_7_0= ruleLink ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:430:6: ( (lv_childLinks_7_0= ruleLink ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:431:1: (lv_childLinks_7_0= ruleLink )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:431:1: (lv_childLinks_7_0= ruleLink )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:432:3: lv_childLinks_7_0= ruleLink
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleEntity562);
                    	    lv_childLinks_7_0=ruleLink();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"childLinks",
                    	    	        		lv_childLinks_7_0, 
                    	    	        		"Link", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:455:6: ( (lv_childPorts_8_0= rulePort ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:455:6: ( (lv_childPorts_8_0= rulePort ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:456:1: (lv_childPorts_8_0= rulePort )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:456:1: (lv_childPorts_8_0= rulePort )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:457:3: lv_childPorts_8_0= rulePort
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePort_in_ruleEntity589);
                    	    lv_childPorts_8_0=rulePort();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"childPorts",
                    	    	        		lv_childPorts_8_0, 
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
                    	case 4 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:480:6: ( (lv_childRelations_9_0= ruleRelation ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:480:6: ( (lv_childRelations_9_0= ruleRelation ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:481:1: (lv_childRelations_9_0= ruleRelation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:481:1: (lv_childRelations_9_0= ruleRelation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:482:3: lv_childRelations_9_0= ruleRelation
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleRelation_in_ruleEntity616);
                    	    lv_childRelations_9_0=ruleRelation();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"childRelations",
                    	    	        		lv_childRelations_9_0, 
                    	    	        		"Relation", 
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
                    	    break loop8;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleEntity628); 

                            createLeafNode(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_0_2(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:509:7: ';'
                    {
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEntity645); 

                            createLeafNode(grammarAccess.getEntityAccess().getSemicolonKeyword_5_1(), null); 
                        

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
    // $ANTLR end ruleEntity


    // $ANTLR start entryRuleLink
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:523:1: entryRuleLink returns [EObject current=null] : iv_ruleLink= ruleLink EOF ;
    public final EObject entryRuleLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLink = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:524:2: (iv_ruleLink= ruleLink EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:525:2: iv_ruleLink= ruleLink EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLinkRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_entryRuleLink684);
            iv_ruleLink=ruleLink();
            _fsp--;

             current =iv_ruleLink; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLink694); 

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
    // $ANTLR end entryRuleLink


    // $ANTLR start ruleLink
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:532:1: ruleLink returns [EObject current=null] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' ) ;
    public final EObject ruleLink() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_0=null;
        EObject lv_annotations_0_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:537:6: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:538:1: ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:538:1: ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:538:2: ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:538:2: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_COMMENT_ANNOTATION||LA10_0==22) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:539:1: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:539:1: (lv_annotations_0_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:540:3: lv_annotations_0_0= ruleAnnotation
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getLinkAccess().getAnnotationsAnnotationParserRuleCall_0_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleLink740);
            	    lv_annotations_0_0=ruleAnnotation();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"annotations",
            	    	        		lv_annotations_0_0, 
            	    	        		"Annotation", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match(input,18,FollowSets000.FOLLOW_18_in_ruleLink751); 

                    createLeafNode(grammarAccess.getLinkAccess().getLinkKeyword_1(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:566:1: ( (lv_name_2_0= RULE_STRING ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:567:1: (lv_name_2_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:567:1: (lv_name_2_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:568:3: lv_name_2_0= RULE_STRING
                    {
                    lv_name_2_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleLink768); 

                    			createLeafNode(grammarAccess.getLinkAccess().getNameSTRINGTerminalRuleCall_2_0(), "name"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_2_0, 
                    	        		"STRING", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:590:3: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:591:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:591:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:592:3: RULE_ID
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleLink792); 

            		createLeafNode(grammarAccess.getLinkAccess().getSourceLinkableCrossReference_3_0(), "source"); 
            	

            }


            }

            match(input,19,FollowSets000.FOLLOW_19_in_ruleLink802); 

                    createLeafNode(grammarAccess.getLinkAccess().getToKeyword_4(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:608:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:609:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:609:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:610:3: RULE_ID
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleLink820); 

            		createLeafNode(grammarAccess.getLinkAccess().getTargetLinkableCrossReference_5_0(), "target"); 
            	

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleLink830); 

                    createLeafNode(grammarAccess.getLinkAccess().getSemicolonKeyword_6(), null); 
                

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
    // $ANTLR end ruleLink


    // $ANTLR start entryRulePort
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:634:1: entryRulePort returns [EObject current=null] : iv_rulePort= rulePort EOF ;
    public final EObject entryRulePort() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePort = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:635:2: (iv_rulePort= rulePort EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:636:2: iv_rulePort= rulePort EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPortRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_rulePort_in_entryRulePort866);
            iv_rulePort=rulePort();
            _fsp--;

             current =iv_rulePort; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePort876); 

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
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:643:1: rulePort returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) ;
    public final EObject rulePort() throws RecognitionException {
        EObject current = null;

        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        EObject lv_annotations_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:648:6: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:649:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:649:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:649:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:649:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:650:5: 
            {
             
                    temp=factory.create(grammarAccess.getPortAccess().getPortAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getPortAccess().getPortAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:660:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_COMMENT_ANNOTATION||LA12_0==22) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:661:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:661:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:662:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getAnnotationsAnnotationParserRuleCall_1_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rulePort931);
            	    lv_annotations_1_0=ruleAnnotation();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"annotations",
            	    	        		lv_annotations_1_0, 
            	    	        		"Annotation", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match(input,20,FollowSets000.FOLLOW_20_in_rulePort942); 

                    createLeafNode(grammarAccess.getPortAccess().getPortKeyword_2(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:688:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:689:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:689:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:690:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rulePort959); 

            			createLeafNode(grammarAccess.getPortAccess().getIdIDTerminalRuleCall_3_0(), "id"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"id",
            	        		lv_id_3_0, 
            	        		"ID", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:712:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:713:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:713:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:714:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rulePort981); 

                    			createLeafNode(grammarAccess.getPortAccess().getNameSTRINGTerminalRuleCall_4_0(), "name"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_4_0, 
                    	        		"STRING", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,17,FollowSets000.FOLLOW_17_in_rulePort997); 

                    createLeafNode(grammarAccess.getPortAccess().getSemicolonKeyword_5(), null); 
                

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


    // $ANTLR start entryRuleRelation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:748:1: entryRuleRelation returns [EObject current=null] : iv_ruleRelation= ruleRelation EOF ;
    public final EObject entryRuleRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:749:2: (iv_ruleRelation= ruleRelation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:750:2: iv_ruleRelation= ruleRelation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRelationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_entryRuleRelation1033);
            iv_ruleRelation=ruleRelation();
            _fsp--;

             current =iv_ruleRelation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleRelation1043); 

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
    // $ANTLR end entryRuleRelation


    // $ANTLR start ruleRelation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:757:1: ruleRelation returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) ;
    public final EObject ruleRelation() throws RecognitionException {
        EObject current = null;

        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        EObject lv_annotations_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:762:6: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:763:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:763:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:763:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:763:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:764:5: 
            {
             
                    temp=factory.create(grammarAccess.getRelationAccess().getRelationAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getRelationAccess().getRelationAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:774:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_COMMENT_ANNOTATION||LA14_0==22) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:775:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:775:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:776:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getAnnotationsAnnotationParserRuleCall_1_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleRelation1098);
            	    lv_annotations_1_0=ruleAnnotation();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"annotations",
            	    	        		lv_annotations_1_0, 
            	    	        		"Annotation", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            match(input,21,FollowSets000.FOLLOW_21_in_ruleRelation1109); 

                    createLeafNode(grammarAccess.getRelationAccess().getRelationKeyword_2(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:802:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:803:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:803:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:804:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleRelation1126); 

            			createLeafNode(grammarAccess.getRelationAccess().getIdIDTerminalRuleCall_3_0(), "id"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"id",
            	        		lv_id_3_0, 
            	        		"ID", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:826:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_STRING) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:827:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:827:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:828:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleRelation1148); 

                    			createLeafNode(grammarAccess.getRelationAccess().getNameSTRINGTerminalRuleCall_4_0(), "name"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_4_0, 
                    	        		"STRING", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleRelation1164); 

                    createLeafNode(grammarAccess.getRelationAccess().getSemicolonKeyword_5(), null); 
                

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
    // $ANTLR end ruleRelation


    // $ANTLR start entryRuleAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:862:1: entryRuleAnnotation returns [EObject current=null] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final EObject entryRuleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:863:2: (iv_ruleAnnotation= ruleAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:864:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_entryRuleAnnotation1200);
            iv_ruleAnnotation=ruleAnnotation();
            _fsp--;

             current =iv_ruleAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleAnnotation1210); 

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
    // $ANTLR end entryRuleAnnotation


    // $ANTLR start ruleAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:871:1: ruleAnnotation returns [EObject current=null] : (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation ) ;
    public final EObject ruleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject this_CommentAnnotation_0 = null;

        EObject this_TagAnnotation_1 = null;

        EObject this_KeyStringValueAnnotation_2 = null;

        EObject this_KeyBooleanValueAnnotation_3 = null;

        EObject this_KeyIntValueAnnotation_4 = null;

        EObject this_KeyFloatValueAnnotation_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:876:6: ( (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:877:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:877:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation )
            int alt16=6;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_COMMENT_ANNOTATION) ) {
                alt16=1;
            }
            else if ( (LA16_0==22) ) {
                int LA16_2 = input.LA(2);

                if ( (LA16_2==RULE_ID) ) {
                    switch ( input.LA(3) ) {
                    case RULE_FLOAT:
                        {
                        alt16=6;
                        }
                        break;
                    case RULE_BOOLEAN:
                        {
                        alt16=4;
                        }
                        break;
                    case RULE_INT:
                        {
                        alt16=5;
                        }
                        break;
                    case RULE_ID:
                    case RULE_STRING:
                        {
                        alt16=3;
                        }
                        break;
                    case EOF:
                    case RULE_COMMENT_ANNOTATION:
                    case 14:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                        {
                        alt16=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("877:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation )", 16, 3, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("877:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation )", 16, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("877:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation )", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:878:5: this_CommentAnnotation_0= ruleCommentAnnotation
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_ruleAnnotation1257);
                    this_CommentAnnotation_0=ruleCommentAnnotation();
                    _fsp--;

                     
                            current = this_CommentAnnotation_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:888:5: this_TagAnnotation_1= ruleTagAnnotation
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_ruleAnnotation1284);
                    this_TagAnnotation_1=ruleTagAnnotation();
                    _fsp--;

                     
                            current = this_TagAnnotation_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:898:5: this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation1311);
                    this_KeyStringValueAnnotation_2=ruleKeyStringValueAnnotation();
                    _fsp--;

                     
                            current = this_KeyStringValueAnnotation_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:908:5: this_KeyBooleanValueAnnotation_3= ruleKeyBooleanValueAnnotation
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_3(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation1338);
                    this_KeyBooleanValueAnnotation_3=ruleKeyBooleanValueAnnotation();
                    _fsp--;

                     
                            current = this_KeyBooleanValueAnnotation_3; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:918:5: this_KeyIntValueAnnotation_4= ruleKeyIntValueAnnotation
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_4(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation1365);
                    this_KeyIntValueAnnotation_4=ruleKeyIntValueAnnotation();
                    _fsp--;

                     
                            current = this_KeyIntValueAnnotation_4; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:928:5: this_KeyFloatValueAnnotation_5= ruleKeyFloatValueAnnotation
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_5(), currentNode); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation1392);
                    this_KeyFloatValueAnnotation_5=ruleKeyFloatValueAnnotation();
                    _fsp--;

                     
                            current = this_KeyFloatValueAnnotation_5; 
                            currentNode = currentNode.getParent();
                        

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
    // $ANTLR end ruleAnnotation


    // $ANTLR start entryRuleCommentAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:944:1: entryRuleCommentAnnotation returns [EObject current=null] : iv_ruleCommentAnnotation= ruleCommentAnnotation EOF ;
    public final EObject entryRuleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommentAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:945:2: (iv_ruleCommentAnnotation= ruleCommentAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:946:2: iv_ruleCommentAnnotation= ruleCommentAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCommentAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation1427);
            iv_ruleCommentAnnotation=ruleCommentAnnotation();
            _fsp--;

             current =iv_ruleCommentAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCommentAnnotation1437); 

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
    // $ANTLR end entryRuleCommentAnnotation


    // $ANTLR start ruleCommentAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:953:1: ruleCommentAnnotation returns [EObject current=null] : ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) ;
    public final EObject ruleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:958:6: ( ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:959:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:959:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:960:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:960:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:961:3: lv_value_0_0= RULE_COMMENT_ANNOTATION
            {
            lv_value_0_0=(Token)input.LT(1);
            match(input,RULE_COMMENT_ANNOTATION,FollowSets000.FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation1478); 

            			createLeafNode(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0(), "value"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getCommentAnnotationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"value",
            	        		lv_value_0_0, 
            	        		"COMMENT_ANNOTATION", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
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
    // $ANTLR end ruleCommentAnnotation


    // $ANTLR start entryRuleTagAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:991:1: entryRuleTagAnnotation returns [EObject current=null] : iv_ruleTagAnnotation= ruleTagAnnotation EOF ;
    public final EObject entryRuleTagAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTagAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:992:2: (iv_ruleTagAnnotation= ruleTagAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:993:2: iv_ruleTagAnnotation= ruleTagAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTagAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation1518);
            iv_ruleTagAnnotation=ruleTagAnnotation();
            _fsp--;

             current =iv_ruleTagAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTagAnnotation1528); 

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
    // $ANTLR end entryRuleTagAnnotation


    // $ANTLR start ruleTagAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1000:1: ruleTagAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleTagAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1005:6: ( ( '@' ( (lv_name_1_0= RULE_ID ) ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1006:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1006:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1006:3: '@' ( (lv_name_1_0= RULE_ID ) )
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleTagAnnotation1563); 

                    createLeafNode(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1010:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1011:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1011:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1012:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleTagAnnotation1580); 

            			createLeafNode(grammarAccess.getTagAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getTagAnnotationRule().getType().getClassifier());
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
    // $ANTLR end ruleTagAnnotation


    // $ANTLR start entryRuleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1042:1: entryRuleKeyStringValueAnnotation returns [EObject current=null] : iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF ;
    public final EObject entryRuleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyStringValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1043:2: (iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1044:2: iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getKeyStringValueAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation1621);
            iv_ruleKeyStringValueAnnotation=ruleKeyStringValueAnnotation();
            _fsp--;

             current =iv_ruleKeyStringValueAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation1631); 

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
    // $ANTLR end entryRuleKeyStringValueAnnotation


    // $ANTLR start ruleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1051:1: ruleKeyStringValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= ruleEString ) ) ) ;
    public final EObject ruleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        AntlrDatatypeRuleToken lv_value_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1056:6: ( ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= ruleEString ) ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1057:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= ruleEString ) ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1057:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= ruleEString ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1057:3: '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= ruleEString ) )
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyStringValueAnnotation1666); 

                    createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1061:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1062:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1062:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1063:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleKeyStringValueAnnotation1683); 

            			createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
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

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1085:2: ( (lv_value_2_0= ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1086:1: (lv_value_2_0= ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1086:1: (lv_value_2_0= ruleEString )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1087:3: lv_value_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation1709);
            lv_value_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"value",
            	        		lv_value_2_0, 
            	        		"EString", 
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
    // $ANTLR end ruleKeyStringValueAnnotation


    // $ANTLR start entryRuleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1117:1: entryRuleKeyBooleanValueAnnotation returns [EObject current=null] : iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF ;
    public final EObject entryRuleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyBooleanValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1118:2: (iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1119:2: iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation1745);
            iv_ruleKeyBooleanValueAnnotation=ruleKeyBooleanValueAnnotation();
            _fsp--;

             current =iv_ruleKeyBooleanValueAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation1755); 

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
    // $ANTLR end entryRuleKeyBooleanValueAnnotation


    // $ANTLR start ruleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1126:1: ruleKeyBooleanValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token lv_value_2_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1131:6: ( ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1132:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1132:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1132:3: '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) )
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyBooleanValueAnnotation1790); 

                    createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1136:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1137:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1137:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1138:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleKeyBooleanValueAnnotation1807); 

            			createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
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

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1160:2: ( (lv_value_2_0= RULE_BOOLEAN ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1161:1: (lv_value_2_0= RULE_BOOLEAN )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1161:1: (lv_value_2_0= RULE_BOOLEAN )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1162:3: lv_value_2_0= RULE_BOOLEAN
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation1829); 

            			createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0(), "value"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"value",
            	        		lv_value_2_0, 
            	        		"Boolean", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

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
    // $ANTLR end ruleKeyBooleanValueAnnotation


    // $ANTLR start entryRuleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1192:1: entryRuleKeyIntValueAnnotation returns [EObject current=null] : iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF ;
    public final EObject entryRuleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyIntValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1193:2: (iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1194:2: iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getKeyIntValueAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation1870);
            iv_ruleKeyIntValueAnnotation=ruleKeyIntValueAnnotation();
            _fsp--;

             current =iv_ruleKeyIntValueAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation1880); 

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
    // $ANTLR end entryRuleKeyIntValueAnnotation


    // $ANTLR start ruleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1201:1: ruleKeyIntValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_INT ) ) ) ;
    public final EObject ruleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token lv_value_2_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1206:6: ( ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_INT ) ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1207:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_INT ) ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1207:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_INT ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1207:3: '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_INT ) )
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyIntValueAnnotation1915); 

                    createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1211:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1212:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1212:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1213:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleKeyIntValueAnnotation1932); 

            			createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
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

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1235:2: ( (lv_value_2_0= RULE_INT ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1236:1: (lv_value_2_0= RULE_INT )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1236:1: (lv_value_2_0= RULE_INT )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1237:3: lv_value_2_0= RULE_INT
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation1954); 

            			createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0(), "value"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"value",
            	        		lv_value_2_0, 
            	        		"INT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

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
    // $ANTLR end ruleKeyIntValueAnnotation


    // $ANTLR start entryRuleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1267:1: entryRuleKeyFloatValueAnnotation returns [EObject current=null] : iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF ;
    public final EObject entryRuleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyFloatValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1268:2: (iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1269:2: iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getKeyFloatValueAnnotationRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation1995);
            iv_ruleKeyFloatValueAnnotation=ruleKeyFloatValueAnnotation();
            _fsp--;

             current =iv_ruleKeyFloatValueAnnotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation2005); 

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
    // $ANTLR end entryRuleKeyFloatValueAnnotation


    // $ANTLR start ruleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1276:1: ruleKeyFloatValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ) ;
    public final EObject ruleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token lv_value_2_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1281:6: ( ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1282:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1282:1: ( '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1282:3: '@' ( (lv_name_1_0= RULE_ID ) ) ( (lv_value_2_0= RULE_FLOAT ) )
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyFloatValueAnnotation2040); 

                    createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1286:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1287:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1287:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1288:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleKeyFloatValueAnnotation2057); 

            			createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
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

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1310:2: ( (lv_value_2_0= RULE_FLOAT ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1311:1: (lv_value_2_0= RULE_FLOAT )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1311:1: (lv_value_2_0= RULE_FLOAT )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1312:3: lv_value_2_0= RULE_FLOAT
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_FLOAT,FollowSets000.FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation2079); 

            			createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0(), "value"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"value",
            	        		lv_value_2_0, 
            	        		"Float", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

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
    // $ANTLR end ruleKeyFloatValueAnnotation


    // $ANTLR start entryRuleEString
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1344:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1345:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1346:2: iv_ruleEString= ruleEString EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString2123);
            iv_ruleEString=ruleEString();
            _fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString2134); 

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
    // $ANTLR end entryRuleEString


    // $ANTLR start ruleEString
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1353:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1358:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1359:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1359:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_STRING) ) {
                alt17=1;
            }
            else if ( (LA17_0==RULE_ID) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1359:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1359:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString2174); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1367:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString2200); 

                    		current.merge(this_ID_1);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
                        

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
    // $ANTLR end ruleEString


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA3_eotS =
        "\16\uffff";
    static final String DFA3_eofS =
        "\16\uffff";
    static final String DFA3_minS =
        "\1\6\1\uffff\1\6\1\4\4\uffff\1\4\5\6";
    static final String DFA3_maxS =
        "\1\26\1\uffff\1\26\1\4\4\uffff\6\26";
    static final String DFA3_acceptS =
        "\1\uffff\1\5\2\uffff\1\1\1\2\1\3\1\4\6\uffff";
    static final String DFA3_specialS =
        "\16\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\7\uffff\1\4\1\uffff\1\1\1\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\10",
            "",
            "",
            "",
            "",
            "\1\15\1\14\1\2\1\12\1\11\1\13\4\uffff\1\4\3\uffff\1\5\1\uffff"+
            "\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3"
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "()* loopback of 183:1: ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )*";
        }
    }
    static final String DFA8_eotS =
        "\16\uffff";
    static final String DFA8_eofS =
        "\16\uffff";
    static final String DFA8_minS =
        "\1\6\1\uffff\1\6\1\4\4\uffff\1\4\5\6";
    static final String DFA8_maxS =
        "\1\26\1\uffff\1\26\1\4\4\uffff\6\26";
    static final String DFA8_acceptS =
        "\1\uffff\1\5\2\uffff\1\1\1\2\1\3\1\4\6\uffff";
    static final String DFA8_specialS =
        "\16\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\2\7\uffff\1\4\1\uffff\1\1\1\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\10",
            "",
            "",
            "",
            "",
            "\1\15\1\14\1\2\1\11\1\13\1\12\4\uffff\1\4\3\uffff\1\5\1\uffff"+
            "\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3",
            "\1\2\7\uffff\1\4\3\uffff\1\5\1\uffff\1\6\1\7\1\3"
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "()* loopback of 405:1: ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )*";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTopLevelEntity85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTopLevelEntity141 = new BitSet(new long[]{0x0000000000404040L});
        public static final BitSet FOLLOW_14_in_ruleTopLevelEntity152 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleTopLevelEntity169 = new BitSet(new long[]{0x0000000000028020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleTopLevelEntity191 = new BitSet(new long[]{0x0000000000028000L});
        public static final BitSet FOLLOW_15_in_ruleTopLevelEntity209 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleEntity_in_ruleTopLevelEntity231 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleLink_in_ruleTopLevelEntity258 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_rulePort_in_ruleTopLevelEntity285 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleRelation_in_ruleTopLevelEntity312 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_16_in_ruleTopLevelEntity324 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_ruleTopLevelEntity341 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity380 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEntity390 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleEntity445 = new BitSet(new long[]{0x0000000000404040L});
        public static final BitSet FOLLOW_14_in_ruleEntity456 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEntity473 = new BitSet(new long[]{0x0000000000028020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEntity495 = new BitSet(new long[]{0x0000000000028000L});
        public static final BitSet FOLLOW_15_in_ruleEntity513 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleEntity_in_ruleEntity535 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleLink_in_ruleEntity562 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_rulePort_in_ruleEntity589 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleRelation_in_ruleEntity616 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_16_in_ruleEntity628 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_ruleEntity645 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_entryRuleLink684 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLink694 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleLink740 = new BitSet(new long[]{0x0000000000440040L});
        public static final BitSet FOLLOW_18_in_ruleLink751 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleLink768 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleLink792 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_19_in_ruleLink802 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleLink820 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLink830 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_entryRulePort866 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePort876 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rulePort931 = new BitSet(new long[]{0x0000000000500040L});
        public static final BitSet FOLLOW_20_in_rulePort942 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_rulePort959 = new BitSet(new long[]{0x0000000000020020L});
        public static final BitSet FOLLOW_RULE_STRING_in_rulePort981 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_rulePort997 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_entryRuleRelation1033 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleRelation1043 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleRelation1098 = new BitSet(new long[]{0x0000000000600040L});
        public static final BitSet FOLLOW_21_in_ruleRelation1109 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleRelation1126 = new BitSet(new long[]{0x0000000000020020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleRelation1148 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleRelation1164 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_entryRuleAnnotation1200 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleAnnotation1210 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_ruleAnnotation1257 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_ruleAnnotation1284 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation1311 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation1338 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation1365 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation1392 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation1427 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCommentAnnotation1437 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation1478 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation1518 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTagAnnotation1528 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleTagAnnotation1563 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleTagAnnotation1580 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation1621 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation1631 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyStringValueAnnotation1666 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleKeyStringValueAnnotation1683 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation1709 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation1745 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation1755 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyBooleanValueAnnotation1790 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleKeyBooleanValueAnnotation1807 = new BitSet(new long[]{0x0000000000000080L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation1829 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation1870 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation1880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyIntValueAnnotation1915 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleKeyIntValueAnnotation1932 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation1954 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation1995 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation2005 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyFloatValueAnnotation2040 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleKeyFloatValueAnnotation2057 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation2079 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString2123 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString2134 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString2174 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString2200 = new BitSet(new long[]{0x0000000000000002L});
    }


}